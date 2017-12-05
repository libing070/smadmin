<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>北京移动wifi分发管理平台</title>
<%@include file="common/include.jsp"%>
<script type="text/javascript">
$(function(){
	if (window != window.top){
		window.top.location.href = location.href;
	}
})
</script>
</head>

<frameset rows="110,*,30" cols="*" framespacing="0" frameborder="no" border="0">
  <frame src="<%= ctxPath%>/loginHead" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
  <frame src="<%= ctxPath%>/loginAction" name="mainFrame" id="mainFrame" title="mainFrame" />
  <frame src="<%= ctxPath%>/loginFood" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" title="底部" />
</frameset>

<noframes>
<body>
</body>
</noframes>
</html>