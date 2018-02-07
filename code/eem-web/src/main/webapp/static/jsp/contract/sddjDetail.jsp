<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<form id="detail_form" class="form-horizontal" style="margin-top: 15px;">
	<div class="form-group">
		<label class="col-sm-2 control-label">电力用户名称 <span class="asterisk">*</span></label>
		<div class="col-sm-7">
			<select class="select detail_search" data-placeholder="请选择合同有效年份" id="customer">
				<option value="apple">2022</option>
				<option value="orange">2022</option>
				<option value="grapes">2022</option>
				<option value="strawberry">2022</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">合约名称<span
			class="asterisk">*</span></label>
		<div class="col-sm-7">
			<input type="text" class="form-control" placeholder="请输入合约名称" required id="name" />
		</div>
	</div>
	<div class="form-group">
		<table class="table table-striped table-hover" style="margin-bottom:10px;border: 1px solid #ddd;background-color: #FFFFFF;">
			<thead>
				<tr>
					<th>月份</th>
					<th>交易总量</th>
					<th>常规直购电交易电量</th>
					<th>精准扶持直购电交易电量</th>
					<th>自备替代直购电交易电量</th>
					<th>富于电量</th>
				</tr>
			</thead>
			<tbody id="dl_datas">
				<tr>
					<td>
						一月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						二月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						三月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						四月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						五月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						六月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						七月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						八月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						九月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						十月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						十一月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						十二月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="form-group">
		<table class="table table-striped table-hover" style="margin-bottom:10px;border: 1px solid #ddd;background-color: #FFFFFF;">
			<thead>
				<tr>
					<th>月份</th>
					<th>常规直购电交易电量</th>
					<th>精准扶持直购电交易电量</th>
					<th>自备替代直购电交易电量</th>
					<th>富于电量</th>
				</tr>
			</thead>
			<tbody id="dl_datas">
				<tr>
					<td>
						一月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						二月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						三月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						四月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						五月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						六月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						七月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						八月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						九月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						十月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						十一月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>
						十二月
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
					<td>
						<input type="text" class="form-control" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="form-group" style="text-align: center;">
		<button class="btn btn-primary">保存</button>
	    <button class="btn btn-default" type="button" style="margin-left: 15px;">取消</button>
	</div>	
</form>
<script type="text/javascript" src="<%=basePath%>static/js/contract/sddjDetail.js"></script>