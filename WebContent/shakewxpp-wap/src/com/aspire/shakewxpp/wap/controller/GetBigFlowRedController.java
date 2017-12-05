package com.aspire.shakewxpp.wap.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aspire.shakewxpp.wap.entity.DistributeFlowRedEnvelope;
import com.aspire.shakewxpp.wap.service.DistributeFlowRedService;
import com.aspire.shakewxpp.wap.util.DESTools;

/**
 * 
 * @author caozhaoyan
 *
 */
@Controller
@Scope("prototype")
public class GetBigFlowRedController {

	protected static Logger logger = LoggerFactory.getLogger(GetBigFlowRedController.class);
	
	@Resource(name = "distributeFlowRedService")
	private DistributeFlowRedService distributeFlowRedService;
	
	/**
	 * 获得流量红包（大红包）页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getBigFlowRedPage.tv", method = RequestMethod.GET)
	public String getBigFlowRedPage(Model model,HttpServletRequest request) throws Exception {
		String page="/bindMsisdn/drawFlowResult";
		String freId = request.getParameter("freId");
		String weixinAppNo = (String)request.getParameter("weixinAppNo");
		
		if(StringUtils.isEmpty(request.getParameter("weixinAppNo"))){
			logger.error("请求/getBigFlowRedPage.tv,缺少weixinAppNo");
			model.addAttribute("ex", new Exception("未携带weixinAppNo参数"));
            return "common/exception";
		}
		if(StringUtils.isEmpty(freId)){
			logger.error("请求/getBigFlowRedPage.tv,缺少freId");
			model.addAttribute("ex", new Exception("未携带freId参数"));
            return "common/exception";
		}
		
		model.addAttribute("weixinAppNo",weixinAppNo);
		
		DESTools desTools = DESTools.getInstance();
		model.addAttribute("desFreId", request.getParameter("freId"));	
		freId = desTools.decrypt(freId);
		model.addAttribute("freId", freId);	
		
		//根据freId查询大红包信息
		DistributeFlowRedEnvelope distributeFlowRedEnvelope = distributeFlowRedService.queryDistributeFlowInfo(Integer.valueOf(freId));
		
		model.addAttribute("source",distributeFlowRedEnvelope.getFreFromSource());
		model.addAttribute("subFreNum", distributeFlowRedEnvelope.getBigCount());	
		
		return page;
	}
}
