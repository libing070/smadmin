<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport" content="width=device-width, user-scalable=yes,minimum-scale=1.0,maximum-scale=2.0" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="/views/common/include.jsp"%>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/my.js"></script>
<script type="text/javascript">
	var pageCount = 3;//每次加载数量
	var beginTimes = 0;//每次查询起始数

	var jaizaiTimes = 1;//加载次数
	$(document).ready(function() {

		$("#getMoreId").click(function() {
			var beginTimes1 = pageCount * jaizaiTimes;
			getZanInfo(beginTimes1, pageCount);
			jaizaiTimes++;
		});
		getZanInfo(0, 3);
	});
</script>
</head>
<body>
	<!--头部-->
	<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
	<!--头部结束-->
	<!--头条-->
	<div class="my-top-tool jiange-top">
		<span class="headline-back-btn" onclick="javascript:history.back();"><img src="<%=ctxPath%>/resources/images/icon_back.png" align="absmiddle" /></span>
		<span class="my-top-title">评论</span>
	</div>
	<div id="zanContentId"></div>
	<div class="qiangzhuo-more" id="getMoreId">点击查看更多...</div>
	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->
</body>
</html>