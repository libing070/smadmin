<%@ page language="java" import="java.util.*,com.aspire.wifi.wap.util.*" pageEncoding="utf-8"%>
<%
String appPath=GetConfigFile.getInstance().getProperties("appAccessPath");
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
var appPath ='<%=appPath%>';
var highestStar = 5;//最高星级为5级
$(document).ready(function (){   
	getVideoIndexInfo(0,3);
	getAppIndexInfo();
	
})

//获取视频信息
function getVideoIndexInfo(beginTimes,endTimes){
var videoName="";
var videoId=0;
var flag="1";
	var jsonData ={"beginTimes":beginTimes,"endTimes":endTimes,"videoName":videoName,"videoId":videoId,"flag":flag};
	jQuery.ajax
	({
		type: "post",
		url: ctxPaths + "/find/getVideoInfo",
		dataType: "json",
		data : jsonData,
		cache: false,
		success: function(data){
			if(data.CODE=="FALSE"){
				phoneAlert(data.msg,3000);
			}else if(data.CODE=="TRUE"){
				var list  = data.videoInfoList;
				var list3  = data.videoInfoList3;
				var html="";		
				var html3='<ul class="scroll_wrap">';		
				if(list.length>0){
					for(var i=0;i<list.length;i++){
						html += '<div class="faxian-video-block" ><div class="faxian-video-content">';
						html+=' <div class="faxian-video-content"><a href="'+ctxPaths+'/find/videoDetail?videoId='+list[i].videoId+'"><img src="'+ctxPaths+'/find/findVideoIcon?videoId='+list[i].videoId+'" alt="" width="100%"  /></a></div>';
						html+='<div class="faxian-video-content">'+list[i].videoName+'</div>';
						html+='<div class="faxian-video-content faxian-video-counter">播放：'+list[i].playCnt+'次</div>';
						html+=" </div></div>";
				
					}
				
				$("#videolistId").html(html);
				
				}

				if(list3.length>0){
					for(var j=0;j<list3.length;j++){
						html3+='<li><a href="'+ctxPaths+'/find/videoDetail?videoId='+list3[j].videoId+'"><img  src="'+ctxPaths+'/find/findVideoIcon?videoId='+list3[j].videoId+'&isTopPic=1" width="100%" /></a></li>';
					}
				}
				html3 +="</ul>";
				$("#scroll_img").html(html3);
				getload();
			}
		},
	error: function(){
			alert("对不起网络异常！");
	}
	});	
}
//获取应用下载信息  onclick="window.location.href=\''+ctxPaths+'/find/downloadFile?fileName='+list[i].appName+'\'"
function getAppIndexInfo(){

  var jsonData ={"begin":0,"times":4,"appName":'',"appType":''};
 
	jQuery.ajax
		({
			type: "POST",
			url: ctxPaths + "/find/showAppByLimit?isIndex=1",
			dataType: "JSON",
			cache: false,
			data : jsonData,
			success: function(data){
				if(data.CODE=="TRUE"){
				  var list=data.list;
				  var topList=data.topList;
				  var htmlCotent="";
				  var htmlCotent1="";
				  var htmlCotent2="";
				  var topListHtml='<ul class="scroll_wrap">';	
				  for(var i=0;i<list.length;i++){
				   //   htmlCotent+='<div class="faxian-index-appblock">';
				      htmlCotent+='<div class="faxian-index-appborder">';
				      htmlCotent+='<div class="faxian-app-logo" onclick="window.location.href=\''+ctxPaths+'/find/findAppDetail?appId='+list[i].id+'\'"><img src="'+ctxPaths+'/find/findIcon?appId='+list[i].id+'&i=1" width="100%" height="100%"/></div>';
				      htmlCotent+='<div class="faxian-app-text">';
				      htmlCotent+="<div >"+list[i].appName+"</div>";
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
				   //  htmlCotent+="<div class='faxian-app-info'>"+list[i].appDownloadTimes+"人下载|"+(list[i].appSize/1024).toFixed(2)+"M</div>";
				   //  htmlCotent+="</div>";	
				 
				     htmlCotent+='<div class="faxian-app-download">';
				     	
       				 htmlCotent+='<a onclick="downLoad(\''+ list[i].id +'\',\'' + list[i].appFileUrl + '\');" href="#"><img src="'+ctxPaths+'/resources/images/icon_download.png" width="25" /></a>';
	                 htmlCotent+="</div>";
	               //  htmlCotent+="</div>";
	                 htmlCotent+="</div>";
	                 if(list.length>2){
	                 	htmlCotent1 += htmlCotent;
	                 	htmlCotent="";
	                 	if(i>2){
	                 		htmlCotent2 += htmlCotent;
	                 		htmlCotent="";
	                 	}
	                 }else{
	                 	htmlCotent1 += htmlCotent;
	                 	htmlCotent="";
	                 }
				  }
			  $("#applistId1").html(htmlCotent1);
			  
			  $("#applistId2").html(htmlCotent2);
			  for(var j=0;j<topList.length;j++)	{
			  	topListHtml+='<li><a href="'+ctxPaths+'/find/findAppDetail?appId='+topList[j].id+'"><img src="'+ctxPaths+'/find/findIcon?appId='+topList[j].id+'&i=5" width="100%" /></a></li>';			  	
			  }
			  topListHtml+="</ul>";
			  $("#scroll_img_app").html(topListHtml);
			  getload2();
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
<body onload="">
	<!--头部-->
	<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
	<!--头部结束-->
 
 
<!--头条-->
<!--分类-->
<!--分类结束-->
 
<!--视频-->
<div class="headline-sort whiteback">
  <div class="faxian-title-video">热门视频</div>
  <div class="faxian-title-more font-orange" onclick="window.location.href='<%=ctxPath %>/find/videoIndex'">更多</div>
</div>
 
<div class="videoad-pic">
    <!--scroll-->
	  <div class="scroll relative">
	    <div class="scroll_box" id="scroll_img">
	    </div>
      <span class="scroll_position_bg opacity6"></span>
      <ul class="scroll_position" id='scroll_position'>
          <li class="on"><a href="javascript:void(0);">1</a></li>
          <li><a href="javascript:void(0);">2</a></li>
          <li><a href="javascript:void(0);">3</a></li>
      </ul>
	  </div>
</div>
<!--scroll-->
 
<script> 
function getload(){
var slider = Swipe(document.getElementById("scroll_img"), {
	auto: 4000,
	continuous: true,
	callback: function(pos) {
		var i = bullets.length;
		while (i--) {
			bullets[i].className = ' ';
		}
		bullets[pos].className = 'on';
	}
});
var bullets = document.getElementById('scroll_position').getElementsByTagName('li');
$(function(){
	$('.scroll_position_bg').css({
		width:$('#scroll_position').width()
	});
});
}
function getload2(){
var slider = Swipe(document.getElementById("scroll_img_app"), {
	auto: 4000,
	continuous: true,
	callback: function(pos) {
		var i = bullets.length;
		while (i--) {
			bullets[i].className = ' ';
		}
		bullets[pos].className = 'on';
	}
});
var bullets = document.getElementById('scroll_positiona_app').getElementsByTagName('li');
$(function(){
	$('.scroll_position_bg').css({
		width:$('#scroll_positiona_app').width()
	});
});
}
</script>
 
<div class="faxian-video-list whiteback" id="videolistId">

</div>
 
<!--应用-->
<div class="faxian-title-border whiteback clearfix jiange-top">
  <div class="faxian-title-app">手机必备</div>
  <div class="faxian-title-more font-blue2" onclick="window.location.href='<%=ctxPath %>/find/app'">更多</div>
</div>
 
  <!--   <div class="videoad-pic clearfix">
      <a href="#"><img src="<%=ctxPath %>/resources/images/weichat.jpg" width="100%" /></a>
    </div> -->
 <div class="videoad-pic">
    <!--scroll-->
	  <div class="scroll relative">
	    <div class="scroll_box" id="scroll_img_app">
	    </div>
      <span class="scroll_position_bg opacity6"></span>
      <ul class="scroll_position" id='scroll_positiona_app'>
          <li class="on"><a href="javascript:void(0);">1</a></li>
          <li><a href="javascript:void(0);">2</a></li>
          <li><a href="javascript:void(0);">3</a></li>
      </ul>
	  </div>
</div>

<div class="faxian-index-applist whiteback" id="applistId1">
  
</div>
<div class="faxian-index-applist whiteback" id="applistId2">
  
</div>

 
<!--网站-->
<!--<div class="faxian-title-border whiteback clearfix jiange-top">
  <div class="faxian-title-site">网站</div>
  <div class="faxian-title-more font-gray" onclick="window.location.href='faxian_site.html'">更多</div>
</div>
 
<div class="faxian-site-index whiteback">
  <div class="faxian-site-indexblock">
    <label><img src="images/logo_site01.jpg" width="100%"  /></label>
    <label>百度</label>
  </div>
  <div class="faxian-site-indexblock">
    <label><img src="images/logo_site01.jpg" width="100%"  /></label>
    <label>百度</label>
  </div>
  <div class="faxian-site-indexblock">
    <label><img src="images/logo_site01.jpg" width="100%"  /></label>
    <label>百度</label>
  </div>
</div>-->
 
 

	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->

</body>
</html>