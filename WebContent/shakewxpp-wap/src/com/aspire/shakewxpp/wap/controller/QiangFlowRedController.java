package com.aspire.shakewxpp.wap.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aspire.shakewxpp.wap.entity.DistributeFlowRedEnvelope;
import com.aspire.shakewxpp.wap.entity.GrabFlowSubRedEnvelope;
import com.aspire.shakewxpp.wap.entity.QiangResult;
import com.aspire.shakewxpp.wap.entity.User;
import com.aspire.shakewxpp.wap.service.DistributeFlowRedService;
import com.aspire.shakewxpp.wap.service.GrabFlowSubRedService;
import com.aspire.shakewxpp.wap.service.QiangFlowService;
import com.aspire.shakewxpp.wap.service.UserService;
import com.aspire.shakewxpp.wap.util.Constants;
import com.aspire.shakewxpp.wap.util.DESTools;
import com.aspire.shakewxpp.wap.util.HttpPostUtil;

/**
 * 手机号码登记管理器

 * @author
 *
 */
@Controller
@Scope("prototype")
public class QiangFlowRedController {

	protected static Logger logger = LoggerFactory.getLogger(QiangFlowRedController.class);

	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "qiangFlowService")
	private QiangFlowService qiangFlowService;
	
	@Resource(name = "distributeFlowRedService")
	private DistributeFlowRedService distributeFlowRedService;
	
	@Resource(name = "grabFlowSubRedService")
	private GrabFlowSubRedService grabFlowSubRedService;
	
	/**
	 * 抢流量红包页面
	 * @return
	 */
	@RequestMapping(value = "/qiangFlow.tv", method = RequestMethod.GET)
	public String drawPage(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String page = "/smallredbag/lateSubRed";
		//若是订阅号,url会携带openid过来
		String openid = StringUtils.isEmpty(request.getParameter("openid"))?(String)request.getSession().getAttribute("openid"):request.getParameter("openid");

		String weixinAppNo = request.getParameter("weixinAppNo");
		if(StringUtils.isEmpty(openid)){	
			if(StringUtils.isEmpty(weixinAppNo)){
				logger.warn("到我们的错误页面，获取当前用户手机号失败");
                model.addAttribute("ex", new Exception("未携带weixinAppNo参数"));
                return "common/exception";
			}
			Map<String,String> map = userService.queryAppMessageByWeixinAppNo(weixinAppNo);
			HttpPostUtil.getOpenId(request, response, null,map);
			return null;
		}

		//判断是否是黑名单用户
		
		String userGroupName = userService.queryUserGroup(openid);
		if("黑名单".equals(userGroupName)){
			logger.debug("抢红包用户为黑名单用户,屏蔽该用户，openid:"+openid);
			model.addAttribute("msg", "由于您的恶意操作,暂时对您屏蔽该功能！");
			model.addAttribute("CODE", "FALSE");
			model.addAttribute("ex", new Exception("由于您的恶意操作,暂时对您屏蔽该功能!"));
			page="/common/exception";
			return page;
		}
		
		//大红包id
		String freId = request.getParameter("freId");
		if(StringUtils.isEmpty(freId)){
			logger.error("抽流量红包出现异常,freId为空");
			model.addAttribute("msg", "系统繁忙，请稍候再试！");
			model.addAttribute("CODE", "FALSE");
			model.addAttribute("ex", new Exception("分享的链接异常,缺少参数"));
			page="/common/exception";
			return page;
		}
		logger.debug("drawPage begin==freId:"+freId);
		
		DESTools desTools = DESTools.getInstance();
	
		freId = desTools.decrypt(freId);
		model.addAttribute("desFreId",request.getParameter("freId"));
		
		logger.debug("drawPage====freId:"+freId);
		
		
		model.addAttribute("freId",freId);

		model.addAttribute("weixinAppNo",weixinAppNo);
		
		//取用户信息
		User user_temp = new User();
		user_temp.setOpenID(openid);
		user_temp.setWeixinAppNo(weixinAppNo);
		
		User user = null;
		try{
			user = userService.getUser(user_temp);
		}catch(Exception e){
			logger.error("抢红包时,查询用户信息失败",e);
		}
		
		User paraUser = null;
		if(user==null){
			user_temp.setSubscribe(Constants.USER_SUBSCRIPT_TEMP);
			userService.insertOpenId(user_temp);
			paraUser = user_temp;
			paraUser.setSubscribe(Constants.USER_SUBSCRIPT_TEMP);
		}else{
			paraUser = user;
		}
		
		model.addAttribute("user",paraUser);
		QiangResult qiangResult = null; 
		try{
			qiangResult = qiangFlowService.qiangFlowRedEnvelope(paraUser, Integer.parseInt(freId));
			
			//抢小红包成功,且用户表中没有该用户,则插入该用户
			if(user==null){
				userService.insertOpenId(paraUser);
			}
			model.addAttribute("qiangResult",qiangResult);
			model.addAttribute("CODE", "TRUE");
		}catch(Exception e){
			logger.error("抽流量红包出现异常,用户："+paraUser.getOpenID(),e);
			model.addAttribute("msg", "系统繁忙，请稍候再试！");
			model.addAttribute("CODE", "FALSE");
			model.addAttribute("ex", e);
			page="/common/exception";
			return page;
		}
		
		
		
		
		if(qiangResult !=null ){
			DistributeFlowRedEnvelope tmpVO = new DistributeFlowRedEnvelope();
			tmpVO.setFreId(Integer.parseInt(freId));
			int subFreNum = distributeFlowRedService.querPerBigRedCountByFreid(tmpVO);
			model.addAttribute("subFreNum",subFreNum);
			
			if(Constants.RED_BAG_SUCCESS == qiangResult.getFailType()){
				if(StringUtils.isNotEmpty(paraUser.getBindMsisdn())){
					page = "/smallredbag/successSubRed";
				}else{
					page = "/smallredbag/notBoundAttention";
				}
			}
			if(Constants.RED_BAG_GAIN == qiangResult.getFailType()){		
				page = "/smallredbag/alreadySubRed";
				if(StringUtils.isEmpty(paraUser.getBindMsisdn())){
					page = "/smallredbag/notBoundAttention";
				}
			}
			if(Constants.RED_BAG_NONE == qiangResult.getFailType()){		
				page = "/smallredbag/lateSubRed";
			}
			if(Constants.RED_BAG_PAST_DUE == qiangResult.getFailType()){		
				page = "/smallredbag/pastDueSubRed";
			}
		}
		model.addAttribute("failType",qiangResult.getFailType()+"");
		model.addAttribute("weixinAppNo",weixinAppNo);
		return page;
	}
	
	
	/**
	 * 抢流量红包页面
	 * @return
	 */
	@RequestMapping(value = "/alreadySubRed.tv", method = RequestMethod.GET)
	public String alreadySubRed(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String page = "/smallredbag/alreadySubRed";
		
		//若是订阅号,url会携带openid过来
		String openid = StringUtils.isEmpty(request.getParameter("openid"))?(String)request.getSession().getAttribute("openid"):request.getParameter("openid");

		String weixinAppNo = request.getParameter("weixinAppNo");
		if(StringUtils.isEmpty(openid)){	
			if(StringUtils.isEmpty(weixinAppNo)){
				logger.warn("到我们的错误页面，获取当前用户手机号失败");
                model.addAttribute("ex", new Exception("未携带weixinAppNo参数"));
                return "common/exception";
			}
			Map<String,String> map = userService.queryAppMessageByWeixinAppNo(weixinAppNo);
			HttpPostUtil.getOpenId(request, response, null,map);
			return null;
		}else{
			request.getSession().setAttribute("openid",openid);
		}
		
		
		User user_temp = new User();
		user_temp.setOpenID(openid);
		User user = userService.getUser(user_temp);
		if(user!=null){
			user_temp = user;
		}
		if(StringUtils.isEmpty(user_temp.getBindMsisdn())){
			page = "/smallredbag/notBoundAttention";
		}
		
		model.addAttribute("user",user_temp);
		model.addAttribute("failType",Constants.RED_BAG_GAIN+"");
		
		//大红包id
		String freId = request.getParameter("freId");
		
		//先判断该红包是否已被当前用户抢到过,防止用户修改连接参数引起的错误
		GrabFlowSubRedEnvelope grabFlowSubRedEnvelope = new GrabFlowSubRedEnvelope();
		try{
			grabFlowSubRedEnvelope.setOpenId(openid);
			grabFlowSubRedEnvelope.setFreId(Integer.parseInt(freId));
			
			int result = grabFlowSubRedService.checkeHadQiangFlowRedPackage(grabFlowSubRedEnvelope);
			if(result==0){
				model.addAttribute("ex",new Exception("数据异常,您没有抢到过该红包！"));
				page = "/common/exception";
				return page;
			}
		}catch(Exception e){
			model.addAttribute("ex",new Exception("数据异常,您没有抢到过该红包！"));
			page = "/common/exception";
			return page;
		}
		
		
		model.addAttribute("freId",freId);
		
		DESTools desTools = DESTools.getInstance();
		String desFreId = desTools.desCrypto(freId);
		model.addAttribute("desFreId",desFreId);
        
		model.addAttribute("weixinAppNo",weixinAppNo);
		
		return page;
	}
}
