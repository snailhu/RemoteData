package DataAn.common;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import DataAn.common.utils.DateUtil;

public class DateUtilTest {

	@Test
	public void test(){
		String datetime = "2010年01月30日00时07分11.944秒";
		String datetime2 = DateUtil.formatString(datetime, "yyyy年MM月dd日HH时mm分ss.SSS秒","yyyy年MM月dd日HH时mm分ss秒");
		
		System.out.println(datetime2);
	}
	
	@Test
	public void test2(){
		Calendar cal = Calendar.getInstance();
		Date beginDate = DateUtil.format("2016-11-01 00:00:00");
		cal.setTime(beginDate);
		Date endDate = DateUtil.format("2016-12-07 00:00:00");
		while(!endDate.before(cal.getTime())){
			String day = DateUtil.format(cal.getTime(), "yyyy-MM-dd");
			System.out.println(day);
			//beginDate.setDate(beginDate.getDate()+1);
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)+1);
		}
	}
	
	@Test
	public void test3(){
		Date beginDate = DateUtil.format("2016-12-01 00:00:00");
		Date endDate = DateUtil.format("2016-12-07 00:00:00");
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
		while(cal.getTime().before(endDate)){
			beginDate = cal.getTime();
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)+1);
			System.out.println(DateUtil.format(beginDate) + " 到 "+ DateUtil.format(cal.getTime()));

		}
	}
}
