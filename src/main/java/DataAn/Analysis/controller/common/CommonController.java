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
}
