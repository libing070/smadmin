package com.aspire.shakewxpp.wap.service;

import java.util.List;

import com.aspire.shakewxpp.wap.entity.WeixinVariable;
import com.aspire.shakewxpp.wap.exception.WxppException;

public interface WeixinVariableService {
	
	//修改系统提示语配置项
	void updateWeixinVariable (WeixinVariable weixinVariable)throws WxppException;
	
	//删除系统提示语配置项
	void deleteWeixinVariable (WeixinVariable weixinVariable)throws WxppException;
	
	//查询系统提示语配置列表
	List<WeixinVariable> queryWeixinVariableList (WeixinVariable weixinVariable)throws WxppException;
	Integer queryWeixinVariableCount (WeixinVariable weixinVariable)throws WxppException;
	
	//查询系统提示语信息-单个查询
	WeixinVariable queryWeixinVariableInfo (WeixinVariable weixinVariable)throws WxppException;
	
	//根据variableName（提示语name获取提示语内容，并将内部的预定义参数替换）
	//String queryWeixinVariableContent(HashMap map ,String variableName) throws WxppException;
}
