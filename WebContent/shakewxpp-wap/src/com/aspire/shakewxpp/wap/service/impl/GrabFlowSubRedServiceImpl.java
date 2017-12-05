package com.aspire.shakewxpp.wap.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aspire.shakewxpp.wap.entity.DistributeFlowRedEnvelope;
import com.aspire.shakewxpp.wap.entity.GrabFlowSubRedEnvelope;
import com.aspire.shakewxpp.wap.entity.User;
import com.aspire.shakewxpp.wap.entity.UserNotifyPojo;
import com.aspire.shakewxpp.wap.exception.WxppException;
import com.aspire.shakewxpp.wap.mapper.GrabFlowSubRedEnvelopeMapper;
import com.aspire.shakewxpp.wap.mapper.UserMapper;
import com.aspire.shakewxpp.wap.service.GrabFlowSubRedService;
import com.aspire.shakewxpp.wap.service.UserNotifyService;
import com.aspire.shakewxpp.wap.service.UserService;
import com.aspire.shakewxpp.wap.util.Constants;

@Service("grabFlowSubRedService")
public class GrabFlowSubRedServiceImpl implements GrabFlowSubRedService {

	protected static Logger logger =  LoggerFactory.getLogger(GrabFlowSubRedServiceImpl.class);

	@Resource(name = "grabFlowSubRedEnvelopeMapper")
	private GrabFlowSubRedEnvelopeMapper grabFlowSubRedEnvelopeMapper;
	
	@Resource(name = "userMapper")
	private UserMapper userMapper;
	
	@Resource(name = "userNotifyService")
	private UserNotifyService userNotifyService;
	
	
	@Resource(name = "userService")
	private UserService userService;
	/**
	 * 查询判断用户是否抢小红包信息
	 * @author liuyao 2014-08-26
	 */
	@Override
	public Integer searchRedBagResultInfo(GrabFlowSubRedEnvelope grabFlowSubRedEnvelope)
			throws WxppException {
		try {
			//判断是否过期
			int pastCount = grabFlowSubRedEnvelopeMapper.searchRedBagPastDueCount(grabFlowSubRedEnvelope);
			if(pastCount>0){
				return Constants.RED_BAG_PAST_DUE;
			}
			
			//判断是否已经抢过
			int count = grabFlowSubRedEnvelopeMapper.searchRedBagQiangGuoCount(grabFlowSubRedEnvelope);
			if(count>0){
				return Constants.RED_BAG_GAIN;
			}
			
			//查询大红包里的小红包个数
			int bigRedBagCount = grabFlowSubRedEnvelopeMapper.searchBigRedBagCount(grabFlowSubRedEnvelope);
			//查询小红包剩余几个

			int haveRedBagCount = grabFlowSubRedEnvelopeMapper.searchHaveRedBagCount(grabFlowSubRedEnvelope);
			//判断是否被抢完

			if(bigRedBagCount==haveRedBagCount){
				return Constants.RED_BAG_NONE;
			}
			
		} catch (Exception e) {
			logger.error("判断用户抢小红包信息异常!",e);
			throw new WxppException("判断用户抢小红包信息异常!");
		}
		return Constants.RED_BAG_SUCCESS;
	}

	/**
	 * 新增抢小红包记录
	 * @author liuyao 2014-08-26
	 */
	@Transactional(rollbackFor=Exception.class) 
	public void saveRedBagInfo(GrabFlowSubRedEnvelope grabFlowSubRedEnvelope)
			throws WxppException {
		try {
			/**
			 * 保存抢到小红包用户的手机号码至小红包表
			 */
			grabFlowSubRedEnvelopeMapper.saveRedBagInfo(grabFlowSubRedEnvelope);
		
			Integer freId = grabFlowSubRedEnvelope.getFreId();
			Integer subFreId = grabFlowSubRedEnvelope.getSubFreId();
			String bindMsisdn = grabFlowSubRedEnvelope.getBindMsisdn();
			
			
			/**
			 * 未绑定用户,先判断是否是关注用户；若是关注用户，仅更新号码
			 */
			User user = new User();
			user.setOpenID(grabFlowSubRedEnvelope.getOpenId());
			User userPara = userService.getUser(user);
			if(userPara==null){
				user.setBindMsisdn(bindMsisdn);
				user.setSubscribe(Constants.USER_SUBSCRIPT_TEMP);
				user.setWeixinAppNo(grabFlowSubRedEnvelope.getWeixinAppNo());	
			}else{
				userPara.setBindMsisdn(bindMsisdn);
				user = userPara;
			}
			userMapper.insertOpenId(user);
			
			
			
			/**
			 * 未绑定未关注用户，录入手机号码后才可以发送通知提醒
			 */
			UserNotifyPojo userNotifyPojoQiang = new UserNotifyPojo();
			userNotifyPojoQiang.setSmsType(Constants.SMS_TYPE_SUB_FRE);
			userNotifyPojoQiang.setMsisdn(bindMsisdn);
			userNotifyPojoQiang.setFreId(freId);
			userNotifyPojoQiang.setSubFreId(subFreId);
			userNotifyPojoQiang.setWeixinAppNo(grabFlowSubRedEnvelope.getWeixinAppNo());
			userNotifyService.addUserNotifyData(userNotifyPojoQiang);
			
		} catch (WxppException e) {
			logger.error("未绑定用户录入记录异常!",e);
			throw new WxppException("未绑定用户录入记录异常!");
		} catch (Exception e) {
			logger.error("同步到流量汇异常!",e);
			throw new WxppException("同步到流量汇异常!");
		}
	}

	/**
	 * 查询用户已抢到的小红包

	 * @author liuyao 2014-08-26
	 */
	@Override
	public List<GrabFlowSubRedEnvelope> searchUserSnagRedBagList(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException {
		try {
			return grabFlowSubRedEnvelopeMapper.searchUserSnagRedBagList(grabFlowSubRedEnvelope);
		} catch (Exception e) {
			logger.error("查询用户已抢到小红包异常!",e);
			throw new WxppException("查询用户已抢到小红包异常!");
		}
	}

	/**
	 * 查询用户已抢到的小红包总数
	 * @author liuyao 2014-08-28
	 */
	@Override
	public Integer searchUserSnagRedBagListCount(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException {
		try {
			return grabFlowSubRedEnvelopeMapper.searchUserSnagRedBagListCount(grabFlowSubRedEnvelope);
		} catch (Exception e) {
			logger.error("查询用户已抢到小红包异常!",e);
			throw new WxppException("查询用户已抢到小红包异常!");
		}
	}

	/**
	 * 查询朋友所抢红包

	 * @author liuyao 2014-08-28
	 */
	@Override
	public List<GrabFlowSubRedEnvelope> searchFriendSubRedList(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException {
		try {
			return grabFlowSubRedEnvelopeMapper.searchFriendSubRedList(grabFlowSubRedEnvelope);
		} catch (Exception e) {
			logger.error("查询朋友所抢红包异常!",e);
			throw new WxppException("查询朋友所抢红包异常!");
		}
	}

	/**
	 * 查询用户所抢红包流量币总和
	 * @author liuyao 2014-08-28
	 */
	@Override
	public Float searchUserSumSubRedInfo(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException {
		try {
			return grabFlowSubRedEnvelopeMapper
					.searchUserSumSubRedInfo(grabFlowSubRedEnvelope);
		} catch (Exception e) {
			logger.error("查询用户所抢红包流量币总和异常!", e);
			throw new WxppException("查询用户所抢红包流量币总和异常!");
		}
	}

	/**
	 * 查询发红包用户信息

	 * @author liuyao 2014-08-28
	 */
	@Override
	public DistributeFlowRedEnvelope searchDistributeFlowRedInfo(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope)
			throws WxppException {
		try {
			return grabFlowSubRedEnvelopeMapper
					.searchDistributeFlowRedInfo(grabFlowSubRedEnvelope);
		} catch (Exception e) {
			logger.error("查询发红包用户信息异常!", e);
			throw new WxppException("查询发红包用户信息异常!");
		}
	}

	/**
	 * 查询用户抢到流量币和规定领取时间
	 * @author liuyao 2014-08-28
	 */
	@Override
	public GrabFlowSubRedEnvelope searchRedBagQiangGuoInfo(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException {
		try {
			return grabFlowSubRedEnvelopeMapper.searchRedBagQiangGuoInfo(grabFlowSubRedEnvelope);
		} catch (Exception e) {
			logger.error("查询用户抢到流量币和规定领取时间异常!",e);
			throw new WxppException("查询用户抢到流量币和规定领取时间异常!");
		}
	}
	
	/**
	 * 判断用户领取抢到小红包时，是否已经抢过此小红包
	 */
	@Override
	public Integer searchRedBagQiangGuoCount(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException {
		try {
			return grabFlowSubRedEnvelopeMapper.searchRedBagQiangGuoCount(grabFlowSubRedEnvelope);
		} catch (Exception e) {
			logger.error("查询用户是否已经抢过大红包"+grabFlowSubRedEnvelope.getFreId()+"里的小红包异常!");
			throw new WxppException("查询用户是否已经抢过大红包"+grabFlowSubRedEnvelope.getFreId()+"里的小红包异常!");
		}
	}

	/**
	 * 查询每个大红包分享的小红包的数量
	 * @param freId
	 * @return
	 * @throws WxppException
	 */
	public int querySmallCount(GrabFlowSubRedEnvelope gfs)throws WxppException{
		int smallCount=0;
		try{
			smallCount = grabFlowSubRedEnvelopeMapper.querySmallCount(gfs);
		}catch(Exception e){
			logger.error("查询每个大红包分享的小红包的数量异常!",e);
			throw new WxppException("查询每个大红包分享的小红包的数量异常!");
		}
		return smallCount;
	}
	
	/**
	 * 查询领取的小红包信息
	 * @param freId
	 * @return
	 * @throws WxppException
	 */
	public List<GrabFlowSubRedEnvelope> queryLingQu(GrabFlowSubRedEnvelope gfs) throws WxppException{
		List<GrabFlowSubRedEnvelope> listGfe=null;
		try{
			listGfe = grabFlowSubRedEnvelopeMapper.queryLingQu(gfs);
		}catch(Exception e){
			logger.error("查询领取的小红包信息异常!",e);
			throw new WxppException("查询领取的小红包信息异常!");
		}
		return listGfe;
	}
	
	/**
	 * 查询领取的小红包的流量币总和
	 * @param freId
	 * @return
	 * @throws WxppException
	 */
	public Float queryFlowSum(Integer freId)throws WxppException{
		Float flowSumCount=0f;
		try{
			flowSumCount = grabFlowSubRedEnvelopeMapper.queryFlowSum(freId);
			if(flowSumCount==null){
				flowSumCount=0f;
			}
		}catch(Exception e){
			logger.error("查询领取的小红包的流量币总和异常!",e);
			throw new WxppException("查询领取的小红包的流量币总和异常!");
		}
		return flowSumCount;
	}

	/**
	 * 查询用户输入的手机号码是否属于归属地市
	 */
	@Override
	public Integer findBindMsisdnCount(
			Map<String, String> map) throws WxppException {
		try {
			return grabFlowSubRedEnvelopeMapper.findBindMsisdnCount(map);
		} catch (Exception e) {
			throw new WxppException("查询手机号码格式异常!");
		}
	}
	
	
	/**
	 * 判断用户是否抢过大红包里的小红包
	 * @param grabFlowSubRedEnvelope
	 * @return
	 * @throws WxppException
	 */
	public int checkeHadQiangFlowRedPackage(GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException{
		return grabFlowSubRedEnvelopeMapper.searchRedBagQiangGuoCount(grabFlowSubRedEnvelope);
	}
	
	/**
	 * 判断用户是否与该红包有关系
	 * @param grabFlowSubRedEnvelope
	 * @return
	 * @throws WxppException
	 */
	public int relationForFlowFlowRedPackage(GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException{
		return grabFlowSubRedEnvelopeMapper.relationForFlowFlowRedPackage(grabFlowSubRedEnvelope);
	}
}
