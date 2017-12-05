/**
 * 
 */
package com.aspire.shakewxpp.wap.smstool.bean.sms;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author chenping
 * @2013-5-23
 */
@XStreamAlias("Recipients")
public class Recipients {

	@XStreamAlias("Msg_Address")
	private Msg_Address msgAddress;

	/**
	 * @param msgAddress
	 */
	public Recipients(Msg_Address msgAddress) {

		this.msgAddress = msgAddress;
	}

	public Recipients() {
	}

	public Msg_Address getMsgAddress() {
		return msgAddress;
	}

	public void setMsgAddress(Msg_Address msgAddress) {
		this.msgAddress = msgAddress;
	}
}
