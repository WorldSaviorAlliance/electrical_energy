<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java"  pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<h4 class="page-title detail_sub_title">当前位置：<span>普通用户</span> / <span>套餐办理</span></h4>
<div class="divider" style="margin-top: 6px;margin-bottom: 6px;"></div>
<form class="form-horizontal">
	<div class="form-group">
		<div class="col-sm-12" style="padding: 0px;">
			<div class="col-md-3">
				<div class="form-group">
                  <label class="col-sm-4 control-label">套餐名称</label>
                  <div class="col-sm-8">
                   <input type="text" class="form-control" id="name">
                  </div>
                </div>
			</div>
			<div class="col-md-2">
				<button class="btn btn-primary" id="search" type="button">查询</button>
				<button class="btn btn-info" id="add" type="button">办理</button>
			</div>
		</div>
		<div class="col-sm-12" style="padding: 0px;">
			<table class="table table-striped table-hover" style="margin-bottom:10px;border: 1px solid #ddd;background-color: #FFFFFF;">
				<thead>
					<tr>
						<th>套餐名称</th>
						<th>办理时间</th>
						<th>办理类型</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="datas">
					<tr>
						<td>套餐1</td>
						<td>2018-01-02 11:01:48</td>
						<td>固定回报</td>
						<td>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="detail">详情</a>
							<a class="btn btn-danger btn-xs" flag="del">退订</a>
						</td>
					</tr>
					<tr>
						<td>套餐1</td>
						<td>2018-01-02 11:01:48</td>
						<td>固定回报</td>
						<td>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="detail">详情</a>
							<a class="btn btn-danger btn-xs" flag="del">退订</a>
						</td>
					</tr>
					<tr>
						<td>套餐1</td>
						<td>2018-01-02 11:01:48</td>
						<td>固定回报</td>
						<td>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="detail">详情</a>
							<a class="btn btn-danger btn-xs" flag="del">退订</a>
						</td>
					</tr>
					<tr>
						<td>套餐1</td>
						<td>2018-01-02 11:01:48</td>
						<td>固定回报</td>
						<td>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="detail">详情</a>
							<a class="btn btn-danger btn-xs" flag="del">退订</a>
						</td>
					</tr>
					<tr>
						<td>套餐1</td>
						<td>2018-01-02 11:01:48</td>
						<td>固定回报</td>
						<td>
							<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="detail">详情</a>
							<a class="btn btn-danger btn-xs" flag="del">退订</a>
						</td>
					</tr>
				</tbody>
			</table>
			</div>
			<div id="page" class="col-sm-12" style="padding: 0px;">
			</div>	
		</div>
</form>
<script type="text/javascript" src="<%=basePath%>static/js/user/wybl.js"></script>
