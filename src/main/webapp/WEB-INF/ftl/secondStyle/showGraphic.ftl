<@override name="script">

<script type="text/javascript" src="${base}/static/js/echarts.min.js"></script>
<script type="text/javascript">
    
    $(function(){
    	var myChart = echarts.init(document.getElementById('main'));
    	var seriesOptions = []
        seriesCounter = 0
        var date = [];
        names = ['flywheel_a_speed','flywheel_f_motor_current'];
         var options = {
            tooltip: {
                trigger: 'axis',              
            },
            title: {
                left: 'center',

            },
            legend: {
                top: 'bottom', 
                 data:['flywheel_a_speed']
                           
            },
            toolbox: {
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    restore: {},
                    saveAsImage: {}
                }
            },
            xAxis: {
            	// axisLabel: {					
				//	rotate: 10
				//	},
                type: 'category',
                boundaryGap: false,
                data:[]              
            },
            yAxis: [{
                type: 'value',
            }],
            dataZoom: [{
                type: 'inside',
                start:20,
                end:25         
            }],
            series: []
        };    	     	
    	 $.getJSON('${base}/getDate', function (data) {
			 options.xAxis.data = eval(data);
			 myChart.setOption(options);	
     });   
    	    	   
        $.each(names, function (i, name) {
	        $.getJSON('${base}/getData?filename=' + name.toLowerCase(), function (data) {	
	            seriesOptions[i] = {
	            	type: 'line',
	                name: name,
	                data: data
	            };
	            seriesCounter += 1;
	            if (seriesCounter === names.length) {
	            	options.series = eval(seriesOptions);
	            	console.log(options)
	                myChart.setOption(options);	                
	            }
	        });
    	});   
    	 
    })

    </script>	
</@override>
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
	                <span class="menu-text">参数分组</span>
	                <b class="arrow icon-angle-down"></b>
	            </a>	
	
	            <ul class="submenu">
				<#list lPs as lp>
					<li>
	                  //  <a href="${base}/group/${lp.id}" class="dropdown-toggle">
	                  <a href="#" class="dropdown-toggle">
	                        <i class="icon-double-angle-right"></i>${lp.id}
	                    	               
	                    </a>
	                </li>
				</#list>
	                
	            </ul>
	        </li>
	
	    </ul><!-- /.nav-list -->
	
	    <div class="sidebar-collapse" id="sidebar-collapse">
	        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
	    </div>	
	</div>
</@override>

<@override name="content_right">
	 <div id="main" style="width: 1200px;height:400px;left:150px"></div>
</@override>	
<@extends name="/secondStyle/baseNew.ftl"/>