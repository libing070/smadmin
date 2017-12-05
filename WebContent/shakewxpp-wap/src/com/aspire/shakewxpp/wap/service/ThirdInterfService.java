package com.aspire.shakewxpp.wap.service;

import java.util.Map;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.service
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/25
 * @Version V1.0
 * Update Logs:
 */
public interface ThirdInterfService {


    /**
     * 获取登录令牌
     * <p/>
     * 说明：向流量汇发送获取登录令牌的请求
     *
     * @param msisdn 手机号
     * @param dateTime 操作时间yyyyMMddHHmmss
     * @return {"Ticket": xxxxx, "ExpireTime": YYYY-MM-DD HH:MI:SS}
     * @throws Exception
     */
    Map<String, String> getLoginToken(String msisdn, String dateTime) throws Exception;

}
