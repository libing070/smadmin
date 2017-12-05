<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width  ,initial-scale = 1.0, minimum-scale = float_value , maximum-scale = float_value ,user-scalable =no ,target- densitydpi = device-dpi" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<%@ include file="../common/include.jsp" %>
<title><%=webTitle %></title>
<script type="text/javascript">
var fialType ="<c:out value="${fialType}"/>";
var timeRange ="<c:out value="${timeRange}"/>";
timeRange = timeRange.replace("-","、");
var tip2_content = "每天 "+timeRange+" 开抢,手快有，手慢无！";

$(function(){
	if(fialType=="2"){
		$("#tipImg").html('<img src="<%=ctxPath%>/resources/images/pic_laiwanle.jpg" width="100%"/>');	
		
		$("#tip1").html("上个整点的免费红包抢光了!");
		$("#tip2").html(tip2_content);
		$("#tip3").html("每人每天限抢一次哦~");
	}else if(fialType=="4"){
		$("#tipImg").html('<img src="<%=ctxPath%>/resources/images/pic_laizaole.jpg" width="100%"/>');	

		$("#tip1").html("亲，来早啦，红包还没派发呢~");
		$("#tip2").html(tip2_content);
		$("#tip3").html("每人每天限抢一次哦~");
	}
})
</script>
</head>
  
  
<body class="bg-yellow">
  <div id="tipImg">
  </div>
  
  <div class="blank-jiange clearfix">
  </div>
  
  <div class="centertext font-block" id="tip1">
  </div>
  
  <div class="centertext font-block" id="tip2">
  </div>
  
  <div class="blank-jiange clearfix">
  </div>
   
  <div class="font-red centertext font-block" id="tip3">
  </div>  
</body>

</html>
