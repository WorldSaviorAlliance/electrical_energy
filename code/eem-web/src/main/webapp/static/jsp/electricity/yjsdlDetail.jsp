<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<form id="detail_form" class="form-horizontal" style="margin-top: 15px;">
	<div class="form-group">
		<label class="col-sm-2 control-label">电力用户名称<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<select class="select" data-placeholder="请选择电力用户名称" id="customerId">
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">用户户号<span	class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="text" class="form-control" placeholder="请输入用户户号" required id="customerNo" maxlength="20"/>
		</div>
		<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="customerNo">
			最长30个字符
		</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">电表编号<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="text" class="form-control" placeholder="请输入电表编号" required id="emNo"  maxlength="20"/>
		</div>
		<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="emNo">
			最长30个字符
		</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">月份<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<select class="select detail_search" data-placeholder="请选择月份" id="month">
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">电压等级<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<select class="select" data-placeholder="请选择电压等级" id="voltageType">
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">交易品种<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<select class="select detail_search" data-placeholder="请选择交易品种" id="tradeType">
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">高峰用电量<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="number" class="form-control" placeholder="请输入高峰用电量" required id="peakKwh" min="0.0001"/>
		</div>
		<label class="col-sm-1 control-label" style="color: gray;text-align: left;">万kWh</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">平段用电量<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="number" class="form-control" placeholder="请输入平段用电量" required id="flatKwh" min="0.0001"/>
		</div>
		<label class="col-sm-1 control-label" style="color: gray;text-align: left;">万kWh</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">低谷用电量<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="number" class="form-control" placeholder="请输入低谷用电量" required id="troughKwh" min="0.0001"/>
		</div>
		<label class="col-sm-1 control-label" style="color: gray;text-align: left;">万kWh</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">无功电量<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="number" class="form-control" placeholder="请输入无功电量" required id="idleKwh" min="0"/>
		</div>
		<label class="col-sm-1 control-label" style="color: gray;text-align: left;">万kWh</label>
	</div>
	<div class="form-group" style="text-align: center;">
		<button class="btn btn-primary">保存</button>
	    <button class="btn btn-default" type="button" style="margin-left: 15px;" id="cancel">取消</button>
	</div>	
</form>
<script type="text/javascript" src="<%=basePath%>static/js/electricity/yjsdlDetail.js"></script>