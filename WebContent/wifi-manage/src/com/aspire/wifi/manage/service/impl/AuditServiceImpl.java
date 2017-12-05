package com.aspire.wifi.manage.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.manage.entity.AppInfoEntity;
import com.aspire.wifi.manage.entity.PinCreatetable;
import com.aspire.wifi.manage.entity.PinCreatetableDetail;
import com.aspire.wifi.manage.entity.PinCreatetableDetailHist;
import com.aspire.wifi.manage.entity.PinCreatetableHist;
import com.aspire.wifi.manage.entity.VideoInfo;
import com.aspire.wifi.manage.entity.constant.ComCons;
import com.aspire.wifi.manage.exception.WxppException;
import com.aspire.wifi.manage.mapper.AppInfoMapper;
import com.aspire.wifi.manage.mapper.PinCreatetableDetailMapper;
import com.aspire.wifi.manage.mapper.PinCreatetableMapper;
import com.aspire.wifi.manage.mapper.VideoInfoMapper;
import com.aspire.wifi.manage.service.AuditService;

/**
 * @Title: WIFI_BJ
 * @Package com.aspire.wifi.manage.service.impl
 * @Description: 审核
 * @Author wuyuehui_a
 * @Date 2014/8/7
 * @Version V1.0
 * Update Logs:
 */
@Service(value = "auditService")
public class AuditServiceImpl implements AuditService {
    protected static Logger logger = LoggerFactory.getLogger(AuditServiceImpl.class);

    @Resource(name = "pinCreatetableMapper")
    private PinCreatetableMapper pinCreatetableMapper;

    @Resource(name = "pinCreatetableDetailMapper")
    private PinCreatetableDetailMapper pinCreatetableDetailMapper;
    @Resource(name = "videoInfoMapper")
    private VideoInfoMapper videoInfoMapper;
    @Resource(name = "appInfoMapper")
    private AppInfoMapper appInfoMapper;

    /**
     * 统一查询图片接口
     *
     * @param plate 1 新生报到 2 抢单 3 头条
     * @param id
     * @return
     * @throws com.aspire.wifi.manage.exception.WxppException
     */
    
    public byte[] findPic(String plate, String id) throws WxppException {

            PinCreatetable temp = pinCreatetableMapper.findPinCreatetable(id);
            if (temp != null)
                return temp.getConsumePic();
      
        return null;
    }

    /**
     * 查找拼单的人
     *
     * @param flashSaleId
     * @return
     * @throws com.aspire.wifi.manage.exception.WxppException
     */
    
    public List<PinCreatetableDetail> queryPindan(String flashSaleId) throws WxppException {
        return pinCreatetableDetailMapper.queryCreatetableDetail(flashSaleId);
    }

    /**
     * 查询抢单列表/查询上传照片抢单列表
     *
     * @param pinCreatetable
     * @return
     * @throws com.aspire.wifi.manage.exception.WxppException
     */
    
    public List<PinCreatetable> queryQiandan(Map<String,Object> paramsMap) throws WxppException {
        return pinCreatetableMapper.queryPinCreatetable(paramsMap);
    }

    
    public Integer queryQiandanCount(Map<String,Object> paramsMap) throws WxppException {
        return pinCreatetableMapper.queryPinCreatetableCount(paramsMap);
    }


    /**
     * 审核抢单记录/审核照片上传记录
     *
     * @param pinCreatetable
     * @throws com.aspire.wifi.manage.exception.WxppException
     */
    
    public void auditQiandan(PinCreatetable pinCreatetable) throws WxppException {
     //   PinCreatetable _temp = pinCreatetableMapper.findPinCreatetable(pinCreatetable.getFlashSaleId());
        if (pinCreatetable.getAuditFlag().equals(ComCons.AUDIT_FLAG_QIANG)) { //抢单审核
            if (pinCreatetable.getAuditStatus().equals(ComCons.AUDIT_PASS)) {
                pinCreatetable.setActStatusId(ComCons.ACT_STATUS_DEPLOYED);
            } else {
                pinCreatetable.setActStatusId(ComCons.ACT_STATUS_DEPLOY_FAILURE);
            }
        } else if (pinCreatetable.getAuditFlag().equals(ComCons.AUDIT_FLAG_IMAGE)) { //上传照片待审核
            if (pinCreatetable.getAuditStatus().equals(ComCons.AUDIT_PASS)) {
                pinCreatetable.setActStatusId(ComCons.ACT_STATUS_FINISH);
            } else {
                pinCreatetable.setActStatusId(ComCons.ACT_STATUS_UPDATE_PIC_FAILURE);
            }
        }
        pinCreatetableMapper.updatePinCreatetable(pinCreatetable);
        // 抢单写入历史表
        PinCreatetableHist hist = new PinCreatetableHist();
        hist.setFlashSaleId(pinCreatetable.getFlashSaleId());
        if (pinCreatetable.getActStatusId().equals(ComCons.ACT_STATUS_UPDATE_PIC_FAILURE)) {
            hist.setActionDesc("上传照片审核不通过");
        } else if (pinCreatetable.getActStatusId().equals(ComCons.ACT_STATUS_FINISH)) {
            hist.setActionDesc("上传照片待审核通过完成");
        }
        pinCreatetableMapper.insertPinCreatetableHist(hist);
        // 拼单写入历史表
        PinCreatetableDetailHist detailHist = new PinCreatetableDetailHist();
        detailHist.setFlashSaleId(pinCreatetable.getFlashSaleId());
        if (pinCreatetable.getActStatusId().equals(ComCons.ACT_STATUS_UPDATE_PIC_FAILURE)) {
            detailHist.setActionDesc("上传照片审核不通过");
        } else if (pinCreatetable.getActStatusId().equals(ComCons.ACT_STATUS_FINISH)) {
            detailHist.setActionDesc("上传照片待审核通过完成");
        }
        pinCreatetableMapper.insertPinCreatetableDetailHist(detailHist);
    }


    /**
     * 查询抢单记录详情
     *
     * @param flashSaleId
     * @return
     * @throws com.aspire.wifi.manage.exception.WxppException
     */
    
    public PinCreatetable findQiandan(String flashSaleId) throws WxppException {
        return pinCreatetableMapper.findPinCreatetable(flashSaleId);
    }
    
    
    public  List<VideoInfo> queryVideoInfo(Map paramMap)  throws WxppException{
  	  return videoInfoMapper.queryVideoInfo(paramMap);
    }
    
    public  List<VideoInfo> searchVideoInfoByVideoId(Map paramMap)  throws WxppException{
  	  return videoInfoMapper.searchVideoInfoByVideoId(paramMap);
    }
  
  public Integer searchVideoInfoListCount(Map paramMap)
  		throws WxppException {
  	return videoInfoMapper.searchVideoInfoListCount(paramMap);
  }
  
  public void uploadVideoInfo(Map paramMap)
  throws WxppException {
  	videoInfoMapper.uploadVideoInfo(paramMap);
  }


public List<AppInfoEntity> queryAppInfo(Map paramMap) throws WxppException {
	// TODO Auto-generated method stub
	return appInfoMapper.queryAppInfo(paramMap);
}


public List<AppInfoEntity> searchAppInfoByAppId(Map paramMap)
		throws WxppException {
	// TODO Auto-generated method stub
	return appInfoMapper.searchAppInfoByAppId(paramMap);
}


public Integer searchAppInfoListCount(Map paramMap) throws WxppException {
	// TODO Auto-generated method stub
	return appInfoMapper.searchAppInfoListCount(paramMap);
}


public void uploadAppInfo(Map paramMap) throws WxppException {
	// TODO Auto-generated method stub
	appInfoMapper.uploadAppInfo(paramMap);
}
@Override
public void insertVideoInfo(VideoInfo v) throws WxppException {
	// TODO Auto-generated method stub
	videoInfoMapper.insertVideoInfo(v);
}
@Override
public void insertAppInfo(AppInfoEntity a) throws WxppException {
	// TODO Auto-generated method stub
	appInfoMapper.insertAppInfo(a);
}
public void clearAppDemoPicAll(int appId) throws WxppException {
	// TODO Auto-generated method stub
	appInfoMapper.clearAppDemoPicAll(appId);
	
}
public void updateAppDemoPic(Map paramMap) throws WxppException {
	// TODO Auto-generated method stub
	 appInfoMapper.updateAppDemoPic(paramMap);
}
}
