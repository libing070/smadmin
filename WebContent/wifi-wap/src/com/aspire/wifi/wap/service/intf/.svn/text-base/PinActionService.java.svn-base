package com.aspire.wifi.wap.service.intf;

import java.util.List;
import java.util.Map;

import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.entity.PinActionEntity;
import com.aspire.wifi.wap.entity.PinCreateTableDetailEntity;
import com.aspire.wifi.wap.entity.PinCreateTableEntity;

public interface PinActionService {

	/**
	 * 抢单 
	 * 
	 * @param pa
	 * @return
	 * @throws WifiException
	 */
	public int doQiangDan(PinActionEntity pa)throws WifiException;
 
	/**
	 * 返回抢单成功的信息
	 * @return
	 * @throws WifiException
	 */
	public List<PinActionEntity> getQiangDanList() throws WifiException;
	/**
	 * 查询每天的桌数
	 * 
	 * @return
	 * @throws WifiException
	 */
	public int everyDayCount() throws WifiException;
    
	/**
	 * 根据用户的手机号码查询当天的参与的次数
	 * @return
	 * @throws WifiException 
	 */
	public int countByMobile(String mobile) throws WifiException;
	
	/**
	 * 根据手机号码和时间删除信息
	 * 
	 * @param pa
	 * @return
	 * @throws WifiException
	 */
	int deleteDanByMobile(String ownerMobile)throws WifiException;
	/**
	 * 更新组建创建桌的时间根据ID和手机号
	 * 
	 * @param pa
	 * @return
	 * @throws WifiException
	 */
	public int updateCreateDate(PinActionEntity pa) throws WifiException;

	/**
	 * 根据手机号码查询当天的拼桌ID
	 * @param ownerMobile
	 * @return
	 */
	public List<PinActionEntity> queryIdByMobile(String ownerMobile) throws WifiException;
	/**
	 * 根据手机号码查询值是否为空
	 * @param ownerMobile
	 * @return
	 */
	public int queryMobileIsNN(String ownerMobile)throws WifiException;
	/**
	 * 光荣榜
	 * 
	 * @return
	 * @throws WifiException
	 */
	public List<PinActionEntity> getQiangDanTopInfo()throws WifiException;
	
	/**
	 * 插入桌主的信息
	 * @param pct
	 * @return
	 * @throws WifiException
	 */
	public int addCreateTableZhuozhu(PinCreateTableDetailEntity pct) throws WifiException;
	
	/***
	 * 拼桌人员的信息
	 * @return
	 */
	List<PinCreateTableDetailEntity> pinDanList()throws WifiException;
	
	/**
	 * 查询拼桌列表
	 * 
	 * @param paramMap
	 * @return
	 * @throws WifiException
	 */
	public List<PinCreateTableEntity> listActivePinZhuo(Map<String, Object> paramMap) throws WifiException;
	
	/**
	 * 查询拼桌成员名单
	 * 
	 * @param paramMap
	 * @return
	 * @throws WifiException
	 */
	public List<PinCreateTableDetailEntity> listPinZhuoMember(Map<String, Object> paramMap) throws WifiException;
	
	/**
	 * 查询拼桌明细
	 * 
	 * @param paramMap
	 * @return
	 * @throws WifiException
	 */
	public PinCreateTableEntity getPinZhuoDetails(Map<String, Object> paramMap) throws WifiException;
	
	/**
	 * 加入拼桌
	 * 
	 * @param paramMap
	 * @return
	 * @throws WifiException
	 */
	public int joinPinZhuo(Map<String, Object> paramMap) throws WifiException;
	
	/**
	 * 删除拼桌成员
	 * 
	 * @param paramMap
	 * @return
	 * @throws WifiException
	 */
	public int deletePinZhuoMember(Map<String, Object> paramMap) throws WifiException;
	
	/**
	 * 开桌
	 * 
	 * @param paramMap
	 * @return
	 * @throws WifiException
	 */
	public int activatePingZhuo(Map<String, Object> paramMap) throws WifiException;

	/**
	 * 解散拼桌
	 * 
	 * @param paramMap
	 * @return
	 * @throws WifiException
	 */
	public int dismissPingZhuo(Map<String, Object> paramMap) throws WifiException;
	
	/**
	 * 退出拼桌
	 * 
	 * @param paramMap
	 * @return
	 * @throws WifiException
	 */
	public int exitPinZhuo(Map<String, Object> paramMap) throws WifiException;
	
	/**
	 * 上传拼桌活动照片
	 * 
	 * @param paramMap
	 * @return
	 * @throws WifiException
	 */
	public int uploadPinZhuoImg(PinCreateTableEntity pc) throws WifiException;
	
	/**
	 * 拼桌活动照片审核
	 * 
	 * @param paramMap
	 * @return
	 * @throws WifiException
	 */
	public int auditPinZhuoImg(Map<String, Object> paramMap) throws WifiException;
	
    /**
     * 检查用户是否参加过活动
     * @param pc
     * @return
     */
    public boolean checkActivityLimit(Map<String, Object> paramMap);
    
    
    /**
	 * 查询用户最近的一条记录 
	 * @param ownerMobile
	 * @return
	 */
	public PinActionEntity queryLastTimeByMobile(String ownerMobile)throws WifiException;
}
