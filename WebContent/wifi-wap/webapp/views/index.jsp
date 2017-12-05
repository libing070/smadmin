﻿<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=yes,minimum-scale=1.0,maximum-scale=2.0" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="common/include.jsp"%>
<style>
.scroll{margin:0px;}
.scroll_box{overflow:hidden;visibility:hidden;position:relative;}
.scroll_wrap{overflow:hidden; position:relative;}
.scroll_wrap li{position:relative;display:block;width:100%;float:left;}
.scroll_wrap li a{display:block;margin:0 auto;position:relative;}
.scroll_position{left:45%;z-index:400px;bottom:3px;}
.scroll_position li{display:inline-block;width:8px;height:8px;border-radius:10px;background:#efefef;}
.scroll_position li a{font-size:0;}
.scroll_position li.on{background-color:#ff9600;}
.scroll_position_bg{background:transparent;position:absolute;bottom:6px;left:44%;padding:0 15px;z-index:380px;height:26px;border-radius:26px;}
</style>
</head>
<body>
<!--头部--><!-- #BeginLibraryItem "/Library/head.lbi" --> 
<jsp:include page="app_head.jsp" flush="true"></jsp:include>

<!--头部结束-->
<div class="ad">
	<div class="scroll relative">
	<div class="scroll_box" id="scroll_img">
	<div class="scroll_wrap">
		<div class="scroll_block"><img src="<%=ctxPath%>/resources/images/indexad01.jpg" onclick="gotoActivityURL2()" width="100%"  border="0" /></div>
	<!--	<div class="scroll_block"><img src="<%=ctxPath%>/resources/images/indexad03.jpg" onclick="gotoActivityURL1()" width="100%"  border="0" /></div>-->
	  	<div class="scroll_block"><img src="<%=ctxPath%>/resources/images/indexad06.jpg" onclick="gotoActivityURL3()" width="100%"  border="0" /></div>
	  	<div class="scroll_block"><img src="<%=ctxPath%>/resources/images/indexad07.jpg" onclick="gotoActivityURL1()" width="100%"  border="0" /></div>
	</div>
	</div>

	  	<div>
			<span class="scroll_position_bg opacity6"></span>
			<div class="scroll_position" id="scroll_position">
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
	jQuery.ajax({
        url : '<%=ctxPath%>/getAcitivityByquyouhui',
        type : "POST",
		 async: false,
        dataType : "json",
      	 success : function(data) {
        	var contentHtml2='';
        	var contentHtml3='';
        	var contentHtml4='';
        	var contentHtml5='';
        	var contentHtml6='';
        	var contentHtml8='';
      	    var list=data.list;
      	    if(list.length>0){
      	      for(var i=0;i<list.length;i++){
          	    var sum=list[i].cashSubsidy+list[i].salePersonNum+list[i].persionParticipateCnt+list[i].pinActionInvalidate+list[i].pinCreatetableInvalidate;
                var activityId=list[i].activityId;
                if(activityId==3){
                	contentHtml3+='<div class="ju-listblock ju-bg-conner1"  onclick="window.location.href=\'<%=ctxPath%>/judetailContr?activityId='+activityId+'\'">';
          	    	contentHtml3+='<div class="l"><img src="<%=ctxPath%>/resources/images/2128235774.jpg" width="100%"/></div>';
          	    	contentHtml3+='<div class="r">';
          	    	contentHtml3+='  <div class="title">'+list[i].activityName+'</div>';
          	    	contentHtml3+=' <div class="renci">累计'+sum+'人抢过</div>';
          	    	contentHtml3+=' <div class="ju-label"><span>7折送酸梅汤再减10元</span></div>';
          	    	contentHtml3+=' </div></div>';
                }else if(activityId==4){
                	contentHtml4+='<div class="ju-listblock ju-bg-conner1" onclick="window.location.href=\'<%=ctxPath%>/judetailContr?activityId='+activityId+'\'">';
          	    	contentHtml4+='<div class="l"><img src="<%=ctxPath%>/resources/images/IMG_1237.JPG" width="100%"/></div>';
          	    	contentHtml4+='<div class="r">';
          	    	contentHtml4+='  <div class="title">王林串串香</div>';
          	    	contentHtml4+=' <div class="renci">累计'+sum+'人抢过</div>';
          	    	contentHtml4+=' <div class="ju-label"><span>8.8折再减10元</span></div>';
          	    	contentHtml4+=' </div></div>';
                }else if(activityId==5){
                	contentHtml5+='<div class="ju-listblock ju-bg-conner1" onclick="window.location.href=\'<%=ctxPath%>/judetailContr?activityId='+activityId+'\'">';
          	    	contentHtml5+='<div class="l"><img src="<%=ctxPath%>/resources/images/ad_movie.jpg" width="100%"/></div>';
          	    	contentHtml5+='<div class="r">';
          	    	contentHtml5+='  <div class="title">'+list[i].activityName+'</div>';
          	    	contentHtml5+=' <div class="renci">累计'+sum+'人抢过</div>';
          	    	contentHtml5+=' <div class="ju-label"><span>【低价】19.9元看大片</span></div>';
          	    	contentHtml5+=' </div></div>';
                }else if(activityId==2){
                	contentHtml2+='<div class="ju-listblock ju-bg-conner1"  onclick="window.location.href=\'<%=ctxPath%>/judetailContr?activityId='+activityId+'\'">';
          	    	contentHtml2+='<div class="l"><img src="<%=ctxPath%>/resources/images/shiyonglog.jpg" width="100%"/></div>';
          	    	contentHtml2+='<div class="r">';
          	    	contentHtml2+='  <div class="title">面膜试用</div>';
          	    	contentHtml2+=' <div class="renci">累计'+sum+'人抢过</div>';
          	    	contentHtml2+=' <div class="ju-label"><span>【人气】静佳面膜免费领</span></div>';
          	    	contentHtml2+=' </div></div>';
                     }
                else if(activityId==6){
                	contentHtml6+='<div class="ju-listblock ju-bg-conner1" onclick="window.location.href=\'<%=ctxPath%>/judetailContr?activityId='+activityId+'\'">';
          	    	contentHtml6+='<div class="l"><img src="<%=ctxPath%>/resources/images/dingdinglog.jpg" width="100%"/></div>';
          	    	contentHtml6+='<div class="r">';
          	    	contentHtml6+='  <div class="title">'+list[i].activityName+'</div>';
          	    	contentHtml6+=' <div class="renci">累计'+sum+'人抢过</div>';
          	    	contentHtml6+=' <div class="ju-label"><span>【惊爆】21元吃自助</span></div>';
          	    	contentHtml6+=' </div></div>';
                    }else if(activityId==9){
                	contentHtml8+='<div class="ju-listblock ju-bg-conner1" onclick="window.location.href=\'<%=ctxPath%>/judetailContr?activityId='+activityId+'\'">';
          	    	contentHtml8+='<div class="l"><img src="<%=ctxPath%>/resources/images/htmt.jpg" width="100%"/></div>';
          	    	contentHtml8+='<div class="r">';
          	    	contentHtml8+='  <div class="title">红舵码头</div>';
          	    	contentHtml8+=' <div class="renci">累计'+sum+'人抢过</div>';
          	    	contentHtml8+=' <div class="ju-label"><span>78元的双人小火锅</span></div>';
          	    	contentHtml8+=' </div></div>';
                        }
          	      }
          	    }
      	    $("#juyouhuilist").html(contentHtml2+contentHtml3+contentHtml4+contentHtml6+contentHtml8);
         	}
       })
	
});
</script>
<script>
	function gotoActivityURL1(){
	//wuh 2014-09-22
	//	window.location.href="<%=ctxPath%>/schoolActivityRule";
	//	window.location.href="<%=ctxPath%>/toutiao/toutiaoDetailIndex?headlineId=670";
		window.location.href="<%=ctxPath%>/jianianhua/youxi";
	}
	function gotoActivityURL2(){
		window.location.href="<%=ctxPath%>/getHomeInfo?index=1";
	}
	function gotoActivityURL3(){
		window.location.href="<%=ctxPath%>/getzhuanfa?index=1";
	}
	
var bullets = document.getElementById("scroll_position").getElementsByTagName("div");
	var slider = Swipe(document.getElementById("scroll_img"), {
	auto: 4000,
	continuous: true,
	callback: function(pos) {
		var i = bullets.length;
		while (i--) {
			bullets[i].className = "scroll_but";
		}
	//	bullets[pos].className = "scroll_but on";
	}
	});
	//$(function() {
		//$("body").showLoading();
		//doEachHeadlineJob();
		//searchHead();
		//setInterval("searchHead()", 1000);
	//});
	$("body").showLoading();
	
	$(document).ready(function() {
		getZhuanFaNum();
		$("#qiang_btn").click(function() {
		$("#qiang_success").show();
			$("#qiang").hide();
		});		
		$("body").hideLoading();
			
	});
	
</script>
<!-- 转发活动入口图 -->
<div id="zhuanfatu"></div>
<!-- 聚友惠列表 -->	

<div id="juyouhuilist">
</div>
<!-- 滚动代码 结束 -->
<div id="maskBox" style="display:none">
	<div id="loading" > 
		<img src="<%=ctxPath%>/resources/images/ajax-loader_blue.gif" />
	</div>
</div>
<!--头条-->
<div class="headline-bg jiange-top clearfix">
	<div class="headline-title">头条 HOT</div>
	<div class="headline-title-edit" onclick="window.location.href='/wifi-wap/toutiao/toutiaoEditIndex?index=2'"></div>
</div>
<!--列表-->
	<div>
		<div id="dataList"></div>
	</div>
<!--头条结束-->

<jsp:include page="app_footer.jsp" flush="true"></jsp:include>
<script type="text/javascript">
var contentTypeId = '1';

var ajaxPost = function (url, data, successCallback) {
    $.ajax({
        url: url,
        data: data,
        type: 'post',
        dataType: 'json',
        success: function (data) {
            successCallback(data);
        }
    });
};
function formatString(content, str, newStr) {
    var re = new RegExp('\\{' + str + '\\}', 'gm');
    if(str == 'content'){
    	if(newStr.length > 200){
    		//newStr = newStr.substr(0,200) + "...";
    	}
    }
    return content.replace(re, newStr);
}
function debug($obj) {
    if (window.console || window.console.log) {
        window.console.log($obj);
    }
}
$(function () {
    var dataListDiv = $('#dataList');
    var strTemplate = '<div class="headline-list clearfix jiange-top" onclick="window.location.href=\'<%=ctxPath%>/toutiao/toutiaoDetailIndex?headlineId={headlineId}\'">\n        <div class="headline-content-layout">\n            <div class="headline-content-border">\n                <div class="headline-content-title">{title}</div>\n                <div class="headline-content-time">{operDateString}</div>\n                <div style="height:16px;text-overflow:ellipsis;-o-text-overflow:ellipsis;overflow:hidden;white-space: nowrap;">{content}</div>\n                <div class="headline-content-pic" id="pic-{headlineId}">\n                    </div>\n                <div class="headline-content-info">\n                    <li class="user">{nickname}</li>\n                    <li class="votes">{praiseNum}</li>\n                    <li class="comments">{replyNum}</li>\n                </div>\n            </div>\n        </div>\n    </div>';
    var deleteTemplate = '<div class="headline-list clearfix jiange-top">\n    <div class="headline-content-layout">\n        <div class="headline-content-border">\n        <div class="headline-content-title">{title}</div>\n            <div class="headline-content-time">{operDateString}</div>\n            <div>该头条已经删除</div>\n            <div class="headline-content-info">\n                <li class="user">{nickname}</li>\n                <li class="votes">{praiseNum}</li>\n                <li class="comments">{replyNum}</li>\n            </div>\n        </div>\n    </div>\n</div>';
    var listUrl = '<%=ctxPath%>/indexHeadlineList';
    var listParam = {
        isOwneHeadline: 'no',
        newsSize: 3,
        guanShuiAndQiuZhuSize: 7
    };
    
    if(contentTypeId != null && contentTypeId != ''){
    	$("#headline-sort-"+contentTypeId).removeClass('headline-sort-normal').addClass('headline-sort-active').siblings().removeClass('headline-sort-active').addClass('headline-sort-normal');
    	listParam.contentTypeId = contentTypeId;
    }
    
    var listPicUrl = '<%=ctxPath%>/headlineAttachList';
    //
    function reload() {
        ajaxPost(listUrl, listParam, function (data) {
            if (!data.list) {
                return ;
            }
            dataListDiv.empty();
            var count = 0;
            $.each(data.list, function (index, record) {
            	if(count >= 10){
            		return ;
            	}
                var _strTemplate = strTemplate;
                var _deleteTemplate = deleteTemplate;
                if (record) {
                    $.each(record, function (key, value) {
                        _strTemplate = formatString(_strTemplate, key, value);
                        _deleteTemplate = formatString(_deleteTemplate, key, value);
                        if (key == 'status' && value == '3') {
                            _strTemplate = _deleteTemplate;
                        }
                    });
                }
                dataListDiv.append($(_strTemplate));
                //
                if (data.more == 'yes') {
                	
                } else {
                	
                }
                //
                ajaxPost(listPicUrl, {headlineId: record.headlineId}, function (_data) {
                    $.each(_data, function (_index, _record) {
                        var imgLi = '<li><img src="<%=ctxPath%>/listHeadlineContentAttach?pictureSerialnum=' + _record.pictureSerialnum + '"width="100%;" class="lightbox"/></li>';
                        $('#pic-' + record.headlineId).append(imgLi);
                        addLigthBoxEvent();
                    });
                });
                
                count++;
            });
            $('#dyncHeight').height($('#footerMenu').height() + 180);
        });
    }

    reload();
    
});

//计算参数值的字符长度，一个汉字为两个字符，标点以及英文字符为一个字符
function len(v){
	return v.replace(/[^\x00-\xff]/g,"**").length;
}
//获取转发的数据
function getZhuanFaNum(){
jQuery.ajax
	({
		type: "post",
		url: ctxPaths + "/getZhuanFaNum",
		dataType: "json",
		cache: false,
		success: function(data){
			if(data.CODE=="TRUE"){
				var zhuanfaNum = data.zhuanfaNum;
				var html="";
					html+='<div class="ju-listblock ju-bg-conner1" onclick="gotoActivityURL3()">';
          	    	html+='<div class="l"><img src="<%=ctxPath%>/resources/images/cishan_list.jpg" width="100%"/></div>';
          	    	html+='<div class="r">';
          	    	html+='  <div class="title">冬日温暖爱心传递</div>';
          	    	html+=' <div class="renci">累计'+zhuanfaNum+'人参与</div>';
          	    	html+=' <div class="ju-label"><span>点击爱心捐出0.1元</span></div>';
          	    	html+=' <div class="ju-label"><span>转发链接爱心捐赠加倍</span></div>';
          	    	html+=' </div></div>';
				$("#zhuanfatu").html(html);
			}
		}
	})

}

</script>
</body>
</html>