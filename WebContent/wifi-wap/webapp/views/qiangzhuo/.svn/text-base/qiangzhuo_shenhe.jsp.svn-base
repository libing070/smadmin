<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
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
		$("#actDesc").html(pinCreateTableEntity.actDesc);
		$("#nickName").html("抢单人：" + pinCreateTableEntity.nickName + "（" + pinCreateTableEntity.ownerMobile + "）");
		$("#expiredDate").html("拼单截止日期：" + pinCreateTableEntity.expireDateStringCN);
	}
</script>
</head>
<body>
	<!--头部-->
	<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
	<!--头部结束-->

	<div class="my-top-tool jiange-top">
		<span class="headline-back-btn" onclick="javascript:history.back();"><img src="<%=ctxPath%>/resources/images/icon_back.png" align="absmiddle" />
		</span>
	</div>

	<!--抢桌详情-->
	<div class="qiangzhuo-detail jiange-top">
		<div class="ok">
			<div>
				<img src="<%=ctxPath%>/resources/images/icon_smile.png" width="95" height="95" />
			</div>
			<div class="text">您已成功抢桌！正在等待审核</div>
		</div>
		<div id="nickName" class="boldfont"></div>
		<div id="actDesc" class="detail"></div>
		<div id="expiredDate" class="boldfont"></div>
	</div>

	<div class="qiangzhuo-detail-bottom"></div>
	<!--抢桌详情结束-->

	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->

</body>
</html>