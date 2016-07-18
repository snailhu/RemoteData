<@override name="link">	
</@override>
<@override name="style">
	<style>
		/*左侧菜单*/
			.sidebar-menu{
				border-right: 1px solid gray;
				width:100%!important;
			}
			/*一级菜单*/
			.menu-first{
				height:45px;
				line-height:45px;
				background-color: #e9e9e9;
				border-top: 1px solid #efefef;
				border-bottom: 1px solid #e1e1e1;
				padding: 0;
				font-size: 14px;
				font-weight: normal;
				text-align: center;
			}
			/*一级菜单鼠标划过状态*/
			.menu-first:hover{
				text-decoration: none;
				background-color: #d6d4d5;
				border-top: 1px solid #b7b7b7;
				border-bottom: 1px solid #acacac;
			}
			/*二级菜单*/
			.menu-second li a{
				background-color: #f6f6f6;
				height:50px;
				line-height:30px;
				border-top: 1px solid #efefef;
				border-bottom: 1px solid #efefef;
				font-size: 12px;
				text-align:center;
			}
			/*二级菜单鼠标划过样式*/
			.menu-second li a:hover {
				text-decoration: none;
				background-color: #66c3ec;
				border-top: 1px solid #83ceed;
				border-bottom: 1px solid #83ceed;
				border-right: 3px solid #f8881c;
				border-left: 3px solid #66c3ec;
			}
			/*二级菜单选中状态*/
			.menu-second-selected {
				background-color: #66c3ec;
				height:50px;
				line-height:31px;
				border-top: 1px solid #83ceed;
				border-bottom: 1px solid #83ceed;
				border-right: 3px solid #f8881c;
				border-left: 3px solid #66c3ec;
				text-align:center;
			}
			/*覆盖bootstrap的样式*/
			.nav-list,.nav-list li a{
				padding: 0px;
				margin: 0px;
			}
		.container-out .content_right{padding-right:0px!important}
		.content_right .content_right_header{height:46px;line-height:46px}
		.content_right .content_right_main{background-color:#efefef}
		
		        #kanbanBox1 {
            width: 400px;
            height: 400px;
            float: left;
            padding: 2px;
        }
        #kanbanBox2 {
            width: 400px;
            height: 400px;
            float: left;
            padding: 2px;
        }
        #outerBox1 {
            float: left;
            padding: 5px;
            margin: 2px;
            background-color: #6bbd49;
            color: #fff;
        }
        #outerBox2 {
            color: #fff;
            float: left;
            padding: 5px;
            margin: 2px;
            background-color: #5dc3f0;
        }
        #kanban{
        	width:95%!important;
        }
		</style>
</@override>

<@override name="header">
<div class="navbar navbar-inverse  navbar-static-top">
    <div class="container topxx" style="font-size:12px">    
        <ul class="nav navbar-nav navbar-right pull-right">
	        <li><a href="/Account/Register" id="registerLink">注册</a></li>
	        <li style="position:relative; top:15px"> |</li>
	        <li><a href="/Account/Login" id="loginLink">登录</a></li>
	    </ul>
	</div>
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>               
            <a class="navbar-brand" href="/">  </a> 
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/">网站首页</a></li>
                <li><a href="/Home/Web">网站开发</a></li>
                <li><a href="/Home/App">APP开发</a></li>
                <li><a href="/Home/Contact">美银团队</a></li>
                <li><a href="/Home/Iflve">开发报价</a></li>        
                <li><a href="/Home/Hongbao">试用下载</a></li>
            </ul>               
        </div>
    </div>
</div>
</@override>
	<@override name="content_left">
		<div class="row-fluid">
			<div class="offset1 span2">
				<!--Sidebar content-->
				<div class="sidebar-menu">
					<a href="#userMeun" class="nav-header menu-first collapsed" data-toggle="collapse"><i class="icon-user-md icon-large"></i>9号星系</a>
					<ul id="userMeun" class="nav nav-list collapse menu-second">
						<li><a href="#"><i class="icon-user"></i> 1号星</a></li>
						<li><a href="#"><i class="icon-edit"></i> 2号星</a></li>
						<li><a href="#"><i class="icon-trash"></i> 3号星</a></li>
						<li><a href="#"><i class="icon-list"></i> 4号星</a></li>
						
					</ul>					
				</div>

			</div>
		</div>	
	</@override>
<@extends name="base.ftl"/>