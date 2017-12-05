/**
 * 
 */
package com.aspire.shakewxpp.wap.http;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.shakewxpp.wap.util.DateUtil;
import com.aspire.shakewxpp.wap.util.GetConfigFile;
import com.aspire.shakewxpp.wap.util.StringTransTool;

/**
 * @author chenping
 * @2013-5-21
 */
public class HttpManager {
	
	
	/**
	 * http请求管理器
	 */
	private static HttpManager httpManager = new HttpManager();
	private static Logger logger =  LoggerFactory.getLogger(HttpManager.class);
	
	
	
	private static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");
	
	public final static String WEB_ACCEPT_XML = "application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5";
	public final static String WEB_ACCEPT_JSON = "application/json, text/javascript, */*";
	public final static String WEB_ACCEPT_CHARSET = "utf-8;q=0.7,*;q=0.3";
	public final static String WEB_ACCEPT_ENCODING = "gzip,deflate,sdch";
	public final static String WEB_ACCEPT_LANGUAGE = "zh-CN,zh;q=0.8";
	public final static String WEB_CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";
	public final static String WEB_USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.13 (KHTML, like Gecko) Chrome/9.0.597.84 Safari/534.13 By LuJian's Client";
	public final static String WEB_X_REQUESTED_WITH = "XMLHttpRequest";
	public static int SECONDS = 30;
	private static String smsUrl = "";
	
	private static boolean isInit = false;
	

	private HttpManager() {
		if(!isInit) {
			init();
		}
	}

	public static HttpManager getInstance() {
		httpManager = new HttpManager();
		
		return httpManager;
	}

	/**
	 * 初始化http请求管理器,从配置文件中读取，初始化http配置参数
	 */
	private void init() {
		try {
			GetConfigFile getConfigFile = GetConfigFile.getInstance();
			smsUrl = getConfigFile.getProperties("smsUrl");
			isInit = true;
		} catch (Exception e) {

			logger.error("获取system.xml配置文件失败", e);
		}

	}


	/**
	 * 发送请求
	 * 
	 * @param context
	 * @return
	 */
	public String sendSMSServer(String context) {
		Date startDate = new Date();
		/**
		 * 请求消息监控
		 */
		logger.info("【【【【【发起时间：" + DateUtil.dateToDateString(startDate) + "】】】】】发起http请求【方法：sendSMSServer】【请求地址：" + smsUrl + "】开始，请求参数报文：" + LINE_SEPARATOR + StringTransTool.replaceDeline(context));
		
		ClientAgent agent = new ClientAgent(SECONDS);
		String result = "";
		try {

			result = agent.post(smsUrl, StringTransTool.replaceDeline(context));

		} catch (IOException e) {

			logger.error("http请求失败",e);
		} finally {
			agent.close();
		}	
		return result;
	}

	

}
