<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java"  pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<h4 class="page-title detail_sub_title">当前位置：<span >电源商管理</span> / <span>电力用户电量调整</span></h4>
<div class="divider" style="margin-top: 6px;margin-bottom: 6px;"></div>
<form class="form-horizontal">
	<div>
		<div class="tab-content detail_content">
			<div class="row" style="margin: 15px 0px;">
				<div class="control-group col-md-3">
					<label class="control-label">名称</label>
					<div class="controls">
						<input type="text" class="form-control" id="name">
					</div>						
				</div>
				<div class="col-md-4 control-group ">
					<label class="control-label">录入时间</label>
					<div class="controls">
						<div class="input-group" style="padding: 0px;">
							<span class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</span>
							<input class="form-control" type="text" name="date-range-picker" id="eaRangTime" />
						</div>
					</div>				
				</div>
				<div class="col-md-3">
					<label class="control-label">显示顺序</label>
					<div class="controls">
						<select class="m-wrap" style="width: 100%;" id="sortType">
							<option value="0">录入时间 降序</option>
							<option value="1">录入时间 升序</option>
						</select>
					</div>				
				</div>
				<div class="control-group col-md-2">
					<button class="btn btn-primary" id="searchEa">查询</button>
					<button class="btn btn-info" id="addEa">添加</button>
				</div>
			</div>
			<table class="table table-striped table-hover" style="margin-bottom:10px;border: 1px solid #ddd;background-color: #FFFFFF;">
				<thead>
					<tr>
						<th>电力用户名称</th>
						<th>用户户号</th>
						<th>合约名称</th>
						<th>合约编号</th>
						<th>月份</th>
						<th>调整类型</th>
						<th>调整电量</th>
						<th>电压等级</th>
						<th>交易品种</th>
						<th>交易价格</th>
						<th>录入时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="ea_table">
					<tr>
						<td>测试电源商1</td>
						<td>231231231</td>
						<td>四川成都</td>
						<td>DDDD</td>
						<td>20180303</td>
						<td>2300万kWh</td>				
						<td>110KV</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>2017-04-17 15:11:05</td>
						<td>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify_ea">修改</a>
							<a class="btn btn-danger btn-xs" flag="del_ea">删除</a>
						</td>
					</tr>
					<tr>
						<td>测试电源商1</td>
						<td>231231231</td>
						<td>四川成都</td>
						<td>DDDD</td>
						<td>20180303</td>
						<td>2300万kWh</td>				
						<td>110KV</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>2017-04-17 15:11:05</td>
						<td>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify_ea">修改</a>
							<a class="btn btn-danger btn-xs" flag="del_ea">删除</a>
						</td>
					</tr>
					<tr>
						<td>测试电源商1</td>
						<td>231231231</td>
						<td>四川成都</td>
						<td>DDDD</td>
						<td>20180303</td>
						<td>2300万kWh</td>				
						<td>110KV</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>2017-04-17 15:11:05</td>
						<td>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify_ea">修改</a>
							<a class="btn btn-danger btn-xs" flag="del_ea">删除</a>
						</td>
					</tr>
					<tr>
						<td>测试电源商1</td>
						<td>231231231</td>
						<td>四川成都</td>
						<td>DDDD</td>
						<td>20180303</td>
						<td>2300万kWh</td>				
						<td>110KV</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>2017-04-17 15:11:05</td>
						<td>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify_ea">修改</a>
							<a class="btn btn-danger btn-xs" flag="del_ea">删除</a>
						</td>
					</tr>
					<tr>
						<td>测试电源商1</td>
						<td>231231231</td>
						<td>四川成都</td>
						<td>DDDD</td>
						<td>20180303</td>
						<td>2300万kWh</td>				
						<td>110KV</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>2017-04-17 15:11:05</td>
						<td>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify_ea">修改</a>
							<a class="btn btn-danger btn-xs" flag="del_ea">删除</a>
						</td>
					</tr>
					<tr>
						<td>测试电源商1</td>
						<td>231231231</td>
						<td>四川成都</td>
						<td>DDDD</td>
						<td>20180303</td>
						<td>2300万kWh</td>				
						<td>110KV</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>2017-04-17 15:11:05</td>
						<td>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify_ea">修改</a>
							<a class="btn btn-danger btn-xs" flag="del_ea">删除</a>
						</td>
					</tr>
					<tr>
						<td>测试电源商1</td>
						<td>231231231</td>
						<td>四川成都</td>
						<td>DDDD</td>
						<td>20180303</td>
						<td>2300万kWh</td>				
						<td>110KV</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>TTTT</td>
						<td>2017-04-17 15:11:05</td>
						<td>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify_ea">修改</a>
							<a class="btn btn-danger btn-xs" flag="del_ea">删除</a>
						</td>
					</tr>
				</tbody>
			</table>
			<div id="page2Ea">
			</div>	
		</div>
	</div>
</form>
<script type="text/javascript" src="<%=basePath%>static/js/contract/ea.js"></script>
