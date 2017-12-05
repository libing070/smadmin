package com.aspire.wifi.wap.entity;

import java.util.Date;

public class ConsumePlaceEntity {
	private Integer consumePlaceId;
	private String consumePlaceName;
	private String consumePlaceAddr;
	private String consumePlaceDesc;
	private Integer actTypeId;
	private Date publishDate;
	private String publishUser;
	private String businessCode;
	private String dinnerPhone;

	public Integer getConsumePlaceId() {
		return consumePlaceId;
	}

	public void setConsumePlaceId(Integer consumePlaceId) {
		this.consumePlaceId = consumePlaceId;
	}

	public String getConsumePlaceName() {
		return consumePlaceName;
	}

	public void setConsumePlaceName(String consumePlaceName) {
		this.consumePlaceName = consumePlaceName;
	}

	public String getConsumePlaceAddr() {
		return consumePlaceAddr;
	}

	public void setConsumePlaceAddr(String consumePlaceAddr) {
		this.consumePlaceAddr = consumePlaceAddr;
	}

	public String getConsumePlaceDesc() {
		return consumePlaceDesc;
	}

	public void setConsumePlaceDesc(String consumePlaceDesc) {
		this.consumePlaceDesc = consumePlaceDesc;
	}

	public Integer getActTypeId() {
		return actTypeId;
	}

	public void setActTypeId(Integer actTypeId) {
		this.actTypeId = actTypeId;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getPublishUser() {
		return publishUser;
	}

	public void setPublishUser(String publishUser) {
		this.publishUser = publishUser;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getDinnerPhone() {
		return dinnerPhone;
	}

	public void setDinnerPhone(String dinnerPhone) {
		this.dinnerPhone = dinnerPhone;
	}

}
