package com.aspire.wifi.common.auth.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aspire.wifi.common.auth.entity.AuthDeviceAccountInfo;
import com.aspire.wifi.common.auth.mapper.AuthDeviceAccountInfoMapper;
import com.aspire.wifi.common.auth.service.AuthService;

@Service("authService")
public class AuthServiceImpl implements AuthService {

	/** 日志对象 */
	private Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

	@Resource(name = "authDeviceAccountInfoMapper")
	private AuthDeviceAccountInfoMapper authDeviceAccountInfoMapper;

	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public AuthDeviceAccountInfo getAuthDeviceAccountInfo() {
		AuthDeviceAccountInfo authDeviceAccountInfo = null;
		try {
			authDeviceAccountInfo = authDeviceAccountInfoMapper
					.getAuthDeviceAccountInfo();
			if (authDeviceAccountInfo != null) {
				int result = authDeviceAccountInfoMapper
						.updateAuthDeviceAccountInfo(authDeviceAccountInfo);
				if (result == 0) {
					authDeviceAccountInfo = null;
				}
			}
		} catch (Exception e) {
			logger.error("获取认证账号信息失败：" + e.getMessage(), e);
		}
		return authDeviceAccountInfo;
	}

}
