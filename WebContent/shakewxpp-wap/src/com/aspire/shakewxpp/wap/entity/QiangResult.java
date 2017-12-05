package com.aspire.shakewxpp.wap.entity;

public class QiangResult {
	//流量币数量
	private Float folwCoinNum;
	//兑换过期时间
	private String expiredTime;
	//抢红包结果   -1：抢红包成功；0:已经抢到过；1：红包已抢完；2：红包已过期
	private Integer failType;

	public Float getFolwCoinNum() {
		return folwCoinNum;
	}

	public void setFolwCoinNum(Float folwCoinNum) {
		this.folwCoinNum = folwCoinNum;
	}

	public Integer getFailType() {
		return failType;
	}

	public void setFailType(Integer failType) {
		this.failType = failType;
	}

	public String getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(String expiredTime) {
		this.expiredTime = expiredTime;
	}
	
	
	
	
	
}
