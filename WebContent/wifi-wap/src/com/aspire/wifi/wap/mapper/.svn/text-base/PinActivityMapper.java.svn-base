package com.aspire.wifi.wap.mapper;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.aspire.wifi.wap.entity.PinActivityEntity;
import com.aspire.wifi.wap.entity.ShiYongEntity;

public interface PinActivityMapper {
	  
	  /**
	   * 获取抢单参与人数
	   * @return
	   */
	  int getQiangDanTotalCount();
	  
	  /**
	   * 返回详细的信息
	   * @return
	   */
	  List<PinActivityEntity> getQiangDanDetails(BigInteger activityId);
	  
	  /**
	   * 增加活动的点击数
	   */
	 int addVisitPvCnt(PinActivityEntity pa);
	 
	 
	 PinActivityEntity activityObject();
	 
	 /**
	  * 
	  * 获取除抢桌活动和使用活动除外的活动
	  * @return
	  */
	  List<PinActivityEntity> getAcitivityByquyouhui();
	  PinActivityEntity getAcitivityInfoByActivityId(BigInteger activityId);
	  void updatePinActivity(BigInteger activityId);
	  void procUpdatePinActivity(Map paramMap);
}
