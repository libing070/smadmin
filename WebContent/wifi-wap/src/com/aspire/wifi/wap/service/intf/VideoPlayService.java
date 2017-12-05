package com.aspire.wifi.wap.service.intf;


import com.aspire.wifi.wap.entity.VideoPlay;

public interface VideoPlayService {
	void insertVideoPlay(VideoPlay videoPlay) throws Exception;
	Integer selectVideoPlayById(VideoPlay videoPlay) throws Exception;
}
