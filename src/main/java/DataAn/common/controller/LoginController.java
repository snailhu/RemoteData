package DataAn.common.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

//	@Resource
//	private IUserService userService;
		
	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String login(
			HttpServletResponse response,
			HttpServletRequest request) throws IOException {
//		HttpSession session = request.getSession();
//		Object obj = session.getAttribute("user");
//		if(obj ==null || "".equals(obj.toString())){			
//			return "/";
//		}else{
//		}
		return "/admin/login/index";
	}
	
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String loginPost(
			HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value="username",required = true) String username,
			@RequestParam(value="password",required =true) String password,
			@RequestParam(value="rememberMe",required =false) String rememberMe
			) throws InvalidKeyException, NoSuchAlgorithmException {
		System.out.println("username: " + username);
		System.out.println("password: " + password);
		System.out.println("rememberMe: " + rememberMe);
		Map<String,String> map = new HashMap<String,String>();
		map.put("shenwp", "shenwp");
		map.put("admin", "admin");
		map.put("root", "root");
		Set<String> set = map.keySet();
		if(set.contains(username)){
			if(password.equals(map.get(username))){
				HttpSession session = request.getSession();
//				session.setAttribute("user", user);
				session.setAttribute("userName", username);
				return "redirect:/Index";
			}else{
				request.setAttribute("loginFlag",1);
				return "/admin/login/index";
			}
		}else{
			request.setAttribute("loginFlag", -1);
			return "/admin/login/index";
		}
//		User user = userService.getUserByName(username);
//		if(user != null){
////			String EncryptedPassword =PBKDF2SHA256.getEncryptedPassword(password, user.getSalt());
//			if(user != null && password.equals(user.getPassword())){
//				
////				if(rememberMe != null && "1".equals(rememberMe)){
////					Cookie userCookie = new Cookie("userInfo",username + "==" + password);
////					
////					int seconds=60*60;  
////					userCookie.setMaxAge(seconds);
////					response.addCookie(userCookie);
////				}else{
////					Cookie[] cookies = request.getCookies(); 
////					if(cookies != null && cookies.length > 0){
////						for(Cookie cookie : cookies){
////							String cookieName = cookie.getName();
////							if("userInfo".equals(cookieName)){
////								Cookie new_cookie = new Cookie(cookieName, null); 
////								new_cookie.setMaxAge(0);
////								response.addCookie(new_cookie);  								
////							}
////						}	
////					}	
////				}
//				
//				HttpSession session = request.getSession();
//				session.setAttribute("user", user);
//				session.setAttribute("userName", user.getUserName());
//			}else{
//				request.setAttribute("loginFlag",1);
////				return "redirect:/admin/index";
//			}
//		}else{
//			request.setAttribute("loginFlag", -1);
////			return "/";
//		}
	}
	@RequestMapping(value = "/loginOut", method = { RequestMethod.GET })
	public String loginOut(HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/admin/index";
	}
}
