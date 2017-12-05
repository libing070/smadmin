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

public class PinCreatetableHist extends BaseDomain{
    private String histId;
    private String flashSaleId;
    private String actTypeId;
    private String actTypeName;
    private String actStatusId;
    private String actStatusName;
    private String consumePlaceId;
    private String actDesc;
    private Date createTableDate;
    private String createTableDateStr;
    private Date expiredDate;
    private String expiredDateStr;
    private String ownerMobile;
    private String freeSeat;
    private byte[] consumePic;
    private String auditUser;
    private Date auditTime;
    private String auditTimeStr;
    private String auditComment;
    private String auditStatus;
    private Date actionDate;
    private String actionDateStr;
    private String actionType;
    private String actionDesc;

    public String getHistId() {
        return histId;
    }

    public void setHistId(String histId) {
        this.histId = histId;
    }

    public String getFlashSaleId() {
        return flashSaleId;
    }

    public void setFlashSaleId(String flashSaleId) {
        this.flashSaleId = flashSaleId;
    }

    public String getActTypeId() {
        return actTypeId;
    }

    public void setActTypeId(String actTypeId) {
        this.actTypeId = actTypeId;
    }

    public String getActTypeName() {
        return actTypeName;
    }

    public void setActTypeName(String actTypeName) {
        this.actTypeName = actTypeName;
    }

    public String getActStatusId() {
        return actStatusId;
    }

    public void setActStatusId(String actStatusId) {
        this.actStatusId = actStatusId;
    }

    public String getActStatusName() {
        return actStatusName;
    }

    public void setActStatusName(String actStatusName) {
        this.actStatusName = actStatusName;
    }

    public String getConsumePlaceId() {
        return consumePlaceId;
    }

    public void setConsumePlaceId(String consumePlaceId) {
        this.consumePlaceId = consumePlaceId;
    }

    public String getActDesc() {
        return actDesc;
    }

    public void setActDesc(String actDesc) {
        this.actDesc = actDesc;
    }

    public Date getCreateTableDate() {
        return createTableDate;
    }

    public void setCreateTableDate(Date createTableDate) {
        this.createTableDate = createTableDate;
    }

    public String getCreateTableDateStr() {
        return createTableDateStr;
    }

    public void setCreateTableDateStr(String createTableDateStr) {
        this.createTableDateStr = createTableDateStr;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getExpiredDateStr() {
        return expiredDateStr;
    }

    public void setExpiredDateStr(String expiredDateStr) {
        this.expiredDateStr = expiredDateStr;
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile;
    }

    public String getFreeSeat() {
        return freeSeat;
    }

    public void setFreeSeat(String freeSeat) {
        this.freeSeat = freeSeat;
    }

    public byte[] getConsumePic() {
        return consumePic;
    }

    public void setConsumePic(byte[] consumePic) {
        this.consumePic = consumePic;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditTimeStr() {
        return auditTimeStr;
    }

    public void setAuditTimeStr(String auditTimeStr) {
        this.auditTimeStr = auditTimeStr;
    }

    public String getAuditComment() {
        return auditComment;
    }

    public void setAuditComment(String auditComment) {
        this.auditComment = auditComment;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
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

    public Date getActionDate() {

        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }
}
