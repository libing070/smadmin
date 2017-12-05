package com.aspire.shakewxpp.wap.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aspire.shakewxpp.wap.cache.RedisHandler;
import com.aspire.shakewxpp.wap.entity.ConfigPojo;
import com.aspire.shakewxpp.wap.entity.DistributeFlowRedEnvelope;
import com.aspire.shakewxpp.wap.entity.GrabFlowSubRedEnvelope;
import com.aspire.shakewxpp.wap.entity.QiangResult;
import com.aspire.shakewxpp.wap.entity.User;
import com.aspire.shakewxpp.wap.entity.UserNotifyPojo;
import com.aspire.shakewxpp.wap.exception.WxppException;
import com.aspire.shakewxpp.wap.mapper.DistributeFlowRedMapper;
import com.aspire.shakewxpp.wap.mapper.QiangFlowPackageMapper;
import com.aspire.shakewxpp.wap.service.ConfigService;
import com.aspire.shakewxpp.wap.service.QiangFlowService;
import com.aspire.shakewxpp.wap.service.UserNotifyService;
import com.aspire.shakewxpp.wap.util.Constants;
import com.aspire.shakewxpp.wap.util.DateUtil;
import com.aspire.shakewxpp.wap.util.DrawRedisKeyUtil;
import com.aspire.shakewxpp.wap.util.GetConfigFile;
import com.aspire.shakewxpp.wap.util.TimeUtil;

@Service("qiangFlowService")
@Scope("prototype")
public class QiangFlowServiceImpl implements QiangFlowService {
	protected static Logger logger =  LoggerFactory.getLogger(QiangFlowServiceImpl.class);	
	
	@Resource(name = "configService")
	private ConfigService configService;
	
	@Resource(name = "qiangFlowPackageMapper")
	private QiangFlowPackageMapper qiangFlowPackageMapper;
	
	@Resource(name = "distributeFlowRedMapper")
	private DistributeFlowRedMapper distributeFlowRedMapper;
	
	@Resource(name = "userNotifyService")
	private UserNotifyService userNotifyService;
	
	/**
	 * 抢流量小红包
	 */
	@Transactional(rollbackFor=Exception.class)
	public QiangResult qiangFlowRedEnvelope(User user,int freId) throws WxppException{
		logger.info("用户openid："+user.getOpenID()+",抢流量红包开始；大红包ID："+freId);
		QiangResult qiangResult = new QiangResult();
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
					//从数据库中查询该大红包中小红包的数量
					Integer subCount = qiangFlowPackageMapper.queryBigFlowPackageInfo(freId);
					if(subCount==0){
						qiangResult.setFailType(Constants.RED_BAG_PAST_DUE);//红包已过期
						return qiangResult;
					}
					
					//判断用户是否已成功抢过
					GrabFlowSubRedEnvelope grabFlowSubRedEnvelope = new GrabFlowSubRedEnvelope();
					grabFlowSubRedEnvelope.setFreId(freId);
					grabFlowSubRedEnvelope.setOpenId(user.getOpenID());
					
					//已经被抢的小红包数
					Integer qiangCount = qiangFlowPackageMapper.queryUserQiangSubFlowByFreId(grabFlowSubRedEnvelope);
					if(qiangCount>0){
						qiangResult.setFailType(Constants.RED_BAG_GAIN);//红包已抢过
						return qiangResult;
					}
					
					//获取该大红包被抢的list在redis中的key
					String bigFlowRedPackageInfo = DrawRedisKeyUtil.getBigFlowRedPackageInfo(freId,configPojo.getActivityStatus());
					long index = redisHandler.synPushObjectT(bigFlowRedPackageInfo,"Y",subCount,Constants.DAY_SEC*configPojo.getFreExpireDays());
					if(index>0){		
						float setSubFreFlowCurrency = qiangSuccess(user,freId,configPojo,index,redisHandler,bigFlowRedPackageInfo,subCount);
						qiangResult.setFolwCoinNum(setSubFreFlowCurrency);
						qiangResult.setFailType(Constants.RED_BAG_SUCCESS);
						logger.info("用户openid："+user.getOpenID()+",抢到一个流量红包：大红包ID："+freId+",小红包id:"+index);
					}else{
						qiangResult.setFailType(Constants.RED_BAG_NONE);//红包已抢完
					}
				}
				break;
			}
		}catch(Exception e){
			logger.error("抽流量红包出现异常",e);
			throw new WxppException("抽流量红包出现异常");
		}
		return qiangResult;
	}
	
	
	/**
	 * 抢到小红包的业务处理
	 * @param user
	 * @param freId
	 * @param configPojo
	 * @param index
	 * @param qiangResult
	 * @param redisHandler
	 * @param bigFlowRedPackageInfo
	 * @param subCount
	 * @throws Exception
	 */
	private float qiangSuccess(User user,int freId,ConfigPojo configPojo,long index,RedisHandler redisHandler,String bigFlowRedPackageInfo,int subCount) throws Exception{	
		float setSubFreFlowCurrency = 0.1f;
		try{
			//入库
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope = addQiangFlow(user,freId,configPojo,index);
			setSubFreFlowCurrency = grabFlowSubRedEnvelope.getSubFreFlowCurrency();
		}catch(WxppException e){
			logger.error("成功抢到流量红包后入库出现异常",e);
			redisHandler.popObjectT(bigFlowRedPackageInfo);
			throw e;
		}catch(Exception e){
			logger.error("抢到小红包后调用该接口向流量汇发送赠送同步请求出现异常",e);
			redisHandler.popObjectT(bigFlowRedPackageInfo);
			throw e;
		}
		
		//通知成功抢到小红包
		if(Constants.USER_SUBSCRIPT.equals(user.getSubscribe()) || StringUtils.isNotEmpty(user.getBindMsisdn())){
			UserNotifyPojo userNotifyPojoQiang = new UserNotifyPojo();
			userNotifyPojoQiang.setSmsType(Constants.SMS_TYPE_SUB_FRE);
			if(Constants.USER_SUBSCRIPT.equals(user.getSubscribe())){
				userNotifyPojoQiang.setOpenid(user.getOpenID());
			}else{
				userNotifyPojoQiang.setMsisdn(user.getBindMsisdn());
			}

			userNotifyPojoQiang.setFreId(freId);
			userNotifyPojoQiang.setSubFreId(Integer.parseInt(index+""));
			userNotifyPojoQiang.setWeixinAppNo(user.getWeixinAppNo());
			
			userNotifyService.addUserNotifyData(userNotifyPojoQiang);
		}
		
		String shareOpenid = distributeFlowRedMapper.queryOpenidByFreid(freId);
		//前两个通知分享人
		if(index <= 2){
			//抽到大红包需要通知用户，往SMS_NOTIFY表插入一条记录
			UserNotifyPojo userNotifyPojo = new UserNotifyPojo();
			userNotifyPojo.setSmsType(Constants.SMS_TYPE_SHARE_ONE);
			userNotifyPojo.setOpenid(shareOpenid);
			userNotifyPojo.setFreId(freId);
			userNotifyPojo.setSubFreId(Integer.parseInt(index+""));
			userNotifyPojo.setWeixinAppNo(user.getWeixinAppNo());
			if(index == 2){
				userNotifyPojo.setSmsType(Constants.SMS_TYPE_SHARE_TWO);
			}
			userNotifyService.addUserNotifyData(userNotifyPojo);
		}

		
		if(Integer.parseInt(index+"") == Integer.parseInt(subCount+"")){
			try{
				//流量小红包被抢完,给分享人送一个流量红包
				addFlowForSuccShare(shareOpenid,configPojo);
				
				//流量小红包被抢完,需通知分享人
				UserNotifyPojo userNotifyPojo = new UserNotifyPojo();
				userNotifyPojo.setSmsType(Constants.SMS_TYPE_SHARE_OVER);
				userNotifyPojo.setOpenid(shareOpenid);
				userNotifyPojo.setFreId(freId);
				userNotifyPojo.setSubFreId(Integer.parseInt(index+""));
				userNotifyPojo.setWeixinAppNo(user.getWeixinAppNo());
				
				userNotifyService.addUserNotifyData(userNotifyPojo);
			}catch(Exception e){
				logger.error("分享的小红包被抢完后,赠送分享人一个小红包出现异常",e);
				redisHandler.popObjectT(bigFlowRedPackageInfo);
				throw e;
			}
		}
		return setSubFreFlowCurrency;
	}
	
	/**
	 * 小红包入库
	 * @param openid
	 * @param freId
	 * @param configPojo
	 * @param subFreId
	 * @return
	 * @throws WxppException
	 */
	private GrabFlowSubRedEnvelope addQiangFlow(User user,int freId,ConfigPojo configPojo,long subFreId) throws WxppException{
		GrabFlowSubRedEnvelope grabFlowSubRedEnvelope = new GrabFlowSubRedEnvelope();
		try{
			//小红包中的流量币数量
			float flowCoinCount = 0;
			String upperLimit="";
			String[] arrayUpper=null;
			
			String lowerLimit="";
			String[] arrayLower=null;
			
			String upperLimitPercent="";
			String lowerLimitPercent="";
			
			if(Constants.USER_SUBSCRIPT_TEMP.equals(user.getSubscribe()) || StringUtils.isEmpty(user.getBindMsisdn())){//未绑定用户
			    upperLimit = configPojo.getNonBindUpperLimit();
				arrayUpper = upperLimit.split("-");
				
				lowerLimit = configPojo.getNonBindLowerLimit();
				arrayLower = lowerLimit.split("-");
				
				upperLimitPercent = configPojo.getNonBindUpperLimitPercent();
				lowerLimitPercent = configPojo.getNonBindLowerLimitPercent();
			}else{//已绑定用户
				upperLimit = configPojo.getBindUpperLimit();
				arrayUpper = upperLimit.split("-");
				
				lowerLimit = configPojo.getBindLowerLimit();
				arrayLower = lowerLimit.split("-");
				
				upperLimitPercent = configPojo.getBindUpperLimitPercent();
				lowerLimitPercent = configPojo.getBindLowerLimitPercent();
			}
			
			float lowPercent = Float.parseFloat(upperLimitPercent) < Float.parseFloat(lowerLimitPercent) ? Float.parseFloat(upperLimitPercent):Float.parseFloat(lowerLimitPercent);
			//随机数(总共的功率是100)
			int random = TimeUtil.getRandom(1,100);
			
			if(random<=lowPercent*100){
				if(Float.parseFloat(upperLimitPercent) < Float.parseFloat(lowerLimitPercent)){
					//落到了nonBindUpperLimit的区间（为用整型随机数,此处把小数的范围*100变为整型）
					flowCoinCount = TimeUtil.getRandom((int)(Float.parseFloat(arrayUpper[0])*100),(int)(Float.parseFloat(arrayUpper[1])*100));
				}else{
					//落到了nonBindLowerLimit的区间
					flowCoinCount = TimeUtil.getRandom((int)(Float.parseFloat(arrayLower[0])*100),(int)(Float.parseFloat(arrayLower[1])*100));
				}
			}else{
				if(Float.parseFloat(upperLimitPercent) > Float.parseFloat(lowerLimitPercent)){
					//落到了nonBindUpperLimit的区间
					flowCoinCount = TimeUtil.getRandom((int)(Float.parseFloat(arrayUpper[0])*100),(int)(Float.parseFloat(arrayUpper[1])*100));
				}else{
					//落到了nonBindLowerLimit的区间
					flowCoinCount = TimeUtil.getRandom((int)(Float.parseFloat(arrayLower[0])*100),(int)(Float.parseFloat(arrayLower[1])*100));
				}
			}
			//由于取随机数时*100,这里*0.01还原
			flowCoinCount = Float.valueOf((flowCoinCount*0.01)+"");
			//小红包入库
			if(flowCoinCount<=0){
				flowCoinCount = 0.1f;
			}
			grabFlowSubRedEnvelope.setFreId(freId);
			grabFlowSubRedEnvelope.setOpenId(user.getOpenID());
			grabFlowSubRedEnvelope.setSubFreId(Integer.parseInt(subFreId+""));
			grabFlowSubRedEnvelope.setBindMsisdn(user.getBindMsisdn());
			grabFlowSubRedEnvelope.setSubFreFlowCurrency(flowCoinCount);
			grabFlowSubRedEnvelope.setSubFreExchangeDays(configPojo.getSubFreExchangeDays());
			grabFlowSubRedEnvelope.setActivityId(configPojo.getActivityId()+"");
			grabFlowSubRedEnvelope.setApplyProvinceId(configPojo.getApplyProvinceId());
			grabFlowSubRedEnvelope.setExpiredTime_toString(DateUtil.getDateOfDay(DateUtil.getCurDateTime(),configPojo.getSubFreExchangeDays(),DateUtil.yyyy_MM_dd_HH_mm_ss_EN));
			
			qiangFlowPackageMapper.addQiangSubFlow(grabFlowSubRedEnvelope);
		}catch(Exception e){
			throw new WxppException(e.getMessage());
		}
		
		return grabFlowSubRedEnvelope;
	}
	
	
	/**
	 * 分享的小红包被抢完后,赠送分享人一个小红包
	 * @param user
	 * @param freId
	 * @param configPojo
	 * @param subFreId
	 * @throws WxppException
	 */
	private void addFlowForSuccShare(String shareOpenid,ConfigPojo configPojo) throws Exception{
		logger.debug("红包被抢完,奖励开始=======");
		String freTotalSharedGains = configPojo.getFreTotalSharedGains();
		if(StringUtils.isEmpty(freTotalSharedGains)){
			logger.error("分享的小红包被抢完后,赠送分享人红包流量币配置项异常");
			return;
		}
		int shareGains = Integer.parseInt(freTotalSharedGains);
		if(shareGains>0){
			Integer newfreId = distributeFlowRedMapper.getFreId();
			
			DistributeFlowRedEnvelope _distributeFlowRedEnvelope = new DistributeFlowRedEnvelope();
			_distributeFlowRedEnvelope.setOpenid(shareOpenid);
			_distributeFlowRedEnvelope.setActivityId(configPojo.getActivityId());
			_distributeFlowRedEnvelope.setFreId(newfreId);
			_distributeFlowRedEnvelope.setSubFreId(1);
			_distributeFlowRedEnvelope.setFreFlowCurrency(Integer.parseInt(freTotalSharedGains));
			_distributeFlowRedEnvelope.setFreExpireDays(configPojo.getFreExpireDays());
			_distributeFlowRedEnvelope.setFreFromSource(Constants.FRE_FROM_SOURCE_JIANGLI);
			_distributeFlowRedEnvelope.setActivityId(configPojo.getActivityId());
			_distributeFlowRedEnvelope.setApplyProvinceId(configPojo.getApplyProvinceId());
			_distributeFlowRedEnvelope.setSubFreExchangeDays(configPojo.getSubFreExchangeDays());
			distributeFlowRedMapper.addJangliFlowRedEnvelope(_distributeFlowRedEnvelope);
			logger.debug("红包被抢完,奖励结束=======");
		}
		
	}
}
