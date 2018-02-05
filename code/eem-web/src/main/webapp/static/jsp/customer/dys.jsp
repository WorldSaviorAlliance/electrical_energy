<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java"  pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<h4 class="page-title detail_sub_title">
	当前位置：<span >客户</span> / <span >电源商</span>
</h4>
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
                  <label class="col-sm-2 control-label">所在城市</label>
                  <div class="col-sm-5">
	                   <select class="select" data-placeholder="请选择省份" id="province">
						<option value=""></option>
						<option value="四川">四川</option>
						<option value="北京">北京</option>
						<option value="上海">上海</option>
						<option value="云南">云南</option>
					</select> 
				</div>
				<div class="col-sm-5">	
					<select class="select medium m-wrap" style="width:100%;" data-placeholder="请选择市区" id="city">
						<option value=""></option>
						<option value="成都">成都</option>
						<option value="德阳">德阳</option>
						<option value="绵阳">绵阳</option>
						<option value="宜宾">宜宾</option>
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
						<th>电源商名称</th>
						<th>电源商简称</th>
						<th>所在城市</th>
						<th>电源类型</th>
						<th>年均发电量</th>
						<th>企业性质</th>
						<th>联系人</th>
						<th>联系电话</th>
						<th>联系人职务</th>
						<th>联系邮箱</th>
						<th>录入时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="datas">
					<tr type="empty_msg" style="display: none;">
						<td colspan="12" style="text-align: center;color:gray;height: 200px; background-color: #ffffff;">
							<img src="<%=basePath%>static/images/window/info.png" width="16px" height="16px">
							没有电源商
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
<script type="text/javascript" src="<%=basePath%>static/js/customer/dys.js"></script>