<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="common/include.jsp"%>
</head>
<body>
<!--头部--><!-- #BeginLibraryItem "/Library/head.lbi" --><div class="header">
  <div class="logo">
    <!--<div class="logo1"><img src="../<%=ctxPath%>/resources/images/logo1.png" height="40" /></div>-->
    <div class="logo2"><img src="<%=ctxPath%>/resources/images/logo2.png" height="40" /></div>
  </div>
  <div class="login"><!--Hi，138****12234--><span class="login-btn">登陆</span></div>
</div><!-- #EndLibraryItem --><!--头部结束-->




            
<!--免费试用活动-->
            <div class="shiyong1">
              <div class="l">
                <div><img src="<%=ctxPath%>/resources/images/pic_sample01.jpg" width="100%" /></div>
              </div>
              <div class="r">
                <div class="boldfont">价值：￥899</div>
                <div class="boldfont">免费试用：30 份</div>
                <div class="apply">3475人已申请<br />
                  <a href="#">更多免费试用>></a>
                </div>
                <div class="tiaojian"><span class="boldfont">申领条件：</span>
                    <div class="smallfont">1、所有用户都可以免费申请</div>
                    <div class="smallfont">2、申请成功需要提交真实原创的试用</div>
                    <div class="smallfont">3、申请成功需要提交真实原创的试用</div>
                </div>
                <div class="bigtitle"><span>免费申请</span></div>
                <div class="time">
                  <div>免费试用剩余时间 : </div>
                  <div>10天07时59分27秒</div>
                </div>
              </div>
            </div>
<!--免费试用活动结束-->
			<%@ include file="public.jsp"%>
  
	<!--导航菜单-->
	<jsp:include page="app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->

</body>
</html>