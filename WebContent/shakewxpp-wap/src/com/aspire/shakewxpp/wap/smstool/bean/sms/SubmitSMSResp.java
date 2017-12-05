/**
 * 
 */
package com.aspire.shakewxpp.wap.smstool.bean.sms;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author chenping
 * @2013-5-23
 */
@XStreamAlias("SubmitSMSResp")
public class SubmitSMSResp {
	
	@XStreamAlias("MsgType")
	private String msgType;
	
	@XStreamAlias("Version")
	private String version;
	
	@XStreamAlias("hRet")
	private String hret;
	
	@XStreamAlias("MsgID")
	private String msgID;
	
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getHret() {
		return hret;
	}
	public void setHret(String hret) {
		this.hret = hret;
	}
	public String getMsgID() {
		return msgID;
	}
	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

}
