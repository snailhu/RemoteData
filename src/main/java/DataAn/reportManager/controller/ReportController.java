package DataAn.reportManager.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import DataAn.common.pageModel.EasyuiDataGridJson;
import DataAn.common.pageModel.JsonMessage;
import DataAn.common.pageModel.Pager;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.UUIDGeneratorUtil;
import DataAn.fileSystem.dto.MongoFSDto;
import DataAn.fileSystem.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.domain.Star;
import DataAn.galaxyManager.dto.StarDto;
import DataAn.jfreechart.dto.LineChartDto;
import DataAn.jfreechart.service.IJfreechartServcie;
import DataAn.mongo.init.InitMongo;
import DataAn.reportManager.domain.ReportFileSystem;
import DataAn.reportManager.domain.StarParam;
import DataAn.reportManager.dto.DataToDocDto;
import DataAn.reportManager.dto.ParamDto;
import DataAn.reportManager.dto.ParamImgDataDto;
import DataAn.reportManager.dto.ProductDto;
import DataAn.reportManager.dto.ReportFileDto;
import DataAn.reportManager.service.IReoportService;
import DataAn.reportManager.service.IStarParamService;
import DataAn.reportManager.util.CommonsConstant;
import DataAn.reportManager.util.ResultJSON;
import DataAn.sys.dto.ActiveUserDto;
import DataAn.wordManager.config.OptionConfig;

@Controller
@RequestMapping("/report")
public class ReportController {
	@Resource
	private IReoportService reoportService;
	
	@Resource
	private IStarParamService starParamService;
	
	@Resource
	private IJfreechartServcie jfreechartServcie;
	
	
	
	@RequestMapping("/index")
	public String reportIndex(Model model,HttpServletRequest request,HttpServletResponse response) {
		//当前所在系列
		model.addAttribute("nowSeries", "j9");
		//当前所在星号
		model.addAttribute("nowStar", "02");
		
		HttpSession session = request.getSession();
		ActiveUserDto acticeUser = (ActiveUserDto) session.getAttribute("activeUser");
		String flywheel = J9Series_Star_ParameterType.FLYWHEEL.getValue();
		String type = acticeUser.getPermissionItems().get(flywheel);
		String value = "";
		String name = "";
		if (StringUtils.isNotBlank(type)) {
			value = J9Series_Star_ParameterType.getJ9SeriesStarParameterType(type).getValue();
			name = J9Series_Star_ParameterType.getJ9SeriesStarParameterType(type).getName();
		}else{
			value = J9Series_Star_ParameterType.TOP.getValue();
			name = J9Series_Star_ParameterType.TOP.getName();
		}
		//当前所在参数名称
		model.addAttribute("nowpartsTypeValue", value);
		model.addAttribute("nowpartsTypeName", name);			
		//当前所在目录
		model.addAttribute("nowDirId", 0);
		return "/admin/reportManager/index";
	}
	
	@RequestMapping("/reportDownLoad")
	public String reportDownLoad(Model model,HttpServletRequest request,HttpServletResponse response) {
		
		return "/admin/reportManager/createReport";
	}
	
	@RequestMapping(value = "getList/{series}/{star}/{paramType}/{dirId}/", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getMongoFSList(@PathVariable String series, 
			   								 @PathVariable String star,
			   								 @PathVariable String paramType,
			   								 @PathVariable long dirId ,
			   								 HttpServletRequest request,HttpServletResponse response) {
		EasyuiDataGridJson json = new EasyuiDataGridJson();
		String strSeries = request.getParameter("series");
		String strStar = request.getParameter("star");
		String strParamType = request.getParameter("paramType");
		String strDirId = request.getParameter("dirId");
		String strPage = request.getParameter("page");
		String strRows= request.getParameter("rows");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String fileTypes= request.getParameter("fileTypes");
		int page = 1;
		int rows = 10;
		if (StringUtils.isNotBlank(strSeries)) {
			series = strSeries;
		}
		if (StringUtils.isNotBlank(strStar)) {
			star = strStar;
		}
		if (StringUtils.isNotBlank(strParamType)) {
			paramType = strParamType;
		}
		if (StringUtils.isNotBlank(strDirId)) {
			dirId = Long.parseLong(strDirId);
		}
		if (StringUtils.isNotBlank(strPage)) {
			page = Integer.parseInt(strPage);
		}
		if (StringUtils.isNotBlank(strRows)) {
			rows = Integer.parseInt(strRows);
		}
		Pager<MongoFSDto> pager = null;
		if(StringUtils.isNotBlank(beginTime) || StringUtils.isNotBlank(endTime) || StringUtils.isNotBlank(fileTypes)){
			pager = reoportService.getMongoFSList(page, rows, series, star, paramType, dirId, beginTime, endTime, fileTypes);			
		}else{
			pager = reoportService.getMongoFSList(page, rows, series, star, paramType, dirId);			
		}
		json.setRows(pager.getRows());
		json.setTotal(pager.getTotalCount());	
		return json;
	}
	
	@RequestMapping(value = "getParentCatalog", method = RequestMethod.POST)
	@ResponseBody
	public JsonMessage getParentCatalog(long dirId){
		JsonMessage msg = new JsonMessage();
		String json = reoportService.getParentFSCatalog(dirId);
		msg.setSuccess(true);
		msg.setObj(json);
		return msg;
	}
	
	//压缩下载
	@RequestMapping("/downloads")
	public void downloads(String itemIds, HttpServletResponse response) {
		String databaseName = InitMongo.DATABASE_TEST;
		reoportService.downLoadsReportForDb(itemIds, databaseName,response);
	}
 
	@RequestMapping("/download")
	public void download(long fileId, HttpServletRequest request,HttpServletResponse response) {
		String databaseName = InitMongo.DATABASE_TEST;
		reoportService.downLoadReportForDb(fileId, databaseName,  response);
	}
		

	@RequestMapping(value="/deleteFiles",method = { RequestMethod.POST })
	@ResponseBody
	public JsonMessage deleteFiles(String itemIds) {
		JsonMessage jsonMsg = new JsonMessage();
		try {
			reoportService.deleteFile(itemIds);
			jsonMsg.setSuccess(true);
			jsonMsg.setMsg("删除成功！");
		} catch (Exception e) {
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("删除失败！");
		}
		return jsonMsg;
	}
	@RequestMapping(value = { "/createReport" })
	public void  createReport(HttpServletResponse response,HttpServletRequest request,String seriesId,String starId,String partsType,String time) throws Exception {
		
			String imgUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\satellite.jpg";  
			String templateUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\卫星状态报告.doc";
			String templateName = "Employees";
			
			String filename = seriesId+"_"+starId+"_"+partsType+"_"+time+".doc";
			String docPath = OptionConfig.getWebPath() + "report\\"+filename;
			
			reoportService.createReport(time, filename, imgUrl, templateUrl, templateName, docPath, seriesId, starId, partsType);
			reoportService.downloadReport(response, docPath,filename);
			reoportService.removeDoc(docPath);
	}
	@RequestMapping(value = { "/createReportTest" })
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
		ParamDto paramDto7 = new ParamDto();
		paramDto7.setParamName("电压");
		paramDto7.setProductName("飞轮b");
		paramDto7.setParamNumMin("10");
		paramDto7.setParamNumMax("20");
		params.add(paramDto1);
		params.add(paramDto2);
		params.add(paramDto3);
		params.add(paramDto4);
		params.add(paramDto5);
		params.add(paramDto6);
		params.add(paramDto7);
		
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
		
		reoportService.reportDoc(filename, data, imgUrl, templateUrl, templateName, docPath);
		
	}
	
	
	
}
