package DataAn.reportManager.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
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
import DataAn.mongo.init.InitMongo;
import DataAn.reportManager.dto.DataToDocDto;
import DataAn.reportManager.dto.ParamDto;
import DataAn.reportManager.dto.ParamImgDataDto;
import DataAn.reportManager.dto.ReportFileDto;
import DataAn.reportManager.service.IReoportService;
import DataAn.sys.dto.ActiveUserDto;
import DataAn.wordManager.config.OptionConfig;

@Controller
@RequestMapping("/report")
public class ReportController {
	@Resource
	private IReoportService reoportService;
	
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
		model.addAttribute("nowParameterTypeValue", value);
		model.addAttribute("nowParameterTypeName", name);			
		//当前所在目录
		model.addAttribute("nowDirId", 0);
		return "/admin/reportManager/index";
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
	@RequestMapping(value = { "/createReport2" }, method = { RequestMethod.GET})
	public void  goCreateReport(HttpServletResponse response) throws Exception {
		
		String filename = "report-20160922.doc";
		
		String imgUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\satellite.jpg";  
		
		String templateUrl =OptionConfig.getWebPath() + "\\report\\wordtemplate\\卫星状态报告.doc";
		
		String templateName = "Employees";
		
		String docPath = OptionConfig.getWebPath() + "\\report\\"+filename;
		
		
		/********************************导出报告到临时目录***********************************/
		
		DataToDocDto data = new DataToDocDto();
		data.setHealthcondition("飞轮运行正常");
		data.setParts("飞轮状态");
		data.setReporttitle("卫星状态");
		ParamDto param1 = new ParamDto();
		param1.setParamName("飞轮a电压");
		param1.setParamNumMax("5伏");
		param1.setParamNumMin("2伏");
		
		ParamDto param2 = new ParamDto();
		param2.setParamName("飞轮b电压");
		param2.setParamNumMax("6伏");
		param2.setParamNumMin("3伏");
		
		List<ParamDto> params = new ArrayList<ParamDto>();
		params.add(param1);
		params.add(param2);
		
		data.setParams(params);
		
		/********************************图片循环插入处理***********************************/
		
		List<ParamImgDataDto> paramImgDatas = new ArrayList<ParamImgDataDto>();
		
		ParamImgDataDto paramImgData1 = new ParamImgDataDto();
		paramImgData1.setParName("飞轮a:温度");
		paramImgData1.setParImg(imgUrl);
		
		
		ParamImgDataDto paramImgData2 = new ParamImgDataDto();
		paramImgData2.setParName("飞轮a：电流，转速");
		paramImgData2.setParImg(imgUrl);
		
		ParamImgDataDto paramImgData3= new ParamImgDataDto();
		paramImgData3.setParName("飞轮b:温度");
		paramImgData3.setParImg(imgUrl);
		
		ParamImgDataDto paramImgData4 = new ParamImgDataDto();
		paramImgData4.setParName("飞轮b：电流，转速");
		paramImgData4.setParImg(imgUrl);
		
		paramImgDatas.add(paramImgData1);
		paramImgDatas.add(paramImgData2);
		paramImgDatas.add(paramImgData3);
		paramImgDatas.add(paramImgData4);
		
		data.setParamImgData(paramImgDatas);
		
		
		/********************************end图片循环插入处理***********************************/
		

		reoportService.reportDoc(filename, data, imgUrl, templateUrl, templateName, docPath);
		
		
		/********************************服务器临时目录下载文档到客户端***********************************/
		
		InputStream inputStream = new FileInputStream(OptionConfig.getWebPath() + "\\report\\report-20160922.doc");
		String fileName =  "report-20160922.doc";
		reoportService.downLoadReportForDis(inputStream, fileName, response);
		
		
		
		/********************************保存报告到db***********************************/
		Map<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("series", "j9");
		dataMap.put("star", "02");
		String date = "2016-09-22";
		dataMap.put("date", DateUtil.formatString(date, "yyyy-MM-dd", "yyyy-MM-dd"));
		String year = DateUtil.formatString(date, "yyyy-MM-dd", "yyyy");
		dataMap.put("year", year);
		String month = DateUtil.formatString(date, "yyyy-MM-dd", "MM");
		dataMap.put("month", month);
		String versions = UUIDGeneratorUtil.getUUID();
		dataMap.put("versions", versions);
		dataMap.put("startTime", "2016-09-22 00:00:01");
		dataMap.put("endTime", "2016-09-22 11:11:11");
		dataMap.put("parameterType","flywheel");
		dataMap.put("databaseName",InitMongo.DATABASE_TEST); 
		
		InputStream input = new FileInputStream(OptionConfig.getWebPath() + "\\report\\report-20160922.doc");
		
		ReportFileDto reportFileDto = new ReportFileDto();
		DecimalFormat df = new DecimalFormat("#.00");
		reportFileDto.setFileName(filename);
		double size = input.available() / 1024 /1024;
		String strSize = df.format(size);
		reportFileDto.setFileSize(Float.parseFloat(strSize));
		reportFileDto.setIn(input);
		
		reoportService.saveReport(reportFileDto, dataMap);
		
		input.close();
		
		
		/********************************删除服务器临时目录文件***********************************/
		/*String filePath = OptionConfig.getWebPath() + "\\report\\"+filename;
		File file = new File(filePath);
		if (file.exists()) {
		    file.delete();
		}*/
		
		/********************************从db中下载文档***********************************/
		long fileId=4L;
		String databaseName = InitMongo.DATABASE_TEST;
		//reoportService.downLoadReportForDb(fileId, databaseName,  response);
		
		
//		String reportNmae="飞轮报告1.doc";
//		response.sendRedirect("secondStyle/wordshow?file="
//				+ URLEncoder.encode(reportNmae, "UTF-8"));
	}
	
}
