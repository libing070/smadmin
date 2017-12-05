package com.aspire.wifi.wap.service.intf;

import java.util.List;

import com.aspire.wifi.wap.entity.UserInfoEntity;
import com.aspire.wifi.wap.exception.WifiException;

public interface UserInfoService {
	public UserInfoEntity queryUserInfo(String mobile) throws Exception;
	public List<UserInfoEntity> queryUserInfoByNickName(String nickName) throws Exception;
	public void updateUserInfo(UserInfoEntity userInfoEntity) throws Exception;
	public void addUserInfo(UserInfoEntity userInfoEntity) throws Exception;
	public List<UserInfoEntity> searchProvinceNameList()throws WifiException;
	public String searchProvinceId(String province)throws WifiException;
	public List<UserInfoEntity> searchCityNameList(UserInfoEntity userInfoEntity)throws WifiException;
	public int queryUserInfoByRegDate(UserInfoEntity userInfoEntity) throws WifiException;//查询马甲号
	public int searchMaxOnlyId() throws WifiException;//查询马甲号
}
