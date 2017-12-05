<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<%@ include file="../common/include.jsp"%>

    <title>北京移动wifi分发管理平台</title>
    
<script>
//抢桌参数
var qiangzhuoTimes =5;//创建桌审核查询的条数
var currentPage=1;//记忆当前页数
//上传照片审核参数
var uploadImageTimes =5;//上传照片查询的条数
var uploadImagecurrentPage=1;//上传照片记忆当前页数

$(document).ready(function(){
	getCreateZhuo(1);
	getCreateZhuo(2);
	$("#qiangzhuoquanxuanId").click(function(){
		  if(document.getElementById("qiangzhuoquanxuanId").checked){
		  	selectAllNullorReserve("qiangzhuocheckboxId","全选");
		  }else{
		  	selectAllNullorReserve("qiangzhuocheckboxId","全不选");
		  	
		  }
	  });
	$("#uploadAllId").click(function(){
		  if(document.getElementById("uploadAllId").checked){
		  	selectAllNullorReserve("imageCheckboxName","全选");
		  }else{
		  	selectAllNullorReserve("imageCheckboxName","全不选");
		  	
		  }
	  });
})
function getCreateZhuo(flag){
$("body").showLoading();
var begin =0;
var end=5;
var mobileOrNickname="";
if(flag=="1"){
	mobileOrNickname=$("#uploadImageInputPassword2").val();
	begin=uploadImageTimes*uploadImagecurrentPage-uploadImageTimes;
}else if(flag=="2"){
 	mobileOrNickname=$("#createZhuoPassword2").val();
	begin=qiangzhuoTimes*currentPage-qiangzhuoTimes;
}
var jsonData = {mobileOrNickname:mobileOrNickname,begin:begin,end:end,flag:flag};
jQuery.ajax
	({
		type: "post",
		url:ctxPaths + "/menu/qiangdanAuditList",
		dataType: "json",
		data : jsonData,
		cache: false,
		success: function(data){
		
                if(data.CODE="TRUE"){
                	var qiangdanList = data.rows;
                	var total = data.total;
                	var contentHtml="";
                	var imageContentHtml="";
                	var rowHtml="";
                	 var tempCurrentPage=0;   
                	 var pageFlag=4;
                	if(qiangdanList.length>0){
	                  	for(var i=0;i<qiangdanList.length;i++){
	                  		if(flag=="2"){
	                  			tempCurrentPage=currentPage;
		                  		contentHtml +='<tr><td><input name="qiangzhuocheckboxId" type="checkbox" value="'+qiangdanList[i].flashSaleId+'" id="checkboxfalseId'+qiangdanList[i].flashSaleId+'"/></td>';
		                  		contentHtml +='<td>';
		                  		if(qiangdanList[i].actTypeId=="1"){
									contentHtml +='<img src="'+ctxPaths+'/resources/images/icon_qiangzhuo_canyin.png" width="40" height="40" />';
								}else if(qiangdanList[i].actTypeId=="2"){
									contentHtml +='<img src="'+ctxPaths+'/resources/images/icon_qiangzhuo_tiyu.png" width="40" height="40" />';
								
								}else if(qiangdanList[i].actTypeId=="3"){
									contentHtml +='<img src="'+ctxPaths+'/resources/images/icon_qiangzhuo_yule.png" width="40" height="40" />';
								
								}
								contentHtml +='</td>';
		                  		contentHtml +='<td>'+qiangdanList[i].actDesc+'</td>';
		                  		contentHtml +='<td>'+qiangdanList[i].nickname+'（'+qiangdanList[i].ownerMobile+'）</td>';
		                  		contentHtml +='<td>'+qiangdanList[i].createTableDateStr+'</td>';
		                  		if(qiangdanList[i].actStatusId=="2"){
		                  			contentHtml +='<td><label class="text-danger">已通过</label></td>';
		                  		}else {
		                  			if(qiangdanList[i].actStatusId=="3"){
			                  			contentHtml +='<td><label class="text-danger">已驳回</label></td>';
			                  		}else if(qiangdanList[i].actStatusId=="1"){
			                  			contentHtml +='<td><label class="text-danger">待审核</label></td>';
			                  		}
			                  		contentHtml +='<td><div class="btn-group">';
			                  		contentHtml +='<button type="button" class="btn btn-primary" onclick="passOrNoPassMethod('+qiangdanList[i].flashSaleId+',2,1)"><span class="glyphicon glyphicon-ok">&nbsp;</span>通过</button>';
			                  		contentHtml +='<a href="#" onclick="passOrNoPassMethod('+qiangdanList[i].flashSaleId+',1,2)"><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal2" ><span class="glyphicon glyphicon-remove">&nbsp;</span>驳回</button></a></div></td>';
		                  		}
		                  		contentHtml +='</tr>';
	                  		}else if(flag=="1"){
	                  			tempCurrentPage=uploadImagecurrentPage;
	                  			pageFlag=5;
	                  			imageContentHtml+='<tr><td><input name="imageCheckboxName" type="checkbox" value="'+qiangdanList[i].flashSaleId+'" id="imageCheckboxfalseId'+qiangdanList[i].flashSaleId+'"/></td>';
	                  			imageContentHtml+='<td>'+qiangdanList[i].nickname+'（'+qiangdanList[i].ownerMobile+'）</td>';
	                  			imageContentHtml+='<td>'+qiangdanList[i].createTableDateStr+'</td>';
	                  			if(qiangdanList[i].actStatusId=="8"){
	                  			
		                  			imageContentHtml+='<td><label class="text-danger">已通过</label></td>';
		                  			imageContentHtml+='<td><div class="btn-group"><button type="button"  class="btn btn-info"  onclick="detailImage('+qiangdanList[i].flashSaleId+');"><span class="glyphicon glyphicon-list-alt">&nbsp;</span>详情</button></div></td>';
	                  			}else{
	                  				if(qiangdanList[i].actStatusId=="6"){
	                  				
	                  					imageContentHtml+='<td><label class="text-danger">待审核</label></td>';
	                  				}else{
	                  					imageContentHtml+='<td><label class="text-danger">已驳回</label></td>';
	                  				}
	                  				imageContentHtml+='<td><div class="btn-group">';
	                  				imageContentHtml+='<button type="button" class="btn btn-info"  onclick="detailImage('+qiangdanList[i].flashSaleId+');" ><span>&nbsp;</span>详情</button>';
	                  				imageContentHtml+='<button type="button" class="btn btn-primary" onclick="passOrNoPassMethod('+qiangdanList[i].flashSaleId+',2,3)"><span class="glyphicon glyphicon-ok">&nbsp;</span>通过</button>';
	                  				imageContentHtml+='<a href="#" onclick="passOrNoPassMethod('+qiangdanList[i].flashSaleId+',1,4)"><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal1"><span class="glyphicon glyphicon-remove">&nbsp;</span>驳回</button></a>';
	                  				imageContentHtml+='</div></td>';
	                  			}
                  				imageContentHtml+='</tr>';
                  		
	                  		}
	                  	}
                	
                	}
                	
	              	rowHtml+='<div class="col-xs-4">';
	              	rowHtml+='<h6>当前为第'+tempCurrentPage+'页，共'+total+'页</h6></div>';
	              	rowHtml+='<div class="col-xs-8">';
	              	rowHtml+='<ul class="pagination pagination-sm pull-right">';
	              	rowHtml+='<li ><a href="#" onclick="setPage('+(tempCurrentPage-1)+','+total+','+pageFlag+')">&laquo;</a></li>';
	              	for(var j=1;j<=total;j++){
	              	
	              	rowHtml+='<li id="page'+j+'"><a href="#" onclick="setPage('+j+','+total+','+pageFlag+')">'+j+'</a></li>';
	              	}
	              	rowHtml+='<li><a href="#" onclick="setPage('+(tempCurrentPage+1)+','+total+','+pageFlag+')">&raquo;</a></li>';
	              	rowHtml+='</ul>';
	              	rowHtml+='</div>';
	              	if(flag=="2"){
              		 	$("#qiangzhuoRowId").html(rowHtml);
                		$("#qiangZhuoContentId").html(contentHtml);
                	}else{
              		 	$("#uploadImageRowId").html(rowHtml);
                		$("#uploadImageContentId").html(imageContentHtml);
                	
                	}
                }else{
                	art.alert(data.msg);
                }
		}
        });
		//去除遮罩
		$("body").hideLoading();
}
/**
**查看详情
**/
function detailImage(falseId){
	var titleHtml="";
	var contentHtml="";
	var imgHtml="";
	var jsonData = {flashSaleId:falseId};
	
	     	jQuery.ajax
		({
			type: "post",
			url:ctxPaths+"/menu/qiangdanAuditDetail",
			dataType: "json",
			data : jsonData,
			cache: false,
			success: function(data){
				var  pinCreatetable =data.pinCreatetable;   
				var pinDanlist = data.pinDanlist;
				var pinSize = pinDanlist.length;
                if(pinCreatetable[0].actTypeId=="1"){
                	titleHtml+='<div class="col-xs-2"><img src="<%=ctxPath %>/resources/images/icon_qiangzhuo_yule.png" width="40" height="40" /></div>';
                }
                titleHtml+='<div class="col-xs-10">'+pinCreatetable[0].actDesc+'</div>';
				for(var j=0;j<pinSize;j++){
					contentHtml+='<tr><td>'+pinDanlist[j].nickName+'<span class="text-red">('+pinDanlist[j].mobile+')</span></td>';
					contentHtml+='<td>'+pinCreatetable[0].nickname+'<span class="text-red">('+pinCreatetable[0].ownerMobile+')</span></td></tr>';                                          
				}
				
              	imgHtml += '<img src=\"<%=ctxPath%>/findPic?plate=2&id=' + pinCreatetable[0].flashSaleId + '\" height=200 />';
            
    			$("#titileId").html(titleHtml);
				$("#chengyuanId").html(contentHtml);
				$("#uploadImageId").html(imgHtml);
				$("#detailxixixId").click();
			}
	     });	
}
/**
** 输入页数
*flag参数1，表示头条管理 2，表示评论管理 3，表示已删除,4表示抢桌活动
**/
function setPage(page,totalPage,flag){
	if(page<1){
		page=1;
		alert("已经是首页了！");
	}else 
	if(page>totalPage){
		page=totalPage;
		alert("已经是最后一页了！");
	}else{
		if(flag=="4"){
			currentPage=page;
			getCreateZhuo(2);

		}else if (flag=="5"){
			uploadImagecurrentPage=page;
			getCreateZhuo(1);
		}
	}
}

/**
**输入查询
* flag参数1，表示头条管理 2，表示评论管理 3，表示已删除，4表示抢桌活动,5照片审核
**/
function inputQuery(flag){
	if(flag=="4"){
		currentPage=1;
		getCreateZhuo(2);
		document.getElementById("qiangzhuoquanxuanId").value="";
	}else if(flag=="5"){
		uploadImagecurrentPage=1;
		getCreateZhuo(1);
		document.getElementById("qiangzhuoquanxuanId").value="";
	}
}

/**
**实现全选，反选
**/
function selectAllNullorReserve(obj,type){  
   if(obj!=null&&obj!=""){  
    if(document.getElementsByName(obj)!=undefined&&document.getElementsByName(obj).length>0){    //getElementsByName函数的作用按名字查找对象，返回一个数组。  
     var userids = document.getElementsByName(obj);  
     if(type=="全选"){  
      for(var i=0;i<userids.length;i++){  
       if(userids[i].checked == false){  
        userids[i].checked = true;  
       }  
      }  
     }else if(type=="全不选"){  
      for(var i=0;i<userids.length;i++){  
       if(userids[i].checked == true){  
        userids[i].checked = false;  
       }  
      }  
     }else if(type=="反选"){  
      for(var i=0;i<userids.length;i++){  
       if(userids[i].checked == true){  
        userids[i].checked = false;  
       }else{  
        userids[i].checked = true;  
       }  
      }  
     }  
    }  
   }    
}  

//单条通过或驳回 
function passOrNoPassMethod(falseId,auditResult,flag){
if(flag=="3"){

document.getElementById("imageCheckboxfalseId"+falseId).checked=true;
shenHeMethod(auditResult,2);
}else if(flag=="2"){
document.getElementById("checkboxfalseId"+falseId).checked=true;
}else if(flag=="1"){
document.getElementById("checkboxfalseId"+falseId).checked=true;
shenHeMethod(auditResult,1);
}else if(flag=="4"){
document.getElementById("imageCheckboxfalseId"+falseId).checked=true;
}
}
/**
**审核通过或驳回    
**/
function shenHeMethod(auditResult,auditFlag)
{
var auditOpinion="";
var str;
$("#tuohuiContentId").val("");
if(auditFlag=="1"){
	 auditOpinion=$("#tuohuiContentId").val();
	 str=document.getElementsByName("qiangzhuocheckboxId");
}else{
	 auditOpinion=$("#ImagetuohuiContentId").val();
	 str=document.getElementsByName("imageCheckboxName");	
}
	
	var objarray=str.length;
	var chestr="";
	for (i=0;i<objarray;i++)
	{
	  if(str[i].checked == true)
	  {
	   chestr+=str[i].value+",";
	  }
	}
	if(chestr == "")
	{
	  alert("请先选择一个抢桌记录审核～！");
	}
	else
	{
		var jsonData ={auditResult:auditResult,auditOpinion:auditOpinion,auditFlag:auditFlag,flashSaleId:chestr};
		jQuery.ajax
		({
			type: "post",
			url:ctxPaths+"/menu/qiangdanAudit",
			dataType: "json",
			data : jsonData,
			cache: false,
			success: function(data){
				if(data.CODE=="TRUE"){
					art.alert("更新成功");
				}else{
					art.alert("更新失败");
				}
			}
	        });		
	}
	if(auditFlag=="1"){
						document.getElementById("qiangzhuoquanxuanId").checked=false;
						$("#myModal2").hide();
						$("#tuohuiContentId").val("");
						getCreateZhuo(2);
						
	}else{
						document.getElementById("uploadAllId").checked=false;
						$("#myModal1").hide();
						$("#ImagetuohuiContentId").val("");
						getCreateZhuo(1);
	}
}
</script>
</head>

<body>
  <div class="container-fluid">
    <div class="row">
      <div class="col-md-12">
        <h4>
          <ol class="breadcrumb">
            <li><a href="#"><span class="glyphicon glyphicon-th-large">&nbsp;</span>活动管理</a></li>
            <li class="active">抢桌活动</li>
            <label class="pull-right"><button type="button" class="btn btn-xs btn-default" onclick="window.location.href='<%=ctxPath %>/activity.tv'"><span class="glyphicon glyphicon-arrow-left">&nbsp;</span>返回</button></label>
          </ol>
        </h4>
      </div>
    </div>
    
    <div>
		<ul class="nav nav-tabs" role="tablist" id="myTab">
          <li class="active"><a href="#pinzhuo" role="tab" data-toggle="tab">创建拼桌</a></li>
          <li><a href="#zhaopian" role="tab" data-toggle="tab">照片审核</a></li>
        </ul>
        
        <div class="tab-content">
          <div class="tab-pane active" id="pinzhuo">
          <!--创建拼桌-->
            <!--搜索-->
           <p></p>
            <div class="panel panel-default">
              <div class="panel-body bg-info">
                <form class="form-inline pull-right" role="form">
                      <div class="form-group">
                        <label class="sr-only" for="exampleInputPassword2">请输入手机号或昵称</label>
                        <input type="text" class="form-control" id="createZhuoPassword2" placeholder="请输入手机号或昵称">
                      </div>
                      <button type="button" class="btn btn-primary" onclick="inputQuery(4)">查询</button>
                    </form>
              </div>
            </div>
                  
            <!--查询结果-->
            
            <div class="table-responsive">

              <table class="table table-hover table-striped">
                  <thead>
                    <tr>
                      <th><input name="" type="checkbox" value="" id="qiangzhuoquanxuanId"/> 全选</th>
                      <th>拼桌类别</th>
                      <th width="200">主题</th>
                      <th>用户名</th>
                      <th>提交时间</th>
                      <th>状态</th>
                      <th>操作</th>
                    </tr>
                  </thead>
             
                 <tbody id="qiangZhuoContentId"></tbody>
                </table>
        
            </div>
            
            <div class="row" id="qiangzhuoRowId">
        
            </div>
            
            <div class="text-center"><button type="button" class="btn btn-primary" onclick="shenHeMethod(2,1)"><span class="glyphicon glyphicon-ok">&nbsp;</span>批量通过</button></div>
            <!--创建拼桌-->
    
          </div>
          <div class="tab-pane" id="zhaopian">
          <!--照片审核-->
          
           <!--搜索-->
           <p></p>
            <div class="panel panel-default">
              <div class="panel-body bg-info">
                <form class="form-inline pull-right" role="form">
                      <div class="form-group">
                        <label class="sr-only" for="exampleInputPassword2">请输入手机号或昵称</label>
                        <input type="text" class="form-control" id="uploadImageInputPassword2" placeholder="请输入手机号或昵称">
                      </div>
                      <button type="button" class="btn btn-primary" onclick="inputQuery(5)">查询</button>
                    </form>
              </div>
            </div>
            <!--查询结果-->
            <div class="table-responsive">

              <table class="table table-hover table-striped">
                  <thead>
                    <tr>
                      <th><input name="" type="checkbox" value="" id="uploadAllId"/> 全选</th>
                      <th>用户名</th>
                      <th>提交时间</th>
                      <th>状态</th>
                      <th>操作</th>
                    </tr>
                  </thead>
               	  <tbody id="uploadImageContentId"></tbody>
                </table>
        
            </div>
            
            <div class="row" id="uploadImageRowId">
              
            </div>
            
            <div class="text-center"><button type="button" class="btn btn-primary" onclick="shenHeMethod(2,2)"><span class="glyphicon glyphicon-ok">&nbsp;</span>批量通过</button></div>
            <!--照片审核-->
          </div>
        </div>
        
        <script>
          $(function () {
            $('#myTab a:last').tab('show')
          })
        </script>
    </div>
    
  </div>
  
   <!-- Modal1 -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" onclick="javascript:$('#myModal').hide();"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>
            <h4 class="modal-title" id="myModalLabel">拼桌详情</h4>
          </div>
          <div class="modal-body">
            <h4><span class="label label-info">主题：</span></h4>
            <div class="alert alert-info" role="alert">
              <div class="row" id="titileId">
               
            
              </div>
            </div>
            
            <h4><span class="label label-info">成员：</span></h4>
             <div class="alert alert-info" role="alert">
               
               <div class="row">
                 <div class="col-xs-2">
                   <img src="<%=ctxPath %>/resources/images/icon_user_default.png" width="40" height="40" />
                 </div>
                 <div class="col-xs-10">
                 
                   <table width="100%" border="0" cellspacing="1" cellpadding="1" id="chengyuanId">
                     
                    </table>
                 </div>
               </div>
             </div>
              <div id="uploadImageId"></div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="javascript:$('#myModal').hide();">关闭</button>
          </div>
        </div>
      </div>
    </div> 
   
   
    <!-- Modal2 -->
    <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" onclick="javascript:chufa(1);" ><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>
            <h4 class="modal-title" id="myModalLabel">审核驳回</h4>
          </div>
          <div class="modal-body">
            <textarea class="form-control" rows="3" id="tuohuiContentId"></textarea>
          </div>
          <div class="modal-footer">
            <h6 class="pull-left">还能输入140个字</h6>
            <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:chufa(1);" >取消</button>
            <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="shenHeMethod(1,1)">确定</button>
          </div>
        </div>
      </div>
    </div>
    <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" onclick="javascript:chufa(2);" ><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>
            <h4 class="modal-title" id="myModalLabel">审核驳回</h4>
          </div>
          <div class="modal-body">
            <textarea class="form-control" rows="3" id="ImagetuohuiContentId"></textarea>
          </div>
          <div class="modal-footer">
            <h6 class="pull-left">还能输入140个字</h6>
            <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:chufa(2);" >取消</button>
            <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="shenHeMethod(1,2)">确定</button>
          </div>
        </div>
      </div>
    </div>
   <div id="modellllId" style="display: none"> <button type=button class="btn btn-info" data-toggle="modal" data-target="#myModal" id="detailxixixId"></button> </div>
    <script>
    function chufa(flag){
    	if(flag=="1"){
	    	$('#myModal2').hide();
	    	selectAllNullorReserve("qiangzhuocheckboxId","全不选");
    	}else{
    		$('#myModal1').hide();
	    	selectAllNullorReserve("imageCheckboxName","全不选");
    	}
    }
    </script>
</body>
</html>
