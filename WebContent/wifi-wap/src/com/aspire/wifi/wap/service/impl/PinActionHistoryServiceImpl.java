package com.aspire.wifi.wap.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.wap.entity.PinActionHistoryEntity;
import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.mapper.PinActionHistoryMapper;
import com.aspire.wifi.wap.service.intf.PinActionHistoryService;
@Service("pinActionHistoryService")
public class PinActionHistoryServiceImpl implements PinActionHistoryService {

	private static final Logger logger =  LoggerFactory.getLogger(PinActionHistoryServiceImpl.class);
	@Resource(name = "pinActionHistoryMapper")
	private PinActionHistoryMapper pinActionHistoryMapper;
	
	/**
	 * 添加到历史记录中
	 * @return
	 */
	public int addPinActionHistory(PinActionHistoryEntity pah) throws WifiException {
		int exeCount=0;
		try{
			exeCount=pinActionHistoryMapper.addPinActionHistory(pah);
		}catch(Exception e){
			e.printStackTrace();
			throw new WifiException("添加到历史记录中失败\r\n" + e.getMessage());	
		}
		return exeCount;
		
	}
	
	/**
	 * 查看历史记录的数量
	 * @return
	 */
	public int queryHistoryCountByMoblie(String ownerMobile)throws WifiException{
		int historyCount=0;
		try{
			historyCount=pinActionHistoryMapper.queryHistoryCountByMoblie(ownerMobile);
		}catch(Exception e){
			e.printStackTrace();
			throw new WifiException("添加到历史记录中失败\r\n" + e.getMessage());	
		}
		return historyCount;
	}
}
