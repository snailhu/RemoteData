<#assign base=request.contextPath + '/DataRemote' />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="${base}/static/js/jquery-2.0.3.min.js"></script>

    
    <script type="text/javascript" src="${base}/static/scripts/echarts.min.js"></script>
    <script type="text/javascript" src="${base}/static/scripts/vintage.js"></script>
    <script type="text/javascript" src="${base}/static/js/conditionMonitoring/datediff.js"></script>
    <!--<script type="text/javascript" src="${base}/static/scripts/test.js"></script>-->
    <!-- 时间选择器 -->
    <link type="text/css" rel="stylesheet" href="${base}/static/content/jeDate/jedate/skin/jedate.css">
    <script type="text/javascript" src="${base}/static/content/jeDate/jedate/jedate.js"></script>
    <!-- 颜色选择器 -->
<!--     <link rel="stylesheet" href="${base}/static/content/minicolors/bootstrap.min.css"> -->
<!-- 	<script type="text/javascript" src="${base}/static/content/minicolors/bootstrap.min.js"></script> -->
	<link rel="stylesheet" href="${base}/static/content/minicolors/jquery.minicolors.css">
    <script type="text/javascript" src="${base}/static/content/minicolors/jquery.minicolors.js"></script>
    
    <!-- ps颜色选择器 -->
<!--     <link rel="stylesheet" type="text/css" href="${base}/static/content/pscolors/css/colorpicker.css"/> -->
<!--     <link rel="stylesheet" media="screen" type="text/css" href="${base}/static/content/pscolors/css/layout.css" /> -->
<!-- 	<script type="text/javascript" src="${base}/static/content/pscolors/js/jquery.js"></script> -->
<!-- 	<script type="text/javascript" src="${base}/static/content/pscolors/js/colorpicker.js"></script> -->
<!--     <script type="text/javascript" src="${base}/static/content/pscolors/js/eye.js"></script> -->
<!--     <script type="text/javascript" src="${base}/static/content/pscolors/js/utils.js"></script> -->
<!--     <script type="text/javascript" src="${base}/static/content/pscolors/js/layout.js?ver=1.0.2"></script> -->
    
    <link rel="stylesheet" href="${base}/static/assets/css/bootstrap.min.css">
    <!--<script src="${base}/static/assets/js/bootstrap.min.js"></script>-->
    <!--<script src=${base}/static/assets/js/bootstrap-tag.min.js"></script>-->
    <script src="${pageContext.request.contextPath}/DataRemote/static/assets/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/DataRemote/static/assets/js/typeahead-bs2.min.js"></script>
    <style type="text/css">
    .dateStyle{
    	margin-left: 80px;
    	margin-top: 25px;
    }
    .datainp{
		width: 150px;
		height: 25px;
	}
	.getData-btn{
		height: 30px;
		width: 100px;
	}
	.form-group {
		margin-left:0px;
		margin-right:0px;
	}
	.dateStyle label {
		font-size: 16px;
    	text-align: center;
    	color: #000000;
    	border-width: 1px;
	}
	.form-horizontal .control-label {
	    text-align: center;
	}
	.shujufenxibutton {
    height: 31px;
    width: 80px;
    color: white;
    background-color: #4B92DD;
    border-width: 2px;
	}
	#getData,#changeColor{
    overflow:hidden;
    padding: 0px;
    line-height: 30px;
    border-radius:10px;
    background-color: #1C76C5;
    color: white; 
    //margin-left:120px;
    //margin-top:-32px
    }
    </style>
</head>
<body>
<div class="main-content">
<div class="page-content">
	<div class="dateStyle">
		<label>开始日期</label>
		<input class="datainp" id="dateStart" type="text" placeholder="请选择" readonly>
		<label>结束日期</label>
		<input class="datainp" id="dateEnd" type="text" placeholder="请选择" readonly>
		<input class="btn btn-default getData-btn" id="getData"  type="button" name="getData" value="获取数据">
		<input class="btn btn-default getData-btn" id="changeColor" type="button" name="changeColor" value="配置图信息" data-toggle="modal" data-target="#configChartModal" >
	</div>
	<div class="changeColor-div">
<!--         <#if (params?size>0) > -->
<!-- 	       	<#list params as param> -->
<!-- 	       		<div class="form-group"> -->
<!-- 					<input type="text" id="${param.value}" name="${param.value}" dataName="${param.name}" class="demo" style="height: 25px;width: 55px;border: none;" data-position="bottom left" value="#0088cc"> -->
<!-- 					<label for="${param.value}">${param.name}</label> -->
<!-- 				</div> -->
<!-- 	       	</#list>       	 -->
<!-- 		</#if> -->
<!-- 		<div class="form-group"> -->
<!-- 			<button name="changeColor" id="changeColor"> 确定改变颜色 </button> -->
<!-- 		</div> -->
		<!-- 配置图表信息表单 -->
		<div class="modal fade" id="configChartModal" tabindex="-1" role="dialog" aria-labelledby="configChartModalLabel"  >
		  <div class="modal-dialog" role="document" style="margin:35px 180px" >
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="configChartModalLabel">配置图参数</h4>
		      </div>
		      <div class="modal-body">
		      	<form id="configChartForm" class="form-horizontal" role="form">
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-5 control-label"> 参数名称</label>
						<label class="col-sm-3 control-label"> 颜色</label>
						<label class="col-sm-3 control-label"> 大小</label>
					</div>
					<#if (params?size>0) > 
						<#list params as param>
							<div class="space-4"></div>
							<div class="form-group">
								<label class="col-sm-5 control-label"> ${param.name} </label>
								<div class="col-sm-3">
									<input type="text" id="${param.value}-color" name="${param.value}" dataName="${param.name}" class=" form-control demo" style="border: none;" data-position="bottom left">					       				
								</div>
								<div class="col-sm-3">
									<select class="form-control" id="${param.value}-size">
										<option value="1">1</option>
										<option value="2" selected="true">2</option>
										<option value="3">3</option>
									</select>
								</div>
							</div>
						</#list>
					</#if>
				</form>
		      </div>
		      <div class="modal-footer">
		      	<div class="col-lg-4 col-lg-offset-5">
			        <button type="button" class="btn btn-default" id="add-series-close" data-dismiss="modal">关闭</button>
			        <button type="button" class="btn btn-primary" data-dismiss="modal" id="submit_configChart">确定</button>
                   </div>
		      </div>
		    </div>
		  </div>
		</div>
	</div>
	<hr/>
	<div id="main" style="width: 850px;height:480px;"></div>
</div><!-- /.page-content -->		
</div><!-- /.main-content -->	
</body>
<script type="text/javascript">
	jeDate({
		dateCell:"#dateStart",//直接显示日期层的容器，可以是ID  CLASS
		format:"YYYY-MM-DD hh:mm:ss",//日期格式
		isinitVal:false, //是否初始化时间
		startMin:"",//清除日期后返回到预设的最小日期
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
    	var paramObject={};	
    	//从滚轮处获取到的画布开始时间和结束时间
    	var startDate ="";
		var endDate ="";
		//从加载画布的tap页面上的日历控件中获取到的时间
		var startDate_tap ="";
		var endDate_tap ="";
		 		
    	var myChart = echarts.init(document.getElementById('main'),'vintage');
    	var seriesOptions = []
    	var pSeriesOptions = []
        var seriesCounter = 0
        var date = [];
        var pDate = [];
        var names = [];
        var tuli=[];
        var mouseover_message=[];
        var paramSize= ${params?size};
        <#if (params?size>0) >
        	<#list params as param>
		        var n={};
        		n.name = '${param.name}';
        		n.value = '${param.value}';
        		n.y = '${param.yname}';
        		n.max = '${param.max}';
        		n.min = '${param.min}'
        		console.log("每条曲线的参数：参数名"+n.name+"*参数值:"+n.value+"*Y轴:"+n.y+"*最大值:"+n.max+"*最小值："+n.min);
        		names.push(n);
        	</#list>       	
        <#else>
        	names = ['flywheel_a_speed','flywheel_f_motor_current'];
		</#if>
        
         var options = {
       		//color:['green'],
            tooltip: {
                trigger: 'axis'
           	//      formatter: function (params) {
			//	     mouseover_message = params;
 			//		}           
            },
            title: {
            	text: '分析图',
                //left: 'center'
            },
           	// 图例	
            legend: {
                top: 'auto', 
                data: names
            },
            //工具栏
            toolbox: {
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    magicType : {show: true, type: ['line', 'bar']},
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
            		name: 'Y1轴',
                	type: 'value',
                	splitLine : {
		                show: true
		            }
            	},{
            		name: 'Y2轴',
		            type : 'value',
		            splitLine : {
		                show: false
		            }
	        	}
            ],
            /*dataZoom: [{
                type: 'inside',
                start:0,
                end:100,
                realtime:false
            }],*/
            dataZoom: [
        	{
        		type:'slider',
            	show: true,
            	realtime: true,
            	start: 1,
            	end: 99
        	},
        	{
            	type: 'inside',
            	realtime: true,
            	start: 1,
            	end: 99
        	}],
        	
        	//时间轴
        	/*timeline: {
            show : true,
            type: 'slider',
            axisType: 'category',
            currentIndex:0,
            data:[
				    '1S',
				    '5S',
				    '15s',
				    '30s',
				    '1m',
				    '15m',
				    '30m',
				    '1h',
				    '6d',
				    '12d',
				    '30d',
				]
        	},*/
            
            series: []
        };    	     	
   
   		//将一组中的所有参数组装为object
   		paramObject.nowSeries='${nowSeries}';
   		paramObject.nowStar='${nowStar}';
   		paramObject.component='${component}';
   		paramObject.startTime='${beginDate}';
   		paramObject.endTime='${endDate}';
   		paramObject.paramAttribute=names;

 
   		  		
     	//一次性组装所有参数的x和y值
   		$.ajax({
             url: '${base}/getData',
             type: 'POST',
             dataType: 'json',
             timeout: 100000,
             cache: false,
             data: {'paramObject':JSON.stringify(paramObject)},
              //成功执行方法
             success: function(data){
             	  //var json = eval(data);
             	  var i=0
             	  var yname = 0;
             	  var legendname ="";
             	  for(var param in data){
             	  yname  = names[i].y;
             	  legendname =names[i].name;
             	  console.log(yname+legendname)
             	  	seriesOptions[i++] = {
			            	type: 'line',
			                //name: param,
			                name:legendname,
			                smooth:false,
			               	yAxisIndex: yname,
			                lineStyle:{
		                    	normal:{
		                    		width:0.5 
		                    		}
		                    },
			                data: data[param].paramValue
			            };
			            //设置X轴，注意，这里X轴存在问题，默认使用了最后一组参数的X轴
			            date =  data[param].yearValue;
             	  }
             	  	pSeriesOptions = seriesOptions
	            	options.series = eval(seriesOptions);
	            	pDate=options.xAxis.data = date;
	                myChart.setOption(options);
             }
         })

     
   		//单个组装参数的x和y值
    //    $.each(names, function (i, n) {       
	//        $.getJSON('${base}/getData?nowSeries=${nowSeries}&nowStar=${nowStar}&component=${component}&start=${beginDate}&end=${endDate}&paramSize='+paramSize+'&filename=' + n.value, function (data) {	
	         //   console.log(data)
	 //           seriesOptions[i] = {
	 //           	type: 'line',
	 //               name: n.name,
	 //               smooth:true,
	 //               yAxisIndex: n.y,
	  //              lineStyle:{
      //              	normal:{
       //             		color:'dark',
     //               		width:2,
        //            	}
       //             },
	    //            data: data["paramValue"]
	   //         };
	   //         seriesCounter += 1;
	   //         if (seriesCounter === names.length) {
	   //         	pSeriesOptions = seriesOptions
	   //         	options.series = eval(seriesOptions);
	    //        	pDate=date=options.xAxis.data = data["yearValue"];	            
	    //            myChart.setOption(options);	                
	    //        }
	 //       });
    //	});   
       	
       	$("#getData").click(function(){
       		startDate =  $('#dateStart').val();
       		endDate =  $('#dateEnd').val();
       		if(endDate && startDate){
       			/*var seriesCounter_date = 0  
       			var seriesOptionsDate = []	
       			  $.each(names, function (i, n) {       
			        $.getJSON('${base}/getData?start='+startDate+'&end='+endDate+'&paramSize='+paramSize+'&filename=' + n.value, function (data) {	
			            seriesOptionsDate[i] = {
			            	type: 'line',
			                name: n.name,
			                smooth:true,
			                yAxisIndex: 1,
			                data: data["paramValue"]
			            };
			            seriesCounter_date += 1;
			            if (seriesCounter_date === names.length) {			            	
			            	options.series = eval(seriesOptionsDate);
			            	date=options.xAxis.data = data["yearValue"];	            
			                myChart.setOption(options);
			                //myChart.setTheme(vintage);             
			            }
			        });
		    	});*/
//修改之后
$.post("getDatabytap", 
		{
			'startDate' : startDate,
			'endDate' : endDate
		},
		function(data){
             	  var i=0
             	  var yname = 0;
             	  var legendname ="";
             	  for(var param in data){
             	  yname  = names[i].y;
             	  legendname =names[i].name;
             	  console.log(yname+legendname)
             	  	seriesOptions[i++] = {
			            	type: 'line',
			                //name: param,
			                name:legendname,
			                smooth:false,
			               	yAxisIndex: yname,
			                lineStyle:{
		                    	normal:{
		                    		width:0.5 
		                    		}
		                    },
			                data: data[param].paramValue
			            };
			            //设置X轴，注意，这里X轴存在问题，默认使用了最后一组参数的X轴
			            date =  data[param].yearValue;
  		}
  	pSeriesOptions = seriesOptions
	options.series = eval(seriesOptions);
	pDate=options.xAxis.data = date;
    myChart.setOption(options);
	
	});
		    	   
       		}       		      	             	
       	})
       	
       	
       	myChart.on('restore' ,function(params){
       		options.series = eval(pSeriesOptions);
            date=options.xAxis.data = pDate;	
            myChart.setOption(options);
       		
       	})
       	
       	
       	//数据缩放区域事件	  	      
        myChart.on('dataZoom', function (params) {
              //  console.log(params);
               // var endDate = date[params.batch[0].endValue];
              //  var startDate = date[params.batch[0].startValue];
              	var xAxis = myChart.getModel().option.xAxis[0];
				//var startDate = xAxis.data[xAxis.rangeStart]
				//var endDate = xAxis.data[xAxis.rangeEnd];
				startDate = xAxis.data[xAxis.rangeStart]
				endDate = xAxis.data[xAxis.rangeEnd];
				$('#dateStart').val(startDate);
       			$('#dateEnd').val(endDate);
              	console.log("开始日期"+startDate+"结束日期："+endDate);
              	var a= stringToDate(startDate);
              	var b= stringToDate(endDate);
              	console.log(a+b);
              	console.log(b-a);
              	var timespace = (b-a)/60000;
                //var time =DateDiff("s", a, b);
              	console.log(timespace);
              	//alert(time);
              	if(timespace<0){ 
                //if(endDate && startDate){
					var seriesCounter_new = 0    
					var seriesOptionsDam = []	
	                $.each(names, function (i, n) {
	                    /*//$.getJSON('${base}/getData?start='+startDate+'&end='+endDate+'&paramSize='+paramSize+'&filename=' + n.value, function (data) {
	                    $.getJSON('${base}/getData?start='+startDate+'&end='+endDate, function (data) {
	                    	//console.log(data);
	                    	console.log("开始日期"+startDate+"结束日期："+endDate);
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
	                    });*/
$.post("getDatabytap", 
		{
			'startDate' : startDate,
			'endDate' : endDate
		},
	function(data){
//  var json = eval(data);
  var i=0
  //debugger;
  var yname = 0;
  for(var param in data){
  yname  = names[i].y;
  console.log(yname)
  	seriesOptions[i++] = {
        	type: 'line',
            name: param,
            smooth:false,
           	yAxisIndex: yname,
            lineStyle:{
            	normal:{
            		width:0.5 
            		}
            },
            data: data[param].paramValue
        };
        //设置X轴，注意，这里X轴存在问题，默认使用了最后一组参数的X轴
        date =  data[param].yearValue;
  }
  	pSeriesOptions = seriesOptions
	options.series = eval(seriesOptions);
	pDate=options.xAxis.data = date;
    myChart.setOption(options);
	
	});
	
	                });
                }
               
        });

    	//设置颜色
    	$('.demo').each( function() {
			$(this).minicolors({
				control: $(this).attr('data-control') || 'hue',
				defaultValue: $(this).attr('data-defaultValue') || '',
				inline: $(this).attr('data-inline') === 'true',
				letterCase: $(this).attr('data-letterCase') || 'lowercase',
				opacity: $(this).attr('data-opacity'),
				position: $(this).attr('data-position') || 'bottom left',
				change: function(hex, opacity) {
					var log;
					try {
						log = hex ? hex : 'transparent';
						if( opacity ) log += ', ' + opacity;
						//console.log(log);
						$(this).attr('value',log);
					} catch(e) {}
				},
				theme: 'default'		                  
        	});
		});
    	
        $('#submit_configChart').click(function(){
	      	var seriesOptionsDam = [];
        	$('.demo').each( function(i, n) {
        	  var paramValue = $(this).attr('name');
        	  var paramColor = $(this).attr('value');
        	  var width = $('#'+paramValue+'-size').val();
        	  if(paramColor != null && paramColor != 'undefined'){
		      	  seriesOptionsDam[i] = {
		      			name: $(this).attr('dataName'),
		  		        type: 'line',
		  	            lineStyle:{
		  	            	normal:{
		  	            		color: paramColor,
				  		        width: width,
		  	            		}}
		           };
        	  }
        	});
        	//console.log(seriesOptionsDam);
        	options.series = eval(seriesOptionsDam);
            myChart.setOption(options);
        })
    })
 </script>	
</html>
