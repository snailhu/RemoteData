package DataAn.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import DataAn.common.utils.DateUtil;
import DataAn.jfreechart.thread.SearchByDayDoneTask2;

public class DateUtilTest {

	@Test
	public void test(){
		String datetime = "2010年01月30日00时07分11.944秒";
		String datetime2 = DateUtil.formatString(datetime, "yyyy年MM月dd日HH时mm分ss.SSS秒","yyyy年MM月dd日HH时mm分ss秒");
		
		System.out.println(datetime2);
		Date beginDate = DateUtil.format("2016-12-01 01:05:00");
		Date endDate = DateUtil.format("2016-12-07 02:05:00");
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
		System.out.println("分钟： " + cal.get(Calendar.MINUTE));
		cal.setTime(endDate);
		System.out.println("分钟： " + cal.get(Calendar.MINUTE));
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
	
	@Test
	public void test4(){
		Date beginDate = DateUtil.format("2016-12-01 00:00:00");
		Date endDate = DateUtil.format("2016-12-05 00:00:00");
		
		List<Integer> taskIntervalList = new ArrayList<Integer>();
		for (int j = 1; j <= 10; j++) {
			taskIntervalList.add(getFibo(j));
		}
		Date tempBeginDate = beginDate;
		Date tempEndDate = null;
//		List<List<SearchByDayDoneTask2>> forksList = new ArrayList<List<SearchByDayDoneTask2>>();
		boolean flag = false;
		for (Integer taskInterval : taskIntervalList) {
//			List<SearchByDayDoneTask2> forks = new LinkedList<SearchByDayDoneTask2>();
			for (int i = 0; i < taskInterval; i++) {
				long tempTime = tempBeginDate.getTime() + 1000 * 60 * 60 * 12;
				if(tempTime <= endDate.getTime()){
					tempEndDate = new Date(tempTime);
					System.out.println(DateUtil.format(tempBeginDate) + " 到 "+ DateUtil.format(tempEndDate));
					tempBeginDate = tempEndDate;
				}else{
					flag = true;
					break;
				}
			}
			if(flag)
				break;
			System.out.println("--");
		}
		
		
	}
	
	private static int getFibo(int i) {
		if (i == 1 || i == 2)
			return 1;
		else
		return getFibo(i - 1) + getFibo(i - 2);
	}
	
	@Test
	public void test5(){
		Date beginDate = DateUtil.format("2016-12-20 17:00:00");
		Date endDate = DateUtil.format("2016-12-21 00:00:00");
		System.out.println(DateUtil.daysOfTwo(beginDate, endDate));
	}
}
