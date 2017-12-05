package com.aspire.wifi.wap.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
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
import com.aspire.wifi.wap.cache.LocalCache;
import com.aspire.wifi.wap.entity.MobileReplyReviewEntity;
import com.aspire.wifi.wap.entity.PinActivityEntity;
import com.aspire.wifi.wap.entity.ShiroUser;
import com.aspire.wifi.wap.entity.UserInfoEntity;
import com.aspire.wifi.wap.entity.ZhuanFaEntity;
import com.aspire.wifi.wap.mapper.UserInfoMapper;
import com.aspire.wifi.wap.mapper.ZhuanFaEntityMapper;
import com.aspire.wifi.wap.service.intf.MobileReplyReviewService;
import com.aspire.wifi.wap.service.intf.PinActivityService;
import com.aspire.wifi.wap.service.intf.SysConfigService;
import com.aspire.wifi.wap.service.intf.UserInfoService;
import com.aspire.wifi.wap.util.DateUtil;
import com.aspire.wifi.wap.util.GetConfigFile;
import com.aspire.wifi.wap.util.SendMsg;
import com.aspire.wifi.wap.util.StringTransTool;
import com.aspire.wifi.wap.util.StringUtil;
import com.aspire.wifi.wap.util.TimeUtil;
import com.aspire.wifi.wap.util.ValidateCode;

@Controller
@Scope("prototype")
public class LoginController {
	protected static Logger logger = LoggerFactory.getLogger(LoginController.class);
	protected static Logger login_log =  LoggerFactory.getLogger("login_log");
	public static String PATTERN_DATE_4 = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 保存动态密码的Session key
	 */
	public static final String SESSION_KEY_USER_INFO = "wifi_user";
	/**
	 * 发送动态密码的频率(秒)
	 */
	private static int passwordSendExpired = 60;
	
	@Resource(name = "userInfoService")
	private UserInfoService userInfoService;
	
	
	@Resource(name = "pinActivityService")
	private PinActivityService pinActivityService;
	
	@Resource(name = "smsSender")
	private SmsSender smsSender;
	
	
	@Resource(name = "sysConfigService")
	private SysConfigService sysConfigService;
	
	@Resource(name = "mobileReplyReviewService")
	private MobileReplyReviewService mobileReplyReviewService;
	@Resource(name = "userInfoMapper")
	private UserInfoMapper userInfoMapper;
	@Resource(name = "zhuanFaEntityMapper")
	private ZhuanFaEntityMapper zhuanFaEntityMapper;
	/**
	 * 首页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView indexPage(HttpServletRequest request) throws Exception {
		// 取得配置的媒体文件存放路径,供页面使用		ModelAndView view = new ModelAndView("/index");
		try{

		view.addObject("videoPath", GetConfigFile.getInstance().getProperties("videoAccessPath"));
		view.addObject("imagePath", GetConfigFile.getInstance().getProperties("imageAccessPath"));
		request.setAttribute("index", request.getParameter("index"));

	/****转发判断开始****/
		/**帮助方开始***/
			String iszhuanFa = request.getSession().getAttribute("iszhuanFa")==null?"":request.getSession().getAttribute("iszhuanFa").toString();
			String onlyzhuanfaId = request.getSession().getAttribute("onlyzhuanfaId")==null?"":request.getSession().getAttribute("onlyzhuanfaId").toString();
			String onlyflag = request.getSession().getAttribute("onlyflag")==null?"":request.getSession().getAttribute("onlyflag").toString();
			if(!onlyzhuanfaId.equals("")&&onlyflag.equals("")){
				Subject currentUser = SecurityUtils.getSubject();
				Map<String,Object> paramMap = new HashMap<String,Object>();

				ModelAndView view2 = new ModelAndView("/zhuanfa/zhuanfa");
				String mobile = currentUser.getPrincipal().toString();
				int onlyIds = userInfoMapper.queryUserInfo(mobile).getOnlyId();
				String nickname = userInfoMapper.queryUserInfo(mobile).getNickname();
				view2.addObject("nickname",nickname);
				request.getSession().setAttribute("onlyzhuanfaId2",onlyzhuanfaId);
//				request.getSession().setAttribute("onlyzhuanfaId", "");
//				request.getSession().setAttribute("onlyflag", "");
				paramMap.put("zhuanFaId",onlyzhuanfaId);
	//			List<ZhuanFaEntity> ZhuanFaEntitylist2 = zhuanFaEntityMapper.queryZhuanFaByLimit(paramMap);
				paramMap.put("zhuanFaId","");
				paramMap.put("mobile",mobile);
				paramMap.put("isCreater", "0");//0表示转发者	,	
				List<ZhuanFaEntity> ZhuanFaEntitylist = zhuanFaEntityMapper.queryZhuanFaByLimit(paramMap);
		//		if(ZhuanFaEntitylist2.size()<5){
					if(ZhuanFaEntitylist.size()==0){//
						String ownzhuanfaId =userInfoMapper.queryUserInfo(currentUser.getPrincipal().toString()).getOnlyId()+""; 
						if(onlyzhuanfaId.equals(ownzhuanfaId)){
							view2.addObject("msg", "自己不能召唤自己");
						}else{
							paramMap.put("zhuanFaId",Long.valueOf(onlyzhuanfaId));

							zhuanFaEntityMapper.insertZhuanFa(paramMap);
							view2.addObject("msg","帮助成功，么么哒");
							view2.addObject("isSendMsg","1");
							
						}
					}else{
						String tempZhuanFaId = ZhuanFaEntitylist.get(0).getZhuanFaId().toString();
						if(tempZhuanFaId.equals(onlyzhuanfaId)){
							
							view2.addObject("msg", "您已经加入了");
						}else{
							view2.addObject("msg", "每人只能加入一个好友的求助");
						}
					}
		//		}
				view2.addObject("onlyIds",onlyIds);
				return view2;
			}
			/**帮助方结束***/
			/**发起方开始***/
			if(!iszhuanFa.equals("")){
				Subject currentUser = SecurityUtils.getSubject();
				if(currentUser.isAuthenticated()){
					Map<String,Object> paramMap = new HashMap<String,Object>();
	
					ModelAndView view3 = new ModelAndView("/zhuanfa/zhuanfa");
					String mobile = currentUser.getPrincipal().toString();
					
					String nickname = userInfoMapper.queryUserInfo(mobile).getNickname();
					view3.addObject("nickname",nickname);
					int onlyIds = userInfoMapper.queryUserInfo(mobile).getOnlyId();
					paramMap.put("mobile", mobile);
					paramMap.put("isCreater","1");//1表示创建者			
					List<ZhuanFaEntity> ZhuanFaEntitylist = zhuanFaEntityMapper.queryZhuanFaByLimit(paramMap);
					if(ZhuanFaEntitylist.size()==0){
						paramMap.put("zhuanFaId",onlyIds);
						zhuanFaEntityMapper.insertZhuanFa(paramMap);
						view3.addObject("isSendMsg","1");

						
					}else{
						paramMap.put("isCreater","");//1表示创建者			
						paramMap.put("mobile","");//1表示创建者			
						paramMap.put("zhuanFaId",ZhuanFaEntitylist.get(0).getZhuanFaId());//1表示创建者			
						if(zhuanFaEntityMapper.queryZhuanFaByLimit(paramMap).size()>4){
							String isHuodongYe =request.getParameter("isHuodongYe");
							if(isHuodongYe==null){
								
								view3.addObject("isChuangJian",1);
							}
						}
					}
	
	//				request.getSession().setAttribute("iszhuanFa", "");
					view3.addObject("onlyIds",onlyIds);
					return view3;
				}
			}
			/**发起方结束***/
			/****转发判断结束****/
	/**嘉年华活动开始**/
			String score=request.getSession().getAttribute("score")==null?"":request.getSession().getAttribute("score").toString();
			if(!score.equals("")){
				view =new ModelAndView("/jianianhua/guaguale"); 
			}
	
			PinActivityEntity pinActivityEntity = pinActivityService.activityObject();
			request.setAttribute("activityId",pinActivityEntity.getActivityId());
		} catch (Exception e) {
			logger.error("查询活动ID失败!",e);
		}
		return view;
	}
	
	/**
	 * 活动页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getHomeInfo", method = RequestMethod.GET)
	public String getHomePage(HttpServletRequest request) {
		String pageId = request.getParameter("pageId");
		String index = request.getParameter("index");
		if(StringUtils.isNotEmpty(pageId)){
			request.getSession().setAttribute("pageId", pageId);
		}
		request.setAttribute("index", index);
		
		try {
			PinActivityEntity pinActivityEntity = pinActivityService.activityObject();
			request.setAttribute("activityId",pinActivityEntity.getActivityId());
		} catch (Exception e) {
			logger.error("查询活动ID失败!",e);
		}
		return "home";
	}
	
	/**
	 * 跳转到登录页面






	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(HttpServletRequest request) {
		String pageId = request.getParameter("pageId");
		if(StringUtils.isNotEmpty(pageId)){
			request.getSession().setAttribute("pageId", pageId);
		}
		
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			return "/index";
		}
		
		return "/login";
	}
	
	/**
	 * 登录页面个人资料录入
	 * @param request
	 * @return
	 * @author liuyao 2014-08-12
	 * @throws Exception 
	 */
	@RequestMapping(value = "/personalData", method = RequestMethod.GET)
	public String getPersonalDataPage(HttpServletRequest request) throws Exception {
//		String pageId = request.getParameter("pageId");
//		if(StringUtils.isNotEmpty(pageId)){
//			request.getSession().setAttribute("pageId", pageId);
//		}
//		request.setAttribute("imagePath", GetConfigFile.getInstance().getProperties("imageAccessPath"));
//		Subject currentUser = SecurityUtils.getSubject();
//		String msisdn = (String) currentUser.getPrincipal();
//		request.setAttribute("msisdn", msisdn);
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
		 try {
	        	UserInfoEntity userinfo = userInfoService.queryUserInfo(msisdn);//获取我的信息，我的昵称，头像，手机号码，性别
	        	request.setAttribute("IsHeadShow", "1");
	        	if(userinfo.getHead_show()==null||userinfo.getHead_show().length==0){
	        		request.setAttribute("IsHeadShow", "0");
	        	}
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return "/person_info";
	}
	
	/**
	 * 查询省份信息
	 * @param userInfoEntity
	 * @return
	 * @author liuyao 2014-08-12
	 */
	@RequestMapping(value = "/searchProvinceNameList", method = RequestMethod.POST)
	public @ResponseBody Map<String, ?> searchProvinceNameList(UserInfoEntity userInfoEntity) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<UserInfoEntity> provinceList = new ArrayList<UserInfoEntity>();
			UserInfoEntity ue = new UserInfoEntity();
			ue.setProvince("");
			provinceList.add(ue);
			provinceList.addAll(userInfoService.searchProvinceNameList());
			//List<UserInfoEntity> provinceList = userInfoService.searchProvinceNameList();
			resultMap.put("provinceList", provinceList);
			resultMap.put("CODE", "TRUE");
		} catch (Exception e) {
			logger.error("查询省份信息失败!",e.getMessage());
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}
	
	/**
	 * 查询城市信息
	 * @param userInfoEntity
	 * @return
	 * @author liuyao 2014-08-12
	 */
	@RequestMapping(value = "/searchCityNameList", method = RequestMethod.POST)
	public @ResponseBody Map<String, ?> searchCityNameList(UserInfoEntity userInfoEntity) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<UserInfoEntity> cityList = userInfoService.searchCityNameList(userInfoEntity);
			resultMap.put("cityList", cityList);
			resultMap.put("CODE", "TRUE");
		} catch (Exception e) {
			logger.error("查询省份信息失败!",e.getMessage());
			resultMap.put("CODE", e.getMessage());
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}
	
	/**
	 * 用户个人资料保存
	 * @param request
	 * @param userInfoEntity
	 * @return
	 * @author liuyao 2014-08-12
	 */
	@RequestMapping(value = "/saveUserInfo", method = RequestMethod.POST)
	public @ResponseBody Map<String, ?> saveUserInfo(HttpServletRequest request,UserInfoEntity userInfoEntity) {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			List<UserInfoEntity> list = userInfoService.queryUserInfoByNickName(userInfoEntity.getNickname());
			UserInfoEntity uie = list.get(0);
			if(userInfoEntity.getMobile().contains("</a>")){
				userInfoEntity.setMobile(userInfoEntity.getMobile().substring(userInfoEntity.getMobile().length()-15,userInfoEntity.getMobile().length()-4));
			}
			if(uie != null && !uie.getMobile().equals(userInfoEntity.getMobile())){//昵称重名
				resultMap.put("msg","昵称已被其他人使用！");
				resultMap.put("CODE", "FALSE");
			}else{
				userInfoService.updateUserInfo(userInfoEntity);
				resultMap.put("CODE", "TRUE");
			}
			
		} catch (Exception e) {
			logger.error("用户个人资料录入失败!",e.getMessage());
			resultMap.put("msg", "用户个人资料录入失败!");
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}
	
	/**
	 * 接收ajax登录请求
	 * 
	 * @param account
	 *            登录帐号
	 * @param password
	 *            登录密码
	 * @param validateCode
	 *            验证码

	 * @param session
	 *            HttpSession
	 * @return
	 */
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> Login(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password/*, @RequestParam("validateCode") String validateCode*/,
			HttpSession session) {
		// 校验验证码

		//Map<String, String> resultMap = chkValidateCode(validateCode, session);
		Map<String, String> resultMap = new HashMap<String, String>();
		
		//if (resultMap != null) {
		//	return resultMap;
		//}
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			resultMap = login(request, currentUser, username, password);
		} 
		/*
		else {// 重复登录
			ShiroUser shiroUser = (ShiroUser) currentUser.getPrscipal();
			if (!shiroUser.getAccount().equalsIgnoreCase(account)) {// 如果登录名不同

				currentUser.logout();
				resultMap = login(currentUser, account, password);
			}
		}
		*/
		return resultMap;
	}

	/**
	 * 接收ajax登录请求
	 * 
	 * @param account
	 *            登录帐号
	 * @param session
	 *            HttpSession
	 * @return
	 */
	@RequestMapping(value = "/doAutoLogin", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> doAutoLogin(HttpServletRequest request, @RequestParam("username") String username,
			HttpSession session) {
		// 校验验证码

		//Map<String, String> resultMap = chkValidateCode(validateCode, session);
		Map<String, String> resultMap = new HashMap<String, String>();
		String password="";
		try {
			password = GetConfigFile.getInstance().getProperties("DefaultPwd");
		} catch (Exception e) {
			logger.error("");
		}
		if("".equals(password)){
			password = "888888";
		}
		
		ShiroUser userTemp = new ShiroUser();
		userTemp.setUsername(username);
		userTemp.setPassword(password);
		LocalCache.loginInfo.put(username, userTemp);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("mobile", username);
		map.put("password", password);
		map.put("timestamp", new Date().getTime());
		request.getSession().setAttribute(SESSION_KEY_USER_INFO, map);
		
		
		//if (resultMap != null) {
		//	return resultMap;
		//}
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			resultMap = login(request, currentUser, username, password);
		} 
		/*
		else {// 重复登录
			ShiroUser shiroUser = (ShiroUser) currentUser.getPrscipal();
			if (!shiroUser.getAccount().equalsIgnoreCase(account)) {// 如果登录名不同

				currentUser.logout();
				resultMap = login(currentUser, account, password);
			}
		}
		*/
		return resultMap;
	}
	
	/**
	 * 登录
	 * 
	 * @param currentUser
	 * @param username
	 * @param password
	 * @return
	 */
	private Map<String, String> login(HttpServletRequest request, Subject currentUser, String msisdn, String password) {
		Map<String, String> resultMap = new HashMap<String, String>();
		UsernamePasswordToken token = new UsernamePasswordToken(msisdn, password);
		token.setRememberMe(true);
		try {
			currentUser.login(token);
			
			try {
				UserInfoEntity register = userInfoService.queryUserInfo(msisdn);
				if(register == null){
					UserInfoEntity userInfoEntity = new UserInfoEntity();
					userInfoEntity.setMobile(msisdn);
					userInfoEntity.setNickname(msisdn);
					if(msisdn.length()==11){
						String temp = msisdn.substring(0, 3)+"****"+msisdn.substring(7);
						userInfoEntity.setNickname(temp);
					}
					userInfoEntity.setSex('男');
					userInfoEntity.setReg_date(TimeUtil.getTimeStr(new Date(), PATTERN_DATE_4));
					userInfoEntity.setOnlyId(userInfoService.searchMaxOnlyId());
					userInfoService.addUserInfo(userInfoEntity);
					//新增用户计数表，记录我的评论和赞的数量使用



					MobileReplyReviewEntity mrre = new MobileReplyReviewEntity();
					mrre.setMobile(msisdn);
					mrre.setLastClickReplyTime(StringUtil.formatDateYYYYMMDDHHMMSS(StringUtil.getNow()));
					mrre.setLastClickPraiseTime(StringUtil.formatDateYYYYMMDDHHMMSS(StringUtil.getNow()));
					mobileReplyReviewService.addMymrrZanInfo(mrre);
					resultMap.put("isFirstLogin", "TRUE");
					resultMap.put("CODE", "TRUE");
				}else{
					//更新session信息
					@SuppressWarnings("unchecked")
					Map<String, Object> map = (Map<String, Object>) request.getSession().getAttribute(LoginController.SESSION_KEY_USER_INFO);
					if (null != map) {
						String nickname = register.getNickname();
						if(nickname == null || "".equals(nickname)){
							nickname = register.getMobile();
						}
						map.put("username",nickname);
						request.getSession().setAttribute(LoginController.SESSION_KEY_USER_INFO, map);
					}
					
					resultMap.put("isFirstLogin", "FALSE");
					resultMap.put("CODE", "TRUE");
				}
				request.getSession().setAttribute("onlyflag", "");

				login_log.info(DateUtil.dateToDateString(new Date())+"|"+msisdn+"|"+new Date().getHours()+"|"+request.getSession().getId());
			} catch (Exception e) {
                logger.error(e.getMessage(), e);
				resultMap.put("msg", "添加新用户到数据库出错！");
				resultMap.put("CODE", "FALSE");
			}
		} catch (AuthenticationException e) {
			token.clear();
			resultMap.put("msg", "您输入的帐号或者密码不正确，请重新输入！");
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;

	}

	/**
	 * 校验验证码






	 * 
	 * @param validateCode
	 * @param session
	 * @return
	 *//*
	private Map<String, String> chkValidateCode(String validateCode, HttpSession session) {
		Map<String, String> resultMap = null;
		String realCode = (String) session.getAttribute("validateCode");

		if (StringUtils.isEmpty(validateCode) || !StringUtils.equals(realCode, validateCode.toLowerCase())) {
			resultMap = new HashMap<String, String>();
			resultMap.put("msg", "请输入正确的验证码！");
			resultMap.put("CODE", "FALSE");
			return resultMap;
		}
		// return resultMap;
		return null;
	}*/

	/**
	 * 登出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}

	/**
	 * 生成验证码






	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/validateCode")
	public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY, 4, null);
		request.getSession().setAttribute("validateCode", verifyCode);
		response.setContentType("image/jpeg");
		BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
		ImageIO.write(bim, "JPEG", response.getOutputStream());
	}
	
	/**
	 * 获取动态密码






	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/getPassWord", method = RequestMethod.POST)
	public @ResponseBody Map<String, ?> getPassWord(HttpServletRequest request,ShiroUser user){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String password = "";
			String isVerificationPwd = GetConfigFile.getInstance().getProperties("isVerificationPwd");
			String regDate = GetConfigFile.getInstance().getProperties("regDate");//马甲号的注册日期
			String msisdn  = user.getUsername();
			UserInfoEntity userInfo  = new UserInfoEntity();
			userInfo.setSure_regDate(regDate);
			userInfo.setMobile(msisdn);
			resultMap.put("isTestUser", "false");
			//验证是否为马甲号
			if(userInfoService.queryUserInfoByRegDate(userInfo)>0){
				isVerificationPwd="false";
				resultMap.put("isTestUser", "TRUE");
			}
			if("false".equals(isVerificationPwd)){
				password = GetConfigFile.getInstance().getProperties("DefaultPwd");
				if("".equals(password)){
					password = "888888";
				}
			}else{
				password = StringTransTool.getRandomNum4String(6).toLowerCase();
			}
			ShiroUser userTemp = new ShiroUser();
			userTemp.setUsername(user.getUsername());
			userTemp.setPassword(password);
			logger.debug("用户："+user.getUsername()+";短信校验码为："+password);
			LocalCache.loginInfo.put(user.getUsername(), userTemp);
			//smsService.sendMsgCode(user.getBindMsisdn(),weixinAppNameView);
			
			if("true".equals(isVerificationPwd)){
				SmsSendMsg message = new SmsSendMsg();
				message.setAsynSend(false);
				message.setCreateTime(new Date());
				message.setMobile(user.getUsername());
				message.setPriority(DIY09SmsConstants.PRIORITY_MIDDLE);
				message.setSendStatus(SmsConstants.SEND_STATUS_WAITING);
				message.setSendTime(new Date());
				message.setRetryTimes(0);
				
				String smsContent = sysConfigService.getSysConfigAsString(SmsConstants.WIFI_SMS_NOTICE_PASSWORD, new String[]{password});
				message.setContent(smsContent);
				
				smsSender.send(message);//发送验证码
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("username", user.getUsername());
			map.put("mobile", user.getUsername());
			map.put("password", password);
			map.put("timestamp", new Date().getTime());
			request.getSession().setAttribute(SESSION_KEY_USER_INFO, map);
			
			//resultMap.put("msg", password);
			resultMap.put("CODE", "TRUE");
			resultMap.put("seconds", passwordSendExpired);
			return resultMap;
		}catch(Exception e){
			resultMap.put("msg", "获取动态密码失败，请稍候再试！");
			resultMap.put("CODE", "FALSE");
			return resultMap;
		}
		
	}
	
	/**
	 * 获取已登录用户号码






	 * @return
	 */
	@RequestMapping(value = "/loadLoginMobile", method = RequestMethod.POST)
	public @ResponseBody Map<String, ?> loadLoginMobile(HttpServletRequest request,ShiroUser user){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String username = "";
			String mobile = "";
			String islogin = "0";//0未登录，1已登陆






			long remainSeconds = 0;
			Subject currentUser = SecurityUtils.getSubject();
			if (currentUser.isAuthenticated()) {
				islogin = "1";
				mobile = (String) currentUser.getPrincipal();
				username = getNickName(request);//获取昵称
			}else{
				islogin = "0";
				remainSeconds = getSendSeconds(request);
			}
			
			resultMap.put("islogin", islogin);
			resultMap.put("username", username);
			resultMap.put("mobile", mobile);
			resultMap.put("remainSeconds", remainSeconds);
			resultMap.put("CODE", "TRUE");
			return resultMap;
		}catch(Exception e){
			resultMap.put("msg", "获取登陆失败，请稍候再试！");
			resultMap.put("CODE", "FALSE");
			return resultMap;
		}
	}
	
	/**
	 * 获取可发送动态密码剩余秒数Ｎ
	 * 即还有Ｎ秒数就可以再次发送动态密码



	 * @return
	 */
	private long getSendSeconds(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) request.getSession().getAttribute(SESSION_KEY_USER_INFO);
		if (null != map) {
			return (Long.parseLong(String.valueOf(map.get("timestamp"))) + passwordSendExpired * 1000 - (new Date().getTime())) /1000;
		}
		return 0;
	}
	
	/**
	 * 获取昵称
	 * 即还有Ｎ秒数就可以再次发送动态密码



	 * @return
	 */
	private String getNickName(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) request.getSession().getAttribute(SESSION_KEY_USER_INFO);
		if (null != map) {
			String nickname = String.valueOf(map.get("username"));
			if(nickname == null || "".equals(nickname)){
				nickname = String.valueOf(map.get("mobile"));
			}
			return nickname;
		}
		return "";
	}
	/**
	 *跳转到迎新优惠页面


	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/yingxin_youhui", method = RequestMethod.GET)
	public ModelAndView youhui(HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("/yingxin_youhui");	
		return view;
	}
	
//	public static void main(String[] args) {
//		String msisdn ="12356478923";
//		System.out.print(msisdn.substring(0, 3)+"****"+msisdn.substring(7));
//	}

}
