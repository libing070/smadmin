package com.aspire.wifi.wap.util;

import java.util.Date;


import com.aspire.wifi.common.sms.constants.DIY09SmsConstants;
import com.aspire.wifi.common.sms.constants.SmsConstants;
import com.aspire.wifi.common.sms.entity.SmsSendMsg;

public class SendMsg {

		public static SmsSendMsg getMsg(String mobile){
			SmsSendMsg message = new SmsSendMsg();
			message.setAsynSend(false);
			message.setCreateTime(new Date()); 
			message.setMobile(mobile);
			message.setPriority(DIY09SmsConstants.PRIORITY_MIDDLE);
			message.setSendStatus(SmsConstants.SEND_STATUS_WAITING);
			message.setSendTime(new Date());
			message.setRetryTimes(0);

			return message;
		}
}
