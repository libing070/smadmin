package com.aspire.shakewxpp.wap.entity;

import java.util.Date;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.entity
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/26
 * @Version V1.0
 * Update Logs:
 */
public class FlowSubRedEnvelopeNotify extends BaseDomain {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7392339118107352402L;
	/**
     * 唯一ID
     */
    private String transactionId;
    /**
     * 绑定手机号
     */
    private String bindMsisdn;
    /**
     * 流量大红包唯一ID
     */
    private Integer freId;
    /**
     * 流量红包内小红包ID
     */
    private Integer subFreId;
    /**
     * 流量红包内小红包对应流量币个数
     */
    private String subFreFlowCurrency;
    /**
     * 流量小红包获得后过期时间
     */
    private Date expiredTime;
    /**
     * 发送状态 0 未发送 1 发送中 2 处理完成
     */
    private Integer status;
    public static final Integer FLOWSUBREDENVELOPENOTIFY_STATUS_NOTSEND = 0;
    public static final Integer FLOWSUBREDENVELOPENOTIFY_STATUS_SENDING = 1;
    public static final Integer FLOWSUBREDENVELOPENOTIFY_STATUS_SENT = 2;
    /**
     * 发送结果
     */
    private Integer result;
    /**
     * 发送结果描述
     */
    private String resultDesc;
    /**
     * 活动ID
     */
    private String activityId;
    /**
     * 活动适用省份
     */
    private String applyProvinceId;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getBindMsisdn() {
        return bindMsisdn;
    }

    public void setBindMsisdn(String bindMsisdn) {
        this.bindMsisdn = bindMsisdn;
    }

    public Integer getFreId() {
        return freId;
    }

    public void setFreId(Integer freId) {
        this.freId = freId;
    }

    public Integer getSubFreId() {
        return subFreId;
    }

    public void setSubFreId(Integer subFreId) {
        this.subFreId = subFreId;
    }

    public String getSubFreFlowCurrency() {
        return subFreFlowCurrency;
    }

    public void setSubFreFlowCurrency(String subFreFlowCurrency) {
        this.subFreFlowCurrency = subFreFlowCurrency;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getApplyProvinceId() {
        return applyProvinceId;
    }

    public void setApplyProvinceId(String applyProvinceId) {
        this.applyProvinceId = applyProvinceId;
    }

    @Override
    public String toString() {
        return "FlowSubRedEnvelopeNotify{" +
                "transactionId='" + transactionId + '\'' +
                ", bindMsisdn='" + bindMsisdn + '\'' +
                ", freId='" + freId + '\'' +
                ", subFreId='" + subFreId + '\'' +
                ", subFreFlowCurrency='" + subFreFlowCurrency + '\'' +
                ", expiredTime=" + expiredTime +
                ", status=" + status +
                ", result=" + result +
                ", resultDesc='" + resultDesc + '\'' +
                ", activityId='" + activityId + '\'' +
                ", applyProvinceId='" + applyProvinceId + '\'' +
                '}';
    }
}
