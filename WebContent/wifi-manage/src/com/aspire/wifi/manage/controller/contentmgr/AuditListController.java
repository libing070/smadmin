package com.aspire.wifi.manage.controller.contentmgr;

import com.aspire.wifi.manage.base.BaseController;
import com.aspire.wifi.manage.entity.*;
import com.aspire.wifi.manage.entity.constant.ComCons;
import com.aspire.wifi.manage.exception.WxppException;
import com.aspire.wifi.manage.service.AuditService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: WIFI_BJ
 * @Package com.aspire.wifi.manage.controller.contentmgr
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/7
 * @Version V1.0
 * Update Logs:
 */
@Controller
public class AuditListController extends BaseController {
    protected static Logger logger = LoggerFactory.getLogger(AuditListController.class);

    @Resource(name = "auditService")
    private AuditService auditService;

    /**
     * 拼单人员列表查询
     *
     * @param flashSaleId
     * @return
     */
    @RequestMapping(value = "/pingdanList", method = RequestMethod.POST)
    public
    @ResponseBody
    List<PinCreatetableDetail> pingdanList(@RequestParam(value = "flashSaleId", required = true) String flashSaleId) {
        logger.debug("进入获取列表方法[pingdanList]");
        logger.debug("输入参数：flashSaleId={}", flashSaleId);
        List<PinCreatetableDetail> list = new ArrayList<PinCreatetableDetail>();
        try {
            list = auditService.queryPindan(flashSaleId);
        } catch (WxppException e) {
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            logger.error("查询拼单人员出现异常", e);
        }
        logger.debug("结束获取列表方法[pingdanList]");
        return list;
    }

    /**
     * 抢单审核列表查询
     * @param expireDateStartSch
     * @param expireDateEndSch
     * @param actDescSch
     * @param ownerMobileSch
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "menu/qiangdanAuditList", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> qiangdanAuditList(
            @RequestParam(value = "mobileOrNickname", required = false) String mobileOrNickname,
            @RequestParam(value = "begin", required = false) int begin,
            @RequestParam(value = "end", required = false) int end,
            @RequestParam(value = "flag", required = false) String flag
    ) {

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        paramsMap.put("mobileOrNickname", mobileOrNickname);
        paramsMap.put("begin", begin);
        paramsMap.put("end", end);
        paramsMap.put("flag", flag);
       
        // 区别是抢单审核 还是 上传照片审核
        if (flag.equalsIgnoreCase("1")) {
        	paramsMap.put("actStatusId",ComCons.ACT_STATUS_UPDATE_PIC_WAITING_AUDIT);
        } else {
        	 Long[] actStatusId={Long.valueOf(ComCons.ACT_STATUS_DEPLOY_WAITING_AUDIT),Long.valueOf(ComCons.ACT_STATUS_DEPLOYED),Long.valueOf(ComCons.ACT_STATUS_DEPLOY_FAILURE)};
        	paramsMap.put("actStatusId",actStatusId);
        }
        List<PinCreatetable> list = new ArrayList<PinCreatetable>();
        Integer count = 0;
        try {
            list = auditService.queryQiandan(paramsMap);
            count = auditService.queryQiandanCount(paramsMap);
            int totalpage=count/(end-0);
	  		  if(count%(end-0)!=0){
	  			  totalpage=count/(end-0)+1;
	  		  }
            returnMap.put("CODE", "TRUE");
        
            returnMap.put("rows", list);
            returnMap.put("total", totalpage);
        } catch (Exception e) {
        	e.printStackTrace();
        	returnMap.put("CODE", "FALSE");
        	returnMap.put("msg", "抢单审核列表查询出现异常");
            logger.error("抢单审核列表查询出现异常", e);
        }
        logger.debug("结束获取列表方法[qiangdanAuditList]");
        return returnMap;
    }

}
