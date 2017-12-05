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
<%@ include file="../common/include.jsp" %>
<title><%=webTitle %></title>

<script type="text/javascript">
var openId = "<c:out value="${user.openID}"/>";
var weixinAppNo = "<c:out value="${user.weixinAppNo}"/>";
var bindMsisdn = "<c:out value="${user.bindMsisdn}"/>";

$(function(){
	if(bindMsisdn!=null && bindMsisdn!=""){
		 $("#tip").html("您已绑定号码："+bindMsisdn);
	     $("#tip").css("display","block");
	}	
})


function getPasswd(){
	 if($("#telePhone").val().length==0){	
		 $("#tip").html("请输入山东移动手机号码!");
	     $("#tip").css("display","block");
	     return;
	 }else if($("#telePhone").val().length!=0){
	      var visPhone=/^\d{11}$/;
	      if(!visPhone.test($.trim($("#telePhone").val()))){
	    	 $("#tip").html("请输入山东移动手机号码!");
		     $("#tip").css("display","block");
		     return;
	      }
	 }
	     
      jQuery.ajax({
			url :"<%=ctxPath%>/getPassWord.tv",
			type : "POST",
			cache: false,
			//async:false, 
			dataType : "json",
			data:{
			"bindMsisdn":$("#telePhone").val(),"openID":openId
			},			           
		    success : function(data) {
            if (data.CODE =="FALSE") {
            	if(data.msg && data.msg!=''){
            		$("#tip").html(data.msg);
	   		     	$("#tip").css("display","block");
            	}else{
            		$("#tip").html("对不起，操作失败！");
	   		     	$("#tip").css("display","block");
            	}  
	        }else if(data.CODE =="TRUE"){
	        	$("#tip").html("发送成功，注意查收！");
   		     	$("#tip").css("display","block");
	        }else{
	        	$("#tip").html("对不起、网络异常!");
   		     	$("#tip").css("display","block");
	        }
		}
	 });
	
}


function submitBind(){
	 if($("#telePhone").val().length==0){	
		 $("#tip").html("请输入山东移动手机号码!");
	     $("#tip").css("display","block");
	     return;
	 }else if($("#password").val().length==0){
	      $("#tip").html("请输入动态密码！");
		  $("#tip").css("display","block");
	      return;
	   }
	    
	 var visPhone=/^\d{11}$/;
  	 if(!visPhone.test($.trim($("#telePhone").val()))){
  		 $("#tip").html("请输入正确的山东移动手机号码！");
	     $("#tip").css("display","block");
       return;
  	 }

  	jQuery.ajax({
           url :"<%=ctxPath%>/bindMsisdn.tv",
            type : "POST",
            cache: false,
           //async:false, 
            dataType : "json",
            data:{
        	  "bindMsisdn":$("#telePhone").val(),
        	  "password":$("#password").val(),
        	  "openID":openId
            },			           
           	success : function(data) {
	            if (data.CODE =="FALSE") {
	            	$("#tip").html(data.msg);
	   		     	$("#tip").css("display","block");
	             }else if(data.CODE =="TRUE"){ 	 
	            	 var desFreId = data.desFreId;
	            	 desFreId = desFreId.replace(/\+/g,"%2B");
	            	 location.href="<%=ctxPath%>/getBigFlowRedPage.tv?weixinAppNo=" + weixinAppNo + "&freId=" + data.desFreId;
	            }else{
	            	$("#tip").html("对不起、网络异常!");
	   		     	$("#tip").css("display","block");
	            }
	      }
  });
}
</script>
</head>

<body>
  <div>
    <img src="<%=ctxPath%>/resources/images/pic_guanlian.jpg" width="100%" />
  </div>
  
  <div class="guanlian-border">
    <div class="font-red xiahuaxian guanlian-layout">关联微信号及手机号</div>
    <div class="guanlian-layout">
      <div class="guanlian-name">微信用户</div>
      <div class="guanlian-ziduan"><c:out value="${user.nickName}"/></div>
    </div>
    
    <div class="guanlian-layout">
      <div class="guanlian-name">手机号码</div>
      <div class="guanlian-ziduan">
        <label>
          <input id="telePhone" name="textfield" type="text" class="guanlian-input qiang-input qiang-input-phone" placeholder="请输入山东移动手机号码"  />
        </label>
      </div>
    </div>
    
    <div class="guanlian-layout">
      <div class="guanlian-name" >动态密码</div>
      
      <div class="guanlian-ziduan">
        <label>
          <input id="password" name="textfield" type="text" class="guanlian-input qiang-input qiang-input-authcode"  placeholder="请输入动态码"/>
        </label>
        <span class="guanlian-authcode" id="getPasswd" onclick="javascript:getPasswd();">免费获取动态码</span>
      </div>
    </div>
    
    <div id="tip" class="guanlian-layout notes1 font-red" style="display:none;">
        	请输入正确的山东移动手机号码！
    </div>  
  </div>
  
  <div class="guanlian-btn" onclick="javascript:submitBind();">提&nbsp;交</div>
  
  <div class="guanlian-layout notes2">
     <span>* 微信号首次成功关联手机号即可免费获赠流量红包哦~更能比未关联时抢到更多流量币</span>
  </div>
  
  <div class="guanlian-layout notes2">
     <span>* 温馨提示：亲，流量红包活动目前试运行内测中，如您在参与过程中遇到问题，欢迎微信留言咨询或反馈，我们会尽快解决，给您带来的不便敬请谅解哦^_^</span>
  </div>
  
  
   
</body>
</html>
<script type="text/javascript">
 
   $("#telePhone").on('click', function() {

	   if($("#telePhone").val().length==0){	

   }  



</script>