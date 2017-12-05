package com.aspire.wifi.manage.ftpUtil;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CleanCommand extends FTPCommand {
    private static Logger logger =  LoggerFactory.getLogger("CleanCommand.class");
    /**
     * 清理日期：清理该日期之前的所有文件
     */
    private Date cleanDate;
    private FTPAccount account;

    /**
     * @param account
     */
    public CleanCommand(FTPAccount account) {
        super(account);
    }

    /**
     * Access method for the cleanDate property.
     *
     * @return   the current value of the cleanDate property
     */
    public Date getCleanDate() {
        return cleanDate;
    }

    /**
     * Sets the value of the cleanDate property.
     *
     * @param aCleanDate the new value of the cleanDate property
     */
    public void setCleanDate(Date aCleanDate) {
        cleanDate = aCleanDate;
    }

    protected void doFTP() {
        //清理FTP服务器目录下的，cleanDate日期之前的所有文件
        try {
            String[] fileList = ftpClient.dir();
            int j = 0;
            for (int i = 0; i < fileList.length; i++) {
//                Date fileDate = ftpClient.modtime(fileList[i]);
            	String filename = fileList[i];
            	String dataStr = "";
            	if(filename.startsWith("f_") || filename.startsWith("r_"))
            		dataStr = filename.split("_")[3];
            	else
            		dataStr = filename.split("_")[2];
            	Date fileDate = new SimpleDateFormat("yyyyMMdd").parse(dataStr);
                if (fileDate.before(cleanDate)) {
                	logger.debug("删除" + fileList[i]);
                    ftpClient.delete(fileList[i]);
                    j++;
                }
            }
            logger.info(j + "个备份文件被清理");
        } catch (Exception e) {
            logger.error("CleanCommand FTP清理文件失败",e);
        }
    }

    protected void doFTP(InputStream inPutStream,String fileName) {
        //清理FTP服务器目录下的，cleanDate日期之前的所有文件
        try {
            String[] fileList = ftpClient.dir();
            int j = 0;
            for (int i = 0; i < fileList.length; i++) {
//                Date fileDate = ftpClient.modtime(fileList[i]);
            	String filename = fileList[i];
            	String dataStr = "";
            	if(filename.startsWith("f_") || filename.startsWith("r_"))
            		dataStr = filename.split("_")[3];
            	else
            		dataStr = filename.split("_")[2];
            	Date fileDate = new SimpleDateFormat("yyyyMMdd").parse(dataStr);
                if (fileDate.before(cleanDate)) {
                	logger.debug("删除" + fileList[i]);
                    ftpClient.delete(fileList[i]);
                    j++;
                }
            }
            logger.info(j + "个备份文件被清理");
        } catch (Exception e) {
            logger.error("CleanCommand FTP清理文件失败",e);
        }
    }
    
    public static void main(String[] args) {
        FTPAccount ftpAccount;
		try {
			ftpAccount = new FTPAccount();

	        CleanCommand cleanCommand = new CleanCommand(ftpAccount);
	        Date nowDate = new Date();
	        cleanCommand.setCleanDate(nowDate);
	        cleanCommand.setRemoteDir("/home/wwwpas/music/firstpublish/");
	        InputStream inPutStream = null;
	        String fileName = "";
	        cleanCommand.excecute(inPutStream,fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

}

