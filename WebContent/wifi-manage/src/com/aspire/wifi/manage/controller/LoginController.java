package com.aspire.wifi.manage.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    protected static Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "/index";
    }

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping(value = "/loginHead", method = RequestMethod.GET)
    public String getLoginHeadPage() {
        return "/index_head";
    }
    
    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping(value = "/loginFood", method = RequestMethod.GET)
    public String getLoginFoodPage() {
        return "/index_foot";
    }
    
    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping(value = "/loginAction", method = RequestMethod.GET)
    public String getLoginActionPage() {
        return "/index_login";
    }
    /**
     * 接收ajax登录请求
     *
     * @param account  登录帐号
     * @param password 登录密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, String> Login(
            @RequestParam("account") String account,
            @RequestParam("password") String password
    ) {
        Subject currentUser = SecurityUtils.getSubject();
        Map<String, String> returnMap = new HashMap<String, String>();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(account, password);
            token.setPassword(password.toCharArray());
            token.setRememberMe(false);
            try {
                currentUser.login(token);
                returnMap.put("result", "yes");
            } catch (AuthenticationException e) {
                logger.debug("登录失败:", e);
                token.clear();
                returnMap.put("result", "no");
                returnMap.put("message", "登录失败，用户名或密码不正确");
            }
        }
        return returnMap;
    }

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

}
