<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java"  pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<h4 class="page-title detail_sub_title">当前位置：<span>普通用户</span> / <span >基本信息</span></h4>
<div class="divider" style="margin-top: 6px;margin-bottom: 6px;"></div>
<form class="form-horizontal">
	<div class="form-group">
		<div class="col-sm-12" style="padding: 0px;">
			<table class="table table-striped table-hover" style="margin-bottom:10px;border: 1px solid #ddd;background-color: #FFFFFF;">
				<tbody>
					<tr>
						<th>客户名称</th>
						<td width="32%">某某水泥</td>
						<th>编号</th>
						<td width="32%">hz001</td>
					</tr>
					<tr>
						<th>所在城市</th>
						<td>四川 乐山</td>
						<th>客户地址</th>
						<td></td>
					</tr>
					<tr>
						<th>企业性质</th>
						<td>民营企业</td>
						<th>企业规模</th>
						<td>小型</td>
					</tr>
					<tr>
						<th>所属行业</th>
						<td>水泥建材</td>
						<th>年均用电量</th>
						<td>2000万kWh</td>
					</tr>
					<tr>
						<th>客户状态</th>
						<td>已签约</td>
						<th>备注</th>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</form>
