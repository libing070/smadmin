<%@ page import="com.aspire.wifi.wap.util.GetConfigFile;" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="common/include.jsp"%>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/file.js"></script>
</head>

<script type="text/javascript">
$(document).ready(function(){
  $("#title01").click(function(){
  $("#content01").toggle();
  $("#arrow01").toggle();
  $("#arrow01_1").toggle();
  });
  
  $("#title02").click(function(){
  $("#content02").toggle();
  $("#arrow02").toggle();
  $("#arrow02_1").toggle();
  });
  
});
</script>
 
<body>
<jsp:include page="app_head.jsp" flush="true"></jsp:include>

<div class="my-top-tool jiange-top">
  <span class="headline-back-btn" onclick="javascript:history.back();"><img src="<%=ctxPath %>/resources/images/icon_back.png"  align="absmiddle" /></span>
  <span class="my-top-title">我的活动</span>
</div>

<div class="qiangzhuo-detail jiange-top" id="smile_tip">
  <div class="ok">
    <div><img src="<%=ctxPath %>/resources/images/icon_smile.png" width="83" height="83" /></div>
    <div class="text">上传报道照片,赢取百兆流量卡和iPad mini实物好礼!<br />天天开奖,根本停不下来！</div>
  </div>
</div>

<div class="qiangzhuo-detail jiange-top">
  <div class="ok" id="notpass">
    <div class=""><img src="<%=ctxPath %>/resources/images/icon_my_verify.png"  align="absmiddle" /><span id="needuploadmsg">上传照片可参加抽奖哦！</span></div>
  </div>
  
   <div class="ok" id="auditing" style="display: none">
    <div class=""><img src="<%=ctxPath %>/resources/images/icon_photo_shenhe.png" width="30" height="30" align="absmiddle" />照片审核中，稍安勿躁啊...</div>
   </div>
   
   <div class="ok" id="pass" style="display: none">
    <div class=""><img src="<%=ctxPath %>/resources/images/icon_qiangzhuo_ok.png" width="30" height="30" align="absmiddle" />审核通过了，坐等大奖砸到你的脑袋上吧～</div>
   </div>
  
  <div class="qiangzhuo-photo"><img id="rpimg" src="<%=ctxPath%>/getReportPic" width="100%" class="lightbox"/></div>
  <div class="qiangzhuo-photo-shadow"><img src="<%=ctxPath %>/resources/images/qiangzhuo_photo_shadow.png" width="100%" /></div>
  
  <div>
	 <form  id="imageForm" name="formName" enctype="multipart/form-data" action="<%=ctxPath%>/uploadImage" method="post" >
		<input name="fileBox" class="uploadImageFile" type="file" id="t_file"  onchange="selectImage(this);"/>
	 </form>
  </div>
  <div class="qiangzhuo-photo-upload" id="uploadImage"><a href="#">换一张上传</a></div>
  <div class="btn" id="uploadSubmit"><span class="join" onclick="javascript:uploadSubmit();">提交审核</span></div>
  <div class="btn font-red" id="picTip">注：照片审核通过后就可参加抽奖哦</div>
</div>

<script >
	$(function(){
		addLigthBoxEvent();//添加图片放大事件
		
		$("#uploadImage").click(function(){
			document.getElementById("t_file").click();							
		//	document.getElementById("imageForm").submit();							
		})
	})
	
	var uploadSuc = false;
	function uploadSubmit(){
		if(uploadSuc){
			jQuery.ajax({
		         url : "<%=ctxPath%>/commitUploadPic?data=" + new Date() + Math.floor(Math.random() * 24),
		         type : "POST",
		         cache: false,
		        //async:false, 
		         dataType : "json",	
		       	 success : function(data) {
				     if(data.CODE =="FALSE") {
				    	 phoneAlert(data.msg,3000);
			         }else if(data.CODE =="TRUE"){
			        	 //window.location.replace(ctxPaths+"/yingxin");
			        	$("#auditing").show();
			        	$("#smile_tip").show();
		 	     		$("#notpass").hide();
		 	     		$("#pass").hide();
		 	     		$("#uploadImage").hide();
			 	     	$("#uploadSubmit").hide();
			 	     	$("#picTip").hide();
			 	     	phoneAlert(data.msg,3000);
			        }else{
			        	 phoneAlert("对不起、网络异常!",3000);
			         }
		        }
		      });
		}else{
			phoneAlert("请先上传图片再提交！",3000);
		}
	}
/*得到预览图片路径*/
function selectImage(target, id){
	//检查文件类型和大小, 单位:M
	var maxSize = <%=GetConfigFile.getInstance().getProperties("maxUploadPicSize")%>;
	var result = checkFile(target, id, maxSize);
	if (result == "FILE_TYPE_ERROR") {
		phoneAlert("图片类型错误, 请重新选择图片!",3000);
		return false;
	} else if (result == "FILE_SIZE_ERROR") {
		phoneAlert("附件大小不能大于" + maxSize / (1024 * 1024)+ "M！",3000);
		return false;
	} else if (result == "EMPTY_FILE") {
		phoneAlert("图片文件错误, 请重新选择图片！",3000);
		return false;
	}
		
	//提交预览图片请求
	var options = {
		success: function(data){
			$("body").hideLoading();
		    var data = jQuery.parseJSON(data);
			if(data.CODE=="FALSE"){
				phoneAlert(data.msg,3000);
				//alert(data.msg,3000);
			}else if(data.CODE=="TRUE"){
			//刷新下页面	
				$("#rpimg").attr("src","<%=ctxPath%>/getReportPic?data=" + new Date() + Math.floor(Math.random() * 24));
				uploadSuc = true;
				phoneAlert(data.msg,3000);
				//alert(data.msg,3000);
			}else{
				//alert("对不起网络异常！",3000);
				phoneAlert("对不起网络异常！",3000);
			}
		},
		error: function(xhr) {
	    	//去除遮罩
	    	$("body").hideLoading();
			alert("上传图片失败了！");
	    }
	};
	
	$("body").showLoading();
	$('#imageForm').ajaxSubmit(options);
	return false;
}
	
function getStudentReport(){
	jQuery.ajax({
        url : "<%=ctxPath%>/getStudentReportInfo",
        type : "POST",
        cache: false,
       //async:false, 
        dataType : "json",	
      	 success : function(data) {
		     if(data.CODE =="FALSE") {
		    	 phoneAlert(data.msg,3000);
	         }else if(data.CODE =="TRUE"){
	        	 var result = data.result;
	        	 var result_desc = data.result_desc;
	        	 if(result == 0){//待审核
	        		$("#auditing").show();
	 	     		$("#notpass").hide();
	 	     		$("#pass").hide();
	 	     		$("#uploadImage").hide();
		 	     	$("#uploadSubmit").hide();
		 	     	$("#picTip").hide();
	        	 }else if(result == 3){//未提交
	        		$("#smile_tip").hide();
	        		$("#notpass").show();
		 	     	$("#auditing").hide();
		 	     	$("#pass").hide();
		 	     	uploadSuc = true;
	        	 }else if(result == 2){//未通过
	        		 $("#smile_tip").hide();
	        		 $("#notpass").show();
			 	     $("#auditing").hide();
			 	     $("#pass").hide();
			 	     if(typeof(result_desc) == "undefined" || result_desc == null ){
			 	    	$("#needuploadmsg").text("审核不通过！");
			 	     }else{
			 	    	$("#needuploadmsg").text(result_desc);
			 	     }
			 	     
	        	 }else{//审核通过
	        		 $("#pass").show();
			 	     $("#auditing").hide();
			 	     $("#notpass").hide();
			 	    $("#uploadImage").hide();
		 	     	$("#uploadSubmit").hide();
		 	     	$("#picTip").hide();
	        	 }
	        }else{
	        	 phoneAlert("对不起、网络异常!",3000);
	         }
       }
     });
}

getStudentReport();
</script>  
  <div style="height:80px;"></div>
<jsp:include page="app_footer.jsp" flush="true"></jsp:include>
</body>
</html>
