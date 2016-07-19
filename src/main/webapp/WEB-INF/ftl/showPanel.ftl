<@override name="link">
	<link href="${base}/static/css/cssnew.css" rel="stylesheet"/>
</@override>
<@override name="script">
	<script src="${base}/static/js/jquery-1.8.3.min.js"></script>
	<script src="${base}/static/js/highcharts.js"></script>
	<script>
		$(function () {
		    $('#container').highcharts({
		
		        title: {
		            text: 'Custom tick positions'
		        },		
		        subtitle: {
		            text: 'through axis.tickPositions and axis.tickPositioner'
		        },
				xAxis: {
		            tickPositions: [0, 1, 2, 4, 8]
		        },		
		        yAxis: [{ // Primary yAxis
		        	tickPositions: [-35, 30, -25, -20, -15,-10,-5,0,5,10,15],
		        	gridLineWidth: 0,
		        	lineWidth:1,
		        	tickWidth: 1,
		            labels: {
		            
		                style: {
		                    color: '#89A54E'
		                }
		            },
		            title: {
		                text: 'Temperature',
		                style: {
		                    color: '#89A54E'
		                }
		            }
		        }, { // Secondary yAxis
		        	gridLineWidth: 0,
		            title: {
		                text: 'Rainfall',
		                style: {
		                    color: '#4572A7'
		                }
		            },
		            labels: {

		                style: {
		                    color: '#4572A7'
		                }
		            },
		            tickPositions: [ 0, 2, 4, 6,8,10],
		            opposite: true
		        }],
		        		
		        series: [{
		            data: [
		                [0, 1],
		                [1, 3],
		                [2, 2],
		                [4, 4],
		                [8, 3]
		            ]
		        }]
		    });
		});
	
	</script>
</@override>

<@override name="content">
	<div id="container" style="height: 400px; width: 900px; margin: 0 auto"></div>
</@override>
<@extends name="headBase.ftl"/>
