<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" />
<title>活动</title>
<%@ include file="common/include.jsp"%>

</head>
<script type="text/javascript">
function searchYinXinInfo(){
//wuh 2014-09-22
//	window.location.href="<%=ctxPath%>/schoolActivityRule";
}
</script>
<body>
	<jsp:include page="app_head.jsp" flush="true"></jsp:include>
    
	<!--抢桌活动-->
	<%@ include file="public.jsp"%>
	<jsp:include page="app_footer.jsp" flush="true"></jsp:include>
</body>
<script type="text/javascript">
$(document).ready(function() {
//wuh 2014-09-22
	chanageStyle();
	$("#qiang_btn").click(function() {
		$("#qiang_success").show();
		$("#qiang").hide();
	});
	fresh();
	searchHonorList();
	setInterval(searchHonorList,10000);
	searchQDtotalCount();
	butInfo();
	
});

</script>
</html>