package com.aspire.shakewxpp.wap.mapper;

import java.util.List;
import java.util.Map;

import com.aspire.shakewxpp.wap.entity.DistributeFlowRedEnvelope;
import com.aspire.shakewxpp.wap.entity.GrabFlowSubRedEnvelope;
import com.aspire.shakewxpp.wap.exception.WxppException;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.mapper
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/26
 * @Version V1.0 Update Logs:
 */
public interface GrabFlowSubRedEnvelopeMapper {
	GrabFlowSubRedEnvelope findEnvelopeByEnvelope(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws Exception;

	void updateEnvelopeByEnvelope(GrabFlowSubRedEnvelope grabFlowSubRedEnvelope)
			throws Exception;

	public void searchRedBagResultInfo(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;

	public void saveRedBagInfo(GrabFlowSubRedEnvelope grabFlowSubRedEnvelope)
			throws WxppException;

	public List<GrabFlowSubRedEnvelope> searchUserSnagRedBagList(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;

	public Integer searchUserSnagRedBagListCount(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;

	public Integer searchRedBagQiangGuoCount(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;

	public Integer searchRedBagPastDueCount(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;

	public Integer searchBigRedBagCount(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;

	public Integer searchHaveRedBagCount(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;

	public List<GrabFlowSubRedEnvelope> searchFriendSubRedList(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;

	public Float searchUserSumSubRedInfo(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;

	public DistributeFlowRedEnvelope searchDistributeFlowRedInfo(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;

	public GrabFlowSubRedEnvelope searchRedBagQiangGuoInfo(
			GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;

	public Integer findBindMsisdnCount(Map<String, String> map)
			throws WxppException;

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
	 * 判断用户是否与该红包有关系
	 * @param grabFlowSubRedEnvelope
	 * @return
	 * @throws WxppException
	 */
	public int relationForFlowFlowRedPackage(GrabFlowSubRedEnvelope grabFlowSubRedEnvelope) throws WxppException;
	
}
