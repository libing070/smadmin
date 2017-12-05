package com.aspire.wifi.manage.entity;

import java.util.Collection;
import java.util.Date;

import com.aspire.wifi.manage.base.BaseDomain;

public class ShiroUser extends BaseDomain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6002919839467170935L;
	
	private Integer id;
	private String account;
	private String password;
	private String nickname;
	private Date createTime;
	
	private String appId;
	private String appSecret;
	private String token;
	private String isServerApp;//0:服务号,1:订阅号
	private String weixinAppNo;//微信平台中公众号的唯一标识
	
	private Collection<ShiroRole> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Collection<ShiroRole> getRoles() {
		return roles;
	}

	public void setRoles(Collection<ShiroRole> roles) {
		this.roles = roles;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIsServerApp() {
		return isServerApp;
	}

	public void setIsServerApp(String isServerApp) {
		this.isServerApp = isServerApp;
	}

	public String getWeixinAppNo() {
		return weixinAppNo;
	}

	public void setWeixinAppNo(String weixinAppNo) {
		this.weixinAppNo = weixinAppNo;
	}
	
	

}
