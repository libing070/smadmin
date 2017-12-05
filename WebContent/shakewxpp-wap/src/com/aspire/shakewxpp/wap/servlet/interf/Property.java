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
public class Property {
    @XmlAttribute(required = true, name = "name")
    private String name;
    @XmlValue
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
