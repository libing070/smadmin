package com.aspire.wifi.wap.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.wap.entity.PinActionEntity;
import com.aspire.wifi.wap.entity.PinCreateTableDetailEntity;
import com.aspire.wifi.wap.entity.PinCreateTableEntity;
import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.mapper.PinActionMapper;
import com.aspire.wifi.wap.mapper.PinCreateTableDetailMapper;
import com.aspire.wifi.wap.mapper.PinCreateTableMapper;
import com.aspire.wifi.wap.service.intf.PinActionService;

@Service("pinActionService")
public class PinActionServiceImpl implements PinActionService {
	
	protected static Logger logger =  LoggerFactory.getLogger(PinActionServiceImpl.class);
	@Resource(name = "pinActionMapper")
	private PinActionMapper pinActionMapper;
	
	@Resource(name = "pinCreateTableMapper")
	private PinCreateTableMapper pinCreateTableMapper;
	
	@Resource(name = "pinCreateTableDetailMapper")
	private PinCreateTableDetailMapper pinCreateTableDetailMapper;
	
	/**
	 * 每天的抢单数 
	 * @return
	 */
	public int everyDayCount() throws WifiException {
		int countEveryDay=0;
		try{
			countEveryDay=pinActionMapper.everyDayCount();
		}catch(Exception e){
			e.printStackTrace();
			throw new WifiException("获取每天抢单的数量失败\r\n" + e.getMessage());
		}
		return countEveryDay;
	}

	/**
	 * 抢单 
	 * @return
	 */
	public int doQiangDan(PinActionEntity pa) throws WifiException {
		int executeCount=0;
        try{
        	executeCount=pinActionMapper.doQiangDan(pa);
		}catch(Exception e){
			e.printStackTrace();
			throw new WifiException("抢单失败\r\n" + e.getMessage());
		}
		return executeCount;
	}

	/**
	 * 更新组建创建桌的时间根据ID和手机号
	 * @return
	 */
	public int updateCreateDate(PinActionEntity pa) throws WifiException {
		int executeCount=0;
       try{
    	   executeCount=pinActionMapper.updateCreateDate(pa);
		}catch(Exception e){
			e.printStackTrace();
			throw new WifiException("更新组建桌时间失败\r\n" + e.getMessage());
		}
		return executeCount;
	}
	
	/**
	 * 根据手机号码以及时间删除
	 * @return
	 */
	public int deleteDanByMobile(String ownerMobile) throws WifiException {
		int executeCount=0;
		try{
	    	   executeCount=pinActionMapper.deleteDanByMobile(ownerMobile);
			}catch(Exception e){
				e.printStackTrace();
				throw new WifiException("删除抢桌失败\r\n" + e.getMessage());
			}
			return executeCount;
	}
	
	/**
	 * 根据手机号码查询当天的拼桌ID
	 * @param ownerMobile
	 * @return
	 */
	public List<PinActionEntity >queryIdByMobile(String ownerMobile) throws WifiException {
		List<PinActionEntity> list=null;
		try{
			list=pinActionMapper.queryIdByMobile(ownerMobile);
		}catch(Exception e){
			e.printStackTrace();
			throw new WifiException(ownerMobile+"查询当天的抢桌id异常\r\n" + e.getMessage());
		}
		return list;
	}
	
	/**
	 * 根据用户的手机号码查询当天的参与的次数




	 * @return
	 * @throws WifiException 
	 */
	
	public int countByMobile(String ownerMobile) throws WifiException {
      int exeCount=0;
      try{
    	  exeCount=pinActionMapper.countByMobile(ownerMobile);
      }catch(Exception e){
    	  e.printStackTrace();
		  throw new WifiException(ownerMobile+"根据手机号码查询当天参与的次数异常\r\n" + e.getMessage());
      }
		return exeCount;
	}
	
	/**
	 * 根据手机号码查询值是否为空


	 * @param ownerMobile
	 * @return
	 */
	public int queryMobileIsNN(String ownerMobile) throws WifiException{
		int count=0;
		try{
			count=pinActionMapper.queryMobileIsNN(ownerMobile);
		}catch(Exception e){
			e.printStackTrace();
			throw new WifiException("用户:"+ownerMobile+"查询异常\r\n" + e.getMessage());
		}
		return count;
	}
	
	/**
	 * 查询用户最近的一条记录 
	 * @param ownerMobile
	 * @return
	 */
	public PinActionEntity queryLastTimeByMobile(String ownerMobile)throws WifiException{
		PinActionEntity pae=null;
		try{
			pae=pinActionMapper.queryLastTimeByMobile(ownerMobile);
		}catch(Exception e){
			e.printStackTrace();
			throw new WifiException("获取用户:"+ownerMobile+"最后一条记录异常\r\n" + e.getMessage());
		}
     return pae;
	}
	
	/**
	 * 返回抢单成功的信息


	 * @return
	 * @throws WifiException
	 */
	public List<PinActionEntity> getQiangDanList() throws WifiException {
		List<PinActionEntity> list=null;
		try{
			list=pinActionMapper.getQiangDanList();
		}catch(Exception e){
			e.printStackTrace();
			throw new WifiException("获取抢桌信息失败\r\n" + e.getMessage());
		}
		return list;
	}
	
	/**
	 * 插入桌主的信息



	 * @param pct
	 * @return
	 * @throws WifiException
	 */
	
	public int addCreateTableZhuozhu(PinCreateTableDetailEntity pct)
			throws WifiException {
		int exeCount=0;
		try{
			exeCount=pinCreateTableDetailMapper.addCreateTableZhuozhu(pct);
		}catch(Exception e){
			e.printStackTrace();
			throw new WifiException("插入桌主的信息失败\r\n" + e.getMessage());
		}
		return exeCount;
	}
	
	/***
	 * 拼桌人员的信息


	 * @return
	 */
	public List<PinCreateTableDetailEntity> pinDanList() throws WifiException{
		List<PinCreateTableDetailEntity> pinlist=null;
		try{
			pinlist=pinCreateTableDetailMapper.pinDanList();
		}catch(Exception e){
			e.printStackTrace();
			throw new WifiException("获取拼桌的信息异常\r\n" + e.getMessage());
		}
		return pinlist;
	}
	
	/* (non-Javadoc)
	 * @see com.aspire.wifi.wap.service.intf.PinActionService#getQiangDanTopInfo()
	 */
	// 光荣榜




	public List<PinActionEntity> getQiangDanTopInfo() throws WifiException {
		List<PinActionEntity> list=null;
		try{
			list=pinActionMapper.getQiangDanTopInfo();
		}catch(Exception e){
			e.printStackTrace();
			throw new WifiException("获取光荣榜失败\r\n" + e.getMessage());
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.aspire.wifi.wap.service.intf.PinActionService#listActivePinZhuo(java.util.Map)
	 */
	public List<PinCreateTableEntity> listActivePinZhuo(
			Map<String, Object> paramMap) throws WifiException {
		return pinCreateTableMapper.queryPinCreateTableList(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.aspire.wifi.wap.service.intf.PinActionService#listPinZhuoMember(java.util.Map)
	 */
	public List<PinCreateTableDetailEntity> listPinZhuoMember(
			Map<String, Object> paramMap) throws WifiException {
		return pinCreateTableDetailMapper.listPinZhuoMember(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.aspire.wifi.wap.service.intf.PinActionService#getPinZhuoDetails(java.util.Map)
	 */
	public PinCreateTableEntity getPinZhuoDetails(Map<String, Object> paramMap)
			throws WifiException {
		return pinCreateTableMapper.getPinZhuoDetails(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.aspire.wifi.wap.service.intf.PinActionService#joinPinZhuo(java.util.Map)
	 */
	public int joinPinZhuo(Map<String, Object> paramMap) throws WifiException {
		pinCreateTableMapper.updateFreeSeat(paramMap);
		
		PinCreateTableDetailEntity pinDetails = new PinCreateTableDetailEntity();
		pinDetails.setFlashSaleId((BigInteger) paramMap.get("flashSaleId"));
		pinDetails.setIsOwnerMobile((Integer) paramMap.get("isOwnerMobile"));
		pinDetails.setMobile((String) paramMap.get("mobile"));
		return pinCreateTableDetailMapper.insertPinCreateTableDetails(pinDetails);
	}

	/* (non-Javadoc)
	 * @see com.aspire.wifi.wap.service.intf.PinActionService#activatePingZhuo(java.util.Map)
	 */
	public int activatePingZhuo(Map<String, Object> paramMap) throws WifiException {
		return pinCreateTableMapper.activatePingZhuo(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.aspire.wifi.wap.service.intf.PinActionService#dismissPingZhuo(java.util.Map)
	 */
	public int dismissPingZhuo(Map<String, Object> paramMap) throws WifiException {
		return pinCreateTableMapper.dismissPingZhuo(paramMap);
	}
	
	/* (non-Javadoc)
	 * @see com.aspire.wifi.wap.service.intf.PinActionService#exitPinZhuo(java.util.Map)
	 */
	public int exitPinZhuo(Map<String, Object> paramMap) throws WifiException {
		return this.deletePinZhuoMember(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.aspire.wifi.wap.service.intf.PinActionService#uploadPinZhuoImg(java.util.Map)
	 */
	public int uploadPinZhuoImg(PinCreateTableEntity pc) throws WifiException {
		return pinCreateTableMapper.uploadPinZhuoImg(pc);
	}

	/* (non-Javadoc)
	 * @see com.aspire.wifi.wap.service.intf.PinActionService#deletePinZhuoMember(java.util.Map)
	 */
	public int deletePinZhuoMember(Map<String, Object> paramMap) throws WifiException {
		pinCreateTableMapper.updateFreeSeat(paramMap);

		PinCreateTableDetailEntity pinDetails = new PinCreateTableDetailEntity();
		pinDetails.setFlashSaleId((BigInteger) paramMap.get("flashSaleId"));
		pinDetails.setMobile((String) paramMap.get("mobile"));
		return pinCreateTableDetailMapper.deletePinZhuoMember(pinDetails);
	}

	/* (non-Javadoc)
	 * @see com.aspire.wifi.wap.service.intf.PinActionService#auditPinZhuoImg(com.aspire.wifi.wap.entity.PinCreateTableEntity)
	 */
	public int auditPinZhuoImg(Map<String, Object> paramMap) throws WifiException {
		return pinCreateTableMapper.auditPinZhuoImg(paramMap);
	}
	
	/* (non-Javadoc)
	 * @see com.aspire.wifi.wap.service.intf.PinActionService#checkActivityLimit(com.aspire.wifi.wap.entity.PinCreateTableEntity)
	 */
	public boolean checkActivityLimit(Map<String, Object> paramMap) {
		Integer activityCount = pinCreateTableMapper.checkActivityLimit(paramMap);
		return activityCount > 0;
	}
}
