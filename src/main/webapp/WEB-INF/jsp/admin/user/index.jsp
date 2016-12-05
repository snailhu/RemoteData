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
	
	<script src="<%=request.getContextPath()%>/static/content/js/outlook2.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/content/js/validDate.js"></script>
	
	<!-- 表单验证 -->
	<!--     <link rel="stylesheet" href="<%=request.getContextPath()%>/static/content/bootstrapValidator/vendor/bootstrap/css/bootstrap.css"/> -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/content/bootstrapValidator/dist/css/bootstrapValidator.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/content/bootstrapValidator/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/content/bootstrapValidator/dist/js/bootstrapValidator.js"></script>
	
	<script src="${pageContext.request.contextPath}/static/assets/js/bootstrap.min.js"></script>
	<!-- 时间选择器 -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/content/jeDate/jedate/skin/jedate.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/content/jeDate/jedate/jedate.js"></script>
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/all.css" type="text/css" />
    
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
.form-group input,.form-group select{
	width: 240px;
	height:30px;
	line-height:30px;
	text-align:left;
}
</style>
<script type="text/javascript">
	$(function() {
		$(function(){
			//修改页面缩放，界面显示不正常
			$(".col-lg-3").addClass("col-sm-3");
			$(".col-lg-7").css("text-align","center");
			$(".modal-dialog").css("margin","20px auto");
			$(".col-lg-4").css({"margin-left":"25%","width":"300px"});
			//$(".col-lg-4").find("button").css("display","inline");
			//左菜单栏
			$("#usermanage-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_74.png");
			$("#sysmanage-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_50.png");
			$("#usermanage-text").css("color","#5d90d6");
			$("#sysmanage-text").css("color", "#5d90d6");
			$("#sysmanageUL").css("display","block");
		})
		
		$('#addUserInfoForm').bootstrapValidator({
			message : '这个值不能为空！',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				userName : {
					message : '用户名不能为空',
					validators : {
						notEmpty : {
							message : '用户名不能为空'
						},
						stringLength : {
							min : 3,
							max : 30,
							message : '用户名必须超过3，不超过30个字符'
						},
						regexp : {
							regexp : /^[a-zA-Z0-9_\.]+$/,
							message : '用户名只能由字母、数字和下划线组成'
						},

					}
				},
				passWord : {
					validators : {
						notEmpty : {
							message : '密码不能为空'
						},
						different : {
							field : 'userName',
							message : '密码不能跟用户名一样'
						}
					}
				},
				confirmPassword : {
					validators : {
						notEmpty : {
							message : '确认密码不能为空'
						},
						identical : {
							field : 'passWord',
							message : '密码和确认密码不一致'
						}
					}
				},
				mobile : {
					validators : {
						regexp : {
							regexp : /^1[3578]\d{9}$/,
							message : '手机号码格式不对'
						},
					}
				},
				email : {
					validators : {
						emailAddress : {
							message : '请输入正确的邮箱地址'
						}
					}
				},
			}
		});
		
// 		$('#reset_addUserInfo').click(function() {
// 			$('#addUserInfoForm').data('bootstrapValidator').resetForm(true);
// 		});
// 		$('#close_addUserInfo').click(function() {
// 			$('#addUserInfoForm').data('bootstrapValidator').resetForm(true);
// 		});
		
		
		$('#editUserInfoForm').bootstrapValidator({
			message : '这个值不能为空！',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				userName : {
					message : '用户名不能为空',
					validators : {
						notEmpty : {
							message : '用户名不能为空'
						},
						stringLength : {
							min : 3,
							max : 30,
							message : '用户名必须超过3，不超过30个字符'
						},
						regexp : {
							regexp : /^[a-zA-Z0-9_\.]+$/,
							message : '用户名只能由字母、数字和下划线组成'
						},
					}
				},
				passWord : {
					validators : {
						
					}
				},
				confirmPassword : {
					validators : {
						identical : {
							field : 'passWord',
							message : '密码和确认密码不一致'
						}
					}
				},
				mobile : {
					validators : {
						regexp : {
							regexp : /^1[3578]\d{9}$/,
							message : '手机号码格式不对'
						},
					}
				},
				email : {
					validators : {
						emailAddress : {
							message : '请输入正确的邮箱地址'
						}
					}
				},
			}
		});
// 		$('#reset_editUserInfo').click(function() {
// 			$('#editUserInfoForm').data('bootstrapValidator').resetForm(true);
// 		});
// 		$('#close_editUserInfo').click(function() {
// 			$('#editUserInfoForm').data('bootstrapValidator').resetForm(true);
// 		});
		$('#vss').click(function() {
			$('#addUserInfoForm').bootstrapValidator('validate');
		});
		//修改搜索框图标
		var flag=false;
		$("#change-search-box").click(function(){		
			if(flag){
				
				$("#toolimg").attr("src","${pageContext.request.contextPath}/static/imgs/DataImport_manage/xia.png")
				$(".widget-body").slideUp("slow");
				flag=false;
			}else{
				$("#toolimg").attr("src","${pageContext.request.contextPath}/static/imgs/DataImport_manage/xia2.png")
		 		$(".widget-body").slideDown("slow");
				flag=true;
			}
		});
		$("#change-search-box").click();
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
				<li class="active">用户管理</li>
			</ul><!--  .breadcrumb -->
		</div>
		<div class="page-content">
<!-- 			<div class="page-header" style="margin: 0px;float: left;"> -->
<!-- 				<h1>用户管理</h1> -->
<!-- 			</div> -->
			<!-- /.page-header -->
			
			<div >
				<div class="col-xs-12 col-sm-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="widget-box">
						<div class="widget-header" id="change-search-box" data-action="collapse">
							<h4 >搜索</h4>
							<div class="widget-toolbar">
								<a href="javascript:void(0);">
									<div hidden="hidden"><i class="icon-chevron-up" hidden="hidden"></i></div>
									<img id="toolimg" style="margin-top: 3px;"
									src="${pageContext.request.contextPath}/static/imgs/DataImport_manage/xia.png">
								</a>
							</div>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<!-- 搜索form -->
								<form id="searchUserForm" action="" class="form-horizontal" role="form" style="margin-left:170px;">
									<div class="space-1"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="search-userName"> 用户名：</label>
										<div class="col-sm-9">
											<input type="text" id="search-userName" name="userName" placeholder="--请输入用户名--" class="col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="search-createdatetimeStart"> 创建开始时间：</label>
										<div class="col-sm-9">
											<input type="text" id="search-createdatetimeStart" name="createdatetimeStart" 
											placeholder="--请选择创建开始时间--" class="col-xs-10 col-sm-5" readonly="true" />
											<div id="getBeginTime"></div>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="search-createdatetimeEnd"> 创建结束时间：</label>
										<div class="col-sm-9">
											<input type="text" id="search-createdatetimeEnd" name="createdatetimeEnd" 
											placeholder="--请选择创建结束时间--" class="col-xs-10 col-sm-5" readonly="true" />
											<div id="getEndTime"></div>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
<!-- 			                           <div class="col-lg-4 col-lg-offset-6"> -->
<!-- 					                        <button type="button" id="btn-search" class="btn btn-primary start"> -->
<!-- 							                    <i></i> -->
<!-- 							                    <span>搜索</span> -->
<!-- 							                </button> -->
<!-- 							                <button type="reset" class="btn btn-warning cancel"> -->
<!-- 							                    <i></i> -->
<!-- 							                    <span>取消</span> -->
<!-- 							                </button> -->
<!-- 			                           </div> -->
										<div class="col-lg-4 col-lg-offset-4">
											<button type="button" id="btn-search" class="subbutton_1">
												<i></i> <span>搜索</span>
											</button>
											<button type="reset" id="btn-reset" class="cancelbutton_1">
												<i></i> <span>取消</span>
											</button>
										</div>
			                       </div>
								</form>
							</div>
						</div>
					</div>
					<!-- PAGE CONTENT ENDS -->
				</div><!-- /.col -->
			</div><!-- /.row -->
			
			<div id="content" region="center" title="用户信息" style="overflow: hidden">
			</div>
			<table id="userList" border="false" width="100%" height="450px">
			</table>
			
			<!-- 创建用户 -->
			<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="addUserModalLabel">
				<div class="modal-dialog" role="document" style="margin:150px 450px">
					<div class="modal-content">
						<form id="addUserInfoForm" class="form-horizontal" role="form" style="margin: 0px;">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="close_addUserInfo">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="addUserModalLabel">用户信息</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-user-name"> 用户名：</label>
									<div class="col-sm-8">
										<input type="text" name="userName" id="add-user-name" placeholder="用户名" class="form-control" />
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-user-passWord"> 密码：</label>
									<div class="col-sm-8">
										<input type="password" name="passWord" id="add-user-passWord" placeholder="密码" class="form-control" />
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-user-confirmPassword"> 确认密码：</label>
									<div class="col-sm-8">
										<input type="password" name="confirmPassword" id="add-user-confirmPassword" placeholder="确认密码" class="form-control" />
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="add-user-mobile"> 联系方式：</label>
									<div class="col-sm-8">
										<input type="text" name="mobile" id="add-user-mobile" placeholder="联系方式" class="form-control" />
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-lg-3 control-label no-padding-right" for="add-user-email"> 邮箱：</label>
									<div class="col-sm-8">
										<input type="text" name="email" id="add-user-email" placeholder="邮箱" class="form-control" />
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<div class="col-lg-7 col-lg-offset-3">
									<button type="button" class="subbutton_1" id="submit_addUserInfo">确定</button>
									<button type="button" class="cancelbutton_1" data-dismiss="modal" id="reset_addUserInfo">关闭</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 编辑用户 -->
			<div class="modal fade" id="editUserModal" tabindex="-1" role="dialog" aria-labelledby="editUserModalLabel">
				<div class="modal-dialog" role="document" style="margin:150px 450px">
					<div class="modal-content">
						<form id="editUserInfoForm" class="form-horizontal" role="form" style="margin: 0px;">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="close_editUserInfo">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="editUserModalLabel">用户信息</h4>
							</div>
							<div class="modal-body">
								<input type="hidden" name="id" id="edit-user-id"/>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="edit-user-name"> 用户名：</label>
									<div class="col-sm-8">
										<input type="text" name="userName" id="edit-user-name" placeholder="用户名" class="form-control" />
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="edit-user-passWord"> 密码：</label>
									<div class="col-sm-8">
										<input type="password" name="passWord" id="edit-user-passWord" placeholder="密码" class="form-control" />
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="edit-user-confirmPassword"> 确认密码：</label>
									<div class="col-sm-8">
										<input type="password" name="confirmPassword" id="edit-user-confirmPassword" placeholder="确认密码" class="form-control" />
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="edit-user-mobile"> 联系方式：</label>
									<div class="col-sm-8">
										<input type="text" name="mobile" id="edit-user-mobile" placeholder="联系方式" class="form-control" />
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-lg-3 control-label no-padding-right" for="edit-user-email"> 邮箱：</label>
									<div class="col-sm-8">
										<input type="text" name="email" id="edit-user-email" placeholder="邮箱" class="form-control" />
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<div class="col-lg-7 col-lg-offset-3">
									<button type="button" class="subbutton_1" id="submit_editUserInfo">确定</button>
									<button type="button" class="cancelbutton_1" id="reset_editUserInfo" data-dismiss="modal">关闭</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 编辑用户角色 -->
			<div class="modal fade" id="editUserRoleModal" tabindex="-1" role="dialog" aria-labelledby="editUserRoleModalLabel"  >
			  <div class="modal-dialog" role="document" style="margin:150px 450px">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="editUserRoleModalLabel">编辑角色</h4>
			      </div>
			      <div class="modal-body">
			      	<form id="editUserRoleForm" class="form-horizontal" role="form" style="margin: 0px;">
			      		<div class="form-group">
					      	<div class="col-lg-7 col-lg-offset-2">
					      		<input type="hidden" id="editUserRole-userId">
								<input id="roleList" name="roleId" style="width: 300px;height: 35px"/>
							</div>
						</div>
					</form>
			      </div>
			      <div class="modal-footer">
			      	<div class="col-lg-7 col-lg-offset-3">
				        <button type="button" class="subbutton_1" data-dismiss="modal" onclick="submit_editUserRole()">确定</button>
				        <button type="button" class="cancelbutton_1" data-dismiss="modal" id="reset_editUserRole">关闭</button>
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
jeDate({
	dateCell:"#search-createdatetimeStart",//直接显示日期层的容器，可以是ID  CLASS
	format:"YYYY-MM-DD hh:mm:ss",//日期格式
	isinitVal:false, //是否初始化时间
	festival:false, //是否显示节日
	isTime:true, //是否开启时间选择
	//minDate:"2014-09-19 00:00:00",//最小日期
	maxDate:jeDate.now(0), //设定最大日期为当前日期
});
jeDate({
	dateCell:"#search-createdatetimeEnd",//直接显示日期层的容器，可以是ID  CLASS
	format:"YYYY-MM-DD hh:mm:ss",//日期格式
	isinitVal:false, //是否初始化时间
	festival:false, //是否显示节日
	isTime:true, //是否开启时间选择
	//minDate:"2014-09-19 00:00:00",//最小日期
	maxDate:jeDate.now(0), //设定最大日期为当前日期
});
$("#search-createdatetimeStart").keypress(function(){
 return false;
});
$("#search-createdatetimeEnd").keypress(function(){
  return false;

});
		var userGrid;
		var deptTree;
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
                frozenColumns: [[{
                    title: 'id',
                    field: 'id',//'UserId',
                    width: 50,
                    checkbox: true
                    
                }, {
                    field: 'userName',
                    title: '用户名',
                    width: 100,
                    sortable:true
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
                    width: 100,
                    sortable:true
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
                     createUser();
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

        // 编辑个人角色
        function doEditRole(userId) {
    		$("#permissionTree").empty();					
    		userGrid.datagrid('unselectAll');
        	//弹出编辑角色
    		$('#editUserRoleModal').modal('show');
    		$("#editUserRole-userId").attr("value",userId);
    		$("#roleList").combobox({
			    url:'${pageContext.request.contextPath}/admin/role/getRoleComboData?userId='+userId,
			    valueField:'value',
			    textField:'text'
			}); 
        }
       function submit_editUserRole(){
			var userId = $("#editUserRole-userId").attr("value");
   			var roleId = $("#roleList").combobox('getValue');
   			if(roleId != null && roleId != 0){
	    			$.ajax({
						url : '${pageContext.request.contextPath}/admin/user/editRole',
						data : {
							userId : userId,
							roleId : roleId
						},
						cache : false,
						dataType : "json",
						success : function(data) {
							if (data.success) {
								top.showMsg('提示', data.msg);
								reloadDataGrid();
							} else {
								top.alertMsg('错误', data.msg+"\n"+data.obj==null?"":data.obj);
							}
						}
					});
   			}
       }

		//快速搜索按钮
		$('#btn-search').click(function(){
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
           	var name = $('#search-userName').val();
           	var createdatetimeStart = $('#search-createdatetimeStart').val();
           	var createdatetimeEnd = $('#search-createdatetimeEnd').val();
           	var updatedatetimeStart = $('#search-updatedatetimeStart').val();
           	var updatedatetimeEnd = $('#search-updatedatetimeEnd').val();
			userGrid.datagrid('load', {
				name : name,
				createdatetimeStart : createdatetimeStart,
				createdatetimeEnd : createdatetimeEnd,
				updatedatetimeStart : updatedatetimeStart,
				updatedatetimeEnd : updatedatetimeEnd,
				orgIds:orgIds.join(',')
			});
		});
		
		$('#addUserModal').on('hide.bs.modal', function () {
			$('#addUserInfoForm').data('bootstrapValidator').resetForm(true);
		});
		//创建用户
		function createUser() {
			//弹出创建用户
			$('#addUserModal').modal('show');
		}
		$('#submit_addUserInfo').click(function(){
			var f = $('#addUserInfoForm');
			f.data('bootstrapValidator').validate();
			var isValid = f.data('bootstrapValidator').isValid();
			if(!isValid){
				//top.alertMsg('错误', '请满足提交条件！');
				return false;
			}
			var toUrl='${pageContext.request.contextPath}/admin/user/createUser';
			var f = $('#addUserInfoForm');
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
				        	$('#addUserModal').modal('hide');
				            top.showMsg('提示', map.msg);
				            reloadDataGrid();
				        } else {
				        	top.alertMsg('错误', map.msg);
				        }
				    },
				    onLoadError: function () {
				        top.showProcess(false);
				        top.$.messager.alert('温馨提示', '由于网络或服务器太忙，提交失败，请重试！');
				    }
				});
		});
		//删除用户
		function deleteUser() {
			var ids = [];
			var rows = userGrid.datagrid('getSelections');
			if (rows.length > 0) {
				var names = [];
				for ( var i = 0; i < rows.length; i++) {
					names.push(rows[i].userName);
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
// 					closeOnCancel : false
				},
				function(isConfirm) {
					if (isConfirm) {
						for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						$.ajax({
							url : '${pageContext.request.contextPath}/admin/user/deleteUser',
							data : {
								userIds : ids.join(',')
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
			} else {
				top.showMsg("提示", "请选择要删除的用户！");
			}
		}
		//编辑用户
		$('#editUserModal').on('hide.bs.modal', function () {
			$('#editUserInfoForm').data('bootstrapValidator').resetForm(true);
		});
		function editUser() {
			var rows = userGrid.datagrid('getSelections');
			if (rows.length > 0) {
				if (rows.length == 1) {
					//弹出编辑框
					$('#edit-user-id').val(rows[0].id)
					$('#edit-user-name').val(rows[0].userName);
					$('#edit-user-mobile').val(rows[0].mobile);
					$('#edit-user-email').val(rows[0].email);
					$('#editUserModal').modal('show');
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
		$('#submit_editUserInfo').click(function(){
			var f = $('#editUserInfoForm');
			f.data('bootstrapValidator').validate();
			var isValid = f.data('bootstrapValidator').isValid();
			if(!isValid){
				return false;
			}
			var id = $('#edit-user-id').val()
			var userName = $('#edit-user-name').val();
			var passWord = $('#edit-user-passWord').val();
			var mobile = $('#edit-user-mobile').val();
			var email = $('#edit-user-email').val();
			var toUrl='${pageContext.request.contextPath}/admin/user/editUser';
			$.post(toUrl, 
					{
						id : id,
						userName : userName,
						passWord : passWord,
						mobile : mobile,
						email : email,
					},
					function(data){
						top.showProcess(false);
						if (data.success) {
							$('#editUserModal').modal('hide');
							top.showMsg('提示', data.msg);
							reloadDataGrid();
						} else {
							top.alertMsg('警告', data.msg);
						}
			});
			
		});
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
