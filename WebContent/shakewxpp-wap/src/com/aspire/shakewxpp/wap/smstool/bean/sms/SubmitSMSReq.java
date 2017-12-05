/**
 * 
 */
package com.aspire.shakewxpp.wap.smstool.bean.sms;

import com.aspire.shakewxpp.wap.smstool.bean.Address;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author chenping
 * @2013-5-23
 */
@XStreamAlias("SubmitSMSReq")
public class SubmitSMSReq {

	@XStreamAlias("MsgType")
	private String msgType;

	@XStreamAlias("Version")
	private String version;

	@XStreamAlias("Send_Address")
	private Address sendAddress;

	@XStreamAlias("Dest_Address")
	private Address destAddress;

	@XStreamAlias("SPID")
	private String spid;

	@XStreamAlias("Senders")
	private Senders senders;

	@XStreamAlias("Recipients")
	private Recipients recipients;

	@XStreamAlias("SPServiceID")
	private String spServiceId;

	@XStreamAlias("ChargedParty")
	private String chargedParty;

	@XStreamAlias("ChargedPartyID")
	private ChargedPartyID chargedPartyId;

	@XStreamAlias("CPCode")
	private String cpCode;

	@XStreamAlias("ContentID")
	private String contentId;

	@XStreamAlias("Content_info")
	private Content_info contentInfo;

	@XStreamAlias("LinkID")
	private String linkId;

//	@XStreamAlias("DeliveryRpt")
//	private String deliveryRpt;

	/**
	 * @param msgType
	 * @param version
	 * @param sendAddress
	 * @param destAddress
	 * @param spid
	 * @param senders
	 * @param recipients
	 * @param spServiceId
	 * @param chargedParty
	 * @param chargedPartyId
	 * @param cpCode
	 * @param contentId
	 * @param contentInfo
	 * @param linkId
	 * @param deliveryRpt
	 */
	public SubmitSMSReq(String msgType, String version, Address sendAddress,
			Address destAddress, String spid, Senders senders,
			Recipients recipients, String spServiceId, String chargedParty,
			ChargedPartyID chargedPartyId, String cpCode, String contentId,
			Content_info contentInfo, String linkId) {
//		, String deliveryRpt
		this.msgType = msgType;
		this.version = version;
		this.sendAddress = sendAddress;
		this.destAddress = destAddress;
		this.spid = spid;
		this.senders = senders;
		this.recipients = recipients;
		this.spServiceId = spServiceId;
		this.chargedParty = chargedParty;
		this.chargedPartyId = chargedPartyId;
		this.cpCode = cpCode;
		this.contentId = contentId;
		this.contentInfo = contentInfo;
		this.linkId = linkId;
//		this.deliveryRpt = deliveryRpt;
	}

	public SubmitSMSReq() {
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Address getSendAddress() {
		return sendAddress;
	}

	public void setSendAddress(Address sendAddress) {
		this.sendAddress = sendAddress;
	}

	public Address getDestAddress() {
		return destAddress;
	}

	public void setDestAddress(Address destAddress) {
		this.destAddress = destAddress;
	}

	public String getSpid() {
		return spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	public Senders getSenders() {
		return senders;
	}

	public void setSenders(Senders senders) {
		this.senders = senders;
	}

	public Recipients getRecipients() {
		return recipients;
	}

	public void setRecipients(Recipients recipients) {
		this.recipients = recipients;
	}

	public String getSpServiceId() {
		return spServiceId;
	}

	public void setSpServiceId(String spServiceId) {
		this.spServiceId = spServiceId;
	}

	public String getChargedParty() {
		return chargedParty;
	}

	public void setChargedParty(String chargedParty) {
		this.chargedParty = chargedParty;
	}

	public ChargedPartyID getChargedPartyId() {
		return chargedPartyId;
	}

	public void setChargedPartyId(ChargedPartyID chargedPartyId) {
		this.chargedPartyId = chargedPartyId;
	}

	public String getCpCode() {
		return cpCode;
	}

	public void setCpCode(String cpCode) {
		this.cpCode = cpCode;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

//	public String getDeliveryRpt() {
//		return deliveryRpt;
//	}
//
//	public void setDeliveryRpt(String deliveryRpt) {
//		this.deliveryRpt = deliveryRpt;
//	}

	public Content_info getContentInfo() {
		return contentInfo;
	}

	public void setContentInfo(Content_info contentInfo) {
		this.contentInfo = contentInfo;
	}
	
	/**
	 * 
	 * @return
	 */
	public String toXml() {
		StringBuilder xml = new StringBuilder();
		
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xml.append("<SubmitSMSReq>");
		xml.append("<MsgType>SubmitSMSReq</MsgType>");
		xml.append("<Version>1.2.0</Version>");
		xml.append("<Send_Address><Address_Info><DeviceType>203</DeviceType><DeviceID>WWW Portal</DeviceID></Address_Info></Send_Address>");
		xml.append("<Dest_Address><Address_Info><DeviceType>200</DeviceType><DeviceID>CSS</DeviceID></Address_Info></Dest_Address>");
		xml.append("<SPID>" + this.spid + "</SPID>");
		xml.append("<SPServiceID>" + this.spServiceId + "</SPServiceID>");
		xml.append("<Senders><Msg_Address><AddressType>" + this.senders.getMsgAddress().getAddressType() + "</AddressType><ClassType>"+this.senders.getMsgAddress().getClassType()+"</ClassType><Address>"+this.senders.getMsgAddress().getAddress()+"</Address></Msg_Address></Senders>");
		xml.append("<Recipients><Msg_Address><AddressType>" + this.recipients.getMsgAddress().getAddressType() + "</AddressType><ClassType>"+this.recipients.getMsgAddress().getClassType()+"</ClassType><Address>"+this.recipients.getMsgAddress().getAddress()+"</Address></Msg_Address></Recipients>");
		xml.append("<ChargedParty>" + this.chargedParty + "</ChargedParty>");
		xml.append("<ChargedPartyID>");
			xml.append("<Msg_Address>");
				xml.append("<AddressType>" + this.chargedPartyId.getMsgAddress().getAddressType() + "</AddressType>");
				xml.append("<ClassType>" + this.chargedPartyId.getMsgAddress().getClassType() + "</ClassType>");
				xml.append("<Address>" + this.chargedPartyId.getMsgAddress().getAddress() + "</Address>");
			xml.append("</Msg_Address>");
		xml.append("</ChargedPartyID>");
		xml.append("<CPCode>" + this.cpCode + "</CPCode>");
		xml.append("<ContentID>" + this.contentId + "</ContentID>");
		xml.append("<Content_info>");
			xml.append("<SMS_Content_Info>");
				xml.append("<MsgFmt>" + this.contentInfo.getSmsContentInfo().getMsgFmt() + "</MsgFmt>");
				xml.append("<Data>" + this.contentInfo.getSmsContentInfo().getData() + "</Data>");
			xml.append("</SMS_Content_Info>");
		xml.append("</Content_info>");
		xml.append("<LinkID>" + this.linkId + "</LinkID>");
		xml.append("</SubmitSMSReq>");
		
		return xml.toString();
	}
}
