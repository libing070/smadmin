<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.aspire.wifi.wap.util.GetConfigFile" %>
<!DOCTYPE html">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport" content="width=device-width, user-scalable=yes,minimum-scale=1.0,maximum-scale=2.0" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="../common/include.jsp"%>
<% 
	String flashSaleId = (String) request.getAttribute("flashSaleId");
	if (flashSaleId == null || "".equals(flashSaleId)) {
		flashSaleId = request.getParameter("flashSaleId");
	}
%>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/file.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#shenshuo").click(function() {
			$("#zhankai").toggle();
			$("#shouqi").toggle();
			$("#liebiao").toggle();
		});
		$("#shenshuo2").click(function() {
			$("#zhankai2").toggle();
			$("#shouqi2").toggle();
			$("#activityRuleId").toggle();
		});
		getCounsumePlace();
		getPinZhuoDetails();
		addLigthBoxEvent();//添加图片放大事件
	});

	//获拼桌列表信息
	function getPinZhuoDetails() {
		var flashSaleId = <%= flashSaleId %>;
		var jsonData = {
			"flashSaleId" : flashSaleId
		};
		jQuery.ajax({
					type : "post",
					url : ctxPaths + "/qiangzhuo/getPinZhuoDetails.ajax",
					dataType : "json",
					data : jsonData,
					cache : false,
					success : function(data) {
						if (data.CODE == "FALSE") {
							phoneAlert(data.msg, 3000);
						} else if (data.CODE == "TRUE") {
							//设置拼桌明细
							initPinZhuoDetails(data.pinCreateTableEntity);
							
							//设置拼桌成员名单
							initPinZhuoMemberList(data.listPinZhuoMember, data.loginMobile);
						} else {
							phoneAlert("对不起网络异常！", 3000);
						}
					},
					error : function() {
						phoneAlert("对不起网络异常！", 3000);
					}
				});
	}
	
	function initPinZhuoDetails(pinCreateTableEntity) {
		var actDesc = '<img src=\"<%=ctxPath%>/resources/images/icon_tongzhi.png\" width=\"20\" align=\"absmiddle\" />';
		$("#actDesc").html(actDesc + pinCreateTableEntity.actDesc);
		$("#nickName").html(pinCreateTableEntity.nickName);
		$("#ownerMobile").html(pinCreateTableEntity.ownerMobile);
		$("#freeSeat").html(pinCreateTableEntity.freeSeat);	
		$("#expiredDate").html("截止日期：" + pinCreateTableEntity.expireDateStringCN);
		if (pinCreateTableEntity.auditComment != null && pinCreateTableEntity.auditComment != "" && pinCreateTableEntity.actStatusId == 7) {
			$("#uploadComment").html("");
			$("#auditComment").html("驳回理由：" + pinCreateTableEntity.auditComment);
		}
		$("#freeSeat").html(pinCreateTableEntity.freeSeat);
		
		// 获取用户头像
        $("#zzHeadShowPic").html('<img src=\"<%=ctxPath%>/my/findAttach?mobile=' + pinCreateTableEntity.ownerMobile + '\" width=\"50\" height=\"50\" class=\"lightbox\" />');
	}
	
	function initPinZhuoMemberList(listPinZhuoMember, loginMobile) {
		var pinZhuoListHtml = "";
		pinZhuoListHtml += '<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#e6f0ff">';
		pinZhuoListHtml += '	<tr><td width="50%" align="center" bgcolor="#e6f0ff">用户名</td><td align="center" bgcolor="#e6f0ff">手机号</td></tr>';
		
		if (listPinZhuoMember.length > 0) {
			for ( var i = 0; i < listPinZhuoMember.length; i++) {
				if (loginMobile == listPinZhuoMember[i].mobile)	{
					pinZhuoListHtml += '	<tr><td align="center" bgcolor="#FFFFFF"><span class="font-red">' + listPinZhuoMember[i].nickName + '</span></td><td align="center" bgcolor="#FFFFFF"><span class="font-red">' + listPinZhuoMember[i].mobile + '</span></td></tr>';	
				} else {
					pinZhuoListHtml += '	<tr><td align="center" bgcolor="#FFFFFF">' + listPinZhuoMember[i].nickName + '</td><td align="center" bgcolor="#FFFFFF">' + maskMobilePhone(listPinZhuoMember[i].mobile) + '</td></tr>';	
				}			
			}
		}
		pinZhuoListHtml += '</table>';	
		$("#listPinZhuoMember").html(pinZhuoListHtml);
	}
	
	function photoAudit() {
		var flashSaleId = <%= flashSaleId %>;
		var jsonData = {
			"flashSaleId" : flashSaleId
		};
		jQuery.ajax({
					type : "post",
					url : ctxPaths + "/qiangzhuo/photoAudit.ajax",
					dataType : "json",
					data : jsonData,
					cache : false,
					success : function(data) {
						if (data.CODE == "FALSE") {
							phoneAlert(data.msg, 3000);
						} else if (data.CODE == "TRUE") {
							window.location.href= ctxPaths + "/huodong/QiangZhuoDetails?flashSaleId=" + flashSaleId;
						} else {
							phoneAlert("对不起网络异常！", 3000);
						}
					},
					error : function() {
						phoneAlert("对不起网络异常！", 3000);
					}
				});
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
			
			//显示遮罩
			$("body").showLoading();
			
			//提交预览图片请求
			var options = {
				success: function(data){
					$("body").hideLoading();
					var data = jQuery.parseJSON(data);
					if(data.CODE=="FALSE"){
						alert(data.msg);
						//alert(data.msg,3000);
					}else if(data.CODE=="TRUE"){
						//刷新下页面	
						$("#actImage").attr("src","<%=ctxPath%>/qiangzhuo/getQianZhuoImage?data=" + new Date() + Math.floor(Math.random() * 24) + "&flashSaleId=<%=flashSaleId%>");

						alert(data.msg);
					} else {
						alert("上传图片失败了！");
					};
				},
				error: function(xhr) {
			    	//去除遮罩
			    	$("body").hideLoading();
					alert("上传图片失败了！");
			    }
			};
			$('#imageForm').ajaxSubmit(options);
			return false;
		};
		//查询活动的消费地点
	function getCounsumePlace(){
		jQuery.ajax({
			url : ctxPaths + "/getCounsumePlace",
			type : "POST",
			dataType : "json",
			success : function(data) {
			    var consumerList=data.cslist;
			    var consumerhtml="";
				if(data.CODE=="TRUE"){
			    consumerhtml+='<table width="98%" border="0" align="center" cellpadding="3"';
				consumerhtml+='cellspacing="1" bgcolor="#60bbff">';
				consumerhtml+='<tr class="font-white2">';
				consumerhtml+='<td width="10%" align="center" bgcolor="#5396ff">序号</td>';
				consumerhtml+='<td width="10%" align="center" bgcolor="#5396ff">地点</td>';
				consumerhtml+='<td align="center" bgcolor="#5396ff">地址</td>';
				consumerhtml+='<td align="center" bgcolor="#5396ff">联系电话</td>';
				consumerhtml+='</tr>';
				for(var i=0;i<consumerList.length;i++){
					consumerhtml+='<tr>';
					consumerhtml+='<td align="center" bgcolor="#FFFFFF">'+consumerList[i].consumePlaceId+'</td>';
					consumerhtml+='<td align="center" bgcolor="#FFFFFF">'+consumerList[i].consumePlaceName+'</td>';
					consumerhtml+='<td align="center" bgcolor="#FFFFFF">'+consumerList[i].consumePlaceAddr+'</td>';
					consumerhtml+='<td align="center" bgcolor="#FFFFFF">'+consumerList[i].dinnerPhone+'</td>';
				    consumerhtml+='</tr>';
				}
				consumerhtml+='</table>';
				
				$("#liebiao").html(consumerhtml);
			 }
			}
		});
	}
</script>
</head>
<body id="qianZhuoDetailsDIV">
	<!--头部-->
	<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
	<!--头部结束-->
	<div class="qiangzhuo-detail jiange-top">
		<div class="l">
			<div id="zzHeadShowPic"></div>
			<div id="nickName" class="name"></div>
			<div id="ownerMobile" class="phone"></div>
		</div>
		<div class="r">
			<div id="actDesc" class="content"></div>
		</div>
	</div>

	<div class="qiangzhuo-detail">
		<div class="qiangzhuo-partner-title">
			<span>入伙人</span>
		</div>
		<div id="listPinZhuoMember"></div>
	</div>
		 
	<!--抢桌详情-->
	<div class="qiangzhuo-detail jiange-top">
		<div class="ok">
			<div id="uploadComment" class=""><img src="<%=ctxPath%>/resources/images/icon_photo_shenhe.png" width="20" height="20" align="absmiddle" />上传合照，通过审核后，大家的人生就圆满了~</div>
			<div id="auditComment" class="font-red"></div>
		</div>
		<div class="qiangzhuo-photo" align="center">
			<img id="actImage" alt="" src="<%=ctxPath%>/resources/images/icon_uploadpic.jpg" width="100%" />
		</div>
		<div class="qiangzhuo-photo-shadow" id="actImage">
			<img src="<%=ctxPath%>/resources/images/qiangzhuo_photo_shadow.png" width="100%" />
		</div>
		<div>
			<form  id="imageForm" name="formName" enctype="multipart/form-data" action="<%=ctxPath%>/qiangzhuo/uploadQianZhuoImage?flashSaleId=<%= flashSaleId %>" method="post" >
				<input name="fileBox" class="uploadImageFile" type="file" id="t_file"  onchange="javascript:selectImage(this);"/>
			</form>
		</div>
		<div class="qiangzhuo-photo-upload" id="uploadImage"><a href="#">换一张上传</a></div>
		<div class="btn">
			<span class="join" onclick="javascript:photoAudit();">提交审核</span>
		</div>
	</div>
	<!--抢桌详情结束-->
	<script>
		$(function(){
			$("#uploadImage").click(function(){
				document.getElementById("t_file").click();														
			});
			
			$("#actImage").click(function(){
				document.getElementById("t_file").click();														
			});
		});
	</script>
		<div class="boldfont clearfix">
			<label id="shenshuo"><span id="zhankai" class="font-blue">查看消费地点<img
					src="<%=ctxPath%>/resources/images/icon_down.png" align="absmiddle"
					width="20" />
			</span><span id="shouqi" class="font-blue" style="display:none;">收起列表<img
					src="<%=ctxPath%>/resources/images/icon_up.png" align="absmiddle"
					width="20" />
			</span>
			</label>
	</div>
	   <!-- 查询活动的展示区 -->
		<div id="liebiao" style="display:none;">
			
		</div>
	<div></p></div>
	<div class="boldfont clearfix">
			<label id="shenshuo2"><span id="zhankai2" class="font-blue">查看活动规则<img
					src="<%=ctxPath%>/resources/images/icon_down.png" align="absmiddle"
					width="20" />
			</span><span id="shouqi2" class="font-blue" style="display:none;">收起活动规则列表<img
					src="<%=ctxPath%>/resources/images/icon_up.png" align="absmiddle"
					width="20" />
			</span>
			</label>
	</div>

	<div style="display:none" id="activityRuleId">
	 <div class="jiange-top"><img src="<%=ctxPath%>/resources/images/icon_guize01.png"  /></div> 
	  <div class="row">
	           <ol>
	            <li>抢桌周一至周五，每天提供10个抢桌名额，11:00-14:00开抢，一周共50个。</li>
	            <li>抢单自创建之日起5天有效期，即：周一抢单周五到期，周五抢单下周二到期，依此类推，抢单拼单内容一经建立，不可取消。</li>
	            <li>每人每周限参与1单抢单和1单拼单，4人成单，抢单人可对拼单人进行删除管理；拼单人有权退出拼单。</li>
	            <li>人数少于4人，或抢单／拼单超过有效期的，抢／拼单自动失效，可继续参与下一次抢单／拼单活动。</li>
			  </ol>
	
	  </div>
	  <div><img src="<%=ctxPath%>/resources/images/icon_guize02.png"  /></div> 
	  <div class="row">
	        <ol>
	            <li>即抢单人在指定范围消费可获得50元超市现金卡奖励。</li>
	            <li>成功消费后，抢单人需上传合影，审核通过后，抢单人／拼单人可继续参与下一次抢单／拼单活动。（这一步直接影响到你盒你的小伙伴的利益，要重视哦～）</li>
			  </ol>
	
	  </div>
  </div>
	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->
</body>
</html>