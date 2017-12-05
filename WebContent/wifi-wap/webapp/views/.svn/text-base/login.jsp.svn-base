<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	String orgi = request.getParameter("orgi");
	if(orgi == null ){
		orgi = "";
	}
%>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="common/include.jsp"%>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/jquery.cookie.js"></script>

<style type="text/css">
#bg{display:none;text-align:center;position:absolute;top:0%;left:0%;width:100%;height:100%;background-color:black;z-index:1001;-moz-opacity:0.7;opacity:.70;filter:alpha(opacity=70);}
</style>
</head>

 
<body>
  <div id="bg"> 	
  	<!--  <div style="margin-top:50%;color:white;z-index:1002;"><img src="<%=ctxPath%>/resources/images/loader.gif"/>正在自动登录，请稍等。。。</div>-->
  	
  	<div style="margin-top:50%;color:white;">正在自动登录，请稍等。。。</div>
  </div>
  
  <jsp:include page="app_head.jsp" flush="true"></jsp:include>
  <div class="login-step">
  <div class="login-step-block">
    <div class="login-step-active">登录</div>
  </div>
  <div class="login-step-block">
    <div class="login-step-normal">完善个人资料</div>
  </div>
</div>
<div class="jiange-dashline"></div>

<div class="login-block clearfix" id="longinViewId">
<table width="100%" border="0" cellspacing="2" cellpadding="3">
  <tr>
    <td width="70">手机号：</td>
    <td><label>
      <input type="text" name="username1" id="username1" class="login-input" />
    </label></td>
    <td style="display:none"><img src="<%=ctxPath%>/resources/images/icon_ok.png" width="20" height="20" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><span class="login-password" id="getPassword">
    	<a href="#" onclick="javascript:getPassWord();">获取随机密码</a>
    	</span>
    </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>随机密码：</td>
    <td><input type="text" name="password" id="password" class="login-input" /></td>
    <td style="display:none"><img src="<%=ctxPath %>/resources/images/icon_ok.png" width="20" height="20" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>

<div class="login-text">
	<a href="javascript:;" id="sendStatus" style="display:none;">已发送,请(<span
						id="seconds" style="display:inline;">0</span>秒)后再获取</a>
</div>
<form id="loginform" name="loginform" method="post" >
<div class="btn-block"><span class="btn-blue" onclick="javascript:login();">登录</span></div> 

<input type="hidden" name="orig_referer" id="orig_referer" value="<%=orgi %>" />
</form>
</div>
<div class="scroll_block" style="text-align:center;"><img src="<%=ctxPath%>/resources/images/indexad06.jpg" onclick="gotoActivityURL3()" width="91%"  border="0" /></div>

<jsp:include page="app_footer.jsp" flush="true"></jsp:include>
</body>
<script type="text/javascript">
//则自动登录
function autoLogin(cache_username){
		var jsonData={"username":cache_username};
		var login_back_href = window.localStorage.getItem("back_href");

		jQuery.ajax({
	         url : ctxPaths +"/doAutoLogin",
	         type : "POST",
	         cache: false,
	      	 async:false, 
	         dataType : "json",	
	         data : jsonData,           
	       	 success : function(data) {
	        	if (data.isFirstLogin=="TRUE" || data.isFirstLogin=="FALSE") {
	           		// 登录成功后，进行认证鉴权
	           		authDeviceAccount();
	           	}
	        	
	       		if(typeof(data) == "undefined" || data == null ){
	           	}else if(data.pageId =="sign"){
	           		window.location.replace(ctxPaths+"/myEInter");
	            }else if(data.CODE =="FALSE") {
			    	 //reloadValidateCode();
			    	 phoneAlert(data.msg,3000);
			    	 return;
		         }else if(data.isFirstLogin=="TRUE"){
	       		//	window.location.replace(ctxPaths+"/personalData");      	
	       			window.location.replace(ctxPaths+"/index?index=0");
		         }else if(login_back_href){
		        	 location.href = login_back_href;
		         }else if(data.isFirstLogin=="FALSE"){
	       			window.location.replace(ctxPaths+"/index?index=0");
		         }
	        }
	      });
};

function loadLoginMobile(){	
	var cache_username = window.localStorage.getItem("cache_username"); 
	
	if(cache_username){
		$("#bg").show();
	}
	
	jQuery.ajax({
         url : ctxPaths +"/loadLoginMobile",
         type : "POST",
         cache: false,
         dataType : "json",	
       	 success : function(data) {
		     if(data.CODE =="FALSE") {
		    	 phoneAlert(data.msg,3000);
	         }else if(data.CODE =="TRUE"){
	        	 var islogin = data.islogin;
	        	 var username1 = data.username;
	        	 var remainSeconds = data.remainSeconds;
	        	 if(islogin == 1){//已登陆
	        		 window.location.replace(ctxPaths+"/index");
	        	 }else{//未登录
	        		 $("#login").hide();

        			if(cache_username){//若用户之前有的登录过,则自动登录
        				autoLogin(cache_username);
        			}else{	
   	        		 if(remainSeconds>0){
   	        			 $("#getPassword").hide();
   	    	 			 $("#sendStatus").show();
   	    	 			secondsRemain(remainSeconds);
   	        		 }else{
   	        			$("#getPassword").show();
   	 	     			$("#sendStatus").hide();
   	        		 }
            		} 
	        	 }
	        }else{
	        	 phoneAlert("对不起、网络异常!",3000);
	         }
        }
      });
}

function focusFun (){	
	$("#error_msg").html("");
	$("#validateCode").val(""); 
}

function reloadValidateCode() {
	$("#validateCodeImg").attr("src","<%=basePath%>/validateCode?data="+ new Date() + Math.floor(Math.random() * 24));
}

function getPassWord(){
	var _username = $("#username1").val();
	if(_username==$("#username1").attr("placeholder") || _username==""){
		phoneAlert("请输入手机号码!",3000);
		return;
	}
	var visPhone=/^\d{11}$/;
    if(!visPhone.test(_username)){
        phoneAlert("手机号码格式不正确！",3000);
        return;
    }
	
	var jsonData={"username":_username};

	jQuery.ajax({
         url : ctxPaths +"/getPassWord",
         type : "POST",
         cache: false,
        async:false, 
         dataType : "json",	
         data : jsonData,           
       	 success : function(data) {
		     if(data.CODE =="FALSE") {
		    	 phoneAlert(data.msg,3000);
	         }else if(data.CODE =="TRUE"){
	         	if(data.isTestUser=="TRUE"){
	         		phoneAlert("您的号码为测试用户号码!",3000);
	         	}else{
	        	 	phoneAlert("短信校验码已下发成功,请注意查收!",3000);
	        	}
	        	 $("#getPassword").hide();
	 			 $("#sendStatus").show();
	 			secondsRemain(data.seconds);
	        }else{
	        	 phoneAlert("对不起、网络异常!",3000);
	         }
        }
      });
}


var orgi = '<%=orgi%>';
function chk() {
	if (orgi == 10){
		alert('账号不存在！');
	}
	if (orgi == 11){
		alert('密码错误！');
	}
}
window.onload = chk;
var pUrl="";
var authDeviceUrl = "";
var authUserAccount = "";
var authUserPasswd = "";
function authDeviceAccount() {
	jQuery.ajax({
        url : ctxPaths +"/getAuthDeviceAccountInfo",
        type : "POST",
        cache: false,
     	 async:false,
      	success : function(data) {
      		if (data.authDeviceUrl != null
      				&& typeof(data.authDeviceUrl) != "undefined" 
          			&& data.authDeviceAccountInfo != null
          			&& typeof(data.authDeviceAccountInfo) != "undefined" 
          			&& data != null){
      			authDeviceUrl = data.authDeviceUrl;
      			authUserAccount = data.authDeviceAccountInfo.userAccount;
      			authUserPasswd = data.authDeviceAccountInfo.userPasswd;

      			document.loginform.action = authDeviceUrl + "?username=" + authUserAccount + "&userpwd=" + authUserPasswd;
       			document.loginform.submit(); 
       			pUrl = authDeviceUrl + "?username=" + authUserAccount + "&userpwd=" + authUserPasswd;
                callAuth(pUrl);	
      		} else {
      			alert("认证鉴权失败！");
      		}
       	}
     });
}

function callAuth(pUrl) {
   jQuery.ajax({
        url : pUrl,
        type : "POST",
            cache : false,
            async : false,
            success : function(data) {
            //alert("login wifi success!");     
                     },
            error : function(xmlhttp,b,c){
           	//alert(pUrl+"login wifi error!");
			//alert(xmlhttp.responseText+" " +xmlhttp.readyState+" " +xmlhttp.status);
            }       
     });
}

function login(){
	var rememberMe_v = "false";
	
	var _username = $.trim($("#username1").val());
	if(_username==$("#username1").attr("placeholder") || _username==""){
		phoneAlert("请输入手机号码!",3000);
		return;
	}
	var visPhone=/^\d{11}$/;
    if(!visPhone.test(_username)){
        phoneAlert("手机号码格式不正确！",3000);
        return;
    }
    
    var _password = $("#password").val();
	if(_password==$("#password").attr("placeholder") || _password==""){
		phoneAlert("请输入随机密码 !",3000);
		return;
	}

	//var _validateCode =  $("#validateCode").val();
	//if(_password==""){
	//	phoneAlert("请输入校验码!",3000);
	//	return;
	//}
	
	var jsonData={"username":_username,"password":_password};//,"validateCode":_validateCode};

	jQuery.ajax({
         url : ctxPaths +"/dologin",
         type : "POST",
         cache: false,
      	 async:false, 
         dataType : "json",	
         data : jsonData,           
       	 success : function(data) {
        	if (data.isFirstLogin=="TRUE" || data.isFirstLogin=="FALSE") {
        		try{
        	    	window.localStorage.setItem("cache_username",_username); 
        	    }catch(e){
        	    }      	    
           		// 登录成功后，进行认证鉴权
           		authDeviceAccount();
           	}
        		
       		if(typeof(data) == "undefined" || data == null ){
           	}else if(data.pageId =="sign"){
           		window.location.replace(ctxPaths+"/myEInter");
            }else if(data.CODE =="FALSE") {
		    	 //reloadValidateCode();
		    	 phoneAlert(data.msg,3000);
		    	 return;
	         }else if(data.isFirstLogin=="TRUE"){
       		//	window.location.replace(ctxPaths+"/personalData"); 
       		     window.location.replace(ctxPaths+"/index?index=0");
       		 	
	         }else if(data.isFirstLogin=="FALSE"){
       			window.location.replace(ctxPaths+"/index?index=0");
	         }
        }
      });
}
var pwdIntervalIndex_;
function secondsRemain(seconds) {
	var $seconds = $("#seconds");
	$seconds.html(seconds);
	var index = setInterval(function() {
		var second = parseInt($seconds.html());
		second = second - 1;
		$seconds.html(second);
		if (second <= 0) {
			clearInterval(pwdIntervalIndex_);
			$("#getPassword").show();
			$("#sendStatus").hide();
		}
	}, 1000);
	pwdIntervalIndex_ = parseInt(index);
};

loadLoginMobile();
</script>
</html>
