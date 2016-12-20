package DataAn.reportManager.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

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
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.jfreechart.service.IJfreechartServcie;
import DataAn.mongo.init.InitMongo;
import DataAn.reportManager.dao.IStarParamDao;
import DataAn.reportManager.domain.StarParam;
import DataAn.reportManager.dto.CreateReportDto;
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
	@Resource
	private IStarParamDao starParamDao;
	LinkedBlockingQueue<CreateReportDto> createReports = new LinkedBlockingQueue<CreateReportDto>();
	
//	@RequestMapping("/index/{series}/{star}/{paramType}/{dirId}/")
	@RequestMapping("/index")
	public String reportIndex(Model model,HttpServletRequest request,HttpServletResponse response) {
		
//		model.addAttribute("nowSeries", series);
//		model.addAttribute("nowStar", star);
//		model.addAttribute("nowDirId", dirId);
//		model.addAttribute("nowParameterTypeValue", paramType);
//		model.addAttribute("nowParameterTypeName", J9Series_Star_ParameterType.getJ9SeriesStarParameterType(paramType).getName());
		
		return "/admin/reportManager/index";
	}
	
	@RequestMapping("/reportDownLoad")
	public String reportDownLoad(Model model,HttpServletRequest request,HttpServletResponse response) {
		
		return "/admin/reportManager/createReport";
	}
	
	//@RequestMapping(value = "getList/{series}/{star}/{paramType}/{dirId}/", method = RequestMethod.POST)
	@RequestMapping(value = "getList", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getMongoFSList( HttpServletRequest request,HttpServletResponse response) {
		EasyuiDataGridJson json = new EasyuiDataGridJson();
		String series = request.getParameter("series");
		String star = request.getParameter("star");
		String paramType = request.getParameter("paramType");
		String strPage = request.getParameter("page");
		String strRows = request.getParameter("rows");
		String strDirId = request.getParameter("dirId");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String fileTypes= request.getParameter("fileTypes");
		int page = 1;
		int rows = 10;
		long dirId = 0L;
		if (StringUtils.isNotBlank(strDirId)) {
			dirId = Long.parseLong(strDirId);
		}
		if (StringUtils.isNotBlank(strPage)) {
			page = Integer.parseInt(strPage);
		}
		if (StringUtils.isNotBlank(strRows)) {
			rows = Integer.parseInt(strRows);
		}
		HttpSession session = request.getSession();
		if(StringUtils.isBlank(paramType)) {
			String value = "";
			ActiveUserDto acticeUser = (ActiveUserDto) session.getAttribute("activeUser");
			String flywheel = J9Series_Star_ParameterType.FLYWHEEL.getValue();
			String flywheelRole = acticeUser.getPermissionItems().get(flywheel);
			String top = J9Series_Star_ParameterType.TOP.getValue();
			String topRole = acticeUser.getPermissionItems().get(top);
			
			if (StringUtils.isNotBlank(flywheelRole) && StringUtils.isBlank(topRole)) {
				value = J9Series_Star_ParameterType.FLYWHEEL.getValue();
			}
			if (StringUtils.isNotBlank(topRole)&& StringUtils.isBlank(flywheelRole) ) {
				value = J9Series_Star_ParameterType.TOP.getValue();
			}
			paramType = value;
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
		
		long begin = System.currentTimeMillis();
		String flag = UUIDGeneratorUtil.getUUID();
		System.out.println();
		System.out.println("begintime:" + DateUtil.format(new Date())+" 开始生成报告： flag="+flag);
		
		ResultJSON res = ResultJSON.getSuccessResultJSON();
		
		String templateUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\卫星状态报告.doc";
		
		String time = DateUtil.getNowTime("yyyy-MM-dd");
		String partsName = "";
		if("flywheel".equals(partsType)) {
			partsName = "飞轮";
		}else if("top".equals(partsType)) {
			partsName = "陀螺";
		}
		String filename = seriesId+"_"+starId+"_"+partsName+"_"+time+".doc";
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
		
		long end = System.currentTimeMillis();
		System.out.println("endtime: " + DateUtil.format(new Date())+" flag="+flag);
		System.out.println("flag="+flag+": 生成报告 " + filename + " time: " + (end-begin));
		 return res;
	}
	
	@RequestMapping(value = "/createReport1")
	@ResponseBody
	public void createReport1(HttpServletRequest request,HttpServletResponse response,
			String seriesId,String starId,String partsType,String beginTime,String endTime) {
		CreateReportDto reportLast = new CreateReportDto();
		reportLast.setRequest(request);
		reportLast.setResponse(response);
		reportLast.setSeriesId(seriesId);
		reportLast.setStarId(starId);
		reportLast.setPartsType(partsType);
		reportLast.setBeginTime(beginTime);
		reportLast.setEndTime(endTime);
		try {
			createReports.put(reportLast);
			CreateReportDto reportFirst = createReports.take();
			while(reportFirst != null){
				
				long begin = System.currentTimeMillis();
				String flag = UUIDGeneratorUtil.getUUID();
				System.out.println();
				System.out.println("begintime:" + DateUtil.format(new Date())+" 开始生成报告： flag="+flag);
				
				ResultJSON res = ResultJSON.getSuccessResultJSON();
				String templateUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\卫星状态报告.doc";
				String time = DateUtil.getNowTime("yyyy-MM-dd");
				String partsName = "";
				if("flywheel".equals(partsType)) {
					partsName = "飞轮";
				}else if("top".equals(partsType)) {
					partsName = "陀螺";
				}
				String filename = seriesId+"_"+starId+"_"+partsName+"_"+time+".doc";
				String docPath = OptionConfig.getWebPath() + "report\\"+filename;
				Date beginDate = DateUtil.format(beginTime,"yyyy-MM-dd");
				Date endDate =  DateUtil.format(endTime,"yyyy-MM-dd");
				
//				reoportService.createReport(beginDate, endDate, filename,templateUrl, docPath, seriesId, starId, partsType);

				Map<String, Object> data = new HashMap<String, Object>();
				data.put("docPath", docPath);
				data.put("filename", filename);
				res.setData(data);
				
				long end = System.currentTimeMillis();
				System.out.println("endtime: " + DateUtil.format(new Date())+" flag="+flag);
				System.out.println("flag="+flag+": 生成报告 " + filename + " time: " + (end-begin));
				//
				reportFirst = createReports.take();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		}
	}
	
	@RequestMapping(value = "/createReport2")
	public Callable<String> processUpload(HttpServletRequest request,  
            final HttpServletResponse response) {  
        System.out.println(DateUtil.format(new Date()) +" 线程名称："+Thread.currentThread().getName());  
        
        
        
        Callable<String> s= new Callable<String>() {  
            public String call() throws Exception {  
                try {  
                    System.out.println("线程名称："+Thread.currentThread().getName());  
                    response.setContentType("text/plain;charset=utf-8");  
                    response.getWriter().write("nihao");  
                    response.getWriter().close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
                return null;  
            }  
        };  
        
//        Executors.newFixedThreadPool(1).submit(s).g
        return s;
    }  
	
	@RequestMapping(value = { "/createReportTest" })
	public void createReprotTest() throws Exception{

		String templateUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\卫星状态报告.doc";
		String templateNullUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\nullData.doc";
		
		List<StarParam> starList = starParamDao.getStarParamByParts();
		for (StarParam starParam : starList) {
			String seriesId = starParam.getSeries();
			String starId = starParam.getStar();
			String partsType = starParam.getPartsType() ;
			
			String  endTime = DateUtil.getYesterdayTime();
			String  starTime =DateUtil.getLastWeekTime();
			String time = DateUtil.getNowTime("yyyy-MM-dd");
			
			Date beginDate = DateUtil.format(starTime,"yyyy-MM-dd HH:mm:ss");
			Date endDate =  DateUtil.format(endTime,"yyyy-MM-dd HH:mm:ss");
			String partsName = "";
			if("flywheel".equals(partsType)) {
				partsName = "飞轮";
			}else if("top".equals(partsType)) {
				partsName = "陀螺";
			}
			
			String databaseName = InitMongo.DATABASE_TEST;
			String filename = seriesId+"_"+starId+"_"+partsName+"_"+time+".doc";
			String docPath = OptionConfig.getWebPath() + "report\\"+filename;
			try {
				reoportService.createReport(beginDate, endDate, filename, templateUrl, docPath, seriesId, starId, partsType);
				reoportService.insertReportToDB(filename, docPath,seriesId,starId, partsType,starTime,endTime,databaseName,partsName);
				reoportService.removeDoc(docPath);
			} catch (Exception e) {
				reoportService.reportNullDoc(filename,templateNullUrl, docPath, starTime, endTime,e.getMessage());
				reoportService.insertReportToDB(filename, docPath,seriesId,starId, partsType,starTime,endTime,databaseName,partsName);
				reoportService.removeDoc(docPath);
			}
		}
	}
}
