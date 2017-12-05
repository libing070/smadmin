<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width  ,initial-scale = 1.0, minimum-scale = float_value , maximum-scale = float_value ,user-scalable =no ,target- densitydpi = device-dpi" />
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<%@ include file="../common/include.jsp" %>
<title><%=webTitle %></title>
</head>

<body class="bg-red">
	<div class="qiang-touxiang">
		<span><img id="imgNameId" width="30" align="absmiddle" />
		</span><span class="qiang-user" id="userId"></span>
	</div>
	<div class="clearfix qiangguang-tishi-block">
		<div class="qiangguang-conner"></div>
		<div class="qiangguang-tishi">
			<span class="font-small font-red" id="titleId">恭喜抢到我的流量红包，可以兑换流量！</span>
		</div>
	</div>

	<div style="position:relative;">
		<img src="<%=ctxPath%>/resources/images/pic_qianghongbao.jpg" width="100%" />
	</div>
	<div class="qiang-layout">
		<div class="qiang-topborder"></div>
		<div class="qiang-block">
			<div class="font-red-big jiange-block" id="liuliangId"><c:out value="${qiangResult.folwCoinNum}"/>流量币</div>
			<div class="guanlian-layout centertext font-small" id="liuliangzhi">(可免费兑换<c:out value="${qiangResult.folwCoinNum}"/>M流量)</div>
			<div class="guanlian-layout  font-small">
				请输入您的手机号码（<span class="font-red">仅限山东移动</span>）
			</div>

			<div class="guanlian-layout qiang-login">
				<label>
					<input name="textfield" type="text" class="guanlian-input qiang-input qiang-input-phone" 
					id="phoneNum" placeholder="请输入山东移动手机号码" />
				</label>
			</div>

			<div class="guanlian-layout qiang-login">
				<label>
					<input name="textfield" type="text" class="guanlian-input qiang-input qiang-input-authcode"
					id="password" placeholder="请输入动态码" />
					<span class="qiang-input qiang-authcode-btn" onclick="gainTrendsCodeInfo()">免费获取动态码</span>
				</label>
			</div>

			<div class="guanlian-layout notes1 font-red" id="tip">请输入正确的山东移动手机号码！</div>
			<div class="yellow-btn clearfix" onclick="submitBoundInfo()">提&nbsp;交</div>
			<div class="qiang-bottom"></div>
			
			<%@ include file="../common/tmpTip.jsp" %>
		</div>
	</div>
	<div id="zz" style="width: 90%; padding:10px 0; line-height:18px; position: absolute; left:5%; bottom:15px; z-index: 9; background: #000; opacity:0.5; border-radius: 6px; text-align:center; color:#fff; display:none">操作失败</div>
</body>
<script type="text/javascript">
var weixinAppNo ="<c:out value="${weixinAppNo}"/>";
var openId = "<c:out value="${user.openID}"/>";
var freId = "<c:out value="${freId}"/>";
var flowConinNum="<c:out value="${qiangResult.folwCoinNum}"/>";
var failType = "<c:out value="${failType}"/>";

var subFreId;
	$(function(){
		$("#tip").hide();
		if(flowConinNum==""||flowConinNum==null){
			searchRedBagQiangGuoInfo();
		}
		searchFriendSubRedList();
	})
	
	function searchRedBagQiangGuoInfo(){
		var jsonData = {
			"openId":openId,
			"freId":freId
		}
		jQuery.ajax({
         url : ctxPaths +"/searchRedBagQiangGuoInfo.tv",
         type : "POST",
		 async: false,
         dataType : "json",
         data:jsonData,
       	 success : function(data) {
       	 	if(data.CODE){
       	 		if(data.subRedEnvelope!=null){
       	 			var subRedEnvelope=data.subRedEnvelope;
       	 			$("#liuliangId").text(subRedEnvelope.subFreFlowCurrency_toFloat+"流量币");
					$("#liuliangzhi").text("(可免费兑换"+ subRedEnvelope.subFreFlowCurrency_toFloat + "M流量)");
       	 			subFreId = subRedEnvelope.subFreId;

       	 			var tip = "";
       	 			if(failType=="0"){
       	 			  	tip = "这是你已经抢过的红包哦";
           	 		}else{
           	 			tip = "恭喜抢到我的流量红包，可以兑换流量！";
               	 	}
       	 			$("#titleId").text(tip);
       	 		}
       	 	}else{
       	 		phoneAlert("对不起，网络异常!");
       	 	}
       	 }
       	 })
	}
	
	function submitBoundInfo(){
		var phoneNum = $("#phoneNum").val();
		var password = $("#password").val();
		if(phoneNum.length==0){
			$("#tip").text("手机号码不能为空！");
			$("#tip").show();
			return;
		}
		if(password.length==0){
			$("#tip").text("动态码不能为空!");
			$("#tip").show();
			return;
		}
		 var visPhone=/^\d{11}$/;
      	 if(!visPhone.test($.trim(phoneNum))){
      		 $("#tip").text("请输入正确的山东移动手机号码！");
      		 $("#tip").show();
			 return;
      	 }
      	var currency = $("#liuliangId").text().replace("流量币","");
		var jsonData = {
			"bindMsisdn":phoneNum,
			"password":password,
			"openId":openId,
			"freId":freId,
			"weixinAppNo":weixinAppNo,
			"subFreFlowCurrency":currency,
			"subFreId":subFreId
		};

		jQuery.ajax({
         url : ctxPaths +"/submitBoundInfo.tv",
         type : "POST",
		 async: false,
         dataType : "json",
         data:jsonData,
       	 success : function(data) {
       	 	if(data.CODE){
				window.location.href=ctxPaths+"/getSuccessSubRedInfo.tv?weixinAppNo="+weixinAppNo+"&freId="+freId;
       	 	}else{
       	 		$("#tip").text(data.msg);
       	 		$("#tip").show();
       	 	}
       	 }
		})
	}
	
	function gainTrendsCodeInfo(){
		var phoneNum = $("#phoneNum").val();
		if(phoneNum.length==0){
			$("#tip").text("手机号码不能为空！");
			$("#tip").show();
			return;
		}
		 var visPhone=/^\d{11}$/;
      	 if(!visPhone.test($.trim(phoneNum))){
      		 $("#tip").text("请输入正确的山东移动手机号码！");
      		 $("#tip").show();
			 return;
      	 }
		$("#tip").hide();
		
		var jsonData = {
			"bindMsisdn":phoneNum,
			"weixinAppNo":weixinAppNo
		}
		jQuery.ajax({
         url : ctxPaths +"/gainTrendsCodeInfo.tv",
         type : "POST",
		 async: false,
         dataType : "json",
         data:jsonData,
       	 success : function(data) {
       	 	if(data.CODE){
       	 		$("#tip").text("短信验证码已下发，请注意查收！");
       	 		$("#tip").show();
       	 	}else{
       	 		$("#tip").text(data.msg);
       	 		$("#tip").show();
       	 	}
       	 }
       	 })
	}

	function searchFriendSubRedList(){
		var jsonData = {
			"openId":openId,
			"freId":freId
		}
		jQuery.ajax({
         url : ctxPaths +"/searchFriendSubRedList.tv",
         type : "POST",
		 async: false,
         dataType : "json",
         data:jsonData,
       	 success : function(data) {
       	 	if(data.CODE=="TRUE"){
	       	 	var envelope = data.envelope;
	       	 	if(envelope!=null){
		       	 	$("#userId").text(envelope.nickName==null?"好友":envelope.nickName+":");
		       	 	$("#imgNameId").attr("src",envelope.imgName==null?"<%=ctxPath%>/resources/images/icon_touxiang.jpg":envelope.imgName);
	       	 	}
       	 	}else if(data.CODE=="FALSE"){
       	 		phoneAlert(data.msg);
       	 	}
       	 }
       	 })
	}
</script>
</html>