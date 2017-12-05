package com.aspire.shakewxpp.wap.util;

import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DESTools {
	protected static Logger logger =  LoggerFactory.getLogger(DESTools.class);
	
	private BASE64Encoder encoder = new BASE64Encoder();
	private BASE64Decoder decoder = new BASE64Decoder();
	private final String key = "qazwsx#EDC$RFV";
	
	
	private static DESTools desTools = new DESTools();
	
	public static DESTools getInstance(){
		
		return desTools;
	}
	/**
	 * 加密
	 * @param datasource
	 * @param password
	 * @return
	 */
	public byte[] desCrypto(byte[] datasource, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			
			Cipher cipher = Cipher.getInstance("DES");
			
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 解密
	 * @param src
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] src, String password) throws Exception {
		
		SecureRandom random = new SecureRandom();
		
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		
		SecretKey securekey = keyFactory.generateSecret(desKey);
		
		Cipher cipher = Cipher.getInstance("DES");
		
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		
		return cipher.doFinal(src);
	}
	
	
	/**
	 * 加密
	 * @param sourceStr
	 * @return
	 */
	public  String desCrypto(String sourceStr){
		String desStr = null;
		try{
			desStr = encoder.encode(desCrypto(sourceStr.getBytes("utf-8"), key));	
			desStr=java.net.URLEncoder.encode(desStr,"UTF-8");
		}catch(Exception e){
			logger.error("des加密出现异常",e);
		}
		return desStr;
	}
	
	/**
	 * 解密
	 * @param sourceStr
	 * @return
	 */
	public String decrypt(String sourceStr){
		String desStr = null;
		try{
			sourceStr = sourceStr.replaceAll(" ", "+");
			try{
				desStr=java.net.URLDecoder.decode(sourceStr,"UTF-8");
				desStr = new String(decrypt(decoder.decodeBuffer(desStr), key), "utf-8");
			}catch(BadPaddingException e){
				desStr = new String(decrypt(decoder.decodeBuffer(sourceStr), key), "utf-8");
			}
		}catch(Exception e){
			logger.error("des解密出现异常",e);
		}
		return desStr;
	}
	
	public static void main(String[] args) throws Exception{
		DESTools des = DESTools.getInstance();// http://211.137.180.18/shakewxpp-wap/qiangFlow.tv?freId=KIR59+uE42k=&weixinAppNo=gh_60d909f10351&from=singlemessage&isappinstalled=0
		
		String aa = "KIR59+uE42k=";
		String bb = des.decrypt(aa);
		System.out.println(bb);

	}
}
