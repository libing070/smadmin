package com.aspire.wifi.wap.service.intf;

import java.util.List;
import java.util.Map;

import com.aspire.wifi.wap.entity.HeadlineContentAttachEntity;
import com.aspire.wifi.wap.entity.HeadlineContentEntity;
import com.aspire.wifi.wap.entity.HeadlineReplyEntity;
import com.aspire.wifi.wap.entity.Parameter;
import com.aspire.wifi.wap.exception.WifiException;



public interface HeadlineContentService {
	int queryMyHeadCount(String mobile) throws Exception;
	Parameter queryReplyAndPraiseCount(String mobile)throws Exception;
	List<HeadlineContentEntity> queryMyHeadInfo(String mobile)throws Exception;
	List<HeadlineContentEntity> queryTopTitle()throws Exception;
	List<HeadlineContentEntity> queryTopContentById(int headlineId)throws Exception;
	List<HeadlineContentEntity> searchHeadlineList(HeadlineContentEntity headlineContentEntity)throws WifiException;

    Map<String, Object> queryHeadlineContent(HeadlineContentEntity headlineContentEntity) throws Exception;
    Map<String, Object> queryIndexHeadlineContent(HeadlineContentEntity headlineContentEntity) throws Exception;
    HeadlineContentEntity findHeadlineContent(Long headlineId) throws Exception;
    List<HeadlineContentAttachEntity> queryHeadlineAttach(Long headlineId) throws Exception;
    byte[] findPic(String pictureSerialnum) throws Exception;
    String addHeadlineContent(HeadlineContentEntity headlineContentEntity) throws Exception;
    void modifyHeadlineContent(HeadlineContentEntity headlineContentEntity) throws Exception;
    void insertHeadlineAttach(HeadlineContentAttachEntity headlineContentAttachEntity) throws Exception;
    Integer voteHeadlineContent(HeadlineReplyEntity headlineReplyEntity) throws Exception;
    void deleteHeadline(Long headlineId) throws Exception;

}
