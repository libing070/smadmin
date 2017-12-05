package com.aspire.wifi.wap.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.aspire.wifi.wap.entity.HeadlineContentAttachEntity;
import com.aspire.wifi.wap.entity.HeadlineReplyEntity;
import com.aspire.wifi.wap.mapper.HeadlineContentAttachMapper;
import com.aspire.wifi.wap.mapper.HeadlineReplyMapper;
import com.aspire.wifi.wap.util.SensitiveWordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.wap.entity.HeadlineContentEntity;
import com.aspire.wifi.wap.entity.Parameter;
import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.mapper.HeadlineContentMapper;
import com.aspire.wifi.wap.service.intf.HeadlineContentService;

@Service("headlineContentService")
public class HeadlineContentServiceImpl implements HeadlineContentService {
	protected static Logger logger =  LoggerFactory.getLogger(HeadlineContentServiceImpl.class);

	@Resource(name = "headlineContentMapper")
	private HeadlineContentMapper headlineContentMapper;

    @Resource(name = "headlineContentAttachMapper")
    private HeadlineContentAttachMapper headlineContentAttachMapper;

    @Resource(name = "headlineReplyMapper")
    private HeadlineReplyMapper headlineReplyMapper;

    @Resource(name = "sensitiveWordUtil")
    private SensitiveWordUtil sensitiveWordUtil;

	public int queryMyHeadCount(String mobile) throws Exception {
		int result =0;
		try{
			
			result=headlineContentMapper.queryMyHeadCount(mobile);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询我的头条总数异常，手机用户："+mobile);
		}
		return result;
	}

	public Parameter queryReplyAndPraiseCount(String mobile) throws Exception {
		Parameter result  =new Parameter();
		try{
			
			result=headlineContentMapper.queryReplyAndPraiseCount(mobile);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询我的头条评论和赞总数异常，手机用户："+mobile);
		}
		return result;
	}

	public List<HeadlineContentEntity> queryMyHeadInfo(String mobile)
			throws Exception {
		List<HeadlineContentEntity> result  =new ArrayList<HeadlineContentEntity>();
		try{
			
			result= headlineContentMapper.queryMyHeadInfo(mobile);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询我的头条信息异常，手机用户："+mobile);
		}
		return result;
	}
	
	public List<HeadlineContentEntity> queryTopTitle()throws Exception {
		List<HeadlineContentEntity> result  =new ArrayList<HeadlineContentEntity>();
		try{
			result=headlineContentMapper.queryTopTitle();
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询头条出错"+e);
		}
		return result;
	}
	
	public List<HeadlineContentEntity> queryTopContentById(int headlineId)throws Exception{
		List<HeadlineContentEntity> result  =new ArrayList<HeadlineContentEntity>();
		try{
			result=headlineContentMapper.queryTopContentById(headlineId);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("根据Id查询头条出错"+e);
		}
		return result;
	}


    public Map<String, Object> queryHeadlineContent(HeadlineContentEntity headlineContentEntity) throws Exception {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<HeadlineContentEntity> list = null;
        Integer perLoadSize = Integer.valueOf(headlineContentEntity.getField("perLoadSize").toString());
        Integer currpage = Integer.valueOf(headlineContentEntity.getField("currpage").toString());
        Integer count = headlineContentMapper.queryHeadlineContentCount(headlineContentEntity);
    	Integer totalPage=count%perLoadSize==0?count/perLoadSize:count/perLoadSize+1;
      /*  if (count <= perLoadSize) {
            list = headlineContentMapper.queryHeadlineContent(headlineContentEntity);
            returnMap.put("more", "no");
        } else {
            headlineContentEntity.setLimit(perLoadSize);
            headlineContentEntity.setMysqlStart(0);
            list = headlineContentMapper.queryHeadlineContent(headlineContentEntity);
            returnMap.put("more", "yes");
        }*/
    	headlineContentEntity.setLimit(perLoadSize);
    	headlineContentEntity.setMysqlStart(currpage*perLoadSize);
    	list = headlineContentMapper.queryHeadlineContent(headlineContentEntity);
    	returnMap.put("totalPage", totalPage);
    	returnMap.put("currpage", currpage);
        returnMap.put("list", list);
        returnMap.put("count", count);
        return returnMap;
    }
    
    
    public Map<String, Object> queryIndexHeadlineContent(HeadlineContentEntity headlineContentEntity) throws Exception {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<HeadlineContentEntity> list = null;
        list = headlineContentMapper.queryIndexHeadlineContent(headlineContentEntity);
        returnMap.put("more", "no");
        returnMap.put("list", list);
        return returnMap;
    }

    
    public HeadlineContentEntity findHeadlineContent(Long headlineId) throws Exception {
        return headlineContentMapper.findHeadlineContent(headlineId);
    }

    
    public List<HeadlineContentAttachEntity> queryHeadlineAttach(Long headlineId) throws Exception {
        return headlineContentAttachMapper.queryAttach(String.valueOf(headlineId));
    }

    
    public byte[] findPic(String pictureSerialnum) throws Exception {
        HeadlineContentAttachEntity attach = headlineContentAttachMapper.findAttach(pictureSerialnum);
        if (attach != null) {
            return attach.getPicture();
        }
        return null;
    }

    
    public String addHeadlineContent(HeadlineContentEntity headlineContentEntity) throws Exception {
        headlineContentEntity.setTitle(sensitiveWordUtil.filterSensitiveWord(headlineContentEntity.getTitle()));
        headlineContentEntity.setContent(sensitiveWordUtil.filterSensitiveWord(headlineContentEntity.getContent()));
        headlineContentMapper.insertHeadlineContent(headlineContentEntity);
        return headlineContentEntity.getHeadlineId().toString();
    }

    
    public void modifyHeadlineContent(HeadlineContentEntity headlineContentEntity) throws Exception {
        headlineContentMapper.updateHeadlineContent(headlineContentEntity);
    }

    
    public void insertHeadlineAttach(HeadlineContentAttachEntity headlineContentAttachEntity) throws Exception {
        headlineContentAttachMapper.uploadHeadlineContentAttach(headlineContentAttachEntity);
    }

    
    public Integer voteHeadlineContent(HeadlineReplyEntity headlineReplyEntity) throws Exception {
        HeadlineReplyEntity temp = new HeadlineReplyEntity();
        temp.setHeadlineId(headlineReplyEntity.getHeadlineId());
        temp.setMobile(headlineReplyEntity.getMobile());
        temp.setReplyType("2");
        List<HeadlineReplyEntity> list = headlineReplyMapper.queryHeadlineReply(temp);
        if (list == null || list.isEmpty()) {
            headlineReplyMapper.voteHeadlineContent(headlineReplyEntity);
        }
        HeadlineContentEntity entity = headlineContentMapper.findHeadlineContent(headlineReplyEntity.getHeadlineId());
        return entity.getPraiseNum();
    }

    
    public void deleteHeadline(Long headlineId) throws Exception {
        HeadlineContentEntity temp = new HeadlineContentEntity();
        temp.setHeadlineId(headlineId);
        temp.setStatus(3);
        headlineContentMapper.updateHeadlineContent(temp);
        //
      headlineContentAttachMapper.deleteAttach(headlineId);
        //
//        HeadlineReplyEntity reply = new HeadlineReplyEntity();
//        reply.setHeadlineId(headlineId);
//        reply.setStatus(3);
//        headlineReplyMapper.updateHeadlineReply(reply);
    }

    /**
     * 查询首页头条信息
     * @author liuyao 2014-08-18
     */
    
	public List<HeadlineContentEntity> searchHeadlineList(
			HeadlineContentEntity headlineContentEntity) throws WifiException {
    	try {
    		return headlineContentMapper.searchHeadlineList(headlineContentEntity);
		} catch (Exception e) {
			logger.error("查询首页头条信息异常!",e);
			throw new WifiException("查询首页头条信息异常!",e);
		}
	}
    
}
