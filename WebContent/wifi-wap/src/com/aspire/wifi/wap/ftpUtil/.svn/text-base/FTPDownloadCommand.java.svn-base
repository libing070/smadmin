package com.aspire.wifi.wap.ftpUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.wifi.wap.util.GetConfigFile;
import com.enterprisedt.net.ftp.FTPClient;
import com.enterprisedt.net.ftp.FTPConnectMode;
import com.enterprisedt.net.ftp.FTPTransferType;


public abstract class FTPDownloadCommand {

	private static Logger logger =  LoggerFactory.getLogger("FTPDownloadCommand.class");
	
    /**
     * 远程FTP目录
     */
    protected String remoteDir;
    protected FTPAccount account;
    protected FTPClient ftpClient;
    private int TRY_COUNT;

    /**
     * @param account
     */
    public FTPDownloadCommand(FTPAccount account) {
        this.account = account;
    }

    /**
     * Access method for the remoteDir property.
     *
     * @return   the current value of the remoteDir property
     */
    public String getRemoteDir() {
        return remoteDir;
    }

    /**
     * Sets the value of the remoteDir property.
     *
     * @param aRemoteDir the new value of the remoteDir property
     */
    public void setRemoteDir(String aRemoteDir) {
        remoteDir = aRemoteDir;
    }

    public byte[] excecute(String fileName) throws Exception {
        //连接
        this.connect();
        //执行FTP命令
        byte[] _byte = this.doFTP(fileName);
        //退出
        this.quit();
        return _byte;
    }

    /**
     * 获取ftp连接
     */
    private void connect() throws Exception{
    	GetConfigFile getConfigFile = null;
    	try{		
    		getConfigFile = GetConfigFile.getInstance();
    	}catch(Exception e){
    		logger.error("初始化getConfigFile出错",e);
    	}
    	
        //系统建立FTP链接
        TRY_COUNT = 0;
        
        int FTP_RECONN_COUNT=0;
        int FTP_RECONN_INTERVAL_TIME=0;
        try{
        	FTP_RECONN_COUNT = Integer.parseInt(getConfigFile.getProperties("FTP_RECONN_COUNT"));
        	FTP_RECONN_INTERVAL_TIME = Integer.parseInt(getConfigFile.getProperties("FTP_RECONN_INTERVAL_TIME"));
        }catch(Exception e){
        	logger.error("读取配置项出现异常",e);
        }
        
        try {
            ftpClient = getFtpconnect(account);
        } catch (UnknownHostException e) {
            TRY_COUNT = 1;
            logger.error("不能连接到指定主机IP:" + account.getIp() + " port:" + account.getPort());
        } catch (IOException e) {
        	logger.error("网络出现异常，异常信息如下：" + e);
            for (int i = 0; TRY_COUNT < FTP_RECONN_COUNT; TRY_COUNT++) {
                i++;
                logger.debug("试着第" + (i) + "次重新连接FTP...;");
                try {
                    ftpClient = getFtpconnect(account);
                    TRY_COUNT = 0;
                    break;
                } catch (Exception ee) {
                	logger.error("第" + i + "次连接失败...");
                    try {
                    	logger.error("等待"+ FTP_RECONN_INTERVAL_TIME + "分钟后重新连接...");
                        Thread.sleep(FTP_RECONN_INTERVAL_TIME*60000);
                    } catch (InterruptedException et) {
                    	logger.error("等待时发生异常...");
                    } // 休眠五分制，重新试着连接
                    continue ;
                }
            }
        }catch (Exception e) {
            TRY_COUNT = 1;
            logger.error(e.getMessage(),e);
        } 
        if(TRY_COUNT!=0){
        	logger.error("网络出现异常");
        	throw new Exception("网络出现异常");
        }
        //如果发生FTP连接异常，系统根据配置的FTP重试次数、重试时间间隔，尝试重新上传
        //如果重试的次数超过了系统设置的重试次数，则告警
    }

    private FTPClient getFtpconnect(FTPAccount ftpAccount) throws Exception,
            IOException {

//                if (log.isDebugEnabled()) {
//                        log.debug("准备连接FTP主机：Host:" + ftpHost + "--Port:" + ftpPort
//                                        + "--TimeOut:" + timeOut + "--UserName:" + ftpUserName
//                                        + "--PassWord:" + ftpPassword);// 增加日志
//                }
        // 初始化FTP客户端
        FTPClient ftpClient = null;
        ftpClient = new FTPClient();
        // 设置编码格式
        ftpClient.setControlEncoding("utf-8");
        ftpClient.setTimeout(Integer.parseInt(ftpAccount.getTimeout())*60000);

        // 连接到指定主机
        ftpClient.setRemoteHost(ftpAccount.getIp());
        ftpClient.setRemotePort(Integer.parseInt(ftpAccount.getPort()));
        ftpClient.connect();
        ftpClient.login(ftpAccount.getUser(), ftpAccount.getPassword());
        // 设置FTP的模式为二进制、被动
        ftpClient.setConnectMode(FTPConnectMode.PASV);
        ftpClient.setType(FTPTransferType.BINARY);
//                if (log.isDebugEnabled()) {
//                        log.debug("OK，连接到FTP主机了...");// 增加日志
//                }
        ftpClient.chdir(remoteDir);
        return ftpClient;
    }

    /**
     * 执行ftp命令
     */
    protected abstract void doFTP() throws Exception;
    
    
    /**
     * 执行ftp命令
     */
    protected abstract byte[] doFTP(String fileName) throws Exception;

    /**
     * 退出ftp连接
     */
    private void quit() {
        //系统退出FTP连接
        try {
            ftpClient.quit();
        } catch (Exception e) {
        	logger.error("断开FTP连接出现错误...");
        }

    }
    
    /**
     * 
     * @param path ftp 文件夹路径
     * @param exp 匹配文件名称
     * @return
     */
    public String[] getFileArray(String path ,String exp) throws Exception{
    	connect();
    	setRemoteDir(path); 
    	List<String> fileList = new ArrayList<String>();
    	try{
	    	//"." 表示只取文件名信息
	    	String[] files = ftpClient.dir(".", true);  
	    	for(String fileName : files){
	    		//若匹配
	    		if(fileName.contains(exp)){
	    			fileList.add(fileName);
	    		}
	    	}
    	}catch(Exception e){
    		logger.error("查询文件名出现异常",e);
    	}finally{
    		quit();
    	}
    	return fileList.toArray(new String[fileList.size()]);
    }
    
    public String[] getFileArray(String path) throws Exception{
    	connect();
    	setRemoteDir(path); 
    	String[] files=null;
     	try{
	    	//"." 表示只取文件名信息
	    	files = ftpClient.dir(".", true);  
    	}catch(Exception e){
    		logger.error("查询文件名出现异常",e);
    	}finally{
    		quit();
    	}
    	return files;
    }
    
    /**
     * 
     * @param path_center
     * @param path_bac
     * @return
     */
    public String[] getDiffFileArray(String path_center,String path_bac) throws Exception{
    	List<String> fileLi = new ArrayList<String>();
    	
    	connect();
    	String[] files_center=null;
    	String[] files_bak=null;
     	try{
	    	//"." 表示只取文件名信息
     		ftpClient.chdir(path_center);
     		files_center = ftpClient.dir(".", true); 
     		ftpClient.chdir(path_bac);
     		files_bak = ftpClient.dir(".", true);  
     		
     		for(String file_c:files_center){
     			//是否为新增文件
     			boolean flag = true;
     			for(String file_b:files_bak){
     				if(file_c.equals(file_b)){
     					flag = false;
     					break;
     				}
     			}
     			if(flag){
     				fileLi.add(file_c);
     			}
     		}	
    	}catch(Exception e){
    		logger.error("查询文件名出现异常",e);
    	}finally{
    		quit();
    	}
    	return fileLi.toArray(new String[fileLi.size()]);
    }
    
    /**
     * 获取未下载的文件
     * @param path_center
     * @param path_bac
     * @return
     */
    public String[] getNeedLoadFileArray(String path_center,String doenMaxFileName) throws Exception{ 
    	//设置当前路径
    	remoteDir = path_center;
    	connect();
    	String[] files_center=null;
     	try{
	    	//"." 表示只取文件名信息
     		String[] files_all = ftpClient.dir(); 
     		Arrays.sort(files_all);
     		
     		int index = 0;
     		for(int i=0;i<files_all.length;i++){
     			if(files_all[i].compareTo(doenMaxFileName)>0){
     				index = i;
     				break;
     			}
     		}
     		int length = files_all.length-index;
     		files_center = new String[length];
     		for(int i=0;i<length;i++){
     			files_center[i] = files_all[index+i];
     		}
    	}catch(Exception e){
    		logger.error("查询文件名出现异常",e);
    	}finally{
    		quit();
    	}
     	return files_center;
    }
    

    /**
     * 获取未下载的文件，根据正在表达式过滤
     * @param path_center
     * @param doenMaxFileName
     * @param regex 正在表达式
     * @return
     */
    public String[] getNeedLoadFileArray(String path_center,String doenMaxFileName,String regex) throws Exception{ 
    	List<String> fileLi = new ArrayList<String>();
     	//设置当前路径
    	remoteDir = path_center;
    	connect();
     	try{
	    	//"." 表示只取文件名信息
     		String[] files_all = ftpClient.dir(); 
     		for(String fileName:files_all){
     			if(fileName.matches(regex) && (fileName.compareTo(doenMaxFileName)>0)){
     				fileLi.add(fileName);
     			}
     		}
    	}catch(Exception e){
    		logger.error("查询文件名出现异常",e);
    	}finally{
    		quit();
    	}
     	return fileLi.toArray(new String[fileLi.size()]);
    }
}
