package com.aspire.shakewxpp.wap.service.thread;

import com.aspire.portal.web.security.client.GenerateSignature;
import com.aspire.shakewxpp.wap.http.MessageSend;
import com.aspire.shakewxpp.wap.servlet.handler.Handler;
import com.aspire.shakewxpp.wap.servlet.interf.Property;
import com.aspire.shakewxpp.wap.servlet.interf.YXReq;
import com.aspire.shakewxpp.wap.servlet.interf.YXResp;
import com.aspire.shakewxpp.wap.util.GetConfigFile;
import com.aspire.shakewxpp.wap.util.MessageHandleUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.service.thread
 * @Description: 原采用callable实现线程返回结果，实现有问题，故取消该callable接口
 * @Author wuyuehui_a
 * @Date 2014/8/26
 * @Version V1.0
 * Update Logs:
 */
@Component(value = "getLoginTokenThread")
public class GetLoginTokenThread {
    private static final Logger logger = LoggerFactory.getLogger(GetLoginTokenThread.class);
    private Map<String, String> param = null;

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    public Map<String, String> call() throws Exception {
        Map<String, String> returnMap = new HashMap<String, String>();
        try {
            //
            String transactionID = param.remove("TransactionID");
            String privatekey = GetConfigFile.getInstance().getProperties("wx_privatekey");
            String privateKeyPath = System.getProperty("user.dir");
            String privateKeyFile = privateKeyPath.concat(File.separator).concat("shakewxpp-wap").concat(File.separator)
                    .concat("conf").concat(File.separator).concat("key").concat(File.separator).concat(privatekey);
            File keyFile = new File(privateKeyFile);
            if (!keyFile.exists()) {
                logger.warn("流量汇签名文件不存在，中断此次任务");
                return returnMap;
            }
            GenerateSignature sign = new GenerateSignature();
            //
            StringBuffer signText = new StringBuffer();
            signText.append("Msisdn=").append(param.get("Msisdn")).append("&")
                    .append("TransactionID=").append(transactionID).append("&")
                    .append("DateTime=").append(param.get("DateTime"));
            //
            logger.debug("签证前字符：" + signText.toString());
            String signCode = sign.sign(signText.toString(), keyFile.getAbsolutePath());
            logger.debug("签证后字符：" + signCode);
            param.put("sign", signCode);
            //
            String deviceid = GetConfigFile.getInstance().getProperties("deviceid");
            //
            YXReq yxReq = new YXReq();
            yxReq.setTransactionID(transactionID);
            yxReq.setVersion(Handler.INTERF_MSG_VERSION);
            yxReq.setSendAddress(MessageHandleUtils.getAddress(deviceid));
            yxReq.setDestAddress(MessageHandleUtils.getAddress(Handler.INTERF_MSG_DESTADDR));
            yxReq.setFunCode(Handler.INTERF_MSG_FUN_GETTICKET);
            yxReq.setParams(MessageHandleUtils.getProperties(param));
            //
            String loginTokenTargetUrl = GetConfigFile.getInstance().getProperties("logintokentargeturl");
            //
            String stringResp = MessageSend.sendMsg(loginTokenTargetUrl, yxReq);
            if (StringUtils.isEmpty(stringResp)) {
                logger.warn("获取登录令牌接口调用失败，返回消息为空");
                return returnMap;
            }
            YXResp _yxResp = new YXResp();
            YXResp yxResp = (YXResp) MessageHandleUtils.unMarshaller(stringResp, _yxResp);
            if (null == yxResp) {
                logger.warn("获取登录令牌接口调用失败，返回消息格式错误");
                return returnMap;
            }
            //
            if (yxReq.getTransactionID().equals(yxResp.getTransactionID())
                    && yxResp.gethRet().equals(Handler.INTERF_MSG_RESP_HRET_SUCCESS)) {
                logger.info("获取登录令牌接口调用成功");
                //
                List<Property> propertyList = yxResp.getParams();
                if (propertyList == null || propertyList.size() <= 0) {
                    logger.warn("获取登录令牌接口返回消息，返回参数为空");
                } else {
                    for (Property property : propertyList) {
                        if (StringUtils.isEmpty(property.getValue())) {
                            logger.warn("获取登录令牌接口返回消息，返回参数[" + property.getName() + "]的对应值为空");
                            continue;
                        }
                        returnMap.put(property.getName(), property.getValue());
                    }
                }
            } else {
                logger.warn("获取登录令牌接口调用失败，返回码[" + yxResp.gethRet() + "]");
            }
            logger.debug("结束获取登录令牌接口调用");
        } catch (Exception ex) {
            logger.error("流量币赠送线程出现异常", ex);
        }
        return returnMap;
    }
}
