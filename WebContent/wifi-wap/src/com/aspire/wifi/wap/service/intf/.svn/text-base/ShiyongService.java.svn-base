package com.aspire.wifi.wap.service.intf;


import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.aspire.wifi.wap.entity.PinActivityEntity;
import com.aspire.wifi.wap.entity.ShiYongEntity;
import com.aspire.wifi.wap.exception.WifiException;

public interface ShiyongService {
	PinActivityEntity queryShiyongInfo() throws WifiException;
	
	Integer queryShiyongApplyCount(BigInteger activityId) throws WifiException;
	void insertShiYongction(ShiYongEntity shiyong);
	void updatePinActivity(BigInteger activityId);
	void updateShiYong(ShiYongEntity shiYongEntity);
	void queryShiYongByMobileAndActivityId(Map paramMap) throws WifiException;
	Map<String, Object> queryCustomCodeByMobileAndActivityId(Map paramMap)throws WifiException;
List<ShiYongEntity> queryshiyongDefine20(BigInteger activityId);
List<ShiYongEntity> queryshiyongBylimit(Map paramMap);
ShiYongEntity queryshiyongexists(Map paramMap);
}
