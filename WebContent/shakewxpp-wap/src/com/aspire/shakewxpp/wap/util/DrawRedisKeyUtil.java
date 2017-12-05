package com.aspire.shakewxpp.wap.util;

public class DrawRedisKeyUtil {
	public static final String TEST_MODUL_PRE = "testModul_";
	/**
	 * 抽奖活动运行模式（0：测试模式，1：运营模式）
	 */
	public static final int DRAW_MODUL_TYPE_TEST=0;
	public static final int DRAW_MODUL_TYPE_APPLY=1;
	

	/**
	 * 获取被抽走的流量红包List的KEY，list的长度表示抽走流量红包的个数
	 * @param modelTypemodul
	 * @return redis中的key
	 */
	public static String getDrawedFlowNumList(int modelTypemodul){
		String currentDate = DateUtil.getCurDate(DateUtil.yyyyMMdd_EN);	
		String key = "drawedFlowNumList_"+currentDate;
		if(DRAW_MODUL_TYPE_TEST == modelTypemodul){
			key=TEST_MODUL_PRE+"drawedFlowNumList_"+currentDate;
		}
		return key;
	}
	
	/**
	 * 判断当天用户是否已抽红包的key
	 * @param modelTypemodul
	 * @return redis中的key
	 */
	public static String getDrawedFlowUser(String openid,int modelTypemodul){
		String currentDate = DateUtil.getCurDate(DateUtil.yyyyMMdd_EN);	
		String key = "drawedFlowUser_"+openid+currentDate;
		if(DRAW_MODUL_TYPE_TEST == modelTypemodul){
			key=TEST_MODUL_PRE+"drawedFlowUser_"+openid+currentDate;
		}
		return key;
	}
	
	
	/**
	 * 获取该大红包被抢的list在redis中的key
	 * @param modelTypemodul
	 * @return redis中的key
	 */
	public static String getBigFlowRedPackageInfo(int freId,int modelTypemodul){
		String key = "bigFlowInfo_"+freId;
		if(DRAW_MODUL_TYPE_TEST == modelTypemodul){
			key=TEST_MODUL_PRE+"bigFlowInfo_"+freId;
		}
		return key;
	}
}
