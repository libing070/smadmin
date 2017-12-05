package com.aspire.wifi.common.sms.service;

import com.aspire.wifi.common.sms.entity.SmsSendMsg;

public interface DIY09ClientService {

	SmsSendMsg sendSms(SmsSendMsg message);
	
	int getMaxRetryTimes();
}
