package com.aspire.shakewxpp.wap.smstool.parsexml;

import org.dom4j.Document;
import org.dom4j.Element;

import com.aspire.shakewxpp.wap.smstool.bean.sms.SubmitSMSResp;

/**
 * 
 *	
 *	@author mo_yq5@163.com
 * 	@date 2013-10-9  
 *
 */
public class SubmitSMSRespParseXml extends ParseXmlAbstract<SubmitSMSResp> {

	@Override
	public SubmitSMSResp parse(Document doc) {
		SubmitSMSResp rtObj = new SubmitSMSResp();
		Element respEl = (Element)doc.selectObject("/SubmitSMSResp");
		
		rtObj.setMsgType(getElementText(respEl,"MsgType"));
		rtObj.setVersion(getElementText(respEl,"Version"));
		rtObj.setHret(getElementText(respEl,"hRet"));
		rtObj.setMsgID(getElementText(respEl,"MsgID"));
		return rtObj;
	}

}
