package com.aspire.wifi.wap.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aspire.wifi.wap.entity.StudentReport;
import com.aspire.wifi.wap.entity.UserInfoEntity;
import com.aspire.wifi.wap.service.intf.StudentReportService;
import com.aspire.wifi.wap.service.intf.UserInfoService;
import com.aspire.wifi.wap.util.Constants;
import com.aspire.wifi.wap.util.GetConfigFile;
import com.aspire.wifi.wap.util.PictureUtil;
import com.aspire.wifi.wap.util.ReadFile;
import com.aspire.wifi.wap.util.TimeUtil;
import com.aspire.wifi.wap.util.UploadByFile;
import com.google.gson.Gson;

@Controller
public class SchoolController {
	protected static Logger logger =  LoggerFactory.getLogger(SchoolController.class);
	public static String PATTERN_DATE_4 = "yyyy-MM-dd HH:mm:ss";
	public static String DEFAULT_YINGXIN_PIC = "default_yingxin.jpg";
	@Resource(name = "studentReportService")
	private StudentReportService studentReportService;
	
	@Resource(name = "userInfoService")
	private UserInfoService userInfoService;
	
	/**
	 * 跳转到新版本迎新页面

	 * 
	 * @return
	 */
	@RequestMapping(value = "/yingxin", method = RequestMethod.GET)
	public String yingxin() {
		return "/yingxin";
	}
	
	/**
	 * 跳转到校园简介页面212112
	 * 
	 * @return
	 */
	@RequestMapping(value = "/schoolDetail", method = RequestMethod.GET)
	public String getSchool() {
		return "/schoolDetail";
	}

	
	/**
	 * 跳转到新生概况页面


	 * 
	 * @return
	 */
	@RequestMapping(value = "/schoolNewGuide", method = RequestMethod.GET)
	public String getSchool2() {
		return "/schoolNewGuide";
	}
	
	
	/**
	 * 跳转到校园地图页面


	 * 
	 * @return
	 */
	@RequestMapping(value = "/schoolMap", method = RequestMethod.GET)
	public String getSchool3() {
		return "/schoolMap";
	}
	/**
	 * 跳转到校园地图页面


	 * 
	 * @return
	 */
	@RequestMapping(value = "/schoolActivityRule", method = RequestMethod.GET)
	public String getSchool4() {
		return "/schoolActivityRule";
	}
	

	/**
	 * 上传照片并发送


	 
	@RequestMapping(value = "/uploadSchIma121", method = RequestMethod.POST)
	public @ResponseBody Map<String, ?>  getuploadSchIma(@RequestParam("imagePath") String imagePath) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String filePath=imagePath;
		 String imageName = imagePath.substring(imagePath.lastIndexOf("\\")+1,imagePath.length())  ;
		 String resultPath = "";          //上传后图片所在的路径 
		    FileOutputStream out = null;     //文件输出流 
		    try {                               //验证图片上传的格式是否正确 
		     File f = new File(filePath); 
		        if (!f.isFile()) { 
		        throw new Exception(" 不是图片文件!"); 
		    } 
		     if (f != null && f.exists()) {          //这里的ImageIO属于java工厂类，在工厂类class里面，调用的System.gc()，频繁调用会造成dump，需要考虑优化 
		        BufferedImage image = ImageIO.read(f); // 读入文件 
		        if (image != null) { 
		        BufferedImage tag = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);  //构造一个类型为预定义图像类型之一的 BufferedImage 
		           tag.getGraphics().drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);                     //绘制所需要尺寸大小的图片 
		      
		    //    int lastLength = filePath.lastIndexOf("."); 
		      
		                             //以系统时间来随机的创建图片文件名 
		      //  String fileType = filePath.substring(lastLength);              //获取上传图片的类型 
		        resultPath = GetConfigFile.getInstance().getProperties("image_Path") + imageName; 
		       
		        out = new FileOutputStream(resultPath); 
		        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); 
		        JPEGEncodeParam param = 
		        encoder.getDefaultJPEGEncodeParam(tag); 
		        param.setQuality(0.95f, true); //95%图像       
		        param.setDensityUnit(1);                //像素尺寸单位.像素/英寸     
		        param.setXDensity(300);                  //水平分辨率       
		        param.setYDensity(300);                 //垂直分辨率 
		        encoder.setJPEGEncodeParam(param); 
		        encoder.encode(tag); 
		        tag = null; 
		      } 
		     } 

		     f = null; 
		     resultMap.put("msg", "上传图片成功！");
		     resultMap.put("imageUrl",GetConfigFile.getInstance().getProperties("imageAccessPath")+ imageName);
			resultMap.put("CODE", "TRUE");
		    } catch (Exception ex) { 
		    	ex.printStackTrace(); 
		    	resultMap.put("msg", "上传图片失败！");
				resultMap.put("CODE", "FALSE");
		    } finally { 
			     try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
					resultMap.put("msg", "上传图片失败！");
					resultMap.put("CODE", "FALSE");
				}
		     out = null; 
		    } 


		return resultMap;
	}
	* 
	 * 
	 */
	
	/**
	 * 上传图片接入口


	 * @return
	 */
	@RequestMapping(value = "uploadImage", method = RequestMethod.POST)
	public void imageUpload(@RequestParam("fileBox") CommonsMultipartFile commonsMultipartFile,HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Gson gson = new Gson();
		//判断是否已上传并且未审核完成
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
		//String msisdn = "13692230475";
		StudentReport sr = null;
		try {
			sr = studentReportService.getStudentReport(msisdn);
			if(sr != null){
				String msg = "";
				if(StudentReport.AUDITI_RESULT_PASS == sr.getResult()){
					msg = "图片已经上传并审核通过，不允许再上传图片!";
					resultMap.put("msg", msg);
					resultMap.put("CODE","TRUE");
					outPut(response, gson.toJson(resultMap));
					return;
				}else if(StudentReport.AUDITI_RESULT_WAITING == sr.getResult()){
					msg = "图片已经上传并等待审核，不允许再上传图片!";
					resultMap.put("msg", msg);
					resultMap.put("CODE","TRUE");
					outPut(response, gson.toJson(resultMap));
					return;
				}
				
			}
		} catch (Exception e) {
			logger.error("查询新生："+msisdn+"信息异常", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
			outPut(response, gson.toJson(resultMap));
			return;
		}
		
		//获取原文件名
		String yFileName = commonsMultipartFile.getOriginalFilename();
		String lowerfn = yFileName.toLowerCase();
		if(!lowerfn.endsWith(".jpg")&&!lowerfn.endsWith(".png")&&!lowerfn.endsWith(".jpeg")&&!lowerfn.endsWith(".gif")){
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", "格式只支持jpg、png、jpeg、gif");
			outPut(response, gson.toJson(resultMap));
			return;
		}
		//上传文件
		if (commonsMultipartFile.isEmpty()){
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", "上传图片为空!");
			outPut(response, gson.toJson(resultMap));
			return;
		}
		//获取文件大小(单位byte)
		long fileSize = commonsMultipartFile.getSize();
		long uploadFileSizeMax = 0;//单位K
		String uploadFileSizeMaxStr = "1048576";
		try {
			uploadFileSizeMaxStr = GetConfigFile.getInstance().getProperties("maxUploadPicSize");
		} catch (Exception e1) {
			e1.printStackTrace();
		};
		/*String path = null;
		try {
	
			path = GetConfigFile.getInstance().getProperties("image_Path");
		} catch (Exception e) {
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", "上传图片的路径获取失败!");
			outPut(response, gson.toJson(resultMap));
			return;
		}*/
		if(uploadFileSizeMaxStr != null || !"".equals(uploadFileSizeMaxStr)){
			uploadFileSizeMax = Long.parseLong(uploadFileSizeMaxStr);
		}else{
			uploadFileSizeMax = 1048576;
		}
		if (fileSize >= uploadFileSizeMax) {
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", "文件大小不能超过"+uploadFileSizeMax+"byte!");
			outPut(response, gson.toJson(resultMap));
			return;
		}
	
		//获取新文件名
		String fileName = UploadByFile.restFileName(yFileName);
		//文件类型
		//String fileType = yFileName.substring(yFileName.lastIndexOf("."));
		/*File file = new File(path+fileName);
		try {
			commonsMultipartFile.getFileItem().write(file);
		} catch (Exception e) {
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", "上传图片失败!");
			outPut(response, gson.toJson(resultMap));
			return;
		}*/
		if(!"".equals(fileName)){
			//写student_report数据库表
			try {
				StudentReport studentReport = new StudentReport();
				studentReport.setMsisdn(msisdn);
				int index = yFileName.lastIndexOf('.');
				String postFix = yFileName.substring(index+1);
				studentReport.setReportPic(PictureUtil.compressPicByByte(IOUtils.toByteArray(commonsMultipartFile.getInputStream()),postFix));
				studentReport.setCreateTime(TimeUtil.getTimeStr(new Date(), PATTERN_DATE_4));
				studentReport.setResult(StudentReport.AUDITI_RESULT_NOTCOMMIT);//未提交
				
				UserInfoEntity uinfo = userInfoService.queryUserInfo(msisdn);
				studentReport.setCreateUser(uinfo!=null?uinfo.getNickname():msisdn);
				
				if(sr != null){//update
					studentReportService.updateStudentReport(studentReport);
				}else{//insert
					studentReportService.insertNewStudent(studentReport);
				}
			} catch (org.springframework.dao.TransientDataAccessResourceException e) {
				e.printStackTrace();
				resultMap.put("msg","上传图片过大!");
				resultMap.put("fileName", fileName);
				resultMap.put("CODE","TRUE");
				outPut(response, gson.toJson(resultMap));
				return;
			}catch (Exception e) {
				e.printStackTrace();
				resultMap.put("msg","上传图片失败!");
				resultMap.put("fileName", fileName);
				resultMap.put("CODE","TRUE");
				outPut(response, gson.toJson(resultMap));
				return;
			}
			resultMap.put("msg","上传图片成功!");
			resultMap.put("fileName", fileName);
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
	
	@RequestMapping(value = "/commitUploadPic")
    public void commitUploadPic(HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Gson gson = new Gson();
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
        try {
            StudentReport sr = studentReportService.getStudentReport(msisdn);
            sr.setResult(StudentReport.AUDITI_RESULT_WAITING);//提交待审核状态

            studentReportService.updateReportPicStatus(sr);
            
            resultMap.put("msg","提交成功!");
			resultMap.put("CODE","TRUE");
			outPut(response, gson.toJson(resultMap));
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("msg","提交失败!");
			resultMap.put("CODE","FALSE");
			outPut(response, gson.toJson(resultMap));
        }
    }
	
	@RequestMapping(value = "/getStudentReportInfo")
    public void getStudentReportInfo(HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Gson gson = new Gson();
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
        try {
            StudentReport sr = studentReportService.getStudentReport(msisdn);
            if(sr != null){
            	//更新阅读状态

            	if(sr.getResult()==1){//通过就更新阅读状态

            		studentReportService.updateReportFootStatus(sr.getId()+"");
            	}

            	resultMap.put("result",sr.getResult());
            	resultMap.put("result_desc", sr.getResultDesc());
            }else{
            	resultMap.put("result",StudentReport.AUDITI_RESULT_NOTCOMMIT);
            }
			resultMap.put("CODE","TRUE");
			outPut(response, gson.toJson(resultMap));
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("msg","提交失败!");
			resultMap.put("CODE","FALSE");
			outPut(response, gson.toJson(resultMap));
        }
    }
	
	@RequestMapping(value = "/getReportPic")
    public void getReportPic(HttpServletRequest request, HttpServletResponse response) {
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
		OutputStream out = null;
        try {
            out = response.getOutputStream();
            StudentReport sr = studentReportService.getStudentReport(msisdn);
            byte[] picBytes = null;
            if(sr != null){
            	picBytes = sr.getReportPic();
            	if(picBytes == null || picBytes.length == 0){
            		String app_path = request.getSession().getServletContext().getRealPath("/");
                	String default_yingxin = app_path + File.separator + "resources" + File.separator + "images" + File.separator + DEFAULT_YINGXIN_PIC;
                	File dy = new File(default_yingxin);
                	picBytes = ReadFile.getBytesFromFile(dy);
            	}
            }else{
            	String app_path = request.getSession().getServletContext().getRealPath("/");
            	String default_yingxin = app_path + File.separator + "resources" + File.separator + "images" + File.separator + DEFAULT_YINGXIN_PIC;
            	File dy = new File(default_yingxin);
            	picBytes = ReadFile.getBytesFromFile(dy);
            }
            IOUtils.write(picBytes, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
        	e.printStackTrace();
        }
    }

}
