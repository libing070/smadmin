package com.aspire.wifi.wap.entity;

public class Parameter {
	private int  begin;
	private int  end;
	private String videoName;
	private String msisdn;
	private String appName;
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}

	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	
	public Parameter(int begin, int end, String videoName) {
		super();
		this.begin = begin;
		this.end = end;
		this.videoName = videoName;
	}
	
	public Parameter(int end,String msisdn) {
		super();
		this.end = end;
		this.msisdn = msisdn;
	}
	
	public Parameter(int begin, int end, String videoName ,String appName) {
		super();
		this.begin = begin;
		this.end = end;
		this.videoName = videoName;
		this.appName=appName;
	}
	public Parameter() {
	}
	
}
