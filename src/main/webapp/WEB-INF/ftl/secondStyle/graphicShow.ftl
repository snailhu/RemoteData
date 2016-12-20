<#assign base=request.contextPath + '/DataRemote' />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="${base}/static/js/jquery-2.0.3.min.js"></script>

	<script  src="${base}/static/js/jquery.mousewheel.js"></script>
    <script  src="${base}/static/showLoading/js/jquery.showLoading.min.js"></script>
    <link rel="stylesheet"  href="${base}/static/showLoading/css/showLoading.css" />
    <script type="text/javascript" src="${base}/static/scripts/echarts.min.js"></script>
    <script type="text/javascript" src="${base}/static/scripts/vintage.js"></script>
    <script type="text/javascript" src="${base}/static/js/conditionMonitoring/datediff.js"></script>
    <!--<script type="text/javascript" src="${base}/static/scripts/test.js"></script>-->
    <!-- 时间选择器 -->
    <link type="text/css" rel="stylesheet" href="${base}/static/content/jeDate/jedate/skin/jedate.css">
    <script type="text/javascript" src="${base}/static/content/jeDate/jedate/jedate.js"></script>
    <!-- 颜色选择器 -->
    	<link rel="stylesheet" href="${base}/static/content/minicolors/bootstrap.min.css">
		<script type="text/javascript" src="${base}/static/content/minicolors/bootstrap.min.js"></script>
		<link rel="stylesheet" href="${base}/static/content/minicolors/jquery.minicolors.css">
		<script type="text/javascript" src="${base}/static/content/minicolors/jquery.minicolors.js"></script>
		<script type="text/javascript" src="${base}/static/content/minicolors/jquery.colorpicker.js"></script>
    
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
	<!-- 颜色选择器jscolor -->
	<!-- <script type="text/javascript" src="${base}/static/content/minicolors/jscolor.js"></script> -->
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
	#add-series-close{
	color: white;
    font-size: 14px;
    width: 80px;
    background-color: #abbac3;
    height: 34px;
    border-width: 0;
	margin-left: 20px;
	}
	#add-series-close:hover{background-color:#8b9aa3;border-color:#abbac3;}
	#submit_configChart{ width: 80px;}
	.col-lg-4{text-align:center;}
    </style>
</head>
<body>
<div class="main-content">
<div class="page-content">
	<div class="dateStyle">
		<label>开始日期</label>
		<input class="datainp" id="dateStart" type="text" placeholder="- -请选择开始时间- -" readonly>
		<label>结束日期</label>
		<input class="datainp" id="dateEnd" type="text" placeholder="- -请选择结束时间- -" readonly>
		<!--<input class="btn btn-default getData-btn" id="getData"  type="button" name="getData" value="获取数据">-->
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
									<input type="text" id="${param.value}-color" name="${param.value}" dataName="${param.name}" class=" form-control demo jscolor" style="border: none;" data-position="bottom left" value="">					       				
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
			        <button type="button" class="btn btn-primary" data-dismiss="modal" id="submit_configChart">确定</button>
			        <button type="button" class="btn btn-default" id="add-series-close" data-dismiss="modal">关闭</button>
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
	Date.prototype.pattern=function(fmt) {          
       	    var o = {          
       	    "M+" : this.getMonth()+1, //月份          
       	    "d+" : this.getDate(), //日          
       	    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时          
       	    "H+" : this.getHours(), //小时          
       	    "m+" : this.getMinutes(), //分          
       	    "s+" : this.getSeconds(), //秒          
       	    "q+" : Math.floor((this.getMonth()+3)/3), //季度          
       	    "S" : this.getMilliseconds() //毫秒          
       	    };          
       	    var week = {          
       	    "0" : "\u65e5",          
       	    "1" : "\u4e00",          
       	    "2" : "\u4e8c",          
       	    "3" : "\u4e09",          
       	    "4" : "\u56db",          
       	    "5" : "\u4e94",          
       	    "6" : "\u516d"         
       	    };          
       	    if(/(y+)/.test(fmt)){          
       	        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));          
       	    }          
       	    if(/(E+)/.test(fmt)){          
       	        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);          
       	    }          
       	    for(var k in o){          
       	        if(new RegExp("("+ k +")").test(fmt)){          
       	            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));          
       	        }          
       	    }          
       	    return fmt;          
       	}  

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
	
	//从滚轮处获取到的画布开始时间和结束时间
	var startDate ="";
	var endDate ="";
	//当前echars画布中最大时间区间
	var startDate_init = '${beginDate}';
	var endDate_init = '${endDate}';
	//实时时间跨度
	var timeZone = stringToDate(endDate_init) - stringToDate(startDate_init)/60000;

    $(function(){
    	//修改页面缩放，界面显示不正常
    	$(".modal-footer").css("text-align","center");
    	$(".modal-footer").find("button").css({"margin-right":"20px","width":"80px"});
    	$(".modal-dialog").css("margin","20px auto");
    	
    	var paramObject={};	
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
        		n.unit = '${param.unit}';
        		n.y = '${param.yname}';
        		n.max = '${param.max}';
        		n.min = '${param.min}';
        		console.log("每条曲线的参数：参数名"+n.name+"*参数值:"+n.value+"单位"+n.unit+"*Y轴:"+n.y+"*最大值:"+n.max+"*最小值："+n.min);
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
             	  var unit="";          	  
             	  var Y1name ="Y1轴";
             	  var Y1nametemp ="Y1轴";
             	  var Y2name ="Y2轴";
             	  var Y2nametemp ="Y2轴";
             	  
             	  for(var param in data){             	  
	             	  unit = names[names.length-1-i].unit;
	             	  yname  = names[names.length-1-i].y;
	  				  if(yname==0)
	  				  {
	  				  	Y1nametemp = Y1name;
	  				  	Y1name ="Y1轴("+unit+")";
	  				  	console.log(Y1nametemp+"-------"+Y1name)	  				  	
	  				  	if((Y1name!=Y1nametemp))
	  				  	{
	  				  		if(Y1nametemp=="Y1轴")
	  				  		{}
	  				  		else{
	  				  			Y1name ="Y1轴";
	  				  		}	
	  				  	}
	  				  	
	  				  }
	  				  if(yname==1)
	  				  {
	  				    var Y2nametemp =Y2name;
	  				  	Y2name ="Y2轴("+unit+")";	  				  	
	  				  	if(Y2name!=Y2nametemp)
	  				  	{
	  				  		Y2name ="Y2轴";
	  				  	}
	  				  }
  				  
	             	  legendname =names[names.length-1-i].name;
	             	  console.log(names.length);
	             	  console.log(yname+legendname+unit);            	  	
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
             	  
             	  //修改Y轴坐标单位
             	  var YAxixs =[{
				        		name: Y1name,
				            	type: 'value',
				            	splitLine : {
					                show: true
					            }
				        	},{
				        		name: Y2name,
					            type : 'value',
					            splitLine : {
					                show: false
					            }
				        	}
				        ]
             	 	
             	  	pSeriesOptions = seriesOptions;
	            	options.series = eval(seriesOptions);
	            	options.yAxis =YAxixs;
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
       		startDate_init = startDate;
       		endDate_init = endDate;
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
	startDate_init = startDate;
    endDate_init = endDate;
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
        		console.log(myChart.getOption().series[0].type);
        	  	var lineType = myChart.getOption().series[0].type;
              	var xAxis = myChart.getModel().option.xAxis[0];
				if(xAxis.data[xAxis.rangeStart]&&xAxis.data[xAxis.rangeEnd]){
					startDate = xAxis.data[xAxis.rangeStart]
					endDate = xAxis.data[xAxis.rangeEnd];
					var a= stringToDate(startDate);
              		var b= stringToDate(endDate);
					$('#dateStart').val(startDate);
       				$('#dateEnd').val(endDate);
       				timeZone = (b-a)/60000;
       				console.log(timeZone);
				}else{
					if(timeZone>10){
						startDate = startDate_init;
						endDate = endDate_init;	
					}
				}
				$('#dateStart').val(startDate);
       			$('#dateEnd').val(endDate);
              	console.log("开始日期"+startDate+"结束日期："+endDate);
              	if(timeZone>10){
              		 if(needGetDate()){
						                	$("#main").showLoading(); 
											var a= stringToDate(startDate);
							              	var b= stringToDate(endDate);
							              	console.log(a);
							              	console.log(b);
							              	startDate = a.pattern("yyyy-MM-dd HH:mm:ss");
							              	endDate = b.pattern("yyyy-MM-dd HH:mm:ss");
							              	console.log(startDate);
							              	console.log(endDate);
								       		//将一组中的所有参数组装为object
								       		paramObject.nowSeries='${nowSeries}';
								       		paramObject.nowStar='${nowStar}';
								       		paramObject.component='${component}';
								       		paramObject.startTime=startDate;
								       		paramObject.endTime=endDate;
								       		paramObject.paramAttribute=names;
								       		  		
								       		 console.log("开始请求后台时间："+new Date());
								       		  console.log(JSON.stringify(paramObject));
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
								                 	 console.log("请求后台完成时间："+new Date());
								                	 $("#main").hideLoading(); 
								                	 console.log("111"+startDate_init);
											         console.log("111"+endDate_init);
								                	 startDate_init = startDate;
													 endDate_init = endDate;	
													 $('#dateStart').val(startDate);
						       						 $('#dateEnd').val(endDate);
								                	 //var json = eval(data);
								                 	  var i=0
								                 	  var yname = 0;
								                 	  var legendname ="";
								                 	  for(var param in data){
								                 	  
								                 	  yname  = names[names.length-1-i].y;
						             	  			  legendname =names[names.length-1-i].name;
								                 	  
								                 	  console.log(yname+legendname)
								                 	  	seriesOptions[i++] = {
								    			            	type: lineType,
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
								    	                 console.log("画图完成时间："+new Date());
								                 }
								             })  	
						                	
						                
						//                 	$("#main").showLoading(); 
						// 	 				var seriesCounter_new = 0    
						// 					var seriesOptionsDam = []	
						// 	                $.each(names, function (i, n) {
						// 	                    //$.getJSON('${base}/getData?start='+startDate+'&end='+endDate+'&paramSize='+paramSize+'&filename=' + n.value, function (data) {
						// 	                    $.getJSON('${base}/getData?start='+startDate+'&end='+endDate, function (data) {
						// 	                    	 $("#main").hideLoading(); 
						// 	                    	//console.log(data);
						// 	                    	console.log("开始日期"+startDate+"结束日期："+endDate);
						// 	                        seriesOptionsDam[i] = {
						// 	                            type: 'line',
						// 	                            smooth:true,
						// 	                            name: n.name,
						// 	                            yAxisIndex: n.y,
						// 	                            data: data["paramValue"]
						// 	                        };
						// 	                        seriesCounter_new += 1;
						// 	                        if (seriesCounter_new === names.length) {
						// 	                            options.series = eval(seriesOptionsDam);
						// 	                            date=options.xAxis.data = data["yearValue"];
						// 	                            startDate_init = startDate;
						// 								endDate_init = endDate;	
						// 								startDate_init = startDate;
						//        							endDate_init = endDate;
						// 	                            myChart.setOption(options);
						// 	                        }
						// 	                    });
							                    
						// $.post("getDatabytap", 
						// 		{
						// 			'startDate' : startDate,
						// 			'endDate' : endDate
						// 		},
						// 	function(data){
						// //  var json = eval(data);
						//   var i=0
						//   //debugger;
						//   var yname = 0;
						//   for(var param in data){
						//   yname  = names[i].y;
						//   console.log(yname)
						//   	seriesOptions[i++] = {
						//         	type: 'line',
						//             name: param,
						//             smooth:false,
						//            	yAxisIndex: yname,
						//             lineStyle:{
						//             	normal:{
						//             		width:0.5 
						//             		}
						//             },
						//             data: data[param].paramValue
						//         };
						//         //设置X轴，注意，这里X轴存在问题，默认使用了最后一组参数的X轴
						//         date =  data[param].yearValue;
						//   }
						//   	pSeriesOptions = seriesOptions
						// 	options.series = eval(seriesOptions);
						// 	pDate=options.xAxis.data = date;
						// 	startDate_init = startDate;
						// 	endDate_init = endDate;
						//     myChart.setOption(options);
							
						// 	});
							
						// 	                });
					}
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
    
    function needGetDate(){
		var startInit = stringToDate(startDate_init);
		var endInit = stringToDate(endDate_init);
		var timespace = (endInit-startInit)/60000;
		var startYear = startInit.getFullYear();
		var startMonth = startInit.getMonth();
		var startDay = startInit.getDate();
		var startHours = startInit.getHours();
		var startMinutes = startInit.getMinutes();
		var startSeconds = startInit.getSeconds();
		var endYear = endInit.getFullYear();
		var endMonth = endInit.getMonth();
		var endDay = endInit.getDate();
		var endHours = endInit.getHours();
		var endMinutes = endInit.getMinutes();
		var endSeconds = endInit.getSeconds();
		var a= stringToDate(startDate);
      	var b= stringToDate(endDate);
      	
		
		if(timespace <= 1440){
		console.log("选择的时间在一天之内"+timespace);
			//一天以内，1小时增减
			if(startDate_init==startDate&&endDate_init==endDate){
				startInit.setMinutes(startMinutes-30);
				endInit.setMinutes(endMinutes+30);
				startDate = startInit.pattern("yyyy-MM-dd HH:mm:ss");
				endDate = endInit.pattern("yyyy-MM-dd HH:mm:ss");
				startDate_init = startDate;
				endDate_init = endDate;
				return true;
			}else{
				//原始数据不请求后台
				return false;
			}
			return false;
		}
		if(timespace > 1440 && timespace <=2880){
			//一天到两天，两小时增减
			if(startDate_init==startDate&&endDate_init==endDate){
				startInit.setHours(startHours-1);
				endInit.setHours(endHours+1);
				startDate = startInit.pattern("yyyy-MM-dd HH:mm:ss");
				endDate = endInit.pattern("yyyy-MM-dd HH:mm:ss");
				startDate_init = startDate;
				endDate_init = endDate;
				return true;
			}else{
				console.log(startDate);
				if(a/60000 - startInit/60000 >= 60 || endInit/60000-b/60000 >= 60){
					return true;
				}else{
					return false;
				}
			}
		}
		if(timespace > 2880 && timespace <=10080){
			//两天到一周，一天增减
			if(startDate_init==startDate&&endDate_init==endDate){
				startInit.setHours(startHours-12);
				endInit.setHours(endHours+12);
				startDate = startInit.pattern("yyyy-MM-dd HH:mm:ss");
				endDate = endInit.pattern("yyyy-MM-dd HH:mm:ss");
				startDate_init = startDate;
				endDate_init = endDate;
				return true;
			}else{
				if(a/60000-startInit/60000 >= 720 || endInit/60000-b/60000 >= 720){
					return true;
				}else{
					return false;
				}
			}
		}
		if(timespace > 10080 && timespace <=43200){
			//一周到一个月，四天天增减
			if(startDate_init==startDate&&endDate_init==endDate){
				startInit.setDate(startDay-2);
				endInit.setDate(endDay+2);
				startDate = startInit.pattern("yyyy-MM-dd HH:mm:ss");
				endDate = endInit.pattern("yyyy-MM-dd HH:mm:ss");
				startDate_init = startDate;
				endDate_init = endDate;
				return true;
			}else{
				console.log(startDate);
				if(a/60000 - startInit/60000 >= 2880 || endInit/60000-b/60000 >= 2880){
					return true;
				}else{
					return false;
				}
			}
		}
		if(timespace > 43200 && timespace <=518400){
			//一月到一年，一月增减
			if(startDate_init==startDate&&endDate_init==endDate){
				startInit.setDate(startDay-15);
				endInit.setDate(endDay+15);
				startDate = startInit.pattern("yyyy-MM-dd HH:mm:ss");
				endDate = endInit.pattern("yyyy-MM-dd HH:mm:ss");
				startDate_init = startDate;
				endDate_init = endDate;
				return true;
			}else{
				console.log(startDate);
				if(a/60000 - startInit/60000 >= 21600 || endInit/60000-b/60000 >= 21600){
					return true;
				}else{
					return false;
				}
			}
		}
		if(timespace > 518400){
			//大于一年，六个月增减
			if(startDate_init==startDate&&endDate_init==endDate){
				startInit.setMonth(startMonth-3);
				endInit.setMonth(endMonth+3);
				startDate = startInit.pattern("yyyy-MM-dd HH:mm:ss");
				endDate = endInit.pattern("yyyy-MM-dd HH:mm:ss");
				startDate_init = startDate;
				endDate_init = endDate;
				return true;
			}else{
				console.log(startDate);
				if(a/60000 - startInit/60000 >= 129600 || endInit/60000-b/60000 >= 129600){
					return true;
				}else{
					return false;
				}
			}
		}
	}
 </script>	
</html>
