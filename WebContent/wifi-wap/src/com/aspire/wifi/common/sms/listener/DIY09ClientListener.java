package com.aspire.wifi.common.sms.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import smscommon.SmsClient;

public class DIY09ClientListener implements ServletContextListener {

	private SmsClient smsClient;

	private FindDeliverSmsThread findDeliverSmsThread;

	public void contextInitialized(ServletContextEvent sce) {
		WebApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		findDeliverSmsThread = (FindDeliverSmsThread) applicationContext
				.getBean("findDeliverSmsThread");

		smsClient = SmsClient.getInstance();
		findDeliverSmsThread.start();
	}

	public void contextDestroyed(ServletContextEvent sce) {
		smsClient.stop();
	}
}