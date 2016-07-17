<@override name="header">	
	<div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="icon-leaf"></i>
                    Ace Admin
                </small>
            </a><!-- /.brand -->
        </div><!-- /.navbar-header -->

        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="${base}/static/assets/avatars/user.jpg" alt="Jason's Photo" />
                        <span class="user-info">
                            <small>Welcome,</small>
                            Jason
                        </span>
                        <i class="icon-caret-down"></i>
                    </a>
                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="#">
                                <i class="icon-cog"></i>
                                Settings
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <i class="icon-user"></i>
                                Profile
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a href="#">
                                <i class="icon-off"></i>
                                Logout
                            </a>
                        </li>
                    </ul>
                </li>
            </ul><!-- /.ace-nav -->
        </div><!-- /.navbar-header -->
    </div><!-- /.container -->
</@override>
<@override name="content_left">
    <div class="sidebar" id="sidebar">
	    <ul class="nav nav-list">
	        <li>
	            <a href="#" class="dropdown-toggle">
	                <i class="icon-desktop"></i>
	                <span class="menu-text"> 9号星系 </span>
	
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
	            </ul>
	        </li>
	
	    </ul><!-- /.nav-list -->
	
	    <div class="sidebar-collapse" id="sidebar-collapse">
	        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
	    </div>	
	</div>
</@override>
<@extends name="/secondStyle/baseNew.ftl"/>