package com.aspire.shakewxpp.wap.entity;

import java.util.Date;

public class Wxcustom extends BaseDomain {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1390884541610246781L;
	// 内容ID
	private Integer contentId;
	// 菜单内容响应事件类型
	private String type;
	// 当TYPE为click时，必填，用户点击菜单后，根据该值应答对应内容
	private String key;
	// 内容类型1：文本 2：图片 3：语音 4：视频 5：音乐 6：图文 7：微网页
	private String messageType;
	// 内容类型为文字时，为文本内容，其他为素材ID
	private String messageContent;
	// 菜单ID
	private Integer menuId;
	// 内容创建时间
	private Date createDate;
	// 内容更新时间
	private Date updateDate;
	// 提示登记手机号（0：需要 1：不需要）
	private String promptphone;
	
	public Integer getContentId() {
		return contentId;
	}
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getPromptphone() {
		return promptphone;
	}
	public void setPromptphone(String promptphone) {
		this.promptphone = promptphone;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

}
