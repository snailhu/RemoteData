package DataAn.common.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DataAn.sys.dto.ActiveUserDto;
import DataAn.sys.service.IUserService;
import DataAn.common.utils.GetIpUtil;
import DataAn.log.domain.SystemLog;
import DataAn.log.service.SystemLogService;


@Controller
public class LoginController {


//	@Resource
//	private IUserService userService;
	
	@Resource 
	private SystemLogService systemLogService;

	@Resource
	private IUserService userService;

		
	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String login(HttpServletResponse response,HttpServletRequest request) 
			throws IOException {
		HttpSession session = request.getSession();
		ActiveUserDto acticeUser = (ActiveUserDto) session.getAttribute("activeUser");
		if(acticeUser != null ){
			return "redirect:/Index";
		}
		return "/admin/account/login";
	}
	
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String loginPost(
			@RequestParam(value="username",required = true) String username,
			@RequestParam(value="password",required =true) String password,
			HttpServletResponse response,HttpServletRequest request) 
			throws InvalidKeyException, NoSuchAlgorithmException, IOException {
//		System.out.println("login...");
//		System.out.println("username: " + username);
//		System.out.println("password: " + password);
		ActiveUserDto acticeUser = userService.getActiveUserByName(username);
		if(acticeUser != null){
			if(password.equals(acticeUser.getPassWord())){
				HttpSession session = request.getSession();
//				session.setAttribute("user", user);
				session.setAttribute("userName", username);
				String ip  =  GetIpUtil.getIpAddress(request);       
				SystemLog slog =  new SystemLog();
				slog.setLoginIp(ip);				
				slog.setUserName(username);
				Date loginTime = new Date(); 
//				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
//				String loginTime = dateFormat.format( now ); 
				slog.setLoginTime(loginTime);
				systemLogService.saveObject(slog);
				session.setAttribute("activeUser", acticeUser);

				return "redirect:/Index";
			}else{
				request.setAttribute("loginFlag",1);
				return "/admin/account/login";
			}
		}else{
			request.setAttribute("loginFlag", -1);
			return "/admin/account/login";
		}
	}
	@RequestMapping(value = "/loginOut", method = { RequestMethod.GET })
	public String loginOut(HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/refuse", method = { RequestMethod.GET })
	public String refuse(HttpServletResponse response,HttpServletRequest request) {
		
		return "/admin/account/refuse";
	}
			  	  	
}
