package DataAn.common.interceptor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

import DataAn.sys.domain.User;
import DataAn.sys.dto.ActiveUserDto;



public class LoginInterceptor extends HandlerInterceptorAdapter {

	//在执行handler之前来执行的
	//用于用户认证校验、用户权限校验
	@Override
	public boolean preHandle(HttpServletRequest request,
		HttpServletResponse response, Object handler) throws Exception {
//		System.out.println("come in longinInterceptor");
//		String localAddr = request.getLocalAddr();//获取WEB服务器的IP地址
//		String localName = request.getLocalName();//获取WEB服务器的主机名
//		String contextPath = request.getContextPath();
//		String serverName = request.getServerName();
//		String servletPath = request.getServletPath();
//		int serverPort = request.getServerPort();
//		String url = request.getRequestURL().toString();
//		String uri = request.getRequestURI();
//		System.out.println("localAddr: " + localAddr);
//		System.out.println("localName: " + localName);
//		System.out.println("contextPath: " + contextPath);
//		System.out.println("serverName: " + serverName);
//		System.out.println("servletPath: " + servletPath);
//		System.out.println("serverPort: " + serverPort);
//		System.out.println("url: " + url);
//		System.out.println("uri: " + uri);
//		System.out.println(new Date());
//		System.out.println();
		
		//得到请求servletPath 路径
		String servletPath = request.getServletPath();
		//判断是否是公开 地址
		if(servletPath.equals("/home")){
			return true;
		}
		HttpSession session = request.getSession();
		ActiveUserDto acticeUser = (ActiveUserDto) session.getAttribute("activeUser");
		if(acticeUser == null ){
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
			
//			acticeUser = new ActiveUserDto();
//			//设置超级管理员
//			acticeUser.setId(0);
//			acticeUser.setUserName("admin");	
//			acticeUser.setPassWord("admin");
//			Map<String,String> map =  new HashMap<String,String>();
//			map.put("flywheel", "flywheel");
//			map.put("top", "top");
//			map.put("userManager", "userManager");
//			acticeUser.setPermissionItems(map);
//			String json = JSON.toJSONString(map);
//			acticeUser.setPermissionItemsJSON(json);
//			String username = "admin";
//			User user = new User();
//			user.setUserName(username);
//			session.setAttribute("warnCount", 0);
//			session.setAttribute("user", user);
//			session.setAttribute("userName", username);
//			session.setAttribute("activeUser", acticeUser);
//			response.sendRedirect(request.getContextPath() + request.getRequestURI());
			
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
	
}
