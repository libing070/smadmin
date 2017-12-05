package com.aspire.wifi.common.sms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.common.sms.constants.DIY09SmsConstants;
import com.aspire.wifi.common.sms.constants.SmsConstants;
import com.aspire.wifi.common.sms.entity.SmsReceiveMsg;
import com.aspire.wifi.common.sms.entity.SmsSendMsg;
import com.aspire.wifi.common.sms.exception.SmsException;
import com.aspire.wifi.common.sms.service.SmsReceiver;
import com.aspire.wifi.common.sms.service.SmsSender;
import com.aspire.wifi.wap.entity.ConsumePlaceEntity;
import com.aspire.wifi.wap.entity.PinCreateTableEntity;
import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.mapper.ConsumePlaceMapper;
import com.aspire.wifi.wap.mapper.PinCreateTableMapper;
import com.aspire.wifi.wap.service.intf.SysConfigService;
import com.aspire.wifi.wap.util.Constants;
import com.aspire.wifi.wap.util.DateUtil;
import com.aspire.wifi.wap.util.StringUtil;

@Service("smsReceiver")
public class SmsReceiverImpl implements SmsReceiver {
	/** 日志对象 */
	@Resource(name = "smsSender")
    private SmsSender smsSender;
	
	@Resource(name = "sysConfigService")
	private SysConfigService sysConfigService;
	
	@Resource(name = "consumePlaceMapper")
	private ConsumePlaceMapper consumePlaceMapper;
	
	@Resource(name = "pinCreateTableMapper")
	private PinCreateTableMapper pinCreateTableMapper;
	
	/* (non-Javadoc)
	 * @see com.aspire.wifi.common.sms.service.SmsReceiver#deliverNotify(com.aspire.wifi.common.sms.entity.SmsReceiveMsg)
	 */
	public void deliverNotify(SmsReceiveMsg message) throws SmsException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		String smsContent = message.getContent();
		if (StringUtil.isNotEmpty(smsContent) && smsContent.length() == (Constants.RANDOM_STRING_LENGTH + Constants.RANDOM_STRING_LENGTH)) {
			String businessCode = smsContent.substring(0, Constants.RANDOM_STRING_LENGTH);
			String verificationCode = smsContent.substring(Constants.RANDOM_STRING_LENGTH,smsContent.length());
			paramMap.put("businessCode", businessCode);			
			paramMap.put("verificationCode", verificationCode.toUpperCase());
			ConsumePlaceEntity consumePleace = null;
			String sendSmsContent = "";
			try {
				List<ConsumePlaceEntity> places = consumePlaceMapper.getCounsumePlace(paramMap);
				PinCreateTableEntity pct = pinCreateTableMapper.getPinZhuoDetailsByVerifyCode(paramMap);
				if (places != null && !places.isEmpty()) {
					// 渠道正确, 检查验证码
					consumePleace = places.get(0);
					paramMap.put("consumePlaceId", consumePleace.getConsumePlaceId());
					if (pct != null) {
						if (pct.getMoSmsTime() != null) {
							// 验证失败-已使用
							String[] para = {pct.getOwnerMobile(), DateUtil.dateToDateString(pct.getMoSmsTime(), DateUtil.yyyy_MM_dd_CN)};
							sendSmsContent = sysConfigService.getSysConfigAsString(SmsConstants.WIFI_SMS_NOTICE_PIN_TABLE_CONSUME_VERIFY_USED, para);

							this.sendSmsMessage(message.getSrcId(), sendSmsContent);
						} else {
							// 验证码通过验证
							paramMap.put("flashSaleId", pct.getFlashSaleId());
							pinCreateTableMapper.updateConsumePlace(paramMap);
							
							String[] para = {pct.getOwnerMobile()};
							sendSmsContent = sysConfigService.getSysConfigAsString(SmsConstants.WIFI_SMS_NOTICE_PIN_TABLE_CONSUME_VERIFY_OK, para);
							
							this.sendSmsMessage(message.getSrcId(), sendSmsContent);
							//给桌主发短信
							SmsSendMsg message2 = new SmsSendMsg();
							message2.setAsynSend(false);
							message2.setCreateTime(new Date());
							message2.setMobile(pct.getOwnerMobile());
							message2.setPriority(DIY09SmsConstants.PRIORITY_MIDDLE);
							message2.setSendStatus(SmsConstants.SEND_STATUS_WAITING);
							message2.setSendTime(new Date());
							message2.setRetryTimes(0);
							
							String smsContent2 = sysConfigService.getSysConfigAsString(SmsConstants.WIFI_SMS_NOTICE_PIN_OWNMOBLIE, new String[]{places.get(0).getConsumePlaceName()});
							message.setContent(smsContent2);
							
						}
					} else {
						// 验证失败, 错误的验证码			
						this.sendSmsMessage(message.getSrcId(), "验证失败, 无效的验证码!");
					}
				} else {
					// 验证失败-渠道错误
					String[] para = {pct.getOwnerMobile()};
					sendSmsContent = sysConfigService.getSysConfigAsString(SmsConstants.WIFI_SMS_NOTICE_PIN_TABLE_CONSUME_VERIFY_ERROR_CHANNEL, para);
					
					this.sendSmsMessage(message.getSrcId(), sendSmsContent);
				}				
			} catch (WifiException e) {
				// 验证失败-系统异常
				this.sendSmsMessage(message.getSrcId(), "验证失败, 系统异常!");
			}
		} else {
			// 消息格式不正确
			this.sendSmsMessage(message.getSrcId(), "验证失败, 错误的验证码	");
		}

	}
	
	public void sendSmsMessage(String mobile, String content) throws SmsException {
		SmsSendMsg message = new SmsSendMsg();
		message.setMobile(mobile);
		message.setContent(content);
		message.setPriority(SmsConstants.SEND_PRIORITY_HIGH);
		message.setSendStatus(SmsConstants.SEND_STATUS_WAITING);
		message.setAsynSend(Boolean.FALSE.booleanValue());
		message.setCreateTime(new Date());

		smsSender.send(message);
	}
public static void main(String[] args) {
	String ss="030001SH92QR";
	System.out.println(ss.substring(0, 6));
	System.out.println(ss.substring(6));
	
}
}
