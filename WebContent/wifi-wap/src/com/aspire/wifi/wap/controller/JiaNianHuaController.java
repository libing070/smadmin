package com.aspire.wifi.wap.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

import com.aspire.wifi.wap.entity.PrizeEntity;
import com.aspire.wifi.wap.entity.ShiYongEntity;
import com.aspire.wifi.wap.mapper.PrizeMapper;
import com.aspire.wifi.wap.service.intf.ShiyongService;
import com.aspire.wifi.wap.util.Constants;
@Controller
public class JiaNianHuaController {
	protected static Logger logger = LoggerFactory.getLogger(JiaNianHuaController.class);

	@Resource(name = "shiyongService")
	private ShiyongService shiyongService;
	@Resource(name = "prizeMapper")
	private PrizeMapper prizeMapper;
	
	@RequestMapping(value = "/jianianhua/youxi", method = RequestMethod.GET)
	public ModelAndView youxi(HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("/jianianhua/youxi");
		String score=request.getSession().getAttribute("score")==null?"":request.getSession().getAttribute("score").toString();
		if(!score.equals("")){
			 view = new ModelAndView("/jianianhua/guaguale");
		}
		return view;
	}
	@RequestMapping(value = "/jianianhua/guaguale", method = RequestMethod.GET)
	public ModelAndView guaguale(HttpServletRequest request) throws Exception {
		Subject currentUser = SecurityUtils.getSubject();
		String score = request.getParameter("score");
		request.getSession().setAttribute("score", score);
		
		ModelAndView view = new ModelAndView("/login");
		if (currentUser.isAuthenticated()) {//假如是登陆状态
			 view = new ModelAndView("/jianianhua/guaguale");
		}
		return view;
	}
	/***
	 * 获取获奖信息
	 * **/
	@RequestMapping(value = "/jianianhua/getPrize", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> youhuiPinLun(HttpServletRequest request,
    		@RequestParam(value="mobile",required = true)String mobile,@RequestParam(value="activityId",required = true)String activityId
	) throws Exception {
		 Map<String, Object> returnMap = new HashMap<String, Object>();
		 Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			paramMap.put("mobileDuan", mobile.substring(0,7));
			//查询是不是北京移动号码
				returnMap.put("isPrize", Constants.TRUE_PRIZE_0);
				paramMap.put("activityId", activityId);
				//查询嘉年华所拥有的奖品
				int shuijiValue = new Random().nextInt(100);
				String score=request.getSession().getAttribute("score")==null?"":request.getSession().getAttribute("score").toString();
				request.getSession().setAttribute("score", "");
				List<PrizeEntity> p = prizeMapper.queryPrizeInfo(paramMap);
				paramMap.put("ownerMobile", mobile);
				paramMap.put("statusCode", Constants.STATUS_GUAGUA_4);
				List<ShiYongEntity> shiyong1 = shiyongService.queryshiyongBylimit(paramMap);
				if(shiyong1.size()==0){//如果没有中奖记录就进行抽奖
					if(Integer.parseInt(score)>10){
						//抽取150M流量
						PrizeEntity p1 = new PrizeEntity();
						for(PrizeEntity p2:p){
							if(p2.getActivityId()==Integer.parseInt(activityId)&&p2.getPrizeName().contains("150M")){
								p1= p2;
							}
						}
						if(p1!=null){
							if(shuijiValue<p1.getPrizeRate()){						
									//证明中奖，插入一条历史记录
									ShiYongEntity shiyong=new ShiYongEntity();
									shiyong.setActivityId(Long.valueOf(activityId));
									shiyong.setOwnerMobile(mobile);
									shiyong.setCustomCode(p1.getId()+"");
									returnMap.put("isPrize", Constants.TRUE_PRIZE_150);							
									//查询是不是北京移动号码
									if(prizeMapper.isBeijingMobile(paramMap)<1){
										shiyong.setStatusCode(Constants.STATUS_GUAGUA_2);
										returnMap.put("isPrize", Constants.TRUE_PRIZE_1);
										returnMap.put("prizeType", p1.getId());
									}
									shiyongService.insertShiYongction(shiyong);
									paramMap.put("id", p1.getId());
									paramMap.put("controlNum", p1.getControlNum()-1);
									prizeMapper.updatePrizeEntity(paramMap);
									returnMap.put("jianianhuaId", shiyong.getId());
	
							}
						}
					} else{
						//抽取70M流量
						PrizeEntity p1 = new PrizeEntity();
						for(PrizeEntity p2:p){
							if(p2.getActivityId()==Integer.parseInt(activityId)&&p2.getPrizeName().contains("70M")){
								p1= p2;
							}
						}
						if(p1!=null){
							if(shuijiValue<p1.getPrizeRate()){							
									//证明中奖，插入一条历史记录
									ShiYongEntity shiyong=new ShiYongEntity();
									shiyong.setActivityId(Long.valueOf(activityId));
									shiyong.setOwnerMobile(mobile);
									shiyong.setCustomCode(p1.getId()+"");								
									returnMap.put("isPrize", Constants.TRUE_PRIZE_70);
									//查询是不是北京移动号码
									if(prizeMapper.isBeijingMobile(paramMap)<1){
										shiyong.setStatusCode(Constants.STATUS_GUAGUA_2);
										returnMap.put("isPrize", Constants.TRUE_PRIZE_2);
										returnMap.put("prizeType", p1.getId());
									}
									shiyongService.insertShiYongction(shiyong);
									paramMap.put("id", p1.getId());
									paramMap.put("controlNum", p1.getControlNum()-1);
									prizeMapper.updatePrizeEntity(paramMap);
									returnMap.put("jianianhuaId", shiyong.getId());
							}
						}
					}
				}
			 returnMap.put("CODE","TRUE");
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("CODE","FALSE");
			logger.error("刮奖的时候报错",e);
		}
		return returnMap;
	}
	
	/***
	 * 赠送
	 * **/
	@RequestMapping(value = "/jianianhua/zengsong", method = RequestMethod.POST)
	public @ResponseBody
    Map<String, Object> zengsong(HttpServletRequest request,@RequestParam(value="jianianhuaId",required = true)String jianianhuaId,@RequestParam(value="prizeId",required = true)String prizeId,
    		@RequestParam(value="ownermobile",required = true)String ownermobile,@RequestParam(value="activityId",required = true)String activityId
	) throws Exception {
		 Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			ShiYongEntity shiYongEntity = new ShiYongEntity();
			shiYongEntity.setId(Long.valueOf(jianianhuaId));
			shiYongEntity.setStatusCode(Constants.STATUS_GUAGUA_3);
			shiyongService.updateShiYong(shiYongEntity);
			shiYongEntity.setActivityId(Long.valueOf(activityId));
			shiYongEntity.setOwnerMobile(ownermobile);
			shiYongEntity.setCustomCode(prizeId);	
			shiYongEntity.setStatusCode(Constants.STATUS_GUAGUA_4);
			shiyongService.insertShiYongction(shiYongEntity);
			 returnMap.put("CODE","TRUE");
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("CODE","FALSE");
			logger.error("赠送嘉年华流量包报错",e);
		}
		return returnMap;
	}
}
