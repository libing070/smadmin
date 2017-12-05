package com.aspire.wifi.wap.mapper;

import com.aspire.wifi.wap.entity.PinActionHistoryEntity;

public interface PinActionHistoryMapper {
    
	/**
	 * 添加到历史记录中
	 * @return
	 */
	public int addPinActionHistory(PinActionHistoryEntity pah);
	
	/**
	 * 查看历史记录的数量
	 * @return
	 */
	public int queryHistoryCountByMoblie(String ownerMobile);
}
