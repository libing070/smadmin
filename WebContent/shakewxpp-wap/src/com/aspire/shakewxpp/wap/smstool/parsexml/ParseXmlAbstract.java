package com.aspire.shakewxpp.wap.smstool.parsexml;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.shakewxpp.wap.util.DateUtil;
import com.aspire.shakewxpp.wap.util.StringTransTool;

/**
 * 
 *	
 *	@author mo_yq5@163.com
 * 	@date 2013-9-29  
 *
 */
public abstract class ParseXmlAbstract<T extends Object> implements ParseXml<T>{
	/**
	 * 时间监控日志文件
	 */
	private static Logger logger =  LoggerFactory.getLogger(ParseXmlAbstract.class);
	private static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");
	
	private String xml;
	
	public abstract T parse(Document doc);
	
	@Override
	public final T parse() {
		Date startDate = new Date();
		T t = null;
		SAXReader reader = new SAXReader();
		logger.debug("解析XML:" + LINE_SEPARATOR + LINE_SEPARATOR + StringTransTool.replaceDeline(xml) + LINE_SEPARATOR + LINE_SEPARATOR);
		try {
			String xml = this.xml.substring("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".length()).replaceAll("\r|\n|\t", "");
			Document doc = reader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
			t = parse(doc);
			
		} catch (DocumentException e) {
			logger.error("XML转换成" + t.getClass().getSimpleName() + "对象时异常", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("XML转换成" + t.getClass().getSimpleName() + "对象时异常", e);
		}
		Date endDate = new Date();
		long ms = DateUtil.compareDateStr(startDate, endDate);
		logger.info("XML转换成" + t.getClass().getSimpleName() + "对象花费时间：" + ms + "ms");
		return t;
	}
	
	/**
	 * 获取节点内容
	 * @param parentEl 父节点
	 * @param tagName 目标节点名称
	 * @return
	 */
	public String getElementText(Element parentEl, String tagName) {
		return ((Element)parentEl.selectObject(tagName)).getTextTrim();
	}
	
	public final void setXml(String xml) {
		this.xml = xml;
	}
	
	public ParseXml<T> getParseXml() {
		return this;
	}
}
