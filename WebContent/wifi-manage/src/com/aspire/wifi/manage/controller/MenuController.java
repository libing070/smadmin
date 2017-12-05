package com.aspire.wifi.manage.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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

import com.aspire.wifi.manage.entity.HeadlineContentAttachEntity;
import com.aspire.wifi.manage.entity.HeadlineContentEntity;
import com.aspire.wifi.manage.entity.HeadlineReplyEntity;
import com.aspire.wifi.manage.entity.UserInfoEntity;
import com.aspire.wifi.manage.service.HeadlineContentService;
import com.aspire.wifi.manage.util.GetConfigFile;
import com.aspire.wifi.manage.util.PictureUtil;
import com.aspire.wifi.manage.util.ReadFile;
import com.aspire.wifi.manage.util.StringUtil;
import com.google.gson.Gson;

@Controller
public class MenuController {
    protected static Logger logger = LoggerFactory.getLogger(MenuController.class);
	@Resource(name = "headlineContentService")
	private HeadlineContentService headlineContentService;
	
    /**
     * 跳转到活动页面
     *
     * @return
     */
    @RequestMapping(value = "/menu/Activity", method = RequestMethod.GET)
    public String getActivity() {
        return "/menu_activity";
    }
    /**
     * 跳转到迎新页面
     *
     * @return
     */
    @RequestMapping(value = "/menu/School", method = RequestMethod.GET)
    public String getSchool() {
    	return "/menu_school";
    }
    /**
     * 跳转到抢桌页面
     *
     * @return
     */
    @RequestMapping(value = "/menu/qiangzhuo", method = RequestMethod.GET)
    public String getQiangzhuo() {
    	return "/activity/menu_qiangzhuo";
    }
    /**
     * 发布头条
     *
     * @return
     */
    @RequestMapping(value = "/menu/addContentline", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, String> addContentline(HeadlineContentEntity hce) {
    	  Map<String, String> returnMap = new HashMap<String, String>();
    	return returnMap;
    }
    /**
     * 查询头条列表
     * 
     * */
    @RequestMapping(value = "/menu/queryContentlineList", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> queryContentlineList(@RequestParam("content") String content,
    										 @RequestParam("mobile") String mobile,
    										 @RequestParam("contentTypeId")  int contentTypeId,
    										 @RequestParam("begin") int begin,
    										 @RequestParam("end") int end , 
    										 @RequestParam(value = "isManger") String isManger//(是否为管理员 0否。1是)
) {
    	
    	Map<String, Object> returnMap = new HashMap<String, Object>();
  		Map<String, Object> paramMap = new HashMap<String, Object>();
  		try{
  			  paramMap.put("begin",begin);
  			  paramMap.put("end",end);
    	  	//按关键字和手机号码号码（后4位）查找,分页
    		  paramMap.put("content",content);
    		  paramMap.put("mobile",mobile);
    		  //按类别查询查找，分页
    		  paramMap.put("contentTypeId",contentTypeId);
    		  if(isManger.equals("0")){
    			  isManger="";
    		  }
    		  paramMap.put("isManger",isManger);
    		  List<HeadlineContentEntity> headlineList = headlineContentService.searchHeadlineList(paramMap);
    		  for(HeadlineContentEntity h:headlineList){
    			  List<HeadlineContentAttachEntity> picList = headlineContentService.queryHeadlineAttach(h.getHeadlineId());
    			  h.setPicList(picList);
    		  }
    		  returnMap.put("headlineList", headlineList);
    		  Integer headcount =  headlineContentService.searchHeadlineListCount(paramMap);
    		  int totalpage=headcount/(end-0);
    		  if(headcount%(end-0)!=0){
    			  totalpage=headcount/(end-0)+1;
    		  }
    		  returnMap.put("totalPage",totalpage);

    		  returnMap.put("CODE","TRUE");
  		}catch(Exception e){
  			returnMap.put("CODE","FALSE");
  			returnMap.put("msg","查询失败！");
  			logger.error("查询头条列表异常"+e);
  		}		  
    	return returnMap;
    }
    
    /**
     * 更新头条（设置）
     *
     * @return
     */
    @RequestMapping(value = "/updateHeadline", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> headlineAttachList(
            @RequestParam(value = "headlineId", required = true) String headlineId,
            @RequestParam(value = "limitReply", required = true) String limitReply,//禁止评论( 1 可以\n            2 不可以)
          //头条首页推广(或公告)位置(1，置顶；2，首页头条推荐2；3，首页头条推荐3；4，首页头条推荐4；5，首页头条推荐5；6，首页头条推荐6；)
          //当是公告时（21，头条公告推荐1；22，头条公告推荐2；23，头条公告推荐3；24，头条公告推荐4；）
            @RequestParam(value = "tuiguangNum", required = true) String tuiguangNum,
            @RequestParam(value = "isDel", required = true) String isDel//(3,表示删除)
                     
    ) {
        logger.debug("根据ID返回list");
        logger.debug("传入参数 headlineId={}", headlineId);
        logger.debug("传入参数 limitReply={}", limitReply);
        logger.debug("传入参数 tuiguangNum={}", tuiguangNum);
        logger.debug("传入参数 isDel={}", isDel);
	  	  Map<String, Object> returnMap = new HashMap<String, Object>();
		  Map<String, Object> paramMap = new HashMap<String, Object>();
		  String newIsDel = isDel;
		  String[] strArray = null;   
		  Long[] newHeadlineId  =null; 
		  if(headlineId.equals("")){
			  newIsDel=newIsDel.substring(0,newIsDel.lastIndexOf(","));
              if(newIsDel.contains(",")){
                  strArray = newIsDel.split(","); //拆分字符为"," ,然后把结果交给数组strArray 
                  newHeadlineId = new Long[strArray.length];        
                  for (int idx = 0; idx < strArray.length; idx++) {            
                	  newHeadlineId[idx] = Long.parseLong(strArray[idx]);        
                  }
                  paramMap.put("isDel", newHeadlineId);
              }else{
            	  newHeadlineId = new Long[1];     
            	  newHeadlineId[0]=Long.parseLong(newIsDel);
              	paramMap.put("isDel",newHeadlineId );
           	
              }
		  }else{
			  newIsDel=headlineId;
			  if(isDel.equals("3")){
				  headlineId="";
			  }
			  newHeadlineId = new Long[1];   
			  newHeadlineId[0]=Long.parseLong(newIsDel);
			  paramMap.put("isDel",newHeadlineId);
		  }
		 		 
		  
	  	  paramMap.put("headlineId",headlineId);
	  	  paramMap.put("limitReply",limitReply);
	  	//按关键字和手机号码号码（后4位）查找,分页
		  paramMap.put("tuiguangNum",tuiguangNum);
        try {
        	headlineContentService.updateHeadline(paramMap);
          returnMap.put("CODE", "TRUE");
          if(StringUtil.isNotEmpty(isDel)){
          returnMap.put("msg","3");
          }
        } catch (Exception e) {
        	returnMap.put("CODE", "FALSE");
            logger.error("设置头条异常", e);
        }
        return returnMap;
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
    
    /**
     * 查询已删除
     * 
     * */
    @RequestMapping(value = "/menu/queryDelheadlineList", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> queryContentlineList(@RequestParam("contentHeadline") String contentHeadline,
    										 @RequestParam("contentReply") String contentReply,
    										 @RequestParam("moibleHeadline")  String moibleHeadline,
    										 @RequestParam("moibleReply")  String moibleReply,
    										 @RequestParam("begin") int begin,
    										 @RequestParam("end") int end ,
    										 @RequestParam("flag") String flag 
) {
    	
    	  Map<String, Object> returnMap = new HashMap<String, Object>();
  		Map<String, Object> paramMap = new HashMap<String, Object>();
  		List<HeadlineReplyEntity> delStatusReplyList = new ArrayList<HeadlineReplyEntity>();
  		List<HeadlineContentEntity> delStatusHeadlineList = new ArrayList<HeadlineContentEntity>();
  		try{  
  			 paramMap.put("contentHeadline",contentHeadline);
  			 paramMap.put("contentReply",contentReply);
  			 paramMap.put("moibleHeadline",moibleHeadline);
  			 paramMap.put("moibleReply",moibleReply);
  			 paramMap.put("begin",begin);
  			 paramMap.put("end",end);
  			Long[] status = {3l};
  			 paramMap.put("status",status);//查询删除状态
  			 Long[] headlineStatus = {0l,2l,3l};
     		paramMap.put("headlineStatus",headlineStatus);//查询评论的头条状态

  			 Integer headcount =  0;
  			if(flag.equals("2")){
  				//表示查询的是评论
  				delStatusReplyList = headlineContentService.querydelStatusReplyList(paramMap);
  				headcount = headlineContentService.querydelStatusReplyListCount(paramMap);
  			}else{
  				//表示查询的是头条
  				delStatusHeadlineList = headlineContentService.queryDelStatusHeadLineList(paramMap);
  				 for(HeadlineContentEntity h:delStatusHeadlineList){
  	    			  List<HeadlineContentAttachEntity> picList = headlineContentService.queryHeadlineAttach(h.getHeadlineId());
  	    			  h.setPicList(picList);
  	    		  }
  				headcount=headlineContentService.queryDelStatusHeadLineListCount(paramMap);
  			}
   		  int totalpage=headcount/end;
   		  if(headcount%end!=0){
   			  totalpage=headcount/end+1;
   		  }
   		  returnMap.put("delStatusReplyList",delStatusReplyList);
   		  returnMap.put("delStatusHeadlineList",delStatusHeadlineList);
   		  returnMap.put("totalPage",totalpage);
   		  returnMap.put("CODE","TRUE");	 
  		} catch (Exception e) {
        	returnMap.put("CODE", "FALSE");
            logger.error("设置头条异常", e);
        }
        return returnMap;
    }
    /**
     * 查询评论（未审核，审核通过）
     * 
     * */
    @RequestMapping(value = "/menu/queryReplyList", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> queryReplyList(@RequestParam("contentReply") String contentReply,
			 @RequestParam("moibleReply") String moibleReply,
			 @RequestParam("begin") int begin,
			 @RequestParam("end") int end
    ) {
    	
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	List<HeadlineReplyEntity> replyList = new ArrayList<HeadlineReplyEntity>();
    	try{  
    		paramMap.put("contentReply",contentReply);
    		paramMap.put("moibleReply",moibleReply);
    		paramMap.put("begin",begin);
    		paramMap.put("end",end);
    		Long[] status = {0l,2l};
    		paramMap.put("status",status);//查询未审核和审核通过评论状态
    		Long[] headlineStatus = {0l,2l};
    		paramMap.put("headlineStatus",headlineStatus);//查询评论的头条状态
    		Integer headcount =  0;
    		replyList = headlineContentService.querydelStatusReplyList(paramMap);
    		headcount = headlineContentService.querydelStatusReplyListCount(paramMap);   	
    		int totalpage=headcount/end;
    		if(headcount%end!=0){
    			totalpage=headcount/end+1;
    		}
    		returnMap.put("replyList",replyList);
    		returnMap.put("totalPage",totalpage);
    		returnMap.put("CODE","TRUE");	 
    	} catch (Exception e) {
    		returnMap.put("CODE", "FALSE");
    		logger.error("设置头条异常", e);
    	}
    	return returnMap;
    }
    
    /**
     * 更新评论（删除）
     *
     * @return
     */
    @RequestMapping(value = "/menu/delReply", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> delReply(
            @RequestParam(value = "replyId", required = true) String replyId
                     
    ) {
	  	  Map<String, Object> returnMap = new HashMap<String, Object>();
	     Map<String, Object> paramMap = new HashMap<String, Object>();

        try {
        	Long[] newreplyId =null; 
        	if(replyId.contains(",")){
        		replyId = replyId.substring(0,replyId.lastIndexOf(","));
        	    String[] strArray = null;   
   
                if(replyId.contains(",")){
                    strArray = replyId.split(","); //拆分字符为"," ,然后把结果交给数组strArray 
                    newreplyId = new Long[strArray.length]; 
                    for (int idx = 0; idx < strArray.length; idx++) {            
                    	newreplyId[idx] = Long.parseLong(strArray[idx]);        
                      }
                    paramMap.put("replyId", newreplyId);
                }else{
                	newreplyId = new Long[1]; 
                	newreplyId[0]=Long.valueOf(replyId);
                	paramMap.put("replyId", newreplyId);
             	
                }
        	}else{
        		newreplyId = new Long[1]; 
            	newreplyId[0]=Long.valueOf(replyId);
            	paramMap.put("replyId", newreplyId);
        	}
        	headlineContentService.delReplyById(paramMap);
          returnMap.put("CODE", "TRUE");
        } catch (Exception e) {
        	returnMap.put("CODE", "FALSE");
            logger.error("设置头条异常", e);
        }
        return returnMap;
    }
    @RequestMapping(value = "/menu/modifyHeadlineContent", method = RequestMethod.POST)
    public void deployHeadlineContent(HttpServletResponse response,
            @RequestParam(value = "picStringList", required = false) String picStringList,
            @RequestParam(value = "contentTypeId", required = true) String contentTypeId,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "ifSupportComment", required = true) String ifSupportComment,
            @RequestParam(value = "sort", required = true) String sort,
            @RequestParam(value = "content", required = true) String content
    ) {
        logger.debug("开始更新/新增头条");
        logger.debug("传入参数 contentTypeId={}", contentTypeId);
        logger.debug("传入参数 title={}", title);
        logger.debug("传入参数 content={}", content);
        
        Map<String, Object> resultMap = new HashMap<String, Object>();
		Gson gson = new Gson();
		Subject currentUser = SecurityUtils.getSubject();
		String mobile = (String)currentUser.getPrincipal();
		
        //
        try {
            String nickname = headlineContentService.queryShiroManger(mobile);
                HeadlineContentEntity entity = new HeadlineContentEntity();
                entity.setMobile(mobile);
                entity.setNickname(nickname);
                entity.setContentTypeId(contentTypeId);
                entity.setTitle(title);
                entity.setOrigTitle(title);
                entity.setContent(content);
                entity.setOrigContent(content);
                entity.setTopNumber(Integer.parseInt(sort));
                entity.setStatus(0);
                entity.setIfSupportComment(ifSupportComment); //允许评论
               String headlineId =headlineContentService.addHeadlineContent(entity);
               String temp = GetConfigFile.getInstance().getProperties("ImageUploadPath");
               if(StringUtil.isNotEmpty(picStringList)){
	               String picPath =picStringList.substring(0,picStringList.lastIndexOf(","));
	               String[] strArray = null;   
	               if(picPath.contains(",")){
	                   strArray = picPath.split(","); //拆分字符为"," ,然后把结果交给数组strArray 
	               
		               for(String pic:strArray){
		            	     File dy = new File(temp+File.separator+pic);
		            	   	 byte[]  picBytes = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy),"");
			                 HeadlineContentAttachEntity attach = new HeadlineContentAttachEntity();
			                 attach.setHeadlineId(Long.valueOf(headlineId));
			                 attach.setPicture(picBytes);
			                 headlineContentService.uploadHeadlineContentAttach(attach);
		               }
	               }else{
	            	   File dy = new File(temp+File.separator+picPath);
	            	   byte[]  picBytes = PictureUtil.compressPicByByte(ReadFile.getBytesFromFile(dy),"");
		                 HeadlineContentAttachEntity attach = new HeadlineContentAttachEntity();
		                 attach.setHeadlineId(Long.valueOf(headlineId));
		                 attach.setPicture(picBytes);
		                 headlineContentService.uploadHeadlineContentAttach(attach);
	               }
               }
            resultMap.put("msg", "头条发布成功！");
			resultMap.put("CODE", "TRUE");
			outPut(response, gson.toJson(resultMap));
        } catch (Exception ex) {
        	
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
    
  
}
