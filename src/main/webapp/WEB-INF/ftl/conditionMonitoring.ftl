<#assign base=request.contextPath + '/DataRemote' />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卫星状态监控</title>
<link href="${base}/static/css/bootstrap.css" rel="stylesheet" />   
<script type="text/javascript" src="${base}/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${base}/static/js/bootstrap.min.js"></script> 	
<script type="text/javascript">
	//模拟动态加载标题(真实情况可能会跟后台进行ajax交互)
	function title() {
	    return '卫星运行状态信息';
	}
	var currentDate =new Date();
	var beginDate=new Date(2016,1,1,1,1,1)
	
	//两个日期的差值(d1 - d2)
	function DateDiff(d1,d2){
	  	var day = 24 * 60 * 60 *1000;
		try{  
		    var dateArr = d1.split("-");
		  var checkDate = new Date();
		    checkDate.setFullYear(dateArr[0], dateArr[1]-1, dateArr[2]);
		  var checkTime = checkDate.getTime();
		  
		  var dateArr2 = d2.split("-");
		  var checkDate2 = new Date();
		    checkDate2.setFullYear(dateArr2[0], dateArr2[1]-1, dateArr2[2]);
		  var checkTime2 = checkDate2.getTime();
		   
		  var cha = (checkTime - checkTime2)/day; 
		    return cha;
		    alert(cha+"|"+d1+"|"+d2)
		  }catch(e){
		  return false;
		}
	}
	//获取时间差
	Date.prototype.diff = function(date){
	  return (this.getTime() - date.getTime())/(24 * 60 * 60 * 1000);
	}
	//var runtime=DateDiff(currentDate,beginDate);
	var runtime=(currentDate-beginDate)/;
	//alert(currentDate+"|"+beginDate+"|"+runtime)
	//模拟动态加载内容(真实情况可能会跟后台进行ajax交互)
	function content() {
	    var data = $("<form><ul><li><span aria-hidden='true' class='icon_globe'></span>&nbsp;<font>卫星代号:</font>j9-1</li>" +
	             "<li><span aria-hidden='true' class='icon_piechart'></span>&nbsp;<font>开始时间:</font>2016年1月1日</li>" +
	             "<li><span aria-hidden='true' class='icon_search_alt'></span>&nbsp;<font>运行时间:"+runtime+"</font></li>"); 
	    return data;
		
	}
	$(function(){
	
		//中心点横坐标
		var dotLeft = ($(".container").width()-$(".dot").width())/2-100;
		//中心点纵坐标
		var dotTop = ($(".container").height()-$(".dot").height())/2-100;
		//椭圆长边
		//a = document.documentElement.clientWidth/3;//获取可见区域的宽度和高度
		//a = document.body.clientWidth/3;//body区域的宽度和高度
		//a = $(".container").width()/3;
		a=460;
		//椭圆短边
		//b = document.documentElement.clientHeight/3;
		//b = document.body.clientHeight/3;
		//b = $(".container").height()/3;
		b=150;
		//起始角度
		var stard = 0;

		//每一个BOX对应的角度;
		var avd = 360/$(".container img").length;
		//每一个BOX对应的弧度;
		var ahd = avd*Math.PI/180;
		//运动的速度
		var speed = 2;
		//图片的宽高
		var wid = $(".container img").width();
		var hei = $(".container img").height();
		//总的TOP值
		var totTop = dotTop+100;

		//设置圆的中心点的位置
		$(".dot").css({"left":dotLeft,"top":dotTop});
		//调整contain的宽、高
		
		//运动函数
		var fun_animat = function(){
			speed = speed<360?speed:2;
			//运运的速度
			speed+=2;
			//运动距离，即运动的弧度数;
			var ainhd = speed*Math.PI/180;
			//按速度来定位DIV元素
			$(".container img").each(function(index, element){
				var allpers = (Math.cos((ahd*index+ainhd))*b+dotTop)/totTop;
				var wpers = Math.max(0.1,allpers);
				var hpers = Math.max(0.1,allpers);
				$(this).css({
					"left":Math.sin((ahd*index+ainhd))*a+dotLeft,
					"top":Math.cos((ahd*index+ainhd))*b+dotTop,
					//"z-index":Math.ceil(allpers*10),
					"z-index":4,
					"width":wpers*wid,
					//"width":((wpers*wid<80) ? 30 :wpers*wid ),
					"height":hpers*hei,
					//"height":((hpers*hei<80) ? 30 :hpers*hei) ,
					"opacity":1
				});
				//alert($(this).attr('id'));
     		});			
		}
		
		//定时调用运动函数
		var setAnimate = setInterval(fun_animat,100);
		//加载悬浮提示窗口
				$("[data-toggle='popover']").popover({  
		        html : true,    
		        title: title(),    
		        delay:{show:10, hide:10},  
		        content: function() { 
					clearInterval(setAnimate);
		          	return content();    
					}   
				});

		//悬浮窗口消失时继续旋转
		$("[data-toggle='popover']").on('hidden.bs.popover', function() {setAnimate = setInterval(fun_animat,100)}); 
	});
</script>
<style type="text/css">
	*{margin:0;padding:0;}
	body{
		background:#FFFFFF;
		}	
	.container{position:relative;width:1000px;height:700px; margin:0 auto;z-index:1}
	.container imgg{position:absolute;width:293px;height:144px;background:#000; color:#FFFFFF; font-size:16px;z-index:2}
	.dot{ position:absolute;width:20px;height:20px;background:#F00;}
	
	.container img {
		position:absolute;width:293px;height:144px;
		azimuth: expression_r(
		this.pngSet?this.pngSet=true:(this.nodeName == "IMG" && this.src.toLowerCase().indexOf('.png')>-1?(this.runtimeStyle.backgroundImage = "none",
		this.runtimeStyle.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + this.src + "', sizingMethod='image')",
		this.src = "transparent.gif"):(this.origBg = this.origBg? this.origBg :this.currentStyle.backgroundImage.toString().replace('url("','').replace('")',''),
		this.runtimeStyle.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + this.origBg + "', sizingMethod='crop')",
		this.runtimeStyle.backgroundImage = "none")),this.pngSet=true);
		}
</style>
</head>
<body>
<!--
<a href="#"  data-toggle="popover" data-placement="bottom" data-trigger="hover">
	<img class="commentAvatarImage" src="${base}/static/images/satellite.jpg" alt="在这张图上面加载悬浮提示框" />
</a>-->
	<!--<div class="wrapper">	
	<div class="main">
		<div data-ptr-autospin="5000ms" data-ptr-angle="20deg" data-ptr-glow="0 0 50px rgba(0,252,255,0.35), inset 33px 20px 50px rgba(0,0,0,0.5)" data-ptr-size="300x300" data-ptr-pattern="${base}/static/images/satellite.jpg" class="earth planet"></div>	
	</div>
	</div>
	-->
	<div id="Layer1" style="position:absolute; width:100%; height:100%; z-index:0">    
		<img src="${base}/static/images/earth1.jpg" height="100%" width="100%"/>
	</div>
	<div class="container">
	<div style="left:500px;top:500px;">
		<a href="Index"  data-toggle="popover" data-placement="bottom" data-trigger="hover">
		<img id="1"  src="${base}/static/images/satellite.png" alt="卫星1" />
		</a>
		<a href="Index"  data-toggle="popover" data-placement="bottom" data-trigger="hover">
		<img  src="${base}/static/images/satellite.png" alt="卫星2" />
		</a>
		<a href="Index"  data-toggle="popover" data-placement="bottom" data-trigger="hover">
		<img id="2"  src="${base}/static/images/satellite.png" alt="卫星3" />
		</a>
		<a  href="Index"  data-toggle="popover" data-placement="bottom" data-trigger="hover">
		<img id="3"  src="${base}/static/images/satellite.png" alt="卫星4" />
		</a>
		<a href="Index"  data-toggle="popover" data-placement="bottom" data-trigger="hover">
		<img  src="${base}/static/images/satellite.png" alt="卫星5" />
		</a>
	</div>
	</div>
</body>
</html>
