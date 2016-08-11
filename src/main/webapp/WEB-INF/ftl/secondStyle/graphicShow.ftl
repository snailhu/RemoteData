<#assign base=request.contextPath + '/DataRemote' />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="${base}/static/js/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="${base}/static/scripts/echarts.js"></script>
    
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
        var mouseover_message=[];
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
                 formatter: function (params) {
				     mouseover_message = params;
 					}           
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
                start:0,
                end:21         
            }],
            series: []
        };    	     	
    	 $.getJSON('${base}/getDate', function (data) {
			date = options.xAxis.data = eval(data);
			 myChart.setOption(options);	
     });   
    	    	   
        $.each(names, function (i, n) {       
	        $.getJSON('${base}/getData?start='+startDate+'&end='+endDate+'&filename=' + n.value, function (data) {	
	            seriesOptions[i] = {
	            	type: 'line',
	                name: n.name,
	                yAxisIndex: n.y,
	                data: data
	            };
	            seriesCounter += 1;
	            if (seriesCounter === names.length) {
	            	options.series = eval(seriesOptions);	            
	                myChart.setOption(options);	                
	            }
	        });
    	});   
    	

    		 
    	      
        myChart.on('dataZoom', function (params) {
            console.log(params);
            var endDate = date[params.batch[0].endValue];
            var startDate = date[params.batch[0].startValue];
         	$.each(names, function (i, n) {       
	        $.getJSON('${base}/getData?start='+startDate+'&end='+endDate+'&filename=' + n.value, function (data) {	
	            seriesOptions[i] = {
	            	type: 'line',
	                name: n.name,
	                yAxisIndex: n.y,
	                data: data
	            };
	            seriesCounter += 1;
	            if (seriesCounter === names.length) {
	            	options.series = eval(seriesOptions);	            
	                myChart.setOption(options);	                
	            }
	        });
    	});   
        });
      
    	 
    })
 </script>	
</html>