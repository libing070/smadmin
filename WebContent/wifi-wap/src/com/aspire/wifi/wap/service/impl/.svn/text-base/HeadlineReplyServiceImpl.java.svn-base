package com.aspire.wifi.wap.service.impl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.aspire.wifi.wap.util.SensitiveWordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.wap.entity.HeadlineContentAttachEntity;
import com.aspire.wifi.wap.entity.HeadlineReplyEntity;
import com.aspire.wifi.wap.entity.Parameter;
import com.aspire.wifi.wap.mapper.HeadlineReplyMapper;
import com.aspire.wifi.wap.service.intf.HeadlineReplyService;

@Service("headlineReplyService")
public class HeadlineReplyServiceImpl implements HeadlineReplyService {
	protected static Logger logger =  LoggerFactory.getLogger(HeadlineReplyServiceImpl.class);

	@Resource(name = "headlineReplyMapper")
	private HeadlineReplyMapper headlineReplyMapper;

    @Resource(name = "sensitiveWordUtil")
    private SensitiveWordUtil sensitiveWordUtil;

	public int queryMyHeadCount(String mobile) throws Exception {
		int result =0;
		try{
			
			result=headlineReplyMapper.queryMyReplyCount(mobile);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询我的评论的回复总数异常，手机用户："+mobile);
		}
		return result;
	}
	public int queryMyZanCount(String mobile) throws Exception {
		int result =0;
		try{
			
			result=headlineReplyMapper.queryMyZanCount(mobile);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询我的评论的回复总数异常，手机用户："+mobile);
		}
		return result;
	}

	public List<HeadlineReplyEntity> queryMyZanInfo(Parameter parameter) {
		List<HeadlineReplyEntity>  result = new ArrayList<HeadlineReplyEntity> ();
		try{
			
			result=headlineReplyMapper.queryMyZanInfo(parameter);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询我的赞信息异常，手机用户："+parameter.getVideoName());
		}
		return result;
	}

	public void delZan(String id) throws Exception {
		try{
			HeadlineReplyEntity h = new HeadlineReplyEntity();
			h.setReplyId(Long.valueOf(id));
			h.setStatus(3);
			headlineReplyMapper.updateReplyStatusById(h);
		}catch(Exception e){
			logger.debug("删除我的赞或评论异常"+e);
		}
	}

	public void addMyZanInfo(HeadlineReplyEntity headlineReplyEntity)
			throws Exception {
	try{
		  // 过滤敏感词

        headlineReplyEntity.setContent(sensitiveWordUtil.filterSensitiveWord(headlineReplyEntity.getContent()));
			headlineReplyMapper.addMyZanInfo(headlineReplyEntity);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("增加我的评论异常");
		}
		
	}
	public List<HeadlineReplyEntity> queryMyReplyInfo(Parameter parameter)
			throws Exception {
		List<HeadlineReplyEntity>  result = new ArrayList<HeadlineReplyEntity> ();
		try{
			
			result=headlineReplyMapper.queryMyReplyInfo(parameter);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询我的评论信息异常，手机用户："+parameter.getVideoName());
		}
		return result;
	}
	
	public String queryParentNameById(String headlineId) throws Exception {
		String result ="";
		try{
			
			result=headlineReplyMapper.queryParentNameById(headlineId);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询我的父亲评论的昵称异常");
		}
		return result;
	}
	
	public String selectParentNameById(String parentReplyId) throws Exception {
		String result ="";
		try{
			
			result=headlineReplyMapper.selectParentNameById(parentReplyId);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询我的父亲头条的昵称异常");
		}
		return result;
	}
	public List<HeadlineContentAttachEntity> findMyAttach(String headlineId)
			throws Exception {
		List<HeadlineContentAttachEntity>  result = new ArrayList<HeadlineContentAttachEntity> ();
		try{
			
			result=headlineReplyMapper.findMyAttach(headlineId);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询我的头条的图片异常：头条ID:"+headlineId);
		}
		return result;
	}

    public Map<String, Object> queryHeadlineReplay(HeadlineReplyEntity headlineReplyEntity) throws Exception {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<HeadlineReplyEntity> returnList = null;
        // 评论总数
        HeadlineReplyEntity entity = new HeadlineReplyEntity();
        entity.setHeadlineId(headlineReplyEntity.getHeadlineId());
        entity.setReplyType(headlineReplyEntity.getReplyType());
        Integer replyCount = headlineReplyMapper.queryHeadlineReplyCount(entity);
        // 页面上每页显示评论数
        String preCommentsSize = headlineReplyEntity.getField("preCommentsSize")==null?"4":headlineReplyEntity.getField("preCommentsSize").toString();
        // 页面点击更多评论按钮带入参数
        String more = headlineReplyEntity.getField("more")==null?"":headlineReplyEntity.getField("more").toString();
        //
        int moreCount = 0;//更多评论显示的条数
        if (replyCount <= Integer.valueOf(preCommentsSize) + 1) {
            returnList = headlineReplyMapper.queryHeadlineReply(headlineReplyEntity);
            returnMap.put("more", "no");
        } else {
            headlineReplyEntity.setLimit(Integer.valueOf(preCommentsSize));
            headlineReplyEntity.setMysqlStart(0);
            returnList = headlineReplyMapper.queryHeadlineReply(headlineReplyEntity);
            returnMap.put("more", "yes");
            moreCount = replyCount - Integer.valueOf(preCommentsSize) - 1;
            //
            headlineReplyEntity.setLimit(1);
            headlineReplyEntity.setMysqlStart(replyCount - 1);
            List<HeadlineReplyEntity> firstRecord = headlineReplyMapper.queryHeadlineReply(headlineReplyEntity);
            returnMap.put("first", firstRecord.get(0));
        }
        returnMap.put("list", returnList);
        returnMap.put("moreCount", moreCount);
        returnMap.put("count", replyCount);
        return returnMap;
    }

    public String addComments(HeadlineReplyEntity headlineReplyEntity) throws Exception {
        String parentReplyId = headlineReplyEntity.getParentReplyId();
        String replyType = headlineReplyEntity.getReplyType();
        if(!replyType.equals("4")&&!replyType.equals("3")){
	        if (parentReplyId == null || parentReplyId.equals("0")) {
	            headlineReplyEntity.setParentReplyId("0");
	            String parentMobile =headlineReplyMapper.queryParentMobileById(headlineReplyEntity.getHeadlineId()+"");
	            headlineReplyEntity.setParentMobile(parentMobile);
	        } else {
	            HeadlineReplyEntity entity = headlineReplyMapper.findHeadlineReply(parentReplyId);
	            headlineReplyEntity.setParentMobile(entity.getMobile());
	        }
        }
        // 过滤敏感词

        headlineReplyEntity.setContent(sensitiveWordUtil.filterSensitiveWord(headlineReplyEntity.getContent()));
        headlineReplyMapper.insertComments(headlineReplyEntity);
        //
        return headlineReplyEntity.getReplyId().toString();
    }
	

}
