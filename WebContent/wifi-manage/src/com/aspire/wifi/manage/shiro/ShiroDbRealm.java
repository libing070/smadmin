package com.aspire.wifi.manage.shiro;

import java.util.ArrayList;
import java.util.List;

import com.aspire.wifi.manage.entity.ShiroUser;
import com.aspire.wifi.manage.exception.WxppException;
import com.aspire.wifi.manage.service.IShiroUserService;
import com.aspire.wifi.manage.entity.ShiroPermission;
import com.aspire.wifi.manage.entity.ShiroRole;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class ShiroDbRealm extends AuthorizingRealm {

    @Resource(name = "shiroUserService")
    private IShiroUserService shiroUserService;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 获取当前登录的用户名
		String account = (String) super.getAvailablePrincipal(principals);

		List<String> roles = new ArrayList<String>();
		List<String> permissions = new ArrayList<String>();
		ShiroUser user = null;
		try {
			user = shiroUserService.getByAccount(account);
		} catch (WxppException e) {
			e.printStackTrace();
		}
		if (user == null) {
			throw new AuthorizationException("用户[" + account + "]不存在");
		}
		if (user.getRoles() == null || user.getRoles().isEmpty()) {
			throw new AuthorizationException("用户[" + account + "]未分配角色");
		}
		for (ShiroRole role : user.getRoles()) {
			roles.add(role.getName());
			if (role.getPmss() == null || role.getPmss().isEmpty()) {
				throw new AuthorizationException("角色[" + role.getName() + "]未分配权限");
			}
			for (ShiroPermission pmss : role.getPmss()) {
				if (!StringUtils.isEmpty(pmss.getPermission())) {
					permissions.add(pmss.getPermission());
				}
			}
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
			user = shiroUserService.getByAccount(token.getUsername());
		} catch (WxppException e) {
			e.printStackTrace();
		}
		if (user != null) {
			return new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(), user.getNickname());
		} else {
			return null;
		}
	}
}
