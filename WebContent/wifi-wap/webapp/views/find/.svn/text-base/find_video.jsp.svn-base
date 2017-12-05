<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="/views/common/include.jsp"%>
<link href="<%=ctxPath %>/resources/css/layout.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
//当前加载次数
var pageCurrent=1;
//每次加载数据量

var pageCount=3;
$(document).ready(function (){   
	getVideoInfo(0,pageCount,0);
})
/** 
  function record(){
  		var m = document.getElementsByClassName('media');//获取所有的视频
  		for(var i=0;i<m.length;i++){
				var Media = m[i];
				var videoInfoId = Media.id;
				var lastTimes = Media.currentTime;//获取当前播放的秒数

  			if(parseInt(lastTimes)>0){
				insertVideoPlay(lastTimes,videoInfoId);//将播放的视频插入视频播放信息表

			}
  		}
   //  var Media = document.getElementById("media");  
    
     var totalTime = Media.duration;//获取视频播放时间
   
  }
   
**/

//获取视频信息
function getVideoInfo(beginTimes,endTimes,type){
	var videoName = $("#videoName").val();
	if(videoName=="搜索"){
		videoName="";
	}
	var jsonData ={"beginTimes":beginTimes,"endTimes":endTimes,"videoName":videoName,"videoId":0,"flag":"2"};
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
				var zixunhtml="";
				var yulehtml="";
				var gaoxiaohtml="";
				var list1=0;
				var list2=0;
				var list3=0;
				if(list.length>0){
					for(var i=0;i<list.length;i++){
				
					
						if(list[i].videoType==3){
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
					
							zixunhtml+=html;
							html="";
							list1++;
						}else if(list[i].videoType==1){
							if(list2%3==0){
								html += '</div>';
								html += '<div class="faxian-video-list whiteback">';
							}
							if(list2==0){
								html += '<div class="faxian-video-list whiteback" >';
							}
							html += '<div class="faxian-video-block">';
							html+=' <div class="faxian-video-content"><a href="'+ctxPaths+'/find/videoDetail?videoId='+list[i].videoId+'"><img src="'+ctxPaths+'/find/findVideoIcon?videoId='+list[i].videoId+'" alt="" width="100%" border="0" /></a></div>';
							html+='<div class="faxian-video-content">'+list[i].videoName+'</div>';
							html+='<div class="faxian-video-content faxian-video-counter">播放：'+list[i].playCnt+'次</div>';
							html+=" </div>";
							yulehtml+=html;
				
							html="";
							list2++;
						}else if(list[i].videoType==2){
							if(list3%3==0){
								html += '</div>';
								html += '<div class="faxian-video-list whiteback" >';
							}
							if(list3==0){
								html += '<div class="faxian-video-list whiteback" >';
							}
							html += '<div class="faxian-video-block">';
							html+=' <div class="faxian-video-content"><a href="'+ctxPaths+'/find/videoDetail?videoId='+list[i].videoId+'"><img src="'+ctxPaths+'/find/findVideoIcon?videoId='+list[i].videoId+'" alt="" width="100%" border="0" /></a></div>';
							html+='<div class="faxian-video-content">'+list[i].videoName+'</div>';
							html+='<div class="faxian-video-content faxian-video-counter">播放：'+list[i].playCnt+'次</div>';
							html+=" </div>";
							gaoxiaohtml+=html;

							html="";
							list3++;
						}
					}
				}
				if(type==3){
					$("#zixunVideoId").html(zixunhtml+"</div>");
				}else if(type==1){
					$("#yuleVideoId").html(yulehtml+"</div>");
				}else if(type==2){
					$("#gaoxiaoVideoId").html(gaoxiaohtml+"</div>");
				}else if(type==0){
					$("#zixunVideoId").html(zixunhtml);
					$("#yuleVideoId").html(yulehtml);
					$("#gaoxiaoVideoId").html(gaoxiaohtml);
				}
				
				if(list3.length>0){
					for(var j=0;j<list3.length;j++){
						html3+='<li><a href="'+ctxPaths+'/find/videoDetail?videoId='+list3[j].videoId+'"><img  src="'+ctxPaths+'/find/findVideoIcon?videoId='+list3[j].videoId+'&isTopPic=1" width="100%" /></a></li>';
					}
				}
				html3 +="</ul>";
				$("#scroll_img").html(html3);
				
				getload();
			}else{
				phoneAlert("对不起网络异常！",3000);
			}
		},
		error: function(){
			phoneAlert("对不起网络异常！",3000);
		}
	});	
}
//分页
var videoTypes ="";//记忆分类
var typeCount3 =1;//记忆微播江湖类的加载数
var typeCount1 =1;//记忆电影音乐类的加载数
var typeCount2=1;//记忆娱乐八卦类的加载数

function getPageVideoInfo(type){
		window.location.replace(ctxPaths+"/find/videoList?videoType="+type);
	//	if(type==3){
	//		typeCount3++;
	//		var pageCounts = typeCount3*pageCount;
	//		getVideoInfo(0,pageCounts,type);
	//	}else if(type==1){
	//		typeCount1++;
	//		var pageCounts = typeCount1*pageCount;
	//		getVideoInfo(0,pageCounts,type);
	//	}else if(type==2){
	//		typeCount2++;
	//		var pageCounts = typeCount2*pageCount;
	//		getVideoInfo(0,pageCounts,type);
	//	}
	
}


//获取视频播放信息(没播放一次增加一条记录)
function insertVideoPlay(lastTimes,videoInfoId){
	//var jsonData ={"lastTimes":lastTimes;"videoInfoId":videoInfoId};
	jQuery.ajax
	({
		type: "post",
		url: ctxPaths + "/find/addVideoPlay",
		dataType: "json",
		data : {
				lastTimes:lastTimes,
				videoInfoId:videoInfoId
			 	},
		cache: false,
		success: function(data){
			if(data.CODE=="FALSE"){
				phoneAlert(data.msg,3000);
			}else if(data.CODE=="TRUE"){
				phoneAlert(data.msg,3000);
			}else{
				phoneAlert("对不起网络异常！",3000);
			}
		},
		error: function(){
			phoneAlert("对不起网络异常！",3000);
		}
	});	
}
	
function videoLogout(){
	record();
	 window.location.replace(ctxPaths+"/logout");
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

<!--头条-->
<div class="my-top-tool jiange-top whiteback">
  <span class="headline-back-btn" onclick="javascript:history.back();"><img src="<%=ctxPath %>/resources/images/icon_back.png"  align="absmiddle" /></span>
  <div class="headline-search"><span>
  <input id="videoName" type="text" value="搜索" style="width:80%;color:#A9A9A9;"  onfocus="if (value =='搜索'){value =''}" onblur="if (value ==''){value='搜索'}"/></span><span><img src="<%=ctxPath %>/resources/images/icon_search.png" width="30"  align="absmiddle" onclick="getVideoInfo(0,3,0);"  /></span></div>
</div>
<!--分类-->
<!--<div class="headline-sort whiteback">
  <div class="headline-sort-block headline-sort-normal" onclick="window.location.href='faxian.html'"><span class="bagua">首页</span></div>
  <div class="headline-sort-block headline-sort-active" onclick="window.location.href='faxian_video.html'"><span class="guanshui">视频</span></div>
  <div class="headline-sort-block headline-sort-normal" onclick="window.location.href='faxian_app.html'"><span class="xinwen">应用</span></div>
  <div class="headline-sort-block headline-sort-normal" onclick="window.location.href='faxian_site.html'"><span class="qiuzhu">网站</span></div>
</div>-->
<!--分类结束-->
<div class="faxian-sort-title whiteback">热门视频</div>
<!--分类结束-->

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
var slider = Swipe(document.getElementById('scroll_img'), {
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
</script>

<!--微播江湖-->
<div class="headline-sort whiteback">
  <div class="faxian-title-video">微播江湖</div>
  <div class="faxian-title-more font-orange" onclick="getPageVideoInfo(3)">更多</div>
</div>
<div id="zixunVideoId">

</div>

<!--电影音乐-->
<div class="headline-sort whiteback">
  <div class="faxian-title-video">电影音乐</div>
  <div class="faxian-title-more font-orange" onclick="getPageVideoInfo(1)">更多</div>
</div>
<div  id="yuleVideoId">
 
</div>

<!--娱乐八卦-->
<div class="headline-sort whiteback">
  <div class="faxian-title-video">娱乐八卦</div>
  <div class="faxian-title-more font-orange"  onclick="getPageVideoInfo(2)">更多</div>
</div>
<div id="gaoxiaoVideoId">
 
</div>

	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->
</body>
</html>
