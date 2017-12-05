/**
 * 
 */
package com.aspire.wifi.manage.util;



/**
 * @author chenping
 * @2013-5-24
 */
public class ConfigPathBean {

	// System.getenv("PLT_HOME");
	// private static String WEB_ROOT_PATH =
	// System.getProperty("payClient.root");
	private static String user_dir = System.getProperty("user.dir");

	private static String sep = System.getProperty("file.separator");;

	public static String getRedisConfigPath() {
		// return WEB_ROOT_PATH + "WEB-INF" + sep + "config" + sep + "system"
		// + sep + "redis.properties";
		return user_dir + sep +"wifi-manage"+sep+ "conf" + sep + "redis.properties";
	}


}
