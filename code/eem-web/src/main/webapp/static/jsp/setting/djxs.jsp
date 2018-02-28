<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java"  pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<h4 class="page-title detail_sub_title">当前位置：<span>电源商管理</span> / <span >电源商列表</span></h4>
<div class="divider" style="margin-top: 6px;margin-bottom: 6px;"></div>
<form id="detail_form" class="form-horizontal" style="margin-top: 15px;">
	<div class="form-group">
		<label class="col-sm-2 control-label">平段电价系数 <span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="text" class="form-control" placeholder="请输入平段电价系数" required value="1" readonly="readonly" id="flat"/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">高峰电价系数 <span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="number" class="form-control" placeholder="请输入高峰电价系数" required value="1" id="peak" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">低估电价系数 <span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="number" class="form-control" placeholder="请输入低估电价系数" required value="1" id="trough" />
		</div>
	</div>
	<div class="form-group" style="text-align: center;">
		<button class="btn btn-primary">保存</button>
	</div>	
</form>
<script type="text/javascript" src="<%=basePath%>static/js/setting/djxs.js"></script>
