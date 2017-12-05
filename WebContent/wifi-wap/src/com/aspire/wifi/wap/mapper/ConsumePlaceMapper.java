package com.aspire.wifi.wap.mapper;

import java.util.List;
import java.util.Map;

import com.aspire.wifi.wap.entity.ConsumePlaceEntity;

public interface ConsumePlaceMapper {
	/**
	 * 展示消费地点
	 */
	List<ConsumePlaceEntity> getCounsumePlace(Map<String, Object> paramMap);
}
