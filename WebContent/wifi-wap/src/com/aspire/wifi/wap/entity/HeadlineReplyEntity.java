package com.aspire.wifi.wap.entity;

import java.sql.Timestamp;

/**
 * 头条评论类

 * **/
public class HeadlineReplyEntity extends BaseDomain {
	private Long replyId;
	private Long headlineId;
	private String mobile;
	private String nickname;
	private String replyType;
	private String content;
    private String origContent;
	private String parentReplyId;
	private String parentMobile;
    private String parentNickName;
    private String parentNickNameReplyPrefix;
	private Timestamp replyDate;
	private String replyStringDate;//String型的评论时间
	private String auditUser;
	private Timestamp auditTime;
	private String auditStringTime;//String型的审核时间
	private String auditComment;
	private int status;
	private String headtitle;//所评论的头条标题

	private String headContent;//所评论的头条内容

	private byte[] headPic;//所评论的头条图片
	private String headNickName;//所评论或赞的头条昵称
	private Timestamp lastReplyDate;

	public Long getReplyId() {
		return replyId;
	}
	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}
	public Long getHeadlineId() {
		return headlineId;
	}
	public void setHeadlineId(Long headlineId) {
		this.headlineId = headlineId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getParentReplyId() {
		return parentReplyId;
	}
	public void setParentReplyId(String parentReplyId) {
		this.parentReplyId = parentReplyId;
	}
	public Timestamp getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Timestamp replyDate) {
		this.replyDate = replyDate;
	}
	public String getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}
	public Timestamp getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Timestamp auditTime) {
		this.auditTime = auditTime;
	}
	public String getAuditComment() {
		return auditComment;
	}
	public void setAuditComment(String auditComment) {
		this.auditComment = auditComment;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getReplyType() {
		return replyType;
	}
	public void setReplyType(String replyType) {
		this.replyType = replyType;
	}
	public String getHeadtitle() {
		return headtitle;
	}
	public void setHeadtitle(String headtitle) {
		this.headtitle = headtitle;
	}
	public String getHeadContent() {
		return headContent;
	}
	public void setHeadContent(String headContent) {
		this.headContent = headContent;
	}
	public byte[] getHeadPic() {
		return headPic;
	}
	public void setHeadPic(byte[] headPic) {
		this.headPic = headPic;
	}
	public String getParentMobile() {
		return parentMobile;
	}
	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}
	public String getReplyStringDate() {
		return replyStringDate;
	}
	public void setReplyStringDate(String replyStringDate) {
		this.replyStringDate = replyStringDate;
	}
	public String getAuditStringTime() {
		return auditStringTime;
	}
	public void setAuditStringTime(String auditStringTime) {
		this.auditStringTime = auditStringTime;
	}
	public String getHeadNickName() {
		return headNickName;
	}
	public void setHeadNickName(String headNickName) {
		this.headNickName = headNickName;
	}

    public String getOrigContent() {
        return origContent;
    }

    public void setOrigContent(String origContent) {
        this.origContent = origContent;
    }

    public String getParentNickName() {
        return parentNickName;
    }

    public void setParentNickName(String parentNickName) {
        this.parentNickName = parentNickName;
    }

    public String getParentNickNameReplyPrefix() {
        return parentNickNameReplyPrefix;
    }

    public void setParentNickNameReplyPrefix(String parentNickNameReplyPrefix) {
        this.parentNickNameReplyPrefix = parentNickNameReplyPrefix;
    }
	public Timestamp getLastReplyDate() {
		return lastReplyDate;
	}
	public void setLastReplyDate(Timestamp lastReplyDate) {
		this.lastReplyDate = lastReplyDate;
	}

    
}
