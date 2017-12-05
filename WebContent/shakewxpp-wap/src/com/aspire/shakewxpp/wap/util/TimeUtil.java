package com.aspire.shakewxpp.wap.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * wayne
 * 
 * @author wayne
 * 
 */
public class TimeUtil {
	public static String PATTERN_DATE_1 = "yyyy-MM-dd";
	public static String PATTERN_DATE_2 = "yyyyMMdd";
	public static String PATTERN_DATE_3 = "yyyyMMddhhmmss";
	public static String PATTERN_DATE_4 = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 将时间转为指定格式的字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getTimeStr(Date date, String pattern) {
		if (null == date) {
			return "";
		}
		return new SimpleDateFormat(pattern).format(date);
	}


	/**
	 * 获取指定范围的随机数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getRandom(int start, int end) {
		return start
				+ ((int) (Math.random() * 10 * (end - start + 2)) % (end
						- start + 1));
	}

	/**
	 * 获取某年某月最大天数
	 * 
	 * @param year
	 *            年
	 * 
	 * @param month
	 *            月
	 * 
	 * @return
	 */
	public static int getMaxDay(String year, String month) {
		// 计算某一月份的最大天数

		Calendar time = Calendar.getInstance();
		time.clear(); // 若不clear，很多信息会继承自系统当前时间

		time.set(Calendar.YEAR, Integer.valueOf(year));
		time.set(Calendar.MONTH, Integer.valueOf(month) - 1);
		int day = time.getActualMaximum(Calendar.DAY_OF_MONTH); // 本月总天数

		return day;
	}

	public static String getYearMonth() {

		Calendar time = Calendar.getInstance();
		String year = String.valueOf(time.get(Calendar.YEAR));
		String month = String.valueOf(time.get(Calendar.MONTH) + 1);
		month = month.length() == 1 ? "0" + month : month;
		return year + month;

	}
}
