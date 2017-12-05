<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<%@ include file="common/include.jsp"%>
    <title>北京移动wifi分发管理平台</title>
</head>

<body>
  <div class="container-fluid">
    <div class="row">
      <div class="col-md-12">
        <h4>
          <ol class="breadcrumb">
            <li><a href="#"><span class="glyphicon glyphicon-th-large">&nbsp;</span>活动管理</a></li>
            <li class="active">校园迎新</li>
            <label class="pull-right"><button type="button" class="btn btn-xs btn-default" onclick="window.location.href='guanli_huodong.html'"><span class="glyphicon glyphicon-arrow-left">&nbsp;</span>返回</button></label>
          </ol>
        </h4>
      </div>
    </div>
    
    <!--搜索-->
           <p></p>
            <div class="panel panel-default">
              <div class="panel-body bg-info">
                <form class="form-inline pull-right" role="form">
                      <div class="form-group">
                        <label class="sr-only" for="exampleInputPassword2">请输入手机号或昵称</label>
                        <input type="password" class="form-control" id="exampleInputPassword2" placeholder="请输入手机号或昵称">
                      </div>
                      <button type="submit" class="btn btn-primary">查询</button>
                    </form>
              </div>
            </div>
            
    <div class="table-responsive">

      <table class="table table-hover table-striped">
          <thead>
            <tr>
              <th><input name="" type="checkbox" value="" /> 全选</th>
              <th>报名表</th>
              <th>用户名</th>
              <th>提交时间</th>
              <th>状态</th>
              <th>操作</th>
            </tr>
          </thead>
          <tr>
            <td><input name="" type="checkbox" value="" /></td>
            <td><a href="#" data-toggle="modal" data-target="#myModal"><img src="image/pic_photo01.jpg" width="60" height="60" /></a></td>
            <td>我是筱爷（13811689019）</td>
            <td>2014-09-01 09:05</td>
            <td><label class="text-danger">已驳回</label></td>
            <td>
              <div class="btn-group">
                  <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-ok">&nbsp;</span>通过</button>
                  <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal2"><span class="glyphicon glyphicon-remove">&nbsp;</span>驳回</button>
              </div>
            </td>
          </tr>
          <tr>
            <td><input name="" type="checkbox" value="" /></td>
            <td><a href="#" data-toggle="modal" data-target="#myModal"><img src="image/pic_photo01.jpg" width="60" height="60" /></a></td>
            <td>我是筱爷（13811689019）</td>
            <td>2014-09-01 09:05</td>
            <td><label class="text-danger">已通过</label></td>
            <td>
              <!--<div class="btn-group">
                  <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-ok">&nbsp;</span>通过</button>
                  <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-remove">&nbsp;</span>驳回</button>
              </div>-->
            </td>
          </tr>
          <tr>
            <td><input name="" type="checkbox" value="" /></td>
            <td><a href="#" data-toggle="modal" data-target="#myModal"><img src="image/pic_photo01.jpg" width="60" height="60" /></a></td>
            <td>我是筱爷（13811689019）</td>
            <td>2014-09-01 09:05</td>
            <td><label class="text-danger">待审核</label></td>
            <td>
              <div class="btn-group">
                  <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-ok">&nbsp;</span>通过</button>
                  <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal2"><span class="glyphicon glyphicon-remove">&nbsp;</span>驳回</button>
              </div>
            </td>
          </tr>
        </table>

    </div>
    
    <div class="row">
      <div class="col-xs-4">
       <h6>当前为第1页，共12页</h6>
      </div>
      <div class="col-xs-8">
        <ul class="pagination pagination-sm pull-right">
          <li class="disabled"><a href="#">&laquo;</a></li>
          <li class="active"><a href="#">1</a></li>
          <li><a href="#">2</a></li>
          <li><a href="#">3</a></li>
          <li><a href="#">4</a></li>
          <li><a href="#">5</a></li>
          <li><a href="#">&raquo;</a></li>
        </ul>
      </div>
    </div>
    
    <div class="text-center"><button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-ok">&nbsp;</span>批量通过</button></div>
  
  </div>
  
    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>
            <h4 class="modal-title" id="myModalLabel">报名表详情</h4>
          </div>
          <div class="modal-body">
            <img src="image/pic_photo01.jpg" />
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
