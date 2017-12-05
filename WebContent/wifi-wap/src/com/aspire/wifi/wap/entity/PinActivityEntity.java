package com.aspire.wifi.wap.entity;

import java.math.BigInteger;

public class PinActivityEntity {
 
	private BigInteger activityId;
	private String activityName;
    private Integer activityStatus;
    private Integer startWeekDay;
    private Integer endWeekDay;
    private Integer startHour;
    private Integer endHour;
    private Integer quota;
    private Integer cashSubsidy;
    private Integer salePersonNum;
    private Integer persionParticipateCnt;
    private Integer  pinActionInvalidate;
    private Integer  pinCreatetableInvalidate;
    private BigInteger visitPvCnt;
    private String ruleDesc;
     
    public String getRuleDesc() {
		return ruleDesc;
	}
	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}
	//    private Integer activityCount;
//	
//    public Integer getActivityCount() {
//		return activityCount;
//	}
//	public void setActivityCount(Integer activityCount) {
//		this.activityCount = activityCount;
//	}
	public BigInteger getActivityId() {
		return activityId;
	}
	public void setActivityId(BigInteger activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public Integer getActivityStatus() {
		return activityStatus;
	}
	public void setActivityStatus(Integer activityStatus) {
		this.activityStatus = activityStatus;
	}
	public Integer getStartWeekDay() {
		return startWeekDay;
	}
	public void setStartWeekDay(Integer startWeekDay) {
		this.startWeekDay = startWeekDay;
	}
	public Integer getEndWeekDay() {
		return endWeekDay;
	}
	public void setEndWeekDay(Integer endWeekDay) {
		this.endWeekDay = endWeekDay;
	}
	public Integer getStartHour() {
		return startHour;
	}
	public void setStartHour(Integer startHour) {
		this.startHour = startHour;
	}
	public Integer getEndHour() {
		return endHour;
	}
	public void setEndHour(Integer endHour) {
		this.endHour = endHour;
	}
	public Integer getQuota() {
		return quota;
	}
	public void setQuota(Integer quota) {
		this.quota = quota;
	}
	public Integer getCashSubsidy() {
		return cashSubsidy;
	}
	public void setCashSubsidy(Integer cashSubsidy) {
		this.cashSubsidy = cashSubsidy;
	}
	public Integer getSalePersonNum() {
		return salePersonNum;
	}
	public void setSalePersonNum(Integer salePersonNum) {
		this.salePersonNum = salePersonNum;
	}
	public Integer getPersionParticipateCnt() {
		return persionParticipateCnt;
	}
	public void setPersionParticipateCnt(Integer persionParticipateCnt) {
		this.persionParticipateCnt = persionParticipateCnt;
	}
	public Integer getPinActionInvalidate() {
		return pinActionInvalidate;
	}
	public void setPinActionInvalidate(Integer pinActionInvalidate) {
		this.pinActionInvalidate = pinActionInvalidate;
	}
	public Integer getPinCreatetableInvalidate() {
		return pinCreatetableInvalidate;
	}
	public void setPinCreatetableInvalidate(Integer pinCreatetableInvalidate) {
		this.pinCreatetableInvalidate = pinCreatetableInvalidate;
	}
	public BigInteger getVisitPvCnt() {
		return visitPvCnt;
	}
	public void setVisitPvCnt(BigInteger visitPvCnt) {
		this.visitPvCnt = visitPvCnt;
	}
	
}
