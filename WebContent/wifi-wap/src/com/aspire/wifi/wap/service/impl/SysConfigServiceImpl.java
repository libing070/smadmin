package com.aspire.wifi.wap.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.wap.entity.SysConfigEntity;
import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.mapper.WifiSysConfigMapper;
import com.aspire.wifi.wap.service.intf.SysConfigService;

@Service("sysConfigService")
public class SysConfigServiceImpl implements SysConfigService {
	protected static Logger logger = LoggerFactory.getLogger(SysConfigServiceImpl.class);

	@Resource(name = "wifiSysConfigMapper")
	private WifiSysConfigMapper wifiSysConfigMapper;

	/* (non-Javadoc)
	 * @see com.aspire.wifi.wap.service.intf.SysConfigService#querySysConfiguation()
	 */
	public List<SysConfigEntity> querySysConfiguation() throws WifiException {
		List<SysConfigEntity> list = null;
		try {
			list = wifiSysConfigMapper.querySysConfiguation();
		} catch (Exception e) {
			throw new WifiException("获取系统配置出现异常 : " + e.getMessage());
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.aspire.wifi.wap.service.intf.SysConfigService#getSysConfigEntity(java.lang.String)
	 */
	public SysConfigEntity getSysConfigEntity(String key) throws WifiException {
		return wifiSysConfigMapper.getSysConfigEntity(key);
	}

	/* (non-Javadoc)
	 * @see com.aspire.wifi.wap.service.intf.SysConfigService#getSysConfigAsString(java.lang.String)
	 */
	public String getSysConfigAsString(String key) throws WifiException {
		SysConfigEntity config = this.getSysConfigEntity(key);
		if (config != null) {
			return config.getConfigParaValue();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.aspire.wifi.wap.service.intf.SysConfigService#getSysConfigAsString(java.lang.String, java.lang.String[])
	 */
	public String getSysConfigAsString(String key, String[] para) throws WifiException {
		String result = null;
		SysConfigEntity config = this.getSysConfigEntity(key);
		if (config != null) {
			result = config.getConfigParaValue();
			for (int i = 0; i < para.length; i++) {
				if (result != null) {
					result = result.replaceAll("&" + String.valueOf(i + 1), para[i]);
				}
			}
		}
		return result;
	}
}
