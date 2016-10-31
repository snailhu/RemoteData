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
.icon-undo {
    background: no-repeat center center;
}
  </style>
  <script type="text/javascript">
  	$(function(){
  		$('#addPermissionGroupInfoForm').bootstrapValidator({
          message: '权限组名称不能为空',
          feedbackIcons: {
              valid: 'glyphicon glyphicon-ok',
              invalid: 'glyphicon glyphicon-remove',
              validating: 'glyphicon glyphicon-refresh'
          },
          fields: {
              name: {
                  message: '权限组名称不能为空',
                  validators: {
                      notEmpty: {
                          message: '权限组名称不能为空'
                      }
                  }
              },
              description : {
            	  message: '',
            	  validators: {
                     
                  }
              }
          }
      });
	$('#reset_addPermissionGroupInfo').click(function() {
	    $('#addPermissionGroupInfoForm').data('bootstrapValidator').resetForm(true);
	});	
	$('#close_addPermissionGroupInfo').click(function() {
	    $('#addPermissionGroupInfoForm').data('bootstrapValidator').resetForm(true);
	});
  	$('#editPermissionGroupInfoForm').bootstrapValidator({
          message: '权限组名称不能为空',
          feedbackIcons: {
              valid: 'glyphicon glyphicon-ok',
              invalid: 'glyphicon glyphicon-remove',
              validating: 'glyphicon glyphicon-refresh'
          },
          fields: {
              name: {
                  message: '权限组名称不能为空',
                  validators: {
                      notEmpty: {
                          message: '权限组名称不能为空'
                      }
                  }
              },
              description : {
                  message: '',
                  validators: {
                      
                  }
              }
          }
    });
  	$('#reset_editPermissionGroupInfo').click(function() {
	    $('#editPermissionGroupInfoForm').data('bootstrapValidator').resetForm(true);
	});	
	$('#close_editPermissionGroupInfo').click(function() {
	    $('#editPermissionGroupInfoForm').data('bootstrapValidator').resetForm(true);
	});
  	$('#addPermissionItemInfoForm').bootstrapValidator({
      message: 'This value is not valid',
      feedbackIcons: {
          valid: 'glyphicon glyphicon-ok',
          invalid: 'glyphicon glyphicon-remove',
          validating: 'glyphicon glyphicon-refresh'
      },
      fields: {
    	  displayName: {
              message: '权限显示名称不能为空',
              validators: {
                  notEmpty: {
                      message: '权限显示名称不能为空'
                  }
              }
          },
          code: {
              validators: {
            	  notEmpty: {
                      message: '权限代码不能为空'
                  },
                  regexp : { 
                      regexp : /^[a-zA-Z]+$/,
                      message : '权限代码只能由字母组成'
                  },
              }
          },
          description : {
              message: '',
              validators: {
                  
              }
          }
      }
  	});  
  	$('#reset_addPermissionItemInfo').click(function() {
	    $('#addPermissionItemInfoForm').data('bootstrapValidator').resetForm(true);
	});	
	$('#close_addPermissionItemInfo').click(function() {
	    $('#addPermissionItemInfoForm').data('bootstrapValidator').resetForm(true);
	});
    $('#editPermissionItemInfoForm').bootstrapValidator({
//        live: 'disabled',
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	displayName: {
                message: '权限显示名称不能为空',
                validators: {
                    notEmpty: {
                        message: '权限显示名称不能为空'
                    }
                }
            },
            code: {
                validators: {
              	    notEmpty: {
                        message: '权限代码不能为空'
                    },
		            regexp : { 
		                regexp : /^[a-zA-Z]+$/,
		                message : '权限代码只能由字母组成'
		            },
                }
            },
            description : {
                message: '',
                validators: {
                    
                }
            }
        }
    });  	
    $('#reset_editPermissionItemInfo').click(function() {
	    $('#editPermissionItemInfoForm').data('bootstrapValidator').resetForm(true);
	});	
	$('#close_editPermissionItemInfo').click(function() {
	    $('#editPermissionItemInfoForm').data('bootstrapValidator').resetForm(true);
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
				<h1 style="text-align: left;">权限管理</h1>
			</div>
			<div id="content" region="center" style="overflow: hidden">
				<div id="toolbar" class="datagrid-toolbar" style="height: 28px;">
					<div style="height: 28px;">
						<button class="easyui-linkbutton" iconcls="icon-add" plain="true" style="float: left;" 
							data-toggle="modal" data-target="#addPermissionGroupModal">创建</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-remove" plain="true" style="float: left;"
							onclick="deletePermissionGroup();">删除</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-edit" plain="true" style="float: left;"
							onclick="editPermissionGroup();">编辑</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-undo" plain="true" style="float: left;"
							onclick="permissionGrid.datagrid('unselectAll');">取消选中</button>
					</div>
				</div>
			</div>
			<table id="permissionList" fit="false" border="false" height="450px">
				<thead>
					<tr>
						<th field="ck" checkbox="true"></th>
						<th field="name" width="80">名称</th>
						<th field="description" width="200" align="center">描述</th>
						<th field="createDate" width="120" align="center">创建日期</th>
					</tr>
				</thead>
			</table>
			<!-- 创建权限组 -->
			<div class="modal fade" id="addPermissionGroupModal" tabindex="-1"
				role="dialog" aria-labelledby="addPermissionGroupModalLabel">
				<div class="modal-dialog" role="document" style="margin:150px 450px">
					<div class="modal-content">
						<form id="addPermissionGroupInfoForm" class="form-horizontal" role="form">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="close_addPermissionGroupInfo">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="addPermissionGroupModalLabel">权限组信息</h4>
							</div>
							<div class="modal-body">
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-permissionGroup-name"> 分组名称：</label>
									<div class="col-sm-8">
										<input type="text" name="name" id="add-permissionGroup-name" placeholder="权限组名称" class="form-control" />
									</div>
								</div>
								<div class="space-8"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-permissionGroup-description"> 分组描述： </label>
									<div class="col-sm-8">
										<textarea class="form-control" name="description" id="add-permissionGroup-description" placeholder="权限组描述"></textarea>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<div class="col-lg-4 col-lg-offset-5">
									<button type="button" class="btn btn-default" data-dismiss="modal" id="reset_addPermissionGroupInfo">关闭</button>
									<button type="submit" class="btn btn-primary" data-dismiss="modal" onclick="submit_addPermissionGroupInfo()">确定</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 编辑权限组 -->
			<div class="modal fade" id="editPermissionGroupModal" tabindex="-1"
				role="dialog" aria-labelledby="editPermissionGroupModalLabel">
				<div class="modal-dialog" role="document" style="margin:150px 450px">
					<div class="modal-content">
						<form id="editPermissionGroupInfoForm" class="form-horizontal" role="form">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="close_editPermissionGroupInfo">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="editPermissionGroupModalLabel">权限组信息</h4>
							</div>
							<div class="modal-body">
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="edit-permissionGroup-name"> 分组名称:</label>
									<div class="col-sm-8">
										<input type="text" name="name" id="edit-permissionGroup-name" placeholder="权限组名称" class="form-control" />
									</div>
								</div>
								<div class="space-8"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="edit-permissionGroup-description"> 分组描述： </label>
									<div class="col-sm-8">
										<textarea class="form-control" name="description" id="edit-permissionGroup-description" placeholder="分组描述"></textarea>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<div class="col-lg-4 col-lg-offset-5">
									<button type="button" class="btn btn-default" data-dismiss="modal" id="reset_editPermissionGroupInfo">关闭</button>
									<button type="submit" class="btn btn-primary" data-dismiss="modal" id="submit_editPermissionGroupInfo">确定</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 添加权限项 -->
			<div class="modal fade" id="addPermissionItemModal" tabindex="-1"
				role="dialog" aria-labelledby="addPermissionItemModalLabel">
				<div class="modal-dialog" role="document" style="margin:150px 450px">
					<div class="modal-content">
						<form id="addPermissionItemInfoForm" class="form-horizontal" role="form">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="close_addPermissionItemInfo">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="addPermissionItemModalLabel">权限项信息</h4>
							</div>
							<div class="modal-body">
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-permissionItem-permissionGroupName"> 所属权限组:</label>
									<div class="col-sm-8">
										<input type="text" name="permissionGroupName" id="add-permissionItem-permissionGroupName"
											placeholder="所属权限组名称" class="form-control" readonly="true" />
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-permissionItem-displayName"> 显示名称:</label>
									<div class="col-sm-8">
										<input type="text" name="displayName" id="add-permissionItem-displayName" placeholder="显示名称"
											class="form-control" />
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-permissionItem-code"> 权限代码:</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" name="code" id="add-permissionItem-code" placeholder="权限代码">
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-permissionItem-description"> 权限描述： </label>
									<div class="col-sm-8">
										<textarea class="form-control" name="description" id="add-permissionItem-description" placeholder="分组描述"></textarea>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<div class="col-lg-4 col-lg-offset-5">
									<button type="button" class="btn btn-default" data-dismiss="modal" id="reset_addPermissionItemInfo">关闭</button>
									<button type="submit" class="btn btn-primary" data-dismiss="modal" id="submit_addPermissionItemInfo">确定</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 编辑权限项 -->
			<div class="modal fade" id="editPermissionItemInfoModal" tabindex="-1" role="dialog"
				aria-labelledby="editPermissionItemInfoModalLabel">
				<div class="modal-dialog" role="document" style="margin:150px 450px">
					<div class="modal-content">
						<form id="editPermissionItemInfoForm" class="form-horizontal" role="form">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="close_editPermissionItemInfo">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="editPermissionItemInfoModalLabel">权限项信息</h4>
							</div>
							<div class="modal-body">
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="edit-permissionItem-displayName"> 显示名称:</label>
									<div class="col-sm-8">
										<input type="text" name="displayName" id="edit-permissionItem-displayName" placeholder="显示名称"
											class="form-control" />
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="edit-permissionItem-code"> 权限代码:</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" name="code" id="edit-permissionItem-code" placeholder="权限代码">
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="edit-permissionItem-description"> 权限描述： </label>
									<div class="col-sm-8">
										<textarea class="form-control" name="description" id="edit-permissionItem-description" placeholder="分组描述"></textarea>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<div class="col-lg-4 col-lg-offset-5">
									<button type="button" class="btn btn-default" data-dismiss="modal" id="reset_editPermissionItemInfo">关闭</button>
									<button type="submit" class="btn btn-primary" data-dismiss="modal" id="submit_editPermissionItemInfo">确定</button>
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

var permissionGrid;
$(function() {
	permissionGrid = $('#permissionList').datagrid({
		view : detailview,
		url: '${pageContext.request.contextPath}/admin/permission/getPermissionGroups',
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
				url : '${pageContext.request.contextPath}/admin/permission/getPermissionItems?permissionGroupId='+ row.id,
						//title: '星星项列表[所属组：'+row.Name+']',
						fitColumns : true,
						rownumbers : true,
						singleSelect : true,
						loadMsg : '正在载入权限项，请稍后...',
						height : 'auto',
						toolbar : [ {
							text : '创建',
							iconCls : 'icon-add',
							handler : function() {
								createPermissionItem(subgridId,row.id,row.name);
							}
						}
						],
						columns : [ [
								{
									field : 'displayName',
									title : '显示名称',
									width : 80
								},
								{
									field : 'code',
									title : '权限代码',
									width : 80
								},
								{
									field : 'description',
									title : '权限描述',
									width : 100
								},
								{
									field : 'operation',
									title : '操作选项',
									align : 'center',
									width : 80,
									formatter : function(value,row,index) {
										// var editStr = "<a name=\"operButton\"  class=\"easyui-linkbutton\" iconcls=\"icon-edit\"  plain=\"true\" href=\"javascript:EditPermissionItem('" + row.Id + "');\">编辑</a>";
										var editStr = "<a class=\"l-btn l-btn-plain\" href=\"javascript:editPermissionItemInfo('"
												+ subgridId
												+ "','"
												+ row.id
												+ "')\" style=\"float: left;\"><span class=\"l-btn-left\" style=\"margin:0px 20px;\" ><span class=\"l-btn-text icon-edit\" style=\"/*padding-left: 50px;*/\">编辑</span></span></a>";
										// var delStr = "<a name=\"operButton\" class=\"easyui-linkbutton\"  iconcls=\"icon-remove\"  plain=\"true\" href=\"javascript:DeletePermissionItem('" + row.Id + "');\"> 删除</a>"
										var delStr = "<a class=\"l-btn l-btn-plain\" href=\"javascript:deletePermissionItemInfo('"
												+ subgridId
												+ "','"
												+ row.id
												+ "','"
												+ row.displayName
												+ "')\" style=\"float: left;\"><span class=\"l-btn-left\" style=\"margin:0px 20px;\" ><span class=\"l-btn-text icon-remove\" style=\"/*padding-left: 50px;*/\">删除</span></span></a>";
										return editStr
												+ '<div class="datagrid-btn-separator"></div>'
												+ delStr;
									}
								} ] ],
						onResize : function() {
							$('#permissionList').datagrid('fixDetailRowHeight',index);
						},
						onLoadSuccess : function() {
							setTimeout(function() {
										$('#permissionList').datagrid('fixDetailRowHeight',index);
									   }, 0);

						}
					});
				$('#permissionList').datagrid('fixDetailRowHeight', index);
			}
		});
	});
	function reloadDatagrid(datagridId) {
		$('#' + datagridId).datagrid("unselectAll");
		$('#' + datagridId).datagrid('reload');
		
		var arr = datagridId.split('-');
		permissionGrid.datagrid('fixDetailRowHeight', arr[1]);
	}
	//提交创建权限组
	function submit_addPermissionGroupInfo(){
		var name = $('#add-permissionGroup-name').val();
		var description = $('#add-permissionGroup-description').val();
		var isValid = $('#addPermissionGroupInfoForm').data('bootstrapValidator').isValid();
        if(isValid){
        	$.ajax({
                url : '${pageContext.request.contextPath}/admin/permission/createPermissionGroup',
                data : {
                    name : name,
                    description : description
                },
                cache : false,
                dataType : "json",
                success : function(data) {
                    if (data.success) {
                        permissionGrid.datagrid("unselectAll");
                        permissionGrid.datagrid('reload');
                        top.showMsg('提示', data.msg);
                    } else {
                        top.alertMsg('警告', data.msg);
                    }
                }
            });
        }
		$('#addPermissionGroupInfoForm').data('bootstrapValidator').resetForm(true);
	}	
	//编辑权限组
	function editPermissionGroup(){
		var rows = permissionGrid.datagrid('getSelections');
		if (rows.length > 0) {
			if (rows.length == 1) {
				//赋值
				var oldName = rows[0].name;
				var oldDescription = rows[0].description;
				$('#edit-permissionGroup-name').val(oldName);
				$('#edit-permissionGroup-description').val(oldDescription);
				//弹出编辑框
				$('#editPermissionGroupModal').modal('show');
				$('#submit_editPermissionGroupInfo').click(function(){
					var name = $('#edit-permissionGroup-name').val();
					var description = $('#edit-permissionGroup-description').val();
					if(oldName != name || oldDescription != description){
						$.ajax({
                            url : '${pageContext.request.contextPath}/admin/permission/editPermissionGroup',
                            data : {
                                id : rows[0].id,
                                name : name,
                                description : description
                            },
                            cache : false,
                            dataType : "json",
                            success : function(data) {
                                if (data.success) {
                                    permissionGrid.datagrid("unselectAll");
                                    permissionGrid.datagrid('reload');
                                    top.showMsg('提示', data.msg);
                                } else {
                                    top.alertMsg('警告', data.msg);
                                }
                            }
                        });
					}else{
						top.showMsg('提示', "权限组信息没有被修改！");
					}
					$('#editPermissionGroupInfoForm').data('bootstrapValidator').resetForm(true);
				});
			}else{
				top.showMsg("提示", "只能编辑一列！");
			}
		}else {
			top.showMsg("提示", "请选择要编辑的权限组！");
		}
	}
	//删除已选中的权限组
	function deletePermissionGroup(){
		var ids = [];
		var rows = permissionGrid.datagrid('getSelections');
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
						url : '${pageContext.request.contextPath}/admin/permission/deletePermissionGroup',
						data : {
							permissionGroupIds : ids.join(',')
						},
						cache : false,
						dataType : "json",
						success : function(data) {
							if (data.success) {
								permissionGrid.datagrid("unselectAll");
								permissionGrid.datagrid('reload');
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
			top.showMsg("提示", "请选择要删除的权限组！");
		}
	}
	
	//在一个权限组下创建权限
	function createPermissionItem(datagridId, permissionGroupId, permissionGroupName){
		$('#add-permissionItem-permissionGroupName').val(permissionGroupName);
 		$('#addPermissionItemModal').modal('show');
 		$('#submit_addPermissionItemInfo').click(function(){
			var displayName = $('#add-permissionItem-displayName').val();
			var code = $('#add-permissionItem-code').val();
			var description = $('#add-permissionItem-description').val();
			var isValid = $('#addPermissionItemInfoForm').data('bootstrapValidator').isValid();
	        if(isValid){
	        	$.ajax({
	                url : '${pageContext.request.contextPath}/admin/permission/createPermissionItem',
	                data : {
	                    permissionGroupId : permissionGroupId,
	                    displayName : displayName,
	                    code : code,
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
 		});
		$('#addPermissionItemInfoForm').data('bootstrapValidator').resetForm(true);
	}
	
	function editPermissionItemInfo(datagridId, permissionItemId) {
// 		console.log('datagridId:' + datagridId);
// 		console.log('permissionItemId:'+permissionItemId);
		var rows = $('#' + datagridId).datagrid("getSelections");
		var oldName = rows[0].displayName;
		var oldCode = rows[0].code;
		var oldDescription = rows[0].description;
		$('#edit-permissionItem-displayName').val(oldName);
		$('#edit-permissionItem-code').val(oldCode);
		$('#edit-permissionItem-description').val(oldDescription);
		$('#editPermissionItemInfoModal').modal('show');
		//$('#editPermissionItemInfoForm').form('load', '${pageContext.request.contextPath}/admin/permissionItem/getPermissionItemForm' + '?permissionItemId=' + permissionItemId);
 		$('#submit_editPermissionItemInfo').click(function(){
			var name = $('#edit-permissionItem-displayName').val();
			var code = $('#edit-permissionItem-code').val();
			var description = $('#edit-permissionItem-description').val();
			if(oldName != name || oldDescription != description || oldCode != code){
				var isValid = $('#addPermissionItemInfoForm').data('bootstrapValidator').isValid();
				if(isValid){
					$.ajax({
						url : '${pageContext.request.contextPath}/admin/permission/editPermissionItem',
						data : {
							id : permissionItemId,
							displayName : name,
							code : code,
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
			}else{
				top.showMsg('提示', "信息没有被修改！");
			}
			$('#editPermissionItemInfoForm').data('bootstrapValidator').resetForm(true);
 		});

	}
	function deletePermissionItemInfo(datagridId, permissionItemId,permissionItemName){
// 		console.log('datagridId:' + datagridId);
// 		console.log('permissionItemId:'+permissionItemId);
// 		console.log('permissionItemName:'+permissionItemName);
		swal({
			title : "你是否确定删除?",
			text : permissionItemName,
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
					url : '${pageContext.request.contextPath}/admin/permission/deletePermissionItem',
					data : {
						permissionItemId : permissionItemId
					},
					cache : false,
					dataType : "json",
					success : function(data) {
						if (data.success) {
//								top.showMsg('提示', data.msg);
							swal("删除成功!","","success");
							permissionGrid.datagrid("unselectAll");
							permissionGrid.datagrid('reload');
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
