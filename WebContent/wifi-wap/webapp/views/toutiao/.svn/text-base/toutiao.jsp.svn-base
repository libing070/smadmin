<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=yes,minimum-scale=1.0,maximum-scale=2.0" id="viewport" />
<title>中国移动免费WIFI上网</title>
<%@ include file="../common/include.jsp"%>
<%
	String contentTypeId = request.getParameter("contentTypeId");
	if(contentTypeId==null){
		contentTypeId = "";
	}
%>
</head>
 <style type="text/css">
   .headline-sort-block .gonggao1{background-color:#ff6060;}
 </style>
<body>
	<!--头部-->
	<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
	<!--头部结束-->


	<!--头条-->
	<div class="headline-write-bg">
		<div class="headline-write" onclick="window.location.href='<%=ctxPath%>/toutiao/toutiaoEditIndex'">我要发头条</div>
		<div class="headline-search" style="float:right">
			<span><input id="headlineSch" name="headlineSch" type="text" value="" placeholder="搜头条" /></span><span style="margin-right: 10px;"><img id="btnHeadlineSch" src="<%=ctxPath%>/resources/images/icon_search.png" width="30" align="absmiddle" /></span>
		</div>
	</div>

	<!--分类-->
	<div class="headline-sort">

		<div class="headline-sort-block headline-sort-active" id="headline-sort-2">
			<span class="xinwen">新闻</span>
		</div>
		<div class="headline-sort-block headline-sort-normal" id="headline-sort-1">
			<span class="guanshui">灌水</span>
		</div>
		<div class="headline-sort-block headline-sort-normal" id="headline-sort-5">
			<span class="qiuzhu">求助</span>
		</div>
		<div class="headline-sort-block headline-sort-normal" id="headline-sort-6">
			<span class="gonggao1">公告</span>
		</div>
	</div>
	<!--分类结束-->
	<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
	<!--列表-->
	<div>
		<div id="dataList"></div>
		  <input id="currpage" name="page" type="hidden" value="0"/>
		  <input id="totalpage" name="page" type="hidden" value=""/>
		<div id="btnMore" class="qiangzhuo-more">点击查看更多</div>
		<!--div id="dyncHeight"></div-->
	</div>
	<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->

	<script type="text/javascript">
	var contentTypeId = '<%=contentTypeId%>';
	
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
        return content.replace(re, newStr);
    }
    function debug($obj) {
        if (window.console || window.console.log) {
            window.console.log($obj);
        }
    }
    $(function () {
        var dataListDiv = $('#dataList');
        var strTemplate = '<div class="headline-list clearfix jiange-top" onclick="window.location.href=\'<%=ctxPath%>/toutiao/toutiaoDetailIndex?headlineId={headlineId}&contentTypeId='+contentTypeId+'\'">\n        <div class="headline-content-layout">\n            <div class="headline-content-border">\n                <div class="headline-content-title">{title}</div>\n                <div class="headline-content-time">{operDateString}</div>\n                <div style="height:16px;text-overflow:ellipsis;-o-text-overflow:ellipsis;overflow:hidden;white-space: nowrap;">{content}</div>\n                <div class="headline-content-pic" id="pic-{headlineId}">\n                    </div>\n                <div class="headline-content-info">\n                    <li class="user">{nickname}</li>\n                    <li class="votes">{praiseNum}</li>\n                    <li class="comments">{replyNum}</li>\n                </div>\n            </div>\n        </div>\n    </div>';
        var gonggaoTemplate = '<div class="headline-list clearfix jiange-top" onclick="window.location.href=\'<%=ctxPath%>/toutiao/toutiaoDetailIndex?headlineId={headlineId}&contentTypeId='+contentTypeId+'\'">\n        <div class="headline-content-layout">\n            <div class="headline-content-border">\n                <div class="headline-content-title">{title}</div>\n                <div class="headline-content-time">{operDateString}</div>\n                <div style="height:16px;text-overflow:ellipsis;-o-text-overflow:ellipsis;overflow:hidden;white-space: nowrap;">{content}</div>\n                <div class="headline-content-pic" id="pic-{headlineId}">\n                    </div>\n                <div class="headline-content-info">\n                    <li class="user">{nickname}</li>\n        </div>\n            </div>\n        </div>\n    </div>';
        var deleteTemplate = '<div class="headline-list clearfix jiange-top" onclick="window.location.href=\'<%=ctxPath%>/toutiao/toutiaoDetailIndex?headlineId={headlineId}&contentTypeId='+contentTypeId+'\'">\n    <div class="headline-content-layout">\n        <div class="headline-content-border">\n        <div class="headline-content-title">{title}</div>\n            <div class="headline-content-time">{operDateString}</div>\n            <div>该头条已经删除</div>\n            <div class="headline-content-info">\n                <li class="user">{nickname}</li>\n                <li class="votes">{praiseNum}</li>\n                <li class="comments">{replyNum}</li>\n            </div>\n        </div>\n    </div>\n</div>';
        var listUrl = '<%=ctxPath%>/headlineList';
        var listParam = {
            headlineSch: '',
            isOwneHeadline: 'no',
            contentTypeId: '2',//修改头条的默认类型
            perLoadSize:10,
            currpage:0
        };
        
        if(contentTypeId != null && contentTypeId != ''){
        	$("#headline-sort-"+contentTypeId).removeClass('headline-sort-normal').addClass('headline-sort-active').siblings().removeClass('headline-sort-active').addClass('headline-sort-normal');
        	listParam.contentTypeId = contentTypeId;
        }
        
        var listPicUrl = '<%=ctxPath%>/headlineAttachList';
        var btnMore = $('#btnMore');
        var btnHeadlineSch = $('#btnHeadlineSch');
        //
        function reload() {
            ajaxPost(listUrl, listParam, function (data) {
                var total=data.totalPage;
                $("#totalpage").val(total);
                if (!data.list) {
                    btnMore.before('<div class="qiangzhuo-more">没有数据，请发布您的头条吧</div>');
                    btnMore.hide();
                    return ;
                }
               // dataListDiv.empty();
                $.each(data.list, function (index, record) {
                    var _strTemplate = strTemplate;
                    var _deleteTemplate = deleteTemplate;
                    var _gonggaoTemplate = gonggaoTemplate;
                    if (record) {
                        $.each(record, function (key, value) {
                            _strTemplate = formatString(_strTemplate, key, value);
                            _deleteTemplate = formatString(_deleteTemplate, key, value);
                            _gonggaoTemplate = formatString(_gonggaoTemplate, key, value);
                            if (key == 'status' && value == '3') {
                                _strTemplate = _deleteTemplate;
                            }
                            if (key == 'contentTypeId' && value == '6') {
                                _strTemplate = _gonggaoTemplate;
                            }
                        });
                    }
                    dataListDiv.append($(_strTemplate));
                    //
                    //
                    ajaxPost(listPicUrl, {headlineId: record.headlineId}, function (_data) {
                        $.each(_data, function (_index, _record) {
                            var imgLi = '<li><img src="<%=ctxPath%>/listHeadlineContentAttach?pictureSerialnum=' + _record.pictureSerialnum + '"  class="lightbox" width="100%;"/></li>';
                            $('#pic-' + record.headlineId).append(imgLi);
                            addLigthBoxEvent();
                        });
                    });
                });
                //$('#dyncHeight').height($('#footerMenu').height());
                 if (data.currpage+1 < data.totalPage) {
                        btnMore.html("点击查看更多");
                    } else {
                        btnMore.html("已显示全部头条");
                    }
            });
        }

        reload();
        btnMore.click(function (obj) {
            var currpage=$("#currpage").val();
            var totalpage=$("#totalpage").val();
            var curDataListSize = dataListDiv.children('div').length;
            listParam.perLoadSize = parseInt(listParam.perLoadSize);
           if(parseInt(currpage)!=parseInt(totalpage)){
            $("#currpage").val(parseInt($("#currpage").val())+1);
            listParam.currpage = parseInt($("#currpage").val());
              reload();
            }
        });
        //
        $('.headline-sort-block').click(function (obj) {
        	  $("#currpage").val(0);
        	  listParam.currpage = parseInt($("#currpage").val());
            $(this).removeClass('headline-sort-normal').addClass('headline-sort-active').siblings().removeClass('headline-sort-active').addClass('headline-sort-normal');
            //
            var strLabel = $(this).children('span').text();
            listUrl = '<%=ctxPath%>/headlineList';
			listParam.perLoadSize = 10;
			if (strLabel == '灌水') {
				listParam.contentTypeId = '1';
				contentTypeId="1";
			} else if (strLabel == '新闻') {
				listParam.contentTypeId = '2';
				contentTypeId="2";
			} else if (strLabel == '八卦') {
				listParam.contentTypeId = '3';
				contentTypeId="3";
			} else if (strLabel == '招聘') {
				listParam.contentTypeId = '4';
				contentTypeId="4";
			} else if (strLabel == '求助') {
				listParam.contentTypeId = '5';
				contentTypeId="5";
			} else if (strLabel == '公告') {
				listParam.contentTypeId = '6';
				contentTypeId="6";
			}
        	gonggaoTemplate = '<div class="headline-list clearfix jiange-top" onclick="window.location.href=\'<%=ctxPath%>/toutiao/toutiaoDetailIndex?headlineId={headlineId}&contentTypeId='+contentTypeId+'\'">\n        <div class="headline-content-layout">\n            <div class="headline-content-border">\n                <div class="headline-content-title">{title}</div>\n                <div class="headline-content-time">{operDateString}</div>\n                <div style="height:16px;text-overflow:ellipsis;-o-text-overflow:ellipsis;overflow:hidden;white-space: nowrap;">{content}</div>\n                <div class="headline-content-pic" id="pic-{headlineId}">\n                    </div>\n                <div class="headline-content-info">\n                    <li class="user">{nickname}</li>\n                    </div>\n            </div>\n        </div>\n    </div>';
			dataListDiv.empty();
			reload();
		});

		btnHeadlineSch.click(function(obj) {
			var headlineSch = $('#headlineSch').val();
			listParam.headlineSch = headlineSch;
			listParam.perLoadSize = 10;
			dataListDiv.empty();
			reload();
		});
	});
	</script>
</body>
</html>