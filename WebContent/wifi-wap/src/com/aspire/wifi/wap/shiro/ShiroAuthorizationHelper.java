package com.aspire.wifi.wap.shiro;

import java.io.Serializable;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroAuthorizationHelper {
	/**
	 * 
	 */
	private static MemoryConstrainedCacheManager cacheManager;
	protected static Logger log =  LoggerFactory.getLogger(ShiroAuthorizationHelper.class);   

	
	
	/**
	 * 清除用户的授权信息
	 * @param username
	 */
	public static void clearAuthorizationInfo(String username){
		if(log.isDebugEnabled()){
			log.debug("clear the " + username + " authorizationInfo");
		}
		//ShiroCasRealm.authorizationCache 为shiro自义cache名(shiroCasRealm为我们定义的reaml类的类名)
		Cache<Object, Object> cache = cacheManager.getCache("ShiroDbRealm.authorizationCache");
		cache.remove(username);
	}
	
	/**
	 * 清除当前用户的授权信息
	 */
	public static void clearAuthorizationInfo(){
		if(SecurityUtils.getSubject().isAuthenticated()){
			clearAuthorizationInfo(ShiroSecurityHelper.getCurrentUsername());
		}
	}
	
	/**清除session(认证信息)
	 * @param JSESSIONID
	 */
	public static void clearAuthenticationInfo(Serializable JSESSIONID){
		if(log.isDebugEnabled()){
			log.debug("clear the session " + JSESSIONID);
		}
		//shiro-activeSessionCache 为shiro自义cache名，该名在org.apache.shiro.session.mgt.eis.CachingSessionDAO抽象类中定义
		//如果要改变此名，可以将名称注入到sessionDao中，例如注入到CachingSessionDAO的子类EnterpriseCacheSessionDAO类中
		Cache<Object, Object> cache = cacheManager.getCache("shiro-activeSessionCache");
		cache.remove(JSESSIONID);
	}

	public static MemoryConstrainedCacheManager getCacheManager() {
		return cacheManager;
	}

	public static void setCacheManager(MemoryConstrainedCacheManager cacheManager) {
		ShiroAuthorizationHelper.cacheManager = cacheManager;
	}


	
	
}