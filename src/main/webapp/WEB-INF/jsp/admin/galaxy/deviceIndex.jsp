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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/content/jquery-easyui-datagridview/datagrid-detailview.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/css/all.css" type="text/css" />
<!-- 弹出框 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.css">
<script
	src="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.min.js"></script>

<script
	src="<%=request.getContextPath()%>/static/content/js/outlook2.js"
	type="text/javascript"></script>

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
<style type="text/css">
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

.mustchoose {
	padding-top: 5px;
	padding-left: 5px;
	color: red;
}

.form-group input, .form-group select {
	width: 240px;
	height: 30px;
	line-height: 30px;
	text-align: left;
}
</style>
<script type="text/javascript">
	var activeUser = '${activeUser}';
	$(function() {
		//修改页面缩放，界面显示不正常
		$(".col-lg-4").css("margin-left","33.3%");

		//左菜单栏
		$("#deviceview-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_14.png");
		$("#galaxymanage-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_82.png");
		$("#deviceview-text").css("color", "#5d90d6");
		$("#galaxymanage-text").css("color", "#5d90d6");
		$("#galaxymanageUL").css("display","block");
		
		$.get('<%=request.getContextPath()%>/admin/device/getDeviceTypeList',
				{}, function(data) {
					if (data) {
						$.each(data, function() {
							$("#search-parameterType").append(
									"<option value = '"+ this.deviceTypeId+"'>"
											+ this.deviceName + "</option>");
						});
					}
				});

		//修改搜索框图标
		var flag = false;
		$("#change-search-box").click(function() {
			if (flag) {
				$("#toolimg").attr("src",
								"${pageContext.request.contextPath}/static/imgs/DataImport_manage/xia.png")
				$(".widget-body").slideUp("slow");
				flag = false;
			} else {
				$("#toolimg").attr("src",
								"${pageContext.request.contextPath}/static/imgs/DataImport_manage/xia2.png")
				$(".widget-body").slideDown("slow");
				flag = true;
			}
		});
	});
</script>
</head>
<body>
	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>
			<ul class="breadcrumb" style="margin-top: 10px;">
				<li><img
					src="${pageContext.request.contextPath}/static/imgs/DataImport/home.png"
					style="margin-bottom: 3px;"> <span>星系管理</span></li>
				<li class="active">设备查看</li>
			</ul>
			<!--  .breadcrumb -->
		</div>
		<div class="page-content">
			<!-- /.page-header -->
			<div id="searchFormDiv">
				<div class="col-xs-12 col-sm-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="widget-box">
						<div class="widget-header" id="change-search-box"
							data-action="collapse">
							<h4>搜索</h4>
							<div class="widget-toolbar">
								<div hidden="hidden">
									<i class="icon-chevron-up" hidden="hidden"></i>
								</div>
								<a href="javascript:void(0);"> <img id="toolimg"
									style="margin-top: 3px;"
									src="${pageContext.request.contextPath}/static/imgs/DataImport_manage/xia.png">
								</a>
							</div>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<!-- 搜索form -->
								<form id="searchDeviceForm" action="" class="form-horizontal"
									role="form">
									<div class="space-1"></div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right"
											for="search-parameterType"> 设备类型：</label>
										<div class="col-sm-8">
											<select class="col-xs-10 col-sm-5" id="search-parameterType"
												name="parameterType">
												<option value="">--请选择--</option>
											</select>
											<label class="mustchoose">*</label>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right"
											for="search-model"> 型号：</label>
										<div class="col-sm-8">
											<input type="text" id="search-model" name="model"
												placeholder="请输入型号" class="col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-8"></div>
									<div class="form-group">
										<div class="col-lg-4 col-lg-offset-4">
											<button type="button" id="btn-search" class="subbutton_1">
												<i></i> <span>搜索</span>
											</button>
											<button type="reset" class="cancelbutton_1" id="btn-reset">
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

			<div id="content" region="center" title="设备信息"
				style="overflow: hidden"></div>
			<table id="deviceList" border="false" width="100%" height="500px">
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
		var deviceGrid;
		//快速搜索按钮
		$('#btn-search').click(function() {
			var parameterType = $('#search-parameterType').val();
			if (parameterType == "" || parameterType.length == 0) {
				top.alertMsg('提示', '请选择设备类型！');
				return;
			}
			var model = $('#search-model').val();
			deviceGrid = $('#deviceList').datagrid({
				view : detailview,
				url : '${pageContext.request.contextPath}/admin/deviceIndex/getDeviceIndexPager',
				queryParams : {
					parameterType : parameterType,
					model : model
				},
				rownumbers : true,
				fitColumns : true,
				idField : 'id',//'id',
				toolbar : '#toolbar',
				pageSize : 10,
				pagination : true,
				pageList : [ 10, 20, 30, 40,
						50, 60, 70, 80, 90, 100 ],
				columns : [ [ {
					title : 'seriersId',
					field : 'seriersId',
					hidden : true
				}, {
					title : 'starId',
					field : 'starId',
					hidden : true
				}, {
					title : 'deviceTypeId',
					field : 'deviceTypeId',
					hidden : true
				}, {
					field : 'seriesName',
					title : '星系',
					width : 50,
				}, {
					field : 'starName',
					title : '星号',
					width : 50
				}, {
					field : 'deviceTypeName',
					title : '设备类型',
					width : 80
				}, {
					field : 'deviceNum',
					title : '设备总数',
					width : 80
				}, {
					field : 'runDays',
					title : '设备总运行时间',
					width : 100
				} ] ],
				onLoadError : function(data) {
					$.messager.alert("信息",
							"暂无数据信息", "error");
				},
				detailFormatter : function(
						index, row) {
					return '<div><table id="ddv-' + index + '"></table></div>';
				},
				onExpandRow : function(index,row) {
					var subgridId = 'ddv-'+ index;
					$('#' + subgridId).datagrid({
						url : '${pageContext.request.contextPath}/admin/device/getDevicesByParam?deviceType='
								+ row.deviceTypeId
								+ '&series='
								+ row.seriersId
								+ '&star='
								+ row.starId
								+ '&model='
								+ model,
						fitColumns : true,
						rownumbers : true,
						singleSelect : true,
						loadMsg : '正在载入设备列表，请稍后...',
						height : 'auto',
						columns : [ [
								{
									title : 'ID',
									field : 'deviceId',
									hidden : true,
								},
								{
									title : 'status',
									field : 'runStatus',
									hidden : true,
								},
								{
									field : 'deviceName',
									title : '设备名称',
									width : 50
								},
								{
									field : 'model',
									title : '型号',
									width : 50
								},
								{
									field : 'startDate',
									title : '开始运行时间',
									width : 80
								},
								{
									field : 'endDate',
									title : '结束运行时间',
									width : 80
								},
								{
									field : 'runDays',
									title : '累计工作时间(天)',
									width : 80
								},
								{
									field : 'descreption',
									title : '运行状态',
									width : 80,
									formatter : function(value,row,index) {
										if (row.runStatus == 1) {
											return '运行中';
										} else if (row.runStatus == 0) {
											return '已停止';
										}
									}
								} ] ],
						onResize : function() {
							$('#deviceList').datagrid('fixDetailRowHeight',index);
						},
						onLoadSuccess : function() {
							setTimeout(function() {
										$('#deviceList').datagrid('fixDetailRowHeight',index);
									},
									0);
						}
					});
					$('#deviceList').datagrid('fixDetailRowHeight',index);
				}
			});
		});
	</script>
</body>
</html>
