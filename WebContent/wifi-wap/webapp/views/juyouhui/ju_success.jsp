<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page language="java" import="org.apache.shiro.subject.Subject,org.apache.shiro.SecurityUtils;"%>
<%
    Subject currentUser = SecurityUtils.getSubject();
    String mobile="";
   	if(currentUser.isAuthenticated()){
    	 mobile = currentUser.getPrincipal().toString();
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=yes,minimum-scale=1.0,maximum-scale=2.0" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="../common/include.jsp"%>

<script type="text/javascript">
var mobile='<%=mobile%>';
var url='<%=ctxPath%>';
var ref="<c:out value="${ref}"/>";
var activityName="<c:out value="${pinActivityEntity.activityName}"/>";
var activityId="<c:out value="${pinActivityEntity.activityId}"/>";
var customCode="<c:out value="${customCode}"/>";
var quota = "<c:out value="${pinActivityEntity.quota}"/>";
 var cashSubsidy="<c:out value="${pinActivityEntity.cashSubsidy}"/>";
 var salePersonNum="<c:out value="${pinActivityEntity.salePersonNum}"/>";
 var persionParticipateCnt="<c:out value="${pinActivityEntity.persionParticipateCnt}"/>";
 var pinActionInvalidate="<c:out value="${pinActivityEntity.pinActionInvalidate}"/>";
 var pinCreatetableInvalidate="<c:out value="${pinActivityEntity.pinCreatetableInvalidate}"/>";
var sum=parseInt(cashSubsidy)+parseInt(salePersonNum)+parseInt(persionParticipateCnt)+parseInt(pinActionInvalidate)+parseInt(pinCreatetableInvalidate);
$(function(){
var contentHtml='';
if(activityId==3){
contentHtml+='<div class="l"><img src="<%=ctxPath%>/resources/images/2128235774.jpg" width="100%"/></div><div class="r">';
contentHtml+='<div class="title">'+activityName+'</div>';
contentHtml+='<div class="renci">累计'+sum+'人抢过</div>';
contentHtml+='<div class="renci">昌平区鼓楼东街34号（三层）物美超市往东200米路南</div>';
contentHtml+='<div class="renci">010-57496455</div>';
contentHtml+='</div>';
$("#xiaofeima p").html('优惠码');
}else if(activityId==4){
contentHtml+='<div class="l"><img src="<%=ctxPath%>/resources/images/IMG_1237.JPG" width="100%"/></div><div class="r">';
contentHtml+='<div class="title">'+activityName+'</div>';
contentHtml+='<div class="renci">累计'+sum+'人抢过</div>';
contentHtml+='<div class="renci">松园路45号政法大学东侧</div>';
contentHtml+='<div class="renci">010-89749118</div>';
contentHtml+='</div>';
$("#xiaofeima p").html('优惠码');
}else if(activityId==5){
contentHtml+='<div class="l"><img src="<%=ctxPath%>/resources/images/cpsddyy.jpg" width="100%"/></div><div class="r">';
contentHtml+='<div class="title">昌平首都电影院</div>';
contentHtml+='<div class="renci">累计'+sum+'人抢过</div>';
contentHtml+='<div class="renci">凭此页领取30元首都电影院心动券</div>';
contentHtml+='</div>';
$("#xiaofeima p").html('优惠码');
}else if(activityId==2){
contentHtml+='<div class="l"><img src="<%=ctxPath%>/resources/images/pic_sample01.jpg" width="100%"/></div><div class="r">';
contentHtml+='<div class="title">'+activityName+'</div>';
contentHtml+='<div class="renci">累计'+sum+'人抢过</div>';
contentHtml+='</div>';
$("#xiaofeima p").html('优惠码');
}else if(activityId==6){
	contentHtml+='<div class="l"><img src="<%=ctxPath%>/resources/images/dingdinglog.jpg" width="100%"/></div><div class="r">';
	contentHtml+='<div class="title">'+activityName+'</div>';
	contentHtml+='<div class="renci">累计'+sum+'人抢过</div>';
	contentHtml+='<div class="renci">昌平区国泰商场旁美廉美超市1层（水榭明珠小吃城内）</div>';
	contentHtml+='<div class="renci">010-89709200</div>';
	contentHtml+='</div>';
	$("#xiaofeima p").html('优惠码');
}
else if(activityId==9){
	contentHtml+='<div class="l"><img src="<%=ctxPath%>/resources/images/htmt.jpg" width="100%"/></div><div class="r">';
	contentHtml+='<div class="title">红舵码头</div>';
	contentHtml+='<div class="renci">累计'+sum+'人抢过</div>';
	contentHtml+='<div class="renci">东环路金隅万科广场5楼电梯口处</div>';
	contentHtml+='<div class="renci">13910966209</div>';
	contentHtml+='</div>';
	$("#xiaofeima p").html('优惠码');
	
}
if(activityId==0){
$("#xiaofeima div").html("");
}else{
$("#xiaofeima div").html(customCode);
}
$("#juyouhuidetail").html(contentHtml);
$("#sharelink #textfield").val(ref);

});

//全选复制链接


function HighlightAll(theField) {  
	var link=$("#textfield").val();
	try{
		window.clipboardData.setData("Text", link); 
		phoneAlert("活动链接已复制，转发给朋友吧");
	}catch(e){
		$("#selfUrl").html('<span style="color: white;">'+link+'</span>');
		$("#showUrlDiv").css('display','block');
	}
}
function foot_close(){
	$("#showUrlDiv").css('display','none');
}

function  qdmcheck(){
	var returnVal = window.confirm("确认消费？","注：确认是否消费此次优惠");
	if(!returnVal) {
		return false;
	}else{
		  var jsonData={"xfms":customCode,"account":$("#qdmcheck").val()};
		  jQuery.ajax({
	          url : "<%=path%>/checkxiaofeimaController",
	          type : "POST",
	 		 async: false,
	          dataType : "json",
	          data : jsonData,
	        	 success : function(data) {
				if(data.CODE=='TRUE'){
					   var list=data.list;
		      	 	   var list1=data.list1;
		      	 	   var list2=data.list2;
		      	 	   if(list2==customCode){
		      	 		  alert("验证成功！");
			      	 	   $("#xiaofeima p").remove();
                           $("#xiaofeima div").before("<div class='ju-btn-gray'>优惠码已使用</div>").addClass("ju-btn-gray").html(list2);
					   }
		      	 	   if(list==customCode){
                         alert("你输入的渠道码不存在");
					   }
		      	 	   if(list1==customCode){
                         alert("该优惠码已经使用过");
					   }
					  
	      	 	  }else{
	      	 	   alert("验证失败");
	              	 }
	           	   }
	         });    
	}
 }

$(function(){
	if(activityId==2){
   	 $("#huodongguize").html('<p>使用说明:</p><ol><li class="ju-guize">限量领取：本次活动限量领取400张，每人限领一张</li><li class="ju-guize">领取方法：凭下发短信前往指定地点领取，过时不候</li><li class="ju-guize">面膜提供商为乐蜂网，如出现不良反应请立即停用</li><li class="ju-guize">有效期限：数量有限送完为止 </li></ol>');
		 }
     if(activityId==3){
   	 $("#huodongguize").html('<p>使用说明:</p><ol><li class="ju-guize">请在有效期限内使用，逾期无效</li><li class="ju-guize">任意消费均享受7折优惠，每桌可使用多张10元优惠卷</li><li class="ju-guize">不兑现、不找零，不得与店内其他优惠同时使用</li><li class="ju-guize">限1个用户领用一张，消费使用后方可再次参与活动 </li><li class="ju-guize">免预约，如遇消费高峰时段您可能需要排队 </li><li class="ju-guize">最终解释权归黄记煌满汉焖品所有 </li><li class="ju-guize">有效期限：至 2015-01-01 </li><li class="ju-guize"> 减10元优惠已包含在优惠码及下发短信中，请在付款时向店家出示</li></ol>');
       }
       if(activityId==4){
   	 $("#huodongguize").html('<p>使用说明:</p><ol><li class="ju-guize">请在有效期限内使用，逾期无效</li><li class="ju-guize">任意消费均享受8.8折优惠，每桌可使用多张10元优惠卷</li><li class="ju-guize">不兑现、不找零，不得与店内其他优惠同时使用</li><li class="ju-guize">限1个用户领用一张，消费使用后方可再次参与活动 </li><li class="ju-guize">不适用于酒水、饮料、茶位、纸巾、餐具，外卖打包</li><li class="ju-guize">免预约，如遇消费高峰时段您可能需要排队 </li><li class="ju-guize">最终解释权归王林串串香所有 </li><li class="ju-guize">有效期限：至 2015-01-01 </li><li class="ju-guize">减10元优惠已包含在优惠码及下发短信中，请在付款时向店家出示 </li></ol>');
       }
       if(activityId==5){
   	 $("#huodongguize").html('<p>使用说明:</p><ol><li class="ju-guize">预约信息：无需预约，如遇消费高峰时段您可能需要排队</li><li class="ju-guize">使用规则：凭下发短信前往指定影院享受优惠，不再与店内其他优惠同享</li><li class="ju-guize">3D眼镜：配备3D眼镜，无需您再缴纳押金和其他费用</li><li class="ju-guize">VIP厅、首映式、见面会等特殊场次不可试用</li></ol>');
       }
       if(activityId==6){
   	 $("#huodongguize").html('<p>使用说明:</p><ol><li class="ju-guize">请在有效期限内使用，逾期无效</li><li class="ju-guize">不兑现、不找零，不得与店内其他优惠同时使用</li><li class="ju-guize">限1个用户领用一张，消费使用后方可再次参与活动 </li><li class="ju-guize">免预约，如遇消费高峰时段您可能需要排队 </li><li class="ju-guize">最终解释权归丁丁洋回转自助火锅所有 </li><li class="ju-guize">有效期限：至2015-08-31 </li></ol>');
       }
       if(activityId==9){
   	 $("#huodongguize").html('<p>使用说明:</p><ol><li class="ju-guize">本优惠为双人套餐，套餐包含菜品，详询店内</li><li class="ju-guize">有效期限：至2015-04-10，请在有效期限内使用，逾期无效</li><li class="ju-guize">不可与其他优惠同享 </li><li class="ju-guize">仅限大厅使用，包厢不可用，打包详询商户 </li><li class="ju-guize">限1个用户领用一张，消费使用后方可再次参与活动 </li><li class="ju-guize">免预约，如遇消费高峰时段您可能需要排队 </li><li class="ju-guize">最终解释权归红舵码头所有 </li></ol>');
       }
});
</script>
  <body>
<!--头部-->
<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
<!--头部结束-->

<div class="headline-write-bg jiange-top">
    <span class="headline-back-btn" onclick="javascript:history.back();"><img
            src="<%=ctxPath%>/resources/images/icon_back.png" align="absmiddle"/></span>
</div>


<!-- 聚友惠详情 -->	
<div class="ju-listblock ju-bg-conner2" id="juyouhuidetail">

</div>

<div class="ju-listblock2 ju-authcode" id="xiaofeima">
    	<p></p>
        <div class="font-big"></div>
</div>
<!-- 聚友惠详情 结束 -->

<div class="ju-listblock2" id="sharelinkfriend">
	<div class="ju-share">
    	长按下面的链接地址，复制并分享给朋友


    </div>
    <div style="text-align:center;" id="sharelink">
      <label>
      <form name=test>
        <input name="select1" type="text" class="ju-pinglun-input" id="textfield" value="" onclick="javascript:HighlightAll()"/>
      </form>
      </label>
    </div>

</div>

<center id="not2and5">
<h1>渠道码</h1>
<input type="text" name="qdmcheck" id="qdmcheck"><input type="button" onclick="qdmcheck()" value="验证">
<p>请输入商户渠道码，进行优惠验证</p>
<p>请慎重进行此操作，避免没有获得优惠~</p>
</center>
<div class="ju-listblock2" id="huodongguize">
</div>
<div id="showUrlDiv" style="width: 90%; padding:10px 0; line-height:18px; position: absolute; left:5%; top:50%; z-index: 9999; background: #000; opacity:1; border-radius: 9px; text-align:center; color:#fff; display:none;">
    <div style="position:absolute; width:25px;float:right; right:0px; top:0px; z-index:31;"><a href="#" onclick="foot_close();"><img src="<%=ctxPath%>/resources/images/foot_close.png" width="100%" /></div>
	<div id="selfUrl"></div>
</div>

<!-- 聚友惠评论 结束 -->

<!--<div class="qiangzhuo-more">下拉查看更多评论...</div>-->

<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
</body>
</html>
