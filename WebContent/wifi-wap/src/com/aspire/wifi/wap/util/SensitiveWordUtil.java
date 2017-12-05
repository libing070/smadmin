package com.aspire.wifi.wap.util;

import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.service.intf.WIFISysMessageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: WIFI_LOST
 * @Package com.aspire.wifi.wap.util
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/17
 * @Version V1.0
 * Update Logs:
 */
@Component(value = "sensitiveWordUtil")
public class SensitiveWordUtil {
    protected static Logger logger = LoggerFactory.getLogger(SensitiveWordUtil.class);

    @Resource(name = "wifiSysMessageService")
    WIFISysMessageService wifiSysMessageService;

    /**
     * 过滤敏感词
     * @param content
     * @return
     */
    public String filterSensitiveWord(String content) {
        String returnContent = content;
        try {
            List<String> words = wifiSysMessageService.listSensitiveWord(content);
            for (String word : words) {
                returnContent = StringUtils.replace(returnContent, word, "***");
            }
        } catch (WifiException e) {
            e.printStackTrace();
        }
        return returnContent;
    }
}
