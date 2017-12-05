package com.aspire.wifi.common.auth.entity;

import java.io.Serializable;

/**
 * 认证数据.
 */
public class AuthDeviceAccountInfo implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 账号 */
	private String userAccount;

	/** 密码 */
	private String userPasswd;

	/** 使用状态（0：未使用、1：已使用） */
	private Integer usedFlag;

	/**
	 * 取得账号
	 * 
	 * @return 账号
	 */
	public String getUserAccount() {
		return userAccount;
	}

	/**
	 * 设置账号
	 * 
	 * @param userAccount
	 *            账号
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * 取得密码
	 * 
	 * @return 密码
	 */
	public String getUserPasswd() {
		return userPasswd;
	}

	/**
	 * 设置密码
	 * 
	 * @param userPasswd
	 *            密码
	 */
	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	/**
	 * 取得使用状态（0：未使用、1：已使用）
	 * 
	 * @return 使用状态（0：未使用、1：已使用）
	 */
	public Integer getUsedFlag() {
		return usedFlag;
	}

	/**
	 * 设置使用状态（0：未使用、1：已使用）
	 * 
	 * @param usedFlag
	 *            使用状态（0：未使用、1：已使用）
	 */
	public void setUsedFlag(Integer usedFlag) {
		this.usedFlag = usedFlag;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("userAccount:" + userAccount);
		sb.append(", userPasswd:" + userPasswd);
		sb.append(", usedFlag:" + usedFlag);
		return sb.toString();
	}

}
