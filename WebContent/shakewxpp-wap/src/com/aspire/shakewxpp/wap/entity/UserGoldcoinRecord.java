package com.aspire.shakewxpp.wap.entity;

import java.util.Date;

/**
 * 用户金币获取记录表
 * @author
 *
 */
public class UserGoldcoinRecord extends BaseDomain {

	private static final long serialVersionUID = -1463290993821548435L;
	
	private String userGoldcoinRecoredId;
	
	private String canal;//金币渠道 1：营销活动    2：推荐ID    3：活动体验
	private String canalDesc;
	
	public static final String CANAL_ACTIVITY = "1";
	public static final String CANAL_RECOMMEND = "2";
	public static final String CANAL_EXPERIENCE = "3";
	public static final String CANAL_SUB = "4";
	public static final String CANAL_ACTIVITY_DESC = "营销活动";
	public static final String CANAL_RECOMMEND_DESC = "业务推荐";
	public static final String CANAL_EXPERIENCE_DESC = "活动体验";
	public static final String CANAL_SUB_DESC = "订购业务";
	
	private String canalId;//对应的渠道ID，如营销活动ID；推荐ID；体验活动ID
	
	private String addGoldcoinNum;//增加金币数
	
	private Date createDate;//
	private String createDateDesc;
		
	private Date updateDate;//
	private String updateDateDesc; 
	
	private String openId;//奖励金币用户
	
	private String visitOpenId;//回访用户
	
	private String operatingDesc;//操作描述
	
	
	public String getUserGoldcoinRecoredId() {
		return userGoldcoinRecoredId;
	}

	public void setUserGoldcoinRecoredId(String userGoldcoinRecoredId) {
		this.userGoldcoinRecoredId = userGoldcoinRecoredId;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}
	
	public String getCanalDesc() {
		this.canalDesc = "";
		if(this.getCanal()!=null && this.getCanal()!=""){
			if(this.getCanal().equals(this.CANAL_ACTIVITY)){
				this.canalDesc = this.CANAL_ACTIVITY_DESC;
			}else if(this.getCanal().equals(this.CANAL_EXPERIENCE)){
				this.canalDesc = this.CANAL_EXPERIENCE_DESC;
			}else if(this.getCanal().equals(this.CANAL_RECOMMEND)){
				this.canalDesc = this.CANAL_RECOMMEND_DESC;
			}
		}
		return canalDesc;
	}
	
	public void setCanalDesc(String canalDesc) {
		this.canalDesc = canalDesc;
	}

	public String getCanalId() {
		return canalId;
	}

	public void setCanalId(String canalId) {
		this.canalId = canalId;
	}
	
	public String getAddGoldcoinNum() {
		return addGoldcoinNum;
	}

	public void setAddGoldcoinNum(String addGoldcoinNum) {
		this.addGoldcoinNum = addGoldcoinNum;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateDateDesc() {
		return createDateDesc;
	}

	public void setCreateDateDesc(String createDateDesc) {
		this.createDateDesc = createDateDesc;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateDateDesc() {
		return updateDateDesc;
	}

	public void setUpdateDateDesc(String updateDateDesc) {
		this.updateDateDesc = updateDateDesc;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	public String getVisitOpenId() {
		return visitOpenId;
	}

	public void setVisitOpenId(String visitOpenId) {
		this.visitOpenId = visitOpenId;
	}
	
	public String getOperatingDesc() {
		return operatingDesc;
	}

	public void setOperatingDesc(String operatingDesc) {
		this.operatingDesc = operatingDesc;
	}

	@Override
	public String toString() {
		return "UserGoldcoinRecord [userGoldcoinRecoredId=" + userGoldcoinRecoredId + 
						", canal=" + canal + 
						", canalDesc=" + canalDesc + 
						", canalId=" + canalId  + 
						", addGoldcoinNum=" + addGoldcoinNum  + 
						", createDate"+ createDate + 
						", updateDate=" + updateDate  + 
						", openId=" + openId  +
						", visitOpenId="+ visitOpenId +"]";
	}
	
}
