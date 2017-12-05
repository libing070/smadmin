package com.aspire.shakewxpp.wap.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.aspire.shakewxpp.wap.entity.WeixinRes;

public class HttpPostUtil {
	private static Logger logger =  LoggerFactory.getLogger(HttpPostUtil.class);
	/**
	 * 请求数据流转换成请求报文
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getReqContent(HttpServletRequest request)
			throws Exception {
		/**
         * 消息解析
         */
		StringBuilder sb = new StringBuilder();
		request.setCharacterEncoding("UTF-8");
		BufferedReader reader = null;
		try {
			reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (IOException e) {
			throw new Exception(e);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param backUrl 获取到openid后,回调路径;传NULL则取servletPath
	 * @param redirectFlag  true:重定向到目标url; false：forward到目标url
	 * @param map  微信app信息
	 * @throws Exception
	 */
	public static void getOpenId(HttpServletRequest request,HttpServletResponse response,String backUrl,Map<String,String> map) throws Exception{
		GetConfigFile getConfigFile = GetConfigFile.getInstance();
		String getAuthCode_url = getConfigFile.getProperties("getAuthCode_url");
		String weixinAppNo = request.getParameter("weixinAppNo");
		String appid = (String)map.get("APPID");
		String redirect_uri = getConfigFile.getProperties("redirect_uri");
		String scope = getConfigFile.getProperties("scope");
		String state = request.getParameter("state")==null?"":request.getParameter("state");
		
		String servletPath = backUrl;
		if(servletPath==null){
			servletPath = request.getServletPath();
			String queryString = request.getQueryString(); 
			
			
/*			if(StringUtils.isNotEmpty(queryString)){
				queryString=queryString+"&redirectFlag="+redirectFlag;
			}else{
				queryString="redirectFlag="+redirectFlag;
			}*/
			if(StringUtils.isNotEmpty(queryString)){
				servletPath = servletPath+"?"+queryString;
			}	
		}
		String addPath = "#"+servletPath+"#"+weixinAppNo;
		state = state.replace(addPath, "");
		state = state+addPath;

		state=java.net.URLEncoder.encode(state,"UTF-8"); 
		
		getAuthCode_url=getAuthCode_url+"?appid="+appid+"&redirect_uri="+redirect_uri+"&response_type=code&scope="+scope+"&state="+state+"#wechat_redirect";
		 
		response.setContentType("text/html; charset=UTF-8"); 
		logger.debug("重定向到:"+getAuthCode_url);
		response.sendRedirect(getAuthCode_url); 
	}
	
	
    /**
     * 发送HTTP请求
     *
     * @param sendXml 发送的消息
     * @param sendUrl 请求发送目的端
     * @return 相应XML
     */
    public static String sendHttpPostForAll(Map<String, String> header, String sendContent, String sendUrl) throws Exception{
        HttpClient client = null;
        HttpPost post = null;
        String respStr = null;
        try {
        	GetConfigFile getConfigFile = GetConfigFile.getInstance();
            client = new DefaultHttpClient();
            
            if(sendUrl.startsWith("https")){
            	client = wrapClient(client);
            }
            // 最大连接时间
            int reqTime = Integer.parseInt(getConfigFile.getProperties("DeliverMaxReqTime"));
            // 最大响应时间
            int respTime = Integer.parseInt(getConfigFile.getProperties("DeliverMaxRespTime"));

            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, reqTime);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, respTime);
            post = new HttpPost(sendUrl);
            if (null != header) {
                for (String s : header.keySet()) {
                    post.setHeader(s, header.get(s));
                }
            }
            StringEntity requestEntity = new StringEntity(sendContent, "text/html", "utf-8");
            post.setEntity(requestEntity);
            HttpResponse httpResponse = client.execute(post);
            respStr = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            if (respStr == null) {
            	throw new Exception("往" + sendUrl + "发送消息时出现异常");
            }
        } catch (Exception e) {
        	throw e;
        } finally {
            if (null != post) {
                post.abort();
            }
            if (null != client) {
                client.getConnectionManager().shutdown();
            }
        }
        return respStr;
    }
    
    /**
     * 获取可信任https链接，以避免不受信任证书出现peer not authenticated异常
     * 
     * @param base
     * @return
     */
    public static HttpClient wrapClient(HttpClient base) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs,
                        String string) {
                }

                public void checkServerTrusted(X509Certificate[] xcs,
                        String string) {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = base.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", ssf, 443));
            return new DefaultHttpClient(ccm, base.getParams());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }	
    
    
    public static WeixinRes sendHttpGet(Map<String,String> parameters,String sendUrl) throws Exception{
    	logger.debug("HttpPostUtil.sendHttpGet方法");
    	String respStr = null;// 返回的结果  
    	WeixinRes weixinRes = null;
    	
        BufferedReader in = null; 
        HttpClient client = null;
        HttpGet request = null;
        try {  
        	GetConfigFile getConfigFile = GetConfigFile.getInstance();
            client = new DefaultHttpClient();
            
            if(sendUrl.startsWith("https")){
            	client = wrapClient(client);
            }
            // 最大连接时间
            int reqTime = Integer.parseInt(getConfigFile.getProperties("DeliverMaxReqTime"));
            // 最大响应时间
            int respTime = Integer.parseInt(getConfigFile.getProperties("DeliverMaxRespTime")); 
            // 实例化HTTP方法  
            request = new HttpGet(); 
             
            String full_url = sendUrl;
         	if(parameters!=null){
         		StringBuffer sb= new StringBuffer();
         		String params=null;
 	            // 编码请求参数  
 	            if (parameters.size() == 1) {  
 	                for (String name : parameters.keySet()) {  
 	                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name),"UTF-8"));  
 	                }  
 	                params = sb.toString();  
 	            } else {  
 	                for (String name : parameters.keySet()) {  
 	                    sb.append(name).append("=").append(  java.net.URLEncoder.encode(parameters.get(name),"UTF-8")).append("&");  
 	                }  
 	                String temp_params = sb.toString();  
 	                params = temp_params.substring(0, temp_params.length() - 1);  
 	            }  
 	            full_url = full_url + "?" + params;  
         	} 
         	
         	URL url = new URL(full_url); 
         	URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
            request.setURI(uri);  
             //request.setURI(new URI(full_url));  
             
             client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, reqTime);
             client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, respTime);
             HttpResponse response = client.execute(request);  
             logger.debug("HttpPostUtil.sendHttpGet===client.execute");
             
             in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"UTF-8"));  
             
             
             StringBuffer sb = new StringBuffer("");  
             String line;  
             while ((line = in.readLine()) != null) {  
                 sb.append(line);  
             }  
             in.close();  
             respStr = sb.toString(); 
             
             weixinRes = wxRsponseError(respStr);
         }catch(Exception e){
        	 logger.error("HttpPostUtil.sendHttpGet方法异常",e);
        	 throw e;
         }finally {
        	 if (in != null) {  
        		 in.close(); 
        	 }
             if (null != request) {
            	 request.abort();
             }
             if (null != client) {
                 client.getConnectionManager().shutdown();
             }
         } 
        return weixinRes;
    }
    
    /**
     * 检验微信返回结果
     * @param resStr
     * @return
     */
    private static WeixinRes wxRsponseError(String resStr) throws Exception{
		WeixinRes weixinRes = JSON.parseObject(resStr, WeixinRes.class);
		weixinRes.setReturnJson(resStr);
    	return weixinRes;
    }
    
    

}
