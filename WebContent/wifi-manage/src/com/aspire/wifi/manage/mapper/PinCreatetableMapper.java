package com.aspire.wifi.manage.mapper;

import com.aspire.wifi.manage.entity.PinCreatetable;
import com.aspire.wifi.manage.entity.PinCreatetableDetailHist;
import com.aspire.wifi.manage.entity.PinCreatetableHist;

import java.util.List;
import java.util.Map;

/**
 * @Title: WIFI_BJ
 * @Package com.aspire.wifi.manage.mapper
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/7
 * @Version V1.0
 * Update Logs:
 */
public interface PinCreatetableMapper {
    List<PinCreatetable> queryPinCreatetable(Map<String,Object> paramsMap);
    Integer queryPinCreatetableCount(Map<String,Object> paramsMap);
    PinCreatetable findPinCreatetable(String flashSaleId);
    void updatePinCreatetable(PinCreatetable pinCreatetable);
    void insertPinCreatetableHist(PinCreatetableHist pinCreatetableHist);
    void insertPinCreatetableDetailHist(PinCreatetableDetailHist pinCreatetableDetailHist);
}
