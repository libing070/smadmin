package com.aspire.shakewxpp.wap.service;

import com.aspire.shakewxpp.wap.entity.DrawResult;
import com.aspire.shakewxpp.wap.entity.User;
import com.aspire.shakewxpp.wap.exception.WxppException;

public interface DrawService {
	/**
	 * 抽流量红包
	 * @param openid
	 * @return 流量红包的个数
	 * @throws WxppException
	 */
	DrawResult drawFlowRedEnvelope(User user) throws WxppException;
	
	/**
	 * 增加大红包
	 * @param openid
	 * @param addType
	 * @throws Exception
	 */
	int addDistributeFlowRedEnvelope(String openid,int addType) throws Exception;
}
