<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>参数曲线分析</title>
</head>
<body>

	
	<!--Table面板JS-->
	<script src="${pageContext.request.contextPath}/static/js/conditionMonitoring/showGraphic/sidebar-menu.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/conditionMonitoring/showGraphic/bootstrap-tab.js"></script>

<style type="text/css">
.seriesbutton{position:absolute;width:100%;height:50px; margin:0 auto;z-index:1}

.shujufenxibutton {
    /*height: 31px;
    width: 80px;
    color: white;
    //background-color: #4B92DD;
    border-width: 2px;*/
    border: 1px solid #DADADA;
    background-color: #ff9900;
    border-radius: 20px;
    height: 30px;
    line-height: 28px;
    width: 80px;
    font-size: 14px;
    margin-right: 15px;
}
</style>
	
	<div class="main-content">
		<div class="page-content">
		
			<div class="page-header" style="margin: 0px;float: left;">
				<h1>查看曲线</h1>	
			</div>
			
			<div class="col-xs-12">
				<div id="menu"></div>
			</div>
			<!--<div class="row" style="margin:0px !important"></div>-->
				<div class="col-xs-12"><hr/></div>
			
	           <div class="col-xs-12">
	             <ul class="nav nav-tabs" role="tablist">
	               <!--<li class="active"><a href="#Index" role="tab" data-toggle="tab">首页</a></li>-->
	             </ul>
	             <div class="tab-content" style="height:100% !important">
	               <div role="tabpanel" class="tab-pane active" id="Index"></div>
	             </div>
	           </div>
	         
		</div><!-- /.page-content -->		
	</div><!-- /.main-content -->
	<script type="text/javascript">
	    $(function(){   
	    	$('#menu').sidebarMenu({ url: "${base}/DataRemote/getMenus"});                	 
	    })
    </script>
 
	</script>
</body>
</html>
