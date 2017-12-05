package com.aspire.shakewxpp.wap.servlet.interf;

import javax.xml.bind.annotation.*;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.servlet.interf
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/25
 * @Version V1.0
 * Update Logs:
 */
@XmlType
@XmlRootElement(name = "MarketCreditNotifyResp")
@XmlAccessorType(XmlAccessType.FIELD)
public class MarketCreditNotifyResp {
    @XmlElement(name = "TransactionID")
    private String transactionID;
    @XmlElement(name = "Version")
    private String version;
    @XmlElement(name = "FunCode")
    private String funCode;
    @XmlElement(name = "Operate")
    private String operate;
    @XmlElement(name = "hRet")
    private String hRet;

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFunCode() {
        return funCode;
    }

    public void setFunCode(String funCode) {
        this.funCode = funCode;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String gethRet() {
        return hRet;
    }

    public void sethRet(String hRet) {
        this.hRet = hRet;
    }

    @Override
    public String toString() {
        return "MarketCreditNotifyResp{" +
                "transactionID='" + transactionID + '\'' +
                ", version='" + version + '\'' +
                ", funCode='" + funCode + '\'' +
                ", operate='" + operate + '\'' +
                ", hRet='" + hRet + '\'' +
                '}';
    }
}
