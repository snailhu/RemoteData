<#assign base=request.contextPath />
<#assign gogsUrl="http://gogs.modelica-china.com">
<!DOCTYPE html>
<html lang="en">
 <head>
 	<meta charset="UTF-8">
 	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="同元协同,tongyuan协同" />
	<meta name="description" content="同元协同,tongyuan协同" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
  	<title><@block name="title"></@block></title>
	<@block name="style"></@block>
	<@block name="link"></@block>
	<@block name="script"></@block>
 </head>
 <body>
 	<div class="navbar navbar-inverse  navbar-static-top">
	    <div class="container topxx" style="font-size:12px">    
	        <ul class="nav navbar-nav navbar-right pull-right">
		        <li><a href="/Account/Register" id="registerLink">注册</a></li>
		        <li style="position:relative; top:15px"> |</li>
		        <li><a href="/Account/Login" id="loginLink">登录</a></li>
		    </ul>
		</div>
	    <div class="container">
	        <div class="navbar-header">
	            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	            </button>               
	            <a class="navbar-brand" href="/">  </a> 
	        </div>
	        <div class="navbar-collapse collapse">
	            <ul class="nav navbar-nav navbar-right">
	                <li><a href="/">网站首页</a></li>
	                <li><a href="/Home/Web">网站开发</a></li>
	                <li><a href="/Home/App">APP开发</a></li>
	                <li><a href="/Home/Contact">美银团队</a></li>
	                <li><a href="/Home/Iflve">开发报价</a></li>        
	                <li><a href="/Home/Hongbao">试用下载</a></li>
	            </ul>               
	        </div>
	    </div>
	</div>
 	<div class="main-container" id="main-container">
 		<@block name="content"></@block>
 	</div>  	
  	<@block name="foot"></@block>
 </body>
</html>