package com.aspire.wifi.wap.service.impl;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.wap.entity.VideoPlay;
import com.aspire.wifi.wap.mapper.VideoPlayMapper;
import com.aspire.wifi.wap.service.intf.VideoPlayService;

@Service("videoPlayService")
public class VideoPlayServiceImpl implements VideoPlayService {
	protected static Logger logger =  LoggerFactory.getLogger(VideoPlayServiceImpl.class);

	@Resource(name = "videoPlayMapper")
	private VideoPlayMapper videoPlayMapper;

	public void insertVideoPlay(VideoPlay videoPlay) throws Exception {
		try{
			 videoPlayMapper.insertVideoPlay(videoPlay);
		}catch(Exception e){
			logger.error("新增视频播放信息异常;error:"+e.getMessage());
			throw new Exception("新增视频播放信息异常;error:"+e.getMessage());
		}
	}
	
	public Integer selectVideoPlayById(VideoPlay videoPlay)
			throws Exception {
		Integer bro = 0;
		try{
			bro =videoPlayMapper.selectVideoPlayById(videoPlay);
		}catch(Exception e){
			logger.error("查询视频播放浏览次数异常;error:"+e.getMessage());
			throw new Exception("查询视频播放浏览次数异常;error:"+e.getMessage());
		}
		return bro;
	}


	
}
