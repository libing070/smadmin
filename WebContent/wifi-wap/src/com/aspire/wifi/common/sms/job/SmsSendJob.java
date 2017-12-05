package com.aspire.wifi.common.sms.job;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.wifi.common.sms.constants.SmsConstants;
import com.aspire.wifi.common.sms.entity.SmsSendMsg;
import com.aspire.wifi.common.sms.mapper.SmsNoticeMapper;
import com.aspire.wifi.common.sms.service.DIY09ClientService;

public class SmsSendJob {

	/** 日志对象 */
	private Logger logger = LoggerFactory.getLogger(SmsSendJob.class);

	/** 短信下发数据访问对象 */
	@Resource(name = "smsNoticeMapper")
	private SmsNoticeMapper smsNoticeMapper;

	/** DIY09客户端Service */
	@Resource(name = "diy09ClientService")
	private DIY09ClientService diy09ClientService;

	/**
	 * 处理.
	 */
	public void execute() {
		logger.debug("短信发送定时任务开始");
		try {
			// 查询待处理短信记录
			List<SmsSendMsg> list = smsNoticeMapper.listSmsNotice();
			for (SmsSendMsg message : list) {
				send(message);
			}
		} catch (Exception e) {
			logger.error("短信发送定时任务处理失败", e);
		}
		logger.debug("短信发送定时任务结束");
	}

	private void send(SmsSendMsg message) throws Exception {
		SmsSendMsg returnMsg = diy09ClientService.sendSms(message);
		if (returnMsg.getSendStatus().equals(SmsConstants.SEND_STATUS_FAILURE)) {
			int maxRetryTimes = diy09ClientService.getMaxRetryTimes();
			// 此次短信发送失败，判断重试次数是否到达限制
			if (returnMsg.getRetryTimes() >= maxRetryTimes) {
				logger.error("发送短信失败，重试次数超过限制。" + " 短信id=" + returnMsg.getId()
						+ "， 发送次数=" + returnMsg.getRetryTimes());
			} else {
				returnMsg.setSendStatus(SmsConstants.SEND_STATUS_WAITING);
			}
		}
		smsNoticeMapper.updateSmsNotice(returnMsg);
	}

}
