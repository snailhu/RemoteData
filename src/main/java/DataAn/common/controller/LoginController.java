package DataAn.common.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import DataAn.common.utils.GetIpUtil;
import DataAn.prewarning.service.IPrewarningService;
import DataAn.sys.domain.User;
import DataAn.sys.dto.ActiveUserDto;
import DataAn.sys.service.IUserService;
import DataAn.sys.service.SystemLogService;

@Controller
public class LoginController {

	@Resource
	private SystemLogService systemLogService;

	@Resource
	private IUserService userService;

	@Resource
	private IPrewarningService prewarningService;

	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String login(HttpServletResponse response, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		ActiveUserDto acticeUser = (ActiveUserDto) session.getAttribute("activeUser");
		if (acticeUser != null) {
			return "redirect:/Index";
		}
		return "/admin/account/login";
	}

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String loginPost(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password, HttpServletResponse response,
			HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		 System.out.println("login...");
		 System.out.println("username: " + username);
		 System.out.println("password: " + password);
		 System.out.println("userService: " + userService);
		ActiveUserDto acticeUser = userService.getActiveUserByName(username);
		if (acticeUser != null) {
			if(username.equals(acticeUser.getUserName())){
				if (password.equals(acticeUser.getPassWord())) {
					acticeUser.setPassWord("");
					HttpSession session = request.getSession();
					
					Long warnCount = 0l;
					try {
						warnCount = prewarningService.getNotReadCount("", "", "", "", "");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					
					User user = new User();
					user.setUserName(username);
					session.setAttribute("warnCount", warnCount);
					session.setAttribute("user", user);
					session.setAttribute("userName", username);
					session.setAttribute("activeUser", acticeUser);
					//添加登录日志到日志数据库
					String operatejob = "登录系统";
					systemLogService.addOneSystemlogs(request,operatejob);
					//response.sendRedirect(request.getContextPath() + request.getRequestURI());
					return "redirect:/Index";
				} else {
					request.setAttribute("loginFlag", 1);
					return "/admin/account/login";
				}
			} else {
				request.setAttribute("loginFlag", -1);
				return "/admin/account/login";
			}
		} else {
			request.setAttribute("loginFlag", -1);
			return "/admin/account/login";
		}
	}

	@RequestMapping(value = "/loginOut", method = { RequestMethod.GET })
	public String loginOut(HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String ip="";
		try {
			ip = GetIpUtil.getIpAddress(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//添加退出日志到日志数据库
		String operatejob = "退出系统";
		systemLogService.addOneSystemlogs(request,operatejob);
		session.invalidate();
		return "redirect:/login";
	}

	@RequestMapping(value = "/refuse", method = { RequestMethod.GET })
	public String refuse(HttpServletResponse response, HttpServletRequest request) {

		return "/admin/account/refuse";
	}

}
