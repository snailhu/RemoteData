function intsatellite(seriesId) {	 
	 var starList = new Array();
	 var url = window.location.pathname+"/getSatellites";
	 //var base=window.location;
	 var containerdiv=$("#id_container");
	 	 containerdiv.empty();
	 	 //alert("执行到定时函数，timeout的值为："+timeout);
	 	 if(!timestate) {
	 		timeout =false;
	 		timestate=true;
	 		time();
	 	 }
/*    $.post(url, { "seriesId": seriesId },
    		   function(data){
		        $.each(data, function (i, item) {
		            var div = $('<div></div>');
		            div.attr('class','imagediv')
		            var a = $('<a></a>');
		            var img = $('<img/>');
		            var span = $('<span></span>');
		            span.text(item.name);
		            //span.text(item.code);
		            alert(item.code+"-----"+item.name);
		            img.attr('id',item.id);
		            var imgurl = "http://localhost:8080/DataRemote/static/images/satellite.png";
		            img.attr('src',imgurl)
		            a.attr('data-trigger','hover');
		            a.attr('data-placement','top');
		            a.attr('data-toggle','popover');		            
		            a.append(img);
		            a.append(span);
		            div.append(a);
		            containerdiv.append(div);
		          });
    		   }, "json");*/
	
	$.ajax({  
         type : "post",  
         url : url,  
         data : "seriesId=" + seriesId,  
         async : false,  
         success : function(data){ 
        	 var containerdiv=$("#id_container");
    	 	 containerdiv.empty();
        	 $.each(data, function (i, item) {
        		 	currenttime=new Date();
     		 		currentdate =formatDate(currenttime);
     		 		var begintime=item.beginDate;
        		 	//begindate = formatDate(begintime)
        		 	var begindate =begintime.substring(0,10);        		    
        		 	var starinformationObj = {};
        		 	starinformationObj.id=item.id;
        		    starinformationObj.name=item.name;
        		    starinformationObj.begintime=begindate;       		    
        		    rundate = DateDiff('d',currentdate,begindate);
        		    starinformationObj.rundate=rundate;       		    
        		    starList.push(starinformationObj);       		 	
		            var div = $('<div></div>');
		            div.attr('class','imagediv')
		            var a = $('<a></a>');
		            a.attr('href','analysisData/'+seriesId+'/'+item.id);
		            var img = $('<img/>');
		            var span = $('<span></span>');
		            span.text(item.name);
		            //span.text(item.code);
		            img.attr('id',item.id);
		            var host = window.location.host;
		            var imgurl = 'http://'+host+'/DataRemote/static/images/satellite.png';
		            img.attr('src',imgurl);
		            a.attr('id',i);
		            a.attr('data-trigger','hover');
		            a.attr('data-placement','top');
		            a.attr('data-toggle','popover');		            
		            a.append(img);
		            a.append(span);
		            div.append(a);
		            containerdiv.append(div);
		            })	
			//中心点横坐标
			var dotLeft = ($(".container").width()-$(".dot").width())/2;
			//中心点纵坐标
			var dotTop = ($(".container").height()-$(".dot").height())/2-100;
			//椭圆长边
			a=460;
			//椭圆短边
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
				speed+=1;
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
         }
    	});
			//加载悬浮提示窗口
			$("[data-toggle='popover']").popover({  
	        html : true,    
	        title: title(),    
	        delay:{show:1, hide:100},  
	        content: function() { 
	        	timeout = true;
		        time();
				var nowid = $(this).attr('id');
				//alert(starList[nowid].name);
				//var nowname =
	          	return content(starList[nowid].name,starList[nowid].begintime,starList[nowid].rundate);    
	          	//return content();    
				}   
			});
			//悬浮窗口消失时继续旋转
			$("[data-toggle='popover']").on('hidden.bs.popover', function() {
		        //clearTimeout(timeoutProcess);
					clearTimeout(timeoutProcess);
					timeout = false;
					time();	
				});
  }
