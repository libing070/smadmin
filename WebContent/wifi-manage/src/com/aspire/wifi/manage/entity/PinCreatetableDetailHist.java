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
public class PinCreatetableDetailHist extends BaseDomain {
    private String histId;
    private String flashSaleId;
    private String mobile;
    private String nickName;
    private Date joinDate;
    private String joinDateStr;
    private String isOwnerMobile;
    private Date actionDate;
    private String actionDateStr;
    private String actionType;
    private String actionDesc;

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

    public String getHistId() {
        return histId;
    }

    public void setHistId(String histId) {
        this.histId = histId;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public String getActionDateStr() {
        return actionDateStr;
    }

    public void setActionDateStr(String actionDateStr) {
        this.actionDateStr = actionDateStr;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getActionDesc() {
        return actionDesc;
    }

    public void setActionDesc(String actionDesc) {
        this.actionDesc = actionDesc;
    }
}
