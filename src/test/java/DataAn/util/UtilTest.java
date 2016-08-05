package DataAn.util;

import java.text.DecimalFormat;

import org.junit.Test;

public class UtilTest {

	@Test
	public void test(){
		DecimalFormat df = new DecimalFormat("#.00");
		String str = df.format(20.16646491);
		String str2 = df.format(5234631.000);
		System.out.println(str);
		System.out.println(Float.parseFloat(str));
		System.out.println(str2);
		System.out.println(Float.parseFloat(str2));
	}
}
