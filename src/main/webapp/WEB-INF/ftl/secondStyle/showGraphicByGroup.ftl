<@override name="script">

	<script src="${base}/static/assets/js/sidebar-menu.js"></script>
	<script src="${base}/static/assets/js/bootstrap-tab.js"></script>
		
	<script type="text/javascript">
	    $(function(){   
	    	$('#menu').sidebarMenu({ url: "${base}/getMenus"});                	 
	    })
    </script>	
</@override>
<@override name="header">	
<link rel="stylesheet"
	href="${base}/static/css/cssnew2.css" />
<style type="text/css">
a:link {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}

a:hover {
	text-decoration: none;
}

a:active {
	text-decoration: none;
}
a, a:focus, a:hover, a:active, button, button:hover {
    outline: 0 !important;
/*     color: #4e5eff; */
}
.navbar-nav>li>a {
    padding-top: 15px;
    padding-bottom: 15px;
}
.nav>li>a {
    position: relative;
    display: block;
/*     padding: 10px 15px; */
}
.navbar {
    position: relative;
    min-height: 50px;
    margin-bottom: 0px;
   /* background-color: #fff;*/
    border: 1px solid transparent;
    border-bottom: 1px solid #4e5eff;
}
</style>
<div class="navbar navbar-inverse  navbar-static-top">
	<div class="container topxx" style="font-size:12px">
		<ul class="nav navbar-nav navbar-right pull-right">
			<li><a href="/Account/Register" id="registerLink">注册</a></li>
			<li style="position:relative; top:15px">|</li>
			<li><a href="/Account/Login" id="loginLink">登录</a></li>
		</ul>
	</div>
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/"> </a>
		</div>
		<div class="navbar-collapse collapse" >
			<ul class="nav navbar-nav navbar-right">
				<li><a href="Index" style="color: #9d9d9d;;font-size: 18px;line-height: 1.8;">网站首页</a></li>
				<li><a href="admin/file/toUploadFile" style="color: #9d9d9d;;font-size: 18px;line-height: 1.8;">文件上传</a></li>
				<li><a href="admin/file/index" style="color: #9d9d9d;;font-size: 18px;line-height: 1.8;">文件管理</a></li>
				<li><a href="analysisData" style="color: #9d9d9d;;font-size: 18px;line-height: 1.8;">图表管理</a></li>
			</ul>
		</div>
	</div>
</div>
</@override>
<@override name="content_left">
    <div class="sidebar" id="sidebar">
	    <ul class="nav nav-list">
	        <li>
	            <a href="#" class="dropdown-toggle">
	                <i class="icon-desktop"></i>
	                <span class="menu-text">参数分组</span>
	                <b class="arrow icon-angle-down"></b>
	            </a>	
	
	            <ul class="nav nav-list" id="menu"></ul>
	        </li>
	
	    </ul><!-- /.nav-list -->
	
	    <div class="sidebar-collapse" id="sidebar-collapse">
	        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
	    </div>	
	</div>
</@override>

<@override name="content_right">
	<div class="main-content">
		<div class="page-content">
         	<div class="row" style="margin:0px !important">
	           <div class="col-xs-12">
	             <ul class="nav nav-tabs" role="tablist">
	               <li class="active"><a href="#Index" role="tab" data-toggle="tab">首页</a></li>
	             </ul>
	             <div class="tab-content" style="height:550px !important">
	               <div role="tabpanel" class="tab-pane active" id="Index"></div>
	             </div>
	           </div>
	         </div>
		 </div>
	</div>
</@override>	
<@extends name="/secondStyle/baseNew.ftl"/>