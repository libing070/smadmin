package com.aspire.shakewxpp.wap.service;

import com.aspire.shakewxpp.wap.entity.QiangResult;
import com.aspire.shakewxpp.wap.entity.User;
import com.aspire.shakewxpp.wap.exception.WxppException;

public interface QiangFlowService {
	/**
	 * 抽流量红包
	 * @param openid
	 * @return 流量红包的个数
	 * @throws WxppException
	 */
	QiangResult qiangFlowRedEnvelope(User user,int freId) throws WxppException;
}
