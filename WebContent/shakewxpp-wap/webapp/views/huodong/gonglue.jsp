<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width  ,initial-scale = 1.0, minimum-scale = float_value , maximum-scale = float_value ,user-scalable =no ,target- densitydpi = device-dpi" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<%@ include file="../common/include.jsp" %>
<title><%=webTitle %></title>
<style type="text/css">
.layout{
	min-width:320px;
	max-width:800px;
	margin:0 auto;
	overflow:hidden;
}


/*page*/
.hb_text_box{background:#eee;overflow:hidden;padding:0 0.5em;color:#515151;}
.hb_text_box h1{padding:0;margin:0;width:140px;}
.hb_text_box h1 img{width:100%;vertical-align:top;}
.hb_text_box ol{padding:0.5em 0 1em 0;margin:0 auto;list-style:none;width:100%;overflow:auto;background:url(<%=ctxPath%>/resources/images/hb_line_bg.jpg) repeat-y 1.8em top;}
.hb_text_box ol li{float:left;padding:0 0 0 6em;line-height:18px;position:relative;display:inline-block;word-break:break-all;font-size:0.8em;}
.hb_text_box ol li b{position:absolute;left:4.5em;top:3px;background:url(<%=ctxPath%>/resources/images/hb_li_ico.jpg) no-repeat left top;background-size:70%;width:16px;height:16px;display:inline-block;}
.hb_text_box ol li i{font-style:normal;color:#fff;background:#ea6503;padding:1px 5px;border-radius:20px;dont-size:1.2em;margin:0 3px;display:inline-block;}
.hb_text_box ol li a{color:#ea6503;text-decoration:none;font-size:1.2em;}
</style>


<script type="text/javascript">
var maxFreCount = "<c:out value="${maxFreCount}"/>";
var timeRange = "<c:out value="${timeRange}"/>";
var maxUserCount = "<c:out value="${maxUserCount}"/>";


var _times ="<c:out value="${times}"/>";
var times = Number(_times);
$(function(){
	if(times>1){
		$("#tip2").html("每天派发<i>"+maxFreCount+"</i>个免费流量红包，<i>"+timeRange+"</i>各发放一次，先到先得，抢光为止，每个微信号每天限抢得一次哦！");
	}else{
		$("#tip2").html("每天派发<i>"+maxFreCount+"</i>个免费流量红包，<i>"+timeRange+"</i>开始发放，先到先得，抢光为止，每个微信号每天限抢得一次哦！");
	}
})
</script>
</head>
<body>
    <div class="layout">
    	<div><img src="<%=ctxPath%>/resources/images/pic_gonglue01.jpg" alt="" style="width:100%;" /></div>
    	<div class="hb_text_box">
        	<h1><img src="<%=ctxPath%>/resources/images/hb_title_01.jpg" alt="" /></h1>
            <ol>
            	<li><b></b><span id="tip1">关注流量汇微信号,首次成功关联手机号,即可获赠<i>${bindSubFreCountPeruser}</i>个流量红包,更能比未关联时抢到更多的流量币哦~</span></li>
                <li><b></b><span id="tip2"></span></li>
                <li><b></b><span id="tip3">通过流量汇微信开通流量包,马上获得<i>${flowpkgSubFreCountPeruser}</i>个哟！</span></li>
                <li><b></b>亲，赶快点击左上角【返回】，再点击【流量红包】-【免费发红包】，疯抢免费红包啦~！</li>
            </ol>
            <h1><img src="<%=ctxPath%>/resources/images/hb_title_02.jpg" alt="" /></h1>
            <ol>
            	<li><b></b>抢到或获赠地流量红包还可以分享到朋友圈呢，流量大侠快去“劫富济贫”吧~</li>
            </ol>
            <h1><img src="<%=ctxPath%>/resources/images/hb_title_03.jpg" alt="" /></h1>
            <ol>
            	<li><b></b>大侠，抢到得红包可以通过流量汇网站<a href="#">（http://shake.sd.chinamobile.com/）</a>或流量汇客户端兑换省内流量，快去兑换吧！！</li>
            </ol>
        </div>
        <div><img src="<%=ctxPath%>/resources/images/pic_gonglue03.gif" alt="" style="width:100%;" /></div>
    </div>
</body>
</html>
