package com.aspire.wifi.wap.ftpUtil;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadCommand extends FTPDownloadCommand {
    private static final Logger logger = LoggerFactory.getLogger("DownloadCommand.class");

    /**
     * 下载文件列表
     */
    private String[] remoteFileList;

    /**
     * 本地目录位置
     */
    private String localDir;

    /**
     * @param account
     */
    public DownloadCommand(FTPAccount account) {
        super(account);
    }

    /**
     * Access method for the remoteFileList property.
     *
     * @return   the current value of the remoteFileList property
     */
    public String[] getRemoteFileList() {
        return remoteFileList;
    }

    /**
     * Sets the value of the remoteFileList property.
     *
     * @param aRemoteFileList the new value of the remoteFileList property
     */
    public void setRemoteFileList(String[] aRemoteFileList) {
        remoteFileList = aRemoteFileList;
    }

    /**
     * Access method for the localDir property.
     *
     * @return   the current value of the localDir property
     */
    public String getLocalDir() {
        return localDir;
    }

    /**
     * Sets the value of the localDir property.
     *
     * @param aLocalDir the new value of the localDir property
     */
    public void setLocalDir(String aLocalDir) {
        localDir = aLocalDir;
    }

    protected void doFTP() throws Exception{
        //下载FTP服务器上文件列表指定的文件到本地文件夹中
        try{
            for (int i = 0; i < remoteFileList.length; i++) { //服务器上文件没准备好，全部不下载，直接返回
                ftpClient.exists(remoteFileList[i]);
            }
        }catch(Exception e){
        	logger.error("FTP文件下载出现异常",e);
        }
        try {
            for(int i=0;i<remoteFileList.length;i++){
            	ftpClient.get(localDir + System.getProperty("file.separator")+ remoteFileList[i],remoteFileList[i]);
                logger.debug(remoteFileList[i]+".FTP文件下载完成");
            }
        } catch (Exception e) {
            logger.error("FTP文件下载出现异常",e);
            throw e;
        }
    }
    
    protected byte[] doFTP(String fileName) throws Exception{
    	byte[] _byte;
        try{
        	_byte = ftpClient.get(fileName);
            logger.debug(fileName+"文件下载完成");
        }catch (Exception e) {
            logger.error("FTP文件下载出现异常",e);
            throw e;
        }
        return _byte;
    }
    
    
    public static void main(String[] args) {
        FTPAccount ftpAccount;
		try {
			ftpAccount = new FTPAccount();

	        DownloadCommand downCommand = new DownloadCommand(ftpAccount);
	        String[] aa = downCommand.getNeedLoadFileArray("WirelessCity","37000001_DataSyn_20121209_000000.req");
	        downCommand.setRemoteFileList(aa);
	        downCommand.setLocalDir("D:/temp");
	        InputStream inPutStream = null;
	        String fileName="";
	        downCommand.excecute(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
    
    
    
}

