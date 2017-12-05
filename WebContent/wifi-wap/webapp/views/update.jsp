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
function updateNickName(){
	jQuery.ajax
		({
			type: "post",
			url: ctxPaths + "/s",
			dataType: "json",
			cache: false,
			success: function(data){
				if(data.CODE=="TRUE"){
					alert("成功");
					alert("修改条数："+data.count);
				}
			}
		})
}

</script>
<body>
<jsp:include page="app_head.jsp" flush="true"></jsp:include>

	<a href="#" onclick="updateNickName()">更新</a>
	<jsp:include page="app_footer.jsp" flush="true"></jsp:include>
	
</body>

</html>