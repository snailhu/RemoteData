//格式化日期，将Date()格式转化为"2000-01-01"格式 
function formatDate(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? '0' + m : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    return y + '-' + m + '-' + d;
};

/*'yyyy-MM-dd HH:mm:ss'格式的字符串转日期*/
function stringToDate(str_time){
    var tempStrs = str_time.split(" ");
    var dateStrs = tempStrs[0].split("-");
    var year = parseInt(dateStrs[0], 10);
    var month = parseInt(dateStrs[1], 10) - 1;
    var day = parseInt(dateStrs[2], 10);
    var timeStrs = tempStrs[1].split(":");
    var hour = parseInt(timeStrs [0], 10);
    var minute = parseInt(timeStrs[1], 10);
    var second = parseInt(timeStrs[2], 10);
    var date = new Date(year, month, day, hour, minute, second);
    return date;
}

//日期相减函数
	function NewDate(str) { 
		str = str.split('-'); 
		var date = new Date(); 
		date.setUTCFullYear(str[0], str[1] - 1, str[2]); 
		date.setUTCHours(0, 0, 0, 0); 
		return date; 
	} 
	function TimeCom(dateValue) { 
		var newCom; 
		if (dateValue == "") { 
			newCom = new Date(); 
		} else { 
			newCom = NewDate(dateValue); 
		} 
		this.year = newCom.getYear(); 
		this.month = newCom.getMonth() + 1; 
		this.day = newCom.getDate(); 
		this.hour = newCom.getHours(); 
		this.minute = newCom.getMinutes(); 
		this.second = newCom.getSeconds(); 
		this.msecond = newCom.getMilliseconds(); 
		this.week = newCom.getDay(); 
	} 
	function DateDiff(interval, date1, date2) { 
		var TimeCom1 = new TimeCom(date1); 
		var TimeCom2 = new TimeCom(date2); 
		var result; 
		switch (String(interval).toLowerCase()) { 
			case "y": 
			case "year": 
			result = TimeCom1.year - TimeCom2.year; 
			break; 
			case "m": 
			case "month": 
			result = (TimeCom1.year - TimeCom2.year) * 12 + (TimeCom1.month - TimeCom2.month); 
			break; 
			case "d": 
			case "day": 
			result = Math.round((Date.UTC(TimeCom1.year, TimeCom1.month - 1, TimeCom1.day) - Date.UTC(TimeCom2.year, TimeCom2.month - 1, TimeCom2.day)) / (1000 * 60 * 60 * 24)); 
			break; 
			case "h": 
			case "hour": 
			result = Math.round((Date.UTC(TimeCom1.year, TimeCom1.month - 1, TimeCom1.day, TimeCom1.hour) - Date.UTC(TimeCom2.year, TimeCom2.month - 1, TimeCom2.day, TimeCom2.hour)) / (1000 * 60 * 60)); 
			break; 
			case "min": 
			case "minute": 
			result = Math.round((Date.UTC(TimeCom1.year, TimeCom1.month - 1, TimeCom1.day, TimeCom1.hour, TimeCom1.minute) - Date.UTC(TimeCom2.year, TimeCom2.month - 1, TimeCom2.day, TimeCom2.hour, TimeCom2.minute)) / (1000 * 60)); 
			break; 
			case "s": 
			case "second": 
			result = Math.round((Date.UTC(TimeCom1.year, TimeCom1.month - 1, TimeCom1.day, TimeCom1.hour, TimeCom1.minute, TimeCom1.second) - Date.UTC(TimeCom2.year, TimeCom2.month - 1, TimeCom2.day, TimeCom2.hour, TimeCom2.minute, TimeCom2.second)) / 1000); 
			break; 
			case "ms": 
			case "msecond": 
			result = Date.UTC(TimeCom1.year, TimeCom1.month - 1, TimeCom1.day, TimeCom1.hour, TimeCom1.minute, TimeCom1.second, TimeCom1.msecond) - Date.UTC(TimeCom2.year, TimeCom2.month - 1, TimeCom2.day, TimeCom2.hour, TimeCom2.minute, TimeCom2.second, TimeCom1.msecond); 
			break; 
			case "w": 
			case "week": 
			result = Math.round((Date.UTC(TimeCom1.year, TimeCom1.month - 1, TimeCom1.day) - Date.UTC(TimeCom2.year, TimeCom2.month - 1, TimeCom2.day)) / (1000 * 60 * 60 * 24)) % 7; 
			break; 
			default: 
			result = "invalid"; 
		} 
		return (result); 
	} 