package com.aspire.wifi.manage.entity;

import java.util.Date;

import com.aspire.wifi.manage.base.BaseDomain;

public class VideoInfo extends BaseDomain{

	private String videoId;
	private String videoName;
	private String videoDesc;
	private String videoType;
	private String resourcePath;
	private byte[] videoPic;
	private String showTimeLong;
	private String playCnt;
	private Date lastUpdateTime;
	private String lastUpdateTimeStr;
	private String isTopArea;
	private byte[] videoTopPic;
	private String videoStatus;
	public String getVideoStatus() {
		return videoStatus;
	}
	public void setVideoStatus(String videoStatus) {
		this.videoStatus = videoStatus;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getVideoDesc() {
		return videoDesc;
	}
	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}
	public String getVideoType() {
		return videoType;
	}
	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}
	public String getResourcePath() {
		return resourcePath;
	}
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	
	public byte[] getVideoPic() {
		return videoPic;
	}
	public void setVideoPic(byte[] videoPic) {
		this.videoPic = videoPic;
	}
	public String getShowTimeLong() {
		return showTimeLong;
	}
	public void setShowTimeLong(String showTimeLong) {
		this.showTimeLong = showTimeLong;
	}
	public String getPlayCnt() {
		return playCnt;
	}
	public void setPlayCnt(String playCnt) {
		this.playCnt = playCnt;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getIsTopArea() {
		return isTopArea;
	}
	public void setIsTopArea(String isTopArea) {
		this.isTopArea = isTopArea;
	}
	
	
	public byte[] getVideoTopPic() {
		return videoTopPic;
	}
	public void setVideoTopPic(byte[] videoTopPic) {
		this.videoTopPic = videoTopPic;
	}
	public String getLastUpdateTimeStr() {
		return lastUpdateTimeStr;
	}
	public void setLastUpdateTimeStr(String lastUpdateTimeStr) {
		this.lastUpdateTimeStr = lastUpdateTimeStr;
	}
}
