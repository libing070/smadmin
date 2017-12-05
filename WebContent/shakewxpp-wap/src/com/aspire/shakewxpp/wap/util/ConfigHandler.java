/**
 * 
 */
package com.aspire.shakewxpp.wap.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 配置文件管理工具.
 * 
 * @author chenping
 */
public class ConfigHandler {
	private static Logger logger =  LoggerFactory.getLogger(ConfigHandler.class);


	/*
	 * 根据参数传递进来的文件名

	 */
	public static InputStream readConfiguration(String filePath)
			throws Exception {
		if (filePath == null) {
			return null;
		}
		try {
			File file = new File(filePath);
			FileInputStream fileInputStream = new FileInputStream(file);
			return fileInputStream;
		} catch (FileNotFoundException e) {
			logger.error("打开配置文件失败，filename=" + filePath);
		}
		return null;
	}

	/**
	 * 读取properties配置文件
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static Configuration getConfiguration4property(String filePath)
			throws Exception {
		if (filePath == null) {
			return null;
		}

		PropertiesConfiguration propertiesConfiguration = null;
		try {
			propertiesConfiguration = new PropertiesConfiguration(filePath);
		} catch (ConfigurationException e) {
			logger.error("打开配置文件失败，filename=" + filePath);
		}

		return propertiesConfiguration;
	}

	public static Configuration getConfiguration4Xml(String filePath)
			throws Exception {
		
		XMLConfiguration xmlConfiguration = null;
		if (filePath != null) {
			try {
				xmlConfiguration = new XMLConfiguration(filePath);
			} catch (ConfigurationException e) {
				
				logger.error("打开配置文件失败，filename=" + filePath);
				throw new Exception("打开配置文件失败");
			}

		}

		return xmlConfiguration;
	}
	
}
