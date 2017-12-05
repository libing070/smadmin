package com.aspire.shakewxpp.wap.mapper;

import java.util.List;

import com.aspire.shakewxpp.wap.entity.DistributeFlowRedEnvelope;
import com.aspire.shakewxpp.wap.entity.User;
import com.aspire.shakewxpp.wap.exception.WxppException;

public interface DistributeFlowRedMapper extends BaseMapper<User>{
	Integer getFreId();
	/**
	 *添加大红包数据 
	 * @param discoverable
	 */
	public void addDistributeFlowRedEnvelope(DistributeFlowRedEnvelope distributeFlowRedEnvelope);
	
	/**
	 *查询当日的红包数量 
	 * @return
	 */
	public int querCurrentCount();
	
	/**
	 * 根据微信号查询用户的大红包列表
	 * @param openid
	 * @return
	 */
	public List<DistributeFlowRedEnvelope> queryByopenid(DistributeFlowRedEnvelope dfe);
	
	/**
	 *查询用户每天抢包数量
	 * @return
	 */
	public int querCurrentCountByOpenid(String openid);
	
	
	/**
	 *查询每个大红包的数
	 * @return
	 */
	public int querPerBigRedCount(DistributeFlowRedEnvelope discoverable);
	
	
	/**
	 *查询每个大红包的数
	 * @return
	 */
	public int querPerBigRedCountByFreid(DistributeFlowRedEnvelope discoverable);
	
	
	/**
	 * 是否已赠送过流量红包（绑定号码）
	 * @param discoverable
	 * @return
	 */
	int checkSendFolwDone(DistributeFlowRedEnvelope discoverable);
	
	
	/**
	 * 根据freId查询openid;
	 * @param freId
	 * @return
	 */
	String queryOpenidByFreid(int freId);

	void addJangliFlowRedEnvelope(DistributeFlowRedEnvelope distributeFlowRedEnvelope);

	int checkTestUser(String openid);
	
	  /**
	   * 根据freId查询大红包的对象
	   * @param freId
	   * @return
	   * @throws WxppException
	   */
	  public DistributeFlowRedEnvelope queryDistributeFlowInfo(Integer freId);
	  
	  
	  
	  
	  public int queryDistributeFlowByOpenidAndSource(DistributeFlowRedEnvelope discoverable);
}
