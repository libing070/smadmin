package com.aspire.wifi.wap.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aspire.wifi.wap.entity.PinActivityEntity;
import com.aspire.wifi.wap.service.intf.PinActivityService;

@Controller
public class PinActivityController {
protected static Logger logger =  LoggerFactory.getLogger(NoticeController.class);
	
	@Resource(name = "pinActivityService")
	private PinActivityService pinActivityService;
	/**
	 * 查询活动参与人数
	 * @return
	 */
	@RequestMapping(value = "/getQiangDanTotalCount")
	public @ResponseBody
	Map<String, ? extends Object> getQiangDanTotalCount() {
		logger.debug("查询活动参与人数");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int activityCount=0;
		try{
			activityCount=pinActivityService.getQiangDanTotalCount();
			if(activityCount>-1){
				resultMap.put("activityCount", String.valueOf(activityCount));
				resultMap.put("CODE", "TRUE");
			}
		}catch(Exception e){
			logger.error("查询活动参与人数", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}
	
	
	/**
	 * 返回活动信息列表根据ID
	 * @return
	 */
	@RequestMapping(value = "/getQiangDanDetails")
	public @ResponseBody
	Map<String, ? extends Object> getQiangDanDetails(@RequestParam("activityId")int activityId) {
		logger.debug("查询活动信息列表");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<PinActivityEntity> paList=null;
		try{
			paList=pinActivityService.getQiangDanDetails(BigInteger.valueOf(activityId));
			if(paList!=null){
				resultMap.put("list",paList);
				resultMap.put("CODE", "TRUE");
			}
		}catch(Exception e){
			logger.error("查询活动信息列表", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		}
	    return resultMap;
	}
		
	 /**
	  * 
	  * 获取除抢桌活动和使用活动除外的活动
	  * @return
	  */
	@RequestMapping(value = "/getAcitivityByquyouhui", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getAcitivityByquyouhui(HttpServletRequest request) {
		logger.debug("查询除抢桌活动和使用活动除外的活动信息列表");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<PinActivityEntity> paList=null;
		try{
			paList=pinActivityService.getAcitivityByquyouhui();
			if(paList!=null){
				resultMap.put("list",paList);
				resultMap.put("CODE", "TRUE");
			}
		}catch(Exception e){
			logger.error("查询除抢桌活动和使用活动除外的活动信息列表", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}
	
}
