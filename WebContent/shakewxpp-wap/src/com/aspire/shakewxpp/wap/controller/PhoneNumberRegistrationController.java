package com.aspire.shakewxpp.wap.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aspire.shakewxpp.wap.entity.ConfigPojo;
import com.aspire.shakewxpp.wap.entity.DistributeFlowRedEnvelope;
import com.aspire.shakewxpp.wap.entity.User;
import com.aspire.shakewxpp.wap.exception.WxppException;
import com.aspire.shakewxpp.wap.service.ConfigService;
import com.aspire.shakewxpp.wap.service.DistributeFlowRedService;
import com.aspire.shakewxpp.wap.service.GrabFlowSubRedService;
import com.aspire.shakewxpp.wap.service.SmsService;
import com.aspire.shakewxpp.wap.service.UserService;
import com.aspire.shakewxpp.wap.util.Constants;
import com.aspire.shakewxpp.wap.util.DESTools;
import com.aspire.shakewxpp.wap.util.DateUtil;
import com.aspire.shakewxpp.wap.util.GetConfigFile;
import com.aspire.shakewxpp.wap.util.HttpPostUtil;

/**
 * 手机号码登记管理器

 * @author
 *
 */
@Controller
@Scope("prototype")
public class PhoneNumberRegistrationController {

	protected static Logger logger = LoggerFactory.getLogger(PhoneNumberRegistrationController.class);
	protected static Logger report_logger =  LoggerFactory.getLogger("report_log");
	  
	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "smsService")
	private SmsService smsService;
	
	@Resource(name = "configService")
	private ConfigService configService;
	
	@Resource(name = "grabFlowSubRedService")
	private GrabFlowSubRedService grabFlowSubRedService;
	
	
	@Resource(name = "distributeFlowRedService")
	private DistributeFlowRedService distributeFlowRedService;
	/**
	 * 绑定手机号页面
	 * @return
	 */
	@RequestMapping(value = "/phone.tv", method = RequestMethod.GET)
	public String phone(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		//若是订阅号,url会携带openid过来
		String openid = StringUtils.isEmpty(request.getParameter("openid"))?(String)request.getSession().getAttribute("openid"):request.getParameter("openid");

		String weixinAppNo = request.getParameter("weixinAppNo");
		if(StringUtils.isEmpty(openid)){	
			if(StringUtils.isEmpty(weixinAppNo)){
				logger.warn("到我们的错误页面，获取当前用户手机号失败");
				request.setAttribute("ex", new Exception("未携带weixinAppNo参数"));
                return "common/exception";
			}
			Map<String,String> map = userService.queryAppMessageByWeixinAppNo(weixinAppNo);
			HttpPostUtil.getOpenId(request, response, null,map);
			return null;
		}
		
		
		User user_temp = new User();
		//openid="oPALYjiRi0K6QQ6HgQvz-rct02aA";
		user_temp.setOpenID(openid);
		
		User user = userService.getUser(user_temp);
		if(user==null){
			user = new User();
			user.setOpenID(openid);
			user.setWeixinAppNo(weixinAppNo);
			try{
				userService.insertOpenId(user);
			}catch(WxppException e){
				logger.error("跳转到绑定手机号页面出现异常",e);
			}
		}
		
		request.setAttribute("user", user);    
	    return "/bindMsisdn/bindMsisdn";
	}


	
	/**
	 * 绑定操作
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/bindMsisdn.tv", method = RequestMethod.POST)
	public @ResponseBody Map<String, ?> bindMsisdn(User user,HttpSession session){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (user == null) {
			resultMap.put("msg", "请求参数错误！");
			resultMap.put("CODE", "FALSE");
			return resultMap;
		}
		int freId;
		try {
            //校验动态密码
            if(!smsService.checkSmsPassWordForBind(user.getPassword(),user.getBindMsisdn())){
            	throw new Exception("动态密码不正确！"); 
            }
			userService.bindMsisdn(user);
			
			report_logger.info(DateUtil.getCurDate()+"|"+user.getOpenID()+"|bindMsisdn.tv");
			
			DistributeFlowRedEnvelope tmpVO = new DistributeFlowRedEnvelope();
			tmpVO.setOpenid(user.getOpenID());
			tmpVO.setFreFromSource(2);
			
			freId=distributeFlowRedService.queryDistributeFlowByOpenidAndSource(tmpVO);
			
		}catch (WxppException we) {
			logger.error("用户号码绑定失败",we);
			resultMap.put("msg", we.getMessage());
			resultMap.put("CODE", "FALSE");
			return resultMap;
		}catch(Exception e){
			logger.error("用户号码绑定失败",e);
			resultMap.put("msg", "用户号码绑定失败，动态密码不正确！");
			resultMap.put("CODE", "FALSE");
			return resultMap;
		}
		
		
		/*try {
			//赠送流量红包(大红包)
			drawService.addDistributeFlowRedEnvelope(user.getOpenID(), Constants.FRE_FROM_SOURCE_BIND);	
		}catch(WxppException e){
			logger.error("赠送用户流量红包失败,openid:"+user.getOpenID(),e);
			resultMap.put("msg", "绑定成功,您已赠送过流量红包！");
			resultMap.put("CODE", "FALSE");
			return resultMap;
		}catch(Exception e){
			logger.error("赠送用户流量红包失败,openid:"+user.getOpenID(),e);
			resultMap.put("msg", "绑定成功,赠送流量红包失败！");
			resultMap.put("CODE", "FALSE");
			return resultMap;
		}*/
		DESTools desTools = DESTools.getInstance();
		String desFreId = desTools.desCrypto(freId+"");
		resultMap.put("desFreId", desFreId);
		resultMap.put("freId", freId);
		resultMap.put("CODE", "TRUE");
		return resultMap;
	}
	/**
	 * 解绑操作
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/unBindMsisdn.tv", method = RequestMethod.POST)
	public @ResponseBody Map<String, ?> unBindMsisdn(User user){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (user == null) {
			resultMap.put("msg", "请求参数错误！");
			resultMap.put("CODE", "FALSE");	
		}
		try {
			userService.unBindMsisdn(user);
			resultMap.put("CODE", "TRUE");
			return resultMap;
		} catch (WxppException we) {
			logger.error("用户号码解绑失败",we);
			resultMap.put("msg", we.getMessage());
			resultMap.put("CODE", "FALSE");
			return resultMap;
		}catch(Exception e){
			logger.error("用户号码解绑失败",e);
			resultMap.put("msg", "用户号码解绑失败，请稍候再试！");
			resultMap.put("CODE", "FALSE");
			return resultMap;
		}
	}

	
	/**
	 * 获取动态密码
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/getPassWord.tv", method = RequestMethod.POST)
	public @ResponseBody Map<String, ?> getPassWord(HttpServletRequest request,User user){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String,String> paraMap = new HashMap<String,String>();
			Integer shirouserId = userService.queryShirouserIdByOpenid(user.getOpenID());
			if(shirouserId == null){
				resultMap.put("msg","您未关注该公众号,不能绑定手机号");
				resultMap.put("CODE", "FALSE");
				return resultMap;
			}
			
			
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
					String phone7 = user.getBindMsisdn().substring(0,7);
					String phone8 = user.getBindMsisdn().substring(0,8);
					map.put("bindMsisdn7", phone7);
					map.put("bindMsisdn8", phone8);
					Integer count = grabFlowSubRedService.findBindMsisdnCount(map);
					if(count==0){
						resultMap.put("CODE", "FALSE");
						resultMap.put("msg","请输入正确的山东移动手机号码!");
						return resultMap;
					}
				}
			}
			
			
			paraMap.put("shirouserId",String.valueOf(shirouserId));
			paraMap.put("msisdn",user.getBindMsisdn());
			boolean flag = userService.checkForMsisdnIsBind(paraMap);
			
            if(flag){
            	//throw new WxppException("该手机号码已绑定其它微信号,一个手机号码不能同时绑定多个微信号");  
            	throw new WxppException("该手机号码已关联一个微信号,一个手机号码不能同时关联多个微信号"); 
            }
			String weixinAppNameView = userService.queryWeixinAppNameVieByShirouserId(shirouserId);
			smsService.sendMsgCodeForBind(user.getBindMsisdn(),weixinAppNameView);
			resultMap.put("CODE", "TRUE");
			return resultMap;
		}catch (WxppException we) {
			logger.error("获取动态密码失败",we);
			resultMap.put("msg", we.getMessage());
			resultMap.put("CODE", "FALSE");
			return resultMap;
		}catch(Exception e){
			logger.error("获取动态密码失败",e);
			resultMap.put("msg", "获取动态密码失败，请稍候再试！");
			resultMap.put("CODE", "FALSE");
			return resultMap;
		}
		
	}
}
