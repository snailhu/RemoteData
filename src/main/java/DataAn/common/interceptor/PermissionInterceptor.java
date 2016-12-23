package DataAn.common.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import DataAn.common.utils.ResourcesUtil;
import DataAn.sys.dto.ActiveUserDto;


public class PermissionInterceptor implements HandlerInterceptor{

	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//得到请求servletPath 路径
		String servletPath = request.getServletPath();
		//得到请求的url
		String uri = request.getRequestURI();
//		System.out.println("come in PermissionInterceptor");
//		System.out.println("servletPath: " + servletPath);
//		System.out.println("uri: " + uri);
		//判断是否是公开 地址
		if(servletPath.equals("/Index")){
			return true;
		}
		//获取session
		HttpSession session = request.getSession();
		ActiveUserDto activeUser = (ActiveUserDto) session.getAttribute("activeUser");
		//从session中取权限范围的权限
		Map<String, String> map = activeUser.getPermissionItems();
		if(map == null || map.isEmpty()){
			response.sendRedirect(request.getContextPath() + "/refuse");
			return false;
		}else{
			Object userManager = map.get("userManager");
			if(userManager == null){
				List<String> userManager_urls = ResourcesUtil.getkeyList("userManagerURL");
				for (String u : userManager_urls) {
					if (uri.indexOf(u) >= 0) {
						response.sendRedirect(request.getContextPath() + "/refuse");
						return false;					
					}
				}
			}
		}
		
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
