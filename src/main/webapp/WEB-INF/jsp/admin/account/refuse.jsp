<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'refuse.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<div class="main-content">
		<!-- 		<div class="breadcrumbs" id="breadcrumbs"> -->
		<!-- 			<script type="text/javascript"> -->
		<!-- 				try { -->
		<!-- 					ace.settings.check('breadcrumbs', 'fixed') -->
		<!-- 				} catch (e) { -->
		<!-- 				} -->
		<!-- 			</script> -->
		<!-- 			<ul class="breadcrumb"> -->
		<!-- 				<li><i class="icon-home home-icon"></i> <a href="javascript:void(0);">首页</a></li> -->
		<!-- 				<li class="active">欢迎页面</li> -->
		<!-- 			</ul>.breadcrumb -->
		<!-- 			<div class="nav-search" id="nav-search"> -->
		<!-- 				<form class="form-search"> -->
		<!-- 					<span class="input-icon"> <input type="text" -->
		<!-- 						placeholder="Search ..." class="nav-search-input" -->
		<!-- 						id="nav-search-input" autocomplete="off" /> <i -->
		<!-- 						class="icon-search nav-search-icon"></i> -->
		<!-- 					</span> -->
		<!-- 				</form> -->
		<!-- 			</div>#nav-search -->
		<!-- 		</div> -->
		<div class="page-content">
			<div class="page-header">
				<h1>拒绝访问</h1>
			</div>
			<!-- /.page-header -->
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					无权访问！
					<!-- PAGE CONTENT ENDS -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.page-content -->
	</div>
	<!-- /.main-content -->

	<script type="text/javascript">
		$(function() {

		});
	</script>
</body>
</html>
