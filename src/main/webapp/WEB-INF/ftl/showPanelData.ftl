<@override name="link">
	<link href="${base}/static/css/cssnew.css" rel="stylesheet"/>
</@override>
<@override name="script">
	<script src="${base}/static/js/jquery-1.8.3.min.js"></script>
	<script src="${base}/static/js/highcharts.js"></script>
	<script>
	$(function () {
    	var seriesOptions = new Array();
    	<#if (dataArray?size>0)>   	
    		var xDataArray= ${dataArray}
    	<#else>
    		var xDataArray=[]
    	</#if>
        seriesCounter = 0,
        var names = ["flywheel_a_motor_current","flywheel_a_power_plus_5V","flywheel_b_motor_current"];

    /**
     * Create the chart when all data is loaded
     * @returns {undefined}
     */
    function createChart() {

        $('#container').highcharts( {
        
			xAxis: {
		            tickPositions: xDataArray
		        },		

            yAxis: {
                labels: {
                    formatter: function () {
                        return (this.value > 0 ? ' + ' : '') + this.value + '%';
                    }
                },
                plotLines: [{
                    value: 0,
                    width: 2,
                    color: 'silver'
                }]
            },

            plotOptions: {
                series: {
                    compare: 'percent'
                }
            },

            tooltip: {
                pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.change}%)<br/>',
                valueDecimals: 2
            },

            series: seriesOptions
        });
    }

    $.each(names, function (i, name) {
		
		$.ajax({
             type: "POST",
             url: "${base}/getJsonData",
             data: {filename:name.toLowerCase()},
             dataType: "json",
             success: function(data){
                seriesOptions[i] = {
                name: name,
                data: data
            };
            seriesCounter += 1;
            if (seriesCounter === names.length) {
                createChart();
         	   }    
         });
				
    	});
	});
		
	</script>
</@override>
<@override name="content">
	<div id="container" style="height: 400px; width: 900px; margin: 0 auto"></div>
</@override>
<@extends name="headBase.ftl"/>

C:\Users\Administrator\AppData\Local\GitHub\imkeh4ow.53f.deleteme\mingw32\bin;C:\Users\Administrator\AppData\Local\GitHub\imkeh4ow.53f.deleteme\cmd;C:\Go\bin;F:\tomcat8.0\apache-tomcat-8.0.28\bin;F:\myeclipse2015\binary\com.sun.java.jdk7.win32.x86_64_1.7.0.u45\bin;F:\maven3.3.3\apache-maven-3.3.3\bin;C:\ProgramData\Oracle\Java\javapath;%SystemRoot%\system32;%SystemRoot%;%SystemRoot%\System32\Wbem;%SYSTEMROOT%\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\ATI Technologies\ATI.ACE\Core-Static;C:\Program Files\TortoiseSVN\bin;F:\mysql\bin;C:\Go\bin;E:\Programnodejs\nodejs\

