<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.aspire.wifi.wap.util.GetConfigFile;"%>
<%
    String headlineId = request.getAttribute("headlineId") == null ? "" : request.getAttribute("headlineId").toString();
    String isLogin = request.getAttribute("isLogin") == null ? "" : request.getAttribute("isLogin").toString();
    String isReply = request.getAttribute("isReply") == null ? "" : request.getAttribute("isReply").toString();
    String contentTypeId = request.getAttribute("contentTypeId") == null ? "" : request.getAttribute("contentTypeId").toString();
    String mobile = request.getAttribute("mobile") == null ? "" : request.getAttribute("mobile").toString();
    String imageAccessPath=GetConfigFile.getInstance().getProperties("imageAccessPath");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=yes,minimum-scale=1.0,maximum-scale=2.0" id="viewport" />

    <title>中国移动免费WIFI上网</title>
    <%@ include file="../common/include.jsp" %>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/json.js"/></script>    
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
</head>
<body>
<!--头部-->
<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
<!--头部结束-->


<!--头条-->
<div class="headline-write-bg jiange-top">
    <span class="headline-back-btn" onclick="javascript:history.back();"><img
            src="<%=ctxPath%>/resources/images/icon_back.png" align="absmiddle"/></span>
</div>

<!--发帖-->
<div id="content" class="headline-list clearfix jiange-top"></div>
<!--评论-->
<div id="commentListPos"></div>
<!--更多评论-->
<!--首条评论-->
<!--发布评论-->
<div class="clearfix jiange-top">&nbsp;</div>
<div id="pinglunId">	
<div class="clearfix jiange-top" >我要说两句<!--（ 最多输入140个字符）： --></div>
<div class="headline-content-layout" >
    <div class="headline-edit-border whiteback">
        <input type="hidden" name="parentReplyId" id="parentReplyId" value="0">
        <textarea id="comments" name="comments" rows="5" class="headline-replay-input" placeholder="评论"></textarea>
    </div>
</div>
<div>&nbsp;&nbsp;&nbsp;&nbsp;</div>
   <div class="form-group">
                <label for="添加照片">&nbsp;&nbsp;</label>
                <div class="e_tForm_uploadBtn">
	                <form name="imageForm" enctype="multipart/form-data" action="<%=ctxPath%>/imageUpload.tv/0" method="post">
						<div class="headline-edit-addpic" onclick="getclick();">+上传图片</div>
						<input name="fileBox"id="t_file" class="fileBtn" onchange="uploadPrizeFile(this);" type="file">
					</form>
				</div>
      </div>
      <div class="form-group">
                <label for="预览">预览：</label>
                  <div class="col-xs-12" id="pic_viewDiv">  
             
                  </div>         
       </div> 
<div id="btnCommentsDiv" class="headline-replay-btn clearfix jiange-top">发表评论</div>
<div id="readOnlyId" style="display:none"></div>
</div>

<!--<div class="qiangzhuo-more">头条发布审核中...</div>-->

<!--导航菜单-->
<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
<!--导航菜单结束-->

<script type="text/javascript">
        var paramData = { headlineId: '<%=headlineId%>'};
        var detailUrl = '<%=ctxPath%>/findHeadlineContent';
        var detailTemplate = '<div class="headline-content-layout">\n    <div class="headline-edit-border">\n      <div class="headline-edit-l">\n        <div class="headline-edit-lz"><img src="<%=ctxPath%>/resources/images/icon_lz.png" /></div>\n<div id="lzHeadShowPic"></div>\n<div class="jiange-top">{nickname}</div>\n        <div class="jiange-top"><span class="headline-detail-sort">{contentTypeId}</span></div>\n      </div>\n      \n      <div class="headline-edit-r">\n          \n          <div class="item">\n            <div class="headline-content-title">{title}</div>\n            <div class="headline-detail">\n              <div class="clearfix"><pre>{content}</pre></div>\n              <div class="headline-edit-pic"></div>\n            </div>\n            \n          </div>\n      </div>\n     \n      <div class="headline-content-info clearfix whiteback"><li class="time">{operDateString}</li><li class="votes" id="votesId" onclick="javascript:voteFunction(this);">{praiseNum}</li><input type="hidden" name="parentReplyId" id="parentReplyId" value="{headlineId}"><input type="hidden" name="parentMobile" id="parentMobile" value="{mobile}"></div> \n    </div>\n  </div>';
        var detailTemplate2 = '<div class="headline-content-layout">\n    <div class="headline-edit-border">\n      <div class="headline-edit-l">\n        <div class="headline-edit-lz"><img src="<%=ctxPath%>/resources/images/icon_lz.png" /></div>\n<div id="lzHeadShowPic"></div>\n<div class="jiange-top">{nickname}</div>\n        <div class="jiange-top"><span class="headline-detail-sort">{contentTypeId}</span></div>\n      </div>\n      \n      <div class="headline-edit-r">\n          \n          <div class="item">\n            <div class="headline-content-title">{title}</div>\n            <div class="headline-detail">\n              <div class="clearfix"><pre>{content}</pre></div>\n              <div class="headline-edit-pic"></div>\n            </div>\n            \n          </div>\n      </div>\n     \n      <div class="headline-content-info clearfix whiteback"><li class="time">{operDateString}</li><li class="votes" id="votesId" onclick="javascript:voteFunction(this);">{praiseNum}</li><li class="votes"><a href="#" onclick="javascript:edit(1,<%=headlineId%>);">编辑</a></li><input type="hidden" name="parentReplyId" id="parentReplyId" value="{headlineId}"><input type="hidden" name="parentMobile" id="parentMobile" value="{mobile}"></div> \n    </div>\n  </div>';
        var detailTemplate_orig = detailTemplate;
        var deleteTemplate = '<div class="headline-list clearfix jiange-top">\n    <div class="headline-content-layout">\n        <div class="headline-content-border">\n        <div class="headline-content-title">{title}</div>\n<div class="headline-content-time">{operDateString}</div>\n<div>该头条已经删除</div>\n            <div class="headline-content-info">\n                <li class="user">{nickname}</li>\n                <li class="votes">{praiseNum}</li>\n                <li class="comments">{replyNum}</li>\n            </div>\n        </div>\n    </div>\n</div>';
        var content = $('#content'); //头条信息占位DIV
        var btnVotes = $('.votes'); //点赞占位DIV
        var commentListPos = $('#commentListPos'); //评论列表占位DIv
        var listPicUrl = '<%=ctxPath%>/headlineAttachList';
        var votesUrl = '<%=ctxPath%>/votesHeadline';
        var commentsUrl = '<%=ctxPath%>/listHeadlineComments';
        var commentsTemplate = '<div class="headline-list jiange-top clearfix headline-replay-relative">\n<div class="headline-replay-num">{replyOrder}</div>\n<div class="headline-content-layout jiange-top">\n<div class="headline-edit-border">\n<div class="headline-edit-l">\n<div class="headline-replay-user">{nickname}</div>\n</div>\n<div class="headline-edit-r">\n<div class="item">\n<div class="headline-replay">\n<div class="clearfix">\n回复: {parentNickNameReplyPrefix}: {content}\n</div>\n</div>\n<div class="headreply-edit-pic" id="headreply-edit-pic{replyId}"></div>\n <div class="headline-content-info clearfix whiteback">\n<li class="time">{replyStringDate}</li>\n<li class="replay"><a id="btnReply" class="btnReply" href="javascript:void(0);" onclick="javascript:replayComment(this);">回复</a><input type="hidden" name="replyId" id="replyId" value="{replyId}"></li>\n</div>\n</div>\n</div>\n</div>\n</div>\n</div>';
        var commentsTemplate4 = '<div class="headline-list jiange-top clearfix headline-replay-relative">\n<div class="headline-replay-num">{replyOrder}</div>\n<div class="headline-content-layout jiange-top">\n<div class="headline-edit-border">\n<div class="headline-edit-l">\n<div class="headline-replay-user">{nickname}</div>\n</div>\n<div class="headline-edit-r">\n<div class="item">\n<div class="headline-replay">\n<div class="clearfix">\n回复: {parentNickNameReplyPrefix}: {content}\n</div>\n</div>\n<div class="headreply-edit-pic" id="headreply-edit-pic{replyId}"></div>\n<div class="headline-content-info clearfix whiteback">\n<li class="time">{replyStringDate}</li>\n<li class="replay">&nbsp;<a  class="" href="javascript:void(0);" onclick="javascript:edit(3,{replyId});">编辑</a>&nbsp;<a id="btnReply" class="btnReply" href="javascript:void(0);" onclick="javascript:replayComment(this);">回复</a><input type="hidden" name="replyId" id="replyId" value="{replyId}"></li>\n</div>\n</div>\n</div>\n</div>\n</div>\n</div>';        
        var deteleCommentsTemplate = '<div class="headline-list jiange-top clearfix headline-replay-relative">\n<div class="headline-replay-num">{replyOrder}</div>\n<div class="headline-content-layout jiange-top">\n<div class="headline-edit-border">\n<div class="headline-edit-l">\n<div class="headline-replay-user">{nickname}</div>\n</div>\n<div class="headline-edit-r">\n<div class="item">\n<div class="headline-replay">\n<div class="clearfix">\n回复: {parentNickNameReplyPrefix}: {content}\n</div>\n</div>\n<div class="headreply-edit-pic" id="headreply-edit-pic{replyId}"></div>\n<div class="headline-content-info clearfix whiteback">\n<li class="time">{replyStringDate}</li>\n<li class="replay"><input type="hidden" name="replyId" id="replyId" value="{replyId}"></li>\n</div>\n</div>\n</div>\n</div>\n</div>\n</div>';
        var commentsTemplate2 = '<div class="headline-list jiange-top clearfix headline-replay-relative">\n<div class="headline-replay-num">{replyOrder}</div>\n<div class="headline-content-layout jiange-top">\n<div class="headline-edit-border">\n<div class="headline-edit-l">\n<div class="headline-replay-user">{nickname}</div>\n</div>\n<div class="headline-edit-r">\n<div class="item">\n<div class="headline-replay">\n<div class="clearfix">\n {content}\n</div>\n</div>\n<div class="headreply-edit-pic" id="headreply-edit-pic{replyId}"></div>\n<div class="headline-content-info clearfix whiteback">\n<li class="time">{replyStringDate}</li>\n<li class="replay"><a id="btnReply" class="btnReply" href="javascript:void(0);" onclick="javascript:replayComment(this);">回复</a><input type="hidden" name="replyId" id="replyId" value="{replyId}"></li>\n</div>\n</div>\n</div>\n</div>\n</div>\n</div>';
        var commentsTemplate3 = '<div class="headline-list jiange-top clearfix headline-replay-relative">\n<div class="headline-replay-num">{replyOrder}</div>\n<div class="headline-content-layout jiange-top">\n<div class="headline-edit-border">\n<div class="headline-edit-l">\n<div class="headline-replay-user">{nickname}</div>\n</div>\n<div class="headline-edit-r">\n<div class="item">\n<div class="headline-replay">\n<div class="clearfix">\n {content}\n</div>\n</div>\n<div class="headreply-edit-pic" id="headreply-edit-pic{replyId}" ></div>\n<div class="headline-content-info clearfix whiteback">\n<li class="time">{replyStringDate}</li>\n<li class="replay">&nbsp;<a  class="" href="javascript:void(0);" onclick="javascript:edit(2,{replyId});">编辑</a>&nbsp;<a id="btnReply" class="btnReply" href="javascript:void(0);" onclick="javascript:replayComment(this);">回复</a><input type="hidden" name="replyId" id="replyId" value="{replyId}"></li>\n</div>\n</div>\n</div>\n</div>\n</div>\n</div>';
        
        var deteleCommentsTemplate2 = '<div class="headline-list jiange-top clearfix headline-replay-relative">\n<div class="headline-replay-num">{replyOrder}</div>\n<div class="headline-content-layout jiange-top">\n<div class="headline-edit-border">\n<div class="headline-edit-l">\n<div class="headline-replay-user">{nickname}</div>\n</div>\n<div class="headline-edit-r">\n<div class="item">\n<div class="headline-replay">\n<div class="clearfix">\n {content}\n</div>\n</div>\n<div class="headreply-edit-pic" id="headreply-edit-pic{replyId}"></div>\n<div class="headline-content-info clearfix whiteback">\n<li class="time">{replyStringDate}</li>\n<li class="replay"><input type="hidden" name="replyId" id="replyId" value="{replyId}"></li>\n</div>\n</div>\n</div>\n</div>\n</div>\n</div>';
        var preCommentsSize = 2; //页面初始加载时，在更多评论按钮前面展示评论记录的条数
        var headlineStatus="";//头条是否为删除状态3表示是
        var pageSize = 20; //点击加载时的每页显示条数
        var moreCommentsTemplate = '<div>\n    <div class="clearfix jiange-top">&nbsp;</div>\n    <div id="moreCommentsCount" class="headline-replay-more clearfix jiange-top" onclick="javascript:moreCommentF(this);">更多评论（{count}条）</div>\n</div>';
        var moreCommentsClickTemplate = '<div class="headline-list jiange-top clearfix headline-replay-relative"><div id="moreComments" class="headline-replay-more clearfix jiange-top" onclick="javascript:uploadMoreCommentF(this);">点击加载更多</div></div>'
        var moreComments = $('#moreComments'); //点击加载更多按钮
        var btnReply = $('#btnReply'); //回复按钮
        var btnComments = $('#btnCommentsDiv'); //发布按钮
        var btnMoreComments = $('#moreCommentsCount'); //更多评论按钮
        var addCommentsUrl = '<%=ctxPath%>/addComments';
        var isLogin='<%=isLogin %>';
        var contentTypeId='<%=contentTypeId %>';
        var isReply='<%=isReply %>';
        var imageAccessPath='<%=imageAccessPath  %>';
        var limitPicTimes=0;
        var mobile='<%=mobile%>';
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
        //
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
        function formatString(content, str, newStr) {
            var re = new RegExp('\\{' + str + '\\}', 'gm');
            return content.replace(re, newStr);
        }
        function formatAgain() {
            var commentsSortPos = $('.headline-detail-sort');
            var contentTypeId = commentsSortPos.text();
            var contentTypeDesc = '';
            if (contentTypeId == '1') {
                contentTypeDesc = '灌水';
            } else if (contentTypeId == '2') {
                contentTypeDesc = '新闻';
            } else if (contentTypeId == '3') {
                contentTypeDesc = '八卦';
            } else if (contentTypeId == '4') {
                contentTypeDesc = '招聘';
            } else if (contentTypeId == '5') {
                contentTypeDesc = '求助';
            } else if (contentTypeId == '6') {
                contentTypeDesc = '公告';
            }
            commentsSortPos.text(contentTypeDesc);
        }
        function formatCommentsOrderNumber(content, count) {
            return formatString(content, 'replyOrder', count);
        }
        function insertLzPicImg() {

        }
        
        // btnMoreType='showMoreCount' 显示“更多评论（XX条）”
        // btnMoreType='showMore' 显示“点击加载更多”
        function loadComments(_paramData, btnMoreType) {
        	 
        
            ajaxPost(commentsUrl, _paramData, function (_data) {
                if (_data.list) {
                    commentListPos.empty();
                       
                    $.each(_data.list, function (index, record) {
                    
                    	
                        var _commentsTemplate = commentsTemplate;
                        if(_paramData['headlineStatus'] == "3")	{//头条是否为删除状态
                        		_commentsTemplate = deteleCommentsTemplate;
                        		$("#pinglunId").hide();
                        }
                        
                        if(record['mobile'] == mobile){//是否为自己的评论
                        	_commentsTemplate=commentsTemplate4; 
                        }
                        if(record['parentReplyId'] == "0"){//是否回复头条（是）
                        		_commentsTemplate = commentsTemplate2; 
                        	if(record['mobile'] == mobile){//是否为自己的评论
                        	_commentsTemplate=commentsTemplate3; 
                        	}	
                        	if(_paramData['headlineStatus'] == "3")	{//头条是否为删除状态不能回复
                        		_commentsTemplate = deteleCommentsTemplate2;
                        	}
                        }	
                        	
                           	
                        $.each(record, function (_key, _value) {
                     
                            _commentsTemplate = formatString(_commentsTemplate, _key, _value);
                       		
                        });
                        //
                        var imgLi="";
                         ajaxPost(ctxPaths+'/findHeadlineReply', {replyId: record['replyId']}, function (_data) {
		                       for(var i=0;i<_data.headlinePicList.length;i++){
		                             imgLi += '<img src="<%=ctxPath%>/listHeadlineReplyAttach?pictureSerialnum=' + _data.headlinePicList[i].pictureSerialnum + '" width="100px"  class="lightbox" />\t';
		                            $('#headreply-edit-pic'+record['replyId']).html(imgLi);
		                           	addLigthBoxEvent();		 
		                       	
		                       }
                   		 	});
                        commentListPos.append($(formatCommentsOrderNumber(_commentsTemplate, parseInt(_data.count) - index)));
                    });
                    if (_data.more == 'yes') {
                        if (btnMoreType == 'showMoreCount') {
                            commentListPos.append($(formatString(moreCommentsTemplate, 'count', parseInt(_data.moreCount))));
                            //
                            var _commentsTemplate = commentsTemplate;
                             if(_data.first['mobile'] == mobile){//是否为自己的评论
                        		_commentsTemplate=commentsTemplate4; 
                       		 }
                             if(_paramData['headlineStatus'] == "3")	{//头条是否为删除状态
                        		_commentsTemplate = deteleCommentsTemplate;
                        		$("#pinglunId").hide();
                       		 }
	                         if(_data.first['parentReplyId'] == "0"){
	                        		_commentsTemplate = commentsTemplate2;
	                        		 if(_data.first['mobile'] == mobile){//是否为自己的评论
                        				_commentsTemplate=commentsTemplate3; 
                       		 		}
	                        		if(_paramData['headlineStatus'] == "3")	{//头条是否为删除状态不能回复
                        				_commentsTemplate = deteleCommentsTemplate2;
                        			}
	                        }
		                                         
                            $.each(_data.first, function (_key, _value) {   
                                _commentsTemplate = formatString(_commentsTemplate, _key, _value);
                            });
                            var imgLi2="";
                            ajaxPost(ctxPaths+'/findHeadlineReply', {replyId: _data.first['replyId']}, function (data) {
		                       for(var i=0;i<data.headlinePicList.length;i++){
		                             imgLi2 += '<img src="<%=ctxPath%>/listHeadlineReplyAttach?pictureSerialnum=' + data.headlinePicList[i].pictureSerialnum + '" width="100px"  class="lightbox" />\t';
		                            $('#headreply-edit-pic'+_data.first['replyId']).html(imgLi2);
		                           	addLigthBoxEvent();		 
		                       	
		                       }
                   		 	});
                            commentListPos.append($(formatCommentsOrderNumber(_commentsTemplate, 1)));
                        } else if (btnMoreType == 'showMore') {
                          if(_paramData['headlineStatus'] == "3")	{//头条是否为删除状态
                        		$("#pinglunId").hide();
                       		 }
                            commentListPos.append($(moreCommentsClickTemplate));
                        }
                    }
                }
                
            })
        }
        //
        function reload() {
			
            ajaxPost(detailUrl, paramData, function (data) {
                if (data.detail) {
                    content.empty();
                    detailTemplate = detailTemplate_orig;
                    $.each(data.detail, function (key, value) {
                        detailTemplate = formatString(detailTemplate, key, value);
                        deleteTemplate = formatString(deleteTemplate, key, value);
                        if(key=='mobile'&&value==mobile){
                        	detailTemplate=detailTemplate2;
                        }
                        if (key == 'status' && value == '3') {
                            detailTemplate = deleteTemplate;
                            headlineStatus="3";
                            $("#pinglunId").hide();
                        }
                    });
                    content.append($(detailTemplate));
                    formatAgain();
                    // 获取用户头像
                    $('#lzHeadShowPic').append('<img src="<%=ctxPath%>/my/findAttach?mobile=' + data.detail['mobile'] + '" class="lightbox"   width="40" />');
                    //
                    ajaxPost(listPicUrl, {headlineId: data.detail['headlineId']}, function (_data) {
                        $.each(_data, function (_index, _record) {
                            var imgLi = '<img src="<%=ctxPath%>/listHeadlineContentAttach?pictureSerialnum=' + _record.pictureSerialnum + '" width="100%"  class="lightbox" />';
                            $('.headline-edit-pic').append(imgLi);
                           	addLigthBoxEvent();		 
                        });
                    });
                 
                   
                    //
                    var _paramData = $.extend({}, paramData);
                    _paramData = $.extend(_paramData, {preCommentsSize: preCommentsSize},{headlineStatus:headlineStatus});
                    loadComments(_paramData, 'showMoreCount');
                    // 更多评论按钮
                    btnMoreComments.live('click', function (obj) {
                        //
                        _paramData.preCommentsSize = pageSize;
                        loadComments(_paramData, 'showMore');
                    });
                    // 点击加载更多按钮
                    moreComments.live('click', function (obj) {
                        //
                        var hasCommentsListSize = commentListPos.children('div').length;
                        _paramData.preCommentsSize = pageSize + parseInt(hasCommentsListSize);
                        loadComments(_paramData, 'showMore');
                    });
                }
            var readOnly =data.readOnly;
            $('#readOnlyId').html(readOnly+"");
            });
            $('#comments').val('');
            $("#pic_viewDiv").html("");
            limitPicTimes=0;
        }
        
        // 评论按钮
        btnComments.click(function (obj) {
      		 
            var commentsText = $('#comments').val();
            if (getByteLen(commentsText) <= 0) {
               alert("输入内容为空，请重新输入");
                $('#comments').focus();
               return ;
            }
            // 判断是否回复还是评论，需要修改parentReplyId
            if (commentsText.indexOf('回复：') != 0) { //内容开头没有回复，视作为对头条的评论
                $('#parentReplyId').val('0');
            }
            // 提交评论
            var parentReplyId = $('#parentReplyId').val();
            var _paramData = $.extend({}, paramData);
            _paramData = $.extend(_paramData, {comments: commentsText, parentReplyId: parentReplyId},{headlineStatus:headlineStatus});
           if(isLogin=="true"){
              if($('#readOnlyId').html()=="true"){
       			alert("管理员禁止此贴评论！请联系管理员！")
	       	  }else{
		            ajaxPost(addCommentsUrl+'?picNums='+getPicNum(), _paramData, function (data) {
		            	alert("发送成功");
		                reload();
		              
		            });
		      }
            }else{
            	window.location.href='<%=ctxPath%>/login';
        	}
        });
        
       function submitComment(replyId) {
       	if($('#readOnlyId').html()=="true"){
       		alert("管理员禁止了此贴！请联系管理员！")
       	}else{
       	 
            var replyContent = $('#replysContent').val();
            if (getByteLen(replyContent) <= 0 ) {
               alert("输入内容为空，请重新输入");
                $('#replysContent').focus();
          } else {
          
                var _paramData = $.extend({}, paramData);
                _paramData = $.extend(_paramData, {comments: replyContent, parentReplyId: replyId},{headlineStatus:headlineStatus});
            if(isLogin=="true"){
              if($('#readOnlyId').html()=="true"){
       			alert("管理员禁止此贴评论！请联系管理员！")
	       	  }else{
	                ajaxPost(addCommentsUrl+'?picNums='+getPicNum(), _paramData, function (data) {
	                	alert("发送成功");
	                    reload();
	              
	                });
               }
            }else{
            	window.location.href='<%=ctxPath%>/login';
        	}
                
           }
         }
        };
        
        // 回复按钮
        function replayComment(target) {
        	$("#pic_viewDiv").html("");
            limitPicTimes=0;
            // 赋值parentReplyId
            var replyId = $(target).next('input').val();
            
            $('#parentReplyId').val(replyId);
            // 赋值nickName
            var nickNameDivValue = $(target).closest('div:has(".headline-edit-l")').children('div:first-child').text();
            //
            $('#replysContent').val('');
            $('.replyContentDiv').remove();
            var btnText = $(target).text();
            if (btnText == '回复') {
                $('.btnReply').text('回复');
                $(target).text('取消回复');
                $(target).closest('div:has(".headline-content-info")').append('<div class="replyContentDiv"><div class="headline-content-layout">\n    <div class="headline-edit-border whiteback">\n        <textarea id="replysContent" name="replysContent" rows="5" class="headline-replay-input" placeholder="回复"></textarea>\n    </div>\n'+
                  '<div class="form-group">'+
	                '<label for="添加照片">&nbsp;&nbsp;</label>'+
	                '<div class="e_tForm_uploadBtn">'+
		                '<form name="imageForm" enctype="multipart/form-data" action="<%=ctxPath%>/imageUpload.tv/0" method="post">'+
							'<div class="headline-edit-addpic" onclick="getclick();">+上传图片</div>'+
							'<input name="fileBox"id="t_file" class="fileBtn" onchange="uploadPrizeFile(this);" type="file">'+
						'</form>'+
				  	'</div>'+
			     '</div>'+
			      '<div class="form-group">'+
			                '<label for="预览">预览：</label>'+
			                  '<div class="col-xs-12" id="pic_viewDiv">'+  
			             
			                  '</div>         '+
			       '</div> '+
                '</div>\n<div id="btnReplyDiv" class="headline-replay-btn clearfix jiange-top" onclick="javascript:submitComment(' + replyId + ')">发表回复</div></div>');
            } else if (btnText == '取消回复') {
                $(target).text('回复');
                $('#replysContent').val('');
                $('#parentReplyId').val('0');
                $('.replyContentDiv').remove();
            }
        }
      var votestimes=0;
        // 点赞按钮
        function voteFunction(target){
        	if(votestimes<1){
	        	var parentReplyId = $("#parentReplyId").val();
	            var parentMobile = $("#parentMobile").val();
	            var _paramData = $.extend({}, paramData);
	            _paramData = $.extend(_paramData, {parentReplyId: parentReplyId, parentMobile: parentMobile});

	            if(isLogin=="true"){
	           	if($('#readOnlyId').html()=="true"){
	       			alert("管理员禁止此贴评论！请联系管理员！")
	       		}else{
		            ajaxPost(votesUrl, _paramData, function (data) {
		                $('#votesId').text(data);
		            });
		         }
	             }else{
	            	window.location.href='<%=ctxPath%>/login';
	            }
	            votestimes++;
            }
        }	
        //加载更多评论
        function moreCommentF(target){
        	var _paramData = $.extend({}, paramData);
            _paramData = $.extend(_paramData, {preCommentsSize: preCommentsSize},{headlineStatus:headlineStatus});
            loadComments(_paramData, 'showMoreCount');
            _paramData.preCommentsSize = pageSize;
            loadComments(_paramData, 'showMore');
            
            
        }
        
        //点击加载更多
        function uploadMoreCommentF(target){
        	var hasCommentsListSize = commentListPos.children('div').length;
            _paramData.preCommentsSize = pageSize + parseInt(hasCommentsListSize);
            loadComments(_paramData, 'showMore');
        }
        //编辑评论2、回复3和头条1
		function edit(flag,Id){
			window.location.href='<%=ctxPath%>/toutiao/toutiaoEditIndex?flag='+flag+'&id='+Id+'&headlineId=<%=headlineId%>';
		}
 $(document).ready(function() {
			if(contentTypeId=="6"){
			     detailTemplate_orig = '<div class="headline-content-layout">\n    <div class="headline-edit-border">\n      <div class="headline-edit-l">\n        <div class="headline-edit-lz"><img src="<%=ctxPath%>/resources/images/icon_lz.png" /></div>\n<div id="lzHeadShowPic"></div>\n<div class="jiange-top">{nickname}</div>\n        <div class="jiange-top"><span class="headline-detail-sort">{contentTypeId}</span></div>\n      </div>\n      \n      <div class="headline-edit-r">\n          \n          <div class="item">\n            <div class="headline-content-title">{title}</div>\n            <div class="headline-detail">\n              <div class="clearfix">{content}</div>\n              <div class="headline-edit-pic"></div>\n            </div>\n            \n          </div>\n      </div>\n     \n      <div class="headline-content-info clearfix whiteback"><li class="time">{operDateString}</li><input type="hidden" name="parentReplyId" id="parentReplyId" value="{headlineId}"><input type="hidden" name="parentMobile" id="parentMobile" value="{mobile}"></div> \n    </div>\n  </div>';
			
				$("#pinglunId").hide();
			}
			
			reload();
        });
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
}
</script>
</body>
</html>