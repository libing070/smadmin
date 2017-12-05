package com.aspire.wifi.wap.entity;

import java.io.Serializable;

public class StudentReport implements Serializable {


	/**
	 * 新生报到表
	 */
	private static final long serialVersionUID = 1L;
	public static final int AUDITI_RESULT_WAITING = 0;
	public static final int AUDITI_RESULT_PASS = 1;
	public static final int AUDITI_RESULT_NOTPASS = 2;
	public static final int AUDITI_RESULT_NOTCOMMIT = 3;
	private Integer id;
	
	private String msisdn;
	private byte[] reportPic;
	private Integer result;
	private String resultDesc;
	private Integer resultBack;
	private String createTime;
	private String createUser;
	private String isFootmark;

	public String getIsFootmark() {
		return isFootmark;
	}

	public void setIsFootmark(String isFootmark) {
		this.isFootmark = isFootmark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public Integer getResultBack() {
		return resultBack;
	}

	public void setResultBack(Integer resultBack) {
		this.resultBack = resultBack;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

    @Override
    public String toString() {
        return "StudentReport{" +
                "id=" + id +
                ", msisdn='" + msisdn + '\'' +
                ", reportPic='" + reportPic + '\'' +
                ", result=" + result +
                ", resultDesc='" + resultDesc + '\'' +
                ", resultBack=" + resultBack +
                ", createTime='" + createTime + '\'' +
                ", createUser='" + createUser + '\'' +
                '}';
    }

	public byte[] getReportPic() {
		return reportPic;
	}

	public void setReportPic(byte[] reportPic) {
		this.reportPic = reportPic;
	}
}
