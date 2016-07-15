function checkDateTime(type,datetime,split){
	var date = datetime.split(" ")[0];
	var time = datetime.split(" ")[1];
	//alert(date + '\n' + time)
	switch (type){
	   case "time"://检查时分秒的有效性
		   var tempArr = time.split(":");
		   if(parseInt(tempArr[0]) > 23){
			   return false;
		   }
		   if(parseInt(tempArr[1]) > 60 || parseInt(tempArr[2]) > 60){
			   return false;
		   }		   
			break;
		case "date"://检查日期的有效性
			var tempArr = date.split("-");	
			if(parseInt(tempArr[1]) == 0 || parseInt(tempArr[1]) > 12){//月份
				return false;
			}
			var lastday=new Date(parseInt(tempArr[0]),parseInt(tempArr[1]),0);//获取当月的最后一天日期			
			if(parseInt(tempArr[2])== 0 || parseInt(tempArr[2]) > lastday.getDate()){//当月的日
				return false;
			}			
			var myDate = new Date(parseInt(tempArr[0]),parseInt(tempArr[1])-1,parseInt(tempArr[2]));				
			if(myDate=="Invalid Date") {		
				return false;
			}
		   break;
	}
	
	return true;
}

/***
 EasyUI的自定义日期验证规则
 * */
//自定义验证规则 名称为myDate
$.extend($.fn.validatebox.defaults.rules, {
   myDate: {
validator: function (mytime) {  
//标准时间格式
var regStandard = /^\1|2\d{3}-\d{1,2}-\d{1,2} \d{1,2}:\d{1,2}:\d{1,2}$/;	//匹配标准日期格式  2014-1-20 12:10:00
//日期快速输入格式 	
var regA =/^\1|2\d{3}-\d{1,2}-\d{1,2}-\d{1,2}-\d{1,2}-\d{1,2}$/;	//匹配日期 和 时\分\秒  2014-1-20-12-10-00 意思是2014-1-20 12:10:00
var regB =/^\1|2\d{3}-\d{1,2}-\d{1,2}-\d{1,2}-\d{1,2}$/;	//匹配日期 和 时\分  2014-1-20-12-10-00 意思是2014-1-20 12:10
var regC =/^\1|2\d{3}-\d{1,2}-\d{1,2}$/;	//匹配日期  2014-1-20
var x = "";

if(!regStandard.test(mytime)){
	if(regA.test(mytime)){			
		var tempArr = mytime.split('-');
		x = tempArr[0] + "-" + tempArr[1] + "-" + tempArr[2] + " " + tempArr[3] + ":" + tempArr[4] + ":" + tempArr[5];					
		/**/	
		if(!(checkDateTime("date",x) && checkDateTime("time",x))) {
			$.fn.validatebox.defaults.rules.myDate.message ="日期格式错误！";
			return false;
		}					
	}else
	if(regB.test(mytime)){			
		var tempArr = mytime.split('-');
		x = tempArr[0] + "-" + tempArr[1] + "-" + tempArr[2] + " " + tempArr[3] + ":" + tempArr[4] + ":00";
		/**/	
		if(!(checkDateTime("date",x) && checkDateTime("time",x))) {
			$.fn.validatebox.defaults.rules.myDate.message ="日期格式错误";
			return false;
		}			
	}else
	if(regC.test(mytime)){
		x = mytime + " 00:00:00";
		/**/
		if(!checkDateTime("date",x)) {
			$.fn.validatebox.defaults.rules.myDate.message ="日期格式错误";
			return false;
		}		
	}else{
		$.fn.validatebox.defaults.rules.myDate.message ="日期格式错误";
		return false;
	}
}else{
	if(!(checkDateTime("date",mytime) && checkDateTime("time",mytime))) {
		$.fn.validatebox.defaults.rules.myDate.message ="日期格式错误";
		return false;
	}
}
		return true;
    },
        message: ''
    }
});