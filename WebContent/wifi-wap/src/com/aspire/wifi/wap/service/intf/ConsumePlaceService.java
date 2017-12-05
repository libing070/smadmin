package com.aspire.wifi.wap.service.intf;

import java.util.List;
import java.util.Map;

import com.aspire.wifi.wap.entity.ConsumePlaceEntity;
import com.aspire.wifi.wap.exception.WifiException;

public interface ConsumePlaceService {
 
	/**
	 * 展示消费地点
	 */
	List<ConsumePlaceEntity> getCounsumePlace(Map<String, Object> paramMap) throws WifiException;
}
