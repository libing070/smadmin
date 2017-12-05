package com.aspire.wifi.manage.controller.contentmgr;

import com.aspire.wifi.manage.entity.PinCreatetable;
import com.aspire.wifi.manage.exception.WxppException;
import com.aspire.wifi.manage.service.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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
public class AuditController {
    protected static Logger logger = LoggerFactory.getLogger(AuditListController.class);

    @Resource(name = "auditService")
    private AuditService auditService;

   

    /**
     * 审核抢单
     *
     * @param auditResult
     * @param auditOpinion
     * @param flashSaleId
     */
    @RequestMapping(value = "/qiangdanAudit", method = RequestMethod.POST)
    public
    @ResponseBody
    void qiangdanAudit(
            @RequestParam(value = "auditResult", required = true) String auditResult,
            @RequestParam(value = "auditOpinion", required = false) String auditOpinion,
            @RequestParam(value = "auditFlag", required = false) String auditFlag,//审核标志1，审核抢单，2审核上传照片
            @RequestParam(value = "flashSaleId", required = true) String flashSaleId) {
        logger.debug("进入审核方法[qiangdanAudit]");
        logger.debug("输入参数：auditResult={}", auditResult);
        logger.debug("输入参数：auditOpinion={}", auditOpinion);
        logger.debug("输入参数：flashSaleId={}", flashSaleId);
        PinCreatetable pinCreatetable = new PinCreatetable();
        pinCreatetable.setFlashSaleId(flashSaleId);
        pinCreatetable.setAuditStatus(auditResult);
        pinCreatetable.setAuditComment(auditOpinion);
        pinCreatetable.setAuditFlag(auditFlag);
        try {
            auditService.auditQiandan(pinCreatetable);
        } catch (WxppException e) {
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            logger.error("审核抢单出现异常", e);
        }
        logger.debug("结束审核方法[qiangdanAudit]");
    }

}
