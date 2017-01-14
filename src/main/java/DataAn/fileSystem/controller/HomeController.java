package DataAn.fileSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	// 返回欢迎登录界面
	@RequestMapping("/new/index")
	public String index() {
		return "/admin/new/index";
	}
	
	// 返回欢迎登录界面
	@RequestMapping(value="/newIndex3d", method = { RequestMethod.GET })
	public String index3d() {
		return "/index3d";
	}
}
