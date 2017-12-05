package com.aspire.wifi.wap.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.mysql.jdbc.Blob;
/**
 * 用户类
 * **/
public class UserInfoEntity extends Object implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3458719888610400980L;
	
	private String mobile;			//手机号
	private String nickname;		//昵称
	private char sex;				//性别
	private byte[] head_show;		//头像
	private String provinceId;		//省份ID
	private String province;		//老家所在省份	private String cityId;			//城市ID
	private String city;			//老家所在城市
	private String reg_date;		//操作时间
	private String sure_regDate;		//注册时间
	private int onlyId;		//注册时间
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public byte[] getHead_show() {
		return head_show;
	}
	public void setHead_show(byte[] headShow) {
		head_show = headShow;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getSure_regDate() {
		return sure_regDate;
	}
	public void setSure_regDate(String sure_regDate) {
		this.sure_regDate = sure_regDate;
	}
	public int getOnlyId() {
		return onlyId;
	}
	public void setOnlyId(int onlyId) {
		this.onlyId = onlyId;
	}
	
}
