package com.aspire.shakewxpp.wap.service.job;

import com.aspire.portal.web.security.client.GenerateSignature;
import com.aspire.shakewxpp.wap.entity.FlowSubRedEnvelopeNotify;
import com.aspire.shakewxpp.wap.entity.GrabFlowSubRedEnvelope;
import com.aspire.shakewxpp.wap.http.MessageSend;
import com.aspire.shakewxpp.wap.service.InterHelpService;
import com.aspire.shakewxpp.wap.servlet.handler.ErrorMessageHandler;
import com.aspire.shakewxpp.wap.servlet.handler.Handler;
import com.aspire.shakewxpp.wap.servlet.interf.YXReq;
import com.aspire.shakewxpp.wap.servlet.interf.YXResp;
import com.aspire.shakewxpp.wap.util.DateUtil;
import com.aspire.shakewxpp.wap.util.GetConfigFile;
import com.aspire.shakewxpp.wap.util.MessageHandleUtils;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.service.job
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/26
 * @Version V1.0
 * Update Logs:
 */
public class PresentCoinJob extends QuartzJobBean implements StatefulJob {
    private static final Logger logger = LoggerFactory.getLogger(PresentCoinJob.class);

    private InterHelpService interHelpService;

    public void setInterHelpService(InterHelpService interHelpService) {
        this.interHelpService = interHelpService;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.debug("流量币赠送消息发送JOB开始");
        try {
            FlowSubRedEnvelopeNotify _notify = new FlowSubRedEnvelopeNotify();
            _notify.setStatus(FlowSubRedEnvelopeNotify.FLOWSUBREDENVELOPENOTIFY_STATUS_NOTSEND);
            List<FlowSubRedEnvelopeNotify> list = interHelpService.queryNotify(_notify);
            if (list != null && !list.isEmpty()) {
                //
                String privatekey = GetConfigFile.getInstance().getProperties("wx_privatekey");
                String privateKeyPath = System.getProperty("user.dir");
                String privateKeyFile = privateKeyPath.concat(File.separator).concat("shakewxpp-wap").concat(File.separator)
                        .concat("conf").concat(File.separator).concat("key").concat(File.separator).concat(privatekey);
                File keyFile = new File(privateKeyFile);
                if (!keyFile.exists()) {
                    logger.warn("流量汇签名文件不存在，中断此次任务");
                    return;
                }
                GenerateSignature sign = new GenerateSignature();
                //
                for (FlowSubRedEnvelopeNotify notify : list) {
                    logger.debug("开始处理通知记录;BindMsisdn:"+ notify.getBindMsisdn()+",tFreId:"+notify.getFreId()+",subFreId:"+notify.getSubFreId());
                    try {
                    	double curreccy =Double.parseDouble(notify.getSubFreFlowCurrency());
                    	if(curreccy<=0){
                    		logger.info("赠送流量为Currency:0");
                    		continue;
                    	}
                        GrabFlowSubRedEnvelope _envelope = new GrabFlowSubRedEnvelope();
                        _envelope.setBindMsisdn(notify.getBindMsisdn());
                        _envelope.setFreId(notify.getFreId());
                        _envelope.setSubFreId(notify.getSubFreId());
                        GrabFlowSubRedEnvelope envelope = interHelpService.findEnvelopeByEnvelope(_envelope);
                        if (envelope == null) {
                            logger.info("此通知没有对应小红包表数据");
                            continue;
                        }
                        //
                        String activityCode = GetConfigFile.getInstance().getProperties("activityCode");
                        //
                        Map<String, String> param = new HashMap<String, String>();
                        param.put("OperType", "1"); //交易类型(0：立即生效 1：待领取)
                        param.put("Msisdn", envelope.getBindMsisdn());
                        param.put("Credit", envelope.getSubFreFlowCurrency()+"");
                        param.put("ActivityCode", activityCode);
                        param.put("DateTime", DateUtil.dateToDateFullString(envelope.getGrabTime()));
                        param.put("ExpiredTime", DateUtil.dateToDateFullString(envelope.getExpiredTime()));
                        param.put("Description", "");
                        param.put("TransType", "0"); //操作类型： 0：赠送
                        String wxppHandleUrl = GetConfigFile.getInstance().getProperties("wxpphandleurl");
                        param.put("NotifyURL", wxppHandleUrl);
                        //
                        StringBuffer signText = new StringBuffer();
                        signText.append("OperType=").append(param.get("OperType")).append("&")
                                .append("Msisdn=").append(param.get("Msisdn")).append("&")
                                .append("ActivityCode=").append(param.get("ActivityCode")).append("&")
                                .append("TransactionID=").append(notify.getTransactionId()).append("&")
                                .append("Credit=").append(param.get("Credit")).append("&")
                                .append("TransType=").append(param.get("TransType")).append("&")
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
                        yxReq.setTransactionID(notify.getTransactionId());
                        yxReq.setVersion(Handler.INTERF_MSG_VERSION);
                        yxReq.setSendAddress(MessageHandleUtils.getAddress(deviceid));
                        yxReq.setDestAddress(MessageHandleUtils.getAddress(Handler.INTERF_MSG_DESTADDR));
                        yxReq.setFunCode(Handler.INTERF_MSG_FUN_PRESENTCOIN);
                        yxReq.setParams(MessageHandleUtils.getProperties(param));
                        //
                        String presentCoinTargetUrl = GetConfigFile.getInstance().getProperties("presentcointargeturl");
                        //
                        String stringResp = MessageSend.sendMsg(presentCoinTargetUrl, yxReq);
                        if (StringUtils.isEmpty(stringResp)) {
                            logger.warn("流量币赠送接口调用失败，返回消息为空");
                            return;
                        }
                        logger.debug("流量币赠送接口返回消息：" + stringResp);
                        YXResp _yxResp = new YXResp();
                        YXResp yxResp = (YXResp) MessageHandleUtils.unMarshaller(stringResp, _yxResp);
                        if (null == yxResp) {
                            logger.warn("流量币赠送接口调用失败，返回消息格式错误");
                            return;
                        }
                        //
                        if (yxReq.getTransactionID().equals(yxResp.getTransactionID())
                                && yxResp.gethRet().equals(Handler.INTERF_MSG_RESP_HRET_SUCCESS)) {
                            logger.info("流量币赠送接口调用成功");
                            notify.setStatus(FlowSubRedEnvelopeNotify.FLOWSUBREDENVELOPENOTIFY_STATUS_SENT);
                        } else {
                            logger.warn("流量币赠送接口调用失败，发送方事务ID[" + yxReq.getTransactionID() + "]， 返回事务ID[" + yxResp.getTransactionID() + "]，返回码[" + yxResp.gethRet() + "]");
                        }
                        notify.setResult(Integer.valueOf(yxResp.gethRet()));
                        notify.setResultDesc(ErrorMessageHandler.getInstance().transformErrorCode(notify.getResult()));
                        interHelpService.updateNotify(notify);
                    } catch (Exception ex) {
                        logger.error("流量币赠送线程出现异常", ex);
                    }
                }
            }
        } catch (Exception e) {
            logger.debug("流量币赠送消息发送JOB出现异常", e);
        }
        logger.debug("流量币赠送消息发送JOB结束");
    }
}
