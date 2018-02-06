<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java"  pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<h4 class="page-title detail_sub_title">当前位置：<span>电量管理</span> / <span >电量月度清算列表</span></h4>
<div class="divider" style="margin-top: 6px;margin-bottom: 6px;"></div>
<form class="form-horizontal">
	<div class="form-group">
		<div class="col-sm-12" style="padding: 0px;">
			<div class="col-md-3">
				<div class="form-group">
                  <label class="col-sm-4 control-label">电力用户名称</label>
                  <div class="col-sm-8">
                   <input type="text" class="form-control" id="search_name">
                  </div>
                </div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-sm-2 control-label">月份</label>
	                  <div class="col-sm-4">
		                   <select class="select" data-placeholder="请选择开始月份" id="search_start_month">
							<option value="">201801</option>
							<option value="apple">201802</option>
							<option value="orange">201803</option>
							<option value="grapes">201804</option>
							<option value="strawberry">201805</option>
						</select> 
					</div>
					<div class="col-sm-4">
		                   <select class="select" data-placeholder="请选择结束月份" id="search_end_month">
							<option value="">201801</option>
							<option value="apple">201802</option>
							<option value="orange">201803</option>
							<option value="grapes">201804</option>
							<option value="strawberry">201805</option>
						</select> 
					</div>
				</div>
			</div>
			<div class="col-md-2">
				<button class="btn btn-primary" id="search" type="button">查询</button>
			</div>
		</div>
		<div class="col-sm-12" style="padding: 0px;">
			<table class="table table-striped table-hover" style="margin-bottom:10px;border: 1px solid #ddd;background-color: #FFFFFF;">
				<thead>
					<tr>
						<th>电力用户名称</th>
						<th>用户户名</th>
						<th>电表编号</th>
						<th>月份</th>
						<th>电压等级</th>
						<th>交易品种</th>
						<th>高峰用电量</th>
						<th>平段用电量</th>
						<th>低谷用电量</th>
						<th>总计有功电量</th>
						<th>无功电量</th>
						<th>录入时间</th>
					</tr>
				</thead>				
				<tbody id="datas">
					<tr>
						<td>测试电源商1</td>
						<td>电源商1</td>
						<td>四川成都</td>
						<td>火电</td>
						<td>2300万kWh</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>张三</td>
						<td>test@tttt.com</td>
						<td>2017-04-17 15:11:05</td>
					</tr>
					<tr>
						<td>测试电源商1</td>
						<td>电源商1</td>
						<td>四川成都</td>
						<td>火电</td>
						<td>2300万kWh</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>张三</td>
						<td>test@tttt.com</td>
						<td>2017-04-17 15:11:05</td>
					</tr>
					<tr>
						<td>测试电源商1</td>
						<td>电源商1</td>
						<td>四川成都</td>
						<td>火电</td>
						<td>2300万kWh</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>张三</td>
						<td>test@tttt.com</td>
						<td>2017-04-17 15:11:05</td>
					</tr>
					<tr>
						<td>测试电源商1</td>
						<td>电源商1</td>
						<td>四川成都</td>
						<td>火电</td>
						<td>2300万kWh</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>张三</td>
						<td>test@tttt.com</td>
						<td>2017-04-17 15:11:05</td>
					</tr>
					<tr>
						<td>测试电源商1</td>
						<td>电源商1</td>
						<td>四川成都</td>
						<td>火电</td>
						<td>2300万kWh</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>张三</td>
						<td>test@tttt.com</td>
						<td>2017-04-17 15:11:05</td>
					</tr>
					<tr>
						<td>测试电源商1</td>
						<td>电源商1</td>
						<td>四川成都</td>
						<td>火电</td>
						<td>2300万kWh</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>张三</td>
						<td>test@tttt.com</td>
						<td>2017-04-17 15:11:05</td>
					</tr>
					<tr>
						<td>测试电源商1</td>
						<td>电源商1</td>
						<td>四川成都</td>
						<td>火电</td>
						<td>2300万kWh</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>张三</td>
						<td>test@tttt.com</td>
						<td>2017-04-17 15:11:05</td>
					</tr>
				</tbody>
			</table>
			</div>
			<div id="page" class="col-sm-12" style="padding: 0px;">
			</div>	
		</div>
</form>

<script type="text/javascript" src="<%=basePath%>static/js/electricity/dlydqs.js"></script>
