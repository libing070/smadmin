package com.aspire.shakewxpp.wap.servlet.handler;

import com.aspire.shakewxpp.wap.entity.FlowSubRedEnvelopeNotify;
import com.aspire.shakewxpp.wap.entity.GrabFlowSubRedEnvelope;
import com.aspire.shakewxpp.wap.http.MessageSend;
import com.aspire.shakewxpp.wap.service.InterHelpService;
import com.aspire.shakewxpp.wap.servlet.interf.MarketCreditNotifyReq;
import com.aspire.shakewxpp.wap.servlet.interf.MarketCreditNotifyResp;
import com.aspire.shakewxpp.wap.util.BeanLocator;
import com.aspire.shakewxpp.wap.util.DateUtil;
import com.aspire.shakewxpp.wap.util.GetConfigFile;
import com.aspire.shakewxpp.wap.util.MessageHandleUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.servlet.handler
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/25
 * @Version V1.0
 * Update Logs:
 */
public class MarketCreditNotifyHandler extends Handler {
    private static final Logger logger = LoggerFactory.getLogger(MarketCreditNotifyHandler.class);
    private MarketCreditNotifyReq marketCreditNotifyReq;

    public MarketCreditNotifyHandler(MarketCreditNotifyReq marketCreditNotifyReq) {
        this.marketCreditNotifyReq = marketCreditNotifyReq;
    }

    @Override
    public void handle() {
        logger.debug("开始处理流量币赠送结果通知请求");
        MarketCreditNotifyResp resp = new MarketCreditNotifyResp();
        resp.setTransactionID(marketCreditNotifyReq.getTransactionID());
        resp.setVersion(marketCreditNotifyReq.getVersion());
        try {
            if (!MessageHandleUtils.checkSign(marketCreditNotifyReq)) {
                logger.warn("此次请求消息验证签名不通过");
                resp.sethRet("40001"); //数字签名错误
            } else {
                InterHelpService interHelpService = (InterHelpService) BeanLocator.getInstance().getBean("interHelpService");
                FlowSubRedEnvelopeNotify notify = interHelpService.findNotify(marketCreditNotifyReq.getTransactionID());
                if (notify != null && StringUtils.isNotEmpty(notify.getTransactionId())) {
                    GrabFlowSubRedEnvelope envelope = new GrabFlowSubRedEnvelope();
                    envelope.setBindMsisdn(notify.getBindMsisdn());
                    envelope.setFreId(notify.getFreId());
                    envelope.setSubFreId(notify.getSubFreId());
                    if (notify.getExpiredTime().compareTo(DateUtil.getDate()) < 0) {
                        logger.debug("此次请求领取已过期");
                        envelope.setStatus(GrabFlowSubRedEnvelope.GRABFLOWSUBREDENVELOPE_STATUS_EXPIRE);
                    } else {
                        envelope.setStatus(GrabFlowSubRedEnvelope.GRABFLOWSUBREDENVELOPE_STATUS_HASGET);
                    }
                    interHelpService.updateEnvelopeByEnvelope(envelope);
                    //
                    interHelpService.deleteNotify(marketCreditNotifyReq.getTransactionID());
                    //
                    resp.sethRet(INTERF_MSG_RESP_HRET_SUCCESS);
                } else {
                    resp.sethRet("40000"); //参数错误
                }
            }
            logger.debug("结束处理流量币赠送结果通知请求");
        } catch (Exception ex) {
            logger.error("处理流量币赠送结果通知请求出现异常", ex);
        }
        //
        try {
            String presentCoinTargetUrl = GetConfigFile.getInstance().getProperties("presentcointargeturl");
            MessageSend.sendMsg(presentCoinTargetUrl, resp);
        } catch (Exception ex) {
            logger.error("发送流量币赠送结果通知响应消息出现异常", ex);
        }
    }
}
