<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.css">
	<script src="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.min.js"></script>
	
	<script src="<%=request.getContextPath()%>/static/content/js/outlook2.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/content/js/validDate.js"></script>
	
	<!-- 表单验证 -->
	<!--     <link rel="stylesheet" href="<%=request.getContextPath()%>/static/content/bootstrapValidator/vendor/bootstrap/css/bootstrap.css"/> -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/content/bootstrapValidator/dist/css/bootstrapValidator.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/content/bootstrapValidator/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/content/bootstrapValidator/dist/js/bootstrapValidator.js"></script>
	
	<script src="${pageContext.request.contextPath}/static/assets/js/bootstrap.min.js"></script>
	<!-- 时间选择器 -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/content/jeDate/jedate/skin/jedate.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/content/jeDate/jedate/jedate.js"></script>
    
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/jqwidgets/styles/jqx.base.css" type="text/css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/jqwidgets/styles/jqx.energyblue.css" type="text/css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/content/css/default.css"  type="text/css"/>	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/select2/select2.min.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/all.css" type="text/css" />
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jqwidgets/jqxdatetimeinput.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jqwidgets/jqxcalendar.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jqwidgets/jqxtooltip.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jqwidgets/globalization/globalize.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jqwidgets/jqxdata.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jqwidgets/jqxmenu.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jqwidgets/jqxcheckbox.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jqwidgets/jqxlistbox.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jqwidgets/jqxdropdownlist.js"></script>
 
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/scripts/demos.js"></script>  
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jqwidgets/jqxdatatable.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jqwidgets/jqxtreegrid.js"></script>     

    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/select2/select2.full.min.js"></script>
    
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
  .selftoolbar {
    display: inline-block;
    padding: 0 10px;
    line-height: 37px;
    float: right;
    position: relative;
  }
</style>

<script type="text/javascript">
	$(function() {
		$('#addStarParamInfoForm').bootstrapValidator({
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
							message : '请选择星系'
						}
					}
				},
				star : {
					validators : {
						notEmpty : {
							message : '请选择星'
						}
					}
				},
				partsType : {
					validators : {
						notEmpty : {
							message : '请选择设备'
						}
					}
				},
				paramCode : {
					validators : {
						notEmpty : {
							message : '请选择参数'
						}
					}
				},
				effeMax : {
					validators : {
						notEmpty : {
							message : '最大值不能为空'
						},
						numeric: {
							message: '最大值只能输入数字'
						}, 
						stringLength: {
                            max: 8,
                            message: '最大值不能超过8位'
                        }
					}
				},
				effeMin : {
					validators : {
						notEmpty : {
							message : '最小值不能为空'
						},
						numeric: {
							message: '最小值只能输入数字'
						},
						 stringLength: {
                            max: 8,
                            message: '最小值不能超过8位'
                        }
					}
				}
			}
		});
		$('#addStarParamModal').on('hide.bs.modal', function () {
			$('#addStarParamInfoForm').data('bootstrapValidator').resetForm(true);
		})
		
		
		$('#reset_addStarParam').click(function() {
			$('#addStarParamInfoForm').data('bootstrapValidator').resetForm(true);
		});
		$('#editStarParamInfoForm').bootstrapValidator({
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
							message : '请选择星系'
						}
					}
				},star : {
					validators : {
						notEmpty : {
							message : '请选择星'
						}
					}
				},
				partsType : {
					validators : {
						notEmpty : {
							message : '请选择设备'
						}
					}
				},
				paramCode : {
					validators : {
						notEmpty : {
							message : '请选择参数'
						}
					}
				},
				effeMax : {
					validators : {
						notEmpty : {
							message : '最大值不能为空'
						},
						numeric: {
							message: '最大值只能输入数字'
						},
						 stringLength: {
                            max: 8,
                            message: '最大值不能超过8位'
                        }
					}
				},
				effeMin : {
					validators : {
						notEmpty : {
							message : '最小值不能为空'
						},
						numeric: {
							message: '最小值只能输入数字'
						},
						 stringLength: {
                            max: 8,
                            message: '最小值不能超过8位'
                        }
					}
				}
			}
		});
		$('#editStarParamModal').on('hide.bs.modal', function () {
			$('#editStarParamInfoForm').data('bootstrapValidator').resetForm(true);
		})
		$('#reset_editStarParam').click(function() {
			$('#editStarParamInfoForm').data('bootstrapValidator').resetForm(true);
		});
		$('#vss').click(function() {
			$('#addStarParamInfoForm').bootstrapValidator('validate');
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
			<ul class="breadcrumb" style=" margin-top: 10px;">
				<li>
					<img src="${pageContext.request.contextPath}/static/imgs/DataImport/home.png" style="margin-bottom: 3px;">
					<span>报告管理</span>
				</li>
				<li class="active">报告参数</li>
			</ul><!--  .breadcrumb -->
		</div>
		<div class="page-content">
<!-- 			<div class="daohanglancs"> -->
<!-- 				<img -->
<!-- 					src="<%=request.getContextPath()%>/static/imgs/DataImport/home.png"> -->
<!-- 				<span>位置:</span> <span>报告管理></span> <span>报告参数配置</span> -->
<!-- 			</div> -->
			<div>
				<div class="col-xs-12 col-sm-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="widget-box">
						<div class="widget-header" id="change-search-box" data-action="collapse">
							<h4>搜索</h4>
							<div class="widget-toolbar">
								<a href="javascript:void(0);" >
									<div hidden="hidden"><i class="icon-chevron-up" hidden="hidden"></i></div>
									<img id="toolimg" src="${pageContext.request.contextPath}/static/imgs/DataImport_manage/xia2.png">
								</a>
							</div>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<!-- 搜索form -->
								<form id="form" action="" class="form-horizontal" role="form" >
									<div class="space-1"></div>
									<div class="form-group">
										<label class="col-sm-5 control-label no-padding-right" for="form-series">星系：</label>
										<div class="col-sm-3"  >
										<select name="series"  id="form-series" class="form-control " >
										<option value="">--请选择--</option>
				                       </select>
									</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-5 control-label no-padding-right" for="form-star">星号：</label>
										<div class="col-sm-3">
												<select name="star"  id="form-star" class="form-control " >
												<option value="">--请选择--</option>
				                       			</select>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-5 control-label no-padding-right" for="form-partsType">设备：</label>
										<div class="col-sm-3">
											<select name="partsType"  id="form-partsType" class="form-control " >
				                           		<option selected="selected" value="">--请选择--</option>
				                       		</select>
										</div>
									</div>
									
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-5 control-label no-padding-right" for="form-paramCode">参数：</label>
										<div class="col-sm-3">
											<select name="paramCode"  id="form-paramCode" class="form-control  select2" style="width: 100%;">
				                           		<option value="">--请选择--</option>
				                       		</select>
										</div>
									</div>
									
									<div class="space-4"></div>
									<div class="form-group">
									<div class="col-sm-6 control-label no-padding-right">
											<button type="button" id="btn-search" class="subbutton_1">
							                    <i></i>
							                    <span>搜索</span>
							                </button>
										</div>
										<div class="col-sm-1 control-label no-padding-right">
											 <button type="reset" id="btn-cancel" class="cancelbutton_1">
							                    <i></i>
							                    <span>取消</span>
							                </button>
										</div>
			                       </div>
								</form>
							</div>
						</div>
					</div>
					<!-- PAGE CONTENT ENDS -->
				</div><!-- /.col -->
			</div><!-- /.row -->
			<div id="content" region="center" title="参数信息" style="overflow: hidden">
			</div>
			<table id="StarParamList" border="false" width="100%" height="500px">
			</table>
			
			<!-- 创建用户 -->
			<div class="modal fade" id="addStarParamModal" tabindex="-1" role="dialog" aria-labelledby="addStarParamModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<form id="addStarParamInfoForm" class="form-horizontal" role="form" style="margin: 0px;">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="addStarParamModalLabel">参数信息</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-starParam-series"> 星系：</label>
									<div class="col-sm-8"  >
										<select name="series"  id="add-starParam-series" class="form-control " >
				                       </select>
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-starParam-star"> 星号： </label>
									<div class="col-sm-8">
										<select name="star"  id="add-starParam-star" class="form-control " >
				                       </select>
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-starParam-partsType">  设备： </label>
									<div class="col-sm-8">
										<select name="partsType"  id="add-starParam-partsType" class="form-control " >
										   <option selected="selected" value="">--请选择--</option>
				                       </select>
									</div>
								</div>
								
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-lg-3 control-label no-padding-right" for="add-starParam-paramCode"> 参数： </label>
									<div class="col-sm-8">
										<select name="paramCode"  id="add-starParam-paramCode" class="form-control  select2" style="width: 100%;">
											<option selected="selected" value="">--请选择--</option>
				                       </select>
									</div>
								</div>
								
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-lg-3 control-label no-padding-right" for="add-starParam-effeMin"> 最小有效值： </label>
									<div class="col-sm-8">
										<input type="text" name="effeMin" id="add-starParam-effeMin" placeholder="最小有效值" class="form-control" />
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-lg-3 control-label no-padding-right" for="add-starParam-effeMax"> 最大有效值： </label>
									<div class="col-sm-8">
										<input type="text" name="effeMax" id="add-starParam-effeMax" placeholder="最大有效值" class="form-control" />
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<div class="col-lg-7 col-lg-offset-3">
									<button type="submit" class="subbutton_1" id="submit_addStarParam" data-dismiss="modal">确定</button>
									<button type="button" class="cancelbutton_1" id="reset_addStarParam" data-dismiss="modal">关闭</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 编辑用户 -->
			<div class="modal fade" id="editStarParamModal" tabindex="-1" role="dialog" aria-labelledby="editStarParamModalLabel">
				<div class="modal-dialog" role="document" >
					<div class="modal-content">
						<form id="editStarParamInfoForm" class="form-horizontal" role="form" style="margin: 0px;">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="editStarParamModalLabel">参数信息</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="edit-starParam-series"> 星系: </label>
									<div class="col-sm-8">
										<select name="series"  id="edit-starParam-series" class="form-control " disabled="disabled">
				                       	</select>
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="edit-starParam-star"> 星号： </label>
									<div class="col-sm-8">
										<select name="star"  id="edit-starParam-star" class="form-control " disabled="disabled">
				                       </select>
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="edit-starParam-partsType">  设备： </label>
									<div class="col-sm-8">
										<select name="partsType"  id="edit-starParam-partsType" class="form-control " disabled="disabled">
										   <option selected="selected" value="">--请选择--</option>
				                           <option value = "flywheel">飞轮</option>
				                           <option value = "top">陀螺</option>
				                       </select>
									</div>
								</div>
								 <div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="edit-starParam-paramCode"> 参数： </label>
									<div class="col-sm-8">
										<select name="paramCode"  id="edit-starParam-paramCode" class="form-control  select2" style="width: 100%;" disabled="disabled">
				                       </select>
									</div>
								</div>
								
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-lg-3 control-label no-padding-right" for="edit-starParam-effeMin"> 最小有效值： </label>
									<div class="col-sm-8">
										<input type="text" name="effeMin" id="edit-starParam-effeMin" placeholder="最小有效值" class="form-control" />
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-lg-3 control-label no-padding-right" for="edit-starParam-effeMax"> 最大有效值： </label>
									<div class="col-sm-8">
										<input type="text" name="effeMax" id="edit-starParam-effeMax" placeholder="最大有效值" class="form-control" />
									</div>
								</div>
								<input type="hidden" name="id" id="edit-starParam-id" />
							</div>
							<div class="modal-footer">
								<div class="col-lg-7 col-lg-offset-3">
									<button type="submit" class="subbutton_1" id="submit_editStarParam" data-dismiss="modal">确定</button>
									<button type="button" class="cancelbutton_1" id="reset_editStarParam" data-dismiss="modal">关闭</button>
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
				</div><!-- /.col -->
			</div><!-- /.row -->
			
			<div class="modal fade" id="jqxWidgetModal" role="dialog" aria-labelledby="jqxWidgetModalLabel">
				<div class="modal-dialog" role="document" style="margin:60px -320px;">
					<div class="modal-content">
						<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="editStarParamModalLabel">参数列表</h4>
							</div>
							<div class="modal-body">
								<div id='jqxWidget'>
								   <div id="treeGrid"></div>	
			      	   	 		</div>
							</div>
							<div class="modal-footer">
								<div class="col-lg-4 col-lg-offset-5">
									<button type="submit" class="btn btn-primary" id="submit_jqxWidgetInfo" data-dismiss="modal">确定</button>
									<button type="button" class="btn btn-default" id="reset_jqxWidgetInfo" data-dismiss="modal">关闭</button>
								</div>
							</div>
      	     		</div>
      	      	</div>
      	   	</div>
			
			
		</div><!-- /.page-content -->
	</div><!-- /.main-content -->
<script type="text/javascript">


		var activeUser = '${activeUser}';
		var StarParamGrid;
		var deptTree;
		var check;
        $(function () {
        
            StarParamGrid = $("#StarParamList").datagrid({
                url: '<%=request.getContextPath()%>/starParam/getStarParamList',
                rownumbers: true,
                fitColumns:true,
                idField: 'id',
                pageSize: 10,
                pagination: true,
                pageList: [10, 20, 30, 40, 50, 60, 70, 80, 90, 100],
                onLoadError:function(data){
                	$.messager.alert("用户信息", "暂无用户数据信息", "error");
                
                },
                frozenColumns: [[{
                    title: 'id',
                    field: 'id',
                    width: 50,
                    checkbox: true
                }, {
                    field: 'series',
                    title: '星系',
                    width: 100
                }]],
                columns: [[ {
                    field: 'star',
                    title: '星号',
                    width: 100,
                },  {
                    field: 'partsType',
                    title: '设备',
                    width: 100
                },{
                	 field: 'paramName',
                     title: '参数',
                     width: 150
                  
                },{
                    field: 'effeMin',
                    title: '最小有效值',
                    width: 100,
                }, {
                    field: 'effeMax',
                    title: '最大有效值',
                    width: 100
                }
                ]],

                toolbar: [
                {
                    text: '创建',
                    iconCls: 'icon-add',
                    handler: function () {
                     createStarParam();
                    }
                }, '-', {
                    text: '删除',
                    iconCls: 'icon-remove',
                    handler: function () {
                        deleteStarParam();
                    }
                }, '-', {
                    text: '编辑',
                    iconCls: 'icon-edit',
                    handler: function () {
                        editStarParam();
                    }
                }, '-', {
                    text: '取消选中',
                    iconCls: 'icon-undo',
                    handler: function () {
                        StarParamGrid.datagrid('unselectAll');
                    }
                }]
            });
		
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
		
		if(activeUser != ''){
				var permissionItemsJSON = '${activeUser.permissionItemsJSON}';
				var map = $.parseJSON(permissionItemsJSON); 
				 $('#form-partsType').find("option").remove();
  			      $('#form-partsType').append("<option value=''>--请选择--</option>"); 
				if(map.flywheel == 'flywheel'){
					$("#form-partsType").append(" <option value = 'flywheel'>飞轮</option>"); 
				}
				if(map.top == 'top'){
					$("#form-partsType").append(" <option value = 'top'>陀螺</option>"); 
				}
			}

            $.get('<%=request.getContextPath()%>/starParam/getSeriesList', {}, function (res) {
  			  if(res.result == "true") {
  			      $('#form-series').find("option").remove();
  			      $('#form-series').append("<option value=''>--请选择--</option>"); 
              	  $.each(res.data.data ,function(){
  						$('#form-series').append("<option value='"+ this.code+"'>"+ this.name +"</option>"); 
  					});
              		  var seriesId = $('#form-series').val();
              			$.get('<%=request.getContextPath()%>/starParam/getStarList', {'seriesId':seriesId},  function (res) {
        					  if(res.result == "true") {
        						  $('#form-star').find("option").remove();
        						  $('#form-star').append("<option value=''>--请选择--</option>"); 
        		            	  $.each(res.data.data ,function(){
        								$('#form-star').append("<option value='"+ this.code+"'>"+ this.name +"</option>"); 
        							});
        							reqParamSearch();
        		              	}
        		          });	
                }
            });
  		$("#form-series").change(function(){
  			 	var seriesId = $('#form-series').val();	
  			 	  $.get('<%=request.getContextPath()%>/starParam/getStarList', {'seriesId':seriesId},  function (res) {
  					  if(res.result == "true") {
  						  $('#form-star').find("option").remove();
  						  $('#form-star').append("<option value=''>--请选择--</option>"); 
  		            	  $.each(res.data.data ,function(){
  								$('#form-star').append("<option value='"+ this.code+"'>"+ this.name +"</option>"); 
  							});
  							reqParamSearch();
  		              }
  		          });	
  		});
  		$.fn.modal.Constructor.prototype.enforceFocus = function() {};
		$(".select2").select2();
        });
     	$("#form-star").change(function(){
			 reqParamSearch();
		});
		
		$("#form-partsType").change(function(){
			 reqParamSearch();
		});
      function reqParamSearch() { 
      		 var partstype = $('#form-partsType').val();	
			 var seriesId = $('#form-series').val();
			 var starId = $('#form-star').val();
			 console.log(partstype);
			 $("#form-paramCode").select2().val("").trigger("change");
			 $.get('<%=request.getContextPath()%>/starParam/getConstraintList', {'seriesId':seriesId,'starId':starId,'partstype':partstype},  function (res) {
					$('#form-paramCode').find("option").remove();
					$('#form-paramCode').append("<option value=''>--请选择--</option>"); 
					if(res.result == "true") {
			          	$.each(res.data.data ,function(){
								$('#form-paramCode').append("<option value='"+ this.value+"'>"+ this.name +"</option>"); 
						});
		             } 
		      });
      }
 
        function reloadDataGrid() {
            StarParamGrid.datagrid('clearChecked');
            StarParamGrid.datagrid('reload');
        }

		$('#btn-cancel').click(function(){
			$("#form-paramCode").select2().val("").trigger("change");
		});

		//快速搜索按钮
		$('#btn-search').click(function(){
			var Qseries =  $('#form-series').val();
			var Qstar = $('#form-star').val();
			var QpartsType = $('#form-partsType').val();
            var QparamCode = $('#form-paramCode').val();
			StarParamGrid.datagrid('load', {
				series : Qseries,
				star : Qstar,
				partsType : QpartsType,
				paramCode : QparamCode
			});
		});
		
		  
		  function reqParamAdd() {
			  var partstype = $('#add-starParam-partsType').val();	
			  var seriesId = $('#add-starParam-series').val();
			  var starId = $('#add-starParam-star').val();
			  $("#add-starParam-paramCode").select2().val("").trigger("change");
				  $.get('<%=request.getContextPath()%>/starParam/getConstraintList', {'seriesId':seriesId,'starId':starId,'partstype':partstype},  function (res) {
					      $('#add-starParam-paramCode').find("option").remove();
					      $('#add-starParam-paramCode').append("<option value=''>--请选择--</option>"); 
					      if(res.result == "true") {
			          	 	 $.each(res.data.data ,function(){
									$('#add-starParam-paramCode').append("<option value='"+ this.value+"'>"+ this.name +"</option>"); 
							 });
		              }
		          });
		  }
		  
		function  validator (series,star,partsType,paramCode,effeMin,effeMax) {
			if(series == "") {
				 top.alertMsg('错误', "星系不能为空");
				  return false;
			}
			if(star == "") {
				  top.alertMsg('错误',"星号不能为空");
				  return false;
			}
			if(partsType == "") {
				  top.alertMsg('错误', "设备不能为空");
				  return false;
			}
			if(paramCode == "") {
				  top.alertMsg('错误', "参数不能为空");
				  return false;
			}
			if(effeMin == "") {
				 top.alertMsg('错误', "最小值不能为空");
				 return false;
			}
			if(effeMax == "") {
			 	 top.alertMsg('错误',"最大值不能为空");
				 return false;
			}
			if(	isNaN(effeMin)) {
				top.alertMsg('错误',"最小值必须为数字");
				 return false;
			}
			if(isNaN(effeMax)) {
			 	 top.alertMsg('错误', "最大值必须为数字");
				 return false;
			}
			if(Number(effeMax) <= Number(effeMin)) {
			 	 top.alertMsg('错误', "最大值不能小于最小值");
				 return false;
			}
			return true;
		}
		
		function createStarParam() {
			 
			 $('#add-starParam-effeMax').val('');
			 $('#add-starParam-effeMin').val('');	
			 $('#add-starParam-series').find("option").remove();
			 $('#add-starParam-star').find("option").remove();
			 $('#add-starParam-partsType').val('');
			 $("#add-starParam-paramCode").select2().val("").trigger("change");
		  	 $.get('<%=request.getContextPath()%>/starParam/getSeriesList', {}, function (res) {
			   if(res.result == "true") {
			   	  $('#add-starParam-series').find("option").remove();
			   	  $('#add-starParam-series').append("<option value=''>--请选择--</option>"); 
            	  $.each(res.data.data ,function(){
						$('#add-starParam-series').append("<option value='"+ this.code+"'>"+ this.name +"</option>"); 
					});
            		var seriesId = $('#add-starParam-series').val();	
    				  $.get('<%=request.getContextPath()%>/starParam/getStarList', {'seriesId':seriesId},  function (res) {
    					  if(res.result == "true") {
    						  $('#add-starParam-star').find("option").remove();
    						  $('#add-starParam-star').append("<option value=''>--请选择--</option>"); 
    		            	  $.each(res.data.data ,function(){
    								$('#add-starParam-star').append("<option value='"+ this.code+"'>"+ this.name +"</option>"); 
    							});
    							reqParamAdd();
    		              }
    		              else {
    		            	  top.showMsg('提示', res.msg);
    		              }
    		          });	
              }
              else {
            	  top.showMsg('提示', res.msg);
              }
          });
          if(activeUser != ''){
				var permissionItemsJSON = '${activeUser.permissionItemsJSON}';
				var map = $.parseJSON(permissionItemsJSON); 
			    $('#add-starParam-partsType').find("option").remove();
  			    $('#add-starParam-partsType').append("<option value=''>--请选择--</option>"); 
				if(map.flywheel == 'flywheel'){
					$("#add-starParam-partsType").append(" <option value = 'flywheel'>飞轮</option>"); 
				}
				if(map.top == 'top'){
					$("#add-starParam-partsType").append(" <option value = 'top'>陀螺</option>"); 
				}
			}
			$('#addStarParamModal').modal('show');
			
		}
		$("#add-starParam-series").change(function(){
		 	var seriesId = $('#add-starParam-series').val();	
			  $.get('<%=request.getContextPath()%>/starParam/getStarList', {'seriesId':seriesId},  function (res) {
				  if(res.result == "true") {
					  $('#add-starParam-star').find("option").remove();
					  $('#add-starParam-star').append("<option value=''>--请选择--</option>"); 
	            	  $.each(res.data.data ,function(){
							$('#add-starParam-star').append("<option value='"+ this.code+"'>"+ this.name +"</option>"); 
						});
						  reqParamAdd();
	              }
	              else {
	            	  top.showMsg('提示', res.msg);
	              }
	          });
	  });
	$("#add-starParam-star").change(function(){
		 reqParamAdd();
	});
	
	$("#add-starParam-partsType").change(function(){
		 reqParamAdd();
	});
	
		function checkParam(series,star,partsType,paramCode) {
			 $.get('<%=request.getContextPath()%>/starParam/checkParam', {'series':series,'star':star,'partsType':partsType,'paramCode':paramCode},  function (res) {
				  if(res.result == "true") {
					  check =  "true";	
	              }else {
					  top.alertMsg('错误', res.msg);
					  check = "false";
	              }
	          });
		}
		
		$('#submit_addStarParam').click(function(){
		    var series = $('#add-starParam-series').val();	
		    var star = $('#add-starParam-star').val();	
		    var partsType = $('#add-starParam-partsType').val();
			var paramCode = $('#add-starParam-paramCode').val();
			
		    var effeMin = $('#add-starParam-effeMin').val();
		    var effeMax = $('#add-starParam-effeMax').val();
			var flag =	validator(series,star,partsType,paramCode,effeMin,effeMax);
			if(!flag) {
				return false;
			} 
			var toUrl='${pageContext.request.contextPath}/starParam/createStarParam';
			var f = $('#addStarParamInfoForm');
			         f.form('submit', {
			             url: toUrl,
			             onsubmit: function () {
			                 var flag = $(this).form('validate');
			                 if (flag) {
			                     top.showProcess(true, '温馨提示', '正在提交数据...');
			                 }
			                 return flag;
			             },
			             success: function (data) {
			                 top.showProcess(false);
			                 var map = $.parseJSON(data);
			                 if (map.success) {
			                     top.showMsg('提示', map.msg);
			                     reloadDataGrid();
			                 }
			                 else {
			                 	top.alertMsg('错误', map.msg);
			                 }
			             },
			             onLoadError: function () {
			                 top.showProcess(false);
			                 top.$.messager.alert('温馨提示', '由于网络或服务器太忙，提交失败，请重试！');
			             }
			         });
		});
		
		//删除用户
		function deleteStarParam() {
			var ids = [];
			var rows = StarParamGrid.datagrid('getSelections');
			if (rows.length > 0) {
				var names = [];
				for ( var i = 0; i < rows.length; i++) {
					names.push(rows[i].paramName);
				}
				swal({
					title : "你是否确定删除?",
					text : names.join(','),
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
						for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						$.ajax({
							url : '${pageContext.request.contextPath}/starParam/deleteStarParam',
							data : {
								starParamIds : ids.join(',')
							},
							cache : false,
							dataType : "json",
							success : function(data) {
								if (data.success) {
									swal("删除成功!","","success");
									reloadDataGrid();
								} else {
									swal("删除失败", data.obj,"error");
								}
							}
						});
					} else {
						swal("取消删除", "","error");
					}
				});
			} else {
				top.showMsg("提示", "请选择要删除的参数！");
			}
		}
		  function reqParamEdit() {
		  		  var partstype = $('#edit-starParam-partsType').val();	
			  	  var seriesId = $('#edit-starParam-series').val();
			 	  var starId = $('#edit-starParam-star').val();
				  $.get('<%=request.getContextPath()%>/starParam/getConstraintList', {'seriesId':seriesId,'starId':starId,'partstype':partstype},  function (res) {
					  if(res.result == "true") {
					  		 $('#edit-starParam-paramCode').find("option").remove();
					  		 $('#edit-starParam-paramCode').append("<option value=''>--请选择--</option>"); 
			          	 	 $.each(res.data.data ,function(){
									$('#edit-starParam-paramCode').append("<option value='"+ this.value+"'>"+ this.name +"</option>"); 
								});
								 $("#edit-starParam-paramCode").select2().val("").trigger("change");
		              }
		              else {
		            	  top.showMsg('提示', res.msg);
		              }
		          });
		  }
		
		
		//编辑参数
		function editStarParam() {
			var rows = StarParamGrid.datagrid('getSelections');
			if (rows.length > 0) {
				if (rows.length == 1) {
					//弹出编辑框
					  $.get('<%=request.getContextPath()%>/starParam/getSeriesList', {}, function (res) {
						  if(res.result == "true") {
							  $('#edit-starParam-series').find("option").remove();
							  $('#edit-starParam-series').append("<option value=''>--请选择--</option>"); 
			            	  $.each(res.data.data ,function(){
			            		  if(rows[0].series == this.id) {
			            			  $('#edit-starParam-series').append("<option value='"+ this.code+"' selected>"+ this.name +"</option>"); 
			            		  } else {
									$('#edit-starParam-series').append("<option value='"+ this.code+"'>"+ this.name +"</option>"); 
			            		  }
								});
								 $('#edit-starParam-series').val(rows[0].series);
			            		var seriesId = $('#edit-starParam-series').val();	
			    				  $.get('<%=request.getContextPath()%>/starParam/getStarList', {'seriesId':seriesId},  function (res) {
			    					  if(res.result == "true") {
			    						  $('#edit-starParam-star').find("option").remove();
			    						  $('#edit-starParam-star').append("<option value=''>--请选择--</option>"); 
			    		            	  $.each(res.data.data ,function(){
			    		            		  if(rows[0].star == this.id) {
			    		            			  $('#edit-starParam-star').append("<option value='"+ this.code+"' selected>"+ this.name +"</option>"); 
			    		            		  }else {
			    								$('#edit-starParam-star').append("<option value='"+ this.code+"'>"+ this.name +"</option>"); 
			    		            		  }
			    							});
			    							
			    		              }
			    		              else {
			    		            	  top.showMsg('提示', res.msg);
			    		              }
			    		          });	
			              }
			              else {
			            	  top.showMsg('提示', res.msg);
			              }
			          });
					var parts = '';
					if(rows[0].partsType == "飞轮") {
						parts = "flywheel";
					}else if(rows[0].partsType == "陀螺") {
						parts = "top";
					}
					 $.get('<%=request.getContextPath()%>/starParam/getConstraintList', {'seriesId':rows[0].series,'starId':rows[0].star,'partstype':parts},  function (res) {
					  if(res.result == "true") {
					  		 $('#edit-starParam-paramCode').find("option").remove();
					  		 $('#edit-starParam-paramCode').append("<option value=''>--请选择--</option>"); 
			          	 	 $.each(res.data.data ,function(){
									$('#edit-starParam-paramCode').append("<option value='"+ this.value+"'>"+ this.name +"</option>"); 
								});
			    			$("#edit-starParam-paramCode").select2().val(rows[0].paramCode).trigger("change");
		              }
		          });
				 $('#edit-starParam-id').val(rows[0].id);
				
				 $('#edit-starParam-star').val(rows[0].star);
				 $('#edit-starParam-partsType').val(parts);
				 $('#edit-starParam-effeMin').val(rows[0].effeMin);
				 $('#edit-starParam-effeMax').val(rows[0].effeMax);
				 $('#editStarParamModal').modal('show');
				} else {
					var names = [];
					for ( var i = 0; i < rows.length; i++) {
						names.push(rows[i].paramName);
					}
					top.showMsg("提示", '只能选择一个参数进行编辑！您已经选择了' + rows.length + '个参数');
				}
			} else {
				top.showMsg("提示", "请选择要编辑的参数！");
			}

		}
		$("#edit-starParam-series").change(function(){
		 	var seriesId = $('#edit-starParam-series').val();	
			  $.get('<%=request.getContextPath()%>/starParam/getStarList', {'seriesId':seriesId},  function (res) {
				  if(res.result == "true") {
					  $('#edit-starParam-star').find("option").remove();
					  $('#edit-starParam-star').append("<option value=''>--请选择--</option>"); 
	            	  $.each(res.data.data ,function(){
	            		  $('#edit-starParam-star').append("<option value='"+ this.code+"'>"+ this.name +"</option>"); 
						});
	              }
	              else {
	            	  top.showMsg('提示', res.msg);
	              }
	          });
	          
			    reqParamEdit();
		});
		$("#edit-starParam-star").change(function(){
		    reqParamEdit();
		});
		
		$("#edit-starParam-partsType").change(function(){
			reqParamEdit();
		});
		$('#submit_editStarParam').click(function(){
			var series = $('#edit-starParam-series').val();	
			var star = $('#edit-starParam-star').val();	
			var partsType = $('#edit-starParam-partsType').val();
			var paramCode = $('#edit-starParam-paramCode').val();	
   			var effeMin = $('#edit-starParam-effeMin').val();
    		var effeMax = $('#edit-starParam-effeMax').val();
			var flag =	validator(series,star,partsType,paramCode,effeMin,effeMax);
			if(!flag) {
				return false;
			}
			var toUrl='${pageContext.request.contextPath}/starParam/editStarParam';
			var f = $('#editStarParamInfoForm');
            f.form('submit', {
                url: toUrl,
                onsubmit: function () {
                    var flag = $(this).form('validate');
                    if (flag) {
                        top.showProcess(true, '温馨提示', '正在提交数据...');
                    }
                    return flag;
                },
                success: function (data) {
                    top.showProcess(false);
                    var map = $.parseJSON(data);
                    if (map.success) {
                        top.showMsg('提示', map.msg);
                        reloadDataGrid();
                    } else {
                    	top.alertMsg('错误', map.msg+"\n"+map.obj==null?"":map.obj);
                    }
                },
                onLoadError: function () {
                    top.showProcess(false);
                    top.$.messager.alert('温馨提示', '由于网络或服务器太忙，提交失败，请重试！');
                }
            });
		});
		
		function getSelectId() {
			var row = StarParamGrid.datagrid('getSelected');

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
			$('#frmSearchStarParam').form('clear');
		}
	</script>	
  </body>
</html>
