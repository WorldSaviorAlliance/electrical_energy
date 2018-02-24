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
		<label class="col-sm-2 control-label">用户户号<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="text" class="form-control" placeholder="请输入用户户号" required id="customerNo" maxlength="30"/>
		</div>
		<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="customerNo">
			最长30个字符
		</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">售电合约名称<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<select class="select" data-placeholder="请选择售电合约名称" id="contractId">
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">合约编号</label>
		<div class="col-sm-7">
			<input type="text" class="form-control"  readonly id="contractNumber" />
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-2 control-label">合同有效年份</label>
		<div class="col-sm-7">
			<input type="text" class="form-control"  readonly id="validYear" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">电压等级</label>
		<div class="col-sm-7">
			<input type="text" class="form-control" readonly id="voltageType"/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">本次调整电量<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="number" class="form-control" placeholder="请输入本次调整电量" required id="quantity" />
		</div>
		<label class="col-sm-1 control-label" style="color: gray;text-align: left;">万kWh</label>
		<label class="col-sm-2 control-label input_msg" style="text-align: left;" for="quantity">
			必须大于0
		</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">调整类型<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<select class="select detail_search" data-placeholder="请选择调整类型" id="adjustmentType">
				<option value="0">调增</option>
				<option value="1">调减</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">交易价格<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="number" class="form-control" placeholder="请输入交易价格" required id="price" />
		</div>
		<label class="col-sm-1 control-label" style="color: gray;text-align: left;">厘/kWh</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">交易品种<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<select class="select detail_search" data-placeholder="请选择交易品种" id="tradeType">
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">调整月份<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<select class="select detail_search" data-placeholder="请选择调整月份" id="month">
				<option value="201801">201802</option>
				<option value="201802">201802</option>
			</select>
		</div>
	</div>
	<div class="form-group" style="text-align: center;">
		<button class="btn btn-primary">保存</button>
	    <button class="btn btn-default" type="button" style="margin-left: 15px;" id="cancel">取消</button>
	</div>	
</form>
<script type="text/javascript" src="<%=basePath%>static/js/contract/dltzDetail.js"></script>