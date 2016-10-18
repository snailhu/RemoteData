<#assign base=request.contextPath + '/DataRemote' >
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="utf-8" />
    <META name="description" content="苏州同元软控信息有限公司">
    <META NAME="keywords" content="苏州同元软控信息有限公司">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta property="qc:admins" content="1403664507655111637646375" />
    <title>中国航天</title>
    <link href="${base}/static/css/cssnew.css" rel="stylesheet"/>
    <style type="text/css">
	h1, h2, h3, h4, h5, h6 {
	    color: #aaa;
	    margin-top: -45px;
	    text-shadow: none;
	    font-weight: normal;
	    font-family: Microsoft YaHei,tahoma,arial,"Hiragino Sans GB",\5b8b\4f53;
	}
	h2 {
	    font-size: 36px;
	    line-height: 33px;
	}
	.jumbotron p {
	    margin-bottom: 15px;
	    font-size: 50px;
	    margin-top: 30px;
	    margin-bottom: 60px;
	}
	.btn-primary {
	    color: #fff;
	    background-color: rgba(255,255,255,.15);
	    border-color: rgba(0,0,0,.075);
	}
	</style>
    <script src="${base}/static/js/modernizr.js"></script>
	<!-- basic scripts -->
	<!--[if !IE]> -->
	<script src="${base}/static/js/jquery-2.0.3.min.js"></script>
	<!-- <![endif]-->
	<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery || document.write("<script src='${base}/static/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
	</script>
	<!-- <![endif]-->
    <script>
		var _hmt = _hmt || [];
		(function() {
		  var hm = document.createElement("script");
		  hm.src = "//hm.baidu.com/hm.js?7c8197ea90292d824c41dd9e11621ef2";
		  var s = document.getElementsByTagName("script")[0];
		  s.parentNode.insertBefore(hm, s);
		})();
    </script>
	<script type="text/javascript">
		$(function(){
			var scroll_offset = $("#main-container").offset();  //得到pos这个div层的offset，包含两个值，top和left
			  $("body,html").animate({
			     scrollTop:scroll_offset.top  //让body的scrollTop等于pos的top，就实现了滚动
			   },0);
		})
	</script>
</head>
<body style="overflow-x:hidden;overflow-y:no-display">             
    <div class="navbar navbar-inverse  navbar-static-top">
	<div class="container topxx" style="font-size:12px">
		<ul class="nav navbar-nav navbar-right pull-right">
			<!--
	        <li><a href="/Account/Register" id="registerLink">注册</a></li>
	        <li style="position:relative; top:15px"> |</li>
	        -->
	        <#if activeUser??>
	        	<li>
					<a href="javascript:void(0)" id="loginLink">
							欢迎光临,${activeUser.userName}
					</a>
				</li>
				<li><a href="${pageContext.request.contextPath}/admin/prewarning/logIndex?hadRead=0" id="prewarning">您有${warnCount}条未读预警信息</a></li>
				<li style='position:relative; top:15px'>
					<a style='margin-top: -15px;' href='${base}/loginOut' id='loginOutLink'> |&nbsp; 注销 
					</a>
				</li>
	        <#else>
		        <li><a href="login" id="loginLink">登录</a></li>
	        </#if>
		</ul>
	</div>
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/"> </a>
		</div>
		<div class="navbar-collapse collapse" >
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${base}/Index" style="color: #9d9d9d;;font-size: 18px;line-height: 1.8;">网站首页</a></li>
				<li><a href="${base}/report/index" style="color: #9d9d9d;;font-size: 18px;line-height: 1.8;">报告管理</a></li>
				<li><a href="${base}/starParam/index" style="color: #9d9d9d;;font-size: 18px;line-height: 1.8;">参数管理</a></li>
				<li><a href="${base}/admin/file/index" style="color: #9d9d9d;;font-size: 18px;line-height: 1.8;">文件管理</a></li>
				<li><a href="${base}/conditionMonitoring" style="color: #9d9d9d;;font-size: 18px;line-height: 1.8;">数据分析</a></li>
				<li><a href="${base}/admin/galaxy/index" style="color: #9d9d9d;;font-size: 18px;line-height: 1.8;">星系管理</a></li>
			</ul>
		</div>
	</div>
	</div>
   
<div class="jumbotron masthead">
    <div class="container">
        <p class="lead">中国航天科技集团公司</p>
        <h3>China Areospace Science and Technology Corpotation</h3>
        <p><a href="javascript:void(0)" class="btn btn-primary btn-lg">第八研究院803所惯导部</a></p>
    </div>
</div>

<div class="container" id="main-container">
    <div class="row">
        <div class="col-md-4 col-sm-6">
            <div class="pricing hover-effect">
                <div class="pricing-head">
                    <br />
                    <h4>系统管理<br /></h4>
                </div>
                <br />
                <a href="admin/systemLog">
                    <h4>
                        <img class="img-circle" src="${base}/static/images/manage.png" />
                        <br /><br />
                    </h4>
                    <p>快捷配置用户权限，管理用户群体</p>
                    <p><span class="btn btn-default">了解更多 &raquo;</span></p>
                </a>
                <div class="pricing-footer"></div>
                </div>
            </div>
           
       
        <div class="col-md-4 col-sm-6">
            <div class="pricing hover-effect">
                <div class="pricing-head">
                    <br />
                    <h4>数据导入<br /></h4>
                </div>
                <br />
                <a href="admin/file/toUploadFile">
                    <h4>
                        <img class="img-circle" src="${base}/static/images/import.png" />
                        <br /><br />
                    </h4>
                    <p>方便快速的导入海量数据，可选择不同格式的数据</p>
                    <p><span class="btn btn-default">了解更多 &raquo;</span></p>
                </a>
                <div class="pricing-footer"></div>
                </div>
            </div>
            
       
        <div class="col-md-4 col-sm-6">
            <div class="pricing hover-effect">
                <div class="pricing-head">
                     <br />
                    <h4>数据分析<br /></h4>
                </div>
                <br />
                <a href="conditionMonitoring">
                    <h4>
                        <img class="img-circle" src="${base}/static/images/analysis.png" />
                        <br /><br />
                    </h4>
                    <p>精确分析导入数据。</p>
                    <p><span class="btn btn-default">了解更多 &raquo;</span></p>
                </a>
                <div class="pricing-footer"></div>
                </div>
            </div>

     </div>
</div>

  
    <div class="footer-v2">
        <div class="footer">
            <div class="container">
                <div class="row">
                    <!-- About -->
                    <div class="col-md-3 md-margin-bottom-40">
                        <a href="index.html"><img id="logo-footer" class="footer-logo" src="/Content/images/login.png" alt=""></a>
                        <ul class="list-unstyled link-list">
                            <li><a href="http://www.iflve.com/map.html">苏州工业园区若水路388号纳米大学科技园E幢E1701 (215123) </a><i class="fa fa-angle-right"></i></li>
                        </ul>
                       
                        
                    </div>
                    <!-- End About -->
                    <!-- Link List -->
                    <div class="col-md-3 md-margin-bottom-40">
                        <div class="headline"><h2 class="heading-sm">苏州同元合作站点</h2></div>
                        <ul class="list-unstyled link-list">
                            <li><a href="/Home/Index">同元 首页</a><i class="glyphicon glyphicon-align-right"></i></li>
                        </ul>
                    </div>
                    <!-- End Link List -->
                    <!-- Latest Tweets -->
                    <div class="col-md-3 md-margin-bottom-40">
                        <div class="latest-tweets">
                            <div class="headline"><h2 class="heading-sm">同元F计划</h2></div>
                            <ul class="list-unstyled link-list">
                                <li><a href="#">移动生活圈构想</a><i class="glyphicon glyphicon-align-right"></i></li>
                            </ul>
                        </div>
                    </div>
                    <!-- End Latest Tweets -->
                    <!-- Address -->
                    <div class="col-md-3 md-margin-bottom-40">
                        <div class="headline"><h2 class="heading-sm">联系我们</h2></div>
                        <address class="md-margin-bottom-40">
                            <i class="fa fa-home"></i>微信公众号： <br>
                        </address>
                        <!-- Social Links -->
                                                             
                        <!-- End Social Links -->
                    </div>
                    <!-- End Address -->
                </div>
            </div>
        </div><!--/footer-->
        <div class="copyright">
            <div class="container">
                <p class="text-center">苏州同元软控信息有限公司/不支持低版本IE </p>
            </div>
        </div><!--/copyright-->
    </div>

    <script src="${base}/static/js/jqueryNew.js"></script>
    <script src="${base}/static/js/bootstrapNew.js"></script>   
</body>
</html>

