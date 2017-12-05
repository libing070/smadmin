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
<script type="text/javascript">
var maxFreCount = "<c:out value="${maxFreCount}"/>";
var timeRange = "<c:out value="${timeRange}"/>";
var maxUserCount = "<c:out value="${maxUserCount}"/>";


var _times ="<c:out value="${times}"/>";
var times = Number(_times);
$(function(){
	if(times>1){
		$("#tip1").html("2、流量汇微信每天派发"+maxFreCount+"个免费流量红包，分"+_times+"次派发，"+timeRange+"各派发一次，先到先得，抢光为止，每个微信号每天限得"+maxUserCount+"次。");
	}else{
		$("#tip1").html("2、流量汇微信每天派发"+maxFreCount+"个免费流量红包，"+timeRange+"开始派发，先到先得，抢光为止，每个微信号每天限得"+maxUserCount+"次。");
	}
})
</script>
</head>
  
<body>
<div>
  <img src="<%=ctxPath%>/resources/images/pic_head.jpg" width="100%" />
  </div>
  
  <div class="guize">
    <div class="guize-title">活动说明</div>
    <div class="guize-conner"></div>
  </div>
  
  <div class="guize">
    <div class="guize-content">   
      <div class="font-red">如何获得流量红包？</div>
      <div class="xiahuaxian">
        <p>1、关注流量汇微信号，首次成功关联手机号，即可获赠${bindSubFreCountPeruser}个流量红包，更能比未关联时抢到更多流量币哦~</p>
        <p id="tip1"></p>	
        <p>3、在流量汇微信“我要免费赚红包”或“精彩活动-流量特惠”区开通流量包，即可获赠${flowpkgSubFreCountPeruser}个流量红包。</p>
      </div>
    </div>
  </div>
  
  <div class="guize">
    <div class="guize-content">
      <div class="font-red">流量红包怎么用？</div>
      <div class="xiahuaxian">
        <p>1、流量红包里有不同额度的流量币，可以通过流量汇网站（http://shake.sd.chinamobile.com）兑换山东省内免费流量（1流量币兑换1M）。</p>
        <p>2、将抢到或获赠的流量红包分享到朋友圈或发送给朋友，即可与好友一起抢红包，抢到多少流量币看你人品哦！</p>
        <p>3、红包有效期${freExpireDays}天，${freExpireDays}天后未被抢完的红包逾期失效</p>
        <p>4、抢到的流量币，需在抢到后的${subFreExchangeDays}天内至流量汇wap门户（http://shake.sd.chinamobile.com）或客户端领取，如未领取，逾期失效。</p>
      </div>  
    </div>
  </div>
  
  <div class="guize">
    <div class="guize-content">
      
      <div class="font-red">温馨提示</div>
      <div class="xiahuaxian">
        <p>亲，流量红包活动当前内测中，可能会出现各种不稳定或错误情况，如您在参与活动时遇到问题，欢迎微信留言咨询或反馈，或者您也可以等待我们完善并推出正式版本后再行参与，给您带来的不便敬请谅解哦^_^ </p>
      </div>
      
    </div>
  </div>
  </body>
</html>
