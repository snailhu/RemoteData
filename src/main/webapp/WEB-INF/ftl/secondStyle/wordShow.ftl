<@override name="content_left">
	<div class="sidebar" id="sidebar">		    
	    <ul class="nav nav-list">
	    	<li>
				<a href="javascript:void(0);" onclick="$('#SatelliteComponents').html('飞轮');">
					<i class="glyphicon glyphicon-certificate"></i>
					<span class="menu-text"> 飞轮 </span>
				</a>
			</li>
			
			<li>
				<a href="javascript:void(0);" onclick="$('#SatelliteComponents').html('陀螺');">
					<i class="glyphicon glyphicon-certificate"></i>
					<span class="menu-text"> 陀螺 </span>
				</a>
			</li>
	    </ul>
	    	    	
	</div>
</@override>
<@override name="content_right">	
	<link rel="stylesheet" href="${base}/static/jqwidgets/styles/jqx.base.css" type="text/css" />
	<link rel="stylesheet" href="${base}/static/jqwidgets/styles/jqx.energyblue.css" type="text/css" />
	<link rel="stylesheet" href="${base}/static/content/css/default.css"  type="text/css"/>	
    
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxdatetimeinput.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxcalendar.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxtooltip.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/globalization/globalize.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxdata.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxmenu.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxcheckbox.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxlistbox.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxdropdownlist.js"></script>
 
    <script type="text/javascript" src="${base}/static/scripts/demos.js"></script>  
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxdatatable.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxtreegrid.js"></script>     

    <script type="text/javascript" src="${base}/static/jqwidgets/jqxbuttons.js"></script>

	<link type="text/css" rel="stylesheet" href="${base}/static/content/jeDate/jedate/skin/jedate.css">
    <script type="text/javascript" src="${base}/static/content/jeDate/jedate/jedate.js"></script>
   			        
	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
			</script>
			<ul class="breadcrumb">
				<li>					
					<a href="${base}/conditionMonitoring"><i class="icon-home home-icon"></i></a>
					<span class="menu-text">${nowSeries}</span>
				</li>
	
				<li>
					<a href="javascript:void(0);"  onclick="this.setAttribute('disabled','disabled')"></a>
					<span class="menu-text">${nowStar}</span>
				</li>
				
				<li class="active"  value="">
					<span class="menu-text" id="SatelliteComponents"></span>					
				</li>
			</ul><!-- .breadcrumb -->	
		</div>
			
		<div class="page-content">
			${content}
		</div>
	</div>
</@override>	
<@extends name="/secondStyle/contentBase.ftl"/>
