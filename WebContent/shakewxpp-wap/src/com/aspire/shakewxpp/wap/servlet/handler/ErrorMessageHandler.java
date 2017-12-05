package com.aspire.shakewxpp.wap.servlet.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.servlet.handler
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/26
 * @Version V1.0
 * Update Logs:
 */
public class ErrorMessageHandler {
    private static final Logger logger = LoggerFactory.getLogger(ErrorMessageHandler.class);
    private static ErrorMessageHandler instance = null;
    private static final String ctxPath = System.getProperty("user.dir");
    private static final String errorJsonFile = ctxPath.concat(File.separator).concat("shakewxpp-wap").concat(File.separator)
            .concat("conf").concat(File.separator).concat("interf_error.json");
    private static List<HashMap> list = null;
    private ErrorMessageHandler(){
        if (list == null) {
            logger.debug("开始加载接口异常代码描述配置...");
            try {
                String strFileContent = FileUtils.readFileToString(new File(errorJsonFile), "UTF-8");
                JSONObject jsonObj = JSON.parseObject(strFileContent);
                JSONArray array = jsonObj.getJSONArray("error_all");
                list = JSON.parseArray(array.toString(), HashMap.class);
                logger.debug("成功加载接口异常代码描述配置");
            } catch (IOException e) {
                logger.error("读取流量汇接口错误码定义文件异常", e);
            }
        }
    }
    public static synchronized ErrorMessageHandler getInstance() {
        if (instance == null) {
            instance = new ErrorMessageHandler();
        }
        return instance;
    }

    public String transformErrorCode(Integer errorCode) {
        logger.debug("开始翻译异常代码[{}]", errorCode);
        if (list == null || list.isEmpty()) {
            return "";
        }
        for (HashMap hashMap : list) {
            if (hashMap.containsKey(errorCode)) {
                String errorDesc = hashMap.get(errorCode).toString();
                logger.debug("翻译异常代码[{}={}]", errorCode, errorDesc);
                return errorDesc;
            }
        }
        logger.debug("翻译异常代码[{}]为空", errorCode);
        return "";
    }
}
