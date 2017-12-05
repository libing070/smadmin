package com.aspire.shakewxpp.wap.mapper;

import java.util.List;
import java.util.Map;

import com.aspire.shakewxpp.wap.entity.ConfigPojo;
import com.aspire.shakewxpp.wap.entity.User;

public interface ActivityConfigMapper extends BaseMapper<User>{
	List<ConfigPojo> getConfigData(Map<String,String> paraMap);
}
