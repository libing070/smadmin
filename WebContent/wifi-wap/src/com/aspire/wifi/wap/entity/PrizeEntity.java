package com.aspire.wifi.wap.entity;

import java.sql.Timestamp;

public class PrizeEntity {
	private Long  id;
	private String  prizeName;
	private Timestamp  createTime;
	private int  activityId;
	private int controlNum ;
	private int prizeRate ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPrizeName() {
		return prizeName;
	}
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public int getControlNum() {
		return controlNum;
	}
	public void setControlNum(int controlNum) {
		this.controlNum = controlNum;
	}
	public int getPrizeRate() {
		return prizeRate;
	}
	public void setPrizeRate(int prizeRate) {
		this.prizeRate = prizeRate;
	}
	
}
