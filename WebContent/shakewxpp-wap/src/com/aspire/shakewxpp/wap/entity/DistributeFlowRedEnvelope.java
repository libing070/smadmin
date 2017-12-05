package com.aspire.shakewxpp.wap.entity;

import java.util.Date;

/**
 * 抢流量大红包表
 * 
 * @author
 * 
 */
public class DistributeFlowRedEnvelope extends BaseDomain {

	private static final long serialVersionUID = -1463290993821548435L;
	private String openid;					// 微信API接口用户唯一标识
	private String bindMsisdn;				// 绑定手机号
	private Integer freId;					// 流量红包唯一ID
	private Integer freFlowCurrency;		// 流量红包对应流量币个数
	private Integer subFreId;				// 流量红包内小红包ID
	private float subFreFlowCurrency;		// 流量红包内小红包对应流量币个数
	private Date grabTime;					// 流量红包获得时间
	private Date expiredTime;				// 流量红包被抢后过期时间
	private Integer activityId;				// 活动ID
	private Integer freFromSource;			// 流量红包来源 1 移动免费红包;2 关注流量汇获得红包;3 购买流量包获得红包;
	private Date shareTime;					// 大红包分享到朋友圈时间	private Integer freExpireDays;          //大红包 中小红包可抢的有效期
	private Integer subFreExchangeDays;     //小红包兑换有效期
	private String nickName; 				//用户名称
	private String imgName;					//用户头像
	private String applyProvinceId;
	private int count; //大红包中小红包的数量
	private int bigCount; //大红包的数量

	public int getBigCount() {
		return bigCount;
	}

	public void setBigCount(int bigCount) {
		this.bigCount = bigCount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getBindMsisdn() {
		return bindMsisdn;
	}

	public void setBindMsisdn(String bindMsisdn) {
		this.bindMsisdn = bindMsisdn;
	}

	public Integer getFreId() {
		return freId;
	}

	public void setFreId(Integer freId) {
		this.freId = freId;
	}

	public Integer getFreFlowCurrency() {
		return freFlowCurrency;
	}

	public void setFreFlowCurrency(Integer freFlowCurrency) {
		this.freFlowCurrency = freFlowCurrency;
	}

	public Integer getSubFreId() {
		return subFreId;
	}

	public void setSubFreId(Integer subFreId) {
		this.subFreId = subFreId;
	}

	public float getSubFreFlowCurrency() {
		return subFreFlowCurrency;
	}

	public void setSubFreFlowCurrency(float subFreFlowCurrency) {
		this.subFreFlowCurrency = subFreFlowCurrency;
	}

	public Date getGrabTime() {
		return grabTime;
	}

	public void setGrabTime(Date grabTime) {
		this.grabTime = grabTime;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getFreFromSource() {
		return freFromSource;
	}

	public void setFreFromSource(Integer freFromSource) {
		this.freFromSource = freFromSource;
	}

	public Date getShareTime() {
		return shareTime;
	}

	public void setShareTime(Date shareTime) {
		this.shareTime = shareTime;
	}

	public Integer getFreExpireDays() {
		return freExpireDays;
	}

	public void setFreExpireDays(Integer freExpireDays) {
		this.freExpireDays = freExpireDays;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getApplyProvinceId() {
		return applyProvinceId;
	}

	public void setApplyProvinceId(String applyProvinceId) {
		this.applyProvinceId = applyProvinceId;
	}

	public Integer getSubFreExchangeDays() {
		return subFreExchangeDays;
	}

	public void setSubFreExchangeDays(Integer subFreExchangeDays) {
		this.subFreExchangeDays = subFreExchangeDays;
	}
	
	
}
