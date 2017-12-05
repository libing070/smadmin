/**头条发布**/

function modifyHeadline(){
var content=$("#modifyInputEmailContentId").val();

var title=$("#modifyInputEmailTitileId").val();
if(title.length>31){
	art.alert("标题不能超过31个汉字")
}else
if(content.length>140){
	art.alert("内容不能超过140个汉字")
}else{
$("body").showLoading();
var ifSupportComment="1";
var sort="1";
var str=document.getElementsByName("picName");
var picStringList="";
for (i=0;i<str.length;i++)
{

   picStringList+=str[i].value+",";
}
if(document.getElementById("islimitReplyIdmodify").checked){
	ifSupportComment="2";
}
if(jiyiModifyType=="6"){
	sort=$("#modifyHeadlineSortId").val();
}else{
	sort=$("#modifyElseIdSortId").val();
}
var jsonData = {picStringList:picStringList,contentTypeId:jiyiModifyType,title:title,ifSupportComment:ifSupportComment,sort:sort,content:content};
jQuery.ajax
	({
		type: "post",
		url:ctxPaths + "/menu/modifyHeadlineContent",
		dataType: "json",
		data : jsonData,
		cache: false,
		success: function(data){
		
                if(data.CODE="TRUE"){
                	art.alert(data.msg);
                	window.location.href=ctxPaths + "/publish.tv";
                }else{
                	art.alert(data.msg);
                }
		}
        });
		//去除遮罩
		$("body").hideLoading();
}
}
/**
**获取分类(发布头条)
*/
function  getModifyType(flag){
	if(flag=="6"){
		$("#gonggaoManage1").attr("class","btn btn-default active");
		$("#xinwenManage1").attr("class","btn btn-default");
		$("#guanshuiManage1").attr("class","btn btn-default");
		jiyiModifyType=6;
		$("#modifyGongGaoId").show();
		$("#modifyElseId").hide();
	}else if(flag=="2"){
		$("#xinwenManage1").attr("class","btn btn-default active");
		$("#gonggaoManage1").attr("class","btn btn-default");
		$("#guanshuiManage1").attr("class","btn btn-default");
		jiyiModifyType=2;
		$("#modifyGongGaoId").hide();
		$("#modifyElseId").show();
	}else if(flag=="1"){
		$("#guanshuiManage1").attr("class","btn btn-default active");
		$("#xinwenManage1").attr("class","btn btn-default");
		$("#gonggaoManage1").attr("class","btn btn-default");
		jiyiModifyType=1;
		$("#modifyGongGaoId").hide();
		$("#modifyElseId").show();
	}
}
/**头条管理**/
//设置请求
function setRequest(url,jsonData){
jQuery.ajax
	({
		type: "post",
		url:url,
		dataType: "json",
		data : jsonData,
		cache: false,
		success: function(data){
		
                if(data.CODE="TRUE"){
                	if(data.msg=="3"){
                		alert("删除成功！")
                		getTouManage(jiyiType,jiyiPage,times);
                		document.getElementById("touTiaoManageSelectId").checked=false;
                	}else{
                		alert("设置成功！");
                	getTouManage(jiyiType,jiyiPage,times);
                	}
                }else{
                	alert("设置失败!");
                }
		}
        });
}

//查询头条管理信息
function getTouManage(contentTypeId,page,times){
	
	var begin = page*times-times;
	var end =times;
	var mobile="";
	var content="";
	var temp1 =$("#exampleInputPassword2").val();
	var temp2 =$("#touManageQueryId").val();
	if(temp2=="1"){
		mobile=temp1;
	}else{
		content=temp1;
	}
	var jsonData ={"contentTypeId":contentTypeId,"begin":begin,"end":end,"mobile":mobile,"content":content,"isManger":isManger};
	jQuery.ajax
	({
		type: "post",
		url: ctxPaths + "/menu/queryContentlineList",
		dataType: "json",
		data : jsonData,
		cache: false,
		success: function(data){
			if(data.CODE=="FALSE"){
				alert(data.msg,3000);
			}else if(data.CODE=="TRUE"){
			 	var headlineList =data.headlineList;
			 	var totalPage=data.totalPage;
			 	var contentHtml='';
			 	var pageHtml='';
            	 if(headlineList.length>0){
            	 	for(var i=0;i<headlineList.length;i++){
			 			contentHtml+='<tr><td><input name="checkboxManageName" type="checkbox" value="'+headlineList[i].headlineId+'" class="checkboxClass" id="checkboxId'+headlineList[i].headlineId+'"/></td><td><div class="panel panel-default">';
            	 		contentHtml+='<div class="panel-body bg-info"><table width="100%" border="0" cellspacing="1" cellpadding="1">';
            	 		contentHtml+='<tr><td>'+headlineList[i].nickname+'</td>';
            	 		contentHtml+='<td><strong>'+headlineList[i].title+'</strong></td></tr>';
            	 		contentHtml+='<tr><td><span class="label label-primary">'+getTypeName(headlineList[i].contentTypeId)+'</span></td>';
            	 		contentHtml+='<td><p>'+headlineList[i].content+'</p>';
            	 		contentHtml+='<div class="row"><div class="col-xs-12" id="imgToutiaoManage'+headlineList[i].headlineId+'">';
				
				               var  picList=headlineList[i].picList;
				               	for (var j=0;j<picList.length;j++) {
									contentHtml+='<div class="pic-preview"><img src="'+ctxPaths+'/listHeadlineContentAttach?pictureSerialnum=' +picList[j].pictureSerialnum + '"  class="lightbox" width="100%;"/></div>';
									
								}
						
            	 		contentHtml+='</div></div></td></tr>';
            	 		contentHtml+='<tr><td><span class="text-red glyphicon glyphicon-heart">'+headlineList[i].praiseNum+'</span></td>';
            	 		contentHtml+='<td><small class="pull-right">'+headlineList[i].operDateString+'</small></td></tr>';
                		contentHtml+='</table></div>';       
                		contentHtml+='<div class="panel-footer"><p class="text-right"><button type="button" class="btn btn-danger" id="btn_shezhi" onclick="openSet('+headlineList[i].headlineId+')">设置</button><button type="button" class="btn btn-danger" id="btn_del" onclick="delTouTiao('+headlineList[i].headlineId+')">删除</button></p>';       
                		contentHtml+='<div class="alert alert-warning" role="alert" id="shezhi'+headlineList[i].headlineId+'" style="display:none;">';       
                		contentHtml+='<form class="form-inline text-right" role="form"><div class="checkbox" style="margin-right:20px;">';       
                		contentHtml+='<label>';
                		if(headlineList[i].ifSupportComment=="2"){
                			
                			contentHtml+='<input type="checkbox" id="limitToutiaoId'+headlineList[i].headlineId+'" checked> ' ;
                		}else{
                			contentHtml+='<input type="checkbox" id="limitToutiaoId'+headlineList[i].headlineId+'" > ' ;
                		}
                		contentHtml+='禁止用户评论此头条</label></div>' ;
                		if(headlineList[i].contentTypeId=="6"){
                		contentHtml+='<div class="checkbox" ><label><input type="checkbox" id="tuijianId'+headlineList[i].headlineId+'" checked> 置顶</label></div>';   
                		}else{
                		contentHtml+='<div class="checkbox" ><label><input type="checkbox" id="tuijianId'+headlineList[i].headlineId+'" checked> 推广</label></div>';   
                			
                		}
                		contentHtml+='<div class="form-group"><select class="form-control" id="tuiguang'+headlineList[i].headlineId+'">';
						if(headlineList[i].contentTypeId=="6"){
							if(headlineList[i].topNumber=="21"){
								
							contentHtml+='<option value="21" selected>首页-顶部</option>';  
							}else{
							contentHtml+='<option value="21">首页-顶部</option>';  
							}
							if(headlineList[i].topNumber=="22"){
							contentHtml+='<option value="22" selected>首页头条2</option>';   
							}else{
							contentHtml+='<option value="22">首页头条2</option>';   
							}
							if(headlineList[i].topNumber=="23"){
							contentHtml+='<option value="23" selected>首页头条3</option>'; 
							}else{
							contentHtml+='<option value="23" >首页头条3</option>'; 
							}
							if(headlineList[i].topNumber=="24"){
							contentHtml+='<option value="24" selected>首页头条4</option>';  
							}else{
							contentHtml+='<option value="24">首页头条4</option>';  
							}
							
						}else{
							if(headlineList[i].topNumber=="1"){
							contentHtml+='<option value="1" selected>首页-顶部</option>';
							}else{
							contentHtml+='<option value="1">首页-顶部</option>';
							}
							if(headlineList[i].topNumber=="2"){
							contentHtml+='<option value="2" selected>首页-推荐2</option>'; 
							}else{
							contentHtml+='<option value="2">首页-推荐2</option>'; 
							}
							if(headlineList[i].topNumber=="3"){
							contentHtml+='<option value="3" selected>首页-推荐3</option>';
							}else{
							contentHtml+='<option value="3">首页-推荐3</option>';
							}
							if(headlineList[i].topNumber=="4"){
							contentHtml+='<option value="4" selected>首页-推荐4</option>';  
							}else{
							contentHtml+='<option value="4">首页-推荐4</option>';  
							}
							if(headlineList[i].topNumber=="5"){
							contentHtml+='<option value="5" selected>首页-推荐5</option>'; 
							}else{
							contentHtml+='<option value="5">首页-推荐5</option>'; 
							}
							if(headlineList[i].topNumber=="6"){
							contentHtml+='<option value="6" selected>首页-推荐6</option>'; 
							}else{
							contentHtml+='<option value="6">首页-推荐6</option>'; 
							}
						}
						contentHtml+='</select></div>';       
                		contentHtml+='<button type="button" class="btn btn-link">预览</button>';       
                		contentHtml+='<button type="button" class="btn btn-primary" onclick="makeSure('+headlineList[i].headlineId+')">确认</button>';       
                		contentHtml+='<button type="button" class="btn btn-warning" id="btn_quxiao" onclick="closeSet('+headlineList[i].headlineId+')">取消</button>';       
                		contentHtml+='</form></div>';       
                		contentHtml+='<div class="alert alert-warning text-right" role="alert" id="del" style="display:none;">';       
                		contentHtml+='<strong>注：</strong> 此操作将删除该头条下的全部评论和赞';       
                		contentHtml+='<button type="button" class="btn btn-primary" id="btn_del_confirm">确认</button>';       
                		contentHtml+='<button type="button" class="btn btn-warning" id="btn_del_cancel">取消</button>';       
                		contentHtml+='</div>';       
                   		contentHtml+='</div></div></td></tr>';    
            	 	}
            	 }
            	 		if(totalPage>0){
           		      		pageHtml+='<div class="col-xs-4"><h6>当前为第'+jiyiPage+'页，共'+totalPage+'页</h6></div>';
           		      	}else{
           		      		pageHtml+='<div class="col-xs-4"><h6>当前为第'+0+'页，共'+totalPage+'页</h6></div>';
           		      		
           		      	}
           		      	pageHtml+='<div class="col-xs-8"><ul class="pagination pagination-sm pull-right">';
           		      	pageHtml+='<li><a href="#" onclick="setPage('+(jiyiPage-1)+','+totalPage+',1)">&laquo;</a></li>';
           		      	for(var v=1;v<=totalPage;v++){
           		      		pageHtml+='<li><a href="#" onclick="setPage('+v+','+totalPage+',1)">'+v+'</a></li>';
           		      	}
           		      	           		      	
           		      	pageHtml+='<li><a href="#" onclick="setPage('+(jiyiPage+1)+','+totalPage+',1)">&raquo;</a></li>';
           		      	pageHtml+='</ul></div>';
              $("#toutiaoContentId").html(contentHtml);
              $("#toutiaoContentPageId").html(pageHtml);
             addLigthBoxEvent();
			}
		},
	error: function(){
			alert("对不起网络异常！");
	}
	});	
}

/**
** 获取类型名称
**/
function getTypeName(type){
	var typeName="公告";
	if(type=="1"){
		typeName="灌水";
	}else if(type=="2"){
		typeName="新闻";
	}else if(type=="6"){
		typeName="公告";
	}
	return typeName;
}
/**
** 打开设置
*
**/
function openSet(headlineId){
		  $("#shezhi"+headlineId).show();
}
/**
** 关闭设置
*
**/
function closeSet(headlineId){
		  $("#shezhi"+headlineId).hide();
}
/**
**删除头条
**/
function delTouTiao(headlineId){
	document.getElementById("checkboxId"+headlineId).checked=true;
	var jsonData = {headlineId:headlineId,limitReply:"",tuiguangNum:"",isDel:3};
	setRequest(listPicUrl,jsonData);		
}
/**
**确认
**/
function makeSure(headlineId){
if(document.getElementById("tuijianId"+headlineId).checked==true&&document.getElementById("limitToutiaoId"+headlineId).checked==false){
	var jsonData = {headlineId:headlineId,limitReply:"1",tuiguangNum:$("#tuiguang"+headlineId).val(),isDel:""};
	setRequest(listPicUrl,jsonData);	
}
if(document.getElementById("limitToutiaoId"+headlineId).checked==true&&document.getElementById("tuijianId"+headlineId).checked==false){
var jsonData = {headlineId:headlineId,limitReply:2,tuiguangNum:"",isDel:""};
	setRequest(listPicUrl,jsonData);	
}
if(document.getElementById("limitToutiaoId"+headlineId).checked==false&&document.getElementById("tuijianId"+headlineId).checked==false){
	alert("您未设置");
}
if(document.getElementById("limitToutiaoId"+headlineId).checked==true&&document.getElementById("tuijianId"+headlineId).checked==true){
var jsonData = {headlineId:headlineId,limitReply:2,tuiguangNum:$("#tuiguang"+headlineId).val(),isDel:""};
	setRequest(listPicUrl,jsonData);	
}

}
/**
** 输入页数
*flag参数1，表示头条管理 2，表示评论管理 3，表示已删除
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
		if(flag=="1"){
			jiyiPage=page;
			getTouManage(jiyiType,jiyiPage,times);
		}else if (flag=="2"){
			replyManageJiyiPage=page;
			getReplyMange();
		}else if(flag=="3"){
			delManageJiyiPage=page;
			getDelList();
		}
	}
}
/**
**获取分类
*/
function  getType(flag){
	if(flag=="6"){
		getTouManage(6,1,times);
		$("#gonggaoManage").attr("class","btn btn-default active");
		$("#xinwenManage").attr("class","btn btn-default");
		$("#guanshuiManage").attr("class","btn btn-default");
		jiyiType=6;
		
	}else if(flag=="2"){
		getTouManage(2,1,times);
		$("#xinwenManage").attr("class","btn btn-default active");
		$("#gonggaoManage").attr("class","btn btn-default");
		$("#guanshuiManage").attr("class","btn btn-default");
		jiyiType=2;
	}else if(flag=="1"){
		getTouManage(1,1,times);
		$("#guanshuiManage").attr("class","btn btn-default active");
		$("#xinwenManage").attr("class","btn btn-default");
		$("#gonggaoManage").attr("class","btn btn-default");
		jiyiType=1;
	}
}

/**
**输入查询
* flag参数1，表示头条管理 2，表示评论管理 3，表示已删除
**/
function inputQuery(flag){
	if(flag=="1"){
		jiyiPage=1;
		getTouManage(jiyiType,jiyiPage,times);
		document.getElementById("exampleInputPassword2").value="";
	}else if(flag=="2"){
		replyManageJiyiPage=1;
		getReplyMange();
		document.getElementById("replyInputPassword2").value="";
	}else if(flag=="3"){
		delManageJiyiPage=1;
		getDelList();
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

/**
**头条批量删除
**/
function toutiaoDel()
{
var str=document.getElementsByName("checkboxManageName");
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
  alert("请先选择一个头条删除～！");
}
else
{
	var jsonData = {headlineId:"",limitReply:"",tuiguangNum:"",isDel:chestr};
	setRequest(listPicUrl,jsonData);
}
}


/**已删除**/
function getDelList(){
var delbegin = delManageJiyiPage*delManageTimes-delManageTimes;	
var temp1 =$("#delManageAId").val();
var temp2 =$("#delManageBId").val();
var moibleHeadline="";
var contentHeadline="";
var contentReply="";
var moibleReply="";
if(temp1=="1"&&temp2=="3"){
	moibleHeadline=$("#delExampleInputPassword2").val();
}
if(temp1=="1"&&temp2=="4"){
	contentHeadline=$("#delExampleInputPassword2").val();
}
if(temp1=="2"&&temp2=="3"){
	moibleReply=$("#delExampleInputPassword2").val();
}
if(temp1=="2"&&temp2=="4"){
	contentReply=$("#delExampleInputPassword2").val();
}
var jsonData ={"flag":temp1,"begin":delbegin,"end":delManageTimes,"contentHeadline":contentHeadline,"contentReply":contentReply,"moibleHeadline":moibleHeadline,"moibleReply":moibleReply};
jQuery.ajax
	({
		type: "post",
		url:ctxPaths + "/menu/queryDelheadlineList",
		dataType: "json",
		data : jsonData,
		cache: false,
		success: function(data){		
                if(data.CODE="TRUE"){
                		var delStatusHeadlineList =data.delStatusHeadlineList;
                		var delStatusReplyList =data.delStatusReplyList;
					 	var totalPage=data.totalPage;
					 	var headlineHtml='';
					 	var replyHtml='';
					 	var totalHtml='';
					 	if(temp1=="1"){
					 		if(delStatusHeadlineList.length>0){
						 		for(var i=0;i<delStatusHeadlineList.length;i++){
						 			headlineHtml+='<tr><td>';
						 			headlineHtml+='<div class="panel panel-default"><div class="panel-body bg-info"><table width="100%" border="0" cellspacing="1" cellpadding="1">';
						 			headlineHtml+='<tr><td>'+delStatusHeadlineList[i].nickname+'</td>';
						 			headlineHtml+='<td><strong>'+delStatusHeadlineList[i].title+'</strong></td>';
						 			headlineHtml+='</tr><tr><td><span class="label label-primary">'+getTypeName(delStatusHeadlineList[i].contentTypeId)+'</span></td>';
						 			headlineHtml+='<td> <p>'+delStatusHeadlineList[i].content+'</p>';
						 			headlineHtml+='<div class="row"><div class="col-xs-12">';
						 			  var  picList=delStatusHeadlineList[i].picList;
				               			for (var j=0;j<picList.length;j++) {
											headlineHtml+='<div class="pic-preview"><img src="'+ctxPaths+'/listHeadlineContentAttach?pictureSerialnum=' +picList[j].pictureSerialnum + '"  class="lightbox" width="100%;"/></div>';
											
										}
						 			headlineHtml+='</div></div> </td></tr>';
						 			headlineHtml+='<tr><td><span class="text-red glyphicon glyphicon-heart">'+delStatusHeadlineList[i].praiseNum+'</span></td>';
						 			headlineHtml+='<td><small class="pull-right">'+delStatusHeadlineList[i].operDateString+'</small></td>';
						 			headlineHtml+='</tr></table></div> </td></tr>';
						 		}
					 		}
					 		$("#delManageTableId").html(headlineHtml);
					 	}else{
					 		if(delStatusReplyList.length>0){
						 		for(var j=0;j<delStatusReplyList.length;j++){
						 			if(delStatusReplyList[j].parentReplyId=="0"){
							 			replyHtml+='<tr><td valign="top"><input name="" type="checkbox" value="" /></td> ';
							 			replyHtml+='<td><p class="bg-info"><h6>'+delStatusReplyList[j].nickname+'<span class="pull-right">'+delStatusReplyList[j].replyStringDate+'</span></h6>';
							 			replyHtml+='<h4>'+delStatusReplyList[j].content+'</h4>';
							 			replyHtml+='<blockquote><h6><span class="text-primary">评论'+delStatusReplyList[j].parentNickName+'的头条：</span>'+delStatusReplyList[j].headtitle+'</h6></blockquote>';
							 			replyHtml+='</p>';                       
							 			replyHtml+='</td></tr>';  
						 			}else{
						 				
							 			replyHtml+='<tr><td valign="top"><input name="" type="checkbox" value="" /></td> ';
							 			replyHtml+='<td><p class="bg-info"><h6>'+delStatusReplyList[j].nickname+'<span class="pull-right">'+delStatusReplyList[j].replyStringDate+'</span></h6>';
							 			replyHtml+='<h4><span class="text-primary">评论'+delStatusReplyList[j].headNickName+'的头条：</span>'+delStatusReplyList[j].content+'</h4>';
							 			replyHtml+='<blockquote><h6><span class="text-primary">回复'+delStatusReplyList[j].parentNickName+'的评论：</span><span class="text-primary">回复'+delStatusReplyList[j].headNickName+'的头条：</span>'+delStatusReplyList[j].replyConent+'</h6></blockquote>';
							 			replyHtml+='</p>';             
							 		            
							 			replyHtml+='</td></tr>';  
						 				
						 			}          
						 		}
					 		}
					 		$("#delManageTableId").html(replyHtml);
					 	}
					 	if(totalPage>0){
					 		totalHtml+='<div class="col-xs-4"><h6>当前为第'+delManageJiyiPage+'页，共'+totalPage+'页</h6></div>';
					 	}else{
					 		totalHtml+='<div class="col-xs-4"><h6>当前为第'+0+'页，共'+totalPage+'页</h6></div>';
					 		
					 	}
              		 	totalHtml+='<div class="col-xs-8"> <ul class="pagination pagination-sm pull-right"> <li><a href="#" onclick="setPage('+(delManageJiyiPage-1)+','+totalPage+',3)">&laquo;</a></li>';
              		 	for(var k=1;k<=totalPage;k++){
	              		 	totalHtml+='<li><a href="#" onclick="setPage('+k+','+totalPage+',3)">'+k+'</a></li>';             		 		
              		 	}
	              		totalHtml+='<li><a href="#" onclick="setPage('+(delManageJiyiPage+1)+','+totalPage+',3)">&raquo;</a></li>';             		 		
	              		totalHtml+='</ul></div>';
	              		$("#delManageRowId").html(totalHtml);
                }else{
                	alert("查询已删除信息失败!");
                }
		},
		error: function(){
				alert("对不起网络异常！");
		}
        });
}

/***评论管理**/
function getReplyMange(){
var replybegin = replyManageJiyiPage*replyManageTimes-replyManageTimes;	
var temp1 =$("#replyManageSelectId").val();
var contentReply="";
var moibleReply="";
if(temp1=="1"){
	moibleReply=$("#replyInputPassword2").val();
}
if(temp1=="2"){
	contentReply=$("#replyInputPassword2").val();
}
var jsonData ={"begin":replybegin,"end":replyManageTimes,"contentReply":contentReply,"moibleReply":moibleReply};
jQuery.ajax
	({
		type: "post",
		url:ctxPaths + "/menu/queryReplyList",
		dataType: "json",
		data : jsonData,
		cache: false,
		success: function(data){		
                if(data.CODE="TRUE"){
                		var replyList =data.replyList;
					 	var totalPage=data.totalPage;
					 	var replyHtml='';
					 	var totalHtml='';
					 
					 		if(replyList.length>0){
						 		for(var j=0;j<replyList.length;j++){
						 			if(replyList[j].parentReplyId=="0"){
							 			replyHtml+='<tr><td valign="top"><input name="checkboxReplyManageName" type="checkbox" value="'+replyList[j].replyId+'" id="checkboxReplyId'+replyList[j].replyId+'"/></td> ';
							 			replyHtml+='<td><p class="bg-info"><h6>'+replyList[j].nickname+'<span class="pull-right">'+replyList[j].replyStringDate+'</span></h6>';
							 			replyHtml+='<h4>'+replyList[j].content+'</h4>';
							 			replyHtml+='<blockquote><h6><span class="text-primary">评论'+replyList[j].parentNickName+'的头条：</span>'+replyList[j].headtitle+'</h6></blockquote>';
							 			replyHtml+='</p>';             
							 			replyHtml+='<p class="text-right"><button type="button" class="btn btn-danger" id="btn_comment_del" onclick="openDelReply('+replyList[j].replyId+')">删除</button></p>';             
							 			replyHtml+='<div class="alert alert-warning text-right" role="alert" id="comment_delReply'+replyList[j].replyId+'" style="display:none;">';             
							 			replyHtml+='确认要删除该条评论吗？';             
							 			replyHtml+='<button type="button" class="btn btn-primary" id="btn_del_comment_confirm" onclick="makeSureDelReply('+replyList[j].replyId+')">确认</button>';             
							 			replyHtml+='<button type="button" class="btn btn-warning" id="btn_del_comment_cancel" onclick="closeDelReply('+replyList[j].replyId+')">取消</button>';             
							 			replyHtml+=' </div>';             
							 			replyHtml+='</td></tr>';  
						 			}else{
						 				
							 			replyHtml+='<tr><td valign="top"><input name="checkboxReplyManageName" type="checkbox" value="'+replyList[j].replyId+'" id="checkboxReplyId'+replyList[j].replyId+'" /></td> ';
							 			replyHtml+='<td><p class="bg-info"><h6>'+replyList[j].nickname+'<span class="pull-right">'+replyList[j].replyStringDate+'</span></h6>';
							 			replyHtml+='<h4><span class="text-primary">评论'+replyList[j].headNickName+'的头条：</span>'+replyList[j].headtitle+'</h4>';
							 			replyHtml+='<blockquote><h6><span class="text-primary">回复'+replyList[j].parentNickName+'的评论：</span><span class="text-primary">回复'+replyList[j].headNickName+'的头条：</span>'+replyList[j].replyConent+'</h6></blockquote>';
							 			replyHtml+='</p>';             
							 			replyHtml+='<p class="text-right"><button type="button" class="btn btn-danger" id="btn_comment_del" onclick="openDelReply('+replyList[j].replyId+')">删除</button></p>';             
							 			replyHtml+='<div class="alert alert-warning text-right" role="alert" id="comment_delReply'+replyList[j].replyId+'" style="display:none;">';             
							 			replyHtml+='确认要删除该条评论吗？';             
							 			replyHtml+='<button type="button" class="btn btn-primary" id="btn_del_comment_confirm" onclick="makeSureDelReply('+replyList[j].replyId+')">确认</button>';             
							 			replyHtml+='<button type="button" class="btn btn-warning" id="btn_del_comment_cancel" onclick="closeDelReply('+replyList[j].replyId+')">取消</button>';             
							 			replyHtml+=' </div>';             
							 			replyHtml+='</td></tr>';  
						 				
						 			}
						 		}
					 		}
					 		$("#replyContentTableId").html(replyHtml);
					 	if(totalPage>0){
					 		totalHtml+='<div class="col-xs-4"><h6>当前为第'+replyManageJiyiPage+'页，共'+totalPage+'页</h6></div>';
              		 	}else{
					 		totalHtml+='<div class="col-xs-4"><h6>当前为第'+0+'页，共'+totalPage+'页</h6></div>';
              		 		
              		 	}
					 	totalHtml+='<div class="col-xs-8"> <ul class="pagination pagination-sm pull-right"> <li><a href="#" onclick="setPage('+(replyManageJiyiPage-1)+','+totalPage+',2)">&laquo;</a></li>';
              		 	for(var k=1;k<=totalPage;k++){
	              		 	totalHtml+='<li><a href="#" onclick="setPage('+k+','+totalPage+',2)">'+k+'</a></li>';             		 		
              		 	}
	              		totalHtml+='<li><a href="#" onclick="setPage('+(replyManageJiyiPage+1)+','+totalPage+',2)">&raquo;</a></li>';             		 		
	              		totalHtml+='</ul></div>';
	              		$("#replyManageRowId").html(totalHtml);
                }else{
                	alert("查询已删除信息失败!");
                }
		},
		error: function(){
				alert("对不起网络异常！");
		}
        });
	
}
/**
**删除评论
**/
function makeSureDelReply(replyId){
	var jsonData = {replyId:replyId+""};
	jQuery.ajax
	({
		type: "post",
		url:ctxPaths + "/menu/delReply",
		dataType: "json",
		data : jsonData,
		cache: false,
		success: function(data){
		
                if(data.CODE="TRUE"){
                	alert("删除成功！")
                	getReplyMange();                	
                }else{
                	alert("删除失败!");
                }
		}
        });		
}
/**
**评论批量删除
**/
function replyListDel()
{
var str=document.getElementsByName("checkboxReplyManageName");
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
  alert("请先选择一个评论删除～！");
}
else
{
	makeSureDelReply(chestr);
}
}
/**
**打开删除div
**/
function openDelReply(replyId){
	$("#comment_delReply"+replyId).show();
}
/**
**取消DIV
**/
function closeDelReply(replyId){
	$("#comment_delReply"+replyId).hide();
}