<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width  ,initial-scale = 1.0, minimum-scale = float_value , maximum-scale = float_value ,user-scalable =no ,target- densitydpi = device-dpi" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<%@ include file="../common/include.jsp" %>
<title><%=webTitle %></title>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/WeixinApi.js"></script> 
<script type="text/javascript">
var source = "<c:out value="${source}"/>";
var weixinAppNo = "<c:out value="${weixinAppNo}"/>";
var freId = "<c:out value="${freId}"/>";
var desFreId = "<c:out value="${desFreId}"/>";
desFreId = desFreId.replace(/\+/g,"%2B");

var subFreNum = "<c:out value="${subFreNum}"/>";

var locationHref = window.location.href;
var hrefArray = locationHref.split(window.ctxPaths);
var ipStr = hrefArray[0];

var imgUrl = ipStr+"<%=ctxPath%>/resources/images/pic_qianghongbao_share.jpg";
var link = ipStr+"<%=ctxPath%>/qiangFlow.tv?freId="+desFreId+"&weixinAppNo="+weixinAppNo;


var wxData = {
        "imgUrl":imgUrl,
        "link":link,
        "desc":"山东移动流量汇给我"+subFreNum+"个流量红包，分你一个，快来抢吧！",
        "title":"抢流量红包"
    };
    
$(function(){
	$("#bg").hide();
	if(source=="1"){
		$("#reason").html("");
	}else if(source=="2"){
		$("#reason").html("成功关联手机号");
	}else if(source=="3"){
		$("#reason").html("成功开通流量包");
	}
})


WeixinApi.ready(function(Api){
		// 隐藏右上角popup菜单入口
		if(freId==null || freId ==""){
			Api.hideOptionMenu();
		}
	    // 隐藏浏览器下方的工具栏
	    //Api.hideToolbar();

	    // 获取网络状态
	    //Api.getNetworkType(function(network){})
	    // 拿到 network 以后，做任何你想做的事
		//微信分享的数据

		// 分享的回调
	    var wxCallbacks = {
	    	async : true,
	        // 分享操作开始之前
	        ready:function () {  	
	            // 你可以在这里对分享的数据进行重组
	        	var self = this;
	        	self.dataLoaded(wxData);
	        },

	        // 分享被用户自动取消
	        cancel:function (resp) {
	            // 你可以在你的页面上给用户一个小Tip，为什么要取消呢？
	        },

	        // 分享失败了
	        fail:function (resp) {
	            // 分享失败了，是不是可以告诉用户：不要紧，可能是网络问题，一会儿再试试？
	        },
	        // 分享成功
	        confirm:function (resp) {
		         var getShareType;
	        	 switch (resp.err_msg) {
	        	 	case 'share_weibo:ok': 
	        	 		getShareType=2;
	        	 		break;  
	        	 	case 'share_timeline:ok': 
	        	 		getShareType=3;
	        	 		break;    
	        	 }
	        	var jsonData = {'getShareType':getShareType,'weixinAppNo':weixinAppNo,'freId':freId};
	     		var message = $.toJSON(jsonData);
	        },
	        // 整个分享过程结束
	        all:function (resp) {
	        }
	    };
	    // 用户点开右上角popup菜单后，点击分享给好友，会执行下面这个代码
	    Api.shareToFriend(wxData, wxCallbacks);
	 	// 点击分享到腾讯微博，会执行下面这个代码
	    Api.shareToWeibo(wxData, wxCallbacks); 
	    // 点击分享到朋友圈，会执行下面这个代码
	    Api.shareToTimeline(wxData, wxCallbacks); 
	});
	

	function shareButtonF(){
		$("#bg").show();
	}
	
	function hideBgF(){
		$("#bg").hide();
	}
	
	function goToback(){
		window.location.href=ctxPaths+"/drawPage.tv?weixinAppNo="+weixinAppNo;
	}
</script>

<style type="text/css">
#bg{display:none;text-align:center;position:absolute;top:0%;left:0%;width:100%;height:100%;background-color:black;z-index:1001;-moz-opacity:0.7;opacity:.70;filter:alpha(opacity=70);}
</style>
</head>

<body class="bg-red">
  <div id="bg">
  	<div style="width:25%;height:30%;float:right;margin-right:0px;margin-top:0px;background:url(<%=ctxPath%>/resources/images/icon_arrow.png);background-repeat:no-repeat;background-size:100% 100%;-webkit-background-origin:content-box;background-origin:content-box;">&nbsp;</div>
  	<div style="margin-top:50%;color:white">点击右上角【分享到朋友圈】</div>
  	<div style="color:white">给好友发红包</div>
  	
  	<div style="margin-top:10%;color:white" id="hideBg" onclick="javascript:hideBgF();">我知道了</div>
  </div>
	
  
  <div>
    <img src="<%=ctxPath%>/resources/images/pic_huodehongbao.jpg" width="100%"/>
  </div>
  
  <div class="font-white font-block" id="reason">
  </div>
  
  <div class="font-yellow-big jiange-block">恭喜获得<c:out value="${subFreNum}"/>个流量红包</div>
  
  <div class="font-white jiange-block huode-layout">
    	可以分享给<c:out value="${subFreNum}"/>个小伙伴~分享后自己也能抢哦，可用于兑换免费流量
  </div>
  
  
  <div class="yellow-btn clearfix huode-layout" id="shareButton" onclick="javascript:shareButtonF();">给好友发红包</div>
  
  <div class="gray-btn clearfix huode-layout" onclick="javascript:goToback();">稍后再说</div>
  
  <%@ include file="../common/tmpTip.jsp" %>
  
</body>
</html>
