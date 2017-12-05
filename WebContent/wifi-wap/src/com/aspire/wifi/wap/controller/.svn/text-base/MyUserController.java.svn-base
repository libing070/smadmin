package com.aspire.wifi.wap.controller;






import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aspire.wifi.common.sms.constants.SmsConstants;
import com.aspire.wifi.common.sms.entity.SmsSendMsg;
import com.aspire.wifi.wap.entity.HeadlineContentAttachEntity;
import com.aspire.wifi.wap.entity.HeadlineContentEntity;
import com.aspire.wifi.wap.entity.HeadlineReplyEntity;
import com.aspire.wifi.wap.entity.MobileReplyReviewEntity;
import com.aspire.wifi.wap.entity.Parameter;
import com.aspire.wifi.wap.entity.PinActivityEntity;
import com.aspire.wifi.wap.entity.UserInfoEntity;
import com.aspire.wifi.wap.entity.ZhuanFaEntity;
import com.aspire.wifi.wap.mapper.UserInfoMapper;
import com.aspire.wifi.wap.mapper.ZhuanFaEntityMapper;
import com.aspire.wifi.wap.service.intf.HeadlineContentService;
import com.aspire.wifi.wap.service.intf.HeadlineReplyService;
import com.aspire.wifi.wap.service.intf.MobileReplyReviewService;
import com.aspire.wifi.wap.service.intf.PinActivityService;
import com.aspire.wifi.wap.service.intf.UserInfoService;
import com.aspire.wifi.wap.util.Constants;
import com.aspire.wifi.wap.util.GetConfigFile;
import com.aspire.wifi.wap.util.PictureUtil;
import com.aspire.wifi.wap.util.ReadFile;
import com.aspire.wifi.wap.util.SendMsg;
import com.aspire.wifi.wap.util.StringUtil;



@Controller
public class MyUserController {
	protected static Logger logger =  LoggerFactory.getLogger(MyUserController.class);
	public static String PATTERN_DATE_4 = "yyyy-MM-dd HH:mm:ss";
	
	public static String DEFAULT_MAN_PIC = "nan.png";
	public static String DEFAULT_WOMAN_PIC = "nv.png";
	
	@Resource(name = "pinActivityService")
	private PinActivityService pinActivityService;
	@Resource(name = "userInfoService")
	private UserInfoService userInfoService;
	@Resource(name = "userInfoMapper")
	private UserInfoMapper userInfoMapper;
	@Resource(name = "headlineContentService")
	private HeadlineContentService headlineContentService;
	@Resource(name = "headlineReplyService")
	private HeadlineReplyService headlineReplyService;
	@Resource(name = "mobileReplyReviewService")
	private MobileReplyReviewService mobileReplyReviewService;
	@Resource(name = "zhuanFaEntityMapper")
	private ZhuanFaEntityMapper zhuanFaEntityMapper;
/**
 * 我的足迹
 * **/
	@RequestMapping(value = "/my/myFoot", method = RequestMethod.GET)
	public ModelAndView myFoot() throws Exception {
		ModelAndView view = new ModelAndView("/my/my_footprint");
		// 取得配置的媒体文件存放路径,供页面使用


		return view;
	}	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView indexwwPage(HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("/index");
		// 取得配置的媒体文件存放路径,供页面使用







		view.addObject("videoPath", GetConfigFile.getInstance().getProperties("videoAccessPath"));
		view.addObject("imagePath", GetConfigFile.getInstance().getProperties("imageAccessPath"));
		request.setAttribute("index", request.getParameter("index"));

		try {
			PinActivityEntity pinActivityEntity = pinActivityService.activityObject();
			request.setAttribute("activityId",pinActivityEntity.getActivityId());
		} catch (Exception e) {
			logger.error("查询活动ID失败!",e);
		}
		return view;
	}
	/***
	 * 1，我的首页


	 * */
	//登陆我的页面（首页）
	@RequestMapping(value = "/my/myIndex", method = RequestMethod.GET)
	public ModelAndView getVideoIndex( HttpServletResponse response,HttpServletRequest request) throws Exception {
		// 取得配置的媒体文件存放路径,供页面使用


		ModelAndView view = new ModelAndView("/my/my");
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isAuthenticated()){			
			String msisdn = (String)currentUser.getPrincipal();
		
	        try {
	        	UserInfoEntity userinfo = userInfoService.queryUserInfo(msisdn);//获取我的信息，我的昵称，头像，手机号码，性别
	        	request.setAttribute("IsHeadShow", Constants.IS_HEADSHOW_1);
	        	if(userinfo.getHead_show()==null||userinfo.getHead_show().length==0){
	        		request.setAttribute("IsHeadShow", Constants.IS_HEADSHOW_0);
	        	}
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        /****转发判断开始****/
//			String iszhuanFa = request.getSession().getAttribute("iszhuanFa")==null?"":request.getSession().getAttribute("iszhuanFa").toString();
//
//	        String onlyzhuanfaId = request.getSession().getAttribute("onlyzhuanfaId")==null?"":request.getSession().getAttribute("onlyzhuanfaId").toString();
//			String onlyflag = request.getSession().getAttribute("onlyflag")==null?"":request.getSession().getAttribute("onlyflag").toString();
//			if(!onlyzhuanfaId.equals("")&&onlyflag.equals("")){//帮助方
//				Map<String,Object> paramMap = new HashMap<String,Object>();
//
//				ModelAndView view2 = new ModelAndView("/zhuanfa/zhuanfa");
//				int onlyIds = userInfoMapper.queryUserInfo(msisdn).getOnlyId();
//				String nickname = userInfoMapper.queryUserInfo(msisdn).getNickname();
//				view2.addObject("nickname",nickname);
//				request.getSession().setAttribute("onlyzhuanfaId2",onlyzhuanfaId);
////				request.getSession().setAttribute("onlyzhuanfaId", "");
////				request.getSession().setAttribute("onlyflag", "");
//				view2.addObject("onlyIds",onlyIds);
//				paramMap.put("zhuanFaId",onlyzhuanfaId);
//		//		List<ZhuanFaEntity> ZhuanFaEntitylist2 = zhuanFaEntityMapper.queryZhuanFaByLimit(paramMap);
//				paramMap.put("zhuanFaId","");
//				paramMap.put("mobile",msisdn);
//				paramMap.put("isCreater", "0");//0表示转发者	,	
//				List<ZhuanFaEntity> ZhuanFaEntitylist = zhuanFaEntityMapper.queryZhuanFaByLimit(paramMap);
//			//	if(ZhuanFaEntitylist2.size()<5){
//					if(ZhuanFaEntitylist.size()==0){//
//						String ownzhuanfaId =userInfoMapper.queryUserInfo(currentUser.getPrincipal().toString()).getOnlyId()+""; 
//						if(onlyzhuanfaId.equals(ownzhuanfaId)){
//							view2.addObject("msg", "自己不能召唤自己");
//						}else{
//							paramMap.put("zhuanFaId",Long.valueOf(onlyzhuanfaId));
//
//							zhuanFaEntityMapper.insertZhuanFa(paramMap);
//							view2.addObject("msg","帮助成功，么么哒");
//							//给帮助人发送短信通知
////							SmsSendMsg ssm =SendMsg.getMsg(msisdn);
////							String code=StringUtil.genRandomString(6);
////							String smsContent = sysConfigService.getSysConfigAsString(SmsConstants.WIFI_SMS_NOTIVE_CISHANG, new String[]{code});
////
////							ssm.setContent(smsContent);
////							smsSender.send(ssm);//发送短信
//						}
//					}else{
//						String tempZhuanFaId = ZhuanFaEntitylist.get(0).getZhuanFaId().toString();
//						if(tempZhuanFaId.equals(onlyzhuanfaId)){
//							
//							view2.addObject("msg", "您已经加入了");
//						}else{
//							view2.addObject("msg", "每人只能加入一个好友的求助");
//						}
//					}
//			//	}
//			//	request.getSession().setAttribute("onlyzhuanfaId2",onlyzhuanfaId);
//			//	request.getSession().setAttribute("onlyzhuanfaId", "");
//				return view2;
//			}
//			
//			/**发起方开始***/
//			if(!iszhuanFa.equals("")){
//				Map<String,Object> paramMap = new HashMap<String,Object>();
//
//				ModelAndView view2 = new ModelAndView("/zhuanfa/zhuanfa");
//				String mobile = currentUser.getPrincipal().toString();
//				int onlyIds = userInfoMapper.queryUserInfo(mobile).getOnlyId();
//				String nickname = userInfoMapper.queryUserInfo(msisdn).getNickname();
//				view2.addObject("nickname",nickname);
//				paramMap.put("mobile", mobile);
//				paramMap.put("isCreater","1");//1表示创建者			
//				List<ZhuanFaEntity> ZhuanFaEntitylist = zhuanFaEntityMapper.queryZhuanFaByLimit(paramMap);
//				if(ZhuanFaEntitylist.size()==0){
//					paramMap.put("zhuanFaId",onlyIds);
//					zhuanFaEntityMapper.insertZhuanFa(paramMap);
//				}else{
//					paramMap.put("isCreater","");//1表示创建者			
//					paramMap.put("mobile","");//1表示创建者			
//					paramMap.put("zhuanFaId",ZhuanFaEntitylist.get(0).getZhuanFaId());//1表示创建者			
//					if(zhuanFaEntityMapper.queryZhuanFaByLimit(paramMap).size()>4){
//						String isHuodongYe =request.getParameter("isHuodongYe");
//						if(isHuodongYe==null){
//							
//							view.addObject("isChuangJian",1);
//						}
//					}
//				}

//	//			request.getSession().setAttribute("iszhuanFa", "");
//				view2.addObject("onlyIds",onlyIds);
//				return view2;
//			}
			/**发起方结束***/
			/****转发判断结束****/
		}
		return view;
	}
	/**
	 * ajax请求信息
	 * **/
	@RequestMapping(value = "/my/queryUserInfo", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> queryUserInfo( HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
		try {
			UserInfoEntity userinfo = userInfoService.queryUserInfo(msisdn);//获取我的信息，我的昵称，头像，手机号码，性别
			if(userinfo.getProvince()!=null){
				String provinceId= userInfoService.searchProvinceId(userinfo.getProvince());
				userinfo.setProvinceId(provinceId);
			}	
			int replayCount = headlineReplyService.queryMyHeadCount(msisdn);//获取我的评论未读数量
			//获取我的评论数总数量(需要我的头条的评论总数加上我的评论的回复数)
			//获取我的赞的总数量


			int pariseCount = headlineReplyService.queryMyZanCount(msisdn);
			resultMap.put("userinfo", userinfo);
			resultMap.put("replayCount", replayCount);
			resultMap.put("pariseCount", pariseCount);
			resultMap.put("CODE", "TRUE");
			resultMap.put("msg", "查询用户信息成功");
	
		} catch (Exception e) {
			logger.error("查询用户："+msisdn+"新增视频播放信息异常", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		}  finally {
           
        }
		return resultMap;
	}
	/**
	 * 请求图片
	 * **/
	@RequestMapping(value = "/my/findAttach")
	public void listHeadlineContentAttach(HttpServletResponse response, HttpServletRequest request) {
		String msisdn = request.getParameter("mobile");
		if (StringUtil.isEmpty(msisdn)) {
			Subject currentUser = SecurityUtils.getSubject();
			msisdn = (String) currentUser.getPrincipal();
		}
		
		OutputStream out = null;
		try {
			String appPath = request.getSession().getServletContext().getRealPath("/");
			UserInfoEntity userinfo = userInfoService.queryUserInfo(msisdn);// 获取我的信息，我的昵称，头像，手机号码，性别
			out = response.getOutputStream();
			byte[] picBytes = null;
			File file = null;
			String defaultHeadImage = "";
			if (userinfo != null) {
				picBytes = userinfo.getHead_show();
				if (picBytes == null || picBytes.length == 0) {
					if (Constants.SEX_MAN == userinfo.getSex()) {
						defaultHeadImage = appPath + File.separator + "resources" + File.separator + "images" + File.separator + DEFAULT_MAN_PIC;
					} else {
						defaultHeadImage = appPath + File.separator + "resources" + File.separator + "images" + File.separator + DEFAULT_WOMAN_PIC;
					}
					file = new File(defaultHeadImage);
					picBytes = ReadFile.getBytesFromFile(file);
				}
			} else {
				defaultHeadImage = appPath + File.separator + "resources" + File.separator + "images" + File.separator + DEFAULT_MAN_PIC;
				file = new File(defaultHeadImage);
				picBytes = ReadFile.getBytesFromFile(file);
			}
			IOUtils.write(picBytes, out);
		} catch (Exception e) {
			logger.error("请求我的头像异常", e);
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
 * 2，我的基本信息


 * 
 * **/	
	//跳转到我的基本信息页面


	@RequestMapping(value = "/my/myDetailIndex", method = RequestMethod.GET)
	public ModelAndView myDetailIndex(HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("/my/my_detail");
		// 取得配置的媒体文件存放路径,供页面使用


		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
		 try {
	        	UserInfoEntity userinfo = userInfoService.queryUserInfo(msisdn);//获取我的信息，我的昵称，头像，手机号码，性别
	        	request.setAttribute("IsHeadShow", Constants.IS_HEADSHOW_1);
	        	byte[] b = userinfo==null?null:userinfo.getHead_show();
	        	if(b==null||b.length==0){
	        		request.setAttribute("IsHeadShow", Constants.IS_HEADSHOW_0);
	        	}
	        } catch (Exception e) {
	        	logger.error("请求我的基本信息页面异常", e);
	        }
			
		return view;
	}

	
	
	/***
	 * 修改用户信息
	 * */
	@RequestMapping(value = "/my/editUserInfo", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> editUserInfo(HttpServletRequest request, @RequestParam("cityName") String cityName,@RequestParam("mobile") String mobile,
			@RequestParam("nickName") String nickName,@RequestParam("province") String province,@RequestParam("sex") String sex	) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		UserInfoEntity userInfoEntity  = new UserInfoEntity();
		try {
			List<UserInfoEntity> list = userInfoService.queryUserInfoByNickName(nickName);
			if(mobile.contains("</a>")){
				mobile = mobile.substring(mobile.length()-15,mobile.length()-4);
			}
				UserInfoEntity uie =list.size()==0?null: list.get(0);
				if(uie != null && !uie.getMobile().equals(mobile)){//昵称重名
					resultMap.put("msg","昵称已被其他人使用！");
					resultMap.put("CODE", "FALSE");
				}else{
					userInfoEntity.setCity(cityName);
					userInfoEntity.setMobile(mobile);
					userInfoEntity.setNickname(nickName);
					userInfoEntity.setProvince(province);
					userInfoEntity.setSex(sex.charAt(0));
					userInfoService.updateUserInfo(userInfoEntity);
					
					//更新session信息
					@SuppressWarnings("unchecked")
					Map<String, Object> map = (Map<String, Object>) request.getSession().getAttribute(LoginController.SESSION_KEY_USER_INFO);
					if (null != map) {
						map.put("username", nickName);
						request.getSession().setAttribute(LoginController.SESSION_KEY_USER_INFO, map);
					}
				
					resultMap.put("CODE", "TRUE");
					resultMap.put("msg", "修改用户信息成功");
				}
		} catch (Exception e) {
			logger.error("修改用户："+userInfoEntity.getMobile()+"修改用户信息异常", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		} 
		return resultMap;
	}
/**
 * 3，我的活动一块（列表）


 * 
 * **/
	//登陆我的活动列表页面
	@RequestMapping(value = "/my/myActivity", method = RequestMethod.GET)
	public ModelAndView myActivity() throws Exception {
		
		ModelAndView view = new ModelAndView("/my/my_huodong");
		// 取得配置的媒体文件存放路径,供页面使用


		return view;
	}
	
/**
 * 4,我的头条
 * 
 * **/
	
	//登陆我的活动列表页面
	@RequestMapping(value = "/my/myHeadline", method = RequestMethod.GET)
	public ModelAndView myHeadline() throws Exception {
		ModelAndView view = new ModelAndView("/my/my_headline");
		// 取得配置的媒体文件存放路径,供页面使用


		return view;
	}
	
	@RequestMapping(value = "/my/myHeadlineInfo", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, ? extends Object> myHeadlineInfo() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
		try {
			List<HeadlineContentEntity> headlineListInfo = headlineContentService.queryMyHeadInfo(msisdn);
		
			resultMap.put("headlineListInfo", headlineListInfo);

			resultMap.put("CODE", "TRUE");
			resultMap.put("msg", "查询用户信息成功");
		} catch (Exception e) {
			logger.error("查询用户："+msisdn+"新增视频播放信息异常", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		} 
		return resultMap;
	}
	/**
	 * 我的赞


	 * **/
	
	@RequestMapping(value = "/my/myZan", method = RequestMethod.GET)
	public ModelAndView myZan() throws Exception {
		ModelAndView view = new ModelAndView("/my/my_zan");
		// 取得配置的媒体文件存放路径,供页面使用


		return view;
	}

	/**
	 * 查询我的赞的信息
	 * 
	 * **/
	@RequestMapping(value = "/my/myZanInfo", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> myZanInfo(@RequestParam("beginTimes") String beginTimes,@RequestParam("pageCount") String pageCount) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
		try {
			List<HeadlineReplyEntity> headReplyListInfo = headlineReplyService.queryMyZanInfo(new Parameter(Integer.parseInt(beginTimes),Integer.parseInt(pageCount),msisdn));
			for(int i=0;i<headReplyListInfo.size();i++){
				headReplyListInfo.get(i).setReplyStringDate(StringUtil.formatLongDate(headReplyListInfo.get(i).getReplyDate()));
				
				headReplyListInfo.get(i).setStatus(headlineReplyService.findMyAttach(headReplyListInfo.get(i).getHeadlineId()+"").size());
			}
			resultMap.put("headReplyListInfo", headReplyListInfo);
			resultMap.put("msisdn", msisdn);
			resultMap.put("CODE", "TRUE");
			resultMap.put("msg", "查询用户信息成功");
		} catch (Exception e) {
			logger.error("查询用户："+msisdn+"新增视频播放信息异常", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		} 
		return resultMap;
	}
	/**
	 * 请求我的头条图片
	 * **/
	@RequestMapping(value = "/my/myZanFindAttach")
    public void listHeadlineContentAttach(
            HttpServletResponse response,
            @RequestParam(value = "headlineId") String headlineId,
            @RequestParam(value = "i") String i
    ) {
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            List<HeadlineContentAttachEntity> list = headlineReplyService.findMyAttach(headlineId);
            	byte[] picBytes = list.get(Integer.parseInt(i)).getPicture();          	
            	IOUtils.write(picBytes, out);
        } catch (Exception e) {
        	logger.error("请求我的头条图片异常", e);
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
	 * 
	 * 新增回复赞我的头条(或回复别人对我的评论)
	 * **/
	@RequestMapping(value = "/my/addMyZanInfo", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> addMyZanInfo(@RequestParam("id") String id,@RequestParam("headlineId") String headlineId,@RequestParam("nickname") String nickname,@RequestParam("parentMobile") String parentMobile,@RequestParam("content") String content) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
		try {
			HeadlineReplyEntity headlineReplyEntity =new HeadlineReplyEntity();
			headlineReplyEntity.setParentReplyId(id);
			headlineReplyEntity.setContent(content);
			headlineReplyEntity.setHeadlineId(Long.parseLong(headlineId));
			headlineReplyEntity.setMobile(msisdn);
			headlineReplyEntity.setNickname(nickname);
			headlineReplyEntity.setParentMobile(parentMobile);
			headlineReplyEntity.setReplyType(Constants.REPLY_TYPE_1);//评论状态


			
			headlineReplyEntity.setStatus(Constants.audit_status_0);//未审核


			headlineReplyService.addMyZanInfo(headlineReplyEntity);			
			
			resultMap.put("CODE", "TRUE");
			resultMap.put("msg", "回复赞或评论信息成功");
		} catch (Exception e) {
			logger.error("用户："+msisdn+"回复赞或评论信息异常", e);
			resultMap.put("msg","SDH");
			resultMap.put("CODE", "FALSE");
		} 
		return resultMap;
	}
	/**
	 * 
	 * 删除我的赞的信息（或者删除我的评论信息）
	 * **/
	@RequestMapping(value = "/my/delZanInfo", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> delZanInfo(@RequestParam("id") String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
	String msisdn = (String)currentUser.getPrincipal();
		try {
			headlineReplyService.delZan(id);
			resultMap.put("CODE", "TRUE");
			resultMap.put("msg", "删除我的赞成功");
		} catch (Exception e) {
			logger.error("用户："+msisdn+"删除我的赞异常", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		} 
		return resultMap;
	}
	/**
	 * 点击赞或评论标识未读和已读


	 * 
	 * **/
	@RequestMapping(value = "/my/myOldRead", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> myOldRead(@RequestParam("flag") String flag) {//flag为1表示第一次已读评论，2表示赞


		Map<String, Object> resultMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
		try {
			int count = mobileReplyReviewService.queryMymrrCount(msisdn);
			MobileReplyReviewEntity mrre = new MobileReplyReviewEntity();
			mrre.setMobile(msisdn);
			if(flag.equals(Constants.REPLY_TYPE_1)){
				mrre.setLastClickReplyTime(StringUtil.formatDateYYYYMMDDHHMMSS(StringUtil.getNow()));
			}else{
				mrre.setLastClickPraiseTime(StringUtil.formatDateYYYYMMDDHHMMSS(StringUtil.getNow()));
			}
			if(count>0){
				mobileReplyReviewService.updateMymrrReplyInfo(mrre);
			
			}else{
				mrre.setLastClickReplyTime(StringUtil.formatDateYYYYMMDDHHMMSS(StringUtil.getNow()));
				mrre.setLastClickPraiseTime(StringUtil.formatDateYYYYMMDDHHMMSS(StringUtil.getNow()));
				mobileReplyReviewService.addMymrrZanInfo(mrre);
			}			
			resultMap.put("CODE", "TRUE");
			resultMap.put("msg", "查询用户信息成功");
		} catch (Exception e) {
			logger.error("查询用户："+msisdn+"新增视频播放信息异常", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		} 
		return resultMap;
	}
	
	/**
	 * 我的评论
	 * 
	 * **/
	
	@RequestMapping(value = "/my/myReplyIndex", method = RequestMethod.GET)
	public ModelAndView myReply(@RequestParam("isMySend") String isMySend,HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("/my/my_comments");
		// 取得配置的媒体文件存放路径,供页面使用

		request.setAttribute("isMySend", isMySend);
		return view;
	}
	/**
	 * 查询我的评论信息
	 * **/
	@RequestMapping(value = "/my/myReplyInfo", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> myReplyInfo(@RequestParam("beginTimes") String beginTimes,@RequestParam("pageCount") String pageCount,@RequestParam("flag") String flag) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
		try {
			Parameter p=null;
			if(flag.equals("0")){//flag为0表示我发出的，为1表示所有评论


				
				 p = new Parameter(Integer.parseInt(beginTimes),Integer.parseInt(pageCount),msisdn);
			}else{
				 p = new Parameter(Integer.parseInt(beginTimes),Integer.parseInt(pageCount),msisdn,msisdn);
			}
			List<HeadlineReplyEntity> replyList = headlineReplyService.queryMyReplyInfo(p);
			for(int i=0;i<replyList.size();i++){
				String parentNickname="";
				if(replyList.get(i).getParentReplyId().equals(Constants.IS_HIGHEST_0)){
				
					
					 parentNickname = headlineReplyService.queryParentNameById(replyList.get(i).getHeadlineId()+"");
				}else{
				
					parentNickname = headlineReplyService.selectParentNameById(replyList.get(i).getParentReplyId());
				}
				replyList.get(i).setHeadtitle(parentNickname);
				
				replyList.get(i).setReplyStringDate(StringUtil.formatLongDate(replyList.get(i).getReplyDate()));

			}
			resultMap.put("replyList", replyList);
			resultMap.put("CODE", "TRUE");
			resultMap.put("msg", "查询用户信息成功");
		} catch (Exception e) {
			logger.error("查询用户："+msisdn+"我的评论信息信息异常", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		} 
		return resultMap;
	}
	
	/**
	 * 上传图片接入口



	 * @return
	 */
	@RequestMapping(value = "my/uploadImage", method = RequestMethod.POST)
	public  @ResponseBody
	Map<String, ? extends Object>   uploadImage(@RequestParam("fileBox") CommonsMultipartFile commonsMultipartFile,HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = (String)currentUser.getPrincipal();
		try {

			UserInfoEntity a = new UserInfoEntity();
			byte[] n = PictureUtil.compressPicByByte(IOUtils.toByteArray(commonsMultipartFile.getInputStream()),"");
			a.setHead_show(n);
			a.setMobile(msisdn);
			userInfoService.updateUserInfo(a);
			resultMap.put("msg", "上传图片成功!");
			resultMap.put("CODE","TRUE");
			return resultMap;
		} catch (Exception e) {
			resultMap.put("CODE", "FALSE");
			resultMap.put("msg", "上传图片失败!");
			logger.error("上传头像图片失败", e);
			return resultMap;
		}
		
	}



	/**
     * 响应方法
     * @param response
     * @param param
     */
    public void outPut(HttpServletResponse response, String param) {
        response.setContentType("textml; charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            out.print(param);
            out.flush();
            out.close();
        }catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    public byte[] inputStream2Byte_0(InputStream is) throws Exception{
		ByteArrayOutputStream arrayStream = new ByteArrayOutputStream();  
		byte[] buff = new byte[100];  
		int rc = 0;  
		while ((rc = is.read(buff, 0, 100)) > 0) {  
			arrayStream.write(buff, 0, rc);  
		}  
		byte[] byt = arrayStream.toByteArray();  
		return byt;  
	}

}
