<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page language="java" import="org.apache.shiro.subject.Subject,org.apache.shiro.SecurityUtils,com.aspire.wifi.wap.util.GetConfigFile;"%>
<%
    Subject currentUser = SecurityUtils.getSubject();
    String mobile="";
   	if(currentUser.isAuthenticated()){
    	 mobile = currentUser.getPrincipal().toString();
    }
    String imageAccessPath=GetConfigFile.getInstance().getProperties("imageAccessPath");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no,minimum-scale=1.0,maximum-scale=1.0" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="../common/include.jsp"%>
 <style type="text/css">
.dan_teletext, .duo_teletext{border-width: 1px; position: relative; cursor: pointer;}
.maskBox{left: 2px; bottom: 2px;}
<%-- 文件上传 开始 --%>
.e_tForm_uploadBtn {
	position: relative;
}

.fileBtn {
	filter: alpha(opacity = 0);
	-moz-opacity: 0;
	opacity: 0;
	width: 60px;
	height: 25px;
	overflow: hidden;
	position: absolute;
	left: 15px;
	top: 5px;
	cursor:pointer;
}
<%-- 文件上传 结束 --%>

<%-- content排版样式--%>
pre { white-space: pre-wrap; /* css-3 */ 
white-space: -moz-pre-wrap; /* Mozilla, since 1999 */ 
white-space: -pre-wrap; /* Opera 4-6 */ 
white-space: -o-pre-wrap; /* Opera 7 */ }

* html pre { word-wrap: break-word; /* Internet Explorer 5.5+ */ 
white-space : normal ; /* Internet Explorer 5.5+ */ }
</style>
<script type="text/javascript">
var mobile='<%=mobile%>';
var url='<%=ctxPath%>';
var activityName="<c:out value="${pinActivityEntity.activityName}"/>";
var activityId="<c:out value="${pinActivityEntity.activityId}"/>";
var quota = "<c:out value="${pinActivityEntity.quota}"/>";
var definenum = "<c:out value="${definenum}"/>";
 var cashSubsidy="<c:out value="${pinActivityEntity.cashSubsidy}"/>";
 var salePersonNum="<c:out value="${pinActivityEntity.salePersonNum}"/>";
 var persionParticipateCnt="<c:out value="${pinActivityEntity.persionParticipateCnt}"/>";
 var pinActionInvalidate="<c:out value="${pinActivityEntity.pinActionInvalidate}"/>";
 var pinCreatetableInvalidate="<c:out value="${pinActivityEntity.pinCreatetableInvalidate}"/>";
var sum=parseInt(cashSubsidy)+parseInt(salePersonNum)+parseInt(persionParticipateCnt)+parseInt(pinActionInvalidate)+parseInt(pinCreatetableInvalidate);
  var limitPicTimes=0;
     var imageAccessPath='<%=imageAccessPath  %>';
     var customCode="";
$(function(){
	 $("#showdizhi").hide();
var contentHtml='';
if(activityId==3){
contentHtml+='<div class="l"><img src="<%=ctxPath%>/resources/images/2128235774.jpg" width="100%"/></div><div class="r">';
contentHtml+='<div class="title">'+activityName+'</div>';
contentHtml+='<div class="renci">累计'+sum+'人抢过</div>';
contentHtml+='<div class="">【折上减】进店即送酸梅汤，7折再减10元，还不心动么？</div>';
contentHtml+='<div class="renci"><span><a href="<%=ctxPath%>/youhuiMapContr">查看位置</a></span></div>';
contentHtml+='</div>';
}else if(activityId==4){
contentHtml+='<div class="l"><img src="<%=ctxPath%>/resources/images/IMG_1237.JPG" width="100%"/></div><div class="r">';
contentHtml+='<div class="title">王林串串香</div>';
contentHtml+='<div class="renci">累计'+sum+'人抢过</div>';
contentHtml+='<div class="">【折上减】8.8折再减10元</div>';
contentHtml+='</div>';
}else if(activityId==5){
contentHtml+='<div class="l"><img src="<%=ctxPath%>/resources/images/cpsddyy.jpg" width="100%"/></div><div class="r">';
contentHtml+='<div class="title">昌平首都电影院</div>';
contentHtml+='<div class="renci">累计'+sum+'人抢过</div>';
contentHtml+='<div class="">凭此页领取30元首都电影院心动券</div>';
contentHtml+='</div>';
}else if(activityId==2){
contentHtml+='<div class="l"><img src="<%=ctxPath%>/resources/images/shiyonglog.jpg" width="100%"/></div><div class="r">';
contentHtml+='<div class="title">'+activityName+'</div>';
contentHtml+='<div class="renci">累计'+sum+'人抢过</div>';
contentHtml+='<div class="">静佳新品珍珂儿面膜疯抢中，共400张，每人限领一张</div>';
contentHtml+='</div>';
}else if(activityId==6){
contentHtml+='<div class="l"><img src="<%=ctxPath%>/resources/images/dingdinglog.jpg" width="100%"/></div><div class="r">';
contentHtml+='<div class="title">'+activityName+'</div>';
contentHtml+='<div class="renci">累计'+sum+'人抢过</div>';
contentHtml+='<div class="">【惊爆】火锅自助原价每人36元，现价每人21元，这个冬天暖暖哒~</div>';
contentHtml+='</div>';
}else if(activityId==9){
contentHtml+='<div class="l"><img src="<%=ctxPath%>/resources/images/htmt.jpg" width="100%"/></div><div class="r">';
contentHtml+='<div class="title">红舵码头</div>';
contentHtml+='<div class="renci">累计'+sum+'人抢过</div>';
contentHtml+='<div class="">双人仅需78元，感受时尚小火锅的魅力~</div>';
contentHtml+='</div>';

	
}
$("#juyouhuidetail").html(contentHtml);

if(activityId==3){
   	$("#juyouhuihuilist").html('<div class="ju-youhui-item-normal"><div class="title">地址</div><div class="intro">昌平区鼓楼东街34号（三层）物美超市往东200米路南</div></div><div class="ju-youhui-item-normal"><div class="title">电话</div><div class="intro">010-57496455</span></div>');
    }
    if(activityId==4){
   	$("#juyouhuihuilist").html('<div class="ju-youhui-item-normal"><div class="title">地址</div><div class="intro">松园路45号政法大学东侧</div></div><div class="ju-youhui-item-normal"><div class="title">电话</div><div class="intro">010-89749118</div></div>');
    }
    if(activityId==5){
   	$("#juyouhuihuilist").html('<div class="ju-youhui-item-normal"><div class="title">地址</div><div class="intro">北京市昌平区南环路金隅万科广场8层</div></div><div class="ju-youhui-item-normal"><div class="title">电话</div><div class="intro">010-60749995</div></div>');
    }
    if(activityId==2){
    }
    if(activityId==6){
   	$("#juyouhuihuilist").html('<div class="ju-youhui-item-normal"><div class="title">地址</div><div class="intro">昌平区国泰商场旁美廉美超市1层（水榭明珠小吃城内）</div></div><div class="ju-youhui-item-normal"><div class="title">电话</div><div class="intro">010-89709200</div></div>');
    }
    if(activityId==9){
     $("#juyouhuihuilist").html('<div class="ju-youhui-item-normal"><div class="title">地址</div><div class="intro">东环路金隅万科广场5楼电梯口处</div></div><div class="ju-youhui-item-normal"><div class="title">电话</div><div class="intro">13910966209</div></div>');
    }
    var locationHref = window.location.href;
    var hrefArray = locationHref.split(window.ctxPaths);
    var ipStr = hrefArray[0];
    var link = ipStr+'<%=ctxPath%>/judetailContr?activityId='+activityId;
    $("#sharelink #textfield").val(link);
});

$(function(){
getYouhuiPinlun();


  //判断用户是否已经领过
  var jsonData={"mobile":mobile,"activityId":activityId};
	jQuery.ajax({
      url : url+"/queryMobileFormShiYong",
      type : "POST",
		 async: false,
      dataType : "json",
      data : jsonData,
    	 success : function(data) {
    	 	if(data.CODE=='FALSE'){
    	 		  $("#showdizhi").show();
    	 		$("#ISORLINGQU div").hide();
    	 		$("#juyouhuihuilist").html("");
    	 		 $("#youhuistatus").html("");
			        if(activityId==3){
   						$("#juyouhuihuilist").html('<div class="ju-youhui-item-normal"><div class="title">地址</div><div class="intro">昌平区鼓楼东街34号（三层）物美超市往东200米路南</div></div><div class="ju-youhui-item-normal"><div class="title">电话</div><div class="intro">010-57496455</span></div>');
			        }
			        if(activityId==4){
   						$("#juyouhuihuilist").html('<div class="ju-youhui-item-normal"><div class="title">地址</div><div class="intro">松园路45号政法大学东侧</div></div><div class="ju-youhui-item-normal"><div class="title">电话</div><div class="intro">010-89749118</div></div>');
			        }
			        if(activityId==5){
			        	 $("#youhuistatus").html('<div class="ju-btn-gray">你已领取，请移驾，看看别的优惠</div>');
			        	
   					$("#juyouhuihuilist").html('<div class="ju-youhui-item-normal"><div class="title">地址</div><div class="intro">北京市昌平区南环路金隅万科广场8层</div></div><div class="ju-youhui-item-normal"><div class="title">电话</div><div class="intro">010-60749995</div></div>');
			        }
			        if(activityId==2){
			              $("#youhuistatus").html('<div class="ju-btn-gray">你已领取，请移驾，看看别的优惠</div>');
			              $("#not2and5").show();
		    	 		    customCode = data.customCode;
			        }
			        if(activityId==6){
   						$("#juyouhuihuilist").html('<div class="ju-youhui-item-normal"><div class="title">地址</div><div class="intro">昌平区国泰商场旁美廉美超市1层（水榭明珠小吃城内）</div></div><div class="ju-youhui-item-normal"><div class="title">电话</div><div class="intro">010-89709200</div></div>');
			        }
			        if(activityId==9){
   						$("#juyouhuihuilist").html('<div class="ju-youhui-item-normal"><div class="title">地址</div><div class="intro">东环路金隅万科广场5楼电梯口处</div></div><div class="ju-youhui-item-normal"><div class="title">电话</div><div class="intro">13910966209</div></div>');
			        }
			        if((activityId!=2)&&(activityId!=5)){
    	 		    $("#youhuistatus").html('<div class="ju-btn-gray">你尚有已领优惠券未使用，请使用完再领，分享给朋友吧</div>');
    	 		    }
    	 		    $("#not2and5").show();
    	 		    customCode = data.customCode;
        	 		}
			       
       	   }
     })
     //判断该活动是否已经领完
     if(quota==0){
         $("#showdizhi").hide();
    	 $("#ISORLINGQU div").hide();
    	 $("#youhuistatus").html("");
    	 $("#juyouhuihuilist").html("");
        if(activityId==5){
            	   $("#youhuistatus").html('<div class="ju-btn-gray">领光了</div>');
        
   			$("#juyouhuihuilist").html('<div class="ju-youhui-item-normal"><div class="title">地址</div><div class="intro">北京市昌平区南环路金隅万科广场8层</div></div><div class="ju-youhui-item-normal"><div class="title">电话</div><div class="intro">010-60749995</div></div>');
        }
        if(activityId==2){
            $("#youhuistatus").html('<div class="ju-btn-gray">领光了</div>');
        
        }
     }
    if(definenum==20){
      if(activityId==2||activityId==5){
          //do nothing
    	    }else{
		   	  $("#showdizhi").hide();
		   	 $("#ISORLINGQU div").hide();
		   	 $("#youhuistatus").html("");
		   	 $("#juyouhuihuilist").html("");
		   	 if(activityId==3){
		            $("#youhuistatus").html('<div class="ju-btn-gray">今天的优惠已领光，明天早点来吧~</div>');
		  			 $("#juyouhuihuilist").html('<div class="ju-youhui-item-normal"><div class="title">地址</div><div class="intro">昌平区鼓楼东街34号（三层）物美超市往东200米路南</div></div><div class="ju-youhui-item-normal"><div class="title">电话</div><div class="intro">010-57496455</div></div>');
			        }
		        if(activityId==4){
		            	   $("#youhuistatus").html('<div class="ju-btn-gray">今天的优惠已领光，明天早点来吧~</div>');
		  					$("#juyouhuihuilist").html('<div class="ju-youhui-item-normal"><div class="title">地址</div><div class="intro">松园路45号城北街政法大学东门斜对面</div></div><div class="ju-youhui-item-normal"><div class="title">电话</div><div class="intro">010-89749118</div></div>');
		        }
		        if(activityId==6){
		            	   $("#youhuistatus").html('<div class="ju-btn-gray">今天的优惠已领光，明天早点来吧~</div>');
		  					$("#juyouhuihuilist").html('<div class="ju-youhui-item-normal"><div class="title">地址</div><div class="intro">昌平区国泰商场旁美廉美超市1层（水榭明珠小吃城内）</div></div><div class="ju-youhui-item-normal"><div class="title">电话</div><div class="intro">010-89709200</div></div>');
		        }
		        if(activityId==9){
		            	   $("#youhuistatus").html('<div class="ju-btn-gray">今天的优惠已领光，明天早点来吧~</div>');
		  					$("#juyouhuihuilist").html('<div class="ju-youhui-item-normal"><div class="title">地址</div><div class="intro">东环路金隅万科广场5楼电梯口处</div></div><div class="ju-youhui-item-normal"><div class="title">电话</div><div class="intro">13910966209</div></div>');
			        }
           }
        }
});
//活动规则
 $(function(){
	 if(activityId==2){
    	 $("#huodongguize").html('<p>使用说明:</p><ol><li class="ju-guize">限量领取：本次活动限量领取400张，每人限领一张</li><li class="ju-guize">领取方法：凭下发短信前往指定地点领取，过时不候</li><li class="ju-guize">面膜提供商为乐蜂网，如出现不良反应请立即停用</li><li class="ju-guize">有效期限：数量有限送完为止 </li></ol>');
		 }
      if(activityId==3){
    	 $("#huodongguize").html('<p>使用说明:</p><ol><li class="ju-guize">请在有效期限内使用，逾期无效</li><li class="ju-guize">任意消费均享受7折优惠，每桌可使用多张10元优惠卷</li><li class="ju-guize">不兑现、不找零，不得与店内其他优惠同时使用</li><li class="ju-guize">限1个用户领用一张，消费使用后方可再次参与活动 </li><li class="ju-guize">免预约，如遇消费高峰时段您可能需要排队 </li><li class="ju-guize">最终解释权归黄记煌满汉焖品所有 </li><li class="ju-guize">有效期限：至 2015-01-01 </li><li class="ju-guize"> 减10元优惠已包含在优惠码及下发短信中，请在付款时向店家出示</li></ol>');
        }
        if(activityId==4){
    	 $("#huodongguize").html('<p>使用说明:</p><ol><li class="ju-guize">请在有效期限内使用，逾期无效</li><li class="ju-guize">任意消费均享受8.8折优惠，每桌可使用多张10元优惠卷</li><li class="ju-guize">不兑现、不找零，不得与店内其他优惠同时使用</li><li class="ju-guize">限1个用户领用一张，消费使用后方可再次参与活动 </li><li class="ju-guize">不适用于酒水、饮料、茶位、纸巾、餐具，外卖打包</li><li class="ju-guize">免预约，如遇消费高峰时段您可能需要排队 </li><li class="ju-guize">最终解释权归王林串串香所有 </li><li class="ju-guize">有效期限：至 2015-01-01 </li><li class="ju-guize">减10元优惠已包含在优惠码及下发短信中，请在付款时向店家出示 </li></ol>');
        }
        if(activityId==5){
    	 $("#huodongguize").html('<p>使用说明:</p><ol><li class="ju-guize">预约信息：无需预约，如遇消费高峰时段您可能需要排队</li><li class="ju-guize">使用规则：凭下发短信前往指定影院享受优惠，不再与店内其他优惠同享</li><li class="ju-guize">3D眼镜：配备3D眼镜，无需您再缴纳押金和其他费用</li><li class="ju-guize">VIP厅、首映式、见面会等特殊场次不可试用</li></ol>');
        }
        if(activityId==6){
    	 $("#huodongguize").html('<p>使用说明:</p><ol><li class="ju-guize">请在有效期限内使用，逾期无效</li><li class="ju-guize">不兑现、不找零，不得与店内其他优惠同时使用</li><li class="ju-guize">限1个用户领用一张，消费使用后方可再次参与活动 </li><li class="ju-guize">免预约，如遇消费高峰时段您可能需要排队 </li><li class="ju-guize">最终解释权归丁丁洋回转自助火锅所有 </li><li class="ju-guize">有效期限：至2015-08-31 </li></ol>');
        }
        if(activityId==9){
    	 $("#huodongguize").html('<p>使用说明:</p><ol><li class="ju-guize">本优惠为双人套餐，套餐包含菜品，详询店内</li><li class="ju-guize">有效期限：至2015-04-10，请在有效期限内使用，逾期无效</li><li class="ju-guize">不可与其他优惠同享 </li><li class="ju-guize">仅限大厅使用，包厢不可用，打包详询商户 </li><li class="ju-guize">限1个用户领用一张，消费使用后方可再次参与活动 </li><li class="ju-guize">免预约，如遇消费高峰时段您可能需要排队 </li><li class="ju-guize">最终解释权归红舵码头所有 </li></ol>');
        }
        $("#idchecksubmit").one("click",function(){
         $("#idchecksubmit").attr("disabled","disabled");
        	if(mobile==''){
        		window.location.href=url+"/login";
        		}else{
        		  var jsonData={"mobile":mobile,"activityId":activityId};
        			 jQuery.ajax({
        		            url : url+"/juyouhuiGet",
        		            type : "POST",
        		   	    	async: false,
        		            dataType : "json",
        		            data : jsonData,
        		          	 success : function(data) {
        		          	 	if(data.CODE=='TRUE'){
        		          	 	
        			          	 	if(data.shiyong!=null){
                                    var customCode=data.shiyong.customCode;
        		              	          suc(activityId,mobile,customCode);
        				          	 	}
        		              	 	 }
        		             	  }
        		        })
        		}

        });
  });

function suc(activityId,mobile,customCode){
 var url_=url+'/youhuiSuccessContr?activityId='+activityId+'&mobile='+mobile+'&customCode='+customCode;
 window.location.href=url_;
}

  function  uploadPrizeFile(obj){
		if(limitPicTimes<4){
			var form = $(obj).parent();
			form.ajaxSubmit({
		 		type : 'post',
		    	url : "<%=ctxPath%>/imageUpload.tv/0",
		    	success:function (result, status) {
					var data = jQuery.parseJSON(result);
					if (data.CODE == "FALSE") {
						alert(data.msg);
					}else if(data.CODE == "TRUE"){
						//当前编辑对象
						var viewhtml='<div class="headline-edit-pic">\n ';
						//	viewhtml += '<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>';
							viewhtml += '<img src="'+imageAccessPath+data.fileName+'" width="100%" alt="..."><div class="headline-edit-pic-del" id="closePic" onclick="closePicss(this);">\n';
							viewhtml +='<img src="<%=ctxPath%>/resources/images/icon_del.png" width="90%"/>\n <input type="hidden" name="picName" value="'+data.fileName+'"/>';
							viewhtml += '</div>';
							$("#pic_viewDiv").prepend(viewhtml);
							limitPicTimes++;
					}else{
						alert("");
					}
				}
			});
			return false;//为了防止刷新	
		}else{
			alert("最多只能传4张图片");
		}
}
/**
**获取图片num
**/
function getPicNum()
{
var str=document.getElementsByName("picName");
var objarray=str.length;
var chestr="";
for (i=0;i<objarray;i++)
{

   chestr+=str[i].value+",";

}
return chestr;
}	
function getclick(){
	document.getElementById("t_file").click();	
}
function closePicss(obj){

    $(obj).parent().remove();
    limitPicTimes--;
}	

function addPinLun(){
		if(mobile==''){
			window.location.href=url+"/login";
		}else{
			  var conetent = $("#textfield2").val();
			  var jsonData={"headlineId":71,"comments":conetent,parentReplyId:0};
				 jQuery.ajax({
			            url : url+'/addComments?picNums='+getPicNum()+'&isYouhui='+activityId,
			            type : "POST",
			   	    	async: false,
			            dataType : "json",
			            data : jsonData,
			          	 success : function(data) {
			          	 alert("评价成功！");
			          	  var url_=url+'/judetailContr?activityId='+activityId;
 							window.location.href=url_;
			             	    }
			          	   });
		}
}
       var ajaxPost = function (url, param, successCallback) {
            $.ajax({
                url: url,
                data: param,
                type: 'post',
                dataType: 'json',
                success: function (result) {
                    successCallback(result);
                }
            });
        };
 var begin =0;//初始查询数








 var times =3;//查询评价条数
 var jiazaitimes=1;//加载次数;
 var totalpage=0;
function getYouhuiPinlun(){
	 var jsonData={"activityId":activityId,begin:parseInt($("#plcurrpage").val())*times,times:times};
				 jQuery.ajax({
			            url : url+"/youhuiPinLun",
			            type : "POST",
			   	    	async: false,
			            dataType : "json",
			            data : jsonData,
			          	 success : function(data) {
			          	 	if(data.CODE=='TRUE'){
				          	 	totalpage=data.totalpage;
			              	 	var list = data.headlineReplyEntitylist;
			              	 	var html="";
			              	 	for(var i=0;i<list.length;i++){
				              	 	html+='<div class="ju-listblock2">'+
										'<div class="ju-pinglun-list">'+										
									    	'<div class="l"><span><img src="'+ctxPaths+'/my/findAttach?mobile='+list[i].mobile+'" width="20" align="absmiddle" /></span><span>'+list[i].nickname+'：</span></div>'+
									        '<div class="r">'+list[i].replyStringDate+'</div>'+
									   ' </div>'+
									    '<div class="ju-pinglun-list-conner"></div>'+
										'<div class="ju-pinglun-list-content">'+
							    	'<div class="text">'+list[i].content+'</div><div class="pic" >';
			                         var replyId = list[i].replyId;
			                          jQuery.ajax({
							            url : url+"/findHeadlineReply?isFlag=1",
							            type : "POST",
							   	    	async: false,
							            dataType : "json",
							            data : {replyId:replyId },
							          	success : function(data) {
				                        	 var picList =data.headlinePicList;
						                       for(var j=0;j<picList.length;j++){			
						                       			html+='<span><img src="<%=ctxPath%>/listHeadlineReplyAttach?pictureSerialnum=' + picList[j].pictureSerialnum + '" width="60" class="lightbox"/></span>\t\t\t';	                       						                           						                      			                       	
						                       }
					                      }
			                   		 	});  	
									    	
									  html+=  '</div></div>'+
									'</div>';
			              	 	}
			              	 	$("#getYouHuiPinLun").append(html);
			              	 	$("#count").html("共有"+data.count+"条评价");

			              	 	if(totalpage==parseInt($("#plcurrpage").val())+1){
			              			$("#dianji").html('<div class="qiangzhuo-more"  >已显示全部评价</div>');
				              	 	}
								addLigthBoxEvent();
			              	}else{
			              	 	alert("获取评论失败");
			              	 	
			                }
			              }
			})
}	

function getMore(){
	if(totalpage>parseInt($("#plcurrpage").val())+1){
	  $("#plcurrpage").val(parseInt($("#plcurrpage").val())+1);
	  getYouhuiPinlun();
	}
}	
//全选复制链接




function HighlightAll(theField) {  
	var link=$("#textfield").val();
	try{
		window.clipboardData.setData("Text", link); 
		phoneAlert("活动链接已复制，转发给朋友吧");
	}catch(e){
		$("#selfUrl").html('<span style="color: white;">'+link+'</span>');
		$("#showUrlDiv").css('display','block');
	}
}
function foot_close(){
	$("#showUrlDiv").css('display','none');
}

//$(function(){
	//if(activityId==5){
 //$("#youhuistatus").hide();
 //$("#ISORLINGQU").show().children("div").remove();
 //$("#ISORLINGQU").append('<div class="ju-btn-red font-big">活动已结束</div>');
// $("#showdizhi").remove();
	//	}
//})


function  qdmcheck(){
	var returnVal = window.confirm("确认消费？","注：确认是否消费此次优惠");
	if(!returnVal) {
		return false;
	}else{
		 var jsonData={"xfms":customCode,"account":$("#qdmcheck").val()};
		  jQuery.ajax({
	          url : "<%=ctxPath%>/checkxiaofeimaController",
	          type : "POST",
	 		 async: false,
	          dataType : "json",
	          data : jsonData,
	        	 success : function(data) {
				if(data.CODE=='TRUE'){
					   var list=data.list;
		      	 	   var list1=data.list1;
		      	 	   var list2=data.list2;
		      	 	   if(list2==customCode){
			      	 	   alert("验证成功！");
			      	 	location.reload();
					   }
		      	 	   if(list==customCode){
		      	 		   alert("你输入的渠道码不存在");
					   }
		      	 	   if(list1==customCode){
                         alert("该优惠码已经使用过");
					   }
					  
	      	 	  }else{
	      	 	   alert("验证失败");
	              	 }
	           	   }
	         });    
	}
 }
</script>
</head>
<body>
<!--头部-->
<!--头部-->
<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
<!--头部结束-->

<!--头部结束-->
<div class="headline-write-bg jiange-top">
    <span class="headline-back-btn" onclick="javascript:history.back();"><img
            src="<%=ctxPath%>/resources/images/icon_back.png" align="absmiddle"/></span>
</div>



<!-- 聚友惠详情 -->	
<div class="ju-listblock ju-bg-conner2" id="juyouhuidetail">
</div>

<div class="ju-listblock" id="juyouhuihuilist">
</div>
<!-- 聚友惠详情 结束 -->

<div class="ju-listblock2" id="ISORLINGQU">
	<div class="ju-btn-red font-big" onclick="" id="idchecksubmit">我要领</div>
</div>
<div class="ju-listblock2" id="youhuistatus">
<!--	<div class="ju-btn-gray">同学，你还有领过的券未使用</div>-->
</div>
<div class="ju-listblock2" id="showdizhi">
	<div class="ju-share">
    	长按下面的链接地址，复制并分享给朋友
    </div>
    <div style="text-align:center;" id="sharelink">
      <label>
        <input name="textfield" type="text" class="ju-pinglun-input" id="textfield" value="" onclick="javascript:HighlightAll()"/>
      </label>
    </div>
</div>
    <center id="not2and5" style="display:none">
<h1>渠道码</h1>
<input type="text" name="qdmcheck" id="qdmcheck"><input type="button" onclick="qdmcheck()" value="验证">
<p>请输入商户渠道码，进行优惠验证</p>
<p>请慎重进行此操作，避免没有获得优惠~</p>
</center>
<div class="ju-listblock2" id="huodongguize">
</div>
<div id="showUrlDiv" style="width: 90%; padding:10px 0; line-height:18px; position: absolute; left:5%; top:50%; z-index: 9999; background: #000; opacity:1; border-radius: 9px; text-align:center; color:#fff; display:none;">
    <div style="position:absolute; width:25px;float:right; right:0px; top:0px; z-index:31;"><a href="#" onclick="foot_close();"><img src="<%=ctxPath%>/resources/images/foot_close.png" width="100%" /></div>
	<div id="selfUrl"></div>
</div>
<!-- 聚友惠评论 -->
<div class="ju-listblock2">
	<div class="ju-pinglun">
        <div class="r" id="count"></div>
    </div>
    <div style="text-align:center;">
      <label>
        <textarea name="textfield" rows="4" class="ju-pinglun-input" id="textfield2"  placeholder="既然这么好就写两句吧："></textarea>
      </label>
    </div>
    <div class="ju-pinglun-btn">
   <div class="form-group">
                <label for="添加照片">&nbsp;&nbsp;</label>
                <div class="e_tForm_uploadBtn">
	                <form name="imageForm" enctype="multipart/form-data" action="<%=ctxPath%>/imageUpload.tv/0" method="post">
						<div class="l"><span class="ju-btn-orange" onclick="getclick();">+上传图片</span></div>
						<input name="fileBox"id="t_file" class="fileBtn" onchange="uploadPrizeFile(this);" type="file">
					</form>
				</div>
      </div>
     
        <div class="r"><span class="ju-btn-red" onclick="addPinLun()">发表评论</span></div>
        </br></br></br>
      <div class="form-group"id="yulanId" >
                <label for="预览">上传图片预览：</label>
                  <div class="col-xs-12" id="pic_viewDiv">  
             
                  </div>         
       </div> 
    </div>
</div>
<!-- 评论列表 -->
<div id="getYouHuiPinLun"></div>
<!-- 评论列表 结束 -->

<!-- 聚友惠评论 结束 -->

<div class="qiangzhuo-more"  id="dianji">
 <input id="plcurrpage" name="page" type="hidden" value="0"/>
 <input id="pltotalpage" name="page" type="hidden" value=""/>
<div class="qiangzhuo-more" onclick="getMore()">下拉查看更多评论...</div>
</div>


<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>

</body>
</html>