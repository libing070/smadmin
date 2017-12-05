package com.aspire.wifi.manage.mapper;

import java.util.List;

import com.aspire.wifi.manage.entity.SysConfigEntity;


public interface WifiSysConfigMapper {
	
	List<SysConfigEntity> querySysConfiguation();

	public Integer searchSensitivityCount(String SensitivityName);

	SysConfigEntity getSysConfigEntity(String configParaCode);

    List<String> listSensitiveWord(String content);
}
