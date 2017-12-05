package com.aspire.wifi.wap.controller;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aspire.wifi.wap.entity.ConsumePlaceEntity;
import com.aspire.wifi.wap.entity.PinActionEntity;
import com.aspire.wifi.wap.entity.PinActionHistoryEntity;
import com.aspire.wifi.wap.entity.PinActivityEntity;
import com.aspire.wifi.wap.entity.PinCreateTableDetailEntity;
import com.aspire.wifi.wap.entity.PinCreateTableDetailHistoryEntity;
import com.aspire.wifi.wap.entity.PinCreateTableEntity;
import com.aspire.wifi.wap.entity.PinCreateTableHistoryEntity;
import com.aspire.wifi.wap.entity.StudentReport;
import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.mapper.ShiyongMapper;
import com.aspire.wifi.wap.mapper.ZhuanFaEntityMapper;
import com.aspire.wifi.wap.service.intf.ConsumePlaceService;
import com.aspire.wifi.wap.service.intf.PinActionHistoryService;
import com.aspire.wifi.wap.service.intf.PinActionService;
import com.aspire.wifi.wap.service.intf.PinActivityService;
import com.aspire.wifi.wap.service.intf.PinCreateTableDetailHistoryService;
import com.aspire.wifi.wap.service.intf.PinCreateTableHistoryService;
import com.aspire.wifi.wap.service.intf.PinCreateTableService;
import com.aspire.wifi.wap.service.intf.StudentReportService;
import com.aspire.wifi.wap.util.Constants;

@Controller
public class PinCreateTableController {
	protected static Logger logger = LoggerFactory.getLogger(PinCreateTableController.class);
	
	@Resource(name = "pinCreateTableService")
	private PinCreateTableService pinCreateTableService;

	@Resource(name = "pinActionService")
	private PinActionService pinActionService;

	@Resource(name = "studentReportService")
	private StudentReportService studentReportService;

	@Resource(name = "pinActivityService")
	private PinActivityService pinActivityService;

	@Resource(name = "pinActionHistoryService")
	private PinActionHistoryService pinActionHistoryService;

	@Resource(name = "pinCreateTableHistoryService")
	private PinCreateTableHistoryService pinCreateTableHistoryService;

	@Resource(name = "pinCreateTableDetailHistoryService")
	private PinCreateTableDetailHistoryService pinCreateTableDetailHistoryService;
	@Resource(name = "shiyongMapper")
	private ShiyongMapper shiyongMapper;
	@Resource(name = "zhuanFaEntityMapper")
	private ZhuanFaEntityMapper zhuanFaEntityMapper;

	/**
	 * 展示消费地点列表接口
	 */
	@Resource(name = "consumePlaceService")
	private ConsumePlaceService consumePlaceService;

	/**
	 * 创建桌页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/createZhuo", method = RequestMethod.GET)
	public ModelAndView getCreateZhuoSuccess(HttpServletRequest request)
			throws Exception {
		ModelAndView view = new ModelAndView("/qiangzhuo/qiangzhuo_creat");
		// 取得配置的媒体文件存放路径,供页面使用
		try {
			PinActivityEntity pinActivityEntity = pinActivityService.activityObject();
			request.setAttribute("activityId", pinActivityEntity.getActivityId());
		} catch (Exception e) {
			logger.error("查询活动ID失败!", e);
		}
		return view;
	}

	/**
	 * 创建桌成功后的页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/huodong/qiangzhuoCreatSucess", method = RequestMethod.GET)
	public ModelAndView getQiangZhuoCreatSucess() throws Exception {
		ModelAndView view = new ModelAndView("/qiangzhuo/qiangzhuo_creat_sucess");
		// 取得配置的媒体文件存放路径,供页面使用

		return view;
	}

	/**
	 * 更新桌
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/qiangzhuo/update_creat", method = RequestMethod.GET)
	public ModelAndView updateCreat() throws Exception {
		ModelAndView view = new ModelAndView("/qiangzhuo/update_creat");
		// 取得配置的媒体文件存放路径,供页面使用

		return view;
	}

	/**
	 * 创建桌
	 * 
	 * @return
	 */
	@RequestMapping(value = "/createPinZhuo")
	public @ResponseBody
	Map<String, ? extends Object> createPinZhuo(PinCreateTableEntity pct,
			@RequestParam("activityId") int activityId) {
		logger.debug("创建桌");
		Map<String, Object> resultMap = new HashMap<String, Object>();

		int exeCount = 0, exeNumber = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int salePersonNum = 0, historyCount = 0, createTableHistory = 0, createTableDetailHistory = 0;
		BigInteger flashSaleId = null;
		String flashSaleDate = "";
		String ownerMobile = "";
		Subject currentUser = SecurityUtils.getSubject();
		ownerMobile = currentUser.getPrincipal().toString();
		int pinCreatetableInvalidate = 0;
		// String ownerMobile="13566653320";
		Date date1 = null;
		Date date2 = null;
		Date date3 = null;

		List<PinActivityEntity> activitylist = null;
		List<PinActionEntity> palist = null;
		List<PinActivityEntity> pailist = null;

		PinActivityEntity pine = null;
		PinCreateTableDetailEntity ptd = null;
		PinActionHistoryEntity phe = null;
		PinCreateTableEntity pc = null;
		PinActionEntity pae = null;
		PinCreateTableHistoryEntity pth = null;
		PinCreateTableDetailHistoryEntity ptde = null;

		try {
			long t = System.currentTimeMillis();
			pailist = pinActivityService.getQiangDanDetails(BigInteger.valueOf(activityId));
			for (int i = 0; i < pailist.size(); i++) {
				PinActivityEntity pinActivity = (PinActivityEntity) pailist.get(i);
				pinCreatetableInvalidate = pinActivity.getPinCreatetableInvalidate();
			}
			long t2 = (System.currentTimeMillis() + (pinCreatetableInvalidate * 24 * 60 * 60 * 1000));// 有效期

			palist = pinActionService.queryIdByMobile(ownerMobile);
			if (palist == null) {
				resultMap.put("msg", "请先去抢单!");
				resultMap.put("CODE", "FALSE");
			}
			if (palist != null) {
				for (int i = 0; i < palist.size(); i++) {
					pae = (PinActionEntity) palist.get(i);
					flashSaleId = pae.getFlashSaleId();
					flashSaleDate = pae.getFlashSaleDate();
				}
			}

			phe = new PinActionHistoryEntity();
			pc = new PinCreateTableEntity();
			pth = new PinCreateTableHistoryEntity();
			ptde = new PinCreateTableDetailHistoryEntity();

			date1 = sdf.parse(sdf.format(new Timestamp(t)));
			date2 = sdf.parse(sdf.format(new Timestamp(t2)));
			date3 = sdf.parse(sdf.format(new Timestamp(t)));// 此处时间有待考证

			pc.setFlashSaleId(flashSaleId);
			pc.setActDesc(pct.getActDesc());
			pc.setActTypeId(pct.getActTypeId());
			pc.setActStatusId(1);
			pc.setConsumePlaceId(1);
			pc.setCreateTableDate(date1);
			pc.setExpireDate(date2);
			pc.setOwnerMobile(ownerMobile);
			pc.setConsumePic(null);

			activitylist = pinActivityService.getQiangDanDetails(BigInteger.valueOf(activityId));// 获取活动的信息

			for (int i = 0; i < activitylist.size(); i++) {
				pine = (PinActivityEntity) activitylist.get(i);
				salePersonNum = pine.getSalePersonNum();
			}
			pc.setFreeSeat(salePersonNum - 1);

			pc.setAuditUser(null);
			pc.setAuditTime(date3);
			pc.setAuditStatus(1);
			pc.setAuditComment(null);

			// 插入到详细列表中
			ptd = new PinCreateTableDetailEntity();
			ptd.setFlashSaleId(flashSaleId);
			ptd.setIsOwnerMobile(1);
			ptd.setJoinDate(date1);
			ptd.setMobile(ownerMobile);
			exeCount = pinCreateTableService.createPinZhuo(pc);// 添加创建桌信息

			exeNumber = pinActionService.addCreateTableZhuozhu(ptd);// 添加桌主的一些信息

			// 抢桌成功后插入到抢桌历史列表
			phe.setFlashSaleId(flashSaleId);
			phe.setOwnerMobile(ownerMobile);
			phe.setActionDate(date1);// 插入当前的时间

			phe.setActionType(pct.getActTypeId());
			phe.setActionDesc("抢到了一个桌,并创建了桌");
			phe.setFlashSaleDate(sdf.parse(flashSaleDate));
			phe.setCreateTableDate(date1);
			historyCount = pinActionHistoryService.addPinActionHistory(phe);

			// 创建桌的记录插入到创建桌记录中

			pth.setFlashSaleId(flashSaleId);
			pth.setActDesc(pct.getActDesc());
			pth.setActTypeId(pct.getActTypeId());
			pth.setActStatusId(1);
			pth.setConsumePlaceId(1);
			pth.setOwnerMobile(ownerMobile);
			pth.setConsumePic(null);
			pth.setActionDate(date1);
			pth.setActionType(1);
			pth.setActionDesc("创建了桌");
			pth.setCreateTableDate(date1);
			pth.setExpiredDate(date2);
			pth.setAuditUser("");
			pth.setAuditTime(null);
			pth.setAuditComment("");
			pth.setAuditStatus(1);

			createTableHistory = pinCreateTableHistoryService.addCreateTableToHistory(pth);

			// createTableDetail信息插入到历史表中

			ptde.setFlashSaleId(flashSaleId);
			ptde.setMobile(ownerMobile);
			ptde.setIsOwnerMobile(1);
			ptde.setActionDate(date1);
			ptde.setActionType(1);
			ptde.setActionDesc("创建了桌");
			ptde.setJoinDate(date1);
			createTableDetailHistory = pinCreateTableDetailHistoryService.addCreateTableDetailToHistory(ptde);

			if (exeCount > 0 && exeNumber > 0 && historyCount > 0
					&& createTableHistory > 0 && createTableDetailHistory > 0) {
				resultMap.put("msg", "创建桌成功");
				resultMap.put("CODE", "TRUE");
			}
		} catch (Exception e) {
			logger.error("创建桌异常", e);
			resultMap.put("msg", e.getMessage());
			resultMap.put("CODE", "FALSE");
		}
		return resultMap;
	}

	/**
	 * 查询我的活动列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listMyActives")
	public @ResponseBody
	Map<String, ? extends Object> listMyActives(
			@RequestParam(value = "plate", required = false) String plate) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		PinCreateTableEntity pc = new PinCreateTableEntity();
		StudentReport studentReport = new StudentReport();
		List<Map<String, Object>> list = null;
		// 登录人的手机号

		Subject currentUser = SecurityUtils.getSubject();
		String msisdn = currentUser.getPrincipal().toString();
		logger.debug("当前登录人的手机号：" + msisdn);
		pc.setOwnerMobile(msisdn);
		studentReport.setMsisdn(msisdn);
		try {
			paramMap.put("ownerMobile", msisdn);
			if (plate != null && plate.equalsIgnoreCase("my_footer")) { // 我的足迹
				studentReport.setIsFootmark(Constants.IS_FOOTPRINT);
				list = pinCreateTableService.listPinCreateTableHis(pc);
				paramMap.put("statusCode", 1);//1代表已消费				paramMap.put("isSendMsg", 1);//
			} else { // 我的活动
				studentReport.setIsFootmark(Constants.IS_NOT_FOOTPRINT);
				list = pinCreateTableService.listPinCreateTable(pc);
				paramMap.put("statusCode", 0);
				paramMap.put("isSendMsg", 0);

			}
			List<Map<String, Object>> _list = studentReportService.queryStudentReportForMyActive(studentReport);
			List<Map<String, Object>> _list2 = shiyongMapper.queryShiyongByMobile(paramMap);
			List<Map<String, Object>> _list3 = shiyongMapper.queryHuodong(paramMap);
			paramMap.put("mobile", msisdn);
			paramMap.put("isCreater","1");//1表示创建者	
			List<Map<String, Object>> _list4 = zhuanFaEntityMapper.queryZhuanFaHuodong(paramMap);
			list.addAll(_list);
			list.addAll(_list2);
			list.addAll(_list3);
			list.addAll(_list4);
			resultMap.put("list", list);
		} catch (WifiException e) {

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("创建桌异常", e);
		}
		return resultMap;
	}

	/**
	 * 查询活动地点
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCounsumePlace")
	public @ResponseBody
	Map<String, ? extends Object> getCounsumePlace() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ConsumePlaceEntity> cslist = null;
		try {
			cslist = consumePlaceService.getCounsumePlace(resultMap);
			if (cslist != null) {
				resultMap.put("cslist", cslist);
				resultMap.put("CODE", "TRUE");
			}
		} catch (Exception e) {
			logger.error("获取拼单地点异常", e);
			resultMap.put("CODE", "FASE");
		}
		return resultMap;
	}

	/**
	 * 根据用户更新创建桌的信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateCreateTableByMobile")
	public @ResponseBody
	Map<String, ? extends Object> updateCreateTableByMobile(
			PinCreateTableEntity pct) {
		logger.debug("根据用户更新创建桌的信息");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String ownerMobile = currentUser.getPrincipal().toString();
		PinCreateTableEntity pcte = null, pc = null;
		int exeCount = 0, actTypeId = 0;
		PinCreateTableHistoryEntity pth = null;
		int createTableHistory = 0;
		Date date1 = null, createTable_Date = null, expireTable_Date = null, auditTime = null;
		String auditComment = "", auditUser = "", actDesc = "";
		byte[] consumepic = null;
		long t = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			pth = new PinCreateTableHistoryEntity();
			pcte = new PinCreateTableEntity();

			pcte.setFlashSaleId(pct.getFlashSaleId());
			pcte.setOwnerMobile(ownerMobile);
			// 查询审核未通过的

			pc = pinCreateTableService.querCreateTableByMobile(pcte);

			if (pc != null) {
				actDesc = pc.getActDesc();
				actTypeId = pc.getActTypeId();
				consumepic = pc.getConsumePic();
				createTable_Date = pc.getCreateTableDate();
				pcte.getExpireDate();
				auditUser = pc.getAuditUser();
				auditTime = pc.getAuditTime();
				auditComment = pc.getAuditComment();

			}
			date1 = sdf.parse(sdf.format(new Timestamp(t)));

			// 创建桌的记录插入到创建桌记录中

			pth.setFlashSaleId(pct.getFlashSaleId());
			pth.setActDesc(actDesc);
			pth.setActTypeId(actTypeId);
			pth.setActStatusId(1);
			pth.setConsumePlaceId(1);
			pth.setOwnerMobile(ownerMobile);
			pth.setConsumePic(consumepic);
			pth.setActionDate(date1);
			pth.setActionType(2);
			pth.setActionDesc("更新了拼桌的信息");
			pth.setCreateTableDate(createTable_Date);
			pth.setExpiredDate(expireTable_Date);
			pth.setAuditUser(auditUser);
			pth.setAuditTime(auditTime);
			pth.setAuditComment(auditComment);
			pth.setAuditStatus(1);

			createTableHistory = pinCreateTableHistoryService.addCreateTableToHistory(pth);

			pcte = new PinCreateTableEntity();
			pcte.setActTypeId(pct.getActTypeId());
			pcte.setFlashSaleId(pct.getFlashSaleId());
			pcte.setOwnerMobile(ownerMobile);
			pcte.setActDesc(pct.getActDesc());

			// 执行更新操作
			exeCount = pinCreateTableService.updateCreateTableByMobile(pcte);
			if (exeCount > 0) {
				if (createTableHistory > 0) {
					resultMap.put("msg", "修改创建桌数据成功");
					resultMap.put("CODE", "TRUE");
				}

			}
		} catch (Exception e) {
			logger.error("根据用户更新创建桌的信息异常", e);
			resultMap.put("msg", "修改创建桌数据失败");
			resultMap.put("CODE", "FASE");
		}
		return resultMap;
	}

	/**
	 * 根据审核状态展示用户的信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/showCreateTableByMobile")
	public @ResponseBody
	Map<String, ? extends Object> showCreateTableByMobile(
			PinCreateTableEntity pct) {
		logger.debug("根据用户更新创建桌的信息");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String mobile = currentUser.getPrincipal().toString();
		PinCreateTableEntity pcte = null, pcte_ = null;
		int actTypeId = 0;
		String actDesc = "";
		String auditComment = "";
		try {
			pcte = new PinCreateTableEntity();
			pcte.setFlashSaleId(pct.getFlashSaleId());
			pcte.setOwnerMobile(mobile);
			// 查询审核未通过的

			pcte_ = pinCreateTableService.querySingle(pcte);
			if (pcte_ != null) {
				auditComment = pcte_.getAuditComment();
			}

			pcte = pinCreateTableService.querCreateTableByMobile(pcte);
			if (pcte != null) {
				actDesc = pcte.getActDesc();
				actTypeId = pcte.getActTypeId();
			}
			resultMap.put("actDesc", actDesc);
			resultMap.put("actTypeId", actTypeId);
			resultMap.put("auditComment", auditComment);
			resultMap.put("CODE", "TRUE");
		} catch (Exception e) {
			logger.error("根据用户更新查询信息异常", e);
			resultMap.put("msg", "查询数据失败");
			resultMap.put("CODE", "FASE");
		}
		return resultMap;
	}

}
