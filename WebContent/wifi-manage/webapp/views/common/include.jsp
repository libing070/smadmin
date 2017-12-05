<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.aspire.wifi.manage.util.GetConfigFile;"
    pageEncoding="UTF-8"%>
<%@include file="taglibs.jsp"%>
<%
	String ctxPath=request.getContextPath();	
	String error_title = "系统繁忙，请稍候再试！";
	String catch_title = "系统异常，请稍候再试！";
	String countPerQuery = "10";
	
	 String imageAccessPath=GetConfigFile.getInstance().getProperties("ImageAccessPath"); 
	String version = "5";
%>
<script type="text/javascript">
	window.ctxPaths="<%= request.getContextPath()%>";
	window.error_title="<%= error_title%>";
	window.catch_title="<%= catch_title%>";
	window.countPerQuery = "<%= countPerQuery%>";
	window.imageAccessPath="<%= imageAccessPath%>";
</script>

<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/style/styles.css?version=<%=version%>"/>
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/style/bootstrap.min.css?version=<%=version%>"/>
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/style/main.css?version=<%=version%>"/>
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/style/green.css"/>
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/style/lightbox.css"/>


<script type="text/javascript" src="<%=ctxPath%>/resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/jquery/jquery.tools-1.2.7.min.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/jquery/jquery.form.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/jquery/jquery.showLoading.min.js"></script>

<script type="text/javascript" src="<%=ctxPath%>/resources/jquery/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/artDialog.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/artDialog.plugins.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/artDialog_zh_ch.js"></script>
