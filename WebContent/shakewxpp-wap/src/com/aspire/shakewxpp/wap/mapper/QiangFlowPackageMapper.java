package com.aspire.shakewxpp.wap.mapper;

import com.aspire.shakewxpp.wap.entity.GrabFlowSubRedEnvelope;
import com.aspire.shakewxpp.wap.entity.User;

public interface QiangFlowPackageMapper extends BaseMapper<User>{
	/**
	 * 根据大红包ID查询未过期的小红包数量
	 * @param freId
	 * @return
	 */
	Integer queryBigFlowPackageInfo(Integer freId);
	
	/**
	 * 查询用户是否抢过指定的大红包
	 * @param grabFlowSubRedEnvelope
	 * @return
	 */
	Integer queryUserQiangSubFlowByFreId(GrabFlowSubRedEnvelope grabFlowSubRedEnvelope);
	
	void addQiangSubFlow(GrabFlowSubRedEnvelope grabFlowSubRedEnvelope);
	
}
