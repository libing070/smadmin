package com.aspire.wifi.wap.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;



public class FileterLog {

	
	private String msisdn = "";

	private String url = "";

	private String ip="";
	
	private String flag="";
	
	private String menuName="";
	
	private int dateFlag;
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}




	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getDateFlag() {
		return dateFlag;
	}

	public void setDateFlag(int dateFlag) {
		this.dateFlag = dateFlag;
	}



	public String toString(HttpServletRequest request) {
		ip=getIpAddr2(request);
		return DateUtil.dateToDateString(new Date())+"|" + msisdn + "|" + ip + "|"+url+"|"+menuName+"|"+flag+"|"+dateFlag+"|"+request.getSession().getId();
	}

	
	private String getIpAddr2(HttpServletRequest request) {   
		     String ipAddress = null;    
		     ipAddress = request.getHeader("x-forwarded-for");    
		    if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {    
		     ipAddress = request.getHeader("Proxy-Client-IP");    
		     }   
		    if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {    
		        ipAddress = request.getHeader("WL-Proxy-Client-IP");    
		     }   
		     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {    
		      ipAddress = request.getRemoteAddr();    
		      if("127.0.0.1".equals(ipAddress)||"localhost".equals(ipAddress)){    
		       //根据网卡取本机配置的IP    
		       InetAddress inet=null;    
		    try {    
		     inet = InetAddress.getLocalHost();   
		    } catch (UnknownHostException e) {    
		    e.printStackTrace();   
		    }   
		    ipAddress= inet.getHostAddress();   
		     }   
		           
		     }   
		 
		    //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割    
		    if(ipAddress!=null && ipAddress.length()>15&&ipAddress.indexOf(",")>0){    
		             ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));    
		    }   
		   return ipAddress;     
		 }   

}