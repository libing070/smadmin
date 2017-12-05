<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>北京移动wifi分发管理平台</title>
<%@include file="common/include.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/style/bootstrap.min.css"/>

<style type="text/css">
.indexbg{
	background-color: #edeeee;
}
body{
	font-family: "微软雅黑", sans-serif, Arial;
}
</style>

<script type="text/javascript">
function login(){
    if($("input[name='account']").val()=="" || $("input[name='password']").val()==""){
        $("#showMsg").html("用户名或密码为空，请输入");
        $("input[name='account']").focus();
    }else{
        $.post("<%=basePath%>/login",
				{account:$("#account").val(),password:$("#password").val()},
          		function(data){
          			if(data.result=="no"){
          				$("#error_msg").html(data.message);  
          			}else{              			           				
          				window.parent.location.href= "<%=basePath%>/main.tv";
					}
				}
			);
    }
}

$(document).keyup(function(event){
  if(event.keyCode ==13){
    $("#submitButton").trigger("click");
  }
});
</script>
</head>

<body class="indexbg">
<br />
<br />
<table width="90%" border="0" cellpadding="1" cellspacing="1">
  <tr>
    <td width="50%" align="center"><img src="<%=ctxPath%>/resources/images/index_pic.png" width="500" /></td>
    <td width="50%">
      <div class="panel panel-primary">
          <div class="panel-heading">
            <h3 class="panel-title "><span class="glyphicon glyphicon-user">&nbsp;</span>用户登录</h3>
          </div>
          
          <div class="panel-body">
            <form class="form-horizontal" role="form">
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" name="account" id="account" placeholder="请输入用户名">
                </div>
              </div>
              <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                <div class="col-sm-10">
                  <input type="password" class="form-control" name="password" id="password"  placeholder="请输入密码">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                  <button type="button" class="btn btn-danger" id="submitButton" style="width:100%;" onclick="javascript:login();">立即登录</button>
                </div>
              </div>
              <div class="form-group">
				<div class="col-sm-offset-2 col-sm-10" name="error_msg" id="error_msg" style="font-size: 12px; color: red; font-weight:bold;"></div>
			 </div>
            </form>
          </div>
      </div>
    </td>
  </tr>
</table>

</body>
</html>
