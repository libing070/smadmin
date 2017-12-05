package com.aspire.wifi.wap.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

public class VideoInfo implements Serializable {

	private static final long serialVersionUID = 6177417450707400228L;

	private int videoId;
	
	private String videoName;
	private int videoType;
	private byte[] videoPic;	
	private Integer showTimeLong;
	private String videoDesc;
	private int playCnt;//浏览次数
	private String resourcePath;//资源路径
	private int isTopArea;//是否置顶
	private byte[] videoTopPic;//置顶图片
	private int begin;//开始查询数
	private int times;//查询多少条

	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public int getVideoType() {
		return videoType;
	}
	public void setVideoType(int videoType) {
		this.videoType = videoType;
	}
	public byte[] getVideoPic() {
		return videoPic;
	}
	public void setVideoPic(byte[] videoPic) {
		this.videoPic = videoPic;
	}
	public Integer getShowTimeLong() {
		return showTimeLong;
	}
	public void setShowTimeLong(Integer showTimeLong) {
		this.showTimeLong = showTimeLong;
	}
	public String getVideoDesc() {
		return videoDesc;
	}
	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}
	public int getPlayCnt() {
		return playCnt;
	}
	public void setPlayCnt(int playCnt) {
		this.playCnt = playCnt;
	}
	public String getResourcePath() {
		return resourcePath;
	}
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	
	public int getIsTopArea() {
		return isTopArea;
	}
	public void setIsTopArea(int isTopArea) {
		this.isTopArea = isTopArea;
	}
	public byte[] getVideoTopPic() {
		return videoTopPic;
	}
	public void setVideoTopPic(byte[] videoTopPic) {
		this.videoTopPic = videoTopPic;
	}

	
	
}
