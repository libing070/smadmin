<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="common/include.jsp"%>

<script>
function goToCreate(){
window.location.href="<%=ctxPath%>/huodong/qiangzhuoSucess";
}

</script>

</head>

<body>

<jsp:include page="app_head.jsp" flush="true"></jsp:include>


<div class="qiangzhuo-chenggong" id="qiang_success">
    <div class="top"><img src="<%=ctxPath%>/resources/images/qiangzhuo_guize02.jpg" width="100%" /></div>
</div>

  <div class="jiange-top"><img src="<%=ctxPath%>/resources/images/icon_guize01.png"  /></div> 
  <div class="row">
          <ol>
            <li>周一至周五，每天提供10个抢桌名额，11:00-14:00开抢，一周共50个。</li>
            <li>抢单自创建之日起5天有效期，即：周一抢单周五到期，周五抢单下周二到期，依此类推，抢单拼单内容一经建立，不可取消。</li>
            <li> 每人每周限参与1单抢单和1单拼单，4人成单，抢单人可对拼单人进行删除管理；拼单人有权退出拼单。</li>
            <li> 人数少于4人，或抢单／拼单超过有效期的，抢／拼单自动失效，可继续参与下一次抢单／拼单活动。</li>
		  </ol>

  </div>
  <div><img src="<%=ctxPath%>/resources/images/icon_guize02.png"  /></div> 
  <div class="row">
          <ol>
          <!-- <li>查看指定消费地点</li>  --> 
            <li>即抢单人在指定范围消费可获得50元超市现金卡奖励。</li>
            <li> 成功消费后，抢单人需上传合影，审核通过后，抢单人／拼单人可继续参与下一次抢单／拼单活动。（这一步直接影响到你盒你的小伙伴的利益，要重视哦～）</li>
		  </ol>

  </div>
  
  <div class="btn-block jiange-top"><span class="btn-blue" onclick="javascript:goToCreate();">我知道了</span></div> 


<jsp:include page="app_footer.jsp" flush="true"></jsp:include>

</body>

</html>