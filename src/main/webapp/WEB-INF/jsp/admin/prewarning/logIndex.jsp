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
<!-- 弹出框 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.css">
<script
	src="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.min.js"></script>

<script
	src="<%=request.getContextPath()%>/static/content/js/outlook2.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/content/js/validDate.js"></script>

<!-- 表单验证 -->
<!--     <link rel="stylesheet" href="<%=request.getContextPath()%>/static/content/bootstrapValidator/vendor/bootstrap/css/bootstrap.css"/> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/content/bootstrapValidator/dist/css/bootstrapValidator.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/content/bootstrapValidator/vendor/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/content/bootstrapValidator/dist/js/bootstrapValidator.js"></script>

<script
	src="${pageContext.request.contextPath}/static/assets/js/bootstrap.min.js"></script>
<!-- 时间选择器 -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/static/content/jeDate/jedate/skin/jedate.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/content/jeDate/jedate/jedate.js"></script>

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

.widget-toolbar>a {
	font-size: 36px;
	margin: 0 1px;
	display: inline-block;
	padding: 0;
	/*     line-height: 24px; */
}

.form-group {
	margin-bottom: 0px;
}

.form-group>label[class*="col-"] {
	padding-top: 2px;
	margin-bottom: 0px;
}
</style>
<script type="text/javascript">
	$(function() {
		$('#change-search-box').click();
	});
</script>
</head>
<body>
	<div class="main-content">
		<div class="page-content">
			<div class="page-header" style="margin: 0px; float: left;">
				<h1>预警信息列表</h1>
			</div>
			<!-- /.page-header -->

			<div>
				<div class="col-xs-12 col-sm-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="widget-box">
						<div class="widget-header" id="change-search-box"
							data-action="collapse">
							<h4>搜索</h4>
							<div class="widget-toolbar">
								<a href="javascript:void(0);"> <i class="icon-chevron-up"></i>
								</a>
							</div>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<!-- 搜索form -->
								<form id="searchLogForm" action="" class="form-horizontal"
									role="form">
									<div class="space-1"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right"
											for="search-series"> 星系 </label>
										<div class="col-sm-9">
											<select class="col-xs-10 col-sm-5" id="search-series"
												name="series">
												<option value="">--请选择--</option>
												<option value="1">j9</option>
											</select>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right"
											for="search-parameterType"> 设备 </label>
										<div class="col-sm-9">
											<select class="col-xs-10 col-sm-5" id="search-parameterType"
												name="parameterType">
												<option value="">--请选择--</option>
												<option value="flywheel">飞轮</option>
												<option value="top">陀螺</option>
											</select>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right"
											for="search-warningType"> 预警类型 </label>
										<div class="col-sm-9">
											<select class="col-xs-10 col-sm-5" id="search-warningType"
												name="warningType">
												<option value="">--请选择--</option>
												<option value="0">特殊工况</option>
												<option value="1">异常</option>
											</select>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right"
											for="search-createdatetimeStart"> 创建开始时间 </label>
										<div class="col-sm-9">
											<input type="text" id="search-createdatetimeStart"
												name="createdatetimeStart" placeholder="创建开始时间"
												class="col-xs-10 col-sm-5" />
											<div id="getBeginTime"></div>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right"
											for="search-createdatetimeEnd"> 创建结束时间 </label>
										<div class="col-sm-9">
											<input type="text" id="search-createdatetimeEnd"
												name="createdatetimeEnd" placeholder="创建结束时间"
												class="col-xs-10 col-sm-5" />
											<div id="getEndTime"></div>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<div class="col-lg-4 col-lg-offset-6">
											<button type="button" id="btn-search"
												class="btn btn-primary start">
												<i></i> <span>搜索</span>
											</button>
											<button type="reset" class="btn btn-warning cancel">
												<i></i> <span>取消</span>
											</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- PAGE CONTENT ENDS -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->

			<div id="content" region="center" title="预警信息"
				style="overflow: hidden"></div>
			<table id="logList" border="false" width="100%" height="500px">
			</table>

			<!-- /.page-header -->
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
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
jeDate({
	dateCell:"#search-createdatetimeStart",//直接显示日期层的容器，可以是ID  CLASS
	format:"YYYY-MM-DD hh:mm:ss",//日期格式
	isinitVal:false, //是否初始化时间
	festival:false, //是否显示节日
	isTime:true, //是否开启时间选择
	//minDate:"2014-09-19 00:00:00",//最小日期
	maxDate:jeDate.now(0), //设定最大日期为当前日期
});
jeDate({
	dateCell:"#search-createdatetimeEnd",//直接显示日期层的容器，可以是ID  CLASS
	format:"YYYY-MM-DD hh:mm:ss",//日期格式
	isinitVal:false, //是否初始化时间
	festival:false, //是否显示节日
	isTime:true, //是否开启时间选择
	//minDate:"2014-09-19 00:00:00",//最小日期
	maxDate:jeDate.now(0), //设定最大日期为当前日期
});
		var hadRead = '${hadRead}';
		var logGrid;
        $(function () {
        	logGrid = $("#logList").datagrid({
                url: '<%=request.getContextPath()%>/admin/prewarning/getLogList?hadRead='
										+ hadRead,
								rownumbers : true,
								fitColumns : true,
								idField : 'logId',//'logId',
								pageSize : 10,
								pagination : true,
								pageList : [ 10, 20, 30, 40, 50, 60, 70, 80,
										90, 100 ],
								onLoadError : function(data) {
									$.messager.alert("预警信息", "暂无预警数据信息",
											"error");

								},
								frozenColumns : [ [ {
									title : 'logId',
									field : 'logId',//'logId',
									width : 50,
									checkbox : true

								} ] ],
								columns : [ [ {
									field : 'series',
									title : '星系',
									width : 100,
								}, {
									field : 'parameterType',
									title : '设备',
									width : 100,
								}, {
									field : 'parameter',
									title : '参数',
									width : 100,
								}, {
									field : 'timeValue',
									title : '时间点',
									width : 100,
								}, {
									field : 'paramValue',
									title : '参数值',
									width : 100
								}, {
									field : 'warningType',
									title : '预警类型',
									width : 100
								} ] ],

								toolbar : [ {
									text : '删除',
									iconCls : 'icon-remove',
									handler : function() {
										deleteLog();
									}
								} ]
							});

		});

		function reloadDataGrid() {
			logGrid.datagrid('clearChecked');
			logGrid.datagrid('reload');
		}

		//快速搜索按钮
		$('#btn-search').click(function() {
			var series = $('#search-series').val();
			var parameterType = $('#search-parameterType').val();
			var warningType = $('#search-warningType').val();
			var createdatetimeStart = $('#search-createdatetimeStart').val();
			var createdatetimeEnd = $('#search-createdatetimeEnd').val();
			logGrid.datagrid('load', {
				series : series,
				parameterType : parameterType,
				warningType : warningType,
				createdatetimeStart : createdatetimeStart,
				createdatetimeEnd : createdatetimeEnd,
			});
		});
		//删除用户
		function deleteLog() {
			var ids = [];
			var rows = logGrid.datagrid('getSelections');
			if (rows.length > 0) {
				swal(
						{
							title : "你是否确定删除?",
							text : "确认删除？",
							type : "warning",
							showCancelButton : true,
							confirmButtonColor : "#DD6B55",
							confirmButtonText : "删除!",
							cancelButtonText : "取消!",
							closeOnConfirm : false,
							closeOnCancel : false
						},
						function(isConfirm) {
							if (isConfirm) {
								for (var i = 0; i < rows.length; i++) {
									ids.push(rows[i].logId);
								}
								$
										.ajax({
											url : '${pageContext.request.contextPath}/admin/prewarning/deleteLog',
											data : {
												logIds : ids.join(',')
											},
											cache : false,
											dataType : "json",
											success : function(data) {
												if (data.success) {
													swal("删除成功!", "", "success");
													reloadDataGrid();
												} else {
													swal("删除失败", data.obj,
															"error");
												}
											}
										});
							} else {
								swal("取消删除", "", "error");
							}
						});
			} else {
				top.showMsg("提示", "请选择要删除的用户！");
			}
		}
		function getSelectId() {
			var row = logGrid.datagrid('getSelected');

			if (!row) {
				$.messager.show({
					title : '提示',
					msg : '请选择数据！',
					showType : 'show'
				});
				return null;
			} else {
				return row.Id;
			}
		}

		function clearFun() {
			$('#frmSearchLog').form('clear');
		}
	</script>
</body>
</html>