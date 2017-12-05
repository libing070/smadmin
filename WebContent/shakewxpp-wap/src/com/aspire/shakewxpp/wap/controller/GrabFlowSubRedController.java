package com.aspire.shakewxpp.wap.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aspire.shakewxpp.wap.entity.ConfigPojo;
import com.aspire.shakewxpp.wap.entity.DistributeFlowRedEnvelope;
import com.aspire.shakewxpp.wap.entity.GrabFlowSubRedEnvelope;
import com.aspire.shakewxpp.wap.entity.User;
import com.aspire.shakewxpp.wap.exception.WxppException;
import com.aspire.shakewxpp.wap.service.ConfigService;
import com.aspire.shakewxpp.wap.service.GrabFlowSubRedService;
import com.aspire.shakewxpp.wap.service.SmsService;
import com.aspire.shakewxpp.wap.service.UserService;
import com.aspire.shakewxpp.wap.util.DESTools;
import com.aspire.shakewxpp.wap.util.DateUtil;
import com.aspire.shakewxpp.wap.util.GetConfigFile;
import com.aspire.shakewxpp.wap.util.HttpPostUtil;

@Controller
@Scope("prototype")
public class GrabFlowSubRedController {

	protected static Logger logger = LoggerFactory
			.getLogger(GrabFlowSubRedController.class);
	
	protected static Logger report_logger =  LoggerFactory.getLogger("report_log");

	@Resource(name = "grabFlowSubRedService")
	private GrabFlowSubRedService grabFlowSubRedService;
	
	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "smsService")
	private SmsService smsService;
	
	@Resource(name = "configService")
	private ConfigService configService;

	/**
	 * 抢红包成功页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author liuyao 2014-08-27
	 */
	@RequestMapping(value = "/getSuccessSubRedInfo.tv", method = RequestMethod.GET)
	public ModelAndView getSuccessSubRedInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView view = new ModelAndView("smallredbag/successSubRed");
		
		//若是订阅号,url会携带openid过来
		String openid = StringUtils.isEmpty(request.getParameter("openid"))?(String)request.getSession().getAttribute("openid"):request.getParameter("openid");

		String weixinAppNo = request.getParameter("weixinAppNo");
		if(StringUtils.isEmpty(openid)){	
			if(StringUtils.isEmpty(weixinAppNo)){
				logger.warn("到我们的错误页面，获取当前用户手机号失败");
				view.addObject("ex", new Exception("未携带weixinAppNo参数"));
				view = new ModelAndView("common/exception");
                return view;
			}
			Map<String,String> map = userService.queryAppMessageByWeixinAppNo(weixinAppNo);
			HttpPostUtil.getOpenId(request, response, null,map);
			return null;
		}
		
		User user = new User();
		user.setOpenID(openid);
		user.setWeixinAppNo(weixinAppNo);
		view.addObject("user", user);
		String  freId = request.getParameter("freId");
		DESTools desTools = DESTools.getInstance();
		String desFreId = desTools.desCrypto(freId);
		
		view.addObject("freId", freId);
		view.addObject("desFreId", desFreId);
		return view;
	}

	/**
	 * 用户所抢小红包页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author liuyao 2014-08-28
	 */
	@RequestMapping(value = "/getUserSnagSubRedInfo.tv", method = RequestMethod.GET)
	public ModelAndView getUserSnagSubRedInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView view = new ModelAndView("smallredbag/snagSubRed");
		
		//若是订阅号,url会携带openid过来
		String openid = StringUtils.isEmpty(request.getParameter("openid"))?(String)request.getSession().getAttribute("openid"):request.getParameter("openid");

		String weixinAppNo = request.getParameter("weixinAppNo");
		if(StringUtils.isEmpty(openid)){	
			if(StringUtils.isEmpty(weixinAppNo)){
				logger.warn("到我们的错误页面，获取当前用户手机号失败");
				view.addObject("ex", new Exception("未携带weixinAppNo参数"));
                view = new ModelAndView("common/exception");
                return view;
			}
			Map<String,String> map = userService.queryAppMessageByWeixinAppNo(weixinAppNo);
			HttpPostUtil.getOpenId(request, response, null,map);
			return null;
		}
		
		report_logger.info(DateUtil.getCurDate()+"|"+openid+"|getUserSnagSubRedInfo.tv");
		
		view.addObject("openId", openid);
		view.addObject("weixinAppNo", weixinAppNo);
		return view;
	}

	/**
	 * 红包攻略页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author liuyao 2014-08-29
	 */
	@RequestMapping(value = "/gonglue.tv", method = RequestMethod.GET)
	public ModelAndView getStrategySubRedInfo(HttpServletRequest request,
			HttpServletResponse response,Model model) throws Exception {
		ModelAndView view = new ModelAndView("huodong/gonglue");
		String openid = StringUtils.isEmpty(request.getParameter("openid"))?(String)request.getSession().getAttribute("openid"):request.getParameter("openid");

		String weixinAppNo = request.getParameter("weixinAppNo");
		if(StringUtils.isEmpty(openid)){	
			if(StringUtils.isEmpty(weixinAppNo)){
				logger.warn("到我们的错误页面，获取当前用户手机号失败");
                model.addAttribute("ex", new Exception("未携带weixinAppNo参数"));
			}
			Map<String,String> map = userService.queryAppMessageByWeixinAppNo(weixinAppNo);
			HttpPostUtil.getOpenId(request, response, null,map);
			return null;
		}
		
		report_logger.info(DateUtil.getCurDate()+"|"+openid+"|gonglue.tv");
		
		
		//取抽流量红包配置
		GetConfigFile getConfigFile = GetConfigFile.getInstance();
		List<ConfigPojo> configPojoList = getConfigFile.getConfigPojoList();
		if(configPojoList==null){
			configService.getConfigData(getConfigFile.getProperties("provinceConfigTable"));
		}
		configPojoList = getConfigFile.getConfigPojoList();
		if(configPojoList.isEmpty()){
			logger.error("配置表无配置数据");
			view.addObject("errorMsg","系统异常");
		}else{
			String activityCode = getConfigFile.getProperties("activityCode");
			for(ConfigPojo configPojo:configPojoList){
				if(activityCode.equals(configPojo.getActivityName())){
					view.addObject("bindSubFreCountPeruser",configPojo.getBindSubFreCountPeruser());
					view.addObject("maxFreCount",configPojo.getMaxFreCount());
					view.addObject("subFreCountPeruser",configPojo.getSubFreCountPeruser());
					
					String[] arrayTimeRange = configPojo.getTimeRange().split("-");
					view.addObject("times",arrayTimeRange.length);
					
					String timeRange = configPojo.getTimeRange();
					timeRange = timeRange.replace("-", "、");
					view.addObject("timeRange",timeRange);
					
					view.addObject("maxUserCount",configPojo.getMaxUserCount());
					view.addObject("flowpkgSubFreCountPeruser",configPojo.getFlowpkgSubFreCountPeruser());
					view.addObject("freExpireDays",configPojo.getFreExpireDays());
					view.addObject("subFreExchangeDays",configPojo.getSubFreExchangeDays());
				}
				break;
			}
		}
		
		return view;
	}
	
	/**
	 * 查询朋友所抢红包
	 * @param request
	 * @param grabFlowSubRedEnvelope
	 * @return
	 * @author liuyao 2014-08-28
	 */
	@RequestMapping(value = "/searchFriendSubRedList.tv", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> searchFriendSubRedList(HttpServletRequest request,
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<GrabFlowSubRedEnvelope> grabList = grabFlowSubRedService
					.searchFriendSubRedList(grabFlowSubRedEnvelope);
			Float sumSubRed = grabFlowSubRedService.searchUserSumSubRedInfo(grabFlowSubRedEnvelope);
			
			DistributeFlowRedEnvelope envelope = grabFlowSubRedService
					.searchDistributeFlowRedInfo(grabFlowSubRedEnvelope);
			resultMap.put("list", grabList);
			resultMap.put("sumSubRed", sumSubRed);
			resultMap.put("envelope", envelope);
			resultMap.put("CODE", "TRUE");
		} catch (WxppException e) {
			logger.error("查询朋友抢红包失败!",e);
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 查询用户所抢小红包
	 * @param request
	 * @param grabFlowSubRedEnvelope
	 * @return
	 * @author liuyao 2014-08-28
	 */
	@RequestMapping(value = "/searchUserSubRedList.tv", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> searchUserSubRedList(HttpServletRequest request,
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<GrabFlowSubRedEnvelope> grabList = grabFlowSubRedService
					.searchUserSnagRedBagList(grabFlowSubRedEnvelope);
			Integer count = grabFlowSubRedService
					.searchUserSnagRedBagListCount(grabFlowSubRedEnvelope);
			resultMap.put("list", grabList);
			resultMap.put("count", count);
			resultMap.put("CODE", true);
		} catch (WxppException e) {
			logger.error("查询用户所抢小红包失败!",e);
			resultMap.put("CODE", false);
			resultMap.put("msg", e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 获取动态码
	 * @param request
	 * @param user
	 * @return
	 * @author liuyao 2014-08-29
	 */
	@RequestMapping(value = "/gainTrendsCodeInfo.tv", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> gainTrendsCodeInfo(HttpServletRequest request,User user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			
			/**
			 * 校验手机号码是否属于当前系统使用的归属地
			 */
			String bindMsisdn = user.getBindMsisdn();
			GetConfigFile getConfigFile = GetConfigFile.getInstance();
			List<ConfigPojo> configPojoList = getConfigFile.getConfigPojoList();
			if(configPojoList==null){
				configService.getConfigData(getConfigFile.getProperties("provinceConfigTable"));
			}
			configPojoList = getConfigFile.getConfigPojoList();
			if(configPojoList.isEmpty()){
				logger.error("配置表无配置数据");
				throw new WxppException("配置表无配置数据");
			}
			
			String activityCode = getConfigFile.getProperties("activityCode");
			for(ConfigPojo configPojo:configPojoList){
				if(activityCode.equals(configPojo.getActivityName())){
					Map<String, String> map = new HashMap<String, String>();
					map.put("applyProvinceId", configPojo.getApplyProvinceId());
					String phone7 = bindMsisdn.substring(0,7);
					String phone8 = bindMsisdn.substring(0,8);
					map.put("bindMsisdn7", phone7);
					map.put("bindMsisdn8", phone8);
					Integer count = grabFlowSubRedService.findBindMsisdnCount(map);
					if(count==0){
						resultMap.put("CODE", false);
						resultMap.put("msg","请输入正确的山东移动手机号码!");
						return resultMap;
					}
				}
			}
			
			Integer shirouserId = userService.queryShirouserIdByWeixinAppNo(user.getWeixinAppNo());
			Map<String,String> paraMap = new HashMap<String,String>();
			paraMap.put("shirouserId",String.valueOf(shirouserId));
			paraMap.put("msisdn",user.getBindMsisdn());
			boolean flag = userService.checkForMsisdnIsBind(paraMap);
            if(flag){
            	throw new WxppException("该手机号码已关联一个微信号,一个手机号码不能同时关联多个微信号");            	
            }
			
			Map<String, String> weiXinMap = userService
					.queryAppMessageByWeixinAppNo(user.getWeixinAppNo());
			/**
			 * 发送手机校验码
			 */
			smsService.sendMsgCodeForGetFLow(bindMsisdn,
					weiXinMap.get("WEIXIN_APP_NAME_VIEW"));
			resultMap.put("CODE", true);
		} catch (WxppException e) {
			logger.error("动态码已发送!",e);
			resultMap.put("CODE", false);
			resultMap.put("msg", e.getMessage());
			return resultMap;
		}catch (Exception e) {
			logger.error("发送动态码失败!",e);
			resultMap.put("CODE", false);
			resultMap.put("msg", "发送动态码失败!");
			return resultMap;
		}
		return resultMap;
	}

	/**
	 * 校验动态码，录入手机号
	 * @param request
	 * @param user
	 * @return
	 * @author liuyao 2014-08-29
	 */
	@RequestMapping(value = "/submitBoundInfo.tv", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> submitBoundInfo(HttpServletRequest request,GrabFlowSubRedEnvelope envelope) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			boolean isCheckSmsPwd = smsService.checkSmsPassWordForGetFLow(envelope.getPassword(),envelope.getBindMsisdn());
			if(!isCheckSmsPwd){
				resultMap.put("CODE", false);
				resultMap.put("msg","动态码输入不正确!");
				return resultMap;
			}
			grabFlowSubRedService.saveRedBagInfo(envelope);
			resultMap.put("CODE", true);
		}catch(WxppException wx){
			logger.error("录入未绑定用户手机号码失败!");
			resultMap.put("CODE", false);
			resultMap.put("msg", "绑定用户手机号码失败!");
		} catch (Exception e) {
			logger.error("校验动态码失败!",e);
			resultMap.put("CODE", false);
			resultMap.put("msg","动态码输入不正确!");
		}
		return resultMap;
	}
	
	/**
	 * 查询用户已抢过小红包数量和过期时间
	 * @param request
	 * @param user
	 * @param envelope
	 * @return
	 * @author liuyao 2014-09-01
	 */
	@RequestMapping(value = "/searchRedBagQiangGuoInfo.tv", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> searchRedBagQiangGuoInfo(HttpServletRequest request,GrabFlowSubRedEnvelope envelope) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			GrabFlowSubRedEnvelope subRedEnvelope = grabFlowSubRedService.searchRedBagQiangGuoInfo(envelope);
			resultMap.put("subRedEnvelope", subRedEnvelope);
			resultMap.put("CODE", true);
		} catch (Exception e) {
			logger.error("查询用户已抢过小红包数量和过期时间!",e);
			resultMap.put("CODE", false);
		}
		return resultMap;
	}
	
	/**
	 * 查询用户是否绑定手机号码
	 * @param request
	 * @param user
	 * @return
	 * @author liuyao 2014-09-12
	 */
	@RequestMapping(value = "/findUserBindMsisdn.tv", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> findUserBindMsisdn(HttpServletRequest request,User user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			User userInfo = userService.getUser(user);
			if(null!=userInfo){
				resultMap.put("user", userInfo);
				resultMap.put("CODE", true);
			}else{
				resultMap.put("CODE", false);
			}
		} catch (Exception e) {
			logger.error("查询用户信息失败!",e);
			resultMap.put("CODE", false);
		}
		return resultMap;
	}
}