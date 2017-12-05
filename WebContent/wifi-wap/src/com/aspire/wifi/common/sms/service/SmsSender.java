package com.aspire.wifi.common.sms.service;

import com.aspire.wifi.common.sms.entity.SmsSendMsg;
import com.aspire.wifi.common.sms.exception.SmsException;

/**
 * 短信发送接口.
 */
public interface SmsSender {

	/**
	 * 发送短信.
	 * 
	 * @param message
	 *            短信内容
	 * @throws SmsException
	 *             短信异常
	 */
	void send(SmsSendMsg message) throws SmsException;

}
