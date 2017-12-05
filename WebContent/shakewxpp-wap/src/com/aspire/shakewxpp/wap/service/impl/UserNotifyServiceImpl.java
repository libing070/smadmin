package com.aspire.shakewxpp.wap.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.shakewxpp.wap.entity.UserNotifyPojo;
import com.aspire.shakewxpp.wap.mapper.UserNotifyMapper;
import com.aspire.shakewxpp.wap.service.UserNotifyService;

@Service("userNotifyService")
public class UserNotifyServiceImpl implements UserNotifyService {
	protected static Logger logger =  LoggerFactory.getLogger(UserNotifyServiceImpl.class);
	
	@Resource(name = "userNotifyMapper")
	private UserNotifyMapper userNotifyMapper;
	
	public void addUserNotifyData(UserNotifyPojo userNotifyPojo) throws Exception{
		userNotifyMapper.addUserNotifyData(userNotifyPojo);
	}
}
