package com.aspire.wifi.wap.controller;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aspire.wifi.wap.entity.HeadlineReplyEntity;
import com.aspire.wifi.wap.entity.PinActivityEntity;
import com.aspire.wifi.wap.entity.ShiYongEntity;
import com.aspire.wifi.wap.entity.UserInfoEntity;
import com.aspire.wifi.wap.mapper.HeadlineReplyMapper;
import com.aspire.wifi.wap.service.intf.ShiyongService;
import com.aspire.wifi.wap.service.intf.UserInfoService;
import com.aspire.wifi.wap.util.Constants;
import com.aspire.wifi.wap.util.DateUtil;
import com.aspire.wifi.wap.util.GetConfigFile;
import com.aspire.wifi.wap.util.SensitiveWordUtil;

@Controller
@Scope("prototype")
public class ShiyongController {
	protected static Logger logger = LoggerFactory.getLogger(ShiyongController.class);
	protected static Logger share_log =  LoggerFactory.getLogger("share_log");
	public static String PATTERN_DATE_4 = "yyyy-MM-dd HH:mm:ss";

	@Resource(name = "shiyongService")
	private ShiyongService shiyongService;
	@Resource(name = "headlineReplyMapper")
	private HeadlineReplyMapper headlineReplyMapper;
    @Resource(name = "userInfoService")
    private UserInfoService userInfoService;
    @Resource(name = "sensitiveWordUtil")
    private SensitiveWordUtil sensitiveWordUtil;
	/**
	 * 使用页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/shiyongContr", method = RequestMethod.GET)
	public ModelAndView shiyongContr(HttpServletRequest request) throws Exception {
//		Subject currentUser = SecurityUtils.getSubject();
//		String username = currentUser.getPrincipal().toString();
//		share_log.debug("|"+username+"|shiyongContr||||||");
		
		ModelAndView view = new ModelAndView("/shiyong/shiyong");
		
		view.addObject("imagePath", GetConfigFile.getInstance().getProperties("imageAccessPath"));
		try {
			PinActivityEntity pinActivityEntity = shiyongService.queryShiyongInfo();
			view.addObject("pinActivityEntity",pinActivityEntity);
			
			Integer applyCount = shiyongService.queryShiyongApplyCount(pinActivityEntity.getActivityId());
			view.addObject("applyCount",applyCount);
			
		} catch (Exception e) {
			logger.error("查询活动ID失败!",e);
		}
		return view;
	}
	
	/**
	 * 试用活动详情页面跳转
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/shiyongDetail", method = RequestMethod.GET)
	public ModelAndView shiyongDetail(HttpServletRequest request) throws Exception {
	
		ModelAndView view = new ModelAndView("/shiyong/huodong_shiyong");
		
		return view;
	}
	
	/**
	 * 查询活动详情登陆用户对试用商品的评论
	 * **/

	@RequestMapping(value = "/getShiYongPinLun", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> getShiYongPinLun(HttpServletRequest request,@RequestParam(value = "begin") int begin,@RequestParam(value = "times") int times,@RequestParam(value = "flag") String flag) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String mobile="";
		if(flag.equals("")){
			Subject currentUser = SecurityUtils.getSubject();
			if(currentUser.isAuthenticated()){
				mobile = currentUser.getPrincipal().toString();
			}
		}
		try {
			logger.debug("查询用户:"+mobile);
			paramMap.put("begin", begin);
			paramMap.put("times", times);
			paramMap.put("mobile", mobile);
			paramMap.put("flag", flag);
			List<HeadlineReplyEntity> list = headlineReplyMapper.queryShiYongByMobile(paramMap);
			paramMap.put("flag", "");
			List<HeadlineReplyEntity> list2 = headlineReplyMapper.queryShiYongByMobile(paramMap);
			
			resultMap.put("account",list2.size());
			resultMap.put("list",list);
			resultMap.put("CODE", "TRUE");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询活动详情登陆用户对试用商品的评论异常", e);
			resultMap.put("msg","查询不成功！");
			resultMap.put("CODE", "FALSE");
		} 
		return resultMap;
	}
	/**
	 * 
	 * 新增对试用品的评价



	 * **/
	@RequestMapping(value = "/insertShiYongPinLun", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> insertShiYongPinLun(HttpServletRequest request,@RequestParam(value = "comments", required = true) String comments) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String mobile =  currentUser.getPrincipal().toString();
		HeadlineReplyEntity reply = new HeadlineReplyEntity();
		try {
			logger.debug("新增评价:"+mobile);
			UserInfoEntity userInfo = userInfoService.queryUserInfo(mobile);
			reply.setHeadlineId(71l);
			reply.setMobile(mobile);
			reply.setNickname(userInfo.getNickname());
			reply.setReplyType(Constants.REPLY_TYPE_3); //评论类型
			reply.setContent(sensitiveWordUtil.filterSensitiveWord(comments));
			reply.setOrigContent(comments);
			reply.setParentReplyId(0+""); //顶级评论
			reply.setParentMobile(mobile); //顶级评论填写自己的手机号
			reply.setStatus(2); //审核通过
			headlineReplyMapper.addMyZanInfo(reply);
			
			resultMap.put("CODE", "TRUE");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询活动详情登陆用户对试用商品的评论异常", e);
			resultMap.put("msg","查询不成功！");
			resultMap.put("CODE", "FALSE");
		} 
		return resultMap;
	}
	
	/**
	 * 查询是否已领取过
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryMobileFormShiYong", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> queryMobileFormShiYong(HttpServletRequest request,
			@RequestParam(value="mobile",required = true)String mobile,
			@RequestParam(value="activityId",required = true)String activityId
	) throws Exception {
		logger.debug("开始查询该用户是否已领取");
        logger.debug("传入参数 mobile={}", mobile);
        logger.debug("传入参数 activityId={}", activityId);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			if(!mobile.equals("")){
				paramMap.put("ownerMobile", mobile);
				paramMap.put("activityId", activityId);
			    shiyongService.queryShiYongByMobileAndActivityId(paramMap);
				if(Integer.parseInt(paramMap.get("tempnum").toString())>=1){
					returnMap.put("customCode",paramMap.get("customCode"));
					returnMap.put("CODE","FALSE");
				}else{
					returnMap.put("CODE","TRUE");
				}
			}else{
				returnMap.put("CODE","TRUE");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("CODE","FALSE");
			logger.error("对不起，你已经申请过!",e);
		}
		return returnMap;
	}
	/**
	 * 领取
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/shiyongGet", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> shiyongGet(HttpServletRequest request,
			@RequestParam(value="mobile",required = true)String mobile,
			@RequestParam(value="activityId",required = true)String activityId
	) throws Exception {
		logger.debug("开始领取");
        logger.debug("传入参数 mobile={}", mobile);
        logger.debug("传入参数 activityId={}", activityId);
		 Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
		
			ShiYongEntity shiyong=new ShiYongEntity();
			shiyong.setOwnerMobile(mobile);
			shiyong.setActivityId(Long.parseLong(activityId));
			 shiyongService.insertShiYongction(shiyong);
			 BigInteger big = new BigInteger(activityId.toString());
			 shiyongService.updatePinActivity(big);
			 returnMap.put("CODE","TRUE");
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("CODE","FALSE");
			logger.error("领取异常!",e);
		}
		return returnMap;
	}
	/**
	 * 统计
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/shiyongShareContr", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> shiyongShareContr(HttpServletRequest request
	) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String username="";
		if(currentUser.isAuthenticated()){
			 username = currentUser.getPrincipal().toString();
		}
		share_log.info(DateUtil.dateToDateString(new Date())+"|"+username+"|shareContr||||||");
		return returnMap;
	}
	/**
	 * 了解此产品统计


	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/shiyongKonwContr", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> shiyongKonwContr(HttpServletRequest request
	) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String username="";
		if(currentUser.isAuthenticated()){
		 username = currentUser.getPrincipal().toString();
		}
		share_log.info(DateUtil.dateToDateString(new Date())+"|"+username+"|knowProductContr||||||");
		return returnMap;
	}
}
