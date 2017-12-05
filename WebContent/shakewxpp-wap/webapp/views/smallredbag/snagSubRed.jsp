<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width  ,initial-scale = 1.0, minimum-scale = float_value , maximum-scale = float_value ,user-scalable =no ,target- densitydpi = device-dpi" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<%@ include file="../common/include.jsp" %>
<title><%=webTitle %></title>
</head>
<body class="bg-white">

	<div><img src="<%=ctxPath%>/resources/images/pic_qiangdao.jpg" width="100%" /></div>
	<div class="qiangdao-block">
		<div id="qiangdaoList"></div>
		<div class="bg-gray centertext font-small font-block font-gray" id="searchMoreListId">没有更多明细了</div>
	</div>
	
	<div class="font-red centertext font-small font-block" id="fontRedId">*请在有效期前至流量汇“我的帐户”领取流量币</div>
	
	<div class="yellow-btn clearfix"></div>
	
	<div id="zz" style="width: 90%; padding:10px 0; line-height:18px; position: absolute; left:5%; bottom:15px; z-index: 9; background: #000; opacity:0.5; border-radius: 6px; text-align:center; color:#fff; display:none">操作失败</div>

	<%@ include file="../common/tmpTip.jsp" %>

</body>

<script type="text/javascript">
var openId ="<c:out value="${openId}"/>";
var weixinAppNo ="<c:out value="${weixinAppNo}"/>";
var pagNum = countPerQuery;
	$(function(){
		searchUserSubRedList(pagNum);
		$("#searchMoreListId").click(function(){
			searchUserSubRedList(pagNum+=countPerQuery);
		})
		
        $('.yellow-btn').on('click', function (e) {
        if($("#qiangdaoList").html()==null||$("#qiangdaoList").html()==""){
			var jsonData = {"openId":openId}
				jQuery.ajax({
		         url : ctxPaths +"/validateUserMsidn.tv",
		         type : "POST",
				 async: false,
				 data:jsonData,
		       	 success : function(data) {
		       	 	if(data.CODE=="TRUE"){//TRUE表示已绑定用户
		       	 		window.location.href=ctxPaths+"/drawPage.tv?weixinAppNo="+weixinAppNo;
		       	 	}else if(data.selfCODE=="NOCONCERN"){//未关注
		       	 		window.location.href = subUrl;
		       	 	}else {
		       	 		window.location.href=ctxPaths+"/phone.tv?weixinAppNo="+weixinAppNo;
		       	 	}
		       	 }
	       	 })
        }else{
	        var isBoolean = findUserBindMsisdn();
	        if(!isBoolean){
	        	window.location.href=ctxPaths+"/phone.tv?weixinAppNo="+weixinAppNo
	        	return;
	        }
            $('<form></form>')
                    .attr('id', 'presentcoinform')
                    .attr('action', '<%=ctxPath%>/topresentcoin.tv')
                    .attr('method', 'post')
                    .append('<input type="hidden" name="openId" value="' + openId + '">')
                    .appendTo('body');
            $('#presentcoinform').submit();
        }
        });
	});
	
	/**
	 * 查询是否绑定手机号码
	 */
	function findUserBindMsisdn(){
		var jsonData = {
			"openID":openId
		}
       	var isBolen = true;
		jQuery.ajax({
         url : ctxPaths +"/findUserBindMsisdn.tv",
         type : "POST",
		 async: false,
         dataType : "json",
         data:jsonData,
       	 success : function(data) {
       	 	if(data.CODE){
       	 		var user = data.user;
       	 		if(user.bindMsisdn==null||user.bindMsisdn==""){
       	 			isBolen = false;
       	 		}
       	 	}
       	 }
       	 })
       	 return isBolen;
	}
	
	
	function searchUserSubRedList(pagNum){
		var jsonData = {
			"openId":openId,
			"end":pagNum,
		}
		jQuery.ajax({
         url : ctxPaths +"/searchUserSubRedList.tv",
         type : "POST",
		 async: false,
         dataType : "json",
         data:jsonData,
       	 success : function(data) {
       	 	if(data.CODE){
       	 		var list = data.list;
       	 		//alert(JSON.stringify(list));
       	 		if(list){
	       	 		if(list.length==0){
	       	 			$("#searchMoreListId").hide();
	       	 			$(".yellow-btn").text("马上免费抢红包");
	       	 			$("#fontRedId").text("亲，您还没有抢过红包哦~");
	       	 		}else{
	       	 			$(".yellow-btn").text("去领流量币");
	       	 		}
           	 		
	       	 		if(list.length<data.count){
	       	 			$("#searchMoreListId").text("点击查询更多明细");
	       	 		}else{
	       	 			$("#searchMoreListId").text("没有更多明细了");
	       	 		}
	       	 		var dataHtml = "";
	       	 		for(var i=0;i<list.length;i++){
		       	 		try{
		       	 			var grabFlow = list[i];
		       	 			dataHtml+="<div class='qiangdao-list'>";
		       	 			
		       	 			dataHtml+="<div>";
							dataHtml+="<div class='l'>";
							dataHtml+="<div><span>"+grabFlow.nickName+"的流量红包</span></div>";
							dataHtml+="</div>";
							  
							
							var status = grabFlow.status;
							if(1==status){
								dataHtml+="<div class='r font-small font-red'>未领取</div>";
							}else if(2==status){
								dataHtml+="<div class='r font-small font-gray'>已领取</div>";
							}else if(3==status){
								dataHtml+="<div class='r font-small font-gray'>已过期</div>";
							}
	
							dataHtml+="</div>";
							dataHtml+="<div class='clearfix date font-small font-gray'><span class='date font-red'>"+grabFlow.subFreFlowCurrency_toFloat+"流量币</span> 有效期至"+grabFlow.expiredTime_toString+"</div>";
							dataHtml+="</div>";
		       	 		}catch (e) {
		       	 			alert(e.message); 
			       	 	}
	       	 		}
	       	 	$("#qiangdaoList").html(dataHtml);
       	 		}else{
       	 			$("#searchMoreListId").hide();
       	 			$(".yellow-btn").text("我也要免费发红包");
       	 		}
       	 	}else{
       	 		phoneAlert(data.msg);
       	 	}
		 }
		 })
	}
	
</script>

</html>