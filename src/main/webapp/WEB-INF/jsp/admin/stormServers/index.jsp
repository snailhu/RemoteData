<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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

<link
	href="${pageContext.request.contextPath}/static/content/css/default.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/content/js/outlook2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/content/js/validDate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/content/jquery-easyui-datagridview/datagrid-detailview.js"></script>

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

.form-horizontal {
	margin-bottom: 0px;
}

a {
	color: #428bca;
}
</style>
<script type="text/javascript">
	$(function() {
		//修改页面缩放，界面显示不正常
		$(".col-lg-7").css("text-align","center");
		$(".modal-dialog").css("margin","20px auto");
		
		//左菜单栏
		$("#servermanage-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_14.png");
		$("#sysmanage-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_50.png");
		$("#servermanage-text").css("color","#5d90d6");
		$("#sysmanage-text").css("color", "#5d90d6");
		$("#sysmanageUL").css("display","block");
		
		$('#addServerInfoForm').bootstrapValidator({
			message : '这个值不能为空！',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				serverIp : {
					message : '服务器IP不能为空',
					validators : {
						notEmpty : {
							message : '服务器IP不能为空'
						},
						regexp : {
							regexp : /^((25[0-5]|2[0-4]\d|[01]?\d\d?)($|(?!\.$)\.)){4}$/,
							message : '服务器IP格式不正确'
						}
					}
				},
				statusValue : {
					message : '',
				}
			}
		});
// 		$('#reset_addRoleInfo').click(function() {
// 			$('#addServerInfoForm').data('bootstrapValidator').resetForm(true);
// 		});
		$('#editServerInfoForm').bootstrapValidator({
			message : '这个值不能为空！',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				serverIp : {
					message : '服务器IP不能为空',
					validators : {
						notEmpty : {
							message : '服务器IP不能为空'
						},
						regexp : {
							regexp : /^((25[0-5]|2[0-4]\d|[01]?\d\d?)($|(?!\.$)\.)){4}$/,
							message : '服务器IP格式不正确'
						}
					}
				},
				statusValue : {
					message : '',
				}
			}
		});
// 		$('#reset_editServerInfo').click(function() {
// 			$('#editServerInfoForm').data('bootstrapValidator').resetForm(true);
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
				<li><img
					src="${pageContext.request.contextPath}/static/imgs/DataImport/home.png"
					style="margin-bottom: 3px;"> <span>系统管理</span></li>
				<li class="active">服务器管理</li>
			</ul>
			<!--  .breadcrumb -->
		</div>
		<div class="page-content">
<!-- 			<div class="page-header"> -->
<!-- 				<h1>服务器管理</h1> -->
<!-- 			</div> -->
			<!-- /.page-header -->

			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->

					<div id="content" region="center" style="overflow: hidden">
<!-- 						<div id="toolbar" class="datagrid-toolbar" style="height: 28px;"> -->
<!-- 							<div style="height: 28px;"> -->
<!-- 								<button class="easyui-linkbutton" iconcls="icon-add" -->
<!-- 									plain="true" style="float: left;" id="createServer-btn">创建</button> -->
<!-- 								<div class="datagrid-btn-separator"></div> -->
<!-- 								<button class="easyui-linkbutton" iconcls="icon-remove" -->
<!-- 									plain="true" style="float: left;" onclick="deleteServer();">删除</button> -->
<!-- 								<div class="datagrid-btn-separator"></div> -->
<!-- 								<button class="easyui-linkbutton" iconcls="icon-edit" -->
<!-- 									plain="true" style="float: left;" onclick="editServer();">编辑</button> -->
<!-- 								<div class="datagrid-btn-separator"></div> -->
<!-- 								<button class="easyui-linkbutton" iconcls="icon-undo" -->
<!-- 									plain="true" style="float: left;" -->
<!-- 									onclick="serverGrid.datagrid('unselectAll');">取消选中</button> -->
<!-- 							</div> -->
<!-- 						</div> -->
					</div>
					<table id="serverList" width="100%" height="450px" border="false">
					</table>
					
					<div class="modal fade" id="addServerModal" tabindex="-1" role="dialog" aria-labelledby="addServerModalLabel"  >
					  <div class="modal-dialog" role="document" style="margin:150px 450px">
					    <div class="modal-content">
							<form id="addServerInfoForm" class="form-horizontal" role="form">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="addServerModalLabel">服务器信息</h4>
								</div>
								<div class="modal-body">
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="add-server-ip"> 服务器IP：</label>
										<div class="col-sm-8">
											<input type="text" name="serverIp" id="add-server-ip" placeholder="服务器IP" class="form-control" />
										</div>
									</div>
									<div class="space-8"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="add-server-statusValue"> 服务器状态： </label>
										<div class="col-sm-8">
											<select class="col-xs-10 col-sm-12" id="add-server-statusValue" name="statusValue">
												<option value="1">正常</option>
												<option value="0">岩机</option>
											</select>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<div class="col-lg-7 col-lg-offset-3">
										<button type="button" class="subbutton_1" id="submit_addServerInfo">确定</button>
										<button type="button" class="cancelbutton_1" data-dismiss="modal" id="reset_addRoleInfo">关闭</button>
									</div>
								</div>
							</form>
					    </div>
					  </div>
					</div>
			
					<div class="modal fade" id="editServerModal" tabindex="-1" role="dialog" aria-labelledby="editServerModalLabel"  >
					  <div class="modal-dialog" role="document" style="margin:150px 450px">
					    <div class="modal-content">
							<form id="editServerInfoForm" class="form-horizontal" role="form">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="editServerModalLabel">服务器信息</h4>
								</div>
								<div class="modal-body">
									<input type="hidden" name="id" id="edit-server-id"/>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="edit-server-ip"> 服务器IP：</label>
										<div class="col-sm-8">
											<input type="text" name="serverIp" id="edit-server-ip" placeholder="服务器IP" class="form-control" />
										</div>
									</div>
									<div class="space-8"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="edit-server-statusValue"> 服务器状态： </label>
										<div class="col-sm-8">
											<select class="col-xs-10 col-sm-12" id="edit-server-statusValue" name="statusValue">
												<option value="1">正常</option>
												<option value="0">岩机</option>
											</select>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<div class="col-lg-7 col-lg-offset-3">
										<button type="button" class="subbutton_1" id="submit_editServerInfo">确定</button>
										<button type="button" class="cancelbutton_1" data-dismiss="modal" id="reset_editServerInfo">关闭</button>
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
		</div>
		<!-- /.page-content -->
	</div>
	<!-- /.main-content -->

<script type="text/javascript">
	var serverGrid;
	$(function () {
		var url='<%=request.getContextPath()%>/admin/stormServers/getList';
		serverGrid = $('#serverList').datagrid({
	          url: url,
	          title: '服务器列表',
	          rownumbers: true,
	          fitColumns:true,
	          idField: 'id',
// 	          toolbar: '#toolbar',
	          pageSize: 10,
	          pagination: true,
	          pageList: [10, 20, 30, 40, 50, 60, 70, 80, 90, 100],
	          frozenColumns: [[{
	              title: 'id',
	              field: 'id',
	              width: 50,
	              checkbox: true,
	              hidden:true
	          }]],
	          columns: [[
	          {
	              field: 'serverIp',
	              title: '服务器IP',
	              width: 200,
	          },{
	              field: 'statusName',
	              title: '服务器状态',
	              width: 100
	          }, {
	              field: 'createDate',
	              title: '更新时间',
	              width: 200
	          }, 
	          ]]
	      });
	});
	function reloadDataGrid() {
	      serverGrid.datagrid('unselectAll');
	      serverGrid.datagrid('reload');
	}
	
	function editServer(){
	      var rows = serverGrid.datagrid('getSelections');
	      if (rows.length > 0) {
	          if (rows.length == 1) {
	        	//赋值
				$('#edit-server-id').val(rows[0].id);
				$('#edit-server-ip').val(rows[0].serverIp);
				$('#edit-server-statusValue').val(rows[0].statusValue);
				//弹出编辑框
				$('#editServerModal').modal('show');
				           
	          }else {
	              var names = [];
	              for (var i = 0; i < rows.length; i++) {
	                  names.push(rows[i].serverIp);
	              }
	              top.showMsg("提示", '只能选择一个服务器进行编辑！您已经选择了【'+names.join(',')+'】'+rows.length+'个服务器');
	          }
	      }else {
	          top.showMsg("提示", "请选择要编辑的记录！");
	      }
	  }	
	  
	$('#editServerModal').on('hide.bs.modal', function () {
		$('#editServerInfoForm').data('bootstrapValidator').resetForm(true);
	});
	  $('#submit_editServerInfo').click(function(){
		    var f = $('#editServerInfoForm');
			f.data('bootstrapValidator').validate();
			var isValid = f.data('bootstrapValidator').isValid();
			if(!isValid){
				return false;
			}
			var id = $('#edit-server-id').val();
			var serverIp = $('#edit-server-ip').val();
			var statusValue = $('#edit-server-statusValue').val();
			$.post('${pageContext.request.contextPath}/admin/stormServers/editServer', 
				{
					id : id,
					serverIp : serverIp,
					statusValue : statusValue 
				},
				function(data){
					top.showProcess(false);
					if (data.success) {
						$('#editServerModal').modal('hide');
						top.showMsg('提示', data.msg);
						reloadDataGrid();
					} else {
						top.alertMsg('警告', data.msg);
					}
			});
		});	    
	  
	function deleteServer() {
	      var ids = [];
	      var rows = serverGrid.datagrid('getSelections');
	      if (rows.length>0) {
	    	  	var names = [];
				for ( var i = 0; i < rows.length; i++) {
					names.push(rows[i].name);
					ids.push(rows[i].id);
				}
				swal({
					title : "你是否确定删除？",
					text : names.join(','),
					type : "warning",
					showCancelButton : true,
					confirmButtonColor : "#DD6B55",
					confirmButtonText : "删除",
					cancelButtonText : "取消",
					closeOnConfirm : false,
//					closeOnCancel : false
				},
				function(isConfirm) {
					if (isConfirm) {
						$.ajax({
							url : '${pageContext.request.contextPath}/admin/stormServers/deleteServer',
							data : {
								ServerIds : ids.join(',')
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
//					else {
//						swal("取消删除", "","error");
//					}
				});
	      }
	      else {
	          top.showMsg("提示", "请选择要删除的记录！");
	      }
	  }
	
	$('#addServerModal').on('hide.bs.modal', function () {
		$('#addServerInfoForm').data('bootstrapValidator').resetForm(true);
	});
	$('#createServer-btn').click(function(){
		$('#addServerModal').modal('show');
	});
	$('#submit_addServerInfo').click(function(){
		var f = $('#addServerInfoForm');
		f.data('bootstrapValidator').validate();
		var isValid = f.data('bootstrapValidator').isValid();
		if(!isValid){
			return false;
		}
		var toUrl='${pageContext.request.contextPath}/admin/stormServers/createServer';
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
                	$('#addServerModal').modal('hide');
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
</script>
</body>
</html>
