<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<form id="detail_form" class="form-horizontal" style="margin-top: 15px;">
	<div class="form-group">
		<label class="col-sm-2 control-label">用户名称 <span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入用户名称" required id="name" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">初始密码 <span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入初始密码" required id="name" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">用户类型<span	class="asterisk">*</span></label>
		<div class="col-sm-9">
			<select class="select detail_search" data-placeholder="请选择用户类型" id="industry_type">
					<option value="0">电力用户</option>
					<option value="1">系统用户</option>
				</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">电力用户<span	class="asterisk">*</span></label>
		<div class="col-sm-9"> 
			<select class="select detail_search" data-placeholder="请选择电力用户" id="industry_type">
				<option value="0">石油化工</option>
				<option value="1">机械制造</option>
				<option value="2">电器设备</option>
			</select>
		</div>
	</div>
	<div class="form-group" style="text-align: center;">
		<button class="btn btn-primary">保存</button>
	    <button class="btn btn-default" type="button" style="margin-left: 15px;">取消</button>
	</div>	
</form>
<script type="text/javascript" src="<%=basePath%>static/js/setting/yhglDetail.js"></script>