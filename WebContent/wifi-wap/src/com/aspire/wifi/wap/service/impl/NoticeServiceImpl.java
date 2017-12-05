package com.aspire.wifi.wap.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.wap.entity.Notice;
import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.mapper.NoticeMapper;
import com.aspire.wifi.wap.service.intf.NoticeService;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	protected static Logger logger =  LoggerFactory.getLogger(NoticeServiceImpl.class);
	@Resource(name = "noticeMapper")
	private NoticeMapper noticeMapper;
	
	
	
	public List<Notice> queryNotice() throws WifiException{
		List<Notice> list = null;
		try{
			list = noticeMapper.queryNotice();
		}catch(Exception e){
			throw new WifiException("滚动通知信息出现异常;error:"+e.getMessage());
		}
		return list;
	}

}
