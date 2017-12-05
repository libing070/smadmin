package com.aspire.wifi.wap.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.wap.entity.PinCreateTableDetailHistoryEntity;
import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.mapper.PinCreateTableDetailHistoryMapper;
import com.aspire.wifi.wap.mapper.PinCreateTableDetailMapper;
import com.aspire.wifi.wap.service.intf.PinCreateTableDetailHistoryService;

@Service("pinCreateTableDetailHistoryService")
public class PinCreateTableDetailHistoryServiceImpl implements
		PinCreateTableDetailHistoryService {
	private static final Logger logger =  LoggerFactory.getLogger(PinCreateTableDetailHistoryServiceImpl.class);
	
	@Resource(name = "pinCreateTableDetailHistoryMapper")
	private PinCreateTableDetailHistoryMapper pinCreateTableDetailHistoryMapper;
	
	@Resource(name = "pinCreateTableDetailMapper")
	private PinCreateTableDetailMapper pinCreateTableDetailMapper;
	
	/**
	 * 创建桌的时候将信息插入到历史表中
	 * @param ptde
	 * @return
	 */
	
	public int addCreateTableDetailToHistory(
			PinCreateTableDetailHistoryEntity ptde) throws WifiException {
     int count=0;
     try{
    	 count=pinCreateTableDetailHistoryMapper.addCreateTableDetailToHistory(ptde);
     }catch(Exception e){
    	 e.printStackTrace();
  	     logger.error("创建桌的时候将信息插入到历史表出现异常",e);
		 throw new WifiException("创建桌的时候将信息插入到历史表出现异常\r\n" + e.getMessage());
     }
		return count;
	}

	
	public void updateFootStatus(String flashSaleId) throws WifiException {
	     try{
	    	 pinCreateTableDetailMapper.updateFootStatus(flashSaleId);
	     }catch(Exception e){
	    	 e.printStackTrace();
	  	     logger.error("更新足迹状态失败",e);
			 throw new WifiException("更新足迹状态失败\r\n" + e.getMessage());
	     }
	}

}
