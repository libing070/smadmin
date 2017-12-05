package com.aspire.wifi.wap.ftpUtil;

import java.io.File;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UploadCommand extends FTPCommand {

	private static final Logger logger =  LoggerFactory.getLogger("UploadCommand.class");

    /**
     * 本地目录位置
     */
    protected String localDir;

    /**
     * @param account
     */
    public UploadCommand(FTPAccount account) {
        super(account);
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
        File dirFile = new File(localDir);
        File[] files = dirFile.listFiles();

            try {
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    ftpClient.put(file.getAbsolutePath(), file.getName());
                    file.delete();
                }
            } catch (Exception ex) {
                logger.error("UploadCommand FTP上传文件失败",ex);
                throw ex;
            }
    }

    protected void doFTP(InputStream inPutStream,String fileName) throws Exception{
    	if(inPutStream==null){
    		doFTP();
    	}else{
	        try {
	                ftpClient.put(inPutStream, fileName);
	            } catch (Exception ex) {
	                logger.error("UploadCommand FTP上传文件失败",ex);
	                throw ex;
	            }
    	}
    }
    
    public static void main(String[] args) {
        FTPAccount ftpAccount;
		try {
			ftpAccount = new FTPAccount();

	        UploadCommand upCommand = new UploadCommand(ftpAccount);
	        upCommand.setRemoteDir("/home/wwwpas/music/firstpublish/");
	        upCommand.setLocalDir("E:\\ftptest");
	        InputStream inPutStream = null;
	        String fileName = "";
	        upCommand.excecute(inPutStream,fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}

