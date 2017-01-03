package DataAn.reportManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.common.config.CommonConfig;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.UUIDGeneratorUtil;
import DataAn.galaxy.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.option.J9SeriesType;
import DataAn.galaxyManager.option.SeriesType;
import DataAn.jfreechart.service.IJfreechartServcie;
import DataAn.reportManager.domain.StarParam;
import DataAn.reportManager.dto.DataToDocDto;
import DataAn.reportManager.dto.ParamDto;
import DataAn.reportManager.dto.ProductDto;
import DataAn.reportManager.service.IReoportService;
import DataAn.reportManager.service.IStarParamService;
import DataAn.wordManager.config.OptionConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class reportServcieTest {

	@Resource
	private IReoportService reoportService;
	
	@Resource
	private IStarParamService starParamService;
	
	@Resource
	private IJfreechartServcie jfreechartServcie;
	
	@Test
	public void getStarParamForReport(){
		String seriesId = "j9";
		String starId = "02";
		String partsType = "flywheel";
		List<StarParam> list = starParamService.getStarParamForReport(seriesId, starId, partsType);
		for (StarParam starParam : list) {
			System.out.println(starParam.getParamName());
		}
	}
	
	@Test
	public void createReport() throws Exception{
		
		String beginTime = "2016-12-08 00:00:00";
		String endTime = "2016-12-09 00:00:00";
//		String beginTime = "2005-01-01 00:00:00";
//		String endTime = "2005-01-05 00:00:00";
		Date beginDate = DateUtil.format(beginTime,"yyyy-MM-dd");
		Date endDate =  DateUtil.format(endTime,"yyyy-MM-dd");
		
		String seriesId = SeriesType.J9_SERIES.getValue();
		String starId = J9SeriesType.STRA3.getValue();
		String partsType = J9Series_Star_ParameterType.FLYWHEEL.getValue();
		
		
		String time = DateUtil.getNowTime("yyyy-MM-dd");
		String partsName = "";
		String templateUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\卫星状态报告1.doc";
		if(J9Series_Star_ParameterType.FLYWHEEL.getValue().equals(partsType)) {
			partsName = J9Series_Star_ParameterType.FLYWHEEL.getName();
			templateUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\卫星状态报告_flywheel.doc";
		}else if(J9Series_Star_ParameterType.TOP.getValue().equals(partsType)) {
			partsName = J9Series_Star_ParameterType.TOP.getName();
			templateUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\卫星状态报告_top.doc";
		}
		String filename = seriesId+"_"+starId+"_"+partsName+"_"+time+".doc";
		String docPath = CommonConfig.getDocCachePath() + File.separator + filename;
		
		reoportService.createReport(beginDate, endDate, filename, templateUrl, docPath, seriesId, starId, partsType);
		
		System.out.println(docPath);
	}
	
	@Test
	public void createReprotTest() throws Exception{
		
		String imgUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\satellite.jpg";  
		String templateUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\卫星状态报告1.doc";
		String templateName = "Employees";
		
		String seriesId = "j9";
		String starId = "02";
		String partsType = "flywheel";
		String time = DateUtil.getBeforeDate();
		
		String filename = seriesId+"_"+starId+"_"+partsType+"_"+time+".doc";
		String docPath = OptionConfig.getWebPath() + "report\\"+filename;
		
		DataToDocDto data = new DataToDocDto();
		
		
		List<ParamDto> params = new ArrayList<ParamDto>();
		ParamDto paramDto1 = new ParamDto();
		paramDto1.setParamName("电流");
		paramDto1.setProductName("飞轮a");
		paramDto1.setParamNumMin("2");
		paramDto1.setParamNumMax("5");
		
		
		ParamDto paramDto2 = new ParamDto();
		paramDto2.setParamName("电压");
		paramDto2.setProductName("飞轮a");
		paramDto2.setParamNumMin("10");
		paramDto2.setParamNumMax("22");
		
		ParamDto paramDto3 = new ParamDto();
		paramDto3.setParamName("转速");
		paramDto3.setProductName("飞轮a");
		paramDto3.setParamNumMin("25");
		paramDto3.setParamNumMax("60");
		
		ParamDto paramDto4 = new ParamDto();
		paramDto4.setParamName("电流");
		paramDto4.setProductName("飞轮b");
		paramDto4.setParamNumMin("3");
		paramDto4.setParamNumMax("6");
		
		
		ParamDto paramDto5 = new ParamDto();
		paramDto5.setParamName("电压");
		paramDto5.setProductName("飞轮b");
		paramDto5.setParamNumMin("12");
		paramDto5.setParamNumMax("31");
		
		ParamDto paramDto6 = new ParamDto();
		paramDto6.setParamName("转速");
		paramDto6.setProductName("飞轮b");
		paramDto6.setParamNumMin("22");
		paramDto6.setParamNumMax("62");
		params.add(paramDto1);
		params.add(paramDto2);
		params.add(paramDto3);
		params.add(paramDto4);
		params.add(paramDto5);
		params.add(paramDto6);
		
		List<ProductDto> products = new ArrayList<ProductDto>();
		
		ProductDto productDto1 = new ProductDto();
		productDto1.setProductName("飞轮a");
		productDto1.setMovableNum(8);
		
		ProductDto productDto2 = new ProductDto();
		productDto2.setProductName("飞轮b");
		productDto2.setMovableNum(10);
		products.add(productDto1);
		products.add(productDto2);
		
		data.setParams(params);
		data.setProducts(products);
		
		//reoportService.reportDoc(filename, data, imgUrl, templateUrl, templateName, docPath);
		
	}
	
}
