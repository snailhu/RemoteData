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
    
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.css">
	<script src="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.min.js"></script>
	
	<!--     <link rel="stylesheet" href="${pageContext.request.contextPath}/static/content/bootstrapValidator/vendor/bootstrap/css/bootstrap.css"/> -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/content/bootstrapValidator/dist/css/bootstrapValidator.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/content/bootstrapValidator/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/content/bootstrapValidator/dist/js/bootstrapValidator.js"></script>
    
	<script src="${pageContext.request.contextPath}/static/content/js/outlook2.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/content/js/validDate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/content/jquery-easyui-datagridview/datagrid-detailview.js"></script>
  	<!-- 时间选择器 -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/content/jeDate/jedate/skin/jedate.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/content/jeDate/jedate/jedate.js"></script>
    
    <!-- 时间选择器 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/content/jQueryCalendar/calendar.css">
	<script src="${pageContext.request.contextPath}/static/content/jQueryCalendar/calendar.js"></script>   
    
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
.form-horizontal {
    margin-bottom: 0px;
}
</style>
  <script type="text/javascript">
  	$(function(){
  		//左菜单栏
		$("#galaxymanage-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_82.png");
		$("#galaxymanage-text").css("color","#5d90d6");
  		
  		$('#addSeriesInfoForm').bootstrapValidator({
          message: 'This value is not valid',
          feedbackIcons: {
              valid: 'glyphicon glyphicon-ok',
              invalid: 'glyphicon glyphicon-remove',
              validating: 'glyphicon glyphicon-refresh'
          },
          fields: {
              name: {
                  message: '系列名称不能为空',
                  validators: {
                      notEmpty: {
                          message: '系列名称不能为空'
                      }
                  }
              },
              code: {
                  message: '系列编码不能为空',
                  validators: {
                      notEmpty: {
                          message: '系列编码不能为空'
                      },
                      regexp : { 
                          regexp : /^[A-Za-z0-9]+$/,
                          message : '系列编码由字母和数字组成'
                      },
                  }
              },
              description : {
            	  message: '',
              }
          }
      });
// 	$('#reset_addSeriesInfo').click(function() {
// 	    $('#addSeriesInfoForm').data('bootstrapValidator').resetForm(true);
// 	});	
// 	$('#close_addSeriesInfo').click(function() {
// 	    $('#addSeriesInfoForm').data('bootstrapValidator').resetForm(true);
// 	});
  	$('#editSeriesInfoForm').bootstrapValidator({
          message: 'This value is not valid',
          feedbackIcons: {
              valid: 'glyphicon glyphicon-ok',
              invalid: 'glyphicon glyphicon-remove',
              validating: 'glyphicon glyphicon-refresh'
          },
          fields: {
              name: {
                  message: '系列名称不能为空',
                  validators: {
                      notEmpty: {
                          message: '系列名称不能为空'
                      }
                  }
              },
              code: {
                  message: '系列编码不能为空',
                  validators: {
                      notEmpty: {
                          message: '系列编码不能为空'
                      },
                      regexp : { 
                          regexp : /^[A-Za-z0-9]+$/,
                          message : '系列编码由字母和数字组成'
                      },
                  }
              },
              description : {
            	  message: '',
              }
          }
      });
  	$('#reset_editSeriesInfo').click(function() {
	    $('#editSeriesInfoForm').data('bootstrapValidator').resetForm(true);
	});	
	$('#close_editSeriesInfo').click(function() {
	    $('#editSeriesInfoForm').data('bootstrapValidator').resetForm(true);
	});
  	$('#addStarInfoForm').bootstrapValidator({
      message: 'This value is not valid',
      feedbackIcons: {
          valid: 'glyphicon glyphicon-ok',
          invalid: 'glyphicon glyphicon-remove',
          validating: 'glyphicon glyphicon-refresh'
      },
      fields: {
          name: {
              message: '星名称不能为空',
              validators: {
                  notEmpty: {
                      message: '星名称不能为空'
                  }
              }
          },
          code: {
              message: '星编码不能为空',
              validators: {
                  notEmpty: {
                      message: '星编码不能为空'
                  },
                  regexp : { 
                      regexp : /^[A-Za-z0-9]+$/,
                      message : '星编码由字母和数字组成'
                  },
              }
          },
          beginDate: {
              validators: {
            	  notEmpty: {
                      message: '开始运行时间不能为空'
                  },
	              regexp: {
	                  regexp: /^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)\s+([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$/,
	                  message: '时间格式不对'
	              },
              }
          },
          description : {
        	  message: '',
          }
      }
  	});  
//   	$('#reset_addStarInfo').click(function() {
// 	    $('#addStarInfoForm').data('bootstrapValidator').resetForm(true);
// 	});	
// 	$('#close_addStarInfo').click(function() {
// 	    $('#addStarInfoForm').data('bootstrapValidator').resetForm(true);
// 	});	
    $('#editStarInfoForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                message: '星名称不能为空',
                validators: {
                    notEmpty: {
                        message: '星名称不能为空'
                    }
                }
            },
            code: {
                message: '星编码不能为空',
                validators: {
                    notEmpty: {
                        message: '星编码不能为空'
                    },
                    regexp : { 
                        regexp : /^[A-Za-z0-9]+$/,
                        message : '星编码由字母和数字组成'
                    },
                }
            },
            beginDate: {
                validators: {
              	  notEmpty: {
                        message: '开始运行时间不能为空'
                    },
                regexp: {
                    regexp: /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/,
                    message: '时间格式不对'
                },
                }
            }
        }
    });  	
    $('#reset_editStarInfo').click(function() {
	    $('#editStarInfoForm').data('bootstrapValidator').resetForm(true);
	});	
	$('#close_editStarInfo').click(function() {
	    $('#editStarInfoForm').data('bootstrapValidator').resetForm(true);
	});
	$('#add-series11-close').click(function() {
        $('#defaultForm').bootstrapValidator('validate');
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
				<li>
					<img src="${pageContext.request.contextPath}/static/imgs/DataImport/home.png" style="margin-bottom: 3px;">
					<span>星系管理</span>
				</li>
			</ul><!--  .breadcrumb -->
		</div>
		<div class="page-content">
<!-- 			<div class="page-header"> -->
<!-- 				<h1 style="text-align: left;">星系管理</h1> -->
<!-- 			</div> -->
			<div id="content" region="center" style="overflow: hidden">
				<div id="toolbar" class="datagrid-toolbar" style="height: 28px;">
					<div style="height: 28px;">
						<button class="easyui-linkbutton" iconcls="icon-add" plain="true" style="float: left;" 
							id="addSeriesInfoModal-btn">创建</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-remove" plain="true" style="float: left;"
							onclick="deleteSeries();">删除</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-edit" plain="true" style="float: left;"
							onclick="editSeries();">编辑</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-undo" plain="true" style="float: left;"
							onclick="galaxyGrid.datagrid('unselectAll');">取消选中</button>
					</div>
				</div>
			</div>
			<table id="galaxyList" fit="false" border="false" height="450px">
				
			</table>
			<!-- 添加系列表单 -->
			<div class="modal fade" id="addSeriesInfoModal" tabindex="-1" role="dialog" aria-labelledby="addSeriesInfoModalLabel">
				<div class="modal-dialog" role="document" style="margin:150px 450px">
					<div class="modal-content">
						<form id="addSeriesInfoForm" class="form-horizontal" role="form">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="close_addSeriesInfo">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="addSeriesInfoModalLabel">添加系列</h4>
							</div>
							<div class="modal-body">
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-series-name"> 系列名称:</label>
									<div class="col-sm-8">
										<input type="text" name="name" id="add-series-name" placeholder="系列名称" class="form-control" />
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-series-code"> 系列编码:</label>
									<div class="col-sm-8">
										<input type="text" name="code" id="add-series-code" placeholder="系列编码" class="form-control" />
									</div>
								</div>
								<div class="space-8"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-series-description"> 系列描述： </label>
									<div class="col-sm-8">
										<textarea class="form-control" name="description" id="add-series-description" placeholder="系列描述"></textarea>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<div class="col-lg-4 col-lg-offset-5">
									<button type="button" class="btn btn-primary" onclick="submit_addSeriesInfo()">确定</button>
									<button type="button" class="btn btn-default" data-dismiss="modal" id="reset_addSeriesInfo">关闭</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 编辑系列表单 -->
			<div class="modal fade" id="editSeriesInfoModal" tabindex="-1"
				role="dialog" aria-labelledby="editSeriesInfoModalLabel">
				<div class="modal-dialog" role="document" style="margin:150px 450px">
					<div class="modal-content">
						<form id="editSeriesInfoForm" class="form-horizontal" role="form">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="close_editSeriesInfo">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="editSeriesInfoModalLabel">编辑系列</h4>
							</div>
							<div class="modal-body">
									<input type="hidden" name="id" id="edit-series-id"/>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="edit-series-name"> 系列名称:</label>
										<div class="col-sm-8">
											<input type="text" name="name" id="edit-series-name" placeholder="系列名称" class="form-control" />
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="edit-series-code"> 系列码:</label>
										<div class="col-sm-8">
											<input type="text" name="code" id="edit-series-code" placeholder="系列码" class="form-control" />
										</div>
									</div>
									<div class="space-8"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="edit-series-description"> 系列描述： </label>
										<div class="col-sm-8">
											<textarea class="form-control" name="description" id="edit-series-description" placeholder="系列描述"></textarea>
										</div>
									</div>
							</div>
							<div class="modal-footer">
								<div class="col-lg-4 col-lg-offset-5">
									<button type="button" class="btn btn-primary" id="submit_editSeriesInfo">确定</button>
									<button type="button" class="btn btn-default" data-dismiss="modal" id="reset_editSeriesInfo">关闭</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 添加一颗星 -->
			<div class="modal fade" id="addStarInfoModal" tabindex="-1"
				role="dialog" aria-labelledby="addStarInfoModalLabel">
				<div class="modal-dialog" role="document" style="margin:150px 450px">
					<div class="modal-content">
						<form id="addStarInfoForm" class="form-horizontal" role="form">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="close_addStarInfo">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="addStarInfoModalLabel">添加星</h4>
							</div>
							<div class="modal-body">
								<input type="hidden" name="datagridId" id="add-star-datagridId"/>
								<input type="hidden" name="seriesId" id="add-star-seriesId"/>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-star-name"> 星名称:</label>
									<div class="col-sm-8">
										<input type="text" name="name" id="add-star-name" placeholder="星名称" class="form-control" />
									</div>
								</div>
								<div class="space-8"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-star-code"> 星编码:</label>
									<div class="col-sm-8">
										<input type="text" name="code" id="add-star-code" placeholder="星编码" class="form-control" />
									</div>
								</div>
								<div class="space-8"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-star-beginDate"> 开始运行时间:</label>
									<div class="col-sm-8">
										<input type="text" class="form-control input-mask-date" name="beginDate" id="add-star-beginDate"
											placeholder="yyyy-MM-dd HH:mm:ss">
										<div id="getBeginTime"></div>
									</div>
								</div>
								<div class="space-8"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-star-description"> 星描述： </label>
									<div class="col-sm-8">
										<textarea class="form-control" name="description" id="add-star-description" placeholder="星描述"></textarea>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<div class="col-lg-4 col-lg-offset-5">
									<button type="button" class="btn btn-primary" id="submit_addStarInfo">确定</button>
									<button type="button" class="btn btn-default" data-dismiss="modal" id="reset_addStarInfo">关闭</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 编辑一颗星 -->
			<div class="modal fade" id="editStarInfoModal" tabindex="-1" role="dialog" aria-labelledby="editStarInfoModalLabel">
				<div class="modal-dialog" role="document" style="margin:150px 450px">
					<div class="modal-content">
						<form id="editStarInfoForm" class="form-horizontal" role="form">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="close_editStarInfo">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="editStarInfoModalLabel">添加星</h4>
							</div>
							<div class="modal-body">
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="edit-star-name"> 星名称:</label>
									<div class="col-sm-8">
										<input type="text" name="name" id="edit-star-name" placeholder="星名称" class="form-control" />
									</div>
								</div>
								<div class="space-8"></div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="edit-star-code"> 星编码:</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="code" id="edit-star-code" placeholder="星编码" class="form-control" />
                                    </div>
                                </div>
								<div class="space-8"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="edit-star-beginDate"> 开始运行时间:</label>
									<div class="col-sm-8">
										<input type="text" class="form-control input-mask-date" name="beginDate" id="edit-star-beginDate"
											placeholder="yyyy-MM-dd HH:mm:ss">
									</div>
								</div>
								<div class="space-8"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="edit-star-description"> 星描述： </label>
									<div class="col-sm-8">
										<textarea class="form-control" name="description" id="edit-star-description" placeholder="星描述"></textarea>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<div class="col-lg-4 col-lg-offset-5">
									<button type="submit" class="btn btn-primary" data-dismiss="modal" id="submit_editStarInfo">确定</button>
									<button type="button" class="btn btn-default" data-dismiss="modal" id="reset_editStarInfo">关闭</button>
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
		</div><!-- /.page-content -->
	</div><!-- /.main-content -->
<script type="text/javascript">

var galaxyGrid;
$(function() {
	
// 	$('#add-star-beginDate').mask('9999-99-99 99:99:99');
// 	$('#edit-star-beginDate').mask('9999-99-99 99:99:99');
	
	galaxyGrid = $('#galaxyList').datagrid({
		view : detailview,
		url: '${pageContext.request.contextPath}/admin/series/getList',
		rownumbers: true,
		fitColumns:true,
		idField: 'id',//'id',
		toolbar : '#toolbar',
		pageSize: 10,
		pagination: true,
		pageList: [10, 20, 30, 40, 50, 60, 70, 80, 90, 100],
        frozenColumns: [[{
            title: 'id',
            field: 'id',
            width: 50,
            checkbox: true
        }]],
        columns: [[{
            field: 'name',
            title: '名称',
            width: 80,
        }, {
            field: 'code',
            title: '编码',
            width: 50
        },{
			field : 'runDays',
			title : '设备累计工作时间(天)',
			width : 50
		}, {
            field: 'description',
            title: '描述',
            width: 100
        }
        ]],
		onLoadError:function(data){
			$.messager.alert("信息", "暂无数据信息", "error");
		},	
		detailFormatter : function(index, row) {
			return '<div><table id="ddv-' + index + '"></table></div>';
		},
		onExpandRow : function(index, row) {
			var seriesCode = row.code;
			var seriesId = row.id;
			var subgridId = 'ddv-' + index;
			$('#' + subgridId).datagrid({
				url : '${pageContext.request.contextPath}/admin/star/getStars?seriesId='+ row.id,
						//title: '星星项列表[所属组：'+row.Name+']',
						fitColumns : true,
						rownumbers : true,
						singleSelect : true,
						loadMsg : '正在载入系列下面的星项，请稍后...',
						height : 'auto',
						toolbar : [ {
							text : '创建',
							iconCls : 'icon-add',
							handler : function() {
								createStar(subgridId,row.id);
							}
						}

						],
						frozenColumns: [[{
				            title: 'id',
				            field: 'id',
				            width: 20,
				            checkbox: false
				        }]],
						columns : [ [
								{
									field : 'name',
									title : '名称',
									width : 30
								},
								{
                                    field : 'code',
                                    title : '星编码',
                                    width : 30
                                },
								{
									field : 'beginDate',
									title : '开始运行时间',
									width : 80
								},
								{
									field : 'runDays',
									title : '设备累计工作时间(天)',
									width : 80
								},
								{
									field : 'editDevice',
									title : '设备管理',
									width : 50,
									formatter: function (value, row, index) {
						                return "<a href=\"javascript:doEditDevice('" + seriesId + "','" + row.id +"');\"" + " title='设备管理'>设备管理</a>";
						            }
								},
								{
						            field: 'editParam',
						            title: '参数列表',
						            width: 50,
						            formatter: function (value, row, index) {
						                return "<a href=\"javascript:doEditParam('" + seriesCode + "','" + row.code +"');\"" + " title='参数列表'>参数列表</a>";
						            }
						        },
								{
									field : 'operation',
									title : '操作选项',
									align : 'center',
									width : 120,
									formatter : function(value,row,index) {
										// var editStr = "<a name=\"operButton\"  class=\"easyui-linkbutton\" iconcls=\"icon-edit\"  plain=\"true\" href=\"javascript:EditPermissionItem('" + row.Id + "');\">编辑</a>";
										var editStr = "<a class=\"l-btn l-btn-plain\" href=\"javascript:editStarInfo('"
												+ subgridId
												+ "','"
												+ row.id
												+ "')\" style=\"float: left;\"><span class=\"l-btn-left\" style=\"margin:0px 20px;\" ><span class=\"l-btn-text icon-edit\" style=\"/*padding-left: 60px;*/\">编辑</span></span></a>";
										// var delStr = "<a name=\"operButton\" class=\"easyui-linkbutton\"  iconcls=\"icon-remove\"  plain=\"true\" href=\"javascript:DeletePermissionItem('" + row.Id + "');\"> 删除</a>"
										var delStr = "<a class=\"l-btn l-btn-plain\" href=\"javascript:deleteStarInfo('"
												+ subgridId
												+ "','"
												+ row.id
												+ "','"
												+ row.name
												+ "')\" style=\"float: left;\"><span class=\"l-btn-left\" style=\"margin:0px 20px;\" ><span class=\"l-btn-text icon-remove\" style=\"/*padding-left: 80px;*/\">删除</span></span></a>";
										return editStr
												+ '<div class="datagrid-btn-separator"></div>'
												+ delStr;
									}
								} ] ],
						onResize : function() {
							$('#galaxyList').datagrid('fixDetailRowHeight',index);
						},
						onLoadSuccess : function() {
							setTimeout(function() {
										$('#galaxyList').datagrid('fixDetailRowHeight',index);
									   }, 0);

						}
					});
				$('#galaxyList').datagrid('fixDetailRowHeight', index);
			}
		});
	});
	
	function doEditParam(series,star){
		 window.location.href='${pageContext.request.contextPath}/admin/parameter/index/'+series+'/'+star+'/'; 
	}
	function doEditDevice(series,star){
		 window.location.href='${pageContext.request.contextPath}/admin/device/index/'+series+'/'+star+'/'; 
	}
	
	function reloadDatagrid(datagridId) {
		$('#' + datagridId).datagrid("unselectAll");
		$('#' + datagridId).datagrid('reload');
		
		var arr = datagridId.split('-');
		galaxyGrid.datagrid('fixDetailRowHeight', arr[1]);
	}
	
	$('#addSeriesInfoModal-btn').click(function(){
		$('#addSeriesInfoModal').modal('show');
	});
	$('#addSeriesInfoModal').on('hide.bs.modal', function () {
		$('#addSeriesInfoForm').data('bootstrapValidator').resetForm(true);
	});
	//提交创建系列
	function submit_addSeriesInfo(){
		var f = $('#addSeriesInfoForm');
		f.data('bootstrapValidator').validate();
		var isValid = f.data('bootstrapValidator').isValid();
		if(!isValid){
			//top.alertMsg('错误', '请满足提交条件！');
			return false;
		}
		var name = $('#add-series-name').val();
		var code = $('#add-series-code').val();
		var description = $('#add-series-description').val();
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/series/createSeries',
			data : {
				name : name,
				code : code,
				description : description
			},
			cache : false,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					$('#addSeriesInfoModal').modal('hide');
					galaxyGrid.datagrid("unselectAll");
					galaxyGrid.datagrid('reload');
					top.showMsg('提示', data.msg);
				} else {
					top.alertMsg('警告', data.msg);
				}
			}
		});
	}	
	
	//编辑系列信息
	$('#editSeriesInfoModal').on('hide.bs.modal', function () {
		$('#editSeriesInfoForm').data('bootstrapValidator').resetForm(true);
	});
	function editSeries(){
		var rows = galaxyGrid.datagrid('getSelections');
		if (rows.length > 0) {
			if (rows.length == 1) {
				//赋值
				$('#edit-series-id').val(rows[0].id);
				$('#edit-series-name').val(rows[0].name);
				$('#edit-series-code').val(rows[0].code);
				$('#edit-series-description').val(rows[0].description);
				//弹出编辑框
				$('#editSeriesInfoModal').modal('show');
				
			}else{
				top.showMsg("提示", "只能编辑一列！");
			}
		}else {
			top.showMsg("提示", "请选择要编辑的系列！");
		}
	}
	$('#submit_editSeriesInfo').click(function(){
		var f = $('#editSeriesInfoForm');
		f.data('bootstrapValidator').validate();
		var isValid = f.data('bootstrapValidator').isValid();
		if(!isValid){
			return false;
		}
		
		var id = $('#edit-series-id').val();
		var name = $('#edit-series-name').val();
		var code = $('#edit-series-code').val();
		var description = $('#edit-series-description').val();
		$.ajax({
            url : '${pageContext.request.contextPath}/admin/series/editSeries',
            data : {
                id : id,
                name : name,
                code : code,
                description : description
            },
            cache : false,
            dataType : "json",
            success : function(data) {
                if (data.success) {
                	$('#editSeriesInfoModal').modal('hide');
                    galaxyGrid.datagrid("unselectAll");
                    galaxyGrid.datagrid('reload');
                    top.showMsg('提示', data.msg);
                } else {
                    top.alertMsg('警告', data.msg);
                }
            }
        });
	});
	
	//删除已选中的系列
	function deleteSeries(){
		var ids = [];
		var rows = galaxyGrid.datagrid('getSelections');
		if (rows.length > 0) {
			var names = [];
			for ( var i = 0; i < rows.length; i++) {
				names.push(rows[i].name);
			}
			swal({
				title : "你是否确定删除?",
				text : names.join(','),
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
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/admin/series/deleteSeries',
						data : {
							seriesIds : ids.join(',')
						},
						cache : false,
						dataType : "json",
						success : function(data) {
							if (data.success) {
								galaxyGrid.datagrid("unselectAll");
								galaxyGrid.datagrid('reload');
// 								top.showMsg('提示', data.msg);
								swal("删除成功!","","success");
							} else {
// 								top.alertMsg('警告', data.msg);
								swal("删除失败", data.obj,"error");
							}
						}
					});
					
				} else {
					swal("取消删除", "","error");
				}
			});
		} else {
			top.showMsg("提示", "请选择要删除的系列！");
		}
	}
	
// 	$('#add-star-beginDate').click(function(){
// 		jeDate({
//  			dateCell:"#add-star-beginDate",//直接显示日期层的容器，可以是ID  CLASS
//  			format:"YYYY-MM-DD hh:mm:ss",//日期格式
//  			isinitVal:false, //是否初始化时间
//  			festival:false, //是否显示节日
//  			isTime:true, //是否开启时间选择
//  			minDate:"2014-09-19 00:00:00",//最小日期
//  			maxDate:jeDate.now(0), //设定最大日期为当前日期
//  		});
// 	});
	
// 	$('#getBeginTime').calendar({
//         trigger: '#add-star-beginDate',
//         zIndex: 999,
// 		format: 'yyyy-mm-dd',
//         onSelected: function (view, date, data) {
//         },
//         onClose: function (view, date, data) {
//         }
//     });
	
	//在一个系列下创建一颗星
	$('#addStarInfoModal').on('hide.bs.modal', function () {
		$('#addStarInfoForm').data('bootstrapValidator').resetForm(true);
	});
	function createStar(datagridId, seriesId){
		$('#add-star-datagridId').val(datagridId);
		$('#add-star-seriesId').val(seriesId);
 		$('#addStarInfoModal').modal('show');
	}
	$('#submit_addStarInfo').click(function(){
		var f = $('#addStarInfoForm');
		f.data('bootstrapValidator').validate();
		var isValid = f.data('bootstrapValidator').isValid();
		if(!isValid){
			return false;
		}
		var datagridId = $('#add-star-datagridId').val();
		var seriesId = $('#add-star-seriesId').val();
		var name = $('#add-star-name').val();
		var code = $('#add-star-code').val();
		var beginDate = $('#add-star-beginDate').val();
		var description = $('#add-star-description').val();
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/star/createStar',
			data : {
				seriesId : seriesId,
				name : name,
				code : code,
				beginDate : beginDate,
				description : description
			},
			cache : false,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					$('#addStarInfoModal').modal('hide');
					reloadDatagrid(datagridId);
					top.showMsg('提示', data.msg);
				} else {
					top.alertMsg('警告', data.msg);
				}
			}
		});
	});
 		
	function editStarInfo(datagridId, starId) {
		var rows = $('#' + datagridId).datagrid("getSelections");
		var oldName = rows[0].name;
		var oldBeginDate = rows[0].beginDate;
		var oldDescription = rows[0].description;
		$('#edit-star-name').val(oldName);
		$('#edit-star-beginDate').val(oldBeginDate);
		$('#edit-star-description').val(oldDescription);
		$('#editStarInfoModal').modal('show');
		//$('#editStarInfoForm').form('load', '${pageContext.request.contextPath}/admin/star/getStarForm' + '?starId=' + starId);
 		$('#submit_editStarInfo').click(function(){
			var name = $('#edit-star-name').val();
			var beginDate = $('#edit-star-beginDate').val();
			var description = $('#edit-star-description').val();
			var isValid = $('#editStarInfoForm').data('bootstrapValidator').isValid();
			if(isValid){
				if(oldName != name || oldDescription != description || oldBeginDate != oldBeginDate){
					$.ajax({
						url : '${pageContext.request.contextPath}/admin/star/editStar',
						data : {
							id : starId,
							name : name,
							beginDate : beginDate,
							description : description
						},
						cache : false,
						dataType : "json",
						success : function(data) {
							if (data.success) {
								reloadDatagrid(datagridId);
								top.showMsg('提示', data.msg);
							} else {
								top.alertMsg('警告', data.msg);
							}
						}
					});
				}else{
					top.showMsg('提示', "星信息没有被修改！");
				}
			}
			$('#editStarInfoForm').data('bootstrapValidator').resetForm(true);
 		});

	}
	function deleteStarInfo(datagridId, starId,starName){
// 		console.log('datagridId:' + datagridId);
// 		console.log('starId:'+starId);
// 		console.log('starName:'+starName);
		swal({
			title : "你是否确定删除?",
			text : starName,
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
				$.ajax({
					url : '${pageContext.request.contextPath}/admin/star/deleteStar',
					data : {
						starId : starId
					},
					cache : false,
					dataType : "json",
					success : function(data) {
						if (data.success) {
//								top.showMsg('提示', data.msg);
							swal("删除成功!","","success");
							galaxyGrid.datagrid("unselectAll");
							galaxyGrid.datagrid('reload');
						} else {
//								top.alertMsg('警告', data.msg);
							swal("删除失败", data.obj,"error");
						}
					}
				});
				
			} else {
				swal("取消删除", "","error");
			}
		});
	}
</script>
</body>
</html>
