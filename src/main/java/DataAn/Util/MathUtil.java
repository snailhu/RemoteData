package DataAn.Util;

import java.util.Calendar;
import java.util.Date;

import DataAn.common.utils.DateUtil;

public class MathUtil {
//	public static float m;
//	
//	public static float n;
	
	//public static float getCommonDivisor(float m, float n){
		
//		float k ,y;
//		if(m<n){			
//			k=m;
//			m=n;
//			n=k;								
//		}
//		y=m%n;
//		if(y==0){
//			return n;
//		}
//		else{
//			m=n;
//			n=y;
//			return getCommonDivisor(m,n);
//		}
		
		
//	}
	
	public static void main(String  args[]){
		
		Calendar date1 = Calendar.getInstance();
		date1.setTime(DateUtil.format("2000-11-02 00:20:43"));
		Date startDate = date1.getTime();//DateUtil.format(params[0]);
		Calendar date2 = Calendar.getInstance();
		date2.setTime(DateUtil.format("2010-11-02 00:20:43"));
		Date endDate = date2.getTime();		
		
		System.out.print((endDate.getTime() - startDate.getTime())/(1000*60*60*24));
		System.out.print((endDate.getDate() - startDate.getDate()));
		String a = "abc";
	}
	
	
	
	
}
