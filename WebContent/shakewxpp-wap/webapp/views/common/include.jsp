<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/error404.html"%>
<%@include file="taglibs.jsp"%>
<%
	String ctxPath=request.getContextPath();	
	String error_title = "系统繁忙，请稍候再试！";
	String catch_title = "系统异常，请稍候再试！";
	String countPerQuery = "8";
	String subUrl = "http://mp.weixin.qq.com/s?__biz=MzA3OTcxNjczMw==&mid=200606957&idx=1&sn=a64f9b8bdea1416da04989a8300d85bc#rd";
	String webTitle = "山东移动流量红包";
	
	String version = "6";
%>
<script type="text/javascript">
	window.ctxPaths="<%= request.getContextPath()%>";
	window.error_title="<%= error_title%>";
	window.catch_title="<%= catch_title%>";
	window.countPerQuery = "<%= countPerQuery%>";
	window.subUrl = "<%= subUrl%>";
</script>

<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/style/main.css?version=<%=version%>"/>

<script type="text/javascript" src="<%=ctxPath%>/resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/jquery/jquery.tools-1.2.7.min.js"></script>

<script type="text/javascript" src="<%=ctxPath%>/resources/js/artDialog.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/artDialog.plugins.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/artDialog_zh_ch.js"></script>

<script type="text/javascript" src="<%=ctxPath%>/resources/js/common.js"></script>
