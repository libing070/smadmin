package com.aspire.shakewxpp.wap.controller;

import com.aspire.shakewxpp.wap.entity.User;
import com.aspire.shakewxpp.wap.exception.WxppException;
import com.aspire.shakewxpp.wap.service.UserService;
import com.aspire.shakewxpp.wap.util.HttpPostUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.controller
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/27
 * @Version V1.0
 * Update Logs:
 */
public class BaseController {
	protected static Logger logger = LoggerFactory.getLogger(BaseController.class);
	
    @Resource(name = "userService")
    private UserService userService;

    protected String getCurrentUserOpenId(HttpServletRequest request, HttpServletResponse response) {
    	//若是订阅号,url会携带openid过来
		String openid = StringUtils.isEmpty(request.getParameter("openid"))?(String)request.getSession().getAttribute("openid"):request.getParameter("openid");

		String weixinAppNo = request.getParameter("weixinAppNo");
		if(StringUtils.isEmpty(openid)){	
			if(StringUtils.isEmpty(weixinAppNo)){
				logger.error("请求/drawPage.tv,缺少weixinAppNo");
			}
			Map<String,String> map = userService.queryAppMessageByWeixinAppNo(weixinAppNo);
			try{
				HttpPostUtil.getOpenId(request, response, null,map);
			}catch(Exception e){
				logger.error("去公众平台获取openid出现异常",e);
			}
			return null;
		}

        return openid;
    }

    protected String getCurrentUserMobile(HttpServletRequest request, HttpServletResponse response) {
        try {
            String openId = getCurrentUserOpenId(request, response);
            if (StringUtils.isNotEmpty(openId)) {
                User _temp = new User();
                _temp.setOpenID(openId);
                User user = userService.getUser(_temp);
                if (user != null && StringUtils.isNotEmpty(user.getBindMsisdn())) {
                    return user.getBindMsisdn();
                }
            }
        } catch (WxppException e) {
            e.printStackTrace();
        }
        return null;
    }
}
