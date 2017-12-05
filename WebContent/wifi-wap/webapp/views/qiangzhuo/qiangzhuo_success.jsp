<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport" content="width=device-width, user-scalable=yes,minimum-scale=1.0,maximum-scale=2.0" id="viewport" />

<title>中国移动免费WIFI上网</title>
<%@ include file="../common/include.jsp"%>
<link href="<%=ctxPath%>/resources/css/css.css" rel="stylesheet" />
<script src="<%=ctxPath%>/resources/js/jquery-2.0.2.min.js"
	language="javascript" type="text/javascript"></script>
</head>
<body>
	<!--头部-->
	<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
	<!--头部结束-->

	<!--抢桌-->
	<div class="qiangzhuo-chenggong" id="qiang_success">
		<div class="top">
			<img src="<%=ctxPath%>/resources/images/qiangzhuo_chenggong.jpg"
				width="100%" />
		</div>
		<div class="notes">点击登录，并完善个人资料，就可以创建你的抢桌了！</div>
		<div class="step">
			<span onclick="window.location.href='login.html'">1、登录</span><span><img
				src="<%=ctxPath%>/resources/images/icon_arrow01.png"
				align="absmiddle" />
			</span><span>2、完善个人资料</span>
		</div>
	</div>
	<!--抢桌结束-->



	<div style="height:80px;" class="clearfix"></div>
	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->

</body>
</html>