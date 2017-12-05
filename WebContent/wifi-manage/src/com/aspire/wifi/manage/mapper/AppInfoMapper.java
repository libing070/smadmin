package com.aspire.wifi.manage.mapper;

import java.util.List;
import java.util.Map;

import com.aspire.wifi.manage.entity.AppInfoEntity;
import com.aspire.wifi.manage.entity.VideoInfo;

public interface AppInfoMapper {
	List<AppInfoEntity> queryAppInfo(Map paramMap);
	List<AppInfoEntity> searchAppInfoByAppId(Map paramMap);
	Integer searchAppInfoListCount(Map paramMap);
	 public void uploadAppInfo(Map paramMap);
	 public void insertAppInfo(AppInfoEntity a);
	 public void clearAppDemoPicAll(int appId);
	 public void updateAppDemoPic(Map paramMap);
}
