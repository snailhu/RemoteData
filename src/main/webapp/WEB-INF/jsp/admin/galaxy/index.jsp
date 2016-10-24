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
	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/content/jeDate/jedate/skin/jedate.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/content/jeDate/jedate/jedate.js"></script>
    
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.css">
	<script src="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.min.js"></script>
	
	<!--     <link rel="stylesheet" href="${pageContext.request.contextPath}/static/content/bootstrapValidator/vendor/bootstrap/css/bootstrap.css"/> -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/content/bootstrapValidator/dist/css/bootstrapValidator.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/content/bootstrapValidator/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/content/bootstrapValidator/dist/js/bootstrapValidator.js"></script>
    
	<script src="${pageContext.request.contextPath}/static/content/js/outlook2.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/content/js/validDate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/content/jquery-easyui-datagridview/datagrid-detailview.js"></script>
  	<script src="${pageContext.request.contextPath}/static/assets/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/assets/js/typeahead-bs2.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/assets/js/bootstrap-colorpicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/assets/js/jquery.knob.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/assets/js/jquery.autosize.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/assets/js/jquery.maskedinput.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/assets/js/bootstrap-tag.min.js"></script>
	
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
.form-horizontal {
    margin-bottom: 0px;
}
  </style>
  <script type="text/javascript">
  	$(function(){
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
                      }
                  }
              },
              description : {
            	  message: '',
              }
          }
      });
	$('#close_addSeriesInfo').click(function() {
	    $('#addSeriesInfoForm').data('bootstrapValidator').resetForm(true);
	});	
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
                      }
                  }
              },
              description : {
            	  message: '',
              }
          }
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
                  }
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
          },
          description : {
        	  message: '',
          }
      }
  	});  
  	$('#close_addStarInfo').click(function() {
	    $('#addStarInfoForm').data('bootstrapValidator').resetForm(true);
	});	
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
                    }
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
				<h1 style="text-align: left;">星系管理</h1>
			</div>
			<div id="content" region="center" style="overflow: hidden">
				<div id="toolbar" class="datagrid-toolbar" style="height: 28px;">
					<div style="height: 28px;">
						<button class="easyui-linkbutton" iconcls="icon-add" style="float: left;" 
							data-toggle="modal" data-target="#addSeriesInfoModal">创建</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-edit" style="float: left;"
							onclick="editSeries();">编辑</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-remove"
							onclick="deleteSeries();" style="float: left;">删除</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-undo"
							onclick="galaxyGrid.datagrid('unselectAll');" style="float: left;">取消选中</button>
					</div>
				</div>
			</div>
			<table id="galaxyList" fit="false" border="false" height="400px">
				<thead>
					<tr>
						<th field="ck" checkbox="true"></th>
						<th field="name" width="80">名称</th>
						<th field="code" width="80">编码</th>
						<th field="description" width="120" align="center">描述</th>
						<th field="createDate" width="100" align="center">创建日期</th>
					</tr>
				</thead>
			</table>
			<!-- 添加系列表单 -->
			<div class="modal fade" id="addSeriesInfoModal" tabindex="-1" role="dialog" aria-labelledby="addSeriesInfoModalLabel">
				<div class="modal-dialog" role="document" style="margin:55px -300px">
					<div class="modal-content">
						<form id="addSeriesInfoForm" class="form-horizontal" role="form">
							<div class="modal-header">
<!-- 								<button type="button" class="close" data-dismiss="modal" -->
<!-- 									aria-label="Close"> -->
<!-- 									<span aria-hidden="true">&times;</span> -->
<!-- 								</button> -->
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
									<button type="button" class="btn btn-default" data-dismiss="modal" id="close_addSeriesInfo">关闭</button>
									<button type="submit" class="btn btn-primary" data-dismiss="modal" onclick="submit_addSeriesInfo()">确定</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 编辑系列表单 -->
			<div class="modal fade" id="editSeriesInfoModal" tabindex="-1"
				role="dialog" aria-labelledby="editSeriesInfoModalLabel">
				<div class="modal-dialog" role="document" style="margin:55px -300px">
					<div class="modal-content">
						<form id="editSeriesInfoForm" class="form-horizontal" role="form">
							<div class="modal-header">
<!-- 								<button type="button" class="close" data-dismiss="modal" -->
<!-- 									aria-label="Close"> -->
<!-- 									<span aria-hidden="true">&times;</span> -->
<!-- 								</button> -->
								<h4 class="modal-title" id="editSeriesInfoModalLabel">编辑系列</h4>
							</div>
							<div class="modal-body">
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
									<button type="button" class="btn btn-default" data-dismiss="modal" id="close_editSeriesInfo">关闭</button>
									<button type="submit" class="btn btn-primary" data-dismiss="modal" id="submit_editSeriesInfo">确定</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 添加一颗星 -->
			<div class="modal fade" id="addStarInfoModal" tabindex="-1"
				role="dialog" aria-labelledby="addStarInfoModalLabel">
				<div class="modal-dialog" role="document" style="margin:55px -300px">
					<div class="modal-content">
						<form id="addStarInfoForm" class="form-horizontal" role="form">
							<div class="modal-header">
<!-- 								<button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!-- 									<span aria-hidden="true">&times;</span> -->
<!-- 								</button> -->
								<h4 class="modal-title" id="addStarInfoModalLabel">添加星</h4>
							</div>
							<div class="modal-body">
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
									<button type="button" class="btn btn-default" data-dismiss="modal" id="close_addStarInfo">关闭</button>
									<button type="submit" class="btn btn-primary" data-dismiss="modal" id="submit_addStarInfo">确定</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 编辑一颗星 -->
			<div class="modal fade" id="editStarInfoModal" tabindex="-1" role="dialog" aria-labelledby="editStarInfoModalLabel">
				<div class="modal-dialog" role="document" style="margin:55px -300px">
					<div class="modal-content">
						<form id="editStarInfoForm" class="form-horizontal" role="form">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
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
									<button type="button" class="btn btn-default" data-dismiss="modal" id="close_editStarInfo">关闭</button>
									<button type="submit" class="btn btn-primary" data-dismiss="modal" id="submit_editStarInfo">确定</button>
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
// jeDate({
//     dateCell:"#add-star-beginDate",//isinitVal:true,
//     format:"YYYY-MM-DD",
//     isTime:false, //isClear:false,
//     minDate:"2014-09-19 00:00:00",
// });
var galaxyGrid;
$(function() {
	$('#add-star-beginDate').mask('9999-99-99 99:99:99');
	$('#edit-star-beginDate').mask('9999-99-99 99:99:99');
	
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
		onLoadError:function(data){
			$.messager.alert("信息", "暂无数据信息", "error");
		},	
		detailFormatter : function(index, row) {
			return '<div><table id="ddv-' + index + '"></table></div>';
		},
		onExpandRow : function(index, row) {
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
						columns : [ [
								{
									field : 'ck',
									checkbox : false
								},
								{
									field : 'name',
									title : '名称',
									width : 80
								},
								{
                                    field : 'code',
                                    title : '星编码',
                                    width : 50
                                },
								{
									field : 'beginDate',
									title : '开始运行时间',
									width : 100
								},
								{
									field : 'description',
									title : '描述',
									width : 100
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
												+ "')\" style=\"float: left;\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\" style=\"padding-left: 60px;\">编辑</span></span></a>";
										// var delStr = "<a name=\"operButton\" class=\"easyui-linkbutton\"  iconcls=\"icon-remove\"  plain=\"true\" href=\"javascript:DeletePermissionItem('" + row.Id + "');\"> 删除</a>"
										var delStr = "<a class=\"l-btn l-btn-plain\" href=\"javascript:deleteStarInfo('"
												+ subgridId
												+ "','"
												+ row.id
												+ "','"
												+ row.name
												+ "')\" style=\"float: left;\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-remove\" style=\"padding-left: 80px;\">删除</span></span></a>";
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
	function reloadDatagrid(datagridId) {
		$('#' + datagridId).datagrid("unselectAll");
		$('#' + datagridId).datagrid('reload');
		
		var arr = datagridId.split('-');
		galaxyGrid.datagrid('fixDetailRowHeight', arr[1]);
	}
	//提交创建系列
	function submit_addSeriesInfo(){
		var name = $('#add-series-name').val();
		var code = $('#add-series-code').val();
		var description = $('#add-series-description').val();
		var isValid = $('#addSeriesInfoForm').data('bootstrapValidator').isValid();
		if(isValid){
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
						galaxyGrid.datagrid("unselectAll");
						galaxyGrid.datagrid('reload');
						top.showMsg('提示', data.msg);
					} else {
						top.alertMsg('警告', data.msg);
					}
				}
			});
		}
		$('#addSeriesInfoForm').data('bootstrapValidator').resetForm(true);
	}	
	//编辑系列信息
	function editSeries(){
		var rows = galaxyGrid.datagrid('getSelections');
		if (rows.length > 0) {
			if (rows.length == 1) {
				//赋值
				var oldName = rows[0].name;
				var oldCode = rows[0].code;
				var oldDescription = rows[0].description;
				$('#edit-series-name').val(oldName);
				$('#edit-series-code').val(oldCode);
				$('#edit-series-description').val(oldDescription);
				//弹出编辑框
				$('#editSeriesInfoModal').modal('show');
				$('#submit_editSeriesInfo').click(function(){
					var name = $('#edit-series-name').val();
					var code = $('#edit-series-code').val();
					var description = $('#edit-series-description').val();
					var isValid = $('#editSeriesInfoForm').data('bootstrapValidator').isValid();
					if(isValid){
						if(name != oldName || code != oldCode || description != oldDescription){
	                        $.ajax({
	                            url : '${pageContext.request.contextPath}/admin/series/editSeries',
	                            data : {
	                                id : rows[0].id,
	                                name : name,
	                                code : code,
	                                description : description
	                            },
	                            cache : false,
	                            dataType : "json",
	                            success : function(data) {
	                                if (data.success) {
	                                    galaxyGrid.datagrid("unselectAll");
	                                    galaxyGrid.datagrid('reload');
	                                    top.showMsg('提示', data.msg);
	                                } else {
	                                    top.alertMsg('警告', data.msg);
	                                }
	                            }
	                        });
	                    }else{
	                        top.showMsg('提示', "系列信息没有被修改！");
	                    }
					}
					$('#editSeriesInfoForm').data('bootstrapValidator').resetForm(true);
				});
			}else{
				top.showMsg("提示", "只能编辑一列！");
			}
		}else {
			top.showMsg("提示", "请选择要编辑的系列！");
		}
	}
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
	
	//在一个系列下创建一颗星
	function createStar(datagridId, seriesId){
 		$('#addStarInfoModal').modal('show');
 		$('#submit_addStarInfo').click(function(){
			var name = $('#add-star-name').val();
			var code = $('#add-star-code').val();
			var beginDate = $('#add-star-beginDate').val();
			var description = $('#add-star-description').val();
			var isValid = $('#addStarInfoForm').data('bootstrapValidator').isValid();
			if(isValid){
				$.ajax({
					url : '${pageContext.request.contextPath}/admin/star/createStar',
					data : {
						seriesId : seriesId,
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
			}
			$('#addStarInfoForm').data('bootstrapValidator').resetForm(true);
 		});
	}
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
