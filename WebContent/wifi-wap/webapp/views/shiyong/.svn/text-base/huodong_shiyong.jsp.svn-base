<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="../common/include.jsp"%>
<script type="text/javascript">
$(document).ready(function (){   

getMe();

})
function getMe(){
		jQuery.ajax
	({
		type: "post",
		url: ctxPaths + "/getShiYongPinLun",
		dataType: "json",
		cache: false,
		data:{
			begin:0,
			times:0,
			flag:""
		},
		success: function(data){
			if(data.CODE=="FALSE"){
				alert("获取失败！");
			}else if(data.CODE=="TRUE"){
				var list =data.list;
				var replyHtml="";
				if(list.length>0){
				
					for(var i=0;i<list.length;i++){
			
				   		replyHtml+='<div class="shiyong1-pl">';
    					replyHtml+='<p><img src="'+ctxPaths+'/my/findAttach?mobile='+list[i].mobile+'" width="25" height="25" align="absmiddle" /> '+list[i].nickname+'</p>';
       					replyHtml+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+list[i].content;
   						replyHtml+='</div>';
					}	
				
				}
				$("#queryPinLun").append(replyHtml);
			}else{
				alert("对不起网络异常！",3000);
			}
		}
		
	});	

}


function insertPinLun(){
	var comments = $("#pinlun").val();
	if(comments.length>140){
		alert("评价字数不能超过140个汉字！");
	}else{
	jQuery.ajax
		({
			type: "post",
			url: ctxPaths + "/insertShiYongPinLun",
			dataType: "json",
			data : {
					comments:comments
				 	},
			cache: false,
			success: function(data){
				if(data.CODE=="FALSE"){
					alert("评价失败！");
				}else if(data.CODE=="TRUE"){
					getMe();
					$("#pinlun").val("");
				}else{
					alert("对不起网络异常！",3000);
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


<div class="my-top-tool jiange-top">
  <span class="headline-back-btn" onclick="javascript:history.back();"><img src="<%=ctxPath %>/resources/images/icon_back.png"  align="absmiddle" /></span>
  <span class="my-top-title">我的活动</span>
</div>

<div class="qiangzhuo-detail jiange-top">
  <div class="ok">
    <div><img src="<%=ctxPath %>/resources/images/shiyonglog.jpg" width="200" height="200" /></div>
    <div class="text">亲，你已成功申请【Jcare澳洲山羊奶保湿滋润发现套装】</div>
    <div class="font-gray">近期我们会通过短信形式通知你领取实物奖品的时间和地点。请注意查收10657520662下发的短信。</div>
  </div>
</div>

    <div  id="queryPinLun">
</div>	

<!--发布评论-->
<div class="clearfix jiange-top">&nbsp;</div>
<div class="clearfix jiange-top">试用评价：</div>
<div class="headline-content-layout">
    <div class="headline-edit-border whiteback">
      <textarea name="" rows="5" class="headline-replay-input" id="pinlun"></textarea>
    </div>
</div>
<div class="shiyong-replay-btn clearfix jiange-top" onclick="javascript:insertPinLun();">发表</div>
<!--发布评论-->

</div>

<div class="qiangzhuo-detail-bottom"></div>
<!--抢桌详情结束-->



<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
</body>
</html>