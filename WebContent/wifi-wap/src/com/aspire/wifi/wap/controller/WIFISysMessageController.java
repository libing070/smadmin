package com.aspire.wifi.wap.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aspire.wifi.wap.service.intf.WIFISysMessageService;
import com.aspire.wifi.wap.util.GetConfigFile;

@Controller
public class WIFISysMessageController {

	protected static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name = "wifiSysMessageService")
	WIFISysMessageService wifiSysMessageService;
	
	/**
	 * 查询系统敏感词
	 * @param request
	 * @param SensitivityName
	 * @return
	 * @author liuyao 2014-08-13
	 */
	@RequestMapping(value = "/searchSensitivityList", method = RequestMethod.POST)
	public @ResponseBody Map<String, ?> saveUserInfo(HttpServletRequest request,
			@RequestParam("SensitivityName") String SensitivityName) {
		
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			int count = wifiSysMessageService.searchSensitivityCount(SensitivityName);
			resultMap.put("count", String.valueOf(count));
			resultMap.put("CODE", "TRUE");
		} catch (Exception e) {
			logger.error("查询系统敏感词失败!",e.getMessage());
			resultMap.put("msg", "查询系统敏感词失败!");
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}
	@RequestMapping(value = "/searchVersionAPI")
	public void searchVersionAPI(HttpServletRequest request,HttpServletResponse response) {
		PrintWriter out = null;
		String version="";
		try {
			 version = GetConfigFile.getInstance().getProperties("version");
			 response.setCharacterEncoding("UTF-8");
             out = response.getWriter();
             out.write("{\"version\":\""+version+"\"}");
             out.flush();
            logger.debug("返回报文成功");

		}catch(Exception e){
			e.printStackTrace();
	      }finally{
	        if(out!=null){
	            out.flush();
	            out.close();
	         }
	      }
	}
}
