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
						<td width="32%"><span id="name"></span></td>
						<th>客户简称</th>
						<td width="32%"><span id="nickName"></span></td>
					</tr>
					<tr>
						<th>所在城市</th>
						<td><span id="city"></span></td>
						<th>客户地址</th>
						<td><span id="address"></span></td>
					</tr>
					<tr>
						<th>企业性质</th>
						<td><span id="natureType"></span></td>
						<th>所属行业</th>
						<td><span id="industryType"></span></td>
					</tr>
					<tr>
						<th>联系人</th>
						<td><span id="contactName"></span></td>
						<th>联系电话</th>
						<td><span id="contactPhone"></span></td>
					</tr>
					<tr>
						<th>联系人职务</th>
						<td><span id="contactPosition"></span></td>
						<th>联系邮箱</th>
						<td><span id="contactEmail"></span></td>
					</tr>
					<tr>
						<th>传真</th>
						<td><span id="fax"></span></td>
						<td colspan="2"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</form>

<script type="text/javascript" src="<%=basePath%>static/js/user/jbxx.js"></script>