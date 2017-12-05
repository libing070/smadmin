package com.aspire.wifi.wap.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.wap.entity.AppClickDetail;
import com.aspire.wifi.wap.entity.AppDownload;
import com.aspire.wifi.wap.mapper.AppDownloadMapper;
import com.aspire.wifi.wap.service.intf.AppDownloadService;

@Service("appDownloadService")
public class AppDownloadServiceImp implements AppDownloadService {
	private static final Logger logger =  LoggerFactory.getLogger(AppDownloadServiceImp.class);
    @Resource(name="appDownloadMapper")
	private AppDownloadMapper appDownloadMapper;
    
    /**
     * 展示app下载应用的信息
     */
	public List<AppDownload> showApp(AppDownload appDownload)throws Exception{
		List<AppDownload> list=null;
		try{
		list=appDownloadMapper.showApp(appDownload);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("应用下载列表展示异常:"+e.getMessage());
		}
		return list;
	}
	/**
	 * 展示app下载应用的信息
	 */
	public List<AppDownload> showTopApp(AppDownload appDownload)throws Exception{
		List<AppDownload> list=null;
		try{
			list=appDownloadMapper.showTopApp(appDownload);
		}catch(Exception e){
			throw new Exception("查询广告栏的app异常:"+e.getMessage());
		}
		return list;
	}
    
	/*

	 * 查询指定的图片
	 * **/
	
	public AppDownload showAppPic(String id) throws Exception {
		AppDownload a = null;
		try{
			a=appDownloadMapper.showAppPic(id);
		}catch(Exception e){
			throw new Exception("下载应用根据ID查询图片出现异常:"+e.getMessage());
		}
		return a;
	}

	
	public void addAppClickDetail(AppClickDetail appClickDetail)
			throws Exception {
		try{
			appDownloadMapper.addAppClickDetail(appClickDetail);
		}catch(Exception e){
			throw new Exception("增加下载应用点击信息出现异常:"+e.getMessage());
		}
		
	}

}
