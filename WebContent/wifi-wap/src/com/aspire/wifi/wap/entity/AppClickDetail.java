package com.aspire.wifi.wap.entity;
/**
 * app应用点击明细表
 * 
 * **/
public class AppClickDetail {
	private int appId;
	private String mobile;
	private String nickname;
	private String operDate;

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public String getOperDate() {
		return operDate;
	}
	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}

	
}
