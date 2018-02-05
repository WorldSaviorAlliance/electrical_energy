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
		                    <h4 style="text-align:center;color:#a2a1a1;border-bottom: 1px #dadada solid;margin:0px 10px;padding-bottom:10px;">本月功率因数：<b id="yshu" style="color:#f7c31e; font-size: 30px; cursor: pointer">0.963</b></h4>
		                    <table class="toplinetable">
		                        <tbody><tr>
		                            <td width="40%" class="title">本月有功电量：</td>
		                            <td><b id="yougong_power">342.73</b> 万kWh</td>
		                        </tr>
		                        <tr>
		                            <td class="title">本月无功电量：</td>
		                            <td><b id="wugong_power">96.53</b> 万kvarh</td>
		                        </tr>
		                        <tr class="ie_hide">
		                            <td class="title">本月电费：</td>
		                            <td><b id="sum_charge" style="color: #f7c31e;">239.61</b> 万元</td>
		                        </tr>
		                        <tr class="ie_hide">
		                            <td class="title">合：</td>
		                            <td><b class="he_num" id="charge_power_he" style="color: #f7c31e;">0.699</b> 元/kWh</td>
		                        </tr>
		                    </tbody></table>
		              </div>
		            </div>
		    </div>
		      <div class="col-md-4">
		            <div class="panel panel-default">
		              <div class="panel-body">
		                <div class="piechar_title" style="">今日<span class="ie_type">用电</span>分析
		                </div>
		                <div id="pie_day_1" style="height:200px;" class="bg_load"></div>
		              </div>
		            </div>
		      </div>
		      <div class="col-md-4">
		            <div class="panel panel-default">
		              <div class="panel-body">
		                <div class="piechar_title">今日电费分析
		                </div>
		                <div id="pie_day_2" style="height:200px;" class="bg_load"></div>
		              </div>
		            </div>
		      </div>
		  </div>
		    
		  <div class="row">  
		       <div class="col-md-12">
		            <div class="panel panel-default">
		                <div class="panel-body">
		                    <div class="piechar_bottom ie_hide">合 <span id="pie_day_2_he" style="color: #1fbba6;font-size: 18px;font-weight: bold; "></span> 元/kWh</div>
		                    <div id="column_1" style="height:300px;" class="bg_load">
		                    </div>
		                </div>
		            </div>
		       </div>
		      
		  </div>
		
		  <div class="row ">
		       <div class="col-md-8">
		            <div class="panel panel-default">
		                <div class="panel-body">
		                    <div class="piechar_title">近10日<span class="ie_type">用电</span></div>
		                    <div id="line_1" style="height:220px;" class="bg_load">
		                    </div>
		                </div>
		            </div>
		       </div>
		       <div class="col-md-4">
		            <div class="panel panel-default">
		                <div class="panel-body">
		                    <div class="piechar_title">近6月<span class="ie_type">用电</span></div>
		                    <div id="line_2" style="height:220px;" class="bg_load">
		                    </div>
		                </div>
		            </div>
		       </div>
		  </div>
		</div>
</form>
<script type="text/javascript" src="<%=basePath%>static/js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/highcharts/highcharts-more.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/highcharts/echarts.common.min.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/user/nx.js"></script>
