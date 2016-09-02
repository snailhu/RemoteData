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
	
	<%@include file="/WEB-INF/jsp/layouts/admin-include-public.jsp"%> 
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.css">
	<script src="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.min.js"></script>
	<!--     <link rel="stylesheet" href="<%=request.getContextPath()%>/static/content/bootstrapValidator/vendor/bootstrap/css/bootstrap.css"/> -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/content/bootstrapValidator/dist/css/bootstrapValidator.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/content/bootstrapValidator/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/content/bootstrapValidator/dist/js/bootstrapValidator.js"></script>
  <style type="text/css">
  .form-horizontal .has-feedback .form-control-feedback {
    top: 0;
    right: 15px;
	}
	.has-feedback .form-control-feedback {
    position: absolute;
/*     top: 25px; */
/*     right: 0; */
    display: block;
    width: 34px;
    height: 34px;
    line-height: 34px;
    text-align: center;
}
.glyphicon {
/*     position: relative; */
/*     top: 1px; */
/*     display: inline-block; */
    font-family: 'Glyphicons Halflings';
    font-style: normal;
    font-weight: normal;
/*     line-height: 1; */
    -webkit-font-smoothing: antialiased;
/*     -moz-osx-font-smoothing: grayscale; */
}
.form-group>label[class*="col-"] {
    padding-top: 15px;
    margin-bottom: -4px;
}
  </style>
<style type="text/css">
.uploader {
	position: relative;
	display: inline-block;
	overflow: hidden;
	cursor: default;
	padding: 0;
	margin: 0px 0px;
	-moz-box-shadow: 0px 0px 5px #ddd;
	-webkit-box-shadow: 0px 0px 5px #ddd;
	box-shadow: 0px 0px 5px #ddd;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
}

.filename {
	float: left;
	display: inline-block;
	outline: 0 none;
	height: 32px;
	width: 200px;
	margin: 0;
	padding: 8px 10px;
	overflow: hidden;
	cursor: default;
	border: 1px solid;
	border-right: 0;
	font: 9pt/100% Arial, Helvetica, sans-serif;
	color: #777;
	text-shadow: 1px 1px 0px #fff;
	text-overflow: ellipsis;
	white-space: nowrap;
	-moz-border-radius: 5px 0px 0px 5px;
	-webkit-border-radius: 5px 0px 0px 5px;
	border-radius: 5px 0px 0px 5px;
	background: #f5f5f5;
	background: -moz-linear-gradient(top, #fafafa 0%, #eee 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #fafafa),
		color-stop(100%, #f5f5f5));
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#fafafa',
		endColorstr='#f5f5f5', GradientType=0);
	border-color: #ccc;
	-moz-box-shadow: 0px 0px 1px #fff inset;
	-webkit-box-shadow: 0px 0px 1px #fff inset;
	box-shadow: 0px 0px 1px #fff inset;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
}

.button {
	float: left;
	height: 32px;
	width: 80px;
	display: inline-block;
	outline: 0 none;
	padding: 8px 12px;
	margin: 0;
	cursor: pointer;
	border: 1px solid;
	font: bold 9pt/100% Arial, Helvetica, sans-serif;
	-moz-border-radius: 0px 5px 5px 0px;
	-webkit-border-radius: 0px 5px 5px 0px;
	border-radius: 0px 5px 5px 0px;
	-moz-box-shadow: 0px 0px 1px #fff inset;
	-webkit-box-shadow: 0px 0px 1px #fff inset;
	box-shadow: 0px 0px 1px #fff inset;
}

.uploader input[type=file] {
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	border: 0;
	padding: 0;
	margin: 0;
	height: 32px;
	width: 200px;
	cursor: pointer;
	filter: alpha(opacity = 0);
	-moz-opacity: 0;
	-khtml-opacity: 0;
	opacity: 0;
}

input[type=button]::-moz-focus-inner {
	padding: 0;
	border: 0 none;
	-moz-box-sizing: content-box;
}

input[type=button]::-webkit-focus-inner {
	padding: 0;
	border: 0 none;
	-webkit-box-sizing: content-box;
}

input[type=text]::-moz-focus-inner {
	padding: 0;
	border: 0 none;
	-moz-box-sizing: content-box;
}

input[type=text]::-webkit-focus-inner {
	padding: 0;
	border: 0 none;
	-webkit-box-sizing: content-box;
}
/* White Color Scheme ------------------------ */
.white .button {
	color: #555;
	text-shadow: 1px 1px 0px #fff;
	background: #ddd;
	background: -moz-linear-gradient(top, #eeeeee 0%, #dddddd 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #eeeeee),
		color-stop(100%, #dddddd));
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#eeeeee',
		endColorstr='#dddddd', GradientType=0);
	border-color: #ccc;
}

.white:hover .button {
	background: #eee;
	background: -moz-linear-gradient(top, #dddddd 0%, #eeeeee 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #dddddd),
		color-stop(100%, #eeeeee));
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#dddddd',
		endColorstr='#eeeeee', GradientType=0);
}
/* Blue Color Scheme ------------------------ */
.blue .button {
	color: #fff;
	text-shadow: 1px 1px 0px #09365f;
	background: #064884;
	background: -moz-linear-gradient(top, #3b75b4 0%, #064884 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #3b75b4),color-stop(100%, #064884));
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#3b75b4',endColorstr='#064884', GradientType=0);
	border-color: #09365f;
}

.blue:hover .button {
	background: #3b75b4;
	background: -moz-linear-gradient(top, #064884 0%, #3b75b4 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #064884),color-stop(100%, #3b75b4));
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#064884',endColorstr='#3b75b4', GradientType=0);
}
.has-feedback .form-control-feedback {
    position: absolute;
    /* top: 25px; */
    /* right: 0; */
    display: block;
    width: 34px;
    height: 34px;
    line-height: 34px;
    text-align: center;
    padding-left: 60px;
}
</style>
<!-- basic styles -->
<link href="${pageContext.request.contextPath}/static/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/font-awesome.min.css" />
<!--[if IE 7]>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/font-awesome-ie7.min.css" />
<![endif]-->

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/ace.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/ace-skins.min.css" />
<!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/buttons.css" /> -->
<!--[if lte IE 8]>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/ace-ie.min.css" />
<![endif]-->
<script type="text/javascript">
	$(function(){
		var scroll_offset = $("#main-container").offset();  //得到pos这个div层的offset，包含两个值，top和left
		  $("body,html").animate({
		     scrollTop:scroll_offset.top  //让body的scrollTop等于pos的top，就实现了滚动
		   },0);
	})
</script>
</head>
  <body>
	<jsp:include page="/WEB-INF/jsp/layouts/admin-include-header2.jsp"></jsp:include>
  	<div class="main-container" id="main-container">
<!-- 		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script> -->

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>
			<jsp:include page="/WEB-INF/jsp/layouts/admin-include-left.jsp"></jsp:include>
			<div class="main-content">
				<div class="page-content" id="page-content">
					<div class="page-header" style="padding-bottom: 10px; /**margin: -5px 0px 5px;*/">
						<h1>文件上传</h1>
					</div>					
					<!-- /.page-header -->
					<div class="row">
 						<div class="col-xs-12 col-sm-12"> 
							<!-- PAGE CONTENT BEGINS -->
							<!-- 文件上传form -->
							<form id="fileupload" action="admin/file/uploadFiles" class="form-horizontal" role="form" method="post" enctype="multipart/form-data">
								<div class="space-20"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" > DAT文件:  </label>
									<div class="col-sm-8">
										<div class="uploader white">
											<input type="text" class="filename" name="showDatFileName" placeholder="No file selected..." readonly/>
											<input type="button" class="button" value="Browse..."/>
											<input type="file" accept=".DAT" name="datFile" id="datFile"/>
										</div>
									</div>
								</div>
								<div class="space-8"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"> CSV文件:  </label>
									<div class="col-sm-8">
										<div class="uploader blue">
											<input type="text" class="filename" placeholder="No file selected..." readonly/>
											<input type="button" class="button" value="Browse..."/>
											<input type="file" accept=".csv" name="csvFile" id="csvFile"/>
										</div>
									</div>
								</div>
								<div class="space-8"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">文件类型:</label>
									<div class="col-sm-8">
										<label>
											<input name="paramType" type="radio" class="ace" value="flywheel" checked="true"/>
											<span class="lbl"> 飞轮</span>
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label>
											<input name="paramType" type="radio" class="ace" value="top"/>
											<span class="lbl"> 陀螺</span>
										</label>
									</div>
								</div>
								<div class="space-8"></div>
								<div style="margin-left: 300px;">
									<span id="returnMsg"></span>
								</div>
								<div class="space-12"></div>
								<div class="form-group">
		                           <div class="col-sm-8" style="float: right;">
				                        <button type="button" id="submit-fileupload" class="btn btn-primary start">
						                    <i class="icon-upload icon-white"></i>
						                    <span>开始 上传</span>
						                </button>
						                <button type="reset" id="resetBtn" class="btn btn-warning cancel">
						                    <i class="icon-ban-circle icon-white"></i>
						                    <span>取消 上传</span>
						                </button>
		                           </div>
		                       </div>
							</form>
						</div><!-- /.col -->								
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->			
		</div><!-- /.main-container-inner -->
		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div><!-- /.main-container -->
  </body>
  <script type="text/javascript">
  	$(function(){
//   		swal("Hello world!");
		$("input[type=file]").change(function() {
			$(this).parents(".uploader").find(".filename").val($(this).val());
		});
		$("input[type=file]").each(function() {
			if ($(this).val() == "") {
				$(this).parents(".uploader").find(".filename").val("No file selected...");
			}
		});
  		$('#fileupload11').bootstrapValidator({
//          live: 'disabled',
          message: 'This value is not valid',
          feedbackIcons: {
              valid: 'glyphicon glyphicon-ok',
              invalid: 'glyphicon glyphicon-remove',
              validating: 'glyphicon glyphicon-refresh'
          },
          fields: {
        	  datFile: {
                  message: 'dat文件名输入不合法',
                  validators: {
//                       notEmpty: {
//                           message: 'dat文件不能为空'
//                       },
                      
                  }
              },
              csvFile: {
                  validators: {
                	  message: 'csv文件名输入不合法',
                      notEmpty: {
                          message: 'csv文件不能为空'
                      },
                      regexp: {
                          regexp: /j9-0[1-9]--([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))).csv/,
                          message: 'csv文件名输入不合法'
                      },
//                       callback: {
//                     	  message: '文件已存在',
//                           callback: function(value, validator) {
// 							 $.post("${pageContext.request.contextPath}/admin/file/existFile", { fileName: value},function(data){
// 								console.log("flag: " + data.success);
// 								if (data.success) {
// 									$("#returnMsg").html("<img src='${pageContext.request.contextPath}/static/imgs/error.png'/><font color='red'>文件已存在</font>");
// 									return false;
// 								}else{
// 									return true;
// 								}
// 							 });
//                           }
//                       }
                  }
              },
          }
      });
  		
	$('#submit-fileupload').click(function() {
        
        var fileName = $('#csvFile').val();
        if(fileName.length == 0){
        	$("#returnMsg").html("<img src='${pageContext.request.contextPath}/static/imgs/error.png'/><font color='red'>csv文件不能为空</font>");
        }else{
        	var regexp = /j9-0[1-9]--([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))).csv/;
        	if(regexp.test(fileName)){
				$.post("${pageContext.request.contextPath}/admin/file/existFile", { fileName: fileName},function(data){
					//console.log("flag: " + data.success);
					if (data.success) {
						$("#returnMsg").html("<img src='${pageContext.request.contextPath}/static/imgs/error.png'/><font color='red'>csv文件已存在</font>");
					}else{
						$("#fileupload").submit();
					}
				});
        	}else{
        		$("#returnMsg").html("<img src='${pageContext.request.contextPath}/static/imgs/error.png'/><font color='red'>csv文件名输入不合法</font>");
        	}
        }
        
    });
	$("#csvFile").blur(function() {
		$("#returnMsg").empty();
	});
	
    $('#resetBtn').click(function() {
    	$("#returnMsg").empty();
//         $('#fileupload').data('bootstrapValidator').resetForm(true);
    });	
  });
  </script>

</html>
