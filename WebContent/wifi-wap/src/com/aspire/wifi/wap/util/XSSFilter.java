package com.aspire.wifi.wap.util;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangxin_b
 * 
 */
public class XSSFilter implements Filter {

	private List<String> filterChainDefinitions;
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String path = ((HttpServletRequest) request).getContextPath();  
        String uri = ((HttpServletRequest) request).getRequestURI().replace(path, ""); 
		if (matchUri(uri)) {//匹配uri
			request = new XssHttpServletRequestWrapper((HttpServletRequest) request);//过滤xss漏洞
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig c) throws ServletException {
	}
	
	private boolean matchUri(String uri)  
    {  
        for(String pattern:filterChainDefinitions)  
        {  
            if(Pattern.matches(pattern,uri))  
            {  
                return true;  
            }  
                  
        }  
        return false;  
    }  

	public List<String> getFilterChainDefinitions() {
		return filterChainDefinitions;
	}

	public void setFilterChainDefinitions(List<String> filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

}
