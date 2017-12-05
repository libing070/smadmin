package com.aspire.wifi.manage.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.manage.entity.SysConfigEntity;
import com.aspire.wifi.manage.exception.WxppException;
import com.aspire.wifi.manage.mapper.WifiSysConfigMapper;
import com.aspire.wifi.manage.service.WIFISysMessageService;

import java.util.List;

@Service("wifiSysMessageService")
public class WIFISysMessageServiceImpl implements WIFISysMessageService {

	protected static Logger logger = LoggerFactory
			.getLogger(WIFISysMessageServiceImpl.class);
	@Resource(name = "wifiSysConfigMapper")
	private WifiSysConfigMapper wifiSysConfigMapper;

	/**
	 * 查询敏感词
	 */
	
	public Integer searchSensitivityCount(String SensitivityName)
			throws WxppException {
		try {
			return wifiSysConfigMapper.searchSensitivityCount(SensitivityName);
		} catch (Exception e) {
			logger.error("查询敏感词异常!", e.getMessage());
			throw new WxppException("查询敏感词异常!");
		}
	}

	
	public List<String> listSensitiveWord(String content) throws WxppException {
		return wifiSysConfigMapper.listSensitiveWord(content);
	}

	
	public SysConfigEntity getSysConfigEntity(String configParaCode)
			throws WxppException {
		return wifiSysConfigMapper.getSysConfigEntity(configParaCode);
	}
}
