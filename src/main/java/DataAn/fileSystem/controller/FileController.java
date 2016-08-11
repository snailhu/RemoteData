package DataAn.fileSystem.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import DataAn.common.pageModel.EasyuiDataGridJson;
import DataAn.common.pageModel.JsonMessage;
import DataAn.common.pageModel.Pager;
import DataAn.fileSystem.dto.FileDto;
import DataAn.fileSystem.dto.MongoFSDto;
import DataAn.fileSystem.service.IVirtualFileSystemService;

@Controller
@RequestMapping("/admin/file")
public class FileController {

	@Resource
	private IVirtualFileSystemService fileService;
	
	@RequestMapping("/index")
	public String mongoFSIndex(Model model) {
		System.out.println("come in mongoFSIndex");
		//当前所在系列
		model.addAttribute("nowSeries", "j9");
		//当前所在星号
		model.addAttribute("nowStar", "02");
		//当前所在目录
		model.addAttribute("nowDirId", 0);
		return "/admin/mongoFs/index";
	}
	
	@RequestMapping("/index/{series}/{star}/{dirId}/")
	public String mongoFSIndex(@PathVariable String series, 
							   @PathVariable String star, 
							   @PathVariable long dirId,Model model) {
		//当前所在系列
		model.addAttribute("nowSeries", series);
		//当前所在星号
		model.addAttribute("nowStar", star);
		//当前所在目录
		model.addAttribute("nowDirId", dirId);
		return "admin/mongoFs/index";
	}
	
	@RequestMapping(value = "getList/{series}/{star}/{dirId}/", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getMongoFSList(@PathVariable String series, 
			   								 @PathVariable String star,
			   								 @PathVariable long dirId ,
			   								 HttpServletRequest request) {
//		System.out.println("come in getMongoFSList..");
		EasyuiDataGridJson json = new EasyuiDataGridJson();
		String strSeries = request.getParameter("series");
		String strStar = request.getParameter("star");
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
		if (StringUtils.isNotBlank(strDirId)) {
			dirId = Long.parseLong(strDirId);
		}
		if (StringUtils.isNotBlank(strPage)) {
			page = Integer.parseInt(strPage);
		}
		if (StringUtils.isNotBlank(strRows)) {
			rows = Integer.parseInt(strRows);
		}
//		System.out.println("strPage: " + strPage);
//		System.out.println("strRows: " + strRows);
//		System.out.println("strDirId: " + strDirId);
//		System.out.println("page: " + page);
//		System.out.println("rows: " + rows);
//		System.out.println("series: " + series);
//		System.out.println("star: " + star);
//		System.out.println("dirId: " + dirId);
//		System.out.println("beginTime: " + beginTime);
//		System.out.println("endTime: " + endTime);
//		System.out.println("fileTypes: " + fileTypes);
		Pager<MongoFSDto> pager = null;
		if(StringUtils.isNotBlank(beginTime) || StringUtils.isNotBlank(endTime) || StringUtils.isNotBlank(fileTypes)){
			pager = fileService.getMongoFSList(page, rows, series, star, dirId, beginTime, endTime, fileTypes);			
		}else{
			pager = fileService.getMongoFSList(page, rows, series, star, dirId);			
		}
		json.setRows(pager.getRows());
		json.setTotal(pager.getTotalCount());	
		System.out.println("totalCount: " + pager.getTotalCount());
		System.out.println();
		return json;
	}
	
	@RequestMapping(value = "getParentCatalog", method = RequestMethod.POST)
	@ResponseBody
	public JsonMessage getParentCatalog(long dirId){
		System.out.println("come in getParentCatalog...");
//		System.out.println("dirId: " + dirId);
		JsonMessage msg = new JsonMessage();
		String json = fileService.getParentFSCatalog(dirId);
//		System.out.println("json: " + json);
		msg.setSuccess(true);
		msg.setObj(json);
		return msg;
	}
	//返回上传文件界面
	@RequestMapping("/toUploadFile")
	public String uploadHome() {
//		System.out.println("come in uploadHome");
		return "admin/mongoFs/uploadFile";
	}
	
	@RequestMapping(value = "existFile", method = RequestMethod.POST)
	@ResponseBody
	public JsonMessage existFile(String fileName){
//		System.out.println("come in existFile..");
//		System.out.println("fileName: " + fileName);
		JsonMessage msg = new JsonMessage();
		fileName = fileName.replace("\\", "/");
		String[] strs = fileName.split("/");
		boolean flag = fileService.isExistFile(strs[strs.length-1]);
		msg.setSuccess(flag);
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
			@RequestParam(value = "csvFile", required = false) MultipartFile csvFile
			) throws Exception {
		System.out.println("come in uploadFiles...");
//		JsonMessage jsonMsg = new JsonMessage();
//		System.out.println("dirId: " + dirId);
//		for (MultipartFile file : files) {
//		System.out.println("getName: " + file.getName());
//		System.out.println("getOriginalFilename: " + file.getOriginalFilename());
//		System.out.println("getContentType: " + file.getContentType());
//		System.out.println("getSize: " + file.getSize());		
//		}
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
		
		long begin = System.currentTimeMillis();
		final Map<String, FileDto> map = new HashMap<String,FileDto>();
		DecimalFormat df = new DecimalFormat("#.00");
		if(csvFile.getSize() != 0){
			FileDto csvFileDto = new FileDto();
			csvFileDto.setFileName(csvFile.getOriginalFilename());
			String size = df.format(csvFile.getSize());
			csvFileDto.setFileSize(Float.parseFloat(size));
			csvFileDto.setIn(csvFile.getInputStream());
			map.put("csv", csvFileDto);			
		}
		if(datFile.getSize() != 0){
			FileDto datFileDto = new FileDto();
			datFileDto.setFileName(datFile.getOriginalFilename());
			String size = df.format(datFile.getSize());
			datFileDto.setFileSize(Float.parseFloat(size));
			datFileDto.setIn(datFile.getInputStream());
			map.put("dat", datFileDto);			
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
			
			System.out.println("download ..");
			System.out.println("fileName: " + fileDto.getFileName());
			
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
		System.out.println("come in downloads ..");
		System.out.println("fileIds: " + itemIds);
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
 
}
