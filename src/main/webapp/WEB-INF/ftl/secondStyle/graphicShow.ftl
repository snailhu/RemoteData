<#assign base=request.contextPath + '/DataRemote' />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="${base}/static/js/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="${base}/static/js/echarts.min.js"></script>
    <link type="text/css" rel="stylesheet" href="${base}/static/content/jeDate/jedate/skin/jedate.css">
    <script type="text/javascript" src="${base}/static/content/jeDate/jedate/jedate.js"></script>
    <style type="text/css">
    .dateStyle{
    	margin-left: 100px;
    	margin-top: 25px;
    }
    .datainp{
		width: 200px;
		height: 22px;
	}
	.getData-btn{
		height: 27px;
		width: 100px;
	}
    </style>
</head>
<body>
	<div class="dateStyle">
		<label>开始日期</label>
		<input class="datainp" id="dateStart" type="text" placeholder="请选择" readonly>
		<label>结束日期</label>
		<input class="datainp" id="dateEnd" type="text" placeholder="请选择" readonly>
		<input class="getData-btn" id="getData" type="button" name="getData" value="获取数据">
	</div>
	 <div id="main" style="width: 1000px;height:450px;"></div>
</body>
<script type="text/javascript">
	jeDate({
		dateCell:"#dateStart",//直接显示日期层的容器，可以是ID  CLASS
		format:"YYYY-MM-DD hh:mm:ss",//日期格式
		isinitVal:false, //是否初始化时间
		festival:false, //是否显示节日
		isTime:true, //是否开启时间选择
		minDate:'${beginDate}',//最小日期
		maxDate:'${endDate}', //设定最大日期为当前日期
	});
	jeDate({
		dateCell:"#dateEnd",//直接显示日期层的容器，可以是ID  CLASS
		format:"YYYY-MM-DD hh:mm:ss",//日期格式
		isinitVal:false, //是否初始化时间
		festival:false, //是否显示节日
		isTime:true, //是否开启时间选择
		minDate:'${beginDate}',//最小日期
		maxDate:'${endDate}', //设定最大日期为当前日期
	});

    $(function(){     
    	var myChart = echarts.init(document.getElementById('main'));
    	var seriesOptions = []
        seriesCounter = 0
        var date = [];
        var names = [];
        <#if (params?size>0) >
        	<#list params as param>
		        var n={};
        		n.name = '${param.name}';
        		n.value = '${param.value}';
        		n.y = '${param.yname}';
        		names.push(n);
        	</#list>       	
        <#else>
        	names = ['flywheel_a_speed','flywheel_f_motor_current'];
		</#if>
        
         var options = {
            tooltip: {
                trigger: 'axis',              
            },
            title: {
                left: 'center',

            },
            legend: {
                top: 'bottom', 
                data:names
                           
            },
            toolbox: {
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    restore: {},
                    saveAsImage: {}
                }
            },
            xAxis: {
            	// axisLabel: {					
				//	rotate: 10
				//	},
                type: 'category',
                boundaryGap: false,
                data:[]              
            },
            yAxis: [{
                	type: 'value',
                	splitLine : {
		                show: true
		            }
            	},{
		            type : 'value',
		            splitLine : {
		                show: true
		            }
	        	}
            ],
            dataZoom: [{
                type: 'inside',
                start:20,
                end:21         
            }],
            series: []
        };    	     	
    	 $.getJSON('${base}/getDate', function (data) {
			 options.xAxis.data = eval(data);
			 myChart.setOption(options);	
     });   
    	    	   
        $.each(names, function (i, n) {
        	console.log(n);
	        $.getJSON('${base}/getData?filename=' + n.value, function (data) {	
	            seriesOptions[i] = {
	            	type: 'line',
	                name: n.name,
	                smooth: true,
	                yAxisIndex: n.y,
	                data: data
	            };
	            seriesCounter += 1;
	            if (seriesCounter === names.length) {
	            	options.series = eval(seriesOptions);
	            	console.log(options)
	                myChart.setOption(options);	                
	            }
	        });
    	});   
    	 
    })
 </script>	
</html>