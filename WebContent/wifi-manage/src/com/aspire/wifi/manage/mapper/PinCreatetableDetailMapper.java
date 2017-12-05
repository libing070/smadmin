package com.aspire.wifi.manage.mapper;

import com.aspire.wifi.manage.entity.PinCreatetableDetail;

import java.util.List;

/**
 * @Title: WIFI_BJ
 * @Package com.aspire.wifi.manage.mapper
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/7
 * @Version V1.0
 * Update Logs:
 */
public interface PinCreatetableDetailMapper {
    List<PinCreatetableDetail> queryCreatetableDetail(String flashSaleId);
}
