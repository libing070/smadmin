package com.xxx.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SecurityInterceptor implements HandlerInterceptor {
	  private static final String LOGIN_URL = "/pages/login.html";  
		private Logger logger = Logger.getLogger(this.getClass());
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2) throws Exception {
	 HttpSession session = req.getSession(true);  
	        // 从session 里面获取用户名的信息  
	        Object obj = session.getAttribute("SESSION_NAME_LOGIN_RESULT");  
	        
	        logger.info("SESSION_NAME_LOGIN_RESULT时间="+session.getMaxInactiveInterval());
	        logger.info("SESSION_NAME_LOGIN_RESULT="+obj);
	        // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆  
	        /*if (obj == null || "".equals(obj.toString())) {  
	          res.sendRedirect(req.getContextPath() +LOGIN_URL);  
	        	logger.info("url="+req.getContextPath() +LOGIN_URL);
	        	return false;
	        }  else{

		        return true; 
	        }*/
	 return true;
	}
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
	}

}
