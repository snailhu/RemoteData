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
		<!-- 弹出框 -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.css">
	<script src="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.min.js"></script>
	
	<link href="<%=request.getContextPath()%>/static/content/css/default.css" rel="stylesheet" type="text/css"/>
	<script src="<%=request.getContextPath()%>/static/content/js/outlook2.js" type="text/javascript"></script>
  	<script src="<%=request.getContextPath()%>/static/content/jQuery-AjaxFileUpload/jquery.ajaxfileupload.js" type="text/javascript"></script>
	
	<!-- 时间选择器 -->
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
.form-horizontal .form-group {
    margin-right: 0px;
    margin-left: 100px;
}
.mustchoose {
    padding-top: 5px;
    padding-left: 5px;
    color: red;
}
.form-group input{
	width: 240px;
	height:30px;
	line-height:30px;
	text-align:left;
}
.combo-arrow{
	background:url('${pageContext.request.contextPath}/static/imgs/arrow.png') no-repeat right center;
	width:238px;
}
.combo-arrow:hover {
    background-color: transparent;
}
</style>
  <script type="text/javascript">
  $(function(){ 
		//修改页面缩放，界面显示不正常
		$(".col-lg-4").css("margin-left","25%");		
		
		//左菜单栏
		$("#fileview-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_46.png");
		$("#filemanage-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_26.png");
		$("#fileview-text").css("color", "#5d90d6");
		$("#filemange-text").css("color", "#5d90d6");
		$("#fileviewUL").css("display", "block");
		$("#filemanageUL").css("display", "block");

	  jeDate({
			dateCell:"#search-beginTime",//直接显示日期层的容器，可以是ID  CLASS
			format:"YYYY-MM-DD",//日期格式
			isinitVal:false, //是否初始化时间
			festival:false, //是否显示节日
			isTime:false, //是否开启时间选择
			//minDate:"2014-09-19 00:00:00",//最小日期
			maxDate:jeDate.now(0), //设定最大日期为当前日期
		});
		jeDate({
			dateCell:"#search-endTime",//直接显示日期层的容器，可以是ID  CLASS
			format:"YYYY-MM-DD",//日期格式
			isinitVal:false, //是否初始化时间
			festival:false, //是否显示节日
			isTime:false, //是否开启时间选择
			//minDate:"2014-09-19 00:00:00",//最小日期
			maxDate:jeDate.now(0), //设定最大日期为当前日期
		});
		$("#search-beginTime").keypress(function(){
		  return false;
		});
		$("#search-endTime").keypress(function(){
		  return false;
		});
	//修改搜索框图标
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
// 	$("#change-search-box").click();
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
			<ul class="breadcrumb" style="margin-top: 10px;">
				<li>
					<img src="${pageContext.request.contextPath}/static/imgs/DataImport/home.png" style="margin-bottom: 3px;">
					<span>文件管理</span>
				</li>
				<li class="active">文件查看</li>
			</ul><!--  .breadcrumb -->
		</div>
		<div class="page-content">
<!-- 			<div class="page-header" style="padding-bottom: 10px; /**margin: -5px 0px 5px;*/"> -->
<!-- 				<h1>${nowSeries}星系-${nowStar}星-${nowParameterTypeName}文件管理</h1> -->
<!-- 			</div> -->
			
			<!-- /.page-header -->
			<div >
				<div class="col-xs-12 col-sm-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="widget-box">
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
									<div class="space-1"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="search-series"> 星系：</label>
										<div class="col-sm-9">
											<input type="text" id="search-series" name="series" placeholder="星系" class="col-xs-10 col-sm-5" style="height:30px;"/>																					
											<label class="mustchoose">*</label>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="search-star"> 星号：</label>
										<div class="col-sm-9">
											<input type="text" id="search-star" name="star" placeholder="星号" class="col-xs-10 col-sm-5" style="height:30px;"/>
											<label class="mustchoose">*</label>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="search-paramType"> 设备：</label>
										<div class="col-sm-9">
											<input type="text" id="search-paramType" name="paramType" placeholder="设备" class="col-xs-10 col-sm-5" style="height:30px;"/>
											<label class="mustchoose">*</label>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="search-beginTime"> 开始时间：</label>
										<div class="col-sm-9">
										
											<input type="text" id="search-beginTime" name="beginTime" placeholder="--请选择开始时间--" class="col-xs-10 col-sm-5"  readonly="true"/>

											<div id="getBeginTime"></div>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="search-endTime"> 结束时间：</label>
										<div class="col-sm-9">
											<input type="text" id="search-endTime" name="endTime" placeholder="--请选择结束时间--" class="col-xs-10 col-sm-5"  readonly="true"/>
											<div id="getEndTime"></div>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" > 文件类型：</label>
										<div class="col-sm-9">
											<label>
												<input name="form-fileType-checkbox" type="checkbox" value="dat" class="ace" checked="checked"/>
												<span class="lbl"> *.dat</span>
											</label>
											&nbsp;&nbsp;
											<label>
												<input name="form-fileType-checkbox" type="checkbox" value="csv" class="ace" checked="checked"/>
												<span class="lbl"> *.csv</span>
											</label>
										</div>
									</div>
									<div class="space-1"></div>
									<div class="form-group">
<!-- 			                           <div class="col-lg-4 col-lg-offset-6"> -->
<!-- 					                        <button type="button" id="btn-search" class="btn btn-primary start"> -->
<!-- 							                    <i></i> -->
<!-- 							                    <span>搜索</span> -->
<!-- 							                </button> -->
<!-- 							                <button type="reset" class="btn btn-warning cancel"> -->
<!-- 							                    <i></i> -->
<!-- 							                    <span>取消</span> -->
<!-- 							                </button> -->
<!-- 			                           </div> -->
			                           <div class="col-lg-4 col-lg-offset-4">
											<button type="button" id="btn-search" class="subbutton_1">
												<i></i> <span>搜索</span>
											</button>
											<button type="reset" id="btn-reset" class="cancelbutton_1">
												<i></i> <span>取消</span>
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
						<button class="easyui-linkbutton" iconcls="icon-cloud-download"
							onclick="downloadFS();" plain="true"
							style="float: left;">下载</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-remove"
							onclick="deleteFS();" plain="true"
							style="float: left;">删除</button>
						<div class="datagrid-btn-separator"></div>
						<button class="easyui-linkbutton" iconcls="icon-undo"
							onclick="fsGrid.datagrid('unselectAll');" plain="true"
							style="float: left;">取消选中</button>
					</div>
				</div>
			</div>

			<div class="row" id="div-fsList">
				<div class="col-xs-12 col-sm-12">
					<ul class="breadcrumb" id="fileCatalog">
						<li class="active">全部文件</li>
					</ul>
					<table id="fsList" height="450px" border="false">
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
	  var url;
	  $(function () {
		  url='<%=request.getContextPath()%>/admin/file/getList';
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
	              width: 80,
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
	              width: 50
	          },  {
	              field: 'createDate',
	              title: '上传时间',
	              width: 100
	          }
	          ]]
	      });
		  
		  var nowDirId = '';
	      //隐藏文件列表
	      $("#div-fsList").hide();
	      $('#btn-search').click(function(){
	      	  $('#fileCatalog').empty();
	    	  var nowSeries = $("#search-series").combobox('getValue');
	    	  var nowStar = $("#search-star").combobox('getValue');
	    	  var nowParamType = $("#search-paramType").combobox('getValue');
			  if (nowSeries == "" || nowSeries.length == 0) {
				top.alertMsg('提示', '请选择星系！');
				return;
			  }
			  if (nowStar == "" || nowStar.length == 0) {
				top.alertMsg('提示', '请选择星号！');
				return;
			  }
			  if (nowParamType == "" || nowParamType.length == 0) {
				top.alertMsg('提示', '请选择设备！');
				return;
			  }
	    	  //显示文件列表
	    	  $("#div-fsList").show();
	    	  var beginTime = $('#search-beginTime').val();
	    	  var endTime = $('#search-endTime').val();
	    	  var fileTypes = [];
	    	  $("[name='form-fileType-checkbox']:checked").each(function(){
	    		  fileTypes.push($(this).val());
	    	  });
	    	  fsGrid.datagrid('load', {
				  series: nowSeries,
				  star: nowStar,
				  paramType: nowParamType,
				  dirId: nowDirId,
				  beginTime: beginTime,
				  endTime: endTime,
				  fileTypes:fileTypes.join(',')
		        });
		  });
		$("#search-series").combobox({
		    url:'${pageContext.request.contextPath}/admin/series/getSeriesComboData',
		    valueField:'value',
		    textField:'text',
		    onSelect: function(node){
		    	$("#search-star").combobox({
				    url:'${pageContext.request.contextPath}/admin/star/getStarComboData?seriesCode='+node.value,
				    valueField:'value',
				    textField:'text',
				    onSelect: function(node){
				    	//alert(node.value);
				    }
				});
		    $(".combo-arrow").css("width","238px");	
		    }
		});
		$("#search-star").combobox({
		    url:'${pageContext.request.contextPath}/admin/star/getStarComboData',
		    valueField:'value',
		    textField:'text',
		    onSelect: function(node){
		    	//alert(node.value);
		    }
		});
		$("#search-paramType").combobox({
		    url:'${pageContext.request.contextPath}/admin/device/getDeviceTypeComboData',
		    valueField:'value',
		    textField:'text',
		    onSelect: function(node){
		    	//alert(node.value);
		    }
		    
		});
	  });
	
	  function doGetChildDir(dirId){
		  var nowSeries = $("#search-series").combobox('getValue');
    	  var nowStar = $("#search-star").combobox('getValue');
		  var nowParamType = $("#search-paramType").combobox('getValue');
    	  
		  fsGrid.datagrid('unselectAll');
		  nowDirId = dirId;
		  var fileTypes = [];
		  $("[name='form-fileType-checkbox']:checked").each(function(){
    		  fileTypes.push($(this).val());
    	  });
		  fsGrid.datagrid('load', {
			  series: nowSeries,
			  star: nowStar,
			  paramType: nowParamType,
			  dirId: dirId,
			  fileTypes:fileTypes.join(',')
	              });
		  var fileCatalog = $('#fileCatalog');
		  fileCatalog.empty();
		  if(dirId != 0){
			  fileCatalog.append("<li><a href=\"javascript:doGetChildDir('0');\" title='全部文件'>全部文件</a></li>");
			  $.post('<%=request.getContextPath()%>/admin/file/getParentCatalog',{ dirId: dirId}, function(data){
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
	    	            	  window.location.href="admin/file/downloads?itemIds="+ids.join(',');
	    	              }
	    	          });
	    		  }else{
	    			  window.location.href="admin/file/download?fileId="+rows[0].id;
	    		  }
	    	  }else{
	              $.messager.confirm('提示', '确定要下载多个文件或目录吗？', function (r) {
    	              if (r) {
    	            	  top.showMsg("提示", "请选择单个文件或目录下载！");
//     	            	  for (var i = 0; i < rows.length; i++) {
//     	                      ids.push(rows[i].id +"/"+rows[i].type);
//     	                  }
//     	            	  window.location.href="admin/file/downloads?itemIds="+ids.join(',');
    	              }
    	          });
	    	  }
	     }else {
	    	 top.showMsg("提示", "请选择要下载的文件！");
	     }  
	  }
	  function deleteFS() {
	      var ids = [];
	      var names = [];
	      var rows = fsGrid.datagrid('getSelections');
	      if (rows.length>0) {
// 	          $.messager.confirm('提示', '确定删除吗？', function (r) {
// 	              if (r) {
// 	                  for (var i = 0; i < rows.length; i++) {
// 	                      ids.push(rows[i].id +"/"+rows[i].type);
// 	                  }
// 	                  $.post('${pageContext.request.contextPath}/admin/file/deleteFiles', { "itemIds": ids.join(',')}, function (data) {
// 	                      if (data.success) {
// 	                          reloadDataGrid();
// 	                          top.showMsg('提示', data.msg);
// 	                      }
// 	                      else {
// 	                          top.alertMsg('错误', data.msg+"\n"+(data.obj==null?"":data.obj));
// 	                      }
	
// 	                  }, "json");
// 	              }
// 	          });

			  if(rows.length > 3){
					names.push(rows[0].name);
					names.push("...");
					names.push(rows[rows.length-1].name);
				}else{
					for ( var i = 0; i < rows.length; i++) {
						names.push(rows[i].name);
					}
				}
	          swal({
					title : "你是否确定删除？",
					text : names.join(','),
					type : "warning",
					showCancelButton : true,
					confirmButtonColor : "#DD6B55",
					confirmButtonText : "删除",
					cancelButtonText : "取消",
					closeOnConfirm : false,
//					closeOnCancel : false
				},
				function(isConfirm) {
					if (isConfirm) {
						for (var i = 0; i < rows.length; i++) {
							  ids.push(rows[i].id +"/"+rows[i].type);
						  }
						$.ajax({
							url : '${pageContext.request.contextPath}/admin/file/deleteFiles',
							data : {
								itemIds : ids.join(',')
							},
							cache : false,
							dataType : "json",
							success : function(data) {
								if (data.success) {
									swal("删除成功","","success");
									reloadDataGrid();
								} else {
									swal("删除失败", data.msg,"error");
								}
							}
						});
					} 
//					else {
//						swal("取消删除", "","error");
//					}
				});
	      }
	      else {
	          top.showMsg("提示", "请选择要删除的记录！");
	
	      }
	  }
	
	  function reloadDataGrid() {
	      fsGrid.datagrid('unselectAll');
	      fsGrid.datagrid('reload');
	  }
	  
$(function(){
	
	  $(".combo-arrow").css("width","238px");
	  
})
	</script>	
  </body>

</html>
