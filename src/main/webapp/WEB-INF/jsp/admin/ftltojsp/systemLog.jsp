<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>日志管理</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/jqwidgets/styles/jqx.base.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/jqwidgets/styles/jqx.energyblue.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/content/css/default.css"  type="text/css"/>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/all.css" type="text/css" />
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
	.page-header{
		padding:0px !important
	}
	.form-group input,.form-group select{
		width: 240px;
		height:30px;
		line-height:30px;
		text-align:left;
	}
	</style>
<script type="text/javascript">
	$(function(){
		$(".col-sm-6").css({"display":"inline","margin-left":"6.6%"});
		$(".col-sm-1").css("display","inline");		
		
		//左菜单栏
		$("#logmanage-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_54.png");
		$("#sysmanage-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_50.png");
		$("#logmanage-text").css("color","#5d90d6");
		$("#sysmanage-text").css("color", "#5d90d6");
		$("#sysmanageUL").css("display","block");
	})
</script>
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
			<ul class="breadcrumb" style=" margin-top: 10px;">
				<li>
					<img src="${pageContext.request.contextPath}/static/imgs/DataImport/home.png" style="margin-bottom: 3px;">
					<span>系统管理</span>
				</li>
				<li class="active">日志管理</li>
			</ul><!--  .breadcrumb -->
		</div>
		<div class="page-content">
		<div class="row">
		<div>
				<div class="col-xs-12 col-sm-12">
					<!-- PAGE CONTENT BEGINS -->
					<div id="id_widget_search_box" class="widget-box">
						<div class="widget-header" id="change-search-box" data-action="collapse">
							<h4>搜索</h4>
							<div class="widget-toolbar">
								<a href="javascript:void(0);" >
									<div hidden="hidden"><i class="icon-chevron-up" hidden="hidden"></i></div>
									<img id="toolimg" style="margin-top: 3px;"
									src="${pageContext.request.contextPath}/static/imgs/DataImport_manage/xia.png">
								</a>
							</div>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<!-- 搜索form -->
								<form id="fileupload" action="" class="form-horizontal" role="form" >
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-5 control-label no-padding-right" for="form-beginTime"> 开始时间：</label>
										<div class="col-sm-3">
											<input class="form-control"  id="form-beginTime"   name="beginTime" type="text" placeholder="--请选择开始时间--" readonly="true"/>
											<!-- <input type="text" id="form-beginTime" name="beginTime" placeholder="开始时间" class="col-xs-10 col-sm-5" />
											<div id="getBeginTime"></div> -->
										</div>
									</div>
									
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-5 control-label no-padding-right" for="form-endTime"> 结束时间：</label>
										<div class="col-sm-3">
											<input  class="form-control"  id="form-endTime" name="endTime"  type="text" placeholder="--请选择结束时间--" readonly="true"/>
											<div id="getEndTime"></div>
										</div>
									</div>
									
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-5 control-label no-padding-right" for="form-beginTime"> 关键字：</label>
										<div class="col-sm-3">
											<input class="form-control"  id="form-keyWord"   name="keyWord" type="text" placeholder="--请输入关键字--">
										</div>
									</div>
									
									<div class="space-4"></div>
									<div class="form-group">
			                        	<div class="col-sm-6 control-label no-padding-right">
											<button type="button" id="btn-search" class="subbutton_1">
							                    <i></i>
							                    <span>搜索</span>
							                </button>
										</div>
										<div class="col-sm-1 control-label no-padding-right">
											  <button type="reset" id = "btn-cancel" class="cancelbutton_1">
							                    <i></i>
							                    <span>取消</span>
							                </button>
										</div>
									</div>
									
								</form>
							</div>
						</div>
					</div>	
			<div class="col-xs-12"><hr/></div>		
			<div class="col-xs-12">
				<div id="jqxgrid"></div>
			</div>
			</div>
			</div>
		</div>
		</div> <!--/.page-content -->		
	</div><!-- /.main-content -->
 <script type="text/javascript">
    var beginTime ="1900-01-01 00:00:01";
    var endTime = "3000-01-01 11:59:01";
    var keyWord = "all";
    //请求日志URL
    //var logurl  = "${pageContext.request.contextPath}/showSystemLog/"+beginTime+"+"/"+"+endTime+";
    //var logurl  ="${pageContext.request.contextPath}/admin/log/showSystemLog/"+beginTime+"/"+endTime+"/all";
    keyWord=encodeURI(encodeURI(keyWord));
    varlogurl  = "${pageContext.request.contextPath}/admin/log/showSystemLog?beginTime="+beginTime+"&endTime="+endTime+"&keyWord="+keyWord;		  
    
    //刷新日志列表   
	function intlogList(){
		var source =
		{	
			datatype: "json",
			datafields: [
				{ name: 'userName',  type: 'string' },
				{ name: 'operateTime',  type: 'String' },
				{ name: 'loginIp', type: 'string' },
				{ name: 'operateJob', type: 'string' }
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
			  { text: '操作时间', datafield: 'operateTime', width: 200, cellsformat: 'D' },
			//{ text: '退出时间', datafield: 'logOutTime', width: 150, cellsformat: 'F2', cellsalign: 'right' },
			  { text: '登录ip', datafield: 'loginIp', width: 250 },
			  { text: '操作', datafield: 'operateJob', width: 300 }
			//{ text: '角色', datafield: 'role', width: 100 }
			]
		});
	} 
	
	//页面加载后自动执行的函数
	$(function () {
		jeDate({
			dateCell:"#form-beginTime",//直接显示日期层的容器，可以是ID  CLASS
			format:"YYYY-MM-DD hh:mm:ss",//日期格式
			isinitVal:false, //是否初始化时间
			festival:false, //是否显示节日
			isTime:true, //是否开启时间选择
			//minDate:"2014-09-19 00:00:00",//最小日期
			maxDate:jeDate.now(0), //设定最大日期为当前日期
		});
		jeDate({
			dateCell:"#form-endTime",//直接显示日期层的容器，可以是ID  CLASS
			format:"YYYY-MM-DD hh:mm:ss",//日期格式
			isinitVal:false, //是否初始化时间
			festival:false, //是否显示节日
			isTime:true, //是否开启时间选择
			minDate:$('#form-beginTime').val(),//最小日期
			maxDate:jeDate.now(0), //设定最大日期为当前日期
		});
		$("#form-beginTime").keypress(function(){
		 return false;
		});
		$("#form-endTime").keypress(function(){
		  return false;

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
		 //logurl  ="${pageContext.request.contextPath}/admin/log/showSystemLog/"+beginTime+"/"+endTime+"/"+keyWord;
		 logurl  = "${pageContext.request.contextPath}/admin/log/showSystemLog?beginTime="+beginTime+"&endTime="+endTime+"&keyWord="+keyWord;		  
		 intlogList();
		 //$.post("${pageContext.request.contextPath}/admin/systemLog", {'beginTime':"1800-01-01",'endTime':"9999-01-01"},function(data){intlogList()});
		  
		 $('#btn-search').click(function(){
				  beginTime = $('#form-beginTime').val();
				  endTime = $('#form-endTime').val();
				  keyWord = $('#form-keyWord').val();
				  /*$.post(	'${pageContext.request.contextPath}/admin/showSystemLog', 
				  			{
				  				'beginTime' : beginTime,
				  				'endTime' : endTime
				  			},
				  			function(data){intlogList();}
				  );*/			  
		if((beginTime.length==0)|(endTime.length==0))		
		{
			beginTime="1950-01-01 00:00:01";
			endTime="9999-01-01 00:00:01";
		}
		if(keyWord=="")
		{keyWord="all"}
				  //logurl  = "${pageContext.request.contextPath}/admin/log/showSystemLog/"+beginTime+"/"+endTime+"/"+keyWord;
				  //logurl  = "${pageContext.request.contextPath}/admin/log/showSystemLog/"+beginTime+"/"+endTime+"/"+encodeURI(encodeURI(keyWord));	//两次编码
				  keyWord=encodeURI(encodeURI(keyWord));	
				  logurl  = "${pageContext.request.contextPath}/admin/log/showSystemLog?beginTime="+beginTime+"&endTime="+endTime+"&keyWord="+keyWord;		  
				  intlogList();			  			  
			 });
				  
		});    	
 
</script>
</body>
</html>
