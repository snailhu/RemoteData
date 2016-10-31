package DataAn.fileSystem.service.impl;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import DataAn.fileSystem.dao.IVirtualFileSystemDao;
import DataAn.fileSystem.domain.VirtualFileSystem;
import DataAn.status.option.StatusTrackingType;
import DataAn.status.service.IStatusTrackingService;

/**
 * 保存文件监控
 *
 */
@Aspect
public class SaveFileMonitor {
	@Resource
	private IStatusTrackingService statusTrackingService;
	@Resource
	private IVirtualFileSystemDao fileDao;

	@Pointcut("execution(* DataAn.fileSystem.service.*.saveFile(..))")
	private void pointCutMethod() {

	}

	// 声明前置通知
	@Before("pointCutMethod()")
	public void beforeSaveFile() throws Exception {
		System.out.println("保存前。。。");
		// throw new Exception("保存前失败！！！");
	}

	// 声明后置通知
	@AfterReturning(pointcut = "pointCutMethod()", returning = "versions")
	public void afterSaveFile(String versions) throws Exception {
		System.out.println("保存成功。。。" + versions);
		VirtualFileSystem file = fileDao.get(versions);
		statusTrackingService.updateStatusTracking(file.getFileName(), StatusTrackingType.IMPORT.getValue(),
				file.getParameterType(), "");
	}

	// 声明例外通知
	@AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
	public void doAfterThrowing(Exception e) {
		System.out.println("保存失败");
		System.out.println(e.getMessage());
	}
}
