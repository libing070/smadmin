package com.aspire.wifi.manage.entity;

import java.util.Date;

import com.aspire.wifi.manage.base.BaseDomain;

public class AppInfoEntity extends BaseDomain{

	
	private String appId;
	private String appName;
	private String appDesc;
	private String appType;
	private String appForSystem;
	private byte[] icon;
	private String  appFileUrl;
	private String appVersion;
	private String appSize;
	private String  appDownloadTimes;
	private String appStarLevel;
	private String appDeveloper;
	private byte[] appDemoPic1;
	private byte[] appDemoPic2;
	private byte[] appDemoPic3;
	private byte[] appDemoPic4;
	private String appStatus;
	private Date lastUpdateTime;
	private String lastUpdateTimeStr;
	private String isTopArea;
	private byte[] appTopPic;
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppDesc() {
		return appDesc;
	}
	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}

	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getAppForSystem() {
		return appForSystem;
	}
	public void setAppForSystem(String appForSystem) {
		this.appForSystem = appForSystem;
	}

	public String getAppFileUrl() {
		return appFileUrl;
	}
	public void setAppFileUrl(String appFileUrl) {
		this.appFileUrl = appFileUrl;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getAppSize() {
		return appSize;
	}
	public void setAppSize(String appSize) {
		this.appSize = appSize;
	}
	
	public String getAppDownloadTimes() {
		return appDownloadTimes;
	}
	public void setAppDownloadTimes(String appDownloadTimes) {
		this.appDownloadTimes = appDownloadTimes;
	}
	public String getAppStarLevel() {
		return appStarLevel;
	}
	public void setAppStarLevel(String appStarLevel) {
		this.appStarLevel = appStarLevel;
	}
	public String getAppDeveloper() {
		return appDeveloper;
	}
	public void setAppDeveloper(String appDeveloper) {
		this.appDeveloper = appDeveloper;
	}
	
	public byte[] getIcon() {
		return icon;
	}
	public void setIcon(byte[] icon) {
		this.icon = icon;
	}
	public byte[] getAppDemoPic1() {
		return appDemoPic1;
	}
	public void setAppDemoPic1(byte[] appDemoPic1) {
		this.appDemoPic1 = appDemoPic1;
	}
	public byte[] getAppDemoPic2() {
		return appDemoPic2;
	}
	public void setAppDemoPic2(byte[] appDemoPic2) {
		this.appDemoPic2 = appDemoPic2;
	}
	public byte[] getAppDemoPic3() {
		return appDemoPic3;
	}
	public void setAppDemoPic3(byte[] appDemoPic3) {
		this.appDemoPic3 = appDemoPic3;
	}
	public byte[] getAppDemoPic4() {
		return appDemoPic4;
	}
	public void setAppDemoPic4(byte[] appDemoPic4) {
		this.appDemoPic4 = appDemoPic4;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getIsTopArea() {
		return isTopArea;
	}
	public void setIsTopArea(String isTopArea) {
		this.isTopArea = isTopArea;
	}

	public byte[] getAppTopPic() {
		return appTopPic;
	}
	public void setAppTopPic(byte[] appTopPic) {
		this.appTopPic = appTopPic;
	}
	public String getLastUpdateTimeStr() {
		return lastUpdateTimeStr;
	}
	public void setLastUpdateTimeStr(String lastUpdateTimeStr) {
		this.lastUpdateTimeStr = lastUpdateTimeStr;
	}
	

}
