/**
 * 
 */
package com.aspire.shakewxpp.wap.smstool.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author chenping
 * @2013-5-21
 */
@XStreamAlias("Address_Info")
public class Address_Info {

	@XStreamAlias("DeviceType")
	private String deviceType;

	@XStreamAlias("DeviceID")
	private String deviceId;
	
	public Address_Info(String deviceType,String deviceId){
		
		this.deviceId = deviceId;
		this.deviceType = deviceType;
	}
	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}
