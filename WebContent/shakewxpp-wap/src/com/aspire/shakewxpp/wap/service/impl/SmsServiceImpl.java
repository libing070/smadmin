package com.aspire.shakewxpp.wap.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.aspire.shakewxpp.wap.cache.RedisHandler;
import com.aspire.shakewxpp.wap.exception.WxppException;
import com.aspire.shakewxpp.wap.service.SmsService;
import com.aspire.shakewxpp.wap.smstool.PlatformFacade;
import com.aspire.shakewxpp.wap.smstool.bean.sms.SubmitSMSResp;
import com.aspire.shakewxpp.wap.util.Constants;
import com.aspire.shakewxpp.wap.util.GetConfigFile;
import com.aspire.shakewxpp.wap.util.StringTransTool;

@Service("smsService")
public class SmsServiceImpl implements SmsService {
	protected static Logger logger =  LoggerFactory.getLogger(SmsServiceImpl.class);
	
	public boolean  checkSmsPassWordForBind(String password,String phoneNum) {
		logger.debug("=========SmsServiceImpl--checkSmsPassWordForBind- 校验动态验证码===========");
		boolean flag = false;
		String redisKey = "bind_"+phoneNum;
		try{
			RedisHandler hander = RedisHandler.getInstance();
			Object dtPassword = hander.getObject(redisKey);
			if (dtPassword != null) {
				/** 动态密码校验 */
				if (dtPassword.equals(password)) {
					flag = true;
					hander.delObject(redisKey);
				}
			}
		}catch(Exception e){
			logger.error("校验动态密码出现异常",e);
		}
		return flag;
	}
	
	public boolean  checkSmsPassWordForGetFLow(String password,String phoneNum) {
		logger.debug("=========SmsServiceImpl--checkSmsPassWordForGetFLow- 校验动态验证码===========");
		boolean flag = false;
		String redisKey = "getFLow_"+phoneNum;
		
		try{
			RedisHandler hander = RedisHandler.getInstance();
			Object dtPassword = hander.getObject(redisKey);
			if (dtPassword != null) {
				/** 动态密码校验 */
				if (dtPassword.equals(password)) {
					flag = true;
					hander.delObject(redisKey);
				}
			}
		}catch(Exception e){
			logger.error("校验动态密码出现异常",e);
		}
		return flag;
	}
	
	/**
	 * forBind
	 */
	public void sendMsgCodeForBind(String phoneNum,String weixinAppNameView) throws Exception{
		logger.debug("================SmsServiceImpl--sendMsgCodeForBind- 获取动态验证码==============");
		String redisKey = "bind_"+phoneNum;
		
		String password = StringTransTool.getRandomNum4String(6).toLowerCase();
		RedisHandler hander = RedisHandler.getInstance();
		int redisTimeout = Integer.parseInt(GetConfigFile.getInstance().getProperties("sms_dt_redis"));

		String exitPassword = StringTransTool.replaceNull(hander.getObject(redisKey));
		if (!StringUtils.isEmpty(exitPassword)) {// 如果已经下发还没超时不再重新下发
			throw new WxppException("已发送短信验证码,"+redisTimeout+"秒内有效;请勿重复获取");
		}
		
		logger.debug("--------------动态短信验证码为：" + password + "-----------开始发送短信");

		boolean sendSucc = false;
		try {
			sendSucc = sendSMS(password,phoneNum,weixinAppNameView,Constants.SEND_MSG_TYPE_FRO_BIND);
		} catch (Exception e) {
			logger.error("=============发送短信失败=============", e);
			throw e;
		}
		if (!sendSucc) {
			logger.debug("============发送短信失败==============短信验证码：" + password);
			throw new Exception("发送短信失败");
		}else{
			logger.debug("============发送短信成功==============短信验证码：" + password);
			hander.setObject(redisKey, password, redisTimeout);
		}
	}
	
	
	/**
	 * forGetFLOW
	 */
	public void sendMsgCodeForGetFLow(String phoneNum,String weixinAppNameView) throws Exception{
		logger.debug("================SmsServiceImpl--sendMsgCodeForGetFLow- 获取动态验证码==============");
		String redisKey = "getFLow_"+phoneNum;
		
		String password = StringTransTool.getRandomNum4String(6).toLowerCase();
		RedisHandler hander = RedisHandler.getInstance();
		int redisTimeout = Integer.parseInt(GetConfigFile.getInstance().getProperties("sms_dt_redis"));

		String exitPassword = StringTransTool.replaceNull(hander.getObject(redisKey));
		if (!StringUtils.isEmpty(exitPassword)) {// 如果已经下发还没超时不再重新下发
			throw new WxppException("已发送短信验证码,"+redisTimeout+"秒内有效;请勿重复获取");
		}
		
		logger.debug("--------------动态短信验证码为：" + password + "-----------开始发送短信");

		boolean sendSucc = false;
		try {
			sendSucc = sendSMS(password,phoneNum,weixinAppNameView,Constants.SEND_MSG_TYPE_FRO_GET_FLOW);
		} catch (Exception e) {
			logger.error("=============发送短信失败=============", e);
			throw e;
		}
		if (!sendSucc) {
			logger.debug("============发送短信失败==============短信验证码：" + password);
			throw new Exception("发送短信失败");
		}else{
			logger.debug("============发送短信成功==============短信验证码：" + password);
			hander.setObject(redisKey, password, redisTimeout);
		}
	}
	
	private boolean sendSMS(String context, String phone,String weixinAppNameView,int msgType) throws Exception {
		SubmitSMSResp resp = PlatformFacade.getInstance().sendSMS(phone,context,weixinAppNameView,msgType);

		if (resp == null || !"0".equals(resp.getHret())) {
			return false;

		} else {
			return true;
		}
	}
	
	
}
