package com.aspire.wifi.wap.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aspire.wifi.wap.entity.PinActivityEntity;
import com.aspire.wifi.wap.entity.ShiYongEntity;
import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.mapper.ShiyongMapper;
import com.aspire.wifi.wap.service.intf.ShiyongService;

@Service("shiyongService")
@Scope("prototype")
public class ShiyongServiceImpl implements ShiyongService {
	protected static Logger logger =  LoggerFactory.getLogger(ShiyongServiceImpl.class);

	@Resource(name = "shiyongMapper")
	private ShiyongMapper shiyongMapper;
	
	public PinActivityEntity queryShiyongInfo() throws WifiException{
		return shiyongMapper.queryShiyongInfo();
	}
	
	public Integer queryShiyongApplyCount(BigInteger activityId)  throws WifiException{
		return shiyongMapper.queryShiyongApplyCount(activityId);
	}
	
	public void insertShiYongction(ShiYongEntity shiyong) {
		 shiyongMapper.insertShiYongAction(shiyong);
	}

	
	public void updatePinActivity(BigInteger activityId) {
		// TODO Auto-generated method stub
		shiyongMapper.updatePinActivity(activityId);
	}
	public void updateShiYong(ShiYongEntity shiyong) {
		// TODO Auto-generated method stub
		shiyongMapper.updateShiYong(shiyong);
	}

	
	public void queryShiYongByMobileAndActivityId(Map paramMap) throws WifiException {
		// TODO Auto-generated method stub
		 shiyongMapper.queryShiYongByMobileAndActivityId(paramMap);
	}

	public Map<String, Object> queryCustomCodeByMobileAndActivityId(Map paramMap)
			throws WifiException {
		// TODO Auto-generated method stub
		return shiyongMapper.queryCustomCodeByMobileAndActivityId(paramMap);
	}

	public List<ShiYongEntity> queryshiyongDefine20(BigInteger activityId) {
		// TODO Auto-generated method stub
		return shiyongMapper.queryshiyongDefine20(activityId);
	}

	@Override
	public ShiYongEntity queryshiyongexists(Map paramMap) {
		return shiyongMapper.queryshiyongexists(paramMap);
	}
	@Override
	public List<ShiYongEntity> queryshiyongBylimit(Map paramMap) {
		return shiyongMapper.queryshiyongBylimit(paramMap);
	}
}
