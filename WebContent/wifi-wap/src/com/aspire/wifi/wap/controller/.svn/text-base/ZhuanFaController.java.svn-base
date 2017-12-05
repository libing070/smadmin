package com.aspire.wifi.wap.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aspire.wifi.common.sms.constants.SmsConstants;
import com.aspire.wifi.common.sms.entity.SmsSendMsg;
import com.aspire.wifi.common.sms.service.SmsSender;
import com.aspire.wifi.wap.service.intf.SysConfigService;
import com.aspire.wifi.wap.util.SendMsg;
import com.aspire.wifi.wap.util.StringUtil;
import com.aspire.wifi.wap.entity.ShiroUser;
import com.aspire.wifi.wap.entity.UserInfoEntity;
import com.aspire.wifi.wap.entity.ZhuanFaEntity;
import com.aspire.wifi.wap.mapper.UserInfoMapper;
import com.aspire.wifi.wap.mapper.ZhuanFaEntityMapper;


@Controller
public class ZhuanFaController {
	protected static Logger logger = LoggerFactory.getLogger(ZhuanFaController.class);

	@Resource(name = "userInfoMapper")
	private UserInfoMapper userInfoMapper;
	@Resource(name = "zhuanFaEntityMapper")
	private ZhuanFaEntityMapper zhuanFaEntityMapper;
	
	@Resource(name = "sysConfigService")
	private SysConfigService sysConfigService;
	@Resource(name = "smsSender")
	private SmsSender smsSender;
	/**
	 * 转发页面
	 * **/
	
	@RequestMapping(value = "/getzhuanfa", method = RequestMethod.GET)
	public ModelAndView getzhuanfa(HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("/zhuanfa/zhuanfa");
		String onlyId =request.getParameter("onlyIds");
		try{
			
					Subject currentUser = SecurityUtils.getSubject();
					if (currentUser.isAuthenticated()) {//假如是登陆状态						Map<String,Object> paramMap = new HashMap<String,Object>();

						String mobile = currentUser.getPrincipal().toString();
						int onlyIds = userInfoMapper.queryUserInfo(mobile).getOnlyId();
						String nickname = userInfoMapper.queryUserInfo(mobile).getNickname();
						view.addObject("nickname",nickname);
						request.getSession().setAttribute("onlyflag", 1);

						if(onlyId==null){//发起方							//增加一条转发记录

							paramMap.put("mobile", mobile);
							paramMap.put("isCreater","1");//1表示创建者			
							List<ZhuanFaEntity> ZhuanFaEntitylist = zhuanFaEntityMapper.queryZhuanFaByLimit(paramMap);
							if(ZhuanFaEntitylist.size()==0){
								paramMap.put("zhuanFaId",onlyIds);
								zhuanFaEntityMapper.insertZhuanFa(paramMap);
								//给发起人发送短信通知
								view.addObject("isSendMsg","1");
							}else{
								paramMap.put("isCreater","");//1表示创建者			
								paramMap.put("mobile","");//1表示创建者			
								paramMap.put("zhuanFaId",ZhuanFaEntitylist.get(0).getZhuanFaId());//1表示创建者			
								if(zhuanFaEntityMapper.queryZhuanFaByLimit(paramMap).size()>4){
									String isHuodongYe =request.getParameter("isHuodongYe");
									if(isHuodongYe==null){
										
										view.addObject("isChuangJian",1);
									}
								}
							}
							request.getSession().setAttribute("onlyzhuanfaId", onlyIds);

						}else{//帮助方
//							
							request.getSession().setAttribute("onlyzhuanfaId", onlyId);
							paramMap.put("zhuanFaId",onlyId);
							paramMap.put("zhuanFaId","");
							paramMap.put("mobile",mobile);
							paramMap.put("isCreater", "0");//0表示转发者	,	
							List<ZhuanFaEntity> ZhuanFaEntitylist = zhuanFaEntityMapper.queryZhuanFaByLimit(paramMap);
						
								if(ZhuanFaEntitylist.size()==0){//
									String ownzhuanfaId =userInfoMapper.queryUserInfo(mobile).getOnlyId()+""; 
									if(onlyId.equals(ownzhuanfaId)){
										view.addObject("msg", "自己不能召唤自己");
										
									}else{
										paramMap.put("zhuanFaId",Long.valueOf(onlyId));
		
										zhuanFaEntityMapper.insertZhuanFa(paramMap);
										view.addObject("msg","帮助成功，么么哒");
										view.addObject("isSendMsg","1");

									}
								}else{
									String tempZhuanFaId = ZhuanFaEntitylist.get(0).getZhuanFaId().toString();
									if(tempZhuanFaId.equals(onlyId)){
										
										view.addObject("msg", "您已经加入了");
									}else{
										view.addObject("msg", "每人只能加入一个好友的求助");
									}
								}
							}
					//	}
						view.addObject("onlyIds",onlyIds);
						
					}else{//未登录
						if(onlyId!=null){//帮助方

							request.getSession().setAttribute("onlyzhuanfaId", onlyId);
						}else{
							request.getSession().setAttribute("iszhuanFa", "1");
						}
						ModelAndView view2= new ModelAndView("/login");
						return view2;
					}
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("转发页面增加转发记录异常！",e);
			
		}
		return view;
	}

	/**
	 * 获取创建者的信息
	 * @return
	 */
	@RequestMapping(value = "/getzhuanfaList", method = RequestMethod.POST)
	public @ResponseBody Map<String, ?> loadLoginMobile(HttpServletRequest request,ShiroUser user){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Subject currentUser = SecurityUtils.getSubject();
			String onlyzhuanfaId = request.getSession().getAttribute("onlyzhuanfaId")==null?"":request.getSession().getAttribute("onlyzhuanfaId").toString();			
			String onlyzhuanfaId2 = request.getSession().getAttribute("onlyzhuanfaId2")==null?"":request.getSession().getAttribute("onlyzhuanfaId2").toString();	
			if(onlyzhuanfaId.equals("")){
				onlyzhuanfaId=onlyzhuanfaId2;
			}
			List<UserInfoEntity> users =new ArrayList<UserInfoEntity>();
			int zhuanFaId=0;
			if(!onlyzhuanfaId.equals("")){
				zhuanFaId=Integer.parseInt(onlyzhuanfaId);
				request.getSession().setAttribute("onlyzhuanfaId2",zhuanFaId);

			}else{
				if(currentUser.isAuthenticated()){
					zhuanFaId= userInfoMapper.queryUserInfo(currentUser.getPrincipal().toString()).getOnlyId();
				} else{
					resultMap.put("users",users);
					resultMap.put("CODE", "TRUE");
					return resultMap;	
				}
			}
			resultMap.put("zhuanFaId", zhuanFaId);
//			List<ZhuanFaEntity> zhuanfaList=zhuanFaEntityMapper.queryZhuanFaByLimit(resultMap);
//			Long mobileLong[] = new Long[zhuanfaList.size()];
//			for(int i=0;i<zhuanfaList.size();i++){
//				mobileLong[i]=Long.valueOf(zhuanfaList.get(i).getMobile());
//			}
//			resultMap.put("mobileLong",mobileLong);
//			if(mobileLong.length>0){
				users=userInfoMapper.queryUserInfoByOnlyId(resultMap);	
//			}
			resultMap.put("users",users);
			resultMap.put("CODE", "TRUE");
			return resultMap;
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("msg", "获取创建者的信息失败，请稍候再试！");
			resultMap.put("CODE", "FALSE");
			return resultMap;
		}
	}
	/**
	 * 增加给朋友转发的转发记录
	 
	@RequestMapping(value = "/addzhuanfa", method = RequestMethod.POST)
	public @ResponseBody Map<String, ?> addzhuanfa(HttpServletRequest request,ShiroUser user){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Subject currentUser = SecurityUtils.getSubject();
			String onlyzhuanfaId = request.getSession().getAttribute("onlyzhuanfaId2")==null?"":request.getSession().getAttribute("onlyzhuanfaId2").toString();			
			if(!onlyzhuanfaId.equals("")){
				String ownzhuanfaId =userInfoMapper.queryUserInfo(currentUser.getPrincipal().toString()).getOnlyId()+""; 
				if(onlyzhuanfaId.equals(ownzhuanfaId)){
					resultMap.put("msg", "自己不能召唤自己");
				}else{
					//增加一条转发记录
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("mobile", SecurityUtils.getSubject().getPrincipal().toString());
					paramMap.put("isCreater", "0");//0表示转发者	,	
					List<ZhuanFaEntity> ZhuanFaEntitylist = zhuanFaEntityMapper.queryZhuanFaByLimit(paramMap);
					if(ZhuanFaEntitylist.size()==0){//
						paramMap.put("zhuanFaId",Long.valueOf(onlyzhuanfaId));
						zhuanFaEntityMapper.insertZhuanFa(paramMap);
						resultMap.put("msg", "加入成功");
					}else{
						String tempZhuanFaId = ZhuanFaEntitylist.get(0).getZhuanFaId().toString();
						if(tempZhuanFaId.equals(onlyzhuanfaId)){
							
							resultMap.put("msg", "您已经加入了");
						}else{
							resultMap.put("msg", "每人只能加入一个好友的求助");
						}
					}
				}
				resultMap.put("zhuanFaId",onlyzhuanfaId);

				request.getSession().setAttribute("onlyzhuanfaId2", "");
			}else{
				resultMap.put("msg", "由于长时间转发已失效，请复制链接重新转发");
			}
			resultMap.put("CODE", "TRUE");
			return resultMap;
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("msg", "获取登陆失败，请稍候再试！");
			resultMap.put("CODE", "FALSE");
			return resultMap;
		}
	}* @return
	 */
	/**
	 * 获取转发活动总数
	 * ***/
	@RequestMapping(value = "/getZhuanFaNum", method = RequestMethod.POST)
	public @ResponseBody Map<String, ?> addzhuanfa(HttpServletRequest request,ShiroUser user){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			int zhuanfaNum = zhuanFaEntityMapper.queryZhuanFaByLimit(paramMap).size();
			resultMap.put("zhuanfaNum", zhuanfaNum);
			resultMap.put("CODE", "TRUE");
			return resultMap;
		}catch(Exception e){
			logger.error("获取转发活动总数失败",e);
			return resultMap;
		}
	}
	/**
	 * 发送转发的短信
	 * ***/
	@RequestMapping(value = "/sendZhuanFaMsg", method = RequestMethod.POST)
	public void sendZhuanFaMsg(String mobile){

		try {
			//给帮助人发送短信通知
			SmsSendMsg ssm =SendMsg.getMsg(mobile);
			String code=StringUtil.genRandomString(6);
			String smsContent = sysConfigService.getSysConfigAsString(SmsConstants.WIFI_SMS_NOTIVE_CISHANG, new String[]{code});
			ssm.setContent(smsContent);
			smsSender.send(ssm);//发送短信
			
		}catch(Exception e){
			logger.error("发送转发短信失败",e);

		}
	}
	
	
}
