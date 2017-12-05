package com.aspire.wifi.wap.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.aspire.wifi.wap.util.DateUtil;
import org.codehaus.jackson.annotate.JsonIgnore;

public class PinCreateTableEntity implements Serializable {
	private static final long serialVersionUID = -5397146589373687146L;

	private BigInteger flashSaleId;
	
	private String actDesc;
	
	private Integer actTypeId;
	
	private Integer actStatusId;

    private List<Integer> excludeActStatusIds; //SQL排除状态条件集合
	
	private Integer consumePlaceId;
	
	private Date createTableDate;
	
	private String createTableDateString;
	
	private Date expireDate;
	
	private String ownerMobile;
	
	private byte[] consumePic;
	
	private Integer freeSeat;
	
	private String auditUser;
	
	private Date auditTime;
	
	private String  auditComment;
	
	private Integer auditStatus;
	
	private String nickName;
	
	
	private String mobile;
	
	private int expiredDays;
    private String expireDateStringCN;

    private String verificationCode;
    
    private Date moSmsTime;
    
    private Integer joinnedMemberCount;
    
    public void setExpireDateStringCN(String expireDateStringCN) {
        this.expireDateStringCN = expireDateStringCN;
    }

    public String getExpireDateStringCN() {
        if (expireDate == null)
            return null;
		return DateUtil.dateToDateString(expireDate, DateUtil.yyyy_MM_dd_CN);
	}

    public BigInteger getFlashSaleId() {
		return flashSaleId;
	}

	public void setFlashSaleId(BigInteger flashSaleId) {
		this.flashSaleId = flashSaleId;
	}

	public String getActDesc() {
		return actDesc;
	}

	public void setActDesc(String actDesc) {
		this.actDesc = actDesc;
	}

	public Integer getActTypeId() {
		return actTypeId;
	}

	public void setActTypeId(Integer actTypeId) {
		this.actTypeId = actTypeId;
	}

	public Integer getActStatusId() {
		return actStatusId;
	}

	public void setActStatusId(Integer actStatusId) {
		this.actStatusId = actStatusId;
	}

	public Integer getConsumePlaceId() {
		return consumePlaceId;
	}

	public void setConsumePlaceId(Integer consumePlaceId) {
		this.consumePlaceId = consumePlaceId;
	}

	public Date getCreateTableDate() {
		return createTableDate;
	}

	public void setCreateTableDate(Date createTableDate) {
		this.createTableDate = createTableDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getOwnerMobile() {
		return ownerMobile;
	}

	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}

	public byte[] getConsumePic() {
		return consumePic;
	}

	public void setConsumePic(byte[] consumePic) {
		this.consumePic = consumePic;
	}

	public Integer getFreeSeat() {
		return freeSeat;
	}

	public void setFreeSeat(Integer freeSeat) {
		this.freeSeat = freeSeat;
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

	public String getAuditComment() {
		return auditComment;
	}

	public void setAuditComment(String auditComment) {
		this.auditComment = auditComment;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getExpiredDays() {
		return expiredDays;
	}

	public void setExpiredDays(int expiredDays) {
		this.expiredDays = expiredDays;
	}

	public String getCreateTableDateString() {
		return createTableDateString;
	}

	public void setCreateTableDateString(String createTableDateString) {
		this.createTableDateString = createTableDateString;
	}
	
	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

    public List<Integer> getExcludeActStatusIds() {
        return excludeActStatusIds;
    }

    public void setExcludeActStatusIds(List<Integer> excludeActStatusIds) {
        this.excludeActStatusIds = excludeActStatusIds;
    }

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Date getMoSmsTime() {
		return moSmsTime;
	}

	public void setMoSmsTime(Date moSmsTime) {
		this.moSmsTime = moSmsTime;
	}

	public Integer getJoinnedMemberCount() {
		return joinnedMemberCount;
	}

	public void setJoinnedMemberCount(Integer joinnedMemberCount) {
		this.joinnedMemberCount = joinnedMemberCount;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
}
