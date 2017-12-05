package com.aspire.shakewxpp.wap.entity;

import org.apache.commons.lang3.StringUtils;

public class ConfigPojo {
	private int activityId;
	
	//活动状态（活动状态1 有效；2 测试；3无效）
	private int activityStatus;
		
	//活动名称
	private String activityName;
	
	private String freCount;
	
	private String timeRange;
	
	//每天发送的最大红包数量
	private long maxFreCount=0;
	
	//每天用户参与最大频次
	private int maxUserCount;
	
	//每次单一用户抽到的小红包数量范围
	private String subFreCountPeruser;
	
	//抽到大红包有效期
	private int freExpireDays;
		
	//流量币小红包领取有效期
	private int subFreExchangeDays;
	
	//用户开通流量包获得的小红包数量
	private int flowpkgSubFreCountPeruser;
	
	//绑定手机号获得的小红包数量
	private int bindSubFreCountPeruser;
	
	//分享的红包抢完后赠送小红包的流量币数
	private String freTotalSharedGains;
	
	//非绑定用户中得流量币数上限
	private String nonBindUpperLimit;
	
	//非绑定用户中得流量币数上限概率
	private String nonBindUpperLimitPercent;
	
	//非绑定用户中得流量币数下限
	private String nonBindLowerLimit;
	
	//非绑定用户中得流量币数下限概率
	private String nonBindLowerLimitPercent;
	
	//绑定用户中得流量币数上限
	private String bindUpperLimit;
	
	//绑定用户中得流量币数上限概率
	private String bindUpperLimitPercent;
	
	//绑定用户中得流量币数下限
	private String bindLowerLimit;
	
	//绑定用户中得流量币数下限概率
	private String bindLowerLimitPercent;
	
	private String applyProvinceId;
	
	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	//活动描述
	private String activityDesc;

	
	public int getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(int activityStatus) {
		this.activityStatus = activityStatus;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public long getMaxFreCount() {
		return maxFreCount;
	}

	public void setMaxFreCount(long maxFreCount) {
		this.maxFreCount = maxFreCount;
	}

	public int getMaxUserCount() {
		return maxUserCount;
	}

	public void setMaxUserCount(int maxUserCount) {
		this.maxUserCount = maxUserCount;
	}

	public String getSubFreCountPeruser() {
		return subFreCountPeruser;
	}

	public void setSubFreCountPeruser(String subFreCountPeruser) {
		this.subFreCountPeruser = subFreCountPeruser;
	}

	public int getFreExpireDays() {
		return freExpireDays;
	}

	public void setFreExpireDays(int freExpireDays) {
		this.freExpireDays = freExpireDays;
	}

	public int getSubFreExchangeDays() {
		return subFreExchangeDays;
	}

	public void setSubFreExchangeDays(int subFreExchangeDays) {
		this.subFreExchangeDays = subFreExchangeDays;
	}

	public int getFlowpkgSubFreCountPeruser() {
		return flowpkgSubFreCountPeruser;
	}

	public void setFlowpkgSubFreCountPeruser(int flowpkgSubFreCountPeruser) {
		this.flowpkgSubFreCountPeruser = flowpkgSubFreCountPeruser;
	}

	public int getBindSubFreCountPeruser() {
		return bindSubFreCountPeruser;
	}

	public void setBindSubFreCountPeruser(int bindSubFreCountPeruser) {
		this.bindSubFreCountPeruser = bindSubFreCountPeruser;
	}

	public String getFreTotalSharedGains() {
		return freTotalSharedGains;
	}

	public void setFreTotalSharedGains(String freTotalSharedGains) {
		this.freTotalSharedGains = freTotalSharedGains;
	}

	public String getNonBindUpperLimit() {
		return nonBindUpperLimit;
	}

	public void setNonBindUpperLimit(String nonBindUpperLimit) {
		this.nonBindUpperLimit = nonBindUpperLimit;
	}

	public String getNonBindUpperLimitPercent() {
		return nonBindUpperLimitPercent;
	}

	public void setNonBindUpperLimitPercent(String nonBindUpperLimitPercent) {
		this.nonBindUpperLimitPercent = nonBindUpperLimitPercent;
	}

	public String getNonBindLowerLimit() {
		return nonBindLowerLimit;
	}

	public void setNonBindLowerLimit(String nonBindLowerLimit) {
		this.nonBindLowerLimit = nonBindLowerLimit;
	}

	public String getNonBindLowerLimitPercent() {
		return nonBindLowerLimitPercent;
	}

	public void setNonBindLowerLimitPercent(String nonBindLowerLimitPercent) {
		this.nonBindLowerLimitPercent = nonBindLowerLimitPercent;
	}

	public String getBindUpperLimit() {
		return bindUpperLimit;
	}

	public void setBindUpperLimit(String bindUpperLimit) {
		this.bindUpperLimit = bindUpperLimit;
	}

	public String getBindUpperLimitPercent() {
		return bindUpperLimitPercent;
	}

	public void setBindUpperLimitPercent(String bindUpperLimitPercent) {
		this.bindUpperLimitPercent = bindUpperLimitPercent;
	}

	public String getBindLowerLimit() {
		return bindLowerLimit;
	}

	public void setBindLowerLimit(String bindLowerLimit) {
		this.bindLowerLimit = bindLowerLimit;
	}

	public String getBindLowerLimitPercent() {
		return bindLowerLimitPercent;
	}

	public void setBindLowerLimitPercent(String bindLowerLimitPercent) {
		this.bindLowerLimitPercent = bindLowerLimitPercent;
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public String getApplyProvinceId() {
		return applyProvinceId;
	}

	public void setApplyProvinceId(String applyProvinceId) {
		this.applyProvinceId = applyProvinceId;
	}

	public String getFreCount() {
		return freCount;
	}

	public void setFreCount(String freCount) {
		this.freCount = freCount;
		if(StringUtils.isNotEmpty(freCount)){
			String[] arrayFreCount = freCount.split("-");
			for(String tempfreCount : arrayFreCount){
				maxFreCount += Long.parseLong(tempfreCount);
			}
		}	
	}

	public String getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}
}
