package com.aspire.wifi.wap.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aspire.wifi.wap.entity.PinActionEntity;
import com.aspire.wifi.wap.entity.PinActionHistoryEntity;
import com.aspire.wifi.wap.service.intf.PinActionHistoryService;

@Controller
public class PinActionHistoryController {
	protected static Logger logger =  LoggerFactory.getLogger(PinActionHistoryController.class);
	
	@Resource(name = "pinActionHistoryService")
	private PinActionHistoryService pinActionHistoryService;
	
	/**
	 * 添加到历史记录中
	 * @return
	 */
	@RequestMapping(value = "/addPinActionHistory")
	public @ResponseBody
	Map<String, ? extends Object> addPinActionHistory(PinActionEntity pa){
		logger.debug("抢单信息添加到历史记录中");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		PinActionHistoryEntity pah=null;
		int exeCount=0;
		try{
			pah=new PinActionHistoryEntity();
			pah.setFlashSaleId(pa.getFlashSaleId());
			pah.setOwnerMobile("");
			pah.setActionDate(null); 
			pah.setActionType(1);
			pah.setActionDesc("");
			pah.setFlashSaleDate(null);
			pah.setCreateTableDate(null);
			exeCount=pinActionHistoryService.addPinActionHistory(pah);
			if(exeCount>0){
				resultMap.put("CODE", "TRUE");
			}
		}catch(Exception e){
			logger.error("添加到历史记录异常", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}
}
