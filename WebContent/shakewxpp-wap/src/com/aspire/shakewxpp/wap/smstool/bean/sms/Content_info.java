/**
 * 
 */
package com.aspire.shakewxpp.wap.smstool.bean.sms;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author chenping
 * @2013-5-23
 */
@XStreamAlias("Content_info")
public class Content_info {
	@XStreamAlias("SMS_Content_Info")
	private SMS_Content_Info smsContentInfo;

	/**
	 * @param smsContentInfo
	 */
	public Content_info(SMS_Content_Info smsContentInfo) {

		this.smsContentInfo = smsContentInfo;
	}

	public Content_info() {
	}

	public SMS_Content_Info getSmsContentInfo() {
		return smsContentInfo;
	}

	public void setSmsContentInfo(SMS_Content_Info smsContentInfo) {
		this.smsContentInfo = smsContentInfo;
	}
}
