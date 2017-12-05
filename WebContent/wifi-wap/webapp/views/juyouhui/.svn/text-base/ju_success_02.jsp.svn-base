<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="../common/include.jsp"%>
<script type="text/javascript">
var customCode="<c:out value="${shiYongEntity.customCode}"/>";
var quote="<c:out value="${shiYongEntity.quote}"/>";
var activityName="<c:out value="${shiYongEntity.activityName}"/>";
var activityId="<c:out value="${shiYongEntity.activityId}"/>";
var ref="<c:out value="${ref}"/>";


$(function(){
var locationHref = window.location.href;
var hrefArray = locationHref.split(window.ctxPaths);
var ipStr = hrefArray[0];
var link = ipStr+'<%=ctxPath%>/judetailContr?activityId='+activityId;
	$("#textfield").val(link);
	if(activityId==5){
	$("#titleName").html("昌平首都电影院");
		}else{
	$("#titleName").html(activityName);
			}
	$("#renciId").html("累计"+quote+"人抢过");
	if(activityId=='0'){
	$("#customCode").html("");
		}else{
	$("#customCode").html(customCode);
			}
	if(activityId=="3"){
		$("#imgId").html('<img src="<%=ctxPath%>/resources/images/2128235774.jpg" width="100%"/>');
		$("#content1").html("地址：昌平区鼓楼东街34号（三层）物美超市往东200米路南");
		$("#content2").html("电话：010-57496455");
		$("#content3").html('<a href="<%=ctxPath%>/youhuiMapContr">查看地图</a>');
		$("#content4").html("【折上减】进店即送酸梅汤，7折再减10元，还不心动么？");
		var html='<p>使用说明:</p>'+
			'<ol>'+
			 ' <li class="ju-guize">请在有效期限内使用，逾期无效</li>'+
			 ' <li class="ju-guize">任意消费均享受7折优惠，每桌可使用多张10元优惠卷</li>'+
			 ' <li class="ju-guize">不兑现、不找零，不得与店内其他优惠同时使用</li>'+
			'  <li class="ju-guize">限1个用户领用一张，消费使用后方可再次参与活动</li>'+
			'  <li class="ju-guize">免预约，如遇消费高峰时段您可能需要排队</li>'+
			'  <li class="ju-guize">最终解释权归黄记煌满汉焖品所有</li>'+
			'  <li class="ju-guize">有效期限：至 2015-01-01</li>'+
			'  <li class="ju-guize">减10元优惠已包含在优惠码及下发短信中，请在付款时向店家出示</li>'+
			'</ol>';
		$("#ruleId").html(html);
	}else if(activityId=="4"){
	$("#imgId").html('<img src="<%=ctxPath%>/resources/images/IMG_1237.JPG" width="100%"/>');
		$("#content1").html("地址：松园路45号政法大学东侧");
		$("#content2").html("电话：010-89749118");
		$("#content4").html("【折上减】8.8折再减10元");
		var html='<p>使用说明:</p>'+
			'<ol>'+
			 ' <li class="ju-guize">请在有效期限内使用，逾期无效</li>'+
			 ' <li class="ju-guize">任意消费均享受8.8折优惠，每桌可使用多张10元优惠卷</li>'+
			 ' <li class="ju-guize">不兑现、不找零，不得与店内其他优惠同时使用</li>'+
			'  <li class="ju-guize">限1个用户领用一张，消费使用后方可再次参与活动</li>'+
			'  <li class="ju-guize">不适用于酒水、饮料、茶位、纸巾、餐具，外卖打包</li>'+
			'  <li class="ju-guize">免预约，如遇消费高峰时段您可能需要排队</li>'+
			'  <li class="ju-guize">最终解释权归王林串串香所有</li>'+
			'  <li class="ju-guize">有效期限：至 2015-01-01</li>'+
			'  <li class="ju-guize">减10元优惠已包含在优惠码及下发短信中，请在付款时向店家出示</li>'+
			'</ol>';
		$("#ruleId").html(html);
	}else if(activityId=="5"){
		$("#imgId").html('<img src="<%=ctxPath%>/resources/images/cpsddyy.jpg" width="100%"/>');
		$("#content1").html("地址：北京市昌平区南环路金隅万科广场8层");
		$("#content2").html("电话：010-60749995");
		$("#content4").html("凭此页领取30元首都电影院心动券");
		var html='<p>使用说明:</p>'+
			'<ol>'+
			 ' <li class="ju-guize">预约信息：无需预约，如遇消费高峰时段您可能需要排队</li>'+
			 ' <li class="ju-guize">使用规则：凭下发短信前往指定影院享受优惠，不再与店内其他优惠同享</li>'+
			 ' <li class="ju-guize">3D眼镜：配备3D眼镜，无需您再缴纳押金和其他费用</li>'+
			 ' <li class="ju-guize">VIP厅、首映式、见面会等特殊场次不可试用</li>'+
			'</ol>';
		$("#ruleId").html(html);
		$("#sharelinkfriend,#sharelink").remove();
	}else if(activityId=="6"){
		$("#imgId").html('<img src="<%=ctxPath%>/resources/images/dingdinglog.jpg" width="100%"/>');
		$("#content1").html("地址：昌平区国泰商场旁美廉美超市1层（水榭明珠小吃城内）");
		$("#content2").html("电话：010-89709200");
		$("#content4").html("【惊爆】火锅自助原价每人36元，现价每人21元，这个冬天暖暖哒~");
		var html='<p>使用说明:</p>'+
			'<ol>'+
			 ' <li class="ju-guize">请在有效期限内使用，逾期无效</li>'+
			 ' <li class="ju-guize">不兑现、不找零，不得与店内其他优惠同时使用</li>'+
			 ' <li class="ju-guize">限1个用户领用一张，消费使用后方可再次参与活动</li>'+
			 ' <li class="ju-guize">免预约，如遇消费高峰时段您可能需要排队</li>'+
			 ' <li class="ju-guize">最终解释权归丁丁洋回转自助火锅所有</li>'+
			 ' <li class="ju-guize">有效期限：至2015-08-31 </li>'+
			'</ol>';
		$("#ruleId").html(html);
	}else if(activityId=='9'){
		$("#imgId").html('<img src="<%=ctxPath%>/resources/images/htmt.jpg" width="100%"/>');
		$("#content1").html("地址：东环路金隅万科广场5楼电梯口处");
		$("#content2").html("电话：13910966209");
		$("#content4").html("双人仅需78元，感受时尚小火锅的魅力~");
		var html='<p>使用说明:</p>'+
			'<ol>'+
			 ' <li class="ju-guize">本优惠为双人套餐，套餐包含菜品，详询店内</li>'+
			 ' <li class="ju-guize">有效期限：至2015-04-10，请在有效期限内使用，逾期无效</li>'+
			 ' <li class="ju-guize">不可与其他优惠同享</li>'+
			 ' <li class="ju-guize">仅限大厅使用，包厢不可用，打包详询商户</li>'+
			 ' <li class="ju-guize">限1个用户领用一张，消费使用后方可再次参与活动</li>'+
			 ' <li class="ju-guize">免预约，如遇消费高峰时段您可能需要排队</li>'+
			 ' <li class="ju-guize">最终解释权归红舵码头所有</li>'+
			'</ol>';
		$("#ruleId").html(html);
		}
	if(ref.indexOf('myFoot')>0){
		 $("#not2and5").hide();
		 $(".xfmheshi p").remove();
         $(".xfmheshi div").before("<div class='ju-btn-gray'>优惠码已使用</div>").addClass("ju-btn-gray").html(list2);
    }
})
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
			      	 	   $(".xfmheshi p").remove();
                           $(".xfmheshi div").before("<div class='ju-btn-gray'>优惠码已使用</div>").addClass("ju-btn-gray").html(list2);
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
</script>
</head>
<body>
<!--头部-->
<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
<!--头部结束-->
<div class="headline-write-bg jiange-top">
    <span class="headline-back-btn" onclick="javascript:history.back();"><img
            src="<%=ctxPath%>/resources/images/icon_back.png" align="absmiddle"/></span>
</div>



<!-- 聚友惠详情 -->	
<div class="ju-listblock ju-bg-conner2">
	<div class="l" id="imgId"><img src="images/ju_sample.jpg" width="100%"/></div>
    <div class="r">
      <div class="title" id="titleName"></div>
      <div class="renci" id="renciId"></div>
      <div class="" ><span id="content1"></span></div>
      <div class="" ><span id="content2"></span></div>
      <div class="" ><span id="content3"></span></div>
    </div>
</div>

<div class="ju-listblock2 ju-authcode xfmheshi">
    	<p id="content4"></p>
        <div class="font-big" id="customCode"></div>
</div>
<!-- 聚友惠详情 结束 -->


<div class="ju-listblock2" id="ruleId">
	
</div>
<div class="ju-share" id="sharelinkfriend">
    	长按下面的链接地址，复制并分享给朋友


</div>
    <div style="text-align:center;" id="sharelink">
      <label>
      <form name=test>
        <input name="select1" type="text" class="ju-pinglun-input" id="textfield" value="" onclick="javascript:HighlightAll()"/>
      </form>
      </label>
    </div>
    
    <center id="not2and5">
<h1>渠道码</h1>
<input type="text" name="qdmcheck" id="qdmcheck"><input type="button" onclick="qdmcheck()" value="验证">
<p>请输入商户渠道码，进行优惠验证</p>
<p>请慎重进行此操作，避免没有获得优惠~</p>
</center>
<div id="showUrlDiv" style="width: 90%; padding:10px 0; line-height:18px; position: absolute; left:5%; top:50%; z-index: 9999; background: #000; opacity:1; border-radius: 9px; text-align:center; color:#fff; display:none;">
    <div style="position:absolute; width:25px;float:right; right:0px; top:0px; z-index:31;"><a href="#" onclick="foot_close();"><img src="<%=ctxPath%>/resources/images/foot_close.png" width="100%" /></div>
	<div id="selfUrl"></div>
</div>



<!-- 聚友惠评论 结束 -->

<!--<div class="qiangzhuo-more">下拉查看更多评论...</div>-->


<!--导航菜单--><!-- #BeginLibraryItem "/Library/foot.lbi" -->
<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
 <!-- #EndLibraryItem --><!--导航菜单结束-->

</body>
</html>