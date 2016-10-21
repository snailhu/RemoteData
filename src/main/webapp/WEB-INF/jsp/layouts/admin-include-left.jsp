<%@ page language="java" pageEncoding="UTF-8"%>
<style>

</style>
<div class="sidebar" id="sidebar">
 	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'fixed')
		} catch (e) {
		}
		var activeUser = '${activeUser}';
		$(function () {
			$(".flywheel-li").hide();
			$(".top-li").hide();
			$("#sysPermission-li").hide();
			if(activeUser != ''){
				var permissionItemsJSON = '${activeUser.permissionItemsJSON}';
				var map = $.parseJSON(permissionItemsJSON); 
// 				console.log('flywheel: ' + map.flywheel);
// 				console.log('top: ' + map.top);
// 				console.log('userManager: ' + map.userManager);
				if(map.flywheel == 'flywheel'){
					$(".flywheel-li").show();
				}
				if(map.top == 'top'){
					$(".top-li").show();
				}
				if(map.userManager == 'userManager'){
					$("#sysPermission-li").show();
				}
			}
		});
	</script> 

<!--  
	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success">
				<i class="icon-signal"></i>
			</button>

			<button class="btn btn-info">
				<i class="icon-pencil"></i>
			</button>

			<button class="btn btn-warning">
				<i class="icon-group"></i>
			</button>

			<button class="btn btn-danger">
				<i class="icon-cogs"></i>
			</button>
		</div>
		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span> <span class="btn btn-info"></span>
			<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
		</div>
	</div>
-->	
	<!-- #sidebar-shortcuts -->

	<!-- 
	<ul id="left_con" class="nav nav-list">
		<li><a href="admin/index"> <i class="icon-dashboard"></i> <span
				class="menu-text"> 欢迎页面 </span>
		</a></li>

		<li><a href="report/toUploadFile"> <i class="icon-dashboard"></i> <span
				class="menu-text"> 文件上传 </span>
		</a></li>
		<li>
			<a href="#" class="dropdown-toggle"> <i class="icon-list"></i>
				<span class="menu-text"> 文件管理</span>
				<b class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<li>
					<a href="#"> <i class="icon-double-angle-right"></i> 其他系列
					</a>
				</li>
				<li>
					<a href="#" class="dropdown-toggle">
						<i class="icon-double-angle-right"></i>
						j9系列
						<b class="arrow icon-angle-down"></b>
					</a>
					<ul class="submenu">
						<li>
							<a href="report/index/j9/01/0/">
								<i class="icon-leaf"></i>
								第一颗星
							</a>
						</li>
						<li>
							<a href="report/index/j9/02/0/">
								<i class="icon-leaf"></i>
								第二颗星
							</a>
						</li>
						<li>
							<a href="report/index/j9/03/0/">
								<i class="icon-leaf"></i>
								第三颗星
							</a>
						</li>
						<li>
							<a href="report/index/j9/04/0/">
								<i class="icon-leaf"></i>
								第四颗星
							</a>
						</li>
						<li>
							<a href="report/index/j9/05/0/">
								<i class="icon-leaf"></i>
								第五颗星
							</a>
						</li>
					</ul>
				</li>
			</ul>
		</li>
		
		<li>
			<a href="#" class="dropdown-toggle"> <i class="icon-list"></i>
				<span class="menu-text"> 系统管理</span>
				<b class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<li><a href="admin/user/index"> <i class="icon-double-angle-right"></i> 用户管理
					</a></li>
				<li><a href="#"> <i class="icon-double-angle-right"></i> 部门管理
					</a></li>
				<li><a href="admin/role/index"> <i class="icon-double-angle-right"></i> 角色管理
					</a></li>
				<li><a href="#"> <i class="icon-double-angle-right"></i> 权限管理
					</a></li>
				<li><a href="javascript:void(0)"> <i class="icon-double-angle-right"></i> 日志管理
					</a></li>
				<li><a href="ftl/hello"> <i class="icon-double-angle-right"></i> ftl视图
				</a></li>
				<li><a href="echart/echartTest"> <i class="icon-double-angle-right"></i> echart图表
					</a></li>
			</ul>
		</li>
		
	</ul>
	-->
	<!-- /.nav-list -->
	<ul id="left_con" class="nav nav-list">
	    <li>
	    	<a href="admin/file/toUploadFile"> <i class="icon-dashboard"></i> <span
				class="menu-text"> 文件上传 </span>
			</a>
		</li>
		<li>
			<a href="#" class="dropdown-toggle"> <i class="icon-list"></i>
				<span class="menu-text"> 文件管理</span>
				<b class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<li>
					<a href="javascript:void(0)"> <i class="icon-double-angle-right"></i> 其他系列
					</a>
				</li>
				<li>
					<a href="#" class="dropdown-toggle">
						<i class="icon-double-angle-right"></i>
						j9系列
						<b class="arrow icon-angle-down"></b>
					</a>
					<ul class="submenu">
						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-double-angle-right"></i>I号星
		                        <b class="arrow icon-angle-down"></b>
							</a>
							<ul class="submenu">
		                        <li class="flywheel-li">
		                            <a href="admin/file/index/j9/01/flywheel/0/">
		                                <i class="icon-leaf"></i> 飞轮
		                            </a>
		                        </li>
		                        <li class="top-li">
		                            <a href="admin/file/index/j9/01/top/0/">
		                                <i class="icon-leaf"></i>陀螺
		                            </a>
		                        </li>
		                    </ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-double-angle-right"></i>II号星
		                        <b class="arrow icon-angle-down"></b>
							</a>
							<ul class="submenu">
		                        <li class="flywheel-li">
		                            <a href="admin/file/index/j9/02/flywheel/0/">
		                                <i class="icon-leaf"></i> 飞轮
		                            </a>
		                        </li>
		                        <li class="top-li">
		                            <a href="admin/file/index/j9/02/top/0/">
		                                <i class="icon-leaf"></i>陀螺
		                            </a>
		                        </li>
		                    </ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-double-angle-right"></i>III号星
		                        <b class="arrow icon-angle-down"></b>
							</a>
							<ul class="submenu">
		                        <li class="flywheel-li">
		                            <a href="admin/file/index/j9/03/flywheel/0/">
		                                <i class="icon-leaf"></i> 飞轮
		                            </a>
		                        </li>
		                          <li class="top-li">
		                            <a href="admin/file/index/j9/03/top/0/">
		                                <i class="icon-leaf"></i>陀螺
		                            </a>
		                        </li>
		                    </ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-double-angle-right"></i>IV号星
		                        <b class="arrow icon-angle-down"></b>
							</a>
							<ul class="submenu">
		                        <li class="flywheel-li">
		                            <a href="admin/file/index/j9/04/flywheel/0/">
		                                <i class="icon-leaf"></i> 飞轮
		                            </a>
		                        </li>
		                          <li class="top-li">
		                            <a href="admin/file/index/j9/04/top/0/">
		                                <i class="icon-leaf"></i>陀螺
		                            </a>
		                        </li>
		                    </ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-double-angle-right"></i>V号星
		                        <b class="arrow icon-angle-down"></b>
							</a>
							<ul class="submenu">
		                        <li class="flywheel-li">
		                            <a href="admin/file/index/j9/05/flywheel/0/">
		                                <i class="icon-leaf"></i> 飞轮
		                            </a>
		                        </li>
		                          <li class="top-li">
		                            <a href="admin/file/index/j9/05/top/0/">
		                                <i class="icon-leaf"></i>陀螺
		                            </a>
		                        </li>
		                    </ul>
						</li>
					</ul>
				</li>
			</ul>
		</li>
		
		<li>
			<a href="#" class="dropdown-toggle"> <i class="icon-list"></i>
				<span class="menu-text"> 报告管理</span>
				<b class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<li>
					<a  href="${pageContext.request.contextPath}/starParam/index" "> <i class="icon-double-angle-right"></i> 参数管理
					</a>
				</li>
				<li>
					<a  href="${pageContext.request.contextPath}/report/reportDownLoad" "> <i class="icon-double-angle-right"></i> 报告下载
					</a>
				</li>
				<li>
					<a href="#" class="dropdown-toggle">
						<i class="icon-double-angle-right"></i>
						j9系列报告
						<b class="arrow icon-angle-down"></b>
					</a>
					<ul class="submenu">
						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-double-angle-right"></i>II号星
		                        <b class="arrow icon-angle-down"></b>
							</a>
							<ul class="submenu">
		                        <li class="flywheel-li">
		                            <a href="report/index/j9/02/flywheel/0/">
		                                <i class="icon-leaf"></i> 飞轮
		                            </a>
		                        </li>
		                    </ul>
						</li>
		                    </ul>
						</li>
					</ul>
				</li>
			</ul>
		</li>
		
		
		<li  id="sysPermission-li">
			<a href="#" class="dropdown-toggle"> <i class="icon-list"></i>
				<span class="menu-text"> 系统管理</span>
				<b class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<li><a href="${pageContext.request.contextPath}/admin/user/index"> <i class="icon-double-angle-right"></i> 用户管理
					</a></li>
<!-- 				<li><a href="${pageContext.request.contextPath}/admin/department/index"> <i class="icon-double-angle-right"></i> 部门管理 -->
<!-- 					</a></li> -->
				<li><a href="${pageContext.request.contextPath}/admin/role/index"> <i class="icon-double-angle-right"></i> 角色管理
					</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/permission/index"> <i class="icon-double-angle-right"></i> 权限管理
					</a></li>
<!-- 				<li><a href="javascript:void(0)"> <i class="icon-double-angle-right"></i> 日志管理 -->
<!-- 					</a></li> -->
			</ul>
		</li>
		<li  id="status-li">
			<a href="#" class="dropdown-toggle"> <i class="icon-list"></i>
				<span class="menu-text"> 状态跟踪</span>
				<b class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<li><a href="${pageContext.request.contextPath}/admin/status/startingIndex"> <i class="icon-double-angle-right"></i> 进行中
					</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/status/endIndex"> <i class="icon-double-angle-right"></i> 已完成
					</a></li>
			</ul>
		</li>
		<li  id="prewarning-li">
			<a href="#" class="dropdown-toggle"> <i class="icon-list"></i>
				<span class="menu-text"> 预警管理</span>
				<b class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<li><a href="${pageContext.request.contextPath}/admin/prewarning/warnvalueIndex"> <i class="icon-double-angle-right"></i> 特殊工况参数配置
					</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/prewarning/errorvalueIndex"> <i class="icon-double-angle-right"></i> 异常参数配置
					</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/prewarning/logIndex"> <i class="icon-double-angle-right"></i> 预警查询
					</a></li>
			</ul>
		</li>
<!-- 		<li>
			<a href="#" class="dropdown-toggle"> <i class="icon-list"></i>
				<span class="menu-text"> 图表管理</span>
				<b class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<li>
					<a href="javascript:void(0)"> <i class="icon-double-angle-right"></i> 其他系列
					</a>
				</li>
				<li>
					<a href="#" class="dropdown-toggle">
						<i class="icon-double-angle-right"></i>
						j9系列
						<b class="arrow icon-angle-down"></b>
					</a>
					<ul class="submenu">
						<li>
		                    <a href="#" class="dropdown-toggle">
		                        <i class="icon-double-angle-right"></i>I号星
		                        <b class="arrow icon-angle-down"></b>
		                    </a>
		                    <ul class="submenu">
		                        <li>
		                            <a href="#">
		                                <i class="icon-leaf"></i> 飞轮
		                            </a>
		                        </li>
		                          <li>
		                            <a href="#">
		                                <i class="icon-leaf"></i>陀螺
		                            </a>
		                        </li>
		                    </ul>
		                </li>
						<li>
							<a href="#" class="dropdown-toggle">
		                        <i class="icon-double-angle-right"></i>II号星
		                        <b class="arrow icon-angle-down"></b>
		                    </a>
		                    <ul class="submenu">
		                        <li>
		                            <a href="analysisData">
		                                <i class="icon-leaf"></i> 飞轮
		                            </a>
		                        </li>
		                          <li>
		                            <a href="#">
		                                <i class="icon-leaf"></i>陀螺
		                            </a>
		                        </li>
		                    </ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle">
		                        <i class="icon-double-angle-right"></i>III号星
		                        <b class="arrow icon-angle-down"></b>
		                    </a>
		                    <ul class="submenu">
		                        <li>
		                            <a href="#">
		                                <i class="icon-leaf"></i> 飞轮
		                            </a>
		                        </li>
		                          <li>
		                            <a href="#">
		                                <i class="icon-leaf"></i>陀螺
		                            </a>
		                        </li>
		                    </ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle">
		                        <i class="icon-double-angle-right"></i>IV号星
		                        <b class="arrow icon-angle-down"></b>
		                    </a>
		                    <ul class="submenu">
		                        <li>
		                            <a href="#">
		                                <i class="icon-leaf"></i> 飞轮
		                            </a>
		                        </li>
		                          <li>
		                            <a href="#">
		                                <i class="icon-leaf"></i>陀螺
		                            </a>
		                        </li>
		                    </ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle">
		                        <i class="icon-double-angle-right"></i>V号星
		                        <b class="arrow icon-angle-down"></b>
		                    </a>
		                    <ul class="submenu">
		                        <li>
		                            <a href="#">
		                                <i class="icon-leaf"></i> 飞轮
		                            </a>
		                        </li>
		                          <li>
		                            <a href="#">
		                                <i class="icon-leaf"></i>陀螺
		                            </a>
		                        </li>
		                    </ul>
						</li>
					</ul>
				</li>
			</ul>
		</li> -->
   </ul><!-- /.nav-list -->

	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
			data-icon2="icon-double-angle-right"></i>
	</div>

<!-- 	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'collapsed')
		} catch (e) {
		}
	</script> -->
</div>
