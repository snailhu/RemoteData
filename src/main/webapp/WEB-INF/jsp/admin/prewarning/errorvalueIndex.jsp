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


<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/jqwidgets/styles/jqx.base.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/jqwidgets/styles/jqx.energyblue.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/content/css/default.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/select2/select2.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/css/all.css" type="text/css" />

<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/jqwidgets/jqxcore.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/jqwidgets/jqxdatetimeinput.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/jqwidgets/jqxcalendar.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/jqwidgets/jqxtooltip.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/jqwidgets/globalization/globalize.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/jqwidgets/jqxdata.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/jqwidgets/jqxbuttons.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/jqwidgets/jqxscrollbar.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/jqwidgets/jqxmenu.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/jqwidgets/jqxcheckbox.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/jqwidgets/jqxlistbox.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/jqwidgets/jqxdropdownlist.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/scripts/demos.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/jqwidgets/jqxdatatable.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/jqwidgets/jqxtreegrid.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/jqwidgets/jqxbuttons.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/select2/select2.full.min.js"></script>

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

.breadcrumb {
    margin-top: 10px;
}
</style>
<script type="text/javascript">
	$(function() {
		$('#addValueInfoForm').bootstrapValidator({
			message : '这个值不能为空！',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				series : {
					validators : {
						notEmpty : {
							message : '请选择星系！'
						}
					}
				},star : {
					validators : {
						notEmpty : {
							message : '请选择星！'
						}
					}
				},
				parameterType : {
					validators : {
						notEmpty : {
							message : '请选择设备'
						}
					}
				},
				parameter : {
					validators : {
						notEmpty : {
							message : '参数不能为空'
						}
					}
				},
				maxVal : {
					validators : {
						notEmpty : {
							message : '最大值不能为空'
						},
						numeric : {
							message : '最大值必须为数字'
						}
					}
				},
				minVal : {
					validators : {
						notEmpty : {
							message : '最小值不能为空'
						},
						numeric : {
							message : '最小值必须为数字'
						}
					}
				}
			}
		});
		$('#editValueInfoForm').bootstrapValidator({
			message : '这个值不能为空！',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				series : {
					validators : {
						notEmpty : {
							message : '请选择星系！'
						}
					}
				},
				star : {
					validators : {
						notEmpty : {
							message : '请选择星！'
						}
					}
				},
				parameterType : {
					validators : {
						notEmpty : {
							message : '请选择设备'
						}
					}
				},
				parameter : {
					validators : {
						notEmpty : {
							message : '参数不能为空'
						}
					}
				},
				maxVal : {
					validators : {
						notEmpty : {
							message : '最大值不能为空'
						},
						numeric : {
							message : '最大值必须为数字'
						}
					}
				},
				minVal : {
					validators : {
						notEmpty : {
							message : '最小值不能为空'
						},
						numeric : {
							message : '最小值必须为数字'
						}
					}
				}
			}
		});
		$('#addValueModal').on('hide.bs.modal', function () {
			$("#add-parameter").select2().val("").trigger("change");
			$('#addValueInfoForm').data('bootstrapValidator').resetForm(true);
		})
		$('#editValueModal').on('hide.bs.modal', function () {
			$('#editValueInfoForm').data('bootstrapValidator').resetForm(true);
		})
		$('#btn-reset').click(function(){
			$("#search-parameter").select2().val("").trigger("change");
		});
		
		
// 		$('#vss').click(function() {
// 			$('#addValueInfoForm').bootstrapValidator('validate');
// 		});
		$('#change-search-box').click();
		
		$.fn.modal.Constructor.prototype.enforceFocus = function() {};
		$(".select2").select2();
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
					<span>预警管理</span>
				</li>
				<li class="active">异常参数配置</li>
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
								<a href="javascript:void(0);"> <i class="icon-chevron-up"></i>
								</a>
							</div>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<!-- 搜索form -->
								<form id="searchValueForm" action="" class="form-horizontal"
									role="form">
									<div class="space-1"></div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right"
											for="search-series"> 星系 </label>
										<div class="col-sm-8">
											<select class="col-xs-10 col-sm-5" id="search-series"
												name="series">
												<option value="">--请选择--</option>
											</select>
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
											</select>
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
												<option value="flywheel">飞轮</option>
												<option value="top">陀螺</option>
											</select>
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

			<div id="content" region="center" title="参数信息"
				style="overflow: hidden"></div>
			<table id="valueList" border="false" width="100%" height="500px">
			</table>

			<!-- 创建参数 -->
			<div class="modal fade" id="addValueModal" role="dialog"
				aria-labelledby="addValueModalLabel">
				<div class="modal-dialog" role="document" style="margin: 55px 30%">
					<div class="modal-content">
						<form id="addValueInfoForm" class="form-horizontal" role="form"
							style="margin: 0px;">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="addValueModalLabel">参数信息</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="add-series"> 星系: </label>
									<div class="col-sm-8">
										<select class="form-control" id="add-series" name="series">
											<option value="">--请选择--</option>
										</select>
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="add-star"> 星: </label>
									<div class="col-sm-8">
										<select class="form-control" id="add-star" name="star">
											<option value="">--请选择--</option>
										</select>
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="add-parameterType"> 设备： </label>
									<div class="col-sm-8">
										<select class="form-control" id="add-parameterType"
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
										for="add-parameter"> 参数： </label>
									<div class="col-sm-8">
										<select class="form-control select2" style="width: 100%;"
											id="add-parameter" name="parameter">
											<option value="">--请选择--</option>
										</select>
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-lg-3 control-label no-padding-right"
										for="add-maxVal"> 最大值： </label>
									<div class="col-sm-8">
										<input type="text" name="maxVal" id="add-maxVal"
											placeholder="最大值" class="form-control" />
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-lg-3 control-label no-padding-right"
										for="add-minVal"> 最小值： </label>
									<div class="col-sm-8">
										<input type="text" name="minVal" id="add-minVal"
											placeholder="最小值" class="form-control" />
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<div class="col-lg-7 col-lg-offset-3">
									<button type="button" class="subbutton_1"
										id="submit_addValueInfo">确定</button>
									<button type="button" class="cancelbutton_1"
										id="reset_addValueInfo" data-dismiss="modal">关闭</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 编辑参数 -->
			<div class="modal fade" id="editValueModal" role="dialog"
				aria-labelledby="editValueModalLabel">
				<div class="modal-dialog" role="document" style="margin: 55px 30%">
					<div class="modal-content">
						<form id="editValueInfoForm" class="form-horizontal" role="form"
							style="margin: 0px;">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="editValueModalLabel">参数信息</h4>
							</div>
							<div class="modal-body">
								<input type="hidden" name="valueId" id="edit-value-id" />
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="edit-series"> 星系: </label>
									<div class="col-sm-8">
										<select class="form-control" id="edit-series" name="series">
											<option value="">--请选择--</option>
										</select>
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="edit-star"> 星: </label>
									<div class="col-sm-8">
										<select class="form-control" id="edit-star" name="star">
											<option value="">--请选择--</option>
										</select>
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="edit-parameterType"> 设备： </label>
									<div class="col-sm-8">
										<select class="form-control" id="edit-parameterType"
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
										for="edit-parameter"> 参数： </label>
									<div class="col-sm-8">
										<select class="form-control select2" style="width: 100%;"
											id="edit-parameter" name="parameter">
											<option value="">--请选择--</option>
										</select>
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-lg-3 control-label no-padding-right"
										for="edit-maxVal"> 最大值： </label>
									<div class="col-sm-8">
										<input type="text" name="maxVal" id="edit-maxVal"
											placeholder="最大值" class="form-control" />
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-lg-3 control-label no-padding-right"
										for="edit-minVal"> 最小值： </label>
									<div class="col-sm-8">
										<input type="text" name="minVal" id="edit-minVal"
											placeholder="最小值" class="form-control" />
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<div class="col-lg-7 col-lg-offset-3">
									<button type="button" class="subbutton_1"
										id="submit_editValueInfo">确定</button>
									<button type="button" class="cancelbutton_1"
										id="reset_editValueInfo" data-dismiss="modal">关闭</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

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
		var valueGrid;
        $(function () {
        	valueGrid = $("#valueList").datagrid({
                url: '<%=request.getContextPath()%>/admin/prewarning/getValueList?warningType=1',
								rownumbers : true,
								fitColumns : true,
								idField : 'valueId',//'valueId',
								pageSize : 10,
								pagination : true,
								pageList : [ 10, 20, 30, 40, 50, 60, 70, 80,
										90, 100 ],
								onLoadError : function(data) {
									$.messager.alert("参数信息", "暂无异常参数信息",
											"error");

								},
								frozenColumns : [ [ {
									title : 'valueId',
									field : 'valueId',//'valueId',
									width : 50,
									checkbox : true
								} ] ],
								columns : [ [ {
									field : 'series',
									title : '星系',
									width : 100,
									//sortable:true
								},{
									field : 'star',
									title : '星',
									width : 100,
									//sortable:true
								}, {
									field : 'parameterType',
									title : '设备',
									width : 100,
									//sortable:true
								}, {
									field : 'parameter',
									title : '参数',
									width : 200,
									//sortable:true
								}, {
									field : 'maxVal',
									title : '最大值',
									width : 100,
									//sortable:true
								}, {
									field : 'minVal',
									title : '最小值',
									width : 100,
									//sortable:true
								} ] ],

								toolbar : [ {
									text : '创建',
									iconCls : 'icon-add',
									handler : function() {
										createValue();
									}
								}, '-', {
									text : '删除',
									iconCls : 'icon-remove',
									handler : function() {
										deleteValue();
									}
								}, '-', {
									text : '编辑',
									iconCls : 'icon-edit',
									handler : function() {
										editValue();
									}
								}, '-', {
									text : '取消选中',
									iconCls : 'icon-undo',
									handler : function() {
										valueGrid.datagrid('unselectAll');
									}
								} ]
							});
        	
        	$.get('<%=request.getContextPath()%>/starParam/getSeriesList', {},
					function(res) {
						if (res.result == "true") {
							$.each(res.data.data, function() {
								$('#search-series').append(
										"<option value='"+ this.id+"'>"
												+ this.description
												+ "</option>");
								$('#add-series').append(
										"<option value='"+ this.id+"'>"
												+ this.description
												+ "</option>");
								$('#edit-series').append(
										"<option value='"+ this.id+"'>"
												+ this.description
												+ "</option>");
							});
						} else {
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
								$('#search-star').append("<option value='"+ this.id+"'>"+ this.description +"</option>"); 
							});
		              }
		              else {
		            	  top.showMsg('提示', res.msg);
		              }
		          });	
			 	  
			 	 var parameterType = $('#search-parameterType').val();
				  $.get('<%=request.getContextPath()%>/admin/prewarning/getParamList', {'parameterType':parameterType , 'series':seriesId},  function (res) {
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
    	
    	$("#add-series").change(function(){
		 	var seriesId = $('#add-series').val();	
		 	  $.get('<%=request.getContextPath()%>/admin/prewarning/getStarList', {'seriesId':seriesId},  function (res) {
				  if(res.result == "true") {
					  $('#add-star').find("option").remove();
					 $('#add-star').append("<option value=''>--请选择--</option>"); 
	            	  $.each(res.data.data ,function(){
							$('#add-star').append("<option value='"+ this.id+"'>"+ this.description +"</option>"); 
						});
	              }
	              else {
	            	  top.showMsg('提示', res.msg);
	              }
	          });	
		 	  
		 	 var parameterType = $('#add-parameterType').val();
			  $.get('<%=request.getContextPath()%>/admin/prewarning/getParamList', {'parameterType':parameterType , 'series':seriesId},  function (res) {
				  if(res) {
					  $('#add-parameter').find("option").remove();
					  $('#add-parameter').append("<option value=''>--请选择--</option>"); 
	           	  $.each(res.paramaters ,function(){
	           		    if(this.code){
	           		    	$('#add-parameter').append("<option value='"+ this.code+"'>"+ this.simplyName +"</option>"); 
	           		    }
						});
	           	  $("#add-parameter").select2().val("").trigger("change");
	             }
	             else {
	           	  top.showMsg('提示', res.msg);
	             }
	         });
		});
    	
    	$("#edit-series").change(function(){
		 	var seriesId = $('#edit-series').val();	
		 	  $.get('<%=request.getContextPath()%>/admin/prewarning/getStarList', {'seriesId':seriesId},  function (res) {
				  if(res.result == "true") {
					  $('#edit-star').find("option").remove();
					 $('#edit-star').append("<option value=''>--请选择--</option>"); 
	            	  $.each(res.data.data ,function(){
							$('#edit-star').append("<option value='"+ this.id+"'>"+ this.description +"</option>"); 
						});
	              }
	              else {
	            	  top.showMsg('提示', res.msg);
	              }
	          });	
		 	  
		 	 var parameterType = $('#edit-parameterType').val();
			  $.get('<%=request.getContextPath()%>/admin/prewarning/getParamList', {'parameterType':parameterType , 'series':seriesId},  function (res) {
				  if(res) {
					  $('#edit-parameter').find("option").remove();
					  $('#edit-parameter').append("<option value=''>--请选择--</option>"); 
	           	  $.each(res.paramaters ,function(){
	           		    if(this.code){
	           		    	$('#edit-parameter').append("<option value='"+ this.code+"'>"+ this.simplyName +"</option>"); 
	           		    }
						});
	           	  $("#edit-parameter").select2().val("").trigger("change");
	             }
	             else {
	           	  top.showMsg('提示', res.msg);
	             }
	         });
		});
        
        
        
        $("#search-parameterType").change(function(){
		 	var parameterType = $('#search-parameterType').val();	
		 	var seriesId = $('#search-series').val();
			  $.get('<%=request.getContextPath()%>/admin/prewarning/getParamList', {'parameterType':parameterType, 'series':seriesId},  function (res) {
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
        
        $("#add-parameterType").change(function(){
		 	var parameterType = $('#add-parameterType').val();	
		 	var seriesId = $('#add-series').val();
			  $.get('<%=request.getContextPath()%>/admin/prewarning/getParamList', {'parameterType':parameterType, 'series':seriesId},  function (res) {
				  if(res) {
					  $('#add-parameter').find("option").remove();
					  $('#add-parameter').append("<option value=''>--请选择--</option>"); 
	            	  $.each(res.paramaters ,function(){
	            		    if(this.code){
	            		    	$('#add-parameter').append("<option value='"+ this.code+"'>"+ this.simplyName +"</option>"); 
	            		    }
						});
	            	  $("#add-parameter").select2().val("").trigger("change");
	              }
	              else {
	            	  top.showMsg('提示', res.msg);
	              }
	          });	
		});
        
        $("#edit-parameterType").change(function(){
		 	var parameterType = $('#edit-parameterType').val();	
		 	var seriesId = $('#edit-series').val();
			  $.get('<%=request.getContextPath()%>/admin/prewarning/getParamList', {'parameterType':parameterType, 'series':seriesId},  function (res) {
				  if(res) {
					  $('#edit-parameter').find("option").remove();
					  $('#edit-parameter').append("<option value=''>--请选择--</option>"); 
	            	  $.each(res.paramaters ,function(){
	            		    if(this.code){
	            		    	$('#edit-parameter').append("<option value='"+ this.code+"'>"+ this.simplyName +"</option>"); 
	            		    }
						});
	            	  $("#edit-parameter").select2().val("").trigger("change");
	              }
	              else {
	            	  top.showMsg('提示', res.msg);
	              }
	          });	
		});

		function reloadDataGrid() {
			valueGrid.datagrid('clearChecked');
			valueGrid.datagrid('reload');
		}

		//快速搜索按钮
		$('#btn-search').click(function() {
			var series = $('#search-series').val();
			var star = $('#search-star').val();
			var parameterType = $('#search-parameterType').val();
			var parameter = $('#search-parameter').val();
			valueGrid.datagrid('load', {
				series : series,
				star : star,
				parameterType : parameterType,
				parameter : parameter,
				warningType : "1"
			});
		});
		//创建参数
		function createValue() {
			//弹出创建参数
			$('#addValueModal').modal('show');
		}
		$('#submit_addValueInfo').click(function() {
			var f = $('#addValueInfoForm');
			f.data('bootstrapValidator').validate();
			if(!f.data('bootstrapValidator').isValid()){
				top.alertMsg('错误', '请满足提交条件！');
				return false;
			}
			var maxval = Number($("#add-maxVal").val());
			var minval = Number($("#add-minVal").val());
			if(maxval<minval){
				top.alertMsg('错误', '最大值必须大于最小值！');
				return false;
			}
			var toUrl = '${pageContext.request.contextPath}/admin/prewarning/createErrorValue';
			f.form('submit', {
				url : toUrl,
				onsubmit : function() {
					var flag = $(this).form('validate');
					if (flag) {
						top.showProcess(true, '温馨提示','正在提交数据...');
					}
					return flag;
				},
				success : function(data) {
					top.showProcess(false);
					var map = $.parseJSON(data);
					if (map.success) {
						top.showMsg('提示', map.msg);
						reloadDataGrid();
					} else {
						top.alertMsg('错误', map.msg + "\n"+ map.obj == null ? "": map.obj);
					}
				},
				onLoadError : function() {
					top.showProcess(false);
					top.$.messager.alert('温馨提示','由于网络或服务器太忙，提交失败，请重试！');
				}
			});
			$('#addValueModal').modal('hide');
		});
		
		$('#submit_editValueInfo').click(function() {
			var f = $('#editValueInfoForm');
			f.data('bootstrapValidator').validate();
			if(!f.data('bootstrapValidator').isValid()){
				top.alertMsg('错误', '请满足提交条件！');
				return false;
			}
			var maxval = Number($("#edit-maxVal").val());
			var minval = Number($("#edit-minVal").val());
			if(maxval<minval){
				top.alertMsg('错误', '最大值必须大于最小值！');
				return false;
			}
			var toUrl = '${pageContext.request.contextPath}/admin/prewarning/editErrorValue';
			f.form('submit',{
				url : toUrl,
				onsubmit : function() {
					var flag = $(this).form('validate');
					if (flag) {
						top.showProcess(true,'温馨提示','正在提交数据...');
					}
					return flag;
				},
				success : function(data) {
					top.showProcess(false);
					var map = $.parseJSON(data);
					if (map.success) {
						top.showMsg('提示',map.msg);
						reloadDataGrid();
					} else {
						top.alertMsg('错误',map.msg+ "\n"+ map.obj == null ? "": map.obj);
					}
				},
				onLoadError : function() {
					top.showProcess(false);
					top.$.messager.alert('温馨提示','由于网络或服务器太忙，提交失败，请重试！');
				}
			});
			$('#editValueModal').modal('hide');
		});
		
		
		
		//删除参数
		function deleteValue() {
			var ids = [];
			var rows = valueGrid.datagrid('getSelections');
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
									ids.push(rows[i].valueId);
								}
								$
										.ajax({
											url : '${pageContext.request.contextPath}/admin/prewarning/deleteValue',
											data : {
												valueIds : ids.join(',')
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
				alert("请选择要删除的用户");
				top.showMsg("提示", "请选择要删除的参数！");
			}
		}
		//编辑用户
		function editValue() {
			var rows = valueGrid.datagrid('getSelections');
			if (rows.length > 0) {
				if (rows.length == 1) {
					$
							.ajax({
								url : '${pageContext.request.contextPath}/admin/prewarning/getValueById',
								data : {
									valueId : rows[0].valueId
								},
								cache : false,
								dataType : "json",
								success : function(data) {
									if (data) {
										$.get('<%=request.getContextPath()%>/admin/prewarning/getParamList',
														{
															'series' : data.series,
															'parameterType' : data.parameterType
														},
														function(res) {
															if (res) {
																$(
																		'#edit-parameter')
																		.find(
																				"option")
																		.remove();
																$('#edit-star')
																		.find(
																				"option")
																		.remove();
																$(
																		'#edit-parameter')
																		.append(
																				"<option value=''>--请选择--</option>");
																$('#edit-star')
																		.append(
																				"<option value=''>--请选择--</option>");
																$
																		.each(
																				res.paramaters,
																				function() {
																					if (this.code) {
																						$(
																								'#edit-parameter')
																								.append(
																										"<option value='"+ this.code+"'>"
																												+ this.simplyName
																												+ "</option>");
																						$(
																								"#edit-parameter")
																								.select2()
																								.val(
																										data.parameter)
																								.trigger(
																										"change");
																					}
																				});

																$
																		.each(
																				res.stars,
																				function() {
																					if (this.id) {
																						if (this.id == data.star) {
																							$(
																									'#edit-star')
																									.append(
																											"<option value='"+ this.id+"' selected = 'selected'>"
																													+ this.description
																													+ "</option>");
																						} else {
																							$(
																									'#edit-star')
																									.append(
																											"<option value='"+ this.id+"'>"
																													+ this.description
																													+ "</option>");
																						}
																					}
																				});
															} else {
																top
																		.showMsg(
																				'提示',
																				res.msg);
															}
														});

										$('#edit-value-id').val(data.valueId)
										$('#edit-series').val(data.series);
										$('#edit-parameterType').val(
												data.parameterType);
										$('#edit-maxVal').val(data.maxVal);
										$('#edit-minVal').val(data.minVal);

										//弹出编辑框
										$('#editValueModal').modal('show');
									} else {
										top.alertMsg('错误', "未找到参数信息！");
									}
								}
							});
				} else {
					top.showMsg("提示", '只能选择一个参数进行编辑！您已经选择了' + rows.length
							+ '个参数');
				}
			} else {
				top.showMsg("提示", "请选择要编辑的参数！");
			}

		}
		function getSelectId() {
			var row = valueGrid.datagrid('getSelected');

			if (!row) {
				$.messager.show({
					title : '提示',
					msg : '请选择数据！',
					showType : 'show'
				});
				return null;
			} else {
				return row.valueId;
			}
		}

		// 		function clearFun() {
		// 			$('#frmSearchValue').form('clear');
		// 		}
	</script>
</body>
</html>
