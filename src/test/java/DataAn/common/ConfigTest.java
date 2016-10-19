package DataAn.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import DataAn.common.config.CommonConfig;
import DataAn.common.utils.DateUtil;

public class ConfigTest {

	@Test
	public void test(){
		String str = CommonConfig.getCachePath();
		System.out.println(str);
		System.out.println(CommonConfig.getUplodCachePath());
		System.out.println(CommonConfig.getDownloadCachePath());
	}
	
	@Test
	public void test2(){
		Date tempDate = new Date();
		long time = tempDate.getTime();
		Date date = new Date(time + 500);
		
		int grade = (int) ((date.getTime() - tempDate.getTime()) / 1000);
		System.out.println((date.getTime() - tempDate.getTime()) / 1000);
		System.out.println(tempDate.compareTo(tempDate));
		String format = "yyyy-MM-dd HH:mm:ss.SSS";
		String strDate = DateUtil.format(tempDate, format);
		System.out.println(strDate);
		System.out.println(DateUtil.format(date, format));
	}
	

	
	@Test
	public void print() throws Exception{
		String date1 = "2016-10-14 13:46:50"+"."+427;
		String date2 = "2016-01-01 13:46:50.927";
		String format = "yyyy-MM-dd HH:mm:ss.SSS";
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date tempDate = dateFormat.parse(date1);
		Date date = dateFormat.parse(date2);
		System.out.println(tempDate);
		System.out.println(date);
		System.out.println(DateUtil.format(tempDate, format));
		System.out.println(DateUtil.format(date, format));
		
		String date3 = DateUtil.format(tempDate, "yyyy-MM-dd HH:mm:ss");
		System.out.println("date3: " + date3);
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(DateUtil.format(dateFormat2.parse(date3), format));
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		System.out.println(cal.get(Calendar.WEEK_OF_YEAR));
		cal.setTime(new Date());
		System.out.println(cal.get(Calendar.WEEK_OF_YEAR));
		
		Set<Date> dateSet = new HashSet<Date>();
		System.out.println(dateSet.contains(date));
	}
	
	@Test
	public void test3(){
		Map<String,String> params = new HashMap<String,String>();
		params.put("aa", "bb");
		System.out.println(params.containsKey("aa"));
		System.out.println(params.containsValue("aa"));
	}
}
