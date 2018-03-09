<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<form id="detail_form" class="form-horizontal" style="margin-top: 15px;">
	<div class="form-group">
		<label class="col-sm-2 control-label">旧密码 <span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="password" class="form-control" placeholder="请输入旧密码" required id="oldpassword" maxlength="12"/>
		</div>
		<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="oldpassword">
			长度范围6到12个字符
		</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">新密码 <span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="password" class="form-control" placeholder="请输入新密码" required id="newpassword" maxlength="12">
		</div>
		<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="newpassword">
			长度范围6到12个字符
		</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">确认密码 <span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="password" class="form-control" placeholder="请输入确认密码" required id="confirmpassword" maxlength="12"/>
		</div>
		<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="confirmpassword">
			长度范围6到12个字符
		</label>
	</div>
	<div class="form-group" style="text-align: center;">
		<button class="btn btn-primary">保存</button>
	    <button class="btn btn-default" type="button" style="margin-left: 15px;" id="cancel">取消</button>
	</div>	
</form>
<script type="text/javascript" src="<%=basePath%>static/js/setting/changepsw.js"></script>