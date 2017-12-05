package com.aspire.wifi.wap.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 业务的处理总分发器 可在web.xml中配置为filter, 并配置初始化参数 <init-param>
 * <param-name>url</param-name> <param-value>/biz</param-value> </init-param>
 * param-value为"/biz"或"/service"
 * 
 * get参数: SPID=SP提供商ID, ServiceID=业务ID
 * 
 * 处理流程: 1 用户点击某一个业务url: http://server:port/biz?SPID=xxx&ServiceID=zzz 2
 * ServiceDispather添加业务,模板,用户等信息到request/session中 2 查找匹配的模板处理url,
 * 转发到对应的action/servlet处理请求 3 响应html或wap页面展示给客户 4 用户再次点击某个url:
 * http://server:port/xxxxaction?SPID=xxx&ServiceID=zzz&param=123 5
 * ServiceDispather添加业务,模板,用户等信息到request/session中 6 转发到xxxaction处理请求
 * 
 * @author x_zhangbl
 * 
 */
public class ServiceDispatcher implements Filter {

	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory
			.getLogger(ServiceDispatcher.class);
	protected static Logger access_url_log =  LoggerFactory.getLogger("access_url_log");
	protected static Logger share_log =  LoggerFactory.getLogger("share_log");
	/**
	 * 业务总处理器的默认处理uri
	 */
	private String defaultURI = null;

	public void init(FilterConfig c) throws ServletException {
		defaultURI = c.getInitParameter("url");
		if (defaultURI != null && "".equals(defaultURI.trim())) {
			defaultURI = null;
		}
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String requestURI = req.getRequestURI();
		
		// 日志
		/**
		 * 将设置的值符号为 "|"的转换为"#"
		 */
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss SSS");
		AccessLog access = AccessLog.getAccessLog(XssHttpServletRequestWrapper
				.getOrgRequest(req));
		FileterLog access2 = new FileterLog();
		if (access != null && access.isNew()) {
			String r = requestURI != null ? requestURI.toLowerCase() : "";
			if (r.endsWith(".gif") || r.endsWith(".jpg") || r.endsWith(".css")
					|| r.endsWith(".png") || r.endsWith(".bmp")  || r.endsWith(".js") || r.endsWith(".mp3") ) {
				access = null;
			} else {
				Subject currentUser = SecurityUtils.getSubject();
				if (currentUser.isAuthenticated()) {//已登录
					access.setMsisdn((String)currentUser.getPrincipal());
					access2.setMsisdn(access.getMsisdn());
				}else{
					access.setMsisdn("");
					access2.setMsisdn(access.getMsisdn());
				}
			
				access.setGateWapIP(handleStr(Util
						.getRemoteAddr(XssHttpServletRequestWrapper
								.getOrgRequest(req))));
				access.setReqBeginDate(handleStr(df.format(new Date())));
				access.setSrc(handleStr(this
						.getReferer(XssHttpServletRequestWrapper
								.getOrgRequest(req))));
				access.setUri(handleStr(requestURI));
				access.setUrl(handleStr(XssHttpServletRequestWrapper
						.getOrgRequest(req).getQueryString()));
				access2.setUrl(access.getSrc()+":"+access.getUri());
				access.setServiceID(handleStr(XssHttpServletRequestWrapper
						.getOrgRequest(req).getParameter("ServiceID")));
				access.setSPID(handleStr(XssHttpServletRequestWrapper
						.getOrgRequest(req).getParameter("SPID")));
				if(AccessUtil.isMobileDevice(req)) {
					//手机访问
					access.setUserSign("1");
				}else{
					//PC访问
					access.setUserSign("2");
				}
				access.setSessionID(req.getSession().getId());
			}
		}

	

		chain.doFilter(req, resp);

		// 写日志
		if (access != null) {
			access.setReqEndDate(df.format(new Date()));
			// 根据URL的长度来判断是否需要记录日志
//			if (isVaildURL(req, access)) {
			int dateFlag = new Date().getHours();
			access2.setDateFlag(dateFlag);
			String AUCIP="10.0.0.10";//鉴权IP
			try {
				 AUCIP = GetConfigFile.getInstance().getProperties("AUCIP");
			} catch (Exception e) {
				logger.error("获取鉴权IP异常："+e);
			}
			if(access2.getUrl().contains("headlineList")){
				access2.setFlag(req.getParameter("contentTypeId"));
				access2.setMenuName("1");
				access_url_log.info(access2.toString(req));
			}
		
			if(req.getMethod().equalsIgnoreCase("GET")&&(!access2.getUrl().contains("find/findVideoIcon"))
					&&(!access2.getUrl().contains("find/findIcon"))&&(!access2.getUrl().contains("listHeadlineContentAttach"))
					&&(!access2.getUrl().contains("my/findAttach"))&&(!access2.getUrl().contains("/my/myZanFindAttach"))&&(!access2.getUrl().contains("/listMyActives"))
					&&(!access2.getUrl().contains(AUCIP))&&(!access2.getUrl().contains("listHeadlineReplyAttach"))){
				
			
				if(!access2.getUrl().contains("toutiao/toutiaoIndex"))	{//头条首页记录在headlist里面
					if(access2.getUrl().contains("/index")||access2.getUrl().contains("/my/myIndex")){//记录未登录后自动登录的活动PV
						String iszhuanFa = req.getSession().getAttribute("iszhuanFa")==null?"":req.getSession().getAttribute("iszhuanFa").toString();
						String onlyzhuanfaId = req.getSession().getAttribute("onlyzhuanfaId")==null?"":req.getSession().getAttribute("onlyzhuanfaId").toString();
						String onlyflag = req.getSession().getAttribute("onlyflag")==null?"":req.getSession().getAttribute("onlyflag").toString();
						if(!onlyzhuanfaId.equals("")&&onlyflag.equals("")){//
							access2.setUrl("/wifi-wap/getzhuanfa");
							req.getSession().setAttribute("onlyzhuanfaId", "");
							req.getSession().setAttribute("onlyflag", "");
						}
						if(!iszhuanFa.equals("")){
							access2.setUrl("/wifi-wap/getzhuanfa");
							req.getSession().setAttribute("iszhuanFa", "");

						}
					}
					String activityId=req.getParameter("activityId");
					//记录公益
					if(access2.getUrl().contains("judetailContr")&&activityId.equals("5")){
						access2.setMenuName("2");
						access2.setFlag("24");//公益活动
					}
					if(access2.getUrl().contains("toutiao")){
						access2.setMenuName("1");
						String flag=req.getParameter("headlineId");
						String flag2=req.getParameter("contentTypeId");
					
						access2.setFlag(flag2);
						//记录爱心
						if(access2.getUrl().contains("toutiaoDetailIndex")&&flag.equals("727")&&flag2.equals("2")){
							access2.setMenuName("2");
							access2.setFlag("25");//爱心活动
						}
					}
					if(access2.getUrl().contains("huodong")){
						access2.setMenuName("2");
						access2.setFlag("21");//抢桌活动
					}
					if(access2.getUrl().contains("shiyong")){
						access2.setMenuName("2");
						access2.setFlag("22");//试用活动
					}
					if(access2.getUrl().contains("zhuanfa")){
						access2.setMenuName("2");
						access2.setFlag("23");//转活动
					}
					if(access2.getUrl().contains("find")){
						if(access2.getUrl().contains("find/app")||access2.getUrl().contains("find/findAppDetail")){
							access2.setFlag("12");
						}else if(access2.getUrl().contains("find/findIndex")){
							access2.setFlag("13");
							
						}else{
							access2.setFlag("11");
						}
						access2.setMenuName("3");
					}
					
					access_url_log.info(access2.toString(req));
				}
			
			}
			logger.info(access.toString());
//			}
		}
	}

	
	

	/**
	 * 获取Referer
	 * 
	 * @param req
	 * @return
	 */
	private String getReferer(HttpServletRequest req) {
		// 来源URL
		String src = "";
		String originalHeader = "referer";
		if (originalHeader != null && originalHeader.trim().length() > 0) {
			originalHeader = originalHeader.trim();
			Enumeration headerNames = req.getHeaderNames();
			Object obj = null;
			while (headerNames.hasMoreElements()) {
				obj = headerNames.nextElement();
				if (obj != null
						&& originalHeader.equalsIgnoreCase(obj.toString()
								.trim())) {
					src = req.getHeader(obj.toString());
					break;
				}
			}
		}
		return src;
	}


	/**
	 * 统一处理字符串（将符号"|"替换成"#"） @ author:x_yph @ date:2012-2-29下午02:53:19
	 */
	private String handleStr(String param) {
		if (param != null && !"".equals(param)) {
			param = param.replaceAll("\\|", "#");
		}
		return param;
	}
	
	public void adaptForward(HttpServletRequest req, HttpServletResponse resp,
			String url) throws ServletException, IOException {

		req.getRequestDispatcher(url).forward(req, resp);
	}

}