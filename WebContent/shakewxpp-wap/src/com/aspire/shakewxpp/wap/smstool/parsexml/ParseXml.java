package com.aspire.shakewxpp.wap.smstool.parsexml;

/**
 * 
 *	
 *	@author mo_yq5@163.com
 * 	@date 2013-9-29  
 *
 */
public interface ParseXml<T extends Object> {
	public T parse();
	public void setXml(String xml);
}
