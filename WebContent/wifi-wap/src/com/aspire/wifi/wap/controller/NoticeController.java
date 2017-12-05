package com.aspire.wifi.wap.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aspire.wifi.wap.entity.Notice;
import com.aspire.wifi.wap.service.intf.NoticeService;

@Controller
public class NoticeController {
	protected static Logger logger =  LoggerFactory.getLogger(NoticeController.class);
	
	@Resource(name = "noticeService")
	private NoticeService noticeService;
	
	
	
	/**
	 * 滚动通知信息
	 * @return
	 */
	@RequestMapping(value = "/queryNotice")
	public @ResponseBody
	Map<String, ? extends Object> queryNotice() {
		logger.debug("查询滚动通知信息");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			List<Notice> noticeList = noticeService.queryNotice();
			
			resultMap.put("list", noticeList);
			resultMap.put("CODE", "TRUE");
		} catch (Exception e) {
			logger.error("查询滚动通知信息异常", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		} 
		return resultMap;
	}
}
