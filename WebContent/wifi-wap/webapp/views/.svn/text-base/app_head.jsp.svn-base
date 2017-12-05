<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String ctxPath=request.getContextPath();	
%>

<style>
 a{
 font-size:12px;
 }

</style>

<script type="text/javascript">
$(function() {
		searchTitleList();
	});

	function searchTitleList(){
		jQuery.ajax({
         url : "<%=ctxPath%>/queryTopTitle",
         type : "POST",
		 async: false,
		 dataType : "json",
       	 success : function(data) {
       	 	if(data.CODE=="TRUE"){
       	 		var list = data.list;
       	 		var dataHtml = "";
       	 		for(var i in list){
       	 			var HeadlineCt = list[i];
       	 			//dataHtml += HeadlineCt.title+"；&nbsp;&nbsp;";
       	 		dataHtml += "<a href='"+ctxPaths+"/toutiao/toutiaoDetailIndex?headlineId="+HeadlineCt.headlineId+"&contentTypeId=6'>"+HeadlineCt.title+";</a>&nbsp;&nbsp;";
       	 		}
       	 		$("#gonggaoId").html(dataHtml);
       	 	}else{
       	 		phoneAlert(data.msg,1000);
       	 	}
		 }
		})
	}
	
	function logout(){
		var confirmMessage = "下次来将重新登录，确定退出吗？";
		if (confirm(confirmMessage)) {
			try{
    	    	window.localStorage.removeItem("cache_username"); 
    	    	window.localStorage.removeItem("back_href"); 
    	    }catch(e){
    	    }
			location.href='<%=ctxPath%>/logout';
		}
	}

	function loadLoginMobile(){	
		jQuery.ajax({
	         url : "<%=ctxPath%>/loadLoginMobile",
	         type : "POST",
	         cache: false,
	         dataType : "json",	
	       	 success : function(data) {
			     if(data.CODE =="FALSE") {
			    	 phoneAlert(data.msg,3000);
		         }else if(data.CODE =="TRUE"){
		        	 var islogin = data.islogin;
		        	 var username = data.username;
		        	 if(islogin == 1){//已登陆
		        		 $("#login").hide();
	    	 			 $("#logout").show();
	    	 			 $("#msisdnWelcome").text(username);
		        	 }else{//未登录
		        		 $("#login").show();
	    	 			 $("#logout").hide();
		        	 }
		        }else{
		        	 phoneAlert("对不起、网络异常!",3000);
		         }
	        }
	      });
	}
	loadLoginMobile();
</script>
<div class="header">
  <div class="logo">
    <div class="logo2"><img src="<%=ctxPath%>/resources/images/logo_new.png" height="40" /></div>
  </div>
  
  <div class="login" id="login" onclick="javascript:location.href='${pageContext.request.contextPath}/login'"><span class="login-btn">登录</span></div>
  <div class="login" id="logout" style="display:none;"><span id="msisdnWelcome">?</span>，欢迎您！<a onclick="javascript:logout();"><span class="login-btn">退出</span></a></div>
</div>
<!--  <div class="gonggao">
  <div class="icon"><img src="<%=ctxPath%>/resources/images/icon_gonggao.png" /></div>
  <marquee id="gonggaoId" direction="left" scrollamount="2" width="90%"></marquee>
</div>-->
<div id="zz" style="width: 90%; padding:10px 0; line-height:18px; position: absolute; left:5%; top:50%; z-index: 9; background: #000; opacity:1; border-radius: 6px; text-align:center; color:#fff; display:none">操作失败</div>
<div id="maskBox" style="display:none"><div id="loading" ><img src="<%=ctxPath%>/resources/images/ajax-loader_blue.gif" /></div></div>
