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
<%@ include file="common/include.jsp" %>
<title><%=webTitle %></title>
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/style/jquery.mobile-1.4.2.min.css"/>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/WeixinApi.js"></script> 
<script type="text/javascript" src="<%=ctxPath%>/resources/jquery/jquery.mobile-1.4.2.min.js"></script>
<script type="text/javascript">
var bindMsisdn = "";
var openId = "<c:out value="${openid}"/>";
var weixinAppNo = "<c:out value="${weixinAppNo}"/>";

var locationHref = window.location.href;
var hrefArray = locationHref.split(window.ctxPaths);
var ipStr = hrefArray[0];
var imgUrl = ipStr+"<%=ctxPath%>/resources/images/pic_head.jpg";
var link = locationHref;

var wxData = {
        "imgUrl":imgUrl,
        "link":link,
        "desc":"流量红包免费抢",
        "title":"流量红包免费抢"
    };

$(function(){
	queryUserInfo();
})



WeixinApi.ready(function(Api){
		// 隐藏右上角popup菜单入口
	    //Api.hideOptionMenu();

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

function nobanding(){
	location.href = window.ctxPaths+"/phone.tv?weixinAppNo="+weixinAppNo;	
}

function drawFlow(){
	if(bindMsisdn==null || bindMsisdn==""){
		queryUserInfo();
	}
	if(bindMsisdn==null || bindMsisdn==""){
		location.href = window.ctxPaths+"/phone.tv?weixinAppNo="+weixinAppNo;
	}else{
		$.mobile.loading( "show", {
	        text: "提交中...",
	        textVisible: true,
	        theme: "b",
	        textonly: false,
	        html: ""
	 	});
	 	
		jQuery.ajax({
	           url :"<%=ctxPath%>/drawFlowRedEnvelope.tv",
	            type : "POST",
	            cache: false,
	           //async:false, 
	            dataType : "json",
	            data:{"openID":openId,"bindMsisdn":bindMsisdn,"weixinAppNo":weixinAppNo},			           
	           	success : function(data) {
	           		$.mobile.loading('hide');
	           		
		            if (data.CODE =="FALSE") {
		            	phoneAlert(data.msg);
		             }else if(data.CODE =="TRUE"){
		            	 var drawResult = data.drawResult; 
						  if(drawResult.failType=="0"){  
							  var desFreId = data.desFreId;        //抢到红包
							  desFreId = desFreId.replace(/\+/g,"%2B");
							  location.href="<%=ctxPath%>/getBigFlowRedPage.tv?weixinAppNo="+weixinAppNo+"&freId="+desFreId;
						  }else if(drawResult.failType=="1"){    //抢过了
							  location.href="<%=ctxPath%>/yiqiang.tv?weixinAppNo="+weixinAppNo;
						  }else if(drawResult.failType=="2"){                                 //来晚了
							  location.href="<%=ctxPath%>/qiangOver.tv?weixinAppNo="+weixinAppNo;
						  }else if(drawResult.failType=="3"){ 
							  phoneAlert("该活动目前正在测试运营阶段。。。");
						  }else if(drawResult.failType=="4"){ 
							  location.href="<%=ctxPath%>/ready.tv?weixinAppNo="+weixinAppNo;
						  }
		            }else{
	                 	  phoneAlert("对不起、网络异常!");
                    }
		      }
   		}); 
	}
}


function buyFolw(){
	if(bindMsisdn==null || bindMsisdn==""){
		queryUserInfo();
	}
	if(bindMsisdn==null || bindMsisdn==""){
		location.href = window.ctxPaths+"/phone.tv?weixinAppNo="+weixinAppNo;  
	}else{
        $('<form></form>')
                .attr('id', 'buypackageform')
                .attr('action', window.ctxPaths+'/tobuypackage.tv?weixinAppNo='+weixinAppNo)
                .attr('method', 'post')
                .append('<input type="hidden" name="bindMsisdn" value="' + bindMsisdn + '">')
                .append('<input type="hidden" name="openID" value="' + openId + '">')
                .appendTo('body');
        $('#buypackageform').submit();
	}
}

function queryUserInfo(){
	jQuery.ajax({
        url :"<%=ctxPath%>/queryUserInfo.tv",
         type : "POST",
         cache: false,
         async:false, 
         dataType : "json",
         data:{"openID":openId},			           
        	success : function(data) {
	            if (data.CODE =="FALSE") {
	            	phoneAlert(data.msg);
	             }else if(data.CODE =="TRUE"){
		             if(!data.user){
		            	 window.location.href=subUrl;
			         }
			         
					 var subscribe = data.user.subscribe;
					 if(subscribe.toString() != "1"){
						 window.location.href=subUrl;
			         }

			         
					 bindMsisdn = data.user.bindMsisdn;
	            	 if(bindMsisdn!=null && bindMsisdn !=""){
            			$("#nobanding").hide();
            		 }
	            }else{
              	  phoneAlert("对不起、网络异常!");
             }
	      }
	});	
}
</script>
</head>

<body>
  <div>
    <img src="<%=ctxPath%>/resources/images/pic_head.jpg" width="100%" />
  </div>
  
  <div class="nobanding" id="nobanding" onclick="javascript:nobanding();">
    <div class="bigtext">我要免费领红包</div>
    <div class="smalltext">关联手机号即可获赠免费流量红包</div>
  </div>
  
  <div class="nobanding" id="drawFlow" onclick="javascript:drawFlow();">
    <div class="bigtext">我要免费抢红包</div>
    <div class="smalltext">每天免费派发<span class="font-red"><c:out value="${maxFreCount}"/></span>个流量红包</div>
  </div>
  
  <div class="nobanding" id="buyFolw" onclick="javascript:buyFolw();">
    <div class="bigtext">我要免费赚红包</div>
    <div class="smalltext">开通流量套餐得免费红包</div>
  </div>
  
  <div class="blank-jiange"></div>
  <div class="blank-jiange"></div>
  <div class="blank-jiange"></div>
  <div class="blank-jiange"></div>
  
  
  
  <div id="zz" style="width: 90%; padding:20px 0; line-height:18px; position: absolute; left:5%; bottom:25px; z-index: 9; background: #000; opacity:0.5; border-radius: 6px; text-align:center; color:#fff; display:none">操作失败</div>
  <div class="menu">
     <span class="border" onclick='javascript:location.href="<%=ctxPath%>/sended.tv?weixinAppNo="+weixinAppNo;'>我发出的红包</span>
     <span class="border" onclick='location.href="<%=ctxPath%>/getUserSnagSubRedInfo.tv?weixinAppNo="+weixinAppNo;'>我抢到的红包</span>
     <span onclick='javascript:location.href="<%=ctxPath%>/guize.tv";'>活动规则</span>
  </div> 
</body>
</html>
