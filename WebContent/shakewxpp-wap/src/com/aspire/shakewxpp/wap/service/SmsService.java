package com.aspire.shakewxpp.wap.service;


public interface SmsService {
	/**
	 * 绑定手机号时校验动态密码
	 * @param password
	 * @param phoneNum
	 * @return
	 */
	 boolean  checkSmsPassWordForBind(String password,String phoneNum);
	 
	 /**
	 * 领取红包时校验动态密码
	 * @param password
	 * @param phoneNum
	 * @return
	 */
	 boolean  checkSmsPassWordForGetFLow(String password,String phoneNum);
	 
	 /**
	  * 发送动态密码，绑定手机号使用
	  * @param content
	  * @param phoneNum
	  * @throws Exception
	  */
	void sendMsgCodeForBind(String phoneNum,String weixinAppNameView) throws Exception;
	
	/**
	  * 发送动态密码，领取红包使用
	  * @param content
	  * @param phoneNum
	  * @throws Exception
	  */
	void sendMsgCodeForGetFLow(String phoneNum,String weixinAppNameView) throws Exception;
}
