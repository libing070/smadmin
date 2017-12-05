package com.aspire.wifi.manage.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aspire.wifi.manage.base.BaseController;
import com.aspire.wifi.manage.entity.AppInfoEntity;
import com.aspire.wifi.manage.entity.VideoInfo;
import com.aspire.wifi.manage.exception.WxppException;
import com.aspire.wifi.manage.service.AuditService;
import com.aspire.wifi.manage.util.GetConfigFile;
import com.aspire.wifi.manage.util.PictureUtil;
import com.aspire.wifi.manage.util.ReadFile;
import com.aspire.wifi.manage.util.StringUtil;
@Controller
public class AppListController extends BaseController{
    protected static Logger logger = LoggerFactory.getLogger(VideoListController.class);
    @Resource(name = "auditService")
    private AuditService auditService;
	
	  /**
     * 应用列表查询
     * @param page
     * @param rows
     * @return
     */
	
	@RequestMapping(value = "/appList/queryAppInfoList", method = RequestMethod.POST)
	 public
	    @ResponseBody
	    Map<String, Object> queryAppInfoList(
	    		@RequestParam(value = "page", required = true) Integer page,
	            @RequestParam(value = "size", required = true) Integer size,
	            @RequestParam(value = "appDesc", required = false) String appDesc,
	            @RequestParam(value = "appName", required = false) String appName,
	            @RequestParam(value = "appStatus", required = false) String appStatus
	    )  {
		 Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		 
	         paramMap.put("rows",page*size);
			 paramMap.put("sizeCount",size);
			 paramMap.put("appDesc",appDesc);
			 paramMap.put("appName",appName);
			 paramMap.put("appStatus",appStatus);
			try {
				Integer appcount = auditService.searchAppInfoListCount(paramMap);
				List<AppInfoEntity> applineList = auditService.queryAppInfo(paramMap);
				Integer totalPage=appcount%size==0?appcount/size:appcount/size+1;
				returnMap.put("applineList", applineList);
				returnMap.put("totalPage", totalPage);
				returnMap.put("page", page);
				returnMap.put("appcount", appcount);
				returnMap.put("CODE","TRUE");
			} catch (WxppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				returnMap.put("CODE","FALSE");
	  			returnMap.put("msg","查询失败！");
	  			logger.error("查询应用列表异常"+e);
			}
		   
				
		return returnMap;
	}
	
	/**
	 * 根据id应用
	 * @param page
	 * @param rows
	 * @return
	 */
	
	@RequestMapping(value = "/appList/queryAppInfoByAppId", method = RequestMethod.POST)
	public
	@ResponseBody
	Map<String, Object> queryAppInfoByAppId(
			@RequestParam(value = "appId", required = true) Integer appId
			
	)  {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("appId",appId);
		try {
			List<AppInfoEntity> appInfo = auditService.searchAppInfoByAppId(paramMap);
			if(appInfo!=null&&appInfo.size()==1){
				returnMap.put("appInfo", appInfo.get(0));
				returnMap.put("CODE","TRUE");
			}
		} catch (WxppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnMap.put("CODE","FALSE");
			returnMap.put("msg","查询失败！");
			logger.error("查询应用列表异常"+e);
		}
		
		
		return returnMap;
	}
	
	
	/**
	 * 更新应用表
	 * @param page
	 * @param rows
	 * @return
	 */
	
	@RequestMapping(value = "/auditList/updateAppInfo", method = RequestMethod.POST)
	public
	@ResponseBody
	Map<String, Object> updateVideoInfo(HttpServletResponse response,
			@RequestParam(value = "appId", required = false) String appId,
			@RequestParam(value = "appName", required = false) String appName,
			@RequestParam(value = "appDesc", required = false) String appDesc,
			@RequestParam(value = "appDeveloper", required = false) String appDeveloper,
			@RequestParam(value = "appVersion", required = false) String appVersion,
			@RequestParam(value = "appSize", required = false) String appSize,
			 @RequestParam(value = "appForSystem", required = false) String appForSystem,
			 @RequestParam(value = "icon", required = false) String icon,
			 @RequestParam(value = "picStringList", required = false) String picStringList,
			 @RequestParam(value = "appTopPic", required = false) String appTopPic,
			 @RequestParam(value = "isTopArea", required = false) String isTopArea,
			 @RequestParam(value = "demopicList", required = false) String demopicList
			 
			
	)  {
		
		    logger.debug("开始配置应用");
	        logger.debug("传入参数 appId={}", appId);
	        logger.debug("传入参数 appName={}", appName);
	        logger.debug("传入参数 appDesc={}", appDesc);
	        logger.debug("传入参数 appDeveloper={}", appDeveloper);
	        logger.debug("传入参数 appVersion={}", appVersion);
	        logger.debug("传入参数 appSize={}", appSize);
	        logger.debug("传入参数 appForSystem={}", appForSystem);
	        logger.debug("传入参数 icon={}", icon);
	        logger.debug("传入参数 picStringList={}", picStringList);
	        logger.debug("传入参数 appTopPic={}", appTopPic);
	        logger.debug("传入参数 isTopArea={}", isTopArea);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("appId",appId);
		try {
			 String temp = GetConfigFile.getInstance().getProperties("ImageUploadPath");
			 if(StringUtil.isNotEmpty(icon)){
	               String picPath =icon.substring(0,icon.lastIndexOf(","));
	               String[] strArray = null;   
	               if(picPath.contains(",")){
	                   //do nothing
	               }else{
	            	   File dy = new File(temp+File.separator+picPath);
	            	   byte[]  picBytes = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy),"");
	            		paramMap.put("icon",picBytes);
	               }
             }
			 if(StringUtil.isNotEmpty(appTopPic)){
	               String picPath =appTopPic.substring(0,appTopPic.lastIndexOf(","));
	               String[] strArray = null;   
	               if(picPath.contains(",")){
	            	   //do nothing
	               }else{
	            	   File dy = new File(temp+File.separator+picPath);
	            	   byte[]  picBytes = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy),"");
	            	    if(isTopArea.equals("0")){
	            		paramMap.put("icon",picBytes);
	            		}else{
	            		paramMap.put("appTopPic",picBytes);
	            		}
	               }
           }
			 String[] removePic=null;
			 if(StringUtil.isNotEmpty(demopicList)){
				 String picPath =demopicList.substring(0,demopicList.lastIndexOf(","));
				 String[] a = new String[]{"1","2","3","4"};
				 String[] strArray=null; 
				 strArray = picPath.split(",");
				 removePic = arrContrast(a, strArray);    
				 if(removePic!=null){
					 for (int i = 0; i < removePic.length; i++) {
						 byte[] b=null;
						if(Integer.parseInt(removePic[i])==1){
							 paramMap1.put("appDemoPic1","BLOB");
						}
						if(Integer.parseInt(removePic[i])==2){
							 paramMap1.put("appDemoPic2","BLOB");
						}
						if(Integer.parseInt(removePic[i])==3){
							paramMap1.put("appDemoPic3","BLOB");
						}
						if(Integer.parseInt(removePic[i])==4){
							paramMap1.put("appDemoPic4","BLOB");
						}
					}
				 }
			 }else{
				 paramMap1.put("appDemoPic1","BLOB");				 
				 paramMap1.put("appDemoPic2","BLOB");				 
				 paramMap1.put("appDemoPic3","BLOB");				 
				 paramMap1.put("appDemoPic4","BLOB");				 
			 }
			 //更新成功！
			auditService.updateAppDemoPic(paramMap1);
			 paramMap.put("isTopArea",isTopArea);
			 paramMap.put("appId",appId);
			 paramMap.put("appName",appName);
			 paramMap.put("appDesc",appDesc);
			 paramMap.put("appDeveloper",appDeveloper);
			 paramMap.put("appVersion",appVersion);
			 paramMap.put("appSize",appSize);
			 paramMap.put("appForSystem",appForSystem);
			 if(StringUtil.isNotEmpty(picStringList)){
				 String picPath =picStringList.substring(0,picStringList.lastIndexOf(","));
				 if(picPath.contains(",")){
				 String[] strArray = picPath.split(",");
					 if(removePic!=null){
						 for(int i=0;i<strArray.length;i++){
								 File dy1 = new File(temp+File.separator+strArray[i]);
								 byte[]  picBytes1 = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy1),"");
								 if(removePic[i]=="1"){
									 paramMap.put("appDemoPic1",picBytes1);
								 }
								 if(removePic[i]=="2"){
									 paramMap.put("appDemoPic2",picBytes1);
								 }
								 if(removePic[i]=="3"){
									 paramMap.put("appDemoPic3",picBytes1);
								 }
								 if(removePic[i]=="4"){
									 paramMap.put("appDemoPic4",picBytes1);
								 }
							 }
						 }else{
							 if(strArray.length>=1){
								 File dy1 = new File(temp+File.separator+strArray[0]);
								 byte[]  picBytes1 = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy1),""); 
								 paramMap.put("appDemoPic1",picBytes1);
							 }
							 if(strArray.length>=2){
								 File dy1 = new File(temp+File.separator+strArray[1]);
								 byte[]  picBytes1 = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy1),""); 
								 paramMap.put("appDemoPic2",picBytes1);
							 }
							 if(strArray.length>=3){
								 File dy1 = new File(temp+File.separator+strArray[2]);
								 byte[]  picBytes1 = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy1),""); 
								 paramMap.put("appDemoPic3",picBytes1);
							 }
							 if(strArray.length>=4){
								 File dy1 = new File(temp+File.separator+strArray[3]);
								 byte[]  picBytes1 = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy1),""); 
								 paramMap.put("appDemoPic4",picBytes1);
							 }
						}
				 }else{
					 File dy1 = new File(temp+File.separator+picPath);
					 byte[]  picBytes1 = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy1),""); 
					 if(removePic==null){
						 paramMap.put("appDemoPic1",picBytes1);
					 }else{
						 if(removePic[0]=="1"){
							 paramMap.put("appDemoPic1",picBytes1);
						 }
						 if(removePic[0]=="2"){
							 paramMap.put("appDemoPic2",picBytes1);
						 }
						 if(removePic[0]=="3"){
							 paramMap.put("appDemoPic3",picBytes1);
						 }
						 if(removePic[0]=="4"){
							 paramMap.put("appDemoPic4",picBytes1);
						 }
					 }
				 }
			 }
			 auditService.uploadAppInfo(paramMap);
		     returnMap.put("CODE","TRUE");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("配置应用异常", e);
			returnMap.put("CODE","FALSE");
		}
		
		return returnMap;
	}

	    
	    //处理数组字符(删除相同的)
	    private static String[] arrContrast(String[] arr1, String[] arr2){
	        List<String> list = new LinkedList<String>();
	        for (String str : arr1) {                //处理第一个数组,list里面的值为1,2,3,4
	            if (!list.contains(str)) {
	                list.add(str);
	            }
	        }
	        for (String str : arr2) {      //如果第二个数组存在和第一个数组相同的值，就删除
	            if(list.contains(str)){
	                list.remove(str);
	            }
	        }
	        String[] result = {};   //创建空数组
	        return list.toArray(result);    //List to Array
	}
	/**
	 * 新增应用表
	 * @param page
	 * @param rows
	 * @return
	 */
	
	@RequestMapping(value = "/auditList/insertAppInfo", method = RequestMethod.POST)
	public
	@ResponseBody
	Map<String, Object> insertAppInfo(HttpServletResponse response,
			@RequestParam(value = "appName", required = false) String appName,
			@RequestParam(value = "appDesc", required = false) String appDesc,
			@RequestParam(value = "appDeveloper", required = false) String appDeveloper,
			@RequestParam(value = "appVersion", required = false) String appVersion,
			@RequestParam(value = "appSize", required = false) String appSize,
			@RequestParam(value = "appType", required = false) String appType,
			@RequestParam(value = "appStar", required = false) String appStar,
			 @RequestParam(value = "appForSystem", required = false) String appForSystem,
			 @RequestParam(value = "icon", required = false) String icon,
			 @RequestParam(value = "picStringList", required = false) String picStringList,
			 @RequestParam(value = "appTopPic", required = false) String appTopPic,
			 @RequestParam(value = "isTopArea", required = false) String isTopArea
			
	)  {
		
		logger.debug("开始新增应用");
		logger.debug("传入参数 appName={}", appName);
        logger.debug("传入参数 appDeveloper={}", appDeveloper);
        logger.debug("传入参数 appVersion={}", appVersion);
        logger.debug("传入参数 appSize={}", appSize);
        logger.debug("传入参数 appForSystem={}", appForSystem);
        logger.debug("传入参数 icon={}", icon);
        logger.debug("传入参数 picStringList={}", picStringList);
        logger.debug("传入参数 appTopPic={}", appTopPic);
        logger.debug("传入参数 isTopArea={}", isTopArea);
        logger.debug("传入参数 appDesc={}", appDesc);
        logger.debug("传入参数 appStar={}", appStar);
        logger.debug("传入参数 appType={}", appType);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			String temp = GetConfigFile.getInstance().getProperties("ImageUploadPath");
			  AppInfoEntity a=new AppInfoEntity();
				 if(StringUtil.isNotEmpty(icon)){
		               String picPath =icon.substring(0,icon.lastIndexOf(","));
		               String[] strArray = null;   
		               if(picPath.contains(",")){
		                   //do nothing
		               }else{
		            	   File dy = new File(temp+File.separator+picPath);
		            	   byte[]  picBytes = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy),"");
		            		a.setIcon(picBytes);
		               }
	             }
				 if(StringUtil.isNotEmpty(appTopPic)){
		               String picPath =appTopPic.substring(0,appTopPic.lastIndexOf(","));
		               String[] strArray = null;   
		               if(picPath.contains(",")){
		            	   //do nothing
		               }else{
		            	   File dy = new File(temp+File.separator+picPath);
		            	   byte[]  picBytes = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy),"");
		            	    if(isTopArea.equals("0")){
		            		a.setIcon(picBytes);
		            		}else{
		            		a.setAppTopPic(picBytes);
		            		}
		               }
	           }
				 if(StringUtil.isNotEmpty(picStringList)){
					 String picPath =picStringList.substring(0,picStringList.lastIndexOf(","));
					 String[] strArray =null;   
					 if(picPath.contains(",")){
						 strArray = picPath.split(","); //拆分字符为"," ,然后把结果交给数组strArray 
						 if(strArray.length>=1){
							 File dy1 = new File(temp+File.separator+strArray[0]);
							 byte[]  picBytes1 = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy1),"");
							 a.setAppDemoPic1(picBytes1);
						 }
						 if(strArray.length>=2){
						 File dy2 = new File(temp+File.separator+strArray[1]);
						 byte[]  picBytes2 = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy2),"");
						 a.setAppDemoPic2(picBytes2);
						 }
						 if(strArray.length>=3){
						 File dy3 = new File(temp+File.separator+strArray[2]);
						 byte[]  picBytes3 = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy3),"");
						 a.setAppDemoPic3(picBytes3);
						 }
						 if(strArray.length>=4){
						 File dy4 = new File(temp+File.separator+strArray[3]);
						 byte[]  picBytes4 = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy4),"");
						 a.setAppDemoPic4(picBytes4);
						 }
					 }else{
						 File dy = new File(temp+File.separator+picPath);
						 byte[]  picBytes = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy),"");
						 a.setAppDemoPic1(picBytes);
					 }
				 }
				 a.setIsTopArea(isTopArea);
				 a.setAppName(appName);
				 a.setAppDesc(appDesc);
				 a.setAppType(appType);
				 a.setAppDeveloper(appDeveloper);
				 a.setAppVersion(appVersion);
				 a.setAppSize(appSize);
				 a.setAppStarLevel(appStar);
				 a.setAppForSystem(appForSystem);
		
			auditService.insertAppInfo(a);
			returnMap.put("CODE","TRUE");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("配置应用异常", e);
			returnMap.put("CODE","FALSE");
		}
		
		return returnMap;
	}
	/**
	 * 请求icon

	 * **/
	@RequestMapping(value = "/find/findAppIcon")
    public void findAppIcon(
            HttpServletResponse response,HttpServletRequest request,
            @RequestParam(value ="appId") String appId
    ) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("appId",appId);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            List<AppInfoEntity> a = auditService.searchAppInfoByAppId(paramMap);
            byte[] picBytesIcon =null;
            if(a!=null){
            	picBytesIcon=a.get(0).getIcon();
            }
            IOUtils.write(picBytesIcon, out);
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
	@RequestMapping(value = "/find/findAppPic1")
	public void findAppPic1(
			HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value ="appId") String appId
	) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("appId",appId);
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			List<AppInfoEntity> a = auditService.searchAppInfoByAppId(paramMap);
			byte[] picBytesIcon =null;
			if(a!=null){
				picBytesIcon=a.get(0).getAppDemoPic1();
			}
			IOUtils.write(picBytesIcon, out);
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
	@RequestMapping(value = "/find/findAppPic2")
	public void findAppPic2(
			HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value ="appId") String appId
	) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("appId",appId);
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			List<AppInfoEntity> a = auditService.searchAppInfoByAppId(paramMap);
			byte[] picBytesIcon =null;
			if(a!=null){
				picBytesIcon=a.get(0).getAppDemoPic2();
			}
			IOUtils.write(picBytesIcon, out);
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
	@RequestMapping(value = "/find/findAppPic3")
	public void findAppPic3(
			HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value ="appId") String appId
	) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("appId",appId);
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			List<AppInfoEntity> a = auditService.searchAppInfoByAppId(paramMap);
			byte[] picBytesIcon =null;
			if(a!=null){
				picBytesIcon=a.get(0).getAppDemoPic3();
			}
			IOUtils.write(picBytesIcon, out);
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
	@RequestMapping(value = "/find/findAppPic4")
	public void findAppPic4(
			HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value ="appId") String appId
	) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("appId",appId);
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			List<AppInfoEntity> a = auditService.searchAppInfoByAppId(paramMap);
			byte[] picBytesIcon =null;
			if(a!=null){
				picBytesIcon=a.get(0).getAppDemoPic4();
			}
			IOUtils.write(picBytesIcon, out);
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
	@RequestMapping(value = "/find/findAppTopPic")
	public void findAppTopPic(
			HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value ="appId") String appId
	) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("appId",appId);
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			List<AppInfoEntity> a = auditService.searchAppInfoByAppId(paramMap);
			byte[] picBytesIcon =null;
			if(a!=null){
				picBytesIcon=a.get(0).getAppTopPic();
			}
			IOUtils.write(picBytesIcon, out);
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
}
