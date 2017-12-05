package com.aspire.shakewxpp.wap.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class User extends BaseDomain {

	private static final long serialVersionUID = 7419229779731522702L;

	private String openID;
	
	private String score = "";
	
	private String nickName;
	
	private String headImgUrl;
	
	private String remark = "";

	private String bindMsisdn = "";
	
	private String weixinAppNo;
	
	private String userGroupId;
	
	private String userGroupName;

	private Date createDate;
	
	private Date updateDate;
	
	private String password;//动态密码
	
	private String validateCode;//验证码
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public String getValidateCode() {
		return validateCode;
	}



	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	private String startDate;
	private String endDate;
	
	private String queryText;
	
	private int sex;
	
	private String city = "";
	
	private String country = "";
	
	private String province = "";
	
	private String language;
	
	private String subscribe;
	
	//最新关注时间
	private Date subscribeTime ;
	
	private Integer receiveInfoId;
	
	public Integer getReceiveInfoId() {
		return receiveInfoId;
	}

	public void setReceiveInfoId(Integer receiveInfoId) {
		this.receiveInfoId = receiveInfoId;
	}

	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}



	public String getProvince() {
		return province;
	}



	public void setProvince(String province) {
		this.province = province;
	}



	public String getLanguage() {
		return language;
	}



	public void setLanguage(String language) {
		this.language = language;
	}



	public Date getSubscribeTime() {
		return subscribeTime;
	}



	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}


	
	public String getQueryText() {
		return queryText;
	}



	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}



	public String getScore() {
		return score;
	}



	public void setScore(String score) {
		this.score = score;
	}

		
	
	public String getOpenID() {
		return openID;
	}



	public void setOpenID(String openID) {
		this.openID = openID;
	}



	public String getNickName() {
		if("2".equals(subscribe) && StringUtils.isEmpty(nickName)){
			return "游客";
		}
		return nickName;
	}



	public void setNickName(String nickName) {
		this.nickName = nickName;
	}



	public String getHeadImgUrl() {
		return headImgUrl;
	}



	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public String getBindMsisdn() {
		return bindMsisdn;
	}



	public void setBindMsisdn(String bindMsisdn) {
		this.bindMsisdn = bindMsisdn;
	}



	public String getUserGroupId() {
		return userGroupId;
	}


	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public Date getUpdateDate() {
		return updateDate;
	}



	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	

	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}



	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	public String getWeixinAppNo() {
		return weixinAppNo;
	}

	public void setWeixinAppNo(String weixinAppNo) {
		this.weixinAppNo = weixinAppNo;
	}

	@Override
	public String toString() {
		return "User [openID=" + openID + 
						", score=" + score + 
						", nickName=" + nickName + 
						", headImgUrl=" + headImgUrl  + 
						", remark=" + remark  + 
						", bindMsisdn=" + bindMsisdn  + 
						", userGroupId=" + userGroupId  + 
						", createDate=" + createDate  + 
						", updateDate=" + updateDate  + "]";
	}

}
