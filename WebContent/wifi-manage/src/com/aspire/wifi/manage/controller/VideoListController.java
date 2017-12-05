package com.aspire.wifi.manage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.aspire.wifi.manage.entity.HeadlineContentAttachEntity;
import com.aspire.wifi.manage.entity.HeadlineContentEntity;
import com.aspire.wifi.manage.entity.PinCreatetable;
import com.aspire.wifi.manage.entity.VideoInfo;
import com.aspire.wifi.manage.exception.WxppException;
import com.aspire.wifi.manage.service.AuditService;
import com.aspire.wifi.manage.service.HeadlineContentService;
import com.aspire.wifi.manage.util.GetConfigFile;
import com.aspire.wifi.manage.util.PictureUtil;
import com.aspire.wifi.manage.util.ReadFile;
import com.aspire.wifi.manage.util.StringUtil;
@Controller
public class VideoListController extends BaseController{
    protected static Logger logger = LoggerFactory.getLogger(VideoListController.class);
    @Resource(name = "auditService")
    private AuditService auditService;
	
	  /**
     * 视频列表查询
     * @param page
     * @param rows
     * @return
     */
	
	@RequestMapping(value = "/auditList/queryVideoInfoList", method = RequestMethod.POST)
	 public
	    @ResponseBody
	    Map<String, Object> queryVideoInfoList(
	    		@RequestParam(value = "page", required = true) Integer page,
	            @RequestParam(value = "size", required = true) Integer size,
	            @RequestParam(value = "resourcePath", required = false) String resourcePath,
	            @RequestParam(value = "videoName", required = false) String videoName,
	            @RequestParam(value = "videoStatus", required = false) String videoStatus
	    )  {
		 Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		 
	         paramMap.put("rows",page*size);
			 paramMap.put("sizeCount",size);
			 paramMap.put("resourcePath",resourcePath);
			 paramMap.put("videoName",videoName);
			 paramMap.put("videoStatus",videoStatus);
			try {
				Integer videocount = auditService.searchVideoInfoListCount(paramMap);
				List<VideoInfo> videolineList = auditService.queryVideoInfo(paramMap);
				Integer totalPage=videocount%size==0?videocount/size:videocount/size+1;
				returnMap.put("videolineList", videolineList);
				returnMap.put("totalPage", totalPage);
				returnMap.put("page", page);
				returnMap.put("videocount", videocount);
				returnMap.put("CODE","TRUE");
			} catch (WxppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				returnMap.put("CODE","FALSE");
	  			returnMap.put("msg","查询失败！");
	  			logger.error("查询视频列表异常"+e);
			}
		   
				
		return returnMap;
	}
	/**
	 * 根据id视频
	 * @param page
	 * @param rows
	 * @return
	 */
	
	@RequestMapping(value = "/auditList/queryVideoInfoByVideoId", method = RequestMethod.POST)
	public
	@ResponseBody
	Map<String, Object> queryVideoInfoByVideoId(
			@RequestParam(value = "videoId", required = true) Integer videoId
			
	)  {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("videoId",videoId);
		try {
			List<VideoInfo> videoInfo = auditService.searchVideoInfoByVideoId(paramMap);
			if(videoInfo!=null&&videoInfo.size()==1){
				returnMap.put("videoInfo", videoInfo.get(0));
				returnMap.put("CODE","TRUE");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnMap.put("CODE","FALSE");
			returnMap.put("msg","查询失败！");
			logger.error("查询视频列表异常"+e);
		}
		
		return returnMap;
	}
	
	/**
	 * 更新视频表
	 * @param page
	 * @param rows
	 * @return
	 */
	
	@RequestMapping(value = "/auditList/updateVideoInfo", method = RequestMethod.POST)
	public
	@ResponseBody
	Map<String, Object> updateVideoInfo(HttpServletResponse response,
			@RequestParam(value = "videoId", required = false) String videoId,
			@RequestParam(value = "videoName", required = false) String videoName,
			@RequestParam(value = "videoDesc", required = false) String videoDesc,
			@RequestParam(value = "videoType", required = false) String videoType,
			@RequestParam(value = "isTopArea", required = false) String isTopArea,
			 @RequestParam(value = "picStringList", required = false) String picStringList
			
	)  {
		
		    logger.debug("开始配置视频");
	        logger.debug("传入参数 videoId={}", videoId);
	        logger.debug("传入参数 videoName={}", videoName);
	        logger.debug("传入参数 videoDesc={}", videoDesc);
	        logger.debug("传入参数 videoType={}", videoType);
	        logger.debug("传入参数 isTopArea={}", isTopArea);
	        logger.debug("传入参数 picStringList={}", picStringList);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			 String temp = GetConfigFile.getInstance().getProperties("ImageUploadPath");
			 if(StringUtil.isNotEmpty(picStringList)){
	               String picPath =picStringList.substring(0,picStringList.lastIndexOf(","));
	               String[] strArray = null;   
	               if(picPath.contains(",")){
	                   strArray = picPath.split(","); //拆分字符为"," ,然后把结果交给数组strArray 
		               for(String pic:strArray){
		            	     File dy = new File(temp+File.separator+pic);
		            	   	 byte[]  picBytes = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy),"");
			                 VideoInfo videoInfo = new VideoInfo();
			                 videoInfo.setVideoId(Long.valueOf(videoId).toString());
		               }
	               }else{
	            	   File dy = new File(temp+File.separator+picPath);
	            	   byte[]  picBytes = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy),"");
	            	    if(isTopArea.equals("0")){
	            		paramMap.put("videoPic",picBytes);
	            		}else{
	            		paramMap.put("videoTopPic",picBytes);
	            		}
	               }
             }
			 paramMap.put("isTopArea",isTopArea);
			 paramMap.put("videoId",videoId);
			 paramMap.put("videoName",videoName);
			 paramMap.put("videoDesc",videoDesc);
			 paramMap.put("videoType",videoType);
		     auditService.uploadVideoInfo(paramMap);
		     returnMap.put("CODE","TRUE");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("配置视频异常", e);
			returnMap.put("CODE","FALSE");
		}
		
		return returnMap;
	}
	
	/**
	 * 新增视频表
	 * @param page
	 * @param rows
	 * @return
	 */
	
	@RequestMapping(value = "/auditList/insertVideoInfo", method = RequestMethod.POST)
	public
	@ResponseBody
	Map<String, Object> insertVideoInfo(HttpServletResponse response,
			@RequestParam(value = "videoName", required = false) String videoName,
			@RequestParam(value = "resourcePath", required = false) String resourcePath,
			@RequestParam(value = "showTimeLong", required = false) String showTimeLong,
			@RequestParam(value = "videoDesc", required = false) String videoDesc,
			@RequestParam(value = "videoType", required = false) String videoType,
			@RequestParam(value = "isTopArea", required = false) String isTopArea,
			@RequestParam(value = "picStringList", required = false) String picStringList
			
	)  {
		
		logger.debug("开始配置视频");
		logger.debug("传入参数 videoName={}", videoName);
		logger.debug("传入参数 resourcePath={}", resourcePath);
		logger.debug("传入参数 showTimeLong={}", showTimeLong);
		logger.debug("传入参数 videoDesc={}", videoDesc);
		logger.debug("传入参数 videoType={}", videoType);
		logger.debug("传入参数 isTopArea={}", isTopArea);
		logger.debug("传入参数 picStringList={}", picStringList);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			String temp = GetConfigFile.getInstance().getProperties("ImageUploadPath");
			  VideoInfo v=new VideoInfo();
			if(StringUtil.isNotEmpty(picStringList)){
				String picPath =picStringList.substring(0,picStringList.lastIndexOf(","));
				String[] strArray = null;   
				if(picPath.contains(",")){
					//什么都不做
				}else{
					File dy = new File(temp+File.separator+picPath);
					byte[]  picBytes = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy),"");
					if(isTopArea.equals("0")){
						v.setVideoPic(picBytes);
					}else{
						v.setVideoTopPic(picBytes);
					}
				}
			}
			v.setIsTopArea(isTopArea);
			v.setVideoDesc(videoDesc);
			v.setVideoName(videoName);
			v.setShowTimeLong(showTimeLong);
			v.setResourcePath(resourcePath);
			v.setVideoType(videoType);
		
			auditService.insertVideoInfo(v);
			returnMap.put("CODE","TRUE");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("配置视频异常", e);
			returnMap.put("CODE","FALSE");
		}
		
		return returnMap;
	}
	/**
	 * 请求视频的图片或置顶图片

	 * **/
	@RequestMapping(value = "/find/findVideoIcon")
    public void findVideoIcon(
            HttpServletResponse response,HttpServletRequest request,
            @RequestParam(value ="videoId") String videoId
    ) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("videoId",videoId);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            List<VideoInfo> v = auditService.searchVideoInfoByVideoId(paramMap);
            byte[] picBytes =null;
            if(v!=null){           	
            	picBytes =v.get(0).getVideoPic();   
            	if(v.get(0).getIsTopArea()!=null){
	            	if(v.get(0).getIsTopArea().equals("1")){
	            		picBytes =v.get(0).getVideoTopPic();            		
	            	}
            	}
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
}
