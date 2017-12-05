package com.aspire.wifi.wap.mapper;

import com.aspire.wifi.wap.entity.PinCreateTableHistoryEntity;

public interface PinCreateTableHistoryMapper {

/**
 * 将创建桌的记录插入到历史表中
 * @param pth
 * @return
 */
  public int addCreateTableToHistory(PinCreateTableHistoryEntity pth);
}
