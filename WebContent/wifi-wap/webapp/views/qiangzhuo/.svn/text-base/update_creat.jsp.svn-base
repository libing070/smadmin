<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page language="java" import="org.apache.shiro.subject.Subject" %>
<%@ page language="java" import="org.apache.shiro.SecurityUtils" %>
<%

	String flashSaleId = (String) request.getAttribute("flashSaleId");
	if (flashSaleId == null || "".equals(flashSaleId)) {
	flashSaleId = request.getParameter("flashSaleId");
	}

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
		
		queryIndividual();
		getCounsumePlace();
		loadIndividualData();
		
		
		
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
	});
	
	
	
	//将未审核的单个的信息给取出来
		function loadIndividualData(){	
				var flashSaleId = $("#flashSaleId").val();
				var jsonData={"flashSaleId":flashSaleId};
				jQuery.ajax({
					url : ctxPaths + "/showCreateTableByMobile",
					type : "POST",
					async : false,
					dataType : "json",
					data : jsonData,
					success : function(data) {
					if(data.CODE=="TRUE"){
						var actDesc = data.actDesc;
			          var actTypeId=data.actTypeId;
			          var auditComment=data.auditComment;
			          $("#actDesc").val(actDesc);
			          $("#actTypeId").val(actTypeId);
			          $("#feedBackwords").html(auditComment);
			          if(actTypeId==1){
			        	$("#foods").removeClass().addClass("qiangzhuo-creat-sort-selected");
       		   			$("#sports").removeClass().addClass("qiangzhuo-creat-sort");
       		     		$("#fun").removeClass().addClass("qiangzhuo-creat-sort"); 
			          }else if(actTypeId==2){
			           $("#sports").removeClass().addClass("qiangzhuo-creat-sort-selected");  
       		           $("#fun").removeClass().addClass("qiangzhuo-creat-sort"); 
       		           $("#foods").removeClass().addClass("qiangzhuo-creat-sort");  
			          }else{
		        	   $("#fun").removeClass().addClass("qiangzhuo-creat-sort-selected");  
     		 		   $("#sports").removeClass().addClass("qiangzhuo-creat-sort"); 
       		  		   $("#foods").removeClass().addClass("qiangzhuo-creat-sort"); 
			          }
					  }
					},
					error : function() {
						alert("数据加载失败,请稍后重试!", 3000);
					}
				});
		
	}


	//更新拼单
	function updateTable() {
		var flashSaleId = $("#flashSaleId").val();
		var actDesc = $("#actDesc").val();
		var actTypeId = $("#actTypeId").val();
		var jsonData = {
			"flashSaleId" : flashSaleId,
			"actDesc" : actDesc,
			"actTypeId" : actTypeId
		};
		jQuery.ajax({
					url : ctxPaths + "/updateCreateTableByMobile",
					type : "POST",
					async : false,
					dataType : "json",
					data : jsonData,
					success : function(data) {
			        if(data.CODE=="TRUE"){
			        	 if(confirm("拼单已经修改完成,请等待审核...")){
			            	window.location.href = ctxPaths + "/index";
			            }else{
			            	
			            }
			        }
					},
					error : function() {
						alert("数据加载失败,请稍后重试!", 3000);
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
    <input type="hidden" value="<%= flashSaleId %>" id="flashSaleId"  />
    <input type="hidden" value="" id="actTypeId"  />
	<!--头部-->
	<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
	<!--头部结束-->
	<div id="zz" style="width: 90%; padding:10px 0; line-height:18px; position: absolute; left:5%; bottom:15px; z-index: 9; background: #000; opacity:0.5; border-radius: 6px; text-align:center; color:#fff; display:none">操作失败</div>
	<!--抢桌详情-->
	<div class="qiangzhuo-detail jiange-top">
		<div class="qiangzhuo-creat-title">修改我的拼单</div>
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
	<!--  		</label> <label class="qiangzhuo-creat-sort" id="sports"><img
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
        <div style="font-size: 12px;color: red;text-align: center;">审核意见:<span id="feedBackwords" style="font-size: 12px;"></span></div>
		<div class="btn">
			<span class="join"
				onclick="javascript:updateTable();">修改拼单</span>
		</div>
	</div>
	<div class="qiangzhuo-detail-bottom"></div>
	<!--抢桌详情结束-->


	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->

</body>
</html>