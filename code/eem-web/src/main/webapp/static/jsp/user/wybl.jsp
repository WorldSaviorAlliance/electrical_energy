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
			<div class="col-md-2" style="padding-left: 0px; margin-bottom: 15px;">
				<button class="btn btn-info" id="add" type="button">办理套餐</button>
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
					<tr type="empty_msg" style="display: none;">
						<td colspan="12" style="text-align: center;color:gray;height: 200px; background-color: #ffffff;">
							<img src="<%=basePath%>static/images/window/info.png" width="16px" height="16px">
							没有任何套餐
						</td>
					</tr>
					<tr type="error_msg" style="display: none;">
						<td colspan="12" style="text-align: center;color:gray">
							<span style="color:red" type="error_detail"></span>
						</td>
					</tr>
					<tr type="loading_msg">
						<td colspan="12">
							<div style="text-align: center;color:gray;">
							 	<img src="<%=basePath%>static/images/loading/loading_32.gif" width="16px" height="16px">
							 	<span>正在获取数据中…….</span>
							</div>
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
