<#assign base=request.contextPath />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>喊单收益曲线图</title>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <script src="${base}/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="${base}/static/js/highcharts.js" type="text/javascript"></script>
    <script src="${base}/static/js/exporting.js" type="text/javascript"></script>
</head>
<body style="margin: auto;">
    <!-- 喊单收益曲线图 -->
    <div id="container" style="width: 100%; height: 100%; margin: 0 auto">
    </div>
</body>
</html>
<script type="text/javascript" language="javascript">
    /*
    * 官方文档： http://www.highcharts.me/
    */
    var chart, myTime = "", myData = "";

    $(function () {
        //盈利率
        myData = "[-20.25,-12.30,-3.68,5.45,19.00,-2.78,-5.45,9.80,10.45,16.15]";
        options.series[0].data = eval(myData);

        //时间
        myTime = "['2014-03-01 01:10','2014-03-01 01:30','2014-03-01 01:45','2014-03-01 02:30','2014-03-01 02:45','2014-03-01 03:00','2014-03-01 03:40','2014-03-01 04:50','2014-03-01 05:05','2014-03-01 06:06']";
        options.xAxis.categories = eval(myTime);

        //加载图表
        chart = new Highcharts.Chart(options);
    });

    var options = {
        chart: {
            renderTo: 'container',
            zoomType: 'x'
        },
        title: {
            text: '喊单收益曲线图'
        },
        subtitle: {
            text: '点击并拖动绘图区域的放大'
        },
        xAxis: {
            title: {
                text: null
            },
            labels: {
                formatter: function () {
                    return this.value;
                }
            }
        },
        yAxis: {
            title: {
                text: '盈利率'
            },
            labels: {
                formatter: function () {
                    return this.value + "%";
                }
            },
            startOnTick: false,
            showFirstLabel: false
        },
        tooltip: {
            formatter: function () {
                return '' + '日期：' + this.x
                          + '盈利率：' + Highcharts.numberFormat(this.y, 2) + "%";
            }
        },
        legend: {
            enabled: false
        },
        plotOptions: {
            area: {
                fillColor: {
                    linearGradient: [0, 0, 0, 300],
                    stops: [
                                    [0, '#4572A7'],
                                [1, 'rgba(2,0,0,0)']
                                                   ]
                },
                lineWidth: 1,
                marker: {
                    enabled: false,
                    states: {
                        hover: {
                            enabled: true,
                            radius: 5
                        }
                    }
                },
                shadow: false,
                states: {
                    hover: {
                        lineWidth: 1
                    }
                }
            }
        },
        series: [{
            type: 'zhebarea',
            name: '盈利率'
        }]
    }
</script>
