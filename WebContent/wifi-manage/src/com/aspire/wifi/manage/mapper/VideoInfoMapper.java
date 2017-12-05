package com.aspire.wifi.manage.mapper;

import java.util.List;
import java.util.Map;

import com.aspire.wifi.manage.entity.HeadlineContentAttachEntity;
import com.aspire.wifi.manage.entity.HeadlineContentEntity;
import com.aspire.wifi.manage.entity.VideoInfo;

public interface VideoInfoMapper {

	List<VideoInfo> queryVideoInfo(Map paramMap);
	List<VideoInfo> searchVideoInfoByVideoId(Map paramMap);
	Integer searchVideoInfoListCount(Map paramMap);
	 public void uploadVideoInfo(Map paramMap);
	 public void insertVideoInfo(VideoInfo videoInfo);
}
