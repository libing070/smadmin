package com.aspire.shakewxpp.wap.servlet.interf;

import javax.xml.bind.annotation.*;

/**
 * @Title: TechTest
 * @Package com.techtest.monitor.servlet.mapper
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/23
 * @Version V1.0
 * Update Logs:
 */
@XmlType
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    @XmlElement(name = "DeviceType")
    private String deviceType;
    @XmlElement(name = "DeviceID")
    private String deviceId;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "deviceType='" + deviceType + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
