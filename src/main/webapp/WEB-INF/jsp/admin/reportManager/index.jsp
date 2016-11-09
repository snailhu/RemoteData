<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="/WEB-INF/jsp/inc/include-easyUI.jsp"></jsp:include>
	<link href="<%=request.getContextPath()%>/static/content/css/default.css" rel="stylesheet" type="text/css"/>
	<script src="<%=request.getContextPath()%>/static/content/js/outlook2.js" type="text/javascript"></script>
  	<script src="<%=request.getContextPath()%>/static/content/jQuery-AjaxFileUpload/jquery.ajaxfileupload.js" type="text/javascript"></script>
	<!-- 时间选择器 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/content/jQueryCalendar/calendar.css">
	<script src="${pageContext.request.contextPath}/static/content/jQueryCalendar/calendar.js"></script> 
	
	    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/content/jeDate/jedate/skin/jedate.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/content/jeDate/jedate/jedate.js"></script>
	       <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/all.css" type="text/css" />
  <style type="text/css">
  .widget-toolbar>a {
    font-size: 36px;
    margin: 0 1px;
    display: inline-block;
    padding: 0;
/*     line-height: 24px; */
}
.form-group {
    margin-bottom: 0px;
}
.form-group>label[class*="col-"] {
    padding-top: 2px;
    margin-bottom: 0px;
}
.breadcrumb {
    margin-top: 10px;
}
  .selftoolbar {
    display: inline-block;
    padding: 0 10px;
    line-height: 37px;
    float: right;
    position: relative;
  }
  </style>
  </head>
  <body>
    <div class="main-content" id="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>
			<ul class="breadcrumb">
				<li>
					<img src="${pageContext.request.contextPath}/static/imgs/DataImport/home.png" style="margin-bottom: 3px;">
					<span>报告管理</span>
				</li>
				<li class="active">定时报告</li>
				<li class="active">${nowSeries}系列-${nowStar}星-${nowParameterTypeName}报告列表</li>
			</ul><!--  .breadcrumb -->
		</div>
		<div class="page-content">
<!-- 			<div class="daohanglancs"> -->
<!-- 					<img -->
<!-- 						src="<%=request.getContextPath()%>/static/imgs/DataImport/home.png"> -->
<!-- 					<span>位置:</span> <span>报告管理></span> <span>报告查看</span> -->
<!-- 			</div> -->
			<!-- /.page-header -->
			<div>
				<div class="col-xs-12 col-sm-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="widget-box">
						<div class="widget-header" id="change-search-box" data-action="collapse">
							<h4>搜索</h4>
							<div class="widget-toolbar">
								<a href="javascript:void(0);" >
									<div hidden="hidden"><i class="icon-chevron-up" hidden="hidden"></i></div>
									<img id="toolimg" src="${pageContext.request.contextPath}/static/imgs/DataImport_manage/xia2.png">
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
											<input class="form-control"  id="form-beginTime"   name="beginTime" type="text" placeholder="请选择开始时间"  >
											<!-- <input type="text" id="form-beginTime" name="beginTime" placeholder="开始时间" class="col-xs-10 col-sm-5" />
											<div id="getBeginTime"></div> -->
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-5 control-label no-padding-right" for="form-endTime"> 结束时间 </label>
										<div class="col-sm-3">
											<input  class="form-control"  id="form-endTime" name="endTime"  type="text" placeholder="请选择结束时间"  >
											<!-- class="datainp" <input type="text" id="form-endTime" name="endTime" placeholder="结束时间" class="col-xs-10 col-sm-5" />
											<div id="getEndTime"></div> -->
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
											  <button type="reset" class="cancelbutton_1">
							                    <i></i>
							                    <span>取消</span>
							                </button>
										</div>
			                       </div>
								</form>
							</div>
						</div>
					</div>
					<!-- PAGE CONTENT ENDS -->
				</div><!-- /.col -->
			</div><!-- /.row -->
			
			<div id="content" region="center" style="overflow: hidden">
				<div id="toolbar" class="datagrid-toolbar" style="height: 28px;">
					<div style="height: 28px;">
<!-- 						<a class="easyui-linkbutton" iconcls="icon-cloud-up" -->
<!-- 							onclick="uploadFS();" plain="true" href="javascript:void(0);" -->
<!-- 							style="float: left;">上传</a> -->
<!-- 						<div class="datagrid-btn-separator"></div> -->
						<a class="easyui-linkbutton" iconcls="icon-cloud-download"
							onclick="downloadFS();" plain="true" href="javascript:void(0);"
							style="float: left;">下载</a>
						<div class="datagrid-btn-separator"></div>
						<a class="easyui-linkbutton" iconcls="icon-remove"
							onclick="deleteFS();" plain="true" href="javascript:void(0);"
							style="float: left;">删除</a>
						<div class="datagrid-btn-separator"></div>
						<a class="easyui-linkbutton" iconcls="icon-undo"
							onclick="fsGrid.datagrid('unselectAll');" plain="true"
							href="javascript:void(0);" style="float: left;">取消选中</a>
						
					<!-- 	<a class="easyui-linkbutton" iconcls="icon-cloud-download"
							onclick="createReport();" plain="true" href="javascript:void(0);"
							style="float: left;">生成</a> -->
							
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<ul class="breadcrumb" id="fileCatalog">
						<li class="active">全部文件</li>
					</ul>
					<table id="fsList" height="400px" border="false">
					</table>
					<form id="upload_file_form" action="admin/file/uploadFile" method="post" enctype="multipart/form-data" style="display:none">
						<input type="hidden" id="file_dirId" name="dirId" />
						<input type="file" id="file_upload" name="file" style="display:none" />
					</form>
				</div>
			</div>
		</div><!-- /.page-content -->
	</div><!-- /.main-content -->
	<script type="text/javascript">
	  var fsGrid;
	  var url
	  var nowSeries = '${nowSeries}';
	  var nowStar = '${nowStar}';
	  var nowDirId = '${nowDirId}';
	  var nowParamType = '${nowParameterTypeValue}';
	  if(nowSeries == ''){
		nowSeries = 'j9';
	  }
	  if(nowStar == ''){
		nowStar =  '02';
	  }
	  if(nowDirId == ''){
		nowDirId = 0;		  
	  }
	  if(nowParamType == ''){
		  nowParamType = 'flywheel';		  
	  }
	  url='<%=request.getContextPath()%>/report/getList/'+nowSeries+'/'+nowStar+'/'+nowParamType+'/'+nowDirId+'/';
	  
	  $(function () {
		  var flag=false;
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
			});
			$("#change-search-box").click();
		  
			$("#form-beginTime").keypress(function(){
			    return false;
			  });
			$("#form-endTime").keypress(function(){
			    return false;
			  });
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
				//minDate:"2014-09-19 00:00:00",//最小日期
				maxDate:jeDate.now(0), //设定最大日期为当前日期
			});
	      fsGrid = $('#fsList').datagrid({
	          url: url,
	          title: '文件列表',
	          rownumbers: true,
	          fitColumns:true,
	          idField: 'id',
	          toolbar: '#toolbar',
	          pageSize: 10,
	          pagination: true,
	          pageList: [10, 20, 30, 40, 50, 60, 70, 80, 90, 100],
	          frozenColumns: [[{
	              title: 'id',
	              field: 'id',
	              width: 50,
	              checkbox: true
	          }]],
	          columns: [[ {
	              field: 'name',
	              title: '文件名',
	              width: 200,
	              formatter: function (value, row, index) {
	            	  if(row.type == 'dir'){
                      	return "<a href=\"javascript:doGetChildDir('" + row.id +  "');\"" + " title='"+row.name+"'>"+row.name+"</a>";	            		  
	            	  }else{
	            		return row.name;
	            	  }
                  }
	          }, {
	              field: 'fileSize',
	              title: '大小',
	              width: 100
	          },  {
	              field: 'createDate',
	              title: '上传时间',
	              width: 150
	          }
	          ]]
	      });
	      $('#btn-search').click(function(){
	    	  var beginTime = $('#form-beginTime').val();
	    	  var endTime = $('#form-endTime').val();
	    	  fsGrid.datagrid('load', {
				  series: nowSeries,
				  star: nowStar,
				  paramType: nowParamType,
				  beginTime: beginTime,
				  endTime: endTime
		        });
		  });
	  });
	
	  function doGetChildDir(dirId){
		  fsGrid.datagrid('unselectAll');
		  nowDirId = dirId;
		  fsGrid.datagrid('load', {
			  series: nowSeries,
			  star: nowStar,
			  paramType: nowParamType,
			  dirId: dirId
	              });
		  var fileCatalog = $('#fileCatalog');
		  fileCatalog.empty();
		  if(dirId != 0){
			  fileCatalog.append("<li><a href=\"javascript:doGetChildDir('0');\" title='全部文件'>全部文件</a></li>");
			  $.post('<%=request.getContextPath()%>/report/getParentCatalog',{ dirId: dirId}, function(data){
				  var jsonData = eval("("+data.obj+")");
				  $.each( jsonData, function(i, n){
					  if(i == dirId){
						  fileCatalog.append("<li class='active'>"+n+"</li>");
					  }else{
						  fileCatalog.append("<li><a href=\"javascript:doGetChildDir('" + i +  "');\"" + " title='"+n+"'>"+n+"</a></li>");
					  }
				   });
			  });
		  }else{
			  fileCatalog.append("<li class='active'>全部文件</li>");
		  }
		 
	  }
	  function uploadFS(){
		  $('#file_upload').click();
		  $('#file_upload').change(function(){	
			  var rows = fsGrid.datagrid('getSelections');
		      if (rows.length > 1) {
		    	  var names = [];
	              for (var i = 0; i < rows.length; i++) {
	                  names.push(rows[i].name);
	              }
	              top.showMsg("提示", '只能选择一个进行上传！您已经选择了【'+names.join(',')+'】'+rows.length+'个');
		     }else {
		    	 var dirId = nowDirId;
		    	 if(rows.length == 1){
		    		 dirId = rows[0].id;
		    	 }
		    	 $('#file_dirId').val(dirId);
			  	$('#upload_file_form').submit();
		     }
		 	});	
	  }
	  function downloadFS(){
		  var ids = [];
		  var rows = fsGrid.datagrid('getSelections');
	      if (rows.length > 0) {
	    	  if (rows.length == 1) {
	    		  if(rows[0].type == 'dir'){
	    			  $.messager.confirm('提示', '确定要下载整个目录吗？', function (r) {
	    	              if (r) {
	    	            	  ids.push(rows[0].id + "/"+rows[0].type);
	    	            	  window.location.href="report/downloads?itemIds="+ids.join(',');
	    	              }
	    	          });
	    		  }else{
	    			  window.location.href="report/download?fileId="+rows[0].id;
	    		  }
	    	  }else{
	              $.messager.confirm('提示', '确定要下载多个文件或目录吗？', function (r) {
    	              if (r) {
    	            	  top.showMsg("提示", "此功能暂时不支持！");
    	            	  for (var i = 0; i < rows.length; i++) {
    	                      ids.push(rows[i].id +"/"+rows[i].type);
    	                  }
    	            	  window.location.href="report/downloads?itemIds="+ids.join(',');
    	              }
    	          });
	    	  }
	     }else {
	    	 top.showMsg("提示", "请选择要下载的文件");
	     }  
	  }
	  function deleteFS() {
	      var ids = [];
	      var rows = fsGrid.datagrid('getSelections');
	      if (rows.length>0) {
	          $.messager.confirm('提示', '确定删除吗？', function (r) {
	              if (r) {
	                  for (var i = 0; i < rows.length; i++) {
	                      ids.push(rows[i].id +"/"+rows[i].type);
	                  }
	                  $.post('<%=request.getContextPath()%>/report/deleteFiles', { "itemIds": ids.join(',')}, function (data) {
	                      if (data.success) {
	                          reloadDataGrid();
	                          top.showMsg('提示', data.msg);
	                      }
	                      else {
	                          top.alertMsg('错误', data.msg+"\n"+(data.obj==null?"":data.obj));
	                      }
	
	                  }, "json");
	              }
	          });
	      }
	      else {
	          top.showMsg("提示", "请选择要删除的记录！");
	
	      }
	  }

		
	  
	  function createReport() {
		  $.get('<%=request.getContextPath()%>/report/createReportTest', {}, function (data) {
              if (data.success) {
                  reloadDataGrid();
                  top.showMsg('提示', "报告生成成功");
              }
              else {
                  top.alertMsg('错误',"报告生成失败");
              }

          }, "json");
	  }
	
	  
	  function reloadDataGrid() {
	      fsGrid.datagrid('unselectAll');
	      fsGrid.datagrid('reload');
	  }
	</script>	
  </body>

</html>
