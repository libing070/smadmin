package com.aspire.wifi.wap.entity;

import java.sql.Timestamp;

public class ZhuanFaEntity {
	private Long  id;
	private String  mobile;
	private Timestamp  createTime;
	private Long  zhuanFaId;
	private Timestamp  updateTime;
	private String  isCreater;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Long getZhuanFaId() {
		return zhuanFaId;
	}
	public void setZhuanFaId(Long zhuanFaId) {
		this.zhuanFaId = zhuanFaId;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public String getIsCreater() {
		return isCreater;
	}
	public void setIsCreater(String isCreater) {
		this.isCreater = isCreater;
	}
	
	
	
}
