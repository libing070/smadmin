/**
 * 
 */
package com.aspire.shakewxpp.wap.smstool.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author chenping
 * @2013-5-21
 */
public class Address {
	@XStreamAlias("Address_Info")
	private Address_Info addressInfo;

	public Address(Address_Info addressInfo) {

		this.addressInfo = addressInfo;
	}

	public Address() {
	}

	public Address_Info getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(Address_Info addressInfo) {
		this.addressInfo = addressInfo;
	}

}
