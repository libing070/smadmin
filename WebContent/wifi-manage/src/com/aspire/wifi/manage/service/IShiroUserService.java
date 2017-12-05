package com.aspire.wifi.manage.service;

import java.util.List;

import com.aspire.wifi.manage.entity.ShiroUser;
import com.aspire.wifi.manage.exception.WxppException;

public interface IShiroUserService {

	public ShiroUser getByAccount(String account) throws WxppException;
	
	Integer getShiroUserListCount(ShiroUser shiroUser)throws WxppException;
	
	List<ShiroUser>getShiroUserList(ShiroUser shiroUser)throws WxppException;
	
	Integer isShiroUserList(String account)throws WxppException;
	
}
