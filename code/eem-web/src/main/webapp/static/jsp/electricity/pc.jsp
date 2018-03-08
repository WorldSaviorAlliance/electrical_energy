<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java"  pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<form class="form-horizontal">
	<div class="form-group">
		<div class="col-sm-12" style="padding: 0px;">
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-sm-2 control-label">月份</label>
	                  <div class="col-sm-4">
		                   <select class="select detail_search" data-placeholder="请选择开始月份" id="startTime">
							<option value="201801">201801</option>
							<option value="201802">201802</option>
							<option value="201803">201803</option>
							<option value="201804">201804</option>
							<option value="201805">201805</option>
						</select> 
					</div>
					<div class="col-sm-4">
		                   <select class="select detail_search" data-placeholder="请选择结束月份" id="endTime">
							<option value="201801">201801</option>
							<option value="201802">201802</option>
							<option value="201803">201803</option>
							<option value="201804">201804</option>
							<option value="201805">201805</option>
						</select> 
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-sm-2 control-label">电力用户</label>
					<div class="col-sm-7">
						<select class="select" data-placeholder="请选择电力用户" id="customerId">
						</select>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-sm-2 control-label">户名</label>
					<div class="col-sm-7">
						<select class="select detail_search" data-placeholder="请选择户名" id="customerNo">
						</select>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-sm-2 control-label">电压等级</label>
					<div class="col-sm-7">
						<select class="select" data-placeholder="请选择电压等级" id="voltageType">
						</select>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-sm-2 control-label">交易品种</label>
					<div class="col-sm-7">
						<select class="select detail_search" data-placeholder="请选择交易品种" id="tradeType">
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
<script type="text/javascript" src="<%=basePath%>static/js/electricity/pc.js"></script>
