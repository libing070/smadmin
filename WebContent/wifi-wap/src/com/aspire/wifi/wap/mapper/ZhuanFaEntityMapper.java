package com.aspire.wifi.wap.mapper;

import java.util.List;
import java.util.Map;

import com.aspire.wifi.wap.entity.ZhuanFaEntity;

public interface ZhuanFaEntityMapper {
	void insertZhuanFa(Map<String,Object> map);	
	
	List<ZhuanFaEntity> queryZhuanFaByLimit(Map<String,Object> map);
	List<Map<String, Object>>  queryZhuanFaHuodong(Map<String,Object> map);
}
