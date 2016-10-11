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
<script type="text/javascript">
var activeUser = '${activeUser}';
$(function () {
	if(activeUser != ''){
		$('#login-nav').empty();
		$('#login-nav').append("<li> <a href='javascript:void(0)' id='loginLink'> 欢迎光临,${activeUser.userName} </a> </li>");
		$('#login-nav').append("<li style='position:relative; top:15px'><a style='margin-top: -15px;' href='${pageContext.request.contextPath}/loginOut' id='loginOutLink'> |&nbsp; 注销 </a></li>");
	}
});
</script>
<div class="navbar navbar-inverse  navbar-static-top">
	<div class="container topxx" style="font-size:12px">
		<ul class="nav navbar-nav navbar-right pull-right" id="login-nav">
<!-- 			<li><a href="/Account/Register" id="registerLink">注册</a></li> -->
<!-- 			<li style="position:relative; top:15px">|</li> -->
			<li><a href="login" id="loginLink">登录</a></li>
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
				<li><a href="${pageContext.request.contextPath}/Index" style="color: #9d9d9d;;font-size: 18px;line-height: 1.8;">网站首页</a></li>
				<li><a href="${pageContext.request.contextPath}/report/index" style="color: #9d9d9d;;font-size: 18px;line-height: 1.8;">报告管理</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/file/index" style="color: #9d9d9d;;font-size: 18px;line-height: 1.8;">文件管理</a></li>
				<li><a href="${pageContext.request.contextPath}/conditionMonitoring" style="color: #9d9d9d;;font-size: 18px;line-height: 1.8;">数据分析</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/galaxy/index" style="color: #9d9d9d;;font-size: 18px;line-height: 1.8;">星系管理</a></li>
			</ul>
		</div>
	</div>
</div>