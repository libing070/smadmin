<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width  ,initial-scale = 1.0, minimum-scale = float_value , maximum-scale = float_value ,user-scalable =no ,target- densitydpi = device-dpi" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<%@ include file="../common/include.jsp" %>
<title><%=webTitle %></title>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/WeixinApi.js"></script> 
<script type="text/javascript">
var ctxPath="<%=ctxPath%>";
var freId= "<c:out value="${freId}"/>";
var desFreId = "<c:out value="${desFreId}"/>";
desFreId = desFreId.replace(/\+/g,"%2B");

var freFromSource="<c:out value="${freFromSource}"/>";
var grabtimeDate="<c:out value="${grabtimeDate}"/>";
var expireDate="<c:out value="${expireDate}"/>";
var bigCount="<c:out value="${bigCount}"/>";
var smallCount="<c:out value="${smallCount}"/>";
var openId ="<c:out value="${openId}"/>";
var weixinAppNo ="<c:out value="${weixinAppNo}"/>";

var locationHref = window.location.href;
var hrefArray = locationHref.split(window.ctxPaths);
var ipStr = hrefArray[0];

var imgUrl = ipStr+"<%=ctxPath%>/resources/images/pic_qianghongbao_share.jpg";
var link = ipStr+"<%=ctxPath%>/qiangFlow.tv?freId="+desFreId+"&weixinAppNo="+weixinAppNo;


var wxData = {
        "imgUrl":imgUrl,
        "link":link,
        "desc":"山东移动流量汇给我"+bigCount+"个流量红包，分你一个，快来抢吧！",
        "title":"抢流量红包"
    };


//每次加载数据量
var ends=countPerQuery;
$(document).ready(function(){
	$("#allOver").hide();
	$("#leftSome").hide();
	$("#noneLingqu").hide();
	$("#rightNowSend").hide();
	$("#overLingqu").hide();
	$("#needMore").hide();
	$("#reback").hide();
	if(freFromSource==1){
  		$("#showTitle").html("抢到的流量红包");
	}else{
		$("#showTitle").html("赠送的流量红包");
	}
	$("#dateShow").html(grabtimeDate);

	showDetail(0,ends);
});




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




function showDetail(begin,ends){
	   var jsonData={"freId":freId,"begin":begin,"ends":ends,"expireDate":expireDate,"openId":openId};
		jQuery.ajax({
         url : ctxPaths +"/queryLingQu.tv",
         type : "POST",
		 async: false,
         dataType : "json",
         data:jsonData,
       	 success : function(data) {
       	 	   var htmlContent="";
       	 	   if(smallCount<=0){
       	 	    	$("#yiling").html("0");
       	 	    	$("#flowSumCount").html("0");
       	 	    	$("#bigHongBao").html(bigCount);
       	 	    	$("#noneLingqu").show();
       	 	    	$("#rightNowSend").show();
       	 	   }
       	 	   if(data.timeCode=="OVERTIME"){
       	 	       if(smallCount<bigCount){
       	 	       		$("#leftSome").show();
       	 	       		$("#needMore").show();
       	 	       	}//截止此处是过期未领完
       	 	       	if(smallCount==0){
       	 	       		$("#leftSome").show();
       	 	       		$("#noneLingqu").hide();
       	 	       		$("#overLingqu").show();
       	 	       		$("#reback").show();
       	 	       		$("#needMore").hide();
       	 	       		$("#shareButton").hide();
       	 	       	}
       	 	      }else if(smallCount==bigCount){
       	 	     		$("#allOver").show();
       	 	      		$("#needMore").show();
       	 	      }//截止此处是未过期领完
       	 	      else if(smallCount<bigCount){
       	 	             $("#leftSome").hide();
       	 	     		 $("#rightNowSend").show();
       	 	      }//截止此处是未过期未领完
       	 	   if(data.CODE=="TRUE"){
				  var list=data.lingquList;
				  if(null!=list||""!=list){
	       	 	      for(var i=0;i<list.length;i++){
	       	 	       	htmlContent+= '<div class="lingqulist bg-white">';
	    				htmlContent+='<div class="l">';
	    				var headImgUrl=list[i].headImgUrl==null?'<%=ctxPath%>/resources/images/icon_touxiang.jpg':list[i].headImgUrl
				      	htmlContent+='<span><img src='+headImgUrl+' align="absmiddle" width="30"/></span>';//'+ctxPath+'/resources/images/icon_touxiang.jpg
				      	var nickName=list[i].nickName==null?'好友':list[i].nickName;
				     	htmlContent+='<span class="qiangguang-user">'+nickName+'</span>';
					    htmlContent+='</div>';
				  	 	htmlContent+='<div class="r font-gray">';
					    htmlContent+='<div class="font-small"><span class="font-red-big2">'+list[i].subFreFlowCurrency+'</span>流量币</div>';
					    htmlContent+='<div><span class="date font-small">'+
					    new Date(list[i].grabTime).getFullYear()+"/"+((new Date(list[i].grabTime).getMonth()+1)<10?"0"+(new Date(list[i].grabTime).getMonth()+1):(new Date(list[i].grabTime).getMonth()+1))+"/"
						+(new Date(list[i].grabTime).getDate()<10?"0"+new Date(list[i].grabTime).getDate():new Date(list[i].grabTime).getDate())+" "+(new Date(list[i].grabTime).getHours()<10?"0"+new Date(list[i].grabTime).getHours():new Date(list[i].grabTime).getHours())+":"
						+(new Date(list[i].grabTime).getMinutes()<10?"0"+new Date(list[i].grabTime).getMinutes():new Date(list[i].grabTime).getMinutes())+":"+
						(new Date(list[i].grabTime).getSeconds()<10?"0"+new Date(list[i].grabTime).getSeconds():new Date(list[i].grabTime).getSeconds())+'</span></div>';
				   		htmlContent+='</div>';
					  	htmlContent+='</div>';
				
					  	//插入详细内容
	       	 	      }
				  		$("#lingquContent").html(htmlContent);
				  		$("#bigHongBao").html(bigCount);
				  		$("#flowSumCount").html(data.flowSumCount);
			  		  	$("#yiling").html(data.smallCount);
				  }
       	 	   }
		 	}
		 });
		}


	//分页
	function getMore(){
		var pageCurrent=0;	
		begin = pageCurrent*ends-ends;
		pageCurrent++;
		ends=Number(ends)+Number(countPerQuery);
		showDetail(begin,ends);
	}

	function sendToFriend(){
		$("#bg").show();
	}
	
	function hideBg(){
		$("#bg").hide();
	}
	
	//返回
	function reback(){
		window.location.href=ctxPaths+"/sended.tv?weixinAppNo="+weixinAppNo;
	}

</script>
<style type="text/css">
#bg{display:none;text-align:center;position:absolute;top:0%;left:0%;width:100%;height:100%;background-color:black;z-index:1001;-moz-opacity:0.7;opacity:.70;filter:alpha(opacity=70);}
</style>
</head>
  
  <body>
  
  <div id="bg">
  	<div style="width:25%;height:30%;float:right;margin-right:0px;margin-top:0px;background:url(<%=ctxPath%>/resources/images/icon_arrow.png);background-repeat:no-repeat;background-size:100% 100%;-webkit-background-origin:content-box;background-origin:content-box;">&nbsp;</div>
  	<div style="margin-top:50%;color:white">点击右上角【发送给朋友】</div>
  	<div style="color:white">给好友发红包</div>
  	
  	<div style="margin-top:10%;color:white" id="hideBg" onClick="javascript:hideBg();">我知道了</div>
  </div>
  
  
  <div class="lingquhead bg-white">
    <div class="l">
      <div><span><img src="<%=ctxPath%>/resources/images/icon_money2.png" width="30" align="absmiddle"/></span><span id="showTitle"></span></div>
    </div>
    <div class="r"><span id="dateShow"></span></div>
  </div>
  
  <div class="clearfix jiange-block lingqu-title-block">
    <div class="lingqu-title jiange-block">
    <span class="bg-gray" id="allOver">
    <img src="<%=ctxPath%>/resources/images/icon_smile.png" width="30" align="absmiddle" />&nbsp;全部领完！</span>
    <span class="bg-gray" id="leftSome">
    <img src="<%=ctxPath%>/resources/images/icon_smile2.png" width="30" align="absmiddle" />&nbsp;未全部领完(已过期)</span>
    
    </div>
   <div class="lingqu-mingxi">
        <div class="l">
          <span class="font-gray">已领<span id="yiling"></span>个，</span><span class="font-red-big2" id="flowSumCount" ></span><span class="font-gray">流量币</span>
        </div>
        <div class="r">
          <div class="font-gray">共<span class="font-red-big2" id="bigHongBao"></span>个红包</div>
        </div>
    </div>
    <div class="lingqu-mingxi-conner"></div>
  </div>
  
   <div id="lingquContent"></div>
  
  
<div class="bg-white centertext font-block font-red" id="noneLingqu">亲,还未有人领取！</div>
<div class="bg-white centertext font-block font-red" id="overLingqu">亲,下次要及时分享到朋友圈或者发送给朋友哦！</div>
  
  <div class="bg-white centertext font-small font-block font-gray" onclick="javascript:getMore();" id="needMore">点击显示更多...</div>
 
   <div class="centertext font-block font-gray" id="rightNowSend">
    <div class="yellow-btn clearfix" id="shareButton" onclick="javascript:sendToFriend();">马上给好友发红包</div>
  </div>
  
   <div class=" centertext font-block font-gray" id="reback">
    <div class="yellow-btn clearfix" id="shareButton" onclick="javascript:reback();">返回</div>
  </div>
</body>

</html>
