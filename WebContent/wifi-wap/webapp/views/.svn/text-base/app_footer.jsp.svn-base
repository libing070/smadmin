<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String ctxPath=request.getContextPath();
	String index = (String) request.getAttribute("index");
	if (index == null || "".equals(index)) {
		index = request.getParameter("index");
	}
%>
<script>
	var index = '<%= index %>';
	
	//菜单点击样式
	function chanageStyle() {
		$("#footerMenu .menu-border").each(function(ind) {
			$("#submenu_" + ind).removeClass("submenuact");
			$("#submenu_" + ind).addClass("submenu");
		});
		$("#submenu_" + index).addClass("submenuact");
		$("#submenu_" + index).removeClass("submenu");
	}

	$(function() {
		chanageStyle();
	});
	function getUrl(ind){
		if(ind==0){
			window.location.href='<%=ctxPath%>/index?index=0';
			document.getElementById("img_0").src = "<%=ctxPath%>/resources/images/icon_menu_ju_act.png";
		}else if(ind==2){
			window.location.href='<%=ctxPath%>/toutiao/toutiaoIndex?index=2';
			document.getElementById("img_2").src = "<%=ctxPath%>/resources/images/icon_menu_headline_act.png";
			
		}else if(ind==3){
			window.location.href='<%=ctxPath %>/find/findIndex?index=3';
			document.getElementById("img_3").src = "<%=ctxPath%>/resources/images/icon_menu_find_act.png";

		}else if(ind==4){
			window.location.href='<%=ctxPath%>/my/myIndex?index=4';
			document.getElementById("img_4").src = "<%=ctxPath%>/resources/images/icon_menu_my_act.png";
			
		}
	}
</script>
<div style="height:80px; clear:both;"></div>
<div class="menu" id="footerMenu">
    <div class="submenuact menu-border" id="submenu_0" onclick="getUrl(0)">
      <div class="menupic"><img src="<%=ctxPath%>/resources/images/icon_menu_ju.png" width="100%" border="0" id="img_0"/></div>
      <div>聚友惠</div>
    </div>
  <!--  <a href="<%=ctxPath%>/getHomeInfo?index=1">
    <div class="submenu menu-border" id="submenu_1">
      <div class="menupic"><img src="<%=ctxPath%>/resources/images/icon_menu_activity.png" width="100%" border="0"/></div>
      <div>活动</div>
    </div>
  </a>-->
    <div class="submenu menu-border" id="submenu_2" onclick="getUrl(2)">
      <div class="menupic"><img src="<%=ctxPath%>/resources/images/icon_menu_headline.png"  width="100%" border="0" id="img_2"/></div>
      <div>头条</div>
    </div>
    <div class="submenu menu-border" id="submenu_3" onclick="getUrl(3)">
      <div class="menupic"><img src="<%=ctxPath%>/resources/images/icon_menu_find.png"  width="100%" border="0" id="img_3"/></div>
      <div>发现</div>
    </div>
    <div class="submenu menu-border" id="submenu_4" onclick="getUrl(4)">
      <div class="menupic"><img src="<%=ctxPath%>/resources/images/icon_menu_my.png"  width="100%" border="0" id="img_4" /></div>
      <div>我的</div>
    </div>
</div>
