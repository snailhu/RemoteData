package DataAn.fileSystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import DataAn.fileSystem.dto.EchartsDto;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/admin")
public class HomeController {

	// 返回欢迎登录界面
	@RequestMapping("/index")
	public String uploadHome() {
		System.out.println("come in uploadHome");
		return "/admin/index";
	}
	

}
