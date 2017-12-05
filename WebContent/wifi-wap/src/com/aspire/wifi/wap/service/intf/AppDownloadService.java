package com.aspire.wifi.wap.service.intf;

import java.util.List;

import com.aspire.wifi.wap.entity.AppClickDetail;
import com.aspire.wifi.wap.entity.AppDownload;

public interface AppDownloadService {
	
	//展示app下载应用的信息	List<AppDownload> showApp(AppDownload appDownload )throws Exception;
	List<AppDownload> showTopApp(AppDownload appDownload )throws Exception;
	//显示指定id的图片
	AppDownload showAppPic(String id) throws Exception;
	
	void addAppClickDetail(AppClickDetail appClickDetail)throws Exception;
}
