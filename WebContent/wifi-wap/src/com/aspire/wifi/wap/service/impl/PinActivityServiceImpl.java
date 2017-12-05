package com.aspire.wifi.wap.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.wap.entity.PinActivityEntity;
import com.aspire.wifi.wap.entity.ShiYongEntity;
import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.mapper.PinActivityMapper;
import com.aspire.wifi.wap.service.intf.PinActivityService;

@Service("pinActivityService")
public class PinActivityServiceImpl implements PinActivityService {
    
	protected static Logger logger =  LoggerFactory.getLogger(PinActionServiceImpl.class);
	@Resource(name = "pinActivityMapper")
	private PinActivityMapper pinActivityMapper;
	
	/**
	 * 获取抢单参与人数
	 * @return
	 */
	
	public int getQiangDanTotalCount() throws WifiException {
		int activityCount=0;
       try{
    	   activityCount=pinActivityMapper.getQiangDanTotalCount();
       }catch(Exception e){
    	   e.printStackTrace();
    	   logger.error("获取抢单参与人数失败",e);
		   throw new WifiException("获取抢单参与人数失败\r\n" + e.getMessage());
       }
		return activityCount;
	}

	/**
	 * 返回活动列表
	 * @return
	 */
	
	public List<PinActivityEntity> getQiangDanDetails(BigInteger activityId)
			throws WifiException {
        List<PinActivityEntity> paList=null;
        try{
        	paList=pinActivityMapper.getQiangDanDetails(activityId);
        }catch(Exception e){
            e.printStackTrace();
     	   logger.error("获取信息失败",e);
 		   throw new WifiException("获取信息失败\r\n" + e.getMessage());
        }
		return paList;
	}

	 /**
	  * 增加活动的点击数
	  */
	
	public int addVisitPvCnt(PinActivityEntity pa) throws WifiException {
		int exeCount=0;
		 try{
			 exeCount=pinActivityMapper.addVisitPvCnt(pa);
	        }catch(Exception e){
	            e.printStackTrace();
	     	   logger.error(" 增加活动的点击数失败",e);
	 		   throw new WifiException(" 增加活动的点击数失败\r\n" + e.getMessage());
	        }
		return exeCount;
	}

	/**
	 * 根据状态返回活动的一个ID
	 * 
	 */
	public PinActivityEntity activityObject() throws WifiException {
		PinActivityEntity pe=null;
		try{
			 pe=pinActivityMapper.activityObject();
		}catch(Exception e){
			 e.printStackTrace();
	     	 logger.error(" 获取活动失败",e);
	 		 throw new WifiException(" 获取活动失败\r\n" + e.getMessage());
		}
		
		return  pe;
	}
	 /**
	  * 
	  * 获取除抢桌活动和使用活动除外的活动
	  * @return
	  */
	public List<PinActivityEntity> getAcitivityByquyouhui()
			throws WifiException {
		return pinActivityMapper.getAcitivityByquyouhui();
	}

	public PinActivityEntity getAcitivityInfoByActivityId(BigInteger activityId)
			throws WifiException {
		return pinActivityMapper.getAcitivityInfoByActivityId(activityId);
	}

	public void updatePinActivity(BigInteger activityId) throws WifiException {
		pinActivityMapper.updatePinActivity(activityId);
	}

	public void procUpdatePinActivity(Map paramMap) {
		pinActivityMapper.procUpdatePinActivity(paramMap);
	}
}
