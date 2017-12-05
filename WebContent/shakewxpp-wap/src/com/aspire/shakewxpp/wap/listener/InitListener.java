/**
 * 
 */
package com.aspire.shakewxpp.wap.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.aspire.shakewxpp.wap.util.BeanLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.shakewxpp.wap.smstool.builder.SMSBuilder;
import com.aspire.shakewxpp.wap.util.HttpPostUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author chenping
 * @2013-6-10
 */
public class InitListener implements ServletContextListener {

	private static Logger logger =  LoggerFactory.getLogger(HttpPostUtil.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.debug("==================服务器终止运行=====================");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("=========初始化所有的配置项===============");
        ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
        BeanLocator.getInstance().setApplicationContext(applicationContext);
        logger.info("===============初始化短信模板的配置文件===========");
		SMSBuilder.init();
		logger.info("=========结束初始化所有的配置项===============");
	}

}
