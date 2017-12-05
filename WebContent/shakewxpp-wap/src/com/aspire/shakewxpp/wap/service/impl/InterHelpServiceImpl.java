package com.aspire.shakewxpp.wap.service.impl;

import com.aspire.shakewxpp.wap.entity.FlowSubRedEnvelopeNotify;
import com.aspire.shakewxpp.wap.entity.GrabFlowSubRedEnvelope;
import com.aspire.shakewxpp.wap.mapper.FlowSubRedEnvelopeNotifyMapper;
import com.aspire.shakewxpp.wap.mapper.GrabFlowSubRedEnvelopeMapper;
import com.aspire.shakewxpp.wap.service.InterHelpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.service.impl
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/27
 * @Version V1.0
 * Update Logs:
 */
@Service(value = "interHelpService")
public class InterHelpServiceImpl implements InterHelpService {

    @Resource(name = "flowSubRedEnvelopeNotifyMapper")
    private FlowSubRedEnvelopeNotifyMapper flowSubRedEnvelopeNotifyMapper;

    @Resource(name = "grabFlowSubRedEnvelopeMapper")
    private GrabFlowSubRedEnvelopeMapper grabFlowSubRedEnvelopeMapper;

    @Override
    public String getSequeneForTransactionId() {
        return flowSubRedEnvelopeNotifyMapper.getSequeneForTransactionId();
    }

    @Override
    public List<FlowSubRedEnvelopeNotify> queryNotify(FlowSubRedEnvelopeNotify flowSubRedEnvelopeNotify) throws Exception {
        return flowSubRedEnvelopeNotifyMapper.queryNotify(flowSubRedEnvelopeNotify);
    }

    @Override
    public void updateNotify(FlowSubRedEnvelopeNotify flowSubRedEnvelopeNotify) throws Exception {
        flowSubRedEnvelopeNotifyMapper.updateNotify(flowSubRedEnvelopeNotify);
    }

    @Override
    public GrabFlowSubRedEnvelope findEnvelopeByEnvelope(GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws Exception {
        return grabFlowSubRedEnvelopeMapper.findEnvelopeByEnvelope(grabFlowSubRedEnvelope);
    }

    @Override
    public FlowSubRedEnvelopeNotify findNotify(String transactionId) throws Exception {
        return flowSubRedEnvelopeNotifyMapper.findNotify(transactionId);
    }

    @Override
    public void deleteNotify(String transactionId) throws Exception {
        flowSubRedEnvelopeNotifyMapper.deleteNotify(transactionId);
    }

    @Override
    public void updateEnvelopeByEnvelope(GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws Exception {
        grabFlowSubRedEnvelopeMapper.updateEnvelopeByEnvelope(grabFlowSubRedEnvelope);
    }
}
