package com.aspire.wifi.common.sms.constants;

public class SmsConstants {

	/** 发送状态（0：待发送） */
	public static final int SEND_STATUS_WAITING = 0;
	/** 发送状态（1 ：发送中） */
	public static final int SEND_STATUS_SENDING = 1;
	/** 发送状态（2 ：已发送） */
	public static final int SEND_STATUS_SUCCESS = 2;
	/** 发送状态（3 ：发送出错） */
	public static final int SEND_STATUS_FAILURE = 3;

	/** 优先级 1 最高级 2 次之 **/
	public static final int SEND_PRIORITY_HIGH = 1;
	public static final int SEND_PRIORITY_NORMAL = 2;
	
	/** 系统配置参数名（WIFI_SMS_NOTICE_PASSWORD：短信验证码） */
	public static final String WIFI_SMS_NOTICE_PASSWORD = "WIFI_SMS_NOTICE_PASSWORD";
	
	/** 系统配置参数名（SYSCONFIG_WIFI_SMS_NOTICE_MAX_RETRY_TIMES：短信下发通知出错最大重试次数） */
	public static final String SYSCONFIG_WIFI_SMS_NOTICE_MAX_RETRY_TIMES = "WIFI_SMS_NOTICE_MAX_RETRY_TIMES";
	
	/** 系统配置参数名（WIFI_SMS_NOTICE_PIN_TABLE_CONSUME_VERIFY_ERROR_CHANNEL：用户前往消费，商户根据验证码进行验证） */
	public static final String WIFI_SMS_NOTICE_PIN_TABLE_CONSUME_VERIFY_ERROR_CHANNEL = "WIFI_SMS_NOTICE_PIN_TABLE_CONSUME_VERIFY_ERROR_CHANNEL";
	
	/** 系统配置参数名（WIFI_SMS_NOTICE_PIN_TABLE_CONSUME_VERIFY_OK：用户前往消费，商户根据验证码进行验证） */
	public static final String WIFI_SMS_NOTICE_PIN_TABLE_CONSUME_VERIFY_OK = "WIFI_SMS_NOTICE_PIN_TABLE_CONSUME_VERIFY_OK";
	
	/** 系统配置参数名（WIFI_SMS_NOTICE_PIN_TABLE_CONSUME_VERIFY_USED：用户前往消费，商户根据验证码进行验证） */
	public static final String WIFI_SMS_NOTICE_PIN_TABLE_CONSUME_VERIFY_USED = "WIFI_SMS_NOTICE_PIN_TABLE_CONSUME_VERIFY_USED";
	
	/** 系统配置参数名（WIFI_SMS_NOTICE_PIN_TABLE_DONE：桌主确认开桌后，桌主、其余入伙人收到的通知短信） */
	public static final String WIFI_SMS_NOTICE_PIN_TABLE_DONE = "WIFI_SMS_NOTICE_PIN_TABLE_DONE";
	
	/** 系统配置参数名（WIFI_SMS_NOTICE_PIN_TABLE_DONE_OWNER_VERIFY_CODE：桌主确认开桌后，桌主收到的验证码短信） */
	public static final String WIFI_SMS_NOTICE_PIN_TABLE_DONE_OWNER_VERIFY_CODE = "WIFI_SMS_NOTICE_PIN_TABLE_DONE_OWNER_VERIFY_CODE";
	
	/** 短信下发通知出错最大重试次数，默认值3 */
	public static final int WIFI_SMS_NOTICE_MAX_RETRY_TIMES_DEFAULT_VAL = 3;


	/** 系统配置参数名（WIFI_SMS_NOTICE_PIN_TIREN：桌主踢人） */
	public static final String WIFI_SMS_NOTICE_PIN_TIREN = "WIFI_SMS_NOTICE_PIN_TIREN";
	
	/** 系统配置参数名（WIFI_SMS_NOTICE_PIN_JIESAN：桌主解散该桌） */
	public static final String WIFI_SMS_NOTICE_PIN_JIESAN = "WIFI_SMS_NOTICE_PIN_JIESAN";
	
	/** 系统配置参数名（WIFI_SMS_NOTICE_PIN_OWNMOBLIE：消费点验证成功，给桌主发短信） */
	public static final String WIFI_SMS_NOTICE_PIN_OWNMOBLIE = "WIFI_SMS_NOTICE_PIN_OWNMOBLIE";
	
	/** 系统配置参数名（WIFI_SMS_NOTICE_MAN_MOBLIE：人工，群发模板） */
	public static final String WIFI_SMS_NOTICE_MAN_MOBLIE = "WIFI_SMS_NOTICE_MAN_MOBLIE";
	
	/** 系统配置参数名（WIFI_SMS_NOTICE_QIANG_TICKET：抢票中奖，群发模板） */
	public static final String WIFI_SMS_NOTICE_QIANG_TICKET = "WIFI_SMS_NOTICE_QIANG_TICKET";
	/** 系统配置参数名（WIFI_SMS_NOTICE_QIANG_TICKET：抢票资格，群发模板） */
	public static final String WIFI_SMS_NOTICE_QIANG_TICKET2 = "WIFI_SMS_NOTICE_QIANG_TICKET2";
	//2表示珍珂儿面膜使用
	public static final String WIFI_SMS_NOTICE_YOUHUI_2="WIFI_SMS_NOTICE_YOUHUI_2";
	//3表示黄记煌
	public static final String WIFI_SMS_NOTICE_YOUHUI_3="WIFI_SMS_NOTICE_YOUHUI_3";
	//4表示玉林串串香
	public static final String WIFI_SMS_NOTICE_YOUHUI_4="WIFI_SMS_NOTICE_YOUHUI_4";
	//5表示保利电影院
	public static final String WIFI_SMS_NOTICE_YOUHUI_5="WIFI_SMS_NOTICE_YOUHUI_5";
	//6丁丁洋回转自助火锅
	public static final String WIFI_SMS_NOTICE_YOUHUI_6="WIFI_SMS_NOTICE_YOUHUI_6";
	//转发活动发起或参与人模板
	public static final String WIFI_SMS_NOTIVE_CISHANG="WIFI_SMS_NOTIVE_CISHANG";
}
