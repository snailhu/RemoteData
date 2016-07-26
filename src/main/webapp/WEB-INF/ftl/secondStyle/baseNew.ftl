<#assign base=request.contextPath + '/DataRemote' />
<!DOCTYPE html>
<html lang="en">
 <head>
 	<meta charset="UTF-8">
 	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="同元协同,tongyuan协同" />
	<meta name="description" content="同元协同,tongyuan协同" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
  	<title><@block name="title"></@block></title>
  	 	
	<link href="${base}/static/assets/css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="${base}/static/assets/css/font-awesome.min.css" />	
	<link rel="stylesheet" href="${base}/static/css/css.css" />
	<link rel="stylesheet" href="${base}/static/assets/css/ace.min.css" />
	<link rel="stylesheet" href="${base}/static/assets/css/ace-rtl.min.css" />
	<link rel="stylesheet" href="${base}/static/assets/css/ace-skins.min.css" />
	
	<script src="${base}/static/assets/js/ace-extra.min.js"></script>
  	
  	
  	    <!-- basic scripts -->
		<!--[if !IE]> -->
		<script src="${base}/static/js/jquery-2.0.3.min.js"></script>
		<!-- <![endif]-->
		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${base}/static/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>
		<!-- <![endif]-->
		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${base}/static/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${base}/static/assets/js/bootstrap.min.js"></script>
		<script src="${base}/static/assets/js/typeahead-bs2.min.js"></script>
		<!-- page specific plugin scripts -->

		<script src="${base}/static/assets/js/jquery.slimscroll.min.js"></script>
		<!-- ace scripts -->
		<script src="${base}/static/assets/js/ace-elements.min.js"></script>
		<script src="${base}/static/assets/js/ace.min.js"></script>	
  	
	<@block name="style"></@block>
	<@block name="link"></@block>

 </head>
 <body>
 	<div class="navbar navbar-default" id="navbar">
    	<@block name="header"></@block>
    </div>
	<div class="main-container" id="main-container">
    	<div class="main-container-inner">
    		<@block name="content_left"></@block>
    		<@block name="content_right"></@block>	
    	</div>
    	<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        	<i class="icon-double-angle-up icon-only bigger-110"></i>
    	</a>
    </div> 

  	<@block name="foot"></@block>
 </body>
 	<@block name="script"></@block>
</html>
