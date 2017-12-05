package com.aspire.wifi.wap.service.impl;




import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.wap.entity.MobileReplyReviewEntity;
import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.mapper.MobileReplyReviewMapper;
import com.aspire.wifi.wap.service.intf.MobileReplyReviewService;

@Service("mobileReplyReviewService")
public class MobileReplyReviewServiceImpl implements MobileReplyReviewService {
	private static final Logger logger =  LoggerFactory.getLogger(MobileReplyReviewServiceImpl.class);

	@Resource(name = "mobileReplyReviewMapper")
	private MobileReplyReviewMapper mobileReplyReviewMapper;


	public void addMymrrZanInfo(MobileReplyReviewEntity mobileReplyReviewEntity)
			throws WifiException {
	try{
			
		mobileReplyReviewMapper.addMymrrZanInfo(mobileReplyReviewEntity);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("新增我的用户查看评论表信息异常，手机用户："+mobileReplyReviewEntity.getMobile());
			throw new WifiException("新增我的用户查看评论表信息异常，\r\n" + e.getMessage());
		}	
	}

	public int queryMymrrCount(String mobile) throws WifiException {
		int result =0;
		try{
			
			result=mobileReplyReviewMapper.queryMymrrCount(mobile);
		}catch(Exception e){
			logger.debug("查询我的评论的回复总数异常，手机用户："+mobile);
			throw new WifiException("查询我的评论的回复总数异常，\r\n" + e.getMessage());
		}
		return result;
	}

	public void updateMymrrReplyInfo(
			MobileReplyReviewEntity mobileReplyReviewEntity) throws WifiException {
		try{
			
			mobileReplyReviewMapper.updateMymrrReplyInfo(mobileReplyReviewEntity);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("新增我的用户查看评论表信息异常，手机用户："+mobileReplyReviewEntity.getMobile());
			throw new WifiException("新增我的用户查看评论表信息异常\r\n" + e.getMessage());
		}	
		
	}
	
	

	
}
