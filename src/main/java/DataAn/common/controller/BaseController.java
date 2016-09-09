package DataAn.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DataAn.sys.domain.User;

/**
 * 控制器基类
 *
 * 添加共性方法
  * 
 */
public class BaseController {
	

	/**
	 * 获取基于应用程序的url绝对路径
	 * 
	 * @param request
	 * @param url
	 *            以"/"打头的URL地址
	 * @return 基于应用程序的url绝对路径
	 */
	public final String getAppbaseUrl(HttpServletRequest request, String url) {
		return request.getContextPath() + url;
	}
	/**
	 * 获取当前用户用户名
	 * @param request
	 * @return
	 */
	protected String getCurrentUserName(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		if (obj != null) {
			User user = (User) obj;
			return user.getUserName();	
		}else {
			System.out.println("userName is null");
			return "admin";
		}
	}
}
