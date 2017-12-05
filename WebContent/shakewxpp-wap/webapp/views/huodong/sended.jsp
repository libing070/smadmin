<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
var openId ="<c:out value='${openId}'/>";
var weixinAppNo ="<c:out value='${weixinAppNo}'/>";
var ctxPath='<%=ctxPath%>';

$(document).ready(function(){
	  $("#bg").hide();
      showMyBigRed();
});

function showMyBigRed(){
	var begin = $("#htmlContent").children("a").size()+1;
	var ends = Number(begin)+Number(countPerQuery) - 1;
	
   var jsonData ={"begin":begin,"ends":ends,"openId":openId};
     jQuery.ajax
	   ({
		type: "POST",
		url: ctxPaths + "/queryByopenid.tv",
		dataType: "json",
		async:false,
		cache: false,
		data : jsonData,
		success: function(data){
		  var list=data;
          
              if(list.length==0){
                  if($("#htmlContent").children("a").size()==0){
                	   $("#faRedBag").text("马上免费发红包");
        	 		   $("#fontRedId").text("亲，您还没有发过红包哦~");
        	 		   $("#faFriendRedBag").hide();
        	 		   $("#dianjiMoreList").hide();
                   }else{
                	   $("#dianjiMoreList").text("没有更多明细了");
                   }  
              }else{
            	  if(list.length < Number(countPerQuery)){
            		  $("#dianjiMoreList").text("没有更多明细了");
            	  }
            	   $("#faRedBag").hide();
    	 		   $("#fontRedId").hide();
    	 		   $("#faFriendRedBag").text("马上给好友发红包");
    	 		   $("#faFriendRedBag").hide();
            	  jQuery.each(list, function(index, obj) {
            		  		var htmlCotent="";
	            		    htmlCotent+="<a href='"+ctxPath+"/lingQu.tv?weixinAppNo="+weixinAppNo+"&freId="+obj.freId+"' style='text-decoration:none'>";
	            		  	htmlCotent+=" <div class='sendedlist'>";
	            		  	htmlCotent+="<div>";
	              			htmlCotent+="<div class='l'>";
	                   		htmlCotent+=" <div><span><img src="+ctxPath+"/resources/images/icon_money2.png width='30' align='absmiddle'/></span><span id='showContent' sytle='font-size:12px'>"+(obj.freFromSource==1?"发出的流量红包":"发出的流量红包")+"</span></div>";
	                   		htmlCotent+="</div>";
	                   		htmlCotent+="<div class='m font-red'>"+obj.dynamicFields.smallCount+"/"+obj.bigCount+"个</div>";
	            			htmlCotent+="<div class='r'>&gt;</div>";
	            			htmlCotent+="</div>";
	            			htmlCotent+="<div class='clearfix'><span class='date font-smaller'>"+obj.dynamicFields.grabtimeDate+"</span><span class='date font-smaller'>有效期至"+obj.dynamicFields.expireDate+"</span></div>";
	            	  	 	htmlCotent+="</div>";
	            	  	 	htmlCotent+="</a>";
	            		  	//插入内容
	            		  	$("#htmlContent").append(htmlCotent);
            		 	 });
            	}        
              }  
		});
	}

//马上免费发红包
function goSendRedBag(){
	window.location.href=ctxPaths+"/drawPage.tv?weixinAppNo="+weixinAppNo;
}

function shareButtonF(){
	$("#bg").show();
}

function hideBgF(){
	$("#bg").hide();
}

</script>
<style type="text/css">
#bg{display:none;text-align:center;position:absolute;top:0%;left:0%;width:100%;height:100%;background-color:black;z-index:1001;-moz-opacity:0.7;opacity:.70;filter:alpha(opacity=70);}
</style>
</head>

  
<body class="bg-white">
  <div id="bg">
  	<div style="width:25%;height:30%;float:right;margin-right:0px;margin-top:0px;background:url(<%=ctxPath%>/resources/images/icon_arrow.png);background-repeat:no-repeat;background-size:100% 100%;-webkit-background-origin:content-box;background-origin:content-box;">&nbsp;</div>
  	<div style="margin-top:50%;color:white">点击右上角【分享到朋友圈】</div>
  	<div style="color:white">给好友发红包</div>
  	
  	<div style="margin-top:10%;color:white" id="hideBg" onclick="javascript:hideBgF();">我知道了</div>
  </div>
  
  
 <div><img src="<%=ctxPath%>/resources/images/pic_fachu.jpg" width="100%"/></div>
  <div id="htmlContent"></div>
  <div id="dianjiMoreList" class="bg-gray centertext font-small font-block" onclick="javascript:showMyBigRed();">点击显示更多...</div>
  
  
  
  
  <div class="font-red centertext font-small font-block" id="fontRedId"></div>
  <div id="faRedBag" class="yellow-btn clearfix" onclick="javascript:goSendRedBag();"></div>
  
  <div id="faFriendRedBag" class="yellow-btn clearfix" onclick="javascript:shareButtonF();"></div>

  
  
  <span id='showContent' sytle='font-size:10px;'></span>
</body>


</html>
