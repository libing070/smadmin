<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	int videoType = request.getAttribute("videoType")==null?0:Integer.parseInt(request.getAttribute("videoType").toString());
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
var videoType ='<%=videoType%>';
//每次加载数据量

var pageCount=6;
$(document).ready(function (){   
	getVideoInfo(0,pageCount);
})
//获取视频信息
function getVideoInfo(beginTimes,endTimes){
	var videoName = $("#videoName").val();
	if(videoName=="搜索"){
		videoName="";
	}
	var jsonData ={"beginTimes":beginTimes,"endTimes":endTimes,"videoName":videoName,"videoId":videoType,"flag":"4"};
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
				var html="";		
				var list1=0;
				var html1="";
				if(list.length>0){
					html1='<div class="faxian-sort-title whiteback">'+videoTypeByName(videoType)+'</div>';
					for(var i=0;i<list.length;i++){

							if(list1>0&&(list1%3)==0){
								html += '</div>';
								html += '<div class="faxian-video-list whiteback" >';
							}
							if(list1==0){
								html += '<div class="faxian-video-list whiteback" >';
							}						
								html += '<div class="faxian-video-block">';
								html+=' <div class="faxian-video-content"><a href="'+ctxPaths+'/find/videoDetail?videoId='+list[i].videoId+'"><img src="'+ctxPaths+'/find/findVideoIcon?videoId='+list[i].videoId+'" alt="" width="100%" border="0" /></a></div>';
								html+='<div class="faxian-video-content">'+list[i].videoName+'</div>';
								html+='<div class="faxian-video-content faxian-video-counter">播放：'+list[i].playCnt+'次</div>';
								html+=" </div>";
							list1++;
						
					}
				}
					$("#videoTypeId").html(html+"</div>");
					$("#videoTypeId1").html(html1);
			
				
			}else{
				phoneAlert("对不起网络异常！",3000);
			}
		},
		error: function(){
			phoneAlert("对不起网络异常！",3000);
		}
	});	
}
var typeCount3 =1;//记忆微播江湖类的加载数
function getMore(){
			typeCount3++;
			var pageCounts = typeCount3*pageCount;
			getVideoInfo(0,pageCounts);
}
/**
** 视频类型的名称
*/
function videoTypeByName(videoType){
	var videoTypeName="";
	if(videoType=="1"){
		videoTypeName="电影音乐";
	}else if(videoType=="2"){
		videoTypeName="娱乐八卦";
	}else if(videoType=="3"){
		videoTypeName="微博江湖";
	}else if(videoType=="4"){
		videoTypeName="其它";
	}else if(videoType=="5"){
		videoTypeName="电影音乐";
	}else if(videoType=="6"){
		videoTypeName="电影音乐";
	}else if(videoType=="7"){
		videoTypeName="电影音乐";
	}
	return videoTypeName;
}
</script>
</head>
<body>
	<!--头部-->
	<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
	<!--头部结束-->
<div class="my-top-tool jiange-top whiteback">
  <span class="headline-back-btn" onclick="javascript:history.back();"><img src="<%=ctxPath %>/resources/images/icon_back.png"  align="absmiddle" /></span>
  <div class="headline-search"><span>
  <input id="videoName" type="text" value="搜索" style="width:80%;color:#A9A9A9;"  onfocus="if (value =='搜索'){value =''}" onblur="if (value ==''){value='搜索'}"/></span><span><img src="<%=ctxPath %>/resources/images/icon_search.png" width="30"  align="absmiddle" onclick="getVideoInfo(0,3);"  /></span></div>
</div>


<div id="videoTypeId1"></div>
<div id="videoTypeId"></div>


<div class="qiangzhuo-more" onclick="getMore()">点击查看更多</div>



	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->


</body>
</html>