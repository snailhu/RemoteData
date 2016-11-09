<%@ page language="java" pageEncoding="UTF-8"%>
<style>
.nav-list>li>.submenu:before {
	content: "";
	display: block;
	position: absolute;
	z-index: 1;
	left: 18px;
	top: 0;
	bottom: 0;
	border: 0px;
	border-width: 0 0 0 1px;
}

.nav-list>li>.submenu>li:before {
	content: "";
	display: inline-block;
	position: absolute;
	width: 7px;
	left: 20px;
	top: 17px;
	border-top: 0px;
}

.DataImport_manage .data_import .left .content .tab_title {
	height: 40px;
	line-height: 40px;
	padding: 0 40px 0 5px;
	font-size: 14px;
	color: #525252;
	border-bottom: 1px solid #DEDEDE;
	padding-left: 0px;
	text-decoration: none;
}

.DataImport_manage .data_import .left .content .tab_li {
	height: 40px;
	line-height: 40px;
	padding: 0 40px;
	font-size: 13px;
	color: #525252;
	background-color: rgba(255, 255, 255, 0.15);
	border-bottom: 1px solid #DEDEDE;
	padding-left: 15px;
	text-decoration: none;
}

.DataImport_manage .data_import .left .content .tab_li.level2 {
	padding-left: 15px;
	text-decoration: none;
}

.DataImport_manage .data_import .left .content .tab_li.level3 {
	padding-left: 30px;
	text-decoration: none;
}

.DataImport_manage .data_import .left .content .tab_li.level4 {
	padding-left: 45px;
	text-decoration: none;
}

.DataImport_manage .data_import .left .content .tab_li.level5 {
	padding-left: 60px;
	text-decoration: none;
}

.DataImport_manage .data_import .left .content {
	height: 0px;
	border: 0px;
}

.DataImport_manage .data_import .left {
	float: left;
	width: 189px;
	background-color: #F3F3F3;
	margin-left: 0px;
}

a:link {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}

a:hover {
	text-decoration: none;
}

a:active {
	text-decoration: none;
}

a {
	text-decoration: none;
}
</style>
<div class="sidebar left" id="sidebar">
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'fixed')
		} catch (e) {
		}
		var activeUser = '${activeUser}';
		$(function() {
			$(".flywheel-li").hide();
			$(".top-li").hide();
			$("#sysPermission-li").hide();
			if (activeUser != '') {
				var permissionItemsJSON = '${activeUser.permissionItemsJSON}';
				var map = $.parseJSON(permissionItemsJSON);
				// 				console.log('flywheel: ' + map.flywheel);
				// 				console.log('top: ' + map.top);
				// 				console.log('userManager: ' + map.userManager);
				if (map.flywheel == 'flywheel') {
					$(".flywheel-li").show();
				}
				if (map.top == 'top') {
					$(".top-li").show();
				}
				if (map.userManager == 'userManager') {
					$("#sysPermission-li").show();
				}
			}
			$("a")
					.click(
							function() {
								var flag = $(this).find(".img_down")
										.attr("src");
								if (flag == "${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png") {
									$(this)
											.find(".img_down")
											.attr("src",
													"${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png");
								} else {
									$(this)
											.find(".img_down")
											.attr("src",
													"${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png");
								}
							})
		});
	</script>


	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="title">
			<img
				src="${pageContext.request.contextPath}/static/new/img/DataImport/mulu.png"
				style="width: 189px;">
		</div>
	</div>

	<!-- #sidebar-shortcuts -->

	<!-- /.nav-list -->
	<ul id="left_con" class="nav nav-list content">

		<li class="left-li-list"><a href="#"
			class="dropdown-toggle tab_title"> <img class="img_head"
				src="${pageContext.request.contextPath}/static/new/img/images/images/A_26.png"
				id="filemanage-img"> <span class="menu-text"
				id="filemange-text"> 文件管理</span> <img class="img_down"
				src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
		</a>
			<ul class="submenu" id="filemanageUL">
				<li><a href="admin/file/toUploadFile" class="tab_li"> <img
						class="img_head" id="uploadFile-img"
						src="${pageContext.request.contextPath}/static/new/img/images/images/A_30.png">
						<span class="menu-text" id="uploadFile-text"> 数据导入 </span>
				</a></li>
				<li id="status-li"><a href="#" class="dropdown-toggle tab_li">
						<img class="img_head" id="statustracking-img"
						src="${pageContext.request.contextPath}/static/new/img/images/images/A_34.png">
						<span class="menu-text" id="statustracking-text"> 文件状态</span> <img
						class="img_down"
						src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
				</a>
					<ul class="submenu" id="statustrackingUL">
						<li><a
							href="${pageContext.request.contextPath}/admin/status/startingIndex"
							class="tab_li level2"> <img id="starting-img"
								class="img_head"
								src="${pageContext.request.contextPath}/static/new/img/images/images/A_38.png">
								<span id="starting-text">进行中</span>
						</a></li>
						<li><a
							href="${pageContext.request.contextPath}/admin/status/endIndex"
							class="tab_li level2"> <img class="img_head" id="ending-img"
								src="${pageContext.request.contextPath}/static/new/img/images/images/A_42.png">
								<span id="ending-text">已结束</span>
						</a></li>
					</ul></li>
				<li><a href="#" class="dropdown-toggle tab_li"> <img
						id="fileview-img" class="img_head"
						src="${pageContext.request.contextPath}/static/new/img/images/images/A_46.png">
						<span class="menu-text" id="fileview-text"> 文件查看</span> <img
						class="img_down"
						src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
				</a>
					<ul class="submenu" id="fileviewUL">
						<li><a href="javascript:void(0)" class="tab_li level2"> <img
								id="otherfile-img" class="img_head"
								src="${pageContext.request.contextPath}/static/new/img/images/images/A_14.png">
								<span id="otherfile-text">其他系列</span> <img class="img_down"
								src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
						</a></li>
						<li><a href="#" class="dropdown-toggle tab_li level2"> <img
								id="j9file-img" class="img_head"
								src="${pageContext.request.contextPath}/static/new/img/images/images/A_14.png">
								<span id="j9file-text">j9系列</span> <img class="img_down"
								src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
						</a>
							<ul class="submenu" id="j9fileviewUL">
								<li><a href="#" class="dropdown-toggle tab_li level3">
										<img class="img_head" id="j901file-img"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span id="j901file-text">Ⅰ号星</span> <img class="img_down"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
								</a>
									<ul class="submenu" id="j901fileviewUL">
										<li class="flywheel-li"><a
											href="admin/file/index/j9/01/flywheel/0/"
											class="tab_li level4"> <img class="img_head"
												id="j901flywheelfile-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j901flywheelfile-text">飞轮</span>
										</a></li>
										<li class="top-li"><a
											href="admin/file/index/j9/01/top/0/" class="tab_li level4">
												<img class="img_head" id="j901topfile-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j901topfile-text">陀螺</span>
										</a></li>
									</ul></li>
								<li><a href="#" class="dropdown-toggle tab_li level3">
										<img class="img_head" id="j902file-img"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span id="j902file-text">Ⅱ号星</span> <img class="img_down"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
								</a>
									<ul class="submenu" id="j902fileviewUL">
										<li class="flywheel-li"><a
											href="admin/file/index/j9/02/flywheel/0/"
											class="tab_li level4"> <img class="img_head"
												id="j902flywheelfile-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j902flywheelfile-text">飞轮</span>
										</a></li>
										<li class="top-li"><a
											href="admin/file/index/j9/02/top/0/" class="tab_li level4">
												<img class="img_head" id="j902topfile-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j902topfile-text">陀螺</span>
										</a></li>
									</ul></li>
								<li><a href="#" class="dropdown-toggle tab_li level3">
										<img class="img_head" id="j903file-img"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span id="j903file-text">Ⅲ号星</span> <img class="img_down"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
								</a>
									<ul class="submenu" id="j903fileviewUL">
										<li class="flywheel-li"><a
											href="admin/file/index/j9/03/flywheel/0/"
											class="tab_li level4"> <img class="img_head"
												id="j903flywheelfile-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j903flywheelfile-text">飞轮</span>
										</a></li>
										<li class="top-li"><a
											href="admin/file/index/j9/03/top/0/" class="tab_li level4">
												<img class="img_head" id="j903topfile-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j903topfile-text">陀螺</span>
										</a></li>
									</ul></li>
								<li><a href="#" class="dropdown-toggle tab_li level3">
										<img class="img_head" id="j904file-img"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span id="j904file-text">Ⅳ号星</span> <img class="img_down"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
								</a>
									<ul class="submenu" id="j904fileviewUL">
										<li class="flywheel-li"><a
											href="admin/file/index/j9/04/flywheel/0/"
											class="tab_li level4"> <img class="img_head"
												id="j904flywheelfile-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j904flywheelfile-text">飞轮</span>
										</a></li>
										<li class="top-li"><a
											href="admin/file/index/j9/04/top/0/" class="tab_li level4">
												<img class="img_head" id="j904topfile-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j904topfile-text">陀螺</span>
										</a></li>
									</ul></li>
								<li><a href="#" class="dropdown-toggle tab_li level3">
										<img class="img_head" id="j905file-img"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span id="j905file-text">Ⅴ号星</span> <img class="img_down"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
								</a>
									<ul class="submenu" id="j905fileviewUL">
										<li class="flywheel-li"><a
											href="admin/file/index/j9/05/flywheel/0/"
											class="tab_li level4"> <img class="img_head"
												id="j905flywheelfile-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j905flywheelfile-text">飞轮</span>
										</a></li>
										<li class="top-li"><a
											href="admin/file/index/j9/05/top/0/" class="tab_li level4">
												<img class="img_head" id="j905topfile-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j905topfile-text">陀螺</span>
										</a></li>
									</ul></li>
							</ul></li>
					</ul></li>

			</ul></li>

		<li><a href="#" class="dropdown-toggle tab_title"> <img
				class="img_head"
				src="${pageContext.request.contextPath}/static/new/img/images/images/A_05.png"
				id="reportmanage-img"> <span class="menu-text"
				id="reportmanage-text"> 报告管理</span> <img class="img_down"
				src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
		</a>
			<ul class="submenu" id="reportmanageUL">
				<li><a
					href="${pageContext.request.contextPath}/starParam/index"
					class="tab_li level2"> <img class="img_head"
						id="parammanage-img"
						src="${pageContext.request.contextPath}/static/new/img/images/images/A_22.png">
						<span id="parammanage-text">参数管理</span>
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/report/reportDownLoad"
					class="tab_li level2"> <img class="img_head"
						id="reportcreate-img"
						src="${pageContext.request.contextPath}/static/new/img/images/images/A_18.png">
						<span id="reportcreate-text">报告生成</span>
				</a></li>
				<li><a href="#" class="dropdown-toggle tab_li level2"> <img
						class="img_head" id="reportview-img"
						src="${pageContext.request.contextPath}/static/new/img/images/images/A_10.png">
						<span class="menu-text" id="reportview-text"> 定时报告</span> <img
						class="img_down"
						src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
				</a>
					<ul class="submenu" id="reportviewUL">
						<li><a href="javascript:void(0)" class="tab_li level3"> <img
								class="img_head" id="otherreport-img"
								src="${pageContext.request.contextPath}/static/new/img/images/images/A_14.png">
								<span id="otherreport-text">其他系列</span> <!-- 								<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png"> -->
						</a></li>
						<li><a href="#" class="dropdown-toggle tab_li level3"> <img
								class="img_head" id="j9report-img"
								src="${pageContext.request.contextPath}/static/new/img/images/images/A_14.png">
								<span id="j9report-text">j9报告</span> <img class="img_down"
								src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
						</a>
							<ul class="submenu" id="j9reportUL">
								<li><a href="#" class="dropdown-toggle tab_li level3">
										<img class="img_head" id="j901report-img"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span id="j901report-text">Ⅰ号星</span> <img class="img_down"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
								</a>
									<ul class="submenu" id="j901reportUL">
										<li class="flywheel-li"><a
											href="report/index/j9/01/flywheel/0/" class="tab_li level4">
												<img class="img_head" id="j901flywheelreport-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j901flywheelreport-text">飞轮</span>
										</a></li>
										<li class="top-li"><a href="report/index/j9/01/top/0/"
											class="tab_li level4"> <img class="img_head"
												id="j901topreport-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j901topreport-text">陀螺</span>
										</a></li>
									</ul></li>
								<li><a href="#" class="dropdown-toggle tab_li level3">
										<img class="img_head" id="j902report-img"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span id="j902report-text">Ⅱ号星</span> <img class="img_down"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
								</a>
									<ul class="submenu" id="j902reportUL">
										<li class="flywheel-li"><a
											href="report/index/j9/02/flywheel/0/" class="tab_li level4">
												<img class="img_head" id="j902flywheelreport-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j902flywheelreport-text">飞轮</span>
										</a></li>
										<li class="top-li"><a href="report/index/j9/02/top/0/"
											class="tab_li level4"> <img class="img_head"
												id="j902topreport-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j902topreport-text">陀螺</span>
										</a></li>
									</ul></li>
								<li><a href="#" class="dropdown-toggle tab_li level3">
										<img class="img_head" id="j903report-img"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span id="j903report-text">Ⅲ号星</span> <img class="img_down"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
								</a>
									<ul class="submenu" id="j903reportUL">
										<li class="flywheel-li"><a
											href="report/index/j9/03/flywheel/0/" class="tab_li level4">
												<img class="img_head" id="j903flywheelreport-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j903flywheelreport-text">飞轮</span>
										</a></li>
										<li class="top-li"><a href="report/index/j9/03/top/0/"
											class="tab_li level4"> <img class="img_head"
												id="j903topreport-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j903topreport-text">陀螺</span>
										</a></li>
									</ul></li>
								<li><a href="#" class="dropdown-toggle tab_li level3">
										<img class="img_head" id="j904report-img"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span id="j904report-text">Ⅳ号星</span> <img class="img_down"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
								</a>
									<ul class="submenu" id="j904reportUL">
										<li class="flywheel-li"><a
											href="report/index/j9/04/flywheel/0/" class="tab_li level4">
												<img class="img_head" id="j904flywheelreport-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j904flywheelreport-text">飞轮</span>
										</a></li>
										<li class="top-li"><a href="report/index/j9/04/top/0/"
											class="tab_li level4"> <img class="img_head"
												id="j904topreport-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j904topreport-text">陀螺</span>
										</a></li>
									</ul></li>
								<li><a href="#" class="dropdown-toggle tab_li level3">
										<img class="img_head" id="j905report-img"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span id="j905report-text">Ⅴ号星</span> <img class="img_down"
										src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
								</a>
									<ul class="submenu" id="j905reportUL">
										<li class="flywheel-li"><a
											href="report/index/j9/05/flywheel/0/" class="tab_li level4">
												<img class="img_head" id="j905flywheelreport-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j905flywheelreport-text">飞轮</span>
										</a></li>
										<li class="top-li"><a href="report/index/j9/05/top/0/"
											class="tab_li level4"> <img class="img_head"
												id="j905topreport-img"
												src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
												<span id="j905topreport-text">陀螺</span>
										</a></li>
									</ul></li>
							</ul></li>
					</ul></li>
			</ul></li>

		<li id="sysPermission-li"><a href="#"
			class="dropdown-toggle tab_title"> <img class="img_head"
				id="sysmanage-img"
				src="${pageContext.request.contextPath}/static/new/img/images/images/A_50.png">
				<span id="sysmanage-text" class="menu-text"> 系统管理</span> <img
				class="img_down"
				src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
		</a>
			<ul class="submenu" id="sysmanageUL">
				<li><a
					href="${pageContext.request.contextPath}/admin/systemLog"
					class="tab_li"> <img class="img_head" id="logmanage-img"
						src="${pageContext.request.contextPath}/static/new/img/images/images/A_54.png">
						<span id="logmanage-text">日志管理</span>
				</a></li>
				<li id="prewarning-li"><a href="#"
					class="dropdown-toggle tab_li"> <img class="img_head"
						id="warnmanage-img"
						src="${pageContext.request.contextPath}/static/new/img/images/images/A_58.png">
						<span class="menu-text" id="warnmanage-text"> 预警管理</span> <img
						class="img_down"
						src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
				</a>
					<ul class="submenu" id="warnmanageUL">
						<li><a
							href="${pageContext.request.contextPath}/admin/prewarning/warnvalueIndex"
							class="tab_li"> <img class="img_head" id="warnparam-img"
								src="${pageContext.request.contextPath}/static/new/img/images/images/A_62.png">
								<span id="warnparam-text">特殊工况配置</span>
						</a></li>
						<li><a
							href="${pageContext.request.contextPath}/admin/prewarning/errorvalueIndex"
							class="tab_li"> <img class="img_head" id="errorparam-img"
								src="${pageContext.request.contextPath}/static/new/img/images/images/A_66.png">
								<span id="errorparam-text">异常配置</span>
						</a></li>
						<li><a
							href="${pageContext.request.contextPath}/admin/prewarning/logIndex?hadReadFlag=1"
							class="tab_li"> <img class="img_head" id="warnlog-img"
								src="${pageContext.request.contextPath}/static/new/img/images/images/A_70.png">
								<span id="warnlog-text">预警查询</span>
						</a></li>
					</ul></li>
				<li><a
					href="${pageContext.request.contextPath}/admin/user/index"
					class="tab_li"> <img class="img_head" id="usermanage-img"
						src="${pageContext.request.contextPath}/static/new/img/images/images/A_74.png">
						<span id="usermanage-text">用户管理</span>
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/admin/role/index"
					class="tab_li"> <img class="img_head" id="rolemanage-img"
						src="${pageContext.request.contextPath}/static/new/img/images/images/A_03.png">
						<span id="rolemanage-text">角色管理</span>
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/admin/permission/index"
					class="tab_li"> <img class="img_head" id="permissionmanage-img"
						src="${pageContext.request.contextPath}/static/new/img/images/images/A_78.png">
						<span id="permissionmanage-text">权限管理</span>
				</a></li>
			</ul></li>
		<li><a
			href="${pageContext.request.contextPath}/admin/galaxy/index"
			class="tab_title"> <img class="img_head" id="galaxymanage-img"
				src="${pageContext.request.contextPath}/static/new/img/images/images/A_82.png">
				<span id="galaxymanage-text">星系管理</span>
		</a></li>
		<li><a
			href="${pageContext.request.contextPath}/conditionMonitoring"
			class="tab_title"> <img class="img_head"
				id="conditionmonitoring-img"
				src="${pageContext.request.contextPath}/static/new/img/images/images/A_86.png">
				<span id="conditionmonitoring-text">数据分析</span>
		</a></li>

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
	</ul>
	<!-- /.nav-list -->

	<!-- 	<div class="sidebar-collapse" id="sidebar-collapse"> -->
	<!-- 		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" -->
	<!-- 			data-icon2="icon-double-angle-right"></i> -->
	<!-- 	</div> -->

	<!-- 	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'collapsed')
		} catch (e) {
		}
	</script> -->
</div>
