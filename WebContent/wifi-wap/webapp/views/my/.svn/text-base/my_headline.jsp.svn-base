<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, user-scalable=yes,minimum-scale=1.0,maximum-scale=2.0" id="viewport" />

    <title>中国移动免费WIFI上网</title>
    <%@ include file="../common/include.jsp" %>
</head>
<body>
	<!--头部-->
	<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
	<!--头部结束-->

<!--头条-->
<div class="my-top-tool jiange-top">
    <span class="headline-back-btn" onclick="javascript:history.back();"><img src="<%=ctxPath%>/resources/images/icon_back.png"  align="absmiddle" /></span>
    <span class="my-top-title">我的头条</span>
</div>

<!--分类-->
<div class="headline-sort">
    <div class="headline-sort-block headline-sort-active"><span class="gonggao1">公告</span></div>
    <div class="headline-sort-block headline-sort-normal"><span class="xinwen">新闻</span></div>
    <div class="headline-sort-block headline-sort-normal"><span class="guanshui">灌水</span></div>
    <!--div class="headline-sort-block headline-sort-normal"><span class="bagua">八卦</span></div-->
    <!--div class="headline-sort-block headline-sort-normal"><span class="zhaopin">招聘</span></div-->
    <div class="headline-sort-block headline-sort-normal"><span class="qiuzhu">求助</span></div>
</div>
<!--分类结束-->

<!--列表-->
<div id="dataList"></div>
<input id="currpage" name="page" type="hidden" value="0"/>
 <input id="totalpage" name="page" type="hidden" value=""/>
<div id="btnMore" class="qiangzhuo-more">点击查看更多</div>
<!--导航菜单-->
	<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
	<!--导航菜单结束-->

<script type="text/javascript">

    var ajaxPost = function (url, data, successCallback) {
        $.ajax({
            url: url,
            data: data,
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
        var deleteUrl = '<%=ctxPath%>/deleteHeadline'; //删除Url
        var dataListDiv = $('#dataList');
        var strTemplate = '<div class="headline-list clearfix jiange-top" onclick="window.location.href=\'<%=ctxPath%>/toutiao/toutiaoDetailIndex?headlineId={headlineId}\'">\n        <div class="headline-content-layout">\n            <div class="headline-content-border">\n                <div class="headline-content-title">{title}</div>\n                <div class="headline-content-time">{operDateString}</div>\n                <div style="height:30px;text-overflow:ellipsis;-o-text-overflow:ellipsis;overflow:hidden;white-space: nowrap;">{content}</div>\n                <div class="headline-content-pic" id="contentPic{headlineId}">\n                </div>\n                <div class="headline-content-info">\n                    <li class="user">{nickname}</li>\n                    <li class="votes">{praiseNum}</li>\n                    <li class="comments">{replyNum}</li>\n                </div>\n            </div>\n        </div>\n    </div><div class="qiangzhuo-more"><a href="javascript:void(0);" id="btn{headlineId}" onClick="javascript:deleteById({headlineId});">删除此头条</a><input type="hidden" name="headlineId" value="{headlineId}" ></div>';
        var deleteTemplate = '<div class="headline-list clearfix jiange-top" onclick="window.location.href=\'<%=ctxPath%>/toutiao/toutiaoDetailIndex?headlineId={headlineId}\'">\n    <div class="headline-content-layout">\n        <div class="headline-content-border">\n        <div class="headline-content-title">{title}</div>\n            <div class="headline-content-time">{operDateString}</div>\n            <div>该头条已经删除</div>\n            <div class="headline-content-info">\n                <li class="user">{nickname}</li>\n                <li class="votes">{praiseNum}</li>\n                <li class="comments">{replyNum}</li>\n            </div>\n        </div>\n    </div>\n</div>';
        var listUrl = '<%=ctxPath%>/headlineList'; //首先列出公告内容
        var listParam = {
            contentTypeId: '6',
            isOwneHeadline: 'yes',
            perLoadSize: 10,
            currpage:0
        };
        var listPicUrl = '<%=ctxPath%>/headlineAttachList';
        var btnMore = $('#btnMore');
         var btnDelete = $('#btnDelete');
         
       
         
        function reload() {
            ajaxPost(listUrl, listParam, function (data) {
            	  var total=data.totalPage;
            	  $("#totalpage").val(total);
                if (!data.list) {
                    btnMore.before('<div class="qiangzhuo-more">没有数据，请发布您的头条吧</div>');
                    btnMore.hide();
                    return ;
                }
                //dataListDiv.empty();
                $.each(data.list, function (index, record) {
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
                    //
                    ajaxPost(listPicUrl, {headlineId: record.headlineId}, function (data) {
                        $.each(data, function (index, _record) {
                            var imgLi = '<li><img src="<%=ctxPath%>/listHeadlineContentAttach?pictureSerialnum=' + _record.pictureSerialnum + '" width="100%;" class="lightbox"/></li>';
                            $('#contentPic' + record.headlineId).append(imgLi);
                        });
                        addLigthBoxEvent();
                    });
                });
                if (data.currpage+1 < data.totalPage) {
                    btnMore.html("点击查看更多");
                } else {
                    btnMore.html("已显示全部头条");
                }
            });
        }
        reload();
        //
        btnMore.on('click', function (obj) {
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
       
       
      
        $('.headline-sort-block').on('click', function (obj) {
        	 $("#currpage").val(0);
        	  listParam.currpage = parseInt($("#currpage").val());
            $(this).removeClass("headline-sort-normal").addClass('headline-sort-active')
                    .siblings().removeClass("headline-sort-active").addClass('headline-sort-normal');
            //
            var strLabel = $(this).children('span').text();
            listUrl = '<%=ctxPath%>/headlineList';
            listParam.perLoadSize = 10;
            if (strLabel == '灌水') {
                listParam.contentTypeId = '1';
            } else if (strLabel == '新闻') {
                listParam.contentTypeId = '2';
            } else if (strLabel == '八卦') {
                listParam.contentTypeId = '3';
            } else if (strLabel == '招聘') {
                listParam.contentTypeId = '4';
            } else if (strLabel == '求助') {
                listParam.contentTypeId = '5';
            } else if (strLabel == '公告') {
                listParam.contentTypeId = '6';
            }
            //
            dataListDiv.empty();
            reload();
        });

    });
    
    
      function deleteById(headlineId) {
    	   var deleteUrl = ctxPaths+'/deleteHeadline'; 
           if (confirm("真的删除此头条吗？")) {
                 var headlineId = $("#btn"+headlineId).next('input').val();
                 ajaxPost(deleteUrl, {headlineId: headlineId}, function (data) {
                 
                    window.location.href='<%=ctxPath%>/my/myHeadline';
               });
           }
        }
</script>
</body>
</html>