package com.aspire.shakewxpp.wap.entity;

public class UserNotifyPojo{
	private int smsId;
	
	//消息类型  1：抽到大红包；2抢到小红包； 3：分享的第1个小红包被抢走 4：分享的第2个小红包被抢走；5：分享的小红包被抢完；6：开通流量套餐
	private Integer smsType;
	
	private String openid;
	
	private String msisdn;
	
	private Integer freId;
	
	private Integer subFreId;
	
	private String productName;
	
	private String weixinAppNo;

	public int getSmsId() {
		return smsId;
	}

	public void setSmsId(int smsId) {
		this.smsId = smsId;
	}

	public Integer getSmsType() {
		return smsType;
	}

	public void setSmsType(Integer smsType) {
		this.smsType = smsType;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public Integer getFreId() {
		return freId;
	}

	public void setFreId(Integer freId) {
		this.freId = freId;
	}

	public Integer getSubFreId() {
		return subFreId;
	}

	public void setSubFreId(Integer subFreId) {
		this.subFreId = subFreId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getWeixinAppNo() {
		return weixinAppNo;
	}

	public void setWeixinAppNo(String weixinAppNo) {
		this.weixinAppNo = weixinAppNo;
	}
	
	
}
