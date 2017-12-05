package com.aspire.wifi.wap.cache;

import java.util.HashMap;
import java.util.Map;

import com.aspire.wifi.wap.entity.ShiroUser;

public class LocalCache {
	public static Map<String,ShiroUser> loginInfo = new HashMap<String,ShiroUser>();
	
	public static Map<String,ShiroUser> map = new HashMap<String,ShiroUser>();
}
