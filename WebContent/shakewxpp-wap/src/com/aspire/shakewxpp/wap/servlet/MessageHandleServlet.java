package com.aspire.shakewxpp.wap.servlet;

import com.aspire.shakewxpp.wap.http.MessageSend;
import com.aspire.shakewxpp.wap.servlet.handler.Handler;
import com.aspire.shakewxpp.wap.servlet.interf.YXReq;
import com.aspire.shakewxpp.wap.servlet.interf.YXResp;
import com.aspire.shakewxpp.wap.util.MessageHandleUtils;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: TechTest
 * @Package com.techtest.monitor.servlet
 * @Description: 处理HTTP消息
 * @Author wuyuehui_a
 * @Date 2014/8/23
 * @Version V1.0
 * Update Logs:
 */
public class MessageHandleServlet extends HttpServletHandle {
    private static final Logger logger = LoggerFactory.getLogger(MessageHandleServlet.class);

    /**
     * 处理字符串消息
     *
     * @param strRequestMessage
     * @throws Exception
     */
    @Override
    void handleRequestMessage(String strRequestMessage) throws Exception {
        logger.debug("开始处理请求");
        Document document = parseStringToXml(strRequestMessage);
        String rootNodeName = document.getRootElement().getName();
        MessageHandleFactory.factory(strRequestMessage, rootNodeName);
        logger.debug("结束处理请求");
    }

    /**
     * 检查远程主机是否授权连接
     *
     * @param remoteHostIp
     * @return
     * @throws Exception
     */
    @Override
    Boolean checkRemoteHostIp(String remoteHostIp) throws Exception {

        return Boolean.TRUE;
    }
}
