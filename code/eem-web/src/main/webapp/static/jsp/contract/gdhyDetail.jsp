<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<form id="detail_form" class="form-horizontal" style="margin-top: 15px;">
	<div class="form-group">
		<label class="col-sm-2 control-label">电源商名称<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<select class="select" data-placeholder="请选择电源商名称" id="supplier">
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">合约名称<span
			class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="text" class="form-control" placeholder="请输入合约名称" required id="name" maxlength="20"/>
		</div>
		<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="name">
			最长20个字符
		</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">合约编号<span
			class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="text" class="form-control" placeholder="请输入合约编号" required id="number" maxlength="20"/>
		</div>
		<label class="col-sm-3 control-label input_msg" style="text-align: left;" for="number">
			最长30个字符
		</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">合同有效年份<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<select class="select detail_search" data-placeholder="请选择合同有效年份" id="validYear">
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">合同附件<span class="asterisk">*</span></label>
		<div class="col-sm-7" style="padding-right: 0px;">
			<input type="text" class="form-control" id="file_path" style="margin-top: -1px;border-top-right-radius: 3px;border-bottom-right-radius: 3p" required>
		</div>
		<div class="col-sm-1" style="padding-left: 0px;">
			<a href="javascript:;" class="file_upload">
			    <input type="file" id="file" name="file">浏览
			</a>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">电压等级<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<select class="select" data-placeholder="请选择电压等级" id="voltageLevel">
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">交易电量<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="number" class="form-control" placeholder="请输入交易电量" required id="quantity" />
		</div>
		<label class="col-sm-1 control-label" style="color: gray;text-align: left;">万kWh</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">交易品种<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<select class="select detail_search" data-placeholder="请选择交易品种" id="tradeType">
				<option></option>
				<option value="常规直购电">常规直购电</option>
				<option value="精准扶持直购电">精准扶持直购电</option>
				<option value="自备替代直购电">自备替代直购电</option>
				<option value="富余电量交易">富余电量交易</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">交易价格<span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="number" class="form-control" placeholder="请输入交易价格" required id="price" />
		</div>
		<label class="col-sm-1 control-label" style="color: gray;text-align: left;">厘/kWh</label>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">交易电量</label>
		<div class="col-sm-10">
			<table class="table table-striped table-hover col-sm-10" style="margin-bottom: 10px; border: 1px solid #ddd; background-color: #FFFFFF;">
				<thead>
					<tr>
						<th>电力用户名称</th>
						<th>交易电量</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="jy_datas">
					<tr type="empty_msg">
						<td colspan="12" style="text-align: center;color:gray;height: 200px; background-color: #ffffff;">
							<img src="<%=basePath%>static/images/window/info.png" width="16px" height="16px">
							没有交易电量
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="form-group" style="text-align: center;">
		<button class="btn btn-primary" id="add_jy" type="button">添加交易电量</button>
		<button class="btn btn-primary" style="margin-left: 15px;">保存</button>
	    <button class="btn btn-default" type="button" style="margin-left: 15px;" id="cancel">取消</button>
	</div>	
</form>
<script type="text/javascript" src="<%=basePath%>static/js/jquery/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/contract/gdhyDetail.js"></script>