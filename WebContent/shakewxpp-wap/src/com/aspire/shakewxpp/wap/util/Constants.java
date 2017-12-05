package com.aspire.shakewxpp.wap.util;

import java.util.HashMap;
import java.util.Map;



public class Constants {
	
	//首页
	public static final Integer WX_ME_TYPE_INDEX = 0;
	//详情
	public static final Integer WX_ME_TYPE_INFO = 1;
	//兑换成功提示
	public static final String TIP_CONTENT_EXCHANGE_Y = "tip_content_Exchange_Y";
	//兑换失败提示
	public static final String TIP_CONTENT_EXCHANGE_N = "tip_content_Exchange_N";
	//未登记手机号提示语
	public static final String TIP_CONTENT_MOBILEBUILD_N = "tip_content_mobileBuild_N";
	
	public static final Map<String,String> copmErrorCode=new HashMap<String, String>();
    static {
    	copmErrorCode.put("0110","用户停机");
    	copmErrorCode.put("0117","用户不存在");
    	copmErrorCode.put("0101","业务不存在");
    	copmErrorCode.put("0102","业务已暂停");
    	copmErrorCode.put("0107","用户已经订购此业务");
    	copmErrorCode.put("0112","用户权限不足");
    }
    
    /**
	 * 订购关系状态  0：临时订购(受理)；1：订购成功；2：订购失败；3：临时退订；4：已退订; 5:暂停
	 */
	public static final int STATUS_TEMP_SUB_FAIL=-1;
	public static final int STATUS_TEMP_SUB_SUCC=0;
	public static final int STATUS_SUB_S=1;
	public static final int STATUS_SUB_S_F=2;
	public static final int STATUS_TEMP_UNSUB=3;
	public static final int STATUS_UNSUB=4;
	public static final int STATUS_UNSUB_STOP=5;
	
	//一天的秒数86400
    public static final int DAY_SEC = 86410;
    
    /**
     * 抽红包结果   0:成功；1：抢过了；2：来晚了;3:测试模式；4：未开始
     */
    public static final int DRAW_FLOW_RED_SUCCESS=0;
    public static final int DRAW_FLOW_RED_DONE=1;
    public static final int DRAW_FLOW_RED_OVER=2;
    public static final int DRAW_FLOW_RED_TEST=3;
    public static final int DRAW_FLOW_RED_READY=4;
    
    /**
     * 抢红包结果   -1：抢红包成功；0:已经抢到过；1：红包已抢完；2：红包已过期
     */
    public static final int RED_BAG_SUCCESS=-1;
    public static final int RED_BAG_GAIN=0;
    public static final int RED_BAG_NONE=1;
    public static final int RED_BAG_PAST_DUE=2;
    
    
    /**
     * 流量红包来源  :1 移动免费红包 2 绑定手机号获得红包 3 开通流量套餐
     */
    public static final int FRE_FROM_SOURCE_DARW=1;
    public static final int FRE_FROM_SOURCE_BIND=2;
    public static final int FRE_FROM_SOURCE_BUY=3;
    public static final int FRE_FROM_SOURCE_JIANGLI=5;
    
    /**
     * 发送短信验证码： 1 绑定的动态验证码；2 获取流量红包的动态验证码
     */
    public static final int SEND_MSG_TYPE_FRO_BIND=1;
    public static final int SEND_MSG_TYPE_FRO_GET_FLOW=2;
    
    /**
     * 用户关注状态：0 取消关乎用户 ；1 关注用户 ；2 游客
     */
    public static final String USER_UNSUBSCRIPT ="0";
    public static final String USER_SUBSCRIPT ="1";
    public static final String USER_SUBSCRIPT_TEMP="2";
    
    /**
     * 消息类型  1：抽到大红包；2抢到小红包； 3：分享的第1个小红包被抢走 4：分享的第2个小红包被抢走；5：分享的小红包被抢完；6：开通流量套餐
     */
    public static final int SMS_TYPE_FRE = 1;
    public static final int SMS_TYPE_SUB_FRE = 2;
    public static final int SMS_TYPE_SHARE_ONE = 3;
    public static final int SMS_TYPE_SHARE_TWO= 4;
    public static final int SMS_TYPE_SHARE_OVER= 5;
    public static final int SMS_TYPE_BUY= 6;
    public static final int SMS_TYPE_BIND= 7;
}
