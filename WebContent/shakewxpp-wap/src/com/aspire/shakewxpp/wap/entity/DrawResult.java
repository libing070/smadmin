package com.aspire.shakewxpp.wap.entity;

public class DrawResult {
	private long subRedFolwNum;
	
	//0:成功；1：抢过了；2：来晚了;3:测试模式
	private Integer failType;
	
	private Integer freId;
	
	public long getSubRedFolwNum() {
		return subRedFolwNum;
	}
	public void setSubRedFolwNum(long subRedFolwNum) {
		this.subRedFolwNum = subRedFolwNum;
	}
	public Integer getFailType() {
		return failType;
	}
	public void setFailType(Integer failType) {
		this.failType = failType;
	}
	public Integer getFreId() {
		return freId;
	}
	public void setFreId(Integer freId) {
		this.freId = freId;
	}
	
	
	
}
