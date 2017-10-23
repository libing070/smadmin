
package com.xxx.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxx.cache.RedisHandler;
import com.xxx.controller.valueobject.ShopInfoVO;
import com.xxx.model.SmiShopInfo;
import com.xxx.service.ISmiShopInfoService;

/**
 * 影院店铺信息action
 * 
 * @author author
 * @date 2016/02/16
 */
@Controller
public class ShopInfoAction {

	private Logger logger = Logger.getLogger(this.getClass());
	public String yzm="";
	@Autowired
	private ISmiShopInfoService smiShopInfoService;
	/**
	 * 用户登录
	 * 
	 * @ResponseBody 设置该参数，指定response的type为比如json或xml，本文采用json序列化方式传输数据
	 * 
	 * @param request
	 * @param response
	 * @param shopNo
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login.do")
	public @ResponseBody ShopInfoVO login(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession,String shopNo,String password,String yzmvalue) throws Exception {
		RedisHandler redis = RedisHandler.getInstance();
		System.out.println("yzmvalue是"+yzmvalue+"  yzm是"+redis.getString("yzm")+"------");
		SmiShopInfo shopInfo = smiShopInfoService.getShopByNoAndPwd(shopNo, password);
		ShopInfoVO vo = new ShopInfoVO();
		if(null==shopInfo||"".equals(shopInfo)){
			logger.warn(shopNo+"：登录失败，用户名或密码错误");
			vo.setStatus("0");
			vo.setNote("登录失败，用户名或密码错误!");
			return vo;
		}else if(!yzmvalue.equals(redis.getString("yzm"))){
			logger.warn(shopNo+"：登录失败，验证码错误");
			vo.setNote("登录失败，验证码错误!");
			vo.setStatus("1");
			return vo;	
		}else{
			vo.setId(shopInfo.getId());
			vo.setShopNo(shopInfo.getShopNo());
			vo.setShopName(shopInfo.getShopName());
			vo.setShopPassword("");//密码不传输到页面
			vo.setShopType(shopInfo.getShopType());
			vo.setNote("登录成功!");
			vo.setStatus("2");
			httpSession.setAttribute("SESSION_NAME_LOGIN_RESULT", shopInfo.getId());  
			logger.info(shopInfo.getShopName()+"["+shopNo+"]：登录成功");
			return vo;
		}
	}
	public static String getType(Object o){
		return o.getClass().toString();
		}
	
	
	/**
	 * 用户注册
	 * 
	 * @ResponseBody 设置该参数，指定response的type为比如json或xml，本文采用json序列化方式传输数据
	 * 
	 * @param request
	 * @param response
	 * @param shopNo
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/reguser.do")
	public @ResponseBody Map<String, Object>  reguser(HttpServletRequest request,HttpServletResponse response,String regusername,String regpassword,String regphone) throws Exception {
		System.out.println("---------"+regusername+"----"+regpassword+"----"+regphone);
		Map<String, Object> returnMap = new HashMap<String, Object>(); 
		if("".equals(regusername)&&"".equals(regpassword)&&"".equals(regphone)){
			returnMap.put("date","注册失败！");
			  logger.info("：注册失败！");
		   }else{
			   SmiShopInfo temp = smiShopInfoService.getByShopNO(regusername);
			   if(temp==null){
				   SmiShopInfo shopinfo =new SmiShopInfo();
				   shopinfo.setShopNo(regusername);
				   shopinfo.setShopPassword(regpassword);
				   shopinfo.setPhone(regphone);
				   smiShopInfoService.insertUser(shopinfo);
				   returnMap.put("date","注册成功！");
				   logger.info("：注册成功     账号为："+regusername);
			   }else{
				   returnMap.put("date","注册失败     门店号:"+regusername+"已存在！");
				   logger.info("注册失败     门店号:"+regusername+"已存在！");
			   }
		   }
	     return returnMap;
			
	}
	
	/**
	 * 获取短信验证码
	 * 
	 * @ResponseBody 设置该参数，指定response的type为比如json或xml，本文采用json序列化方式传输数据
	 * 
	 * @param request
	 * @param response
	 * @param currphone
	 * @return
	 * @throws Exception
	 */
	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	@RequestMapping(value = "/sendsms.do")
	public @ResponseBody Map<String, Object> sendsms(HttpServletRequest request,HttpServletResponse response,String currphone) throws Exception {
		System.out.print("---------"+currphone);
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url);

		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

		int mobile_code = (int)((Math.random()*9+1)*100000);

	    String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
	    System.out.println("验证码是："+mobile_code);

		NameValuePair[] data = {//提交短信
			    new NameValuePair("account", "C71294683"), //查看用户名请登录用户中心->验证码、通知短信->帐户及签名设置->APIID
			    new NameValuePair("password", "1e339900ab3e613be0b6c806bdb93569"),  //查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY
			    //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
			    new NameValuePair("mobile", currphone), 
			    new NameValuePair("content", content),
		};
		method.setRequestBody(data);

		try {
			client.executeMethod(method);
			
			String SubmitResult =method.getResponseBodyAsString();

			//System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();

			String code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");

			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);

			 if("2".equals(code)){
				System.out.println("短信提交成功");
			}

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Map<String, Object> returnMap = new HashMap<String, Object>();
		 
		 returnMap.put("currphone", currphone);
		returnMap.put("resYzm", mobile_code);
		  yzm=mobile_code+"";
		  RedisHandler redis = RedisHandler.getInstance();
		 redis.setString("yzm",yzm,60);//验证码60秒后失效
		return returnMap;
		
	}
	@RequestMapping(value = "/checkuser.do")
	public @ResponseBody Map<String, Object>  checkuser(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession,String findusername,String findphone) throws Exception {
		 Map<String, Object> returnMap = new HashMap<String, Object>();
		SmiShopInfo temp = smiShopInfoService.getByShopNO(findusername);
		   if(temp!=null&&temp.getPhone().equals(findphone)){
			   returnMap.put("status", true);
		   }else {
			   returnMap.put("status", false);
			   logger.info("账号或手机号错误");
		   }
	return returnMap;
	}
	
	@RequestMapping(value = "/finduser.do")
	public @ResponseBody Map<String, Object>  finduser(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession,String findusername,String findpassword) throws Exception {
		 Map<String, Object> returnMap = new HashMap<String, Object>();
			SmiShopInfo temp = new SmiShopInfo();
			temp.setShopNo(findusername);
			temp.setShopPassword(findpassword);
			int updateLine = smiShopInfoService.updateShopInfoByShopId(temp);
			if(updateLine==1 ){
				   returnMap.put("status", true);
				   logger.info("重置密成功"+updateLine);
			   }else {
				   returnMap.put("status", false);
				   logger.info("重置密码失败"+updateLine);
			   }
			
	return returnMap;
	}
	 
}
