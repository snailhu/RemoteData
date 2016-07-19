<%@ page language="java" pageEncoding="UTF-8"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/cssnew2.css" />
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