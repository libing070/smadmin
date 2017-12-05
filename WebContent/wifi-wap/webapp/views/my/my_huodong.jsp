<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="../common/include.jsp"%>
</head>
<body>
	<!--头部-->
	<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
	<!--头部结束-->
	<div class="my-top-tool jiange-top">
		<span class="headline-back-btn" onclick="javascript:history.back();"><img src="<%=ctxPath%>/resources/images/icon_back.png" align="absmiddle" /></span><span class="my-top-title">我的活动</span>
	</div>
	<!--我的活动-->
	<div class="headline-list clearfix jiange-top">
		<div class="headline-content-layout">
			<div class="headline-edit-border whiteback"></div>
		</div>
	</div>
	<div class="clearfix">
		<a href="<%=ctxPath%>/my/myFoot" class="my-footprint-link"><img src="<%=ctxPath%>/resources/images/icon_footprint.png" width="30" align="absmiddle" />我的足迹</a>
	</div>
	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->

	<script type="text/javascript">
		var listUrl = '<%=ctxPath%>/listMyActives';
		var listParam = {};
		var listTemplate = "<div class=\"my-list my-read\" onclick=\"window.location.href=\'{innerAddress}\'\">\n<div class=\"my-item my-icon-message\">【{activeType}】{activeStatus}-{isOwnerMobile},{activityName}</div>\n<div class=\"my-detail-link\">详情&gt;</div>\n</div>";
		var innerAddressForPCT = "<%=ctxPath%>/huodong/QiangZhuoDetails?flashSaleId={activeId}";
		var innerAddressForSR = "<%=ctxPath%>/yingxin";
		var innerAddressForSHIYONG= "<%=ctxPath%>/shiyongDetail";
		var innerAddressForHUODONG= "<%=ctxPath%>/youhuiDetail?id={id}&activityId={activityId}";
		var innerAddressForZHUANFA= "<%=ctxPath%>/getzhuanfa?isHuodongYe=1";
		var content = $('.whiteback');

		$(document).ready(function() {
			getMyActivities();
		});

		var ajaxPost = function(url, data, successCallback) {
			$.ajax({
				url : url,
				data : data,
				dataType : 'json',
				success : function(data) {
					successCallback(data);
				}
			});
		};
		
		function formatString(content, str, newStr, exParams) {
			var re = new RegExp('\\{' + str + '\\}', 'gm');
			if (exParams != null) {
				var innerRE = new RegExp('\\{innerAddress\\}', 'gm');
				if (str == 'activeType' && newStr == '1') {
					content = content.replace(innerRE, exParams[0]);
				} else if (str == 'activeType' && newStr == '2') {
					content = content.replace(innerRE, exParams[1]);
				} else if (str == 'activeType' && newStr == '3') {
					content = content.replace(innerRE, exParams[2]);
				} else if (str == 'activeType' && newStr == '4') {
					content = content.replace(innerRE, exParams[3]);
				} else if (str == 'activeType' && newStr == '5') {
					content = content.replace(innerRE, exParams[4]);
				}
			}
			return content.replace(re, newStr);
		}
		
		function formatCodeLine(codeLine) {
			var activeType = parseInt(codeLine.charAt(1));
			var activeStatus = codeLine.substr(3, codeLine.lastIndexOf("-") - 3);
			var isOwnerMobile = codeLine.substr(codeLine.lastIndexOf("-") + 1,codeLine.lastIndexOf(","));
			var activityName = codeLine.substr(codeLine.lastIndexOf(",") + 1);
			var codeLineString = '';
			if (activeType == '1') {
				codeLineString += '【抢桌】';
				if (activeStatus == '1') { //发布待审核
					codeLineString += '抢桌创建成功，等待审核';
				} else if (activeStatus == '2') { //已发布(审核通过)
					if (isOwnerMobile == '1') {
						codeLineString += '抢桌创建成功，审核通过 /招募小伙伴';
					} else {
						codeLineString += '等待开桌';
					}
				} else if (activeStatus == '3') { //发布失败(审核不通过)
					codeLineString += '抢桌创建成功，审核驳回';
				} else if (activeStatus == '4') { //已过有效期
					codeLineString += '拼桌过期'; //桌主与拼桌人显示一样
				} else if (activeStatus == '5') { //已成单
					if (isOwnerMobile == '1') {
						codeLineString += '开桌了，等待提交照片';
					} else {
						codeLineString = '开桌了，等待桌主提交并审核照片';
					}
				} else if (activeStatus == '6') { //上传合照待审核
					codeLineString += '开桌了，合照审核中';
				} else if (activeStatus == '7') { //上传合照审核不通过
					codeLineString += '开桌了，合照审核驳回';
				} else if (activeStatus == '8') { //已完成
					codeLineString += '开桌了，合照审核通过'; //桌主与拼桌人显示一样
				} else if (activeStatus == '9') { //已提交消费发票已领补贴
					codeLineString += '已提交消费发票已领补贴';
				} else if (activeStatus == '10') { //拼桌解散
					codeLineString += '拼桌已经解散';
				}
			} else if (activeType == '2') {
				codeLineString += '【校园迎新】';
				//
				if (activeStatus == '0') { //待审核
					codeLineString += '照片已提交，等待审核';
				} else if (activeStatus == '1') { //通过
					codeLineString += '照片审核通过';
				} else if (activeStatus == '2') { //未通过
					codeLineString += '照片审核驳回';
				}
			}else if(activeType=="3"){
				codeLineString += '【试用】';
				codeLineString+='Jcare澳洲山羊奶保湿滋润发现套装';
			}else if(activeType=="4"){
				codeLineString += '【聚友惠】';
				codeLineString+=activityName;
			}else if(activeType=="5"){
				codeLineString += '【聚友惠】';
				codeLineString+='转发活动';
			}
			//
			return codeLineString;
		}
		
		function getMyActivities() {
			this.ajaxPost(listUrl, listParam, function(data) {
				content.html("");
				$.each(data.list, function(index, record) {
					var _listTemplate = listTemplate;
					var _innerAddressForPCT = innerAddressForPCT;
					var _innerAddressForSR = innerAddressForSR;
					var _innerAddressForSHIYONG = innerAddressForSHIYONG;
					var _innerAddressForHUODONG = innerAddressForHUODONG;
					var _innerAddressForZHUANFA = innerAddressForZHUANFA;
					$.each(record, function(key, value) {
						_innerAddressForPCT = formatString(_innerAddressForPCT, key, value, null);
						_innerAddressForSR = formatString(_innerAddressForSR, key, value, null);
						_innerAddressForSHIYONG= formatString(_innerAddressForSHIYONG, key, value, null);
						_innerAddressForHUODONG= formatString(_innerAddressForHUODONG, key, value, null);
						_innerAddressForZHUANFA= formatString(_innerAddressForZHUANFA, key, value, null);
						_listTemplate = formatString(_listTemplate, key, value, [ _innerAddressForPCT, _innerAddressForSR, _innerAddressForSHIYONG,_innerAddressForHUODONG,_innerAddressForZHUANFA]);
					});
					$('.whiteback').append($(_listTemplate));
				});
				$('.my-item').each(function(index, obj) {
					var codeLine = $(obj).text();
					$(obj).text(formatCodeLine(codeLine));
				});
			});
		}
	</script>
</body>
</html>