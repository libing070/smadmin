package com.aspire.wifi.wap.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.aspire.wifi.wap.entity.HeadlineReplyAttachEntity;
import com.aspire.wifi.wap.entity.HeadlineReplyEntity;
import com.aspire.wifi.wap.entity.PinActivityEntity;
import com.aspire.wifi.wap.entity.ShiYongEntity;
import com.aspire.wifi.wap.mapper.HeadlineReplyMapper;
import com.aspire.wifi.wap.mapper.ShiyongMapper;
import com.aspire.wifi.wap.service.intf.PinActivityService;
import com.aspire.wifi.wap.service.intf.ShiyongService;
import com.aspire.wifi.wap.util.StringUtil;
import com.aspire.wifi.wap.service.intf.SysConfigService;
import com.aspire.wifi.common.sms.constants.DIY09SmsConstants;
import com.aspire.wifi.common.sms.constants.SmsConstants;
import com.aspire.wifi.common.sms.entity.SmsSendMsg;
import com.aspire.wifi.common.sms.service.SmsSender;
@Controller
public class JuYouHuiDetailController {
	protected static Logger logger = LoggerFactory.getLogger(JuYouHuiDetailController.class);
	protected static Logger share_log =  LoggerFactory.getLogger("share_log");
	@Resource(name = "pinActivityService")
	private PinActivityService pinActivityService;
	@Resource(name = "shiyongService")
	private ShiyongService shiyongService;
	@Resource(name = "shiyongMapper")
	private ShiyongMapper shiyongMapper;
	
	@Resource(name = "headlineReplyMapper")
	private HeadlineReplyMapper headlineReplyMapper;
	@Resource(name = "sysConfigService")
	private SysConfigService sysConfigService;
	@Resource(name = "smsSender")
	private SmsSender smsSender;
	
	/**
	 * 使用页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/judetailContr", method = RequestMethod.GET)
	public ModelAndView judetailContr(HttpServletRequest request) throws Exception {
		   String activityId = request.getParameter("activityId");
		ModelAndView view = new ModelAndView("/juyouhui/ju_detail");
		try {
			PinActivityEntity pinActivityEntity=pinActivityService.getAcitivityInfoByActivityId(BigInteger.valueOf(Integer.parseInt(activityId)));
			List<ShiYongEntity> listshiyong=shiyongService.queryshiyongDefine20(BigInteger.valueOf(Integer.parseInt(activityId)));
			view.addObject("pinActivityEntity",pinActivityEntity);
			view.addObject("definenum",listshiyong.size());
		} catch (Exception e) {
			logger.error("查询活动ID失败!",e);
		}
		return view;
	}
	
	/**
	 * 领取
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/juyouhuiGet", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> juyouhuiGet(HttpServletRequest request,
			@RequestParam(value="mobile",required = true)String mobile,
			@RequestParam(value="activityId",required = true)String activityId
	) throws Exception {
		logger.debug("开始领取");
        logger.debug("传入参数 mobile={}", mobile);
        logger.debug("传入参数 activityId={}", activityId);
		 Map<String, Object> returnMap = new HashMap<String, Object>();
			Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
		
			ShiYongEntity shiyong=new ShiYongEntity();
			shiyong.setOwnerMobile(mobile);
			shiyong.setActivityId(Long.parseLong(activityId));
			String customCode=StringUtil.genRandomString(6);
			shiyong.setCustomCode(customCode);
			 shiyongService.insertShiYongction(shiyong);
			 BigInteger big = new BigInteger(activityId.toString());
			 pinActivityService.updatePinActivity(big);
			 paramMap.put("ownerMobile", mobile);
			 paramMap.put("activityId", activityId);
			 Map<String, Object> sy=shiyongService.queryCustomCodeByMobileAndActivityId(paramMap);
			 returnMap.put("shiyong",sy);
			 returnMap.put("CODE","TRUE");
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("CODE","FALSE");
			logger.error("领取异常!",e);
		}
		return returnMap;
	}
	
	
	/**
	 * 
	 * 
	 * **/
	
	/**
	 * 成功页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/youhuiSuccessContr", method = RequestMethod.GET)
	public ModelAndView youhuiSuccessContr(HttpServletRequest request) throws Exception {
		   String ref="";
		   //获取上一级访问的路径
		   ref=request.getHeader("referer")==null?"":request.getHeader("referer");
		   String activityId = request.getParameter("activityId");
		   String mobile = request.getParameter("mobile");
		   String customCode = request.getParameter("customCode");
		ModelAndView view = new ModelAndView("/juyouhui/ju_success");
		view.addObject("ref",ref);
		try {
			PinActivityEntity pinActivityEntity=pinActivityService.getAcitivityInfoByActivityId(BigInteger.valueOf(Integer.parseInt(activityId)));
			view.addObject("pinActivityEntity",pinActivityEntity);
			view.addObject("activityId",activityId);
			view.addObject("mobile",mobile);
			view.addObject("customCode",customCode);
			//发短信给用户
			SmsSendMsg message = new SmsSendMsg();
			message.setAsynSend(false);
			message.setCreateTime(new Date());
			message.setMobile(mobile);
			message.setPriority(DIY09SmsConstants.PRIORITY_MIDDLE);
			message.setSendStatus(SmsConstants.SEND_STATUS_WAITING);
			message.setSendTime(new Date());
			message.setRetryTimes(0);
			String code="";
			if(!customCode.equals("")){
				code=customCode;
			}
			String smsContent="";
			/*if(Integer.parseInt(activityId)==2){
				 smsContent = sysConfigService.getSysConfigAsString(SmsConstants.WIFI_SMS_NOTICE_YOUHUI_2, new String[]{code});
			}*/
			if(Integer.parseInt(activityId)==3){
				 smsContent = sysConfigService.getSysConfigAsString(SmsConstants.WIFI_SMS_NOTICE_YOUHUI_3, new String[]{code});
			}
			if(Integer.parseInt(activityId)==4){
				 smsContent = sysConfigService.getSysConfigAsString(SmsConstants.WIFI_SMS_NOTICE_YOUHUI_4, new String[]{code});
			}
			if(Integer.parseInt(activityId)==5){
				 smsContent = sysConfigService.getSysConfigAsString(SmsConstants.WIFI_SMS_NOTICE_YOUHUI_5, new String[]{code});
			}
			
			if(Integer.parseInt(activityId)==6){
				smsContent = sysConfigService.getSysConfigAsString(SmsConstants.WIFI_SMS_NOTICE_YOUHUI_6, new String[]{code});
			}
			
			message.setContent(smsContent);
			smsSender.send(message);//发送短信
			share_log.info("成功发送短信成功，发送的消费码是："+code+"\t手机号码是："+mobile+"发送的信息内容是："+message);
		} catch (Exception e) {
			logger.error("查询活动ID失败!",e);
		}
		return view;
	}
	/**
	 * 地图
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/youhuiMapContr", method = RequestMethod.GET)
	public ModelAndView youhuiMapContr(HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("/juyouhui/map");
		try {
		} catch (Exception e) {
			logger.error("地图加载失败失败!",e);
		}
		return view;
	}
	
	/**
	 * 优惠活动详细页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/youhuiDetail", method = RequestMethod.GET)
	public ModelAndView youhuiDetail(HttpServletRequest request) throws Exception {
		   String id = request.getParameter("id");
		   String activityId = request.getParameter("activityId")==null?"0":request.getParameter("activityId");
		   ModelAndView view = new ModelAndView("/juyouhui/ju_success_02");
		if(Integer.parseInt(activityId.toString())==8){
			//嘉年华活动
			//	  view = new ModelAndView("/jianianhua/youxi");
		}else{
			String ref=request.getHeader("referer")==null?"":request.getHeader("referer");
			view.addObject("ref",ref);
		}
		
		try {
			ShiYongEntity shiYongEntity=shiyongMapper.queryHuodongById(id);
			view.addObject("shiYongEntity",shiYongEntity);
		} catch (Exception e) {
			logger.error("查询活动ID失败!",e);
		}
		return view;
	}
	
	/**
	 * 查询优惠活动的评论
	 * 
	 * **/
	
	@RequestMapping(value = "/youhuiPinLun", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> youhuiPinLun(HttpServletRequest request,@RequestParam(value="activityId",required = true)String activityId,@RequestParam(value="begin",required = true)int begin,@RequestParam(value="times",required = true)int times
	) throws Exception {
		 Map<String, Object> returnMap = new HashMap<String, Object>();
		 Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			paramMap.put("begin", begin);
			paramMap.put("times", times);
			if(activityId.equals("2")){
				paramMap.put("replyType", 3);
				
			}else{
				paramMap.put("replyType", 4);
				paramMap.put("parentReplyId",activityId);
			}
			int count =  headlineReplyMapper.queryReplyByReplyType(paramMap).size();
			int totalpage=count%times==0?count/times:count/times+1;
			paramMap.put("flag","0");
	        List<HeadlineReplyEntity> headlineReplyEntity = headlineReplyMapper.queryReplyByReplyType(paramMap);
	        returnMap.put("count",count);
	        returnMap.put("totalpage",totalpage);
	        returnMap.put("headlineReplyEntitylist",headlineReplyEntity);
			 returnMap.put("CODE","TRUE");
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("CODE","FALSE");
			logger.error("领取异常!",e);
		}
		return returnMap;
	}
	
	@RequestMapping(value = "/checkxiaofeimaController", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> checkxiaofeimaController(HttpServletRequest request,
			@RequestParam(value="xfms",required = true)String xfms,
			@RequestParam(value="account",required = true)String account
	) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List list=new ArrayList();
		List list1=new ArrayList();
		List list2=new ArrayList();
		int success=0,fail=0,cg=0;
		try {
			if(!account.equals("")&&!xfms.equals("")){
				String xfm=xfms.replaceAll(" ", "");
				//珍可儿面膜使用
				if(account.equals("030009")){
					paramMap.put("id", 2);
				}
				//黄记煌三支焖锅
				if(account.equals("010009")){
					paramMap.put("id", 3);
				}
				//玉林串串香火锅
				if(account.equals("010004")){
					paramMap.put("id", 4);
				}
				//昌平保利电影院
				if(account.equals("030002")){
					paramMap.put("id", 5);
				}
				//丁丁洋回转自助火锅
				if(account.equals("010008")){
					paramMap.put("id", 6);
				}
				//测试
				if(account.equals("ceshi")){
					paramMap.put("id", 7);
				}
				if(account.equals("010010")){
					paramMap.put("id", 9);
				}
					paramMap.put("xfm", xfm);
					ShiYongEntity shiyong=shiyongService.queryshiyongexists(paramMap);
			    	if(shiyong==null){
			    		fail++;
			    		returnMap.put("list",xfm);//不存在的消费码
			    	}else{
			    		ShiYongEntity shiyonguse=shiyong;
			    		if(shiyonguse.getStatusCode().equals("1")){
			    			returnMap.put("list1",shiyonguse.getCustomCode());	//已经使用过得消费码
			    		}else{
			    			pinActivityService.procUpdatePinActivity(paramMap);
			    			if(!paramMap.get("customcode").equals(""))
			    			returnMap.put("list2",paramMap.get("customcode"));//验证成功的消费码
			    		}
			    	}
				
				returnMap.put("CODE","TRUE");
				returnMap.put("fail",fail);
			}else{
				returnMap.put("CODE","FALSE");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("CODE","FALSE");
		}
		return returnMap;
	}
}
