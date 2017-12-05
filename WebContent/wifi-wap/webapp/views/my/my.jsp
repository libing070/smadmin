<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	String is_headShow = request.getAttribute("IsHeadShow")==null?"1":request.getAttribute("IsHeadShow").toString();
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport" content="width=device-width, user-scalable=yes,minimum-scale=1.0,maximum-scale=2.0" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="/views/common/include.jsp"%>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/my.js"></script>

<script>
var is_headShow='<%= is_headShow%>';
$(document).ready(function (){   
	getMyUserInfo(1);
	addLigthBoxEvent();//添加图片放大事件
})

function getHeadShow(){

}
</script>
</head>
<body>
	<!--头部-->
	<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
	<!--头部结束-->

<!--我的信息-->
<div class="my-info-list jiange-top" onclick="window.location.href='<%=ctxPath%>/my/myDetailIndex'">
  <div class="my-thumbnail" id="myHeadShowId"><img src="<%=ctxPath%>/my/findAttach" class="lightbox" width="50" height="50" /></div>
  <div class="my-info-block">
    <div class="name" id="myName"><!--<span class="icon-male">男性用户</span>--></div>
    <div class="phone" ></div>
  </div>
</div>

<div class="my-jiange clearfix"></div>


<!--我的活动-->
<div class="my-info-list jiange-top" onclick="window.location.href='<%=ctxPath%>/my/myActivity'">
  <div class="my-small-icon"><img src="<%=ctxPath%>/resources/images/icon_my_activity.png"  width="50" /></div>
  <div class="my-info-block">
    <div class="title">我的活动</div>
  </div>
</div>

<!--我的活动-->
<div class="my-info-list jiange-top" onclick="window.location.href='<%=ctxPath%>/my/myHeadline'">
  <div class="my-small-icon"><img src="<%=ctxPath%>/resources/images/icon_my_bbs.png" width="50" /></div>
  <div class="my-info-block">
    <div class="title">头条</div>
  </div>
</div>
<div class="my-info-list my-info-list-notop" onclick="clickoldRead(1);">
  <div class="my-small-icon"><img src="<%=ctxPath%>/resources/images/icon_my_comments.png" width="50" /></div>
  <div class="my-info-block">
    <div class="title">评论</div>
  </div>
  <div  id="pingId"></div>
</div>
<div class="my-info-list my-info-list-notop" onclick="clickoldRead(2);">
  <div class="my-small-icon"><img src="<%=ctxPath%>/resources/images/icon_my_zan.png" width="50" /></div>
  <div class="my-info-block">
    <div class="title">赞</div>
  </div>
   <div  id="zanId"></div>
</div>
  



<!--<div class="qiangzhuo-more">头条发布审核中...</div>-->

	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->
</body>
</html>