package com.aspire.shakewxpp.wap.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aspire.shakewxpp.wap.cache.RedisHandler;
import com.aspire.shakewxpp.wap.entity.ConfigPojo;
import com.aspire.shakewxpp.wap.entity.DistributeFlowRedEnvelope;
import com.aspire.shakewxpp.wap.entity.DrawResult;
import com.aspire.shakewxpp.wap.entity.User;
import com.aspire.shakewxpp.wap.exception.WxppException;
import com.aspire.shakewxpp.wap.mapper.DistributeFlowRedMapper;
import com.aspire.shakewxpp.wap.service.ConfigService;
import com.aspire.shakewxpp.wap.service.DrawService;
import com.aspire.shakewxpp.wap.service.UserService;
import com.aspire.shakewxpp.wap.util.Constants;
import com.aspire.shakewxpp.wap.util.DateUtil;
import com.aspire.shakewxpp.wap.util.DrawRedisKeyUtil;
import com.aspire.shakewxpp.wap.util.GetConfigFile;
import com.aspire.shakewxpp.wap.util.TimeUtil;

@Service("drawService")
@Scope("prototype")
public class DrawServiceImpl implements DrawService {
	protected static Logger logger =  LoggerFactory.getLogger(UserServiceImpl.class);	
	
	@Resource(name = "configService")
	private ConfigService configService;
	
	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "distributeFlowRedMapper")
	private DistributeFlowRedMapper distributeFlowRedMapper;
	
	/**
	 * 抽流量红包
	 */
	@Transactional(rollbackFor=Exception.class)
	public DrawResult drawFlowRedEnvelope(User user) throws WxppException{
		logger.debug("抽取大红包开始=====;openid："+user.getOpenID()+";手机号："+user.getBindMsisdn());
		
		DrawResult drawResult = new DrawResult();
		RedisHandler redisHandler = RedisHandler.getInstance();
		try{
			GetConfigFile getConfigFile = GetConfigFile.getInstance();
			List<ConfigPojo> configPojoList = getConfigFile.getConfigPojoList();
			if(configPojoList==null){
				configService.getConfigData(getConfigFile.getProperties("provinceConfigTable"));
			}
			configPojoList = getConfigFile.getConfigPojoList();
			if(configPojoList.isEmpty()){
				logger.error("配置表无配置数据");
				throw new WxppException("配置表无配置数据");
			}
				
			
			String activityCode = getConfigFile.getProperties("activityCode");
			for(ConfigPojo configPojo:configPojoList){
				if(activityCode.equals(configPojo.getActivityName())){
					//若是测试模式
					if(configPojo.getActivityStatus()==DrawRedisKeyUtil.DRAW_MODUL_TYPE_TEST){
						int count_ = distributeFlowRedMapper.checkTestUser(user.getOpenID());
						if(count_ < 1){
							drawResult.setFailType(Constants.DRAW_FLOW_RED_TEST);
							return drawResult;
						}
					}
					
					//判断是否当天已领取
					String drawedFlowUser = DrawRedisKeyUtil.getDrawedFlowUser(user.getOpenID(),configPojo.getActivityStatus());
					long drawedFlowUserResult = redisHandler.listSize(drawedFlowUser);
					if(configPojo.getMaxUserCount() <= Integer.parseInt(drawedFlowUserResult+"")){
						drawResult.setSubRedFolwNum(0);
						drawResult.setFailType(Constants.DRAW_FLOW_RED_DONE);
						return drawResult;
					}
					
					//判断活动是否正在有效时段
					long maxFreCount = checkTime(configPojo);
					if(maxFreCount == 0){
						drawResult.setSubRedFolwNum(0);
						drawResult.setFailType(Constants.DRAW_FLOW_RED_READY);
						return drawResult;
					}
					
					//抽到的流量红包
					drawFolwRed(user,maxFreCount,configPojo,drawResult);	
					if(drawResult.getSubRedFolwNum() == 0){
						//来晚了
						drawResult.setFailType(Constants.DRAW_FLOW_RED_OVER);
					}else{
						logger.debug("抽取大红包成功=====;openid："+user.getOpenID()+";大红包个数："+drawResult.getSubRedFolwNum());
						drawResult.setFailType(Constants.DRAW_FLOW_RED_SUCCESS);
					}
				}
				break;
			}
		}catch(Exception e){
			logger.error("抽流量红包出现异常",e);
			throw new WxppException("抽流量红包出现异常");
		}
		return drawResult;
	}
	
	/**
	 * 
	 * @param openid
	 * @param configPojoList
	 * @return
	 * @throws Exception
	 */
	private void drawFolwRed(User user,long maxFreCount,ConfigPojo configPojo,DrawResult drawResult) throws Exception{
		RedisHandler redisHandler = RedisHandler.getInstance();
		long drawResultNum=0;

		//当天已领取的红包数量
		String drawedFlowNumListKey = DrawRedisKeyUtil.getDrawedFlowNumList(configPojo.getActivityStatus());
		long drawedFlowNum = redisHandler.listSize(drawedFlowNumListKey);
		
		if(maxFreCount <= drawedFlowNum){
			drawResult.setSubRedFolwNum(0);
			return;
		}else{
			//此处实现注意同步处理
			String subFreCountPeruser = configPojo.getSubFreCountPeruser();
			String[] array = subFreCountPeruser.split("-");
			if(array.length!=2){
				throw new Exception("抽奖的小红包范围subFreCountPeruser不正确！");
			}
			
			//本次抽到红包数
			int drawNum = TimeUtil.getRandom(Integer.parseInt(array[0]),Integer.parseInt(array[1]));
			//实际抽到的红包数
			drawResultNum = redisHandler.synPushObjectForNum(drawNum,drawedFlowNumListKey, "y", maxFreCount, Constants.DAY_SEC);
			
			if(drawResultNum > 0){
				Integer freId = distributeFlowRedMapper.getFreId();
				drawResult.setFreId(freId);
				drawResult.setSubRedFolwNum(drawResultNum);
				for(int i=1;i<drawResultNum+1;i++){
					//入库
					DistributeFlowRedEnvelope distributeFlowRedEnvelope = new DistributeFlowRedEnvelope();
					distributeFlowRedEnvelope.setOpenid(user.getOpenID());
					distributeFlowRedEnvelope.setBindMsisdn(user.getBindMsisdn());
					distributeFlowRedEnvelope.setActivityId(configPojo.getActivityId());
					distributeFlowRedEnvelope.setFreId(freId);
					distributeFlowRedEnvelope.setSubFreId(i);
					distributeFlowRedEnvelope.setFreExpireDays(configPojo.getFreExpireDays());
					distributeFlowRedEnvelope.setFreFromSource(Constants.FRE_FROM_SOURCE_DARW);
					distributeFlowRedEnvelope.setActivityId(configPojo.getActivityId());
					distributeFlowRedEnvelope.setApplyProvinceId(configPojo.getApplyProvinceId());
					
					addDistributeFlowRedEnvelope(distributeFlowRedEnvelope);
				}
				
				//抽到大红包不需要发通知消息
			}
		}
		
		//设置为已抽用户
		String drawedFlowUser = DrawRedisKeyUtil.getDrawedFlowUser(user.getOpenID(),configPojo.getActivityStatus());

		redisHandler.pushObject(drawedFlowUser, drawResultNum+"", Constants.DAY_SEC);
	}
	
	
	/**
	 * 绑定手机号、开通流量套餐 赠送流量红包
	 */
	@Transactional(rollbackFor=Exception.class) 
	public int addDistributeFlowRedEnvelope(String openid,int addType) throws Exception{
		DistributeFlowRedEnvelope distributeFlowRedEnvelope = new DistributeFlowRedEnvelope();
		distributeFlowRedEnvelope.setOpenid(openid);
		distributeFlowRedEnvelope.setFreFromSource(addType);
		
		//只有第一次绑定手机号 送大红包
		if(Constants.FRE_FROM_SOURCE_BIND == addType){
			Integer count = distributeFlowRedMapper.checkSendFolwDone(distributeFlowRedEnvelope);
			if(count>0){
				throw new WxppException("该业务,用户已赠送过流量红包");
			}
		}
		
		GetConfigFile getConfigFile = GetConfigFile.getInstance();
		List<ConfigPojo> configPojoList = getConfigFile.getConfigPojoList();
		if(configPojoList==null){
			configService.getConfigData(getConfigFile.getProperties("provinceConfigTable"));
		}
		configPojoList = getConfigFile.getConfigPojoList();
		if(configPojoList.isEmpty()){
			logger.error("配置表无配置数据");
			throw new Exception("配置表无配置数据");
		}
		int addNum = 0;
		//取用户手机号
		User _user = new User();
		_user.setOpenID(openid);
		User user = userService.getUser(_user);
		
		Integer freId = distributeFlowRedMapper.getFreId();
		String activityCode = getConfigFile.getProperties("activityCode");
		for(ConfigPojo configPojo:configPojoList){
			if(activityCode.equals(configPojo.getActivityName())){
				if(Constants.FRE_FROM_SOURCE_BIND == addType){
					addNum = configPojo.getBindSubFreCountPeruser();
				}else if(Constants.FRE_FROM_SOURCE_BUY== addType){
					addNum = configPojo.getFlowpkgSubFreCountPeruser();
				}
				
				for(int i=1;i<addNum+1;i++){
					DistributeFlowRedEnvelope _distributeFlowRedEnvelope = new DistributeFlowRedEnvelope();
					_distributeFlowRedEnvelope.setOpenid(openid);
					_distributeFlowRedEnvelope.setBindMsisdn(user.getBindMsisdn());
					_distributeFlowRedEnvelope.setActivityId(configPojo.getActivityId());
					_distributeFlowRedEnvelope.setFreId(freId);
					_distributeFlowRedEnvelope.setSubFreId(i);
					_distributeFlowRedEnvelope.setFreExpireDays(configPojo.getFreExpireDays());
					_distributeFlowRedEnvelope.setFreFromSource(addType);
					_distributeFlowRedEnvelope.setActivityId(configPojo.getActivityId());
					_distributeFlowRedEnvelope.setApplyProvinceId(configPojo.getApplyProvinceId());
					
					addDistributeFlowRedEnvelope(_distributeFlowRedEnvelope);
				}	
				break;
			}
		}	
		
		return freId;
	}
	
	private void addDistributeFlowRedEnvelope(DistributeFlowRedEnvelope distributeFlowRedEnvelope) throws Exception{
		try{
			distributeFlowRedMapper.addDistributeFlowRedEnvelope(distributeFlowRedEnvelope);
		}catch(Exception e){
			logger.error("新增抽取流量红包数据出现异常",e);
			throw new Exception("新增大红包出现异常,openid:"+distributeFlowRedEnvelope.getOpenid()+",freFromSource:"+distributeFlowRedEnvelope.getFreFromSource());
		}
	}
	
	/*
	 * 检查抽奖是否开始，返回到目前应该发放的红包数
	 */
	private long checkTime(ConfigPojo configPojo){
		String timeRange = configPojo.getTimeRange();
		String freCount = configPojo.getFreCount();
		
		String[] arrayTimeRange = timeRange.split("-");
		String[] arrayFreCount= freCount.split("-");
		
		Long result = Long.parseLong("0");
		
		String currentDate_hh = DateUtil.getCurDate("HH:mm");
		for(int i=0;i<arrayTimeRange.length;i++){
			if(currentDate_hh.compareTo(arrayTimeRange[i]) > 0 ){
				result += Long.parseLong(arrayFreCount[i]);
			}else{
				break;
			}
		}
		return result;
	}
}
