<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java"  pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<h4 class="page-title detail_sub_title">当前位置：<span>电量管理</span> / <span>月结算电量列表</span></h4>
<div class="divider" style="margin-top: 6px;margin-bottom: 6px;"></div>
<form class="form-horizontal">
	<div class="form-group">
		<div class="col-sm-12" style="padding: 0px;">
			<div class="col-md-3">
				<div class="form-group">
                  <label class="col-sm-4 control-label" style="width: auto;">电力用户名称</label>
                  <div class="col-sm-8">
                   <input type="text" class="form-control" id="search_name">
                  </div>
                </div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-sm-2 control-label">月份</label>
	                 <div class="col-sm-4">
		                <select class="select search_select" data-placeholder="请选择开始月份" id="beginMonth">
							<option value="">请选择开始月份</option>
							<option value="201802">201802</option>
							<option value="201803">201803</option>
							<option value="201804">201804</option>
							<option value="201805">201805</option>
						</select> 
					</div>
					<div class="col-sm-4">
		                <select class="select search_select" data-placeholder="请选择结束月份" id="endMonth">
							<option value="">请选择结束月份</option>
							<option value="201802">201802</option>
							<option value="201803">201803</option>
							<option value="201804">201804</option>
							<option value="201805">201805</option>
						</select> 
					</div>
				</div>
			</div>
			<div class="col-md-2">
				<button class="btn btn-primary" id="do_search" type="button">查询</button>
				<button class="btn btn-info" id="add" type="button">添加</button>
			</div>
		</div>
		<div class="col-sm-12" style="padding: 0px;">
			<table class="table table-striped table-hover" style="margin-bottom:10px;border: 1px solid #ddd;background-color: #FFFFFF;">
				<thead>
					<tr>
						<th>电力用户名称</th>
						<th>用户户号</th>
						<th>电表编号</th>
						<th>月份</th>
						<th>电压等级</th>
						<th>交易品种</th>
						<th>高峰用电量</th>
						<th>平段用电量</th>
						<th>低谷用电量</th>
						<th>总计有功电量</th>
						<th>无功电量</th>
						<th>添加时间</th>
						<th>操作</th>
					</tr>
				</thead>				
				<tbody id="datas">
					<tr type="empty_msg" style="display: none;">
						<td colspan="12" style="text-align: center;color:gray;height: 200px; background-color: #ffffff;">
							<img src="<%=basePath%>static/images/window/info.png" width="16px" height="16px">
							没有月结算电量
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
<script type="text/javascript" src="<%=basePath%>static/js/electricity/yjsdl.js"></script>
