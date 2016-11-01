<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'parameterList.jsp' starting page</title>
    
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
/*     margin: 26px 30px 0px; */
/*     width: 150px; */
}
.sweet-alert .sa-confirm-button-container {
    display: inline-block;
    position: relative;
/*     padding-left: 20px; */
}
.cancel{
	margin-top: 15px;
	margin-left: 50px;
}
.confirm{
	margin-top: 15px;
	margin-left: 150px;
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
.form-horizontal {
    margin-bottom: 0px;
}
.icon-remove {
    background: no-repeat center center;
}
.icon-edit {
    background: no-repeat center center;
}
.icon-undo {
    background: no-repeat center center;
}
</style>
<script type="text/javascript">
$(function() {
    //创建参数验证
    $('#addParamInfoForm').bootstrapValidator({
        message : '这个值不能为空！',
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },
        fields : {
        	series : {
                message: '请选择一个系列',
                validators : {
                    notEmpty : {
                        message : '请选择一个系列'
                    },
                }
            },
            fullName : {
                message : '参数名不能为空',
                validators : {
                    notEmpty : {
                        message : '参数名不能为空'
                    },
                }
            },
        }
    });
    $('#reset_addParamInfo').click(function() {
        $('#addParamInfoForm').data('bootstrapValidator').resetForm(true);
    });
    $('#close_addParamInfo').click(function() {
        $('#addParamInfoForm').data('bootstrapValidator').resetForm(true);
    });
    //编辑角色表单验证
    $('#editRoleInfoForm').bootstrapValidator({
        message : '这个值不能为空！',
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },
        fields : {
        	fullName : {
                message : '参数名不能为空',
                validators : {
                    notEmpty : {
                        message : '参数名不能为空'
                    },
                }
            },
            description : {
              message: '',
            }
        }
    });
    $('#reset_editParamInfo').click(function() {
        $('#editRoleInfoForm').data('bootstrapValidator').resetForm(true);
    });
    $('#close_editParamInfo').click(function() {
        $('#editRoleInfoForm').data('bootstrapValidator').resetForm(true);
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
				<h1>遥测节点</h1>
			</div>
			<!-- /.page-header -->
			<div id="content" region="center" style="overflow: hidden">
				<div id="toolbar" class="datagrid-toolbar" style="height: 28px;">
					<div style="height: 28px;">
						<button class="easyui-linkbutton" iconcls="icon-add" plain="true" style="float: left;" 
							data-toggle="modal" data-target="#addParamModal">创建</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-remove" plain="true" style="float: left;"
							onclick="deleteRole();">删除</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-edit" plain="true" style="float: left;"
							onclick="editParam();">编辑</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-undo" plain="true" style="float: left;"
							onclick="paramGrid.datagrid('unselectAll');">取消选中</button>
					</div>
				</div>
			</div>
			<table id="roleList" width="100%" fit="false" height="450px" border="false">
			</table>
			
			<!-- 创建参数 -->
			<div class="modal fade" id="addParamModal" tabindex="-1" role="dialog" aria-labelledby="addParamModalLabel"  >
			  <div class="modal-dialog" role="document" style="margin:150px 450px">
			    <div class="modal-content">
					<form id="addParamInfoForm" class="form-horizontal" role="form">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="close_addParamInfo">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="addParamModalLabel">参数信息</h4>
						</div>
							<div class="modal-body">
								<div class="space-8"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-param-series"> 系列： </label>
									<div class="col-sm-8">
										<input name="series" id="add-param-series" class="form-control" style="width: 357px;height: 34px" 
										placeholder="系列" />
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-param-fullName"> 参数名称：</label>
									<div class="col-sm-8">
										<input type="text" name="fullName" id="add-param-fullName" class="form-control" placeholder="参数名称" />
									</div>
								</div>
							</div>
							<div class="modal-footer">
							<div class="col-lg-4 col-lg-offset-5">
								<button type="button" class="btn btn-default" data-dismiss="modal" id="reset_addParamInfo">关闭</button>
								<button type="submit" class="btn btn-primary" data-dismiss="modal" id="submit_addParamInfo">确定</button>
							</div>
						</div>
					</form>
			    </div>
			  </div>
			</div>
			<!-- 编辑参数 -->
			<div class="modal fade" id="editParamModal" tabindex="-1" role="dialog" aria-labelledby="editParamModalLabel">
				<div class="modal-dialog" role="document" style="margin:150px 450px">
					<div class="modal-content">
						<form id="editParamInfoForm" class="form-horizontal" role="form">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="close_editParamInfo">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="editParamModalLabel">参数信息</h4>
							</div>
							<div class="modal-body">
								<input type="hidden" name="id" id="edit-role-id"/>
                                <div class="space-8"></div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="edit-param-series"> 系列： </label>
                                    <div class="col-sm-8">
                                        <input name="series" id="edit-param-series" class="form-control" style="width: 357px;height: 34px" 
                                        placeholder="系列"/>
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="edit-param-fullName"> 参数名称：</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="fullName" id="edit-param-fullName" class="form-control" placeholder="参数名称" />
                                    </div>
                                </div>
							</div>
							<div class="modal-footer">
								<div class="col-lg-4 col-lg-offset-5">
									<button type="button" class="btn btn-default" data-dismiss="modal" id="reset_editParamInfo">关闭</button>
									<button type="submit" class="btn btn-primary" data-dismiss="modal" id="submit_editParamInfo">确定</button>
								</div>
							</div>
						</form>
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
	  var nowSeries = '${nowSeries}';
	  //console.log('nowSeries:' + nowSeries);
	  var paramGrid;
	  var url='<%=request.getContextPath()%>/admin/parameter/getList/'+nowSeries;
	  $(function () {
	      paramGrid = $('#roleList').datagrid({
	          url: url,
	          title: '参数列表',
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
	              field: 'fullName',
	              title: '参数名',
	              width: 300,
	              sortable: true
	          }]],
	          columns: [[{
	              field: 'code',
	              title: '参数码',
	              width: 300
	          }, 
	          ]]
	      });
	  });
	
	$("#add-param-series").combobox({
          url:'${pageContext.request.contextPath}/admin/series/getSeriesComboData?seriesId=0',
          valueField:'value',
          textField:'text'
      }); 
// 	$("#add-param-series").combobox('setValues', '${nowSeries}');
	
	//创建参数
	$('#submit_addParamInfo').click(function(){
		var series = $("#add-param-series").combobox('getValue');
		var name = $('#add-param-fullName').val();
		var isValid = $('#addParamInfoForm').data('bootstrapValidator').isValid();
		if(isValid){
			$.ajax({
				url : '${pageContext.request.contextPath}/admin/parameter/createParam',
				data : {
					series : series,
					name : name
				},
				cache : false,
				dataType : "json",
				success : function(data) {
					if (data.success) {
						top.showMsg('提示', data.msg);
						reloadDataGrid();
					} else {
						top.alertMsg('警告', data.msg);
					}
				}
			});
			
		}
		
		$('#addParamInfoForm').data('bootstrapValidator').resetForm(true);
	});
	  //编辑参数信息
	  function editParam(){
	      var rows = paramGrid.datagrid('getSelections');
	      if (rows.length > 0) {
	          if (rows.length == 1) {
	        	//赋值
				var oldName = rows[0].name;
				var oldDescription = rows[0].description;
				$('#edit-param-fullName').val(oldName);
				$('#edit-param-description').val(oldDescription);
				//弹出编辑框
				$('#editParamModal').modal('show');
				$('#submit_editParamInfo').click(function(){
					if(isValid){
						var name = $('#edit-param-fullName').val();
						var description = $('#edit-param-description').val();
						if(oldName != name){
							$.post('${pageContext.request.contextPath}/admin/parameter/editParam', 
									{
										id : rows[0].id,
										name : name,
										description : description 
									},
									function(data){
										top.showProcess(false);
										if (data.success) {
											top.showMsg('提示', data.msg);
											reloadDataGrid();
										} else {
											top.alertMsg('警告', data.msg);
										}
								});
							
						}else{
							top.showMsg('提示', "权限组信息没有被修改！");
						}
						var isValid = $('#editParamInfoForm').data('bootstrapValidator').isValid();
					}
				});	               
	          }else {
	              var names = [];
	              for (var i = 0; i < rows.length; i++) {
	                  names.push(rows[i].name);
	              }
	              top.showMsg("提示", '只能选择一个角色进行编辑！您已经选择了【'+names.join(',')+'】'+rows.length+'个角色');
	          }
	      }else {
	          top.showMsg("提示", "请选择要编辑的记录！");
	      }
	  }	
	  //删除系统角色
	  function deleteRole() {
	      var ids = [];
	      var rows = paramGrid.datagrid('getSelections');
	      if (rows.length>0) {
	    	  	var names = [];
				for ( var i = 0; i < rows.length; i++) {
					names.push(rows[i].name);
					ids.push(rows[i].id);
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
						$.ajax({
							url : '${pageContext.request.contextPath}/admin/role/deleteRole',
							data : {
								roleIds : ids.join(',')
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
	      }
	      else {
	          top.showMsg("提示", "请选择要删除的记录！");
	      }
	  }
	  function reloadDataGrid() {
	      paramGrid.datagrid('unselectAll');
	      paramGrid.datagrid('reload');
	  }
	</script>	
	
  </body>
</html>
