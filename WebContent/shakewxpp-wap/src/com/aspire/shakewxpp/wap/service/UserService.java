package com.aspire.shakewxpp.wap.service;

import java.util.Map;

import com.aspire.shakewxpp.wap.entity.User;
import com.aspire.shakewxpp.wap.exception.WxppException;

public interface UserService {
	//获取单个用户信息
	public User getUser(User user) throws WxppException;
	
	public void insertOpenId(User user) throws WxppException;
	
	//用户号码绑定
	public void bindMsisdn(User user) throws WxppException;
	
	//用户号码解绑
	public void unBindMsisdn(User user) throws WxppException;
	
	//用户信息变更
	public void updateUserInfo(User user) throws WxppException;
	
	//检查手机号是否已绑定其它的微信号
	public boolean checkForMsisdnIsBind(Map<String,String> paraMap)  throws WxppException;
	
	public Map<String,String> queryAppMessageByWeixinAppNo(String weixinAppNo);
	
	public Integer queryShirouserIdByOpenid(String openid);
	
	public String queryWeixinAppNoByOpenid(String openid);
	
	Integer queryShirouserIdByWeixinAppNo(String weixinAppNo);
	
	String queryWeixinAppNameVieByShirouserId(Integer shirouserId);
	
	String queryUserGroup(String openid);
	
}
