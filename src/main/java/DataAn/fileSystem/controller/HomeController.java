package DataAn.fileSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/new")
public class HomeController {

	// 返回欢迎登录界面
	@RequestMapping("/index")
	public String index() {
		return "/admin/new/index";
	}
	

}
