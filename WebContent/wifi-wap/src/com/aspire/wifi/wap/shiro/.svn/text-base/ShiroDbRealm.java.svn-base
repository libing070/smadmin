package com.aspire.wifi.wap.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.aspire.wifi.wap.cache.LocalCache;
import com.aspire.wifi.wap.entity.ShiroUser;

public class ShiroDbRealm extends AuthorizingRealm {

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 获取当前登录的用户名
		String username = (String) super.getAvailablePrincipal(principals);

		List<String> roles = new ArrayList<String>();
		List<String> permissions = new ArrayList<String>();
		ShiroUser user = null;
		try {
			user = LocalCache.loginInfo.get(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user == null) {
			throw new AuthorizationException("用户[" + username + "]不存在");
		}

		// 给当前用户设置角色
		info.addRoles(roles);
		// 给当前用户设置权限
		info.addStringPermissions(permissions);
		return info;

	}

	/**
	 * 认证回调函数,登录时调用
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		ShiroUser user = null;
		try {	
			user = LocalCache.loginInfo.get(token.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user != null) {
			return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), user.getUsername());
		} else {
			return null;
		}
	}
}
