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
	<!-- 弹出框 -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.css">
	<script src="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.min.js"></script>
	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/content/jeDate/jedate/skin/jedate.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/content/jeDate/jedate/jedate.js"></script>
	       <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/all.css" type="text/css" />
	  <script type="text/javascript" src="<%=request.getContextPath()%>/static/select2/select2.full.min.js"></script>
	  	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/select2/select2.min.css" type="text/css" />     
	       
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
  
  .sweet-alert h2 {
	color: rgb(87, 87, 87);
	font-size: 30px;
	text-align: center;
	font-weight: 600;
	text-transform: none;
	position: relative;
	line-height: 40px;
	display: block;
	margin: 25px 0px;
	padding: 0px;
}

.sweet-alert p {
	color: rgb(121, 121, 121);
	font-size: 16px;
	font-weight: 300;
	position: relative;
	text-align: inherit;
	float: none;
	line-height: normal;
	margin: 0px;
	padding: 0px;
}

.sweet-alert .sa-error-container {
	background-color: rgb(241, 241, 241);
	margin-left: -17px;
	margin-right: -17px;
	max-height: 0px;
	overflow: hidden;
	padding: 0px 10px;
	transition: padding 0.15s, max-height 0.15s;
}

.sweet-alert button.cancel {
	background-color: rgb(193, 193, 193);
}

.sweet-alert button {
	font-size: 14px;
	width: 100px;
	height: 32px;
	border-width: 0;
	margin-right: 20px;
}

.sweet-alert .sa-confirm-button-container {
	display: inline-block;
	position: relative;
	/*     padding-left: 20px; */
}
  .icon-remove {
	background: url('') no-repeat center center;
}

.icon-edit {
	background: url('') no-repeat center center;
}

.glyphicon {
	position: relative;
	top: -23px;
	padding-right: 10px;
	display: inline-block;
	font-family: 'Glyphicons Halflings';
	-webkit-font-smoothing: antialiased;
	font-style: normal;
	font-weight: normal;
	line-height: 1;
	float: right;
}

.help-block {
	display: block;
	margin-top: 10px;
	margin-bottom: 0px;
	color: #737373;
}
.sa-button-container{
	margin-top: 30px;
	text-align: center;
}
  </style>
  <script type="text/javascript">
	$(function(){
		//左菜单栏
		$("#reportview-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_10.png");
		$("#reportmanage-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_05.png");
		$("#${nowSeries}report-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_14.png");
		$("#${nowSeries}${nowStar}${nowParameterTypeValue}report-text").css("color", "#5d90d6");
		$("#reportview-text").css("color", "#5d90d6");
		$("#reportmanage-text").css("color", "#5d90d6");
		$("#${nowSeries}report-text").css("color", "#5d90d6");
		$("#${nowSeries}${nowStar}report-text").css("color", "#5d90d6");
		$("#${nowSeries}${nowStar}reportUL").css("display","block");
		$("#${nowSeries}reportUL").css("display", "block");
		$("#reportviewUL").css("display", "block");
		$("#reportmanageUL").css("display", "block");
	})
</script>
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
				<li class="active titleReport">${nowSeries}系列-${nowStar}星-${nowParameterTypeName}报告列表</li>
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
										<label class="col-sm-5 control-label no-padding-right" for="form-series">星系：</label>
										<div class="col-sm-3"  >
										<select name="series"  id="form-series" class="form-control " >
										<option value="">--请选择--</option>
				                       </select>
									</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-5 control-label no-padding-right" for="form-star">星号：</label>
										<div class="col-sm-3">
												<select name="star"  id="form-star" class="form-control " >
												<option value="">--请选择--</option>
				                       			</select>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-5 control-label no-padding-right" for="form-partsType">设备：</label>
										<div class="col-sm-3">
											<select name="partsType"  id="form-partsType" class="form-control " >
				                           		<option selected="selected" value="">--请选择--</option>
				                       		</select>
										</div>
									</div>
								
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
						
					<!-- <a class="easyui-linkbutton" iconcls="icon-cloud-download"
							onclick="createReport();" plain="true" href="javascript:void(0);"
							style="float: left;">生成</a>  -->
							
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
	  var nowSeries;
	  var nowStar;
	  var nowDirId;
	  var nowParamType;
	  var activeUser;
	  url='<%=request.getContextPath()%>/report/getList';
	  
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
	    	  var Qseries =  $('#form-series').val();
			  var Qstar = $('#form-star').val();
			  var QpartsType = $('#form-partsType').val();
	    	  var beginTime = $('#form-beginTime').val();
	    	  var endTime = $('#form-endTime').val();
	    	   nowSeries = Qseries;
	    	   nowStar = Qstar;
	    	   nowParamType = QpartsType;
	    	  fsGrid.datagrid('load', {
				  series: Qseries,
				  star: Qstar,
				  paramType: QpartsType,
				  beginTime: beginTime,
				  endTime: endTime
		     });
		  });

			if(activeUser != ''){
					var permissionItemsJSON = '${activeUser.permissionItemsJSON}';
					var map = $.parseJSON(permissionItemsJSON); 
					 $('#form-partsType').find("option").remove();
	  			      $('#form-partsType').append("<option value=''>--请选择--</option>"); 
					if(map.flywheel == 'flywheel'){
						$("#form-partsType").append(" <option value = 'flywheel'>飞轮</option>"); 
					}
					if(map.top == 'top'){
						$("#form-partsType").append(" <option value = 'top'>陀螺</option>"); 
					}
				}

	            $.get('<%=request.getContextPath()%>/starParam/getSeriesList', {}, function (res) {
	  			  if(res.result == "true") {
	  			      $('#form-series').find("option").remove();
	  			      $('#form-series').append("<option value=''>--请选择--</option>"); 
	              	  $.each(res.data.data ,function(){
	  						$('#form-series').append("<option value='"+ this.code+"'>"+ this.name +"</option>"); 
	  					});
	              		  var seriesId = $('#form-series').val();
	              			$.get('<%=request.getContextPath()%>/starParam/getStarList', {'seriesId':seriesId},  function (res) {
	        					  if(res.result == "true") {
	        						  $('#form-star').find("option").remove();
	        						  $('#form-star').append("<option value=''>--请选择--</option>"); 
	        		            	  $.each(res.data.data ,function(){
	        								$('#form-star').append("<option value='"+ this.code+"'>"+ this.name +"</option>"); 
	        							});
	        		              	}
	        		          });	
	                }
	            });
	  		$("#form-series").change(function(){
	  			 	var seriesId = $('#form-series').val();	
	  			 	  $.get('<%=request.getContextPath()%>/starParam/getStarList', {'seriesId':seriesId},  function (res) {
	  					  if(res.result == "true") {
	  						  $('#form-star').find("option").remove();
	  						  $('#form-star').append("<option value=''>--请选择--</option>"); 
	  		            	  $.each(res.data.data ,function(){
	  								$('#form-star').append("<option value='"+ this.code+"'>"+ this.name +"</option>"); 
	  							});
	  		              }
	  		          });	
	  		});
	  });
	  $('#btn-cancel').click(function(){
		      $('#form-star').find("option").remove();
			  $('#form-star').append("<option value=''>--请选择--</option>"); 
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
	  
		//删除用户
		function deleteFS() {
			 var ids = [];
		      var rows = fsGrid.datagrid('getSelections');
		      if (rows.length>0) {
				swal({
					title : "你是否确定删除?",
					type : "warning",
					showCancelButton : true,
					confirmButtonColor : "#DD6B55",
					confirmButtonText : "删除",
					cancelButtonText : "取消",
					closeOnConfirm : false,
					closeOnCancel : false
				},
				function(isConfirm) {
					if (isConfirm) {
						 for (var i = 0; i < rows.length; i++) {
		                      ids.push(rows[i].id +"/"+rows[i].type);
		                  }
						$.ajax({
							url : '${pageContext.request.contextPath}/report/deleteFiles',
							data : {
								 "itemIds": ids.join(',')
							},
							cache : false,
							dataType : "json",
							success : function(data) {
								if (data.success) {
									swal("删除成功!","","success");
									reloadDataGrid();
								} else {
									swal("删除失败", data.obj,"error");
								}
							}
						});
					} else {
						swal("取消删除", "","error");
					}
				});
			} else {
				top.showMsg("提示", "请选择要删除的报告");
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
