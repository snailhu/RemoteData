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
	
	<link href="${pageContext.request.contextPath}/static/content/css/default.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/content/js/outlook2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/content/js/validDate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/content/jquery-easyui-datagridview/datagrid-detailview.js"></script>
	
	<!-- 表单验证 -->
	<!--     <link rel="stylesheet" href="<%=request.getContextPath()%>/static/content/bootstrapValidator/vendor/bootstrap/css/bootstrap.css"/> -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/content/bootstrapValidator/dist/css/bootstrapValidator.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/content/bootstrapValidator/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/content/bootstrapValidator/dist/js/bootstrapValidator.js"></script>
	
	<script src="${pageContext.request.contextPath}/static/assets/js/bootstrap.min.js"></script>
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

.form-horizontal {
	margin-bottom: 0px;
}
a {
    color: #428bca;
}
.powedit{
	display:block;
	width:80px;
	height:26px;
	line-height:26px;
	color:#000;
	text-align:center;
	margin:0px auto;
	overflow:hidden;
}
.powedit:hover{
	background:#e2e2e2;
	border:1px solid #CCC;
	border-radius:5px 5px 5px 5px;
}
</style>
<script type="text/javascript">
	$(function() {
		//修改页面缩放，界面显示不正常
		$(".col-lg-7").css("text-align","center");
		$(".modal-dialog").css("margin","20px auto");
		
		//左菜单栏
		$("#rolemanage-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_03.png");
		$("#sysmanage-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_50.png");
		$("#rolemanage-text").css("color","#5d90d6");
		$("#sysmanage-text").css("color", "#5d90d6");
		$("#sysmanageUL").css("display","block");
		
		//创建角色表单验证
		$('#addRoleInfoForm').bootstrapValidator({
			message : '这个值不能为空！',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				name : {
					message : '角色名不能为空',
					validators : {
						notEmpty : {
							message : '角色名不能为空'
						},
					}
				},
				description : {
					message : '',
				}
			}
		});
// 		$('#reset_addRoleInfo').click(function() {
// 			$('#addRoleInfoForm').data('bootstrapValidator').resetForm(true);
// 		});
// 		$('#close_addRoleInfo').click(function() {
// 			$('#addRoleInfoForm').data('bootstrapValidator').resetForm(true);
// 		});
		//编辑角色表单验证
		$('#editRoleInfoForm').bootstrapValidator({
			message : '这个值不能为空！',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				name : {
					message : '角色名不能为空',
					validators : {
						notEmpty : {
							message : '角色名不能为空'
						},
					}
				},
				description : {
					message : '',
				}
			}
		});
// 		$('#reset_editRoleInfo').click(function() {
// 			$('#editRoleInfoForm').data('bootstrapValidator').resetForm(true);
// 		});
// 		$('#close_editRoleInfo').click(function() {
// 			$('#editRoleInfoForm').data('bootstrapValidator').resetForm(true);
// 		});
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
					<span>系统管理</span>
				</li>
				<li class="active">角色管理</li>
			</ul><!--  .breadcrumb -->
		</div>
		<div class="page-content">
<!-- 			<div class="page-header"> -->
<!-- 				<h1>角色管理</h1> -->
<!-- 			</div> -->
			<!-- /.page-header -->
			<div id="content" region="center" style="overflow: hidden">
				<div id="toolbar" class="datagrid-toolbar" style="height: 28px;">
					<div style="height: 28px;">
						<button class="easyui-linkbutton" iconcls="icon-add" plain="true" style="float: left;" 
							id="createRole-btn">创建</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-remove" plain="true" style="float: left;" 
							onclick="deleteRole();">删除</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-edit" plain="true" style="float: left;" 
							onclick="editRole();">编辑</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-undo" plain="true" style="float: left;"
							onclick="roleGrid.datagrid('unselectAll');">取消选中</button>
					</div>
				</div>
			</div>
			<table id="roleList" width="100%" height="450px" border="false">
			</table>
			
			<!-- 创建角色 -->
			<div class="modal fade" id="addRoleModal" tabindex="-1" role="dialog" aria-labelledby="addRoleModalLabel"  >
			  <div class="modal-dialog" role="document" style="margin:150px 450px">
			    <div class="modal-content">
					<form id="addRoleInfoForm" class="form-horizontal" role="form">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="close_addRoleInfo">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="addRoleModalLabel">系统角色信息</h4>
						</div>
						<div class="modal-body">
							<div class="space-4"></div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="add-role-name"> 角色名称：</label>
								<div class="col-sm-8">
									<input type="text" name="name" id="add-role-name" placeholder="角色名称" class="form-control" />
								</div>
							</div>
							<div class="space-8"></div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="add-role-description"> 角色描述： </label>
								<div class="col-sm-8">
									<textarea class="form-control" name="description" id="add-role-description" placeholder="角色描述"></textarea>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="col-lg-7 col-lg-offset-3">
								<button type="button" class="subbutton_1" id="submit_addRoleInfo">确定</button>
								<button type="button" class="cancelbutton_1" data-dismiss="modal" id="reset_addRoleInfo">关闭</button>
							</div>
						</div>
					</form>
			    </div>
			  </div>
			</div>
			<!-- 编辑角色 -->
			<div class="modal fade" id="editRoleModal" tabindex="-1" role="dialog" aria-labelledby="editRoleModalLabel">
				<div class="modal-dialog" role="document" style="margin:150px 450px">
					<div class="modal-content">
						<form id="editRoleInfoForm" class="form-horizontal" role="form">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="close_editRoleInfo">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="editRoleModalLabel">系统角色信息</h4>
							</div>
							<div class="modal-body">
								<input type="hidden" name="id" id="edit-role-id"/>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="edit-role-name"> 角色名称：</label>
									<div class="col-sm-8">
										<input type="text" name="name" id="edit-role-name" placeholder="角色名称" class="form-control" />
									</div>
								</div>
								<div class="space-8"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="edit-role-description"> 角色描述： </label>
									<div class="col-sm-8">
										<textarea class="form-control" name="description" id="edit-role-description" placeholder="角色描述"></textarea>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<div class="col-lg-7 col-lg-offset-3">
									<button type="button" class="subbutton_1" id="submit_editRoleInfo">确定</button>
									<button type="button" class="cancelbutton_1" data-dismiss="modal" id="reset_editRoleInfo">关闭</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 编辑角色权限 -->
			<div class="modal fade" id="editRolePermissionModal" tabindex="-1" role="dialog" aria-labelledby="editRolePermissionModalLabel"  >
			  <div class="modal-dialog" role="document" style="margin:150px 450px">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="editRolePermissionModalLabel">编辑权限：</h4>
			      </div>
			      <div class="modal-body">
			      	<div id="permissionTree-div">
			      		<input type="hidden" id="editRolePermission-roleId">
						<ul id="permissionTree" fit="true"></ul>
			      	</div>
			      </div>
			      <div class="modal-footer">
			      	<div class="col-lg-7 col-lg-offset-3">
				        <button type="button" class="subbutton_1" data-dismiss="modal" onclick="submit_editRolePermission()">确定</button>
				        <button type="button" class="cancelbutton_1" data-dismiss="modal" id="reset_editRolePermission">关闭</button>
                    </div>
			      </div>
			    </div>
			  </div>
			</div>
			
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<!-- PAGE CONTENT ENDS -->
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
	</div><!-- /.main-content -->
	
	<script type="text/javascript">
	  var roleGrid;
	  var url='<%=request.getContextPath()%>/admin/role/getList';
	  $(function () {
	      roleGrid = $('#roleList').datagrid({
	          url: url,
	          title: '角色列表',
	          rownumbers: true,
	          fitColumns:true,
	          idField: 'id',
	          toolbar: '#toolbar',
	          pageSize: 10,
	          pagination: true,
	          pageList: [10, 20, 30, 40, 50, 60, 70, 80, 90, 100],
	          frozenColumns: [[{
	              title: 'id',
	              field: 'id',
	              width: 50,
	              checkbox: true
	          }, {
	              field: 'name',
	              title: '角色名',
	              width: 120,
	              sortable: true
	          }]],
	          columns: [[{
	              field: 'description',
	              title: '描述',
	              width: 200
	          }, {
	              field: 'createDate',
	              title: '创建时间',
	              width: 120
	          }, 
	          {
	              field: 'editPermission',
	              title: '编辑权限',
	              width: 80,
	              formatter: function (value, row, index) {
	                  return "<a class='powedit' style='color:#0909df' href=\"javascript:doEditPermission('" + row.id +  "');\"" + " title='编辑权限'>编辑权限</a>";
	              }
	          }
	          ]]
	      });
	
	  });
	//编辑角色权限
	function doEditPermission(roleId) {
  		$("#permissionTree").empty();
  		roleGrid.datagrid('unselectAll');
  		//弹出编辑权限
  		$('#editRolePermissionModal').modal('show');
  		$("#editRolePermission-roleId").attr("value",roleId);
  		$("#permissionTree").tree({
              url: '${pageContext.request.contextPath}/admin/permission/getTree?roleId=' + roleId,
              checkbox: true,
       });
	}
	function submit_editRolePermission(){
		var roleId = $("#editRolePermission-roleId").attr("value");
		var nodes = $("#permissionTree").tree('getChecked');
       	if (nodes.length > 0) {
       		var permissionItemId = '';
   			for(var i = 0; i < nodes.length; i++){
   				if (permissionItemId != '') permissionItemId += ',';
   				permissionItemId += nodes[i].id;
   			}
   			$.ajax({
   				url: '${pageContext.request.contextPath}/admin/role/editRolePermission',
   				data : {
   					roleId : roleId,
   					permissionItemId : permissionItemId
   				},
   				cache: false,
   				dataType: "json",
   				success: function(data) {
					if (data.success) {
					    top.showMsg('提示', data.msg);
					}
					else {
					    top.showMsg('警告', map.msg);
					}
   				}
   			});
       	} else {
			top.showMsg("提示", "请选择要关联的权限！");
		}
	}
	
	$('#addRoleModal').on('hide.bs.modal', function () {
		$('#addRoleInfoForm').data('bootstrapValidator').resetForm(true);
	});
	$('#createRole-btn').click(function(){
		$('#addRoleModal').modal('show');
	});
	
	//创建系统角色
	$('#submit_addRoleInfo').click(function(){
		var f = $('#addRoleInfoForm');
		f.data('bootstrapValidator').validate();
		var isValid = f.data('bootstrapValidator').isValid();
		if(!isValid){
			//top.alertMsg('错误', '请满足提交条件！');
			return false;
		}
		var toUrl='${pageContext.request.contextPath}/admin/role/createRole';
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
                	$('#addRoleModal').modal('hide');
                    top.showMsg('提示', map.msg);
                    reloadDataGrid();
                }
                else {
                	top.alertMsg('错误', map.msg+"\n"+map.obj==null?"":map.obj);
                }
            },
            onLoadError: function () {
                top.showProcess(false);
                top.$.messager.alert('温馨提示', '由于网络或服务器太忙，提交失败，请重试！');
            }
        });
	});
	  //编辑系统角色
	  function editRole(){
	      var rows = roleGrid.datagrid('getSelections');
	      if (rows.length > 0) {
	          if (rows.length == 1) {
	        	//赋值
				$('#edit-role-id').val(rows[0].id);
				$('#edit-role-name').val(rows[0].name);
				$('#edit-role-description').val(rows[0].description);
				//弹出编辑框
				$('#editRoleModal').modal('show');
				           
	          }else {
	              top.showMsg("提示", '只能选择一个角色进行编辑！');
	          }
	      }else {
	          top.showMsg("提示", "请选择要编辑的记录！");
	      }
	  }	
	  
	$('#editRoleModal').on('hide.bs.modal', function () {
		$('#editRoleInfoForm').data('bootstrapValidator').resetForm(true);
	});
	  $('#submit_editRoleInfo').click(function(){
		    var f = $('#editRoleInfoForm');
			f.data('bootstrapValidator').validate();
			var isValid = f.data('bootstrapValidator').isValid();
			if(!isValid){
				return false;
			}
			var id = $('#edit-role-id').val();
			var name = $('#edit-role-name').val();
			var description = $('#edit-role-description').val();
			$.post('${pageContext.request.contextPath}/admin/role/editRole', 
				{
					id : id,
					name : name,
					description : description 
				},
				function(data){
					top.showProcess(false);
					if (data.success) {
						$('#editRoleModal').modal('hide');
						top.showMsg('提示', data.msg);
						reloadDataGrid();
					} else {
						top.alertMsg('警告', data.msg);
					}
			});
		});	    
	  //删除系统角色
	  function deleteRole() {
	      var ids = [];
	      var rows = roleGrid.datagrid('getSelections');
	      if (rows.length>0) {
	    	  	var names = [];
				if(rows.length > 3){
					names.push(rows[0].name);
					names.push("...");
					names.push(rows[rows.length-1].name);
				}else{
					for ( var i = 0; i < rows.length; i++) {
						names.push(rows[i].name);
					}
				}
				swal({
					title : "您是否确定删除？",
					text : names.join(','),
					type : "warning",
					showCancelButton : true,
					confirmButtonColor : "#DD6B55",
					confirmButtonText : "删除",
					cancelButtonText : "取消",
					closeOnConfirm : false,
// 					closeOnCancel : false
				},
				function(isConfirm) {
					if (isConfirm) {
						for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						$.ajax({
							url : '${pageContext.request.contextPath}/admin/role/deleteRole',
							data : {
								roleIds : ids.join(',')
							},
							cache : false,
							dataType : "json",
							success : function(data) {
								if (data.success) {
									swal("删除成功","","success");
									reloadDataGrid();
								} else {
									swal("删除失败", data.obj,"error");
								}
							}
						});
					} 
// 					else {
// 						swal("取消删除", "","error");
// 					}
				});
	      }
	      else {
	          top.showMsg("提示", "请选择要删除的记录！");
	      }
	  }
	  function reloadDataGrid() {
	      roleGrid.datagrid('unselectAll');
	      roleGrid.datagrid('reload');
	  }
	  
	</script>	
  </body>

</html>
