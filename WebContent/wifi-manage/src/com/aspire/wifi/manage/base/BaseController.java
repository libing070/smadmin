package com.aspire.wifi.manage.base;

/**
 * @Title: WIFI_BJ
 * @Package com.aspire.wifi.manage.base
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/10
 * @Version V1.0
 * Update Logs:
 */
public class BaseController {
    /**
     * 传入DB的分页设置
     * @param domain
     * @param rows
     * @param page
     */
    public void setPagination(BaseDomain domain, Integer rows, Integer page) {
        domain.setLimit(rows);
        domain.setPageNum(page);
        domain.setMysqlStart(domain.getLimit()*(domain.getPageNum()-1));
    }
}
