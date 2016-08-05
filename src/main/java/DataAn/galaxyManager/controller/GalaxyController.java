package DataAn.galaxyManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/galaxy")
public class GalaxyController {

	// 返回星系管理界面
	@RequestMapping("/")
	public String toGalaxyIndex() {
//		System.out.println("come in toGalaxyIndex");
		return "/admin/galaxy/index";
	}
	// 返回星系管理界面
	@RequestMapping("/index")
	public String galaxyIndex() {
//		System.out.println("come in galaxyIndex");
		return "/admin/galaxy/index";
	}
}
