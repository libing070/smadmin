package com.aspire.wifi.wap.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {
	private static int count = 0;

	private static int RANDOM_MAX_NUM = 99999;
	/**
     * wap客户端cookie名称
     */
   
	private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);

	public static String getTransactionID() {
		return getTimeH() + "" + getRandom();
	}

	public static String getTime() {
		SimpleDateFormat ss = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = ss.format(new Date());
		return time;
	}

	public static String getTimeH() {
		SimpleDateFormat ss = new SimpleDateFormat("yyyyMMddHH");
		String time = ss.format(new Date());
		return time;
	}

	public static synchronized int getCount() {
		count++;
		if (count >= 1000) {
			count = 0;
		}
		return count;
	}

	private static synchronized int getRandom() {
		Random random = new Random();
		return random.nextInt(RANDOM_MAX_NUM);
	}
	
	public static int random(int from, int to) {
		return (int)(Math.random()* (to - from)  + from );
	}

	public static boolean trim(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public static String md5Encryption(String s) {
		return "";
	}

	public static String formatEmpty(String par) {
		if (null == par) {
			return "";
		}
		return par;
	}

	/**
	 * 将传入的参数左补 0
	 * 
	 * @param str
	 *            传入参数
	 * @param size
	 *            位数
	 * @return
	 */
	public static String fillLeft(String str, int size) {
		StringBuilder sb = new StringBuilder();
		char[] charValue = new char[size];
		for (int i = 0; i < size; i++) {
			charValue[i] = '0';
		}
		sb.append(charValue).append(str);
		return sb.substring(sb.length() - size);
	}

	/**
	 * 去掉前面的0
	 * 
	 * @param price
	 * @return
	 */
	public static String trimPrefix(String str) {
		return str.replaceAll("^(0+)", "");

	}

	/**
	 * 获取Apache代理前的真实IP
	 * 
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		ip = XssHttpServletRequestWrapper.getOrgRequest(request).getHeader(
				"x-forwarded-for");
		if (ip != null && ip.trim().length() > 0
				&& !"unknown".equalsIgnoreCase(ip.trim())) {
			if (ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static void main(String[] args) {
		System.out.println(fillLeft("32", 3));
	}

}
