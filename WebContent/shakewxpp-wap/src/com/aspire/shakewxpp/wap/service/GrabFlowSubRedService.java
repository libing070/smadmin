package com.aspire.shakewxpp.wap.service;

import java.util.List;
import java.util.Map;

import com.aspire.shakewxpp.wap.entity.DistributeFlowRedEnvelope;
import com.aspire.shakewxpp.wap.entity.GrabFlowSubRedEnvelope;
import com.aspire.shakewxpp.wap.exception.WxppException;

public interface GrabFlowSubRedService {

	public Integer searchRedBagResultInfo(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;

	public void saveRedBagInfo(GrabFlowSubRedEnvelope grabFlowSubRedEnvelope)
			throws WxppException;

	public List<GrabFlowSubRedEnvelope> searchUserSnagRedBagList(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;

	public List<GrabFlowSubRedEnvelope> searchFriendSubRedList(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;

	public Float searchUserSumSubRedInfo(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;

	public DistributeFlowRedEnvelope searchDistributeFlowRedInfo(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;

	public Integer searchUserSnagRedBagListCount(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;

	public GrabFlowSubRedEnvelope searchRedBagQiangGuoInfo(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;

	/**
	 * 查询手机号码输入正确
	 * 
	 * @param configPojo
	 * @return
	 * @throws WxppException
	 */
	public Integer findBindMsisdnCount(Map<String, String> map)
			throws WxppException;

	/**
	 * 判断用户领取抢到小红包时，是否已绑定
	 * 
	 * @param grabFlowSubRedEnvelope
	 * @return
	 * @throws WxppException
	 */
	public Integer searchRedBagQiangGuoCount(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;

	/**
	 * 查询每个大红包分享的小红包的数量
	 * 
	 * @param freId
	 * @return
	 * @throws WxppException
	 */
	public int querySmallCount(GrabFlowSubRedEnvelope gfs) throws WxppException;

	/**
	 * 查询领取的小红包信息
	 * 
	 * @param freId
	 * @return
	 * @throws WxppException
	 */
	public List<GrabFlowSubRedEnvelope> queryLingQu(GrabFlowSubRedEnvelope gfs)
			throws WxppException;

	/**
	 * 查询领取的小红包的流量币总和
	 * 
	 * @param freId
	 * @return
	 * @throws WxppException
	 */
	public Float queryFlowSum(Integer freId) throws WxppException;
	
	
	/**
	 * 判断用户是否抢过大红包里的小红包
	 * @param grabFlowSubRedEnvelope
	 * @return
	 * @throws WxppException
	 */
	public int checkeHadQiangFlowRedPackage(GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;
	
	
	public int relationForFlowFlowRedPackage(GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;
}
