package com.aspire.wifi.common.sms.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.aspire.wifi.common.sms.constants.SmsConstants;
import com.aspire.wifi.common.sms.entity.SmsSendMsg;
import com.aspire.wifi.common.sms.exception.SmsException;
import com.aspire.wifi.common.sms.mapper.SmsNoticeMapper;
import com.aspire.wifi.common.sms.service.DIY09ClientService;
import com.aspire.wifi.common.sms.service.SmsSender;

/**
 * 短信发送实现类
 */
@Service("smsSender")
public class SmsSenderImpl implements SmsSender {

	/** 日志对象 */
	private Logger logger = LoggerFactory.getLogger(SmsSenderImpl.class);

	/** 短信下发数据访问对象 */
	@Resource(name = "smsNoticeMapper")
	private SmsNoticeMapper smsNoticeMapper;

	/** DIY09客户端Service */
	@Resource(name = "diy09ClientService")
	private DIY09ClientService diy09ClientService;

	/**
	 * 发送短信.
	 * 
	 * @param message
	 *            短信内容
	 * @throws SmsException
	 *             短信异常
	 */
	public void send(SmsSendMsg message) throws SmsException {
		logger.info("接收到短信下行请求：" + message.toString());
		// 参数检查
		checkArgumentValid(message);
		if (message.isAsynSend()) {
			// 异步发送
			asyncSend(message);
		} else {
			// 同步发送
			syncSend(message);
		}
	}

	/**
	 * 参数合法性检查.
	 * 
	 * @param smsMessage
	 */
	private void checkArgumentValid(SmsSendMsg message) {
		// 参数检查
		if (message == null) {
			logger.error("短信合法性检验失败：输入参数为空");
			throw new IllegalArgumentException("短信合法性检验失败：输入参数为空");
		}

		if (StringUtils.isEmpty(message.getMobile())
				|| message.getMobile().length() != 11) {
			logger.error("短信合法性检验失败：手机号码格式错误");
			throw new IllegalArgumentException("短信合法性检验失败：手机号码格式错误");
		}

		if (StringUtils.isEmpty(message.getContent())) {
			logger.error("短信合法性检验失败：短信内容为空");
			throw new IllegalArgumentException("短信合法性检验失败：短信内容为空");
		}

		if (message.getPriority() == null) {
			logger.error("短信合法性检验失败：优先级为空");
			throw new IllegalArgumentException("短信合法性检验失败：优先级为空");
		}

		message.setSendStatus(SmsConstants.SEND_STATUS_WAITING);
	}

	/**
	 * 异步发送短信
	 * 
	 * @param message
	 * @throws SmsException
	 */
	private void asyncSend(SmsSendMsg message) throws SmsException {
		try {
			smsNoticeMapper.insertSmsNotice(message);
		} catch (Exception e) {
			logger.error("异步发送短信内容写入短信下发通知表失败" + e.getMessage(), e);
			throw new SmsException("异步发送短信内容写入短信下发通知表失败!", e);
		}
	}

	/**
	 * 同步发送短信
	 * 
	 * @param message
	 * @throws SmsException
	 */
	private void syncSend(SmsSendMsg message) throws SmsException {
		int maxRetryTimes = diy09ClientService.getMaxRetryTimes();
		SmsSendMsg returnMsg = null;
		do {
			returnMsg = diy09ClientService.sendSms(message);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		} while (returnMsg.getSendStatus() == SmsConstants.SEND_STATUS_FAILURE
				&& returnMsg.getRetryTimes() < maxRetryTimes);

		try {
			// 将同步发送短信内容写入短信下发通知表
			smsNoticeMapper.insertSmsNotice(returnMsg);
		} catch (Exception e) {
			logger.error("同步发送短信内容写入短信下发通知表失败：" + e.getMessage(), e);
			throw new SmsException("同步发送短信内容写入短信下发通知表失败!", e);
		}

		if (returnMsg.getSendStatus() == SmsConstants.SEND_STATUS_FAILURE) {
			throw new SmsException("同步发送短信失败!");
		}
	}
}
