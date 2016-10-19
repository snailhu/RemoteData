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

</style>
  </head>
  <body>

    <div class="main-content">
		<div class="page-content">
			<div class="page-header" style="margin: 0px;float: left;">
				<h1>报表下载</h1>
			</div>
			<div >
				<div class="col-xs-12 col-sm-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="widget-box">
						<div class="widget-header" id="change-search-box" data-action="collapse">
							<h4>参数</h4>
							<div class="widget-toolbar">
								<a href="javascript:void(0);" >
									<i class="icon-chevron-up"></i>
								</a>
							</div>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<!-- 搜索form -->
								<form id="form" action="" class="form-horizontal" role="form" >
									<div class="space-1"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-series">星系：</label>
										<div class="col-sm-4"  >
										<select name="series"  id="form-series" class="form-control " >
				                       </select>
									</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-star">星号：</label>
										<div class="col-sm-4">
												<select name="star"  id="form-star" class="form-control " >
				                       			</select>
										</div>
									</div>
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-partsType">部件：</label>
										<div class="col-sm-4">
											<select name="partsType"  id="form-partsType" class="form-control " >
				                           		<option selected="selected" value = "飞轮">飞轮</option>
				                           		<option value = "陀螺">陀螺</option>
				                       		</select>
										</div>
									</div>
									<div class="space-1"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-Time"> 日期： </label>
										<div class="col-sm-4">
											<input type="text" id="form-Time" name="time" placeholder="日期" class="col-xs-10 col-sm-5" />
											<div id="getTime"></div>
										</div>
									</div>
									<div class="space-1"></div>
									<div class="form-group">
			                           <div class="col-lg-4 col-lg-offset-6">
					                        <button type="button" id="btn-downLoad" class="btn btn-primary start">
							                    <i></i>
							                    <span>下载</span>
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
					<!-- PAGE CONTENT ENDS -->
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
	</div><!-- /.main-content -->
<script type="text/javascript">

$(function(){
	  $('#getTime').calendar({
	        trigger: '#form-Time',
	        zIndex: 999,
			format: 'yyyy-mm-dd',
	        onSelected: function (view, date, data) {
	        },
	        onClose: function (view, date, data) {
	        }
	    });

      $.get('<%=request.getContextPath()%>/starParam/getSeriesList', {}, function (res) {
		  if(res.result == "true") {
        	  $.each(res.data.data ,function(){
					$('#form-series').append("<option value='"+ this.name+"'>"+ this.description +"</option>"); 
				});
        		  var seriesId = $('#form-series').val();
        			$.get('<%=request.getContextPath()%>/starParam/getStarList', {'seriesId':seriesId},  function (res) {
  					  if(res.result == "true") {
  						  $('#form-star').find("option").remove();
  		            	  $.each(res.data.data ,function(){
  								$('#form-star').append("<option value='"+ this.name+"'>"+ this.description +"</option>"); 
  							});
  		              }
  		              else {
  		            	  top.showMsg('提示', res.msg);
  		              }
  		          });	
          }
          else {
        	  top.showMsg('提示', res.msg);
          }
      });
	$("#form-series").change(function(){
		 	var seriesId = $('#form-series').val();	
		 	  $.get('<%=request.getContextPath()%>/starParam/getStarList', {'seriesId':seriesId},  function (res) {
				  if(res.result == "true") {
					  $('#form-star').find("option").remove();
	            	  $.each(res.data.data ,function(){
							$('#form-star').append("<option value='"+ this.name+"'>"+ this.description +"</option>"); 
						});
	              }
	              else {
	            	  top.showMsg('提示', res.msg);
	              }
	          });	
	});
});
	$('#btn-downLoad').click(function(){
			var Qseries =  $('#form-series').val();
			var Qstar = $('#form-star').val();
			var QpartsType = $('#form-partsType').val();
			var Qtime = $("#form-Time").val();
         	var data = {'seriesId':Qseries,'starId':Qstar,'partsType':QpartsType,'time':Qtime};
         	window.location.href="report/createReport?seriesId="+Qseries+"&starId="+Qstar+"&partsType="+QpartsType+"&time="+Qtime;
         	
		});
	</script>	
  </body>
</html>
