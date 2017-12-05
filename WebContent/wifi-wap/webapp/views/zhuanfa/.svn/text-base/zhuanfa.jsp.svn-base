<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page language="java" import="org.apache.shiro.subject.Subject,org.apache.shiro.SecurityUtils"%>
<%
    Subject currentUser = SecurityUtils.getSubject();
    String mobile="";
   	if(currentUser.isAuthenticated()){
    	 mobile = currentUser.getPrincipal().toString();
    }
String onlyIds = request.getAttribute("onlyIds")==null?"":request.getAttribute("onlyIds").toString();			
String isChuangJian = request.getAttribute("isChuangJian")==null?"":request.getAttribute("isChuangJian").toString();			
String msg = request.getAttribute("msg")==null?"":request.getAttribute("msg").toString();			    
String nickname = request.getAttribute("nickname")==null?"":request.getAttribute("nickname").toString();
String isSendMsg = request.getAttribute("isSendMsg")==null?"":request.getAttribute("isSendMsg").toString();			    
%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="../common/include.jsp" %>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/WeixinApi.js"></script>

<script>
var mobile='<%=mobile %>';
var onlyIds='<%=onlyIds %>';
var isChuangJian='<%=isChuangJian %>';
var msg='<%=msg %>';
var nickname='<%=nickname %>';
var isSendMsg='<%=isSendMsg %>';
var locationHref = window.location.href;
var hrefArray = locationHref.split(window.ctxPaths);
var ipStr = hrefArray[0];
var link = ipStr+'<%=ctxPath%>/getzhuanfa?onlyIds='+onlyIds;
var imgUrl = ipStr+"<%=ctxPath %>/resources/images/cishan_ad3.jpg";
var wxData = {
        "imgUrl":imgUrl,
        "link":link,
        "desc":"扶助弱小，传播正能量！来自"+nickname+"的转发链接",
        "title":"冬日温暖，爱心传递"
    };
    
WeixinApi.ready(function(Api){

		// 隐藏右上角popup菜单入口
//		if(freId==null || freId ==""){
//			Api.hideOptionMenu();
			
//		}
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
  
$(function(){
	var html='<p>活动规则：</p><ol><li class="ju-guize">点击爱心，中国政法大学青年志愿者协会将为北京地区打工子弟学校捐出0.1元。</li><li class="ju-guize">复制活动链接转发，成功邀请4人上线参与活动，本平台将为北京地区打工子弟学校捐出2元。</li><li class="ju-guize">所有募得款项将全部用于购置北京打工子弟学校所需的学习用品及生活用品！</li></ol><p class="ju-guize">注：你也可以在“我的”-“活动”里查看</p>';
		    
	$("#guize").html(html);
    $("#textfield").val(link);

	getuserHeadShow();	
	if(isChuangJian=="1"){
		alert("忘了么，你已经参与过了");
	}
	if(isSendMsg=="1"){
		sendMsg();
	}
})



function getuserHeadShow(){

jQuery.ajax
	({
		type: "post",
		url: ctxPaths + "/getzhuanfaList",
		dataType: "json",
		cache: false,
		success: function(data){
			if(data.CODE=="FALSE"){
				alert(data.msg);
			}else if(data.CODE=="TRUE"){
				var list =data.users;
				var html="";
				var listSize=list.length;
				var count = 5-listSize;
				if(list.length>5){
					listSize=5;
				}
				if(listSize>0){
					for(var i=0;i<listSize;i++){
				
					   	html+='<td width="20%" align="center" valign="top"><img src="'+ctxPaths+'/my/findAttach?mobile='+list[i].mobile+'" width="50" height="50" />';
	    				if(i==0){
	    				
	    					html+='<p><span class="font-red">'+list[i].nickname+'</span></p></td>';
	    				}else{
	    					html+='<p>'+list[i].nickname+'</p></td>';
	    				}
					}	
					
					    if(list[0].mobile!=mobile){//表示为帮助方
					    	$("#shuaxinId").hide();
					    	$("#zhidaoId").html("0.1元助捐成功！您可转发链接发起自己的爱心传递！");
					    	if(msg!=""){
					    	//	$("#zhidaoId").html(msg);
					    		alert(msg);
					    	}else{
					    		$("#zhidaoId").html("点击下面的+号，就能帮助TA");
					    	}
					    	  if(mobile!=""){
						    	$("#zhaohuanId").show();
						    	$("#zhaohuanId2").show();
						    }
					    }else{
					    	
						    if(mobile!=""){
						    	$("#fuzhiId").show();
						    }
					    
					    }
			//		if(count>0){
			//		}else{
						if(list[0].mobile!=mobile){//表示为帮助方
							$("#shuaxinId").hide();	
						//	if(isAdd!="1"){
							//	$("#zhidaoId").html("糟糕，来晚了~TA已经找到别人了~");
						//		alert("糟糕，来晚了~TA已经找到别人了~");
						//	}
											
						}else{
						//	$("#fuzhiId").hide();
						//	$("#shuaxinId").hide();
						//	$("#zhidaoId").html("恭喜~成功召唤50元沃尔玛超市卡~我们会在活动结束后统一下发领取短信，请注意查收！");
							if(count<=0){
								$("#zhidaoId").html("爱心集齐，本平台将再为活动捐出2元。爱心权益请点击上方按钮查看");
							}
							$("#countId").html('已有<span class="font-big font-red">'+list.length+'</span>人参与捐助');
							
						}
				//	}  
				} else{
					$("#shuaxinId").hide();
				} 
				for(var j=0;j<count;j++){
					//	html+=' <td width="20%" align="center" valign="top"><a href="#" onclick="addZhuanFa()"><img src="<%=ctxPath%>/resources/images/touxiang_none.jpg" width="50" height="50" /></a></td>';
						html+=' <td width="20%" align="center" valign="top"><img src="<%=ctxPath%>/resources/images/heart_none.jpg" width="50" height="50" /></td>';
				}
				
				$("#headShowId").html(html);
				
			}else{
				alert("对不起网络异常！",3000);
			}
		}
		
	});	
}
/**点击增加转发记录
function addZhuanFa(){	
	if(mobile!=""){		
		jQuery.ajax
			({
				type: "post",
				url: ctxPaths + "/addzhuanfa",
				dataType: "json",
				cache: false,
				success: function(data){
					if(data.CODE=="FALSE"){
						alert(data.msg);
					}else if(data.CODE=="TRUE"){
						alert(data.msg);
						window.location.href=ctxPaths+'/getzhuanfa?onlyIds='+data.zhuanFaId;	
						
					}else{
						alert("对不起网络异常！",3000);
					}
				}
				
		});	
	}else{
		window.location.href=ctxPaths+'/login';	
	}
}
**/
//全选复制链接

function HighlightAll(theField) {  
	var link=$("#textfield").val();
	if(mobile==""){
		window.location.href=ctxPaths+'/login';
	}
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

function sendMsg(){
	var jsonData={"mobile":mobile};
	jQuery.ajax
	({
		type: "post",
		url: ctxPaths + "/sendZhuanFaMsg",
		cache: false,
		dataType: "json",
		data : jsonData,
		success: function(data){
			
		}
	})
}
</script>
</head>
<body>
<!--头部-->
<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
<!--头部结束-->
<div class="zhuanfa-title">冬日温暖爱心传递</div>

<!-- 聚友惠详情 -->	
<div class="ju-listblock2" style="text-align:center;">
  <table width="100%" border="0" cellspacing="1" cellpadding="1">
    <tr>
      <td width="79%"><img src="<%=ctxPath %>/resources/images/cishan_ad3.jpg" width="99%" /></td>
      <td>
		  <div class="zhuanfa-ad" onclick="window.location.href='<%=ctxPath %>/judetailContr?activityId=5'"><img src="<%=ctxPath %>/resources/images/cishan_ad1.jpg" width="100%" /></div>
          <div onclick="window.location.href='<%=ctxPath %>/toutiao/toutiaoDetailIndex?headlineId=727&contentTypeId=2'"><img src="<%=ctxPath %>/resources/images/cishan_ad2.jpg" width="100%" /></div>
      </td>
    </tr>
  </table>
</div>

<div  class="zhuanfa-tishi font-red" id="zhidaoId">成功捐助0.1元，转发链接成功邀请4人上线助捐，本平台将再为活动捐出2元！</div>

<div class="ju-listblock2 zhuanfa-huoban">
        <div><span id="countId"></span><span style="float:right; padding:6px;" id="shuaxinId" onclick="window.location.href='<%=ctxPath %>/getzhuanfa?isHuodongYe=1'"><img src="<%=ctxPath %>/resources/images/icon_refresh.png" width="20"/></span></div>
        <table width="100%" border="0" cellspacing="1" cellpadding="1" >
          <tr id="headShowId">
           
          </tr>
        </table>

</div>
<!-- 聚友惠详情 结束 -->

<!-- 聚友惠评论 -->

<div class="ju-listblock2" id="fuzhiId" style="display:none">
	<div class="ju-share">
    	长按下面的链接地址，复制并分享给朋友
    </div>
    <div style="text-align:center;" id="sharelink">
      <label>
        <input name="textfield" type="text" class="ju-pinglun-input" id="textfield" value="" onclick="javascript:HighlightAll()"/>
      </label>
    </div>
</div>
<div id="showUrlDiv" style="width: 90%; padding:10px 0; line-height:18px; position: absolute; left:5%; top:50%; z-index: 9999; background: #000; opacity:1; border-radius: 9px; text-align:center; color:#fff; display:none;">
    <div style="position:absolute; width:25px;float:right; right:0px; top:0px; z-index:31;"><a href="#" onclick="foot_close();"><img src="<%=ctxPath%>/resources/images/foot_close.png" width="100%" /></div>
	<div id="selfUrl"></div>
</div>
<div class="zhuanfa-tishi font-red" id="zhaohuanId2" style="display:none">转发链接成功邀请4人上线助捐，本平台将再为活动捐出2元！</div>
<div class="btn-block" id="zhaohuanId" style="display:none"><span class="btn-red" onclick="window.location.href='<%=ctxPath %>/getzhuanfa'">发起我的爱心传递</span></div> 
<!-- 聚友惠评论 结束 -->

<!--活动规则-->
<div class="ju-listblock2" id="guize">
	
    
</div>
<!--活动规则-->

<!--<div class="qiangzhuo-more">下拉查看更多评论...</div>-->


<!--导航菜单-->
<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
<!--导航菜单结束-->

</body>
</html>