<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<form id="detail_form" class="form-horizontal" style="margin-top: 15px;">
	<div class="form-group">
		<label class="col-sm-2 control-label">合约名称<span	class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="text" class="form-control" readonly="readonly" id="name" />
			<input id="normalTradePrice" style="display: none;"/>
			<input id="supportTradePrice" style="display: none;"/>
			<input id="replaceTradePrice" style="display: none;"/>
			<input id="marginTradePrice" style="display: none;"/>
		</div>
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
		<button class="btn btn-primary">保存</button>
	    <button class="btn btn-default" type="button" style="margin-left: 15px;" id="cancel">取消</button>
	</div>	
</form>
<script type="text/javascript" src="<%=basePath%>static/js/jquery/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/control/month2Dl.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/contract/sddjDetail.js"></script>