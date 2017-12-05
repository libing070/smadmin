/**
 * 
 */
package com.aspire.shakewxpp.wap.smstool.bean.sms;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author chenping
 * @2013-5-23
 */
@XStreamAlias("Msg_Address")
public class Msg_Address {

	@XStreamAlias("AddressType")
	private String addressType;

	@XStreamAlias("ClassType")
	private String classType;

	@XStreamAlias("Address")
	private String address;

	/**
	 * @param addressType
	 * @param classType
	 * @param address
	 */
	public Msg_Address(String addressType, String classType, String address) {

		this.addressType = addressType;
		this.classType = classType;
		this.address = address;
	}

	public Msg_Address() {
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
