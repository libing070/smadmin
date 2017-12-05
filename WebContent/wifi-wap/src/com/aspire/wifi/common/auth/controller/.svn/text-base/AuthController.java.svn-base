package com.aspire.wifi.common.auth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aspire.wifi.common.auth.constants.AuthConstants;
import com.aspire.wifi.common.auth.entity.AuthDeviceAccountInfo;
import com.aspire.wifi.common.auth.service.AuthService;
import com.aspire.wifi.wap.entity.SysConfigEntity;
import com.aspire.wifi.wap.service.intf.WIFISysMessageService;

@Controller
public class AuthController {

	protected static Logger logger = LoggerFactory
			.getLogger(AuthController.class);

	@Resource(name = "authService")
	private AuthService authService;

	@Resource(name = "wifiSysMessageService")
	WIFISysMessageService wifiSysMessageService;

	/**
	 * 获取认证账号信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAuthDeviceAccountInfo", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getAuthDeviceAccountInfo(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Subject currentUser = SecurityUtils.getSubject();
			if (currentUser.isAuthenticated()) {
				String msisdn = (String) currentUser.getPrincipal();
				AuthDeviceAccountInfo authDeviceAccountInfo = authService
						.getAuthDeviceAccountInfo();
				resultMap.put("authDeviceAccountInfo", authDeviceAccountInfo);
				logger.info("用户【" + msisdn + "】登陆成功，获取认证账号信息【"
						+ authDeviceAccountInfo.toString() + "】");

				String authDeviceUrl = null;
				SysConfigEntity sysConfigEntity = wifiSysMessageService
						.getSysConfigEntity(AuthConstants.SYSCONFIG_WIFI_WEB_AUTH_URL);
				if (sysConfigEntity != null) {
					authDeviceUrl = sysConfigEntity.getConfigParaValue();
				}
				resultMap.put("authDeviceUrl", authDeviceUrl);
				logger.info("获取认证鉴权URL：" + authDeviceUrl);
			} else {
				resultMap.put("authDeviceAccountInfo", null);
				logger.info("用户未登陆，获取认证账号信息失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return resultMap;
	}

}
