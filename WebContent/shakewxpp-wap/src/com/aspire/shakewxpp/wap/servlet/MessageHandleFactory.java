package com.aspire.shakewxpp.wap.servlet;

import com.aspire.shakewxpp.wap.servlet.handler.MarketCreditNotifyHandler;
import com.aspire.shakewxpp.wap.servlet.interf.MarketCreditNotifyReq;
import com.aspire.shakewxpp.wap.util.MessageHandleUtils;
import com.aspire.shakewxpp.wap.util.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.servlet
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/25
 * @Version V1.0
 * Update Logs:
 */
public class MessageHandleFactory {
    private static final Logger logger = LoggerFactory.getLogger(MessageHandleFactory.class);

    public static void factory(String strRequestMessage, String messageType) throws Exception{
        if (messageType.equals("MarketCreditNotifyReq")) {
            logger.debug("收到请求消息[MarketCreditNotifyReq]");
            MarketCreditNotifyReq req = new MarketCreditNotifyReq();
            MarketCreditNotifyReq result = (MarketCreditNotifyReq) MessageHandleUtils.unMarshaller(strRequestMessage, req);
            if (result != null) {
                logger.debug("收到请求消息[MarketCreditNotifyReq]，" + result.toString());
                //
                ThreadUtils.getInstance().execute(new MarketCreditNotifyHandler(result));
            } else {
                logger.error("收到请求消息[MarketCreditNotifyReq]，转换对象出现异常");
            }
        }
    }
}
