package com.aspire.wifi.manage.entity;

import com.aspire.wifi.manage.base.BaseDomain;

import java.util.Date;

/**
 * @Title: WIFI_BJ
 * @Package com.aspire.wifi.manage.entity
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/7
 * @Version V1.0
 * Update Logs:
 */
public class PinCreatetableDetail extends BaseDomain {
    private String flashSaleId;
    private String mobile;
    private String nickName;
    private Date joinDate;
    private String joinDateStr;
    private String isOwnerMobile;

    public String getFlashSaleId() {
        return flashSaleId;
    }

    public void setFlashSaleId(String flashSaleId) {
        this.flashSaleId = flashSaleId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getJoinDateStr() {
        return joinDateStr;
    }

    public void setJoinDateStr(String joinDateStr) {
        this.joinDateStr = joinDateStr;
    }

    public String getIsOwnerMobile() {
        return isOwnerMobile;
    }

    public void setIsOwnerMobile(String isOwnerMobile) {
        this.isOwnerMobile = isOwnerMobile;
    }
}
