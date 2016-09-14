package DataAn.wordManager.controller;

import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DataAn.wordManager.service.IFlyWheelReoportService;

@Controller
public class CreateReportController {
	@Resource
	private IFlyWheelReoportService flyWhereReoportService;
	@RequestMapping(value = { "/createReport" }, method = { RequestMethod.GET})
	public void  goCreateReport( HttpServletResponse response) throws Exception
	{
		flyWhereReoportService.CreateReport("flyWherrereport1");
		//return "/secondStyle/wordshow";
		String reportNmae="飞轮报告1.doc";
		response.sendRedirect("secondStyle/wordshow?file="
				+ URLEncoder.encode(reportNmae, "UTF-8"));
	}
	
}
