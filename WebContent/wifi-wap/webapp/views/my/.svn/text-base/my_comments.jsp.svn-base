<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%	
	String isMySend = request.getAttribute("isMySend")==null?"1":request.getAttribute("isMySend").toString();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport" content="width=device-width, user-scalable=yes,minimum-scale=1.0,maximum-scale=2.0" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="/views/common/include.jsp"%>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/my.js"></script>
</head>
<script type="text/javascript">
var isMySend='<%=isMySend%>';
var pageCount= 3;//每次加载数量
var replymySendTimes =1;//记忆我发出的加载数
var replyCommentsTimes =1;//记亿所有评论的加载数
function getReplay(isReplay){
	isMySend=isReplay;
	if(isMySend=="0"){
	$("#loginStep1").attr("class","login-step-normal");
	$("#loginStep0").attr("class","login-step-active");
	replyCommentsTimes=1;//初始化所有评论的加载数
	}else{
	$("#loginStep0").attr("class","login-step-normal");
	$("#loginStep1").attr("class","login-step-active");
	replymySendTimes=1;//初始化我发出的加载数
	}
	getReplyInfo(0,3,isReplay);
}
$(document).ready(function(){
  $("#getMoreId").click(function(){
  //	getMore(1,isMySend);
  	var pageCount1 = pageCount;
 	 if(isMySend=="0"){
  		replymySendTimes++;
  		pageCount1= pageCount*replymySendTimes;
  	}else{
  		replyCommentsTimes++;
  		pageCount1= pageCount*replyCommentsTimes;
  	}
		getReplyInfo(0,pageCount1,isMySend);
  	
  });  
 getReplyInfo(0,3,isMySend);
 	if(isMySend=="0"){
	$("#loginStep1").attr("class","login-step-normal");
	$("#loginStep0").attr("class","login-step-active");
	}else{
	$("#loginStep0").attr("class","login-step-normal");
	$("#loginStep1").attr("class","login-step-active");
	}
});

</script>

<body>
<!--头部-->
	<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
	<!--头部结束-->
<!--头条-->
<div class="my-top-tool jiange-top">
  <span class="headline-back-btn" onclick="javascript:history.back();"><img src="<%=ctxPath %>/resources/images/icon_back.png"  align="absmiddle" /></span>
  <span class="my-top-title">评论</span>
</div>
<div class="login-step">
  <div class="login-step-block" onclick="getReplay(1)">
    <div class="login-step-active" id="loginStep1" >所有评论</div>
  </div>
  <div class="login-step-block"  onclick="getReplay(0)">
    <div class="login-step-normal" id="loginStep0" >我发出的</div>
  </div>
</div>
  

<div id="replyContentId"></div>
  


<div class="qiangzhuo-more clearfix jiange-top" id="getMoreId">点击查看更多...</div>

	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->

</body>
</html>