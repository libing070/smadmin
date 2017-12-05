/**
 * 
 */
package com.aspire.shakewxpp.wap.smstool.builder;

import org.apache.commons.configuration.Configuration;

import com.aspire.shakewxpp.wap.smstool.bean.Address;
import com.aspire.shakewxpp.wap.smstool.bean.Address_Info;
import com.aspire.shakewxpp.wap.smstool.bean.sms.ChargedPartyID;
import com.aspire.shakewxpp.wap.smstool.bean.sms.Content_info;
import com.aspire.shakewxpp.wap.smstool.bean.sms.Msg_Address;
import com.aspire.shakewxpp.wap.smstool.bean.sms.Recipients;
import com.aspire.shakewxpp.wap.smstool.bean.sms.SMS_Content_Info;
import com.aspire.shakewxpp.wap.smstool.bean.sms.Senders;
import com.aspire.shakewxpp.wap.smstool.bean.sms.SubmitSMSReq;
import com.aspire.shakewxpp.wap.util.ConfigHandler;
import com.aspire.shakewxpp.wap.util.ConfigPathBean;
import com.aspire.shakewxpp.wap.util.Constants;
import com.aspire.shakewxpp.wap.util.StringTransTool;

/**
 * @author chenping
 * @2013-5-23
 */
public class SMSBuilder {

	private static String sendDeviceType;
	private static String sendDeviceId;
	private static String destDeviceType;
	private static String destDeviceId;
	private static String addressType;
	private static String classType;
	private static String recipientsAddressType;
	private static String recipientsClassType;
	private static String chargedPartyIDAddressType;
	private static String chargedPartyIDClassType;
	private static String msgType;
	private static String version;
	private static String spid;
	private static String sendersAddress;
	private static String spServiceId;
	private static String chargedParty;
	private static String cpCode;
	private static String linkId;
	private static String msgFmt;
	private static String bindMsisdnContentData;
	private static String getSubFolwRedData;
	
	private static Configuration config = null;

	/** 初始化配置项 */
	public static void init() {

		try {
			config = ConfigHandler.getConfiguration4Xml(ConfigPathBean
					.getSmsConfigPath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msgType = config.getString("MsgType");
		version = config.getString("Version");
		spid = config.getString("SPID");
		spServiceId = config.getString("SPServiceID");
		chargedParty = config.getString("ChargedParty");
		cpCode = config.getString("CPCode");
		linkId = config.getString("LinkID");

		/** 发送方信息 */
		sendDeviceType = config
				.getString("Send_Address.Address_Info.DeviceType");
		sendDeviceId = config.getString("Send_Address.Address_Info.DeviceID");

		/** 接收方消息 */
		destDeviceType = config
				.getString("Dest_Address.Address_Info.DeviceType");
		destDeviceId = config.getString("Dest_Address.Address_Info.DeviceID");

		addressType = config.getString("Senders.Msg_Address.AddressType");
		classType = config.getString("Senders.Msg_Address.ClassType");
		sendersAddress = config.getString("Senders.Msg_Address.Address");

		recipientsAddressType = config
				.getString("Recipients.Msg_Address.AddressType");
		recipientsClassType = config
				.getString("Recipients.Msg_Address.ClassType");

		chargedPartyIDAddressType = config
				.getString("ChargedPartyID.Msg_Address.AddressType");
		chargedPartyIDClassType = config
				.getString("ChargedPartyID.Msg_Address.ClassType");
		msgFmt = config.getString("Content_info.SMS_Content_Info.MsgFmt");
		bindMsisdnContentData = config.getString("Content_info.SMS_Content_Info.BindMsisdnData");
		getSubFolwRedData = config.getString("Content_info.SMS_Content_Info.GetSubFolwRedData");
	}

	public SMSBuilder() {

	}

	/**
	 * 构建地址信息
	 * 
	 * @param deviceType
	 * @param deviceId
	 * @return
	 */
	public Address_Info buildAddressInfo(String deviceType, String deviceId) {

		return new Address_Info(deviceType, deviceId);

	}

	/**
	 * 构建地址
	 * 
	 * @param deviceType
	 * @param deviceId
	 * @return
	 */
	public Address buildAddress(String deviceType, String deviceId) {

		return new Address(buildAddressInfo(deviceType, deviceId));

	}

	public Senders buildSenders(Msg_Address msgAddress) {

		return new Senders(msgAddress);
	}

	public ChargedPartyID buildChargedPartyID(Msg_Address msgAddress) {

		return new ChargedPartyID(msgAddress);
	}

	public Content_info buildContentInfo(SMS_Content_Info smsContentInf) {

		return new Content_info(smsContentInf);
	}

	public Msg_Address buildMsgAddress(String addressType, String classType,
			String address) {

		return new Msg_Address(addressType, classType, address);
	}

	public Recipients buildRecipients(Msg_Address msgAddress) {

		return new Recipients(msgAddress);
	}

	public SMS_Content_Info buildSMSContentInfo(String msgFmt, String data) {

		return new SMS_Content_Info(msgFmt, data);
	}

	/**
	 * 提供一个短信消息发送实体
	 * 
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
	 * @return
	 */
	public SubmitSMSReq buildSubmitSMSReq(String destPhone, String context,String weixinAppNameView,int msgTypePara) throws Exception{

		Address sendAddress = this.buildAddress(sendDeviceType, sendDeviceId);
		Address destAddress = this.buildAddress(destDeviceType, destDeviceId);
		String contentId = StringTransTool.getTimestampStr();
		Msg_Address msgAddress = this.buildMsgAddress(addressType, classType,
				sendersAddress);
		Senders senderd = this.buildSenders(msgAddress);

		Msg_Address msgAddress2 = this.buildMsgAddress(recipientsAddressType,
				recipientsClassType, destPhone);
		Recipients recipients = this.buildRecipients(msgAddress2);

		Msg_Address msgAddress3 = this.buildMsgAddress(
				chargedPartyIDAddressType, chargedPartyIDClassType, destPhone);
		ChargedPartyID chargedPartyId = this.buildChargedPartyID(msgAddress3);
		//替换模板中的${context}
		String smsContent = "";
		if(msgTypePara==Constants.SEND_MSG_TYPE_FRO_BIND){
			smsContent = bindMsisdnContentData.replace("${context}", context);
		}else if(msgTypePara==Constants.SEND_MSG_TYPE_FRO_GET_FLOW){
			smsContent = getSubFolwRedData.replace("${context}", context);
		}
		
		
		//替换模板中的${weixinAppNameView}
		smsContent = smsContent.replace("${weixinAppNameView}", weixinAppNameView);
	
		context = StringTransTool.base64Encode(smsContent);

		SMS_Content_Info content = this.buildSMSContentInfo(msgFmt, context);
		Content_info contentInfo = this.buildContentInfo(content);

		return new SubmitSMSReq(msgType, version, sendAddress, destAddress,
				spid, senderd, recipients, spServiceId, chargedParty,
				chargedPartyId, cpCode, contentId, contentInfo, linkId);

	}
}
