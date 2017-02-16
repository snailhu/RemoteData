package DataAn.galaxyManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class ParamTest {

	@Test
	public void test(){
		String[] items = new String[]{"F10W113:飞轮电流Ya(00816)","F1W53_54:滚动陀螺角(00103)","采集数据19：1b滚动陀螺信号输出(16020)"};
		for (String item : items) {
			String num = item.substring(item.indexOf("(") + 1, item.indexOf(")"));
			String sequence = "sequence";
			String[] sequences = item.split(":");
			if(sequences.length > 0 && StringUtils.isNotBlank(sequences[0]) && (!isContainChinese(sequences[0])))
				sequence = sequences[0];
			String code = sequence + "_" + num;
			System.out.println("code: " + code);			
		}
	}
	
	@Test
	public void test2(){
		System.out.println(this.isContainChinese("采集数据19：1b滚动陀螺信号输出(16020)"));
	}

	public boolean isContainChinese(String str) {

		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}
}
