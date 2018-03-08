<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java"  pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<style>
.main {
  width: 1080px;
  height: 140px;
  background: rgba(255, 255, 255, 0.5);
  position: absolute;
  left: 50%;
  top: 50%;
  margin-left: -540px;
  margin-top: -70px;
  border-radius: 20px;
  line-height: 140px;
  text-align: center;
  font-size: 40px;
}
.page-content{
	padding: 0px;
	
}
</style>
<div style="width: 100%;height: 90%;background: url(<%=basePath%>static/images/back.jpg) 100% 100% no-repeat;">
    <div class="main">
        欢迎登录售电业务管理平台
    </div>
</div>