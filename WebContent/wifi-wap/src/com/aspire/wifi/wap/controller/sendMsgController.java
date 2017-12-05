package com.aspire.wifi.wap.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aspire.wifi.common.sms.constants.DIY09SmsConstants;
import com.aspire.wifi.common.sms.constants.SmsConstants;
import com.aspire.wifi.common.sms.entity.SmsSendMsg;
import com.aspire.wifi.common.sms.service.SmsSender;
import com.aspire.wifi.wap.entity.UserInfoEntity;
import com.aspire.wifi.wap.mapper.UserInfoMapper;
import com.aspire.wifi.wap.service.intf.SysConfigService;
import com.aspire.wifi.wap.util.StringUtil;
@Controller
public class sendMsgController {
	
	protected static Logger share_log =  LoggerFactory.getLogger("share_log");
	@Resource(name = "smsSender")
	private SmsSender smsSender;
	
	
	@Resource(name = "sysConfigService")
	private SysConfigService sysConfigService;
	
	@Resource(name = "userInfoMapper")
	private UserInfoMapper userInfoMapper;
	/**
	 * 发送指定号码信息
	 * @param userInfoEntity
	 * @return
	 * @author wuh 2014-08-12
	 */
	@RequestMapping(value = "/sendMobileMsg", method = RequestMethod.POST)
	public @ResponseBody Map<String, ?> sendMobileMsg(HttpServletRequest request, @RequestParam(value = "mobileList", required = true) String mobileList,@RequestParam(value = "tempCode", required = true) String tempCode) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String[] picNums = mobileList.substring(0,mobileList.length()).split(",");  	
			String tempCode3 = request.getParameter("tempCode2");
			String tempCode4 = request.getParameter("tempCode4");
			int ll=0;
			if(!"".equals(tempCode4)){
				ll=tempCode4.length();
			}
			String[] tempCode5=new String[ll];
			if(!"".equals(tempCode4)){
				 tempCode5 = tempCode4.substring(0,tempCode4.length()).split(",");  
			}
		try {
				for(int i=0;i<picNums.length;i++){
					String mobile=picNums[i];
					SmsSendMsg message = new SmsSendMsg();
					message.setAsynSend(false);
					message.setCreateTime(new Date());
					if(picNums[i].contains("\n")){
						mobile=picNums[i].substring(picNums[i].length()-11,picNums[i].length());
					}
					message.setMobile(mobile);
					message.setPriority(DIY09SmsConstants.PRIORITY_MIDDLE);
					message.setSendStatus(SmsConstants.SEND_STATUS_WAITING);
					message.setSendTime(new Date());
					message.setRetryTimes(0);
					String code  = StringUtil.genRandomString(6);
					String smsContent="";
					if("2".equals(tempCode3)){

						if("".equals(tempCode4)){
							if((i+1)<10){
								code = "00"+(i+1);
							}else if((i+1)>=10&&(i+1)<100){
								code ="0"+(i+1);
							}else{
								code = (i+1)+"";
							}
						}else{
							code=tempCode5[i];
						}
					
				//发送试用品短信模板
						 smsContent = sysConfigService.getSysConfigAsString(SmsConstants.WIFI_SMS_NOTICE_MAN_MOBLIE, new String[]{code});
					}else{
					//发送抢票模板	
						if("1".equals(tempCode)){
							 smsContent = sysConfigService.getSysConfigAsString(SmsConstants.WIFI_SMS_NOTICE_QIANG_TICKET, new String[]{code});
						}else{
							 smsContent = sysConfigService.getSysConfigAsString(SmsConstants.WIFI_SMS_NOTICE_QIANG_TICKET2, new String[]{code});
		
						}
					}			
				message.setContent(smsContent);
				
			smsSender.send(message);//发送短信
				share_log.info("成功发送短信成功，发送的编码是："+code+"\t手机号码是："+picNums[i]+"发送的信息内容是："+message);
				}
			resultMap.put("CODE", "TRUE");
		} catch (Exception e) {
			e.printStackTrace();
			share_log.info("人工群发短信失败!",e.getMessage());
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}
	

	
	
	/**
	 * 人工发送短信页面
	 * **/
	
	@RequestMapping(value = "/getSendMsg", method = RequestMethod.GET)
	public ModelAndView getSendMsg() throws Exception {
		ModelAndView view = new ModelAndView("/sendMsg");
		return view;
	}
	/**
	 * 人工发送短信页面
	 * **/
	
	@RequestMapping(value = "/a", method = RequestMethod.GET)
	public ModelAndView getupdatae() throws Exception {
		//ModelAndView view = new ModelAndView("/update");
	//	ModelAndView view = new ModelAndView("/jianianhua/youxi");
		ModelAndView view = new ModelAndView("/jianianhua/index22");
		return view;
	}
	
	@RequestMapping(value = "/s", method = RequestMethod.POST)
	public @ResponseBody Map<String, ?> s() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			List<UserInfoEntity> list =userInfoMapper.nickname();
			List<String> list2 = new ArrayList<String>();
//			for(UserInfoEntity u:list){
//				if(u.getNickname().length()==11){
//					UserInfoEntity uu = new UserInfoEntity();
//					String tempnickname = u.getNickname().substring(0,3)+"****"+u.getNickname().substring(7);
//					uu.setNickname(tempnickname);
//					uu.setMobile(u.getMobile());
//					userInfoMapper.updateUserInfo(uu);
//					list2.add("1");
//				}
//			}
			resultMap.put("count",list2.size());
			resultMap.put("CODE", "TRUE");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
		
	}
	
	public static void main(String[] args) {
		
		int level = new Random().nextInt(2);
		System.out.println(level);
	}
	
}
