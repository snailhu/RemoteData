package DataAn.fileSystem.service.impl;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 保存文件监控
 *
 */
@Aspect
public class SaveFileMonitor {

	@Pointcut("execution(* DataAn22.fileSystem.service.*.saveFile(..))")
	private void pointCutMethod() {

	}
	// 声明后置通知
	@AfterReturning(pointcut = "pointCutMethod()", returning = "versions")
	public void afterSaveFile(String versions) throws Exception {
		System.out.println("保存成功。。。" + versions);
	}
	
	// 声明例外通知
	@AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
	public void doAfterThrowing(Exception e) {
		System.out.println("保存失败");
		System.out.println(e.getMessage());
	}
}
