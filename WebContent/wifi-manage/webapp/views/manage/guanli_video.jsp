<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>北京移动wifi分发管理平台</title>
		<%@include file="../common/include.jsp"%>
		<script type="text/javascript" src="<%=ctxPath%>/resources/js/json.js"></script>
		<style type="text/css">
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
</style>
	</head>

	<body>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<h4>
						<ol class="breadcrumb">
							<li>
								<a href="#"><span class="glyphicon glyphicon-th-large">&nbsp;</span>视频管理</a>
							</li>
						</ol>
					</h4>
				</div>
			</div>

			<!--搜索-->
			<p></p>
			<div class="panel panel-default">
				<div class="panel-body bg-info">
					<form class="form-inline pull-right" role="form">
					<button type="button" id="insertVideoInfo"
							class="btn btn-primary" data-target="#myModal3" data-toggle="modal">
							新增
						</button>
						<div class="form-group">
							<label class="sr-only" for="exampleInputPassword2">
								请输入关键字
							</label>
							<input type="text" class="form-control"
								id="exampleInputPassword2" placeholder="">
						</div>
						<div class="form-group">
							<label class="sr-only" for="exampleInputPassword2">
								查询条件
							</label>
							<select class="form-control" id="videoByNameSearch">
								<option value="1">
									按展示名
								</option>
								<option value="2">
									按文件名
								</option>
							</select>
							<select class="form-control" id="videoByStatusSearch">
								<option value="">
									全部状态
								</option>
								<option value="0">
									新增
								</option>
								<option value="1">
									上线
								</option>
								<option value="2">
									下线
								</option>
							</select>
						</div>
						<button type="button" id="videoSearchSubmit"
							class="btn btn-primary">
							查询
						</button>
						</from>
				</div>
			</div>

			<div class="table-responsive">
				<form id="videoInfoListForm" method="post"
					loadUrl="<%=ctxPath%>/auditList/videoInfoList">
					<table class="table table-hover table-striped">
						<thead>
							<tr>
								<th>
									<input name="" type="checkbox" value="" />
									全选
								</th>
								<th>
									编号
								</th>
								<th>
									文件名
								</th>
								<th>
									展示名
								</th>
								<th>
									状态
								</th>
								<th>
									操作
								</th>
							</tr>
						</thead>
						<tbody id="videoContentId">
						</tbody>
					</table>
				</form>
			</div>

			<div class="row">
				<div id="videoContentPageId"></div>
				<div class="col-xs-8">
					<ul class="pagination pagination-sm pull-right" id="video_next_">
					</ul>
				</div>
			</div>

		</div>

		<!-- Modal2 -->
		<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							视频配置
						</h4>
					</div>
					<div class="modal-body">
						<table class="table">
							<tr>
								<td>
									<strong>编号：</strong>
								</td>
								<td class="videoIdClass"></td>
							</tr>
							<tr>
								<td>
									<strong>文件名：</strong>
								</td>
								<td class="videoResourePathClass"></td>
							</tr>
							<tr>
								<td>
									<strong>展示名：</strong>
								</td>
								<td>
									<input type="text" class="form-control videoNameClass"
										id="intro" placeholder="">
								</td>
							</tr>
							<tr>
								<td>
									<strong>时长：</strong>
								</td>
								<td class="videoShowTimeLongClass"></td>
							</tr>
							<tr>
								<td>
									<strong>地址：</strong>
								</td>
								<td class="videoResourePathClass"></td>
							</tr>
							<tr>
								<td>
									<strong>简介：</strong>
								</td>
								<td>
									<input type="text" class="form-control videoDescClass"
										id="intro" placeholder="">
								</td>
							</tr>
							<tr>
								<td>
									<strong>类别：</strong>
								</td>
								<td>
									<div class="checkVideoType" data-toggle="buttons">
										<label class="btn btn-default">
											<input type="radio" name="options" id="option1"
												autocomplete="off" value="3">
												微博江湖 
										</label>
										<label class="btn btn-default">
											<input type="radio" name="options" id="option2"
												autocomplete="off" value="1">
												电影音乐 
										</label>
										<label class="btn btn-default">
											<input type="radio" name="options" id="option3"
												autocomplete="off" value="2">
												娱乐八卦 
										</label>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<strong>图片：</strong>
								</td>
								<td>
									<div class="form-group">
									 <div class="e_tForm_uploadBtn">
										<form id="image_form" name="imageForm"
											enctype="multipart/form-data"
											action="<%=ctxPath%>/imageUpload.tv/0" method="post">
											<%--<label for="exampleInputFile">
												点此上传宣传大图
											</label>
											--%><input value="上传" type="button">
											<input name="fileBox" class="fileBtn" onchange="uploadVideoFile(this);" type="file"/>
										</form>
									</div>
									</div>
									<div class="alert alert-warning alert-dismissible pic-preview " role="alert" id="pic_viewDiv">
										<!--  <img src="<%=ctxPath%>/resources/images/pic_sample02.jpg" >-->
									</div>
									<br/>
									<p class="small text-red" style="float:left">
										（列表图尺寸：372 x 188，详情页尺寸：600 x 300）
									</p>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
								<td>
									<label class="jiange-r">
										<!--   <input type="checkbox">推广设置-->
									</label>
									<label class="sr-only" for="exampleInputPassword2"></label>
									<select class="form-control smallOrBigPicChoose">
										<option value="0">
											发现-小图
										</option>
										<option value="1">
											发现-大图
										</option>
									</select>
									<!--    <button type="button" class="btn btn-xs btn-success jiange-l">查看预览效果</button>-->
									<p class="small text-red">
										注：本次推广设置将覆盖上次的操作
									</p>
								</td>
							</tr>
						</table>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary cancelhiden" data-dismiss="modal">
							取消
						</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal"
							id="btnOkFormVideo">
							确定
						</button>
					</div>
				</div>
			</div>
		</div>


<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							视频新增
						</h4>
					</div>
					<div class="modal-body">
						<table class="table">
								<td>
									<strong>文件名：</strong>
								</td>
								<td >
								   <input type="text" class="form-control insertvideoResourePathClass"
										id="intro" placeholder="">
								</td>
							</tr>
							<tr>
								<td>
									<strong>展示名：</strong>
								</td>
								<td>
									<input type="text" class="form-control insertvideoNameClass"
										id="intro" placeholder="">
								</td>
							</tr>
							<tr>
								<td>
									<strong>时长：</strong>
								</td>
								<td>
								  <input type="text" class="form-control insertvideoShowTimeLongClass"
										id="intro" placeholder="">
								</td>
							</tr>
							<tr>
								<td>
									<strong>简介：</strong>
								</td>
								<td>
									<input type="text" class="form-control insertvideoDescClass"
										id="intro" placeholder="">
								</td>
							</tr>
							<tr>
								<td>
									<strong>类别：</strong>
								</td>
								<td>
									<div class="insertcheckVideoType" data-toggle="buttons">
										<label class="btn btn-default">
											<input type="radio" name="radio_options" id=""
												autocomplete="off" value="3">
												微博江湖 
										</label>
										<label class="btn btn-default">
											<input type="radio" name="radio_options" id=""
												autocomplete="off" value="1">
												电影音乐 
										</label>
										<label class="btn btn-default">
											<input type="radio" name="radio_options" id=""
												autocomplete="off" value="2">
												娱乐八卦 
										</label>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<strong>图片：</strong>
								</td>
								<td>
									<div class="form-group">
									 <div class="e_tForm_uploadBtn">
										<form id="image_form" name="imageForm"
											enctype="multipart/form-data"
											action="<%=ctxPath%>/imageUpload.tv/0" method="post">
											<%--<label for="exampleInputFile">
												点此上传宣传大图
											</label>
											--%>
											<input value="上传" type="button">
											<input name="fileBox" class="fileBtn"
												onchange="uploadVideoFile(this);" type="file"
												id="exampleInputFile">
										</form>
									</div>
									</div>
									<div class="alert alert-warning alert-dismissible pic-preview" role="alert" id="pic_viewDiv_insert">
									</div>
									<br/>
									<p class="small text-red" style="float:left">
										（列表图尺寸：372 x 188，详情页尺寸：600 x 300）
									</p>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
								<td>
									<label class="jiange-r">
										<!--   <input type="checkbox">推广设置-->
									</label>
									<label class="sr-only" for="exampleInputPassword2"></label>
									<select class="form-control smallOrBigPicChooseinsert">
										<option value="0">
											发现-小图
										</option>
										<option value="1">
											发现-大图
										</option>
									</select>
									<!--    <button type="button" class="btn btn-xs btn-success jiange-l">查看预览效果</button>-->
									<p class="small text-red">
										注：本次推广设置将覆盖上次的操作
									</p>
								</td>
							</tr>
						</table>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary cancelhiden" data-dismiss="modal">
							取消
						</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal"
							id="insertVideoInfoSubmit">
							确定
						</button>
					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript">
       var page=0;
       var size=countPerQuery;
        var resourcePath='';
       var videoName='';
       var videoStatus='';
       var picName='';
       //是否第一次加载,是否是条件查询
       var firstornull='';
       var currPage =0;
      $(document).ready(function(){
    	  getVideoManage(page,size,firstornull);
         
       $("body").on("click","#video_next_ #hd a",function(){
          firstornull=$(this).parent().attr("idnum");
          getVideoManage($(this).text()-1,size,firstornull);
          $(this).parent().addClass("active");
         });
       
       $("#videoSearchSubmit").bind("click",function(){
           getVideoManage(page,size,'query');
          });
   
       $("#btnOkFormVideo").bind("click",function(){
           var flag=false;
    	     var videoId=$(".videoIdClass").html();
    	     var videoName=$(".videoNameClass").val();
    	     var placeholderName=$(".videoNameClass").attr("placeholder");
    	     var videoDesc=$(".videoDescClass").val();
    	      var videoType= $('.checkVideoType input[name="options"]:checked').val();
    		var smallOrBigPic=$(".smallOrBigPicChoose").val();
    		var  str="'"+$("#pic_viewDiv img").attr("src")+"'";
    		var picStringList='';
    		if(!str=='undefined'||str.indexOf('wxyx_')!=-1){
    			picStringList=str.substring(str.indexOf('wxyx_')).replace("'","")+",";
        	}else{
        		picStringList='';
            }
    		var jsonData ={"videoId":videoId,"videoName":videoName,"videoDesc":videoDesc,"videoType":videoType,"videoName":videoName,"isTopArea":smallOrBigPic,"picStringList":picStringList};
    		jQuery.ajax
    		({
    			type: "post",
    			url: '<%=ctxPath%>'+"/auditList/updateVideoInfo",
    			dataType: "json",
    			data : jsonData,
    			cache: false,
    			success: function(data){
    		    if(data.CODE=="TRUE"){
    		    		$("#myModal2").hide();
    		    	 alert("配置成功！");
    		    		window.location.reload();
    			  }else{
                    alert("配置失败！");
    				  }
    			},
    		error: function(){
    				alert("对不起网络异常！");
    		}
    		});	
    	});

      	$(".close,.cancelhiden").on('click',function(){
          		$("#myModal2").hide();
          		$("#myModal3").hide();
        })
         
      });
      
       
        
      function getVideoManage(page,size,firstornull){
	    var url_='<%=ctxPath%>';
	    var contentSearchValue=$("#exampleInputPassword2").val();
	    var videoByNameSearch=$("#videoByNameSearch").val();
	    //是否是条件查询
	    if(firstornull!=''&&firstornull!='next'&&!isNumber(firstornull)){
		    if(videoByNameSearch==1){
		    //展示名
		      videoName=contentSearchValue;
		       resourcePath='';
		    }else{
		    //文件名
		       resourcePath=contentSearchValue;
		       videoName='';
		    }
	       videoStatus=$("#videoByStatusSearch").val();
	    }
	    var jsonData ={"page":page,"size":size,"resourcePath":resourcePath,"videoName":videoName,"videoStatus":videoStatus};
	    	jQuery.ajax
	({
		type: "post",
		url: url_+"/auditList/queryVideoInfoList",
		dataType: "json",
		data : jsonData,
		cache: false,
		success: function(data){
		if(data.CODE=="TRUE"){
		var videolineList=data.videolineList;
		var totalPage=data.totalPage;
		var page=data.page;
		var videocount=data.videocount;
		var contentHtml='';
		var pageHtml='';
		 if(videolineList.length>0){
		   for(var i=0;i<videolineList.length;i++){
		      contentHtml+='<tr><td><input name="checkboxManageName" type="checkbox" value="'+videolineList[i].videoId+'" class="checkboxClass" id="checkboxId'+videolineList[i].videoId+'"/></td>';
		      contentHtml+='<td>'+videolineList[i].videoId+'</td><td>'+videolineList[i].resourcePath+'</td><td>'+videolineList[i].videoName+'</td><td><label class="text-danger">';
		      if(videolineList[i].videoStatus==0){
			       contentHtml+='新增</label></td>';
                }
              if(videolineList[i].videoStatus==1){
			       contentHtml+='上线</label></td>';
                }
              if(videolineList[i].videoStatus==2){
			       contentHtml+='下线</label></td>';
                }
			     contentHtml+='<td><div class="btn-group"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal2" onclick="findVideoInfoByVideoId('+videolineList[i].videoId+')"><span class="glyphicon glyphicon-pencil">&nbsp;</span>配置</button></div></td>';
		   }
		     $("#videoContentId").html(contentHtml);
		 }else{
	        alert("没有记录");
		       $("#videoContentPageId").html();
		       $("#videoContentId").children().remove();
		       $("#video_next_").children("#hd").remove();
		       $("#video_next_").html("");
		  }
		 if(totalPage>0){
			 var content_li='<li class=""><a href="javascript:void(0);" onclick="setPage('+parseInt(page-1)+','+totalPage+')">&laquo;</a></li>';
			   for(var i=1;i<totalPage+1;i++){
			   content_li+=' <li class="page_li" idnum="'+i+'" id="hd"><a href="javascript:void(0);">'+i+'</a></li>';
			   }
			   content_li+='<li class=""><a href="javascript:void(0);" onclick="setPage('+parseInt(page+1)+','+totalPage+')">&raquo;</a></li>';
		       pageHtml+='<div class="col-xs-4"><h6>当前为第<span class="currPage_">'+parseInt(page+1)+'</span>页，共'+totalPage+'页， 共'+videocount+'个视频</h6></div>';
		  }
		 $("#videoContentPageId").html(pageHtml);
		 $("#video_next_").html(content_li);
		  if(page<=0){
			  $("#video_next_ li:first").addClass("disabled");
		  }
		  if(page>=totalPage-1){
			  $("#video_next_ li:last").addClass("disabled");
			  }
		 var p=$(".currPage_").html();
		 $("#video_next_ #hd").each(function(){
              if(parseInt($(this).attr("idnum"))==parseInt(p)){
                 $(this).addClass("active");
                  }
			 })
		 fanye(totalPage,page);
		}else if(data.CODE=="FALSE"){
		
		}
		},
	error: function(){
			alert("对不起网络异常！");
	}
	});	
      }
      
function isNumber(C) {
    var A = /^(\d*\.)?\d+$/;
    var B = new RegExp(A);
    return B.test(C)
};

function setPage(page ,totalPage){
	if(page<0){
		page=1;
	}else if(page>=totalPage){
		page=totalPage;
	}else{
		  getVideoManage(page,size,'next');
		}
	
}
function fanye(totalPage,page){
	 var size = totalPage;
     var index = page;	
 	  if((index+3)<size){
 	 	$('li[id="hd"]').hide();
 	 	$('li[id="hd"]').eq(index).show();
 	 	$('li[id="hd"]').eq(index+1).show();
 	 	$('li[id="hd"]').eq(index+2).show();
 	 	$('li[id="hd"]').eq(index+3).show();
 	 }
	  if(index>=(size-3)){
 	  $('li[id="hd"]').hide();
 	 	$('li[id="hd"]').eq(size-1).show();
 	 	$('li[id="hd"]').eq(size-2).show();
 	 	$('li[id="hd"]').eq(size-3).show();
 	 	$('li[id="hd"]').eq(size-4).show();
 	 }
 	 if(page+1>4&&page+1<=size){
 	   $("#video_next_ li").each(function(){
 	     if($(this).attr("idnum")==1){
 	        $(this).show().after('<li class="more_li"><a href="javascript:void(0);">...</a></li>');
 	     }
 	   });
 	 }
}

function findVideoInfoByVideoId(videoId){
	$("#pic_viewDiv").html("");
	var url_='<%=ctxPath%>';
	  var jsonData ={"videoId":videoId};
	jQuery.ajax
	({
		type: "post",
		url: url_+"/auditList/queryVideoInfoByVideoId",
		dataType: "json",
		data : jsonData,
		cache: false,
		success: function(data){
	    if(data.CODE=="TRUE"){
	    	var videoInfo=data.videoInfo;
		    $(".videoIdClass").html(videoInfo.videoId);
		    $(".videoResourePathClass").html(videoInfo.resourcePath);
		    $(".videoNameClass").attr("placeholder",videoInfo.videoName);
		    $(".videoNameClass").val(videoInfo.videoName);
		    if(videoInfo.videoType==3){
               $(".checkVideoType #option1").parent().addClass("active").siblings().removeClass("active");
			    }
		    if(videoInfo.videoType==1){
               $(".checkVideoType #option2").parent().addClass("active").siblings().removeClass("active");
			    }
		    if(videoInfo.videoType==2){
               $(".checkVideoType #option3").parent().addClass("active").siblings().removeClass("active");
			    }
		    $(".videoShowTimeLongClass").html(videoInfo.showTimeLong);
		    $(".videoDescClass").attr("placeholder",videoInfo.videoDesc);
		    $(".videoDescClass").val(videoInfo.videoDesc);
		    $("#pic_viewDiv").addClass("alert alert-warning alert-dismissible pic-preview ").append('<img width="100%" src="'+url_+'/find/findVideoIcon?videoId='+videoInfo.videoId+'">');
		  }else{

			  }
		},
	error: function(){
			alert("对不起网络异常！");
	}
	});	
}

function uploadVideoFile(obj){
	$("#pic_viewDiv").html("");
	$("#pic_viewDiv_insert").html("");
	var form1=$(obj).parent();
	form1.ajaxSubmit({
	 		type : 'post',
	    	url : "<%=ctxPath%>/imageUpload.tv/0",
	    	success:function (result, status) {
				var data = jQuery.parseJSON(result);
				if (data.CODE == "FALSE") {
				alert(data.msg);
				}else if(data.CODE == "TRUE"){
					var viewhtml='';
					//当前编辑对象
					viewhtml += '<img src="'+imageAccessPath+data.fileName+'" width="100%" alt="...">';
					viewhtml +='<input type="hidden" name="picName" value="'+data.fileName+'"/>';
					$("#pic_viewDiv").prepend(viewhtml);
					$("#pic_viewDiv_insert").prepend(viewhtml);
				}else{
				alert(window.catch_title);
				}
			}
		});
		return false;//为了防止刷新	
}

$("#insertVideoInfoSubmit").bind("click",function(){
	var flag=false;
    var insertvideoResourePathClass=$(".insertvideoResourePathClass").val();
    insertvideoResourePathClass=insertvideoResourePathClass.replace(/\ /g,"");
     if(insertvideoResourePathClass<=0){
	   alert("请输入文件名！");
       $(".insertvideoResourePathClass").focus();
       return flag;
    }
var insertvideoNameClass=$(".insertvideoNameClass").val();
insertvideoNameClass=insertvideoNameClass.replace(/\ /g,"");
if(insertvideoNameClass<=0){
  alert("请输入展示名！");
  $(".insertvideoNameClass").focus();
  return flag;
}
var insertvideoShowTimeLongClass=$(".insertvideoShowTimeLongClass").val();
if(!isNumber(insertvideoShowTimeLongClass)){
	alert("时长，请输入数字！");
	  $(".insertvideoShowTimeLongClass").focus();
	return flag;
}
var  insertvideoDescClass=$(".insertvideoDescClass").val();
insertvideoDescClass=insertvideoDescClass.replace(/\ /g,"");
if(insertvideoDescClass<=0){
alert("请输入描述信息！");
  $(".insertvideoDescClass").focus();
  return flag;
}
var insertcheckVideoType=$('input[name="radio_options"]:checked').val();
   if(typeof insertcheckVideoType=="undefined"){
          alert("请选择视频类别！");
          return flag;
	   }
   var  str="'"+$("#pic_viewDiv img").attr("src")+"'";
	var picStringList='';
	if(!str=='undefined'||str.indexOf('wxyx_')!=-1){
		picStringList=str.substring(str.indexOf('wxyx_')).replace("'","")+",";
	}else{
		picStringList='';
   }
	var smallOrBigPic=$(".smallOrBigPicChooseinsert").val();
   var url_='<%=ctxPath%>';
	  var jsonData ={"resourcePath":insertvideoResourePathClass,
			  "videoName":insertvideoNameClass,
			  "showTimeLong":insertvideoShowTimeLongClass,
			  "videoDesc":insertvideoDescClass,
			  "videoType":insertcheckVideoType,
			  "isTopArea":smallOrBigPic,
			  "picStringList":picStringList

			  };
	jQuery.ajax
	({
		type: "post",
		url: url_+"/auditList/insertVideoInfo",
		dataType: "json",
		data : jsonData,
		cache: false,
		success: function(data){
	    if(data.CODE=="TRUE"){
	    	alert("添加成功！");
	    	$("#myModal3").hide();
	    	   $(document).ready(function(){
	    	     getVideoManage(page,size,firstornull);
	    	   });
		  }else{
	    	alert("添加失败！");
			  }
		},
	error: function(){
			alert("对不起网络异常！");
	}
	});	

	
})
  $("#insertVideoInfo").bind("click" ,function(){
		$("#pic_viewDiv_insert").html("");
		$("#myModal3 table input").slice(0, 4).val("");
	  })
    </script>
	</body>
</html>

