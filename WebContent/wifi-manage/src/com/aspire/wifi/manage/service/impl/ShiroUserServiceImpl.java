package com.aspire.wifi.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.manage.entity.ShiroUser;
import com.aspire.wifi.manage.exception.WxppException;
import com.aspire.wifi.manage.mapper.ShiroUserMapper;
import com.aspire.wifi.manage.service.IShiroUserService;

@Service("shiroUserService")
public class ShiroUserServiceImpl implements IShiroUserService {
	protected static Logger logger =  LoggerFactory.getLogger(ShiroUserServiceImpl.class);

	@Resource(name = "shiroUserMapper")
	private ShiroUserMapper shiroUserMapper;

	public ShiroUser getByAccount(String account) throws WxppException {
		try {
			ShiroUser user = shiroUserMapper.queryOneByName(account);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WxppException("用户登录----->根据用户名查询用户失败\r\n" + e.getMessage());
		}
	}

	
	/**
	 * 查询用户信息
	 */
	public List<ShiroUser> getShiroUserList(ShiroUser shiroUser)throws WxppException{
		logger.debug("ShiroUserServiceImpl.getShiroUserList=========begin;");
		try{
			return shiroUserMapper.getShiroUserList(shiroUser);
		}catch(Exception e){
			logger.error("查询用户信息列表异常",e);
			throw new WxppException("查询用户信息列表异常 ，请稍候再试！");
		}
	}
	
	public Integer  getShiroUserListCount(ShiroUser shiroUser)throws WxppException{
		try{
			return shiroUserMapper.getShiroUserListCount(shiroUser);
		}catch(Exception e){
			logger.error("查询用户信息列表异常",e);
			throw new WxppException("查询用户信息列表异常 ，请稍候再试！");
		}
	}
	
	/**
	 * 验证新增用户名是否存在
	 */
	public Integer isShiroUserList(String account)throws WxppException{
		logger.debug("ShiroUserServiceImpl.isShiroUserList=========begin;");
		try{
			return shiroUserMapper.isShiroUserList(account);
		}catch(Exception e){
			logger.error("验证用户名异常",e);
			throw new WxppException("验证用户名异常 ，请稍候再试！");
		}
	}
	
}
