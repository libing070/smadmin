package com.aspire.wifi.manage.mapper;

import java.util.List;

import com.aspire.wifi.manage.entity.ShiroUser;

public interface ShiroUserMapper {

	ShiroUser queryOneByName(String account);
	
	Integer getShiroUserListCount(ShiroUser shiroUser);
	
	List<ShiroUser>getShiroUserList(ShiroUser shiroUser);
	
	Integer isShiroUserList(String shiroUser);
}
