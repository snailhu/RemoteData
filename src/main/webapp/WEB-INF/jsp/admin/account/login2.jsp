<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>登录页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<%@include file="/WEB-INF/jsp/layouts/admin-include-public.jsp"%>
<script src="<%=request.getContextPath()%>/static/content/js/outlook2.js" type="text/javascript"></script>

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style type="text/css">
.row {
	margin-right: -12px;
	margin-left: -12px;
	margin-top: 100px;
}
</style>
<script type="text/javascript">
	$(function() {

		$("#uname").blur(function() {
			$("#returnMsg").empty();
		});
		$("#upassword").blur(function() {
			$("#vpassword").removeClass();
		});
		$("#submitForm").click(function() {
			chekUser();
		});
		$("#upassword").keydown(function(event) {
			if (event.keyCode == 13) {
				chekUser();
			}
		});
		function chekUser() {
			var username = $("#uname").val();
			if (username == "") {
				$("#returnMsg").html("<img src='${pageContext.request.contextPath}/static/imgs/error.png'/><font color='red'>请输入用户名</font>");
			} else {
				$("#returnMsg").empty();
				var upass = $("#upassword").val();
				if (upass == "") {
					$("#returnMsg").html("<img src='${pageContext.request.contextPath}/static/imgs/error.png'/><font color='red'>请输入密码</font>");
				} else {
					$("#loginForm").submit();
				}
			}
		}
		
	});
	$(document).ready(function() {
		var loginFlag = '${loginFlag}';
		if (loginFlag == -1) {
			$("#returnMsg").html("<img src='${pageContext.request.contextPath}/static/imgs/error.png'/><font color='red'>请输入正确的用户</font>");
		}
		if (loginFlag == 1) {
			$("#returnMsg").html("<img src='${pageContext.request.contextPath}/static/imgs/error.png'/><font color='red'>请输入正确的密码</font>");
		}
	});
	
</script>
</head>
<body class="login-layout" style="overflow: hidden;">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1>
								<i class="icon-leaf green"></i> <span class="red">产品在轨数据分析平台</span>
							</h1>
							<h4 class="blue">&copy; TYCC</h4>
						</div>
						<div class="space-6"></div>
						<div class="position-relative">
							<div id="login-box"
								class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="icon-coffee green"></i> 用户登录
										</h4>
										<div class="space-6"></div>
										<form id="loginForm" action="login" method="post">
											<label class="block clearfix">
												<span class="block input-icon input-icon-right"> 
													<input type="text" class="form-control" id="uname" name="username"
													value="${username}" placeholder="用户名" /> 
													<i class="icon-user" id="vuname"></i>
												</span>
											</label>
											<div class="space"></div>
											<label class="block clearfix"> 
												<span class="block input-icon input-icon-right"> 
													<input type="password" class="form-control" id="upassword"
													name="password" value="${upassword}" placeholder="密码" /> 
													<i class="icon-lock" id="vpassword"></i>
												</span>
											</label>
											<div>
												<span id="returnMsg"></span>
											</div>
											<div class="space"></div>
											<div class="clearfix">
												<!--  
												<label class="inline"> <input type="checkbox"
													class="ace" name="rememberMe" value="1" /> <span
													class="lbl">记住我</span>
												</label>
												-->
												<button type="button" class="width-35 pull-right btn btn-sm btn-primary" id="submitForm">
													<i class="icon-key"></i> 登录
												</button>
											</div>
											<div class="space-4"></div>
										</form>
									</div>
									<!-- /widget-main -->
								</div>
								<!-- /widget-body -->
							</div>
							<!-- /login-box -->
						</div>
						<!-- /position-relative -->
					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
	</div>
	<!-- /.main-container -->

</body>
</html>
