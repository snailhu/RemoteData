<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'echartTest.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script
	src="<%=request.getContextPath()%>/static/content/jquery-2.0.3/jquery-2.0.3.min.js" type="text/javascript"></script>
 	<!-- 引入 ECharts 文件 -->
    <script src="<%=request.getContextPath()%>/static/content/echarts/echarts.min.js"></script>
  <script type="text/javascript">
  $(function () {
	  var rowsData = '${rowsData}';
	  var colsData = '${colsData}';
	  var data = '${jsonData}';
	  var jsonData = eval("("+data+")");
	  var rows = [];
	  var clos = [];
	  $.each(jsonData, function(i, n){
		  rows.push(i);
		  clos.push(n);
	  });
	  // 基于准备好的dom，初始化echarts实例
      var myChart = echarts.init(document.getElementById('main'));

      // 指定图表的配置项和数据
      var option = {
          title: {
              text: 'ECharts 入门示例'
          },
          tooltip: {},
          legend: {
              data:['销量']
          },
          xAxis: {
              data: rows
          },
          yAxis: {},
          series: [{
              name: '销量',
              type: 'bar',
              data: clos
          }]
      };

      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
  });
  </script>
  </head>
  
  <body>
    <!-- 为 ECharts 准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>
  </body>
</html>
