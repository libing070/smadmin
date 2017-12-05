<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="common/include.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/school.css"/>
</head>


 
<body>
  <jsp:include page="app_head.jsp" flush="true"></jsp:include>
  
  <div class="school-menubg layout clearfix">
    <div class="school-menu-block">
      <div class="index-menu-button btn-lightblue">
        <div class="index-menu-text" onclick="javascript:location.href='<%=ctxPath%>/schoolActivityRule'">活动规则</div>
      </div>
    </div>
    <div class="school-menu-block">
      <div class="index-menu-button btn-lightblue">
        <div class="index-menu-text" onclick="javascript:location.href='<%=ctxPath%>/schoolNewGuide'">新生指引</div>
      </div>
    </div>
    <div class="school-menu-block">
      <div class="index-menu-button btn-pink">
        <div class="index-menu-text" onclick="javascript:location.href='<%=ctxPath%>/schoolDetail'">校园概况</div>
      </div>
    </div>
  </div>
 <div class="ad">
<!--scroll-->
	  <div class="scroll relative">
	    <div class="scroll_box" id="scroll_img">
	        <div class="scroll_wrap">
		          <div class="scroll_block">
					<img src="<%=ctxPath %>/resources/images/fada_a.jpg" width="100%" />
		          </div>
		          
		          <div class="scroll_block">
		            <img src="<%=ctxPath %>/resources/images/fada_b.jpg" width="100%" />
		          </div>
		          
		          <div class="scroll_block">
		          	<img src="<%=ctxPath %>/resources/images/fada_c.jpg" width="100%" />
		          </div>
	        </div>
	    </div>
  	</div>
	  <!--scroll-->
</div>

  <!-- 
  <div class="layout clearfix margin10">
 <video id="media" class="media" width="768" height="387" controls="" src="<%=ctxPath %>/resources/video/1.mp4" poster="<%=ctxPath %>/resources/images/ad_index1.jpg"></video>
 </div>
  -->
  <div class="title-bg">
    <div class="title-pink"><span class="title-text">学校简介</span></div>
  </div>
  <div class="layout clearfix margin10">
    <div class="content">
      中国政法大学是一所以法学为特色和优势，兼有文学、史学、哲学、经济学、管理学、教育学等多学科的“211工程”重点建设大学，“‘985’优势学科创新平台”项目重点建设高校，直属于国家教育部。学校现有海淀区学院路和昌平区府学路两个校区。学校的前身是1952年由北京大学、清华大学、燕京大学、辅仁大学四校的法学、政治学、社会学等学科组合而成的北京政法学院，1954年，学校迁址至学院路。文革中学校被停办，文革结束后复办。1983年，北京政法学院与中央政法干校合并，组建为中国政法大学。1985年，学校开辟昌平校区新校址...

    </div>
    <div class="content2" style="display: none">
      <div class="entry-content group">
							<p style="text-indent: 2em;" align="left"><span style="color: #333333;"> 中国政法大学是一所以法学为特色和优势，兼有文学、史学、哲学、经济学、管理学、教育学等多学科的“211工程”重点建设大学，“‘985’优势学科创新平台”项目重点建设高校，直属于国家教育部。学校现有海淀区学院路和昌平区府学路两个校区。</span></p>
<p align="left"><span style="color: #333333;">   学校的前身是1952年由北京大学、清华大学、燕京大学、辅仁大学四校的法学、政治学、社会学等学科组合而成的北京政法学院。1954年，学校迁址至学院路。文革中学校被停办，文革结束后复办。1983年，北京政法学院与中央政法干校合并，组建为中国政法大学。1985年，学校开辟昌平校区新校址。学校形成一校及本科生院、进修生院、研究生院三院办学格局。进修生院后更名为中央政法管理干部学院单独办学，2000年复又合并于中国政法大学。</span></p>
<p align="left"><span style="color: #333333;">   学校在半个多世纪的办学历程中，为国家培养了各类优秀人才20余万人，参与了自建校以来几乎国家的所有立法活动，引领着国家法学理论的变革和法律思想的更新，代表着国家对外进行法学等领域的学术交流。</span></p>
<p align="left"><span style="color: #333333;">   学校现有全日制在校生15374人，其中本科生8368人，研究生5936人，留学生1070人；教师913人，其中教学科研岗位教师833人，辅导员80人；教学科研岗位教师中博士生导师167人、硕士生导师602人，教授263人，有博士或硕士学位的比例达88.00%。</span></p>
<p align="left"><span style="color: #333333;">   学校现有法学院、民商经济法学院、国际法学院、刑事司法学院、政治与公共管理学院、商学院、人文学院、外国语学院、继续教育学院/网络教育学院、国际教育学院/港澳台教育中心、马克思主义学院、社会学院、法律硕士学院、新闻与传播学院、国际儒学院、高级政法管理干部进修中心、中欧法学院、科学技术教学部、体育教学部共19个教学单位；设有诉讼法学研究院（教育部人文社会科学重点研究基地）、法律史学研究院（教育部人文社会科学重点研究基地）、证据科学研究院（教育部重点实验室）、法治政府研究院/青少年法制教育研究中心（北京市哲学社会科学研究基地）、人权研究院（国家人权教育与培训基地）、比较法学研究院、法律古籍整理研究所、法学教育研究与评估中心/高等教育研究所、法和经济学研究中心、全球化与全球问题研究所、司法文明协同创新中心、全球治理与国际法治协同创新中心、知识经济与法治发展协同创新中心、人权建设协同创新中心、法治政府协同创新中心15个校级科研机构。其中，由中国政法大学牵头组建的司法文明协同创新中心是首批经教育部、财政部认定的十四个国家“2011计划”协同创新中心之一。</span></p>
<p align="left"><span style="color: #333333;">   学校设有法学、侦查学、政治学与行政学、行政管理、国际政治、公共事业管理、工商管理、经济学、国际商务、哲学、汉语言文学、思想政治教育、社会学、社会工作、应用心理学、英语、德语、新闻学、法学第二学士学位共19个本科专业，其中法学、政治学与行政学、社会学为国家级特色专业。拥有71个硕士学位授权点、5个专业硕士学位授权点、30个博士学位授权点和3个博士后科研流动站。法学、政治学、马克思主义理论为博士学位授权一级学科，哲学、理论经济学、应用经济学、社会学、 心理学、外国语言文学、新闻传播学、中国史、工商管理、公共管理为硕士学位授权一级学科，其中，政治学为一级学科北京市重点学科，法学为一级学科国家重点学科。</span></p>
<p align="left"><span style="color: #333333;">   学校先后与39个国家和地区的161所知名大学和机构建立了合作关系，每年通过各类合作交流项目派出近千名师生赴境外交流学习，聘请三百余名长短期外国专家来校讲学。2008年建立的中国政法大学中欧法学院是中国政府和欧盟在法学教育领域最大的合作项目。随着该项目的实施，学校培养国际型法律人才的格局、规模已经初步形成。学校从2009年开始全面实施国际化发展战略，不断提升国际化办学水平。2012年以来，学校已分别与英国班戈大学、罗马尼亚布加勒斯特大学合作建成2所海外孔子学院。</span></p>
<p align="left"><span style="color: #333333;">   学校的校训是：厚德、明法、格物、致公。</span></p>
<p align="left"><span style="color: #333333;">   学校的办学目标是：用20年左右的时间，把学校建设成为开放式、国际化、多科性、创新型的世界知名法科强校。</span></p>
<p style="padding-left: 240px;" align="left"><span style="color: #333333;">                                                                          数据截至2014年1月</span></p>
						</div>
    </div>
    <div id="queryMore" class="btn-confirm content-btn" style="text-align:center;"  onclick="getMore()">查看更多介绍</div>
  </div>
  <div style="height:80px;"></div>
<jsp:include page="app_footer.jsp" flush="true"></jsp:include>
</body>

<script>
var slider = Swipe(document.getElementById('scroll_img'), {
	auto: 4000,
	continuous: true,
	callback: function(pos) {
		var i = bullets.length;
		while (i--) {
			bullets[i].className = ' ';
		}
		bullets[pos].className = 'on';
	}
});
var bullets = document.getElementById('scroll_position').getElementsByTagName('.scroll_block');
$(function(){
	$('.scroll_position_bg').css({
		width:$('#scroll_position').width()
	});
});

	function getMore(){
		$(".content").hide();
		$(".content2").show();
		$("#queryMore").hide();
	}
</script>
</html>
