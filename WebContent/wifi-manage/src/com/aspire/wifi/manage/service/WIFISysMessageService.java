package com.aspire.wifi.manage.service;


import java.util.List;

import com.aspire.wifi.manage.entity.SysConfigEntity;
import com.aspire.wifi.manage.exception.WxppException;

public interface WIFISysMessageService {

	public Integer searchSensitivityCount(String SensitivityName)
			throws WxppException;

	List<String> listSensitiveWord(String content) throws WxppException;

	SysConfigEntity getSysConfigEntity(String configParaCode)
			throws WxppException;
}
