<%@ page import="java.util.*" pageEncoding="UTF-8"%>
<%
	Date date = new Date();
%>
<script type="text/javascript">
var index = "<c:out value="${index}"/>";
var activityId ="<c:out value="${activityId}"/>";
var leftsecond = 0; 
var sh;
//菜单点击样式
function chanageStyle(){
	$("#footerMenu .menu-border").each(function(ind){
		$("#submenu_"+ind).removeClass("submenuact");
		$("#submenu_"+ind).addClass("submenu");
	});
	$("#submenu_"+index).addClass("submenuact");
	$("#submenu_"+index).removeClass("submenu");
}

	function butInfo(){
		var jsonData = {"activityId":activityId};
		jQuery.ajax({
         url : ctxPaths +"/getQiangDanDetails",
         type : "POST",
		 async: false,
         dataType : "json",
         data : jsonData,
       	 success : function(data) {
       	 	if(null!=data){
       	 		var list = data.list;
	       	 	var activity = list[0];
	       	 	var startHour = activity.startHour;
	       	 	var endHour = activity.endHour;
	       	 	var startWeekDay = activity.startWeekDay;
       	 		var endWeekDay = activity.endWeekDay;
				var day=<%=date.getDay()%>;
				var h=<%=date.getHours()%>;
				var spanHtml_ = "";
				if(day>=startWeekDay&&day<=endWeekDay){
					if(h>=startHour&&h<endHour){
						spanHtml_+="<span class='btn-bg' id='qiang_btn' onclick='qiangzhuoInfo()'>";
						spanHtml_+="我要抢桌主 <img src='<%=ctxPath%>/resources/images/bg_qiangzhuo_arrow.png' align='absmiddle' />";
						spanHtml_+="</span>";
					}else{
						spanHtml_+="<span class='btn-bg' id='qiang_btn' onclick='pinzhuoInfo()'>";
						spanHtml_+="我要拼桌<img src='<%=ctxPath%>/resources/images/bg_qiangzhuo_arrow.png' align='absmiddle' />";
						spanHtml_+="</span>";
					}
				}else{
					spanHtml_+="<span class='btn-bg' id='qiang_btn' onclick='pinzhuoInfo()'>";
					spanHtml_+="我要拼桌<img src='<%=ctxPath%>/resources/images/bg_qiangzhuo_arrow.png' align='absmiddle' />";
					spanHtml_+="</span>";
				}
				$("#qiangzhuoId").html(spanHtml_);
       	 	}
       	 }
       	 })
    }
	
	/** 抢桌 */
	function qiangzhuoInfo(){
	    if($("#msisdnWelcome").text()=="?"){
			window.location.replace(ctxPaths+"/login");
		} 
		var jsonData = {"activityId":activityId};
		jQuery.ajax({
         url : ctxPaths +"/doQiangDan",
         type : "POST",
		 async: false,
		 dataType : "json",
		 data : jsonData,
       	 success : function(data) {
			if(data.CODE=="FALSE"){
	  		 	phoneAlert(data.msg);
		  		return;
			}else{
				var everyDayCount=data.everyDayCount;
       	 		if(data.CODE=="TRUE"){
       	 	 		if(data.count<=0||data.historyCount<=0){
       	 	 		    alert("恭喜您，抢单成功，请马上创建你的拼桌，否则就失效了。")
       	 	 		     window.location.replace(ctxPaths+"/huodong/guize");
       	 	 		    
       	 	 		}else{
       	 	 			alert("恭喜您，抢单成功，请马上创建你的拼桌，否则就失效了。")
    	 	 		  		window.location.replace(ctxPaths+"/huodong/qiangzhuoSucess");
       	 	 		}
      	 		}else if(data.CODE="COUNTOVER"){
       	 	 			alert("对不起，今天的桌已抢完，请您明天再来！")
      	 			 window.location.replace(ctxPaths+"/huodong/qiangzhuo_fail");
       	 			 return;
       	       }
			}	
		 }
		});
	}
	
	/**拼桌pinzhuoCountPlus**/
	function pinzhuoInfo(){
		var jsonData = {"activityId":activityId};
		jQuery.ajax({
         url : ctxPaths +"/pinzhuoCountPlus",
         type : "POST",
		 async: false,
		 dataType : "json",
		 data : jsonData,
       	 success : function(data) {
			window.location.href=ctxPaths+"/huodong/QiangZhuoList";   	 
		 }
		});
	}

	/** 光荣榜滚动数据 */
	function searchHonorList(){
	/**	jQuery.ajax({
         url : ctxPaths +"/pinDanList",
         type : "POST",
		 async: false,
       	 success : function(data) {
       	 	if(data.CODE=="TRUE"){
	       	 	var list = data.pindanlist;
	       	 	if(null!=list){
					var dataHtml = "";
					//dataHtml+="<ul>";
		       	 	for(var i in list){
		       	 		var pin = list[i];
		       	 		if(pin.isOwnerMobile==1){
							dataHtml+="<li>"+pin.mobile+"&nbsp;&nbsp;"+pin.createDate+"&nbsp;&nbsp;抢桌成功"+"</li>";
		       	 		}else{
							dataHtml+="<li>"+pin.mobile+"&nbsp;&nbsp;"+pin.createDate+"&nbsp;&nbsp;拼桌成功"+"</li>";
		       	 		}
		       	 	}
		       	 	//dataHtml+="</ul>";
					if(list.length>2){
			       	 	$("#newGlory").html(dataHtml);
					}
		       	 	$("#cloneGlory").html(dataHtml);
	       	 	}
       	 	}else{
       	 		phoneAlert(data.msg,1000);
       	 	}
		 }
		})*/
		
		$("#newGlory").html("1、抢桌当桌主，招唤三个小伙伴加入，完成流程可得50元超市现金卡！</br>2、拼桌找到小伙伴的空桌加入，坐等桌主约你开饭！");
	}
	
	/** 已参加人数 */
	function searchQDtotalCount(){
		jQuery.ajax({
         url : ctxPaths +"/getQiangDanTotalCount",
         type : "POST",
		 async: false,
         dataType : "json",
       	 success : function(data) {
       	 	if(data.CODE=="TRUE"){
	       	 	var count = data.activityCount;
	       	 	var spanHtml = "";
	       	 	for(var j=0;j<count.length;j++){
	       	 		spanHtml+="<span>"+count[j]+"</span>";
	       	 	}
	       	 	$("#spanId").html("已有"+spanHtml+"人次参加");
       	 	}else{
       	 		phoneAlert(data.msg,1000);
       	 	}
       	 }
       	 })
	}
	
	var dateTime=<%=date.getTime()%>;
	function searhTimeInfo(startHour,startWeekDay,endWeekDay,endHour,quota){
   	 	dateTime+=1000;
   	 	var weekDay = ["周日", "周一","周二","周三","周四","周五","周六"];
   	 	$("#notes1").html("("+weekDay[startWeekDay]+"至"+weekDay[endWeekDay]+"每天"+quota+"个名额，"+startHour+":00-"+endHour+":00限时开抢。)");
		var day=<%=date.getDay()%>;
		var h=<%=date.getHours()%>;
        if(day>=startWeekDay&&day<=endWeekDay&&h>=startHour&&h<endHour){
	        for (var i = 1; i <= 5; i++) {
	            var endtime = new Date();
	            endtime.setHours(endHour,0,0);//此方法是时，分，秒格式
	            leftsecond = (endtime.getTime() - dateTime);
	            var d = parseInt(leftsecond / 1000 /60 /60 / 24, 10);//天
	            var h = parseInt(leftsecond / 1000 / 60 / 60 % 24, 10);//时
	            var m = parseInt(leftsecond / 1000 / 60 % 60, 10);//分
	            var s = parseInt(leftsecond / 1000 % 60, 10);//秒
	            d = checkTime(d);
	            h = checkTime(h);
	            m = checkTime(m);
	            s = checkTime(s);
	            $("#timeId").html("距结束剩余："+h + ":" + m + ":" + s +"(<a href='<%=ctxPath%>/huodong/QiangZhuoList'>点此进拼桌流程</a>)");
	            if (leftsecond <= 0) {
	            	$("#timeId").html("");
	            	clearInterval(sh);
					return false;
	            }
	        }
			function checkTime(i){
				if (i < 10) {    
				    i = "0" + i;    
				}    
				return i;    
			}
        }else{
        	$("#timeId").html("");
        }
	}
	
	var startHour,startWeekDay,endWeekDay,endHour,quota;
	/**抢桌倒计时*/
	function fresh(){
		var jsonData = {"activityId":activityId};
		jQuery.ajax({
         url : ctxPaths +"/getQiangDanDetails",
         type : "POST",
		 async: false,
         dataType : "json",
         data : jsonData,
       	 success : function(data) {
       	 if(null!=data){
       	 	var list = data.list;
       	 	var activity = list[0];
       	 	startHour = activity.startHour;
      	 	startWeekDay = activity.startWeekDay;
      	 	endWeekDay = activity.endWeekDay;
      	 	endHour = activity.endHour;
      	 	ruleDesc = activity.ruleDesc;
      	 	quota = activity.quota;
       	 	searhTimeInfo(startHour,startWeekDay,endWeekDay,endHour,quota);
       	 }else{
       	 	phoneAlert(data.msg,1000);
       	 }
       	 }
       })
    sh = setInterval("searhTimeInfo("+startHour+","+startWeekDay+","+endWeekDay+","+endHour+","+quota+")", 1000);
	}
	
</script>
<!--抢桌活动-->
<div class="qiangzhuo" id="qiang">
	<div class="top">
		<img src="<%=ctxPath%>/resources/images/qiangzhuo01.jpg" width="100%" />
		<div class="num" id="spanId">
			已有
			<span>0</span>人次参加
		</div>
		<div class="notes1" id="notes1">
			<%--(周一至周五每天10个名额，11:00-14:00限时开抢。)--%>
			 
		</div>
		<div class="btn">
			<!-- 点击抢桌 -->
			<div id="qiangzhuoId"></div>
		</div>
		<div class="time" id="timeId"></div>
		<div class="bangdan">
			<div class="">
			<!-- 	光荣榜： -->
			小贴示：
			</div>
			<div id="gloryId" class="content">
				<div id="newGlory"></div>
				<div id="cloneGlory"></div>
			</div>
		</div>
		<div class="notes2">
			注：每人每周仅限参与一次抢桌和一次拼桌哦～
		</div>
	</div>
</div>
<!--抢桌活动结束-->
<script>
var speed = 100;
var gloryId = document.getElementById("gloryId");
var cloneGlory = document.getElementById("cloneGlory");
var newGlory = document.getElementById("newGlory");
cloneGlory.innerHTML = newGlory.innerHTML;
function Marquee() {
	if (newGlory.scrollHeight - gloryId.scrollTop <= 0) {
		gloryId.scrollTop -= newGlory.scrollHeight;
	} else {
		gloryId.scrollTop++;
	}
}
var MyMar = setInterval(Marquee, speed);
</script>