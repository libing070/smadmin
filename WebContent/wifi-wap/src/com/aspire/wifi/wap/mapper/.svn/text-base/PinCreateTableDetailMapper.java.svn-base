package com.aspire.wifi.wap.mapper;

import java.util.List;
import java.util.Map;

import com.aspire.wifi.wap.entity.PinCreateTableDetailEntity;
import com.aspire.wifi.wap.exception.WifiException;

public interface PinCreateTableDetailMapper {
	/**
	 * 查询拼桌成员列表
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<PinCreateTableDetailEntity> listPinZhuoMember(Map<String, Object> paramMap);
	
	/**
	 * 增加拼桌成员
	 * 
	 * @param pinCreateTableDetailEntity
	 */
	public int insertPinCreateTableDetails(PinCreateTableDetailEntity pinCreateTableDetailEntity);
	
	/**
	 * 删除拼桌成员
	 * 
	 * @param pinCreateTableDetailEntity
	 */
	public int deletePinZhuoMember(PinCreateTableDetailEntity pinCreateTableDetailEntity);
	
	/**
	 * 插入桌主的信息
	 * @param pct
	 * @return
	 * @throws WifiException
	 */
   public int addCreateTableZhuozhu(PinCreateTableDetailEntity pct);
	 
	/***
	 * 拼桌人员的信息
	 * @return
	 */
	public List<PinCreateTableDetailEntity> pinDanList();
	/***
	 * 修改足迹状态
	 * @return
	 */
	public void updateFootStatus(String flashSaleId);
}
