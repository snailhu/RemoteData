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

import DataAn.sys.dto.ActiveUserDto;
import DataAn.sys.dto.UserDto;
import DataAn.sys.service.IUserService;


@Controller
public class LoginController {

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
		return "/admin/login/index";
	}
	
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String loginPost(
			@RequestParam(value="username",required = true) String username,
			@RequestParam(value="password",required =true) String password,
			HttpServletResponse response,HttpServletRequest request) 
			throws InvalidKeyException, NoSuchAlgorithmException {
//		System.out.println("login...");
//		System.out.println("username: " + username);
//		System.out.println("password: " + password);
		ActiveUserDto acticeUser = userService.getActiveUserByName(username);
		if(acticeUser != null){
			if(password.equals(acticeUser.getPassWord())){
				HttpSession session = request.getSession();
				session.setAttribute("activeUser", acticeUser);
				return "redirect:/Index";
			}else{
				request.setAttribute("loginFlag",1);
				return "/admin/login/index";
			}
		}else{
			request.setAttribute("loginFlag", -1);
			return "/admin/login/index";
		}
	}
	@RequestMapping(value = "/loginOut", method = { RequestMethod.GET })
	public String loginOut(HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/login";
	}
}
