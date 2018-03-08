<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java"  pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>
<h4 class="page-title detail_sub_title">当前位置：<span>普通用户</span> / <span >能效管理</span></h4>
<div class="divider" style="margin-top: 6px;margin-bottom: 6px;"></div>
<form class="form-horizontal">
	<div class="form-group">
		<div class="row ">
		      <div class="col-md-4">
		          <div class="panel panel-default">
		              <div class="panel-body">
		                    <table class="toplinetable">
		                        <tbody>
			                        <tr>
			                            <td width="40%" style="height: 40px;">本月有功电量：</td>
			                            <td><b id="validQuantity">0</b> 万kWh</td>
			                        </tr>
			                        <tr>
			                            <td class="title" style="height: 40px;">本月无功电量：</td>
			                            <td><b id="invalidQuantity">0</b> 万kvarh</td>
			                        </tr>
			                        <tr class="ie_hide">
			                            <td class="title" style="height: 40px;">本月电费：</td>
			                            <td><b id="totalPrice" style="color: #f7c31e;">0</b> 万元</td>
			                        </tr>
			                    </tbody>
		                    </table>
		              </div>
		          </div>
		      </div>
		      <div class="col-md-4">
		            <div class="panel panel-default">
		              <div class="panel-body">
		                <div class="piechar_title" style="">年度用电分析
		                </div>
		                <div id="pie_day_1" style="height:200px;" class="bg_load"></div>
		              </div>
		            </div>
		      </div>
		      <div class="col-md-4">
		            <div class="panel panel-default">
		              <div class="panel-body">
		                <div class="piechar_title">年度电费分析
		                </div>
		                <div id="pie_day_2" style="height:200px;" class="bg_load"></div>
		              </div>
		            </div>
		      </div>
		  </div>
		    
		  <div class="row ">
		       <div class="col-md-8">
		            <div class="panel panel-default">
		                <div class="panel-body">
		                    <div class="piechar_title">月度电量趋势图(万kWh)</div>
		                    <div id="month" style="height:220px;" class="bg_load">
		                    </div>
		                </div>
		            </div>
		       </div>
		  </div>
		</div>
</form>
<script type="text/javascript" src="<%=basePath%>static/js/highcharts/echarts.common.min.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/user/nx.js"></script>
