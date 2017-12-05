package com.aspire.wifi.wap.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.wap.entity.PinCreateTableEntity;
import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.mapper.PinCreateTableMapper;
import com.aspire.wifi.wap.service.intf.PinCreateTableService;

@Service("pinCreateTableService")
public class PinCreateTableServiceImpl implements PinCreateTableService {
    protected static Logger logger = LoggerFactory.getLogger(PinCreateTableServiceImpl.class);
    @Resource(name = "pinCreateTableMapper")
    private PinCreateTableMapper pinCreateTableMapper;

    /**
     * 创建桌
     *
     * @return
     */
    public int createPinZhuo(PinCreateTableEntity pc) throws WifiException {
        int exeCount = 0;
        try {
            exeCount = pinCreateTableMapper.createPinZhuo(pc);
        } catch (Exception e) {
            e.printStackTrace();
            throw new WifiException("创建桌失败\r\n" + e.getMessage());
        }
        return exeCount;
    }

    /**
     * 用户更新创建桌的内容 
     * @return
     * @throws WifiException
     */
    public int updateCreateTableByMobile(PinCreateTableEntity pce)throws WifiException {
    	int exeCount=0;
    	try{
    		exeCount=pinCreateTableMapper.updateCreateTableByMobile(pce);
    	}catch(Exception e){
    		 e.printStackTrace();
             throw new WifiException("更新桌失败\r\n" + e.getMessage());
    	}
    	return exeCount;
    }
    
    /**
     *用户可根据状态查询未通过的信息
     * @return
     */
   public PinCreateTableEntity querCreateTableByMobile(PinCreateTableEntity pce)throws WifiException{
	   PinCreateTableEntity pct=null;
	     try{
    		 pct=pinCreateTableMapper.querCreateTableByMobile(pce);
    	 }catch(Exception e){
    		 e.printStackTrace();
             throw new WifiException("查询未通过的信息失败\r\n" + e.getMessage());
    	 }
    	 return pct;
     }
     
   /**
    * 查询所有的信息
    * @return
    */
 public List<PinCreateTableEntity> queryAllList()throws WifiException{
	 List<PinCreateTableEntity> listPt=null;
	 try{
		 listPt=pinCreateTableMapper.queryAllList();
	 }catch(Exception e){
		 e.printStackTrace();
         throw new WifiException("查询信息失败\r\n" + e.getMessage());
	 }
	 return listPt;
 }
   
   /**
    * 根据用户手机号码删除过期数据
    * @param mobile
    * @return
    */
  public int deleteOverTimeByMobile(String mobile)throws WifiException{
	  int exeCount=0;
	  try{
		  exeCount=pinCreateTableMapper.deleteOverTimeByMobile(mobile);
 	 }catch(Exception e){
 		 e.printStackTrace();
          throw new WifiException("删除信息失败\r\n" + e.getMessage());
 	 }
 	 return exeCount;
  }
  
  /**
   * 根据手机号码以及ID更改状态编号
   * 
   * @param mobile
   * @return
   */
   public int updateOverTimeByMobile(PinCreateTableEntity pce)throws WifiException{
	 int exeCount=0;
	  try{
		  exeCount=pinCreateTableMapper.updateOverTimeByMobile(pce);
	 }catch(Exception e){
		 e.printStackTrace();
         throw new WifiException("更新状态失败\r\n" + e.getMessage());
	 }
	 return exeCount;
  }
   
   /**
    * 根据手机号码以及ID查询单条记录
    * @param pce
    * @return
    * @throws WifiException
    */
   public PinCreateTableEntity querySingle(PinCreateTableEntity pce)throws WifiException{
	   PinCreateTableEntity pcte=null;
	   try{
		   pcte=pinCreateTableMapper.querySingle(pce);
	   }catch(Exception e){
		   e.printStackTrace();
	       throw new WifiException("查询单条记录失败\r\n" + e.getMessage());
	   }
	   return pcte;
   }
     
    /* (non-Javadoc)
     * @see com.aspire.wifi.wap.service.intf.PinCreateTableService#listPinCreateTable(com.aspire.wifi.wap.entity.PinCreateTableEntity)
     */
    public List<Map<String, Object>> listPinCreateTable(PinCreateTableEntity pc) throws WifiException {
        // TODO Auto-generated method stub
        return pinCreateTableMapper.listPinCreateTableDetail(pc);
    }

    /* (non-Javadoc)
     * @see com.aspire.wifi.wap.service.intf.PinCreateTableService#listPinCreateTableHis(com.aspire.wifi.wap.entity.PinCreateTableEntity)
     */
    public List<Map<String, Object>> listPinCreateTableHis(PinCreateTableEntity pc) throws WifiException {
        // TODO Auto-generated method stub
        return pinCreateTableMapper.listPinCreateTableDetailHis(pc);
    }
    
    /* (non-Javadoc)
     * @see com.aspire.wifi.wap.service.intf.PinCreateTableService#updateVerificationCode(com.aspire.wifi.wap.entity.PinCreateTableEntity)
     */
    public boolean checkVerificationCode(Map<String, Object> paramMap) throws WifiException {
    	return pinCreateTableMapper.checkVerificationCode(paramMap) > 0;
    }
}
