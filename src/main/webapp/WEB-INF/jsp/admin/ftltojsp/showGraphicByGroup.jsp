<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>参数曲线分析</title>
<!--Table面板JS-->
<script src="${pageContext.request.contextPath}/static/js/conditionMonitoring/showGraphic/sidebar-menu.js"></script>
<script src="${pageContext.request.contextPath}/static/js/conditionMonitoring/showGraphic/bootstrap-tab.js"></script>

<style type="text/css">
<!--.seriesbutton{position:absolute;width:986px;height:50px; margin:0 auto;z-index:1}-->
.seriesbutton{width:986px;height:50px}
.btngroup{
    	margin-left: 80px;
    	margin-top: 25px;
    }
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
</head>
<body>	
	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>
			<ul class="breadcrumb">
				<br/>
				<li>
					<img src="${pageContext.request.contextPath}/static/imgs/DataImport/home.png" style="margin-bottom: 3px;">
					<span>数据分析</span>
				</li>
				<li class="active">查看曲线</li>
			</ul>
		</div>

		<div class="page-content">
			
			<div  style="margin:0px height:31px !important">
				<div id="menu"></div>
			</div>
			<span> &amp;</span>
			<div class="col-xs-12"><hr/></div>
			<!--<div class="row" style="margin:0px height:31px!important"></div>-->
	           <div class="col-xs-12">
	             <ul class="nav nav-tabs" role="tablist">
	               <!--<li class="active"><a href="#Index" role="tab" data-toggle="tab">首页</a></li>-->
	             </ul>
	             <div class="tab-content" style="height:600px !important">
	               <div role="tabpanel" class="tab-pane active" id="Index"></div>
	             </div>
	           </div>
	         
	    	<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<!-- PAGE CONTENT ENDS -->
				</div>
				<!-- /.col -->
			</div>
		</div><!-- /.page-content -->		
	</div><!-- /.main-content -->
	<script type="text/javascript">
	    $(function(){   
	    	$('#menu').sidebarMenu({ url: "${base}/DataRemote/getMenus"});   
	    	
	    	//左菜单栏
			$("#conditionmonitoring-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_86.png");
			$("#conditionmonitoring-text").css("color","#5d90d6");
	    })
    </script>
</body>
</html>
