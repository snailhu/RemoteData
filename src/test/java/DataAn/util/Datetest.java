package DataAn.util;

import java.util.Date;

import org.junit.Test;

import DataAn.common.utils.DateUtil;
import DataAn.routing.DataSearchByTimeTask;

public class Datetest {
	@Test
	public void dateutil(){	
		long currenttime =System.currentTimeMillis();
		System.out.println("当前时间戳："+currenttime);
		Date endtime =new Date(currenttime);
		System.out.println("时间戳转时间："+endtime);
		java.util.Date currentdate=DateUtil.format("2000-01-01 12:01:01");
		System.out.println("当前时间："+currentdate);
	}
	@Test
	public void chaifentime(){
		Date startdate =DateUtil.format("2000-01-01 01:00:01");
		Date enddate = DateUtil.format("2000-01-01 23:59:59");
		long timespace =enddate.getTime()-startdate.getTime();
		int THRESHOLD=5;
		for(int i=0;i<THRESHOLD;i++){
			Date starttime =startdate;
			Date endtime =new Date(startdate.getTime()+timespace/THRESHOLD);
			//Date endtime =enddate;
			System.out.println("开始时间："+DateUtil.format(starttime)+"结束时间："+DateUtil.format(endtime));
			startdate = new Date(endtime.getTime()+1000);
		}
			
	}

}
