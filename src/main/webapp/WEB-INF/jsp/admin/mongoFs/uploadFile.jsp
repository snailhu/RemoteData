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
		
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.css">
	<script src="${pageContext.request.contextPath}/static/content/sweetalert/dist/sweetalert.min.js"></script>
	
<style type="text/css">
/* 弹出框居中样式  */
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
/* 	background-color: rgb(140, 212, 245);
	color: white;
	box-shadow: none;
	font-size: 17px;
	font-weight: 500;
	cursor: pointer;
	border: none;
	border-radius: 5px;
	padding: 10px 32px;
	margin: 26px 30px 0px; */
}

.sweet-alert .sa-confirm-button-container {
	display: inline-block;
	position: relative;
	/*     padding-left: 20px; */
}

.sa-button-container {
    margin-top: 20px;
	text-align: center;
}

.sa-confirm-button-container{
	text-align: center;
}
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
/*     padding-top: 15px; */
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
/* 	width: 80px; */
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
.form-horizontal{
	font-size: 14px;   
    margin-top: 40px;
	margin-left: 75px;
}
.btn {
	margin-right: 30px;
}

.breadcrumb {
    margin-top: 10px;
}
</style>
<script type="text/javascript">
	$(function(){
		//左菜单栏
		$("#uploadFile-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_30.png");
		$("#filemanage-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_26.png");
		$("#uploadFile-text").css("color","#5d90d6");
		$("#filemange-text").css("color", "#5d90d6");
		$("#filemanageUL").css("display","block");
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
			<ul class="breadcrumb">
				<li>
					<img src="${pageContext.request.contextPath}/static/imgs/DataImport/home.png" style="margin-bottom: 3px;">
					<span>文件管理</span>
				</li>
				<li class="active">数据导入</li>
			</ul><!--  .breadcrumb -->
		</div>
		<div class="page-content" id="page-content">
<!-- 			<div class="page-header" style="padding-bottom: 10px; /**margin: -5px 0px 5px;*/"> -->
<!-- 				<h1>文件上传</h1> -->
<!-- 			</div>					 -->
			<!-- /.page-header -->
			<div class="row">
				<div class="col-xs-12 col-sm-12"> 
					<!-- PAGE CONTENT BEGINS -->
					
					<!-- 文件上传form -->
					<form id="fileupload" action="admin/file/uploadFiles" class="form-horizontal" role="form" method="post" enctype="multipart/form-data">
						<div class="space-20"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" >DAT文件：</label>
							<div class="col-sm-8">
								<div class="uploader white">
									<input type="text" class="filename" name="showDatFileName" placeholder="请选择文件..." readonly/>
									<input type="button" class="button" value=" 浏  览 "/>
									<input type="file" accept=".DAT" name="datFile" id="datFile"/>
								</div>
							</div>
						</div>
						<div class="space-12"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">CSV文件：</label>
							<div class="col-sm-8">
								<div class="uploader blue">
									<input type="text" class="filename" placeholder="请选择文件..." readonly/>
									<input type="button" class="button" value=" 浏  览 "/>
									<input type="file" accept=".csv" name="csvFile" id="csvFile"/>
								</div>
							</div>
						</div>
						<div class="space-12"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">文件类型：</label>
							<div class="col-sm-8" style="margin-top: 4px;">
								<label style="margin-right: 50px;">
									<input name="paramType" type="radio" class="ace" value="flywheel" checked="true"/>
									<span class="lbl"> 飞轮</span>
								</label>
								<label>
									<input name="paramType" type="radio" class="ace" value="top"/>
									<span class="lbl"> 陀螺</span>
								</label>
							</div>
						</div>
						<div class="space-1"></div>
						<div class="form-group">
							<div style="margin-left:30%;">
								<span id="returnMsg"></span>
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
                           <div class="col-sm-8" style="margin-left:25%;">
		                        <button type="button" id="submit-fileupload" class=" button btn btn-primary start">
				                    <i class="icon-upload icon-white"></i>
				                    <span>开始 上传</span>
				                </button>
				                <button type="reset" id="resetBtn" class="button btn btn-warning cancel">
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
	
  <script type="text/javascript">
  	$(function(){
//   		swal("Hello world!");
		$("input[type=file]").change(function() {
			$(this).parents(".uploader").find(".filename").val($(this).val());
		});
		$("input[type=file]").each(function() {
			if ($(this).val() == "") {
				$(this).parents(".uploader").find(".filename").val("请选择文件...");
			}
		});
  		
	$('#submit-fileupload').click(function() {
        var fileName = $('#csvFile').val();
        if(fileName.length == 0){
        	$("#returnMsg").html("<img src='${pageContext.request.contextPath}/static/imgs/error.png'/><font color='red'>csv文件不能为空</font>");
        }else{
        	var regexp = /[a-zA-Z0-9]-\d{1,}--([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))/;
        	var datFile = $('#datFile').val();
        	var flag = true;
        	if(datFile.length != 0){
        		if(!regexp.test(datFile)){
            		$("#returnMsg").html("<img src='${pageContext.request.contextPath}/static/imgs/error.png'/><font color='red'>dat文件名输入不合法</font>");
					return false;
        		}else{
        			$.ajax({  
        		        url : "${pageContext.request.contextPath}/admin/file/existFile",  
        		        data : {
        		        	fileName : datFile
						},
						cache : false,
        		        async : false, // 注意此处需要同步，因为返回完数据后，下面才能让结果的第一条selected  
        		        type : "POST",  
        		        dataType : "json",  
        		        success : function(data) {  
        		        	if (data.success) {
	        		        	$("#returnMsg").html("<img src='${pageContext.request.contextPath}/static/imgs/error.png'/><font color='red'>dat"+data.msg+"</font>");
	    						flag = false;
        		        	}
        		        }  
        		    });  
        		}
        	}
        	if(flag){
        		if(regexp.test(fileName)){
            		var activeUser = '${activeUser}';
            		if(activeUser != null){
        				var permissionItemsJSON = '${activeUser.permissionItemsJSON}';
        				var type=$('input:radio[name="paramType"]:checked').val();
        				//console.log('type: ' + type);
        				var obj = eval("(" + permissionItemsJSON + ")");
        				var flag = false;
        				$.each(obj, function (n, value) { 
        					if(value == type){
        						//console.log(n + ' : ' + value);
        						flag = true;
        					}
        				});
        				if(flag){
    	   	 				$.post("${pageContext.request.contextPath}/admin/file/existFile", { fileName: fileName},function(data){
//     	    					console.log("flag: " + data.success);
    	    					if (data.success) {
    	    						$("#returnMsg").html("<img src='${pageContext.request.contextPath}/static/imgs/error.png'/><font color='red'>csv"+data.msg+"</font>");
    	    					}else{
    	    						swal({
    	    							title : "你是否确定上传？",
    	    							text : fileName,
    	    							type : "warning",
    	    							showCancelButton : true,
    	    							confirmButtonColor : "#DD6B55",
    	    							confirmButtonText : "上传",
    	    							cancelButtonText : "取消",
    	    							closeOnConfirm : false,
    	    							closeOnCancel : false
    	    						},
    	    						function(isConfirm) {
    	    							if (isConfirm) {
    	    								$("#fileupload").submit();
    	    							} else {
    	    								swal("取消上传", "","error");
    	    							}
    	    						});
    	    					}
    	    				});
        				}else{
        					swal("无上传权限！");
        				}
        			}
            	}else{
            		$("#returnMsg").html("<img src='${pageContext.request.contextPath}/static/imgs/error.png'/><font color='red'>csv文件名输入不合法</font>");
            	}
        	}
        }
    });
	$("#csvFile").blur(function() {
		$("#returnMsg").empty();
	});
    $('#resetBtn').click(function() {
    	$("#returnMsg").empty();
    });	
    
  });
  </script>
  </body>
</html>
