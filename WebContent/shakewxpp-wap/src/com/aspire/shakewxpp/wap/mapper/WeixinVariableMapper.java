package com.aspire.shakewxpp.wap.mapper;

import java.util.List;

import com.aspire.shakewxpp.wap.entity.User;
import com.aspire.shakewxpp.wap.entity.WeixinVariable;

/**
 * 系统提示语内容配置
 * @author
 *
 */
public interface WeixinVariableMapper extends BaseMapper<User>{
	//修改系统提示语配置项
	int updateWeixinVariable (WeixinVariable weixinVariable);
	//查询系统提示语配置列表
	List<WeixinVariable> queryWeixinVariableList (WeixinVariable weixinVariable);
	Integer queryWeixinVariableCount (WeixinVariable weixinVariable);
	//查询单个系统提示语信息
	WeixinVariable queryWeixinVariableInfo (WeixinVariable weixinVariable);
}
