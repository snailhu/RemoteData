package DataAn.mongo;

import java.util.Date;

import DataAn.common.utils.DateUtil;

public class TestTime {

	public static void main(String args[]){
		
		Date testDate= DateUtil.format("2015年08月10日00时15分01秒","yyyy年MM月dd日HH时mm分ss秒");
		
		String ss =DateUtil.formatString("2015年08月10日00时15分01秒","yyyy-MM-dd HH:mm:ss");
		
		System.out.println(ss);
	}

		
		
		
}
