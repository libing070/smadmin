package com.aspire.shakewxpp.wap.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MiniwebFilter implements Filter {
    
	protected static Logger logger =  LoggerFactory.getLogger(MiniwebFilter.class);
    public void destroy() {
        // Auto-generated method stub
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
    	logger.debug("进入miniwebFilter过滤器");
        HttpServletRequest httpReq = (HttpServletRequest) req;
        
        String weixinAppNo = httpReq.getParameter("weixinAppNo");
        if(StringUtils.isNotEmpty(weixinAppNo)){
        	httpReq.getSession().setAttribute("weixinAppNo", weixinAppNo);
        }
    
        chain.doFilter(req, resp);
    }

  

    public void init(FilterConfig arg0) throws ServletException {
        
    }

   

}
