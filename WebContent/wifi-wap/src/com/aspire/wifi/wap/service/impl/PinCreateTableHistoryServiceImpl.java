package com.aspire.wifi.wap.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.wap.entity.PinCreateTableHistoryEntity;
import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.mapper.PinCreateTableHistoryMapper;
import com.aspire.wifi.wap.service.intf.PinCreateTableHistoryService;
@Service("pinCreateTableHistoryService")
public class PinCreateTableHistoryServiceImpl implements
		PinCreateTableHistoryService {
	protected static final Logger logger =  LoggerFactory.getLogger(PinCreateTableHistoryServiceImpl.class);
	
	@Resource(name = "pinCreateTableHistoryMapper")
	private PinCreateTableHistoryMapper pinCreateTableHistoryMapper;
	
	/**
	 * 将创建的桌添加到历史记录中
	 */
	public int addCreateTableToHistory(PinCreateTableHistoryEntity pth)
			throws WifiException {
		int count=0;
		try{
			count=pinCreateTableHistoryMapper.addCreateTableToHistory(pth);
		}catch(Exception e){
			e.printStackTrace();
            throw new WifiException("创建桌的记录添加到历史表中失败\r\n" + e.getMessage());
		}
		return count;
	}

}
