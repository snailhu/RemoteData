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

</style>
<script type="text/javascript">
$(function() {
	//左菜单栏
	$("#galaxymanage-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_82.png");
	$("#galaxymanage-text").css("color","#5d90d6");
	
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
            star : {
                message: '请选择一个星',
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
                    regexp : { 
                        regexp : /^[\u4E00-\u9FA5A-Za-z0-9_:\u4E00-\u9FA5A-Za-z0-9_]+(\([0-9]*\))+$/,
                        message : '参数名格式不对'
                    },
                }
            },
        }
    });
//     $('#reset_addParamInfo').click(function() {
//         $('#addParamInfoForm').data('bootstrapValidator').resetForm(true);
//     });
//     $('#close_addParamInfo').click(function() {
//         $('#addParamInfoForm').data('bootstrapValidator').resetForm(true);
//     });
    //编辑角色表单验证
    $('#editParamInfoForm').bootstrapValidator({
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
            star : {
                message: '请选择一个星',
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
                    regexp : { 
                        regexp : /^[\u4E00-\u9FA5A-Za-z0-9_:\u4E00-\u9FA5A-Za-z0-9_]+(\([0-9]*\))+$/,
                        message : '参数名格式不对'
                    },
                }
            },
        }
    });
//     $('#reset_editParamInfo').click(function() {
//         $('#editRoleInfoForm').data('bootstrapValidator').resetForm(true);
//     });
//     $('#close_editParamInfo').click(function() {
//         $('#editRoleInfoForm').data('bootstrapValidator').resetForm(true);
//     });
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
				<li class="active">${nowSeries}系列${nowStar}星的参数列表</li>
			</ul><!--  .breadcrumb -->
		</div>
		<div class="page-content">
<!-- 			<div class="page-header"> -->
<!-- 				<h1>遥测节点</h1> -->
<!-- 			</div> -->
			<!-- /.page-header -->
			<div id="content" region="center" style="overflow: hidden">
				<div id="toolbar" class="datagrid-toolbar" style="height: 28px;">
					<div style="height: 28px;">
						<button class="easyui-linkbutton" iconcls="icon-add" plain="true" style="float: left;" 
							id="addParamModal-btn">创建</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-remove" plain="true" style="float: left;"
							onclick="deleteParam();">删除</button>
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
										<input name="series" id="add-param-series" class="form-control" readonly="true"/>
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-param-series"> 星： </label>
									<div class="col-sm-8">
										<input name="star" id="add-param-star" class="form-control" readonly="true"/>
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
							<div class="col-lg-7 col-lg-offset-3">
								<button type="button" class="subbutton_1" id="submit_addParamInfo">确定</button>
								<button type="button" class="cancelbutton_1" data-dismiss="modal" id="reset_addParamInfo">关闭</button>
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
								<input type="hidden" name="id" id="edit-param-id"/>
                                <div class="space-8"></div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="edit-param-series"> 系列： </label>
                                    <div class="col-sm-8">
                                        <input name="series" id="edit-param-series" class="form-control" readonly="true"/>
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="edit-param-series"> 系列： </label>
                                    <div class="col-sm-8">
                                        <input name="series" id="edit-param-star" class="form-control" readonly="true"/>
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
								<div class="col-lg-7 col-lg-offset-3">
									<button type="button" class="subbutton_1" id="submit_editParamInfo">确定</button>
									<button type="button" class="cancelbutton_1" data-dismiss="modal" id="reset_editParamInfo">关闭</button>
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
	  var nowSeriesName = '${nowSeriesName}';
	  var nowStar = '${nowStar}';
	  var nowStarName = '${nowStarName}';
	  //console.log('nowSeries:' + nowSeries);
	  var paramGrid;
	  var url='<%=request.getContextPath()%>/admin/parameter/getList/'+nowSeries+'/'+nowStar;
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
	
// 	$("#add-param-series").combobox({
//           url:'${pageContext.request.contextPath}/admin/series/getSeriesComboData?seriesId=0',
//           valueField:'value',
//           textField:'text'
//       }); 
// 	$("#add-param-series").combobox('setValues', '${nowSeries}');
	
	$('#addParamModal-btn').click(function(){
		$('#add-param-series').val(nowSeriesName);
		$('#add-param-star').val(nowStarName);
		$('#addParamModal').modal('show');
	});
	$('#addParamModal').on('hide.bs.modal', function () {
		$('#addParamInfoForm').data('bootstrapValidator').resetForm(true);
	});
	//创建参数
	$('#submit_addParamInfo').click(function(){
		var f = $('#addParamInfoForm');
		f.data('bootstrapValidator').validate();
		var isValid = f.data('bootstrapValidator').isValid();
		if(!isValid){
			return false;
		}
// 		var series = $("#add-param-series").val();
// 		var star = $("#add-param-star").val();
		var name = $('#add-param-fullName').val();
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/parameter/createParam',
			data : {
				series : nowSeries,
				star : nowStar,
				name : name
			},
			cache : false,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					$('#addParamModal').modal('hide');
					top.showMsg('提示', data.msg);
					reloadDataGrid();
				} else {
					top.alertMsg('警告', data.msg);
				}
			}
		});
	});
	//编辑参数信息
	$('#editParamModal').on('hide.bs.modal', function () {
		$('#editParamInfoForm').data('bootstrapValidator').resetForm(true);
	});
	function editParam(){
	    var rows = paramGrid.datagrid('getSelections');
	    if (rows.length > 0) {
	        if (rows.length == 1) {
	      	//赋值
	      	$('#edit-param-series').val(nowSeriesName);
	      	$('#edit-param-star').val(nowStarName);
			$('#edit-param-id').val(rows[0].id);
			$('#edit-param-fullName').val(rows[0].fullName);
			//弹出编辑框
			$('#editParamModal').modal('show');
	        }else {
	            var names = [];
	            for (var i = 0; i < rows.length; i++) {
	                names.push(rows[i].fullName);
	            }
	            top.showMsg("提示", '只能选择一个参数进行编辑！您已经选择了'+rows.length+'个参数');
	        }
	    }else {
	        top.showMsg("提示", "请选择要编辑的记录！");
	    }
	}	
	
	$('#submit_editParamInfo').click(function(){
		var f = $('#editParamInfoForm');
		f.data('bootstrapValidator').validate();
		var isValid = f.data('bootstrapValidator').isValid();
		if(!isValid){
			return false;
		}
		var id = $('#edit-param-id').val();
		var name = $('#edit-param-fullName').val();
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/parameter/editParam',
			data : {
				id : id,
				series : nowSeries,
				star : nowStar,
				name : name
			},
			cache : false,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					$('#editParamModal').modal('hide');
					top.showMsg('提示', data.msg);
					reloadDataGrid();
				} else {
					top.alertMsg('警告', data.msg);
				}
			}
		});
	});	 
	  
	  //删除
	  function deleteParam() {
	      var ids = [];
	      var rows = paramGrid.datagrid('getSelections');
	      if (rows.length>0) {
	    	  	var names = [];
				for ( var i = 0; i < rows.length; i++) {
					names.push(rows[i].fullName);
					ids.push(rows[i].id);
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
// 					closeOnCancel : false
				},
				function(isConfirm) {
					if (isConfirm) {
						$.ajax({
							url : '${pageContext.request.contextPath}/admin/parameter/deleteParam',
							data : {
								paramIds : ids.join(',')
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
	      paramGrid.datagrid('unselectAll');
	      paramGrid.datagrid('reload');
	  }
	</script>	
	
  </body>
</html>
