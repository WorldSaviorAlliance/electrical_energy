<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@page import="com.warrior.eem.util.Constant"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<html>
<head>
<title><%=ToolUtil.getProjectName() %></title>
<link rel="shortcut icon" href="<%=basePath%>static/images/favicon.ico">
<link rel="stylesheet" href="<%=basePath%>static/js/bootstrap/css/bootstrap.min.css?version=<%= Constant.getCurTime()%>"/>
<link rel="stylesheet" href="<%=basePath%>static/js/font-awesome/css/font-awesome.min.css?version=<%= Constant.getCurTime()%>">
<link rel="stylesheet" href="<%=basePath%>static/css/login.css?version=<%= Constant.getCurTime()%>" />
</head>
<body style="min-width:  800px; overflow: auto;">
	<div id="login_area">
		<div id="login_form">
			<div id="login_tip">
				用户登录
			</div>
			<div><input type="text" class="username" id="username" placeholder="请输入账号"></div>
			<div><input type="password" class="pwd" id="password" placeholder="请输入密码"></div>
			<div id="btn_area">
				<input type="button" id="loginBtn" value="登&nbsp;&nbsp;录">
				<font id="wait_msg" style="display: none;"><i class="fa fa-spinner fa-pulse fa-2x fa-fw"></i>正在登录中，请稍候......</font>
				<font style="color:red;display: none;" id="error_msg"></font>
			</div>
		</div>
	</div>
<script type="text/javascript" src="<%=basePath%>static/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/bootstrap/js/bootstrap.min.js?version=<%= Constant.getCurTime()%>" ></script>
<script type="text/javascript" src="<%=basePath%>static/js/constants.js?version=<%= Constant.getCurTime()%>"></script>
<script type="text/javascript" src="<%=basePath%>static/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/base.js?version=<%= Constant.getCurTime()%>"></script>
<script type="text/javascript" src="<%=basePath%>static/js/login.js?version=<%= Constant.getCurTime()%>"></script>
</body>
</html>
