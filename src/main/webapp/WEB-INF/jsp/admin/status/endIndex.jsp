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
	border-width: 0;
	padding: 10px 32px;
	margin: 26px 30px 0px;
	height:100%;
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
.form-group input,.form-group select{
	width: 240px;
	height:30px;
	line-height:30px;
	text-align:left;
}
.form-group select{
	color:#858585;
	font-size:14px;
	padding-left:0px;
}
.form-group>label[class*="col-"] {
	padding-top: 2px;
	margin-bottom: 0px;
}

.breadcrumb {
    margin-top: 10px;
}
.detailexp{
	display:block;
	width:80px;
	height:26px;
	line-height:26px;
	color:#000;
	text-align:center;
	overflow:hidden;
}
.detailexp:hover{
	background:#e2e2e2;
	border:1px solid #CCC;
	border-radius:5px 5px 5px 5px;
}
</style>
<script type="text/javascript">
	$(function() {
		//修改页面缩放，界面显示不正常
		$(".modal-dialog").css("margin","20px auto");
		$(".modal-footer").find(".col-lg-4").css("text-align","center");
		$(".form-group").find(".col-lg-4").css("margin-left","33.3%");

		//左菜单栏
		$("#ending-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_42.png");
		$("#statustracking-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_34.png");
		$("#filemanage-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_26.png");
		$("#ending-text").css("color", "#5d90d6");
		$("#statustracking-text").css("color", "#5d90d6");
		$("#filemange-text").css("color", "#5d90d6");
		$("#statustrackingUL").css("display","block");
		$("#filemanageUL").css("display", "block");		
		
		//修改搜索框图标
		var flag=false;
		$("#change-search-box").click(function(){		
			if(flag){
				$("#toolimg").attr("src","${pageContext.request.contextPath}/static/imgs/DataImport_manage/xia.png")
				$(".widget-body").slideUp("slow");
				flag=false;
			}else{
				$("#toolimg").attr("src","${pageContext.request.contextPath}/static/imgs/DataImport_manage/xia2.png")
		 		$(".widget-body").slideDown("slow");
				flag=true;
			}
		});
		$("#change-search-box").click();
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
			<ul class="breadcrumb">
				<li>
					<img src="${pageContext.request.contextPath}/static/imgs/DataImport/home.png" style="margin-bottom: 3px;">
					<span>文件管理</span>
				</li>
				<li class="active">文件状态</li>
				<li class="active">已结束</li>
			</ul><!--  .breadcrumb -->
		</div>
		<div class="page-content">
			<!-- /.page-header -->
			<div>
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
								<form id="searchLogForm" action="" class="form-horizontal"
									role="form">
									<div class="space-1"></div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right"
											for="search-series"> 文件名：</label>
										<div class="col-sm-8">
											<input type="text" id="search-fileName" name="fileName"
												placeholder="--请输入文件名--" class="col-xs-10 col-sm-5" /> </select>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right"
											for="search-status"> 文件状态：</label>
										<div class="col-sm-8">
											<select class="col-xs-10 col-sm-5" id="search-statusType"
												name="statusType">
												<option value="">--请选择文件状态--</option>
												<option value="0">已成功</option>
												<option value="1">失败</option>
											</select>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right"
											for="search-createdatetimeStart"> 创建开始时间：</label>
										<div class="col-sm-8">
											<input type="text" id="search-createdatetimeStart"
												name="createdatetimeStart" placeholder="--请选择创建开始时间--"
												class="col-xs-10 col-sm-5" readonly="true" />
											<div id="getBeginTime"></div>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right"
											for="search-createdatetimeEnd"> 创建结束时间：</label>
										<div class="col-sm-8">
											<input type="text" id="search-createdatetimeEnd"
												name="createdatetimeEnd" placeholder="--请选择创建结束时间--"
												class="col-xs-10 col-sm-5" readonly="true" />
											<div id="getEndTime"></div>
										</div>
									</div>
									<div class="space-8"></div>
									<div class="form-group">
										<div class="col-lg-4 col-lg-offset-4">
											<button type="button" id="btn-search" class="subbutton_1">
												<i></i> <span>搜索</span>
											</button>
											<button type="reset" class="cancelbutton_1">
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

			<!-- 异常详情 -->
			<div class="modal fade" id="exceptionInfoModal" role="dialog"
				aria-labelledby="exceptionInfoModalLabel">
				<div class="modal-dialog" role="document" style="margin: 55px 30%">
					<div class="modal-content">
						<form class="form-horizontal" role="form" style="margin: 0px;">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="exceptionInfoModalLabel">异常信息</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="edit-series"> 详情：</label>
									<div class="col-sm-8">
										<textarea rows="5" cols="20" id="exceptionInfoModalArea"
											class="form-control" readonly="true"></textarea>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<div class="col-lg-4 col-lg-offset-4">
									<button type="button" class="cancelbutton_1"
										id="closeExceptionInfoModal" data-dismiss="modal">关闭</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div id="content" region="center" title="已完成信息"
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
$("#search-createdatetimeStart").keypress(function(){
	  return false;
});
$("#search-createdatetimeEnd").keypress(function(){
	   return false;
});	
	
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
		var logGrid;
        $(function () {
        	logGrid = $("#logList").datagrid({
                url: '<%=request.getContextPath()%>/admin/status/getStatusList',
								rownumbers : true,
								fitColumns : true,
								idField : 'trackingId',//'trackingId',
								pageSize : 10,
								pagination : true,
								pageList : [ 10, 20, 30, 40, 50, 60, 70, 80,
										90, 100 ],
								onLoadError : function(data) {
									$.messager.alert("预警信息", "暂无预警数据信息",
											"error");

								},
								frozenColumns : [ [ {
									title : 'trackingId',
									field : 'trackingId',//'trackingId',
									width : 50,
									checkbox : true
								} ] ],
								columns : [ [ {
									field : 'fileName',
									title : '文件名',
									width : 100,
								//sortable : true
								}, {
									field : 'createDate',
									title : '上传时间',
									width : 100,
								//sortable : true
								}, {
									field : 'statusType',
									title : '状态',
									width : 100,
								//sortable : true
								}, {
									field : 'exceptionInfo',
									title : '操作',
									width : 100,
									formatter : formatOper
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

		function formatOper(val, row, index) {
			if (row.statusType == "结束") {
				return '<input type="hidden" id="search-fileName" value = "'+row.exceptionInfo+'"/>'
			} else {
				return '<a class="detailexp" style="color:#0909df" href="javascript:ShowexceptionInfo('
						+ index
						+ '" onclick="ShowexceptionInfo('
						+ index
						+ ')">异常详情</a><input type="hidden" id="ShowexceptionInfo'+index+'" value = "'+row.exceptionInfo+'"/>';
			}
		}

		function ShowexceptionInfo(index) {
			$('#exceptionInfoModalArea').text(
					$('#ShowexceptionInfo' + index).val());
			$('#exceptionInfoModal').modal('show');
		}

		$('#closeExceptionInfoModal').click(function() {
			$('#exceptionInfoModalArea').text("");
		});

		function reloadDataGrid() {
			logGrid.datagrid('clearChecked');
			logGrid.datagrid('reload');
		}

		//快速搜索按钮
		$('#btn-search').click(function() {
			var fileName = $('#search-fileName').val();
			var statusType = $('#search-statusType').val();
			var createdatetimeStart = $('#search-createdatetimeStart').val();
			var createdatetimeEnd = $('#search-createdatetimeEnd').val();
			logGrid.datagrid('load', {
				fileName : fileName,
				createdatetimeStart : createdatetimeStart,
				createdatetimeEnd : createdatetimeEnd,
				statusType : statusType
			});
		});
		//删除用户
		function deleteLog() {
			var ids = [];
			var rows = logGrid.datagrid('getSelections');
			if (rows.length > 0) {
				swal(
						{
							title : "您是否确定删除？",
							//text : "确认删除？", 
							type : "warning",
							showCancelButton : true,
							confirmButtonColor : "#DD6B55",
							confirmButtonText : "删除",
							cancelButtonText : "取消",
							closeOnConfirm : false,
							closeOnCancel : false
						},
						function(isConfirm) {
							if (isConfirm) {
								for (var i = 0; i < rows.length; i++) {
									ids.push(rows[i].trackingId);
								}
								$
										.ajax({
											url : '${pageContext.request.contextPath}/admin/status/deleteStatus',
											data : {
												trackingIds : ids.join(',')
											},
											cache : false,
											dataType : "json",
											success : function(data) {
												if (data.success) {
													swal("删除成功", "", "success");
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
				top.showMsg("提示", "请选择要删除的记录！");
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

// 		function clearFun() {
// 			$('#frmSearchLog').form('clear');
// 		}
	</script>
</body>
</html>
