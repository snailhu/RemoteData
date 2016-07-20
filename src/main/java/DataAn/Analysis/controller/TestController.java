package DataAn.Analysis.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import DataAn.Analysis.service.OperateDataService;


@Controller
public class TestController {
	
	
	@Resource
	private OperateDataService operateDataService;
	
	
	@RequestMapping(value = "/test", method = { RequestMethod.GET })
	public String goIndex(HttpServletRequest request, HttpServletResponse response) {
		return "/secondStyle/dataAnalysis";
	}
	
	@RequestMapping(value = "/showTest", method = { RequestMethod.GET })
	public String showTest(HttpServletRequest request, HttpServletResponse response) {
		return "/secondStyle/testEchars";
	}
	
	@RequestMapping(value = "/analysisData", method = { RequestMethod.GET })
	public String analysisData(HttpServletRequest request, HttpServletResponse response) {
		return "/secondStyle/dataAnalysis";
	}
	
	

	

	
	
//	@RequestMapping(value = "/getData", method = { RequestMethod.GET })
//	public ModelAndView getData(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView mv = new ModelAndView("showPanelData");
//		String [] dataArray = operateDataService.getDateArray();
//		mv.addObject("dataArray", dataArray);
//		return mv;
//	}
	
	
	@RequestMapping(value = "/getJsonData", method = RequestMethod.POST)
	public @ResponseBody
	String uploadExcel(MultipartFile uploadExcel) {
		return null;
	}
}
