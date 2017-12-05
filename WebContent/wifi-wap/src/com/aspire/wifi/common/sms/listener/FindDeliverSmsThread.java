package com.aspire.wifi.common.sms.listener;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import smscommon.ComFun;
import smsmessage.sms.DELIVER;

import com.aspire.wifi.common.sms.constants.DIY09SmsConstants;
import com.aspire.wifi.common.sms.constants.SmsConstants;
import com.aspire.wifi.common.sms.entity.SmsReceiveMsg;
import com.aspire.wifi.common.sms.entity.SmsSendMsg;
import com.aspire.wifi.common.sms.mapper.SmsNoticeMapper;
import com.aspire.wifi.common.sms.service.DIY09ClientService;
import com.aspire.wifi.common.sms.service.SmsReceiver;

@Component("findDeliverSmsThread")
public class FindDeliverSmsThread {

	/** 日志对象 */
	private Logger logger = LoggerFactory.getLogger(FindDeliverSmsThread.class);

	/** 短信上行接口 */
	@Resource(name = "smsReceiver")
	private SmsReceiver smsReceiver;

	/** 短信下发数据访问对象 */
	@Resource(name = "smsNoticeMapper")
	private SmsNoticeMapper smsNoticeMapper;

	/** DIY09客户端Service */
	@Resource(name = "diy09ClientService")
	private DIY09ClientService diy09ClientService;

	/**
	 * 监听状态报告或上行短信
	 */
	public void start() {
		new Thread(new Runnable() {
			public void run() {
				DELIVER deliver = new DELIVER();
				while (true) {
					if ((deliver = ComFun.getSMS()) != null) {
						if (deliver.getMsgFlag() == DIY09SmsConstants.UPLOAD_MSG_FLAG_REPORT) {
							// 状态报告
							updateSmsStatus(deliver);
						} else {
							// 上行短信
							deliver(deliver);
						}
					} else {
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
						}
					}
				}
			}
		}).start();
	}

	/**
	 * 根据状态报告，更新短信下发状态
	 * 
	 * @param deliver
	 */
	private void updateSmsStatus(DELIVER deliver) {
		logger.info("-状态报告: 企业下行消息标识 [" + deliver.getMsgContent() + "]，手机号 ["
				+ deliver.getSrcID() + "]，短信接收情况描述 [" + deliver.getReserve()
				+ "]");
		
		try {
			SmsSendMsg message = smsNoticeMapper.getSmsNoticeByGwSmsId(deliver
					.getMsgContent());
			if (message != null
					&& message.getSendStatus() == SmsConstants.SEND_STATUS_SENDING) {
				if (DIY09SmsConstants.RESERVE_TYPE_DELIVRD.equals(deliver
						.getReserve())) {
					// 已发送
					message.setSendStatus(SmsConstants.SEND_STATUS_SUCCESS);
				} else {
					int maxRetryTimes = diy09ClientService.getMaxRetryTimes();
					// 此次短信发送失败，判断重试次数是否到达限制
					if (message.getRetryTimes() >= maxRetryTimes) {
						logger.error("发送短信失败，重试次数超过限制。" + " 短信id="
								+ message.getId() + "， 发送次数="
								+ message.getRetryTimes());
						// 超过，设置为发送出错
						message.setSendStatus(SmsConstants.SEND_STATUS_FAILURE);
					} else {
						// 未超过，设置为待发送
						message.setSendStatus(SmsConstants.SEND_STATUS_WAITING);
					}
				}
				message.setSendResult(deliver.getReserve());
				// 更新短信下发状态
				smsNoticeMapper.updateSmsNotice(message);
			}
		} catch (Exception e) {
			logger.error("更新短信下发状态异常：" + e.getMessage(), e);
		}
	}

	/**
	 * 上行短信处理
	 * 
	 * @param deliver
	 */
	private void deliver(DELIVER deliver) {
		logger.info("-上行短信：上行源号码 [" + deliver.getSrcID() + "]，上行目的号码 ["
				+ deliver.getDestID() + "]，上行消息内容 [" + deliver.getMsgContent()
				+ "]");
		try {
			SmsReceiveMsg message = new SmsReceiveMsg();
			message.setSrcId(deliver.getSrcID());
			message.setDestId(deliver.getDestID());
			message.setContent(deliver.getMsgContent());
			message.setReceiveTime(deliver.getAriveTime());
			smsReceiver.deliverNotify(message);
		} catch (Exception e) {
			logger.error("上行短信处理异常：" + e.getMessage(), e);
		}
	}
}
