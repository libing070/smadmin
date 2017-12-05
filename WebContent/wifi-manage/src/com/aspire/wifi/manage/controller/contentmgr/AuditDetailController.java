package com.aspire.wifi.manage.controller.contentmgr;

import com.aspire.wifi.manage.entity.PinCreatetable;
import com.aspire.wifi.manage.entity.constant.ComCons;
import com.aspire.wifi.manage.exception.WxppException;
import com.aspire.wifi.manage.service.AuditService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

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
public class AuditDetailController {
    protected static Logger logger = LoggerFactory.getLogger(AuditDetailController.class);

    @Resource(name = "auditService")
    private AuditService auditService;

    /**
     * 查询抢单详情
     *
     * @param flashSaleId
     * @return
     */
    @RequestMapping(value = "/qiangdanAuditDetail", method = RequestMethod.POST)
    public
    @ResponseBody
    PinCreatetable qiangdanAuditDetail(@RequestParam(value = "flashSaleId", required = true) String flashSaleId) {
        logger.debug("进入获取详情方法[qiangdanAuditDetail]");
        logger.debug("输入参数：flashSaleId={}", flashSaleId);
        PinCreatetable pinCreatetable = new PinCreatetable();
        try {
            pinCreatetable = auditService.findQiandan(flashSaleId);
        } catch (WxppException e) {
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            logger.error("查询抢单详情出现异常", e);
        }
        logger.debug("结束获取详情方法[qiangdanAuditDetail]");
        return pinCreatetable;
    }

    /**
     * 查询图片统一接口
     * @param response
     * @param plate 1 新生报到 2 抢单 3 头条
     * @param id
     */
    @RequestMapping(value = "findPic")
    public void findPic(
            HttpServletResponse response,
            @RequestParam(value = "plate") String plate,
            @RequestParam(value = "id") String id
    ) {
        logger.debug("进入获取详情方法[findPic]");
        logger.debug("输入参数：plate={}", plate);
        logger.debug("输入参数：id={}", id);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            byte[] picBytes = auditService.findPic(plate, id);
            IOUtils.write(picBytes, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.debug("结束获取详情方法[findPic]");
    }

    private byte[] handleFile(MultipartFile file) {
        try {
            return IOUtils.toByteArray(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
