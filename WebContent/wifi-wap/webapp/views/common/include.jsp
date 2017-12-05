<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/error404.html"%>
<%@include file="taglibs.jsp"%>
<%
	String ctxPath=request.getContextPath();	
	String error_title = "系统繁忙，请稍候再试！";
	String catch_title = "系统异常，请稍候再试！";
%>
<script type="text/javascript">
	window.ctxPaths="<%= request.getContextPath()%>";
	window.error_title="<%= error_title%>";
	window.catch_title="<%= catch_title%>";
</script>

<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/css.css"/>
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/slide.css"/>
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/modal.css"/>
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/lightbox.css"/>

<script type="text/javascript" src="<%=ctxPath%>/resources/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/jquery.showLoading.min.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/hhSwipe.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/common.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/modal.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/jquery.lightbox.js"></script>

<script type="text/javascript">
	$(function(){
		$(".logout").click(function(){
			window.location.replace(ctxPaths+"/logout");
		});	
		
		var back_href = window.location.href;
		if(back_href.indexOf("/logout") < 0 && back_href.indexOf("/login") < 0){
				window.localStorage.setItem("back_href",back_href); 
		}
	});
</script>