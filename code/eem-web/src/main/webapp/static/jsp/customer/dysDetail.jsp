<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<form id="detail_form" class="form-horizontal" style="margin-top: 15px;">
	<div class="form-group">
		<label class="col-sm-2 control-label">电源商名称 <span class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入电源商名称" required id="name" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">电源商简称 <span
			class="asterisk">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" placeholder="请输入电源商简称" required id="nick_name" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">所在城市 <span
			class="asterisk">*</span></label>
		<div class="col-sm-9">
			<div class="form-group" style="margin: 0px;">
				<div class="col-sm-6" style="padding-left: 0px;">
					<select class="select" data-placeholder="请选择省份" id="province">
						<option value="apple">四川</option>
						<option value="orange">北京</option>
						<option value="grapes">上海</option>
						<option value="strawberry">云南</option>
					</select>
				</div>
				<div class="col-sm-6" style="padding-right: 0px;">
					<select class="select" data-placeholder="请选择市区" id="city">
						<option value="apple">成都</option>
						<option value="orange">德阳</option>
						<option value="grapes">绵阳</option>
						<option value="strawberry">宜宾</option>
					</select>
				</div>
			</div>
		</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">地址 <span	class="asterisk">*</span></label>
			<div class="col-sm-9">
				<input type="text" class="form-control" placeholder="请输入地址" required id="address" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">电源类型<span	class="asterisk">*</span></label>
			<div class="col-sm-9">
				<select class="select" data-placeholder="请选择电源类型" id="power_type">
						<option value="0">火电</option>
						<option value="1">水电</option>
						<option value="2">风电</option>
						<option value="3">核电</option>
						<option value="4">其他</option>
					</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">年均发电量<span class="asterisk">*</span></label>
			<div class="col-sm-9">
				<input type="text" class="form-control" placeholder="请输入年均发电量" required id="capacity" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">企业性质<span class="asterisk">*</span></label>
			<div class="col-sm-9">
				<select class="select" data-placeholder="请选择企业性质" id="nature_type">
					<option value="0">国营企业</option>
					<option value="1">民营企业</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">联系人</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="contact_name" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">联系电话</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="contact_phone" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">联系人职务</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="contact_position" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">联系邮箱</label>
			<div class="col-sm-9">
				<input type="email" class="form-control" id="contact_email" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">传真</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="fax" />
			</div>
		</div>
		<div class="form-group" style="text-align: center;">
			<button class="btn btn-primary">保存</button>
		    <button class="btn btn-default" type="button">取消</button>
		</div>	
</form>
<script type="text/javascript"
	src="<%=basePath%>static/js/customer/dysDetail.js"></script>