<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<form id="detail_form" class="form-horizontal" style="margin-top: 15px;">
	<div class="form-group">
		<label class="col-sm-2 control-label">套餐<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<select class="select" data-placeholder="请选择套餐" id="pkgId">
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">详细介绍</label>
		<div class="col-sm-7">
			<span id="detail"></span>
		</div>
	</div>
	<div class="form-group" style="text-align: center;">
		<button class="btn btn-primary">保存</button>
	    <button class="btn btn-default" type="button" style="margin-left: 15px;">取消</button>
	</div>	
</form>
<script type="text/javascript" src="<%=basePath%>static/js/user/wyblDetail.js"></script>