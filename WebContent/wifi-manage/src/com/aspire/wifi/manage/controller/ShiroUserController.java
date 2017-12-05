package com.aspire.wifi.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aspire.wifi.manage.entity.ShiroUser;
import com.aspire.wifi.manage.exception.WxppException;
import com.aspire.wifi.manage.service.IShiroUserService;
@Controller
public class ShiroUserController {

	protected static Logger logger = LoggerFactory.getLogger(ShiroUserController.class);
	
	@Resource(name = "shiroUserService")
	private IShiroUserService shiroUserService;
	
	/**
	 * 返回后台用户列表页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/shiroUsersPage.tv", method = RequestMethod.GET)
	public String shiroUsersPage(HttpServletRequest request,HttpServletResponse response) throws Exception {
		return "/user/shiroUserList";
	}
	
	/**
	 * 进入新建后台用户信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/shiroUser.tv", method = RequestMethod.GET)
	public String getCreateShiroUserPage() throws Exception {
		return "/user/createShiroUser";
	}
	
	/**
	 * 获取后台用户列表数据
	 * @param request
	 * @param shiroUser
	 * @return
	 */
	@RequestMapping(value = "/shiroUserList.tv", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> getBussinessPackageList(HttpServletRequest request,ShiroUser shiroUser){
		if (logger.isDebugEnabled()) {
			logger.debug("进入获取用户列表方法，参数 = " + shiroUser);
		}
		if(shiroUser == null){
			shiroUser = new ShiroUser();
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			shiroUser.setPagination(shiroUser);
			Integer shirouserId = (Integer)request.getSession().getAttribute("currentUser_id");
			if(shirouserId==null){
				logger.error("未能获取当前用户表的id");
				resultMap.put("msg", "未能获取当前用户表的id！");
				resultMap.put("CODE", "FALSE");
				return resultMap;
			}
			shiroUser.setId(shirouserId);
			List<ShiroUser> list = shiroUserService.getShiroUserList(shiroUser);
			Integer count = shiroUserService.getShiroUserListCount(shiroUser);
			resultMap.put("list", list);
			resultMap.put("totalCount", count);
			resultMap.put("CODE", "TRUE");
		}catch(Exception e){
			logger.error("查询后台用户信息列表异常", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}

	
	/**
	 * 验证帐号是否存在 
	 * @param request
	 * @param shiroUser
	 * @return
	 * @throws WxppException
	 */
	@RequestMapping(value = "/isShiroUser.tv", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object>
	isShiroUser(HttpServletRequest request,ShiroUser shiroUser) throws WxppException{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			Integer count = shiroUserService.getShiroUserListCount(shiroUser);
			if(count != 0 && count != null){
				resultMap.put("CODE", "FALSE");
			}else{
				resultMap.put("CODE", "TRUE");
			}
		}catch(Exception e){
			logger.error("验证帐号异常", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}
}
