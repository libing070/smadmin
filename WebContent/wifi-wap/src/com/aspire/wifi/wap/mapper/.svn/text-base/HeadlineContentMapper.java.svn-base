package com.aspire.wifi.wap.mapper;

import java.util.List;

import com.aspire.wifi.wap.entity.HeadlineContentEntity;
import com.aspire.wifi.wap.entity.HeadlineReplyEntity;
import com.aspire.wifi.wap.entity.Parameter;



public interface HeadlineContentMapper {
	int queryMyHeadCount(String mobile);
	Parameter queryReplyAndPraiseCount(String mobile);
	List<HeadlineContentEntity> queryMyHeadInfo(String mobile);
	List<HeadlineContentEntity> queryTopTitle();
	List<HeadlineContentEntity> queryTopContentById(int headlineId);
	List<HeadlineContentEntity> searchHeadlineList(HeadlineContentEntity headlineContentEntity);

    List<HeadlineContentEntity> queryHeadlineContent(HeadlineContentEntity headlineContentEntity) throws Exception;
    Integer queryHeadlineContentCount(HeadlineContentEntity headlineContentEntity) throws Exception;
    List<HeadlineContentEntity> queryIndexHeadlineContent(HeadlineContentEntity headlineContentEntity) throws Exception;
    HeadlineContentEntity findHeadlineContent(Long headlineId) throws Exception;
    void insertHeadlineContent(HeadlineContentEntity headlineContentEntity) throws Exception;
    void updateHeadlineContent(HeadlineContentEntity headlineContentEntity) throws Exception;

}
