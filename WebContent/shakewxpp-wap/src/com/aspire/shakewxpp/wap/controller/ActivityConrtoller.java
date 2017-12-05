package com.aspire.shakewxpp.wap.controller;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aspire.shakewxpp.wap.entity.ConfigPojo;
import com.aspire.shakewxpp.wap.entity.DistributeFlowRedEnvelope;
import com.aspire.shakewxpp.wap.entity.GrabFlowSubRedEnvelope;
import com.aspire.shakewxpp.wap.entity.User;
import com.aspire.shakewxpp.wap.service.ConfigService;
import com.aspire.shakewxpp.wap.service.DistributeFlowRedService;
import com.aspire.shakewxpp.wap.service.GrabFlowSubRedService;
import com.aspire.shakewxpp.wap.service.UserService;
import com.aspire.shakewxpp.wap.util.Constants;
import com.aspire.shakewxpp.wap.util.DESTools;
import com.aspire.shakewxpp.wap.util.DateUtil;
import com.aspire.shakewxpp.wap.util.GetConfigFile;
import com.aspire.shakewxpp.wap.util.HttpPostUtil;

@Controller
public class ActivityConrtoller {
	protected static Logger logger =  LoggerFactory.getLogger(ActivityConrtoller.class);
	
	protected static Logger report_logger =  LoggerFactory.getLogger("report_log");
	
	@Resource(name = "distributeFlowRedService")
	public DistributeFlowRedService distributeFlowRedService;
	
	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "grabFlowSubRedService")
	private GrabFlowSubRedService grabFlowSubRedService;
	
	@Resource(name = "configService")
	private ConfigService configService;
	
	/**
	 * 微信流量红包规则
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/guize.tv")
	public ModelAndView getGuiZe(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String openid = StringUtils.isEmpty(request.getParameter("openid"))?(String)request.getSession().getAttribute("openid"):request.getParameter("openid");

		String weixinAppNo = request.getParameter("weixinAppNo");
		if(StringUtils.isEmpty(openid)){	
			if(StringUtils.isEmpty(weixinAppNo)){
				logger.warn("到我们的错误页面，获取当前用户手机号失败");
                model.addAttribute("ex", new Exception("未携带weixinAppNo参数"));
			}
			Map<String,String> map = userService.queryAppMessageByWeixinAppNo(weixinAppNo);
			HttpPostUtil.getOpenId(request, response, null,map);
			return null;
		}
		
		report_logger.info(DateUtil.getCurDate()+"|"+openid+"|guize.tv");
		
		ModelAndView view = new ModelAndView("huodong/guize");
		//取抽流量红包配置
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
					view.addObject("bindSubFreCountPeruser",configPojo.getBindSubFreCountPeruser());
					view.addObject("maxFreCount",configPojo.getMaxFreCount());
					view.addObject("subFreCountPeruser",configPojo.getSubFreCountPeruser());
					
					String[] arrayTimeRange = configPojo.getTimeRange().split("-");
					view.addObject("times",arrayTimeRange.length);
					
					String timeRange = configPojo.getTimeRange();
					timeRange = timeRange.replace("-", "、");
					view.addObject("timeRange",timeRange);
					
					view.addObject("maxUserCount",configPojo.getMaxUserCount());
					view.addObject("flowpkgSubFreCountPeruser",configPojo.getFlowpkgSubFreCountPeruser());
					view.addObject("freExpireDays",configPojo.getFreExpireDays());
					view.addObject("subFreExchangeDays",configPojo.getSubFreExchangeDays());
				}
				break;
			}
		}
		return view;
	}	
	
	
	/**
	 * 微信关注
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/guanzhu.tv")
	public ModelAndView getGuanZhu() throws Exception {
		ModelAndView view = new ModelAndView("bindMsisdn/concern");
		return view;
	}	
	
	
	/**
	 * 微信流量红包抢过了

	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/yiqiang.tv")
	public ModelAndView getYiQiang(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView view = new ModelAndView("smallredbag/yiqiang");

		//若是订阅号,url会携带openid过来
		String openid = StringUtils.isEmpty(request.getParameter("openid"))?(String)request.getSession().getAttribute("openid"):request.getParameter("openid");

		String weixinAppNo = request.getParameter("weixinAppNo");
		if(StringUtils.isEmpty(openid)){	
			if(StringUtils.isEmpty(weixinAppNo)){
				logger.warn("到我们的错误页面，获取当前用户手机号失败");
				view.addObject("ex", new Exception("未携带weixinAppNo参数"));
                view = new ModelAndView("common/exception");
                return view;
			}
			Map<String,String> map = userService.queryAppMessageByWeixinAppNo(weixinAppNo);
			HttpPostUtil.getOpenId(request, response, null,map);
			return null;
		}

		view.addObject("weixinAppNo", weixinAppNo);
		view.addObject("openId", openid);
		return view;
	}	
	
	/**
	 * 微信流量红包规则
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sended.tv")
	public ModelAndView getSended(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView view = new ModelAndView("huodong/sended");
		
		//若是订阅号,url会携带openid过来
		String openid = StringUtils.isEmpty(request.getParameter("openid"))?(String)request.getSession().getAttribute("openid"):request.getParameter("openid");

		String weixinAppNo = request.getParameter("weixinAppNo");
		if(StringUtils.isEmpty(openid)){	
			if(StringUtils.isEmpty(weixinAppNo)){
				logger.error("请求/drawPage.tv,缺少weixinAppNo");
				throw new Exception("缺少参数");
			}
			Map<String,String> map = userService.queryAppMessageByWeixinAppNo(weixinAppNo);
			HttpPostUtil.getOpenId(request, response, null,map);
			return null;
		}
		
		view.addObject("weixinAppNo",weixinAppNo);
		view.addObject("openId", openid);
		return view;
	}	
	
	/**
	 * 红包抢完了页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/qiangOver.tv")
	public ModelAndView qiangOver(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView view = new ModelAndView("smallredbag/qiangOver");
		String weixinAppNo = request.getParameter("weixinAppNo");
		if(StringUtils.isEmpty(weixinAppNo)){
			logger.error("请求/qiangOver.tv,缺少weixinAppNo");
			throw new Exception("缺少参数");
		}
		
		GetConfigFile getConfigFile = GetConfigFile.getInstance();
		List<ConfigPojo> configPojoList = getConfigFile.getConfigPojoList();
		String activityCode = getConfigFile.getProperties("activityCode");
		for(ConfigPojo configPojo:configPojoList){
			if(activityCode.equals(configPojo.getActivityName())){
				view.addObject("timeRange", configPojo.getTimeRange());
			}
		}
		view.addObject("fialType", Constants.DRAW_FLOW_RED_OVER);
		view.addObject("weixinAppNo", weixinAppNo);
		return view;
	}
	
	
	/**
	 * 抢红包未开始
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ready.tv")
	public ModelAndView ready(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView view = new ModelAndView("smallredbag/qiangOver");
		String weixinAppNo = request.getParameter("weixinAppNo");
		if(StringUtils.isEmpty(weixinAppNo)){
			logger.error("请求/qiangOver.tv,缺少weixinAppNo");
			throw new Exception("缺少参数");
		}
		
		GetConfigFile getConfigFile = GetConfigFile.getInstance();
		List<ConfigPojo> configPojoList = getConfigFile.getConfigPojoList();
		String activityCode = getConfigFile.getProperties("activityCode");
		for(ConfigPojo configPojo:configPojoList){
			if(activityCode.equals(configPojo.getActivityName())){
				view.addObject("timeRange", configPojo.getTimeRange());
			}
		}
		view.addObject("fialType", Constants.DRAW_FLOW_RED_READY);
		view.addObject("weixinAppNo", weixinAppNo);
		return view;
	}
	
	
	
	/**
	 * 领取页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lingQu.tv")
	public ModelAndView lingQu(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView view = new ModelAndView("huodong/lingqu");
		
		
		//若是订阅号,url会携带openid过来
		String openid = StringUtils.isEmpty(request.getParameter("openid"))?(String)request.getSession().getAttribute("openid"):request.getParameter("openid");

		String weixinAppNo = request.getParameter("weixinAppNo");
		if(StringUtils.isEmpty(openid)){	
			if(StringUtils.isEmpty(weixinAppNo)){
				logger.warn("到我们的错误页面，获取当前用户手机号失败");
				view.addObject("ex", new Exception("未携带weixinAppNo参数"));
                view = new ModelAndView("common/exception");
                return view;
			}
			Map<String,String> map = userService.queryAppMessageByWeixinAppNo(weixinAppNo);
			HttpPostUtil.getOpenId(request, response, null,map);
			return null;
		}
		
		
		String freId=(String)request.getParameter("freId");
		
		GrabFlowSubRedEnvelope grabFlowSubRedEnvelope = new GrabFlowSubRedEnvelope();
		grabFlowSubRedEnvelope.setOpenId(openid);
		grabFlowSubRedEnvelope.setFreId(Integer.parseInt(freId));
		try{
			int result = grabFlowSubRedService.relationForFlowFlowRedPackage(grabFlowSubRedEnvelope);
			if(result==0){
				view.addObject("ex", new Exception("用户没有该红包数据"));
	            view = new ModelAndView("common/exception");
	            return view;
			}
		}catch(Exception e){
			view.addObject("ex", new Exception("查询用户与红包关系出现异常"));
            view = new ModelAndView("common/exception");
            return view;
		}
		
		
		
		
		DistributeFlowRedEnvelope dfr=distributeFlowRedService.queryDistributeFlowInfo(Integer.valueOf(freId));//查询大红包的对象列表
		Integer freFromSource=0,bigCount=0;
		String grabtimeDate="",expireDate="";
		if(dfr!=null ){
			freFromSource=dfr.getFreFromSource();
			grabtimeDate=DateUtil.dateToDateString(dfr.getGrabTime(),DateUtil.yyyy_MM_dd_decline);
			expireDate = DateUtil.dateToDateString(dfr.getExpiredTime(), DateUtil.yyyy_MM_dd_decline);
			bigCount=dfr.getBigCount();
		}
		
		GrabFlowSubRedEnvelope gfse=new GrabFlowSubRedEnvelope();
		gfse.setFreId(Integer.valueOf(freId));
		int smallCount=grabFlowSubRedService.querySmallCount(gfse);
		
		view.addObject("freFromSource", freFromSource);
		view.addObject("grabtimeDate", grabtimeDate);
		view.addObject("expireDate", expireDate);
		view.addObject("bigCount", bigCount);
		view.addObject("smallCount", smallCount);
		view.addObject("freId", freId);
		
		DESTools desTools = DESTools.getInstance();
		String desFreId = desTools.desCrypto(freId);
        view.addObject("desFreId",desFreId);
        
		view.addObject("weixinAppNo", weixinAppNo);
		view.addObject("openId", openid);
		return view;
	}
	
	/**
	 * 根据用户的微信账号查询用户的大红包列表
	 * @return
	 */
	@RequestMapping(value = "/querCurrentCount.tv")
	public @ResponseBody Map<String, ?>  querCurrentCount(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int percurrentCount=0;
		try{
			percurrentCount=distributeFlowRedService.querCurrentCount();
			if(percurrentCount>0){
				resultMap.put("percurrentCount", percurrentCount);
				resultMap.put("CODE", "TRUE");	
			}
		}catch(Exception e){
		 logger.error("根据用户的微信账号查询用户的大红包列表异常",e);
		 resultMap.put("CODE", "FALSE");	
		}
		return resultMap;
	}
	
	
	/**
	 * 根据用户的微信账号查询用户的大红包列表
	 * @return
	 */
	@RequestMapping(value = "/queryByopenid.tv")
	public @ResponseBody List<DistributeFlowRedEnvelope> queryByopenid(@RequestParam("begin") int begin,@RequestParam("ends") int ends,@RequestParam("openId") String openId){
		List<DistributeFlowRedEnvelope> listBigRed=null;
		//String openid="oPALYjkPTGHscH7d5clmWU6f2cfM";
		DistributeFlowRedEnvelope dfe_out=null;
		try{
			dfe_out=new DistributeFlowRedEnvelope();
			dfe_out.setBegin(begin);
			dfe_out.setEnds(ends);
			dfe_out.setOpenid(openId);
			
			listBigRed=distributeFlowRedService.queryByopenid(dfe_out);
			for(DistributeFlowRedEnvelope df : listBigRed) {
				DistributeFlowRedEnvelope dfe=new DistributeFlowRedEnvelope();
				GrabFlowSubRedEnvelope gfs=new GrabFlowSubRedEnvelope();
				dfe.setFreId(df.getFreId());
				dfe.setOpenid(openId);
				int freId=df.getFreId();
				
				//根据用户的openid以及大红包的id查询领取的数量

				//gfs.setOpenId(openid);
				gfs.setFreId(freId);
				int smallCount=grabFlowSubRedService.querySmallCount(gfs);
				df.setField("smallCount", smallCount);
				//做时间处理

				String grabtimeDate=DateUtil.dateToDateString(df.getGrabTime(),DateUtil.yyyy_MM_dd_decline);
				String expireDate = DateUtil.dateToDateString(df.getExpiredTime(), DateUtil.yyyy_MM_dd_HH_mm_ss_EN);
				df.setField("grabtimeDate", grabtimeDate);
				df.setField("expireDate", expireDate);
		  }
		}catch(Exception e){
		 logger.error("根据用户的微信账号查询用户的大红包列表异常",e);
		}
		return listBigRed;
	}
	
	/**
	 * 验证用户是否绑定了手机
	 * @return
	 */
	@RequestMapping(value = "/validateUserMsidn.tv")
	public @ResponseBody Map<String, ?>  validateUserMsidn(@RequestParam("openId") String openId){
		Map<String, Object> resultMap = new HashMap<String, Object>();
	    User user = new User();
	    user.setOpenID(openId);
		try{
			User userTemp=userService.getUser(user);
			if(null==userTemp){
				userTemp = user;
			}
			
			if(StringUtils.isNotEmpty(userTemp.getBindMsisdn())){
				resultMap.put("CODE", "TRUE");//已绑定
			}else{
				resultMap.put("CODE", "FALSE");//未绑定
			}
			
			if(Constants.USER_SUBSCRIPT_TEMP.equals(userTemp.getSubscribe()) || Constants.USER_UNSUBSCRIPT.equals(userTemp.getSubscribe())){
				resultMap.put("CODE", "FALSE");//未绑定
				resultMap.put("selfCODE", "NOCONCERN");//未关注
			}
		}catch(Exception e){
			logger.error("验证用户是否绑定手机异常",e);
			resultMap.put("selfCODE", "NOCONCERN");//如果查询出现异常，说明数据库不存在此用户，提示用户关注
		}
		return resultMap;
	}
	
	/**
	 * 查询领取的小红包信息
	 * @return
	 */
	@RequestMapping(value = "/queryLingQu.tv")
	public @ResponseBody Map<String, ?>  queryLingQu(@RequestParam("freId")int freId ,@RequestParam("begin") int begin,@RequestParam("ends") int ends,@RequestParam("expireDate") String expireDate,@RequestParam("openId") String openId){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<GrabFlowSubRedEnvelope> lingquList=null;
		Float flowSumCount=0f;
		GrabFlowSubRedEnvelope gfse=null;
		try{
			gfse=new GrabFlowSubRedEnvelope();
			gfse.setFreId(freId);
			gfse.setBegin(begin);
			gfse.setEnds(ends);
			
			int smallCount=grabFlowSubRedService.querySmallCount(gfse);//领取的数量
			
			lingquList=grabFlowSubRedService.queryLingQu(gfse);//根据freid查询已领取的列表
			
			String newExpireDate=expireDate.replace("/", "-");
			Date dt1=DateUtil.convertDate(newExpireDate);
			Long dt1mm=dt1.getTime();
			Date currentDate=DateUtil.getDate();
			Long currentDatemm=currentDate.getTime();
			if((dt1mm-currentDatemm)<=0){
				resultMap.put("timeCode", "OVERTIME");
			}
			
			flowSumCount=grabFlowSubRedService.queryFlowSum(freId);//领取的流量币的总数
			if(lingquList!=null){
				resultMap.put("flowSumCount", flowSumCount);
				resultMap.put("smallCount", smallCount);
				resultMap.put("lingquList", lingquList);
				resultMap.put("CODE", "TRUE");
			}
		}catch(Exception e){
			logger.error("查询领取的小红包信息",e);
			resultMap.put("CODE", "FALSE");
		}
		
		return resultMap;
	}
	
}
