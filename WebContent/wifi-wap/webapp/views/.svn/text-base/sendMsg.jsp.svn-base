<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="common/include.jsp"%>
<script type="text/javascript">
function sendMsg(){
var mobileList = $("#mobileList").val();
var tempCode = $("#tempCode").val();
var tempCode2 = $("#tempCode2").val();
var tempCode4 = $("#codeList").val();
	var jsonData = {mobileList:mobileList,tempCode:tempCode};
	  		jQuery.ajax
				({
					type: "post",
					url: ctxPaths + "/sendMobileMsg?tempCode2="+tempCode2+"&tempCode4="+tempCode4,
					dataType: "json",
					data : jsonData,
					cache: false,
					success: function(data){
						if(data.CODE=="TRUE"){
							alert("发送成功");
						}else{
							alert("发送失败");
						}
					}
				});

}

</script>
</head>

<script type="text/javascript">

</script>
 
<body>
<jsp:include page="app_head.jsp" flush="true"></jsp:include>

      <div class="item">
                        <div class="title"id="headcontentID">发送的手机号码：</div>
                        <div class="content">
                            <textarea name="content" id="mobileList" rows="10" class="input" placeholder="发送的手机号码（多个号码用半角的逗号隔开）"></textarea>
                        </div>
        </div>
      <div class="item">
                        <div class="title"id="headcontentID">选择对应的活动(抢票或试用活动)</div>
                        <div class="content">
                        	<select id="tempCode2">
							  <option value ="1">抢票</option>
							  <option value ="2">试用活动</option>
							</select>
                        </div>
        </div>
      <div class="item">
                        <div class="title"id="headcontentID">选择对应的模板(抢票用)</div>
                        <div class="content">
                        	<select id="tempCode">
							  <option value ="1">活动资格的模板</option>
							  <option value ="2">抢票中奖的模板</option>
							</select>
                        </div>
        </div>
      <div class="item">
                        <div class="title"id="headcontentID">选择对应的模板(试用用)</div>
                        <div class="content">
                        	 <textarea name="content" id="codeList" rows="10" class="input" placeholder="试用品对应的编码（多个编码用半角的逗号隔开和上面的手机号对应）"></textarea>
                        	
                        </div>
        </div>
        
        
             <div class="item">
                        <div class="headline-edit-btn clearfix" ><a href="#"onclick="sendMsg()" >发送</a></div>
              </div>
<jsp:include page="app_footer.jsp" flush="true"></jsp:include>
</body>
</html>
