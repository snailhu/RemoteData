<%@ page language="java" pageEncoding="UTF-8"%>

<style type="text/css">
.head a:link {
	text-decoration: none;
	
}
.head a:visited {
	text-decoration: none;
}
.head a:hover {
	text-decoration: none;
}
.head a:active {
	text-decoration: none;
}
.head a {
    color: White;
    text-decoration: underline;
}
</style>
<script type="text/javascript">
	var activeUser = '${activeUser}';
	var warnCount = ${warnCount};
	$(function () {
		if(activeUser != ''){
			$('#prewarning').append("<a href='${pageContext.request.contextPath}/admin/prewarning/logIndex?hadReadFlag=0' id='prewarning'>"+warnCount+"条未读预警信息</a>");
		}else{
			$('#prewarning').append("<a href='${pageContext.request.contextPath}/loginOut' id='prewarning'>请先登录！</a>");
		}
	});
</script>
   <div class="head white">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/static/new/img/head/head_logo.png">
        </div>
        <div class="head_button">
            <span>
                <img src="${pageContext.request.contextPath}/static/new/img/head/gonggongshouye.png">
                <a href="login">公共首页</a>
            </span>
            <span>
                <img src="${pageContext.request.contextPath}/static/new/img/head/mimaxiugai.png">
                <a>密码修改</a>
            </span>
            <span id="prewarning">
                <img src="${pageContext.request.contextPath}/static/new/img/head/xinxitixing.png">
            </span>
            <span>
                <img src="${pageContext.request.contextPath}/static/new/img/head/tuichu.png">
                <a>退出</a>
            </span>
        </div>
    </div>
