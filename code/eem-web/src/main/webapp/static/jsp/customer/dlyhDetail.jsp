<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<form id="detail_form" class="form-horizontal" style="margin-top: 15px;">
	<div class="form-group">
		<label class="col-sm-2 control-label">电力用户名称 <span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="text" class="form-control" placeholder="请输入电力用户名称" required id="name" maxlength="30" minlength="3"/>
		</div>
		<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="name">
			最长30个字符
		</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">电力用户简称 <span
			class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="text" class="form-control" placeholder="请输入电力用户简称" required id="nickName" />
		</div>
		<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="nickName">
			最长10个字符
		</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">所在城市 <span
			class="asterisk">*</span></label>
		<div class="col-sm-7">
			<div class="form-group" style="margin: 0px;">
				<div class="col-sm-6" style="padding-left: 0px;">
					<select class="select detail_search" data-placeholder="请选择省份" id="province">
						<option value="四川">四川</option>
						<option value="北京">北京</option>
						<option value="上海">上海</option>
						<option value="云南">云南</option>
					</select>
				</div>
				<div class="col-sm-6" style="padding-right: 0px;">
					<select class="select detail_search" data-placeholder="请选择市区" id="city">
						<option value="成都">成都</option>
						<option value="德阳">德阳</option>
						<option value="绵阳">绵阳</option>
						<option value="宜宾">宜宾</option>
					</select>
				</div>
			</div>
		</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">地址 <span class="asterisk">*</span></label>
			<div class="col-sm-7">
				<input type="text" class="form-control" placeholder="请输入地址" required id="address"  maxlength="100"/>
			</div>
			<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="address">
				最长100个字符
			</label>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">所属行业<span	class="asterisk">*</span></label>
			<div class="col-sm-7">
				<select class="select detail_search" data-placeholder="请选择所属行业" id="industryType">
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">企业性质<span class="asterisk">*</span></label>
			<div class="col-sm-7">
				<select class="select detail_search" data-placeholder="请选择企业性质" id="natureType">
					<option value="0">国营企业</option>
					<option value="1">民营企业</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">联系人</label>
			<div class="col-sm-7">
				<input type="text" class="form-control" id="contactName" maxlength="10"/>
			</div>
			<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="contactName">
				最长10个字符
			</label>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">联系电话</label>
			<div class="col-sm-7">
				<input type="text" class="form-control" id="contactPhone" maxlength="64"/>
			</div>
			<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="contactPhone">
				最长64个字符
			</label>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">联系人职务</label>
			<div class="col-sm-7">
				<input type="text" class="form-control" id="contactPosition" maxlength="10"/>
			</div>
			<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="contactPosition">
				最长10个字符
			</label>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">联系邮箱</label>
			<div class="col-sm-7">
				<input type="email" class="form-control" id="contactEmail" maxlength="30"/>
			</div>
			<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="contactEmail">
				最长30个字符
			</label>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">传真</label>
			<div class="col-sm-7">
				<input type="text" class="form-control" id="fax" maxlength="10"/>
			</div>
			<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="fax">
				最长10个字符
			</label>
		</div>
		<div class="form-group" style="text-align: center;">
			<button class="btn btn-primary">保存</button>
		    <button class="btn btn-default" type="button" style="margin-left: 15px;" id="cancel">取消</button>
		</div>	
</form>
<script type="text/javascript" src="<%=basePath%>static/js/customer/dlyhDetail.js"></script>