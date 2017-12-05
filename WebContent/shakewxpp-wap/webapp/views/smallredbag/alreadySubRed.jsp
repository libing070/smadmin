<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="<%=ctxPath%>/resources/js/WeixinApi.js"></script>
<script type="text/javascript">
var openId ="<c:out value="${user.openID}"/>";
var weixinAppNo = "<c:out value="${weixinAppNo}"/>";

var freId = "<c:out value="${freId}"/>";
var desFreId = "<c:out value="${desFreId}"/>";
desFreId = desFreId.replace(/\+/g,"%2B");

var subFreNum = "<c:out value="${subFreNum}"/>";
var locationHref = window.location.href;
var hrefArray = locationHref.split(window.ctxPaths);
var ipStr = hrefArray[0];
var imgUrl = ipStr+"<%=ctxPath%>/resources/images/pic_qianghongbao_share.jpg";
var link = ipStr+"<%=ctxPath%>/qiangFlow.tv?freId="+desFreId+"&weixinAppNo="+weixinAppNo;

var wxData = {
        "imgUrl":imgUrl,
        "link":link,
        "desc":"山东移动流量汇给我"+subFreNum+"个流量红包，分你一个，快来抢吧！",
        "title":"抢流量红包"
    };
    
    
WeixinApi.ready(function(Api){
	// 隐藏右上角popup菜单入口
	if(freId==null || freId ==""){
		Api.hideOptionMenu();
	}

    // 隐藏浏览器下方的工具栏
    //Api.hideToolbar();

    // 获取网络状态
    //Api.getNetworkType(function(network){})
    // 拿到 network 以后，做任何你想做的事
	//微信分享的数据

	// 分享的回调
    var wxCallbacks = {
    	async : true,
        // 分享操作开始之前
        ready:function () {  	
            // 你可以在这里对分享的数据进行重组
        	var self = this;
        	self.dataLoaded(wxData);
        },

        // 分享被用户自动取消
        cancel:function (resp) {
            // 你可以在你的页面上给用户一个小Tip，为什么要取消呢？
        },

        // 分享失败了
        fail:function (resp) {
            // 分享失败了，是不是可以告诉用户：不要紧，可能是网络问题，一会儿再试试？
        },
        // 分享成功
        confirm:function (resp) {
	         var getShareType;
        	 switch (resp.err_msg) {
        	 	case 'share_weibo:ok': 
        	 		getShareType=2;
        	 		break;  
        	 	case 'share_timeline:ok': 
        	 		getShareType=3;
        	 		break;    
        	 }
        	var jsonData = {'getShareType':getShareType,'weixinAppNo':weixinAppNo,'freId':freId};
     		var message = $.toJSON(jsonData);
        },
        // 整个分享过程结束
        all:function (resp) {
        }
    };
    // 用户点开右上角popup菜单后，点击分享给好友，会执行下面这个代码
    Api.shareToFriend(wxData, wxCallbacks);
 	// 点击分享到腾讯微博，会执行下面这个代码
    Api.shareToWeibo(wxData, wxCallbacks); 
    // 点击分享到朋友圈，会执行下面这个代码
    Api.shareToTimeline(wxData, wxCallbacks); 
});

</script>
</head>

<body class="bg-red">
	<div class="qiang-touxiang">
		<span><img id="imgNameId" width="30" align="absmiddle" />
		</span><span class="qiang-user" id="userId"></span>
	</div>
	<div class="clearfix qiangguang-tishi-block">
		<div class="qiangguang-conner"></div>
		<div class="qiangguang-tishi">
			<span class="font-small font-red" id="titleId">这是你已经抢过的红包！</span>
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

			<div class="guanlian-layout centertext font-small" id="liquTip">请于<span id="qinquId"><c:out value="${qiangResult.expiredTime}"/></span>前<br>到流量汇“我的帐户”领取</div>
			<div class="yellow-btn clearfix" onclick="javascript:goClient();" id="linquId" style="display:none;">立即去流量汇领取</div>
			<!--  <div class="yellow-btn clearfix" id="downLoadClient">下载流量汇客户端</div>-->
			<div class="yellow-btn clearfix" onclick="javascript:goTosend();">我也要免费发红包</div>
			<div class="qiang-bottom"></div>
			
			<div class="bg-red qiang-mingxi clearfix">
				<div class="l"><img src="<%=ctxPath%>/resources/images/icon_money.png" width="30" align="absmiddle" />我抢到的红包总额</div>
				<div class="r" id="flowId">0流量币</div>
			</div>
			
			<div class="bg-red qiang-mingxi clearfix">
				<div class="clearfix jiange-block qiang-friend">
					<div class="qiang-friend-title">
						<span class="bg-red">看朋友们手气如何</span>
					</div>
				</div>
				<div id="friendList">
				</div>
				<%@ include file="../common/tmpTip.jsp" %>
			</div>
			
			
            
		</div>
	</div>

	<div id="zz" style="width: 90%; padding:10px 0; line-height:18px; position: absolute; left:5%; bottom:15px; z-index: 9; background: #000; opacity:0.5; border-radius: 6px; text-align:center; color:#fff; display:none">操作失败</div>
</body>

<script type="text/javascript">
var freId ="<c:out value="${freId}"/>";
	$(function(){
		searchFriendSubRedList();
		searchRedBagQiangGuoInfo();


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
       	 			$("#qinquId").text(subRedEnvelope.expiredTime_toString);
       	 			var status = subRedEnvelope.status;

       	 			if(2==status){
       	 				$("#liquTip").html('已成功领取');
	       	 			$("#titleId").text("你已领取过这个红包的流量币了哦！");
       	 			}else if(3==status){
	       	 			$("#titleId").text("亲,该红包逾期未领取流量币,已失效！");
       	 			}else{
       	 				$("#linquId").css('display','block');
           	 		}
       	 		}
       	 	}else{
       	 		phoneAlert("对不起，网络异常!");
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
		       	 	$("#flowId").text(data.sumSubRed+"流量币");
		       	 	$("#userId").text(envelope.nickName+":");
		       	 	$("#imgNameId").attr("src",envelope.imgName==null?"<%=ctxPath%>/resources/images/icon_touxiang.jpg":envelope.imgName);
	       	 	}
	       	 	var list = data.list;
	       	 	if(list!=null){
		       	 	var dataHtml = "";
		       	 	for(var i in list){
		       	 		var grabFlow = list[i];
		       	 		dataHtml+="<div class='clearfix qiang-friend qiang-friend-text'>";
						dataHtml+="<div class='qiangguang-friend-l'>";
						var imgUrl = grabFlow.imgName==null?'<%=ctxPath%>/resources/images/icon_touxiang.jpg':grabFlow.imgName
						dataHtml+="<img src='"+imgUrl+"' align='absmiddle' width='30' />"+(grabFlow.nickName==null?"好友":grabFlow.nickName);
						dataHtml+="</div>";
						dataHtml+="<div class='qiang-friend-m'>"+grabFlow.grabTime_toString+"</div>";
						dataHtml+="<div class='qiang-friend-r'>"+grabFlow.subFreFlowCurrency_toFloat+"流量币</div>";
						dataHtml+="</div>";
		       	 	}
		       	 $("#friendList").html(dataHtml);
	       	 	}
       	 	}else if(data.CODE=="FALSE"){
       	 		phoneAlert(data.msg);
       	 	}
       	 }
       	 })
	}

	function goClient(){
		 $('<form></form>')
         .attr('id', 'presentcoinform')
         .attr('action', '<%=ctxPath%>/topresentcoin.tv')
         .attr('method', 'post')
         .append('<input type="hidden" name="openId" value="' + openId + '">')
         .appendTo('body');
 		 $('#presentcoinform').submit();
	}
	
	//根据有没有绑定手机号码来确定去向
	function goTosend(){
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
	       	 		window.location.href=subUrl;
	       	 	}else {
	       	 		window.location.href=ctxPaths+"/phone.tv?weixinAppNo="+weixinAppNo;
	       	 	}
	       	 }
       	 })
	}
</script>
</html>