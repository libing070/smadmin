package com.aspire.shakewxpp.wap.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.shakewxpp.wap.entity.ConfigPojo;
import com.aspire.shakewxpp.wap.mapper.ActivityConfigMapper;
import com.aspire.shakewxpp.wap.service.ConfigService;
import com.aspire.shakewxpp.wap.util.GetConfigFile;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	protected static Logger logger =  LoggerFactory.getLogger(ConfigServiceImpl.class);
	
	@Resource(name = "activityConfigMapper")
	private ActivityConfigMapper activityConfigMapper;
	
	public void getConfigData(String configTable) {
		try{
			Map<String,String> map = new HashMap<String,String>();
			map.put("configTable", configTable);
			List<ConfigPojo> configPojoList = activityConfigMapper.getConfigData(map);
			GetConfigFile.getInstance().setConfigPojoList(configPojoList);
		}catch(Exception e){
			logger.error("读取配置项信息失败",e);
		}
	}
	
	
	public void getNextConfigData(String configTable) {
		try{
			Map<String,String> map = new HashMap<String,String>();
			map.put("configTable", configTable);
			List<ConfigPojo> configPojoList = activityConfigMapper.getConfigData(map);
			GetConfigFile.getInstance().setNextConfigPojoList(configPojoList);
		}catch(Exception e){
			logger.error("读取配置项信息失败",e);
		}
	}
}
