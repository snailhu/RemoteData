package DataAn.reportManager.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import DataAn.fileSystem.dto.MongoFSDto;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.jfreechart.service.IJfreechartServcie;
import DataAn.mongo.init.InitMongo;
import DataAn.reportManager.dao.IStarParamDao;
import DataAn.reportManager.domain.StarParam;
import DataAn.reportManager.service.IReoportService;
import DataAn.reportManager.service.IStarParamService;
import DataAn.reportManager.util.CommonsConstant;
import DataAn.reportManager.util.ResultJSON;
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
	
	@Resource
	private IStarParamDao starParamDao;
	
	@RequestMapping("/index/{series}/{star}/{paramType}/{dirId}/")
	public String reportIndex(@PathVariable String series, 
			   @PathVariable String star, 
			   @PathVariable String paramType,
			   @PathVariable long dirId,Model model,HttpServletRequest request,HttpServletResponse response) {
		
		model.addAttribute("nowSeries", series);
		model.addAttribute("nowStar", star);
		model.addAttribute("nowDirId", dirId);
		model.addAttribute("nowParameterTypeValue", paramType);
		model.addAttribute("nowParameterTypeName", J9Series_Star_ParameterType.getJ9SeriesStarParameterType(paramType).getName());
		
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
		String strPage = request.getParameter("page");
		String strRows= request.getParameter("rows");
		String strDirId = request.getParameter("dirId");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String fileTypes= request.getParameter("fileTypes");
		int page = 1;
		int rows = 10;
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
		if(StringUtils.isNotBlank(beginTime) || StringUtils.isNotBlank(endTime) || StringUtils.isNotBlank(fileTypes) ){
			pager = reoportService.getMongoFSList(page, rows, series, star, paramType, dirId, beginTime, endTime);
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
	@RequestMapping(value = { "/downloadReport" })
	public void  downloadReport(HttpServletResponse response,HttpServletRequest request,String docPath,String filename) throws Exception {
		
		try {	
			reoportService.downloadReport(response, docPath,filename);
			reoportService.removeDoc(docPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/createReport")
	@ResponseBody
	public ResultJSON createReport(HttpServletRequest request,String seriesId,String starId,String partsType,String beginTime,String endTime) {
		ResultJSON res = ResultJSON.getSuccessResultJSON();
		
		String templateUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\卫星状态报告.doc";
		
		String time = DateUtil.getNowTime("yyyy-MM-dd");
		String filename = seriesId+"_"+starId+"_"+partsType+"_"+time+".doc";
		String docPath = OptionConfig.getWebPath() + "report\\"+filename;
		Date beginDate = DateUtil.format(beginTime,"yyyy-MM-dd");
		Date endDate =  DateUtil.format(endTime,"yyyy-MM-dd");
		try {
			reoportService.createReport(beginDate, endDate, filename, templateUrl, docPath, seriesId, starId, partsType);
			
			 Map<String, Object> data = new HashMap<String, Object>();
			 data.put("docPath", docPath);
			 data.put("filename", filename);
			 res.setData(data);
		 } catch (Exception ex) {
			 ex.printStackTrace();
			 res.setMsg(ex.getMessage());
			 res.setResult(CommonsConstant.RESULT_FALSE);
		 }
		 return res;
	}
	
	
	@RequestMapping(value = { "/createReportTest" })
	public void createReprotTest() throws Exception{

		String templateUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\卫星状态报告.doc";
		String templateNullUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\nullData.doc";
		
		List<StarParam> starList = starParamDao.getStarParamByParts();
		for (StarParam starParam : starList) {
			String seriesId = starParam.getSeries();
			String starId = starParam.getStar();
			String partsType = starParam.getPartsType();
			
			String starTime = DateUtil.getYesterdayTime();
			String endTime =DateUtil.getLastWeekTime();
			String time = DateUtil.getNowTime("yyyy-MM-dd");
			
			Date beginDate = DateUtil.format(starTime,"yyyy-MM-dd HH:mm:ss");
			Date endDate =  DateUtil.format(endTime,"yyyy-MM-dd HH:mm:ss");
			
			String databaseName = InitMongo.DATABASE_TEST;
			String filename = seriesId+"_"+starId+"_"+partsType+"_"+time+".doc";
			String docPath = OptionConfig.getWebPath() + "report\\"+filename;
			try {
				reoportService.createReport(beginDate, endDate, filename, templateUrl, docPath, seriesId, starId, partsType);
				reoportService.insertReportToDB(filename, docPath,seriesId,starId, partsType,starTime,endTime,databaseName);
				reoportService.removeDoc(docPath);
			} catch (Exception e) {
				reoportService.reportNullDoc(filename,templateNullUrl, docPath, starTime, endTime);
				reoportService.insertReportToDB(filename, docPath,seriesId,starId, partsType,starTime,endTime,databaseName);
				reoportService.removeDoc(docPath);
			}
		}
	}
}
