<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport" content="width=device-width, user-scalable=yes,minimum-scale=1.0,maximum-scale=2.0" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="../common/include.jsp"%>
<script type="text/javascript">
	function huodongPage() {
		window.location.replace(ctxPaths + "/getHomeInfo?index=1");
	}
</script>
</head>
<body>
	<!--头部-->
	<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
	<!--头部结束-->

	<!--抢桌-->
	<div class="qiangzhuo-chenggong" id="qiang_success">

		<img src="<%=ctxPath%>/resources/images/qiangzhuo_fabu_chenggong.jpg"
			width="100%" />
		<div class="notes">注：你可以在“我的”－“我的活动”里查看详情</div>
		<div class="step">
			<span onclick="javascript:huodongPage();">还有别的活动，去看看</span><span><img
				src="<%=ctxPath%>/resources/images/icon_arrow01.png"
				align="absmiddle" /> </span>
		</div>
	</div>
	<!--抢桌结束-->

	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->
</body>
</html>