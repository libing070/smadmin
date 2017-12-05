/**
 * 
 */
package com.aspire.shakewxpp.wap.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * @author chenping http请求
 * @2013-5-21
 */
public class ClientAgent {

	private HttpClient httpclient;
	/** 设置返回的字符编码 */
	private String charset = "UTF-8";
	private String url = "";
	private int sseconds = 30;

	public ClientAgent(int seconds) {
		httpclient = new DefaultHttpClient();
		httpclient.getConnectionManager().closeIdleConnections(sseconds,
				TimeUnit.SECONDS);
	}

	/**
	 * 设置请求头
	 * 
	 * @param httpGet
	 * @param headers
	 * @return
	 */
	private HttpGet setHttpGetHeader(HttpGet httpGet,
			Map<String, Object> headers) {
		if (headers != null && !headers.isEmpty()) {
			for (Map.Entry<String, Object> entry : headers.entrySet()) {
				httpGet.addHeader(entry.getKey(), entry.getValue().toString());
			}
		}
		return httpGet;
	}

	/**
	 * GET方式发送请求--->不设置请求头
	 * 
	 * @param httpget
	 * @return
	 * @throws IOException
	 */
	public String get(HttpGet httpget) throws IOException {
		HttpResponse response = httpclient.execute(httpget);
		return this.getResponseBodyAsString(response);
	}

	/**
	 * 带请求头的GET方式发送请求
	 * 
	 * @param headers
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public String get(Map<String, Object> headers, String url)
			throws IOException {
		HttpGet httpget = new HttpGet(url);
		httpget = this.setHttpGetHeader(httpget, headers);
		return this.get(httpget);
	}

	/**
	 * 指定返回的数据类型的请求
	 * 
	 * @param url
	 * @param headerAccept
	 * @return
	 * @throws IOException
	 */
	private String get(String url, String headerAccept) throws IOException {
		Map<String, Object> headers = this.getCommonHeader();
		headers.put("Accept", headerAccept);
		return this.get(headers, url);
	}

	/**
	 * 返回结果是xml的请求
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public String get(String url) throws IOException {
		return this.get(url, HttpManager.WEB_ACCEPT_XML);
	}

	/**
	 * 返回结果是json的请求
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public String getJson(String url) throws IOException {
		return this.get(url, HttpManager.WEB_ACCEPT_JSON);
	}

	/**
	 * 设置post请求头和参数
	 * 
	 * @param httpPost
	 * @param headers
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private HttpPost setHttpPostHeaderAndParams(HttpPost httpPost,
			Map<String, Object> headers, Map<String, Object> params)
			throws UnsupportedEncodingException {
		if (headers != null && !headers.isEmpty()) {
			for (Map.Entry<String, Object> entry : headers.entrySet()) {
				httpPost.addHeader(entry.getKey(), entry.getValue().toString());
			}
		}
		List nvps = new ArrayList<NameValuePair>();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {

				nvps.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue().toString()));
			}
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		return httpPost;
	}

	/**
	 * post方式请求
	 * 
	 * @param httppost
	 * @return
	 * @throws IOException
	 */
	public String post(HttpPost httppost) throws IOException {
		HttpResponse response = httpclient.execute(httppost);
		return this.getResponseBodyAsString(response);
	}

	/**
	 * 发送请求带请求头和参数
	 * 
	 * @param headers
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public String post(Map<String, Object> headers, String url,
			Map<String, Object> params) throws IOException {
		HttpPost httpPost = new HttpPost(url);
		this.setHttpPostHeaderAndParams(httpPost, headers, params);
		return this.post(httpPost);
	}

	/**
	 * 带请求参数http请求,返回xml数据
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public String post(String url, Map<String, Object> params)
			throws IOException {
		Map<String, Object> headers = this.getCommonHeader();
		headers.put("Accept", HttpManager.WEB_ACCEPT_XML);
		return this.post(headers, url, params);
	}

	/**
	 * 带请求参数http请求,返回json数据
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public String postJson(String url, Map<String, Object> params)
			throws IOException {
		Map<String, Object> headers = this.getCommonHeader();
		headers.put("Accept", HttpManager.WEB_ACCEPT_JSON);
		return this.post(headers, url, params);
	}

	/**
	 * 字符串的post请求
	 * 
	 * @param httppost
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public String post(HttpPost httppost, String str) throws IOException {
		StringEntity reqEntity = new StringEntity(str, "utf8");
		httppost.setEntity(reqEntity);
		HttpResponse response = httpclient.execute(httppost);
		return this.getResponseBodyAsString(response);
	}

	/**
	 * 设置请求头的post请求
	 * 
	 * @param headers
	 * @param url
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public String post(Map<String, Object> headers, String url, String str)
			throws IOException {
		HttpPost httpPost = new HttpPost(url);
		this.setHttpPostHeaderAndParams(httpPost, headers, null);
		return this.post(httpPost, str);
	}

	/**
	 * post请求，返回xml数据
	 * 
	 * @param url
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public String post(String url, String str) throws IOException {
		Map<String, Object> headers = this.getCommonHeader();
		headers.put("Accept", HttpManager.WEB_ACCEPT_XML);
		return this.post(headers, url, str);
	}

	/**
	 * post请求，返回json数据
	 * 
	 * @param url
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public String postJson(String url, String str) throws IOException {
		Map<String, Object> headers = this.getCommonHeader();
		headers.put("Accept", HttpManager.WEB_ACCEPT_JSON);
		return this.post(headers, url, str);
	}

	public void close() {
		httpclient.getConnectionManager().shutdown();
	}

	public HttpClient getHttpclient() {
		return httpclient;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	private String getResponseBodyAsString(HttpResponse response)
			throws IOException {
		String result = null;
		GZIPInputStream gzin;
		if (response.getEntity().getContentEncoding() != null
				&& response.getEntity().getContentEncoding().getValue()
						.toLowerCase().indexOf("gzip") > -1) {
			InputStream is = response.getEntity().getContent();
			gzin = new GZIPInputStream(is);

			InputStreamReader isr = new InputStreamReader(gzin, "iso-8859-1");
			java.io.BufferedReader br = new java.io.BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			String tempbf;
			while ((tempbf = br.readLine()) != null) {
				sb.append(tempbf);
				sb.append("\r\n");
			}
			isr.close();
			gzin.close();
			result = sb.toString();
			result = new String(result.getBytes("iso-8859-1"), this.charset);
		} else {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, this.charset);
			if (entity != null) {
				entity.consumeContent();
			}
		}
		return result;
	}

	private Map<String, Object> getCommonHeader() {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("Accept-Charset", HttpManager.WEB_ACCEPT_CHARSET);
		headers.put("Accept-Encoding", HttpManager.WEB_ACCEPT_ENCODING);
		headers.put("Accept-Language", HttpManager.WEB_ACCEPT_LANGUAGE);
		headers.put("User-Agent", HttpManager.WEB_USER_AGENT);
		return headers;
	}
}
