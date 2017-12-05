package com.aspire.wifi.wap.service.intf;

import com.aspire.wifi.wap.entity.PinCreateTableEntity;
import com.aspire.wifi.wap.exception.WifiException;

import java.util.List;
import java.util.Map;

public interface PinCreateTableService {

	/**
	 * 创建桌

	 * @param pc
	 * @return
	 * @throws WifiException
	 */
	int createPinZhuo(PinCreateTableEntity pc)throws WifiException;

    /**
     * 查询我的活动
     * @param pc
     * @return
     * @throws WifiException
     */
    List<Map<String, Object>> listPinCreateTable(PinCreateTableEntity pc) throws WifiException;

    /**
     * 查询我的足迹
     * @param pc
     * @return
     * @throws WifiException
     */
    List<Map<String, Object>> listPinCreateTableHis(PinCreateTableEntity pc) throws WifiException;
    
    /**
     * 用户更新创建桌的内容 
     * @return
     * @throws WifiException
     */
    int updateCreateTableByMobile(PinCreateTableEntity pce)throws WifiException;
    
    /**
     *用户可根据状态查询未通过的信息
     * @return
     */
     PinCreateTableEntity querCreateTableByMobile(PinCreateTableEntity pce)throws WifiException;
     
     /**
      * 查询所有的信息
      * @return
      */
    List<PinCreateTableEntity> queryAllList()throws WifiException;
     
     /**
      * 根据用户手机号码删除过期数据
      * @param mobile
      * @return
      */
     int deleteOverTimeByMobile(String mobile)throws WifiException;
     
     /**
      * 检查验证码是否重复
      * 
     * @param pce
     * @return
     * @throws WifiException
     */
     public boolean checkVerificationCode(Map<String, Object> paramMap) throws WifiException;
     
     /**
      * 根据手机号码以及ID更改状态编号
      * 
      * @param mobile
      * @return
      */
    public int updateOverTimeByMobile(PinCreateTableEntity pce)throws WifiException;
    
    /**
     * 根据手机号码以及ID查询单条记录
     * @param pce
     * @return
     * @throws WifiException
     */
    public PinCreateTableEntity querySingle(PinCreateTableEntity pce)throws WifiException;
}
