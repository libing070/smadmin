/**
 * 
 */
package com.aspire.shakewxpp.wap.smstool.bean.sms;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author chenping
 * @2013-5-23
 */
@XStreamAlias("SMS_Content_Info")
public class SMS_Content_Info {

	@XStreamAlias("MsgFmt")
	private String msgFmt;
	@XStreamAlias("Data")
	private String data;

	/**
	 * @param msgFmt
	 * @param data
	 */
	public SMS_Content_Info(String msgFmt, String data) {
		this.msgFmt = msgFmt;
		this.data = data;
	}

	public SMS_Content_Info() {
	}

	public String getMsgFmt() {
		return msgFmt;
	}

	public void setMsgFmt(String msgFmt) {
		this.msgFmt = msgFmt;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
