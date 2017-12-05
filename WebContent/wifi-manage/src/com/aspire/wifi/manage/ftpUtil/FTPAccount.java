package com.aspire.wifi.manage.ftpUtil;

import com.aspire.wifi.manage.util.GetConfigFile;


public class FTPAccount {
	
	public FTPAccount() throws Exception{
		GetConfigFile getConfigFile;
		getConfigFile = GetConfigFile.getInstance();
		String ftp_ip = getConfigFile.getProperties("ftp_ip");
		String ftp_port = getConfigFile.getProperties("ftp_port");
		String ftp_user = getConfigFile.getProperties("ftp_user");
		String ftp_passwd = getConfigFile.getProperties("ftp_passwd");
		String ftp_connect_timeout = getConfigFile.getProperties("ftp_connect_timeout");

		ip=ftp_ip;    
		port=ftp_port;
		user=ftp_user;
		password=ftp_passwd;
		timeout=ftp_connect_timeout;			
	}

    /**
     * FTP服务器IP地址
     */
    private String ip;

    /**
     * FTP服务器端口
     */
    private String port;

    /**
     * FTP服务器登录用户名
     */
    private String user;

    /**
     * FTP服务器登录密码
     */
    private String password;

    /**
     * FTP连接超时时间
     */
    private String timeout;

    /**
     * Access method for the ip property.
     *
     * @return   the current value of the ip property
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets the value of the ip property.
     *
     * @param aIp the new value of the ip property
     */
    public void setIp(String aIp) {
        ip = aIp;
    }

    /**
     * Access method for the port property.
     *
     * @return   the current value of the port property
     */
    public String getPort() {
        return port;
    }

    /**
     * Sets the value of the port property.
     *
     * @param aPort the new value of the port property
     */
    public void setPort(String aPort) {
        port = aPort;
    }

    /**
     * Access method for the user property.
     *
     * @return   the current value of the user property
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     *
     * @param aUser the new value of the user property
     */
    public void setUser(String aUser) {
        user = aUser;
    }

    /**
     * Access method for the password property.
     *
     * @return   the current value of the password property
     */
    public String getPassword() {
        return password;
    }

    public String getTimeout() {
        return timeout;
    }

    /**
     * Sets the value of the password property.
     *
     * @param aPassword the new value of the password property
     */
    public void setPassword(String aPassword) {
        password = aPassword;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }
}
