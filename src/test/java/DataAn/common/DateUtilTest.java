package DataAn.common;

import org.junit.Test;

import DataAn.common.utils.DateUtil;

public class DateUtilTest {

	@Test
	public void test(){
		String datetime = "2010年01月30日00时07分11.944秒";
		String datetime2 = DateUtil.formatString(datetime, "yyyy年MM月dd日HH时mm分ss.SSS秒","yyyy年MM月dd日HH时mm分ss秒");
		
		System.out.println(datetime2);
	}
}
