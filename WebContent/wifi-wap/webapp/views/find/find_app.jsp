<%@ page language="java" import="com.aspire.wifi.wap.util.GetConfigFile;" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String appPath = GetConfigFile.getInstance().getProperties("appAccessPath");  
	String iconPath = GetConfigFile.getInstance().getProperties("iconAccessPath"); 
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="/views/common/include.jsp"%>
<link href="<%=ctxPath %>/resources/css/layout.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
var appPath='<%=appPath%>';
var iconPath='<%=iconPath%>';
var appTypeLoad="1";//app类型
//当前加载次数
var pageCurrent=1;
//每次加载数据量

var pageCount=6;
//最高星级
var highestStar = 5;
/***
**获取不同类型的app
*/
function getAppInfoByType(appTypes){
	if(appTypes=="1"){
		$("#appTypes"+appTypeLoad).attr("class","headline-sort-block headline-sort-normal");
		$("#appTypes1").attr("class","headline-sort-block headline-sort-active2");
	}else if(appTypes=="2"){
		$("#appTypes"+appTypeLoad).attr("class","headline-sort-block headline-sort-normal");
		$("#appTypes2").attr("class","headline-sort-block headline-sort-active2");
	}else if(appTypes=="3"){
		$("#appTypes"+appTypeLoad).attr("class","headline-sort-block headline-sort-normal");
		$("#appTypes3").attr("class","headline-sort-block headline-sort-active2");
	}else if(appTypes=="4"){
		$("#appTypes"+appTypeLoad).attr("class","headline-sort-block headline-sort-normal");
		$("#appTypes4").attr("class","headline-sort-block headline-sort-active2");
	}
	getAppInfo(0,pageCount,appTypes);
	appTypeLoad=appTypes;
	pageCurrent=1;
}
/***
**加载更多
**/

function getMore(){
		pageCurrent++;
	var pageCount1 = pageCount*pageCurrent;
		getAppInfo(0,pageCount1,appTypeLoad);	
}
/**
**搜索
**/
function queryAppInfoByName(){
	getAppInfo(0,pageCount,appTypeLoad);
}
$(document).ready(function(){
 getAppInfo(0,pageCount,1);
});
function getAppInfo(begin,times,appType){
  var appName1=$("#appName").val();
  	if(appName1=="大家都在搜"){
		appName1="";
	}
  var jsonData ={"begin":begin,"times":times,"appName":appName1,"appType":appType};
 
	jQuery.ajax
		({
			type: "POST",
			url: ctxPaths + "/find/showAppByLimit",
			dataType: "JSON",
			cache: false,
			data : jsonData,
			success: function(data){
				if(data.CODE=="TRUE"){
				  var list=data.list;
				  var htmlCotent="";
					
				  for(var i=0;i<list.length;i++){
				      htmlCotent+='<div class="faxian-list-appborder">';
				      htmlCotent+='<div class="faxian-app-logo"  onclick="window.location.href=\''+ctxPaths+'/find/findAppDetail?appId='+list[i].id+'\'"><img src="'+ctxPaths+'/find/findIcon?appId='+list[i].id+'&i=1" width="100%" height="100%" /></div>';
				      htmlCotent+="<div class='faxian-app-text'>";
				      htmlCotent+="<div class=''><span>"+list[i].appName+"</span></div>";
				       var appDesc = list[i].appDesc+"";
				      //显示简介的前十二个字字符
				      if(appDesc.length>12){
				      	appDesc=appDesc.substr(0,12)+"...";
				      }
				      htmlCotent+='<div class="faxian-app-info">'+appDesc+'</div>';
				     htmlCotent+="<div>";
					for(var j=0;j<parseInt(list[i].appStarLevel);j++){//显示实体星
						
						 htmlCotent+="<img src='"+ctxPaths+"/resources/images/star.png' width='12' />";
					}	
					for(var k=0;k<highestStar-parseInt(list[i].appStarLevel);k++){//显示虚体星
						
						 htmlCotent+="<img src='"+ctxPaths+"/resources/images/star2.png' width='12' />";
					}	
				     htmlCotent+="</div></div>";
			//	     htmlCotent+="<div class='faxian-app-info'>"+list[i].appDownloadTimes+"人下载|"+(list[i].appSize/1024).toFixed(2)+"M</div>";
		//		     htmlCotent+="</div>";
				     htmlCotent+='<div class="faxian-app-download">';
       				 htmlCotent+='<a onclick="downLoad(\''+ list[i].id +'\',\'' + list[i].appFileUrl + '\');" href="#"><img src="'+ctxPaths+'/resources/images/icon_download.png" width="25" /></a>';
      			     htmlCotent+='</div>';
	                 htmlCotent+="</div>";
				  }
			  $("#listAppId").html(htmlCotent);
				}else{
					alert(data.msg);
				}
			},
			error: function(){
				alert("网络异常");
			}
		});		
}
function downLoad(id,appFileUrl){
		jQuery.ajax
	({
		type: "post",
		url: ctxPaths + "/find/downloadFile",
		dataType: "json",
		data:{
			appId:id
		},
		cache: false,
		success: function(data){
			if(data.CODE=="FALSE"){
					phoneAlert("下载失败",3000);
			}else if(data.CODE=="TRUE"){
				window.location.href = appPath+appFileUrl;
			}
		},
		error: function(){
			phoneAlert("对不起网络异常！",3000);
		}
	});	

}
</script>
</head>


<body >
	<!--头部-->
	<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
	<!--头部结束-->
 
<!--头条-->
<div class="my-top-tool jiange-top whiteback">
  <span class="headline-back-btn" onclick="javascript:history.back();"><img src="<%=ctxPath %>/resources/images/icon_back.png"  align="absmiddle" /></span>
  <div class="headline-search"><span><input id="appName" type="text" value="大家都在搜" style="width:80%;color:#A9A9A9;"  onfocus="if (value =='大家都在搜'){value =''}"onblur="if (value ==''){value='大家都在搜'}"/></span><span><img src="<%=ctxPath %>/resources/images/icon_search.png" width="30"  align="absmiddle" onclick="queryAppInfoByName();" /></span></div>
</div>
 
<!--分类-->
<div class="headline-sort whiteback">
  <div class="headline-sort-block"><span><label class="font-black">手机必备</label></span></div>
  <div class="headline-sort-block headline-sort-active2" id="appTypes1" onclick="getAppInfoByType(1);"><span class="bagua">游戏</span></div>
  <div class="headline-sort-block headline-sort-normal"  id="appTypes3" onclick="getAppInfoByType(3);"><span class="guanshui">工具</span></div>
  <div class="headline-sort-block headline-sort-normal" id="appTypes2" onclick="getAppInfoByType(2);"><span class="xinwen">社交</span></div>
  <div class="headline-sort-block headline-sort-normal" id="appTypes4" onclick="getAppInfoByType(4);"><span class="qiuzhu">娱乐</span></div>
</div>
<!--分类结束-->
 <div id="listAppId"></div>


 
<div class="qiangzhuo-more" onclick="getMore();">点击查看更多</div>    
 

	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->
</body>
</html>
