package com.aspire.wifi.wap.ftpUtil;

import java.io.File;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UploadIntfFileCommand extends UploadCommand {

	private static Logger logger =  LoggerFactory.getLogger("UploadIntfFileCommand.class");

    /**
     * @param account
     */
    public UploadIntfFileCommand(FTPAccount account) {
        super(account);
    }

    protected void doFTP() throws Exception{
        File dirFile = new File(localDir);
        File[] files = dirFile.listFiles();
        try {
            //先上传.dat文件
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.getName().indexOf(".dat") >0) {
                    ftpClient.put(file.getAbsolutePath(), file.getName());
                    file.delete();
                }
            }
            //再上传.verf文件
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.getName().indexOf(".verf" ) >0) {
                    ftpClient.put(file.getAbsolutePath(), file.getName());
                    file.delete();
                }
            }
        } catch (Exception ex) {
            logger.error("UploadIntfFileCommand FTP上传文件失败", ex);
            throw ex;
        }
    }
    
    protected void doFTP(InputStream inPutStream,String fileName) throws Exception{
        try {
               ftpClient.put(inPutStream, fileName);
        } catch (Exception ex) {
            logger.error("UploadIntfFileCommand FTP上传文件失败", ex);
            throw ex;
        }
    }

    public static void main(String[] args) {
        FTPAccount ftpAccount;
		try {
			ftpAccount = new FTPAccount();
	        UploadIntfFileCommand upCommand = new UploadIntfFileCommand(ftpAccount);
	        upCommand.setRemoteDir("/home/wwwpas/music/firstpublish/");
	        upCommand.setLocalDir("E:\\ftptest");
	        InputStream inPutStream = null;
	        String fileName = "";
	        upCommand.excecute(inPutStream,fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

}
