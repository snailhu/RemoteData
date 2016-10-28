package DataAn.fileSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class NewController {

	// 返回欢迎登录界面
	@RequestMapping("/index")
	public String uploadHome() {
		System.out.println("come in uploadHome");
		return "/admin/index";
	}
	

}
