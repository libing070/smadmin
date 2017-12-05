package com.aspire.shakewxpp.wap.service.impl;

import com.aspire.shakewxpp.wap.entity.DistributeFlowRedEnvelope;
import com.aspire.shakewxpp.wap.exception.WxppException;
import com.aspire.shakewxpp.wap.mapper.DistributeFlowRedMapper;
import com.aspire.shakewxpp.wap.service.DistributeFlowRedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("distributeFlowRedService")
public class DistributeFlowRedServiceImpl implements DistributeFlowRedService {
    protected static Logger logger = LoggerFactory.getLogger(DistributeFlowRedServiceImpl.class);

    @Resource(name = "distributeFlowRedMapper")
    private DistributeFlowRedMapper distributeFlowRedMapper;


    @Override
    public void addDistributeFlowRedEnvelope(DistributeFlowRedEnvelope discoverable) throws WxppException {
        try {
            distributeFlowRedMapper.addDistributeFlowRedEnvelope(discoverable);
        } catch (Exception e) {
            logger.error("录入大红包数据操作失败", e);
            throw new WxppException("传入参数为空，请重新请求");
        }

    }

	/**
	   * 用于查询当日的红包的数量
	   * @return
	   * @throws WxppException
	   */
	  public int querCurrentCount()throws WxppException {
		  int count=0;
		  try{
			  count=distributeFlowRedMapper.querCurrentCount();
		  }catch(Exception e){
			  logger.error("查询当日的红包的数量异常", e);
			  throw new WxppException("查询当日的红包的数量失败"); 
		  }
		  return count;
	  }

   /***
    * 根据用户的微信账号查询用户的大红包列表

    * @return
    * @throws WxppException 
    */
    public List<DistributeFlowRedEnvelope> queryByopenid(
    		DistributeFlowRedEnvelope dfe) throws WxppException {
    	List<DistributeFlowRedEnvelope> bigRedEnvelopeList=null;
    	try{
    		bigRedEnvelopeList=distributeFlowRedMapper.queryByopenid(dfe);
    	}catch(Exception e){
    		logger.error("查询用户的大红包列表异常", e);
		  	throw new WxppException("查询用户的大红包列表失败"); 
    	}
    	return bigRedEnvelopeList;
    }

    
    /**
	   *查询用户每天抢包数量
	   * @return
	   */
	  public int querCurrentCountByOpenid(String openid)throws WxppException{
		  int count=0;
		  try{
			 count=distributeFlowRedMapper.querCurrentCountByOpenid(openid);
		  }catch(Exception e){
		  		logger.error("查询用户每天抢包数量异常", e);
		  		throw new WxppException("查询用户每天抢包数量异常");
		  }
		  return count;
	  }
	  
	  
	  /**
	   *查询每个大红包的数

	   * @return
	   */
	  public int querPerBigRedCount(DistributeFlowRedEnvelope discoverable)throws WxppException{
		  int perBigRedCount=0;
		  try{
			  perBigRedCount=distributeFlowRedMapper.querPerBigRedCount(discoverable);
		  }catch(Exception e){
			  	logger.error("查询每个大红包的数异常", e);
  				throw new WxppException("查询每个大红包的数异常");
		  }
		  return perBigRedCount; 
	  	}
	  
	  
	  /**
	   *查询每个大红包的数

	   * @return
	   */
	  public int querPerBigRedCountByFreid(DistributeFlowRedEnvelope discoverable)throws WxppException{
		  int perBigRedCount=0;
		  try{
			  perBigRedCount=distributeFlowRedMapper.querPerBigRedCountByFreid(discoverable);
		  }catch(Exception e){
			  	logger.error("查询每个大红包的数异常", e);
  				throw new WxppException("查询每个大红包的数异常");
		  }
		  return perBigRedCount; 
	  	}
	  
	  /**
	   * 根据freId查询大红包的对象
	   * @param freId
	   * @return
	   * @throws WxppException
	   */
	  public DistributeFlowRedEnvelope queryDistributeFlowInfo(Integer freId)throws WxppException{
		  DistributeFlowRedEnvelope dfr=null;
		  try{
			  dfr=distributeFlowRedMapper.queryDistributeFlowInfo(freId);
		  }catch(Exception e){
			  	logger.error("根据freId查询大红包的对象异常", e);
  				throw new WxppException("根据freId查询大红包的对象异常");
		  }
		  return dfr; 
	  }

    /**
     * 
     */
	public int queryDistributeFlowByOpenidAndSource(
			DistributeFlowRedEnvelope discoverable) throws WxppException {
		int freId;
		try{
			freId = distributeFlowRedMapper.queryDistributeFlowByOpenidAndSource(discoverable);
			  
		  }catch(Exception e){
			  	logger.error("根据openid和source查询大红包的对象异常", e);
				throw new WxppException("根据openid和source查查询大红包的对象异常");
		  }
		  return freId; 
	}
}
