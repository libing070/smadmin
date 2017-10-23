
package com.xxx.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.dao.SmiShopInfoMapper;
import com.xxx.model.SmiShopInfo;
import com.xxx.service.ISmiShopInfoService;

/**
 * 影院店铺信息接口服务实现
 * 
 * @author author
 * @date 2016/02/16
 */
@Service("smiShopInfoService")
public class SmiShopInfoService implements ISmiShopInfoService {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	public SmiShopInfoMapper shopInfoMapper;
	
	@Override
	public SmiShopInfo getShopByNoAndPwd(String shopNo, String password) throws Exception {
		if (null==shopNo||"".equals(shopNo)) {
			logger.warn("门店编码为null或空，请检查！");
			throw new Exception("门店编码为空");
		}
		
		return shopInfoMapper.getByNoAndPwd(password, shopNo);
	}

	@Override
	public void insertUser(SmiShopInfo shopinfo) {
		// TODO Auto-generated method stub
		if ("".equals(shopinfo.getShopNo())&&"".equals(shopinfo.getShopPassword())&&"".equals(shopinfo.getPhone())) {
			logger.warn("新增失败  门店编码||密码||phone为空！");
		}else{
			shopInfoMapper.insertUser(shopinfo);
		}
		
	}

	@Override
	public SmiShopInfo getByShopNO(String shopNo) throws Exception {
		// TODO Auto-generated method stub
		return shopInfoMapper.getByShopNO( shopNo);
	}

	@Override
	public int updateShopInfoByShopId(SmiShopInfo shopinfo) {
		// TODO Auto-generated method stub
		return shopInfoMapper.updateShopInfoByShopId(shopinfo);
	}
	
}
