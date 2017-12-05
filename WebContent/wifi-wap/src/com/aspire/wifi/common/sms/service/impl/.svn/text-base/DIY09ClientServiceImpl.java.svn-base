package com.aspire.wifi.common.sms.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import smsmessage.sms.SMSclientSubmit;

import com.aspire.wifi.common.sms.constants.DIY09SmsConstants;
import com.aspire.wifi.common.sms.constants.SmsConstants;
import com.aspire.wifi.common.sms.entity.SmsSendMsg;
import com.aspire.wifi.common.sms.service.DIY09ClientService;
import com.aspire.wifi.wap.entity.SysConfigEntity;
import com.aspire.wifi.wap.mapper.WifiSysConfigMapper;

@Service("diy09ClientService")
public class DIY09ClientServiceImpl implements DIY09ClientService {

	/** 日志对象 */
	private Logger logger = LoggerFactory
			.getLogger(DIY09ClientServiceImpl.class);

	@Resource(name = "wifiSysConfigMapper")
	private WifiSysConfigMapper wifiSysConfigMapper;

	/**
	 * 向DIY09客户端发送短信
	 * 
	 * @param message
	 *            下发短信内容
	 */
	public SmsSendMsg sendSms(SmsSendMsg message) {
		try {
			SMSclientSubmit mt = new SMSclientSubmit();
			// 用户号码
			mt.setDestID(message.getMobile());
			// 短信内容
			mt.setMsgContent(message.getContent());
			// 设置优先级，默认为1
			mt.setPriority(DIY09SmsConstants.PRIORITY_MIDDLE);

			message.setSendTime(new Date());
			message.setRetryTimes(message.getRetryTimes() + 1);

			// 发送短信
			String retVal = mt.sendSMS();
			if (DIY09SmsConstants.SEND_RETVAL.containsKey(retVal)) {
				String retMsg = DIY09SmsConstants.SEND_RETVAL.get(retVal);
				logger.error("发送短信失败: " + retMsg + "。短信id=" + message.getId()
						+ "发送次数：" + message.getRetryTimes());
				// 发送出错
				message.setSendResult(retVal);
				message.setSendStatus(SmsConstants.SEND_STATUS_FAILURE);
			} else {
				logger.info("企业下行消息标识 [" + retVal + "]，" + "短信内容 ["
						+ mt.getMsgContent() + "]");
				// 发送中
				message.setGwSmsId(retVal);
				message.setSendResult("");
				message.setSendStatus(SmsConstants.SEND_STATUS_SENDING);
			}
		} catch (Exception e) {
			logger.error(
					"发送短信异常: " + e.getMessage() + " 短信id=" + message.getId()
							+ "，发送次数=" + message.getRetryTimes(), e);
			message.setSendResult("发送短信异常");
			message.setSendStatus(SmsConstants.SEND_STATUS_FAILURE);
		}

		return message;
	}

	public int getMaxRetryTimes() {
		int maxRetryTimes = SmsConstants.WIFI_SMS_NOTICE_MAX_RETRY_TIMES_DEFAULT_VAL;
		SysConfigEntity configEntity = wifiSysConfigMapper
				.getSysConfigEntity(SmsConstants.SYSCONFIG_WIFI_SMS_NOTICE_MAX_RETRY_TIMES);
		if (configEntity != null) {
			try {
				maxRetryTimes = Integer.parseInt(configEntity
						.getConfigParaValue());
			} catch (Exception e) {
				logger.error("获取系统配置["
						+ SmsConstants.SYSCONFIG_WIFI_SMS_NOTICE_MAX_RETRY_TIMES
						+ "]时异常，使用系统默认值["
						+ SmsConstants.WIFI_SMS_NOTICE_MAX_RETRY_TIMES_DEFAULT_VAL
						+ "]");
				maxRetryTimes = SmsConstants.WIFI_SMS_NOTICE_MAX_RETRY_TIMES_DEFAULT_VAL;
			}
		}
		return maxRetryTimes;
	}
}
