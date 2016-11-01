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
    font-size: 15px;
    color: #525252;
    border-bottom: 1px solid #DEDEDE;
}
.DataImport_manage .data_import .left .content .tab_li {
    height: 40px;
    line-height: 40px;
    padding: 0 40px;
    font-size: 14px;
    color: #525252;
    background-color: rgba(255, 255, 255, 0.15);
    border-bottom: 1px solid #DEDEDE;
    padding-left: 5px;
}
.DataImport_manage .data_import .left .content .tab_li.level2 {
    padding-left: 15px;
}
.DataImport_manage .data_import .left .content .tab_li.level3 {
    padding-left: 30px;
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

</style>
<div class="sidebar left" id="sidebar">
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

  
	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="title">
			<img src="${pageContext.request.contextPath}/static/new/img/DataImport/mulu.png" style="width: 189px;">
		</div>
	</div>

	<!-- #sidebar-shortcuts -->

	<!-- /.nav-list -->
	<ul id="left_con" class="nav nav-list content">
		
		<li>
			<a href="#" class="dropdown-toggle tab_title"> 
				<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/wenjianguanli.png">
				<span class="menu-text"> 文件管理</span> 
				<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
			</a>
			<ul class="submenu">
				<li>
					<a href="admin/file/toUploadFile" class="tab_title"> 
						<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/wenjianshangchuan.png">
						<span class="menu-text"> 文件上传 </span>
<!-- 		 				<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">  -->
					</a>
				</li>
				<li>
					<a href="javascript:void(0)" class="tab_li"> 
						<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						<span>其他系列</span>
						<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
					</a>
				</li>
				<li>
					<a href="#" class="dropdown-toggle tab_li"> 
						<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png"> 
                		<span>j9系列</span>
                		<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
					</a>
					<ul class="submenu">
						<li>
							<a href="#" class="dropdown-toggle tab_li level2"> 
				                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
				                <span>Ⅰ号星</span>
				                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
							</a>
							<ul class="submenu">
								<li class="flywheel-li">
									<a href="admin/file/index/j9/01/flywheel/0/" class="tab_li level3"> 
										<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span>飞轮</span>
<!-- 										<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
								<li class="top-li">
									<a href="admin/file/index/j9/01/top/0/" class="tab_li level3">
						                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						                <span>陀螺</span>
<!-- 						                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle tab_li level2"> 
				                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
				                <span>Ⅱ号星</span>
				                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
							</a>
							<ul class="submenu">
								<li class="flywheel-li">
									<a href="admin/file/index/j9/02/flywheel/0/" class="tab_li level3"> 
										<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span>飞轮</span>
<!-- 										<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
								<li class="top-li">
									<a href="admin/file/index/j9/02/top/0/" class="tab_li level3">
						                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						                <span>陀螺</span>
<!-- 						                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle tab_li level2"> 
				                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
				                <span>Ⅲ号星</span>
				                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
							</a>
							<ul class="submenu">
								<li class="flywheel-li">
									<a href="admin/file/index/j9/03/flywheel/0/" class="tab_li level3"> 
										<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span>飞轮</span>
<!-- 										<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
								<li class="top-li">
									<a href="admin/file/index/j9/03/top/0/" class="tab_li level3">
						                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						                <span>陀螺</span>
<!-- 						                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle tab_li level2"> 
				                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
				                <span>Ⅳ号星</span>
				                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
							</a>
							<ul class="submenu">
								<li class="flywheel-li">
									<a href="admin/file/index/j9/04/flywheel/0/" class="tab_li level3"> 
										<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span>飞轮</span>
<!-- 										<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
								<li class="top-li">
									<a href="admin/file/index/j9/04/top/0/" class="tab_li level3">
						                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						                <span>陀螺</span>
<!-- 						                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle tab_li level2"> 
				                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
				                <span>Ⅴ号星</span>
				                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
							</a>
							<ul class="submenu">
								<li class="flywheel-li">
									<a href="admin/file/index/j9/05/flywheel/0/" class="tab_li level3"> 
										<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span>飞轮</span>
<!-- 										<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
								<li class="top-li">
									<a href="admin/file/index/j9/05/top/0/" class="tab_li level3">
						                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						                <span>陀螺</span>
<!-- 						                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
			</ul>
		</li>
		
		<li>
			<a href="#" class="dropdown-toggle tab_title"> 
				<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/wenjianguanli.png">
				<span class="menu-text"> 报告管理</span> 
				<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
			</a>
			<ul class="submenu">
				<li>
					<a href="${pageContext.request.contextPath}/starParam/index" class="tab_li"> 
						<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						<span>参数管理</span>
<!-- 						<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png"> -->
					</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/report/reportDownLoad" class="tab_li"> 
						<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						<span>报告下载</span>
<!-- 						<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png"> -->
					</a>
				</li>
				<li>
					<a href="#" class="dropdown-toggle tab_li"> 
						<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png"> 
                		<span>j9报告</span>
                		<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
					</a>
					<ul class="submenu">
						<li>
							<a href="#" class="dropdown-toggle tab_li level2"> 
				                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
				                <span>Ⅰ号星</span>
				                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
							</a>
							<ul class="submenu">
								<li class="flywheel-li">
									<a href="report/index/j9/01/flywheel/0/" class="tab_li level3"> 
										<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span>飞轮</span>
<!-- 										<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
								<li class="top-li">
									<a href="report/index/j9/01/top/0/" class="tab_li level3">
						                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						                <span>陀螺</span>
<!-- 						                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle tab_li level2"> 
				                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
				                <span>Ⅱ号星</span>
				                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
							</a>
							<ul class="submenu">
								<li class="flywheel-li">
									<a href="report/index/j9/02/flywheel/0/" class="tab_li level3"> 
										<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span>飞轮</span>
<!-- 										<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
								<li class="top-li">
									<a href="report/index/j9/02/top/0/" class="tab_li level3">
						                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						                <span>陀螺</span>
<!-- 						                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle tab_li level2"> 
				                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
				                <span>Ⅲ号星</span>
				                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
							</a>
							<ul class="submenu">
								<li class="flywheel-li">
									<a href="report/index/j9/03/flywheel/0/" class="tab_li level3"> 
										<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span>飞轮</span>
<!-- 										<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
								<li class="top-li">
									<a href="report/index/j9/03/top/0/" class="tab_li level3">
						                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						                <span>陀螺</span>
<!-- 						                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle tab_li level2"> 
				                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
				                <span>Ⅳ号星</span>
				                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
							</a>
							<ul class="submenu">
								<li class="flywheel-li">
									<a href="report/index/j9/04/flywheel/0/" class="tab_li level3"> 
										<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span>飞轮</span>
<!-- 										<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
								<li class="top-li">
									<a href="report/index/j9/04/top/0/" class="tab_li level3">
						                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						                <span>陀螺</span>
<!-- 						                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle tab_li level2"> 
				                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
				                <span>Ⅴ号星</span>
				                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
							</a>
							<ul class="submenu">
								<li class="flywheel-li">
									<a href="report/index/j9/05/flywheel/0/" class="tab_li level3"> 
										<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
										<span>飞轮</span>
<!-- 										<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
								<li class="top-li">
									<a href="report/index/j9/05/top/0/" class="tab_li level3">
						                <img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						                <span>陀螺</span>
<!-- 						                <img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/shangla.png"> -->
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
			</ul>
		</li>

		<li id="sysPermission-li">
			<a href="#" class="dropdown-toggle tab_title"> 
				<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xitongguanli.png">
				<span class="menu-text"> 系统管理</span> 
				<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
			</a>
			<ul class="submenu">
				<li>
					<a href="${pageContext.request.contextPath}/admin/user/index" class="tab_li"> 
						<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						<span>用户管理</span>
<!-- 						<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png"> -->
					</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/admin/role/index" class="tab_li"> 
						<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						<span>角色管理</span>
<!-- 						<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png"> -->
					</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/admin/permission/index" class="tab_li"> 
						<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						<span>权限管理</span>
<!-- 						<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png"> -->
					</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/admin/galaxy/index" class="tab_li"> 
						<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						<span>星系管理</span>
<!-- 						<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png"> -->
					</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/admin/systemLog" class="tab_li"> 
						<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						<span>日志管理</span>
<!-- 						<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png"> -->
					</a>
				</li>
			</ul>
		</li>
		
		<li id="status-li">
			<a href="#" class="dropdown-toggle tab_title"> 
				<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/wenjianguanli.png">
				<span class="menu-text"> 状态跟踪</span> 
				<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
			</a>
			<ul class="submenu">
				<li>
					<a href="${pageContext.request.contextPath}/admin/status/startingIndex" class="tab_li"> 
						<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						<span>进行中</span>
<!-- 						<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png"> -->
					</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/admin/status/endIndex" class="tab_li"> 
						<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						<span>已结束</span>
<!-- 						<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png"> -->
					</a>
				</li>
			</ul>
		</li>
			
		<li id="prewarning-li">
			<a href="#" class="dropdown-toggle tab_title"> 
				<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/wenjianguanli.png">
				<span class="menu-text"> 预警管理</span> 
				<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png">
			</a>
			<ul class="submenu">
				<li>
					<a href="${pageContext.request.contextPath}/admin/prewarning/warnvalueIndex" class="tab_li"> 
						<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						<span>特殊工况参数配置</span>
<!-- 						<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png"> -->
					</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/admin/prewarning/errorvalueIndex" class="tab_li"> 
						<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						<span>异常参数配置</span>
<!-- 						<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png"> -->
					</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/admin/prewarning/logIndex?hadReadFlag=1" class="tab_li"> 
						<img class="img_head" src="${pageContext.request.contextPath}/static/new/img/DataImport/xuxian.png">
						<span>预警查询</span>
<!-- 						<img class="img_down" src="${pageContext.request.contextPath}/static/new/img/DataImport/xiala.png"> -->
					</a>
				</li>
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
