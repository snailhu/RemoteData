<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<jsp:include page="/WEB-INF/jsp/inc/include-easyUI.jsp"></jsp:include>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/css/all.css" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/static/ystep/css/ystep.css">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.css">
<script
	src="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.min.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/content/bootstrapValidator/dist/css/bootstrapValidator.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/content/bootstrapValidator/vendor/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/content/bootstrapValidator/dist/js/bootstrapValidator.js"></script>

<script
	src="${pageContext.request.contextPath}/static/assets/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/assets/js/typeahead-bs2.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/assets/js/bootstrap-colorpicker.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/assets/js/jquery.knob.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/assets/js/jquery.autosize.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/assets/js/jquery.maskedinput.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/assets/js/bootstrap-tag.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/ystep/js/ystep.js"></script>

<style type="text/css">
.sweet-alert h2 {
	color: rgb(87, 87, 87);
	font-size: 30px;
	text-align: center;
	font-weight: 600;
	text-transform: none;
	position: relative;
	line-height: 40px;
	display: block;
	margin: 25px 0px;
	padding: 0px;
}

.sweet-alert p {
	color: rgb(121, 121, 121);
	font-size: 16px;
	font-weight: 300;
	position: relative;
	text-align: inherit;
	float: none;
	line-height: normal;
	margin: 0px;
	padding: 0px;
}

.sweet-alert .sa-error-container {
	background-color: rgb(241, 241, 241);
	margin-left: -17px;
	margin-right: -17px;
	max-height: 0px;
	overflow: hidden;
	padding: 0px 10px;
	transition: padding 0.15s, max-height 0.15s;
}

.sweet-alert button.cancel {
	background-color: rgb(193, 193, 193);
}

.sweet-alert button {
	background-color: rgb(140, 212, 245);
	color: white;
	box-shadow: none;
	font-size: 17px;
	font-weight: 500;
	cursor: pointer;
	border: none;
	border-radius: 5px;
	padding: 10px 32px;
	margin: 26px 30px 0px;
	/*     width: 150px; */
}

.sweet-alert .sa-confirm-button-container {
	display: inline-block;
	position: relative;
	/*     padding-left: 20px; */
}

.icon-remove {
	background: url('') no-repeat center center;
}

.icon-edit {
	background: url('') no-repeat center center;
}

.glyphicon {
	position: relative;
	top: -23px;
	padding-right: 10px;
	display: inline-block;
	font-family: 'Glyphicons Halflings';
	-webkit-font-smoothing: antialiased;
	font-style: normal;
	font-weight: normal;
	line-height: 1;
	float: right;
}

.help-block {
	display: block;
	margin-top: 10px;
	margin-bottom: 0px;
	color: #737373;
}

.ysteptitle {
	font-size: 17px;
	width: 95%;
}

.breadcrumb {
	margin-top: 10px;
}
</style>
<script type="text/javascript">
	$(function() {
		$("#starting-img")
				.attr("src",
						"${pageContext.request.contextPath}/static/new/img/images/a_38.png");
		$("#statustracking-img")
				.attr("src",
						"${pageContext.request.contextPath}/static/new/img/images/a_34.png");
		$("#filemanage-img")
				.attr("src",
						"${pageContext.request.contextPath}/static/new/img/images/a_26.png");
		$("#starting-text").css("color", "#5d90d6");
		$("#statustracking-text").css("color", "#5d90d6");
		$("#filemange-text").css("color", "#5d90d6");
		$("#statustrackingUL").css("display", "block");
		$("#filemanageUL").css("display", "block");
	})
</script>
</head>

<body>
	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs" style="text-align: left;">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>
			<ul class="breadcrumb">
				<li><img
					src="${pageContext.request.contextPath}/static/imgs/DataImport/home.png"
					style="margin-bottom: 3px;"> <span>文件管理</span></li>
				<li class="active">文件状态</li>
				<li class="active">正在进行中</li>
			</ul>
			<!--  .breadcrumb -->
		</div>
		<div class="page-content">
			<!-- 			<div class="page-header" style="padding-bottom: 10px;"> -->
			<!-- 				<h1>正在处理中的流程</h1> -->
			<!-- 			</div> -->
			<div class="row" style="margin-left: 20%;">
				<br /> <br />
				<div class="col-xs-12 col-sm-12" id="ystepDiv"
					style="height: 450px; height: auto;">
					<!-- ystep容器 -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.page-content -->
	</div>
	<!-- /.main-content -->
	<script type="text/javascript">
		var statusYstepList = ${statusYstepList};
		var i = 0;
		$.each(statusYstepList, function() {
			i = i + 1;
			$('#ystepDiv').append(
					"<div><div class='ystep"+i+"'></div><div class='ysteptitle'>"
							+ this.fileName + "</div><br></div>");

			//根据jQuery选择器找到需要加载ystep的容器
			//loadStep 方法可以初始化ystep
			$(".ystep" + i).loadStep({
				//ystep的外观大小
				//可选值：small,large
				size : "large",
				//ystep配色方案
				//可选值：green,blue
				color : "blue",
				//ystep中包含的步骤
				steps : [ {
					title : "开始",
					content : "开始"
				}, {
					//步骤名称
					title : "文件上传",
					//步骤内容(鼠标移动到本步骤节点时，会提示该内容)
					content : "文件上传至服务器"
				}, {
					title : "数据导入",
					content : "文件数据导入后台系统"
				}, {
					title : "预处理",
					content : "将导入的数据进行一系列预处理"
				}, {
					title : "结束",
					content : "结束"
				} ]
			});

			$(".ystep" + i).setStep(this.statusType);
		});
	</script>
</body>
</html>
