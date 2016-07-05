<@override name="header">
<script src="${base}/static/js/JScriptCommon.js"
	type="text/javascript"></script>
<link href="${base}/static/content/css/Common.css"
	rel="stylesheet" type="text/css" />
	<style>
		.nav-list>li>a {
		    display: block;
		    height: 38px;
		    line-height: 24px;
		    padding: 9px;
		    background-color: #f9f9f9;
		    color: #585858;
		    text-shadow: none!important;
		    font-size: 13px;
		    text-decoration: none;
	    }
		.sidebar-shortcuts-large {
		    padding-bottom: 5px;
		}

.icon-folder{
	background: url('${base}/static/img/folder.png');
    background-color: gray; 
	background-repeat:no-repeat;
	background-position:center;
}

#navbar-container a {
	font-size:18px;
	text-decoration:none;
	cursor: pointer;
}
	</style>
	<script type="text/javascript">
		try{ace.settings.check('navbar' , 'fixed')}catch(e){}
	</script>
	<div class="navbar navbar-default" id="navbar">
		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left" style="margin:3px 0px 3px 80px !important;">
				<div class="navbar-brand">
					<small>
						<i class="icon-leaf"></i>
						<font size="5px">Syslink</font>
					</small>
					
				</div><!-- /.brand -->
				<a  href="${gogsUrl}/" class="navbar-brand">
					<small>
						控制面板	
					</small>
				</a>
				<a href="${gogsUrl}/issues" class="navbar-brand">
					<small>
						工单管理	
					</small>
				</a>
				<a href="${gogsUrl}/pulls" class="navbar-brand">
					<small>
						合并请求
					</small>
				</a>
				<a href="${gogsUrl}/explore" class="navbar-brand">
					<small>
						探索	
					</small>
				</a>

			</div><!-- /.navbar-header -->
		</div>
		<div class="navbar-header pull-right" role="navigation" style="margin:3px 0px !important; ">
			<ul class="nav ace-nav">
				<li class="light-blue">
					<a data-toggle="dropdown" href="#" class="dropdown-toggle">
						<img class="nav-user-photo" src="${gogsUrl}/avatars/${user.id}"/>
						<span class="user-info">
							<small>欢迎光临,</small>
							${user.name}
						</span>
						<i class="icon-caret-down"></i>
					</a>
					<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						<li>
							<a href="${gogsUrl}/user/settings" target="_blank">
								<i class="icon-cog"></i>
								用户设置
							</a>
						</li>
						<#if user.is_admin==1>
							<li>
								<a href="${gogsUrl}/admin" target="_blank">
									<i class="icon-cog"></i>
									管理面板
								</a>
							</li>
						</#if>
						<li>
							<a href="${gogsUrl}/${username}" target="_blank">
								<i class="icon-user"></i>
								个人资料
							</a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="javascript:void(0)" onclick="user_logout()">
								<i class="icon-off"></i>
								退出
							</a>
							<a href="${gogsUrl}/user/logout" id="gogs_logout" style="display:none">
								退出
							</a>
						</li>
					</ul>
				</li>		
			</ul>
		</div>
	</div>	
</@override>
	
<@override name="content_left">
<p style="margin-top: 100px"><img src="${base}/static/img/404.png" alt="404"/></p>
</@override>
<@extends name="base_second.ftl"/>