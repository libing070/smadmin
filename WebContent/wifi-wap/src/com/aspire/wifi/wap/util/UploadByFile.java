package com.aspire.wifi.wap.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>上传文件的保存类</p>
 * <p>保存上传文件，提供外部调用的全部接口</p>
 * <p>Copyright (c) 2003-2005 ASPire TECHNOLOGIES (SHENZHEN) LTD All Rights Reserved</p>
 * @author lijun
 * @version 1.0.0.0
 * @since 1.0.0.0
 */

public class UploadByFile {

	/**
	 * 日志引用
	 */
	protected static Logger logger = LoggerFactory
			.getLogger(UploadByFile.class);

	/**
	 * singleton模式的实例
	 */
	private static UploadByFile instance = new UploadByFile();

	/**
	 * 构造方法，由singleton模式调用
	 */
	public UploadByFile() {
	}

	/**
	 * 获取实例
	 * @return 实例
	 */
	public static UploadByFile getInstance() {
		return instance;
	}


	/**
	 * 根据条件保存上传的文件	 * @throws IOException
	 * @param in
	 * @param isOverwrite
	 * @param pathName 
	 * @return 0 成功，1 系统错误，FILE_EXIST_ERR 文件已经存在
	 */

	public int saveFile(InputStream in, String pathName, boolean isOverwrite)
			throws IOException {
		File tmpFile = null;
		BufferedOutputStream bout = null;
		try {
			tmpFile = new File(pathName);
			if (isOverwrite == false && tmpFile.exists()) {
				return ErrorCode.FILE_EXIST_ERR;
			}
			//保存文件
			bout = new BufferedOutputStream(new FileOutputStream(tmpFile));
			int rc = 0;
			byte[] buf = new byte[Constants.TEMP_BUF_SIZE + 1];
			while ((rc = in.read(buf, 0, Constants.TEMP_BUF_SIZE)) > 0) {
				bout.write(buf, 0, rc);
			}
			bout.flush();
		} catch (Exception e) {
			logger.error(e.toString());
			return ErrorCode.FAIL;
		} finally {
			if (bout != null) {
				bout.close();
			}
			if (bout != null) {
				in.close();
			}
			tmpFile = null;
		}
		return ErrorCode.SUCC;
	}
	
	/**
	 * 重新生成文件名
	 * @param fileName
	 * @return
	 */
	public static String restFileName(String fileName){
		//文件后缀
		String suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());
		String tempName = "wxyx_"+(System.currentTimeMillis() + "").substring(5)+ new Random().nextInt(9);
		fileName = tempName+suffix;
		return fileName;
	}
	
	/**
	 *  删除单个文件
	 * @param fileName 文件名
	 * @param FileType 文件类型 1.图片 2.语音 3.其它
	 * @return
	 */  
	public static boolean deleteFile(String fileName,int fileType) {
		boolean flag = false;
		String path = "";
		//得到系统分隔符
		char separatorChar = File.separatorChar;
		try {
			if(fileType == Constants.IMGFILE_TYPE_SINGLE){
				path = GetConfigFile.getInstance().getProperties("ImageUploadPath");
			}else if(fileType == Constants.AUDIOFILE_TYPE_SINGLE){
				path = GetConfigFile.getInstance().getProperties("AudioUploadPath");
			}
		}catch (Exception e) {
			logger.error("获取配置文件异常 ",e);
		}
		path = path.replace('/', separatorChar);
		path = path.replace('\\', separatorChar);
		String filePath = path+fileName;
		File file = new File(filePath);   
		// 路径为文件且不为空则进行删除   
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;   
	}
}
