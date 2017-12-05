package com.aspire.shakewxpp.wap.service;

import com.aspire.shakewxpp.wap.entity.UserNotifyPojo;


public interface UserNotifyService { 
	 /**
	  * 用户通知消息入库
	  * @param userNotifyPojo
	  * @throws Exception
	  */
	void addUserNotifyData(UserNotifyPojo userNotifyPojo) throws Exception;
}
