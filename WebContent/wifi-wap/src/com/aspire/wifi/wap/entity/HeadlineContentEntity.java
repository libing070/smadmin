package com.aspire.wifi.wap.entity;

import java.sql.Timestamp;
/**
 * 头条类

 * 
 * */
public class HeadlineContentEntity extends BaseDomain {
	private Long headlineId;
	private String mobile;
	private String nickname;
	private String contentTypeId;
    private String origTitle;
	private String title;
	private String content;
    private String origContent;
	private Timestamp operDate;
	private Timestamp createDate;
    private String operDateString;
	private String auditUser;
	private Timestamp auditTime;
	private String auditComment;
    private String ifSupportComment;
	private int status;
	private int topNumber;
	private int replyNum;
	private int praiseNum;
	private int clickNum;
	
	private int newsSize;
	private int guanShuiAndQiuZhuSize;
	private int indexHlTotalSize;
	
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
	public String getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(String contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getOperDate() {
		return operDate;
	}
	public void setOperDate(Timestamp operDate) {
		this.operDate = operDate;
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
	public int getTopNumber() {
		return topNumber;
	}
	public void setTopNumber(int topNumber) {
		this.topNumber = topNumber;
	}
	public int getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}
	public int getPraiseNum() {
		return praiseNum;
	}
	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}

    public String getOperDateString() {
        return operDateString;
    }

    public void setOperDateString(String operDateString) {
        this.operDateString = operDateString;
    }

    public String getOrigTitle() {
        return origTitle;
    }

    public void setOrigTitle(String origTitle) {
        this.origTitle = origTitle;
    }

    public String getOrigContent() {
        return origContent;
    }

    public void setOrigContent(String origContent) {
        this.origContent = origContent;
    }

    public String getIfSupportComment() {
        return ifSupportComment;
    }

    public void setIfSupportComment(String ifSupportComment) {
        this.ifSupportComment = ifSupportComment;
    }

    public int getClickNum() {
		return clickNum;
	}
	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}
	@Override
    public String toString() {
        return "HeadlineContentEntity{" +
                "headlineId=" + headlineId +
                ", mobile='" + mobile + '\'' +
                ", nickname='" + nickname + '\'' +
                ", contentTypeId='" + contentTypeId + '\'' +
                ", origTitle='" + origTitle + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", origContent='" + origContent + '\'' +
                ", operDate=" + operDate +
                ", operDateString='" + operDateString + '\'' +
                ", auditUser='" + auditUser + '\'' +
                ", auditTime=" + auditTime +
                ", auditComment='" + auditComment + '\'' +
                ", ifSupportComment='" + ifSupportComment + '\'' +
                ", status=" + status +
                ", topNumber=" + topNumber +
                ", replyNum=" + replyNum +
                ", praiseNum=" + praiseNum +
                ", clickNum=" + clickNum +
                ", newsSize=" + newsSize +
                ", guanShuiAndQiuZhuSize=" + guanShuiAndQiuZhuSize +
                '}';
    }
	public int getNewsSize() {
		return newsSize;
	}
	public void setNewsSize(int newsSize) {
		this.newsSize = newsSize;
	}
	public int getGuanShuiAndQiuZhuSize() {
		return guanShuiAndQiuZhuSize;
	}
	public void setGuanShuiAndQiuZhuSize(int guanShuiAndQiuZhuSize) {
		this.guanShuiAndQiuZhuSize = guanShuiAndQiuZhuSize;
	}
	public int getIndexHlTotalSize() {
		return indexHlTotalSize;
	}
	public void setIndexHlTotalSize(int indexHlTotalSize) {
		this.indexHlTotalSize = indexHlTotalSize;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
}
