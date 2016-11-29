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
			<div class="page-header">
				<h1>服务器管理</h1>
			</div>
			<!-- /.page-header -->

			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->

					<div id="content" region="center" style="overflow: hidden">
						<div id="toolbar" class="datagrid-toolbar" style="height: 28px;">
							<div style="height: 28px;">
								<button class="easyui-linkbutton" iconcls="icon-add"
									plain="true" style="float: left;" id="createRole-btn">创建</button>
								<div class="datagrid-btn-separator"></div>
								<button class="easyui-linkbutton" iconcls="icon-remove"
									plain="true" style="float: left;" onclick="deleteRole();">删除</button>
								<div class="datagrid-btn-separator"></div>
								<button class="easyui-linkbutton" iconcls="icon-edit"
									plain="true" style="float: left;" onclick="editRole();">编辑</button>
								<div class="datagrid-btn-separator"></div>
								<button class="easyui-linkbutton" iconcls="icon-undo"
									plain="true" style="float: left;"
									onclick="roleGrid.datagrid('unselectAll');">取消选中</button>
							</div>
						</div>
					</div>
					<table id="roleList" width="100%" height="450px" border="false">
					</table>

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
	var roleGrid;
	$(function () {
		var url='<%=request.getContextPath()%>/admin/role/getList';
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
	                  return "<a href=\"javascript:doEditPermission('" + row.id +  "');\"" + " title='编辑权限'>编辑权限</a>";
	              }
	          }
	          ]]
	      });
	});
</script>
</body>
</html>
