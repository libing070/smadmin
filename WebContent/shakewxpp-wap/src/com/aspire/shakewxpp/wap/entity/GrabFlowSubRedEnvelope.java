package com.aspire.shakewxpp.wap.entity;

import java.util.Date;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.entity
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/26
 * @Version V1.0
 * Update Logs:
 */
public class GrabFlowSubRedEnvelope extends BaseDomain {

	private static final long serialVersionUID = -8256563499750378637L;
	
	private String openId;
    private String bindMsisdn;
    private String password;
    private String shareOpenId;
    private String shareBindMsisdn;
    private String fromOpenid;
    private String fromBindMsisdn;
    private Integer freId;
    private Integer subFreId;
    private float subFreFlowCurrency;
    private Float subFreFlowCurrency_toFloat;
    private Date grabTime;
    private String grabTime_toString;
    private Date expiredTime;
    private int subFreExchangeDays;
    private String expiredTime_toString;
    private Integer status;
    public static final Integer GRABFLOWSUBREDENVELOPE_STATUS_NOGET = 1;
    public static final Integer GRABFLOWSUBREDENVELOPE_STATUS_HASGET = 2;
    public static final Integer GRABFLOWSUBREDENVELOPE_STATUS_EXPIRE = 3;
    private Date gainTime;
    private String activityId;
    private String applyProvinceId;
    
    private String nickName;//用户名称
    private String imgName;	//用户头像
    private String headImgUrl;
    private String weixinAppNo;
	
    public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	private int begin; //分页起始
    private int ends; //分页结束
    
    public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnds() {
		return ends;
	}

	public void setEnds(int ends) {
		this.ends = ends;
	}


    
    
    
	public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getBindMsisdn() {
        return bindMsisdn;
    }

    public void setBindMsisdn(String bindMsisdn) {
        this.bindMsisdn = bindMsisdn;
    }

    public String getShareOpenId() {
        return shareOpenId;
    }

    public void setShareOpenId(String shareOpenId) {
        this.shareOpenId = shareOpenId;
    }

    public String getShareBindMsisdn() {
        return shareBindMsisdn;
    }

    public void setShareBindMsisdn(String shareBindMsisdn) {
        this.shareBindMsisdn = shareBindMsisdn;
    }

    public String getFromOpenid() {
        return fromOpenid;
    }

    public void setFromOpenid(String fromOpenid) {
        this.fromOpenid = fromOpenid;
    }

    public String getFromBindMsisdn() {
        return fromBindMsisdn;
    }

    public void setFromBindMsisdn(String fromBindMsisdn) {
        this.fromBindMsisdn = fromBindMsisdn;
    }

    public Integer getFreId() {
        return freId;
    }

    public void setFreId(Integer freId) {
        this.freId = freId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGainTime() {
        return gainTime;
    }

    public void setGainTime(Date gainTime) {
        this.gainTime = gainTime;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGrabTime_toString() {
		return grabTime_toString;
	}

	public void setGrabTime_toString(String grabTime_toString) {
		this.grabTime_toString = grabTime_toString;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getExpiredTime_toString() {
		return expiredTime_toString;
	}

	public void setExpiredTime_toString(String expiredTime_toString) {
		this.expiredTime_toString = expiredTime_toString;
	}

    public String getApplyProvinceId() {
        return applyProvinceId;
    }

    public void setApplyProvinceId(String applyProvinceId) {
        this.applyProvinceId = applyProvinceId;
    }

	public int getSubFreExchangeDays() {
		return subFreExchangeDays;
	}

	public void setSubFreExchangeDays(int subFreExchangeDays) {
		this.subFreExchangeDays = subFreExchangeDays;
	}

	public Float getSubFreFlowCurrency_toFloat() {
		return subFreFlowCurrency_toFloat;
	}

	public void setSubFreFlowCurrency_toFloat(Float subFreFlowCurrency_toFloat) {
		this.subFreFlowCurrency_toFloat = subFreFlowCurrency_toFloat;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWeixinAppNo() {
		return weixinAppNo;
	}

	public void setWeixinAppNo(String weixinAppNo) {
		this.weixinAppNo = weixinAppNo;
	}

    
	
}
