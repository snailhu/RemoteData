
<@override name="script">
    
 <script src="${base}/static/js/echarts.min.js"></script>


<script type="text/javascript">
	     $(function(){

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
                data: []
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'line',
                data: [5, 20, 36, 10, 10, 20]
            }]
        };
		
         $.getJSON('http://localhost:8080/DataRemote/getDate', function (data) {
         	alert("123")
		     option.xAxis.data = eval(data);

       });
        //使用刚指定的配置项和数据显示图表。
         console.log(JSON.stringify(option))
        myChart.setOption(option);


        })

	
  </script>
</@override>
<@override name="content_right">
	 <div id="main" style="width: 1200px;height:400px;float: left"></div>
</@override>	
<@extends name="/secondStyle/baseNew.ftl"/>

