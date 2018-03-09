<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<form id="detail_form" class="form-horizontal" style="margin-top: 15px;">
	<div class="form-group">
		<label class="col-sm-2 control-label">名称 <span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="text" class="form-control" placeholder="请输入名称" required id="name"  maxlength="20"/>
		</div>
		<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="name">
			最长20个字符
		</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">权限<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<table class="table table-striped table-hover" style="margin-bottom:10px;border: 1px solid #ddd;background-color: #FFFFFF;">
				<thead>
					<tr>
						<th>权限</th>
					</tr>
				</thead>
				<tbody id="js_datas">
					<tr>
						<td>
							<input type="checkbox" id="ck_dys" desc="PowerSupplier:complete_control">
							<label for="ck_dys">电源商</label>
						</td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" id="ck_dlyh" desc="PowerCustomer:complete_control">
							<label for="ck_dlyh">电力用户</label>
						</td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" id="ck_sdhy" desc="SellPowerAgreement:complete_control">
							<label for="ck_sdhy">售电合约</label>
						</td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" id="ck_gdhy" desc="BuyElectricityContract:complete_control">
							<label for="ck_gdhy">购电合约</label>
						</td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" id="ck_dltz" desc="ElectricityAdjustmentData:complete_control">
							<label for="ck_dltz">电量调整</label>
						</td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" id="ck_yjsdl" desc="PowerData:complete_control">
							<label for="ck_yjsdl">电量</label>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="form-group" style="text-align: center;">
		<button class="btn btn-primary">保存</button>
	    <button class="btn btn-default" type="button" style="margin-left: 15px;" id="cancel">取消</button>
	</div>	
</form>
<script type="text/javascript" src="<%=basePath%>static/js/setting/jsglDetail.js"></script>