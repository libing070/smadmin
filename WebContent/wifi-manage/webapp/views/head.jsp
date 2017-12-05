<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Cache-Control" content="no-cache" />
<meta name="description" content="">
<meta name="author" content="">

<title>北京移动wifi分发管理平台</title>
<%@include file="common/include.jsp"%>
<script type="text/javascript">
function logout(){
	window.parent.location.href= "<%=basePath%>/logout";
}
</script>
</head>

<body class="bg-primary">
  <div class="container-fluid">
    <div class="header">
        <ul class="nav nav-pills pull-right" role="tablist">
          <li role="presentation"><button type="button" class="btn btn-warning" onclick="logout();">退出</button></li>
        </ul>
        <h3><img src="<%=ctxPath%>/resources/images/index_logo.png" width="60" align="absmiddle"  /> 北京移动WIFI分发管理平台</h3>
      </div>
  </div>
</body>
</html>
