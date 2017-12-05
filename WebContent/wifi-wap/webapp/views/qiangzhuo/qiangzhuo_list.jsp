<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  String mobile = request.getAttribute("mobile") == null ? "" : request.getAttribute("mobile").toString(); %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport" content="width=device-width, user-scalable=yes,minimum-scale=1.0,maximum-scale=2.0" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="../common/include.jsp"%>
<script type="text/javascript">
	//当前加载次数
	var totalLoadCount = 1;
	//每次加载数据量
	var loadRecordCount = 3;
	var mobile='<%=mobile %>';
	$(document).ready(function() {
		getPinZhuoList(totalLoadCount, loadRecordCount);
	});

	//获拼桌列表信息
	function getPinZhuoList(totalLoadCount, loadRecordCount) {
		var queryParm = $("#queryParm").val();
		if (queryParm == "请输入桌主名称") {
			queryParm = "";
		}
		var jsonData = {
			"totalLoadCount" : totalLoadCount,
			"loadRecordCount" : loadRecordCount,
			"queryParm" : queryParm
		};
		jQuery.ajax({
					type : "post",
					url : ctxPaths + "/qiangzhuo/queryQiangZhuoList.ajax",
					dataType : "json",
					data : jsonData,
					cache : false,
					success : function(data) {
						if (data.CODE == "FALSE") {
							phoneAlert(data.msg, 3000);
						} else if (data.CODE == "TRUE") {
							var list = data.pinZhuoList;
							var pinZhuoListHtml = "";

							if (list.length > 0) {
								for ( var i = 0; i < list.length; i++) {
									var actType = list[i].actTypeId;
									var actTypeImg = "icon-canyin";
									if (actType == 1) {
										actTypeImg = "icon-canyin";
									} else if (actType == 2) {
										actTypeImg = "icon-tiyu";
									} else if (actType == 3) {
										actTypeImg = "icon-yule";
									}
									
									var joinnedPercent = (list[i].joinnedMemberCount / (list[i].freeSeat + list[i].joinnedMemberCount)) * 100;
									pinZhuoListHtml += '<!--抢桌列表-->';
									pinZhuoListHtml += '<div class="qiangzhuo-list jiange-top clearfix">';
									pinZhuoListHtml += '	<div class="l">';
									pinZhuoListHtml += '		<div class="' + actTypeImg + '" onclick="javascript:gotoPinZhuoDetails(' + list[i].flashSaleId + ')"><img src="<%=ctxPath%>/resources/images/icon_mask.png" height="' + joinnedPercent + '%" width="100%" class="waterline" /></div>';
									pinZhuoListHtml += '	</div>';
									pinZhuoListHtml += '	<div class="r">';
									pinZhuoListHtml += '		<div class="content" onclick="javascript:gotoPinZhuoDetails(' + list[i].flashSaleId + ')">' + list[i].actDesc + '<a href="QiangZhuoDetails?flashSaleId=' + list[i].flashSaleId + '" class="font-red qiangzhuo-list-detail">详情</a></div>';
									pinZhuoListHtml += '		<div class="clearfix">';
									pinZhuoListHtml += '			<div class="date">倒计时：<span class="font-red">' + list[i].expiredDays + '天</span></div>';
									pinZhuoListHtml += '			<div class="remain">可加：<span class="font-red">' + list[i].freeSeat + '人</span></div>';
									pinZhuoListHtml += '		</div>';
									if (data.pinZhuoLimit == "TRUE") {
										pinZhuoListHtml += '		<div class="join clearfix"></div>';
									} else {
										if(mobile!=list[i].mobile){
											pinZhuoListHtml += '<div class="join clearfix"><a href="javascript:joinPinZhuo(\'' + list[i].flashSaleId + '\')" class="font-blue btn">加入</a></div>';
										}
									}
									pinZhuoListHtml += '	</div>';
									pinZhuoListHtml += '	<div class="clearfix userinfo jiange-top"><span>' + list[i].nickName + '</span><span>' + list[i].createTableDateString + '</span></div>';
									pinZhuoListHtml += '</div>';
									pinZhuoListHtml += '<!--抢桌列表结束-->';
								}
							}

							$("#pinZhuoList").html(pinZhuoListHtml);
						} else {
							phoneAlert("对不起网络异常！", 3000);
						}
					},
					error : function() {
						phoneAlert("对不起网络异常！", 3000);
					}
				});
	}
	
	function gotoPinZhuoDetails(flashSaleId) {
		window.location.href = ctxPaths + "/huodong/QiangZhuoDetails?flashSaleId=" + flashSaleId;
	}
	
	//加入拼桌
	function joinPinZhuo(flashSaleId) {
		var jsonData = {
			"flashSaleId" : flashSaleId
		};
		jQuery.ajax({
					type : "post",
					url : ctxPaths + "/qiangzhuo/joinPinZhuo.ajax",
					dataType : "json",
					data : jsonData,
					cache : false,
					success : function(data) {
						if (data.CODE == "FALSE") {
							phoneAlert(data.msg, 3000);
						} else if (data.CODE == "TRUE") {
							window.location.href = ctxPaths + "/huodong/QiangZhuoJoinSuccess";
						} else {
							phoneAlert("对不起网络异常！", 3000);
						}
					},
					error : function() {
						phoneAlert("对不起网络异常！", 3000);
					}
				});
	}
	
	// 查看更多拼桌信息
	function loadMorePinZhuoList() {
		totalLoadCount ++;
		
		//重新加载拼桌信息
		getPinZhuoList(totalLoadCount, loadRecordCount);
	}
	
	function clearInitQueryParm() {
		var queryParm = $("#queryParm").val();
		if (queryParm == "请输入桌主名称") {
			$("#queryParm").val("");
		}
	}
	
	function resetQueryParm() {
		var queryParm = $("#queryParm").val();
		if (queryParm == "") {
			$("#queryParm").val("请输入桌主名称");
		} 
	}
	
	function searchPinZhuoList() {
		//设置默认值
		totalLoadCount = 1;
		loadRecordCount = 3;
		
		getPinZhuoList(totalLoadCount, loadRecordCount);
	} 
</script>
</head>
<body>
	<!--头部-->
	<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
	<!--头部结束-->
	<!--抢桌-->
	<div class="qiangzhuo" id="qiang_success">
		<div class="top">
			<img src="<%=ctxPath%>/resources/images/qiangzhuo03.jpg" width="100%" />
			<div class="join-pin">
				<img src="<%=ctxPath%>/resources/images/qiangzhuo_pin.png"
					width="100%" />
			</div>
			<div class="search">
				<input id="queryParm" name="queryParm" type="text" value="请输入桌主名称" style="float:left;" onmousedown="javascript:clearInitQueryParm()" onblur="javascript:resetQueryParm()"/> <span
					style="float:right;"><img
					src="<%=ctxPath%>/resources/images/icon_search.png"
					align="absmiddle" onclick="javascript:searchPinZhuoList();"/>
				</span>
			</div>
		</div>
	</div>
	<!--抢桌结束-->
	<div class="qiangzhuo-list-title"></div>
	<div id="pinZhuoList"></div>
	<div class="qiangzhuo-more" onclick="javascript:loadMorePinZhuoList()">点击查看更多</div>
	<div style="height:80px;"></div>
	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->

</body>
</html>