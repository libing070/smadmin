package com.aspire.wifi.wap.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

public class HeadlineReplyAttachEntity extends BaseDomain{
	   private Long replyId;
	    @JsonIgnore
	    private byte[] picture;
	    private Long pictureSerialnum;
		public Long getReplyId() {
			return replyId;
		}
		public void setReplyId(Long replyId) {
			this.replyId = replyId;
		}
		public byte[] getPicture() {
			return picture;
		}
		public void setPicture(byte[] picture) {
			this.picture = picture;
		}
		public Long getPictureSerialnum() {
			return pictureSerialnum;
		}
		public void setPictureSerialnum(Long pictureSerialnum) {
			this.pictureSerialnum = pictureSerialnum;
		}

}
