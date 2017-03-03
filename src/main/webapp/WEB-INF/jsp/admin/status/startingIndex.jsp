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
<!-- <link rel="stylesheet" -->
<%-- 	href="<%=request.getContextPath()%>/static/css/all.css" type="text/css" /> --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/statusstep/css/stateTracking.css">
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
				<li class="active">进行中</li>
			</ul>
			<!--  .breadcrumb -->
		</div>
		<div class="page-content">
			<div class="right_content" id="stautsstep"></div>
		</div>
		<!-- /.page-content -->
	</div>
	<!-- /.main-content -->
	<script type="text/javascript">
		function getstatus1Div(filename) {
			var status1Div = '<div class="status1"><p>'+filename+'</p>'
					+'<div class="status-box1">\
						<div class="status-box1-1">\
							<div class="statu-l">\
								<img src="${pageContext.request.contextPath}/static/statusstep/img/image/01c.png">\
								<span class="spacing-1">开始</span>\
								<img src="${pageContext.request.contextPath}/static/statusstep/img/image/01y.png">\
							</div>\
						</div>\
						<div class="statu-r">\
							<img src="${pageContext.request.contextPath}/static/statusstep/img/image/02s.png">\
							<span>文件上传</span>\
							<img src="${pageContext.request.contextPath}/static/statusstep/img/image/02ys.png">\
						</div>\
						<div class="statu-r">\
							<img src="${pageContext.request.contextPath}/static/statusstep/img/image/03s.png">\
							<span>数据导入</span>\
							<img src="${pageContext.request.contextPath}/static/statusstep/img/image/03ys.png">\
						</div>\
						<div class="statu-r">\
							<img src="${pageContext.request.contextPath}/static/statusstep/img/image/04s.png">\
							<span class="spacing-4">预处理</span>\
							<img src="${pageContext.request.contextPath}/static/statusstep/img/image/04ys.png">\
						</div>\
						<div class="statu-r">\
							<img src="${pageContext.request.contextPath}/static/statusstep/img/image/05s.png">\
							<span class="spacing-1">结束</span>\
							<img src="${pageContext.request.contextPath}/static/statusstep/img/image/05ys.png">\
						</div>\
					</div>\
				</div>';
			return status1Div;
		}
		function getstatus2Div(filename) {
			var status2Div = '<div class="status2"><p>'+filename+'</p>'
						+'<div class="status-box2">\
							<div class="status-box2-1">\
								<div class="statu-l">\
									<img src="${pageContext.request.contextPath}/static/statusstep/img/image/01c.png">\
									<span class="spacing-1">开始</span>\
									<img src="${pageContext.request.contextPath}/static/statusstep/img/image/01y.png">\
								</div>\
								<div class="statu-l">\
									<img src="${pageContext.request.contextPath}/static/statusstep/img/image/02c.png">\
									<span>文件上传</span>\
									<img src="${pageContext.request.contextPath}/static/statusstep/img/image/02y.png">\
								</div>\
							</div>\
							<div class="statu-r">\
								<img src="${pageContext.request.contextPath}/static/statusstep/img/image/03s.png">\
								<span>数据导入</span>\
								<img src="${pageContext.request.contextPath}/static/statusstep/img/image/03ys.png">\
							</div>\
							<div class="statu-r">\
								<img src="${pageContext.request.contextPath}/static/statusstep/img/image/04s.png">\
								<span class="spacing-4">预处理</span>\
								<img src="${pageContext.request.contextPath}/static/statusstep/img/image/04ys.png">\
							</div>\
							<div class="statu-r">\
								<img src="${pageContext.request.contextPath}/static/statusstep/img/image/05s.png">\
								<span class="spacing-1">结束</span>\
								<img src="${pageContext.request.contextPath}/static/statusstep/img/image/05ys.png">\
							</div>\
						</div>\
					</div>';
			return status2Div;
		}
		function getstatus3Div(filename) {
			var status3Div = '<div class="status3"><p>'+filename+'</p>'
						+'<div class="status-box3">\
							<div class="status-box3-1">\
								<div class="statu-l">\
									<img src="${pageContext.request.contextPath}/static/statusstep/img/image/01c.png">\
									<span class="spacing-1">开始</span>\
									<img src="${pageContext.request.contextPath}/static/statusstep/img/image/01y.png">\
								</div>\
								<div class="statu-l">\
									<img src="${pageContext.request.contextPath}/static/statusstep/img/image/02c.png">\
									<span>文件上传</span>\
									<img src="${pageContext.request.contextPath}/static/statusstep/img/image/02y.png">\
								</div>\
								<div class="statu-l">\
									<img src="${pageContext.request.contextPath}/static/statusstep/img/image/03c.png">\
									<span>数据导入</span>\
									<img src="${pageContext.request.contextPath}/static/statusstep/img/image/03y.png">\
								</div>\
							</div>\
							<div class="statu-r">\
								<img src="${pageContext.request.contextPath}/static/statusstep/img/image/04s.png">\
								<span class="spacing-4">预处理</span>\
								<img src="${pageContext.request.contextPath}/static/statusstep/img/image/04ys.png">\
							</div>\
							<div class="statu-r">\
								<img src="${pageContext.request.contextPath}/static/statusstep/img/image/05s.png">\
								<span class="spacing-1">结束</span>\
								<img src="${pageContext.request.contextPath}/static/statusstep/img/image/05ys.png">\
							</div>\
						</div>\
					</div>';
			return status3Div;
		}
		function getstatus4Div(filename) {
			var status4Div = '<div class="status4"><p>'+filename+'</p>'
						+'<div class="status-box4">\
							<div class="status-box4-1">\
								<div class="statu-l">\
									<img src="${pageContext.request.contextPath}/static/statusstep/img/image/01c.png">\
									<span class="spacing-1">开始</span>\
									<img src="${pageContext.request.contextPath}/static/statusstep/img/image/01y.png">\
								</div>\
								<div class="statu-l">\
									<img src="${pageContext.request.contextPath}/static/statusstep/img/image/02c.png">\
									<span>文件上传</span>\
									<img src="${pageContext.request.contextPath}/static/statusstep/img/image/02y.png">\
								</div>\
								<div class="statu-l">\
									<img src="${pageContext.request.contextPath}/static/statusstep/img/image/03c.png">\
									<span>数据导入</span>\
									<img src="${pageContext.request.contextPath}/static/statusstep/img/image/03y.png">\
								</div>\
								<div class="statu-l">\
									<img src="${pageContext.request.contextPath}/static/statusstep/img/image/04c.png">\
									<span class="spacing-4">预处理</span>\
									<img src="${pageContext.request.contextPath}/static/statusstep/img/image/04y.png">\
								</div>\
							</div>\
							<div class="statu-r">\
								<img src="${pageContext.request.contextPath}/static/statusstep/img/image/05s.png">\
								<span class="spacing-1">结束</span>\
								<img src="${pageContext.request.contextPath}/static/statusstep/img/image/05ys.png">\
							</div>\
						</div>\
					</div>';
			return status4Div;
		}

		var statusYstepList = ${statusYstepList};
		var statusDiv = "";
		var sumYstep = 0;
		$.each(statusYstepList, function() {
			if(this.statusType == '1'){
				statusDiv = getstatus1Div(this.fileName);
				sumYstep = sumYstep + 1;
			}
			if(this.statusType == '2'){
				statusDiv = getstatus2Div(this.fileName);
				sumYstep = sumYstep + 1;
			}
			if(this.statusType == '3'){
				statusDiv = getstatus3Div(this.fileName);
				sumYstep = sumYstep + 1;
			}
			if(this.statusType == '4'){
				statusDiv = getstatus4Div(this.fileName);
				sumYstep = sumYstep + 1;
			}
			$('#stautsstep').append(statusDiv);
		});
		if(sumYstep == 0){
			$('#stautsstep').append('<div class="status1"><p>当前无进行中的文件</p></div>');
		}
	</script>
</body>
</html>
