package com.aspire.wifi.common.sms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 短信下行信息对象.
 */
public class SmsSendMsg implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** SMS标识 */
	private Long id;

	/** 手机号码 */
	private String mobile;

	/** 下发内容 */
	private String content;

	/** 优先级（1 ：最高级、2： 次之） */
	private Integer priority;

	/** 记录创建时间 */
	private Date createTime;

	/** 发送状态（0：待发送、1：发送中、2：已发送、3：发送出错） */
	private Integer sendStatus;

	/** 发送结果描述 */
	private String sendResult;

	/** 发送时间 */
	private Date sendTime;

	/** 重试次数 */
	private Integer retryTimes = 0;

	/** 企业下行消息标识ID */
	private String gwSmsId;

	/** 是否异步发送（默认：异步发送） */
	private boolean isAsynSend = true;

	/**
	 * 取得SMS标识
	 * 
	 * @return SMS标识
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置SMS标识
	 * 
	 * @param id
	 *            SMS标识
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 取得手机号码
	 * 
	 * @return 手机号码
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置手机号码
	 * 
	 * @param mobile
	 *            手机号码
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 取得下发内容
	 * 
	 * @return 下发内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置下发内容
	 * 
	 * @param content
	 *            下发内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 取得优先级（1 ：最高级、2： 次之）
	 * 
	 * @return 优先级（1 ：最高级、2： 次之）
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * 设置优先级（1 ：最高级、2： 次之）
	 * 
	 * @param priority
	 *            优先级（1 ：最高级、2： 次之）
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * 取得记录创建时间
	 * 
	 * @return 记录创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置记录创建时间
	 * 
	 * @param createTime
	 *            记录创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 取得发送状态（0：待发送、1：发送中、2：已发送、3：发送出错）
	 * 
	 * @return 发送状态（0：待发送、1：发送中、2：已发送、3：发送出错）
	 */
	public Integer getSendStatus() {
		return sendStatus;
	}

	/**
	 * 设置发送状态（0：待发送、1：发送中、2：已发送、3：发送出错）
	 * 
	 * @param sendStatus
	 *            发送状态（0：待发送、1：发送中、2：已发送、3：发送出错）
	 */
	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}

	/**
	 * 取得发送结果描述
	 * 
	 * @return 发送结果描述
	 */
	public String getSendResult() {
		return sendResult;
	}

	/**
	 * 设置发送结果描述
	 * 
	 * @param sendResult
	 *            发送结果描述
	 */
	public void setSendResult(String sendResult) {
		this.sendResult = sendResult;
	}

	/**
	 * 取得发送时间
	 * 
	 * @return 发送时间
	 */
	public Date getSendTime() {
		return sendTime;
	}

	/**
	 * 设置发送时间
	 * 
	 * @param sendTime
	 *            发送时间
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * 取得重试次数
	 * 
	 * @return 重试次数
	 */
	public Integer getRetryTimes() {
		return retryTimes;
	}

	/**
	 * 设置重试次数
	 * 
	 * @param retryTimes
	 *            重试次数
	 */
	public void setRetryTimes(Integer retryTimes) {
		this.retryTimes = retryTimes;
	}

	/**
	 * 取得企业下行消息标识ID
	 * 
	 * @return 企业下行消息标识ID
	 */
	public String getGwSmsId() {
		return gwSmsId;
	}

	/**
	 * 设置企业下行消息标识ID
	 * 
	 * @param gwSmsId
	 *            企业下行消息标识ID
	 */
	public void setGwSmsId(String gwSmsId) {
		this.gwSmsId = gwSmsId;
	}

	/**
	 * 取得是否异步发送（默认：异步发送）
	 * 
	 * @return 是否异步发送（默认：异步发送）
	 */
	public boolean isAsynSend() {
		return isAsynSend;
	}

	/**
	 * 设置是否异步发送（默认：异步发送）
	 * 
	 * @param isAsynSend
	 *            是否异步发送（默认：异步发送）
	 */
	public void setAsynSend(boolean isAsynSend) {
		this.isAsynSend = isAsynSend;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id:" + id);
		sb.append(", mobile:" + mobile);
		sb.append(", content:" + content);
		sb.append(", priority:" + priority);
		sb.append(", createTime:" + createTime);
		sb.append(", sendStatus:" + sendStatus);
		sb.append(", sendResult:" + sendResult);
		sb.append(", sendTime:" + sendTime);
		sb.append(", retryTimes:" + retryTimes);
		sb.append(", gwSmsId:" + gwSmsId);
		sb.append(", isAsynSend:" + isAsynSend);
		return sb.toString();
	}

}
