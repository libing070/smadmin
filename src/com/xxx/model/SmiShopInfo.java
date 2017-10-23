package com.xxx.model;

import java.io.Serializable;

public class SmiShopInfo implements Serializable{
	private static final long serialVersionUID = -205189814706545519L;

	private Integer id;

    private String shopNo;

    private String shopName;

    private String shopType;

    private String shopPassword;

    private String note;
    private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getShopPassword() {
		return shopPassword;
	}

	public void setShopPassword(String shopPassword) {
		this.shopPassword = shopPassword;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
