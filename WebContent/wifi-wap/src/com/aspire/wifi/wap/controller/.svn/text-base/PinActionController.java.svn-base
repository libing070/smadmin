package com.aspire.wifi.wap.controller;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.aspire.wifi.wap.entity.PinActionEntity;
import com.aspire.wifi.wap.entity.PinActivityEntity;
import com.aspire.wifi.wap.service.intf.PinActionHistoryService;
import com.aspire.wifi.wap.service.intf.PinActionService;
import com.aspire.wifi.wap.service.intf.PinActivityService;
import com.aspire.wifi.wap.util.Constants;
@Controller
public class PinActionController {
	protected static Logger logger =  LoggerFactory.getLogger(PinActionController.class);
	@Resource(name = "pinActionService")
	private PinActionService pinActionService;
	
	@Resource(name = "pinActivityService")
	private PinActivityService pinActivityService;
	
	@Resource(name = "pinActionHistoryService")
	private PinActionHistoryService pinActionHistoryService;
	
	/**
	 * 抢桌成功页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/huodong/qiangzhuoSucess", method = RequestMethod.GET)
	public ModelAndView getQiangZhuoSuccess(HttpServletRequest request) throws Exception {
		try {
			PinActivityEntity pinActivityEntity = pinActivityService.activityObject();
			request.setAttribute("activityId",pinActivityEntity.getActivityId());
		} catch (Exception e) {
			logger.error("查询活动ID失败!",e);
		}
		ModelAndView view = new ModelAndView("/qiangzhuo/qiangzhuo_creat");
		// 取得配置的媒体文件存放路径,供页面使用



		return view;
	}
	/**
	 * 抢桌失败页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/huodong/qiangzhuo_fail", method = RequestMethod.GET)
	public ModelAndView getQiangZhuoFail() throws Exception {
		ModelAndView view = new ModelAndView("/qiangzhuo/qiangzhuo_fail");
		// 取得配置的媒体文件存放路径,供页面使用



		return view;
	}
	
	
	/**
	 * 创建桌成功后调到活动页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/huoDongPage", method = RequestMethod.GET)
	public ModelAndView getHuoDongPage() throws Exception {
		ModelAndView view = new ModelAndView("/huodong");
		// 取得配置的媒体文件存放路径,供页面使用



		return view;
	}
	
	/**
	 * 规则页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/huodong/guize", method = RequestMethod.GET)
	public ModelAndView getGuiZe() throws Exception {
		ModelAndView view = new ModelAndView("/guize");
		// 取得配置的媒体文件存放路径,供页面使用



		return view;
	}
	
	
	/**
	 * 光荣榜



	 * @return
	 */
	@RequestMapping(value = "/getQiangDanTopInfo")
	public @ResponseBody
	Map<String, ? extends Object> getQiangDanTopInfo() {
		logger.debug("光荣榜");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<PinActionEntity> palist=null;
		
		try{
			palist=pinActionService.getQiangDanTopInfo();
			
			if(palist!=null){
				resultMap.put("list", palist);
		
				resultMap.put("CODE", "TRUE");
			}
		}catch(Exception e){
			logger.error("获取光荣榜异常", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}
	
	
	/**
	 * 抢单
	 * @return
	 */
	@RequestMapping(value = "/doQiangDan")
	public @ResponseBody
	Map<String, ? extends Object> doQiangDan(@RequestParam("activityId")int activityId){
		logger.debug("抢单");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int exeCount=0;
		int everyDayCount=0;
		PinActionEntity pa=null;
		PinActivityEntity pin=null;
		int countVisit=0;
		List<PinActivityEntity> palist=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long t=System.currentTimeMillis();
		int pinActionInvalidate=0;
		String ownerMobile="";
		int countNumber=0;
		Subject currentUser = SecurityUtils.getSubject();
		String mobile = currentUser.getPrincipal().toString();
		List<PinActivityEntity> activitylist=null;
		PinActivityEntity pine=null;
		int quota=0;
		int historyCount=0;
		//String mobile="13566653321";
		int count=0;
		try{
			palist=pinActivityService.getQiangDanDetails(BigInteger.valueOf(activityId));
			for(int i=0;i<palist.size();i++){
				PinActivityEntity pae=(PinActivityEntity)palist.get(i);
				pinActionInvalidate=pae.getPinActionInvalidate();
			}
			long t2=(System.currentTimeMillis()+(pinActionInvalidate*60*1000));//pinActionInvalidate可配置的抢单成功后未建桌的有效期
			pa=new PinActionEntity();
			pa.setActivityId(BigInteger.valueOf(activityId));
			pa.setOwnerMobile(mobile);//通过登录获得
			pa.setFlashSaleDate(sdf.format(new Timestamp(t)));
			pa.setExpiredDate(sdf.format(new Timestamp(t2)));
			pa.setCreatetableDate(sdf.format(new Timestamp(t)));//抢单的时候让创建桌的时间与抢单的时间一直

		    everyDayCount=pinActionService.everyDayCount();//每天的数量


			pin=new PinActivityEntity();
			pin.setActivityId(BigInteger.valueOf(activityId));//活动的IDactivityId
			countVisit=pinActivityService.getQiangDanTotalCount();//返回的远数据库的参与的人数


			pin.setVisitPvCnt(BigInteger.valueOf(countVisit+1));//点的时候就+1
			pinActivityService.addVisitPvCnt(pin);
			
			
			 activitylist=pinActivityService.getQiangDanDetails(BigInteger.valueOf(activityId));//获取活动的信息


	         for(int i=0;i<activitylist.size();i++){
	            	pine=(PinActivityEntity)activitylist.get(i);
	            	quota=pine.getQuota();
	         }
			
	         count=pinActionService.queryMobileIsNN(ownerMobile);
	         historyCount=pinActionHistoryService.queryHistoryCountByMoblie(ownerMobile);
	         resultMap.put("count", count);
	         resultMap.put("historyCount", historyCount);
	         resultMap.put("mobile", mobile);
	         resultMap.put("isOwnerMobile", Constants.IS_OWNER_MOBILE);
	        boolean islimit = pinActionService.checkActivityLimit(resultMap);
	        if (islimit) {
	        	resultMap.put("msg", "你有未完成的活动或者在限定的时间已经参加过活动!");
				resultMap.put("CODE", "FALSE");
				return resultMap;
	        }
	         
			if(everyDayCount+1<=quota){
				countNumber=pinActionService.countByMobile(mobile);
				if(countNumber>0){
					resultMap.put("countNumber", countNumber);//一个手机号码每天抢桌的个数（这里只做抢桌时的个数验证）
					resultMap.put("CODE", "MESSAGE");
					return resultMap;
				}else{
					exeCount=pinActionService.doQiangDan(pa);
					resultMap.put("everyDayCount", everyDayCount);//每天的数量



					if(exeCount>0){
						resultMap.put("quota", quota);
						resultMap.put("msg", "抢桌成功");
						resultMap.put("CODE", "TRUE");
						return resultMap;
					}
				}
				
			}else{
				resultMap.put("CODE", "COUNTOVER");
				return resultMap;
			}
		}catch(Exception e){
			logger.error("抢单异常"+ownerMobile, e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}
	
	
	/**
	 * 返回抢单成功的信息


	 * @return
	 */
	@RequestMapping(value = "/getQiangDanList")
	public @ResponseBody
	Map<String, ? extends Object> getQiangDanList(){
		logger.debug("返回抢单成功的信息");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<PinActionEntity> list=null;
		try{
			list=pinActionService.getQiangDanList();
			if(list!=null){
				resultMap.put("list", list);
				resultMap.put("CODE", "TRUE");
			}
		}catch(Exception e){
			logger.error("返回抢单成功的信息", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}
	
	/**
	 * 抢单过期删除(定时删除要用线程在spring里面配置此处要重新考虑)
	 * @return 
	 */
	@RequestMapping(value = "/deleteDanByMobile")
	public @ResponseBody
	Map<String, ? extends Object> deleteDanByMobile(){
		logger.debug("返回抢单成功的信息");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int exeCount=0;
		Subject currentUser = SecurityUtils.getSubject();
		String ownerMobile = currentUser.getPrincipal().toString();
		try{
			exeCount=pinActionService.deleteDanByMobile(ownerMobile);
			if(exeCount>0){
				resultMap.put("CODE", "TRUE");
			}
		}catch(Exception e){
			logger.error("抢单过期删除", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}
	
	/**
	 * 根据手机号码查询当天的拼桌ID（用于将获得的id插入到创建拼桌的flashSaleId中）
	 * @return
	 */
	@RequestMapping(value = "/queryIdByMobile")//@RequestParam("mobile")String mobile
	public @ResponseBody
	Map<String, ? extends Object> queryIdByMobile(@RequestParam("mobile")String mobile){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<PinActionEntity> list=null;
	//	Subject currentUser = SecurityUtils.getSubject();
	//	String mobile = currentUser.getPrincipal().toString();
		try{
			list=pinActionService.queryIdByMobile(mobile);
			if(null!=list&&!list.isEmpty()){
				resultMap.put("list", list);
				resultMap.put("CODE", "TRUE");
			}
		}catch(Exception e){
			logger.error("根据手机号码查询当天的拼桌ID", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}
	
	/**
	 * 根据手机号码以及ID更新创建表的日期
	 * @return
	 */
	@RequestMapping(value = "/updateCreateDate")
	public @ResponseBody
	Map<String, ? extends Object> updateCreateDate(PinActionEntity pae){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		PinActionEntity pa=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long t=System.currentTimeMillis();
		int exeCount=0;
		Subject currentUser = SecurityUtils.getSubject();
		String mobile = currentUser.getPrincipal().toString();
		try{
			pa=new PinActionEntity();
			pa.setOwnerMobile(mobile);//pae.getOwnerMobile() mobile
			pa.setFlashSaleId(pae.getFlashSaleId());
			pa.setCreatetableDate(sdf.format(new Timestamp(t)));
			exeCount=pinActionService.updateCreateDate(pa);
			if(exeCount>0){
				resultMap.put("CODE", "TRUE");
			}
		}catch(Exception e){
			logger.error(" 根据手机号码以及ID更新创建表的日期", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}
	
	
	/**
	 * 根据手机号码以及ID更新创建表的日期
	 * @return
	 */
	@RequestMapping(value = "/countTimeBack")
	public @ResponseBody
	Map<String, ? extends Object> countTimeBack(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
//		PinQiangZhuoOverTimeThread pqz=new PinQiangZhuoOverTimeThread();
//		Thread tr=new Thread(pqz);
//		tr.start();
//		System.out.println("go");
		return resultMap;
	}
	
	/**
	 * 拼桌的时候也加1
	 * @return
	 */
	@RequestMapping(value = "/pinzhuoCountPlus")//@RequestParam("activityId")int activityId
	public @ResponseBody
	Map<String, ? extends Object> pinzhuoCountPlus(@RequestParam("activityId")int activityId){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		PinActivityEntity pin=null;
		int countVisit=0;
		int exeCount=0;
		try{
			pin=new PinActivityEntity();
			pin.setActivityId(BigInteger.valueOf(activityId));
			countVisit=pinActivityService.getQiangDanTotalCount();//返回的远数据库的参与的人数



			pin.setVisitPvCnt(BigInteger.valueOf(countVisit+1));
			exeCount=pinActivityService.addVisitPvCnt(pin);
			if(exeCount>0){
				resultMap.put("exeCount", exeCount);
				resultMap.put("CODE", "TRUE");
			}
				
		}catch(Exception e){
			logger.error(" 拼桌的时候加1异常", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}
	
	
	
}
