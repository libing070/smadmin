package com.aspire.wifi.wap.mapper;

import java.util.List;

import com.aspire.wifi.wap.entity.PinActionEntity;
import com.aspire.wifi.wap.exception.WifiException;

public interface PinActionMapper {
	
	 /**
     * 抢单 
     * @return
     */
	int doQiangDan(PinActionEntity pa);
    
	 /**
     * 查询每天的桌数
     * @return
     */
	int everyDayCount();
     
	/**
	 * 根据用户的手机号码查询当天的参与的次数
	 * @return
	 */
	int countByMobile(String mobile);
	
	 /**
     * 更新组建创建桌的时间根据ID和手机号
     * @return
     */
	int updateCreateDate(PinActionEntity pa);
	
	/**
     * 光荣榜
     * @return
     */
	List<PinActionEntity> getQiangDanTopInfo();
	
	/**
	 * 根据用户以及时间删除
	 * @param pa
	 * @return
	 */
	int deleteDanByMobile(String ownerMobile);
	
	/**
	 * 返回抢单成功的信息
	 * @return
	 * @throws WifiException
	 */
	public List<PinActionEntity> getQiangDanList();
	
	
	/**
	 * 根据手机号码查询当天的拼桌ID
	 * @param ownerMobile
	 * @return
	 */
	public List<PinActionEntity >queryIdByMobile(String ownerMobile);
	
	/**
	 * 根据手机号码查询值是否为空
	 * @param ownerMobile
	 * @return
	 */
	public int queryMobileIsNN(String ownerMobile);
	
	/**
	 * 查询用户最近的一条记录 
	 * @param ownerMobile
	 * @return
	 */
	public PinActionEntity queryLastTimeByMobile(String ownerMobile);
}
