<@override name="link">
		<link href="${base}/static/assets/css/bootstrap.min.css" rel="stylesheet"/>
		<link href="${base}/static/assets/css/font-awesome.min.css" rel="stylesheet"/>
		<!--[if IE 7]>
		  <link rel="stylesheet" href="${base}/static/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<!-- fonts -->

		<#--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />-->

		<!-- ace styles -->
		<link rel="stylesheet" href="${base}/static/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${base}/static/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${base}/static/assets/css/ace-skins.min.css" />

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="${base}/static/assets/css/ace-ie.min.css" />
		<![endif]-->
				
		<!-- inline styles related to this page -->
	
</@override>
<@override name="script">
			<script src="${base}/static/assets/js/ace-extra.min.js"></script>
		<#--<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>-->

		<!-- <![endif]-->

		<!--[if IE]>
		<#--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>-->
		<![endif]-->

		<!--[if !IE]> -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='${base}/static/assets/js/jquery-2.0.3.min.js'>"+"<"+"script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
 			window.jQuery || document.write("<script src='${base}/static/assets/js/jquery-1.10.2.min.js'>"+"<"+"script>");
		</script>
		<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${base}/static/assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
		</script>
		<script src="${base}/static/assets/js/bootstrap.min.js"></script>
		<script src="${base}/static/assets/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="${base}/static/assets/js/excanvas.min.js"></script>
		<![endif]-->

		<script src="${base}/static/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="${base}/static/assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="${base}/static/assets/js/jquery.slimscroll.min.js"></script>
		<script src="${base}/static/assets/js/jquery.easy-pie-chart.min.js"></script>
		<script src="${base}/static/assets/js/jquery.sparkline.min.js"></script>
		<script src="${base}/static/assets/js/flot/jquery.flot.min.js"></script>
		<script src="${base}/static/assets/js/flot/jquery.flot.pie.min.js"></script>
		<script src="${base}/static/assets/js/flot/jquery.flot.resize.min.js"></script>

		<!-- ace scripts -->

		<script src="${base}/static/assets/js/ace-elements.min.js"></script>
		<script src="${base}/static/assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
		
		<script src="${base}/static/assets/js/sidebar-menu.js"></script>
		<script src="${base}/static/assets/js/bootstrap-tab.js"></script>
		<script type="text/javascript">
</@override>
<@override name="header">
	<div class="navbar navbar-default" id="navbar">
		<script type="text/javascript">
			try{ace.settings.check('navbar' , 'fixed')}catch(e){}
		</script>
		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand">
					<small>
						<i class="icon-leaf"></i>
						遥测数据分析
					</small>
				</a><!-- /.brand -->
			</div><!-- /.navbar-header -->
			<div class="navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
						<li class="purple">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-bell-alt icon-animated-bell"></i>
								<span class="badge badge-important">8</span>
							</a>

							<ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="icon-warning-sign"></i>
									8条通知
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-pink icon-comment"></i>
												卫星1参数异常
											</span>
											<span class="pull-right badge badge-info">+12</span>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<i class="btn btn-xs btn-primary icon-user"></i>
										卫星1参数异常
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-success icon-shopping-cart"></i>
												卫星1参数异常
											</span>
											<span class="pull-right badge badge-success">+8</span>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										查看所有通知
										<i class="icon-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>

						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="${base}/static/assets/avatars/user.jpg" alt="工程师1" />
								<span class="user-info">
									<small>欢迎光临,</small>
									工程师1
								</span>

								<i class="icon-caret-down"></i>
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="#">
										<i class="icon-cog"></i>
										设置
									</a>
								</li>

								<li>
									<a href="#">
										<i class="icon-user"></i>
										个人资料
									</a>
								</li>

								<li class="divider"></li>

								<li>
									<a href="#">
										<i class="icon-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
			</div>
		</div>
	</div>
</@override>
<@override name="content_left">
	<div class="sidebar" id="sidebar">
		<script type="text/javascript">
			try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
		</script>

		<div class="sidebar-shortcuts" id="sidebar-shortcuts">
			<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
				<button class="btn btn-success">
					<i class="icon-signal"></i>
				</button>

				<button class="btn btn-info">
					<i class="icon-pencil"></i>
				</button>


			</div>

			<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
				<span class="btn btn-success"></span>

				<span class="btn btn-info"></span>

				<span class="btn btn-warning"></span>

				<span class="btn btn-danger"></span>
			</div>
		</div><!-- #sidebar-shortcuts -->

		<ul class="nav nav-list" id="menu">
			<li class="active">
				<a href="index.html">
					<i class="icon-dashboard"></i>
					<span class="menu-text"> j9系列卫星健康监控 </span>
				</a>
			</li>

			<li>
				<a href="typography.html">
					<i class="icon-text-width"></i>
					<span class="menu-text"> 单颗卫星参数分析柱状图 </span>
				</a>
			</li>
			<li>
				<a href="widgets.html">
					<i class="icon-list-alt"></i>
					<span class="menu-text"> 历史数据查询 </span>
				</a>
			</li>
			
			
			<li>
				<a href="#" class="dropdown-toggle">
					<i class="icon-desktop"></i>
					<span class="menu-text"> 单颗卫星参数分析曲线图 </span>

					<b class="arrow icon-angle-down"></b>
				</a>

				<ul class="submenu">
					<li>
						<a href="tabs.html">
							<i class="icon-double-angle-right"></i>
							卫星1
						</a>
					</li>

					<li>
						<a href="tabs.html">
							<i class="icon-double-angle-right"></i>
							卫星2
						</a>
					</li>

					<li>
						<a href="tabs.html">
							<i class="icon-double-angle-right"></i>
							卫星3
						</a>
					</li>

					<li>
						<a href="tabs.html">
							<i class="icon-double-angle-right"></i>
							卫星5
						</a>
					</li>
				</ul>
			</li>	
		</ul>
		<script type="text/javascript">
				  $(function () {
			      $('#menu').sidebarMenu({
			        data: [{
			          id: '1',
			          text: '报告管理',
			          icon: 'icon-cog',
			          url: '',
			          menus: [{
			            id: '11',
			            text: '卫星1报告',
			            icon: 'icon-glass',
			            url: '/DataRemote/Index'
			          }]
			        }, {
			          id: '2',
			          text: '统计图表',
			          icon: 'icon-leaf',
			          url: '',
			          menus: [{
			            id: '21',
			            text: '卫星1',
			            icon: 'icon-glass',
			            url: '/DataRemote/Index'
			          }, {
			            id: '22',
			            text: '卫星2',
			            icon: 'icon-glass',
			            url: '/DataRemote/Index'
			          }, {
			            id: '23',
			            text: '卫星3',
			            icon: 'icon-glass',
			            url: '/DataRemote/Index'
			          }, {
			            id: '24',
			            text: '卫星4',
			            icon: 'icon-glass',
			            url: '/DataRemote/Index'
			          },{
			            id: '25',
			            text: '卫星5',
			            icon: 'icon-glass',
			            url: '/DataRemote/Index'
			          }]
			        }, {
			          id: '3',
			          text: '参数查询',
			          icon: 'icon-user',
			          url: '',
			          menus: [{
			            id: '31',
			            text: '自定义时间段查询',
			            icon: 'icon-user',
			            url: '/DataRemote/Index'
			          }, {
			            id: '32',
			            text: '复选框多值查询',
			            icon: 'icon-apple',
			            url: '/Index'
			          }, {
			            id: '33',
			            text: '大量数据查询',
			            icon: 'icon-list',
			            url: '/Index'
			          }, {
			            id: '34',
			            text: '部门管理',
			            icon: 'icon-glass',
			            url: '/Index'
			          }]
			        }]
			      });
			    });
		</script>
		<#-- /.nav-list -->

		<div class="sidebar-collapse" id="sidebar-collapse">
			<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
		</div>
		<script type="text/javascript">
			try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
		</script>
	</div>
</@override>
	
<@override name="content_right">
	<div class="main-content">
		<div class="page-content">
         	<div class="row">
	           <div class="col-xs-12" style="padding-left:5px;">
	             <ul class="nav nav-tabs" role="tablist">
	               <li class="active"><a href="#Index" role="tab" data-toggle="tab">首页</a></li>
	             </ul>
	             <div class="tab-content">
	               <div role="tabpanel" class="tab-pane active" id="Index"></div>
	             </div>
	           </div>
	         </div>
		 </div>
	</div>
</@override>
<@extends name="base.ftl"/>