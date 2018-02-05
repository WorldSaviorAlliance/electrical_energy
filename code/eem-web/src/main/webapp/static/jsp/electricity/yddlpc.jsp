<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java"  pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<h4 class="page-title detail_sub_title">当前位置：<span>电量管理</span> / <span >月度电量偏差</span></h4>
<div class="divider" style="margin-top: 6px;margin-bottom: 6px;"></div>
<form class="form-horizontal">
	<div class="form-group">
		<div class="col-sm-12" style="padding: 0px;">
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-sm-2 control-label">电力用户<span class="asterisk">*</span></label>
					<div class="col-sm-9">
						<select class="select" data-placeholder="请选择电力用户" id="valid_year">
							<option value="apple">2022</option>
							<option value="orange">2022</option>
							<option value="grapes">2022</option>
							<option value="strawberry">2022</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-sm-2 control-label">月份</label>
	                  <div class="col-sm-4">
		                   <select class="select" data-placeholder="请选择开始月份" id="start_month">
							<option value="">201801</option>
							<option value="apple">201802</option>
							<option value="orange">201803</option>
							<option value="grapes">201804</option>
							<option value="strawberry">201805</option>
						</select> 
					</div>
					<div class="col-sm-4">
		                   <select class="select" data-placeholder="请选择结束月份" id="end_month">
							<option value="">201801</option>
							<option value="apple">201802</option>
							<option value="orange">201803</option>
							<option value="grapes">201804</option>
							<option value="strawberry">201805</option>
						</select> 
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-sm-2 control-label">户名<span class="asterisk">*</span></label>
					<div class="col-sm-9">
						<select class="select" data-placeholder="请选择户名" id="valid_year">
							<option value="apple">2022</option>
							<option value="orange">2022</option>
							<option value="grapes">2022</option>
							<option value="strawberry">2022</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-sm-2 control-label">电压等级<span class="asterisk">*</span></label>
					<div class="col-sm-9">
						<select class="select" data-placeholder="请选择电压等级" id="valid_year">
							<option value="apple">2022</option>
							<option value="orange">2022</option>
							<option value="grapes">2022</option>
							<option value="strawberry">2022</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-sm-2 control-label">交易品种<span class="asterisk">*</span></label>
					<div class="col-sm-9">
						<select class="select" data-placeholder="请选择交易品种" id="valid_year">
							<option value="apple">2022</option>
							<option value="orange">2022</option>
							<option value="grapes">2022</option>
							<option value="strawberry">2022</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-md-2">
				<button class="btn btn-primary" id="search" type="button">查询</button>
			</div>
		</div>
		<div class="col-sm-12" style="padding: 0px;">
			<div id="chart" style="width: 100%; height: 380px;" data-highcharts-chart="0">
			</div>
		</div>
	</div>
</form>
<script type="text/javascript" src="<%=basePath%>static/js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/electricity/yddlpc.js"></script>
