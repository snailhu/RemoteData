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
	<link href="<%=request.getContextPath()%>/static/content/css/default.css" rel="stylesheet" type="text/css"/>
	<script src="<%=request.getContextPath()%>/static/content/js/outlook2.js" type="text/javascript"></script>
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
				<h1>角色管理</h1>
			</div>
			<!-- /.page-header -->
			<div id="content" region="center" style="overflow: hidden">
				<div id="toolbar" class="datagrid-toolbar" style="height: 28px;">
					<div style="height: 28px;">
						<a class="easyui-linkbutton" iconcls="icon-add"
							onclick="createRole();" plain="true" href="javascript:void(0);"
							style="float: left;">创建</a>
						<div class="datagrid-btn-separator"></div>
						<a class="easyui-linkbutton" iconcls="icon-edit"
							onclick="editRole();" plain="true" href="javascript:void(0);"
							style="float: left;">编辑</a>
						<div class="datagrid-btn-separator"></div>
						<a class="easyui-linkbutton" iconcls="icon-remove"
							onclick="deleteRole();" plain="true" href="javascript:void(0);"
							style="float: left;">删除</a>
						<div class="datagrid-btn-separator"></div>
						<a class="easyui-linkbutton" iconcls="icon-undo"
							onclick="roleGrid.datagrid('unselectAll');" plain="true"
							href="javascript:void(0);" style="float: left;">取消选中</a>
					</div>
				</div>
			</div>
			<table id="roleList" width="100%" height="500px" border="false">
			</table>
					
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
	          }
// 	          , {
// 	              field: 'editPermission',
// 	              title: '编辑权限',
// 	              width: 80,
// 	              formatter: function (value, row, index) {
// 	                  return "<a href=\"javascript:doEditPermission('" + row.id +  "');\"" + " title='编辑权限'>编辑权限</a>";
// 	              }
// 	          }
	          ]]
	      });
	
	  });
	
	  function doEditPermission(roleId) {
	      var url = '<%=request.getContextPath()%>/admin/role/toEditPermission?roleId=' + roleId;
	      top.showMyWindow('编辑角色权限', 'icon-edit', url, 830, 480, true, false, true);
	  }
	 
	  //创建系统角色
	  function createRole(){
	  	 var fromUrl = '<%=request.getContextPath()%>/admin/role/toCreateRole';
	  	 var toUrl = '<%=request.getContextPath()%>/admin/role/createRole';
	       var p = top.sy.dialog({
	           title: '创建系统角色',
	           iconCls: 'icon-add',
	           href: fromUrl,
	           width: 450,
	           height: 350,
	           modal: true,
	           minimizable: false,
	           maximizable: false,
	           cache: false,
	           buttons: [{
	               text: '确定',
	               iconCls: 'icon-ok',
	               handler: function () {
	                   var f = p.find('form');
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
	                               p.dialog('close');
	                           }
	                           else {
	                               top.alertMsg('错误', map.msg+"\n"+(map.obj==null?"":map.obj));
	                           }
	
	                       },
	                       onLoadError: function () {
	                           top.showProcess(false);
	                           top.$.messager.alert('温馨提示', '由于网络或服务器太忙，提交失败，请重试！');
	                       }
	                   });
	               }
	           }, {
	               text: '取消',
	               iconCls: 'icon-cancel',
	               handler: function () {
	                   p.dialog('close');
	               }
	           }]
	       });
	
	  }
	  
	  //编辑系统角色
	  function editRole(){
	      var rows = roleGrid.datagrid('getSelections');
	      if (rows.length > 0) {
	          if (rows.length == 1) {
	              var url='<%=request.getContextPath()%>/admin/role/toEditRole?roleId='+rows[0].id;
	               var p = top.sy.dialog({
	                   title: '编辑系统角色',
	                   iconCls: 'icon-edit',
	                   href: url,
	                   width: 450,
	                   height: 350,
	                   modal: true,
	                   minimizable: false,
	                   maximizable: false,
	                   cache: false,
	                   buttons: [{
	                       text: '确定',
	                       iconCls: 'icon-ok',
	                       handler: function () {
	                           var f = p.find('form');
	                           f.form('submit', {
	                               url: '<%=request.getContextPath()%>/admin/role/editRole?roleId='+rows[0].id,
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
	                                       p.dialog('close');
	                                   }
	                                   else {
						top.alertMsg('错误', map.msg+"\n"+(map.obj==null?"":map.obj));
	                                   }
	
	                               },
	                               onLoadError: function () {
	                                   top.showProcess(false);
	                                   top.$.messager.alert('温馨提示', '由于网络或服务器太忙，提交失败，请重试！');
	                               }
	                           });
	                       }
	                   }, {
	                       text: '取消',
	                       iconCls: 'icon-cancel',
	                       handler: function () {
	                           p.dialog('close');
	                       }
	                   }]
	               });
	          }
	          else {
	              var names = [];
	              for (var i = 0; i < rows.length; i++) {
	                  names.push(rows[i].name);
	              }
	              top.showMsg("提示", '只能选择一个角色进行编辑！您已经选择了【'+names.join(',')+'】'+rows.length+'个角色');
	          }
	      }
	      else {
	          top.showMsg("提示", "请选择要编辑的记录！");
	      }
	  }
	  //删除系统角色
	  function deleteRole() {
	      var ids = [];
	      var rows = roleGrid.datagrid('getSelections');
	      if (rows.length>0) {
	          $.messager.confirm('提示', '确定删除吗？', function (r) {
	              if (r) {
	                  for (var i = 0; i < rows.length; i++) {
	                      ids.push(rows[i].id);
	                  }
	                  $.post('<%=request.getContextPath()%>/admin/role/deleteRole', { "roleIds": ids.join(',') }, function (data) {
	                      if (data.success) {
	                          reloadDataGrid();
	                          top.showMsg('提示', data.msg);
	                      }
	                      else {
	                          top.alertMsg('错误', data.msg+"\n"+(data.obj==null?"":data.obj));
	                      }
	
	                  }, "json");
	              }
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
