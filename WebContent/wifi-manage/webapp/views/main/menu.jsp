<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>北京移动wifi分发管理平台</title>
<script type="text/javascript">
$(document).ready(function(){
						   
	/* 滑动/展开 */
	$("ul.expmenu li > div.header").click(function(){
												   
		var arrow = $(this).find("span.arrow");
	
		if(arrow.hasClass("up")){
			arrow.removeClass("up");
			arrow.addClass("down");
		}else if(arrow.hasClass("down")){
			arrow.removeClass("down");
			arrow.addClass("up");
		}
	
		$(this).parent().find("ul.menu").slideToggle();
		
	});
	
});
</script>
<script language="JavaScript">
<!--//
function ShowMenu(obj,n){
 var Nav = obj.parentNode;
 if(!Nav.id){
  var BName = Nav.getElementsByTagName("ol");
  var HName = Nav.getElementsByTagName("h2");
  var t = 2;
 }else{
  var BName = document.getElementById(Nav.id).getElementsByTagName("span");
  var HName = document.getElementById(Nav.id).getElementsByTagName(".header");
  var t = 1;
 }
 for(var i=0; i<HName.length;i++){
  HName[i].innerHTML = HName[i].innerHTML.replace("-","+");
  HName[i].className = "";
 }
 obj.className = "h" + t;
 for(var i=0; i<BName.length; i++){if(i!=n){BName[i].className = "no";}}
 if(BName[n].className == "no"){
  BName[n].className = "";
  obj.innerHTML = obj.innerHTML.replace("+","-");
 }else{
  BName[n].className = "no";
  obj.className = "";
  obj.innerHTML = obj.innerHTML.replace("-","+");
 }
}
//-->
</script>
<style>
.menu ol { padding-left:15px; border:#cde6f2 1px solid; border-top:none;background: #cde6f2;}
.menu li i{background-color: #cde6f2;padding: 1px 4px;color: #fff;text-shadow: 0px 0px 0px rgba(255, 255, 255, 0.8);font-family:"微软雅黑"; font-style:normal;}
.menu a{color: #3f3f3f;text-decoration: none;}
.menu .no {display:none;}
.menu ol a{width: 228px;display: block;line-height: 2em;margin-left: 20px;}
</style>
</head>

<body style="background-color:#F3FAFA">

	<ul class="expmenu">
		<li>
			<div class="header">
				<span class="label" ><img src="<%=ctxPath%>/resources/images/icon_menu01.png" width="20" align="absmiddle" /> 功能</span>
				<span class="arrow up"></span>
		  </div>
			<span class="no">
			<ul class="menu" style="display:block;">
				<a href="<%=ctxPath%>/activity.tv" target="mainFrame"><li>活动管理</li></a>
                <a href="<%=ctxPath%>/publish.tv" target="mainFrame"><li>头条发布管理</li></a>
            
			<!--<li  onClick="javascript:ShowMenu(this,3)"><a href="javascript:void(0)"><i>+</i>  其他系列</a></li>
			<ol class="no">
             <a href="javascript:void(0)">一体坐便器</a>
             <a href="javascript:void(0)">一体坐便器</a>
  			</ol>-->
		</ul>
		</span>
		</li>
		<li>
			<div class="header">
				<span class="label"><img src="<%=ctxPath%>/resources/images/icon_menu02.png" width="20" align="absmiddle" /> 管理</span>
				<span class="arrow down"></span>
			</div>
			<ul class="menu">
				<a href="<%=ctxPath%>/video.tv" target="mainFrame"><li>视频管理</li></a>
                <a href="<%=ctxPath%>/app.tv" target="mainFrame"><li>应用管理</li></a>
			</ul>
		</li>
	</ul>
</div>
</body>
</html>