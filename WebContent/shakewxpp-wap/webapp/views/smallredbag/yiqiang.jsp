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
var openId ="<c:out value="${openId}"/>";
var weixinAppNo = "<c:out value="${weixinAppNo}"/>";

function gotomy(){
  window.location.href = ctxPaths + "/sended.tv?weixinAppNo="+weixinAppNo;
}

</script>
</head>
  
<body class="bg-yellow">
  <div>
    <img src="<%=ctxPath%>/resources/images/pic_qiangguo.jpg" width="100%"/>
  </div>
  
  <div class="blank-jiange clearfix">
  </div>
  
  <div class="centertext font-block">
    您今天已经抢过一次免费红包啦！

  </div>
  
  <div class="centertext font-block">
    一天一次，明天再继续啦！

  </div>
  
  <div class="blank-jiange clearfix">
  </div>
  
  
  <div class="red-btn clearfix huode-layout" onclick="javascript:gotomy();">查看我的红包</div>
 
  
</body>

</html>
