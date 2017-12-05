package com.aspire.wifi.manage.util;

import java.util.Iterator;
import java.util.Set;

/**
 * 表情特殊符号转换
 * @author caozhaoyan
 *
 */
public class KeyWordTrans {
	
	/**
	 * 表情特殊符号转换
	 * @param message
	 * @return
	 */
	public static String trans(String message) throws Exception{
		String transMessage = message;
		Set<String> set = Constants.expressionMap.keySet();
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();)
		{
			String keyword =  iterator.next();
			transMessage.contains(keyword);
			String unicodeKey = encodeUnicode(keyword);
			transMessage = transMessage.replaceAll(unicodeKey, Constants.expressionMap.get(keyword));
		}
		return transMessage;
	}
	
	public static String encodeUnicode(String gbString) throws Exception{     
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
	
	public static String decodeUnicode(String uinidoceString) throws Exception{     
		return  new String(uinidoceString.getBytes("unicode"),"UTF-8");
	} 
}
