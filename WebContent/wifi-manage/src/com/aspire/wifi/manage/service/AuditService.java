package com.aspire.wifi.manage.service;

import java.util.List;
import java.util.Map;

import com.aspire.wifi.manage.entity.AppInfoEntity;
import com.aspire.wifi.manage.entity.PinCreatetable;
import com.aspire.wifi.manage.entity.PinCreatetableDetail;
import com.aspire.wifi.manage.entity.VideoInfo;
import com.aspire.wifi.manage.exception.WxppException;

/**
 * @Title: WIFI_BJ
 * @Package com.aspire.wifi.manage.service
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/7
 * @Version V1.0
 * Update Logs:
 */
public interface AuditService {
    /**
     * 统一查询图片接口
     * @param plate
     * @param id
     * @return
     * @throws WxppException
     */
    byte[] findPic(String plate, String id) throws WxppException;

    /**
     * 查找拼单的人
     * @param flashSaleId
     * @return
     * @throws WxppException
     */
    List<PinCreatetableDetail> queryPindan(String flashSaleId)  throws WxppException;


    /**
     * 查询抢单列表/查询上传照片抢单审核列表
     * @param pinCreatetable
     * @return
     * @throws WxppException
     */
    List<PinCreatetable> queryQiandan(Map<String,Object> paramsMap) throws WxppException;
    Integer queryQiandanCount(Map<String,Object> paramsMap) throws WxppException;


    /**
     * 审核抢单记录/审核照片上传记录
     * @param pinCreatetable
     * @throws WxppException
     */
    void auditQiandan(PinCreatetable pinCreatetable) throws WxppException;

    /**
     * 查询抢单记录详情
     * @param flashSaleId
     * @return
     * @throws WxppException
     */
    PinCreatetable findQiandan(String flashSaleId) throws WxppException;
    
    /**
	    * 查询视频列表
	     * @param 
     * @return
     * @throws WxppException
	    */
	   List<VideoInfo> queryVideoInfo(Map paramMap)throws WxppException;
	   /**
	    * 根据id查询视频
	    * @param 
	    * @return
	    * @throws WxppException
	    */
	   List<VideoInfo> searchVideoInfoByVideoId(Map paramMap)throws WxppException;
	   /**
	    * 查询视频数量
	    * @param 
	    * @return
	    * @throws WxppException
	    */
	   Integer searchVideoInfoListCount(Map paramMap)throws WxppException;
	   /**
	    * 更新视频
	    * @param 
	    * @return
	    * @throws WxppException
	    */
	void uploadVideoInfo(Map paramMap) throws WxppException;
	/**
	 * 查询应用列表
	 * @param 
	 * @return
	 * @throws WxppException
	 */
	List<AppInfoEntity> queryAppInfo(Map paramMap)throws WxppException;
	/**
	 * 根据id查询应用
	 * @param 
	 * @return
	 * @throws WxppException
	 */
	List<AppInfoEntity> searchAppInfoByAppId(Map paramMap)throws WxppException;
	/**
	 * 查询应用数量
	 * @param 
	 * @return
	 * @throws WxppException
	 */
	Integer searchAppInfoListCount(Map paramMap)throws WxppException;
	/**
	 * 更新应用
	 * @param 
	 * @return
	 * @throws WxppException
	 */
	void uploadAppInfo(Map paramMap) throws WxppException;
	/**
	 *新增视频
	 * @param 
	 * @return
	 * @throws WxppException
	 */
	void insertVideoInfo(VideoInfo v) throws WxppException;
	
	/**
	 *新增应用
	 * @param 
	 * @return
	 * @throws WxppException
	 */
	void insertAppInfo(AppInfoEntity a) throws WxppException;
	void clearAppDemoPicAll(int appId)throws WxppException;
	void updateAppDemoPic(Map paramMap)throws WxppException;
}
