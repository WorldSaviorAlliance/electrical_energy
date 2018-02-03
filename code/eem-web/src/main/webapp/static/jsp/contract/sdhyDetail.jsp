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
		<label class="col-sm-2 control-label">合约名称<span
			class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入合约名称" required id="name" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">合约编号<span
			class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入合约编号" required id="no" />
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-2 control-label">合同有效年份<span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<select class="select" data-placeholder="请选择合同有效年份" id="valid_year">
				<option value="apple">2022</option>
				<option value="orange">2022</option>
				<option value="grapes">2022</option>
				<option value="strawberry">2022</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">合同附件<span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control box" id="file_path" style="margin-top: -1px;" readonly="readonly">
			
		</div>
		<div class="col-sm-1">
			<a href="javascript:;" class="a-upload">
			    <input type="file" id="licenseFile" name="licenseFile">浏览
			</a>
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
		<label class="col-sm-2 control-label">交易电量<span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入交易电量" required id="trade_electric_quantity" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">常规直购电量交易价格<span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入常规直购电量交易价格" required id="normal_trade_price" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">精准扶持直购电量交易价格<span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入精准扶持直购电量交易价格" required id="support_trade_price" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">自备替代直购电交易价格<span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入自备替代直购电交易价格" required id="replace_trade_price" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">富于电量交易价格<span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入富于电量交易价格" required id="margin_trade_price" />
		</div>
	</div>
	<div class="form-group" style="text-align: center;">
		<button class="btn btn-primary">保存</button>
	    <button class="btn btn-default" type="button">取消</button>
	</div>	
</form>
<script type="text/javascript" src="<%=basePath%>static/js/contract/sdhyDetail.js"></script>