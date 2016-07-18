package DataAn.Analysis.controller.common;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import DataAn.Analysis.dto.ConstraintDto;
import DataAn.fileSystem.service.IFlyWheelService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import DataAn.Analysis.dto.AllJsonData;
import DataAn.Analysis.dto.ParamGroup;
import DataAn.Util.EhCache;
import DataAn.Util.JsonStringToObj;


@Controller
public class CommonController {

	@Resource
	private IFlyWheelService flyWheelService;
	
	@RequestMapping(value = "/Index", method = { RequestMethod.GET })
	public String goIndex(HttpServletRequest request, HttpServletResponse response) {
		return "index";
	}
	
	@RequestMapping(value = "getConstraint", method = RequestMethod.POST)
	@ResponseBody
	public List<ConstraintDto> getConstraint() throws Exception{
		
		return flyWheelService.getFlyWheelParameterList();
	}
	@RequestMapping(value = "/showPanel", method = { RequestMethod.POST})
	public void showPanel(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="JsonG",required = true) String JsonG) throws Exception {		
		AllJsonData ad =JsonStringToObj.jsonToObject(JsonG,AllJsonData.class);
		EhCache ehCache = new EhCache(); 
		ehCache.addToCache("AllJsonData", ad);		
	}
	

	@RequestMapping(value = "/showPanel", method = { RequestMethod.GET})
	public ModelAndView showGraphic(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		EhCache ehCache = new EhCache(); 
		AllJsonData ad = (AllJsonData) ehCache.getCacheElement("AllJsonData");
		List<ParamGroup> lPs = ad.getAlldata();
		ModelAndView mv = new ModelAndView("/secondStyle/showGraphic");
		mv.addObject("lPs", lPs);
		return mv;
		}
	
}
