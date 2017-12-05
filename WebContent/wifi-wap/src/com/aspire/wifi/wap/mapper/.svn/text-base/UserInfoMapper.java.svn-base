package com.aspire.wifi.wap.mapper;

import java.util.List;
import java.util.Map;

import com.aspire.wifi.wap.entity.UserInfoEntity;

public interface UserInfoMapper {
	public UserInfoEntity queryUserInfo(String mobile);
	public List<UserInfoEntity> queryUserInfoByOnlyId(Map<String,Object> map);
	public List<UserInfoEntity> queryUserInfoByNickName(String nickName);
	public List<UserInfoEntity> nickname();
	public void updateUserInfo(UserInfoEntity userInfoEntity);
	public void addUserInfo(UserInfoEntity userInfoEntity);
	public String searchProvinceId(String province);
	public List<UserInfoEntity> searchProvinceNameList();
	public List<UserInfoEntity> searchCityNameList(UserInfoEntity userInfoEntity);
	public int queryUserInfoByRegDate(UserInfoEntity userInfoEntity);//查询马甲号
	public int searchMaxOnlyId();//查询马甲号
}
