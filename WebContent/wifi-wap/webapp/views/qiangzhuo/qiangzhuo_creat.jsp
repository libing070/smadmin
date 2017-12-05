<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="org.apache.shiro.subject.Subject,org.apache.shiro.SecurityUtils;"%>
<%
    Subject currentUser = SecurityUtils.getSubject();
    String mobile = currentUser.getPrincipal().toString();
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, user-scalable=yes,minimum-scale=1.0,maximum-scale=2.0" id="viewport" />

<title>中国移动免费WIFI上网</title>
<%@ include file="../common/include.jsp"%>
<link href="<%=ctxPath%>/resources/css/css.css" rel="stylesheet" />
</head>
<script type="text/javascript">
var activityId ="<c:out value="${activityId}"/>";
	$(document).ready(function() {
		$("#shenshuo").click(function() {
			$("#zhankai").toggle();
			$("#shouqi").toggle();
			$("#liebiao").toggle();
		});
		
		
		 $("#actTypeId").val(1);
		queryIdByMobile();
		queryIndividual();
		getCounsumePlace();
	});
	
	function queryIdByMobile(){
		var mobile=$("#ownerMobile").text();
		 var jsonData={"mobile":mobile};
		 jQuery.ajax({
         url : ctxPaths +"/queryIdByMobile",
         type : "POST",
		 async: false,
		 dataType : "json",
       	 data:jsonData,
		 success : function(data) {
       	   var  list=data.list;
       	   var flashSaleId=0;
       	   for(var i=0;i<list.length;i++){
       		 flashSaleId=list[i].flashSaleId;
       	   }
       	   $("#flashSaleId").val(flashSaleId);
       	   $("#foods").bind("click",function(){
       		   $("#actTypeId").val(1);
       		   $("#foods").removeClass().addClass("qiangzhuo-creat-sort-selected");
       		    $("#sports").removeClass().addClass("qiangzhuo-creat-sort");
       		     $("#fun").removeClass().addClass("qiangzhuo-creat-sort"); 
       	   });
       	    $("#sports").bind("click",function(){
       		   $("#actTypeId").val(2);
       		    $("#sports").removeClass().addClass("qiangzhuo-creat-sort-selected");  
       		     $("#fun").removeClass().addClass("qiangzhuo-creat-sort"); 
       		      $("#foods").removeClass().addClass("qiangzhuo-creat-sort"); 
       	   });
       	    $("#fun").bind("click",function(){
       		   $("#actTypeId").val(3);
       		  $("#fun").removeClass().addClass("qiangzhuo-creat-sort-selected");  
       		  $("#sports").removeClass().addClass("qiangzhuo-creat-sort"); 
       		  $("#foods").removeClass().addClass("qiangzhuo-creat-sort"); 
       	   });
		 }
 	});
	}


	//发布拼单
	function createTable() {
		var actDesc = $("#actDesc").val();
		if(actDesc.length<=0){
		 alert("文本框内容为空，请重新输入！");
		}else{
			var flashSaleId = $("#flashSaleId").val();
			//var ownerMobile = $("#ownerMobile").text();
			var actTypeId = $("#actTypeId").val();
			var jsonData = {
				
				"flashSaleId" : flashSaleId,
				"actDesc" : actDesc,
				//"ownerMobile" : ownerMobile,
				"actTypeId" : actTypeId,
				"activityId" : activityId
			};
			jQuery.ajax({
						url : ctxPaths + "/createPinZhuo",
						type : "POST",
						async : false,
						dataType : "json",
						data : jsonData,
						success : function(data) {
							if (data.CODE == "TRUE") {
								updateCreateDate();
								window.location.replace(ctxPaths + "/huodong/qiangzhuoCreatSucess");
							} else {
								alert("发布拼单失败,请稍后重试!", 3000);
							}
						},
						error : function() {
							alert("数据加载失败,请稍后重试!", 3000);
						}
	
					});
		}
	}

	//用于更新抢单的创建桌的时间
	function updateCreateDate() {
		var flashSaleId = $("#flashSaleId").val();
		var ownerMobile = $("#ownerMobile").text();
		var jsonData = {
			"flashSaleId" : flashSaleId,
			"ownerMobile" : ownerMobile
		};
		jQuery.ajax({
			url : ctxPaths + "/updateCreateDate",
			type : "POST",
			async : false,
			dataType : "json",
			data : jsonData,
			success : function(data) {
				if (data.CODE == "TRUE") {
				} else {
				}
			}
		});
	}

	//文本框输入字符，限制字符递减
	function textCounter(field, countfield, maxlimit) {
	 	
	 	if(field.value.indexOf(" ")>=0){ 
	  		field.value =  field.value.replace(/(^\s*)|(\s*$)/g, "");
	 	}
		if(field.value.length > maxlimit) {
			field.value = field.value.substring(0, maxlimit);
		} else {
			countfield.value = maxlimit - field.value.length;
		}
	}
	
	
	
	//加载个人的信息（例如昵称）
	function queryIndividual() {
		var mobile = $("#ownerMobile").html();
		var jsonData = {
			"mobile" : mobile
		};
		jQuery.ajax({
			url : ctxPaths + "/queryUser",
			type : "POST",
			dataType : "json",
			data : jsonData,
			success : function(data) {
			    var sex=data.sex;
			    var headShow=data.headShow;
				var nickName = data.nickName;
				if (data.CODE == "TRUE") {
					 if(headShow==null && sex=="男"){
			    	 $("#imageHead").attr('src',ctxPaths+'/resources/images/nan.png');
			         }else if(sex=="女"){
			        $(headShow==null && "#imageHead").attr('src',ctxPaths+'/resources/images/nv.png');
			         }else{
			         $("#imageHead").attr('src',ctxPaths+'/queryUserHeadShow;');
			         }
					$("#nickName").html(nickName);
				} 
			}
		});
	}
	
	
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
<body>
    <input type="hidden" value="" id="flashSaleId"  />
    <input type="hidden" value="" id="actTypeId"  />
	<!--头部-->
	<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
	<!--头部结束-->
	<!--抢桌详情-->
	<div class="qiangzhuo-detail jiange-top">
		<div class="qiangzhuo-creat-title">创建我的拼单</div>
	</div>

	<div class="qiangzhuo-detail">
		<div class="l">
			<div>
				<img id="imageHead" src="<%=ctxPath%>/resources/images/nan.png" style="height:66px; width:66px;" />
			</div>
			<div class="name" id="nickName"></div>
			<div class="phone" id="ownerMobile"><%= mobile %></div>
		</div>
		<div class="r">
		<form action="">
			<div>
				<textarea name="message" rows="4" class="qiangzhuo-creat-input" id="actDesc"  onKeyDown="textCounter(message,remLen,31);" onKeyUp="textCounter(message,remLen,31);"></textarea>
				<img src="<%=ctxPath%>/resources/images/icon_ok.png"
					align="absmiddle" />
			</div>
			<div class="qiangzhuo-creat-inputtext">还可输入<input name="remLen" type="text" value="31" size="1" readonly="readonly" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px;color:gray;width:20px' >个字</div>
		</form>
		</div>
	</div>

	<div class="qiangzhuo-detail">
		<div class="boldfont">请选择类型：</div>
		<div class="clearfix">
			<label class="qiangzhuo-creat-sort-selected" id="foods"><img
				src="<%=ctxPath%>/resources/images/icon_qiangzhuo_canyin.png"
				width="60" /><br />
			<span>餐饮</span>
	<!--  	</label> <label class="qiangzhuo-creat-sort" id="sports"><img
				src="<%=ctxPath%>/resources/images/icon_qiangzhuo_tiyu.png"
				width="60" /><br />
			<span>运动</span>
			</label> <label class="qiangzhuo-creat-sort" id="fun"><img
				src="<%=ctxPath%>/resources/images/icon_qiangzhuo_yule.png"
				width="60" /><br />
			<span>KTV</span>
			</label>-->	
		</div>
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

		<div class="btn">
			<span class="join"
				onclick="javascript:createTable();">发布拼单</span>
		</div>
	</div>
	<div class="qiangzhuo-detail-bottom"></div>
	<!--抢桌详情结束-->



	<div style="height:80px;"></div>
	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->

</body>
</html>