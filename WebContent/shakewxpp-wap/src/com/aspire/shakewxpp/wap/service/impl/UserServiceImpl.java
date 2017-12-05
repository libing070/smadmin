package com.aspire.shakewxpp.wap.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.shakewxpp.wap.entity.User;
import com.aspire.shakewxpp.wap.exception.WxppException;
import com.aspire.shakewxpp.wap.mapper.UserMapper;
import com.aspire.shakewxpp.wap.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	protected static Logger logger =  LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource(name = "userMapper")
	private UserMapper userMapper;
	
	/**
	 * 单个用户信息查询
	 */
	@Override
	public User getUser(User user) throws WxppException{
		User user_ = null;
		if(user!=null){
			user_ = userMapper.queryUserOne(user);
		}else{
			logger.error("传入参数为空");
			throw new WxppException("传入参数为空，请重新请求");
		}
		return user_;
	}
		
	public void insertOpenId(User user) throws WxppException{
		try{
			userMapper.insertOpenId(user);
		}catch(Exception e){
			logger.error("录入用户资料失败",e);
			throw new WxppException("传入参数为空，请重新请求");
		}
	}
	
	//用户号码绑定
	public void bindMsisdn(User user) throws WxppException{
		try{
			updateUserInfo(user);
		}catch(WxppException we){
			logger.error("号码绑定失败！",we);
			throw new WxppException(we.getMessage());
		}catch(Exception e){
			logger.error("号码绑定失败！",e);
			throw new WxppException("用户号码绑定失败，请稍候再试！");
		}
	}
	
	//用户号码解绑
	public void unBindMsisdn(User user) throws WxppException{		
		try{
			user.setField("bindMsisdn", "bindMsisdn");
			updateUserInfo(user);
		}catch(WxppException we){
			logger.error("号码绑定失败！",we);
			throw new WxppException(we.getMessage());
		}catch(Exception e){
			logger.error("号码绑定失败！",e);
			throw new WxppException("用户号码绑定失败，请稍候再试！");
		}
	}
	
	//用户信息变更
	public void updateUserInfo(User user)throws WxppException{
		if(user!=null){
			try{
				userMapper.updateUser(user);
			}catch(Exception e){
				logger.error("用户信息变更失败！",e);
				throw new WxppException("用户信息变更失败，请稍候再试！");
			}
		}else{
			logger.error("传入参数为空");
			throw new WxppException("传入参数为空，请重新请求");
		}
	}
	
	//检查手机号是否已绑定其它的微信号
	public boolean checkForMsisdnIsBind(Map<String,String> paraMap)  throws WxppException{
		boolean flag = false;
		if(StringUtils.isNotEmpty(paraMap.get("msisdn")) && StringUtils.isNotEmpty(paraMap.get("shirouserId"))){
			try{
				Integer count = userMapper.checkForMsisdnIsBind(paraMap);
				if(count>0){
					flag = true;
				}
			}catch(Exception e){
				logger.error("检查手机号是否已绑定其它的微信号出现异常！",e);
				throw new WxppException("检查手机号是否已绑定其它的微信号出现异常，请稍候再试！");
			}
		}else{
			logger.error("传入参数msisdn为空");
			throw new WxppException("传入msisdn参数为空，请重新请求");
		}
		return flag;
	}
	
	public Map<String,String> queryAppMessageByWeixinAppNo(String weixinAppNo){
		return userMapper.queryAppMessageByWeixinAppNo(weixinAppNo);
	}
	
	public Integer queryShirouserIdByOpenid(String openid){
		return userMapper.queryShirouserIdByOpenid(openid);
	}
	
	public String queryWeixinAppNoByOpenid(String openid){
		return userMapper.queryWeixinAppNoByOpenid(openid);
	}
	
	public Integer queryShirouserIdByWeixinAppNo(String weixinAppNo){
		return userMapper.queryShirouserIdByWeixinAppNo(weixinAppNo);
	}
	
	public String queryWeixinAppNameVieByShirouserId(Integer shirouserId){
		return userMapper.queryWeixinAppNameVieByShirouserId(shirouserId);
	}
	
	public String queryUserGroup(String openid){
		return userMapper.queryUserGroup(openid);
	}
}
