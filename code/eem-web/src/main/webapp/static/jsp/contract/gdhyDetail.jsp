<%@page import="com.warrior.eem.util.ToolUtil"%>
<!-- 联系人详细的模板 -->
<%@ page language="java"  pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>

<form id="detail_form" class="form-horizontal" style="margin-top: 15px;">
	<div class="form-group">
		<label class="col-sm-2 control-label">电源商名称 <span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入电源商名称" required id="customer" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">合约名称<span
			class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入合约名称" required id="customer_no" />
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
			<select class="select detail_search" data-placeholder="请选择合同有效年份" id="valid_year">
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
			<select class="select detail_search" data-placeholder="请选择电压等级" id="voltage_type">
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
		<label class="col-sm-2 control-label">交易品种<span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<select class="select detail_search" data-placeholder="请选择交易品种" id="voltage_type">
				<option value="apple">常规直购电</option>
				<option value="orange">精准扶持直购电</option>
				<option value="orange">自备替代直购电</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">交易价格<span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入交易价格" required id="normal_trade_price" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">电力用户名称</label>
		<div class="col-sm-3">
			<select class="select detail_search" data-placeholder="请选择电力用户名称" id="voltage_type">
				<option value="apple">张三</option>
				<option value="orange">王五</option>
				<option value="orange">李四</option>
			</select>
		</div>
		<label class="col-sm-2 control-label">交易电量</label>
		<div class="col-sm-3">
			<input type="text" class="form-control" placeholder="请输入交易电量" required id="normal_trade_price" />
		</div>
		<div class="col-sm-2">
			<button class="btn btn-primary">添加</button>
		</div>
	</div>
	<div class="form-group">
		<table class="table table-striped table-hover"
			style="margin-bottom: 10px; border: 1px solid #ddd; background-color: #FFFFFF;">
			<thead>
				<tr>
					<th>电力用户名称</th>
					<th>交易电量</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="jy_datas">
				<tr>
					<td>张三</td>
					<td>111</td>
					<td><a class="btn btn-danger btn-xs" flag="del">删除</a></td>
				</tr>
				<tr>
					<td>张三</td>
					<td>111</td>
					<td><a class="btn btn-danger btn-xs" flag="del">删除</a></td>
				</tr>
				<tr>
					<td>张三</td>
					<td>111</td>
					<td><a class="btn btn-danger btn-xs" flag="del">删除</a></td>
				</tr>
				<tr>
					<td>张三</td>
					<td>111</td>
					<td><a class="btn btn-danger btn-xs" flag="del">删除</a></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="form-group" style="text-align: center;">
		<button class="btn btn-primary">保存</button>
	    <button class="btn btn-default" type="button" style="margin-left: 15px;">取消</button>
	</div>	
</form>
<script type="text/javascript" src="<%=basePath%>static/js/contract/gdhyDetail.js"></script>