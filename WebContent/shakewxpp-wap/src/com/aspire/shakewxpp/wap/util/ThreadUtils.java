package com.aspire.shakewxpp.wap.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.util
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/26
 * @Version V1.0
 * Update Logs:
 */
public class ThreadUtils {
    private static ThreadUtils instance = null;
    private static ExecutorService pool = null;
    private ThreadUtils() {
        if (pool == null) {
            pool = Executors.newCachedThreadPool();
        }
    }
    public static synchronized ThreadUtils getInstance() {
        if (instance == null) {
            instance = new ThreadUtils();
        }
        return instance;
    }

    public void execute(Runnable thread) {
        pool.execute(thread);
    }

    public Future<?> submit(Callable thread) {
        return pool.submit(thread);
    }
}
