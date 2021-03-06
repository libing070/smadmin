/**
 * 
 */
package com.aspire.shakewxpp.wap.smstool.bean.sms;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author chenping
 * @2013-5-23
 */
@XStreamAlias("Senders")
public class Senders {
	@XStreamAlias("Msg_Address")
	private Msg_Address msgAddress;

	/**
	 * @param msgAddress
	 */
	public Senders(Msg_Address msgAddress) {

		this.msgAddress = msgAddress;
	}

	public Senders() {
	}

	public Msg_Address getMsgAddress() {
		return msgAddress;
	}

	public void setMsgAddress(Msg_Address msgAddress) {
		this.msgAddress = msgAddress;
	}
}
