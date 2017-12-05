<%@ page import="com.aspire.wifi.wap.util.GetConfigFile;" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String nickName = request.getAttribute("nickName") == null ? "" : request.getAttribute("nickName").toString();
    String mobile = request.getAttribute("mobile") == null ? "" : request.getAttribute("mobile").toString();
    String flag = request.getAttribute("flag") == null ? "" : request.getAttribute("flag").toString();
    String Id = request.getAttribute("Id") == null ? "" : request.getAttribute("Id").toString();
    String headlineId = request.getAttribute("headlineId") == null ? "" : request.getAttribute("headlineId").toString();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=yes,minimum-scale=1.0,maximum-scale=2.0" id="viewport" />

    <title>中国移动免费WIFI上网</title>
    <%@ include file="../common/include.jsp" %>
    <script type="text/javascript" src="<%=ctxPath%>/resources/js/file.js"></script>
</head>
 <style type="text/css">
   .headline-sort-block .qiuzhu{background-color:#a6a2a2;}
   .headline-sort-block .xinwen{background-color:#00b2cc;}
   .headline-sort-block .guanshui{background-color:#ffac0c;}
   .headline-sort-block .gonggao1{background-color:#ff6060;}
 </style>
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
<div class="headline-list clearfix jiange-top">
    <div class="headline-content-layout">
        <div class="headline-edit-border">
            <form id="headlineForm" name="headlineForm" method="post" enctype="multipart/form-data">
                <input type="hidden" name="headlineId" id="headlineId">
                <div class="headline-edit-l">
                    <div class="headline-edit-lz"><img src="<%=ctxPath%>/resources/images/icon_lz.png"/></div>
                    <div><img src="<%=ctxPath%>/my/findAttach?mobile=<%=mobile%>" width="40" class="lightbox" class="lightbox" /></div>
                    <div><%=nickName%></div>
                </div>
                <div class="headline-edit-r">
                    <div class="item" id="headtypeId">
                        <div class="title">类别：</div>
                        <div class="content">
                            <input type="hidden" name="contentTypeId" id="contentTypeId">
                            <div class="headline-sort-block" id="xinwen"><span class="unSelect xinwen">新闻</span></div>
                            <div class="headline-sort-block" id="guanshui"><span class="unSelect guanshui">灌水</span></div>
                            <!--div class="headline-sort-block"><span class="unSelect">八卦</span></div-->
                            <!--div class="headline-sort-block"><span class="unSelect">招聘</span></div-->
                            <div class="headline-sort-block" id="qiuzhu"><span class="unSelect qiuzhu">求助</span></div>
                           <!-- <div class="headline-sort-block"><span class="unSelect gonggao1">公告</span></div>-->
                        </div>
                    </div>
                    <div class="item" id="headtitileId">
                        <div class="title">标题：</div>
                        <div class="content">
                            <input name="title" id="title" type="text" value="" class="input" placeholder="标题（不能超过31汉字）"/>
                        </div>
                    </div>
                    <div class="item">
                        <div class="title"id="headcontentID">正文：</div>
                        <div class="title" id="punlunId" style="display: none">评论</div>
                        <div class="content">
                            <textarea name="content" id="content" rows="5" class="input" placeholder="正文(不能超过140汉字)"></textarea>
                        </div>
                        <div class="headline-edit-addpic" id="uploadImageId">+上传图片</div>
                    </div>
                    <div class="item">
                        <div class="title">预览：</div>
                        <div class="content" id="imgReviewContent"></div>
                    </div>
                    <div class="item">
                        <div class="headline-edit-btn clearfix" id="fabuID"><span>发布</span></div>
                        <div class="headline-edit-btn clearfix" id="punlunSubmitID" style="display: none"><span>更新</span></div>
                    </div>
                    <div class="item">
                        <div style="height: 70px"><span> </span></div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<!--<div class="qiangzhuo-more">头条发布审核中...</div>-->

<!--导航菜单-->
<jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
<!--导航菜单结束-->

<script type="text/javascript">
    	var flag ='<%=flag%>';
    	var Id ='<%=Id%>';
    	var headlineId ='<%=headlineId%>';
    	var listPicUrl = '<%=ctxPath%>/headlineAttachList';
    	var listPicUrl2 = '<%=ctxPath%>/listHeadlineContentAttach';
    	var headlineForm = $('#headlineForm');
        var btnSubmit = $('.headline-edit-btn');
        var btnUploadPic = $('.headline-edit-addpic');
        var imgReviewContent = $('#imgReviewContent');
    	var picReviewTemplate = '<div class="headline-edit-pic">\n    <img id="img" name="headlineAttach" src="<%=ctxPath%>/resources/images/pic_sample02.jpg" width="100%;"/>\n    <div class="headline-edit-pic-del">\n        <img src="<%=ctxPath%>/resources/images/icon_del.png" width="90%"/>\n    </div>\n</div>';
        
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
       function closePicss(obj){
              $(obj).parent().remove();
       }
    $(function () {
    
        //
        getMessage();
        btnUploadPic.click(function (obj) {
            //
            var fileSize = imgReviewContent.children('div').length;
            if (fileSize >= 4) {
                alert("仅能上传4个图片");
                return ;
            }
            //
            if (typeof FileReader === 'undefined') {
                $('.headline-edit-addpic').text('抱歉，你的浏览器不支持 FileReader');
            } else {
                //
                var inputPic = $('<input type="file" name="uploadPics" style="position:absolute; top:0; right:80px; height:24px; filter:alpha(opacity:0);opacity: 0;width:260px">');
                inputPic.on('change', function () {
                    var inputFile = this;
                  //检查文件类型和大小, 单位:M
                	var maxSize = <%=GetConfigFile.getInstance().getProperties("maxUploadPicSize")%>;
                	var result = checkFile(inputFile,0, maxSize);
                	if (result == "FILE_TYPE_ERROR") {
                		phoneAlert("图片类型错误, 请重新选择图片!",3000);
                		return false;
                	} else if (result == "FILE_SIZE_ERROR") {
                		phoneAlert("附件大小不能大于" + maxSize / (1024 * 1024)+ "M！",3000);
                		return false;
                	} else if (result == "EMPTY_FILE") {
                		phoneAlert("图片文件错误, 请重新选择图片！",3000);
                		return false;
                	}
                    if (inputFile.files && inputFile.files[0]) {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var reviewImg = $(picReviewTemplate);
                            imgReviewContent.append(reviewImg);
                            $('.headline-edit-pic:last img:first').attr('src', e.target.result);
                            //
                            $('.headline-edit-pic:last').append(inputFile);
                            //
                            $('.headline-edit-pic-del').click(function (obj) {
                                $(this).parent().remove();
                            });
                        };
                        reader.readAsDataURL(this.files[0]);
                    }
                });
                inputPic.focus().click();
            }
        });
        //
        btnSubmit.click(function (obj) {
        	if(flag=="1"||flag==""){
	            if (!$('#contentTypeId').val()) {
	                alert('请选择头条类型');
	                return ;
	            }
	            if (getByteLen($('#title').val()) <= 0) {
	                alert('输入的标题不能为空');
	                return ;
	            }
            }
            //if (getByteLen($('#title').val()) > 62) {
             //   alert('输入的标题太长，内容要求31个字符以内');
             //   return ;
          //  }
            if (getByteLen($('#content').val()) <= 0) {
                alert('输入的内容不能为空');
                return ;
            }
           // if (getByteLen($('#content').val()) > 280) {
             //   alert('输入的内容太长，内容要求140个字符以内');
             //   return ;
          //  }
          var picNum = getPicNum();
            headlineForm.attr('action', '<%=ctxPath%>/headline/modifyHeadlineContent?picNum='+picNum+'&isEdit='+flag+'&headIds='+Id);
            if (confirm("是否确定发布")) {
            	$("body").showLoading();
            	submitToutiao(headlineForm);
            }
        });
        //
        $('.headline-sort-block').click(function (obj) {
            var selChild = $(this).children('span');
            var text = selChild.text();
            if (text == '灌水') {
                selChild.removeClass('unSelect').addClass('guanshui');
                $('#contentTypeId').val('1');
            } else if (text == '新闻') {
                selChild.removeClass('unSelect').addClass('xinwen');
                $('#contentTypeId').val('2');
            } else if (text == '求助') {
                selChild.removeClass('unSelect').addClass('qiuzhu');
                $('#contentTypeId').val('5');
            } else if (text == '公告') {
                selChild.removeClass('unSelect').addClass('gonggao1');
                $('#contentTypeId').val('6');
            }
            $(this).siblings().children('span').removeClass().addClass('unSelect');
        });
        	addLigthBoxEvent();		
    });
    
    /*提交头条*/
    function submitToutiao(headlineForm){
    	var options = {
    		success: function(data){
    			$("body").hideLoading();
    		    var data = jQuery.parseJSON(data);
    			if(data.CODE=="FALSE"){
    				phoneAlert(data.msg,3000);
    				//alert(data.msg,3000);
    			}else if(data.CODE=="TRUE"){
    			//刷新下页面	
    			//	phoneAlert(data.msg,3000);
    				alert(data.msg);
    				if(flag!=""	){
    					window.location.replace(ctxPaths+"/toutiao/toutiaoDetailIndex?headlineId="+headlineId+"&contentTypeId="+$('#contentTypeId').val());
    				}else{
    					window.location.replace(ctxPaths+"/toutiao/toutiaoIndex?contentTypeId="+$('#contentTypeId').val());
    				}
    			}else{
    				//alert("对不起网络异常！",3000);
    				phoneAlert("对不起网络异常！",3000);
    			}
    		},
    		error: function(xhr) {
    	    	//去除遮罩
    	    	$("body").hideLoading();
    			alert("上传图片失败了！");
    	    }
    	};
    	
    	$("body").showLoading();
    	headlineForm.ajaxSubmit(options);
    	
    	return false;
    }

  function getMessage(){
  	if(flag=="1"&&Id!=""){
	  	var jsonData = {headlineId:Id};
	  		jQuery.ajax
				({
					type: "post",
					url: ctxPaths + "/findHeadlineContent",
					dataType: "json",
					data : jsonData,
					cache: false,
					success: function(data){
						
						var de = data.detail;
						  $("#title").val(de.title);
						  $("#content").text(de.content);
						  $('#contentTypeId').val(de.contentTypeId);
						  if(de.contentTypeId=="2"){
						  	$("#xinwen").click();
						  }else if(de.contentTypeId=="1"){
						  	$("#guanshui").click();
						  }else if(de.contentTypeId=="5"){
						  	$("#qiuzhu").click();
						  }
					 ajaxPost(listPicUrl, jsonData, function (_data) {
                        $.each(_data, function (_index, _record) {
                            var imgLi = '<div class="headline-edit-pic">\n <img src="<%=ctxPath%>/listHeadlineContentAttach?pictureSerialnum=' + _record.pictureSerialnum + '" width="100%"  class="lightbox" />  <div class="headline-edit-pic-del" id="closePic" onclick="closePicss(this);">\n '+
                                  ' <img src="<%=ctxPath%>/resources/images/icon_del.png" width="90%"/>\n    </div>\n<input name="contentNum"  type="hidden" value="'+_record.pictureSerialnum+'"> </div>';
                            imgReviewContent.append(imgLi);
                           	addLigthBoxEvent();		 
                        });
                    });
					}
				});
	  	}else if(flag=="2"||flag=="3"){
	  	$("#headtypeId").hide();
	  	$("#headtitileId").hide();
	  	$("#headcontentID").hide();
	  	$("#fabuID").hide();
	 // 	$("#uploadImageId").hide();
	  	$("#punlunId").show();
	  	$("#punlunSubmitID").show();
	  	
						
	  		var jsonData = {replyId:Id};
	  		jQuery.ajax
				({
					type: "post",
					url: ctxPaths + "/findHeadlineReply",
					dataType: "json",
					data : jsonData,
					cache: false,
					success: function(data){
						var de = data.detail;
						$("#content").text(de.content); 
						var headlinePicList = data.headlinePicList;
							for(var i=0;i<headlinePicList.length;i++){
	                            var imgLi = '<div class="headline-edit-pic">\n <img src="<%=ctxPath%>/listHeadlineReplyAttach?pictureSerialnum=' + headlinePicList[i].pictureSerialnum + '" width="100%"  class="lightbox" />  <div class="headline-edit-pic-del" id="closePic" onclick="closePicss(this);">\n '+
	                                  		'<img src="<%=ctxPath%>/resources/images/icon_del.png" width="90%"/>\n    </div>\n<input name="contentNum"  type="hidden" value="'+headlinePicList[i].pictureSerialnum+'"> </div>';
	                            imgReviewContent.append(imgLi);
	                           	addLigthBoxEvent();		 
                        	}
                    
					}
				});
	  	}
	 }	
	 
/**
**获取图片num
**/
function getPicNum()
{
var str=document.getElementsByName("contentNum");
var objarray=str.length;
var chestr="";
for (i=0;i<objarray;i++)
{

   chestr+=str[i].value+",";

}

return chestr;
}	 
	 	
</script>
</body>
</html>