package com.aspire.wifi.manage.controller;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aspire.wifi.manage.util.GetConfigFile;

@Controller
public class SystemConfigManagerController {
	protected static Logger logger =  LoggerFactory.getLogger(SystemConfigManagerController.class);
	
	@RequestMapping(value = "/refreshSystemConfig.tv")
	public void refreshSystemConfig(HttpServletRequest request,HttpServletResponse response)
	{
		logger.debug("进入SystemConfigManagerController.refreshSystemConfig方法");
		try {
			GetConfigFile.refreshSystemConfig();
		} catch (Exception e) {
			logger.error("刷新系统配置项出现异常",e);
		}
		
		try {
			GetConfigFile getConfigFile = GetConfigFile.getInstance();
			Set set = getConfigFile.viewProps();
			Iterator ite = set.iterator();
			response.getWriter().println("<html><body><table>");
			while(ite.hasNext()){
				String key = (String)ite.next();
				response.getWriter().println("<tr><td>");
				response.getWriter().println(key+"="+getConfigFile.getProperties(key));
				response.getWriter().println("</td></tr>");
			}
			
			response.getWriter().println("</table></body></html>");
		} catch (Exception e) {
			logger.error("打印配置信息出现异常",e);
		}
	}
}
