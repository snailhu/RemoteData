<#assign base=request.contextPath + '/DataRemote' />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="${base}/static/js/jquery-2.0.3.min.js"></script>

    <script type="text/javascript" src="${base}/static/scripts/echarts.js"></script>


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
		<input class="getData-btn" id="getData"  type="button" name="getData" value="获取数据">
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
    	var pSeriesOptions = []
        var seriesCounter = 0
        var date = [];
        var pDate = [];
        var names = [];
        var mouseover_message=[];
        var paramSize= ${params?size};
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
           //      formatter: function (params) {
			//	     mouseover_message = params;
 			//		}           
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
                end:100         
            }],
            series: []
        };    	     	
   	    	   
        $.each(names, function (i, n) {       
	        $.getJSON('${base}/getData?start=${beginDate}&end=${endDate}&paramSize='+paramSize+'&filename=' + n.value, function (data) {	
	         //   console.log(data)
	            seriesOptions[i] = {
	            	type: 'line',
	                name: n.name,
	                smooth:true,
	                yAxisIndex: n.y,
	                data: data["paramValue"]
	            };
	            seriesCounter += 1;
	            if (seriesCounter === names.length) {
	            	pSeriesOptions = seriesOptions
	            	options.series = eval(seriesOptions);
	            	pDate=date=options.xAxis.data = data["yearValue"];	            
	                myChart.setOption(options);	                
	            }
	        });
    	});   
       	
       	$("#getData").click(function(){
       		var startDate =  $('#dateStart').val();
       		var endDate =  $('#dateEnd').val();
       		if(endDate && startDate){
       			var seriesCounter_date = 0  
       			var seriesOptionsDate = []	
       			  $.each(names, function (i, n) {       
			        $.getJSON('${base}/getData?start='+startDate+'&end='+endDate+'&paramSize='+paramSize+'&filename=' + n.value, function (data) {	
			            seriesOptionsDate[i] = {
			            	type: 'line',
			                name: n.name,
			                smooth:true,
			                yAxisIndex: n.y,
			                data: data["paramValue"]
			            };
			            seriesCounter_date += 1;
			            if (seriesCounter_date === names.length) {			            	
			            	options.series = eval(seriesOptionsDate);
			            	date=options.xAxis.data = data["yearValue"];	            
			                myChart.setOption(options);	                
			            }
			        });
		    	});   
       		}       		      	             	
       	})
       	
       	
       	myChart.on('restore' ,function(params){
       		options.series = eval(pSeriesOptions);
            date=options.xAxis.data = pDate;	
            myChart.setOption(options);
       		
       	})
       			  	      
        myChart.on('dataZoom', function (params) {
              //  console.log(params);
                var endDate = date[params.batch[0].endValue];
                var startDate = date[params.batch[0].startValue];
                if(endDate && startDate){
					var seriesCounter_new = 0    
					var seriesOptionsDam = []	
	                $.each(names, function (i, n) {
	                    $.getJSON('${base}/getData?start='+startDate+'&end='+endDate+'&paramSize='+paramSize+'&filename=' + n.value, function (data) {
	                    	console.log(data)
	                        seriesOptionsDam[i] = {
	                            type: 'line',
	                            smooth:true,
	                            name: n.name,
	                            yAxisIndex: n.y,
	                            data: data["paramValue"]
	                        };
	                        seriesCounter_new += 1;
	                        if (seriesCounter_new === names.length) {
	                            options.series = eval(seriesOptionsDam);
	                            date=options.xAxis.data = data["yearValue"];	
	                            myChart.setOption(options);
	                        }
	                    });
	                });
                }
               
        });
      
    	 
    })
 </script>	
</html>
