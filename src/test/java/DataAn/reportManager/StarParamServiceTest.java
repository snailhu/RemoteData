package DataAn.reportManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.galaxyManager.option.J9SeriesType;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.option.SeriesType;
import DataAn.reportManager.dto.StarParamDto;
import DataAn.reportManager.service.IStarParamService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class StarParamServiceTest {

	@Resource
	private IStarParamService starParamService;
	
	@Test
	public void saveList() throws Exception{
		String flywheelStr = "F5W71_74:飞轮转速Xa(00423),F5W75_78:飞轮转速Ya(00424),F5W79_82:飞轮转速Za(00425),"
				+ "F5W83_86:飞轮转速Xb(00426),F5W87_90:飞轮转速Yb(00427),F5W91_94:飞轮转速Zb(00428),"
				+ "F10W111:飞轮电流Xa(00814),F10W112:飞轮温度Xa(00815),F10W113:飞轮电流Ya(00816),"
				+ "F10W114:飞轮温度Ya(00817),F10W115:飞轮电流Za(00818),F10W116:飞轮温度Za(00819),"
				+ "F10W117:飞轮电流Xb(00820),F10W118:飞轮温度Xb(00821),F10W119:飞轮电流Yb(00822),"
				+ "F10W120:飞轮温度Yb(00823),F10W121:飞轮电流Zb(00824),F10W122:飞轮温度Zb(00825)";
		String topStr = "F0W53_54:滚动陀螺角速度(00102),F0W55_56:俯仰陀螺角速度(00104),F0W57_58:偏航陀螺角速度(00106),"
				+ "F1W53_54:滚动陀螺角(00103),F1W55_56:俯仰陀螺角(00105),F1W57_58:偏航陀螺角(00107),"
				+ "F3W59_60:x轴陀螺欧拉角速度L(00146),F3W61_62:y轴陀螺欧拉角速度L(00147),F3W63_64:z轴陀螺欧拉角速度L(00148),"
				+ "F10W123_126:X轴陀螺漂移估计(00826),F10W127_130:Y轴陀螺漂移估计(00827),"
				+ "F10W131_134:Z轴陀螺漂移估计(00828),";
		String series = SeriesType.J9_SERIES.getName();
		String star = J9SeriesType.STRA5.getValue();
		String paramType = J9Series_Star_ParameterType.FLYWHEEL.getValue();
		String[] items = flywheelStr.split(",");
		StarParamDto starParamDto = null;
		for (String item : items) {
			if(item.equals("接收地方时") || item.equals("时间"))
				continue;
			starParamDto = new StarParamDto();
			String num = item.substring(item.indexOf("(") + 1, item.indexOf(")"));
			String sequence = "sequence";
			String[] sequences = item.split(":");
			if(sequences.length > 0 && StringUtils.isNotBlank(sequences[0]) && (!isContainChinese(sequences[0])))
				sequence = sequences[0];
			String paramCode = sequence + "_" + num;
			String paramName = item.split(":")[1];
			starParamDto.setParamCode(paramCode);
			starParamDto.setParamName(paramName);
			starParamDto.setSeries(series);
			starParamDto.setStar(star);
			starParamDto.setPartsType(paramType);
			starParamDto.setEffeMin(0);
			starParamDto.setEffeMax(100);
			starParamDto.setValueUnit("");
			if(paramName.indexOf("温度") > -1)
				starParamDto.setValueUnit("°C");
			if(paramName.indexOf("转速") > -1)
				starParamDto.setValueUnit("r/min");
			if(paramName.indexOf("电流") > -1)
				starParamDto.setValueUnit("A");
			if(paramName.indexOf("角速度") > -1)
				starParamDto.setValueUnit("rad/s");
			starParamDto.setCreater("admin");
			//System.out.println(starParamDto);
			starParamService.save(starParamDto);
		}
	}
	@Test
	public void saveList2() throws Exception{
		Set<String> topSet = new HashSet<String>();
		Set<String> flywheelSet = new HashSet<String>();
		String[] paths = new String[]{"C:\\03星数据.csv"};
		for (String path : paths) {
			String line = this.getTitile(path);
			String[] strs = line.split(",");
			int flag = 0;
			for (int i = 0; i < strs.length; i++) {
				if(strs[i].indexOf("程序标志") > -1)
					flag = i;
				if(flag == 0)
					topSet.add(strs[i]);
				if(flag != 0 && i > flag)
					flywheelSet.add(strs[i]);
			}			
		}
		for (String param : flywheelSet) {
			System.out.println(param);
		}
	}
	private String getTitile(String path) throws Exception{
		File file = new File(path);
		InputStream in = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(in, "gb2312");
		BufferedReader reader = new BufferedReader(inputStreamReader);// 换成你的文件名
		String line = reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
		return line;
	}
	private boolean isContainChinese(String str) {

		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}
}
