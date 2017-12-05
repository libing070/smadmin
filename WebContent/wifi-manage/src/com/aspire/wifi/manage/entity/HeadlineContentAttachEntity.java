package com.aspire.wifi.manage.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.aspire.wifi.manage.base.BaseDomain;

/**
 * @Title: WIFI_BJ
 * @Package com.aspire.wifi.manage.entity
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/9
 * @Version V1.0
 * Update Logs:
 */
public class HeadlineContentAttachEntity extends BaseDomain{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7385233298395514365L;
	private Long headlineId;
    @JsonIgnore
    private byte[] picture;
    private Long pictureSerialnum;

    public Long getHeadlineId() {
        return headlineId;
    }

    public void setHeadlineId(Long headlineId) {
        this.headlineId = headlineId;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Long getPictureSerialnum() {
        return pictureSerialnum;
    }

    public void setPictureSerialnum(Long pictureSerialnum) {
        this.pictureSerialnum = pictureSerialnum;
    }
}
