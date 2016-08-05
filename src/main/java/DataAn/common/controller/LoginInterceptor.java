package DataAn.common.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Controller
public class LoginInterceptor extends HandlerInterceptorAdapter {

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
//		System.out.println("localAddr: " + localAddr);
//		System.out.println("localName: " + localName);
//		System.out.println("contextPath: " + contextPath);
//		System.out.println("serverName: " + serverName);
//		System.out.println("servletPath: " + servletPath);
//		System.out.println("serverPort: " + serverPort);
//		System.out.println(new Date());
//		System.out.println();
//		if("/Index".equals(servletPath)){
//			return true;
//		}
//		HttpSession session = request.getSession();
//		Object obj = session.getAttribute("userName");
//		if(obj == null || "".equals(obj.toString())){
//			response.sendRedirect(request.getContextPath() + "/login");
//			return false;
//		}
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
