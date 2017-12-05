package com.aspire.shakewxpp.wap.util;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.huawei.eidc.slee.security.Base64;

public class StringTransTool {
	/**
	 * 获取时间格式的字符
	 * 
	 * @return
	 */
	public static String getTimestampStr() {
		Calendar time = Calendar.getInstance();
		String year = String.valueOf(time.get(Calendar.YEAR));
		String month = String.valueOf(time.get(Calendar.MONTH) + 1);
		month = month.length() == 1 ? ("0" + month) : month;
		String day = String.valueOf(time.get(Calendar.DATE));
		day = day.length() == 1 ? ("0" + day) : day;
		String hour = String.valueOf(time.get(Calendar.HOUR));
		hour = hour.length() == 1 ? ("0" + hour) : hour;
		String minute = String.valueOf(time.get(Calendar.MINUTE));
		minute = minute.length() == 1 ? ("0" + minute) : minute;
		String second = String.valueOf(time.get(Calendar.SECOND));
		second = second.length() == 1 ? ("0" + second) : second;
		String milliSecond = String.valueOf(Calendar.MILLISECOND);
		milliSecond = milliSecond.length() == 1 ? ("0" + milliSecond)
				: milliSecond;
		return year + month + day + hour + minute + second + milliSecond;
	}
	
	public static String base64Decode(String str) {
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		try {
			return new String(Base64.decode(str.trim()), "utf-8");
		} catch (UnsupportedEncodingException e) {

			return "";
		}
	}
	
	public static String base64Encode(String str) throws Exception {
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		return Base64.encode(str.trim().getBytes("gbk"));
	}
	
	public static String replaceDeline(String str) {

		return str.replace("__", "_");
	}
	
	public static String getRandomNum4String(int length) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < length; i++) {
			result.append(String.valueOf(new Random().nextInt(10)));
		}
		return result.toString();
	}
	
	public static String replaceNull(Object object) {

		return object != null ? object.toString() : "";
	}
	
	public static String encodeUnicode(String gbString) {     
	       char[] utfBytes = gbString.toCharArray();     
	       String unicodeBytes = "";     
	       for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {     
	           String hexB = Integer.toHexString(utfBytes[byteIndex]);     
	           if (hexB.length() <= 2) {     
	               hexB = "00" + hexB;     
	           }     
	            unicodeBytes = unicodeBytes + "\\u" + hexB;     
	        }     
	       return unicodeBytes;    
	    } 
}
