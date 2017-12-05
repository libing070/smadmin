package com.aspire.wifi.wap.mapper;



import java.util.List;

import com.aspire.wifi.wap.entity.Parameter;
import com.aspire.wifi.wap.entity.VideoInfo;

public interface VideoInfoMapper {

	List<VideoInfo> selectVideoInfo(VideoInfo videoInfo);
	List<VideoInfo> selectVideoIndexInfo(VideoInfo videoInfo);
	List<VideoInfo> selectVideoIndexTopInfo(VideoInfo videoInfo);
	
	List<VideoInfo> selectVideoPicById(String videoId);
	
}
