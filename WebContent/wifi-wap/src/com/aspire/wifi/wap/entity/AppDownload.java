package com.aspire.wifi.wap.entity;

import java.math.BigDecimal;

public class AppDownload {
	private Integer id;
	private String appName;
	private byte[] icon;
	private String appVersion;
	private BigDecimal appSize;
	private Integer appDownloadTimes;
	private String appDesc;
	private String appType;//应用类型
	private String appFileUrl;//应用下载url
	private int appStarLevel;//推荐星级
	private String appDeveloper;//应用开发者
	private byte[] appDemoPic1;//应用界面截图1
	private byte[] appDemoPic2;//应用界面截图2
	private byte[] appDemoPic3;//应用界面截图3
	private byte[] appTopPic;//置顶图片
	private int begin;//开始查询数
	private int times;//查询多少条
	private String isIndex;//是否首页查询
	private String appForSystem;//系统支持
	private int clickTimes;//app点击量

	public String getIsIndex() {
		return isIndex;
	}

	public void setIsIndex(String isIndex) {
		this.isIndex = isIndex;
	}

	public String getAppForSystem() {
		return appForSystem;
	}

	public void setAppForSystem(String appForSystem) {
		this.appForSystem = appForSystem;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}


	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public BigDecimal getAppSize() {
		return appSize;
	}

	public void setAppSize(BigDecimal appSize) {
		this.appSize = appSize;
	}


	public Integer getAppDownloadTimes() {
		return appDownloadTimes;
	}

	public void setAppDownloadTimes(Integer appDownloadTimes) {
		this.appDownloadTimes = appDownloadTimes;
	}

	public String getAppDesc() {
		return appDesc;
	}

	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}

	public byte[] getIcon() {
		return icon;
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getAppFileUrl() {
		return appFileUrl;
	}

	public void setAppFileUrl(String appFileUrl) {
		this.appFileUrl = appFileUrl;
	}

	public int getAppStarLevel() {
		return appStarLevel;
	}

	public void setAppStarLevel(int appStarLevel) {
		this.appStarLevel = appStarLevel;
	}

	public String getAppDeveloper() {
		return appDeveloper;
	}

	public void setAppDeveloper(String appDeveloper) {
		this.appDeveloper = appDeveloper;
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

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public byte[] getAppDemoPic3() {
		return appDemoPic3;
	}

	public void setAppDemoPic3(byte[] appDemoPic3) {
		this.appDemoPic3 = appDemoPic3;
	}

	public byte[] getAppTopPic() {
		return appTopPic;
	}

	public void setAppTopPic(byte[] appTopPic) {
		this.appTopPic = appTopPic;
	}

	public int getClickTimes() {
		return clickTimes;
	}

	public void setClickTimes(int clickTimes) {
		this.clickTimes = clickTimes;
	}




}
