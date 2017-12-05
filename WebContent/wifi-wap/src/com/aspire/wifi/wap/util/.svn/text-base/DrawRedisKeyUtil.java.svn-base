package com.aspire.wifi.wap.util;



public class DrawRedisKeyUtil {
	public static final String TEST_MODUL_PRE = "testDraw_";
	/**
	 * 抽奖活动运行模式（0：测试模式，1：运营模式）
	 */
	public static final int DRAW_MODUL_TYPE_TEST=0;
	public static final int DRAW_MODUL_TYPE_APPLY=1;
	
	
	/**
	 * 获取奖品中奖List在reids中的KEY
	 * @param drawConfigId
	 * @param prizeId
	 * @param modelTypemodul
	 * @return
	 */
	public static String getRedisWinPrizedKey(Integer drawConfigId,Integer prizeId,int modelTypemodul){
		String currentDate = DateUtil.getCurDate(DateUtil.yyyyMMdd_EN);	
		String key = "drawDayly_"+drawConfigId+"_"+prizeId+"_"+currentDate;
		if(DRAW_MODUL_TYPE_TEST == modelTypemodul){
			key=TEST_MODUL_PRE+"drawDayly_"+drawConfigId+"_"+prizeId+"_"+currentDate;
		}
		return key;
	}
	
	/**
	 * 该用户已抽奖次数
	 * @param openid
	 * @param modelTypemodul
	 * @return
	 */
	public static String getUseKey(String openid,int modelTypemodul){
		String currentDate = DateUtil.getCurDate(DateUtil.yyyyMMdd_EN);	
		String key = "drawDaylyUse_"+openid+"_"+currentDate;
		if(DRAW_MODUL_TYPE_TEST == modelTypemodul){
			key=TEST_MODUL_PRE+"drawDaylyUse_"+openid+"_"+currentDate;
		}
		return key;
	}
	
	/**
	 * 用户当日能抽奖的次数
	 * @param openid
	 * @param modelTypemodul
	 * @return
	 */
	public static String getTimesKey(String openid,int modelTypemodul){
		String currentDate = DateUtil.getCurDate(DateUtil.yyyyMMdd_EN);	
		String key = "drawDaylyTimes_"+openid+"_"+currentDate;
		if(DRAW_MODUL_TYPE_TEST == modelTypemodul){
			key = TEST_MODUL_PRE+"drawDaylyTimes_"+openid+"_"+currentDate;
		}
		return key;
	}
	
	/**
	 * 取最近中奖人的key
	 * @param drawConfigId
	 * @param modelTypemodul
	 * @return
	 */
	public static String getNowWinnerKey(Integer drawConfigId,int modelTypemodul){
		String currentDate = DateUtil.getCurDate(DateUtil.yyyyMMdd_EN);	
		String key = "nowWinner_"+drawConfigId+"_"+currentDate;
		if(DRAW_MODUL_TYPE_TEST == modelTypemodul){
			key = TEST_MODUL_PRE+"nowWinner_"+drawConfigId+"_"+currentDate;
		}
		return key;
	}
	
	
	public static String getDayWinningMaxnumKey(Integer prizeId,int modelTypemodul){
		String currentDate = DateUtil.getCurDate(DateUtil.yyyyMMdd_EN);	
		String key = "dayWinningMaxnum_"+prizeId+"_"+currentDate;
		if(DRAW_MODUL_TYPE_TEST == modelTypemodul){
			key = TEST_MODUL_PRE+"dayWinningMaxnum_"+prizeId+"_"+currentDate;
		}
		return key;
	}
}
