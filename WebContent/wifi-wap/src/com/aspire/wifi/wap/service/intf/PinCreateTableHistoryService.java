package com.aspire.wifi.wap.service.intf;

import com.aspire.wifi.wap.entity.PinCreateTableHistoryEntity;
import com.aspire.wifi.wap.exception.WifiException;

public interface PinCreateTableHistoryService {

	/**
	 * 将创建桌的记录插入到历史表中
	 * @param pth
	 * @return
	 */
	  public int addCreateTableToHistory(PinCreateTableHistoryEntity pth) throws WifiException;
}
