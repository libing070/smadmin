package com.aspire.wifi.wap.mapper;

import com.aspire.wifi.wap.entity.VideoPlay;





public interface VideoPlayMapper {

	void insertVideoPlay(VideoPlay videoPlay);
	
	Integer selectVideoPlayById(VideoPlay videoPlay);
}
