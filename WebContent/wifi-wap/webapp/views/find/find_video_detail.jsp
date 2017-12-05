<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	int videoId = request.getAttribute("videoId")==null?0:Integer.parseInt(request.getAttribute("videoId").toString());
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
	var videoId = <%=videoId %>;
$(document).ready(function (){   
	getVideoDetailInfo();
	
})
var videoJiaZaiTimes =1;
var jiazaiTimes=3;
function getVideoId(){//点击第一次播放时增加加载次数
if(videoJiaZaiTimes==1){
jQuery.ajax
	({
		type: "post",
		url: ctxPaths + "/find/addVideoPlay",
		dataType: "json",
		data:{
			videoId:videoId	
			},
		cache: false,
		success: function(data){
			if(data.CODE=="FALSE"){
				alert("新增视频播放详细信息失败");
			}else if(data.CODE=="TRUE"){
				
				
			}
		},
		error: function(){
			alert("对不起网络异常！");
		}
	});	
	
}
videoJiaZaiTimes++;
}
	//获取视频信息
function getVideoDetailInfo(){
$("body").showLoading();
	var jsonData ={"beginTimes":0,"endTimes":jiazaiTimes,"videoName":'',"videoId":videoId,"flag":"3"};
	jQuery.ajax
	({
		type: "post",
		url: ctxPaths + "/find/getVideoInfo",
		dataType: "json",
		data : jsonData,
		cache: false,
		success: function(data){
			if(data.CODE=="FALSE"){
				alert("获取视频播放详细信息失败");
			}else if(data.CODE=="TRUE"){
				//去除遮罩
				$("body").hideLoading();
				var list  = data.videoInfoList;
				var list2  = data.videoInfoList2;
				var html="";
				var html2="";
				if(list.length>0){
				//	for(var i=0;i<list.length;i++){
						html+= ' <div class="faxian-video-play">'+list[0].videoName+'</div>';
						html+= '<div class="faxian-video-content" onclick="getVideoId()"><video id="'+list[0].videoId+'" class="media" width="100%" poster="'+ctxPaths+'/find/findVideoIcon?videoId='+list[0].videoId+'"  src="<c:out value='${videoPath}'/>'+list[0].resourcePath+'" controls> ';
						html+='此视频不能播放</video> </div>';
						//html+='<div class="faxian-video-content">'+list[i].videoName+'</div>';
						html+='<div class="faxian-video-play"><label class="times">播放次数：'+list[0].playCnt+'次</label></div>';					
						
				//	}
				}
	
				if(list2.length>0){
					for(var i=0;i<list2.length;i++){
						html2+= '<div class="faxian-video-block">';
						html2+= '<div class="faxian-video-content" ><a href="'+ctxPaths+'/find/videoDetail?videoId='+list2[i].videoId+'"><img src="'+ctxPaths+'/find/findVideoIcon?videoId='+list2[i].videoId+'" width="100%" height="100"/></a></div>';
						html2+='<div class="faxian-video-content">'+list2[i].videoName+'</div>';
						//html+='<div class="faxian-video-content">'+list[i].videoName+'</div>';
						html2+='<div class="faxian-video-content faxian-video-counter">播放：'+list2[i].playCnt+'次</div>';					
						html2+='</div>';
					}
				}
				
				$("#videoDetailId").html(html);
				$("#videoDeatilgetId").html(html2);
			}
		},
		error: function(){
		//去除遮罩
		$("body").hideLoading();
			alert("对不起网络异常！");
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
<div class="my-top-tool jiange-top">
  <span class="headline-back-btn" onclick="javascript:history.back();"><img src="<%=ctxPath %>/resources/images/icon_back.png"  align="absmiddle" /></span>
</div>


<div class="jiange-top" id="videoDetailId">

</div>


<!--资讯-->
<div class="headline-sort whiteback">
  <div class="faxian-title-video">猜你喜欢</div>
  <div class="faxian-title-more font-orange"  onclick="window.location.href='<%=ctxPath %>/find/videoIndex'">更多</div>
</div>
<div class="faxian-video-list whiteback" id="videoDeatilgetId">

</div>



	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->

</body>
</html>