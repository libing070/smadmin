package com.aspire.shakewxpp.wap.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServlet;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.util
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/26
 * @Version V1.0
 * Update Logs:
 */
public class BeanLocator implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(BeanLocator.class);
    private static BeanLocator instance = null;
    private ApplicationContext applicationContext = null;
    private BeanLocator(){}
    public static synchronized BeanLocator getInstance() {
        if (instance == null) {
            instance = new BeanLocator();
        }
        return instance;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取对象
     * 一个以所给名字注册的bean的实例 (service注解方式，自动生成以首字母小写的类名为bean name)
     */
    public Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }
}
