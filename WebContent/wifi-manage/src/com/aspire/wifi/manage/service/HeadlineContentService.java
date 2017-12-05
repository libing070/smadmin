package com.aspire.wifi.manage.service;

import java.util.List;
import java.util.Map;

import com.aspire.wifi.manage.entity.HeadlineContentAttachEntity;
import com.aspire.wifi.manage.entity.HeadlineContentEntity;
import com.aspire.wifi.manage.entity.HeadlineReplyEntity;
import com.aspire.wifi.manage.entity.UserInfoEntity;

public interface HeadlineContentService {
	List<HeadlineContentEntity> searchHeadlineList(Map paramMap);
	String queryShiroManger(String account);
	Integer searchHeadlineListCount(Map paramMap);
	 byte[] findPic(String pictureSerialnum) throws Exception ;
	  List<HeadlineContentAttachEntity> queryHeadlineAttach(Long headlineId) throws Exception;
	void updateHeadline(Map<String, Object> paramMap);
	List<HeadlineContentEntity> queryDelStatusHeadLineList(
			Map<String, Object> paramMap);
	Integer queryDelStatusHeadLineListCount(Map<String, Object> paramMap);
	List<HeadlineReplyEntity> querydelStatusReplyList(
			Map<String, Object> paramMap);
	Integer querydelStatusReplyListCount(Map<String, Object> paramMap);
	void delReplyById(Map<String, Object> paramMap);
	String addHeadlineContent(HeadlineContentEntity entity);
	UserInfoEntity queryUserInfo(String msisdn);
	void uploadHeadlineContentAttach(HeadlineContentAttachEntity attach);
}
