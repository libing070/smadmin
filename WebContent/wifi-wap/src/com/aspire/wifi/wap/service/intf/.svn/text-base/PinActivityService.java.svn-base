package com.aspire.wifi.wap.service.intf;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.aspire.wifi.wap.entity.ShiYongEntity;
import com.aspire.wifi.wap.entity.PinActivityEntity;
import com.aspire.wifi.wap.exception.WifiException;

public interface PinActivityService {
 
	/**
	 * 获取抢单参与人数
	 * @return
	 */
  int getQiangDanTotalCount()throws WifiException;
  
  /**
   * 返回详细的信息
   * @return
   */
  List<PinActivityEntity> getQiangDanDetails(BigInteger activityId) throws WifiException;
  
  
  /**
   * 增加活动的点击数
   */
 int addVisitPvCnt(PinActivityEntity pa)throws WifiException;
 
 PinActivityEntity activityObject()throws WifiException;
 
 /**
  * 
  * 获取除抢桌活动和使用活动除外的活动
  * @return
  */
 List<PinActivityEntity> getAcitivityByquyouhui()throws WifiException;
 PinActivityEntity getAcitivityInfoByActivityId(BigInteger activityId)throws WifiException;
 void updatePinActivity(BigInteger activityId)throws WifiException;
	void procUpdatePinActivity(Map paramMap);
}
