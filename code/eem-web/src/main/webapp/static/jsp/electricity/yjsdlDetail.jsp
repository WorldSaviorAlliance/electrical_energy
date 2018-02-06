<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<form id="detail_form" class="form-horizontal" style="margin-top: 15px;">
	<div class="form-group">
		<label class="col-sm-2 control-label">电力用户名称 <span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入电力用户名称" required id="customer" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">用户户号<span
			class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入用户户号" required id="customer_no" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">电表编号<span
			class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入电表编号" required id="name" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">月份<span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<select class="select" data-placeholder="请选择月份" id="valid_year">
				<option value="apple">2022</option>
				<option value="orange">2022</option>
				<option value="grapes">2022</option>
				<option value="strawberry">2022</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">电压等级<span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<select class="select" data-placeholder="请选择电压等级" id="voltage_type">
				<option value="apple">110KV</option>
				<option value="orange">220KV</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">交易品种<span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<select class="select" data-placeholder="请选择交易品种" id="voltage_type">
				<option value="apple">常规直购电</option>
				<option value="orange">精准扶持直购电</option>
				<option value="orange">自备替代直购电</option>
				<option value="orange">富余直购电</option>				
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">高峰用电量<span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入高峰用电量" required id="trade_electric_quantity" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">平段用电量<span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入平段用电量" required id="normal_trade_price" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">低估用电量<span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入低估用电量" required id="replace_trade_price" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">无功电量<span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入无功电量" required id="normal_trade_price" />
		</div>
	</div>
	<div class="form-group" style="text-align: center;">
		<button class="btn btn-primary">保存</button>
	    <button class="btn btn-default" type="button" style="margin-left: 15px;">取消</button>
	</div>	
</form>
<script type="text/javascript" src="<%=basePath%>static/js/electricity/yjsdlDetail.js"></script>