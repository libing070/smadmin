package com.aspire.wifi.wap.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aspire.wifi.wap.entity.HeadlineContentAttachEntity;
import com.aspire.wifi.wap.entity.HeadlineContentEntity;
import com.aspire.wifi.wap.entity.HeadlineReplyAttachEntity;
import com.aspire.wifi.wap.entity.HeadlineReplyEntity;
import com.aspire.wifi.wap.entity.UserInfoEntity;
import com.aspire.wifi.wap.mapper.HeadlineContentAttachMapper;
import com.aspire.wifi.wap.mapper.HeadlineReplyMapper;
import com.aspire.wifi.wap.service.intf.HeadlineContentService;
import com.aspire.wifi.wap.service.intf.HeadlineReplyService;
import com.aspire.wifi.wap.service.intf.UserInfoService;
import com.aspire.wifi.wap.util.Constants;
import com.aspire.wifi.wap.util.GetConfigFile;
import com.aspire.wifi.wap.util.PictureUtil;
import com.aspire.wifi.wap.util.ReadFile;
import com.aspire.wifi.wap.util.SensitiveWordUtil;
import com.aspire.wifi.wap.util.StringUtil;
import com.aspire.wifi.wap.util.UploadByFile;
import com.google.gson.Gson;

@Controller
public class HeadlineContentController {
    protected static Logger logger = LoggerFactory.getLogger(HeadlineContentController.class);
    @Resource(name = "headlineContentService")
    private HeadlineContentService headlineContentService;
    @Resource(name = "userInfoService")
    private UserInfoService userInfoService;
    @Resource(name = "headlineReplyService")
    private HeadlineReplyService headlineReplyService;
    @Resource(name = "headlineContentAttachMapper")
    private HeadlineContentAttachMapper headlineContentAttachMapper;
    @Resource(name = "headlineReplyMapper")
    private HeadlineReplyMapper headlineReplyMapper;
    @Resource(name = "sensitiveWordUtil")
    private SensitiveWordUtil sensitiveWordUtil;

    @RequestMapping(value = "/toutiao/toutiaoIndex", method = RequestMethod.GET)
    public ModelAndView toutiaoIndex(HttpServletRequest request) throws Exception {
        ModelAndView view = new ModelAndView("/toutiao/toutiao");
        view.addObject("index", request.getParameter("index"));
        return view;
    }

    @RequestMapping(value = "/toutiao/toutiaoDetailIndex", method = RequestMethod.GET)
    public ModelAndView toutiaoDetailIndex(HttpServletRequest request) throws Exception {
        String headlineId = request.getParameter("headlineId");
        String contentTypeId= request.getParameter("contentTypeId")==null?"":request.getParameter("contentTypeId");
        request.setAttribute("contentTypeId",contentTypeId);
        if (StringUtils.isEmpty(headlineId)) {
            //
        }
        ModelAndView view = new ModelAndView("/toutiao/toutiao_detail");
        view.addObject("headlineId", headlineId);
        Subject currentUser = SecurityUtils.getSubject();
    
        if(currentUser.isAuthenticated()){
        	request.setAttribute("isLogin","true");
        	String mobile =  currentUser.getPrincipal().toString();
        	request.setAttribute("mobile", mobile);
       
        }else{
        	request.setAttribute("isLogin","false");
        }
        return view;
    }

    @RequestMapping(value = "/toutiao/toutiaoEditIndex", method = RequestMethod.GET)
    public ModelAndView toutiaoDeployIndex(HttpServletRequest request) throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        String msisdn = currentUser.getPrincipal().toString();
        UserInfoEntity userInfo = userInfoService.queryUserInfo(msisdn);
        ModelAndView view = new ModelAndView("/toutiao/toutiao_edit");
        view.addObject("nickName", userInfo.getNickname());
        view.addObject("mobile", msisdn);
        String flag = request.getParameter("flag") == null ? "" : request.getParameter("flag").toString();
        String Id = request.getParameter("id") == null ? "" : request.getParameter("id").toString();
        String headlineId = request.getParameter("headlineId") == null ? "" : request.getParameter("headlineId").toString();
        view.addObject("flag",flag);
        view.addObject("Id", Id);
        view.addObject("headlineId", headlineId);
        return view;
    }

    /**
     * 查询头条（首页滚动的标题）
     *
     * @return
     */
    @RequestMapping(value = "/queryTopTitle")
    public
    @ResponseBody
    Map<String, ? extends Object> queryTopTitle() {
        logger.debug("查询头条（首页滚动的标题）");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<HeadlineContentEntity> list = null;
        try {
            list = headlineContentService.queryTopTitle();
            if (list != null) {
                resultMap.put("list", list);
                resultMap.put("CODE", "TRUE");
            }
        } catch (Exception e) {
            logger.error("查询头条异常", e);
            resultMap.put("msg", e.getMessage());
            resultMap.put("CODE", "FALSE");
        }
        return resultMap;
    }


    /**
     * 根据Id返回相应的list
     *
     * @return
     */
    @RequestMapping(value = "/queryTopContentById")
    public
    @ResponseBody
    Map<String, ? extends Object> queryTopContentById(@RequestParam("headlineId") int headlineId) {
        logger.debug("根据ID返回list");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<HeadlineContentEntity> list = null;
        try {
            list = headlineContentService.queryTopContentById(headlineId);
            if (list != null) {
                resultMap.put("list", list);
                resultMap.put("CODE", "TRUE");
            }
        } catch (Exception e) {
            logger.error("查询头条异常", e);
            resultMap.put("msg", e.getMessage());
            resultMap.put("CODE", "FALSE");
        }
        return resultMap;
    }

    /**
     * 查询头条列表
     *
     * @return
     */
    @RequestMapping(value = "/headlineList")
    public
    @ResponseBody
    Map<String, Object> queryHeadlineContent(
            @RequestParam(value = "headlineSch", required = false) String headlineSch,
            @RequestParam(value = "contentTypeId", required = true) String contentTypeId,
            @RequestParam(value = "isOwneHeadline", required = false) String isOwneHeadline,
            @RequestParam(value = "perLoadSize", required = true) int perLoadSize,
            @RequestParam(value = "currpage", required = true) int currpage
    ) {
        logger.debug("根据ID返回list");
        logger.debug("传入参数 headlineSch={}", headlineSch);
        logger.debug("传入参数 contentTypeId={}", contentTypeId);
        logger.debug("传入参数 isOwneHeadline={}", isOwneHeadline);
        logger.debug("传入参数 perLoadSize={}", perLoadSize);
        HeadlineContentEntity headlineContentEntity = new HeadlineContentEntity();
        headlineContentEntity.setField("headlineSch", headlineSch);
        headlineContentEntity.setContentTypeId(contentTypeId);
        headlineContentEntity.setField("perLoadSize", perLoadSize);
        headlineContentEntity.setField("currpage", currpage);
        //
        if (isOwneHeadline != null && isOwneHeadline.equals("yes")) {
            Subject currentUser = SecurityUtils.getSubject();
            String msisdn = currentUser.getPrincipal().toString();
            headlineContentEntity.setMobile(msisdn);
        }
        //
        try {
            return headlineContentService.queryHeadlineContent(headlineContentEntity);
        } catch (Exception e) {
            logger.error("查询头条异常", e);
        }
        return null;
    }
    
    /**
     * 查询首页头条列表
     *
     * @return
     */
    @RequestMapping(value = "/indexHeadlineList")
    public
    @ResponseBody
    Map<String, Object> queryIndexHeadlineContent(
            @RequestParam(value = "newsSize", required = false) int newsSize,
            @RequestParam(value = "guanShuiAndQiuZhuSize", required = true) int guanShuiAndQiuZhuSize,
            @RequestParam(value = "isOwneHeadline", required = false) String isOwneHeadline
    ) {
        logger.debug("根据ID返回list");
        logger.debug("传入参数 newsSize={}", newsSize);
        logger.debug("传入参数 guanShuiAndQiuZhuSize={}", guanShuiAndQiuZhuSize);
        logger.debug("传入参数 isOwneHeadline={}", isOwneHeadline);
        HeadlineContentEntity headlineContentEntity = new HeadlineContentEntity();
        headlineContentEntity.setNewsSize(newsSize);
        headlineContentEntity.setGuanShuiAndQiuZhuSize(guanShuiAndQiuZhuSize);
        headlineContentEntity.setIndexHlTotalSize(newsSize + guanShuiAndQiuZhuSize);
        //
        if (isOwneHeadline != null && isOwneHeadline.equals("yes")) {
            Subject currentUser = SecurityUtils.getSubject();
            String msisdn = currentUser.getPrincipal().toString();
            headlineContentEntity.setMobile(msisdn);
        }
        //
        try {
            return headlineContentService.queryIndexHeadlineContent(headlineContentEntity);
        } catch (Exception e) {
            logger.error("查询头条异常", e);
        }
        return null;
    }

    /**
     * 查询首页头条
     * @param headlineContentEntity
     * @return
     * @author liuyao 2014-08-18
     */
    @RequestMapping(value = "/searchheadlineList", method = RequestMethod.POST)
	public @ResponseBody Map<String, ?> searchHeadlineList(HeadlineContentEntity headlineContentEntity) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<HeadlineContentEntity> headlineList = headlineContentService
					.searchHeadlineList(headlineContentEntity);
			resultMap.put("list", headlineList);
			resultMap.put("CODE", "TRUE");
		} catch (Exception e) {
			logger.error("查询头条信息失败!",e.getMessage());
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	} 

    /**
     * 查询头条图片列表
     *
     * @return
     */
    @RequestMapping(value = "/headlineAttachList")
    public
    @ResponseBody
    List<HeadlineContentAttachEntity> headlineAttachList(
            @RequestParam(value = "headlineId", required = true) Long headlineId
    ) {
        logger.debug("根据ID返回list");
        logger.debug("传入参数 headlineId={}", headlineId);
        List<HeadlineContentAttachEntity> list = null;
        try {
            list = headlineContentService.queryHeadlineAttach(headlineId);
            if (list != null) {
                return list;
            }
        } catch (Exception e) {
            logger.error("查询头条异常", e);
        }
        return null;
    }

    @RequestMapping(value = "/listHeadlineContentAttach")
    public void listHeadlineContentAttach(
            HttpServletResponse response,
            @RequestParam(value = "pictureSerialnum", required = true) Long pictureSerialnum
    ) {
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            byte[] picBytes = headlineContentService.findPic(String.valueOf(pictureSerialnum));
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
    @RequestMapping(value = "/listHeadlineReplyAttach")
    public void listHeadlineReplyAttach(
    		HttpServletResponse response,HttpServletRequest request
    ) {
    	String pictureSerialnum =request.getParameter("pictureSerialnum");
    	OutputStream out = null;
    	try {
    		out = response.getOutputStream();
    		byte[] picBytes=headlineReplyMapper.findReAttach(pictureSerialnum).get(0).getPicture();
    	
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

    @RequestMapping(value = "/headline/modifyHeadlineContent", method = RequestMethod.POST)
    public void deployHeadlineContent(HttpServletResponse response,HttpServletRequest request,
            @RequestParam(value = "uploadPics", required = false) MultipartFile[] uploadPics,
            @RequestParam(value = "headlineId", required = false) String headlineId,
            @RequestParam(value = "contentTypeId", required = true) String contentTypeId,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "content", required = true) String content
    ) {
        logger.debug("开始更新/新增头条");
        logger.debug("传入参数 uploadPics={}", uploadPics);
        logger.debug("传入参数 headlineId={}", headlineId);
        logger.debug("传入参数 contentTypeId={}", contentTypeId);
        logger.debug("传入参数 title={}", title);
        logger.debug("传入参数 content={}", content);
        String picNum= request.getParameter("picNum");
        String isEdit= request.getParameter("isEdit");
        String id= request.getParameter("headIds");
        Map<String, Object> resultMap = new HashMap<String, Object>();
		Gson gson = new Gson();
        List<String> list = new ArrayList<String>();
        Subject currentUser = SecurityUtils.getSubject();
        String msisdn = currentUser.getPrincipal().toString();
        HeadlineReplyEntity reply = new HeadlineReplyEntity();
        //
        try {
            UserInfoEntity userInfo = userInfoService.queryUserInfo(msisdn);
            //
            String[] picNums = picNum.substring(0,picNum.length()).split(",");
    		 for(String pic:picNums){
    			 list.add(pic);            		
    		 }
    		 List<String> numPic = isPic(id,list,isEdit);
            if(isEdit.equals("1")){
            	//查看图片存不存在，存在不更新，不存在，删除图片
//            	String[] picNums;
            	if(picNum.contains(",")){
//            		picNums = picNum.substring(0,picNum.length()).split(",");
//            		 for(String pic:picNums){
//            			 list.add(pic);            		
//            		 }
//            		 List<String> numPic = isPic(id,list,isEdit);
            		 for(String num:numPic){
            			 headlineContentAttachMapper.deleteAttachByNum(Long.valueOf(num));
            		 }
            	}else{
//            		 List<String> numPic = isPic(id,list,isEdit);
            		 for(String num:numPic){
            			 headlineContentAttachMapper.deleteAttachByNum(Long.valueOf(num));
            		 }
            	}
            	//修改文本内容与标题
                HeadlineContentEntity entity = new HeadlineContentEntity();
                entity.setHeadlineId(Long.valueOf(id));
                entity.setTitle(sensitiveWordUtil.filterSensitiveWord(title));
                entity.setOrigTitle(title);
                entity.setContent(sensitiveWordUtil.filterSensitiveWord(content));
                entity.setOrigContent(content);
                entity.setContentTypeId(contentTypeId);
                headlineContentService.modifyHeadlineContent(entity);
            }else if(isEdit.equals("2")||isEdit.equals("3")){
          
              reply.setReplyId(Long.valueOf(id));
              reply.setContent(sensitiveWordUtil.filterSensitiveWord(content));
              reply.setOrigContent(content);
           
	              for(String pic2:numPic){
	            	  headlineReplyMapper.deleteReAttachByNum(pic2);
	              }
              
              
            }else{
            	
  
	            if (StringUtils.isEmpty(headlineId)) { //新增
	                HeadlineContentEntity entity = new HeadlineContentEntity();
	                entity.setMobile(msisdn);
	                entity.setNickname(userInfo.getNickname());
	                entity.setContentTypeId(contentTypeId);
	                entity.setTitle(title);
	                entity.setOrigTitle(title);
	                entity.setContent(content);
	                entity.setOrigContent(content);
	                entity.setIfSupportComment("1"); //允许评论
	                headlineId = headlineContentService.addHeadlineContent(entity);
	            } else { //修改
	                HeadlineContentEntity entity = new HeadlineContentEntity();
	                entity.setHeadlineId(Long.valueOf(headlineId));
	                entity.setMobile(msisdn);
	                entity.setNickname(userInfo.getNickname());
	                entity.setContentTypeId(contentTypeId);
	                entity.setTitle(title);
	                entity.setOrigTitle(title);
	                entity.setContent(content);
	                entity.setOrigContent(content);
	                headlineContentService.modifyHeadlineContent(entity);
	            }
            }
            //
            if (uploadPics != null && uploadPics.length > 0) {
            	for (int i=0;i<uploadPics.length;i++) {
            		if(isEdit.equals("2")||isEdit.equals("3")){
            			HeadlineReplyAttachEntity h =new HeadlineReplyAttachEntity();
            			h.setReplyId(Long.valueOf(id));
            			h.setPicture(handleFile(uploadPics[i]));
            			headlineReplyMapper.uploadHeadlineReplyAttach(h);
            		}else{
	                    HeadlineContentAttachEntity attach = new HeadlineContentAttachEntity();
	                    if(headlineId.equals("")){
	                    	headlineId=id;
	                    }
	                    attach.setHeadlineId(Long.valueOf(headlineId));
	                    attach.setPicture(handleFile(uploadPics[i]));
	                    headlineContentService.insertHeadlineAttach(attach);
            		}
                }
            }
            if(isEdit.equals("2")||isEdit.equals("3")){
            	headlineReplyMapper.updateHeadlineReply(reply);   
            }
            resultMap.put("msg", "发送成功！");
			resultMap.put("CODE", "TRUE");
			outPut(response, gson.toJson(resultMap));
        } catch (Exception ex) {
        	ex.printStackTrace();
            logger.error("录入头条异常", ex);
            resultMap.put("msg","头条发布失败!");
			resultMap.put("CODE","FALSE");
			outPut(response, gson.toJson(resultMap));
        }
        logger.debug("结束更新/新增头条");
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
    
    private byte[] handleFile(MultipartFile file) {
        try {
        	String yFileName = file.getOriginalFilename();
        	int index = yFileName.lastIndexOf('.');
			String postFix = yFileName.substring(index+1);
            return PictureUtil.compressPicByByte(IOUtils.toByteArray(file.getInputStream()),postFix);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/listHeadlineComments")
    @ResponseBody
    public Map<String, Object> listHeadlineComments(
            @RequestParam(value = "headlineId", required = true) String headlineId,
            @RequestParam(value = "preCommentsSize", required = true) String preCommentsSize,
            @RequestParam(value = "more", required = false) String more
    ) {
        logger.debug("开始查询头条评论列表");
        logger.debug("传入参数 headlineId={}", headlineId);
        logger.debug("传入参数 preCommentsSize={}", preCommentsSize);
        logger.debug("传入参数 more={}", more);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        HeadlineReplyEntity headlineReplyEntity = new HeadlineReplyEntity();
        headlineReplyEntity.setHeadlineId(Long.valueOf(headlineId));
        headlineReplyEntity.setField("preCommentsSize", preCommentsSize);
        headlineReplyEntity.setField("more", more);
        headlineReplyEntity.setReplyType("1");
        try {
            returnMap = headlineReplyService.queryHeadlineReplay(headlineReplyEntity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询评论列表出现异常", e);
        }
        logger.debug("结束查询头条评论列表");
        return returnMap;
    }


    @RequestMapping("/findHeadlineContent")
    @ResponseBody
    public Map<String, Object> findHeadlineContent(HttpServletRequest request,
            @RequestParam(value = "headlineId", required = true) String headlineId
    ) {
        logger.debug("开始查询头条详情");
        logger.debug("传入参数 headlineId={}", headlineId);
        HeadlineContentEntity headline = null;
        Boolean readOnly = false;
        try {
            headline = headlineContentService.findHeadlineContent(Long.valueOf(headlineId));
            //增加点赞数


            HeadlineContentEntity headline2=new HeadlineContentEntity();
            headline2.setHeadlineId(Long.valueOf(headlineId));
            headline2.setClickNum(headline.getClickNum()+1);
            headlineContentService.modifyHeadlineContent(headline2);
            String ifSupportComment = StringUtils.isEmpty(headline.getIfSupportComment())?"1":headline.getIfSupportComment();
            readOnly = ifSupportComment.equals("2");
       
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询头条详情异常", e);
        }
        logger.debug("结束查询头条详情");
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("detail", headline);
        returnMap.put("readOnly", readOnly); //判断该头条是否允许评论

        return returnMap;
    }
    @RequestMapping("/findHeadlineReply")
    @ResponseBody
    public Map<String, Object> findHeadlineReply(HttpServletRequest request,
    		@RequestParam(value = "replyId", required = true) String replyId
    ) {
    	logger.debug("开始查询评论详情");
    	logger.debug("传入参数 replyId={}", replyId);
    	HeadlineReplyEntity headline = null;
    	List<HeadlineReplyAttachEntity> headlinePic = null;
        String isFlag = request.getParameter("isFlag");

    	try {
    		if(isFlag==null){
    			headline = headlineReplyMapper.queryReplyById(replyId);
    		}
    		headlinePic=headlineReplyMapper.queryReplyAttach(replyId);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error("查询头条详情异常", e);
    	}
    	logger.debug("结束查询头条详情");
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	returnMap.put("detail", headline);
    	returnMap.put("headlinePicList", headlinePic);
   	
    	return returnMap;
    }

    @RequestMapping(value = "/addComments", method = RequestMethod.POST)
    @ResponseBody
    public void addComments(HttpServletRequest request,
            @RequestParam(value = "headlineId", required = true) String headlineId,
            @RequestParam(value = "comments", required = true) String comments,
            @RequestParam(value = "parentReplyId", required = true) String parentReplyId
            
    ) {
        logger.debug("开始新增评论/回复");
        logger.debug("传入参数 headlineId={}", headlineId);
        logger.debug("传入参数 comments={}", comments);
        logger.debug("传入参数 parentReplyId={}", parentReplyId);
        Subject currentUser = SecurityUtils.getSubject();
        String msisdn = currentUser.getPrincipal().toString();
        String picNums = request.getParameter("picNums");
        String isYouhui = request.getParameter("isYouhui");
        HeadlineReplyEntity replyEntity = new HeadlineReplyEntity();
        try {
            UserInfoEntity userInfo = userInfoService.queryUserInfo(msisdn);
            replyEntity.setHeadlineId(Long.valueOf(headlineId));
            replyEntity.setMobile(msisdn);
            replyEntity.setNickname(userInfo.getNickname());
            replyEntity.setContent(comments);
            replyEntity.setOrigContent(comments);
            replyEntity.setParentReplyId(parentReplyId); //顶级评论
            replyEntity.setParentMobile(msisdn); //顶级评论填写自己的手机号
            replyEntity.setStatus(2); //审核通过
            if(isYouhui==null){
            	replyEntity.setReplyType("1"); //评论类型
            }else{
            	replyEntity.setReplyType("4"); //评论类型
            	replyEntity.setParentReplyId(isYouhui);
            	if(isYouhui.equals("2")){
            		replyEntity.setReplyType(Constants.REPLY_TYPE_3);
            	}
            }
           String replyId =  headlineReplyService.addComments(replyEntity);
            String temp = GetConfigFile.getInstance().getProperties("image_Path");
            if(StringUtil.isNotEmpty(picNums)){
            	String[] picNumString = picNums.substring(0, picNums.lastIndexOf(",")).split(",");
            	for(int i=0;i<picNumString.length;i++){
            		 File dy = new File(temp+File.separator+picNumString[i]);
            		 HeadlineReplyAttachEntity h = new HeadlineReplyAttachEntity();
            		 h.setReplyId(Long.valueOf(replyId));
            		 h.setPicture(PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy),""));
            		 headlineReplyMapper.uploadHeadlineReplyAttach(h);
            		 	
            		
            	}
            }
           
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增评论/回复异常", e);
        }
        logger.debug("结束新增评论/回复");
    }

    @RequestMapping(value = "/deleteHeadline", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public void deleteHeadline(
            @RequestParam(value = "headlineId", required = true) String headlineId
    ) {
        logger.debug("开始删除头条");
        logger.debug("传入参数 headlineId={}", headlineId);
        try {
            headlineContentService.deleteHeadline(Long.valueOf(headlineId));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除头条异常", e);
        }
        logger.debug("结束删除头条");
    }

    @RequestMapping(value = "/votesHeadline", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Integer votesHeadline(
            @RequestParam(value = "headlineId", required = true) String headlineId,
            @RequestParam(value = "parentReplyId", required = true) String parentReplyId,
            @RequestParam(value = "parentMobile", required = true) String parentMobile
    ) {
        logger.debug("开始点赞操作");
        logger.debug("传入参数 headlineId={}", headlineId);
        Subject currentUser = SecurityUtils.getSubject();
        String msisdn = currentUser.getPrincipal().toString();
        try {
            UserInfoEntity userInfo = userInfoService.queryUserInfo(msisdn);
            HeadlineReplyEntity entity = new HeadlineReplyEntity();
            entity.setHeadlineId(Long.valueOf(headlineId));
            entity.setMobile(msisdn);
            entity.setNickname(userInfo.getNickname());
            entity.setReplyType("2");
            //entity.setParentReplyId(parentReplyId);
            entity.setParentReplyId("0");
            entity.setParentMobile(parentMobile);
            return headlineContentService.voteHeadlineContent(entity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("点赞操作异常", e);
        }
        logger.debug("结束点赞操作");
        return 0;
    }

    /**
     * 根据手机号码头像
     *
     * @return
     */
    @RequestMapping(value = "/queryUserHeadShowByMobile", method = RequestMethod.GET)
    public
    @ResponseBody
    void queryUserHeadShow(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("mobile") String mobile
    ) {
        // read defaule pic /resources/images/icon_user_default.png
        String defaultPicPath = "http://" + request.getHeader("Host") + request.getContextPath() + "/resources/images/icon_user_default.png";
        InputStream is = null;
        URL url = null;
        try {
            url = new URL(defaultPicPath);
            is = url.openStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStream out = null;
        if (StringUtils.isEmpty(mobile)) {
            Subject currentUser = SecurityUtils.getSubject();
            mobile = currentUser.getPrincipal().toString();
        }
        byte[] picBytes = null;
        try {
            out = response.getOutputStream();
            UserInfoEntity uie = userInfoService.queryUserInfo(mobile);
            if (uie != null) {
                if (uie.getHead_show() == null) {
                    picBytes = IOUtils.toByteArray(is);
                } else {
                    picBytes = uie.getHead_show();
                }
            } else {
                picBytes = IOUtils.toByteArray(is);
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
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
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
			logger.error("上传的文件为空!");
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", "上传图片失败!");
			outPut(response, gson.toJson(resultMap));
			return;
		}
		//获取文件大小(单位K)
		long fileSize = (commonsMultipartFile.getSize()/1024);
		long uploadFileSizeMax = 0;//单位K
		String uploadFileSizeMaxStr = "0";
		String path = null;
		try {
			uploadFileSizeMaxStr = GetConfigFile.getInstance().getProperties("maxUploadPicSize");
			path = GetConfigFile.getInstance().getProperties("image_Path");
		} catch (Exception e) {
			logger.debug("获取文件上传路径配置项失败");
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", "上传图片失败!");
			outPut(response, gson.toJson(resultMap));
			return;
		}
		String reg = "^[0-9_]+$";
		if(uploadFileSizeMaxStr != null && uploadFileSizeMaxStr.matches(reg)){
			uploadFileSizeMax = Long.parseLong(uploadFileSizeMaxStr);
		}else{
			logger.debug("获取文件上传大小配置项失败");
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", "上传图片失败!");
			outPut(response, gson.toJson(resultMap));
			return;
		}
		if (fileSize >= uploadFileSizeMax) {
			logger.debug("文件大小不能超过"+uploadFileSizeMax+"K!");
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
			PictureUtil.compressPicByByte(IOUtils.toByteArray(commonsMultipartFile.getInputStream()),"");
			commonsMultipartFile.getFileItem().write(file);
		} catch (Exception e) {
			logger.debug("写入图片文件失败!");
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
	 * 是否存在该图片
	 * 
	 * 
	 * **/
	
	public List<String>  isPic(String id,List<String> nums,String flag){
		List<String> list2 = new ArrayList<String>();
		try {
			if(flag.equals("1")){
				List<HeadlineContentAttachEntity> list = headlineContentService.queryHeadlineAttach(Long.valueOf(id));
				for(HeadlineContentAttachEntity h:list){
					list2.add(h.getPictureSerialnum().toString());
				}	
			}else {
				List<HeadlineReplyAttachEntity> list = headlineReplyMapper.queryReplyAttach(id);
				for(HeadlineReplyAttachEntity h:list){
					list2.add(h.getPictureSerialnum().toString());
				}
			}
			list2.removeAll(nums);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据headlineID查询图片异常，"+e);
		}
		return list2;
	}
	
}
