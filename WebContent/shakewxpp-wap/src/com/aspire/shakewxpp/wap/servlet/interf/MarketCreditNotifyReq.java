package com.aspire.shakewxpp.wap.servlet.interf;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

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
@XmlRootElement(name = "MarketCreditNotifyReq")
@XmlAccessorType(XmlAccessType.FIELD)
public class MarketCreditNotifyReq {
    @XmlElement(name = "TransactionID")
    private String transactionID;
    @XmlElement(name = "Version")
    private String version;
    @XmlElement(name = "SendAddress")
    private Address sendAddress;
    @XmlElement(name = "DestAddress")
    private Address destAddress;
    @XmlElement(name = "FunCode")
    private String funCode;
    @XmlElementWrapper(name = "Params")
    @XmlElement(name = "Property")
    private List<Property> params = new ArrayList<Property>();

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

    public Address getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(Address sendAddress) {
        this.sendAddress = sendAddress;
    }

    public Address getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(Address destAddress) {
        this.destAddress = destAddress;
    }

    public String getFunCode() {
        return funCode;
    }

    public void setFunCode(String funCode) {
        this.funCode = funCode;
    }

    public List<Property> getParams() {
        return params;
    }

    public void setParams(List<Property> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "MarketCreditNotifyReq{" +
                "transactionID='" + transactionID + '\'' +
                ", version='" + version + '\'' +
                ", sendAddress=" + sendAddress +
                ", destAddress=" + destAddress +
                ", funCode='" + funCode + '\'' +
                ", params=" + params +
                '}';
    }
}
