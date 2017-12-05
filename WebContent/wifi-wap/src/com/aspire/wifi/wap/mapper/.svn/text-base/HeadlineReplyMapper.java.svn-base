package com.aspire.wifi.wap.mapper;

import java.util.List;
import java.util.Map;

import com.aspire.wifi.wap.entity.HeadlineContentAttachEntity;
import com.aspire.wifi.wap.entity.HeadlineReplyAttachEntity;
import com.aspire.wifi.wap.entity.HeadlineReplyEntity;
import com.aspire.wifi.wap.entity.Parameter;



public interface HeadlineReplyMapper {
	int queryMyReplyCount(String mobile);
	int queryMyZanCount(String mobile);
	void delZan(String id);
	void addMyZanInfo(HeadlineReplyEntity headlineReplyEntity);
	List<HeadlineReplyEntity> queryMyZanInfo(Parameter parameter);
	List<HeadlineReplyEntity> queryMyReplyInfo(Parameter parameter);
	String selectParentNameById(String id);
	String queryParentNameById(String id);
	String queryParentMobileById(String id);
	List<HeadlineContentAttachEntity> findMyAttach(String headlineId);
    List<HeadlineReplyEntity> queryHeadlineReply(HeadlineReplyEntity headlineReplyEntity);
    HeadlineReplyEntity findHeadlineReply(String replyId) throws Exception;
    int queryHeadlineReplyCount(HeadlineReplyEntity headlineReplyEntity);
    void insertComments(HeadlineReplyEntity headlineReplyEntity);
    void updateHeadlineReply(HeadlineReplyEntity headlineReplyEntity);
    void updateReplyStatusById(HeadlineReplyEntity headlineReplyEntity);
    void voteHeadlineContent(HeadlineReplyEntity headlineReplyEntity) throws Exception;
    List<HeadlineReplyEntity> queryShiYongByMobile(Map<String, Object> paramMap)throws Exception;
    HeadlineReplyEntity queryReplyById(String replyId)throws Exception;
    void deleteReAttachByNum(String pictureSerialnum);
    List<HeadlineReplyAttachEntity> findReAttach(String pictureSerialnum);
    List<HeadlineReplyAttachEntity> queryReplyAttach(String replyId);
    int uploadHeadlineReplyAttach(HeadlineReplyAttachEntity h);
    List<HeadlineReplyEntity> queryReplyByReplyType(Map<String, Object> paramMap)throws Exception;
}
