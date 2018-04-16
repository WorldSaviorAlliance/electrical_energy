<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<form id="detail_form" enctype="multipart/form-data" class="form-horizontal" style="margin-top: 15px;">
	<div class="form-group">
		<label class="col-sm-2 control-label">电力用户名称<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<select class="select" data-placeholder="请选择电力用户名称" id="customerId">
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">用户户号<span
			class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="text" class="form-control" placeholder="请输入用户户号" required id="customerNo" maxlength="30" />
		</div>
		<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="customerNo">
			最长30个字符
		</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">合约名称<span
			class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="text" class="form-control" placeholder="请输入合约名称" required id="name"  maxlength="30"/>
		</div>
		<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="name">
			最长30个字符
		</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">合约编号<span
			class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="text" class="form-control" placeholder="请输入合约编号" required id="no" maxlength="30"/>
		</div>
		<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="no">
			最长30个字符
		</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">合同附件<span class="asterisk">*</span></label>
		<div class="col-sm-7" style="padding-right: 0px;">
			<input type="text" class="form-control" id="file_path" style="margin-top: -1px;border-top-right-radius: 3px;border-bottom-right-radius: 3p" required>
		</div>
		<div class="col-sm-1" style="padding-left: 0px;">
			<a href="javascript:;" class="file_upload">
			    <input type="file" id="att_file" name="att_file" accept=".xls,.xlsx,.doc,.docx">浏览
			</a>
		</div>
		<label class="col-sm-2 control-label input_msg" style="text-align: left;display: block;">
			只支持doc、docx和excel文件
		</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">合同有效年份<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<select class="select detail_search" data-placeholder="请选择合同有效年份" id="validYear">
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
		<label class="col-sm-2 control-label">交易电量<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="number" class="form-control" placeholder="请输入交易电量" required id="tradePowerQuantity" />
		</div>
		<label class="col-sm-1 control-label" style="color: gray;text-align: left;">万kWh</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">常规直购电量交易价格<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="number" class="form-control" placeholder="请输入常规直购电量交易价格" required id="normalTradePrice" />
		</div>
		<label class="col-sm-1 control-label" style="color: gray;text-align: left;">厘/kWh</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">精准扶持直购电量交易价格<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="number" class="form-control" placeholder="请输入精准扶持直购电量交易价格" required id="supportTradePrice" />
		</div>
		<label class="col-sm-1 control-label" style="color: gray;text-align: left;">厘/kWh</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">自备替代直购电交易价格<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="number" class="form-control" placeholder="请输入自备替代直购电交易价格" required id="replaceTradePrice" />
		</div>
		<label class="col-sm-1 control-label" style="color: gray;text-align: left;">厘/kWh</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">富余电量交易价格<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="number" class="form-control" placeholder="请输入富于电量交易价格" required id="marginTradePrice" />
		</div>
		<label class="col-sm-1 control-label" style="color: gray;text-align: left;">厘/kWh</label>
	</div>
	<div class="form-group" style="margin-bottom: 0px;">
		<label class="col-sm-12 control-label" style="color: gray;text-align: left;">每个月不同类型的电量至少有一个大于0</label>
	</div>
	<div class="form-group">
		<table class="table table-striped table-hover" style="margin-bottom:10px;border: 1px solid #ddd;background-color: #FFFFFF;">
			<thead>
				<tr>
					<th>月份</th>
					<th>交易总量</th>
					<th>常规直购电交易电量</th>
					<th>精准扶持直购电交易电量</th>
					<th>自备替代直购电交易电量</th>
					<th>富于电量</th>
				</tr>
			</thead>
			<tbody id="dl_datas">
			</tbody>
		</table>
	</div>
	<div class="form-group" style="margin-bottom: 0px;">
		<label class="col-sm-12 control-label" style="color: gray;text-align: left;">每个月不同类型的电价至少有一个大于0</label>
	</div>
	<div class="form-group">
		<table class="table table-striped table-hover" style="margin-bottom:10px;border: 1px solid #ddd;background-color: #FFFFFF;">
			<thead>
				<tr>
					<th>月份</th>
					<th>常规直购电交易电价</th>
					<th>精准扶持直购电交易电价</th>
					<th>自备替代直购电交易电价</th>
					<th>富于电价</th>
				</tr>
			</thead>
			<tbody id="dj_datas">				
			</tbody>
		</table>
	</div>
	<div class="form-group" style="text-align: center;">
		<button class="btn btn-primary" id="btn_save">保存</button>
	    <button class="btn btn-default" type="button" style="margin-left: 15px;" id="cancel">取消</button>
	</div>	
</form>
<script type="text/javascript" src="<%=basePath%>static/js/jquery/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/control/month2Dl.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/contract/sdhyDetail.js"></script>