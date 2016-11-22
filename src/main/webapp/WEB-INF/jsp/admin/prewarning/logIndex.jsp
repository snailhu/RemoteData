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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/select2/select2.min.css"
	type="text/css" />

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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/select2/select2.full.min.js"></script>

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

</style>
<script type="text/javascript">
	var activeUser = '${activeUser}';
	$(function() {
		//左菜单栏
		$("#warnlog-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_70.png");
		$("#warnmanage-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_58.png");
		$("#warnlog-text").css("color", "#5d90d6");
		$("#warnmanage-text").css("color", "#5d90d6");
		$("#warnmanageUL").css("display","block");
		
// 		if(activeUser != ''){
// 			var permissionItemsJSON = '${activeUser.permissionItemsJSON}';
// 			var map = $.parseJSON(permissionItemsJSON); 
// 			if(map.flywheel == 'flywheel'){
// 				$("#search-parameterType").append("<option value = 'flywheel'>飞轮</option>"); 
// 			}
// 			if(map.top == 'top'){
// 				$("#search-parameterType").append("<option value = 'top'>陀螺</option>"); 
// 			}
// 		}
		$.get('<%=request.getContextPath()%>/admin/device/getDeviceTypeList', {}, function (data) {
	 	 	if(data) {
          	  	$.each(data ,function(){
	          	  	$("#search-parameterType").append("<option value = '"+ this.deviceCode+"'>"+ this.deviceName+"</option>"); 
				});
            }
        });
		
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
		
		$(".select2").select2();
		
		$("#logList").hide();
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
					style="margin-bottom: 3px;"> <span>预警管理</span></li>
				<li class="active">预警查询</li>
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
								<form id="searchLogForm" action="" class="form-horizontal"
									role="form">
									<div class="space-1"></div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right"
											for="search-warningType"> 预警类型 </label>
										<div class="col-sm-8">
											<select class="col-xs-10 col-sm-5" id="search-warningType"
												name="warningType">
												<option value="">--请选择--</option>
												<option value="0">特殊工况</option>
												<option value="1">异常</option>
											</select> <label class="mustchoose">*</label>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right"
											for="search-series"> 星系 </label>
										<div class="col-sm-8">
											<select class="col-xs-10 col-sm-5" id="search-series"
												name="series">
												<option value="">--请选择--</option>
											</select> <label class="mustchoose">*</label>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right"
											for="search-star"> 星</label>
										<div class="col-sm-8">
											<select class="col-xs-10 col-sm-5" id="search-star"
												name="star">
												<option value="">--请选择--</option>
											</select> <label class="mustchoose">*</label>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right"
											for="search-parameterType"> 设备 </label>
										<div class="col-sm-8">
											<select class="col-xs-10 col-sm-5" id="search-parameterType"
												name="parameterType">
												<option value="">--请选择--</option>
											</select> <label class="mustchoose">*</label>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right"
											for="search-parameter"> 参数 </label>
										<div class="col-sm-8">
											<select class="col-xs-10 col-sm-5 select2"
												style="width: 41.7%;" id="search-parameter" name="parameter">
												<option value="">--请选择--</option>
											</select>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right"
											for="search-createdatetimeStart"> 创建开始时间 </label>
										<div class="col-sm-8">
											<input type="text" id="search-createdatetimeStart"
												name="createdatetimeStart" placeholder="创建开始时间"
												class="col-xs-10 col-sm-5" />
											<div id="getBeginTime"></div>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right"
											for="search-createdatetimeEnd"> 创建结束时间 </label>
										<div class="col-sm-8">
											<input type="text" id="search-createdatetimeEnd"
												name="createdatetimeEnd" placeholder="创建结束时间"
												class="col-xs-10 col-sm-5" />
											<div id="getEndTime"></div>
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
			</c:if>

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
		var hadReadFlag = '${hadReadFlag}';
		var hadRead = "";
		if(hadReadFlag == 0){
			$("#searchFormDiv").hide();
			hadRead = 0;
			logGrid = $("#logList").datagrid({
                url: '<%=request.getContextPath()%>/admin/prewarning/getLogList?hadRead='+ hadRead,
				rownumbers : true,
				fitColumns : true,
				idField : 'logId',//'logId',
				pageSize : 10,
				pagination : true,
				pageList : [ 10, 20, 30,
						40, 50, 60, 70, 80,
						90, 100 ],
				onLoadError : function(data) {
					$.messager.alert(
							"预警信息",
							"暂无预警数据信息",
							"error");

				},
				frozenColumns : [ [ {
					title : 'logId',
					field : 'logId',//'logId',
					width : 50,
					checkbox : true
				} ] ],
				columns : [ [
						{
							field : 'series',
							title : '星系',
							width : 100,
						//sortable:true
						},
						{
							field : 'star',
							title : '星',
							width : 100,
						//sortable:true
						},
						{
							field : 'parameterType',
							title : '设备',
							width : 100,
						//sortable:true
						},
						{
							field : 'parameter',
							title : '参数',
							width : 200,
						//sortable:true
						},
						{
							field : 'timeValue',
							title : '时间点',
							width : 200,
						//sortable:true
						},
						{
							field : 'paramValue',
							title : '参数值',
							width : 100,
						//sortable:true
						},
						{
							field : 'warningType',
							title : '预警类型',
							width : 100,
						//sortable:true
						},
						{
							field : 'warnRemark',
							title : '备注',
							width : 300,
						//sortable:true
						} ] ],

				toolbar : [ {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						deleteLog();
					}
				} ]
			});
		}
        $(function () {
        	$.get('<%=request.getContextPath()%>/starParam/getSeriesList', {}, function (res) {
   			  if(res.result == "true") {
               	  $.each(res.data.data ,function(){
   						$('#search-series').append("<option value='"+ this.id+"'>"+ this.name +"</option>"); 
   					});
                 }
                 else {
               	  top.showMsg('提示', res.msg);
                 }
             });
		});
        
        $("#search-series").change(function(){
		 	var seriesId = $('#search-series').val();	
		 	  $.get('<%=request.getContextPath()%>/admin/prewarning/getStarList', {'seriesId':seriesId},  function (res) {
				  if(res.result == "true") {
					  $('#search-star').find("option").remove();
					 $('#search-star').append("<option value=''>--请选择--</option>"); 
	            	  $.each(res.data.data ,function(){
							$('#search-star').append("<option value='"+ this.id+"'>"+ this.name +"</option>"); 
						});
	              }
	              else {
	            	  top.showMsg('提示', res.msg);
	              }
	          });	
		 	  
		 	 var parameterType = $('#search-parameterType').val();
		 	var starId = $('#search-star').val();
			  $.get('<%=request.getContextPath()%>/admin/prewarning/getParamList', {'parameterType':parameterType , 'series':seriesId ,  'star':starId}, function (res) {
				  if(res) {
					  $('#search-parameter').find("option").remove();
					  $('#search-parameter').append("<option value=''>--请选择--</option>"); 
	           	  $.each(res.paramaters ,function(){
	           		    if(this.code){
	           		    	$('#search-parameter').append("<option value='"+ this.code+"'>"+ this.simplyName +"</option>"); 
	           		    }
						});
	           	  $("#search-parameter").select2().val("").trigger("change");
	             }
	             else {
	           	  top.showMsg('提示', res.msg);
	             }
	         });
		});
        
        $("#search-parameterType").change(function(){
		 	var parameterType = $('#search-parameterType').val();	
		 	var seriesId = $('#search-series').val();
		 	var starId = $('#search-star').val();
			  $.get('<%=request.getContextPath()%>/admin/prewarning/getParamList',
											{
												'parameterType' : parameterType,
												'series' : seriesId,
												'star':starId
											},
											function(res) {
												if (res) {
													$('#search-parameter')
															.find("option")
															.remove();
													$('#search-parameter')
															.append(
																	"<option value=''>--请选择--</option>");
													$
															.each(
																	res.paramaters,
																	function() {
																		if (this.code) {
																			$(
																					'#search-parameter')
																					.append(
																							"<option value='"+ this.code+"'>"
																									+ this.simplyName
																									+ "</option>");
																		}
																	});
												} else {
													top.showMsg('提示', res.msg);
												}
											});
						});

		function reloadDataGrid() {
			logGrid.datagrid('clearChecked');
			logGrid.datagrid('reload');
		}

		$('#btn-reset').click(function() {
			$("#search-parameter").select2().val("").trigger("change");
		});

		var clickTotal = 0;
		//快速搜索按钮
		$('#btn-search').click(function() {
			var series = $('#search-series').val();
			var star = $('#search-star').val();
			var parameterType = $('#search-parameterType').val();
			var parameter = $('#search-parameter').val();
			var warningType = $('#search-warningType').val();
			var createdatetimeStart = $('#search-createdatetimeStart').val();
			var createdatetimeEnd = $('#search-createdatetimeEnd').val();
			if (warningType == "") {
				top.alertMsg('提示', '请选择预警类型！');
				return;
			}
			if (series == "") {
				top.alertMsg('提示', '请选择星系！');
				return;
			}
			if (star == "") {
				top.alertMsg('提示', '请选择星！');
				return;
			}
			if (parameterType == "") {
				top.alertMsg('提示', '请选择设备！');
				return;
			}
			if(clickTotal == 0){
				logGrid = $("#logList").datagrid({
	                url: '<%=request.getContextPath()%>/admin/prewarning/getLogList?hadRead='+ hadRead,
					rownumbers : true,
					fitColumns : true,
					idField : 'logId',//'logId',
					pageSize : 10,
					pagination : true,
					pageList : [ 10, 20, 30,
							40, 50, 60, 70, 80,
							90, 100 ],
					onLoadError : function(data) {
						$.messager.alert(
								"预警信息",
								"暂无预警数据信息",
								"error");

					},
					frozenColumns : [ [ {
						title : 'logId',
						field : 'logId',//'logId',
						width : 50,
						checkbox : true
					} ] ],
					columns : [ [
							{
								field : 'series',
								title : '星系',
								width : 100,
							//sortable:true
							},
							{
								field : 'star',
								title : '星',
								width : 100,
							//sortable:true
							},
							{
								field : 'parameterType',
								title : '设备',
								width : 100,
							//sortable:true
							},
							{
								field : 'parameter',
								title : '参数',
								width : 200,
							//sortable:true
							},
							{
								field : 'timeValue',
								title : '时间点',
								width : 200,
							//sortable:true
							},
							{
								field : 'paramValue',
								title : '参数值',
								width : 100,
							//sortable:true
							},
							{
								field : 'warningType',
								title : '预警类型',
								width : 100,
							//sortable:true
							},
							{
								field : 'warnRemark',
								title : '备注',
								width : 300,
							//sortable:true
							} ] ],

					toolbar : [ {
						text : '删除',
						iconCls : 'icon-remove',
						handler : function() {
							deleteLog();
						}
					} ]
				});
				clickTotal = clickTotal + 1;
			}
			logGrid.datagrid('load', {
				series : series,
				star : star,
				parameterType : parameterType,
				parameter : parameter,
				warningType : warningType,
				createdatetimeStart : createdatetimeStart,
				createdatetimeEnd : createdatetimeEnd,
				clickCount : 1
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
							confirmButtonText : "删除",
							cancelButtonText : "取消",
							closeOnConfirm : false,
							closeOnCancel : false
						},
						function(isConfirm) {
							if (isConfirm) {
								var series = "";
								var star = "";
								var parameterType = "";
								var warningType = "";
								for (var i = 0; i < rows.length; i++) {
									ids.push(rows[i].logId);
									series = rows[i].series;
									star = rows[i].star;
									parameterType = rows[i].parameterType;
									series = rows[i].series;
									if (rows[i].warningType == '特殊工况') {
										warningType = "0";
									} else {
										warningType = "1";
									}
								}
								$
										.ajax({
											url : '${pageContext.request.contextPath}/admin/prewarning/deleteLog',
											data : {
												logIds : ids.join(','),
												series : series,
												star : star,
												parameterType : parameterType,
												warningType : warningType
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
				top.showMsg("提示", "请选择要删除的信息！");
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
