<#assign base=request.contextPath + '/DataRemote' />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>综合预览页面</title>
<link href="${base}/static/css/bootstrap.css" rel="stylesheet" />   
<script type="text/javascript" src="${base}/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${base}/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${base}/static/js/conditionMonitoring/datediff.js"></script>
<script type="text/javascript" src="${base}/static/js/conditionMonitoring/intsatellite.js"></script>
<script type="text/javascript" src="${base}/static/js/conditionMonitoring/series-button.js"></script> 	
<script type="text/javascript">
	var currentDate =new Date();
	var beginDate=new Date(2016,1,1,1,1,1)
	var runtime=DateDiff('d','2016-6-6','2016-1-1');
	
	//卫星信息显示悬浮窗口Title
	function title() {
	    return '卫星运行状态信息';
	}
	//卫星信息显示悬浮窗口，动态加载内容(真实情况可能会跟后台进行ajax交互)
	function content(id,begintime,rundate) {
	    var data = $("<form><ul><li><span aria-hidden='true' class='icon_globe'></span>&nbsp;<font>卫星代号:</font>"+id+"</li>" +
	             "<li><span aria-hidden='true' class='icon_piechart'></span>&nbsp;<font>开始时间:</font>"+begintime+"</li>" +
	             "<li><span aria-hidden='true' class='icon_search_alt'></span>&nbsp;<font>运行时间:"+rundate+"天</font></li>"); 
	    return data;
	}
	
	//中心点横坐标
	var dotLeft = ($(".container").width()-$(".dot").width())/2-100;
	//中心点纵坐标
	var dotTop = ($(".container").height()-$(".dot").height())/2-100;
	//椭圆长边
	//a = document.documentElement.clientWidth/3;//获取可见区域的宽度和高度
	a=460;
	//椭圆短边
	//b = document.documentElement.clientHeight/3;
	b=150;
	//起始角度
	var stard = 0;
	//每一个BOX对应的角度;
	//var avd = 360/$(".container img").length;
	var avd = 180;
	//每一个BOX对应的弧度;
	var ahd = avd*Math.PI/180;
	//运动的速度
	var speed = 2;
	//图片的宽高
	//var wid = $(".container img").width();
	//var hei = $(".container img").height();
	var wid = $(".imagediv").width();
	var hei = $(".imagediv").height();
	//总的TOP值
	var totTop = dotTop+100;
	//设置圆的中心点的位置
	$(".dot").css({"left":dotLeft,"top":dotTop});
	
	//运动函数
	fun_animat = function(){
	speed = speed<360?speed:2;
	//运运的速度
	speed+=2;
	//运动距离，即运动的弧度数;
	var ainhd = speed*Math.PI/180;
	//按速度来定位DIV元素
	$(".imagediv").each(function(index, element){
	var allpers = (Math.cos((ahd*index+ainhd))*b+dotTop)/totTop;
	var wpers = Math.max(0.1,allpers);
	var hpers = Math.max(0.1,allpers);
	$(this).css({
		"left":Math.sin((ahd*index+ainhd))*a+dotLeft,
		"top":Math.cos((ahd*index+ainhd))*b+dotTop,
		//"z-index":Math.ceil(allpers*10),
		"z-index":2,
		"width":wpers*wid,
		"height":hpers*hei,
		"opacity":1
	});
	});
}
	//延时函数
	var timeout = false; //启动及关闭按钮为fals时开始定时器 | 为true时停止计时
	var timestate = false; //标记定时器状态 false时停止计时 |true时开始计时
	var sportstate = false;//图片运动状态，运动为true;	
	function time()
	{
	  if(timeout) return;
	  //clearTimeout(timeoutProcess);
	  //clearTimeout(time);
	  fun_animat();
	  setTimeout(time,300); //time是指本身,延时递归调用自己,100为间隔调用时间,单位毫秒
	};
	
	$(function(){
		//中心点横坐标
		dotLeft = ($(".container").width()-$(".dot").width())/2-100;
		//中心点纵坐标
		dotTop = ($(".container").height()-$(".dot").height())/2-100;
		//椭圆长边
		a=460;
		//椭圆短边
		b=150;
		//起始角度
		var stard = 0;
		//每一个BOX对应的角度;
		var avd = 180;
		//每一个BOX对应的弧度;
		var ahd = avd*Math.PI/180;
		//运动的速度
		var speed = 2;
		//图片的宽高
		//var wid = $(".container img").width();
		//var hei = $(".container img").height();
		var wid = $(".imagediv").width();
		var hei = $(".imagediv").height();
		//总的TOP值
		var totTop = dotTop+100;
		//设置圆的中心点的位置
		$(".dot").css({"left":dotLeft,"top":dotTop});	
		
		//首页卫星运动函数（虚拟卫星）
		//var fun_animat = function(){
		fun_animat = function(){
		speed = speed<360?speed:2;
		//运运的速度
		speed+=2;
		//运动距离，即运动的弧度数;
		var ainhd = speed*Math.PI/180;
		//按速度来定位DIV元素
		$(".imagediv").each(function(index, element){
		var allpers = (Math.cos((ahd*index+ainhd))*b+dotTop)/totTop;
		var wpers = Math.max(0.1,allpers);
		var hpers = Math.max(0.1,allpers);
		$(this).css({
			"left":Math.sin((ahd*index+ainhd))*a+dotLeft,
			"top":Math.cos((ahd*index+ainhd))*b+dotTop,
			//"z-index":Math.ceil(allpers*10),
			"z-index":2,
			"width":wpers*wid,
			"height":hpers*hei,
			"opacity":1
		});
		});
		}	
		$('#id_div_seriesbtnmanage').seriesbtnmenu({ url: "${base}/getSeriesBtnMenus"});
	});
</script>
<style type="text/css">
	*{margin:0;padding:0;}
	body{
		background:#FFFFFF;
		}	
	.container{position:relative;width:1000px;height:700px; margin:0 auto;z-index:0}
	.dot{ position:absolute;width:20px;height:20px;background:#F00;}
	.container img {
		position:absolute;
		width:auto;height:auto;max-width:100%;max-height:100%;
		azimuth: expression_r(
		this.pngSet?this.pngSet=true:(this.nodeName == "IMG" && this.src.toLowerCase().indexOf('.png')>-1?(this.runtimeStyle.backgroundImage = "none",
		this.runtimeStyle.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + this.src + "', sizingMethod='image')",
		this.src = "transparent.gif"):(this.origBg = this.origBg? this.origBg :this.currentStyle.backgroundImage.toString().replace('url("','').replace('")',''),
		this.runtimeStyle.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + this.origBg + "', sizingMethod='crop')",
		this.runtimeStyle.backgroundImage = "none")),this.pngSet=true);
		}
	.imagediv{position:absolute;width:293px;height:144px;z-index:2}
	.imagediv span{font-size:16px;color:red;position:absolute;left:0px;top:0px;padding:0px;margin:0px}
	.background{position:absolute; width:100%; height:100%; z-index:-1}
	.seriesbutton{position:absolute;width:1000px;height:50px; margin:0 auto;z-index:1}
</style>
</head>
<body>
	<div class="background">    
		<img src="${base}/static/images/earth.jpg" height="100%" width="100%">
		</img>
	</div>
	<div id="id_div_seriesbtnmanage" class="seriesbutton" >
	</div>
	<div id="id_container" class="container">		
	</div>
</body>
</html>
