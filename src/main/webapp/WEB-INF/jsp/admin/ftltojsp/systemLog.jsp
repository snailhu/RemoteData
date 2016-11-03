<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>日志管理</title>
</head>
<body>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/jqwidgets/styles/jqx.base.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/jqwidgets/styles/jqx.energyblue.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/content/css/default.css"  type="text/css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxdatetimeinput.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxcalendar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxtooltip.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/globalization/globalize.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxdata.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxmenu.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxcheckbox.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxlistbox.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxdropdownlist.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/scripts/demos.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxdatatable.js"></script>
 	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxgrid.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxgrid.pager.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxgrid.sort.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxgrid.filter.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxgrid.columnsresize.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxgrid.selection.js"></script> 

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxbuttons.js"></script>
	
	<!-- 时间选择器 -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/content/jeDate/jedate/skin/jedate.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/content/jeDate/jedate/jedate.js"></script>

	<style>
		.dateStyle{
			float:left;
		}
		.row {
		  margin:0px !important
		}
		.page-header{
			padding:0px !important
		}
		.datainp{
			width: 300px;
			height: 25px;
		}
	</style>

	<div class="main-content">
	    <div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>
			<ul class="breadcrumb" style=" margin-top: 10px;">
				<li>
					<img src="${pageContext.request.contextPath}/static/imgs/DataImport/home.png" style="margin-bottom: 3px;">
					<span>系统管理</span>
				</li>
				<li class="active">系统日志</li>
			</ul><!--  .breadcrumb -->
		</div>
		<div class="page-content">
		<div>
				<div class="col-xs-12 col-sm-12">
					<!-- PAGE CONTENT BEGINS -->
					<div id="id_widget_search_box" class="widget-box">
						<div class="widget-header" id="change-search-box" data-action="collapse">
							<h4>搜索</h4>
							<div class="widget-toolbar">
								<a href="javascript:void(0);" >
									<div hidden="hidden"><i class="icon-chevron-up" hidden="hidden"></i></div>
									<img id="toolimg" src="${pageContext.request.contextPath}/static/imgs/DataImport_manage/xia.png">
								</a>
							</div>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<!-- 搜索form -->
								<form id="fileupload" action="" class="form-horizontal" role="form" >
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-5 control-label no-padding-right" for="form-beginTime"> 开始时间 </label>
										<div class="col-sm-3">
											<input class="form-control"  id="form-beginTime"   name="beginTime" type="text" placeholder="输入开始时间">
											<!-- <input type="text" id="form-beginTime" name="beginTime" placeholder="开始时间" class="col-xs-10 col-sm-5" />
											<div id="getBeginTime"></div> -->
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-5 control-label no-padding-right" for="form-endTime"> 结束时间 </label>
										<div class="col-sm-3">
											<input  class="form-control"  id="form-endTime" name="endTime"  type="text" placeholder="输入结束时间" >
											<!-- class="datainp" <input type="text" id="form-endTime" name="endTime" placeholder="结束时间" class="col-xs-10 col-sm-5" />
											<div id="getEndTime"></div> -->
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
			                           <div class="col-lg-4 col-lg-offset-6">
					                        <button type="button" id="btn-search" class="btn btn-primary start">
							                    <i></i>
							                    <span>搜索</span>
							                </button>
							                <button type="reset" class="btn btn-warning cancel">
							                    <i></i>
							                    <span>取消</span>
							                </button>
			                           </div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			<div class="col-xs-12"><hr/></div>		
			<div class="col-xs-12">
				<div id="jqxgrid"></div>
			</div>
		</div>
		<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<!-- PAGE CONTENT ENDS -->
				</div>
				<!-- /.col -->
		</div>
		</div> <!--/.page-content -->		
	</div><!-- /.main-content -->
 <script type="text/javascript">
    var beginTime ="1800-01-01";
    var endTime = "9999-01-01";
    //请求日志URL
    //var logurl  = "${pageContext.request.contextPath}/showSystemLog/"+beginTime+"+"/"+"+endTime+";
    var logurl  ="${pageContext.request.contextPath}/admin/showSystemLog/"+beginTime+"/"+endTime;
    //刷新日志列表   
	function intlogList(){
		var source =
		{	
			datatype: "json",
			datafields: [
				{ name: 'userName',  type: 'string' },
				{ name: 'loginTime',  type: 'String' },
				{ name: 'logOutTime', type: 'string' },
				{ name: 'loginIp', type: 'string' },
				{ name: 'operateJob', type: 'string' }
			  //  { name: 'role', type: 'string' }
			],     
			id: 'UserId',
			url: logurl,
			pager: function (pagenum, pagesize, oldpagenum) {
				// callback called when a page or page size is changed.
			}
		};
		var dataAdapter = new $.jqx.dataAdapter(source);
		$("#jqxgrid").jqxGrid(
		{
			width: 900,
			height: 600,
			source: dataAdapter,
			selectionmode: 'multiplerowsextended',
			theme: 'energyblue',
			sortable: true,
			pageable: true,
			autoheight: true,
			columnsresize: true,
			pagermode: 'simple',
			columns: [
			  { text: '用户名', datafield: 'userName', width: 150 },
			  { text: '登录时间', datafield: 'loginTime', width: 150, cellsformat: 'D' },
			  { text: '退出时间', datafield: 'logOutTime', width: 150, cellsformat: 'F2', cellsalign: 'right' },
			  { text: '登录ip', datafield: 'loginIp', width: 250 },
			  { text: '操作', datafield: 'operateJob', width: 200 }
			//{ text: '角色', datafield: 'role', width: 100 }
			]
		});
	} 
	
	//页面加载后自动执行的函数
	$(function () {
		jeDate({
			dateCell:"#form-beginTime",//直接显示日期层的容器，可以是ID  CLASS
			format:"YYYY-MM-DD",//日期格式
			isinitVal:false, //是否初始化时间
			festival:false, //是否显示节日
			isTime:false, //是否开启时间选择
			//minDate:"2014-09-19 00:00:00",//最小日期
			maxDate:jeDate.now(0), //设定最大日期为当前日期
		});
		jeDate({
			dateCell:"#form-endTime",//直接显示日期层的容器，可以是ID  CLASS
			format:"YYYY-MM-DD",//日期格式
			isinitVal:false, //是否初始化时间
			festival:false, //是否显示节日
			isTime:false, //是否开启时间选择
			//minDate:"2014-09-19 00:00:00",//最小日期
			maxDate:jeDate.now(0), //设定最大日期为当前日期
		});
		
		//修改搜索框图标
		var  flag=false;
		$("#change-search-box").click(function(){		
		if(flag){
			
			$("#toolimg").attr("src","${pageContext.request.contextPath}/static/imgs/DataImport_manage/xia.png")
			$(".widget-body").slideUp("slow");
			flag=false;
		}else{
			$("#toolimg").attr("src","${pageContext.request.contextPath}/static/imgs/DataImport_manage/xia2.png")
    		$(".widget-body").slideDown("slow");
			flag=true;
		}
		
		})
		
		 $('#change-search-box').click();
		 logurl  ="${pageContext.request.contextPath}/admin/showSystemLog/"+beginTime+"/"+endTime;
		 intlogList();
		 //$.post("${pageContext.request.contextPath}/admin/systemLog", {'beginTime':"1800-01-01",'endTime':"9999-01-01"},function(data){intlogList()});
		  
		 $('#btn-search').click(function(){
				  beginTime = $('#form-beginTime').val();
				  endTime = $('#form-endTime').val();
				  /*$.post(	'${pageContext.request.contextPath}/admin/showSystemLog', 
				  			{
				  				'beginTime' : beginTime,
				  				'endTime' : endTime
				  			},
				  			function(data){intlogList();}
				  );*/
				  logurl  = "${pageContext.request.contextPath}/admin/showSystemLog/"+beginTime+"/"+endTime;
				  intlogList();			  			  
			 });
				  
		});    	
 
</script>
</body>
</html>
