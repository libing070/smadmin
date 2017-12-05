<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">

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
								<a href="#"><span class="glyphicon glyphicon-th-large">&nbsp;</span>应用管理</a>
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
					<button type="button" class="btn btn-primary" id="insertAppInfo" data-target="#myModal3" data-toggle="modal">
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
									按描述名
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
						<button type="button" class="btn btn-primary" id="videoSearchSubmit">
							查询
						</button>
					</form>
				</div>
			</div>

			<div class="table-responsive">

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
								描述

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
					<tbody id="appContentId">
						</tbody>
				</table>

			</div>

			<div class="row">
				<div id="appContentPageId"></div>
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
						<button type="button" class="close closehide" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							应用配置
						</h4>
					</div>
					<div class="modal-body">
					<div class="form-inline" role="form">
							<table class="table">
								<tr>
									<td>
										<strong>编号：</strong>
									</td>
									<td class="appInfo_appId">
									
									</td>
								</tr>
								<tr>
									<td>
										<strong>描述：</strong>
									</td>
									<td>
									   <input type="text" class="form-control appInfo_appDesc"  id="intro"
											placeholder="111">
									</td>
								</tr>
								<tr>
									<td>
										<strong>展示名：</strong>
									</td>
									<td>
										<input type="text" class="form-control appInfo_appName"  id="intro"
											placeholder="111">
									</td>
								</tr>
								<tr>
									<td>
										<strong>开发者：</strong>
									</td>
									<td>
										<input type="text" class="appInfo_appDeveloper form-control" id="intro"
											placeholder="">
									</td>
								</tr>
								<tr>
									<td>
										<strong>版本：</strong>
									</td>
									<td>
										<input type="text" class="form-control appInfo_appVersion" id="intro"
											placeholder="">
									</td>
								</tr>
								<tr>
									<td>
										<strong>文件大小：</strong>
									</td>
									<td>
										<input type="text" class="form-control appInfo_appSize" id="intro"
											placeholder="">
										M
									</td>
								</tr>
								<tr>
									<td>
										<strong>系统支持：</strong>
									</td>
									<td>
										<input type="text" class="form-control appInfo_appForSystem" id="intro"
											placeholder="" >
									</td>
								</tr>
								<tr>
									<td>
										<strong>评星：</strong>
									</td>
									<td class="appInfo_appStar">
										
									</td>
								</tr>
								<tr>
									<td>
										<strong>应用logo：</strong>
									</td>
									<td class="first_logo">
									<div class="form-group first_icon last_icon_div">
										<label for="exampleInputFile">
												上传图片：
											</label>
										 <div class="e_tForm_uploadBtn">
											<form name="imageForm1" enctype="multipart/form-data" action="<%=ctxPath%>/imageUpload.tv/0" method="post">
											<input value="上传" type="button">
											<input class="fileBtn" type="file" name="fileBox" id="exampleInputFile" onchange="uploadVideoFile(this,0,0);">
											</form>
										 </div>
										</div>
										<div class="alert alert-warning alert-dismissible pic-preview first_icon_div"
											role="alert" style="display:none">
												<span class="sr-only">Close</span>
											</button>
											<img class="appInfo_appiconimg" src="" alt="...">
										</div>
										
									</td>
								</tr>
								<tr>
									<td>
										<strong>界面截图：</strong>
									</td>
									<td class="jiemian_icon_more">
										<div class="form-group jmjt_div">
											<label for="exampleInputFile">
												上传图片：
											</label>
                                             <div class="e_tForm_uploadBtn">											
										      <form name="imageForm2" enctype="multipart/form-data" action="<%=ctxPath%>/imageUpload.tv/0" method="post">
											    <input value="上传" type="button">
											    <input class="fileBtn" name="fileBox" type="file" id="exampleInputFile" onchange="uploadVideoFile(this,1,0);">
											  </form>
									    	</div>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<strong>推广大图：</strong>
									</td>
									<td class="last_icon tgdt_icon">
										<div class="form-group tgdt_no_insert">
											<label for="exampleInputFile">
												上传图片：

											</label>
										   	<div class="e_tForm_uploadBtn">	
											    <form name="imageForm3" enctype="multipart/form-data" action="<%=ctxPath%>/imageUpload.tv/0" method="post">
											      <input value="上传" type="button">
											     <input name="fileBox" class="fileBtn" type="file" id="exampleInputFile" onchange="uploadVideoFile(this,2,0);">
											   </form>
										   </div>
										</div>
										<br/>
										<div class="alert alert-warning alert-dismissible pic-preview  tgdt_icon_img"
											role="alert" style="display:none">
												<span class="sr-only">Close</span>
											</button>
											<img src="image/pic_sample01.jpg" alt="...">
										</div>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;
									</td>
									<td>
										<label class="sr-only" for="exampleInputPassword2"></label>
										<select class="form-control smallOrBigPicChoose">
											<option value="1">
												发现-大图
											</option>
											<option value="0">
												发现-小图
											</option>
										</select>
										<p class="small text-red">
											注：本次推广设置将覆盖上次的操作
										</p>
									</td>
								</tr>
							</table>

					</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary cancelhiden" data-dismiss="modal">
							取消
						</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal" id="btnOkFormApp">
							确定
						</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal3新增 -->
		<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close closehide" data-dismiss="modal">
							<span class="sr-only">关闭</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							应用新增
						</h4>
					</div>
					<div class="modal-body">
					<div class="form-inline" role="form">
							<table class="table">
								<tr>
									<td>
										<strong>描述：</strong>
									</td>
									  <td>
										<input type="text" class="form-control insertappInfo_appDesc"  id="intro"
											placeholder="">
									</td>
								</tr>
								<tr>
									<td>
										<strong>展示名：</strong>
									</td>
									<td>
										<input type="text" class="form-control insertappInfo_appName"  id="intro"
											placeholder="">
									</td>
								</tr>
								<tr>
									<td>
										<strong>开发者：</strong>
									</td>
									<td>
										<input type="text" class="insertappInfo_appDeveloper form-control" id="intro"
											placeholder="">
									</td>
								</tr>
								<tr>
									<td>
										<strong>版本：</strong>
									</td>
									<td>
										<input type="text" class="form-control insertappInfo_appVersion" id="intro"
											placeholder="">
									</td>
								</tr>
								<tr>
								<td>
									<strong>类别：</strong>
								</td>
								<td>
									<div class="insertcheckAppType" data-toggle="buttons">
										<label class="btn btn-default">
											<input type="radio" name="radio_options" id=""
												autocomplete="off" value="1">
												游戏
										</label>
										<label class="btn btn-default">
											<input type="radio" name="radio_options" id=""
												autocomplete="off" value="2">
												社交
										</label>
										<label class="btn btn-default">
											<input type="radio" name="radio_options" id=""
												autocomplete="off" value="3">
												工具
										</label>
										<label class="btn btn-default">
											<input type="radio" name="radio_options" id=""
												autocomplete="off" value="4">
												娱乐
										</label>
										<label class="btn btn-default">
											<input type="radio" name="radio_options" id=""
												autocomplete="off" value="5">
												其他
										</label>
									</div>
								</td>
							</tr>
								<tr>
									<td>
										<strong>文件大小：</strong>
									</td>
									<td>
										<input type="text" class="form-control insertappInfo_appSize" id="intro"
											placeholder="">
										M
									</td>
								</tr>
								<tr>
									<td>
										<strong>系统支持：</strong>
									</td>
									<td>
										<input type="text" class="form-control insertappInfo_appForSystem" id="intro"
											placeholder="" >
									</td>
								</tr>
								<tr>
									<td>
										<strong>评星：</strong>
									</td>
									<td class="insertappInfo_appStar">
										<a href="#" class="start1" ><img class=""  src="resources/images/star2.png"></a>
										<a href="#" class="start2" ><img class=""  src="resources/images/star2.png"></a>
										<a href="#" class="start3" ><img class=""  src="resources/images/star2.png"></a>
										<a href="#" class="start4" ><img class=""  src="resources/images/star2.png"></a>
										<a href="#" class="start5" ><img class=""  src="resources/images/star2.png"></a>
									</td>
								</tr>
								<tr>
									<td>
										<strong>应用logo：</strong>
									</td>
									<td class="first_logo_insert">
									<div class="form-group first_icon last_icon_div">
											<label for="exampleInputFile">
												上传图片：

											</label>
										   <div class="e_tForm_uploadBtn">	
												<form name="imageForm1" enctype="multipart/form-data" action="<%=ctxPath%>/imageUpload.tv/0" method="post">
												 <input value="上传" type="button">
												<input type="file" class="fileBtn" name="fileBox" id="exampleInputFile" onchange="uploadVideoFile(this,0,1);">
												</form>
										  </div>
										</div>
										<div class="alert alert-warning alert-dismissible pic-preview first_icon_div_insert"
											role="alert" style="display:none">
												<span class="sr-only">Close</span>
											</button>
											<img class="insertappInfo_appiconimg" src="" alt="...">
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<strong>界面截图：</strong>
									</td>
									<td class="jiemian_icon_more_insert">
										<div class="form-group jmjt_div_insert">
											<label for="exampleInputFile">
												上传图片：

											</label>
											 <div class="e_tForm_uploadBtn">	
										   <form name="imageForm2" enctype="multipart/form-data" action="<%=ctxPath%>/imageUpload.tv/0" method="post">
											 <input value="上传" type="button">
											<input name="fileBox" class="fileBtn" type="file" id="exampleInputFile" onchange="uploadVideoFile(this,1,1);">
											</form>
										</div>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<strong>推广大图：</strong>
									</td>
									<td class=" tgdt_icon_insert">
										<div class="form-group tgdt_insert_insert">
											<label for="exampleInputFile">
												上传图片：

											</label>
											 <div class="e_tForm_uploadBtn">
											  <form name="imageForm3" enctype="multipart/form-data" action="<%=ctxPath%>/imageUpload.tv/0" method="post">
											  <input value="上传" type="button">
											   <input name="fileBox" class="fileBtn" type="file" id="exampleInputFile" onchange="uploadVideoFile(this,2,1);">
											</form>
									    	</div>
										</div>
										<br/>
										<div class="alert alert-warning alert-dismissible pic-preview  tgdt_icon_img_insert"
											role="alert" style="display:none">
												<span class="sr-only">Close</span>
											</button>
											<img src="image/pic_sample01.jpg" alt="...">
										</div>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;
									</td>
									<td>
										<label class="sr-only" for="exampleInputPassword2"></label>
										<select class="form-control insertsmallOrBigPicChoose">
											<option value="1">
												发现-大图
											</option>
											<option value="0">
												发现-小图
											</option>
										</select>
										<p class="small text-red">
											注：本次推广设置将覆盖上次的操作
										</p>
									</td>
								</tr>
							</table>

					</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary cancelhiden" data-dismiss="modal">
							取消
						</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal" id="insertbtnOkFormApp">
							确定
						</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Bootstrap core JavaScript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		
		<script type="text/javascript">
    	var page=0;
       var size=countPerQuery;
       var appDesc='';
       var appName='';
       var appStatus='';
       var picName='';
    //是否第一次加载,是否是条件查询

       var firstornull='';
   	//是否是google浏览器
   	var isChrome = window.navigator.userAgent.indexOf("Chrome") !== -1 ;
   $(document).ready(function(){
    	  getAppManage(page,size,firstornull);

          $("body").on("click","#video_next_ #hd a",function(){
             firstornull=$(this).parent().attr("idnum");
             getAppManage($(this).text()-1,size,firstornull);
             $(this).parent().addClass("active");
            });

          $("#videoSearchSubmit").bind("click",function(){
              getAppManage(page,size,'query');
             });

          $("#btnOkFormApp").bind("click",function(){
              var flag=false;
     	     var appId=$(".appInfo_appId").html();
     	     var appName=$(".appInfo_appName").val();
     	     var appDesc=$(".appInfo_appDesc").val();
     	     var appDeveloper=$(".appInfo_appDeveloper").val();
     	     var appVersion=$(".appInfo_appVersion").val();
     	     var appSize=$(".appInfo_appSize").val();
     	     appSize=appSize.replace(/\ /g,"");
     	     var appForSystem=$(".appInfo_appForSystem").val();
     		var  str_logo="'"+$(".appInfo_appiconimg").attr("src")+"'";
     		var icon='';
     		if(!str_logo=='undefined'||str_logo.indexOf('wxyx_')!=-1){
     			icon=str_logo.substring(str_logo.indexOf('wxyx_')).replace("'","")+",";
         	}else{
         		icon='';
             }
        
     		var picName_more=document.getElementsByName("picName_more");
     		
     		var picStringList='';
     	
     		
     		for (i=0;i<picName_more.length;i++)
     		{

     		   picStringList+=picName_more[i].value+",";
     		}
     		var demopic=document.getElementsByName("demopic");
     		var demopicList='';
     		for (i=0;i<demopic.length;i++)
     		{

     			demopicList+=demopic[i].value+",";
     		}
     		var  tuiguangbig="'"+$(".appInfo_appiconimg_last").attr("src")+"'";
     		var appTopPic='';
     		if(!tuiguangbig=='undefined'||tuiguangbig.indexOf('wxyx_')!=-1){
     			appTopPic=tuiguangbig.substring(tuiguangbig.indexOf('wxyx_')).replace("'","")+",";
         	}else{
         		appTopPic='';
             }
     		var smallOrBigPic=$(".smallOrBigPicChoose").val();
     		var jsonData ={"appId":appId,"appName":appName,"appDesc":appDesc,"appDeveloper":appDeveloper,"appVersion":appVersion,"appSize":appSize,"appForSystem":appForSystem,"icon":icon,"picStringList":picStringList,"appTopPic":appTopPic,"isTopArea":smallOrBigPic,"demopicList":demopicList};
     		jQuery.ajax
     		({
     			type: "post",
     			url: '<%=ctxPath%>'+"/auditList/updateAppInfo",
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
       	$(".closehide,.cancelhiden").on('click',function(){
      		$("#myModal2").hide();
      		$("#myModal3").hide();
    })
         
 });
    	
    	
 function getAppManage(page,size,firstornull){
	    var url_='<%=ctxPath%>';
	    var contentSearchValue=$("#exampleInputPassword2").val();
	    var videoByNameSearch=$("#videoByNameSearch").val();
	    //是否是条件查询

	    if(firstornull!=''&&firstornull!='next'&&!isNumber(firstornull)){
		    if(videoByNameSearch==1){
		    //展示名

		      appName=contentSearchValue;
		       appDesc='';
		    }else{
		    //文件名

		       appDesc=contentSearchValue;
		       appName='';
		    }
	       appStatus=$("#videoByStatusSearch").val();
	    }
	    var jsonData ={"page":page,"size":size,"appDesc":appDesc,"appName":appName,"appStatus":appStatus};
	    	jQuery.ajax
	({
		type: "post",
		url: url_+"/appList/queryAppInfoList",
		dataType: "json",
		data : jsonData,
		cache: false,
		success: function(data){
		if(data.CODE=="TRUE"){
		var applineList=data.applineList;
		var totalPage=data.totalPage;
		var page=data.page;
		var appcount=data.appcount;
		var contentHtml='';
		var pageHtml='';
		 if(applineList.length>0){
		   for(var i=0;i<applineList.length;i++){
		      contentHtml+='<tr><td><input name="checkboxManageName" type="checkbox" value="'+applineList[i].appId+'" class="checkboxClass" id="checkboxId'+applineList[i].appId+'"/></td>';
		      contentHtml+='<td>'+applineList[i].appId+'</td><td>'+applineList[i].appDesc+'</td><td>'+applineList[i].appName+'</td><td><label class="text-danger">';
               if(applineList[i].appStatus==0){
			       contentHtml+='新增</label></td>';
                 }
               if(applineList[i].appStatus==1){
			       contentHtml+='上线</label></td>';
                 }
               if(applineList[i].appStatus==2){
			       contentHtml+='下线</label></td>';
                 }
			      contentHtml+='<td><div class="btn-group"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal2" onclick="findAppInfoByAppId('+applineList[i].appId+')"><span class="glyphicon glyphicon-pencil">&nbsp;</span>配置</button></div></td>';
		   }
		     $("#appContentId").html(contentHtml);
		 }else{
	          alert("没有记录");
		       $("#appContentPageId").html();
		       $("#appContentId").children().remove();
		       $("#video_next_").children("#hd").remove();
		       $("#video_next_").html("");
		  }
		 if(totalPage>0){
			 var content_li='<li class=""><a href="javascript:void(0);" onclick="setPage('+parseInt(page-1)+','+totalPage+')">&laquo;</a></li>';
			   for(var i=1;i<totalPage+1;i++){
			   content_li+=' <li class="page_li" idnum="'+i+'" id="hd"><a href="javascript:void(0);">'+i+'</a></li>';
			   }
			   content_li+='<li class=""><a href="javascript:void(0);" onclick="setPage('+parseInt(page+1)+','+totalPage+')">&raquo;</a></li>';
		       pageHtml+='<div class="col-xs-4"><h6>当前为第<span class="currPage_">'+parseInt(page+1)+'</span>页，共'+totalPage+'页， 共'+appcount+'个视频</h6></div>';
		  }
		 $("#appContentPageId").html(pageHtml);
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
		  getAppManage(page,size,'next');
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
    	
	
function findAppInfoByAppId(appId){
	limitPicTimes=0;
	var url_='<%=ctxPath%>';
	$(".first_icon_div").remove();
	$(".jiemian_icon_div").remove();
	$(".tgdt_icon_img").remove();
	  var jsonData ={"appId":appId};
	jQuery.ajax
	({
		type: "post",
		url: url_+"/appList/queryAppInfoByAppId",
		dataType: "json",
		data : jsonData,
		cache: false,
		success: function(data){
	    if(data.CODE=="TRUE"){
	    	var appInfo=data.appInfo;
		    $(".appInfo_appId").html(appInfo.appId);
		    $(".appInfo_appDesc").attr("placeholder",appInfo.appDesc);
		    $(".appInfo_appDesc").val(appInfo.appDesc);
		    $(".appInfo_appName").attr("placeholder",appInfo.appName);
		    $(".appInfo_appName").val(appInfo.appName);
		    $(".appInfo_appDeveloper").attr("placeholder",appInfo.appDeveloper);
		    $(".appInfo_appDeveloper").val(appInfo.appDeveloper);
		    $(".appInfo_appVersion").attr("placeholder",appInfo.appVersion);
		    $(".appInfo_appVersion").val(appInfo.appVersion);
		    $(".appInfo_appSize").attr("placeholder",appInfo.appSize);
		    $(".appInfo_appSize").val(appInfo.appSize);
		    $(".appInfo_appDeveloper").attr("placeholder",appInfo.appDeveloper);
		    $(".appInfo_appDeveloper").val(appInfo.appDeveloper);
		    $(".appInfo_appForSystem").attr("placeholder",appInfo.appForSystem);
		    $(".appInfo_appForSystem").val(appInfo.appForSystem);
		    if(appInfo.appStarLevel=='1'){
			    $(".appInfo_appStar").html('<img src="'+url_+'/resources/images/star.png"><img src="'+url_+'/resources/images/star2.png"><img src="'+url_+'/resources/images/star2.png"><img src="'+url_+'/resources/images/star2.png"><img src="'+url_+'/resources/images/star2.png">');
			 }else if(appInfo.appStarLevel=='2'){
				 $(".appInfo_appStar").html('<img src="'+url_+'/resources/images/star.png"><img src="'+url_+'/resources/images/star.png"><img src="'+url_+'/resources/images/star2.png"><img src="'+url_+'/resources/images/star2.png"><img src="'+url_+'/resources/images/star2.png">');
			 }else if(appInfo.appStarLevel=='3'){
				 $(".appInfo_appStar").html('<img src="'+url_+'/resources/images/star.png"><img src="'+url_+'/resources/images/star.png"><img src="'+url_+'/resources/images/star.png"><img src="'+url_+'/resources/images/star2.png"><img src="'+url_+'/resources/images/star2.png">');
			 }else if(appInfo.appStarLevel=='4'){
				 $(".appInfo_appStar").html('<img src="'+url_+'/resources/images/star.png"><img src="'+url_+'/resources/images/star.png"><img src="'+url_+'/resources/images/star.png"><img src="'+url_+'/resources/images/star.png"><img src="'+url_+'/resources/images/star2.png">');
			 }else{
				 $(".appInfo_appStar").html('<img src="'+url_+'/resources/images/star.png"><img src="'+url_+'/resources/images/star.png"><img src="'+url_+'/resources/images/star.png"><img src="'+url_+'/resources/images/star.png"><img src="'+url_+'/resources/images/star.png">');
				 }
			 var pic1='',pic2='',pic3='',pic4='';
			 if(isChrome){
			 if(appInfo.icon!=null&&appInfo.icon.length>=10)
		    $(".first_logo").prepend('<div  class="alert alert-warning alert-dismissible first_icon_div" role="alert"><span class="sr-only">Close</span></button><img class="appInfo_appiconimg" src="'+url_+'/find/findAppIcon?appId='+appInfo.appId+'" alt="..." width="50px"></div>');
			 if(appInfo.appDemoPic1!=null&&appInfo.appDemoPic1.length>=10){
			    pic1='<div demopic1="1" style="float:center" class="alert alert-warning alert-dismissible pic-preview jiemian_icon_div" role="alert"><button onclick="jiemian_icon_div_close()" type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><img width="100%" src="'+url_+'/find/findAppPic1?appId='+appInfo.appId+'" alt="..."><input type="hidden" name="demopic" value="1"/></div>';
			    limitPicTimes++;
				 }
			if(appInfo.appDemoPic2!=null&&appInfo.appDemoPic2.length>=10){
		     pic2='<div demopic2="2" style="float:center" class="alert alert-warning alert-dismissible pic-preview jiemian_icon_div" role="alert"><button onclick="jiemian_icon_div_close()" type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><img width="100%" src="'+url_+'/find/findAppPic2?appId='+appInfo.appId+'" alt="..."><input type="hidden" name="demopic" value="2"/></div>';
			    limitPicTimes++;
				}
			 if(appInfo.appDemoPic3!=null&&appInfo.appDemoPic3.length>=10){
		     pic3='<div demopic3="3" style="float:center" class="alert alert-warning alert-dismissible pic-preview jiemian_icon_div" role="alert"><button onclick="jiemian_icon_div_close()" type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><img width="100%" src="'+url_+'/find/findAppPic3?appId='+appInfo.appId+'" alt="..."><input type="hidden" name="demopic" value="3"/></div>';
			    limitPicTimes++;
				 }
			 if(appInfo.appDemoPic4!=null&&appInfo.appDemoPic4.length>=10){
		        pic4='<div demopic4="4"  style="float:center" class="alert alert-warning alert-dismissible pic-preview jiemian_icon_div" role="alert"><button onclick="jiemian_icon_div_close()" type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><img width="100%" src="'+url_+'/find/findAppPic4?appId='+appInfo.appId+'" alt="..."><input type="hidden" name="demopic" value="4"/></div>';
			    limitPicTimes++;
				 }
		       $(".jmjt_div").after(pic1+pic2+pic3+pic4);
		       if(appInfo.appTopPic!=null&&appInfo.appTopPic.length>=10)
		       $(".tgdt_no_insert").after('<div style="float:center" class="alert alert-warning alert-dismissible pic-preview tgdt_icon_img" role="alert"><span class="sr-only">Close</span></button><img class="appInfo_appiconimg_last" src="'+url_+'/find/findAppTopPic?appId='+appInfo.appId+'" alt="..." width="100%"></div>');
				 }else{
			 if(appInfo.icon!=null&&appInfo.icon.length>=10)
		    $(".first_logo").prepend('<div class="alert alert-warning alert-dismissible first_icon_div" role="alert"><span class="sr-only">Close</span></button><img class="appInfo_appiconimg" src="'+url_+'/find/findAppIcon?appId='+appInfo.appId+'" alt="..." width="50px"></div>');
			 if(appInfo.appDemoPic1!=null&&appInfo.appDemoPic1.length>=10){
			    pic1='<div demopic1="1"  style="float:unset" class="alert alert-warning alert-dismissible pic-preview jiemian_icon_div" role="alert"><button onclick="jiemian_icon_div_close()" type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><img width="100%" src="'+url_+'/find/findAppPic1?appId='+appInfo.appId+'" alt="..."><input type="hidden" name="demopic" value="1"/></div>';
			    limitPicTimes++;
				 }
			if(appInfo.appDemoPic2!=null&&appInfo.appDemoPic2.length>=10){
		     pic2='<div demopic2="2"  style="float:unset" class="alert alert-warning alert-dismissible pic-preview jiemian_icon_div" role="alert"><button onclick="jiemian_icon_div_close()" type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><img width="100%" src="'+url_+'/find/findAppPic2?appId='+appInfo.appId+'" alt="..."><input type="hidden" name="demopic" value="2"/></div>';
			    limitPicTimes++;
				}
			 if(appInfo.appDemoPic3!=null&&appInfo.appDemoPic3.length>=10){
		     pic3='<div demopic3="3"  style="float:unset" class="alert alert-warning alert-dismissible pic-preview jiemian_icon_div" role="alert"><button onclick="jiemian_icon_div_close()" type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><img width="100%" src="'+url_+'/find/findAppPic3?appId='+appInfo.appId+'" alt="..."><input type="hidden" name="demopic" value="3"/></div>';
			    limitPicTimes++;
				 }
			 if(appInfo.appDemoPic4!=null&&appInfo.appDemoPic4.length>=10){
		        pic4='<div demopic4="4"  style="float:unset" class="alert alert-warning alert-dismissible pic-preview jiemian_icon_div" role="alert"><button onclick="jiemian_icon_div_close()" type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><img width="100%" src="'+url_+'/find/findAppPic4?appId='+appInfo.appId+'" alt="..."><input type="hidden" name="demopic" value="4"/></div>';
			    limitPicTimes++;
				 }
		       $(".jmjt_div").after(pic1+pic2+pic3+pic4);
		       if(appInfo.appTopPic!=null&&appInfo.appTopPic.length>=10)
		       $(".tgdt_no_insert").after('<div style="float:unset" class="alert alert-warning alert-dismissible pic-preview tgdt_icon_img" role="alert"><span class="sr-only">Close</span></button><img class="appInfo_appiconimg_last" src="'+url_+'/find/findAppTopPic?appId='+appInfo.appId+'" alt="..." width="100%"></div>');
					 }
		  }else{
			  alert("对不起网络异常！");
			  }
		},
	error: function(){
			alert("对不起网络异常！");
	}
	});	
}
var limitPicTimes=0;
var limitPicTimesinsert=0;
function uploadVideoFile(obj,logo,index){
	var form1=$(obj).parent();
	if(logo==0){
		if(index==0){
	      $(".first_logo .first_icon_div").remove();
	      $(".first_logo").prepend('<div class="alert alert-warning alert-dismissible first_icon_div" role="alert"><span class="sr-only">Close</span></button><img class="appInfo_appiconimg" src="" alt="..." width="50px"></div>');
		}else{
	      $(".first_logo_insert .first_icon_div_insert").remove();
	      $(".first_logo_insert").prepend('<div class="alert alert-warning alert-dismissible first_icon_div_insert" role="alert"><span class="sr-only">Close</span></button><img class="insertappInfo_appiconimg" src="" alt="..." width="50px"></div>');
			}
		form1.ajaxSubmit({
				type : 'post',
		   	url : "<%=ctxPath%>/imageUpload.tv/0",
		   	success:function (result, status) {
				var data = jQuery.parseJSON(result);
				if (data.CODE == "FALSE") {
					art.alert(data.msg);
				}else if(data.CODE == "TRUE"){
					//当前编辑对象
					if(index==0){
				      $(".appInfo_appiconimg").attr("src",imageAccessPath+data.fileName);
					}else{
				      $(".insertappInfo_appiconimg").attr("src",imageAccessPath+data.fileName);
						}
				}else{
					art.alert(window.catch_title);
				}
			}
		});
		return false;//为了防止刷新	
	}else if(logo==1){
		if(index==0){
		 if(limitPicTimes<4){
			form1.ajaxSubmit({
				type : 'post',
		   	url : "<%=ctxPath%>/imageUpload.tv/0",
		   	success:function (result, status) {
				var data = jQuery.parseJSON(result);
				if (data.CODE == "FALSE") {
					art.alert(data.msg);
				}else if(data.CODE == "TRUE"){
					//当前编辑对象
					if(isChrome){
					   var viewhtml='<div  style="float:center" class="alert alert-warning alert-dismissible pic-preview jiemian_icon_div" role="alert"><button onclick="jiemian_icon_div_close()" type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><img width="100%" src="'+imageAccessPath+data.fileName+'" alt="..."><input type="hidden" name="picName_more" value="'+data.fileName+'"/></div>';
						}else{
					   var viewhtml='<div  style="float:unset" class="alert alert-warning alert-dismissible pic-preview jiemian_icon_div" role="alert"><button onclick="jiemian_icon_div_close()" type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><img width="100%" src="'+imageAccessPath+data.fileName+'" alt="..."><input type="hidden" name="picName_more" value="'+data.fileName+'"/></div>';
							}
				       $(".jmjt_div").after(viewhtml);
				     limitPicTimes++;
				}else{
					art.alert(window.catch_title);
				}
			}
		});
		}else{
			alert("最多只能传4张图片");
			}
		}else{
			 if(limitPicTimesinsert<4){
					form1.ajaxSubmit({
						type : 'post',
				   	url : "<%=ctxPath%>/imageUpload.tv/0",
				   	success:function (result, status) {
						var data = jQuery.parseJSON(result);
						if (data.CODE == "FALSE") {
							art.alert(data.msg);
						}else if(data.CODE == "TRUE"){
							//当前编辑对象
							if(isChrome){
							   var viewhtml='<div style="float:center" class="alert alert-warning alert-dismissible pic-preview jiemian_icon_div_insert" role="alert"><button onclick="jiemian_icon_div_close_insert()" type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><img width="100%" src="'+imageAccessPath+data.fileName+'" alt="..."><input type="hidden" name="picName_more1" value="'+data.fileName+'"/></div>';
								}else{
							   var viewhtml='<div style="float:unset" class="alert alert-warning alert-dismissible pic-preview jiemian_icon_div_insert" role="alert"><button onclick="jiemian_icon_div_close_insert()" type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><img width="100%" src="'+imageAccessPath+data.fileName+'" alt="..."><input type="hidden" name="picName_more1" value="'+data.fileName+'"/></div>';
									}
						       $(".jmjt_div_insert").after(viewhtml);
						       limitPicTimesinsert++;
							
						}else{
							art.alert(window.catch_title);
						}
					}
				});
				}else{
				alert("最多只能传4张图片");
					}
			}
	}else{
		if(index==0){
		  $(".tgdt_icon .tgdt_icon_img").remove();
		  if(isChrome){
		  $(".tgdt_no_insert").after('<div style="float:center" class="alert alert-warning alert-dismissible pic-preview tgdt_icon_img" role="alert"><span class="sr-only">Close</span></button><img class="appInfo_appiconimg_last" src="" alt="..." width="100%"></div>');
			  }else{
		  $(".tgdt_no_insert").after('<div style="float:unset" class="alert alert-warning alert-dismissible pic-preview tgdt_icon_img" role="alert"><span class="sr-only">Close</span></button><img class="appInfo_appiconimg_last" src="" alt="..." width="100%"></div>');
				  }
	    }else{
		  $(".tgdt_icon_insert .tgdt_icon_img_insert").remove();
		  if(isChrome){
		  $(".tgdt_insert_insert").after('<div style="float:center" class="alert alert-warning alert-dismissible pic-preview tgdt_icon_img_insert" role="alert"><span class="sr-only">Close</span></button><img class="appInfo_appiconimg_last_insert" src="" alt="..." width="100%"></div>');
			  }else{
		  $(".tgdt_insert_insert").after('<div style="float:unset" class="alert alert-warning alert-dismissible pic-preview tgdt_icon_img_insert" role="alert"><span class="sr-only">Close</span></button><img class="appInfo_appiconimg_last_insert" src="" alt="..." width="100%"></div>');
			  }
		    }
			form1.ajaxSubmit({
					type : 'post',
			   	url : "<%=ctxPath%>/imageUpload.tv/0",
			   	success:function (result, status) {
					var data = jQuery.parseJSON(result);
					if (data.CODE == "FALSE") {
						art.alert(data.msg);
					}else if(data.CODE == "TRUE"){
						//当前编辑对象
						if(index==0){
					      $(".appInfo_appiconimg_last").attr("src",imageAccessPath+data.fileName);
							}else{
					      $(".appInfo_appiconimg_last_insert").attr("src",imageAccessPath+data.fileName);
								}
					}else{
						art.alert(window.catch_title);
					}
				}
			});
			return false;//为了防止刷新	
		}
	
}  	
    	
function jiemian_icon_div_close(){
  	limitPicTimes--;
  	if(limitPicTimes<=0){
  		limitPicTimes==0;
  	    $(".jiemian_icon_div1").show();
      }

}
function jiemian_icon_div_close_insert(){
	limitPicTimesinsert--;
  	if(limitPicTimesinsert<=0){
  		limitPicTimesinsert==0;
  	    $(".jiemian_icon_div1").show();
      }

}
    	
$("#insertAppInfo").bind("click" ,function(){
	$(".appInfo_appiconimg").attr("src","");
	$(".first_icon_div_insert").remove();
	$(".jiemian_icon_div_insert").remove();
	$(".tgdt_icon_img_insert").remove();
  })   	
    	
  	$("#insertbtnOkFormApp").bind("click",function(){
  	  	var flag=false;
		  var appDesc=$(".insertappInfo_appDesc").val();
		  appDesc=appDesc.replace(/\ /g,"");
		  if(appDesc.length<=0 ){
              alert("请输入描述信息 ！");
              $(".insertappInfo_appDesc").focus();
              return flag;
		   }
		  var appName=$(".insertappInfo_appName").val();
		  appName=appName.replace(/\ /g,"");
		  if(appName.length<=0 ){
              alert("请输入展示名称 ！");
              $(".insertappInfo_appName").focus();
              return flag;
		   }
		  var appDeveloper=$(".insertappInfo_appDeveloper").val();
		  appDeveloper=appDeveloper.replace(/\ /g,"");
		  if(appDeveloper.length<=0 ){
              alert("请输入开发者名称 ！");
              $(".insertappInfo_appDeveloper").focus();
              return flag;
		   }
		  var appVersion=$(".insertappInfo_appVersion").val();
		  appVersion=appVersion.replace(/\ /g,"");
		  if(appVersion.length<=0 ){
              alert("请输入版本名称 ！");
              $(".insertappInfo_appVersion").focus();
              return flag;
		   }
			var insertcheckAppType=$('input[name="radio_options"]:checked').val();
			   if(typeof insertcheckAppType=='undefined'){
			    alert("请选择应用类别！");
			          return flag;
				   }
		  var appSize=$(".insertappInfo_appSize").val();
		  appSize=appSize.replace(/\ /g,"");
			if(!isNumber(appSize)){
				alert("文件大小，请输入数字！");
			     $(".insertappInfo_appSize").focus();
				return flag;
			}
		  var appForSystem=$(".insertappInfo_appForSystem").val();
		  var star_i=0;
		  $(".insertappInfo_appStar a").each(function(){
            if($(this).children("img").attr("class")=='ok'){
            	star_i++;
                }
            });
          var appStar=star_i;
          var  str_logo="'"+$(".insertappInfo_appiconimg").attr("src")+"'";
   		var icon='';
   		if(!str_logo=='undefined'||str_logo.indexOf('wxyx_')!=-1){
   			icon=str_logo.substring(str_logo.indexOf('wxyx_')).replace("'","")+",";
       	}else{
       		icon='';
          }
   		var picName_more=document.getElementsByName("picName_more1");
 		var picStringList='';
 		for (i=0;i<picName_more.length;i++)
 		{

 		   picStringList+=picName_more[i].value+",";
 		}
 		var  tuiguangbig="'"+$(".appInfo_appiconimg_last_insert").attr("src")+"'";
 		var appTopPic='';
 		if(!tuiguangbig=='undefined'||tuiguangbig.indexOf('wxyx_')!=-1){
 			appTopPic=tuiguangbig.substring(tuiguangbig.indexOf('wxyx_')).replace("'","")+",";
     	}else{
     		appTopPic='';
         }
 		var smallOrBigPic=$(".insertsmallOrBigPicChoose").val();
		  var url_='<%=ctxPath%>';
		  var jsonData ={"appDesc":appDesc,
				  "appName":appName,
				  "appDeveloper":appDeveloper,
				  "appVersion":appVersion,
				  "appSize":appSize,
				  "appType":insertcheckAppType,
				  "appForSystem":appForSystem,
				  "appStar":appStar,
				  "icon":icon,
				  "picStringList":picStringList,
				  "appTopPic":appTopPic,
				  "isTopArea":smallOrBigPic

				  };
		jQuery.ajax
		({
			type: "post",
			url: url_+"/auditList/insertAppInfo",
			dataType: "json",
			data : jsonData,
			cache: false,
			success: function(data){
		    if(data.CODE=="TRUE"){
		    	alert("添加成功！");
		    	$("#myModal3").hide();
		    	$(document).ready(function(){
   		     	  getAppManage(page,size,firstornull);
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
    	
    	$(".insertappInfo_appStar a").bind("click",function(e){
              if($(this).attr("class")=='start1'){
                $(this).children().attr("src","resources/images/star.png");
                $(this).children().attr("class","ok");
                $(this).attr("class","start_1");
               }else if($(this).attr("class")=='start_1'){
                $(this).children().attr("src","resources/images/star2.png");
                $(this).children().attr("class","");
                $(this).attr("class","start1");
               }
              if($(this).attr("class")=='start2'){
                $(this).children().attr("src","resources/images/star.png");
                $(this).children().attr("class","ok");
                $(this).attr("class","start_2");
               }else if($(this).attr("class")=='start_2'){
                $(this).children().attr("src","resources/images/star2.png");
                $(this).children().attr("class","");
                $(this).attr("class","start2");
               }
              if($(this).attr("class")=='start3'){
                $(this).children().attr("src","resources/images/star.png");
                $(this).children().attr("class","ok");
                $(this).attr("class","start_3");
               }else if($(this).attr("class")=='start_3'){
                $(this).children().attr("src","resources/images/star2.png");
                $(this).children().attr("class","");
                $(this).attr("class","start3");
               }
              if($(this).attr("class")=='start4'){
                $(this).children().attr("src","resources/images/star.png");
                $(this).children().attr("class","ok");
                $(this).attr("class","start_4");
               }else if($(this).attr("class")=='start_4'){
                $(this).children().attr("src","resources/images/star2.png");
                $(this).children().attr("class","");
                $(this).attr("class","start4");
               }
              if($(this).attr("class")=='start5'){
                $(this).children().attr("src","resources/images/star.png");
                $(this).children().attr("class","ok");
                $(this).attr("class","start_5");
               }else if($(this).attr("class")=='start_5'){
                $(this).children().attr("src","resources/images/star2.png");
                $(this).children().attr("class","");
                $(this).attr("class","start5");
               }
           
        	})
    	</script>
	</body>
</html>
