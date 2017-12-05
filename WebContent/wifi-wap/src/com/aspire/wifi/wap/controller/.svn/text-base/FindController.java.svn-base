package com.aspire.wifi.wap.controller;






import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aspire.wifi.wap.entity.AppClickDetail;
import com.aspire.wifi.wap.entity.AppDownload;
import com.aspire.wifi.wap.entity.VideoInfo;
import com.aspire.wifi.wap.entity.VideoPlay;
import com.aspire.wifi.wap.mapper.AppDownloadMapper;
import com.aspire.wifi.wap.service.intf.AppDownloadService;
import com.aspire.wifi.wap.service.intf.VideoInfoService;
import com.aspire.wifi.wap.service.intf.VideoPlayService;
import com.aspire.wifi.wap.util.GetConfigFile;

@Controller
public class FindController {
	private static final Logger logger =  LoggerFactory.getLogger(FindController.class);
	public static final String PATTERN_DATE_4 = "yyyy-MM-dd HH:mm:ss";
	
	@Resource(name = "videoInfoService")
	private VideoInfoService videoInfoService;
	
	@Resource(name = "videoPlayService")
	private VideoPlayService videoPlayService;
	
	@Resource(name="appDownloadService")
	private AppDownloadService appDownloadService;
	
	@Resource(name="appDownloadMapper")
	private AppDownloadMapper appDownloadMapper;
	

	
	/**
	 * 发现首页
	 * 
	 * **/
	
	@RequestMapping(value = "/find/findIndex", method = RequestMethod.GET)
	public ModelAndView findIndex(HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("/find/find_index");
		// 取得配置的媒体文件存放路径,供页面使用
		view.addObject("videoPath", GetConfigFile.getInstance().getProperties("videoAccessPath"));
		view.addObject("index", request.getParameter("index"));
		return view;
	}	
	
	/**
	 * 发现的视频

	 * **/
	
	@RequestMapping(value = "/find/videoIndex", method = RequestMethod.GET)
	public ModelAndView getVideoIndex() throws Exception {
		ModelAndView view = new ModelAndView("/find/find_video");
		// 取得配置的媒体文件存放路径,供页面使用


		view.addObject("videoPath", GetConfigFile.getInstance().getProperties("videoAccessPath"));
		return view;
	}
	
	/**
	 * 发现的视频详细页面

	 * **/
	
	@RequestMapping(value = "/find/videoDetail", method = RequestMethod.GET)
	public ModelAndView videoDetail(@RequestParam("videoId") int videoId,HttpServletRequest request) throws Exception {
		request.setAttribute("videoId", videoId);
		ModelAndView view = new ModelAndView("/find/find_video_detail");
		// 取得配置的媒体文件存放路径,供页面使用
		
		view.addObject("videoPath", GetConfigFile.getInstance().getProperties("videoAccessPath"));
		return view;
	}
	
	/**
	 * 发现的视频列表页面

	 * **/
	
	@RequestMapping(value = "/find/videoList", method = RequestMethod.GET)
	public ModelAndView videoList(@RequestParam("videoType") int videoType,HttpServletRequest request) throws Exception {
		request.setAttribute("videoType", videoType);
		ModelAndView view = new ModelAndView("/find/find_video_list");
		// 取得配置的媒体文件存放路径,供页面使用		
		
		view.addObject("videoPath", GetConfigFile.getInstance().getProperties("videoAccessPath"));
		return view;
	}
	
	
	/***
	 * 新增播放记录信息
	 * */
	@RequestMapping(value = "/find/addVideoPlay", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> insertVideoPlay(@RequestParam("videoId") int videoId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = "";
		if(currentUser.getPrincipal()!=null){
			msisdn=(String)currentUser.getPrincipal();
		}
		try {
			VideoPlay  videoPlay =new VideoPlay();	
			videoPlay.setMobile(msisdn);
			videoPlay.setVideoId(videoId);
			videoPlayService.insertVideoPlay(videoPlay);
			resultMap.put("CODE", "TRUE");
			resultMap.put("msg", "新增播放信息成功");
		} catch (Exception e) {
			logger.error("查询用户："+msisdn+"新增视频播放信息异常", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		} 
		return resultMap;
	}
	/**
	 * ajax请求查询视频信息
	 * **/
	@RequestMapping(value = "/find/getVideoInfo", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> selectVideoInfo(HttpServletRequest request,@RequestParam("beginTimes") int beginTimes,@RequestParam("endTimes") int endTimes,@RequestParam("videoName") String videoName,@RequestParam("videoId") int videoId,@RequestParam("flag") String flag) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			List<VideoInfo> videoInfoList = new  ArrayList<VideoInfo>();//存储主要视频
			List<VideoInfo> videoInfoList2 = new  ArrayList<VideoInfo>();//存储同类型名称视频
			List<VideoInfo> videoInfoList3 = new  ArrayList<VideoInfo>();//存储广告栏的3个视频
			VideoInfo v = new VideoInfo();
			v.setBegin(beginTimes);
			v.setTimes(endTimes);
			v.setVideoName(videoName);
			if(videoId>0){
				v.setVideoId(videoId);
			}
			if(flag.equals("1")){
				videoInfoList= videoInfoService.selectVideoIndexInfo(v);
				videoInfoList3=videoInfoService.selectVideoIndexTopInfo(v);
			}else{
				
				if(flag.equals("3")){//这是查询用户点的这个视频的相关名称的视频
					videoInfoList=videoInfoService.selectVideoInfo(v);	
					int videoType = videoInfoList==null?null:videoInfoList.get(0).getVideoType();
					v.setVideoType(videoType);
					videoInfoList2=videoInfoService.selectVideoIndexInfo(v);				
					

				}else if(flag.equals("2")){
					videoInfoList=videoInfoService.selectVideoInfo(v);	

					v.setTimes(3);
					videoInfoList3=videoInfoService.selectVideoIndexTopInfo(v);
				}else if(flag.equals("4")){
					v.setVideoId(0);
					v.setVideoType(videoId);
					videoInfoList=videoInfoService.selectVideoIndexInfo(v);	

			}
			}
			resultMap.put("videoInfoList",videoInfoList);
			resultMap.put("videoInfoList2",videoInfoList2);
			resultMap.put("videoInfoList3",videoInfoList3);
			resultMap.put("CODE", "TRUE");
		} catch (Exception e) {
			logger.error("查询播放信息异常", e);
			resultMap.put("msg","查询不成功！");
			resultMap.put("CODE", "FALSE");
		} 
		return resultMap;
	}
	

	
	/**
	 * 请求视频的图片或置顶图片

	 * **/
	@RequestMapping(value = "/find/findVideoIcon")
    public void findVideoIcon(
            HttpServletResponse response,HttpServletRequest request,
            @RequestParam(value ="videoId") String videoId
    ) {
        OutputStream out = null;
        String  isTop = request.getParameter("isTopPic");
        try {
            out = response.getOutputStream();
            List<VideoInfo> v = videoInfoService.selectVideoPicById(videoId);
            byte[] picBytes =null;
            if(v!=null){           	
            	picBytes =v.get(0).getVideoPic();   
            	if(isTop!=null){
	            	if(isTop.equals("1")){
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
	
	/**
	 * 发现的下载

	 * **/
	/**
	 * 跳转到app页面
	 * 
	 * @return
	 */
	
	
	@RequestMapping(value = "/find/app", method = RequestMethod.GET)
	public ModelAndView getApp() throws Exception {
		ModelAndView view = new ModelAndView("/find/find_app");
		return view;
	}
	/**
	 * 跳转到app_detail页面
	 * 
	 * @return
	 */

	@RequestMapping(value = "/find/findAppDetail", method = RequestMethod.GET)
	public ModelAndView findAppDetail(@RequestParam("appId") int appId,HttpServletRequest request) throws Exception {
		request.setAttribute("appId",appId);
		ModelAndView view = new ModelAndView("/find/find_app_detail");
		AppDownload a=appDownloadMapper.showAppPic(appId+"");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("appId", appId);
		map.put("clickTimes", a.getClickTimes()+1);
		appDownloadMapper.updateClicktimes(map);
		return view;
	}
	//展示应用列表 (根据条件展示列表)
	@RequestMapping(value = "/find/showAppByLimit")
	public @ResponseBody
	Map<String, ? extends Object> showApp(HttpServletRequest request,@RequestParam("begin") int begin,@RequestParam("times") int times,@RequestParam("appName") String appName,@RequestParam("appType") String appType) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<AppDownload> list= new ArrayList<AppDownload>();
		List<AppDownload> topList= new ArrayList<AppDownload>();

		String isIndex = request.getParameter("isIndex")==null?"":request.getParameter("isIndex");//是否为首页查询
		try{
			AppDownload a = new AppDownload();
			a.setAppName(appName);
			a.setAppType(appType);
			a.setBegin(begin);
			a.setTimes(times);
			a.setIsIndex(isIndex);
			if(begin>0){
				a.setBegin(0);
				a.setId(begin);
			}
			list=appDownloadService.showApp(a);
			if(isIndex.equals("1")){
				topList = appDownloadService.showTopApp(a);				
			}
			resultMap.put("list", list);
			resultMap.put("topList", topList);
			resultMap.put("CODE", "TRUE");
		}catch(Exception e){
			logger.error("获得应用下载列表信息异常", e);
			resultMap.put("msg", "获取应用下载列表失败");
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}
	/**
	 * 请求我的应用图片
	 * **/
	@RequestMapping(value = "/find/findIcon")
    public void listHeadlineContentAttach(
            HttpServletResponse response,
            @RequestParam(value = "appId") String appId,
            @RequestParam(value = "i") String i
    ) {
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            AppDownload a  = appDownloadService.showAppPic(appId);
            byte[] picBytes= null;
            if(i.equals("1")){
            	 picBytes = a.getIcon();  
            }else if(i.equals("2")){
            	picBytes = a.getAppDemoPic1();  
            }else if(i.equals("3")){
            	picBytes = a.getAppDemoPic2();  
            }else if(i.equals("4")){
            	picBytes = a.getAppDemoPic3();  
            }else if(i.equals("5")){
            	picBytes = a.getAppTopPic();
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
	
	
	//下载APP
	@RequestMapping(value = "/find/downloadFile")
	public  @ResponseBody
	Map<String, ? extends Object> downloadFile(@RequestParam("appId") int appId,HttpServletResponse response) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn ="";
		if(currentUser.getPrincipal()!=null){
			msisdn=(String)currentUser.getPrincipal();
		}
	   	try {
	   		AppClickDetail app = new AppClickDetail();
	   		app.setAppId(appId);
	   		app.setMobile(msisdn);
	   		appDownloadService.addAppClickDetail(app);
	   		resultMap.put("CODE", "TRUE");	      
		}catch(Exception e) {
			resultMap.put("CODE", "TRUE");
	   	}
		return resultMap;
	}
	
}
