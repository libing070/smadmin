package com.aspire.wifi.wap.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aspire.wifi.wap.entity.UserInfoEntity;
import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.mapper.UserInfoMapper;
import com.aspire.wifi.wap.service.intf.UserInfoService;

@Service("userInfoService")
@Scope("prototype")
public class UserInfoServiceImpl implements UserInfoService {
	protected static Logger logger =  LoggerFactory.getLogger(UserInfoServiceImpl.class);

	@Resource(name = "userInfoMapper")
	private UserInfoMapper userInfoMapper;
	
	public void addUserInfo(UserInfoEntity userInfoEntity) throws Exception {
		try{
			userInfoMapper.addUserInfo(userInfoEntity);
		}catch(Exception e){
			throw new WifiException("添加用户信息异常!",e);
		}
	}
	
	public void updateUserInfo(UserInfoEntity userInfoEntity) throws Exception {
		try{
			userInfoMapper.updateUserInfo(userInfoEntity);
		}catch(Exception e){
			logger.debug("修改用户信息异常，用户手机号："+userInfoEntity.getMobile());
			throw new Exception("修改用户信息异常",e);
		}
	}

	/**
	 * 根据手机号码返回userinfo的信息
	 * @return
	 */
	public UserInfoEntity queryUserInfo(String mobile) throws Exception {
		UserInfoEntity result= new UserInfoEntity();
		try{
			result=userInfoMapper.queryUserInfo(mobile);
		}catch(Exception e){
			throw new WifiException("查询用户信息异常!",e);
		}
		return result;
	}
	
	/**
	 * 根据昵称返回userinfo的信息
	 * @return
	 */
	public List<UserInfoEntity> queryUserInfoByNickName(String nickname) throws Exception {
		List<UserInfoEntity> result= new ArrayList<UserInfoEntity>();
		try{
			result=userInfoMapper.queryUserInfoByNickName(nickname);
		}catch(Exception e){
			throw new WifiException("查询用户信息异常!",e);
		}
		return result;
	}

	/**
	 * 查询省份信息
	 */
	
	public List<UserInfoEntity> searchProvinceNameList() throws WifiException {
		try {
			return userInfoMapper.searchProvinceNameList();
		} catch (Exception e) {
			logger.error("查询省份信息异常!");
			throw new WifiException("查询省份信息异常!",e);
		}
	}

	/**
	 * 查询城市信息
	 */
	
	public List<UserInfoEntity> searchCityNameList(UserInfoEntity userInfoEntity)
			throws WifiException {
		try {
			return userInfoMapper.searchCityNameList(userInfoEntity);
		} catch (Exception e) {
			throw new WifiException("查询城市信息异常!",e);
		}
	}

	
	public String searchProvinceId(String province) throws WifiException {
		try {
			return userInfoMapper.searchProvinceId(province);
		} catch (Exception e) {
			throw new WifiException("查询城市信息异常!",e);
		}
	}

	public int queryUserInfoByRegDate(UserInfoEntity userInfoEntity)
			throws WifiException {
		int result=-1;
		try{
			result=userInfoMapper.queryUserInfoByRegDate(userInfoEntity);
		}catch(Exception e){
			throw new WifiException("添加用户信息异常!",e);
		}
		return result;
	}
	public int searchMaxOnlyId() throws WifiException {
		try {
			return userInfoMapper.searchMaxOnlyId();
		} catch (Exception e) {
			throw new WifiException("查询最大标识符异常!",e);
		}
	}
}
