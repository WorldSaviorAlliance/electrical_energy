<%@page import="com.warrior.eem.util.ToolUtil"%>
<!-- 联系人详细的模板 -->
<%@ page language="java"  pageEncoding="utf-8"%>
<%
	String basePath = ToolUtil.getBasePath(request);
%>

<form id="basicForm" class="form-horizontal" style="margin-top: 15px;" onsubmit="return false;">
	 <div class="form-group">
       <label class="col-sm-2 control-label">Name <span class="asterisk">*</span></label>
       <div class="col-sm-9">
         <input type="text" name="name" class="form-control" placeholder="Type your name..." required />
       </div>
     </div>
     <div class="form-group">
       <label class="col-sm-2 control-label">Name <span class="asterisk">*</span></label>
       <div class="col-sm-9">
         <input type="text" name="name" class="form-control" placeholder="Type your name..." required />
       </div>
     </div>
     <div class="form-group">
       <label class="col-sm-2 control-label">Name <span class="asterisk">*</span></label>
       <div class="col-sm-9">
         <input type="text" name="name" class="form-control" placeholder="Type your name..." required />
       </div>
     </div>
     <div class="form-group">
       <button class="btn btn-primary" >Submit</button>
     </div>
      
</form>
<script type="text/javascript" src="<%=basePath%>static/js/customer/dysDetail.js"></script>