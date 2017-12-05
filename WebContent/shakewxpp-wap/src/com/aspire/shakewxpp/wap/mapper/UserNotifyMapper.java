package com.aspire.shakewxpp.wap.mapper;

import com.aspire.shakewxpp.wap.entity.User;
import com.aspire.shakewxpp.wap.entity.UserNotifyPojo;

public interface UserNotifyMapper extends BaseMapper<User>{
	
	void addUserNotifyData(UserNotifyPojo userNotifyPojo);
}
