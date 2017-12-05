package com.aspire.wifi.wap.util;

import javax.servlet.ServletRequest;



public class AccessLog {
	private static final String DELIMITER = "|";
	private static int UA_LENGTH = 30;

	public static final String KEY_ACCESSLOG = "AccessLogAttributeKey";
	
	private AccessLog() {
		
	}
	
	private String msisdn = "";

	private String brandId = "";
	
	private String cityId = "";
	
	private String reqBeginDate = "";

	
	private String reqEndDate = "";

	
	private String mobileIP = "";

	
	private String gateWapIP = "";
	
	
	private String src = "";

	
	private String uri = "";

	
	private String url = "";

	
	private String ua = "";
	
	
	private String ServiceID = "";
	
	/**
	 * SPID
	 */
	private String SPID = "";
	
	private String shiftCityCode="";
	
    private String cityAreaCode = "";
	

    private String userSign = "";
    
    private String sessionID = "";

	
    private String cateId = "";
	
    

	/**
     * sessionId
     */
    private String sid = "";

	public String getUserSign() {
		return userSign;
	}

	public void setUserSign(String userSign) {
		this.userSign = userSign;
	}

	
	public String getMobileIP()
	{
		return mobileIP;
	}

	public void setMobileIP(String ip)
	{
		mobileIP = ip;
	}

	
	public String getReqBeginDate()
	{
		return reqBeginDate;
	}

	public void setReqBeginDate(String reqBeginDate)
	{
		this.reqBeginDate = reqBeginDate;
	}


	public String getReqEndDate()
	{
		return reqEndDate;
	}

	public void setReqEndDate(String reqEndDate)
	{
		this.reqEndDate = reqEndDate;
	}

	public String getSrc()
	{
		return src;
	}

	public void setSrc(String src)
	{
		if(src!=null) {
			src = src.toLowerCase();
			if(src.startsWith("http://")) {
				src = src.substring(7);
				if(src.length()>0 && src.indexOf('/')>0) {
					src = src.substring(0, src.indexOf('/'));
					this.src = src;
				}
			}
		}
	}

	
	public String getUri()
	{
		return uri;
	}

	public void setUri(String uri)
	{
		this.uri = uri;
	}

	
	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}
	
	

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();

		sb.append(nullToBlank(reqBeginDate)).append(DELIMITER);
		sb.append(nullToBlank(reqEndDate)).append(DELIMITER);
		sb.append(nullToBlank(msisdn)).append(DELIMITER);
//		sb.append(nullToBlank(ServiceID)).append(DELIMITER);
//		sb.append(nullToBlank(SPID)).append(DELIMITER);
		//sb.append(nullToBlank(brandId)).append(DELIMITER);
		//sb.append(nullToBlank(cityId)).append(DELIMITER);
		if(this.ua != null && this.ua.length() > UA_LENGTH){
			sb.append(this.ua.substring(0, UA_LENGTH)).append(DELIMITER);
		}else
		{
			sb.append(nullToBlank(this.ua)).append(DELIMITER);
		}
		sb.append(nullToBlank(mobileIP)).append(DELIMITER);
		sb.append(nullToBlank(gateWapIP)).append(DELIMITER);
		sb.append(nullToBlank(src)).append(DELIMITER);
		sb.append(nullToBlank(uri)).append(DELIMITER);
		sb.append(nullToBlank(url)).append(DELIMITER);
		sb.append(nullToBlank(shiftCityCode)).append(DELIMITER);
		
		sb.append(nullToBlank(userSign)).append(DELIMITER);
		sb.append(nullToBlank(sessionID));
		//sb.append(nullToBlank(cateId)).append(DELIMITER);
		//sb.append(nullToBlank(userSign)).append(DELIMITER);
//		sb.append(nullToBlank(sid)).append(DELIMITER);
		return sb.toString();
	}
	
	private String nullToBlank(String s) {
		if(s==null) {
			return "";
		}
		return s;
	}

	
	public String getUa()
	{
		return ua;
	}

	public void setUa(String ua)
	{
		this.ua = ua;
	}


	public String getGateWapIP() {
		return gateWapIP;
	}

	public void setGateWapIP(String gateWapIP) {
		this.gateWapIP = gateWapIP;
	}
	
	
	
	public String getServiceID() {
		return ServiceID;
	}

	public void setServiceID(String serviceID) {
		ServiceID = serviceID;
	}

	public String getSPID() {
		return SPID;
	}

	public void setSPID(String sPID) {
		SPID = sPID;
	}

	public String getShiftCityCode() {
		return shiftCityCode;
	}

	public void setShiftCityCode(String shiftCityCode) {
		this.shiftCityCode = shiftCityCode;
	}

	
	public static AccessLog getAccessLog(ServletRequest request) {
		if(request!=null) {
			AccessLog log = (AccessLog)request.getAttribute(KEY_ACCESSLOG);
			if(log==null) {
				log = new AccessLog();
				request.setAttribute(KEY_ACCESSLOG, log);
				log.isNew = true;
			}else {
				log.isNew = false;
			}
			return log;
		}
		return null;
	}
	private boolean isNew = false;
	
	public boolean isNew() {
		return isNew;
	}

	public String getCateId() {
		return cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getCityAreaCode() {
		return cityAreaCode;
	}

	public void setCityAreaCode(String cityAreaCode) {
		this.cityAreaCode = cityAreaCode;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	
	
}