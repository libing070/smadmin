package com.aspire.wifi.wap.service.intf;

import java.util.List;
import java.util.Map;

import com.aspire.wifi.wap.entity.HeadlineContentAttachEntity;
import com.aspire.wifi.wap.entity.HeadlineReplyEntity;
import com.aspire.wifi.wap.entity.Parameter;



public interface HeadlineReplyService {
	int queryMyHeadCount(String mobile) throws Exception;
	int queryMyZanCount(String mobile) throws Exception;
	void delZan(String id) throws Exception;
	void addMyZanInfo(HeadlineReplyEntity headlineReplyEntity) throws Exception;
	List<HeadlineReplyEntity> queryMyZanInfo(Parameter parameter)  throws Exception;
	List<HeadlineReplyEntity> queryMyReplyInfo(Parameter parameter)  throws Exception;
	String selectParentNameById(String id)  throws Exception;
	String queryParentNameById(String id)  throws Exception;
    Map<String, Object> queryHeadlineReplay(HeadlineReplyEntity headlineReplyEntity) throws Exception;
	List<HeadlineContentAttachEntity> findMyAttach(String headlineId)throws Exception;
    String addComments(HeadlineReplyEntity headlineReplyEntity) throws Exception;

}
