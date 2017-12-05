package com.aspire.shakewxpp.wap.mapper;

import java.util.Map;

import com.aspire.shakewxpp.wap.entity.User;

public interface UserMapper extends BaseMapper<User>{
	public User queryUserOne(User user);
	public void insertOpenId(User user);
	public int updateUser(User user);
	public Integer checkForMsisdnIsBind(Map<String,String> paraMap);
	public Map<String,String> queryAppMessageByWeixinAppNo(String weixinAppNo);
	public Integer queryShirouserIdByOpenid(String openid);
	public String queryWeixinAppNoByOpenid(String openid);
	Integer queryShirouserIdByWeixinAppNo(String weixinAppNo);
	String queryWeixinAppNameVieByShirouserId(Integer shirouserId);
	public String checkBindUser(String openId);
	public String queryUserGroup(String openId);
}
