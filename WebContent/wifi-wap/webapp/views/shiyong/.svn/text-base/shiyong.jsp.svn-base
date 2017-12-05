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
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/reveal.css"/>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/jquery.reveal.js"></script>
<script type="text/javascript">
	var salePersonNum = "<c:out value="${pinActivityEntity.salePersonNum}"/>";
	var quota = "<c:out value="${pinActivityEntity.quota}"/>";
	var applyCount = "<c:out value="${applyCount}"/>";
	var activityId = "<c:out value="${pinActivityEntity.activityId}"/>";
	var begin =0;//开始查询数
	var times = 2;//显示的条数	
	var jiazaitimes=1;//加载次数;
	 var mobile='<%=mobile%>';
	 var url='<%=ctxPath%>';
	$(function(){
		jiazaitimes=1;
		getpinjia();
		$("#quota").text(quota);
		$("#applyCount").text(applyCount);
		if(quota==0){
          $(".bigtitle").remove();
          $(".fawan").show();
	    }

	    $("#shareLink").on('click',function(){
	    	copyUrl();
		})

		 var jsonData={"mobile":mobile,"activityId":activityId};
			jQuery.ajax({
	            url : url+"/queryMobileFormShiYong",
	            type : "POST",
	   		 async: false,
	            dataType : "json",
	            data : jsonData,
	          	 success : function(data) {
	          	 	if(data.CODE=='FALSE'){
	          	 		suc();
	              	 }
	             	   }
	           })

	       //了解此产品统计
	   	    $(".knowProduct").bind("click",function(){
	   			jQuery.ajax({
	   	            url : url+"/shiyongKonwContr",
	   	            type : "POST",
	   	         })
		   	  })
	});	
	function checkStatus(obj){
		if(mobile==''){
			window.location.href=url+"/login";
			}else{
		    if($(obj).attr("class","freeApplication")){
			    var jsonData={"mobile":mobile,"activityId":activityId};
				   jQuery.ajax({
			            url : url+"/shiyongGet",
			            type : "POST",
			   	    	async: false,
			            dataType : "json",
			            data : jsonData,
			          	 success : function(data) {
			          	 	if(data.CODE=='TRUE'){
			              	 	$("#quota").text(parseInt($("#quota").text())-1);
			              	 	$("#applyCount").text(parseInt($("#applyCount").text())+1);
			              	 	}else{
			                  	 	}
			             	    }
			          	   })
		           }
			}
	     }
	function suc(){
         $(".freeApplication").addClass("seeDetails").
         html("领奖事宜").
         attr("onclick","sucessApplication()").
         parent().attr("data-reveal-id","myModa2").
         removeClass("freeApplication");
		}
	function sucessApplication(){
      $(".duanxintongzhi").show();
		}


	function copyUrl(){

		//统计
		jQuery.ajax({
            url : url+"/shiyongShareContr",
            type : "POST",
         })
         
		var locationHref = window.location.href;
		var hrefArray = locationHref.split(window.ctxPaths);
		
		var ipStr = hrefArray[0];
		var link = ipStr+"<%=ctxPath%>/shiyongContr";
		
		
		try{
			window.clipboardData.setData("Text", link); 
			phoneAlert("活动链接已复制，转发给朋友吧");
		}catch(e){
			$("#selfUrl").html('<span style="color: white;">'+link+'</span>');
			$("#showUrlDiv").css('display','block');

			//window.prompt("请复制下面链接", link);    
		}
	}

function foot_close(){
	$("#showUrlDiv").css('display','none');
}		
	
function getpinjia(){

	jQuery.ajax
	({
		type: "post",
		url: ctxPaths + "/getShiYongPinLun",
		dataType: "json",
		cache: false,
		data:{
			begin:jiazaitimes*times-times,
			times:times,
			flag:1
		},
		success: function(data){
			if(data.CODE=="FALSE"){
				alert("获取失败！");
			}else if(data.CODE=="TRUE"){
				var list =data.list;
				var account =data.account;
				var replyHtml="";
				if(list.length>0){
				
					for(var i=0;i<list.length;i++){
			
				   		replyHtml+='<div class="shiyong1-pl">';
    					replyHtml+='<p><img src="'+ctxPaths+'/my/findAttach?mobile='+list[i].mobile+'" width="25" height="25" align="absmiddle" /> '+list[i].nickname+'</p>';
       					replyHtml+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+list[i].content;
   						replyHtml+='</div>';
					}	
					if((jiazaitimes*times+list.length)>=account){
						$("#dianji").html('<div class="qiangzhuo-more"  >已显示全部评价</div>')
					}
				}
				$("#shiyongtiyanId").append(replyHtml);
			}else{
				alert("对不起网络异常！",3000);
			}
		}
		
	});	
}

function getMore(){
	jiazaitimes++;
	getpinjia();
}
</script>
</head>
<body>
<!--头部-->
<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
<!--头部结束-->

       
<!--免费试用活动-->          
<div class="shiyong1 shiyong-title">Jcare澳洲山羊奶保湿滋润发现套装（2片面膜）</div>
              <div class="shiyong1">
                  <div class="l">
                    <div><img src="<%=ctxPath%>/resources/images/pic_sample01.jpg" width="100%" /><p><a href="http://zhenkeer.lefeng.com">了解此产品>></a></p></div>
                     <div class="font-red"><p><a href="javascript:void(0);" id="shareLink">转发给朋友>></a></p></div>
                  </div>
                  <div class="r">
                    <div class="boldfont">价值：￥12</div>
                    <div class="boldfont">免费试用：<span  id="quota"></span>份</div>
                    <div class="apply font-red"><span id="applyCount"></span>人已申请</div>
                    
                 <div class="fawan" style="display:none"><span>已发完</span></div>
                    <div class="bigtitle"><a data-reveal-id="myModal"><span class="freeApplication" onclick="checkStatus(this)">免费申请</span></a></div>
                  </div>
            </div>
            
            <div class="shiyong1">
                <div class="tiaojian">
                    <span class="boldfont">申领条件：</span>
                    <ul class="shiyong1-smallfont">
                        <li>所有用户都可以免费申请，数量有限领完为止；</li>
                        <li>申请成功后需提交真实评论，请仔细阅读领取规则。</li>
                    </ul>
                </div>
           </div>
<!--免费试用活动结束-->
<div id="myModal" class="reveal-modal">
			<h1>申领成功</h1>
			<p>你可以在“我的”里查看详情</p>
            <div class="shiyong1-bigtitle" onclick="suc()"><span class="shiyong1-clsbtn">我知道了</span></div>
</div>
<div id="myModa2" class="reveal-modal duanxintongzhi">
			<h1>领奖事宜</h1>
			<p style="text-align:left;">亲，你已成功申请Jcare澳洲山羊奶保湿滋润发现套装。</p>
            <p style="text-align:left;">近期我们会通过短信形式通知你领取实物奖品的时间和地点。请注意查收10657520662下发的短信。</p>
            <div class="shiyong1-bigtitle"><span class="shiyong1-clsbtn">我知道了</span></div>
</div>
        

<div class="shiyong1">
  <div class="shiyong1-pl-title">试用体验</div>
  <div id="shiyongtiyanId"></div>
</div>

<div id="dianji"><div class="qiangzhuo-more" onclick="getMore()" >点击查看更多</div> </div>

<div id="showUrlDiv" style="width: 90%; padding:10px 0; line-height:18px; position: absolute; left:5%; top:50%; z-index: 9999; background: #000; opacity:1; border-radius: 9px; text-align:center; color:#fff; display:none;">
    <div style="position:absolute; width:25px;float:right; right:0px; top:0px; z-index:31;"><a href="#" onclick="foot_close();"><img src="<%=ctxPath%>/resources/images/foot_close.png" width="100%" /></div>
	<div><span style="color: white;">请复制下面链接</span></div></br>
	<div id="selfUrl"></div>
</div>

<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
</body>
</html>