package com.aspire.wifi.wap.service.intf;

import java.util.List;

import com.aspire.wifi.wap.entity.SysConfigEntity;
import com.aspire.wifi.wap.exception.WifiException;

public interface SysConfigService {
	/**
	 * 查询所有系统配置
	 * 
	 * @return
	 * @throws WifiException
	 */
	public List<SysConfigEntity> querySysConfiguation() throws WifiException;
	
	/**
	 * 查询数据库配置项
	 * 
	 * @param key
	 * @return
	 * @throws WifiException
	 */
	public SysConfigEntity getSysConfigEntity(String key) throws WifiException;
	
	/**
	 * 查询数据库配置项,并返回字符串类型
	 * 
	 * @param key
	 * @param para
	 * @return
	 * @throws WifiException
	 */
	public String getSysConfigAsString(String key) throws WifiException;
	
	/**
	 * 查询数据库配置项, 可传入参数对配置项的值进行替换,并返回字符串类型
	 * 
	 * @param key
	 * @param para
	 * @return
	 * @throws WifiException
	 */
	public String getSysConfigAsString(String key, String[] para) throws WifiException;
}
