package DataAn.Analysis.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class CommonController {

	@RequestMapping(value = "/Index", method = { RequestMethod.GET })
	public String goIndex(HttpServletRequest request, HttpServletResponse response) {
		return "index";
	}
	
	@RequestMapping(value = "/showPanel", method = { RequestMethod.GET })
	public String showPanel(HttpServletRequest request, HttpServletResponse response) {
		return "showPanel";
	}
}
