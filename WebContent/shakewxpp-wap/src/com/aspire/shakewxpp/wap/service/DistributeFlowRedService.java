package com.aspire.shakewxpp.wap.service;

import java.util.List;

import com.aspire.shakewxpp.wap.entity.DistributeFlowRedEnvelope;
import com.aspire.shakewxpp.wap.exception.WxppException;

public interface DistributeFlowRedService {
		/**
	    * 根据用户的微信账号查询其红包列表
	    * @param openid
	    * @return
	    * @throws WxppException
	    */
	  public List<DistributeFlowRedEnvelope>  queryByopenid(DistributeFlowRedEnvelope dfe) throws WxppException;
	  
	  /**
	   *添加大红包数据 
	   * @param discoverable
	   */
	  public void addDistributeFlowRedEnvelope(DistributeFlowRedEnvelope discoverable) throws WxppException;
	  
	  /**
	   * 用于查询当日的红包的数量
	   * @return
	   * @throws WxppException
	   */
	  public int querCurrentCount() throws WxppException;
	  
	  /**
	   *查询用户每天抢包数量
	   * @return
	   */
	  public int querCurrentCountByOpenid(String openid)throws WxppException;
	  
	  /**
	   *查询每个大红包的数
	   * @return
	   */
	  public int querPerBigRedCount(DistributeFlowRedEnvelope discoverable)throws WxppException;
	  
	  
	  /**
	   *查询每个大红包的数

	   * @return
	   */
	  public int querPerBigRedCountByFreid(DistributeFlowRedEnvelope discoverable)throws WxppException;
	  
	  
	  /**
	   * 根据freId查询大红包的对象
	   * @param freId
	   * @return
	   * @throws WxppException
	   */
	  public DistributeFlowRedEnvelope queryDistributeFlowInfo(Integer freId)throws WxppException;
	  
	  
	  /**
	   * 根据openid和source查询大红包的对象
	   */
	  public int queryDistributeFlowByOpenidAndSource(
				DistributeFlowRedEnvelope discoverable)throws WxppException;
}
