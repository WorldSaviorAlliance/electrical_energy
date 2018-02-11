<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java"  pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>

<form id="detail_form" class="form-horizontal" style="margin-top: 15px;">
	<div class="form-group" style="text-align: center;">
		<label id="jy_error" style="color:red;"></label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">电力用户名称<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<select class="select" data-placeholder="请选择电力用户名称" id="all_dlyh">
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">交易电量<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="text" class="form-control" placeholder="请输入交易电量" required id="jy_price" maxlength="20"/>
		</div>
		<label class="col-sm-1 control-label" style="color: gray;text-align: left;">万kWh</label>
	</div>	
</form>
<script type="text/javascript" src="<%=basePath%>static/js/contract/jydl.js"></script>