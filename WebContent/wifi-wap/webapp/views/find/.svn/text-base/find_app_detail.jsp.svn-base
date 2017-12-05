<%@ page language="java" import="com.aspire.wifi.wap.util.GetConfigFile;" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String appId = request.getAttribute("appId")==null?"":request.getAttribute("appId").toString();
		String appPath = GetConfigFile.getInstance().getProperties("appAccessPath");  
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="/views/common/include.jsp"%>
<link href="<%=ctxPath %>/resources/css/layout.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
var appId=<%=appId%>;
var appPath='<%=appPath%>';
//最高星级

var highestStar = 5;
$(document).ready(function(){
 getAppDetailInfo();
});
function getAppDetailInfo(){

  var jsonData ={"begin":appId,"times":1,"appName":'',"appType":''};
 
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
				      htmlCotent+='<div class="faxian-detail-appborder">'
				      htmlCotent+='<div class="faxian-detail-app-logo"><img src="'+ctxPaths+'/find/findIcon?appId='+list[i].id+'&i=1" width="100%" />';
					for(var j=0;j<parseInt(list[i].appStarLevel);j++){//显示实体星

						
						 htmlCotent+="<img src='"+ctxPaths+"/resources/images/star.png' width='12' />";
					}	
					for(var k=0;k<highestStar-parseInt(list[i].appStarLevel);k++){//显示虚体星

						
						 htmlCotent+="<img src='"+ctxPaths+"/resources/images/star2.png' width='12' />";
					}	
				     htmlCotent+="</div>";
				     htmlCotent+="<div class='faxian-detail-app-text'>";
				     htmlCotent+="<div class='faxian-detail-app-text-title'>"+list[i].appName+"</div>";
				     htmlCotent+='<div class="faxian-app-info">版本：'+list[i].appVersion+'</div>';
				     htmlCotent+='<div class="faxian-app-info">开发者：'+list[i].appDeveloper+'</div>';
				     htmlCotent+='<div class="faxian-app-info">所属类别：'+getTypeName(list[i].appType)+'</div>';
				     htmlCotent+='<div class="faxian-app-info">文件大小：'+(list[i].appSize/1024).toFixed(2)+'M</div>';
				     htmlCotent+='<div class="faxian-app-info">系统支持：'+list[i].appForSystem+' </div>';
				  
      			     htmlCotent+='</div>';
	                 htmlCotent+="</div>";
	                 htmlCotent+='<div class="btn-block whiteback"><span class="btn-blue" ><a onclick="downLoad(\''+ list[i].id +'\',\'' + list[i].appFileUrl + '\');" href="#">一键安装</a></span></div>';
				  }
			  $("#appDetailId").html(htmlCotent);
			  $("#yingyongjieshaoId").html(list[0].appDesc);
				}else{
					alert(data.msg);
				}
			},
			error: function(){
				alert("网络异常");
			}
		});		
}
function getTypeName(appType){

var typeName = "游戏";
	if(appType==1){
		 typeName = "游戏";
	}else if(appType==2){
		 typeName = "社交";
	}else if(appType==3){
		 typeName = "工具";
	}else if(appType==4){
		 typeName = "娱乐";
	}else if(appType==5){
		 typeName = "其它";
	}
	return typeName;
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
<body>
	<!--头部-->
	<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
	<!--头部结束-->
 



<!--头条-->
<div class="my-top-tool jiange-top whiteback">
  <span class="headline-back-btn" onclick="javascript:history.back();"><img src="<%=ctxPath %>/resources/images/icon_back.png"  align="absmiddle" /></span>
  <!--  <div class="headline-search"><span><input id="appName" type="text" value="大家都在搜" style="width:80%;" onfocus="if (value =='大家都在搜'){value =''}" onblur="if (value ==''){value='大家都在搜'}"/></span><span><img src="<%=ctxPath %>/resources/images/icon_search.png" width="30"  align="absmiddle" /></span></div>-->
</div>

<!--分类-->
<!--分类结束-->
<div id="appDetailId"></div>
<!--列表-->
 <!-- 
<div class="faxian-detail-appborder">
      <div class="faxian-detail-app-logo">
        <img src="images/app01.png" width="100%" />
        <div><img src="images/star.png" width="12" /><img src="images/star.png" width="12" /><img src="images/star.png" width="12" /><img src="images/star2.png" width="12" /><img src="images/star2.png" width="12" /></div>
      </div>
      <div class="faxian-detail-app-text">
        <div class="faxian-detail-app-text-title">优酷视频</div>
        
        <div class="faxian-app-info">版本：2.2.1</div>
        <div class="faxian-app-info">开发者：中国移动</div>
        <div class="faxian-app-info">所属类别：工具</div>
        <div class="faxian-app-info">文件大小：6.9MB</div>
        <div class="faxian-app-info">系统支持：Android 2.2及以下 及以上</div>
      </div>
</div>

<div class="btn-block whiteback"><span class="btn-blue">一键安装</span></div>--> 

<div class="faxian-detail-intro">
        <div class="faxian-detail-app-text-title">应用介绍</div>
        <div class="faxian-app-info" id="yingyongjieshaoId">
   <!--     <ol>
            <li>应用共分4个大类，游戏、社交、工具、娱乐</li>
            <li>每个大类，一页展示4个应用下载，点击可加载更多</li>
            <li>显示应用图标、名称、简要描述，星级推荐（满星五星）点击图标可直接下载，点击名称则查看详情</li>
            <li>搜索，根据名称或类别，给出搜索结果</li>
          </ol> -->   
        </div>
        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <div id="appPicId"></div>
        <div class="faxian-detail-jietu"><img src="<%=ctxPath %>/find/findIcon?appId=<%=appId %>&i=2" width="100%" height=150px/></div>
        <div class="faxian-detail-jietu"><img src="<%=ctxPath %>/find/findIcon?appId=<%=appId %>&i=3" width="100%" height=150px/></div>
        <div class="faxian-detail-jietu"><img src="<%=ctxPath %>/find/findIcon?appId=<%=appId %>&i=4" width="100%" height=150px/></div>
</div>
	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->

</body>
</html>