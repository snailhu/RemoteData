<#assign base=request.contextPath + '/DataRemote' >
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link type="text/css" rel="stylesheet" href="${base}/static/css/all.css"/>
    <link type="text/css" rel="stylesheet" href="${base}/static/css/home.css"/>
    <script src="${base}/static/js/jquery-1.8.3.min.js"></script>
    <script src="${base}/static/js/homepage/home.js"></script>
</head>
<body class="home">
<div class="home">
    <div class="head">
        <div class="container">
            <div class="head_text">
            	<#if activeUser??>
	            	<span>
						欢迎,${activeUser.userName}
					</span>
	                <span>|</span>
	                <span><a href='${base}/loginOut'>退出</a></span>
            	<#else>
			        <span><a href="login" id="loginLink">登录</a></span>
	        	</#if>
            </div>
        </div>
    </div>

    <div class="top">
        <div class="container">
            <div class="top_logo ">
                <img src="${base}/static/imgs/home/logo.png" width="380" height="70">
            </div>
            <div class="top_text">
                <span><a href="${base}/admin/file/index">文件管理</a></span>
                <span><a href="${base}/conditionMonitoring">数据分析</a></span>
                <span><a href="${base}/report/reportDownLoad">报告管理</a></span>
                <span><a href="${base}/admin/galaxy/index">星系管理</a></span>
                <span class="blue"><a href="${base}/admin/log/systemLog">系统管理</a></span>
            </div>
        </div>
    </div>

    <div class="div1"></div>

    <div class="div2">
        <div class="container">
            <div class="div2_title">
                <div>PROJECT INTRODUCTION</div>
                <div>项目介绍</div>
            </div>
            <div class="line">
                <div class="block"></div>
            </div>
            <div class="div2_content">
            	<a href="admin/systemLog">
                <div class="content-1 hover-effect">
                    <div class="content_img">
                        <img src="${base}/static/imgs/home/20160923_13.png">
                    </div>
                    <div class="content_title">
                        	系统管理
                    </div>
                    <div class="content_content">
                      	  快捷配置用户权限 管理用户群体
                    </div>
                    <div class="content_button">
                        <a><span>更多详细</span><span> > </span></a>
                    </div>
                </div></a>
                <a href="admin/file/toUploadFile">
                <div class="content-2 hover-effect">
                    <div class="content_img">
                        <img src="${base}/static/imgs/home/20160923_16.png">
                    </div>
                    <div class="content_title blue">
                        数据导入
                    </div>
                    <div class="content_content blue">
                        方便快速的导入海量数据 可选择不同格式的数据
                    </div>
                    <div class="content_button blue_back white">
                        <a><span>更多详细</span><span> > </span></a>
                    </div>
                </div></a>
                 <a href="conditionMonitoring">
                <div class="content-3 hover-effect">
                    <div class="content_img">
                        <img src="${base}/static/imgs/home/20160923_18.png">
                    </div>
                    <div class="content_title">
                        数据分析
                    </div>
                    <div class="content_content">
                        精准分析导入数据
                    </div>
                    <div class="content_button">
                        <a><span>更多详细</span><span> > </span></a>
                    </div>
                </div></a>
            </div>
        </div>
    </div>

    <div class="div3">
        <div class="container">
            <div class="block">
                <dvi class="title">苏州同元合作站点</dvi>
                <div class="line">
                    <dvi class="dot"></dvi>
                </div>
                <dvi class="content">同元首页</dvi>
            </div>

            <div class="block">
                <dvi class="title">同元F计划</dvi>
                <div class="line">
                    <dvi class="dot"></dvi>
                </div>
                <dvi class="content">移动生活圈构想</dvi>
            </div>

            <div class="block">
                <dvi class="title">联系我们</dvi>
                <div class="line"></div>
                <dvi class="content">填写联系地址或其他</dvi>
            </div>

            <div class="block">
                <div class="qr_img">
                    <img src="${base}/static/imgs/home/QrCode.png">
                </div>
                <dvi class="info">
                    微信公众号
                </dvi>
            </div>
        </div>
    </div>

    <div class="tail">
        <div class="container">
            <div>
                Copyright © 2016 xxxxxxx.com All Rights Reserved 版权所有·lxxxxxxxxx 苏ICP备00000000号-1
            </div>
        </div>
    </div>
</div>
</body>
</html>