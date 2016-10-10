package DataAn.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import DataAn.common.config.CommonConfig;

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
	}
	
//	@Test
//	public void test3() throws Exception{
//		new Thread(new Runnable(){
//			@Override
//			public void run() {
//				try {
//					ConfigTest test = new ConfigTest();
//					test.print();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}				
//			}}).start();
//		
//	}
	
	@Test
	public void print() throws Exception{
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		List<String> list1 = list.subList(1, list.size());
		for (String str : list1) {
			System.out.println(str);
		}
	}
}
