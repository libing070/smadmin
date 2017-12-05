<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
<script type="text/javascript">
	$(document).ready(function() {
		getPinZhuoDetails();
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
	
	function initPinZhuoDetails() {
		$("#actDesc").html(pinCreateTableEntity.actDesc);
		$("#nickName").html("抢单人：" + pinCreateTableEntity.nickName + "（" + pinCreateTableEntity.ownerMobile + "）");
		$("#freeSeat").html(pinCreateTableEntity.freeSeat);	
		$("#expiredDate").html("拼单截止日期：" + pinCreateTableEntity.expireDateStringCN);
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
</script>
</head>
<body>
	<!--头部-->
	<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
	<!--头部结束-->
	<div class="my-top-tool jiange-top">
		<span class="headline-back-btn" onclick="javascript:history.back();"><img
			src="<%=ctxPath%>/resources/images/icon_back.png" align="absmiddle" />
		</span>
	</div>

	<!--抢桌详情-->
	<div class="qiangzhuo-detail jiange-top">
		<div class="ok qiangzhuo-stamp">
			<div>
				<img src="<%=ctxPath%>/resources/images/icon_qiangzhuo_ok.png"
					width="95" height="95" />
			</div>
			<div class="text">主人，审核通过了！</div>
		</div>
		<div id="nickName" class="boldfont"></div>
		<div id="expiredDate" class="boldfont"></div>
		<div id="actDesc" class="detail"></div>
		<div class="qiangzhuo-photo">
			<img src="<%=ctxPath%>/qiangzhuo/getQianZhuoImage?flashSaleId=<%=flashSaleId%>" width="100%" />
		</div>
		<div class="qiangzhuo-photo-shadow">
			<img src="<%=ctxPath%>/resources/images/qiangzhuo_photo_shadow.png" width="100%" />
		</div>
	</div>

	<div class="qiangzhuo-detail-bottom"></div>
	<!--抢桌详情结束-->



	<div style="height:80px;" class="clearfix"></div>
	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->

</body>
</html>