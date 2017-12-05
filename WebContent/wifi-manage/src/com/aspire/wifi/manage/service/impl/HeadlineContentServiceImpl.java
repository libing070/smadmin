package com.aspire.wifi.manage.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aspire.wifi.manage.entity.HeadlineContentAttachEntity;
import com.aspire.wifi.manage.entity.HeadlineContentEntity;
import com.aspire.wifi.manage.entity.HeadlineReplyEntity;
import com.aspire.wifi.manage.entity.UserInfoEntity;
import com.aspire.wifi.manage.mapper.HeadlineContentAttachMapper;
import com.aspire.wifi.manage.mapper.HeadlineContentMapper;
import com.aspire.wifi.manage.service.HeadlineContentService;
import com.aspire.wifi.manage.util.SensitiveWordUtil;
@Service("headlineContentService")
public class HeadlineContentServiceImpl implements HeadlineContentService {
	@Resource(name = "headlineContentMapper")
	private HeadlineContentMapper headlineContentMapper;
	@Resource(name = "headlineContentAttachMapper")
	private HeadlineContentAttachMapper headlineContentAttachMapper;
    @Resource(name = "sensitiveWordUtil")
    private SensitiveWordUtil sensitiveWordUtil;
	public List<HeadlineContentEntity> searchHeadlineList(Map paramMap) {
		return headlineContentMapper.searchHeadlineList(paramMap);
	}
	public Integer searchHeadlineListCount(Map paramMap) {
		return headlineContentMapper.searchHeadlineListCount(paramMap);
	}
	public byte[] findPic(String pictureSerialnum) throws Exception {
	        HeadlineContentAttachEntity attach = headlineContentAttachMapper.findAttach(pictureSerialnum);
	        if (attach != null) {
	            return attach.getPicture();
	        }
	       return null;
	}
	
    public List<HeadlineContentAttachEntity> queryHeadlineAttach(Long headlineId) throws Exception {
        return headlineContentAttachMapper.queryAttach(String.valueOf(headlineId));
    }
	public void updateHeadline(Map<String, Object> paramMap) {
		 headlineContentMapper.updateHeadline(paramMap);
	}
	public List<HeadlineContentEntity> queryDelStatusHeadLineList(
			Map<String, Object> paramMap) {
		return headlineContentMapper.queryDelStatusHeadLineList(paramMap);
	}
	public List<HeadlineReplyEntity> querydelStatusReplyList(
			Map<String, Object> paramMap) {
		return headlineContentMapper.querydelStatusReplyList(paramMap);
	}
	public Integer queryDelStatusHeadLineListCount(Map<String, Object> paramMap) {
		return headlineContentMapper.queryDelStatusHeadLineListCount(paramMap);
	}
	public Integer querydelStatusReplyListCount(Map<String, Object> paramMap) {
		return headlineContentMapper.querydelStatusReplyListCount(paramMap);
	}
	public void delReplyById(Map<String, Object> paramMap) {
		headlineContentMapper.delReplyById(paramMap);
		
	}
	
    public String addHeadlineContent(HeadlineContentEntity headlineContentEntity){
        headlineContentEntity.setTitle(sensitiveWordUtil.filterSensitiveWord(headlineContentEntity.getTitle()));
        headlineContentEntity.setContent(sensitiveWordUtil.filterSensitiveWord(headlineContentEntity.getContent()));
        headlineContentMapper.insertHeadlineContent(headlineContentEntity);
        return headlineContentEntity.getHeadlineId().toString();
    }
	public UserInfoEntity queryUserInfo(String msisdn) {
		return headlineContentMapper.queryUserInfo(msisdn);
	}
	public void uploadHeadlineContentAttach(HeadlineContentAttachEntity attach) {
		headlineContentAttachMapper.uploadHeadlineContentAttach(attach);
		
	}
	public String queryShiroManger(String account) {
		return headlineContentMapper.queryShiroManger(account);
	}
}
