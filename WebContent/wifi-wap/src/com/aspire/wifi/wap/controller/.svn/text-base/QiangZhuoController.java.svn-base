package com.aspire.wifi.wap.controller;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aspire.wifi.common.sms.constants.DIY09SmsConstants;
import com.aspire.wifi.common.sms.constants.SmsConstants;
import com.aspire.wifi.common.sms.entity.SmsSendMsg;
import com.aspire.wifi.common.sms.service.SmsSender;
import com.aspire.wifi.wap.entity.PinCreateTableDetailEntity;
import com.aspire.wifi.wap.entity.PinCreateTableDetailHistoryEntity;
import com.aspire.wifi.wap.entity.PinCreateTableEntity;
import com.aspire.wifi.wap.entity.PinCreateTableHistoryEntity;
import com.aspire.wifi.wap.entity.UserInfoEntity;
import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.service.intf.PinActionService;
import com.aspire.wifi.wap.service.intf.PinCreateTableDetailHistoryService;
import com.aspire.wifi.wap.service.intf.PinCreateTableHistoryService;
import com.aspire.wifi.wap.service.intf.PinCreateTableService;
import com.aspire.wifi.wap.service.intf.SysConfigService;
import com.aspire.wifi.wap.service.intf.UserInfoService;
import com.aspire.wifi.wap.util.Constants;
import com.aspire.wifi.wap.util.DateUtil;
import com.aspire.wifi.wap.util.GetConfigFile;
import com.aspire.wifi.wap.util.PictureUtil;
import com.aspire.wifi.wap.util.ReadFile;
import com.aspire.wifi.wap.util.StringUtil;
import com.aspire.wifi.wap.util.UploadByFile;
import com.google.gson.Gson;

@Controller
public class QiangZhuoController {
	private static final Logger logger =  LoggerFactory.getLogger(QiangZhuoController.class);
	public static String DEFAULT_PINZHUO_PIC = "default_pinzhuo.jpg";
	
	@Resource(name = "sysConfigService")
	private SysConfigService sysConfigService;
	
	@Resource(name = "pinActionService")
	private PinActionService pinActionService;
	
	@Resource(name = "pinCreateTableService")
	private PinCreateTableService pinCreateTableService;
	
	@Resource(name = "pinCreateTableHistoryService")
    private PinCreateTableHistoryService pinCreateTableHistoryService;
	
    @Resource(name = "pinCreateTableDetailHistoryService")
    private PinCreateTableDetailHistoryService pinCreateTableDetailHistoryService;
    
    @Resource(name = "smsSender")
    private SmsSender smsSender;
    
	@Resource(name = "userInfoService")
	private UserInfoService userInfoService;
	/**
	 * 判断是否是加入拼桌成员


	 * 
	 * @param flashSaleId
	 * @param mobile
	 * @return
	 * @throws WifiException
	 */
	private boolean isJoinedPinZhuo(String flashSaleId, String mobile) throws WifiException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("flashSaleId", flashSaleId);
		paramMap.put("mobile", mobile);
		paramMap.put("isOwnerMobile", Constants.IS_NOT_OWNER_MOBILE);
		
		List<PinCreateTableDetailEntity> listPinZhuoMember = pinActionService.listPinZhuoMember(paramMap);
		Boolean isJoinned = Boolean.FALSE;
		if (listPinZhuoMember != null && !listPinZhuoMember.isEmpty()) {
			isJoinned = Boolean.TRUE;
		}
		return isJoinned.booleanValue();
	}
	
	/**
	 * 是否是桌主


	 * 
	 * @param flashSaleId
	 * @return
	 * @throws WifiException
	 */
	private boolean isPinZhuoOwner(String flashSaleId) throws WifiException {
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("flashSaleId", flashSaleId);
		paramMap.put("mobile", msisdn);
		
		PinCreateTableEntity pinDetails = pinActionService.getPinZhuoDetails(paramMap);
		Boolean isOwner = Boolean.FALSE;
		if (msisdn != null && pinDetails != null && msisdn.equals(pinDetails.getOwnerMobile())) {
			isOwner = Boolean.TRUE;
		}
		return isOwner.booleanValue();
	}
	
	private void sendOpenPinZhuoSMSToMember(String flashSaleId) throws WifiException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("flashSaleId", flashSaleId);
		try {

			PinCreateTableEntity pinDetails = pinActionService.getPinZhuoDetails(paramMap);
			List<PinCreateTableDetailEntity> listPinZhuoMember = pinActionService.listPinZhuoMember(paramMap);
			for (PinCreateTableDetailEntity pctd : listPinZhuoMember) {

				SmsSendMsg message = new SmsSendMsg();
				message.setMobile(pctd.getMobile());

				String content = "";
				if (Constants.IS_OWNER_MOBILE == pctd.getIsOwnerMobile()) {
					String[] para = { DateUtil.getCurCNDate(), pinDetails.getVerificationCode()};
					content = sysConfigService.getSysConfigAsString(SmsConstants.WIFI_SMS_NOTICE_PIN_TABLE_DONE_OWNER_VERIFY_CODE, para);
				} else {
					String[] para = { pinDetails.getNickName() + "(" + pinDetails.getOwnerMobile() + ")"};
					content = sysConfigService.getSysConfigAsString(SmsConstants.WIFI_SMS_NOTICE_PIN_TABLE_DONE, para);
				}
				message.setContent(content);
				message.setPriority(SmsConstants.SEND_PRIORITY_NORMAL);
				message.setSendStatus(SmsConstants.SEND_STATUS_WAITING);
				message.setCreateTime(new Date());
				
				String regDate = GetConfigFile.getInstance().getProperties("regDate");
				//马甲号的注册日期
				String msisdn  = pctd.getMobile();
				UserInfoEntity userInfo  = new UserInfoEntity();
				userInfo.setReg_date(regDate);
				userInfo.setMobile(msisdn);
				//验证是否为马甲号
				if(userInfoService.queryUserInfoByRegDate(userInfo)>0){
					message.setMobile(GetConfigFile.getInstance().getProperties("testMobile"));
				}
				smsSender.send(message);
			}
		} catch (Exception e) {
			throw new WifiException("发送短信失败, 异常信息:", e);
		}
	}
	
	/**
	 * 拼桌列表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/huodong/QiangZhuoList", method = RequestMethod.GET)
	public ModelAndView gotoQiangZhuoListPage() throws Exception {
		ModelAndView view = new ModelAndView("/qiangzhuo/qiangzhuo_list");
		// 取得配置的媒体文件存放路径,供页面使用		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();


		view.addObject("mobile", msisdn);


		return view;
	}

	/**
	 * 拼桌详情页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/huodong/QiangZhuoDetails", method = RequestMethod.GET)
	public ModelAndView gotoQiangZhuoDetailsPage(@RequestParam String flashSaleId) throws Exception {
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("flashSaleId", flashSaleId);
		paramMap.put("mobile", msisdn);
		
		PinCreateTableEntity pinDetails = pinActionService.getPinZhuoDetails(paramMap);
		//更新足迹状态


		if(pinDetails!=null){
			int status = pinDetails.getActStatusId();
			if(status==4||status==8||status==9){
				
				pinCreateTableDetailHistoryService.updateFootStatus(flashSaleId);
			}
		}
		boolean isOwner = this.isPinZhuoOwner(flashSaleId);
		ModelAndView view = new ModelAndView("/index");
		int actStatusId = 0;
		
		if (pinDetails != null) {
			actStatusId = pinDetails.getActStatusId();
		}
		
		boolean isJoinned = this.isJoinedPinZhuo(flashSaleId, msisdn);
		switch (actStatusId) {
		case Constants.ACT_STATUS_PEND_PUBLISH_AUDIT :
			view = new ModelAndView("/qiangzhuo/qiangzhuo_shenhe");
			break;
		case Constants.ACT_STATUS_PUBLISH_AUDIT :
			if (isOwner) {
				view = new ModelAndView("/qiangzhuo/qiangzhuo_guanli");
			} else if (isJoinned) {
				view = new ModelAndView("/qiangzhuo/qiangzhuo_pin");
			} else {
				view = new ModelAndView("/qiangzhuo/qiangzhuo_detail");
			}
			break;
		case Constants.ACT_STATUS_PUBLISH_FAILED :
			view = new ModelAndView("/qiangzhuo/update_creat");
			break;
		case Constants.ACT_STATUS_EXPIRED :
			view = new ModelAndView("/qiangzhuo/qiangzhuo_detail_overdue");
			break;
		case Constants.ACT_STATUS_ORDER : 
			if (isOwner) {
				view = new ModelAndView("/qiangzhuo/qiangzhuo_upload_photo");
			} else {
				view = new ModelAndView("/qiangzhuo/qiangzhuo_pin_ok");
			}
			break;
		case Constants.ACT_STATUS_PENDING_AUDIT :
			view = new ModelAndView("/qiangzhuo/qiangzhuo_shenhe_photo");
			break;
		case Constants.ACT_STATUS_AUDIT_FAILED : 
			if (isOwner) {
				view = new ModelAndView("/qiangzhuo/qiangzhuo_upload_photo");
			} else {
				view = new ModelAndView("/qiangzhuo/qiangzhuo_pin_ok");
			}
			break;
		case Constants.ACT_STATUS_FINISH :
			view = new ModelAndView("/qiangzhuo/qiangzhuo_shenhe_ok");
			break;
		case Constants.ACT_STATUS_SUBSIDY :
			view = new ModelAndView("/qiangzhuo/qiangzhuo_shenhe_ok");
			break;
		case Constants.ACT_STATUS_DISMISS :
			view = new ModelAndView("/qiangzhuo/qiangzhuo_detail_overdue");
			break;
		default :
			view = new ModelAndView("/index");
		} 
		view.addObject("flashSaleId", flashSaleId);
		return view;
	}
	
	/**
	 * 加入拼桌成功页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/huodong/QiangZhuoJoinSuccess", method = RequestMethod.GET)
	public ModelAndView gotoQiangZhuoJoinSuccessPage() throws Exception {
		ModelAndView view = new ModelAndView("/qiangzhuo/qiangzhuo_join_sucess");
		// 取得配置的媒体文件存放路径,供页面使用



		return view;
	}
	
	/**
	 * 开桌成功页面



	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/huodong/OpenPinZhuoSuccess", method = RequestMethod.GET)
	public ModelAndView gotoOpenPinZhuoSuccessPage() throws Exception {
		ModelAndView view = new ModelAndView("/qiangzhuo/qiangzhuo_chengdan");
		// 取得配置的媒体文件存放路径,供页面使用



		return view;
	}
	
	/**
	 * 拼桌信息查询
	 */
	@RequestMapping(value = "/qiangzhuo/queryQiangZhuoList.ajax")
	public @ResponseBody
	Map<String, ? extends Object> queryQiangZhuoList(
			@RequestParam String totalLoadCount,
			@RequestParam String loadRecordCount, 
			@RequestParam String queryParm)
			throws Exception {
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
		
		int intTotalLoadCount = 0;
		if (StringUtil.isNotEmpty(totalLoadCount)) {
			intTotalLoadCount = Integer.valueOf(totalLoadCount);
		}
		
		int intLoadRecordCount = 0;
		if (StringUtil.isNotEmpty(loadRecordCount)) {
			intLoadRecordCount = Integer.valueOf(loadRecordCount);
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mobile", msisdn);
		paramMap.put("queryParm", queryParm);
		paramMap.put("totalRecordCount", intTotalLoadCount * intLoadRecordCount);
		paramMap.put("isOwnerMobile", Constants.IS_NOT_OWNER_MOBILE);

		try {
			logger.debug("进入 拼桌信息查询");
			List<PinCreateTableEntity> pinZhuoList = pinActionService.listActivePinZhuo(paramMap);
			paramMap.put("pinZhuoLimit", (pinActionService.checkActivityLimit(paramMap) ? "TRUE" : "FALSE"));
			paramMap.put("pinZhuoList", pinZhuoList);
			paramMap.put("CODE", "TRUE");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询用户：" + queryParm + "查询拼桌信息异常", e);
			paramMap.put("msg", e.getMessage());
			paramMap.put("CODE", "FALSE");
		}
		return paramMap;
	}
	
	/**
	 * 查询拼桌明细
	 */
	@RequestMapping(value = "/qiangzhuo/getPinZhuoDetails.ajax")
	public @ResponseBody
		Map<String, ? extends Object> getPinZhuoDetails(@RequestParam String flashSaleId) throws Exception {
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("flashSaleId", flashSaleId);
		paramMap.put("isOwnerMobile", Constants.IS_NOT_OWNER_MOBILE);
		try {
			logger.debug("进入 查询拼桌明细");
			PinCreateTableEntity pinCreateTableEntity = pinActionService.getPinZhuoDetails(paramMap);
			
			logger.debug("查询拼桌成员名单");
			List<PinCreateTableDetailEntity> listPinZhuoMember = pinActionService.listPinZhuoMember(paramMap);
			
			paramMap.put("pinCreateTableEntity", pinCreateTableEntity);
			paramMap.put("listPinZhuoMember", listPinZhuoMember);
			paramMap.put("loginMobile", msisdn);
			paramMap.put("CODE", "TRUE");
		} catch (Exception e) {
			logger.error("拼桌单号：" + flashSaleId + "查询拼桌明细异常", e);
			paramMap.put("msg", e.getMessage());
			paramMap.put("CODE", "FALSE");
		}
		return paramMap;
	}
	
	/**
	 * 查询拼桌明细
	 */
	@RequestMapping(value = "/qiangzhuo/joinPinZhuo.ajax")
	public @ResponseBody
		Map<String, ? extends Object> joinPinZhuo(@RequestParam String flashSaleId) throws Exception {
		
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("flashSaleId", new BigInteger(flashSaleId));
		paramMap.put("mobile", msisdn);
		paramMap.put("changeValue", -1);
		paramMap.put("isOwnerMobile", Constants.IS_NOT_OWNER_MOBILE);
		
		if (pinActionService.checkActivityLimit(paramMap)) {
			paramMap.put("msg", "你有未完成的活动或者你这周已经在参加过活动!");
			paramMap.put("CODE", "FALSE");
			return paramMap;
		} else if (this.isPinZhuoOwner(flashSaleId)) {
			paramMap.put("msg", "你已经是桌主不能加入该活动!");
			paramMap.put("CODE", "FALSE");
			return paramMap;
		} else if (this.isJoinedPinZhuo(flashSaleId, msisdn)) {
			paramMap.put("msg", "你已经参加该活动!");
			paramMap.put("CODE", "FALSE");
			return paramMap;
		}
		
		try {
			logger.debug("进入 查询拼桌明细");
			int result = this.pinActionService.joinPinZhuo(paramMap);
			if (result > 0) {
				// 保存历史记录
				PinCreateTableDetailHistoryEntity ptde = new PinCreateTableDetailHistoryEntity();
				ptde.setFlashSaleId(new BigInteger(flashSaleId));
			    ptde.setMobile(msisdn);
			    ptde.setIsOwnerMobile(Constants.IS_NOT_OWNER_MOBILE);
			    ptde.setActionDate(new Date());
			    ptde.setActionType(Constants.PIN_DETAILS_ACTION_TYPE_JOIN);
			    ptde.setActionDesc("加入活动");
			    ptde.setJoinDate(new Date());
			    
			    pinCreateTableDetailHistoryService.addCreateTableDetailToHistory(ptde);
			    
			    paramMap.put("CODE", "TRUE");
			} else {
				paramMap.put("msg", "活动状态已发生变化,不能加入该活动!");
				paramMap.put("CODE", "FALSE");
			}
		} catch (Exception e) {
			logger.error("拼桌单号：" + flashSaleId + "查询拼桌明细异常", e);
			paramMap.put("msg", e.getMessage());
			paramMap.put("CODE", "FALSE");
		}
		return paramMap;
	}

	/**
	 * 删除拼桌成员
	 */
	@RequestMapping(value = "/qiangzhuo/deletePinZhuoMemeber.ajax")
	public @ResponseBody
	Map<String, ? extends Object> deletePinZhuoMemeber(
			@RequestParam String flashSaleId, String mobile,String nicknameAndMobile) throws Exception {
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("flashSaleId", new BigInteger(flashSaleId));
		paramMap.put("mobile", mobile);
		paramMap.put("changeValue", 1);

		try {
			logger.debug("查询拼桌成员名单");
			
			if (!isJoinedPinZhuo(flashSaleId, mobile)) {
				paramMap.put("msg", "该成员状态已经发生变化,不能被删除!");
				paramMap.put("CODE", "FALSE");
				
				return paramMap;
			}
			
			logger.debug("进入 删除拼桌成员");
			int result = this.pinActionService.deletePinZhuoMember(paramMap);		    
		    if (result > 0) {
		    	//发送短信给踢了的那个人
		    	SmsSendMsg message = new SmsSendMsg();
				message.setAsynSend(false);
				message.setCreateTime(new Date());
				message.setMobile(mobile);
				message.setPriority(DIY09SmsConstants.PRIORITY_MIDDLE);
				message.setSendStatus(SmsConstants.SEND_STATUS_WAITING);
				message.setSendTime(new Date());
				message.setRetryTimes(0);
				
				String smsContent = sysConfigService.getSysConfigAsString(SmsConstants.WIFI_SMS_NOTICE_PIN_TIREN, new String[]{nicknameAndMobile});
				message.setContent(smsContent);
				
				smsSender.send(message);//发送短信
				
		    	PinCreateTableDetailEntity pinDetials = null;
				List<PinCreateTableDetailEntity> listPinZhuoMember = pinActionService.listPinZhuoMember(paramMap);
				if (listPinZhuoMember != null && !listPinZhuoMember.isEmpty()) {
					pinDetials = listPinZhuoMember.get(0);
				}
				
				// 保存历史记录
				PinCreateTableDetailHistoryEntity ptde = new PinCreateTableDetailHistoryEntity();
				ptde.setFlashSaleId(new BigInteger(flashSaleId));
			    ptde.setMobile(mobile);
			    ptde.setIsOwnerMobile(Constants.IS_NOT_OWNER_MOBILE);
			    ptde.setActionDate(new Date());
			    ptde.setActionType(Constants.PIN_DETAILS_ACTION_TYPE_DELETE);
			    ptde.setActionDesc("该成员被桌主" + msisdn + "删除");
			    ptde.setJoinDate(pinDetials != null ? pinDetials.getJoinDate() : new Date());
			    
			    pinCreateTableDetailHistoryService.addCreateTableDetailToHistory(ptde);
			    
			    paramMap.put("CODE", "TRUE");
			} else {
				paramMap.put("msg", "活动状态已发生变化,不能加入该活动!");
				paramMap.put("CODE", "FALSE");
			}
		} catch (Exception e) {
			logger.error("拼桌单号：" + flashSaleId + "删除拼桌成员异常", e);
			paramMap.put("msg", e.getMessage());
			paramMap.put("CODE", "FALSE");
		}
		return paramMap;
	}

	/**
	 * 退出拼桌



	 */
	@RequestMapping(value = "/qiangzhuo/existPinZhuo.ajax")
	public @ResponseBody
	Map<String, ? extends Object> existPinZhuo(
			@RequestParam String flashSaleId) throws Exception {
		logger.debug("进入退出拼桌");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		try {
			Subject currentUser = SecurityUtils.getSubject();
			String msisdn = (String)currentUser.getPrincipal();
			
			paramMap.put("flashSaleId", new BigInteger(flashSaleId));
			paramMap.put("mobile", msisdn);
			paramMap.put("changeValue", 1);
		
			int result = this.pinActionService.deletePinZhuoMember(paramMap);
			if (result > 0) {
				PinCreateTableDetailEntity pinDetials = null;
				List<PinCreateTableDetailEntity> listPinZhuoMember = pinActionService.listPinZhuoMember(paramMap);
				if (listPinZhuoMember != null && !listPinZhuoMember.isEmpty()) {
					pinDetials = listPinZhuoMember.get(0);
				}
				
				// 保存历史记录
				PinCreateTableDetailHistoryEntity ptde = new PinCreateTableDetailHistoryEntity();
				ptde.setFlashSaleId(new BigInteger(flashSaleId));
			    ptde.setMobile(msisdn);
			    ptde.setIsOwnerMobile(Constants.IS_NOT_OWNER_MOBILE);
			    ptde.setActionDate(new Date());
			    ptde.setActionType(Constants.PIN_DETAILS_ACTION_TYPE_EXIT);
			    ptde.setActionDesc("退出拼桌");
			    ptde.setJoinDate(pinDetials != null ? pinDetials.getJoinDate() : new Date());
			    
			    pinCreateTableDetailHistoryService.addCreateTableDetailToHistory(ptde);
				paramMap.put("CODE", "TRUE");
			} else {
				paramMap.put("msg", "退出拼桌失败!");
				paramMap.put("CODE", "FALSE");
			}
		} catch (Exception e) {
			logger.error("拼桌单号：" + flashSaleId + "退出拼桌异常", e);
			paramMap.put("msg", e.getMessage());
			paramMap.put("CODE", "FALSE");
		}
		return paramMap;
	}
	
	/**
	 * 开桌
	 */
	@RequestMapping(value = "/qiangzhuo/openPinZhuo.ajax")
	public @ResponseBody
	Map<String, ? extends Object> openPinZhuo(@RequestParam String flashSaleId) throws Exception {
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("flashSaleId", new BigInteger(flashSaleId));
		paramMap.put("ownerMobile", msisdn);
		try {
			logger.debug("进入 查询拼桌明细");
			PinCreateTableEntity pcte = pinActionService.getPinZhuoDetails(paramMap);
			if (pcte == null) {
				paramMap.put("msg", "不能找到活动信息!");
				paramMap.put("CODE", "FALSE");
				
				return paramMap;
			}
			if (pcte.getFreeSeat() > 0) {
				paramMap.put("msg", "再加入" + pcte.getFreeSeat() + "人你就可以开桌了!");
				paramMap.put("CODE", "FALSE");
			} else {
				logger.debug("进入开桌流程");
				String verifyCode = StringUtil.genRandomString(Constants.RANDOM_STRING_LENGTH);
				paramMap.put("verificationCode", verifyCode);
				
				boolean isUsed = pinCreateTableService.checkVerificationCode(paramMap);
				if (isUsed) {
					//verifyCode = StringUtil.genRandomString(Constants.RANDOM_STRING_LENGTH);
					verifyCode = StringUtil.buildRandom(Constants.RANDOM_STRING_LENGTH)+"";
					paramMap.put("verificationCode", verifyCode);
				}
				
				int result = this.pinActionService.activatePingZhuo(paramMap);
				if (result > 0) {
					PinCreateTableHistoryEntity pth = new PinCreateTableHistoryEntity();
					pth.setFlashSaleId(pcte.getFlashSaleId());
				    pth.setActDesc(pcte.getActDesc());
				    pth.setActTypeId(pcte.getActTypeId());
				    pth.setActStatusId(pcte.getActStatusId());
				    pth.setConsumePlaceId(pcte.getConsumePlaceId());
				    pth.setOwnerMobile(pcte.getOwnerMobile());
				    pth.setConsumePic(pcte.getConsumePic());
				    pth.setActionDate(new Date());
				    pth.setActionType(Constants.PIN_ACTION_TYPE_MODIFY);
				    pth.setActionDesc("状态ID跟新为:" + Constants.ACT_STATUS_ORDER);
				    pth.setCreateTableDate(pcte.getCreateTableDate());
				    pth.setExpiredDate(pcte.getExpireDate());
				    pth.setAuditUser(pcte.getAuditUser());
				    pth.setAuditTime(pcte.getAuditTime());
				    pth.setAuditComment(pcte.getAuditComment());
				    pth.setAuditStatus(pcte.getAuditStatus());
				    
				    // 保存开桌历史记录
				    this.pinCreateTableHistoryService.addCreateTableToHistory(pth);
				    
				    // 发送短信给拼桌成员
				    this.sendOpenPinZhuoSMSToMember(flashSaleId);
				    
					paramMap.put("CODE", "TRUE");
				} else {
					paramMap.put("msg", "开桌失败!");
					paramMap.put("CODE", "FALSE");
				}				
			}

		} catch (Exception e) {
			logger.error("拼桌单号：" + flashSaleId + "开桌异常", e);
			paramMap.put("msg", e.getMessage());
			paramMap.put("CODE", "FALSE");
		}
		return paramMap;
	}
	
	/**
	 * 卓主解散拼桌
	 * 
	 * @param flashSaleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/qiangzhuo/dismissPinZhuo.ajax")
	public @ResponseBody
	Map<String, ? extends Object> dismissPinZhuo(@RequestParam String flashSaleId) throws Exception {
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("flashSaleId", new BigInteger(flashSaleId));
		paramMap.put("ownerMobile", msisdn);
		try {
			logger.debug("进入 查询拼桌明细");
			PinCreateTableEntity pcte = pinActionService.getPinZhuoDetails(paramMap);
			if (pcte == null) {
				paramMap.put("msg", "不能找到活动信息!");
				paramMap.put("CODE", "FALSE");
				
				return paramMap;
			}
			if (pcte.getActStatusId() != Constants.ACT_STATUS_PUBLISH_AUDIT) {
				paramMap.put("msg", "拼桌状态已经改变, 不能解散拼桌!");
				paramMap.put("CODE", "FALSE");
			} else {
				logger.debug("进入解散拼桌流程");			
				int result = this.pinActionService.dismissPingZhuo(paramMap);
				if (result > 0) {
					//发送短信给拼桌的人员
					paramMap.put("isOwnerMobile", Constants.IS_NOT_OWNER_MOBILE);
					logger.debug("查询拼桌成员名单");
					List<PinCreateTableDetailEntity> listPinZhuoMember = pinActionService.listPinZhuoMember(paramMap);
					for(PinCreateTableDetailEntity pt:listPinZhuoMember){
						String mobile = pt.getMobile();
						UserInfoEntity ownnickname = userInfoService.queryUserInfo(msisdn);
						SmsSendMsg message = new SmsSendMsg();
						message.setAsynSend(false);
						message.setCreateTime(new Date());
						message.setMobile(mobile);
						message.setPriority(DIY09SmsConstants.PRIORITY_MIDDLE);
						message.setSendStatus(SmsConstants.SEND_STATUS_WAITING);
						message.setSendTime(new Date());
						message.setRetryTimes(0);
						
						String smsContent = sysConfigService.getSysConfigAsString(SmsConstants.WIFI_SMS_NOTICE_PIN_JIESAN, new String[]{ownnickname.getNickname()+msisdn});
						message.setContent(smsContent);
						
						smsSender.send(message);//发送短信
					}
					PinCreateTableHistoryEntity pth = new PinCreateTableHistoryEntity();
					pth.setFlashSaleId(pcte.getFlashSaleId());
				    pth.setActDesc(pcte.getActDesc());
				    pth.setActTypeId(pcte.getActTypeId());
				    pth.setActStatusId(pcte.getActStatusId());
				    pth.setConsumePlaceId(pcte.getConsumePlaceId());
				    pth.setOwnerMobile(pcte.getOwnerMobile());
				    pth.setConsumePic(pcte.getConsumePic());
				    pth.setActionDate(new Date());
				    pth.setActionType(Constants.PIN_ACTION_TYPE_MODIFY);
				    pth.setActionDesc("状态ID跟新为:" + Constants.ACT_STATUS_DISMISS);
				    pth.setCreateTableDate(pcte.getCreateTableDate());
				    pth.setExpiredDate(pcte.getExpireDate());
				    pth.setAuditUser(pcte.getAuditUser());
				    pth.setAuditTime(pcte.getAuditTime());
				    pth.setAuditComment(pcte.getAuditComment());
				    pth.setAuditStatus(pcte.getAuditStatus());
				    
				    // 保存拼桌历史记录
				    this.pinCreateTableHistoryService.addCreateTableToHistory(pth);
					paramMap.put("CODE", "TRUE");
				} else {
					paramMap.put("msg", "解散拼桌失败!");
					paramMap.put("CODE", "FALSE");
				}				
			}

		} catch (Exception e) {
			logger.error("拼桌单号：" + flashSaleId + "解散拼桌异常", e);
			paramMap.put("msg", e.getMessage());
			paramMap.put("CODE", "FALSE");
		}
		return paramMap;
	}
	
	/**
	 * 提交照片审核
	 */
	@RequestMapping(value = "/qiangzhuo/photoAudit.ajax")
	public @ResponseBody
	Map<String, ? extends Object> photoAudit(@RequestParam String flashSaleId) throws Exception {	
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("flashSaleId", new BigInteger(flashSaleId));
		
		logger.debug("进入 查询拼桌明细");
		PinCreateTableEntity pcte = pinActionService.getPinZhuoDetails(paramMap);
		if (pcte == null) {
			paramMap.put("msg", "不能找到活动信息!");
			paramMap.put("CODE", "FALSE");
			
			return paramMap;
		}
		
		try {
			logger.debug("进入照片审核流程");
			int result = pinActionService.auditPinZhuoImg(paramMap);
			if (result > 0) {
				PinCreateTableHistoryEntity pth = new PinCreateTableHistoryEntity();
				pth.setFlashSaleId(pcte.getFlashSaleId());
			    pth.setActDesc(pcte.getActDesc());
			    pth.setActTypeId(pcte.getActTypeId());
			    pth.setActStatusId(pcte.getActStatusId());
			    pth.setConsumePlaceId(pcte.getConsumePlaceId());
			    pth.setOwnerMobile(pcte.getOwnerMobile());
			    pth.setConsumePic(pcte.getConsumePic());
			    pth.setActionDate(new Date());
			    pth.setActionType(Constants.PIN_ACTION_TYPE_MODIFY);
			    pth.setActionDesc("提交照片审核, 状态ID跟新为:" + Constants.ACT_STATUS_ORDER);
			    pth.setCreateTableDate(pcte.getCreateTableDate());
			    pth.setExpiredDate(pcte.getExpireDate());
			    pth.setAuditUser(pcte.getAuditUser());
			    pth.setAuditTime(pcte.getAuditTime());
			    pth.setAuditComment(pcte.getAuditComment());
			    pth.setAuditStatus(pcte.getAuditStatus());
			    
			    this.pinCreateTableHistoryService.addCreateTableToHistory(pth);
			    
				paramMap.put("CODE", "TRUE");
			} else {
				paramMap.put("msg", "提交审核失败!");
				paramMap.put("CODE", "FALSE");
			}

		} catch (Exception e) {
			logger.error("拼桌单号：" + flashSaleId + "照片审核异常", e);
			paramMap.put("msg", e.getMessage());
			paramMap.put("CODE", "FALSE");
		}
		return paramMap;
	}
	
	/**
	 * 上传图片接入口




	 * @return
	 */
	@RequestMapping(value = "/qiangzhuo/uploadQianZhuoImage", method = RequestMethod.POST)
	public void imageUpload(@RequestParam("fileBox") CommonsMultipartFile commonsMultipartFile,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Gson gson = new Gson();
		String flashSaleId = request.getParameter("flashSaleId");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("flashSaleId", new BigInteger(flashSaleId));

		PinCreateTableEntity pct = null;
		try {
			pct = pinActionService.getPinZhuoDetails(paramMap);
			if (pct != null) {
				if (!(Constants.ACT_STATUS_ORDER == pct.getActStatusId() || Constants.ACT_STATUS_AUDIT_FAILED == pct.getActStatusId())) {
					resultMap.put("msg", "活动状态已经变更, 不允许再上传图片!");
					resultMap.put("CODE", "TRUE");
					outPut(response, gson.toJson(resultMap));
					return;
				}
			}
		} catch (Exception e) {
			logger.error("查询活动：" + flashSaleId + "信息异常", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
			outPut(response, gson.toJson(resultMap));
			return;
		}

		// 获取原文件名
		String yFileName = commonsMultipartFile.getOriginalFilename();
		String lowerfn = yFileName.toLowerCase();
		if (!lowerfn.endsWith(".jpg") && !lowerfn.endsWith(".png")
				&& !lowerfn.endsWith(".jpeg") && !lowerfn.endsWith(".gif")) {
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", "格式只支持jpg、png、jpeg、gif");
			outPut(response, gson.toJson(resultMap));
			return;
		}
		// 上传文件
		if (commonsMultipartFile.isEmpty()) {
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", "上传图片为空!");
			outPut(response, gson.toJson(resultMap));
			return;
		}
		// 获取文件大小(单位byte)
		long fileSize = commonsMultipartFile.getSize();
		long uploadFileSizeMax = 0;// 单位K
		String uploadFileSizeMaxStr = "1048576";
		try {
			uploadFileSizeMaxStr = GetConfigFile.getInstance().getProperties("maxUploadPicSize");
		} catch (Exception e1) {
			logger.warn("获取配置文件错误, 异常: ", e1);
		}
		;

		if (uploadFileSizeMaxStr != null || !"".equals(uploadFileSizeMaxStr)) {
			uploadFileSizeMax = Long.parseLong(uploadFileSizeMaxStr);
		} else {
			uploadFileSizeMax = 1048576;
		}
		if (fileSize >= uploadFileSizeMax) {
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", "文件大小不能超过" + uploadFileSizeMax + "byte!");
			outPut(response, gson.toJson(resultMap));
			return;
		}

		// 获取新文件名
		String fileName = UploadByFile.restFileName(yFileName);
		if (!"".equals(fileName)) {
			// 写student_report数据库表
			try {
				int index = yFileName.lastIndexOf('.');
				String postFix = yFileName.substring(index+1);
				
				pct.setConsumePic(PictureUtil.compressPicByByte(IOUtils.toByteArray(commonsMultipartFile.getInputStream()),postFix));
				int result = pinActionService.uploadPinZhuoImg(pct);

				if (result > 0) {
					PinCreateTableHistoryEntity pth = new PinCreateTableHistoryEntity();
					pth.setFlashSaleId(pct.getFlashSaleId());
					pth.setActDesc(pct.getActDesc());
					pth.setActTypeId(pct.getActTypeId());
					pth.setActStatusId(pct.getActStatusId());
					pth.setConsumePlaceId(pct.getConsumePlaceId());
					pth.setOwnerMobile(pct.getOwnerMobile());
					pth.setConsumePic(pct.getConsumePic());
					pth.setActionDate(new Date());
					pth.setActionType(Constants.PIN_ACTION_TYPE_MODIFY);
					pth.setActionDesc("更新照片");
					pth.setCreateTableDate(pct.getCreateTableDate());
					pth.setExpiredDate(pct.getExpireDate());
					pth.setAuditUser(pct.getAuditUser());
					pth.setAuditTime(pct.getAuditTime());
					pth.setAuditComment(pct.getAuditComment());
					pth.setAuditStatus(pct.getAuditStatus());

					this.pinCreateTableHistoryService.addCreateTableToHistory(pth);
				}
			} catch (Exception e) {
				logger.warn("上传图片文件错误, 异常: ", e);
				
				resultMap.put("msg", "上传图片失败!");
				resultMap.put("fileName", fileName);
				resultMap.put("CODE", "TRUE");
				outPut(response, gson.toJson(resultMap));
				return;
			}
			resultMap.put("msg", "上传图片成功!");
			resultMap.put("fileName", fileName);
			resultMap.put("CODE", "TRUE");
			outPut(response, gson.toJson(resultMap));
			return;
		} else {
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", "上传图片失败!");
			outPut(response, gson.toJson(resultMap));
			return;
		}
	}
	
	@RequestMapping(value = "/qiangzhuo/getQianZhuoImage")
    public void getQianZhuoImage(HttpServletRequest request, HttpServletResponse response) {
		String flashSaleId = request.getParameter("flashSaleId");
		OutputStream out = null;
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
    		paramMap.put("flashSaleId", new BigInteger(flashSaleId));
    		
            PinCreateTableEntity pct = pinActionService.getPinZhuoDetails(paramMap);
            out = response.getOutputStream();
            byte[] picBytes = null;
            if(pct != null){
            	picBytes = pct.getConsumePic();
            	if(picBytes == null || picBytes.length == 0){
            		String app_path = request.getSession().getServletContext().getRealPath("/");
                	String defaultPinZhuo = app_path + File.separator + "resources" + File.separator + "images" + File.separator + DEFAULT_PINZHUO_PIC;
                	File dy = new File(defaultPinZhuo);
                	picBytes = ReadFile.getBytesFromFile(dy);
            	}
            }else{
            	String app_path = request.getSession().getServletContext().getRealPath("/");
            	String defaultPinZhuo = app_path + File.separator + "resources" + File.separator + "images" + File.separator + DEFAULT_PINZHUO_PIC;
            	File dy = new File(defaultPinZhuo);
            	picBytes = ReadFile.getBytesFromFile(dy);
            }
            IOUtils.write(picBytes, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	
	/**
     * 响应方法
     * @param response
     * @param param
     */
    public void outPut(HttpServletResponse response, String param) {
        response.setContentType("text/html; charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            out.print(param);
            out.flush();
            out.close();
        }catch (IOException e) {
        	e.printStackTrace();
        }
    }
 
    
    /**
	 * 光荣榜



	 * @return
	 */
	@RequestMapping(value = "/pinDanList")
	public @ResponseBody
	Map<String, ? extends Object> pinDanList(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<PinCreateTableDetailEntity> pindanlist=null;
		try{
			pindanlist=pinActionService.pinDanList();
			if(pindanlist!=null){
				resultMap.put("pindanlist", pindanlist);
				resultMap.put("CODE", "TRUE");
			}
		}catch(Exception e){
			logger.error("获取拼单桌的信息异常", e);
            resultMap.put("msg", e.getMessage());
            resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}
    
}
