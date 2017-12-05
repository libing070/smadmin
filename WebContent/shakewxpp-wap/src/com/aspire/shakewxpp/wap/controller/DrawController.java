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

import com.aspire.shakewxpp.wap.entity.ConfigPojo;
import com.aspire.shakewxpp.wap.entity.DrawResult;
import com.aspire.shakewxpp.wap.entity.User;
import com.aspire.shakewxpp.wap.service.ConfigService;
import com.aspire.shakewxpp.wap.service.DrawService;
import com.aspire.shakewxpp.wap.service.UserService;
import com.aspire.shakewxpp.wap.util.DESTools;
import com.aspire.shakewxpp.wap.util.DateUtil;
import com.aspire.shakewxpp.wap.util.GetConfigFile;
import com.aspire.shakewxpp.wap.util.HttpPostUtil;

/**
 * 
 * @author caozhaoyan
 *
 */
@Controller
@Scope("prototype")
public class DrawController {

	protected static Logger logger = LoggerFactory.getLogger(DrawController.class);
	
	protected static Logger report_logger =  LoggerFactory.getLogger("report_log");

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "drawService")
	private DrawService drawService;
	
	@Resource(name = "configService")
	private ConfigService configService;
	
	/**
	 * 抽流量红包页面
	 * @return
	 */
	@RequestMapping(value = "/drawPage.tv", method = RequestMethod.GET)
	public String drawPage(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		//若是订阅号,url会携带openid过来
		String openid = StringUtils.isEmpty(request.getParameter("openid"))?(String)request.getSession().getAttribute("openid"):request.getParameter("openid");

		String weixinAppNo = request.getParameter("weixinAppNo");
		if(StringUtils.isEmpty(openid)){	
			if(StringUtils.isEmpty(weixinAppNo)){
				logger.warn("到我们的错误页面，获取当前用户手机号失败");
                model.addAttribute("ex", new Exception("未携带weixinAppNo参数"));
                return "common/exception";
			}
			Map<String,String> map = userService.queryAppMessageByWeixinAppNo(weixinAppNo);
			HttpPostUtil.getOpenId(request, response, null,map);
			return null;
		}
		
		  report_logger.info(DateUtil.getCurDate()+"|"+openid+"|drawPage.tv");
		
		// 取得配置的媒体文件存放路径,供页面使用
		model.addAttribute("openid",openid);

		//取抽流量红包配置
		GetConfigFile getConfigFile = GetConfigFile.getInstance();
		List<ConfigPojo> configPojoList = getConfigFile.getConfigPojoList();
		if(configPojoList==null){
			configService.getConfigData(getConfigFile.getProperties("provinceConfigTable"));
		}
		configPojoList = getConfigFile.getConfigPojoList();
		if(configPojoList.isEmpty()){
			logger.error("配置表无配置数据");
			model.addAttribute("errorMsg","系统异常");
		}else{
			String activityCode = getConfigFile.getProperties("activityCode");
			for(ConfigPojo configPojo:configPojoList){
				if(activityCode.equals(configPojo.getActivityName())){
					model.addAttribute("maxFreCount",configPojo.getMaxFreCount());
				}
				break;
			}
		}
		
		model.addAttribute("weixinAppNo",weixinAppNo);
		return "/index";
	}
	
	
	/**
	 * 抽流量红包操作
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/drawFlowRedEnvelope.tv", method = RequestMethod.POST)
	public @ResponseBody Map<String, ?> drawFlowRedEnvelope(User user,HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			DrawResult drawResult = drawService.drawFlowRedEnvelope(user);
			Integer freId = drawResult.getFreId();
			if(freId!=null){
				DESTools desTools = DESTools.getInstance();
				String desFreId = desTools.desCrypto(freId+"");
				resultMap.put("desFreId", desFreId);
			}
			
			resultMap.put("drawResult", drawResult);
            resultMap.put("CODE", "TRUE");
            return  resultMap;
		}catch(Exception e){
			logger.error("抽流量红包出现异常,用户："+user.getOpenID(),e);
			resultMap.put("msg", "系统繁忙，请稍候再试！");
			resultMap.put("CODE", "FALSE");
			return resultMap;
		}
	}
	
	/**
	 * 查询用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/queryUserInfo.tv", method = RequestMethod.POST)
	public @ResponseBody Map<String, ?> queryUserInfo(User user,HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			User _user = userService.getUser(user);

			resultMap.put("user", _user);
            resultMap.put("CODE", "TRUE");
            return  resultMap;
		}catch(Exception e){
			logger.error("查询用户信息出现异常,用户："+user.getOpenID(),e);
			resultMap.put("msg", "系统繁忙，请稍候再试！");
			resultMap.put("CODE", "FALSE");
			return resultMap;
		}
	}
}
