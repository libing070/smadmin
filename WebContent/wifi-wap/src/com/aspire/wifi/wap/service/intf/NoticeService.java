package com.aspire.wifi.wap.service.intf;

import java.util.List;

import com.aspire.wifi.wap.entity.Notice;
import com.aspire.wifi.wap.exception.WifiException;


public interface NoticeService {
	List<Notice> queryNotice() throws WifiException;
}


