<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
 <head>
 	<meta charset="UTF-8">
 	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="同元协同,tongyuan协同" />
	<meta name="description" content="同元协同,tongyuan协同" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
  	<title><@block name="title"></@block></title> 
	<script src="${base}/static/js/jquery-1.11.0.min.js"></script>
	<script src="${base}/static/js/bootstrap.min.js"></script>
	<style>
		*{margin:0;padding:0;font-size: 12px}
		.container-out{margin:20px auto !important;border:1px solid red}
		.container-out .content_left{padding:0px!important}
		.container-out .content_right{border:1px solid black}
	</style>
	<@block name="style"></@block>
	<@block name="link"></@block>
	<@block name="script"></@block>
 </head>
 <body>
 	<div class="navbar navbar-default" id="navbar">
 		<@block name="header"></@block>
 	</div>
 	<div class="container-fluid" id="main-container">
 		<div class="row container-out">
	 		<div class="col-md-2 content_left">
	 			<@block name="content_left"></@block>
	 		</div>
	 		<div class="col-md-10 content_right">
	 			<@block name="content_right"></@block>
	 		</div>	
	 	</div>	
 	</div>  	
  	<@block name="foot"></@block>
 </body>
</html>
