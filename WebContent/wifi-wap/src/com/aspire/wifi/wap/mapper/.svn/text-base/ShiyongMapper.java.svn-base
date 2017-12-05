package com.aspire.wifi.wap.mapper;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.aspire.wifi.wap.entity.PinActivityEntity;
import com.aspire.wifi.wap.entity.ShiYongEntity;

public interface ShiyongMapper {

	PinActivityEntity queryShiyongInfo() ;
	
	Integer queryShiyongApplyCount(BigInteger activityId);
	void insertShiYongAction(ShiYongEntity shiyong);
	void updatePinActivity(BigInteger activityId);
	void updateShiYong(ShiYongEntity shiYongEntity);
	void queryShiYongByMobileAndActivityId(Map paramMap);
	List<Map<String, Object>>  queryShiyongByMobile(Map paramMap);
	List<Map<String, Object>>  queryHuodong(Map paramMap);
	ShiYongEntity queryHuodongById(String id);
	Map<String, Object> queryCustomCodeByMobileAndActivityId(Map paramMap);
	List<ShiYongEntity> queryshiyongDefine20(BigInteger activityId);
	List<ShiYongEntity> queryshiyongBylimit(Map paramMap);
	ShiYongEntity queryshiyongexists(Map paramMap);
}
