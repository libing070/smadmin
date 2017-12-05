package com.aspire.wifi.wap.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.wap.entity.Parameter;
import com.aspire.wifi.wap.entity.VideoInfo;
import com.aspire.wifi.wap.mapper.VideoInfoMapper;
import com.aspire.wifi.wap.service.intf.VideoInfoService;

@Service("videoInfoService")
public class VideoInfoServiceImpl implements VideoInfoService {
	protected static Logger logger =  LoggerFactory.getLogger(VideoInfoServiceImpl.class);

	@Resource(name = "videoInfoMapper")
	private VideoInfoMapper videoInfoMapper;

	
	public List<VideoInfo> selectVideoInfo(VideoInfo videoInfo) throws Exception {
		List<VideoInfo> sesult = null;
		try{
			sesult = videoInfoMapper.selectVideoInfo(videoInfo);
		}catch(Exception e){
			throw new Exception("查询视频异常;error:"+e.getMessage());
		}
		return sesult;
	}



	public List<VideoInfo> selectVideoPicById(String videoId) throws Exception {
		List<VideoInfo> sesult = null;
		try{
			sesult = videoInfoMapper.selectVideoPicById(videoId);
		}catch(Exception e){
			throw new Exception("查询视频显示图片异常;error:"+e.getMessage());
		}
		return sesult;
	}



	public List<VideoInfo> selectVideoIndexInfo(VideoInfo videoInfo)
			throws Exception {
		List<VideoInfo> sesult = null;
		try{
			sesult = videoInfoMapper.selectVideoIndexInfo(videoInfo);
		}catch(Exception e){
			throw new Exception("查询视频异常;error:"+e.getMessage());
		}
		return sesult;
	}
	
	public List<VideoInfo> selectVideoIndexTopInfo(VideoInfo videoInfo)
	throws Exception {
		List<VideoInfo> sesult = null;
		try{
			sesult = videoInfoMapper.selectVideoIndexTopInfo(videoInfo);
		}catch(Exception e){
			throw new Exception("查询视频异常;error:"+e.getMessage());
		}
		return sesult;
	}



	
}
