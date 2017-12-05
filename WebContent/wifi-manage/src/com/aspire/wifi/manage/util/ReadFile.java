package com.aspire.wifi.manage.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	private static String separator = System.getProperty("file.separator");
	public static String LR="\r\n";
	public static String RECORD_SEPERATOR="\\|";
	public static String FIELD_SEPERRATOR=";";
	public static String OBJ_SEPERATOR=",";
	
	
	public static String getStringFromFile(String filePath,String fileName) throws IOException {
		StringBuilder sb = new StringBuilder();
		if(!filePath.endsWith(separator)){
			filePath = filePath+separator;
		}
		File file = new File(filePath+fileName);
		if(file.exists() && !file.isDirectory()){
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line).append(LR);
			}
		}else{
			return null;
		}
		return sb.toString();
	}
	
	/**
	 * 创建文件夹
	 * @param file
	 */
	public static void createPath(String path)
	{
		File filePath = new File(path);
		if (filePath.exists())
		{
			return;
		}
		filePath.mkdirs();
	}
	
	public static byte[] getBytesFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1024*1024*10];//10MB
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
        }
        return null;
    }
}
