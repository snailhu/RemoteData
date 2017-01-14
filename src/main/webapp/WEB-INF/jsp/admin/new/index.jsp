<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/content/echarts-x/doc/css/common.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/content/echarts-x/doc/css/example.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/content/echarts-x/doc/css/codemirror.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/content/echarts-x/doc/css/monokai.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/content/echarts-x/doc/css/twilight.css">


<script src="${pageContext.request.contextPath}/static/content/echarts-x/doc/lib/esl.js"></script>
<script src="${pageContext.request.contextPath}/static/content/echarts-x/doc/lib/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/content/echarts-x/doc/example/js/bootExample.js"></script>

<script async=""
	src="${pageContext.request.contextPath}/static/content/echarts-x/doc/lib/echarts-x/echarts-x.js"></script>
<script async=""
	src="${pageContext.request.contextPath}/static/content/echarts-x/doc/lib/echarts/echarts.js"></script>
<script async=""
	src="${pageContext.request.contextPath}/static/content/echarts-x/doc/lib/echarts/chart/map.js"></script>
<script data-require-id="js/example" async=""
	src="${pageContext.request.contextPath}/static/content/echarts-x/doc/example/js/example.js"></script>
<script data-require-id="lib/codemirror/codemirror" async=""
	src="${pageContext.request.contextPath}/static/content/echarts-x/doc/lib/codemirror/codemirror.js"></script>
<script data-require-id="echarts/chart/bar" async=""
	src="${pageContext.request.contextPath}/static/content/echarts-x/doc/lib/echarts/chart/bar.js"></script>
<script data-require-id="echarts-x/chart/map3d" async=""
	src="${pageContext.request.contextPath}/static/content/echarts-x/doc/lib/echarts-x/chart/map3d.js"></script>
<script data-require-id="lib/codemirror/mode/javascript" async=""
	src="${pageContext.request.contextPath}/static/content/echarts-x/doc/lib/codemirror/mode/javascript.js"></script>
<style id="style-1-cropbar-clipper">/* Copyright 2014 Evernote Corporation. All rights reserved. */
.en-markup-crop-options {
	top: 18px !important;
	left: 50% !important;
	margin-left: -100px !important;
	width: 200px !important;
	border: 2px rgba(255, 255, 255, .38) solid !important;
	border-radius: 4px !important;
}

.en-markup-crop-options div div:first-of-type {
	margin-left: 0px !important;
}
</style>

<!-- 必须要先引入 ECharts 主文件 -->
<script src="${pageContext.request.contextPath}/static/content/echarts-x/echarts/src/echarts.js"></script>
<!-- 引入 ECharts-X 主文件 -->
<script src="${pageContext.request.contextPath}/static/content/echarts-x/build/source/echarts-x.js"></script>

<style type="text/css">

#main{
	margin-top: -50px;
}
</style>

</head>
<body>

	<main id="main" style="width: 100%;height:100%;">
		<div id="chart"></div>
	</main>
	
	<script type="text/javascript">
        // 配置后续加载的各种 chart 配置 config
        require.config({
            paths: {
                'echarts': 'dep/echarts/build',
                'echarts-x': 'dep/echarts-x/build'
            }
        });

        // 然后就可以动态加载图表进行绘制啦
        require([
            'echarts',
            'echarts-x',
            // ECharts-X 中 map3d 的地图绘制基于 ECharts 中的 map。
            'echarts/chart/map',
            'echarts-x/chart/map3d'
        ], function (ec) {
            // 跟 ECharts 一样的方式初始化
            var myChart = ec.init(document.getElementById('main'));
            myChart.setOption({
                title: {
                    text: '',
                    subtext: '',
                    sublink: '',
                    x: 'center',
                    textStyle: {
                        color: 'white'
                    }
                },
                tooltip: {
                    formatter: '{b}'
                },
                series: [{
                    type: 'map3d',
                    mapType: 'world',
                    // 底图配置
                    background: '${pageContext.request.contextPath}/static/content/echarts-x/doc/example/asset/starfield.jpg',
                    // Have a try to change an environment
                    // background: 'asset/background.jpg',

                    baseLayer: {
                        backgroundColor: '',
                        backgroundImage: '${pageContext.request.contextPath}/static/content/echarts-x/doc/example/asset/earth.jpg',
                        quality: 'medium',

                        heightImage: '${pageContext.request.contextPath}/static/content/echarts-x/doc/example/asset/elev_bump.jpg'
                    },

                    light: {
                        show: true,
                        // Use the system time
                        // time: '2013-08-07 18:09:09',
                        sunIntensity: 1
                    },

                    itemStyle: {
                        normal: {
                            label: {
                                show: true
                            },
                            borderWidth: 1,
                            borderColor: 'yellow',
                            areaStyle: {
                                color: 'rgba(0, 0, 0, 0)'
                            }
                        }
                    },
                    data: [{}]
                }]
            });
        });
    </script>
    
</body>
</html>
