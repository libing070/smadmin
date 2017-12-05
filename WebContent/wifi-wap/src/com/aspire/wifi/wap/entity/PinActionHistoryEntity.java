package com.aspire.wifi.wap.entity;

import java.math.BigInteger;
import java.util.Date;

public class PinActionHistoryEntity {

	private BigInteger histId;
	private BigInteger flashSaleId;
	private String ownerMobile;
	private Date actionDate;
	private Integer actionType;
	private String actionDesc;
	private Date flashSaleDate;
	private Date createTableDate;

	public BigInteger getHistId() {
		return histId;
	}

	public void setHistId(BigInteger histId) {
		this.histId = histId;
	}

	public BigInteger getFlashSaleId() {
		return flashSaleId;
	}

	public void setFlashSaleId(BigInteger flashSaleId) {
		this.flashSaleId = flashSaleId;
	}

	public String getOwnerMobile() {
		return ownerMobile;
	}

	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

	public String getActionDesc() {
		return actionDesc;
	}

	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

	public Date getFlashSaleDate() {
		return flashSaleDate;
	}

	public void setFlashSaleDate(Date flashSaleDate) {
		this.flashSaleDate = flashSaleDate;
	}

	public Date getCreateTableDate() {
		return createTableDate;
	}

	public void setCreateTableDate(Date createTableDate) {
		this.createTableDate = createTableDate;
	}

}
