<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8" />
    <title>产品在轨数据分析平台</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/WEB-INF/jsp/layouts2/admin-include-public.jsp"%>
	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/new/css/all.css"/>
	<style type="text/css">
	.container {
	    max-width: 1500px;
	}
	.DataImport_manage .container {
	    width: 1200px;
	    margin: 0 auto;
	}
	</style>
	<script type="text/javascript">
	
// 		$(function(){
// 			var scroll_offset = $("#main-container").offset();  //得到pos这个div层的offset，包含两个值，top和left
// 			  $("body,html").animate({
// 			     scrollTop:scroll_offset.top  //让body的scrollTop等于pos的top，就实现了滚动
// 			   },0);
// 		})
		
	</script>
	
	<sitemesh:write property='head' />
	
  </head>
  <body class="DataImport_manage">
<div class="data_import container clearfix">

	<jsp:include page="/WEB-INF/jsp/layouts2/admin-include-header.jsp"></jsp:include>
	
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>
			
			<jsp:include page="/WEB-INF/jsp/layouts2/admin-include-left.jsp"></jsp:include>
			
			<sitemesh:write property='body' />
			
		</div><!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div><!-- /.main-container -->
	
</div>
<div class="down">
    版权所有 © 公司名称 2016-2020 All Reserved 今日网站访问量：201 累计网站访问量：168666
</div>
</body>
</html>
