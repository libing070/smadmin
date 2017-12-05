package com.aspire.shakewxpp.wap.http;

import com.aspire.shakewxpp.wap.util.MessageHandleUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.http
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/25
 * @Version V1.0
 * Update Logs:
 */
public class MessageSend {
    private static final Logger logger = LoggerFactory.getLogger(MessageSend.class);

    public static <T> String sendMsg(String url, T t) throws Exception {
        String stringMsg = MessageHandleUtils.marshaller(t);
        logger.debug("发送的消息：{}", stringMsg);
        //
        if (StringUtils.isNotEmpty(stringMsg)) {
            return MessageHandleUtils.postMessage(url, stringMsg);
        }
        return "";
    }
}
