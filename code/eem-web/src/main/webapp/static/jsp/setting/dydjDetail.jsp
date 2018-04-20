<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<form id="detail_form" class="form-horizontal" style="margin-top: 15px;">
	<div class="form-group">
		<label class="col-sm-2 control-label">名称 <span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<div class="col-sm-11">
				<input type="number" class="form-control" placeholder="请输入电压等级" required id="name"  maxlength="20"/>
			</div>
			<label class="col-sm-1 control-label" style="text-align: left;text-size: 14px;" id="unit">KV</label>
		</div>
		<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="name">
			最长20个字符
		</label>
	</div>
	<div class="form-group" style="text-align: center;">
		<button class="btn btn-primary">保存</button>
	    <button class="btn btn-default" type="button" style="margin-left: 15px;" id="cancel">取消</button>
	</div>	
</form>
<script type="text/javascript" src="<%=basePath%>static/js/setting/dydjDetail.js"></script>