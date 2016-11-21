package DataAn.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import DataAn.common.utils.DateUtil;

public class UtilTest {

	private long id;
	
	@Test
	public void test2(){
		System.out.println(id);
	}
	
	@Test
	public void test(){
		Date startDate = DateUtil.format("2010-01-01 00:00:00");
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.get(Calendar.YEAR);
		System.out.println(c.get(Calendar.YEAR));
		System.out.println(startDate.getDay());
		System.out.println(startDate.getHours());
		System.out.println(startDate.getTime());
		System.out.println(startDate.getTimezoneOffset());
		System.out.println(startDate.getYear());
		System.out.println(startDate.getMonth());

		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String sDate=sdf.format(startDate);
		System.out.println(sDate);
		System.out.println(1f%4);
		List<Integer> a = new ArrayList<Integer>();
		a.add(1);
		a.add(2);
	
	}
	
	
}
