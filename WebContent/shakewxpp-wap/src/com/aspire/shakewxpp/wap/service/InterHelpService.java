package com.aspire.shakewxpp.wap.service;

import com.aspire.shakewxpp.wap.entity.FlowSubRedEnvelopeNotify;
import com.aspire.shakewxpp.wap.entity.GrabFlowSubRedEnvelope;

import java.util.List;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.service
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/27
 * @Version V1.0
 * Update Logs:
 */
public interface InterHelpService {
    String getSequeneForTransactionId();
    FlowSubRedEnvelopeNotify findNotify(String transactionId) throws Exception;
    List<FlowSubRedEnvelopeNotify> queryNotify(FlowSubRedEnvelopeNotify flowSubRedEnvelopeNotify) throws Exception;
    void updateNotify(FlowSubRedEnvelopeNotify flowSubRedEnvelopeNotify) throws Exception;
    void deleteNotify(String transactionId) throws Exception;
    void updateEnvelopeByEnvelope(GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws Exception;
    GrabFlowSubRedEnvelope findEnvelopeByEnvelope(GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws Exception;

}
