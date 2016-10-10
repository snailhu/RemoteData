//package DataAn.fileSystem.service.impl;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.bson.Document;
//import org.springframework.stereotype.Component;
//
//import DataAn.fileSystem.dao.IDateParametersDao;
//import DataAn.fileSystem.dao.IVirtualFileSystemDao;
//import DataAn.fileSystem.domain.DateParameters;
//import DataAn.fileSystem.domain.VirtualFileSystem;
//import DataAn.fileSystem.dto.CSVFileDataResultDto;
//import DataAn.fileSystem.service.ICSVService;
//import DataAn.mongo.fs.IDfsDb;
//import DataAn.mongo.fs.MongoDfsDb;
//import DataAn.mongo.init.InitMongo;
//import DataAn.mongo.service.IMongoService;
//
//@Aspect
//@Component
//public class SaveFileHelper {
//
//	@Resource
//	private IVirtualFileSystemDao fileDao;
//	@Resource
//	private IDateParametersDao parametersDao;
//	@Resource
//	private ICSVService csvService;
//	@Resource
//	private IMongoService mongoService;
//
//	@Pointcut("execution(* DataAn.fileSystem.service.*.saveFile(..))")
//	private void pointCutMethod() {
//
//	}
//
//	// 声明前置通知
//	@Before("pointCutMethod()")
//	public void beforeSaveFile() throws Exception {
//		System.out.println("保存前。。。");
//		// throw new Exception("保存前失败！！！");
//	}
//
//	// 声明后置通知
//	@AfterReturning(pointcut = "pointCutMethod()", returning = "versions")
//	public void afterSaveFile(String versions) throws Exception {
//		System.out.println("保存后。。。" + versions);
//		// throw new Exception("保存后失败！！！");
//
//		final String uuId = versions;
//		// new Thread(new Runnable(){
//		// @Override
//		// public void run() {
//		//
//		// try {
//		//
//		// }
//		// } catch (Exception e) {
//		// e.printStackTrace();
//		// }
//		//
//		// }}).start();
//
//		long begin = System.currentTimeMillis();
//
//		VirtualFileSystem fs = fileDao
//				.selectByFileTypeIsFileAndMongoFSUUId(uuId);
//		System.out.println(fs);
//		if (fs != null) {
//			// IDfsDb dfs = MongoDfsDb.getInstance();
//			final String series = fs.getSeries();
//			final String star = fs.getStar();
//			final String date = fs.getYear_month_day();
//			final String parameterType = fs.getParameterType();
//
//			// String databaseName =
//			// InitMongo.getFSBDNameBySeriesAndStar(series, star);
//			// final CSVFileDataResultDto<Document> result =
//			// csvService.readCSVFileToDoc(dfs.downLoadToStream(databaseName,
//			// uuId),uuId);
//			CSVFileDataResultDto<Document> result = csvService
//					.readCSVFileToDoc(fs.getCachePath(), uuId);
//			List<Document> docList = result.getDatas();
//			// 数据不为空
//			if (docList != null && docList.size() > 0) {
//				// 保存csv文件数据
//				// mongoService.saveCSVData(series, star,parameterType, date,
//				// docList, uuId);
//				// 存储某一天的参数信息
//				String title = result.getTitle();
//				DateParameters dateParameters = new DateParameters();
//				dateParameters.setSeries(series);
//				dateParameters.setStar(star);
//				dateParameters.setParameterType(parameterType);
//				dateParameters.setParameters(title);
//				dateParameters.setYear_month_day(date);
//				parametersDao.add(dateParameters);
//			}
//			// 更新临时文件存储目录
//			fs.setCachePath(null);
//			fileDao.update(fs);
//
//			// test 等级
//			mongoService.saveCSVData(series, star, parameterType, date,
//					result.getMap(), uuId);
//
//			long end = System.currentTimeMillis();
//			System.out.println("保存数据： " + (end - begin));
//		}
//	}
//
//	// 声明例外通知
//	@AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
//	public void doAfterThrowing(Exception e) {
//		System.out.println("例外通知");
//		System.out.println(e.getMessage());
//	}
//
//	// 声明最终通知
//	@After("pointCutMethod()")
//	public void doAfter() {
//		System.out.println("最终通知");
//	}
//
//}
