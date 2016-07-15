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
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/">网站首页</a></li>
				<li><a href="/Home/Web">网站开发</a></li>
				<li><a href="/Home/App">APP开发</a></li>
				<li><a href="/Home/Contact">美银团队</a></li>
				<li><a href="/Home/Iflve">开发报价</a></li>
				<li><a href="/Home/Hongbao">试用下载</a></li>
			</ul>
		</div>
	</div>
</div>