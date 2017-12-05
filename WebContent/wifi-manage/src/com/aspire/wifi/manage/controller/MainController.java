package com.aspire.wifi.manage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aspire.wifi.manage.util.GetConfigFile;

@Controller
public class MainController {
    protected static Logger logger = LoggerFactory.getLogger(MainController.class);

    /**
     * 跳转到主页面
     *
     * @return
     */
    @RequestMapping(value = "/main.tv", method = RequestMethod.GET)
    public String getLoginPage() {
        return "/main/main";
    }
    
    /**
     * 跳转到头条发布页面
     *
     * @return
     */
    @RequestMapping(value = "/publish.tv", method = RequestMethod.GET)
	public ModelAndView getCreateActivityPage() throws Exception {
		ModelAndView view = new ModelAndView("/publish/publish");
		// 取得配置的媒体文件存放路径,供页面使用
		view.addObject("imageAccessPath", GetConfigFile.getInstance().getProperties("ImageAccessPath"));
		return view;
	}
    
    /**
     * 跳转到活动页面
     *
     * @return
     */
    @RequestMapping(value = "/activity.tv", method = RequestMethod.GET)
    public ModelAndView getCreateActivity() throws Exception {
    	ModelAndView view = new ModelAndView("/activity/menu_activity");
    	return view;
    }
    
    /**
     * 跳转到视频管理
     * 
     * @return
     */
    
    @RequestMapping(value = "/video.tv", method = RequestMethod.GET)
    public ModelAndView getVideoManager() throws Exception{
    	ModelAndView view = new ModelAndView("/manage/guanli_video");
    	return view;
    }
    
    /**
     * 跳转到应用管理
     * 
     * @return
     */
    
    @RequestMapping(value = "/app.tv", method = RequestMethod.GET)
    public String getAppManager() {
    	return "/manage/guanli_app";
    }
    
}
