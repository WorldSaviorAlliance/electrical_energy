<%@page import="com.warrior.eem.util.Constant"%>
<%@page import="com.warrior.eem.util.ToolUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String headerPath = ToolUtil.getBasePath(request);
%>
<style>
.nav.navbar-nav>li:hover>ul.dropdown-menu {
	display: block;
	-webkit-animation: fadeInUp 400ms;
	-moz-animation: fadeInUp 400ms;
	-ms-animation: fadeInUp 400ms;
	-o-animation: fadeInUp 400ms;
	animation: fadeInUp 400ms;
}

.toptitle {
	font-size: 16px;
	color: #FFFFFF;
}

.eem-dropdown-menu, .eem-dropdown-menu a {
	height: 35px;
	line-height: 35px;
}
</style>
<script type="text/javascript"  src="<%=headerPath%>static/js/header.js?version=<%= Constant.getCurTime()%>"></script>
<header>
	<nav class="navbar navbar-fixed-top navbar-default affix-top">
	  <div class="container">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-wrapper" aria-expanded="false"> 
	      	<span class="sr-only">展示</span> 
	      	<span class="icon-bar top-bar" style="margin: 4px 0px;"></span> 
	      	<span class="icon-bar middle-bar" style="margin: 4px 0px;"></span> 
	      	<span class="icon-bar bottom-bar" style="margin: 4px 0px;"></span>  
	      </button>
	      <a id="home-link" class="gnome-navbar-brand" title="<%=ToolUtil.getProjectName()%>" href="<%=headerPath%>home">
        	 <img src="<%=headerPath%>static/images/logo.png" height="32px" style="margin-top: 10px;">  
	      	 <span class="project-name"><%=ToolUtil.getProjectName()%></span>
	      </a> 
	     </div>
	    
	    <div class="navbar-collapse collapse navbar-right" id="navbar-wrapper" aria-expanded="false" style="height: 1px;">
	    	<ul id="menu-primary" class="nav navbar-nav" style="margin-top: 9px;">
	    		<li class="dropdown" id="header_change_password">
					<a title="首页" href="<%=headerPath %>home" >
						<i class="fa fa-home fa-lg" title="首页"></i>
						<span class="toptitle">首页</span>
					</a>
				</li>
				<li class="dropdown" style="position: relative;">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="fa fa-users fa-lg"></i>
						<span class="toptitle">客户</span>
					</a>
					<ul class="dropdown-menu extended inbox" style="min-width: 150px;margin-left: 3px;height: auto;overflow: auto;">
						<li class="eem-dropdown-menu"><a href="<%=headerPath%>dys"><i class="fa fa-user-circle"></i><span style="margin-left:5px;">电源商</span></a></li>
						<li class="eem-dropdown-menu"><a href="<%=headerPath%>dlyh"><i class="fa fa-user-o"></i><span style="margin-left:5px;">电力用户</span></a></li>
					</ul>
				</li>
				<li class="dropdown" style="position: relative;">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="fa fa-money fa-lg"></i>
						<span class="toptitle">合约</span>
					</a>
					<ul class="dropdown-menu extended inbox eem-dropdown-menu" style="min-width: 150px;margin-left: 3px;height: auto;overflow: auto;">
						<li class="eem-dropdown-menu"><a href="<%=headerPath%>sdhy"><i class="fa fa-id-card"></i><span style="margin-left:5px;">售电合约</span></a></li>
						<li class="eem-dropdown-menu"><a href="<%=headerPath%>gdhy"><i class="fa fa-id-card-o"></i><span style="margin-left:5px;">购电合约</span></a></li>
						<li class="eem-dropdown-menu"><a href="<%=headerPath%>dltz"><i class="fa fa-address-card-o"></i><span style="margin-left:5px;">电量调整</span></a></li>
					</ul>
				</li>
				<li class="dropdown" style="position: relative;">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="fa fa-stack-exchange fa-lg"></i>
						<span class="toptitle">电量</span>
					</a>
					<ul class="dropdown-menu extended inbox eem-dropdown-menu" style="min-width: 150px;margin-left: 3px;height: auto;overflow: auto;">
						<li class="eem-dropdown-menu"><a href="<%=headerPath%>yjsdl"><i class="fa fa-road"></i><span style="margin-left:5px;">月结算电量</span></a></li>
						<li class="eem-dropdown-menu"><a href="<%=headerPath%>dlydqs"><i class="fa fa-shield"></i><span style="margin-left:5px;">电量月度清算</span></a></li>
						<li class="eem-dropdown-menu"><a href="<%=headerPath%>yddlpc"><i class="fa fa-ship"></i><span style="margin-left:5px;">月度电量偏差</span></a></li>
						<li class="eem-dropdown-menu"><a href="<%=headerPath%>nddlpc"><i class="fa fa-tasks"></i><span style="margin-left:5px;">年度电量偏差</span></a></li>
					</ul>
				</li>
				<li class="dropdown" style="position: relative;">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="fa fa-cog fa-lg"></i>
						<span class="toptitle">系统管理</span>
					</a>
					<ul class="dropdown-menu extended inbox eem-dropdown-menu" style="min-width: 150px;margin-left: 3px;height: auto;overflow: auto;">
						<li class="eem-dropdown-menu"><a href="<%=headerPath%>dydj"><i class="fa fa-american-sign-language-interpreting"></i><span style="margin-left:5px;">电压等级</span></a></li>
						<li class="eem-dropdown-menu"><a href="<%=headerPath%>djxs"><i class="fa fa-resistance"></i><span style="margin-left:5px;">电价系数</span></a></li>
						<li class="eem-dropdown-menu"><a href="<%=headerPath%>yhgl"><i class="fa fa-user"></i><span style="margin-left:5px;">用户管理</span></a></li>
						<li class="eem-dropdown-menu"><a href="<%=headerPath%>jsgl"><i class="fa fa-id-badge"></i><span style="margin-left:5px;">角色管理</span></a></li>
						<li class="eem-dropdown-menu"><a href="#" id="changePsw"><i class="fa fa-key"></i><span style="margin-left:5px;">修改密码</span></a></li>
					</ul>
				</li>
				<li class="dropdown" id="header_change_password"> <!-- 这里后面指定为普通用户的Home -->
					<a title="能效管理" href="<%=headerPath %>nx" >
						<i class="fa fa-home fa-lg" title="能效管理"></i>
						<span class="toptitle">能效管理</span>
					</a>
				</li>
				<li class="dropdown" id="header_change_password">
					<a title="我要办理" href="<%=headerPath %>wybl" >
						<i class="fa fa-stack-exchange fa-lg" title="我要办理"></i>
						<span class="toptitle">我要办理</span>
					</a>
				</li>
				<li class="dropdown" id="header_change_password">
					<a title="我的资料" href="#" >
						<i class="fa fa-cog fa-lg" title="我的资料"></i>
						<span class="toptitle">我的资料</span>
					</a>
					<ul class="dropdown-menu extended inbox eem-dropdown-menu" style="min-width: 150px;margin-left: 3px;height: auto;overflow: auto;">
						<li class="eem-dropdown-menu"><a href="<%=headerPath%>jbxx"><i class="fa fa-american-sign-language-interpreting"></i><span style="margin-left:5px;">基本信息</span></a></li>
						<li class="eem-dropdown-menu"><a href="<%=headerPath%>sdhy"><i class="fa fa-resistance"></i><span style="margin-left:5px;">我的合同</span></a></li>
						<li class="eem-dropdown-menu"><a href="#" id="changePsw"><i class="fa fa-key"></i><span style="margin-left:5px;">修改密码</span></a></li>
					</ul>
				</li>
				<li class="dropdown" id="header_exist">
					<a title="注销" href="#" style="color: #FFFFFF;">
						<i class="fa fa-sign-out fa-lg" title="注销"></i>
						<span class="toptitle">注销</span>
					</a>
				</li>
	   		</ul>
	   	</div>	   	
	  </div>
	</nav>
</header>