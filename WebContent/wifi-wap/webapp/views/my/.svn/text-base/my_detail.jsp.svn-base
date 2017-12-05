<%@ page language="java" import="com.aspire.wifi.wap.util.GetConfigFile;" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	String is_headShow = request.getAttribute("IsHeadShow")==null?"":request.getAttribute("IsHeadShow").toString();
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport" content="width=device-width, user-scalable=yes,minimum-scale=1.0,maximum-scale=2.0" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="/views/common/include.jsp"%>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/my.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/file.js"></script>
<script>
var is_headShow='<%=is_headShow%>';
$(document).ready(function (){   
	getMyUserInfo(2);
	addLigthBoxEvent();//添加图片放大事件
	$("#radio1").click(function(){
			$("#radio1").attr({checked: "checked"});
			$("#radio2").attr({checked:false });
			if(is_headShow=="0"){
				$("#myHeadShowId").html('<img src="'+ctxPaths+'/resources/images/nan.png" width="50" height="50" />');
			}
	})
	$("#radio2").click(function(){
			$("#radio1").attr({checked: false });
			$("#radio2").attr({checked:"checked"});
			if(is_headShow=="0"){
				$("#myHeadShowId").html('<img src="'+ctxPaths+'/resources/images/nv.png" width="50" height="50" />');
			}
			
	})


})

</script>
</head>
<body>
	<!--头部-->
	<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
	<!--头部结束-->
<div class="jiange-dashline"></div>
<div class="login-block clearfix"><table width="100%" border="0" cellspacing="6" cellpadding="6">
  <tr>
    <td width="60">头像：</td>
    <td><div id="myHeadShowId"><img src="<%=ctxPath%>/my/findAttach"class="lightbox" width="50" height="50" /></div>

<div id="fromId">
 <form  id="imageForm" name="formName" enctype="multipart/form-data" action="<%=ctxPath%>/my/uploadImage" method="post" >
	<input name="fileBox" class="uploadImageFile" type="file" id="t_file"  onchange="selectImage(this);"/>
 </form>
 </div>
      <div id = "uploadImage" class="btn-confirm content-btn" style="text-align:center;" ><a href="#" class="font-blue">上传头像</a></div>
<script >
	$(function(){
		$("#uploadImage").click(function(){
			document.getElementById("fromId").style.display="";
			document.getElementById("t_file").click();	
			document.getElementById("fromId").style.display="none";		
		})
	})


/*得到预览图片路径*/
function selectImage(target, id){
$("body").showLoading();
	//检查文件类型和大小, 单位:M
			var maxSize = <%=GetConfigFile.getInstance().getProperties("maxUploadPicSize")%>;
			var result = checkFile(target, id, maxSize);
			if (result == "FILE_TYPE_ERROR") {
				alert("图片类型错误, 请重新选择图片!",3000);
				return false;
			} else if (result == "FILE_SIZE_ERROR") {
				alert("附件大小不能大于" + maxSize / (1024 * 1024)+ "M！",3000);
				return false;
			} else if (result == "EMPTY_FILE") {
				alert("图片文件错误, 请重新选择图片！",3000);
				return false;
			}
		//提交预览图片请求
		var options = {
			success: function(data){
			var s = data.toString();
		if(s.indexOf("TRUE") > 0 )
			{
			editUserInfo(2);
			//去除遮罩
		$("body").hideLoading();
			}else{
			alert("上传图片失败！");
			}
		}
		};
		$("#imageForm").ajaxSubmit(options);
		return false;
		
}
</script>  
    

    </td>
  </tr>
  <tr>
    <td>手机号：</td>
    <td class="phone"></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>昵称：</td>
    <td ><input type="text" name="textfield2" id="textfield2" class="login-input" value=""/></td>
  </tr>
  <tr>
    <td>性别：</td>
    <td><span class="icon-male">
      <label>
        <input name="radio" type="radio" id="radio1" value="男" checked="checked" />
      </label>
      男</span> <input id="radio2" type="radio" value="女" /><span class="icon-female">女</span></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>籍贯：</td>
    <td>
    <div id="selectId">
    	<span><select name="select" id="select" onchange="searchCityNameList()"></select></span>     
		<span><select name="select2" id="select2"></select></span>     
	</div>
    </td>
    <td>&nbsp;</td>
  </tr>
</table>
  <div class="btn-block jiange-top"><span class="btn-blue" onclick="editUserInfo(1);">保存资料</span></div> 
</div>



<div style="height:80px;" class="clearfix"></div>
	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->

</body>
</html>