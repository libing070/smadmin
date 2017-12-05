package com.aspire.shakewxpp.wap.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aspire.shakewxpp.wap.entity.ConfigPojo;
import com.aspire.shakewxpp.wap.entity.User;
import com.aspire.shakewxpp.wap.entity.UserNotifyPojo;
import com.aspire.shakewxpp.wap.exception.WxppException;
import com.aspire.shakewxpp.wap.service.ConfigService;
import com.aspire.shakewxpp.wap.service.DrawService;
import com.aspire.shakewxpp.wap.service.ThirdInterfService;
import com.aspire.shakewxpp.wap.service.UserNotifyService;
import com.aspire.shakewxpp.wap.service.UserService;
import com.aspire.shakewxpp.wap.util.Constants;
import com.aspire.shakewxpp.wap.util.DESTools;
import com.aspire.shakewxpp.wap.util.DateUtil;
import com.aspire.shakewxpp.wap.util.GetConfigFile;
import com.aspire.shakewxpp.wap.util.HttpPostUtil;
import com.aspire.shakewxpp.wap.util.MessageHandleUtils;

/**
 * @Title: Weixin
 * @Package com.aspire.shakewxpp.wap.controller
 * @Description: TODO (用一句话描述该文件做什么)
 * @Author wuyuehui_a
 * @Date 2014/8/27
 * @Version V1.0
 * Update Logs:
 */
@Controller
public class ThirdInterfController {
    private static final Logger logger = LoggerFactory.getLogger(ThirdInterfController.class);
    
    protected static Logger report_logger =  LoggerFactory.getLogger("report_log");

    @Resource(name = "thirdInterfService")
    private ThirdInterfService thirdInterfService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "userNotifyService")
    private UserNotifyService userNotifyService;

    @Resource(name = "drawService")
    private DrawService drawService;
    
    @Resource(name = "configService")
	private ConfigService configService;
    		
    /**
     * 流量汇购买套餐入口
     * @param user
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/tobuypackage.tv")
    public ModelAndView toBuyPackage(User user,
            HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView view = null;
        String method = request.getMethod();
        if("get".equalsIgnoreCase(method)){
        	view = new ModelAndView("redirect:/drawPage.tv?weixinAppNo="+request.getParameter("weixinAppNo"));
        	return view;
        }
        
        try {
            String deviceID = GetConfigFile.getInstance().getProperties("deviceid");
            view = new ModelAndView("common/redirectto");
            //
            String msisdn = user.getBindMsisdn();
            String openId = "";
            if( StringUtils.isEmpty(msisdn.trim())){
            	openId = getCurrentUserOpenId(request, response, user.getWeixinAppNo());
            	 if( StringUtils.isEmpty(openId)){
            		 return null;
            	 }
            	 msisdn = getCurrentUserMobile(request, response, openId, null);
            }

            String dateTime = DateUtil.dateToDateFullString(DateUtil.getDate());
            if (StringUtils.isEmpty(msisdn)) {	
            	logger.warn("到我们的错误页面，获取当前用户手机号失败");
                view = new ModelAndView("common/exception");
                view.addObject("ex", new Exception("获取当前用户手机号失败"));
                return view;
            }
            Map<String, String> resultMap = thirdInterfService.getLoginToken(msisdn, dateTime);
            if (resultMap == null || StringUtils.isEmpty(resultMap.get("Ticket"))) {
                logger.warn("到我们的错误页面，登录流量汇出现异常");
                view = new ModelAndView("common/exception");
                view.addObject("ex", new Exception("登录流量汇出现异常"));
                return view;
            }
            //
            String buyPackageReturnUrl = GetConfigFile.getInstance().getProperties("buyPackageReturnUrl");
 
            buyPackageReturnUrl = buyPackageReturnUrl + "?WeixinAppNo=" + user.getWeixinAppNo();
            String transactionID = MessageHandleUtils.getTransactionID();
            //
            Map<String, String> param = new HashMap<String, String>();
            param.put("DeviceID", deviceID);
            param.put("TransactionID", transactionID);
            param.put("DateTime", dateTime);
            param.put("ReturnUrl", buyPackageReturnUrl);
            String[] propertyNames = {"DeviceID", "TransactionID", "DateTime", "ReturnUrl"};
            //
            Map<String, String> params = new HashMap<String, String>();
            params.put("Ticket", resultMap.get("Ticket"));
            params.put("DeviceID", deviceID);
            params.put("TransactionID", transactionID);
            params.put("DateTime", dateTime);
            params.put("ReturnUrl", buyPackageReturnUrl);
            params.put("Sign", MessageHandleUtils.sign(param, propertyNames));
            view.addObject("params", params);
            //
            String buyPackageUrl = GetConfigFile.getInstance().getProperties("buypackageurl");
            view.addObject("redirectToUrl", buyPackageUrl);
        } catch (Exception ex) {
            logger.error("登录流量汇出现异常", ex);
            view = new ModelAndView("common/exception");
            view.addObject("ex", ex);
        }
        return view;
    }
    
    /**
     * 精彩活动入口
     * @param user
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/wonderful.tv")
    public ModelAndView wonderfulActirity(HttpServletRequest request,HttpServletResponse response) throws Exception {
        ModelAndView view = null;
        User user = new User();
        String weixinAppNo = request.getParameter("weixinAppNo");
        if(StringUtils.isEmpty(weixinAppNo)){
        	 view = new ModelAndView("common/exception");
             view.addObject("ex", new Exception("未携带weixinAppNo参数"));
             return view;
        }
        
        String openid = StringUtils.isEmpty(request.getParameter("openid"))?(String)request.getSession().getAttribute("openid"):request.getParameter("openid");

		if(StringUtils.isEmpty(openid)){	
			if(StringUtils.isEmpty(weixinAppNo)){
				logger.warn("到我们的错误页面，获取当前用户手机号失败");
				view = new ModelAndView("common/exception");
				view.addObject("ex", new Exception("未携带weixinAppNo参数"));
                return view;
			}
			Map<String,String> map = userService.queryAppMessageByWeixinAppNo(weixinAppNo);
			HttpPostUtil.getOpenId(request, response, null,map);
			return null;
		}
		user.setOpenID(openid);
		report_logger.info(DateUtil.getCurDate()+"|"+openid+"|wonderful.tv");
		
        user.setWeixinAppNo(request.getParameter("weixinAppNo"));
        view = toShake(user,request,response);
        
        return view;
    }
    
    /**
     * 流量汇购买套餐通知
     * @param ProductPrice
     * @param CompanyCode
     * @param CompanyName
     * @param ProductCode
     * @param ProductName
     * @param TransactionID
     * @param Msisdn
     * @param Result
     * @param ResultDesc
     * @param Sign
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/packageReturn.tv")
    public ModelAndView packageReturn(
            @RequestParam(value = "CompanyCode", required = true) String CompanyCode,
            @RequestParam(value = "CompanyName", required = true) String CompanyName,
            @RequestParam(value = "ProductCode", required = true) String ProductCode,
            @RequestParam(value = "ProductName", required = true) String ProductName,
            @RequestParam(value = "ProductPrice", required = true) String ProductPrice,
            @RequestParam(value = "TransactionID", required = true) String TransactionID,
            @RequestParam(value = "WeixinAppNo", required = false) String WeixinAppNo,
            @RequestParam(value = "Msisdn", required = true) String Msisdn,
            @RequestParam(value = "Result", required = true) String Result,
            @RequestParam(value = "ResultDesc", required = true) String ResultDesc,
            @RequestParam(value = "Sign", required = true) String Sign,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        logger.debug("开始处理流量汇购买套餐通知");
        ModelAndView view = new ModelAndView("/bindMsisdn/drawFlowResult");
        try {
            String openId = getCurrentUserOpenId(request, response, WeixinAppNo);
            if (StringUtils.isEmpty(openId)) {
                return null;
            }
            logger.debug("获取参数[openid=" + openId + "，weixinAppNo=" + WeixinAppNo + "]并且[Result=" + Result + "]");
            //
            Map<String, String> params = new HashMap<String, String>();
            params.put("CompanyCode", CompanyCode);
            params.put("CompanyName", CompanyName);
            params.put("ProductCode", ProductCode);
            params.put("ProductName", ProductName);
            params.put("ProductPrice", ProductPrice);
            params.put("TransactionID", TransactionID);
            params.put("Msisdn", Msisdn);
            params.put("Result", Result);
            params.put("ResultDesc", ResultDesc);
            params.put("Sign", Sign);
            
            logger.debug("获取参数：" + params.toString());
            //
            String[] proptiesName = {"CompanyCode", "CompanyName", "ProductCode", "ProductName", "ProductPrice", "TransactionID"
                    , "Msisdn", "Result", "ResultDesc"};
            //
            Boolean isOk = MessageHandleUtils.checkSign(params, proptiesName);
            //
            if (isOk && Result.equals("0") && StringUtils.isNotEmpty(openId)) {
            	int freId = drawService.addDistributeFlowRedEnvelope(openId, Constants.FRE_FROM_SOURCE_BUY);
                //抽到大红包需要通知用户，往SMS_NOTIFY表插入一条记录
                UserNotifyPojo userNotifyPojo = new UserNotifyPojo();
                userNotifyPojo.setSmsType(Constants.SMS_TYPE_BUY);
                userNotifyPojo.setOpenid(openId);
                userNotifyPojo.setMsisdn(Msisdn);
                userNotifyPojo.setProductName(ProductName);
                userNotifyPojo.setFreId(freId);
                userNotifyPojo.setWeixinAppNo(WeixinAppNo);
                userNotifyService.addUserNotifyData(userNotifyPojo);
                
                DESTools desTools = DESTools.getInstance();
				String desFreId = desTools.desCrypto(freId+"");
                view.addObject("freId",freId);
                view.addObject("desFreId",desFreId);
            }
 //           ModelMap model = new ModelMap();
//            //取用户信息
//            User user_temp = new User();
//            user_temp.setOpenID(openId);
//            User user = userService.getUser(user_temp);
//            ModelMap model = new ModelMap();
//            model.addAttribute("user",user);
//            model.addAttribute("imagePath", GetConfigFile.getInstance().getProperties("ImageAccessPath"));
//            model.addAttribute("weixinAppNo", WeixinAppNo);
            view.addObject("source",Constants.FRE_FROM_SOURCE_BUY);
            view.addObject("weixinAppNo",WeixinAppNo);
    		
    		//流量红包配置
    		GetConfigFile getConfigFile = GetConfigFile.getInstance();
    		List<ConfigPojo> configPojoList = getConfigFile.getConfigPojoList();
    		if(configPojoList==null){
    			configService.getConfigData(getConfigFile.getProperties("provinceConfigTable"));
    		}
    		configPojoList = getConfigFile.getConfigPojoList();
    		if(configPojoList.isEmpty()){
    			logger.error("配置表无配置数据");
    			view.addObject("errorMsg","系统异常");
    		}else{
    			String activityCode = getConfigFile.getProperties("activityCode");
    			for(ConfigPojo configPojo:configPojoList){
    				if(activityCode.equals(configPojo.getActivityName())){
    					view.addObject("subFreNum", configPojo.getFlowpkgSubFreCountPeruser());	
    				}
    				break;
    			}
    		}
   //         view.addObject(model);
        } catch (Exception ex) {
            logger.error("流量汇购买套餐后赠送大红包出现异常", ex);
            view = new ModelAndView("common/exception");
            view.addObject("ex", ex);
            return view;
        }
        logger.debug("结束处理流量汇购买套餐通知");
        return view;
    }
    
//    /**
//     * 流量汇购买套餐通知
//     * @param ProductPrice
//     * @param CompanyCode
//     * @param CompanyName
//     * @param ProductCode
//     * @param ProductName
//     * @param TransactionID
//     * @param Msisdn
//     * @param Result
//     * @param ResultDesc
//     * @param Sign
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/packageReturn.tv")
//    public ModelAndView packageReturn(
//            @RequestParam(value = "CompanyCode", required = true) String CompanyCode,
//            @RequestParam(value = "CompanyName", required = true) String CompanyName,
//            @RequestParam(value = "ProductCode", required = true) String ProductCode,
//            @RequestParam(value = "ProductName", required = true) String ProductName,
//            @RequestParam(value = "ProductPrice", required = true) String ProductPrice,
//            @RequestParam(value = "TransactionID", required = true) String TransactionID,
//            @RequestParam(value = "WeixinAppNo", required = false) String WeixinAppNo,
//            @RequestParam(value = "Msisdn", required = true) String Msisdn,
//            @RequestParam(value = "Result", required = true) String Result,
//            @RequestParam(value = "ResultDesc", required = true) String ResultDesc,
//            @RequestParam(value = "Sign", required = true) String Sign,
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) {
//        logger.debug("开始处理流量汇购买套餐通知");
//        ModelAndView view = new ModelAndView("/index");
//        try {
//            String openId = getCurrentUserOpenId(request, response, WeixinAppNo);
//            if (StringUtils.isEmpty(openId)) {
//                logger.error("流量汇购买套餐通知获取openId出现异常");
//                view = new ModelAndView("common/exception");
//                view.addObject("ex", new Exception("流量汇购买套餐通知获取openId出现异常"));
//                return view;
//            }
//            logger.debug("获取参数[openid=" + openId + "，weixinAppNo=" + WeixinAppNo + "]并且[Result=" + Result + "]");
//            //
//            Map<String, String> params = new HashMap<String, String>();
//            params.put("CompanyCode", CompanyCode);
//            params.put("CompanyName", CompanyName);
//            params.put("ProductCode", ProductCode);
//            params.put("ProductName", ProductName);
//            params.put("ProductPrice", ProductPrice);
//            params.put("TransactionID", TransactionID);
//            params.put("Msisdn", Msisdn);
//            params.put("Result", Result);
//            params.put("ResultDesc", ResultDesc);
//            params.put("Sign", Sign);
//            logger.debug("获取参数：" + params.toString());
//            //
//            String[] proptiesName = {"CompanyCode", "CompanyName", "ProductCode", "ProductName", "ProductPrice", "TransactionID"
//                    , "Msisdn", "Result", "ResultDesc"};
//            //
//            Boolean isOk = MessageHandleUtils.checkSign(params, proptiesName);
//            //
//            if (isOk && Result.equals("0") && StringUtils.isNotEmpty(openId)) {
//            	int freId = drawService.addDistributeFlowRedEnvelope(openId, Constants.FRE_FROM_SOURCE_BUY);
//                //抽到大红包需要通知用户，往SMS_NOTIFY表插入一条记录
//                UserNotifyPojo userNotifyPojo = new UserNotifyPojo();
//                userNotifyPojo.setSmsType(Constants.SMS_TYPE_BUY);
//                userNotifyPojo.setOpenid(openId);
//                userNotifyPojo.setMsisdn(Msisdn);
//                userNotifyPojo.setProductName(ProductName);
//                userNotifyPojo.setFreId(freId);
//                userNotifyPojo.setWeixinAppNo(WeixinAppNo);
//                userNotifyService.addUserNotifyData(userNotifyPojo);
//            }
//            //取用户信息
//            User user_temp = new User();
//            user_temp.setOpenID(openId);
//            User user = userService.getUser(user_temp);
//            ModelMap model = new ModelMap();
//            model.addAttribute("user",user);
//            model.addAttribute("imagePath", GetConfigFile.getInstance().getProperties("ImageAccessPath"));
//            model.addAttribute("weixinAppNo", WeixinAppNo);
//            view.addObject(model);
//        } catch (Exception ex) {
//            logger.error("流量汇购买套餐后赠送大红包出现异常", ex);
//            view = new ModelAndView("common/exception");
//            view.addObject("ex", ex);
//            return view;
//        }
//        logger.debug("结束处理流量汇购买套餐通知");
//        return view;
//    }


    /**
     * 领取流量币入口
     * @param openId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/topresentcoin.tv")
    public ModelAndView toPresentCoin(
            @RequestParam(value = "openId", required = false) String openId,
            HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView view = null;
        try {
            String deviceID = GetConfigFile.getInstance().getProperties("deviceid");
            view = new ModelAndView("common/redirectto");
            //
            String msisdn = getCurrentUserMobile(request, response, openId, null);
            String dateTime = DateUtil.dateToDateFullString(DateUtil.getDate());
            if (StringUtils.isEmpty(msisdn)) {
                logger.warn("到我们的错误页面，获取当前用户手机号失败");
                view = new ModelAndView("common/exception");
                view.addObject("ex", new Exception("获取当前用户手机号失败"));
                return view;
            }
            Map<String, String> resultMap = thirdInterfService.getLoginToken(msisdn, dateTime);
            if (resultMap == null || StringUtils.isEmpty(resultMap.get("Ticket"))) {
                logger.warn("到我们的错误页面，登录流量汇出现异常");
                view = new ModelAndView("common/exception");
                view.addObject("ex", new Exception("登录流量汇出现异常"));
                return view;
            }
            //
            String transactionID = MessageHandleUtils.getTransactionID();
            //
            Map<String, String> param = new HashMap<String, String>();
            param.put("DeviceID", deviceID);
            param.put("TransactionID", transactionID);
            param.put("DateTime", dateTime);
            String[] propertyNames = {"DeviceID", "TransactionID", "DateTime"};
            //
            Map<String, String> params = new HashMap<String, String>();
            params.put("Ticket", resultMap.get("Ticket"));
            params.put("DeviceID", deviceID);
            params.put("TransactionID", transactionID);
            params.put("DateTime", dateTime);
            params.put("Sign", MessageHandleUtils.sign(param, propertyNames));
            view.addObject("params", params);
            //
            String presentCoinUrl = GetConfigFile.getInstance().getProperties("presentcoinurl");
            view.addObject("redirectToUrl", presentCoinUrl);
            
            report_logger.info(DateUtil.getCurDate()+"|"+openId+"|topresentcoin.tv");
            
        } catch (Exception ex) {
            logger.error("领取流量汇币出现异常", ex);
            view = new ModelAndView("common/exception");
            view.addObject("ex", ex);
            return view;
        }
        return view;
    }

    /**
     * 流量汇页面跳转（index为跳转到首页）
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/tollhindex.tv")
    public ModelAndView tollhindex(
            HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView view = null;
        try {
        	String tollhindexUrl = GetConfigFile.getInstance().getProperties("tollhindexUrl");
        	String url = "index";
            String deviceID = GetConfigFile.getInstance().getProperties("deviceid");
            Map<String, String> param = new HashMap<String, String>();
            param.put("DeviceID", deviceID);
            param.put("Url", url);
            
            view = new ModelAndView("common/redirectto");
           
            String openid = getCurrentUserOpenId(request, response, null);
            if (StringUtils.isEmpty(openid)) {
            	return null;
            }
            String msisdn = getCurrentUserMobile(request,response,openid,null);
            String dateTime = DateUtil.dateToDateFullString(DateUtil.getDate());
            if (StringUtils.isEmpty(msisdn)) {
                logger.warn("tollhindex.tv----用户未绑定手机号,跳转到流量会首页");
                //view = new ModelAndView("redirect:/phone.tv?weixinAppNo="+request.getParameter("weixinAppNo"));  
                view.addObject("params", param);
                view.addObject("redirectToUrl", tollhindexUrl);
                //view = new ModelAndView("redirect:http://shake.sd.chinamobile.com/"); 
                response.sendRedirect("http://shake.sd.chinamobile.com/"); 
                return null;
            }
            
            
            Map<String, String> resultMap = thirdInterfService.getLoginToken(msisdn, dateTime);
            if (resultMap == null || StringUtils.isEmpty(resultMap.get("Ticket"))) {
                logger.warn("到我们的错误页面，登录流量汇出现异常");
                view = new ModelAndView("common/exception");
                view.addObject("ex", new Exception("登录流量汇出现异常"));
                return view;
            }

            param.put("Ticket", resultMap.get("Ticket"));        
            String[] propertyNames = {"DeviceID", "Ticket", "Url"};
            Map<String, String> params = new HashMap<String, String>();
            params.put("DeviceID", deviceID);
            params.put("Url", url);
            params.put("Ticket", resultMap.get("Ticket"));
            params.put("Sign", MessageHandleUtils.sign(param, propertyNames));
            view.addObject("params", params);

            view.addObject("redirectToUrl", tollhindexUrl);
        } catch (Exception ex) {
            logger.error("领取流量汇币出现异常", ex);
            view = new ModelAndView("common/exception");
            view.addObject("ex", ex);
            return view;
        }
        return view;
    }


    protected String getCurrentUserOpenId(HttpServletRequest request, HttpServletResponse response, String _weixinAppNo) {
        //若是订阅号,url会携带openid过来
        String openid = (String)request.getSession().getAttribute("openid");
        String weixinAppNo = StringUtils.isEmpty(_weixinAppNo) ? request.getParameter("weixinAppNo") : _weixinAppNo;
       
        
        logger.debug("参数中获取参数[openid=" + openid + "]和[weixinAppNo=" + weixinAppNo + "]");
        if (StringUtils.isEmpty(openid)) {
        	 if(StringUtils.isEmpty(weixinAppNo)){
     			logger.error("请求/qiangFlow.tv,缺少weixinAppNo");
     			return null;
     		}
            try {
                Map<String, String> map = userService.queryAppMessageByWeixinAppNo(weixinAppNo);
                HttpPostUtil.getOpenId(request, response, null, map);
            } catch (Exception e) {
                logger.error("获取当前用户微信OPENID出现异常", e);
            }
            logger.warn("获取当前用户OPENID为空");
            return null;
        }
        return openid;
    }

    protected String getCurrentUserMobile(HttpServletRequest request, HttpServletResponse response, String openId, String _weixinAppNo) {
        try {
            String openIdTmp = StringUtils.isEmpty(openId)?getCurrentUserOpenId(request, response, _weixinAppNo):openId;
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(openIdTmp)) {
                User _temp = new User();
                _temp.setOpenID(openIdTmp);
                User user = userService.getUser(_temp);
                if (user != null && org.apache.commons.lang3.StringUtils.isNotEmpty(user.getBindMsisdn())) {
                    return user.getBindMsisdn();
                }
            }
        } catch (WxppException e) {
            logger.error("获取当前用户手机号出现异常", e);
        }
        logger.warn("获取当前用户手机号为空");
        return null;
    }
    
    
    private ModelAndView toShake(User user,
            HttpServletRequest request,
            HttpServletResponse response){
    	 ModelAndView view = null;
    	try {
            String deviceID = GetConfigFile.getInstance().getProperties("deviceid");
            view = new ModelAndView("common/redirectto");
            //
            String bindMsisdn = user.getBindMsisdn();
            
            String msisdn = StringUtils.isEmpty(bindMsisdn.trim())?getCurrentUserMobile(request, response, user.getOpenID(), user.getWeixinAppNo()):bindMsisdn;
            String dateTime = DateUtil.dateToDateFullString(DateUtil.getDate());
            if (StringUtils.isEmpty(msisdn)) {	
            	 response.sendRedirect("phone.tv?weixinAppNo="+request.getParameter("weixinAppNo")); 
                 return null;
            }
            Map<String, String> resultMap = thirdInterfService.getLoginToken(msisdn, dateTime);
            if (resultMap == null || StringUtils.isEmpty(resultMap.get("Ticket"))) {
                logger.warn("到我们的错误页面，登录流量汇出现异常");
                view = new ModelAndView("common/exception");
                view.addObject("ex", new Exception("登录流量汇出现异常"));
                return view;
            }
            //
            String buyPackageReturnUrl = GetConfigFile.getInstance().getProperties("buyPackageReturnUrl");
 
            buyPackageReturnUrl = buyPackageReturnUrl + "?WeixinAppNo=" + user.getWeixinAppNo();
            String transactionID = MessageHandleUtils.getTransactionID();
            //
            Map<String, String> param = new HashMap<String, String>();
            param.put("DeviceID", deviceID);
            param.put("TransactionID", transactionID);
            param.put("DateTime", dateTime);
            param.put("ReturnUrl", buyPackageReturnUrl);
            String[] propertyNames = {"DeviceID", "TransactionID", "DateTime", "ReturnUrl"};
            //
            Map<String, String> params = new HashMap<String, String>();
            params.put("Ticket", resultMap.get("Ticket"));
            params.put("DeviceID", deviceID);
            params.put("TransactionID", transactionID);
            params.put("DateTime", dateTime);
            params.put("ReturnUrl", buyPackageReturnUrl);
            params.put("Sign", MessageHandleUtils.sign(param, propertyNames));
            view.addObject("params", params);
            //
            String buyPackageUrl = GetConfigFile.getInstance().getProperties("buypackageurl");
            view.addObject("redirectToUrl", buyPackageUrl);
        } catch (Exception ex) {
            logger.error("登录流量汇出现异常", ex);
            view = new ModelAndView("common/exception");
            view.addObject("ex", ex);
        }
    	return view;
    }
}
