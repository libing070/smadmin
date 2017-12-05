package com.aspire.wifi.wap.mapper;

import com.aspire.wifi.wap.entity.PinCreateTableEntity;
import com.aspire.wifi.wap.exception.WifiException;

import java.util.List;
import java.util.Map;

public interface PinCreateTableMapper {
    /**
     * 创建桌
     *
     * @return
     */
    public int createPinZhuo(PinCreateTableEntity pc);

    /**
     * 查询活跃的拼桌列表
     *
     * @param paramMap
     * @return
     */
    public List<PinCreateTableEntity> queryPinCreateTableList(Map<String, Object> paramMap);

    /**
     * 查询拼桌明细
     *
     * @param paramMap
     * @return
     */
    public PinCreateTableEntity getPinZhuoDetails(Map<String, Object> paramMap);

    /**
     *  根据验证码查询拼桌明细
     *
     * @param paramMap
     * @return
     */
    public PinCreateTableEntity getPinZhuoDetailsByVerifyCode(Map<String, Object> paramMap);
    
    /**
     * 更新剩余席位
     *
     * @param paramMap
     * @return
     */
    public int updateFreeSeat(Map<String, Object> paramMap);

    /**
     * 更新拼桌活动状态
     *
     * @param paramMap
     * @return
     */
    public int updatePinZhuoStatus(Map<String, Object> paramMap);

    /**
     * 更新拼桌活动地点
     *
     * @param paramMap
     * @return
     */
    public int updateConsumePlace(Map<String, Object> paramMap);
    
    /**
     * 开桌
     *
     * @param paramMap
     * @return
     */
    public int activatePingZhuo(Map<String, Object> paramMap);
    
    /**
     * 解除拼桌
     *
     * @param paramMap
     * @return
     */
    public int dismissPingZhuo(Map<String, Object> paramMap);

    /**
     * 上传拼桌活动照片
     *
     * @param paramMap
     * @return
     */
    public int uploadPinZhuoImg(PinCreateTableEntity pc);

    /**
     * 拼桌活动照片审核
     *
     * @param paramMap
     * @return
     */
    public int auditPinZhuoImg(Map<String, Object> paramMap);
    
    /**
     * 查询活动列表
     * @param pc
     * @return
     */
    public List<Map<String, Object>> listPinCreateTableDetail(PinCreateTableEntity pc);

    /**
     * @param pc
     * @return
     */
    public List<Map<String, Object>> listPinCreateTableDetailHis(PinCreateTableEntity pc);
    
    /**
     * 检查用户是否参加过活动
     * @param pc
     * @return
     */
    public Integer checkActivityLimit(Map<String, Object> paramMap);
    
    /**
     *用户更新创建桌的内容 
     * @return
     */
    public int updateCreateTableByMobile(PinCreateTableEntity pce);
    
    /**
     *用户可根据状态查询未通过的信息
     * @return
     */
    public PinCreateTableEntity querCreateTableByMobile(PinCreateTableEntity pce);
    
    /**
     * 查询所有的信息
     * @return
     */
    public List<PinCreateTableEntity> queryAllList();
    
    /**
     * 根据用户手机号码删除过期数据
     * @param mobile
     * @return
     */
    public int deleteOverTimeByMobile(String mobile);
    
    /**
     * 检查验证码是否重复
     * 
     * @param mobile
     * @return
     */
    public int checkVerificationCode(Map<String, Object> paramMap);
    
    /**
     * 根据手机号码以及ID更改状态编号
     * 
     * @param mobile
     * @return
     */
   public int updateOverTimeByMobile(PinCreateTableEntity pce);
   
   /**
    * 根据手机号码以及ID查询单条记录
    * @param pce
    * @return
    * @throws WifiException
    */
   public PinCreateTableEntity querySingle(PinCreateTableEntity pce);
}
