<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>北京移动wifi分发管理平台</title>
<%@include file="../common/include.jsp"%>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/json.js"/></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/jquery.lightbox.js"/></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/publish.js"/></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/pagination.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/resources/jquery/jquery.form.js"></script>
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
</style>


<script type="text/javascript">
//上传图片限制张数
var limitPicTimes =0;

//发布头条参数
var jiyiModifyType =6;
//头条管理参数
var listPicUrl = '<%=ctxPath%>/updateHeadline';
var page =1;//访问页数
var times =5;//每页显示条数
var jiyiType =6;//记忆查询类型
var jiyiPage=1;//记忆页数
var isManger=0;//记忆是否为管理员	  
//评论参数
var replyManageJiyiPage=1;//记忆访问页数（评论）
var replyManageTimes = 5;//每页显示条数（评论）
//已删除参数
var delManageTimes =5;//每页显示条数（已删除）
var delManageJiyiPage=1;//记忆访问页数（已删除）
	$(document).ready(function(){
	  $('#myTab a:first').tab('show');
		
	  $("#touTiaoManageSelectId").click(function(){
		  if(document.getElementById("touTiaoManageSelectId").checked){
		  	selectAllNullorReserve("checkboxManageName","全选");
		  }else{
		  	selectAllNullorReserve("checkboxManageName","全不选");
		  	
		  }
	  });
	  $("#isManagerId").click(function(){
	  	 if(document.getElementById("isManagerId").checked){
	  	 	isManger=1;
		  	getTouManage(jiyiType,jiyiPage,times);
		  }else{
		  	isManger=0;
		  	getTouManage(jiyiType,jiyiPage,times);
		  }
	  });
	  $("#replyAllCheckedId").click(function(){
		  if(document.getElementById("replyAllCheckedId").checked){
		  	selectAllNullorReserve("checkboxReplyManageName","全选");
		  }else{
		  	selectAllNullorReserve("checkboxReplyManageName","全不选");
		  	
		  }
	  });
	  $("#btn_del_cancel").click(function(){
		  $("#del").hide();
	  })
	  
	  $("#btn_comment_del").click(function(){
		  $("#comment_del").show();
	  });
	  $("#btn_del_comment_confirm").click(function(){
		  $("#comment_del").hide();
	  })
	  $("#btn_del_comment_cancel").click(function(){
		  $("#comment_del").hide();
	  })
	
	  getTouManage(6,1,times);
	  getDelList();
	  getReplyMange();
	  addLigthBoxEvent();
	});
	

	function  uploadPrizeFile(obj){
		if(limitPicTimes<4){
			var form = $(obj).parent();
			form.ajaxSubmit({
		 		type : 'post',
		    	url : "<%=ctxPath%>/imageUpload.tv/0",
		    	success:function (result, status) {
					var data = jQuery.parseJSON(result);
					if (data.CODE == "FALSE") {
						art.alert(data.msg);
					}else if(data.CODE == "TRUE"){
						//当前编辑对象
						var viewhtml='<div class="alert alert-warning alert-dismissible pic-preview" role="alert">';
						viewhtml += '<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>';
						viewhtml += '<img src="'+imageAccessPath+data.fileName+'" width="100%" alt="...">';
						viewhtml +='<input type="hidden" name="picName" value="'+data.fileName+'"/>';
						viewhtml += '</div>';
						$("#pic_viewDiv").prepend(viewhtml);
						limitPicTimes++;
					}else{
						art.alert(window.catch_title);
					}
				}
			});
			return false;//为了防止刷新	
		}else{
			art.alert("最多只能传4张图片");
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
            <li class="active"><a href="#"><span class="glyphicon glyphicon-th-large">&nbsp;</span>头条发布管理</a></li>
            <!--<label class="pull-right"><button type="button" class="btn btn-xs btn-default" onclick="window.location.href='guanli_huodong.html'"><span class="glyphicon glyphicon-arrow-left">&nbsp;</span>返回</button></label>-->
          </ol>
        </h4>
      </div>
    </div>
    <div>
		<ul class="nav nav-tabs" role="tablist" id="myTab">
          <li class="active"><a href="#fabu" role="tab" data-toggle="tab">头条发布</a></li>
          <li><a href="#toutiao" role="tab" data-toggle="tab">头条管理</a></li>
          <li><a href="#pinglun" role="tab" data-toggle="tab">评论管理</a></li>
          <li><a href="#tuiguang" role="tab" data-toggle="tab">现网预览</a></li>
          <li><a href="#shanchu" role="tab" data-toggle="tab">已删除</a></li>
        </ul>
        
        <div class="tab-content">
          <div class="tab-pane active" id="fabu">
          <!--头条发布-->
          <br />
              <div class="form-group">
                <label for="exampleInputEmail1">类别：</label>
                <div class="" data-toggle="buttons">
                 <!--   <label class="btn btn-default active">
                    <input type="radio" name="options" id="option1" autocomplete="off" checked onclick="getModifyType(6)">公告
                  </label>
                  <label class="btn btn-default">
                    <input type="radio" name="options" id="option2" autocomplete="off" onclick="getModifyType(6)">新闻
                  </label>
                  <label class="btn btn-default">
                    <input type="radio" name="options" id="option6" autocomplete="off">灌水
                  </label>-->
                  <label class="btn btn-default active" id="gonggaoManage1" onclick="getModifyType(6);">
                    	<a herf="#" >公告</a>
                  </label>
                  <label class="btn btn-default" id="xinwenManage1" onclick="getModifyType(2);">
                    <a herf="#" >新闻</a>
                  </label>
                  <label class="btn btn-default" id="guanshuiManage1"  onclick="getModifyType(1);">
                    <a herf="#">灌水</a>
                  </label>
                </div>
              </div>
              <div class="form-group">
                <label for="标题">标题：</label>
                <input type="email" class="form-control" id="modifyInputEmailTitileId">
              </div>
              <div class="form-group">
                <label for="正文">正文：</label>
                <textarea class="form-control" rows="3" id="modifyInputEmailContentId"></textarea>
              </div>
              <div class="form-group">
                <label for="添加照片">添加照片：</label>
                <div class="e_tForm_uploadBtn">
	                <form name="imageForm" enctype="multipart/form-data" action="<%=ctxPath%>/imageUpload.tv/0" method="post">
						<input value="上传" type="button">
						<input name="fileBox" class="fileBtn" onchange="uploadPrizeFile(this);" type="file">
					</form>
					<p class="help-block text-red small">照片大小不超过1M</p>
				</div>
              </div>
              <div class="form-group">
                <label for="预览">预览：</label>
                <div class="row">    
                  <div class="col-xs-12" id="pic_viewDiv">  
                  <!--    
                    <div class="alert alert-warning alert-dismissible pic-preview" role="alert">
                      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                      <img src="<%=ctxPath%>/resources/images/icon/nullImg.jpg" width="100%" alt="...">
                    </div>
                     --> 
                  </div>         
                </div> 
              </div>
              <div class="form-group" id="modifyGongGaoId">
                <label for="置顶">置顶：</label>
                <select class="form-control" style="width:50%;" id="modifyHeadlineSortId">
                  <option value="21" >首页-顶部</option>
                  <option value="22"  >首页头条2</option>
                  <option value="23">首页头条3</option>
                  <option value="24">首页头条4</option>
                </select>
              </div>
              <div class="form-group" id="modifyElseId" style="display: none">
                <label for="推广">推广：</label>
                <select class="form-control" style="width:50%;" id="modifyElseIdSortId">
                  <option value="1">首页-顶部</option>
                  <option value="2">首页-推荐2</option>
                  <option value="3">首页-推荐3</option>
                  <option value="4">首页-推荐4</option>
                  <option value="5">首页-推荐5</option>
                  <option value="6">首页-推荐6</option>
                </select>
              </div>
              <div class="checkbox">
                <label>
                  <input type="checkbox" id="islimitReplyIdmodify">禁止用户评论<span class="small text-red">（选中则此头条用户无法评论）</span>
                </label>
              </div>
              <p class="text-center"><button type="button" class="btn btn-primary" onclick="modifyHeadline()"><span class="glyphicon glyphicon-ok">&nbsp;</span>提交</button></p>

   
          </div>
          
          <div class="tab-pane" id="toutiao">
          <!--头条管理-->

           <p></p>
            <div class="panel panel-default">
              <div class="panel-body bg-info">
                  <div class="row">
                    <div class="col-xs-7">
                    <!--    <form class="form" role="form"> -->
          <div class="form-group">
                <label for="类别"></label>
                <div class="" data-toggle="buttons">
                  <label class="btn btn-default active" id="gonggaoManage" onclick="getType(6);">
                    	<a herf="#" >公告</a>
                  </label>
                  <label class="btn btn-default" id="xinwenManage" onclick="getType(2);">
                    <a herf="#" >新闻</a>
                  </label>
                  <label class="btn btn-default" id="guanshuiManage"  onclick="getType(1);">
                    <a herf="#">灌水</a>
                  </label>
                </div>
              </div>
             <!--   </form> -->
                    </div>
                    <div class="col-xs-5">
                  <form class="form-inline pull-right" role="form">
                          <div class="form-group">
                            <label class="sr-only" for="exampleInputPassword2">查询</label>
                            
                            <input type="text" class="form-control" id="exampleInputPassword2" placeholder="">
                            <select class="form-control" id="touManageQueryId">
                              <option value="1">按手机号</option>
                              <option value="2">按关键字</option>
                            </select>
                          </div>
                          <button type="button" class="btn btn-primary" onclick="inputQuery(1);">查询</button>
                       </form>
                     </div>
                  </div>
              </div>
            </div>
          <table  class="table table-hover">
              <thead>
                <tr>
                  <th width="80">
                  <input name="" type="checkbox" value="" id="touTiaoManageSelectId"/> 全选
                  </th>
                  <th>
                    <div class="pull-right"><input name="" type="checkbox" value="" id="isManagerId"/> 只看管理员</div>
                  </th>
                </tr>
              </thead>
             	<tbody id="toutiaoContentId">
             	
             	</tbody>
            </table>
            
      		<div id="toutiaoContentPageId"></div>
            
            <p class="text-center">
              <button type="button" class="btn btn-danger" onclick="toutiaoDel()"><span class="glyphicon glyphicon-remove">&nbsp;</span>批量删除</button>
              <!--<button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-ok">&nbsp;</span>提交</button>
              <button type="button" class="btn btn-success"><span class="glyphicon glyphicon-eye-open">&nbsp;</span>预览</button>-->
            </p>

          </div>
          
          <div class="tab-pane" id="pinglun">
          <!--评论管理-->
          <!--搜索-->
           <p></p>
            <div class="panel panel-default">
              <div class="panel-body bg-info">
                <form class="form-inline pull-right" role="form">
                      <div class="form-group">
                        <label class="sr-only" for="exampleInputPassword2">查询</label>
                        <input type="text" class="form-control" id="replyInputPassword2" placeholder="">
                        <select class="form-control" id="replyManageSelectId">
                              <option value="1">按手机号</option>
                              <option value="2">按关键字</option>
                        </select>
                        
                      </div>
                      <button type="button" class="btn btn-primary" onclick="inputQuery(2);">查询</button>
                    </form>
              </div>
            </div>
            <!--搜索结束-->
            <!--评论列表-->
            <table class="table table-striped table-hover">
              <thead>
                <tr>
                  <th width="80"><input name="" type="checkbox" value="" id="replyAllCheckedId"/> 全选</th>
                  <th>&nbsp;</th>
                </tr>
              </thead>
			  <tbody id="replyContentTableId"></tbody>

            </table>
            

            <div class="row" id="replyManageRowId">
            </div>
            
            <p class="text-center">
              <button type="button" class="btn btn-danger" onclick="replyListDel()"><span class="glyphicon glyphicon-remove">&nbsp;</span>批量删除</button>
              <!--<button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-ok">&nbsp;</span>提交</button>
              <button type="button" class="btn btn-success"><span class="glyphicon glyphicon-eye-open">&nbsp;</span>预览</button>-->
            </p>
            <!--评论列表结束-->
          </div>
          
          <div class="tab-pane" id="tuiguang">
          <!--现网推广-->
          这里应该是直接嵌入预览页面吧？
          </div>
          
          <div class="tab-pane" id="shanchu">
          
          <!--已删除管理-->
          <!--搜索-->
           <p></p>
            <div class="panel panel-default">
              <div class="panel-body bg-info">
                <form class="form-inline pull-right" role="form">
                      <div class="form-group">
                        <label class="sr-only" for="exampleInputPassword2">查询</label>
                        <input type="text" class="form-control" id="delExampleInputPassword2" placeholder="">
                        <select class="form-control" id="delManageAId">
                              <option value="1">搜头条</option>
                              <option value="2">搜评论</option>
                        </select>
                        <select class="form-control" id="delManageBId">
                              <option value="3">按手机号</option>
                              <option value="4">按关键字</option>
                        </select>
                        
                      </div>
                      <button type="button" class="btn btn-primary" onclick="inputQuery(3)">查询</button>
                    </form>
              </div>
            </div>
            <!--搜索结束-->
            <!--评论列表-->
            <table class="table table-striped table-hover" id="delManageTableId">
             
            </table>
            

            <div class="row" id="delManageRowId">
             
            </div>
            
            <!--<p class="text-center">
              <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-remove">&nbsp;</span>批量删除</button>
              <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-ok">&nbsp;</span>提交</button>
              <button type="button" class="btn btn-success"><span class="glyphicon glyphicon-eye-open">&nbsp;</span>预览</button>
            </p>-->
            <!--已删除列表结束-->
          </div>
          
        </div>
    </div>
    
  </div>
  
  <!-- Modal1 -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>
            <h4 class="modal-title" id="myModalLabel">拼桌详情</h4>
          </div>
          <div class="modal-body">
            <h4><span class="label label-info">主题：</span></h4>
            <div class="alert alert-info" role="alert">
              <div class="row">
                <div class="col-xs-2">
                  <img src="image/icon_qiangzhuo_yule.png" width="40" height="40" />
                </div>
                <div class="col-xs-10">
                  相约好汉8月9日去汉拿山腐败，寻爱吃肉的一起来加入哈，哈哈哈哈！
                </div>
              </div>
            </div>
            
            <h4><span class="label label-info">成员：</span></h4>
             <div class="alert alert-info" role="alert">
               
               <div class="row">
                 <div class="col-xs-2">
                   <img src="image/icon_user_default.png" width="40" height="40" />
                 </div>
                 <div class="col-xs-10">
                 
                   <table width="100%" border="0" cellspacing="1" cellpadding="1">
                      <tr>
                        <td>小咩<span class="text-red">（13811689019）</span></td>
                        <td>13811689019<span class="text-red">（13811689019）</span></td>
                      </tr>
                      <tr>
                        <td>小咩<span class="text-red">（13811689019）</span></td>
                        <td>13811689019<span class="text-red">（13811689019）</span></td>
                      </tr>
                      <tr>
                        <td>小咩<span class="text-red">（13811689019）</span></td>
                        <td>13811689019<span class="text-red">（13811689019）</span></td>
                      </tr>
                      <tr>
                        <td>小咩<span class="text-red">（13811689019）</span></td>
                        <td>13811689019<span class="text-red">（13811689019）</span></td>
                      </tr>
                      <tr>
                        <td>小咩<span class="text-red">（13811689019）</span></td>
                        <td>13811689019<span class="text-red">（13811689019）</span></td>
                      </tr>
                    </table>
                 </div>
               </div>
             </div>
              
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div>
      </div>
    </div> 
   
   
    <!-- Modal2 -->
    <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">	
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>
            <h4 class="modal-title" id="myModalLabel">审核驳回</h4>
          </div>
          <div class="modal-body">
            <textarea class="form-control" rows="3"></textarea>
          </div>
          <div class="modal-footer">
            <h6 class="pull-left">还能输入140个字</h6>
            <button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
            <button type="button" class="btn btn-danger" data-dismiss="modal">确定</button>
          </div>
        </div>
      </div>
    </div>
</body>
</html>
