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
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/content/sweetalert/dist/sweetalert.css">
	<script src="<%=request.getContextPath()%>/static/content/sweetalert/dist/sweetalert.min.js"></script>
	
	<script src="<%=request.getContextPath()%>/static/content/js/outlook2.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/content/js/validDate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/content/jquery-easyui-datagridview/datagrid-detailview.js"></script>
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
				<h1>星系管理</h1>
			</div>
			<div id="content" region="center" style="overflow: hidden">
				<div id="toolbar" class="datagrid-toolbar" style="height: 28px;">
					<div style="height: 28px;">
						<button class="easyui-linkbutton" iconcls="icon-add" style="float: left;" 
							data-toggle="modal" data-target="#seriesInfoModal">创建</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-edit" style="float: left;"
							onclick="getSeriesInfo();">编辑</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-remove"
							onclick="deleteSeries();" style="float: left;">删除</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-undo"
							onclick="galaxyGrid.datagrid('unselectAll');" style="float: left;">取消选中</button>
					</div>
				</div>
			</div>
			<table id="galaxyList" fit="true" border="false" >
				<thead>
					<tr>
						<th field="ck" checkbox="true"></th>
						<th field="name" width="80">名称</th>
						<th field="description" width="200" align="center">描述</th>
						<th field="createDate" width="120" align="center">创建日期</th>
					</tr>
				</thead>
			</table>
			<div class="modal fade" id="seriesInfoModal" tabindex="-1" role="dialog" aria-labelledby="seriesInfoModalLabel"  >
			  <div class="modal-dialog" role="document" style="margin:55px -300px">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="seriesInfoModalLabel">添加系列</h4>
			      </div>
			      <div class="modal-body">
					<form id="seriesInfoForm" class="form-horizontal" role="form">
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label no-padding-right" for="form-field-name"> 系列名称:</label>
							<div class="col-sm-9">
								<input type="text" name="name" id="form-field-name" placeholder="系列名称" class="form-control" />
							</div>
						</div>
						<div class="space-8"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label no-padding-right" for="form-field-description"> 系列描述： </label>
							<div class="col-sm-9">
								<textarea class="form-control" name="description" id="form-field-description" placeholder="系列描述"></textarea>
							</div>
						</div>
					</form>
			      </div>
			      <div class="modal-footer">
			      	<div class="col-lg-4 col-lg-offset-5">
<!--                         <button type="submit" class="btn btn-primary" name="signup" value="Sign up">提交</button> -->
<!--                         <button type="button" class="btn btn-info" id="resetBtn">重置表单</button> -->
				        <button type="button" class="btn btn-default" id="modal-close" data-dismiss="modal">关闭</button>
				        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="submit_seriesInfo()">确定</button>
                    </div>
			      </div>
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
	galaxyGrid = $('#galaxyList').datagrid({
		view : detailview,
		url: '<%=request.getContextPath()%>/admin/series/getList',
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
				url : '<%=request.getContextPath()%>/admin/star/getStars?seriesId='+ row.id,
						//title: '星星项列表[所属组：'+row.Name+']',
						fitColumns : true,
						rownumbers : true,
						loadMsg : '正在载入权限项，请稍后...',
						height : 'auto',
						toolbar : [ {
							text : '创建',
							iconCls : 'icon-add',
							handler : function() {
								CreatePermissionItem(
										subgridId,
										row.id);
							}
						}

						],
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'name',
									title : '名称',
									width : 100
								},
								{
									field : 'startRunDate',
									title : '开始运行时间',
									width : 100
								},
								{
									field : 'description',
									title : '描述',
									width : 150
								},
								{
									field : 'operation',
									title : '操作选项',
									align : 'center',
									width : 120,
									formatter : function(
											value,
											row,
											index) {
										// var editStr = "<a name=\"operButton\"  class=\"easyui-linkbutton\" iconcls=\"icon-edit\"  plain=\"true\" href=\"javascript:EditPermissionItem('" + row.Id + "');\">编辑</a>";
										var editStr = "<a class=\"l-btn l-btn-plain\" href=\"javascript:EditPermissionItem('"
												+ subgridId
												+ "','"
												+ row.id
												+ "')\" style=\"float: left;\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\" style=\"padding-left: 60px;\">编辑</span></span></a>";
										// var delStr = "<a name=\"operButton\" class=\"easyui-linkbutton\"  iconcls=\"icon-remove\"  plain=\"true\" href=\"javascript:DeletePermissionItem('" + row.Id + "');\"> 删除</a>"
										var delStr = "<a class=\"l-btn l-btn-plain\" href=\"javascript:DeletePermissionItem('"
												+ subgridId
												+ "','"
												+ row.id
												+ "')\" style=\"float: left;\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-remove\" style=\"padding-left: 80px;\">删除</span></span></a>";
										return editStr
												+ '<div class="datagrid-btn-separator"></div>'
												+ delStr;
									}
								} ] ],
						onResize : function() {
							$('#permissionList')
									.datagrid(
											'fixDetailRowHeight',
											index);
						},
						onLoadSuccess : function() {
							setTimeout(
									function() {
										$(
												'#permissionList')
												.datagrid(
														'fixDetailRowHeight',
														index);
									}, 0);

						}
					});
				$('#permissionList').datagrid('fixDetailRowHeight', index);
			}
		});
	});
	function submit_seriesInfo(){
		//$('#seriesInfoModal').hide();
// 		$('#modal-close').click();
// 		swal("Here's a message!");
		var name = $('#form-field-name').val();
		var description = $('#form-field-description').val();
// 		console.log('name:' + name.val());
// 		console.log('description:'+description.val());
		$('#form-field-name').val("");
		$('#form-field-description').val("");
		if(name != ""){
			$.ajax({
				url : '<%=request.getContextPath()%>/admin/series/createSeries',
				data : {
					name : name,
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
	}	
	function getSeriesInfo(){
		var rows = galaxyGrid.datagrid('getSelections');
		if (rows.length > 0) {
			if (rows.length == 1) {
				//弹出编辑框
				$('#seriesInfoModal').show();
			}
		}
// 		$('#seriesInfoForm').form('load', '<%=request.getContextPath()%>/admin/series/getList' + '?userId=' + '${userId}');
	}
	</script>
</body>
</html>
