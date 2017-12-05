package com.aspire.wifi.wap.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aspire.wifi.wap.entity.PinActionEntity;
import com.aspire.wifi.wap.entity.PinActionHistoryEntity;
import com.aspire.wifi.wap.entity.PinCreateTableEntity;
import com.aspire.wifi.wap.entity.PinCreateTableHistoryEntity;
import com.aspire.wifi.wap.service.intf.PinActionHistoryService;
import com.aspire.wifi.wap.service.intf.PinActionService;
import com.aspire.wifi.wap.service.intf.PinCreateTableHistoryService;
import com.aspire.wifi.wap.service.intf.PinCreateTableService;

@Component
public class QuartzJob {
	protected static Logger logger =  LoggerFactory.getLogger(QuartzJob.class);
	public QuartzJob(){
        System.out.println("Quartzjob创建成功");
    }
	@Resource(name = "pinActionService")
	private PinActionService pinActionService;
	
	@Resource(name = "pinActionHistoryService")
	private PinActionHistoryService pinActionHistoryService;
	
	@Resource(name = "pinCreateTableService")
	private PinCreateTableService pinCreateTableService;
	
	@Resource(name = "pinCreateTableHistoryService")
	private PinCreateTableHistoryService pinCreateTableHistoryService;
	
	int count=0;
	List<PinActionEntity> list=null;
	List<PinCreateTableEntity> listPt=null;
	
	PinActionEntity pae=null; 
	PinCreateTableEntity pte=null,pte_=null;
	
	BigInteger flashId=BigInteger.valueOf(0);
	String flashDate="",expiredDate="",createTableDate="";
	Date flash_Date=null,expired_Date=null,create_Date=null,current_Date=null;;
	long flashDatemm=0l,expiredDatemm=0l,createDatemm=0l,currentDatemm=0l;
	int execount=0;
	PinActionHistoryEntity phe=null;
	PinCreateTableHistoryEntity pthe=null;
	
	int hesCount=0;
	//String mobile="11111111111";
	
	/**
	 * 过期检索,如果超时则删除

	 * @return
	 */
	@Scheduled(cron="0 0/1 * * * ?")//0 * 17 * * ? 正确的0 0/1 * * * ?  11-14
	public void run(){
		try{
			list=pinActionService.getQiangDanList();
			if(list==null||list.size()==0){
				return;
			}
			Date dt=new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate="";
			phe=new PinActionHistoryEntity();
			for(int i=0;i<list.size();i++){
				pae=(PinActionEntity)list.get(i);
				flashDate=pae.getFlashSaleDate();
			    expiredDate=pae.getExpiredDate();
			    createTableDate=pae.getCreatetableDate();
			    flash_Date=sdf.parse(flashDate);
			    expired_Date=sdf.parse(expiredDate);
			    create_Date=sdf.parse(createTableDate);
			    flashDatemm=flash_Date.getTime();
			    expiredDatemm=expired_Date.getTime();
			    createDatemm=create_Date.getTime();
			    strDate = sdf.format(dt);
			    current_Date=sdf.parse(strDate);
			    currentDatemm=current_Date.getTime();
			
			    //插入历史记录
			    phe.setFlashSaleId(pae.getFlashSaleId());
			    phe.setOwnerMobile(pae.getOwnerMobile());
			    phe.setActionDate(current_Date);//插入当前的时间

			    phe.setActionType(3);
			    phe.setActionDesc("未能及时创建桌,已被系统删除");
			    phe.setFlashSaleDate(sdf.parse(pae.getFlashSaleDate()));
			    phe.setCreateTableDate(sdf.parse(createTableDate));
			    
			   long sec=expiredDatemm-currentDatemm;
			   
			    if(sec<=0){
			    if(createDatemm==flashDatemm){
			    	hesCount=pinActionHistoryService.addPinActionHistory(phe);
			    	if(hesCount>0){
			    		 execount=pinActionService.deleteDanByMobile(pae.getOwnerMobile());
					      if(execount>0){
					    	  logger.info("用户: "+pae.getOwnerMobile()+" 未能及时创建桌,则被系统删除!");
					      }
					    }
			    	}
			      
		       }
		     }
				
		}catch(Exception e){
			logger.error("过期检索异常", e);
			e.printStackTrace();
			
		}
	}
	
	/**
	 * 过期检索,如果超时则删除(检索桌如果过期了所做的处理)
	 * @return
	 */
	@Scheduled(cron="0 0/2 * * * ?")//0 0/1 * ? * MON-FRI(周一到周五每分钟)
	public void runCreatetable(){
		 int count=0,exehistoryCount=0,actStatusId=0;
		 Date expiredDate=null,currentDate=null;
		 long expiredmm=0l,currentmm=0l;
		try{
			listPt=pinCreateTableService.queryAllList();
			if(listPt==null||listPt.size()==0){
				return;
			}
			Date dt=new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate="";
			pthe=new PinCreateTableHistoryEntity();
			
			pte=new PinCreateTableEntity();
			
			for(int i=0;i<listPt.size();i++){
				pte=(PinCreateTableEntity)listPt.get(i);
				
				expiredDate=pte.getExpireDate();
			    expiredmm=expiredDate.getTime();
			    strDate = sdf.format(dt);
			    currentDate=sdf.parse(strDate);
			    currentmm=currentDate.getTime();
			    pte.setFlashSaleId(pte.getFlashSaleId());
			    pte.setOwnerMobile(pte.getOwnerMobile());
			    //插入到历史记录

			    pthe.setFlashSaleId(pte.getFlashSaleId());
			    pthe.setActDesc(pte.getActDesc());
			    pthe.setActTypeId(pte.getActTypeId());
			    pthe.setActStatusId(pte.getActStatusId());
			    pthe.setConsumePlaceId(pte.getConsumePlaceId());
			    pthe.setOwnerMobile(pte.getOwnerMobile());
			    pthe.setConsumePic(pte.getConsumePic());
			    pthe.setActionDate(current_Date);
			    pthe.setActionType(3);
			    pthe.setActionDesc("未能及时开桌，已被系统软删除");
			    pthe.setCreateTableDate(pte.getCreateTableDate());
			    pthe.setExpiredDate(expired_Date);
			    pthe.setAuditUser(pte.getAuditUser());
			    pthe.setAuditTime(pte.getAuditTime());
			    pthe.setAuditComment(pte.getAuditComment());
			    pthe.setAuditStatus(pte.getAuditStatus());
			    
			    pte_=pinCreateTableService.querySingle(pte);
			    if(pte_!=null){
			    	actStatusId=pte_.getActStatusId();
			    }
			    
			   long sec=expiredmm-currentmm;
			   
			    if(sec<=0){
			    if(pte.getFreeSeat()>0){
			    	if(actStatusId!=4){
			    		exehistoryCount=pinCreateTableHistoryService.addCreateTableToHistory(pthe);
			    		if(exehistoryCount>0){
			    			count=pinCreateTableService.updateOverTimeByMobile(pte);
					    	if(count>0){
					    		logger.info("用户: "+pte.getOwnerMobile()+"状态已更新");
					    		logger.info("用户: "+pte.getOwnerMobile()+" 未能及时创建桌,则被系统软删除!编号:"+pte.getFlashSaleId());
					    	}
			    			
			    		}
			    	}
			      }
		       }
		     }
				
		}catch(Exception e){
			logger.error("过期检索异常", e);
			e.printStackTrace();
			
		}
	}
}
