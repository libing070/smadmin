package com.aspire.wifi.manage.mapper;


import java.util.List;

import com.aspire.wifi.manage.entity.HeadlineContentAttachEntity;

/**
 * @Title: WIFI_BJ
 * @Package com.aspire.wifi.manage.mapper
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/9
 * @Version V1.0
 * Update Logs:
 */
public interface HeadlineContentAttachMapper {
    List<HeadlineContentAttachEntity> queryAttach(String headlineId);
    void uploadHeadlineContentAttach(HeadlineContentAttachEntity headlineContentAttach);
    HeadlineContentAttachEntity findAttach(String pictureSerialnum);
    void deleteAttach(Long headlineId);
}
