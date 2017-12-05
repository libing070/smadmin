package com.aspire.wifi.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aspire.wifi.manage.base.BaseController;
import com.aspire.wifi.manage.service.AuditService;

public class AuditListController extends BaseController{
    protected static Logger logger = LoggerFactory.getLogger(AuditListController.class);
    @Resource(name = "auditService")
    private AuditService auditService;

	  /**
     * 拼桌列表查询
     * @param page
     * @param rows
     * @return
     */
	
	@RequestMapping(value = "/qiangdanAuditList", method = RequestMethod.POST)
	 public
	    @ResponseBody
	    Map<String, Object> qiangdanAuditList(
	            @RequestParam(value = "page", required = true) Integer page,
	            @RequestParam(value = "rows", required = true) Integer rows
	    ) {
		
		 logger.debug("输入参数：page={}", page);
	     logger.debug("输入参数：rows={}", rows);
	        
		Map<String, Object> returnMap = new HashMap<String, Object>();
       
		return returnMap;
	}
}
