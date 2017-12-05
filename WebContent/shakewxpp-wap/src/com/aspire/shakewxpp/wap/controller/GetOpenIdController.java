package com.aspire.shakewxpp.wap.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.aspire.shakewxpp.wap.entity.WeixinGetOpenId;
import com.aspire.shakewxpp.wap.entity.WeixinRes;
import com.aspire.shakewxpp.wap.service.UserService;
import com.aspire.shakewxpp.wap.util.GetConfigFile;
import com.aspire.shakewxpp.wap.util.HttpPostUtil;

@Controller
@Scope("prototype")
public class GetOpenIdController {
	protected static Logger logger =  LoggerFactory.getLogger(GetOpenIdController.class);

	@Resource(name = "userService")
	private UserService userService;
	
	@RequestMapping(value = "/getOpenId.tv")
	public String getOpenId(HttpServletRequest request, HttpServletResponse response)
	{
		WeixinRes weixinRes = new WeixinRes();
		WeixinGetOpenId weixinGetOpenId = new WeixinGetOpenId();
		logger.debug("进入GetOpenIdController.getOpenId方法");
		
		//重定向后会带上state参数
		String state = request.getParameter("state");
		try {
			state=java.net.URLDecoder.decode(state,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error("GetOpenIdController.getOpenId==URLDecoder statec出现异常",e1);
		} 
		String[] paraReq= state.split("#");
		String weixinAppNo = paraReq[paraReq.length-1];
		String url=paraReq[paraReq.length-2];
		
		try{
			GetConfigFile getConfigFile = GetConfigFile.getInstance();
			Map<String,String> map = userService.queryAppMessageByWeixinAppNo(weixinAppNo);
			//String appid = getConfigFile.getProperties("AppID");
			//String secret = getConfigFile.getProperties("AppSecret");
			String appid = map.get("APPID");
			String secret = map.get("APPSECRET");
			
			String getOpenId_url = getConfigFile.getProperties("getOpenId_url");
			getOpenId_url = getOpenId_url+"&appid="+appid+"&secret="+secret+"&code="+request.getParameter("code");
			weixinRes = HttpPostUtil.sendHttpGet(null, getOpenId_url);
			if(StringUtils.isEmpty(weixinRes.getErrcode())){
				weixinGetOpenId = JSON.parseObject(weixinRes.getReturnJson(), WeixinGetOpenId.class); 
			}else{
				logger.error("[[GetOpenIdController.getOpenId]] web方式通过code获取用户openid出现错误:"+weixinRes.getErrmsg());
			}
		}catch(Exception e){
			logger.error("[[GetOpenIdController.getOpenId]] web方式通过code获取用户openid出现异常",e);
		}

		request.setAttribute("openid", weixinGetOpenId.getOpenid());
		request.getSession().setAttribute("openid", weixinGetOpenId.getOpenid());
		if(url.contains(".tv")){
			return "forward:"+url;
		}else{		
			return url;
		}
	}
	
	
}
