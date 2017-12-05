package com.aspire.shakewxpp.wap.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.shakewxpp.wap.entity.WeixinVariable;
import com.aspire.shakewxpp.wap.exception.WxppException;
import com.aspire.shakewxpp.wap.mapper.WeixinVariableMapper;
import com.aspire.shakewxpp.wap.service.WeixinVariableService;
/**
 * 系统提示语内容配置  业务处理类
 * @author
 *
 */
@Service("weixinVariableService")
public class WeixinVariableServiceImpl implements WeixinVariableService {
	protected static Logger logger =  LoggerFactory.getLogger(WeixinVariableServiceImpl.class);
	
	@Resource(name = "weixinVariableMapper")
	private WeixinVariableMapper weixinVariableMapper;

	@Override
	public void updateWeixinVariable(WeixinVariable weixinVariable)
			throws WxppException {
		weixinVariableMapper.updateWeixinVariable(weixinVariable);
	}

	@Override
	public void deleteWeixinVariable(WeixinVariable weixinVariable)
			throws WxppException {
		weixinVariable.setStatus(WeixinVariable.UNAVAILABLE_STATUS);//设置状态为不可用
		weixinVariableMapper.updateWeixinVariable(weixinVariable);
	}

	@Override
	public List<WeixinVariable> queryWeixinVariableList(
			WeixinVariable weixinVariable) throws WxppException {		
		return weixinVariableMapper.queryWeixinVariableList(weixinVariable);
	}

	@Override
	public Integer queryWeixinVariableCount(WeixinVariable weixinVariable)
			throws WxppException {		
		return weixinVariableMapper.queryWeixinVariableCount(weixinVariable);
	}
	
	//查询系统提示语信息-单个查询
	public WeixinVariable queryWeixinVariableInfo (WeixinVariable weixinVariable)throws WxppException{
		WeixinVariable weixinVariable_temp = weixinVariableMapper.queryWeixinVariableInfo(weixinVariable);
		if(weixinVariable_temp == null){
			throw new WxppException("查询系统提示语信息结果为空");
		}
		return weixinVariable_temp;
	}
}
