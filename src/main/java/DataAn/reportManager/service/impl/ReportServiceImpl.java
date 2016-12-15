package DataAn.reportManager.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.aspose.words.net.System.Data.DataColumn;
import com.aspose.words.net.System.Data.DataRelation;
import com.aspose.words.net.System.Data.DataRow;
import com.aspose.words.net.System.Data.DataSet;
import com.aspose.words.net.System.Data.DataTable;

import DataAn.common.config.CommonConfig;
import DataAn.common.pageModel.Pager;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.UUIDGeneratorUtil;
import DataAn.fileSystem.dto.FileDto;
import DataAn.fileSystem.dto.MongoFSDto;
import DataAn.fileSystem.option.FileType;
import DataAn.jfreechart.dto.ConstraintDto;
import DataAn.jfreechart.dto.LineChartDto;
import DataAn.jfreechart.service.IJfreechartServcie;
import DataAn.mongo.db.MongodbUtil;
import DataAn.mongo.fs.IDfsDb;
import DataAn.mongo.fs.MongoDfsDb;
import DataAn.mongo.service.IMongoService;
import DataAn.mongo.zip.ZipCompressorByAnt;
import DataAn.reportManager.config.OptionConfig;
import DataAn.reportManager.dao.IReportFileSystemDao;
import DataAn.reportManager.domain.ReportFileSystem;
import DataAn.reportManager.domain.StarParam;
import DataAn.reportManager.dto.DataToDocDto;
import DataAn.reportManager.dto.ParamDto;
import DataAn.reportManager.dto.ParamImgDataDto;
import DataAn.reportManager.dto.ProductDto;
import DataAn.reportManager.dto.ReportFileDto;
import DataAn.reportManager.option.ReportDataType;
import DataAn.reportManager.service.IReoportService;
import DataAn.reportManager.service.IStarParamService;
import DataAn.reportManager.util.AsposeLicenseManage;
import DataAn.reportManager.util.MapMailMergeDataSource;

@Service
public class ReportServiceImpl implements IReoportService {

	@Resource
	private IReportFileSystemDao fileDao;

	@Resource
	private IStarParamService starParamService;

	@Resource
	private IJfreechartServcie jfreechartServcie;

	@Resource
	private IMongoService iMongoService;

	@Override
	public void reportNullDoc(String filename, String templateUrl, String docPath, String beginDate, String endDate,
			String msg) throws Exception {

		// 验证License
		if (!AsposeLicenseManage.getAsposeLicense()) {
			return;
		}
		// 1 读取模板
		Document doc = new Document(templateUrl);

		doc.getMailMerge()
				.executeWithRegions(new MapMailMergeDataSource(getNullDoc(beginDate, endDate, msg), "nullTab"));
		// 3生成报告
		doc.save(docPath, SaveFormat.DOC);

	}

	private List<Map<String, Object>> getNullDoc(String beginDate, String endDate, String msg) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Map<String, Object> record = new HashMap<String, Object>();
		record.put("title", "日期区间" + beginDate + "到" + endDate + "导出报告失败，失败原因：" + msg);
		dataList.add(record);
		return dataList;
	}

	@Override
	public void reportDoc(String filename, DataToDocDto data, String templateUrl, String docPath) throws Exception {
		// 验证License
		if (!AsposeLicenseManage.getAsposeLicense()) {
			return;
		}
		// 1 读取模板
		Document doc = new Document(templateUrl);

		DataSet dataSet = new DataSet();

		DataTable product = new DataTable("productList");
		product.getColumns().add(new DataColumn("productName"));
		product.getColumns().add(new DataColumn("movableNum"));
		DataRow row_pro = null;
		List<ProductDto> products = data.getProducts();
		for (ProductDto productDto : products) {
			row_pro = product.newRow();
			row_pro.set("productName", productDto.getProductName());
			row_pro.set("movableNum", productDto.getMovableNum());
			product.getRows().add(row_pro);
		}

		DataTable param = new DataTable("paramList");
		param.getColumns().add(new DataColumn("productName"));
		param.getColumns().add(new DataColumn("paramName"));
		param.getColumns().add(new DataColumn("paramNumMax"));
		param.getColumns().add(new DataColumn("paramNumMin"));
		List<ParamDto> params = data.getParams();
		DataRow row_par = null;

		for (ParamDto paramDto : params) {
			row_par = param.newRow();
			row_par.set("productName", paramDto.getProductName());
			row_par.set("paramName", paramDto.getParamName());
			row_par.set("paramNumMax", paramDto.getParamNumMax());
			row_par.set("paramNumMin", paramDto.getParamNumMin());
			param.getRows().add(row_par);
		}
		dataSet.getTables().add(product);
		dataSet.getTables().add(param);

		dataSet.getRelations().add(new DataRelation("paramListForProduct", product.getColumns().get("productName"),
				param.getColumns().get("productName")));
		doc.getMailMerge().executeWithRegions(new MapMailMergeDataSource(getTitle(data), "titleTab"));
		doc.getMailMerge().executeWithRegions(
				new MapMailMergeDataSource(getImgTab(data.getOneParamImg(), "parNameOne", "parImgOne"), "firstImgTab"));
		doc.getMailMerge().executeWithRegions(
				new MapMailMergeDataSource(getImgTab(data.getTwoParamImg(), "parNameTwo", "parImgTwo"), "twoImgTab"));
		doc.getMailMerge().executeWithRegions(new MapMailMergeDataSource(
				getImgTab(data.getThreeParamImg(), "parNameThree", "parImgThree"), "thirdImgTab"));
		doc.getMailMerge().executeWithRegions(dataSet);
		// 3生成报告
		doc.save(docPath, SaveFormat.DOC);
	}

	private List<Map<String, Object>> getTitle(DataToDocDto data) throws Exception {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

		Map<String, Object> record = new HashMap<String, Object>();
		record.put("series", data.getSeries());
		record.put("star", data.getStar());
		record.put("beginDate", data.getBeginDate());
		record.put("endDate", data.getEndDate());
		record.put("createDate", data.getCreateDate());
		dataList.add(record);
		return dataList;
	}

	private List<Map<String, Object>> getImgTab(List<ParamImgDataDto> paramImgDatas, String parName, String parImg)
			throws Exception {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		byte[] image = null;
		for (ParamImgDataDto param : paramImgDatas) {
			if (StringUtils.isNotBlank(parImg) && StringUtils.isNotBlank(param.getParImg())) {
				FileInputStream fis = new FileInputStream(param.getParImg());
				image = new byte[fis.available()];
				fis.read(image);
				fis.close();
			}
			Map<String, Object> record = new HashMap<String, Object>();
			record.put(parName, param.getParName());
			record.put(parImg, image);
			dataList.add(record);
		}
		return dataList;
	}

	@Override
	public ReportFileSystem saveReport(ReportFileDto reportFileDto, Map<String, String> dataMap) {

		String uuId = dataMap.get("versions");
		String series = dataMap.get("series");
		String star = dataMap.get("star");
		String date = dataMap.get("date");
		String year = dataMap.get("year");
		String month = dataMap.get("month");
		String startTime = dataMap.get("startTime");
		String endTime = dataMap.get("endTime");
		String partsType = dataMap.get("partsType");
		String partsName = dataMap.get("partsName");
		String databaseName = dataMap.get("databaseName");

		saveDocToMongoDFs(reportFileDto, uuId, databaseName);
		ReportFileSystem docDir = createDocDir(series, star, partsType, partsName);
		ReportFileSystem yearDir = createYearDir(series, star, year, partsType, docDir);
		ReportFileSystem monthDir = createMonthDir(series, star, year, month, partsType, yearDir);
		return saveFile(reportFileDto, uuId, series, star, date, startTime, endTime, partsType, monthDir);
	}

	private void saveDocToMongoDFs(ReportFileDto reportFileDto, String uuId, String databaseName) {
		IDfsDb dfs = MongoDfsDb.getInstance();
		dfs.upload(databaseName, reportFileDto.getFileName(), uuId, reportFileDto.getIn());
	}

	private ReportFileSystem saveFile(ReportFileDto reportFileDto, String uuId, String series, String star, String date,
			String startTime, String endTime, String partsType, ReportFileSystem monthDir) {
		ReportFileSystem file = new ReportFileSystem();
		file.setSeries(series);
		file.setStar(star);
		file.setStartTime(startTime);
		file.setEndTime(endTime);
		file.setPartsType(partsType);
		file.setDataType(ReportDataType.DOC);
		file.setFileName(reportFileDto.getFileName());
		file.setFileSize(reportFileDto.getFileSize());
		file.setFileType(FileType.FILE);
		file.setParentId(monthDir.getId());
		file.setYear_month_day(date);
		file.setMongoFSUUId(uuId);
		return fileDao.add(file);
	}

	private ReportFileSystem createMonthDir(String series, String star, String year, String month, String partsType,
			ReportFileSystem yearDir) {
		ReportFileSystem monthDir = fileDao.selectByParentIdAndFileName(yearDir.getId(), month);
		if (monthDir == null) {
			monthDir = new ReportFileSystem();
			monthDir.setSeries(series);
			monthDir.setStar(star);
			monthDir.setPartsType(partsType);
			monthDir.setDataType(ReportDataType.DOC);
			monthDir.setFileName(month);
			monthDir.setFileType(FileType.DIR);
			monthDir.setYear_month_day(year + "-" + month);
			monthDir.setParentId(yearDir.getId());
			monthDir = fileDao.add(monthDir);
		}
		return monthDir;
	}

	private ReportFileSystem createYearDir(String series, String star, String year, String partsType,
			ReportFileSystem docDir) {
		ReportFileSystem yearDir = fileDao.selectByParentIdAndFileName(docDir.getId(), year);
		if (yearDir == null) {
			yearDir = new ReportFileSystem();
			yearDir.setSeries(series);
			yearDir.setStar(star);
			yearDir.setPartsType(partsType);
			yearDir.setDataType(ReportDataType.DOC);
			yearDir.setFileName(year);
			yearDir.setFileType(FileType.DIR);
			yearDir.setYear_month_day(year);
			yearDir.setParentId(docDir.getId());
			yearDir = fileDao.add(yearDir);
		}
		return yearDir;
	}

	private ReportFileSystem createDocDir(String series, String star, String partsType, String partsName) {
		ReportFileSystem docDir = fileDao.selectByParentIdisNullAndFileName(partsName);
		if (docDir == null) {
			docDir = new ReportFileSystem();
			docDir.setSeries(series);
			docDir.setPartsType(partsType);
			docDir.setStar(star);
			docDir.setDataType(ReportDataType.DOC);
			docDir.setFileName(partsName);
			docDir.setFileType(FileType.DIR);
			docDir = fileDao.add(docDir);
		}
		return docDir;
	}

	@Override
	public void downLoadReportForDb(long fileId, String databaseName, HttpServletResponse response) {
		OutputStream os = null;
		InputStream inputStream = null;
		try {
			ReportFileDto fileDto = downloadFile(fileId, databaseName);
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition",
					"attachment;fileName=" + new String(fileDto.getFileName().getBytes("gb2312"), "ISO8859-1"));
			inputStream = fileDto.getIn();
			os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStream(os, inputStream);
		}
	}

	private void closeStream(OutputStream os, InputStream inputStream) {
		// 关闭输入流
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (os != null) {
			// 这里主要关闭。
			try {
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Transactional(readOnly = true)
	public ReportFileDto downloadFile(long fileId, String databaseName) throws Exception {
		ReportFileDto fileDto = new ReportFileDto();
		// 从数据库中获取文件信息
		ReportFileSystem file = fileDao.get(fileId);
		fileDto.setFileName(file.getFileName());
		fileDto.setFileSize(file.getFileSize());
		// 从mongofs中获取数据流
		IDfsDb dfs = MongoDfsDb.getInstance();
		fileDto.setIn(dfs.downLoadToStream(databaseName, file.getMongoFSUUId()));
		return fileDto;
	}

	@Override
	public void downLoadReportForDis(InputStream inputStream, String fileName, HttpServletResponse response) {
		OutputStream os = null;
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
			os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStream(os, inputStream);
		}
	}

	@RequestMapping("/downloads")
	public String downLoadsReportForDb(String itemIds, String databaseName, HttpServletResponse response) {
		BufferedInputStream buff = null;
		OutputStream myout = null;
		FileInputStream fis = null;
		try {
			FileDto fileDto = downloadFiles(itemIds, databaseName);
			response.setContentType("text/html; charset=GBK");
			// 创建file对象
			File file = new File(fileDto.getFilePath());

			// 设置response的编码方式
			response.setContentType("application/octet-stream");

			// 写明要下载的文件的大小
			response.setContentLength((int) file.length());

			// 解决中文乱码
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(fileDto.getFileName().getBytes("gb2312"), "ISO8859-1"));
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void deleteFile(String ids) {
		String[] arrayIds = ids.split(",");
		ReportFileSystem file = null;
		Set<String> uuIds = new HashSet<String>();
		for (String id : arrayIds) {
			String[] items = id.split("/");
			// 遍历目录写文件
			if ("doc".equals(items[1])) {
				ReportFileSystem dir = fileDao.get(Long.parseLong(items[0]));
				uuIds.addAll(deleteFile(dir));
			} else {
				file = fileDao.get(Long.parseLong(items[0]));
				uuIds.add(file.getMongoFSUUId());
				fileDao.delete(file);
			}
		}
		// 删除mongodb的文件和标志记录状态
		IDfsDb dfs = MongoDfsDb.getInstance();
		MongodbUtil mg = MongodbUtil.getInstance();
		String collectionName = "";
		for (String uuId : uuIds) {
			if (uuId != null && !uuId.equals("")) {
				dfs.delete(uuId);
			}
		}
	}

	private Set<String> deleteFile(ReportFileSystem dir) {
		Set<String> uuIds = new HashSet<String>();
		List<ReportFileSystem> fileList = fileDao.findByParam("parentId", dir.getId());
		if (fileList != null && fileList.size() > 0) {
			for (ReportFileSystem childFile : fileList) {
				if (childFile.getFileType().getName().equals("dir")) {
					uuIds.addAll(deleteFile(childFile));
				}
				uuIds.add(childFile.getMongoFSUUId());
				// fileDao.delete(childFile);
			}
		}
		return uuIds;
	}

	@Override
	@Transactional(readOnly = true)
	public Pager<MongoFSDto> getMongoFSList(int pageIndex, int pageSize, String series, String star, String partsType,
			long dirId, String beginTime, String endTime) {
		Pager<ReportFileSystem> pager = fileDao.selectByOption(series, star, partsType, dirId, beginTime, endTime,
				"updateDate", pageIndex, pageSize);
		return returnPager(pageIndex, pageSize, pager.getRows(), pager.getTotalCount());
	}

	private Pager<MongoFSDto> returnPager(int pageIndex, int pageSize, List<ReportFileSystem> fileList,
			long totalCount) {
		List<MongoFSDto> fsList = new ArrayList<MongoFSDto>();
		if (fileList != null && fileList.size() > 0) {
			MongoFSDto fsDto = null;
			for (ReportFileSystem fs : fileList) {
				fsDto = new MongoFSDto();
				fsDto.setId(fs.getId());
				fsDto.setCreateDate(DateUtil.format(fs.getUpdateDate()));
				if (fs.getFileType().getName().equals("dir")) {
					fsDto.setFileSize("-");
				} else {
					fsDto.setFileSize(String.valueOf(fs.getFileSize()) + " KB");
				}
				fsDto.setName(fs.getFileName());
				fsDto.setType(fs.getFileType().getName());
				fsList.add(fsDto);
			}
		}
		Pager<MongoFSDto> pager = new Pager<MongoFSDto>(pageIndex, pageSize, totalCount, fsList);
		return pager;
	}

	@Override
	public Pager<MongoFSDto> getMongoFSList(int pageIndex, int pageSize, String series, String star, String partsType,
			long dirId) {
		Pager<ReportFileSystem> pager = null;
		if (dirId == 0) {
			pager = fileDao.selectBySeriesAndStarAndParameterTypeAndParentIdisNullAndOrder(series, star, partsType,
					"updateDate", pageIndex, pageSize);
		} else {
			pager = fileDao.selectBySeriesAndStarAndParameterTypeAndParentIdAndOrder(series, star, partsType, dirId,
					"updateDate", pageIndex, pageSize);
		}
		return this.returnPager(pageIndex, pageSize, pager.getRows(), pager.getTotalCount());
	}

	@Override
	public String getParentFSCatalog(long dirId) {
		List<ReportFileSystem> list = new ArrayList<ReportFileSystem>();
		ReportFileSystem fs = fileDao.get(dirId);
		list.add(fs);
		Long parentId = fs.getParentId();
		while (parentId != null) {
			ReportFileSystem parentFs = fileDao.get(parentId);
			list.add(parentFs);
			parentId = parentFs.getParentId();
		}
		Collections.reverse(list);
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (ReportFileSystem f : list) {
			sb.append("\"" + f.getId().toString() + "\"" + ":" + "\"" + f.getFileName() + "\"" + ",");
		}
		if (sb.lastIndexOf(",") != -1) {
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
		sb.append("}");
		return sb.toString();
	}

	@Override
	@Transactional(readOnly = true)
	public FileDto downloadFiles(String ids, String databaseName) throws Exception {
		FileDto fileDto = new FileDto();
		String[] arrayIds = ids.split(",");
		String mogodbFilePath = CommonConfig.getDownloadCachePath();
		ReportFileSystem file = null;
		IDfsDb dfs = MongoDfsDb.getInstance();
		for (String id : arrayIds) {
			String[] items = id.split("/");
			// 遍历目录写文件
			if ("dir".equals(items[1])) {
				writeDirFile(Long.parseLong(items[0]), dfs, mogodbFilePath, databaseName);
			} else {
				file = fileDao.get(Long.parseLong(items[0]));
				// 判断临时文件是否存在
				dfs.downLoadToLocal(databaseName, file.getMongoFSUUId(), mogodbFilePath);
			}
		}
		String zipFileName = DateUtil.format(new Date(), "yyyy-MM-dd-HH-mm-ss") + ".zip";
		String zipPath = CommonConfig.getZipCachePath() + File.separator + zipFileName;
		ZipCompressorByAnt zca = new ZipCompressorByAnt(zipPath);
		zca.compressExe(mogodbFilePath);
		fileDto.setFileName(zipFileName);
		fileDto.setFilePath(zipPath);
		return fileDto;
	}

	private void writeDirFile(long dirId, IDfsDb dfs, String path, String databaseName) throws Exception {
		ReportFileSystem dir = fileDao.get(dirId);
		path = path + File.separator + dir.getFileName();
		List<ReportFileSystem> fileList = fileDao.findByParam("parentId", dirId);
		if (fileList != null && fileList.size() > 0) {
			for (ReportFileSystem childFile : fileList) {
				if ("dir".equals(childFile.getFileType().getName())) {
					writeDirFile(childFile.getId(), dfs, path, databaseName);
				} else {
					dfs.downLoadToLocal(databaseName, childFile.getMongoFSUUId(), path);
				}
			}
		}
	}

	@Override
	public void createReport(Date beginDate, Date endDate, String filename, String templateUrl, String docPath,
			String seriesId, String starId, String partsType) throws Exception {

		DataToDocDto data = new DataToDocDto();
		data.setSeries(seriesId);
		data.setStar(starId);
		data.setBeginDate(DateUtil.format(beginDate, "yyyy-MM-dd"));
		data.setEndDate(DateUtil.format(endDate, "yyyy-MM-dd"));
		data.setCreateDate(DateUtil.getNowTime("yyyy-MM-dd"));

		List<StarParam> starParamList = starParamService.getStarParamForReport(seriesId, starId, partsType);
		if (starParamList == null || starParamList.size() < 1) {
			String templateNullUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\nullData.doc";
			reportNullDoc(filename, templateNullUrl, docPath, data.getBeginDate(), data.getEndDate(), "参数管理中未配置任何参数信息");
			return;
		}
		List<String> parList = new ArrayList<String>();
		String paramStr = OptionConfig.getParamStr();
		String[] parArr = paramStr.split(",");
		for (String p : parArr) {
			parList.add(p);
		}

		Map<String, List<ConstraintDto>> constraintsMap = new HashMap<String, List<ConstraintDto>>();
		List<StarParam> doubleList = new ArrayList<StarParam>();

		// 封装一条线（温度、电压等）的参数值
		List<StarParam> firstList = new ArrayList<StarParam>();
		for (StarParam starParam : starParamList) {
			if (!parList.contains(starParam.getParameterType())) {
				String key = starParam.getProductName() + starParam.getParameterType();
				List<ConstraintDto> listSingle = new ArrayList<ConstraintDto>();
				ConstraintDto constraintDto = new ConstraintDto();
				constraintDto.setParamName(starParam.getParamName());
				constraintDto.setParamCode(starParam.getParamCode());
				constraintDto.setMax(starParam.getEffeMax());
				constraintDto.setMin(starParam.getEffeMin());
				listSingle.add(constraintDto);
				constraintsMap.put(key, listSingle);
				firstList.add(starParam);
			} else {
				doubleList.add(starParam);
			}
		}
		// 等到产品数 如飞轮A、飞轮B
		List<String> productType = new ArrayList<String>();
		for (StarParam starParam : doubleList) {
			if (!productType.contains(starParam.getProductName())) {
				productType.add(starParam.getProductName());
			}
		}
		// 封装两条线（转速,电流）的参数值 如 飞轮A转速,电流 ；飞轮B转速,电流
		for (String product : productType) {
			List<ConstraintDto> productlist = new ArrayList<ConstraintDto>();
			for (StarParam starParam : doubleList) {
				if (product.equals(starParam.getProductName())) {
					ConstraintDto constraintDto = new ConstraintDto();
					constraintDto.setParamName(starParam.getParamName());
					constraintDto.setParamCode(starParam.getParamCode());
					constraintDto.setMax(starParam.getEffeMax());
					constraintDto.setMin(starParam.getEffeMin());
					productlist.add(constraintDto);
				}
			}
			constraintsMap.put(product + paramStr, productlist);
		}

		// 等到参数类型 如转速、电流、温度、电压等
		List<String> parameterType = new ArrayList<String>();
		for (StarParam starParam : starParamList) {
			if (!parameterType.contains(starParam.getParameterType())) {
				parameterType.add(starParam.getParameterType());
			}
		}

		// 封装同一参数类型下的多条线 如 在轨电流、在轨转速、在轨温度等
		for (String string : parameterType) {
			List<ConstraintDto> parameterTypelist = new ArrayList<ConstraintDto>();
			for (StarParam starParam : starParamList) {
				if (string.equals(starParam.getParameterType())) {
					ConstraintDto constraintDto = new ConstraintDto();
					constraintDto.setParamName(starParam.getParamName());
					constraintDto.setParamCode(starParam.getParamCode());
					constraintDto.setMax(starParam.getEffeMax());
					constraintDto.setMin(starParam.getEffeMin());
					parameterTypelist.add(constraintDto);
				}
			}
			constraintsMap.put(string, parameterTypelist);
		}
		LineChartDto lineChartDto = null;
		// 画图并返回参数
		lineChartDto = jfreechartServcie.createLineChart(seriesId, starId, partsType, beginDate, endDate,
				constraintsMap);

		Map<String, Double> minMap = lineChartDto.getMinMap();// 所以参数最小值Map
		Map<String, Double> maxMap = lineChartDto.getMaxMap();// 所以参数最大值Map
		Map<String, String> chartMap = lineChartDto.getChartMap();// 所以图片路径Map

		// 封装参数列表list
		List<ParamDto> params = new ArrayList<ParamDto>();
		Double paramNumMax = 0D;
		Double paramNumMin = 0D;
		for (StarParam starParam : starParamList) {
			ParamDto param = new ParamDto();
			param.setParamName(starParam.getParamName());
			param.setProductName(starParam.getProductName());
			if (maxMap != null && maxMap.size() != 0) {
				paramNumMax = maxMap.get(starParam.getParamCode());
				paramNumMin = minMap.get(starParam.getParamCode());
			}
			param.setParamNumMax(String.valueOf(paramNumMax));
			param.setParamNumMin(String.valueOf(paramNumMin));
			params.add(param);
		}

		// 封装参数类型 图片list
		List<ParamImgDataDto> threeParamImgList = new ArrayList<ParamImgDataDto>();
		String chartPathThree = OptionConfig.getWebPath() + "\\report\\wordtemplate\\satellite.jpg";
		for (String parName : parameterType) {
			ParamImgDataDto paramImgData = new ParamImgDataDto();
			paramImgData.setParName(parName);
			if (chartMap != null && chartMap.size() != 0) {
				chartPathThree = chartMap.get(parName);
			}
			paramImgData.setParImg(chartPathThree);
			threeParamImgList.add(paramImgData);
		}

		// 封装产品转速、电流 图片list
		List<ParamImgDataDto> twoParamImgList = new ArrayList<ParamImgDataDto>();
		String chartPathTwo = OptionConfig.getWebPath() + "\\report\\wordtemplate\\satellite.jpg";
		for (String product : productType) {
			ParamImgDataDto paramImgData = new ParamImgDataDto();
			paramImgData.setParName(product + paramStr);
			if (chartMap != null && chartMap.size() != 0) {
				chartPathTwo = chartMap.get(product + paramStr);
			}
			paramImgData.setParImg(chartPathTwo);
			twoParamImgList.add(paramImgData);
		}

		// 封装产品非转速、电流 图片list
		List<ParamImgDataDto> oneParamImgList = new ArrayList<ParamImgDataDto>();
		String chartPathOne = OptionConfig.getWebPath() + "\\report\\wordtemplate\\satellite.jpg";
		for (StarParam starParam : firstList) {
			ParamImgDataDto paramImgData = new ParamImgDataDto();
			paramImgData.setParName(starParam.getProductName() + starParam.getParameterType());
			if (chartMap != null && chartMap.size() != 0) {
				chartPathOne = chartMap.get(starParam.getProductName() + starParam.getParameterType());
			}
			paramImgData.setParImg(chartPathOne);
			oneParamImgList.add(paramImgData);
		}
		// 封装产品列表list
		List<ProductDto> products = new ArrayList<ProductDto>();

		for (String product : productType) {

			int proMovableNum = 0;
			for (StarParam starParam : starParamList) {
				int movableNum = getMovableNumByParamCode(seriesId, starId, partsType, beginDate, endDate,
						starParam.getParamCode());
				if (product.equals(starParam.getProductName())) {
					proMovableNum += movableNum;
				}
			}
			ProductDto productDto = new ProductDto();
			productDto.setProductName(product);
			productDto.setMovableNum(proMovableNum);
			products.add(productDto);
		}

		data.setParams(params);
		data.setProducts(products);
		data.setOneParamImg(oneParamImgList);
		data.setTwoParamImg(twoParamImgList);
		data.setThreeParamImg(threeParamImgList);

		reportDoc(filename, data, templateUrl, docPath);
	}

	@Override
	public Map<String, List<ConstraintDto>> getConstraintDtoList(String seriesId, String starId, String partsType) {
		List<StarParam> starParamList = starParamService.getStarParamForReport(seriesId, starId, partsType);
		List<String> parList = new ArrayList<String>();
		String paramStr = "转速,电流";
		String[] parArr = paramStr.split(",");
		for (String p : parArr) {
			parList.add(p);
		}
		Map<String, List<ConstraintDto>> constraintsMap = new HashMap<String, List<ConstraintDto>>();
		List<StarParam> doubleList = new ArrayList<StarParam>();

		// 封装一条线（温度、电压等）的参数值
		List<StarParam> firstList = new ArrayList<StarParam>();
		for (StarParam starParam : starParamList) {
			if (!parList.contains(starParam.getParameterType())) {
				String key = starParam.getProductName() + starParam.getParameterType();
				List<ConstraintDto> listSingle = new ArrayList<ConstraintDto>();
				ConstraintDto constraintDto = new ConstraintDto();
				constraintDto.setParamName(starParam.getParamName());
				constraintDto.setParamCode(starParam.getParamCode());
				constraintDto.setMax(starParam.getEffeMax());
				constraintDto.setMin(starParam.getEffeMin());
				listSingle.add(constraintDto);
				constraintsMap.put(key, listSingle);
				firstList.add(starParam);
			} else {
				doubleList.add(starParam);
			}
		}
		// 等到产品数 如飞轮A、飞轮B
		List<String> productType = new ArrayList<String>();
		for (StarParam starParam : doubleList) {
			if (!productType.contains(starParam.getProductName())) {
				productType.add(starParam.getProductName());
			}
		}
		// 封装两条线（转速,电流）的参数值 如 飞轮A转速,电流 ；飞轮B转速,电流
		for (String product : productType) {
			List<ConstraintDto> productlist = new ArrayList<ConstraintDto>();
			for (StarParam starParam : doubleList) {
				if (product.equals(starParam.getProductName())) {
					ConstraintDto constraintDto = new ConstraintDto();
					constraintDto.setParamName(starParam.getParamName());
					constraintDto.setParamCode(starParam.getParamCode());
					constraintDto.setMax(starParam.getEffeMax());
					constraintDto.setMin(starParam.getEffeMin());
					productlist.add(constraintDto);
				}
			}
			constraintsMap.put(product + paramStr, productlist);
		}

		// 等到参数类型 如转速、电流、温度、电压等
		List<String> parameterType = new ArrayList<String>();
		for (StarParam starParam : starParamList) {
			if (!parameterType.contains(starParam.getParameterType())) {
				parameterType.add(starParam.getParameterType());
			}
		}

		// 封装同一参数类型下的多条线 如 在轨电流、在轨转速、在轨温度等
		for (String string : parameterType) {
			List<ConstraintDto> parameterTypelist = new ArrayList<ConstraintDto>();
			for (StarParam starParam : starParamList) {
				if (string.equals(starParam.getParameterType())) {
					ConstraintDto constraintDto = new ConstraintDto();
					constraintDto.setParamName(starParam.getParamName());
					constraintDto.setParamCode(starParam.getParamCode());
					constraintDto.setMax(starParam.getEffeMax());
					constraintDto.setMin(starParam.getEffeMin());
					parameterTypelist.add(constraintDto);
				}
			}
			constraintsMap.put(string, parameterTypelist);
		}
		return constraintsMap;
	}

	private int getMovableNumByParamCode(String seriesId, String starId, String partsType, Date beginDate, Date endDate,
			String paramCode) {
		String collectionName = partsType + "_SpecialCase";
		long mnum = iMongoService.findMovableNumByParamCode(seriesId, starId, collectionName, paramCode, beginDate,
				endDate);
		return Integer.parseInt(String.valueOf(mnum));
	}

	@Override
	public ReportFileSystem insertReportToDB(String filename, String docPath, String seriesId, String starId,
			String partsType, String startTime, String endTime, String databaseName, String partsName)
			throws FileNotFoundException, IOException {
		/******************************** 保存报告到db ***********************************/
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("series", seriesId);
		dataMap.put("star", starId);
		String date = DateUtil.getNowTime("yyyy-MM-dd");
		dataMap.put("date", DateUtil.formatString(date, "yyyy-MM-dd", "yyyy-MM-dd"));
		String year = DateUtil.formatString(date, "yyyy-MM-dd", "yyyy");
		dataMap.put("year", year);
		String month = DateUtil.formatString(date, "yyyy-MM-dd", "MM");
		dataMap.put("month", month);
		String versions = UUIDGeneratorUtil.getUUID();
		dataMap.put("versions", versions);
		dataMap.put("startTime", startTime);
		dataMap.put("endTime", endTime);
		dataMap.put("partsType", partsType);
		dataMap.put("partsName", partsName);
		dataMap.put("databaseName", databaseName);

		InputStream input = new FileInputStream(docPath);

		ReportFileDto reportFileDto = new ReportFileDto();
		DecimalFormat df = new DecimalFormat("#.00");
		reportFileDto.setFileName(filename);
		double size = input.available() / 1024;
		String strSize = df.format(size);
		reportFileDto.setFileSize(Float.parseFloat(strSize));
		reportFileDto.setIn(input);

		ReportFileSystem reportFileSystem = saveReport(reportFileDto, dataMap);

		input.close();
		return reportFileSystem;
	}

	@Override
	public void removeDoc(String docPath) {
		File file = new File(docPath);
		if (file.exists()) {
			file.delete();
		}
	}

	@Override
	public void downloadReport(HttpServletResponse response, String docPath, String filename)
			throws FileNotFoundException {
		InputStream inputStream = new FileInputStream(docPath);
		downLoadReportForDis(inputStream, filename, response);
	}
}
