package DataAn.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
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
		Long id = new Long(94);
		long id2 = 94;
		System.out.println(id == id2);
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
	@Test
	public void test3(){
		/*String strname="汉字abc123";
		try {
			//byte[] bytes=strname.getBytes();
			byte[] bytes=strname.getBytes("UTF-8");			
			System.out.println("byte[]:"+bytes);
			//String b=new String(bytes,"UTF-8");
			String b=new String(bytes,Charset.forName("utf-8"));
			System.out.println("UTF-8:"+b);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String newstr;
		try {
			newstr = new String(strname.getBytes(),"UTF-8");
			System.out.println("UTF-8:"+newstr);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("系统默认编码："+Charset.defaultCharset());*/
		String str="a加b等于c，如果a等于1、b等于2，那么c等于3";
		byte[] bytes=str.getBytes(Charset.forName("Unicode"));
		
		try {
			System.out.println(bSubstring(str,6));
			
			String str2="a加b等";
			//String str3=new String(str2.getBytes(),"Unicode");
			//String str4=new String(str2.getBytes(),"UTF-8");
			System.out.println("Unicode+byte[]:"+str2.getBytes("Unicode"));
			System.out.println("  UTF-8+byte[]:"+str2.getBytes("Unicode"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static String bSubstring(String s,int length) throws Exception{
		 byte[] bytes = s.getBytes("Unicode");
		 System.out.println("字符串："+bytes);
		 System.out.println(":"+bytes[0]+":"+bytes[1]+":"+bytes[3]+":"+bytes[4]+":"+bytes[5]);
	        int n = 0; // 表示当前的字节数
	        int i = 2; // 要截取的字节数，从第3个字节开始
	        for (; i < bytes.length && n < length; i++)
	        {
	            // 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
	            if (i % 2 == 1)
	            {
	                n++; // 在UCS2第二个字节时n加1
	            }
	            else
	            {
	                // 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
	                if (bytes[i] != 0)
	                {
	                    n++;
	                }
	            }
	        }
	        // 如果i为奇数时，处理成偶数
	        if (i % 2 == 1)

	        {
	            // 该UCS2字符是汉字时，去掉这个截一半的汉字
	            if (bytes[i - 1] != 0)
	                i = i - 1;
	            // 该UCS2字符是字母或数字，则保留该字符
	            else
	                i = i + 1;
	        }

	        return new String(bytes, 0, i, "Unicode");
	}
	
	
}
