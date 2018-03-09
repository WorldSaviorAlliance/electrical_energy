<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<form id="detail_form" class="form-horizontal" style="margin-top: 15px;">
	<div class="form-group">
		<label class="col-sm-2 control-label">用户名称 <span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="text" class="form-control" placeholder="请输入用户名称" required id="name"  maxlength="20" minlength="2"/>
		</div>
		<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="name">
			长度范围2到20个字符
		</label>
	</div>
	<div class="form-group" style="display: none;" id="pasword_div">
		<label class="col-sm-2 control-label">初始密码 <span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="password" class="form-control" placeholder="请输入初始密码" required id="password"  maxlength="12" minlength="6"/>
		</div>
		<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="password">
			长度范围6到12个字符
		</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">用户类型<span	class="asterisk">*</span></label>
		<div class="col-sm-7">
			<select class="select detail_search" data-placeholder="请选择用户类型" id="userType">
				<option value="0">电力用户</option>
				<option value="1">系统用户</option>
			</select>
		</div>
	</div>
	<div class="form-group" id="dlyhDiv">
		<label class="col-sm-2 control-label">电力用户<span	class="asterisk">*</span></label>
		<div class="col-sm-7"> 
			<select class="select" data-placeholder="请选择电力用户" id="customerId">
			</select>
		</div>
	</div>
	<div class="form-group" style="display: none" id="roleDiv">
		<label class="col-sm-2 control-label">角色<span	class="asterisk">*</span></label>
		<div class="col-sm-7"> 
			<select class="select" data-placeholder="请选择角色" id="roleId">
			</select>
		</div>
	</div>
	<div class="form-group" style="text-align: center;">
		<button class="btn btn-primary">保存</button>
	    <button class="btn btn-default" type="button" style="margin-left: 15px;" id="cancel">取消</button>
	</div>	
</form>
<script type="text/javascript" src="<%=basePath%>static/js/setting/yhglDetail.js"></script>