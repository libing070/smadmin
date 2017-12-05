<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width  ,initial-scale = 1.0, minimum-scale = float_value , maximum-scale = float_value ,user-scalable =no ,target- densitydpi = device-dpi" />
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <%@ include file="include.jsp" %>
	<title><%=webTitle %></title>
</head>
<body>
<form action="<c:out value="${redirectToUrl}" />" method="post" id="redirectTo">
    <c:forEach items="${params}" var="paramObj">
        <input type="hidden" name="<c:out value="${paramObj.key}" />" value="<c:out value="${paramObj.value}" />"/>
    </c:forEach>
</form>
<script type="text/javascript">
    $(function () {
        $('#redirectTo').submit();
    });
</script>
</body>
</html>