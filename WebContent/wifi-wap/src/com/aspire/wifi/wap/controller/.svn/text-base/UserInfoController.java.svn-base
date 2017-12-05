package com.aspire.wifi.wap.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aspire.wifi.wap.entity.UserInfoEntity;
import com.aspire.wifi.wap.service.intf.UserInfoService;
@Controller
public class UserInfoController {
	private static final Logger logger =  LoggerFactory.getLogger(UserInfoController.class);
	
	@Resource(name = "userInfoService")
	private UserInfoService userInfoService;
	/**
	 * 根据手机号码查询单条的信息
	 * @return
	 */
	@RequestMapping(value = "/queryUser")//@RequestParam("mobile")String mobile
	public @ResponseBody
	Map<String, ? extends Object> queryUser(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfoEntity uie=null;
		Subject currentUser = SecurityUtils.getSubject();
		String mobile = currentUser.getPrincipal().toString();
        //String mobile = "13412345678";
		String nickName="";
		char sex='男';
		byte[] headShow=null;
		try{
			uie=userInfoService.queryUserInfo(mobile);
			if(uie!=null){
				nickName=uie.getNickname();
				sex=uie.getSex();
				headShow=uie.getHead_show();
			}
			resultMap.put("sex", sex);
			resultMap.put("headShow", headShow);
			resultMap.put("nickName", nickName);
			resultMap.put("CODE", "TRUE");
		}catch(Exception e){
			logger.error("根据手机号码查询单条的信息异常", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}
	
	/**
	 * 根据手机号码头像
	 * @return
	 */
	@RequestMapping(value = "/queryUserHeadShow")
	public @ResponseBody
	void  queryUserHeadShow(HttpServletResponse response){
		 OutputStream out = null;
		 UserInfoEntity uie=null;
		 Subject currentUser = SecurityUtils.getSubject();
		 String mobile = currentUser.getPrincipal().toString();
		 byte[] picBytes=null;
		 try{
			 out = response.getOutputStream();
			 uie=userInfoService.queryUserInfo(mobile);
			 if(uie!=null){
				 picBytes=uie.getHead_show(); 
			 }
	         IOUtils.write(picBytes, out);
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally {
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
	
	
}
