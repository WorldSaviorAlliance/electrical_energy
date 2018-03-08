<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java"  pageEncoding="utf-8"%>
<h4 class="page-title detail_sub_title">当前位置：<span>电量管理</span> / <span >月度电量偏差</span></h4>
<div class="divider" style="margin-top: 6px;margin-bottom: 6px;"></div>
<%@include file="pc.jsp" %>
<script>
$(function(){
	new PC(0);
});
</script>