package com.xxx.controller.valueobject;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.stereotype.Component;

/**
 * 影院店铺信息vo
 * 
 * @author author
 * @date 2016/02/16
 */
@Component
@JsonSerialize
public class ShopInfoVO {
	
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
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the shopNo
	 */
	public String getShopNo() {
		return shopNo;
	}
	/**
	 * @param shopNo the shopNo to set
	 */
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	/**
	 * @return the shopName
	 */
	public String getShopName() {
		return shopName;
	}
	/**
	 * @param shopName the shopName to set
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	/**
	 * @return the shopType
	 */
	public String getShopType() {
		return shopType;
	}
	/**
	 * @param shopType the shopType to set
	 */
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	/**
	 * @return the shopPassword
	 */
	public String getShopPassword() {
		return shopPassword;
	}
	/**
	 * @param shopPassword the shopPassword to set
	 */
	public void setShopPassword(String shopPassword) {
		this.shopPassword = shopPassword;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
}
