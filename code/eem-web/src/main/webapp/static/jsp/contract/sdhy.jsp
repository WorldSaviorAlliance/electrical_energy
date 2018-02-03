<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java"  pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<h4 class="page-title detail_sub_title">当前位置：<span >合约管理</span> / <span >售电合约</span></h4>
<div class="divider" style="margin-top: 6px;margin-bottom: 6px;"></div>
<form class="form-horizontal">
	<div class="form-group">
		<div class="col-sm-12" style="padding: 0px;">
			<div class="col-md-3">
				<div class="form-group">
                  <label class="col-sm-3 control-label">名称</label>
                  <div class="col-sm-9">
                   <input type="text" class="form-control" id="name">
                  </div>
                </div>
			</div>
			
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-sm-2 control-label">年份</label>
	                  <div class="col-sm-10">
		                   <select class="select" data-placeholder="请选择年份" id="year">
							<option value="">2018</option>
							<option value="apple">2018</option>
							<option value="orange">2018</option>
							<option value="grapes">2018</option>
							<option value="strawberry">2018</option>
						</select> 
					</div>
				</div>
			</div>
			<div class="col-md-2">
				<button class="btn btn-primary" id="search" type="button">查询</button>
				<button class="btn btn-info" id="add" type="button">添加</button>
			</div>
		</div>
		<div class="col-sm-12" style="padding: 0px;">
			<table class="table table-striped table-hover" style="margin-bottom:10px;border: 1px solid #ddd;background-color: #FFFFFF;">
				<thead>
					<tr>
						<th>电力用户名称</th>
						<th>用户户号</th>
						<th>合约名称</th>
						<th>合约编号</th>
						<th>年份</th>
						<th>交易电量</th>
						<th>电压等级</th>
						<th>合同附件</th>
						<th>录入时间</th>
						<th>操作</th>
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
						<td>test@tttt.com</td>
						<td>2017-04-17 15:11:05</td>
						<td>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify">修改合约</a>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify_p">修改电价</a>
							<a class="btn btn-danger btn-xs" flag="del">删除</a>
						</td>
					</tr>
					<tr>
						<td>测试电源商1</td>
						<td>电源商1</td>
						<td>四川成都</td>
						<td>火电</td>
						<td>2300万kWh</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>test@tttt.com</td>
						<td>2017-04-17 15:11:05</td>
						<td>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify">修改合约</a>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify_p">修改电价</a>
							<a class="btn btn-danger btn-xs" flag="del">删除</a>
						</td>
					</tr>
					<tr>
						<td>测试电源商1</td>
						<td>电源商1</td>
						<td>四川成都</td>
						<td>火电</td>
						<td>2300万kWh</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>test@tttt.com</td>
						<td>2017-04-17 15:11:05</td>
						<td>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify">修改合约</a>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify_p">修改电价</a>
							<a class="btn btn-danger btn-xs" flag="del">删除</a>
						</td>
					</tr>
					<tr>
						<td>测试电源商1</td>
						<td>电源商1</td>
						<td>四川成都</td>
						<td>火电</td>
						<td>2300万kWh</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>test@tttt.com</td>
						<td>2017-04-17 15:11:05</td>
						<td>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify">修改合约</a>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify_p">修改电价</a>
							<a class="btn btn-danger btn-xs" flag="del">删除</a>
						</td>
					</tr>
					<tr>
						<td>测试电源商1</td>
						<td>电源商1</td>
						<td>四川成都</td>
						<td>火电</td>
						<td>2300万kWh</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>test@tttt.com</td>
						<td>2017-04-17 15:11:05</td>
						<td>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify">修改合约</a>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify_p">修改电价</a>
							<a class="btn btn-danger btn-xs" flag="del">删除</a>
						</td>
					</tr>
					<tr>
						<td>测试电源商1</td>
						<td>电源商1</td>
						<td>四川成都</td>
						<td>火电</td>
						<td>2300万kWh</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>test@tttt.com</td>
						<td>2017-04-17 15:11:05</td>
						<td>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify">修改合约</a>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify_p">修改电价</a>
							<a class="btn btn-danger btn-xs" flag="del">删除</a>
						</td>
					</tr>
					<tr>
						<td>测试电源商1</td>
						<td>电源商1</td>
						<td>四川成都</td>
						<td>火电</td>
						<td>2300万kWh</td>
						<td>国营企业</td>					
						<td>张三</td>
						<td>test@tttt.com</td>
						<td>2017-04-17 15:11:05</td>
						<td>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify">修改合约</a>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify_p">修改电价</a>
							<a class="btn btn-danger btn-xs" flag="del">删除</a>
						</td>
					</tr>
				</tbody>
			</table>
			</div>
			<div id="page" class="col-sm-12" style="padding: 0px;">
			</div>	
		</div>
</form>
<script type="text/javascript" src="<%=basePath%>static/js/contract/sdhy.js"></script>