package com.aspire.wifi.wap.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class getUV {
	public static void main(String[] args) {
		//	String ssss = "C:/Users/rt.wuhai/Desktop/access_url.2014-12-17.log";//access_url.2014-10-27.log" Result-10-25.txt ;
			String ssss = "C:/Users/rt.wuhai/Desktop/Result-11-07.txt";//access_url.2014-10-27.log" Result-10-25.txt ;
		File file  =new File(ssss);
		List<String> list = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		 if(file.isFile() && file.exists()){ //锟叫讹拷锟侥硷拷锟角凤拷锟斤拷锟�

             InputStreamReader read;
			try {
				FileWriter fileWriter=new FileWriter("C:/Users/rt.wuhai/Desktop/Result-11-07.txt", true); 
				read = new InputStreamReader(

				 new FileInputStream(file),"GBK");
			    BufferedReader bufferedReader = new BufferedReader(read);

	             String lineTxt = null;

	             while((lineTxt = bufferedReader.readLine()) != null){
	            	 String lineText2 = lineTxt.replace("|", ",,,");
	            	 //取客户端IP和时间
/*	            	 String lineText3 = getNString(5,",,,",lineText2);
	            	 String lineText6 = getNString(0,",,,",lineText2);
	            	 String lineText4 = getNString(0,",",lineText2);
	            	 
	            	 
	            	 if(!lineText6.contains("com.aspire.wifi.wap.util.ServiceDispatcher")){
	            		 
	            	 }else{	      
	            		 String lineText5 = lineText4.substring(0,13)+lineText3;
	            		 if(isExitIp(lineText5,list)){
		            		 fileWriter.write(lineText4+"|");
		            		 fileWriter.write(lineText3+"\n");
	            		 }
	            		 list.add(lineText5);
	            	 }
	             }*/
	            //取URL和时间和IP
//	            	 String lineText3 = getNString(7,",,,",lineText2);
//	            	 String lineText5 = getNString(5,",,,",lineText2);
//	            	 String lineText6 = getNString(0,",,,",lineText2);
//	            	 String lineText4 = getNString(0,",",lineText2);
//	            	 
//	            	 
//	            	 if(!lineText6.contains("com.aspire.wifi.wap.util.ServiceDispatcher")){
//	            		 
//	            	 }else{	      
//	            		 if(queryTouTiao(lineText2)){
//		            		 fileWriter.write(lineText4+"|");
//		            		 fileWriter.write(lineText5+"|");
//		            		 fileWriter.write(lineText3+"\n");
//	            		 }
//	            	 }
//是否为app
//	            	 
//	            	 if(queryFIndapp(lineText2)){
//	            		 list.add(lineTxt);
//            		 fileWriter.write(lineTxt+"\n");
//
//            		 }
////是否为视频
//	              	 if(queryFInd(lineText2)){
//	            		 list.add(lineTxt);
//	            		 fileWriter.write(lineTxt+"\n");
//
//            		 }
//查询头条的PV
//	            	 if(queryTouTiao(lineText2)){
//	            		 list.add(lineTxt);
//	            		 fileWriter.write(lineTxt+"\n");
//	            	 }
////	            	 

            	//筛选不同的手机号
            		 String  lineText4 = getNString(1,",,,",lineText2);
            		 if(isExitIp(lineText4,list)){
	            	//		fileWriter.write(lineTxt+"\n");
	            			list2.add(lineTxt);
	            		 }
	            		 list.add(lineText4);
	           //筛选登陆用户
//	            	 String lineText3 = getNString(1,",,,",lineText2);
//            		 if(StringUtil.isNotEmpty(lineText3)){
//            		//	 fileWriter.write(lineTxt+"\n");
//            			 list.add(lineTxt+"\n");
//            		 }
	            	 //刷选get请求的PV
//	            	 if(isGet(lineTxt)){
//	            		 fileWriter.write(lineTxt+"\n");
//	            	 }
//	     	            //筛选不相同的IP
//		            	 String lineText3 = getNString(2,",,,",lineText2);
//	            		 if(lineTxt.contains("verificationCode")||lineTxt.contains("pct")){
//		            		
//	            		 }else{
//	            			 if(isExitIp(lineText3,list)){
//		            //			fileWriter.write(lineTxt+"\n");
//		            			list2.add(lineTxt);
//		            		 }
//		            		 list.add(lineText3);
//	            		 }            	 
	            	 //是否是活动页面
//	            	 if(isHuodong(lineText2)){
//	            		 fileWriter.write(lineTxt+"\n");
//	            		 list.add(lineTxt);
//	            	 }
	            	 //是否是试用
//	            	 if(isShiYong(lineText2)){
//	            		 fileWriter.write(lineTxt+"\n");
//	            		 list.add(lineTxt);
//	            	 }
//            		 //是否是转发（登陆状态的）
//	            	 if(isZhuanFa(lineText2,"1")){
//	            		 fileWriter.write(lineTxt+"\n");
//	            		 list.add(lineTxt);
//	            	 }
//	            	 //是否是转发
//	            	 if(isZhuanFa(lineText2,"0")){
//	            		 fileWriter.write(lineTxt+"\n");
//	            		 list.add(lineTxt);
//	            	 }
//	            	 
	           //是否为“捐助慈善家”页面开数
//	            if(iscishang1(lineText2)){
//            	//	 fileWriter.write(lineTxt+"\n");
//            		 list.add(lineTxt);
//            	 }
	            //是否为“爱心权益”页打开数
//	            if(iscishang2(lineText2)){
//	          //  	fileWriter.write(lineTxt+"\n");
//	            	list.add(lineTxt);
//	            }
	            	 

            	 	             }
	             fileWriter.flush();   
	             fileWriter.close();
	             read.close();
	            System.out.println(list.size());
	            System.out.println("list2===:"+list2.size());
	            
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//锟斤拷锟角碉拷锟斤拷锟斤拷锟绞�

         
		 }
	}
	
	/**
	 * 锟斤拷取锟斤拷n锟斤拷指锟斤拷锟街凤拷n+1锟斤拷指锟斤拷锟街凤拷之锟斤拷锟斤拷址锟�
	 * param n锟斤拷示锟节硷拷锟斤拷
	 * param containString 锟斤拷示锟斤拷锟斤拷址锟�
	 * param initString 锟斤拷示原始锟街凤拷
	 * **/
	
	public static String getNString(int n,String containsString,String initString){
		String ss = initString;
		int f =containsString.length();
		Pattern p = Pattern.compile(containsString);
		Matcher m = p.matcher(ss);
		int c=0,
		index=-1;
		int index1=0;
		int index2=0;
		while(m.find()){  
			c++;  
			index=m.start();  
			if(c==n){
				index1=index+f;
			}
			if(c==n+1){
				index2=index;
			}
		}
		if(index2==0){
			index2=ss.length();
		}
		ss=ss.substring(index1,index2);
		return ss;
	}
	/**
	 * 锟叫讹拷锟角凤拷锟截革拷,锟角凤拷锟斤拷一锟斤拷时锟斤拷锟斤拷锟斤拷锟�
	 * **/
	public static boolean isExitIp(String lineText3,List<String> list){
		for(String lineText:list){
			if(lineText3.equals(lineText)){
				return false;
			}
			
		}
		return true;
	}
	/**
	 * 是否为头条
	 * **/
	public static boolean  queryTouTiao(String lineText2){
//		if(lineText2.contains("toutiao")){
//		
//			return true;
//		}else{
//			return false;
//		}
		 String lineText3 = getNString(4,",,,",lineText2);
		if(lineText3.equals("1")){
		
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 是否为视频
	 * **/
	public static boolean  queryFInd(String lineText2){
		String lineText3 = getNString(4,",,,",lineText2);
		String lineText4 = getNString(5,",,,",lineText2);
		if(lineText3.equals("3")&&(!lineText4.equals("12"))){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 是否为app
	 * **/
	public static boolean  queryFIndapp(String lineText2){
		String lineText3 = getNString(4,",,,",lineText2);
		String lineText4 = getNString(5,",,,",lineText2);
		if(lineText3.equals("3")&&(!lineText4.equals("11"))){
			return true;
		}else{
			return false;
		}
	}
	public static boolean isGet(String lineText3){
		if(lineText3.contains("find/findIndex")||lineText3.contains("findideoIndex")||lineText3.contains("findideoDetail")||lineText3.contains("findideoList")
		||lineText3.contains("findideoList")||lineText3.contains("find/app")||lineText3.contains("find/findAppDetail")||lineText3.contains("toutiao/toutiaoIndex")
		||lineText3.contains("toutiao/toutiaoDetailIndex")||lineText3.contains("toutiao/toutiaoEditIndex")||lineText3.contains("/index")||lineText3.contains("/getHomeInfo")
		||lineText3.contains("/login")||lineText3.contains("/personalData")||lineText3.contains("/yingxin_youhui")||lineText3.contains("/my/myIndex")||lineText3.contains("/my/myDetailIndex") 
		||lineText3.contains("/my/myActivity")||lineText3.contains("/my/myHeadline")||lineText3.contains("/my/myZan")||lineText3.contains("/my/myReplyIndex")		
		){
			
			return true;
		}else{
			return false;
		}
		
	}
	/**
	 * 是否为抢桌活动
	 * **/
	public static boolean isHuodong(String l){
		String lineText3 = getNString(4,",,,",l);
		String lineText4 = getNString(5,",,,",l);
		if(lineText3.equals("2")&&lineText4.equals("21")
			){
			return true;
		}else{
			return false;
		}
		
	} 
	/**
	 * 是否为试用
	 * **/
	public static boolean isShiYong(String l){
		String lineText3 = getNString(4,",,,",l);
		String lineText4 = getNString(5,",,,",l);
		if(lineText3.equals("2")&&lineText4.equals("22")
		){
			return true;
		}else{
			return false;
		}
		
	} 
	/**
	 * 是否为转发
	 * isLogin表示是否为登陆，1代表是
	 * **/
	public static boolean isZhuanFa(String l,String isLogin){
		String lineText3 = getNString(4,",,,",l);
		String lineText4 = getNString(5,",,,",l);
		
		String lineText5 = getNString(1,",,,",l);
		if(lineText3.equals("2")&&lineText4.equals("23")
		){
			if(isLogin.equals("1")){
				if(StringUtil.isBlank_new(lineText5)){
					return false;
				}else{
					return true;
				}
			}else{
				return true;
			}
		}else{
			return false;
		}
		
	} 
	
	public static boolean iscishang1(String l){
		String lineText3 = getNString(4,",,,",l);
		String lineText4 = getNString(5,",,,",l);
		if(lineText3.equals("2")&&lineText4.equals("24")){
			return true;
		}
		return false;
	}
	public static boolean iscishang2(String l){
		String lineText3 = getNString(4,",,,",l);
		String lineText4 = getNString(5,",,,",l);
		if(lineText3.equals("2")&&lineText4.equals("25")){
			return true;
		}
		return false;
	}
}
