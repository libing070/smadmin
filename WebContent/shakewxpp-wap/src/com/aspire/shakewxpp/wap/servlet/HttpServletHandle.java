package com.aspire.shakewxpp.wap.servlet;

import com.aspire.shakewxpp.wap.util.MessageHandleUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 * @Title: TechTest
 * @Package com.techtest.monitor.servlet
 * @Description: 消息处理SERVLET抽象类
 * @Author wuyuehui_a
 * @Date 2014/8/23
 * @Version V1.0
 * Update Logs:
 */
public abstract class HttpServletHandle extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(HttpServletHandle.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("开始处理HTTP请求消息...");
        try {
            String remoteHostIp = readRemoteHost(req);
            if (!checkRemoteHostIp(remoteHostIp)) {
                throw new Exception("此远程主机[" + remoteHostIp + "]未授权连接");
            }
            String requestMsg = readRequestMessageToString(req);
            logger.info("收到主机[" + remoteHostIp + "]发出的请求消息：" + requestMsg);
            handleRequestMessage(requestMsg);
        } catch (Exception ex) {
            logger.error("处理HTTP请求消息出现异常", ex);
        }
        logger.debug("结束处理HTTP请求消息...");
    }

    /**
     * 读取请求字符串消息
     *
     * @param request
     * @return
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    protected String readRequestMessageToString(HttpServletRequest request) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            if (StringUtils.isEmpty(line)) {
                continue;
            }
            line = URLDecoder.decode(line, "UTF-8");
//            if (line.contains("=<")) {
//                line = StringUtils.split(line, "=", 2)[1];
//            }
            sb.append(line.trim());
        }
        return sb.toString();
    }

    /**
     * 解析字符串为XML对象
     *
     * @param requestMsg
     * @return
     * @throws java.io.IOException
     */
    protected Document parseStringToXml(String requestMsg) throws IOException {
        try {
            if (StringUtils.isEmpty(requestMsg))
                return null;
            MessageHandleUtils.checkXml(requestMsg);
            return DocumentHelper.parseText(requestMsg);
        } catch (DocumentException e) {
            logger.error("解析XML的消息出现异常", e);
            throw new IOException("解析XML的消息出现异常");
        } catch (Exception e) {
            logger.error("解析XML的消息出现异常", e);
            throw new IOException("解析XML的消息出现异常");
        }
    }

    /**
     * 读取消息发出的主机IP
     *
     * @param request
     * @return
     */
    protected String readRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 写入响应字符串内容
     *
     * @param response
     * @param strResponse
     */
    protected void writeResponseStringMsg(HttpServletResponse response, String strResponse) {
        PrintWriter pw = null;
        try {
            response.setContentType("text/xml;charset=utf-8");
            pw = response.getWriter();
            pw.print(strResponse);
        } catch (IOException ex) {
            logger.error("写入消息出现异常：", ex);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    /**
     * 写入响应JSON内容
     *
     * @param response
     * @param strResponse
     */
    protected void writeResponseJsonMsg(HttpServletResponse response, String strResponse) {
        PrintWriter pw = null;
        try {
            response.setContentType("application/json;charset=utf-8");
            pw = response.getWriter();
            pw.print(strResponse);
        } catch (IOException ex) {
            logger.error("写入消息出现异常：", ex);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    /**
     * 处理字符串消息
     *
     * @param strRequestMessage
     * @return
     * @throws Exception
     */
    abstract void handleRequestMessage(String strRequestMessage) throws Exception;

    /**
     * 检查远程主机是否授权连接
     *
     * @param remoteHostIp
     * @return
     * @throws Exception
     */
    abstract Boolean checkRemoteHostIp(String remoteHostIp) throws Exception;
}
