package com.aspire.shakewxpp.wap.service.impl;

import com.aspire.shakewxpp.wap.entity.FlowSubRedEnvelopeNotify;
import com.aspire.shakewxpp.wap.entity.GrabFlowSubRedEnvelope;
import com.aspire.shakewxpp.wap.mapper.FlowSubRedEnvelopeNotifyMapper;
import com.aspire.shakewxpp.wap.mapper.GrabFlowSubRedEnvelopeMapper;
import com.aspire.shakewxpp.wap.service.ThirdInterfService;
import com.aspire.shakewxpp.wap.service.thread.GetLoginTokenThread;
import com.aspire.shakewxpp.wap.util.MessageHandleUtils;
import com.aspire.shakewxpp.wap.util.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.service.impl
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/25
 * @Version V1.0
 * Update Logs:
 */
@Service("thirdInterfService")
public class ThirdInterfServiceImpl implements ThirdInterfService {
    private static final Logger logger = LoggerFactory.getLogger(ThirdInterfServiceImpl.class);

    @Resource(name = "flowSubRedEnvelopeNotifyMapper")
    private FlowSubRedEnvelopeNotifyMapper flowSubRedEnvelopeNotifyMapper;

    @Resource(name = "grabFlowSubRedEnvelopeMapper")
    private GrabFlowSubRedEnvelopeMapper grabFlowSubRedEnvelopeMapper;

    @Resource(name = "getLoginTokenThread")
    private GetLoginTokenThread getLoginTokenThread;

 

    /**
     * 获取登录令牌
     * <p/>
     * 说明：向流量汇发送获取登录令牌的请求
     *
     * @param msisdn 手机号
     * @param dateTime 操作时间yyyyMMddHHmmss
     * @return {"Ticket": xxxxx, "ExpireTime": YYYY-MM-DD HH:MI:SS}
     * @throws Exception
     */
    @Override
    public Map<String, String> getLoginToken(String msisdn, String dateTime) throws Exception {
        logger.debug("开始获取登录令牌接口调用");
        Map<String, String> param = new HashMap<String, String>();
        param.put("Msisdn", msisdn);
        param.put("DateTime", dateTime);
        param.put("TransactionID", MessageHandleUtils.getTransactionID());
        getLoginTokenThread.setParam(param);
        return getLoginTokenThread.call();
    }


}
