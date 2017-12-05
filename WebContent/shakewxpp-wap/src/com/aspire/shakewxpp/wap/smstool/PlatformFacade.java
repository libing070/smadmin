/**
 * 
 */
package com.aspire.shakewxpp.wap.smstool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.shakewxpp.wap.http.HttpManager;
import com.aspire.shakewxpp.wap.smstool.bean.sms.SubmitSMSReq;
import com.aspire.shakewxpp.wap.smstool.bean.sms.SubmitSMSResp;
import com.aspire.shakewxpp.wap.smstool.builder.SMSBuilder;
import com.aspire.shakewxpp.wap.smstool.parsexml.ParseXml;
import com.aspire.shakewxpp.wap.smstool.parsexml.SubmitSMSRespParseXml;

/**
 * 平台接口的门面类
 * 
 * @author chenping
 * @2013-5-22
 */
public class PlatformFacade {

	private static PlatformFacade platformFacade = null;

	private static Logger logger =  LoggerFactory.getLogger(PlatformFacade.class);

	/** http请求对象 */
	private static HttpManager httpManager = null;
	/** 短信请求报文 */
	private static SMSBuilder smsBuilder = null;

	private PlatformFacade() {	
	}

	public synchronized static PlatformFacade getInstance() {
		if(httpManager==null || smsBuilder==null || platformFacade ==null){
			httpManager = HttpManager.getInstance();
			smsBuilder = new SMSBuilder();
			platformFacade = new PlatformFacade();
		}
		return platformFacade;
	}

	

	/**
	 * 发送短信接口
	 * 
	 * @throws Exception
	 */
	public SubmitSMSResp sendSMS(String destPhone, String context,String weixinAppNameView,int msgType)
			throws Exception {
		logger.debug("平台开始>>>>>调用发送短信接口: destPhone=" + destPhone + "context="
				+ context);
		String reqXml = "";
		try{
			SubmitSMSReq sms = smsBuilder.buildSubmitSMSReq(destPhone, context,weixinAppNameView,msgType);
	//		String reqXml = XStreamUtil.writeEnter2XMl4Annotation(sms);
			reqXml = sms.toXml();
		}catch(Exception e){
			logger.error("提供短信消息发送实体出现异常",e);
			throw e;
		}
		logger.debug("短信报文base64转码后:"+reqXml);
		String respXml = httpManager.sendSMSServer(reqXml);
		
		logger.debug("平台结束>>>>>调用发送短信接口: destPhone=" + destPhone + "context="
				+ context);
		//return XStreamUtil.readXML2Object(respXml, SubmitSMSResp.class);
		ParseXml<SubmitSMSResp> parseXml = new SubmitSMSRespParseXml();
		parseXml.setXml(respXml);
		return parseXml.parse();
	}

	

}
