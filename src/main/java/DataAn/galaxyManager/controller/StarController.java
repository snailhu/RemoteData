package DataAn.galaxyManager.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import DataAn.galaxyManager.dto.StarDto;
import DataAn.galaxyManager.service.IStarService;

@Controller
@RequestMapping("/admin/star")
public class StarController {

	@Resource
	private IStarService starService;
	
	@RequestMapping("/getStars")
	@ResponseBody
	public List<StarDto> getStarsBySeriesId(long seriesId){
		List<StarDto> list = starService.getStarsBySeriesId(seriesId);
		return list;
	}
}
