package com.aspire.shakewxpp.wap.entity;

import java.util.Date;

/**
 * 微信提示语内容设置
 * @author 
 *
 */
public class WeixinVariable extends BaseDomain{
	//提示语变量值
	private String variableName;
	//提示语内容
	private String variableValue;
	//有效时间，单位：秒
	private String expiresIn;
	//修改时间
	private Date updateTime;
	private String updateTimeDesc;
	
	//状态 1：可用 0：不可用
	private String status;
	public static final String AVAILABLE_STATUS = "1";
	public static final String UNAVAILABLE_STATUS = "0";
	public static final String AVAILABLE_STATUSDESC = "可用";
	public static final String UNAVAILABLE_STATUSDESC = "不可用";
	private String statusDesc;
	//提示语描述
	private String tipDesc;
	
	private Integer shirouserId;
	
	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getVariableValue() {
		return variableValue;
	}

	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}

	public String getExpiresIn() {
		return expiresIn;
	}
	
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateTimeDesc() {
		return updateTimeDesc;
	}

	public void setUpdateTimeDesc(String updateTimeDesc) {
		this.updateTimeDesc = updateTimeDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDesc() {
		if(this.getStatus().equals(this.AVAILABLE_STATUS)){
			this.statusDesc = this.AVAILABLE_STATUSDESC;
		}else{
			this.statusDesc = this.UNAVAILABLE_STATUSDESC;
		}
		return this.statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		statusDesc = statusDesc;
	}
	
	public String getTipDesc() {
		return tipDesc;
	}

	public void setTipDesc(String tipDesc) {
		this.tipDesc = tipDesc;
	}

	
	public Integer getShirouserId() {
		return shirouserId;
	}

	public void setShirouserId(Integer shirouserId) {
		this.shirouserId = shirouserId;
	}

	@Override
	public String toString() {
		return "WeixinVariable [variableName=" + variableName + 
						", variableValue=" + variableValue + 
						", expiresIn=" + expiresIn + 
						", updateTime=" + updateTime  + 
						", updateTimeDesc=" + updateTimeDesc  + 
						", status"+ status + 
						", StatusDesc=" + statusDesc  + 
						", tipDesc=" + tipDesc  +"]";
	}
}
