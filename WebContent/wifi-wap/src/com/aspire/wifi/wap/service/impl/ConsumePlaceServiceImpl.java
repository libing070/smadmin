package com.aspire.wifi.wap.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.wap.entity.ConsumePlaceEntity;
import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.mapper.ConsumePlaceMapper;
import com.aspire.wifi.wap.service.intf.ConsumePlaceService;
@Service("consumePlaceService")
public class ConsumePlaceServiceImpl implements ConsumePlaceService {
	protected static final Logger logger =  LoggerFactory.getLogger(PinActionHistoryServiceImpl.class);
	@Resource(name = "consumePlaceMapper")
	private ConsumePlaceMapper consumePlaceMapper;
	
	/**
	 * 展示消费地点
	 */
	
	public List<ConsumePlaceEntity> getCounsumePlace(Map<String, Object> paramMap) throws WifiException {
		List<ConsumePlaceEntity> list=null;
		try{
			list=consumePlaceMapper.getCounsumePlace(paramMap);
		}catch(Exception e){
			e.printStackTrace();
			throw new WifiException("展示消费地点失败\r\n" + e.getMessage());
		}
		return list;
	}

}
