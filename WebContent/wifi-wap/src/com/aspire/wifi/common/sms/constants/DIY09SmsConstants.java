package com.aspire.wifi.common.sms.constants;

import java.util.HashMap;
import java.util.Map;

public class DIY09SmsConstants {

	/** 优先级（0 ：低） */
	public static final int PRIORITY_LOW = 0;
	/** 优先级（1 ：中） */
	public static final int PRIORITY_MIDDLE = 1;
	/** 优先级（2 ：高） */
	public static final int PRIORITY_HIGH = 2;

	/** 短信下发返回码 */
	public final static Map<String, String> SEND_RETVAL = new HashMap<String, String>() {
		/** serialVersionUID */
		private static final long serialVersionUID = 1L;

		{
			put("1", "信息下发缓冲队列已满");
			put("2", "没有建立连接或连接断开");
			put("3", "流量超出设定值");
			put("4", "短信长度超出限制");
			put("5", "群发数量超出限制");
			put("6", "分隔符不正确");
			put("7", "手机号为空");
			put("8", "手机号超长");
			put("78", "手机号为空或号码超长，没有正确的号码");
		}
	};

	/** 上行消息类型（0：正常上行消息deliver） */
	public static final int UPLOAD_MSG_FLAG_DELIVER = 0;
	/** 上行消息类型（1：状态报告report） */
	public static final int UPLOAD_MSG_FLAG_REPORT = 1;

	/** 消息推送状态描述（DELIVRD：已发送） */
	public static final String RESERVE_TYPE_DELIVRD = "DELIVRD";

}
