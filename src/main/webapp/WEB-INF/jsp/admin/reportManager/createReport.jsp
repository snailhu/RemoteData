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
    
    <script  src="<%=request.getContextPath()%>/static/showLoading/js/jquery.showLoading.min.js"></script>
      <link rel="stylesheet"  href="<%=request.getContextPath()%>/static/showLoading/css/showLoading.css" />
<%--           <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/all.css" type="text/css" /> --%>
    
<style type="text/css">
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
	background-color: rgb(140, 212, 245);
	color: white;
	box-shadow: none;
	font-size: 17px;
	font-weight: 500;
	cursor: pointer;
	border: none;
	border-radius: 5px;
	padding: 10px 32px;
	margin: 26px 30px 0px;
	/*     width: 150px; */
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
.form-group input{cursor:pointer;}
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
		$("#reportcreate-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_18.png");
		$("#reportmanage-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_05.png");
		$("#reportcreate-text").css("color","#5d90d6");
		$("#reportmanage-text").css("color", "#5d90d6");
		$("#reportmanageUL").css("display","block");
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
					<span>报告管理</span>
				</li>
				<li class="active">报告生成</li>
			</ul><!--  .breadcrumb -->
		</div>
		<div class="page-content">
<!-- 			<div class="daohanglancs"> -->
<!-- 				<img -->
<!-- 					src="<%=request.getContextPath()%>/static/imgs/DataImport/home.png"> -->
<!-- 				<span>位置:</span> <span>报告管理></span> <span>实时报告下载</span> -->
<!-- 			</div> -->
			<div >
				<div class="col-xs-12 col-sm-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="widget-box" id = "downloadReport">
						<div class="widget-header">
							<h4>参数</h4>
							<!-- <div class="widget-toolbar">
								<a href="javascript:void(0);" >
									<i class="icon-chevron-up"></i>
								</a>
							</div> -->
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<!-- 搜索form -->
								<form id="form" action="" class="form-horizontal" role="form" >
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-5 control-label no-padding-right" for="form-series">星系：</label>
										<div class="col-sm-3"  >
										<select name="series"  id="form-series" class="form-control " >
											<option selected="selected" value="">--请选择--</option>
				                       </select>
									</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-5 control-label no-padding-right" for="form-star">星号：</label>
										<div class="col-sm-3">
												<select name="star"  id="form-star" class="form-control " >
													<option selected="selected" value="">--请选择--</option>
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
										<label class="col-sm-5 control-label no-padding-right" for="form-beginTime"> 开始日期： </label>
										<div class="col-sm-3">
											<input type="text" id="form-beginTime" name="beginTime" 
											placeholder="--请选择开始日期--" class="form-control" readonly="true"/>
											<div id="getBeginTime"></div>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-5 control-label no-padding-right" for="form-endTime"> 结束日期： </label>
										<div class="col-sm-3">
											<input type="text" id="form-endTime" name="endTime" 
											placeholder="--请选择结束日期--" class="form-control" readonly="true"/>
											<div id="getEndTime"></div>
										</div>
									</div>
									
									<div class="space-12"></div>
									<div class="form-group" style="margin-top:-15px;">
										<div class="col-sm-6 control-label no-padding-right">
											<button type="button" id="btn-downLoad" class="subbutton_1">
							                    <i></i>
							                    <span>下载</span>
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
		</div><!-- /.page-content -->
	</div><!-- /.main-content -->
<script type="text/javascript">
	var activeUser = '${activeUser}';
$(function(){
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
		 $("#form-beginTime").keypress(function(){
			  return false;
		});
		$("#form-endTime").keypress(function(){
			   return false;
		});

	$.get('<%=request.getContextPath()%>/admin/device/getDeviceTypeList', {}, function (data) {
	 	 	if(data) {
	 	 		$('#form-partsType').find("option").remove();
 			    $('#form-partsType').append("<option value=''>--请选择--</option>"); 
          	  	$.each(data ,function(){
	          	  	$("#form-partsType").append("<option value = '"+ this.deviceCode+"'>"+ this.deviceName+"</option>"); 
				});
            }
        });


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
	              else {
	            	  top.showMsg('提示', res.msg);
	              }
	          });	
	});
});


		function dateDiff(date1, date2) {
		    var type1 = typeof date1, type2 = typeof date2;
		    if (type1 == 'string')
		        date1 = stringToTime(date1);
		    else if (date1.getTime)
		        date1 = date1.getTime();
		    if (type2 == 'string')
		        date2 = stringToTime(date2);
		    else if (date2.getTime)
		        date2 = date2.getTime();
		    //alert((date1 - date2) / (1000*60*60)); 
		    return (date1 - date2) / (1000 * 60 * 60 * 24); //结果是小时 
		}
		//字符串转成Time(dateDiff)所需方法 
		function stringToTime(string) {
		    var f = string.split(' ', 2);
		    var d = (f[0] ? f[0] : '').split('-', 3);
		    var t = (f[1] ? f[1] : '').split(':', 3);
		    return (new Date(
		   parseInt(d[0], 10) || null,
		   (parseInt(d[1], 10) || 1) - 1,
		    parseInt(d[2], 10) || null,
		    parseInt(t[0], 10) || null,
		    parseInt(t[1], 10) || null,
		    parseInt(t[2], 10) || null
		    )).getTime();
		}


	$('#btn-downLoad').click(function(){
			var Qseries =  $('#form-series').val();
			var Qstar = $('#form-star').val();
			var QpartsType = $('#form-partsType').val();
			var QbeginTime = $("#form-beginTime").val();
			var QendTime = $("#form-endTime").val();
			if(Qseries == '') {
				  top.showMsg('提示', "星系不能为空");
				  return false;
			}
			if(Qstar == '') {
				  top.showMsg('提示', "星号不能为空");
				  return false;
			}
			if(QpartsType == '') {
				  top.showMsg('提示', "设备不能为空");
				  return false;
			}
			if(QbeginTime == '') {
				  top.showMsg('提示', "开始日期不能为空");
				  return false;
			}
			if(QendTime == '') {
				  top.showMsg('提示', "结束日期不能为空");
				  return false;
			}
			var t = dateDiff(QendTime, QbeginTime);
			if(t<=0) {
				 top.showMsg('提示', "结束日期需大于开始日期");
				  return false;
			}
			if(t>6) {
				 top.showMsg('提示', "日期区间需小于一周");
				  return false;
			}
			
         	var data = {'seriesId':Qseries,'starId':Qstar,'partsType':QpartsType,'beginTime':QbeginTime,'endTime':QendTime};
        	$("#downloadReport").showLoading(); 
         	 $.get('<%=request.getContextPath()%>/report/createReport', data ,  function (res) {
				  if(res.result == "true") {
					  $("#downloadReport").hideLoading();
					 	var docPath = res.data.docPath;
					 	var filename = res.data.filename;
					 	var docURI = "report/downloadReport?docPath="+docPath+"&filename="+filename;
					 	console.log(filename);
					 	console.log(docPath);
						console.log(docURI);
						docURI = encodeURI(encodeURI(docURI));
         				window.location.href=docURI;
	              }
	              else {
	            	  $("#downloadReport").hideLoading();
	            	  top.showMsg('提示', res.msg);
	              }
	          });	
		});
	</script>	
  </body>
</html>
