package com.aspire.shakewxpp.wap.mapper;

import com.aspire.shakewxpp.wap.entity.FlowSubRedEnvelopeNotify;

import java.util.List;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.mapper
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/26
 * @Version V1.0
 * Update Logs:
 */
public interface FlowSubRedEnvelopeNotifyMapper {
    String getSequeneForTransactionId();
    void insertNotify(FlowSubRedEnvelopeNotify flowSubRedEnvelopeNotify) throws Exception;
    void updateNotify(FlowSubRedEnvelopeNotify flowSubRedEnvelopeNotify) throws Exception;
    void deleteNotify(String transactionId) throws Exception;
    List<FlowSubRedEnvelopeNotify> queryNotify(FlowSubRedEnvelopeNotify flowSubRedEnvelopeNotify) throws Exception;
    Integer queryNotifyCount(FlowSubRedEnvelopeNotify flowSubRedEnvelopeNotify) throws Exception;
    FlowSubRedEnvelopeNotify findNotify(String transactionId) throws Exception;

}
