package com.aspire.shakewxpp.wap.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BASE64 {
	/**    
     * BASE64解密   
     * @param content          
     * @return          
     * @throws Exception          
     */              
    public static String decryptBASE64(String content) throws Exception {               
        return new String((new BASE64Decoder()).decodeBuffer(content),"utf-8");               
    }               
                  
    /**         
     * BASE64加密   
     * @param content          
     * @return          
     * @throws Exception          
     */              
    public static String encryptBASE64(String content) throws Exception {               
        return (new BASE64Encoder()).encodeBuffer(content.getBytes("utf-8"));               
    }
    
}
