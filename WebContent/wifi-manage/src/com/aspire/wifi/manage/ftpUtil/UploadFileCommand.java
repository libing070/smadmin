package com.aspire.wifi.manage.ftpUtil;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadFileCommand  extends FTPCommand {


	private static Logger logger =  LoggerFactory.getLogger("UploadFileCommand.class");
    /**
     * 需要上传的文件LIST
     */
	private List<File> fileList;


	public List<File> getFileList() {
		return fileList;
	}

	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}

	public UploadFileCommand(FTPAccount account) {
		super(account);
	}


	protected void doFTP() throws Exception{
		try {
			for(File file:fileList){
				ftpClient.put(file.getAbsolutePath(), file.getName());
			}
		} catch (Exception e) {
			logger.error("上传文件报错", e);
			throw e;
		} 
		
	}
	
	
	protected void doFTP(InputStream inPutStream,String fileName) throws Exception{
		try {
				ftpClient.put(inPutStream,fileName);
			} catch (Exception e) {
				logger.error("上传文件报错", e);
				throw e;
			} 
		
	}
	
    public static void main(String[] args) {
        FTPAccount ftpAccount;
		try {
			ftpAccount = new FTPAccount();        
	        
	        UploadFileCommand updateCommand = new UploadFileCommand(ftpAccount);
	        updateCommand.setRemoteDir("/opt/aspire/product/wxyx/userUploadMediaPath/");
	        File file = new File("D:\\test.jpg");
	        List<File> fileList = new ArrayList<File>();
	        fileList.add(file);
	        updateCommand.setFileList(fileList);
	        InputStream inPutStream = null;
	        String fileName="";
	        updateCommand.excecute(inPutStream,fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
	

}
