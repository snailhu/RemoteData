<#assign base=request.contextPath + '/DataRemote' />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="${base}/static/js/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="${base}/static/js/echarts.min.js"></script>
    
</head>
<body>
	 <div id="main" style="width: 1000px;height:450px;"></div>
</body>
<script type="text/javascript">    
    $(function(){     
    	var myChart = echarts.init(document.getElementById('main'));
    	var seriesOptions = []
        seriesCounter = 0
        var date = [];
        var names = [];
        <#if (params?size>0) >
        	<#list params as param>
        		names.push('${param}')
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
            }],
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
    	    	   
        $.each(names, function (i, name) {
	        $.getJSON('${base}/getData?filename=' + name.toLowerCase(), function (data) {	
	            seriesOptions[i] = {
	            	type: 'line',
	                name: name,
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