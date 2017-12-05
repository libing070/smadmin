package com.aspire.wifi.common.auth.mapper;

import com.aspire.wifi.common.auth.entity.AuthDeviceAccountInfo;

public interface AuthDeviceAccountInfoMapper {

	int updateAuthDeviceAccountInfo(AuthDeviceAccountInfo authDeviceAccountInfo);

	AuthDeviceAccountInfo getAuthDeviceAccountInfo();

}
