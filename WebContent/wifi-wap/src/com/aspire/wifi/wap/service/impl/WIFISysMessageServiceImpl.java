package com.aspire.wifi.wap.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.wap.entity.SysConfigEntity;
import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.mapper.WifiSysConfigMapper;
import com.aspire.wifi.wap.service.intf.WIFISysMessageService;

import java.util.List;

@Service("wifiSysMessageService")
public class WIFISysMessageServiceImpl implements WIFISysMessageService {

	protected static Logger logger = LoggerFactory
			.getLogger(PinActionServiceImpl.class);
	@Resource(name = "wifiSysConfigMapper")
	private WifiSysConfigMapper wifiSysConfigMapper;

	/**
	 * 查询敏感词
	 */
	public Integer searchSensitivityCount(String SensitivityName)
			throws WifiException {
		try {
			return wifiSysConfigMapper.searchSensitivityCount(SensitivityName);
		} catch (Exception e) {
			logger.error("查询敏感词异常!", e.getMessage());
			throw new WifiException("查询敏感词异常!", e);
		}
	}

	public List<String> listSensitiveWord(String content) throws WifiException {
		return wifiSysConfigMapper.listSensitiveWord(content);
	}

	public SysConfigEntity getSysConfigEntity(String configParaCode)
			throws WifiException {
		return wifiSysConfigMapper.getSysConfigEntity(configParaCode);
	}
}
