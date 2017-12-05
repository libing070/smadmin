package com.aspire.shakewxpp.wap.servlet.handler;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.servlet.handler
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/25
 * @Version V1.0
 * Update Logs:
 */
public abstract class Handler implements Runnable {
    public static final String INTERF_MSG_VERSION = "1.0.0.0"; //版本
    public static final String INTERF_MSG_SENDADDR = "LLH_WEIXIN"; //发送地址
    public static final String INTERF_MSG_DESTADDR = "LLH"; //目标地址
    public static final String INTERF_MSG_FUN_PRESENTCOIN = "marketCreditOper"; //消息类型 - 流量币赠送接口
    public static final String INTERF_MSG_FUN_GETTICKET = "getTicket"; //消息类型 - 获取登录令牌接口
    public static final String INTERF_MSG_RESP_HRET_SUCCESS = "0"; //消息返回代码 - 成功

    abstract void handle();
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        handle();
    }
}
