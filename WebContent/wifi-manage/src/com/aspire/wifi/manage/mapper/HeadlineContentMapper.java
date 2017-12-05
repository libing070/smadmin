package com.aspire.wifi.manage.mapper;

import java.util.List;
import java.util.Map;

import com.aspire.wifi.manage.entity.HeadlineContentEntity;
import com.aspire.wifi.manage.entity.HeadlineReplyEntity;
import com.aspire.wifi.manage.entity.UserInfoEntity;


public interface HeadlineContentMapper {
	List<HeadlineContentEntity> searchHeadlineList(Map paramMap);
	String queryShiroManger(String account);
	Integer searchHeadlineListCount(Map paramMap);
	void updateHeadline(Map<String, Object> paramMap);
	List<HeadlineContentEntity> queryDelStatusHeadLineList(
			Map<String, Object> paramMap);
	Integer queryDelStatusHeadLineListCount(Map<String, Object> paramMap);
	List<HeadlineReplyEntity> querydelStatusReplyList(
			Map<String, Object> paramMap);
	Integer querydelStatusReplyListCount(Map<String, Object> paramMap);
	void delReplyById(Map<String, Object> paramMap);
	void insertHeadlineContent(HeadlineContentEntity headlineContentEntity);
	UserInfoEntity queryUserInfo(String msisdn);
}
