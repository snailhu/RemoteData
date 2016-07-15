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
	<script src="<%=request.getContextPath()%>/static/content/js/outlook2.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/content/js/validDate.js"></script>
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
				<h1>用户管理</h1>
			</div>
			<div id="content" region="center" title="用户信息" style="overflow: hidden">
			</div>
			<table id="userList" border="false" width="100%" height="500px">
			</table>
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
		var userGrid;
		var deptTree;
		var editWindow;

		function closeWindow() {
			editWindow.window('close');
		}

        $(function () {
            userGrid = $("#userList").datagrid({
                url: '<%=request.getContextPath()%>/admin/user/getUserList',
                rownumbers: true,
                fitColumns:true,
                idField: 'id',//'UserId',
                pageSize: 10,
                pagination: true,
                pageList: [10, 20, 30, 40, 50, 60, 70, 80, 90, 100],
                onLoadError:function(data){
                	$.messager.alert("用户信息", "暂无用户数据信息", "error");
                
                },
//                 onClickRow: function(){
//                 	$.messager.alert("1", "2", "3");
// 					var i = 1;
//                 	 $("input[type='checkbox']").each(function () {
//                          if (i == 1) {
//                              $(this).attr("checked", true);
//                          }
//                      });
//                 },
                frozenColumns: [[{
                    title: 'id',
                    field: 'id',//'UserId',
                    width: 50,
                    checkbox: true
                    
                }, {
                    field: 'userName',
                    title: '用户名',
                    width: 100,
                    sortable: true
                }]],
                columns: [[ {
                    field: 'mobile',
                    title: '联系方式',
                    width: 100,
                }, {
                    field: 'email',
                    title: '邮箱',
                    width: 100
                },{
                    field: 'createUser',
                    title: '创建人',
                    width: 100,
                }, {
                    field: 'createDate',
                    title: '创建时间',
                    width: 100
                }, 
//                 {
//                     field: 'loginLimit',
//                     title: '登录限制',
//                     width: 80,
//                     formatter: function (value, row, index) {
//                         return "<a href=\"javascript:doEditLoginLimit('" + row.id + "');\"" + " title='登录限制'>登录限制</a>";
//                     }
//                 }, 
                {
                    field: 'userRole',
                    title: '个人角色',
                    width: 100,
                    formatter: function (value, row, index) {
                        return "<a href=\"javascript:doEditRole('" + row.id + "');\"" + " title='个人角色'>"+ row.userRole +"</a>";
                    }
                }
                ]],

                toolbar: [
                {
                    text: '创建',
                    iconCls: 'icon-add',
                    handler: function () {
                     CreateUser();
                    }
                }, '-', {
                    text: '删除',
                    iconCls: 'icon-remove',
                    handler: function () {
                        deleteUser();
                    }
                }, '-', {
                    text: '编辑',
                    iconCls: 'icon-edit',
                    handler: function () {
                        editUser();
                    }
                }, '-', {
                    text: '取消选中',
                    iconCls: 'icon-undo',
                    handler: function () {
                        userGrid.datagrid('unselectAll');
                    }
                }]
            });

        });
     
 
        function reloadDataGrid() {
            userGrid.datagrid('clearChecked');
            userGrid.datagrid('reload');
        }
        // 登录限制
//         function doEditLoginLimit(userId) {
//             var url = '/taskpro/user/toEditLoginLimit.do';
//             url = $.format(url + '?userId={0} ', userId);
//             top.showMyWindow('编辑登录限制', 'icon-edit', url, 830, 480, true, false, true);
//         }
        // 编辑个人角色
        function doEditRole(userId) {
            var fromUrl = '<%=request.getContextPath()%>/admin/user/toEditRole?userId='+userId;
            var toUrl='<%=request.getContextPath()%>/admin/user/editRole';
// 			top.showMyWindow('编辑个人角色', 'icon-edit', url, 830, 480, true, false, true);
            var p = top.sy.dialog({
                title: '编辑个人角色',
                iconCls: 'icon-edit',
                href: fromUrl,
                width: 400 ,
                height: 300 ,
                modal: true,
                minimizable: false,
                maximizable: false,
                cache: false,
                buttons: [{
                    text: '确定',
                    iconCls: 'icon-save',
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
                                	top.alertMsg('错误', map.msg+"\n"+map.obj==null?"":map.obj);
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
       

		//快速搜索按钮
		function doSubmit(url) {
			var node = $('#deptTree').tree('getSelected');
           var orgIds=[];
           if(node){
             var children = deptTree.tree('getChildren', node.target);
             var orgId=node.id;
             orgIds.push(orgId);
			 for(var i=0; i<children.length; i++){
				 orgIds.push(children[i].id);
              }
            }
			userGrid.datagrid('load', {
				name : $('#name').val(),
				createdatetimeStart : $('#createdatetimeStart').datetimebox(
						'getValue'),
				createdatetimeEnd : $('#createdatetimeEnd').datetimebox(
						'getValue'),
				modifydatetimeStart : $('#modifydatetimeStart').datetimebox(
						'getValue'),
				modifydatetimeEnd : $('#modifydatetimeEnd').datetimebox(
						'getValue'),
				orgIds:orgIds.join(',')
			});
		}
		//创建用户
		function CreateUser() {
			
			var fromUrl = '<%=request.getContextPath()%>/admin/user/toCreateUser';
			var toUrl='<%=request.getContextPath()%>/admin/user/createUser';
			//showMyWindow('创建用户', 'icon-add', url, 550, 400, true, false, false);
			var p = top.sy.dialog({
                title: '创建用户',
                iconCls: 'icon-add',
                href: fromUrl,
                width: 480,
                height: 400,
                modal: true,
                minimizable: false,
                maximizable: false,
                cache: false,
                buttons: [{
                    text: '确定',
                    iconCls: 'icon-save',
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
                                	top.alertMsg('错误', map.msg+"\n"+map.obj==null?"":map.obj);
                                   
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
		//删除用户
		function deleteUser() {
			var ids = [];
			var rows = userGrid.datagrid('getSelections');
			if (rows.length > 0) {
				var names = [];
				var copyRows = [];
				for ( var i = 0; i < rows.length; i++) {
					names.push(rows[i].userName);
					copyRows.push(rows[i]);
				}
				top.$.messager.confirm("提示", "确定要删除用户【" + names.join(",") + "】吗？",
						function(r) {
							if (r) {
								for ( var i = 0; i < rows.length; i++) {
									ids.push(rows[i].id);
								}
								$.ajax({
									url : '<%=request.getContextPath()%>/admin/user/deleteUser',
									data : {
										userIds : ids.join(',')
									},
									cache : false,
									dataType : "json",
									success : function(data) {
										if (data.success) {
//												location.reload();
											userGrid.datagrid("unselectAll");
//												userGrid.datagrid('deleteRow',rows.length);
//												reloadDataGrid();
											for(var i = 0;i < copyRows.length;i++){    
									            var index = userGrid.datagrid('getRowIndex',copyRows[i]);
									            userGrid.datagrid('deleteRow',index); 
									        }
											top.showMsg('提示', data.msg);
										} else {
											top.alertMsg('警告', data.msg);
										}
									}
								});
							}
						});
				
			} else {
				top.showMsg("提示", "请选择要删除的用户！");
			}
		}
		//编辑用户
		function editUser() {
			var rows = userGrid.datagrid('getSelections');
			if (rows.length > 0) {
				if (rows.length == 1) {
					//弹出编辑框
					var fromUrl = '<%=request.getContextPath()%>/admin/user/toEditUser?userId='+rows[0].id;
					var toUrl='<%=request.getContextPath()%>/admin/user/editUser';
					var p = top.sy.dialog({
		                title: '编辑用户',
		                iconCls: 'icon-edit',
		                href: fromUrl,
		                width: 450,
		                height: 400,
		                modal: true,
		                minimizable: false,
		                maximizable: false,
		                cache: false,
		                buttons: [{
		                    text: '确定',
		                    iconCls: 'icon-save',
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
		                                	top.alertMsg('错误', map.msg+"\n"+map.obj==null?"":map.obj);
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

				} else {
					var names = [];
					for ( var i = 0; i < rows.length; i++) {
						names.push(rows[i].userName);
					}
					top.showMsg("提示", '只能选择一个用户进行编辑！您已经选择了【' + names.join(',')
							+ '】' + rows.length + '个用户');
				}
			} else {
				top.showMsg("提示", "请选择要编辑的用户！");
			}

		}
		function getSelectId() {
			var row = userGrid.datagrid('getSelected');

			if (!row) {
				$.messager.show({
					title : '提示',
					msg : '请选择数据！',
					showType : 'show'
				});
				return null;
			} else {
				return row.Id;
			}
		}
		
		function clearFun() {
			$('#frmSearchUser').form('clear');
		}
	</script>	
  </body>
</html>
