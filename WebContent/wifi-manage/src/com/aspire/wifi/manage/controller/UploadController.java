package com.aspire.wifi.manage.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aspire.wifi.manage.util.Constants;
import com.aspire.wifi.manage.util.GetConfigFile;
import com.aspire.wifi.manage.util.UploadByFile;
import com.google.gson.Gson;

/**
 * @author lijun
 * @version 1.0.0.0
 * @since 1.0.0.0
 */

@Controller
public class UploadController {

	/**
	 * 日志引用
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(UploadController.class);

	/**
	 * 上传图片接入口
	 * @return
	 */
	@RequestMapping(value = "/imageUpload.tv/{uploadType}", method = RequestMethod.POST)
	public void imageUpload(@RequestParam("fileBox") CommonsMultipartFile commonsMultipartFile,@PathVariable(value = "uploadType") Integer uploadType,HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Gson gson = new Gson();
		//上传文件
		if (commonsMultipartFile.isEmpty()){
			LOG.error("上传的文件为空!");
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", "上传图片失败!");
			outPut(response, gson.toJson(resultMap));
			return;
		}
		//获取文件大小(单位K)
		long fileSize = (commonsMultipartFile.getSize()/Constants.kSize);
		long uploadFileSizeMax = 0;//单位K
		String uploadFileSizeMaxStr = "0";
		String path = null;
		try {
			uploadFileSizeMaxStr = GetConfigFile.getInstance().getProperties("ImageUploadSize");
			path = GetConfigFile.getInstance().getProperties("ImageUploadPath");
		} catch (Exception e) {
			LOG.debug("获取文件上传路径配置项失败");
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", "上传图片失败!");
			outPut(response, gson.toJson(resultMap));
			return;
		}
		String reg = "^[0-9_]+$";
		if(uploadFileSizeMaxStr != null && uploadFileSizeMaxStr.matches(reg)){
			uploadFileSizeMax = Long.parseLong(uploadFileSizeMaxStr);
		}else{
			LOG.debug("获取文件上传大小配置项失败");
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", "上传图片失败!");
			outPut(response, gson.toJson(resultMap));
			return;
		}
		if (fileSize >= uploadFileSizeMax) {
			LOG.debug("文件大小不能超过"+uploadFileSizeMax+"K!");
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", "文件大小不能超过"+uploadFileSizeMax+"K!");
			outPut(response, gson.toJson(resultMap));
			return;
		}
		//获取原文件名
		String yFileName = commonsMultipartFile.getOriginalFilename();
		//获取新文件名
		String fileName = UploadByFile.restFileName(yFileName);
		//文件类型
		String fileType = yFileName.substring(yFileName.lastIndexOf("."));
		File file = new File(path+fileName);
		try {
			commonsMultipartFile.getFileItem().write(file);
		} catch (Exception e) {
			LOG.debug("写入图片文件失败!");
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", "上传图片失败!");
			outPut(response, gson.toJson(resultMap));
			return;
		}
		if(!"".equals(fileName)){
			resultMap.put("fileName",fileName);
			resultMap.put("yfileName",yFileName);
			resultMap.put("fileSize",fileSize);
			resultMap.put("fileType",fileType);
			resultMap.put("CODE","TRUE");
			outPut(response, gson.toJson(resultMap));
			return;
		}else{
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", "上传图片失败!");
			outPut(response, gson.toJson(resultMap));
			return;
		}
	}
	
	/**
     * 响应方法
     * @param response
     * @param param
     */
    public void outPut(HttpServletResponse response, String param) {
        response.setContentType("text/html; charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            out.print(param);
            out.flush();
            out.close();
        }catch (IOException e) {
        	LOG.error("响应请求异常：", e);
        }
    }
}
