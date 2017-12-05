package com.aspire.wifi.wap.mapper;

import java.util.List;
import java.util.Map;

import com.aspire.wifi.wap.entity.AppClickDetail;
import com.aspire.wifi.wap.entity.AppDownload;

public interface AppDownloadMapper {
	//展示app下载应用的信息
	List<AppDownload> showApp(AppDownload appDownload);
	List<AppDownload> showTopApp(AppDownload appDownload);
	
	AppDownload showAppPic(String id);
	
	//首页展示4个应用
	void addAppClickDetail(AppClickDetail appClickDetail);
	
	void updateClicktimes(Map<String,Object> map);
}
