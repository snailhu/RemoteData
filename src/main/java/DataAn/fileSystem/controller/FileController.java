package DataAn.fileSystem.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import DataAn.common.pageModel.EasyuiDataGridJson;
import DataAn.common.pageModel.JsonMessage;
import DataAn.common.pageModel.Pager;
import DataAn.fileSystem.dto.FileDto;
import DataAn.fileSystem.dto.MongoFSDto;
import DataAn.fileSystem.service.IVirtualFileSystemService;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.service.ISeriesService;
import DataAn.log.service.SystemLogService;
import DataAn.sys.dto.ActiveUserDto;

@Controller
@RequestMapping("/admin/file")
public class FileController {

	@Resource
	private IVirtualFileSystemService fileService;
	@Resource
	private ISeriesService seriesService;
	@Resource
	private SystemLogService systemLogService;
	
	@RequestMapping("/index")
	public String mongoFSIndex(Model model,HttpServletRequest request,HttpServletResponse response) {
//		System.out.println("mongoFSIndex..");
//		//当前所在系列
//		model.addAttribute("nowSeries", "j9");
//		//当前所在星号
//		model.addAttribute("nowStar", "02");
//		
//		HttpSession session = request.getSession();
//		ActiveUserDto acticeUser = (ActiveUserDto) session.getAttribute("activeUser");
//		String flywheel = J9Series_Star_ParameterType.FLYWHEEL.getValue();
//		String type = acticeUser.getPermissionItems().get(flywheel);
//		String value = "";
//		String name = "";
//		if (StringUtils.isNotBlank(type)) {
//			value = J9Series_Star_ParameterType.getJ9SeriesStarParameterType(type).getValue();
//			name = J9Series_Star_ParameterType.getJ9SeriesStarParameterType(type).getName();
//		}else{
//			value = J9Series_Star_ParameterType.TOP.getValue();
//			name = J9Series_Star_ParameterType.TOP.getName();
//		}
//		//当前所在参数名称
//		model.addAttribute("nowParameterTypeValue", value);
//		model.addAttribute("nowParameterTypeName", name);			
//		//当前所在目录
//		model.addAttribute("nowDirId", 0);
		return "/admin/mongoFs/index";
	}
	
//	@RequestMapping("/index/{series}/{star}/{paramType}/{dirId}/")
//	public String mongoFSIndex(@PathVariable String series, 
//							   @PathVariable String star, 
//							   @PathVariable String paramType,
//							   @PathVariable long dirId,Model model) {
//		//当前所在系列
//		model.addAttribute("nowSeries", series);
//		//当前所在星号
//		model.addAttribute("nowStar", star);
//		//当前所在参数名称
//		model.addAttribute("nowParameterTypeValue", paramType);
////		model.addAttribute("nowParameterTypeName", J9Series_Star_ParameterType.getJ9SeriesStarParameterType(paramType).getName());
//		//当前所在目录
//		model.addAttribute("nowDirId", dirId);
//		return "admin/mongoFs/index";
//	}
	
	@RequestMapping(value = "getList", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getMongoFSList(HttpServletRequest request,HttpServletResponse response) {
		EasyuiDataGridJson json = new EasyuiDataGridJson();
		String series = request.getParameter("series");
		String star = request.getParameter("star");
		String paramType = request.getParameter("paramType");
		String strDirId = request.getParameter("dirId");
		String strPage = request.getParameter("page");
		String strRows= request.getParameter("rows");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String fileTypes= request.getParameter("fileTypes");
		System.out.println("getMongoFSList...");
		System.out.println("series : " + series);
		System.out.println("star : " + star);
		System.out.println("paramType : " + paramType);
		System.out.println("strDirId : " + strDirId);
		System.out.println("strPage : " + strPage);
		System.out.println("strRows : " + strRows);
		System.out.println("beginTime : " + beginTime);
		System.out.println("endTime : " + endTime);
		System.out.println("fileTypes : " + fileTypes);
		int page = 1;
		int rows = 10;
		if(StringUtils.isBlank(series) || StringUtils.isBlank(star) || StringUtils.isBlank(paramType)){
			json.setRows(new ArrayList<MongoFSDto>());
			json.setTotal(0l);
		}else{
			if (StringUtils.isNotBlank(strPage)) {
				page = Integer.parseInt(strPage);
			}
			if (StringUtils.isNotBlank(strRows)) {
				rows = Integer.parseInt(strRows);
			}
			
			Pager<MongoFSDto> pager = null;
			if(StringUtils.isNotBlank(beginTime) || StringUtils.isNotBlank(endTime)){
				if (StringUtils.isNotBlank(strDirId)) {
					long dirId = Long.parseLong(strDirId);
					pager = fileService.getMongoFSList(page, rows, series, star, paramType, dirId, beginTime, endTime, fileTypes);								
				}else{
					pager = fileService.getMongoFSList(page, rows, series, star, paramType, null, beginTime, endTime, fileTypes);
				}
			}else{
				long dirId = 0;
				if (StringUtils.isNotBlank(strDirId)) {
					dirId = Long.parseLong(strDirId);
				}
				pager = fileService.getMongoFSList(page, rows, series, star, paramType, dirId);			
			}
			json.setRows(pager.getRows());
			json.setTotal(pager.getTotalCount());	
		}
		return json;
	}
	
	@RequestMapping(value = "getParentCatalog", method = RequestMethod.POST)
	@ResponseBody
	public JsonMessage getParentCatalog(long dirId){
		JsonMessage msg = new JsonMessage();
		String json = fileService.getParentFSCatalog(dirId);
		msg.setSuccess(true);
		msg.setObj(json);
		return msg;
	}
	//返回上传文件界面
	@RequestMapping("/toUploadFile")
	public String uploadHome() {
		return "admin/mongoFs/uploadFile";
	}
	
	@RequestMapping(value = "existFile", method = RequestMethod.POST)
	@ResponseBody
	public JsonMessage existFile(String fileName){
		JsonMessage msg = new JsonMessage();
		fileName = fileName.replace("\\", "/");
		String[] strs = fileName.split("/");
		//j9-02--2016-02-01.csv
		fileName = strs[strs.length-1];
		boolean flag = false;
		//j9-02
		String[] seriesStarStrs = fileName.substring(0, fileName.lastIndexOf(".csv")).split("--");
		String[] ss = seriesStarStrs[0].split("-");
		String nowSeries = ss[0];//SeriesType.J9_SERIES.getName();
		String nowStar = ss[1];//J9SeriesType.getJ9SeriesType(ss[1]).getValue();
		flag = seriesService.checkSeriesAndStar(nowSeries, nowStar);
		if(!flag){
			msg.setSuccess(true);
			msg.setMsg("文件中的星系不存在！！！");			
		}else{
			flag = fileService.isExistFile(fileName);
			if(flag){
				msg.setSuccess(true);
				msg.setMsg("csv文件已存在！！！");	
			}
		}
		return msg;
	}
	
	@RequestMapping(value = "/uploadFile", method = { RequestMethod.POST })
	public String uploadFile(
			@RequestParam(value = "dirId", required = true) long dirId,
			@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
		
		System.out.println("come in uploadFile...");
		System.out.println("dirId: " + dirId);
		System.out.println("getName: " + file.getName());
		System.out.println("getOriginalFilename: " + file.getOriginalFilename());
		System.out.println("getContentType: " + file.getContentType());
		System.out.println("getSize: " + file.getSize());		
		
		FileDto fileDto = new FileDto();
//		fileDto.setDirId(dirId);			
		fileDto.setFileName(file.getOriginalFilename());
		fileDto.setFileSize(file.getSize());
		fileDto.setIn(file.getInputStream());
//		fileService.saveFile(fileDto );
		return "redirect:/admin/mongoFS/index/"+dirId+"/";
	}
	@RequestMapping(value = "/uploadFiles", method = { RequestMethod.POST })
//	@ResponseBody
	public String uploadFiles(
//			@RequestParam(value = "dirId", required = true) long dirId,
//			@RequestParam(value = "files[]", required = false) List<MultipartFile> files
			@RequestParam(value = "datFile", required = false) MultipartFile datFile,
			@RequestParam(value = "csvFile", required = false) MultipartFile csvFile,
			@RequestParam(value = "paramType", required = true) String paramType,
			HttpServletRequest request
			) throws Exception {
		System.out.println("come in uploadFiles...");
		System.out.println("datFile...");
		System.out.println("getName: " + datFile.getName());
		System.out.println("getOriginalFilename: " + datFile.getOriginalFilename());
		System.out.println("getContentType: " + datFile.getContentType());
		System.out.println("getSize: " + datFile.getSize());	
		System.out.println("csvFile...");
		System.out.println("getName: " + csvFile.getName());
		System.out.println("getOriginalFilename: " + csvFile.getOriginalFilename());
		System.out.println("getContentType: " + csvFile.getContentType());
		System.out.println("getSize: " + csvFile.getSize());
		System.out.println("paramType: " + paramType);
		
		long begin = System.currentTimeMillis();
		final Map<String, FileDto> map = new HashMap<String,FileDto>();
		DecimalFormat df = new DecimalFormat("#.00");
		if(csvFile.getSize() != 0){
			FileDto csvFileDto = new FileDto();
			csvFileDto.setFileName(csvFile.getOriginalFilename());
			double size = csvFile.getSize() / 1024 /1024;
			String strSize = df.format(size);
			csvFileDto.setFileSize(Float.parseFloat(strSize));
			csvFileDto.setIn(csvFile.getInputStream());
			csvFileDto.setParameterType(paramType);
			map.put("csv", csvFileDto);	
			//TODO 这里的operateJob为日志操作的具体内容格式应该为：上传文件+文件名
			String operateJob;
			operateJob = "上传文件"+csvFile.getName();
			systemLogService.addOneSystemlogs( request,operateJob);
		}
		if(datFile.getSize() != 0){
			FileDto datFileDto = new FileDto();
			datFileDto.setFileName(datFile.getOriginalFilename());
			double size = datFile.getSize() / 1024 /1024;
			String strSize = df.format(size);
			datFileDto.setFileSize(Float.parseFloat(strSize));
			datFileDto.setIn(datFile.getInputStream());
			map.put("dat", datFileDto);	
			//TODO 这里的operateJob为日志操作的具体内容格式应该为：上传文件+文件名
			String operateJob;
			operateJob = "上传文件"+datFile.getName();
			systemLogService.addOneSystemlogs( request,operateJob);
		}
		//打开另外一个线程处理文件
		new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					fileService.saveFile(map);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}}).start();
//		fileService.saveFile(map);
		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));
		
		
		
//		jsonMsg.setSuccess(true);
//		jsonMsg.setMsg("上传成功");
//		return jsonMsg;
		return "redirect:/admin/file/toUploadFile";
	}
	
	@RequestMapping("/download")
	public String download(long fileId, HttpServletRequest request,HttpServletResponse response) {
		OutputStream os = null;
		InputStream inputStream = null;
		try {
			FileDto fileDto = fileService.downloadFile(fileId);
			
//			System.out.println("download ..");
//			System.out.println("fileName: " + fileDto.getFileName());
			
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName="+ fileDto.getFileName());
			inputStream = fileDto.getIn();
			os = response.getOutputStream();
			
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
//			os.flush();
//			inputStream.close();
//			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//关闭输入流
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
			if(os != null){
				 // 这里主要关闭。
				try {
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
        //  返回值要注意，要不然就出现下面这句错误！
        //java+getOutputStream() has already been called for this response
		return null;
	}
	
	//压缩下载
	@RequestMapping("/downloads")
	public String downloads(String itemIds, HttpServletRequest request,HttpServletResponse response) {
//		System.out.println("come in downloads ..");
//		System.out.println("fileIds: " + itemIds);
		BufferedInputStream buff = null;
		OutputStream myout = null;
		FileInputStream fis = null;
		
		try {
			FileDto fileDto = fileService.downloadFiles(itemIds);
			response.setContentType("text/html; charset=GBK");
			// 创建file对象
			File file = new File(fileDto.getFilePath());

			// 设置response的编码方式
			response.setContentType("application/octet-stream");

			// 写明要下载的文件的大小
			response.setContentLength((int) file.length());

			// 设置附加文件名
			String filename = fileDto.getFileName();
			byte[] bt;
			bt = filename.getBytes("UTF-8");
			filename = new String(bt, "8859_1");
			// 解决中文乱码
			response.setHeader("Content-Disposition", "attachment;filename="+ filename);

			// 读出文件到i/o流
			fis = new FileInputStream(file);
			buff = new BufferedInputStream(fis);

			byte[] b = new byte[1024];// 相当于我们的缓存

			long k = 0;// 该值用于计算当前实际下载了多少字节

			// 从response对象中得到输出流,准备下载
			myout = response.getOutputStream();

			// 开始循环下载
			while (k < file.length()) {

				int j = buff.read(b, 0, 1024);
				k += j;

				// 将b中的数据写到客户端的内存
				myout.write(b, 0, j);

			}

			// 将写入到客户端的内存的数据,刷新到磁盘
			myout.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (buff != null) {
					buff.close();
				}
				if (myout != null) {
					myout.close();
				}
				if (fis != null) {
					fis.close();
				}
				// 删除mogoDB文件临时保存位置所在文件夹
				//mongoDBService.deleteDirectory(mogodbFilePath);
				// 删除zip文件
				//mongoDBService.deleteAllFile(FilePath);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
 
	@RequestMapping(value="/deleteFiles",method = { RequestMethod.POST })
	@ResponseBody
	public JsonMessage deleteFiles(HttpServletRequest request,String itemIds) {
		System.out.println("deleteFiles...");
		System.out.println("itemIds: " + itemIds);
		JsonMessage jsonMsg = new JsonMessage();
		try {
			//TODO 这里的operateJob为日志操作的具体内容格式应该为：删除文件+文件名
			String operateJob;
			operateJob = "删除文件"+itemIds;
			systemLogService.addOneSystemlogs( request,operateJob);
			fileService.deleteFile(itemIds);
			jsonMsg.setSuccess(true);
			jsonMsg.setMsg("删除成功！");
		} catch (Exception e) {
//			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("删除失败！");
		}
		return jsonMsg;
	}
	
	@RequestMapping(value="/deleteCSVFile",method = { RequestMethod.POST })
	@ResponseBody
	public JsonMessage deleteCSVFile(String versions) {
		System.out.println("deleteCSVFile...");
		System.out.println("version: " + versions);
		JsonMessage jsonMsg = new JsonMessage();
		try {
			fileService.deleteFileByUUId(versions);
			jsonMsg.setSuccess(true);
			jsonMsg.setMsg("删除成功！");
		} catch (Exception e) {
//			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("删除失败！");
		}
		return jsonMsg;
	}
}
