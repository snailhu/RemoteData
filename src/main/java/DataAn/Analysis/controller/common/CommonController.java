package DataAn.Analysis.controller.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import DataAn.Analysis.dto.ConstraintDto;
import DataAn.common.pageModel.Pager;
import DataAn.fileSystem.option.FlyWheelDataType;
import DataAn.fileSystem.service.IFlyWheelService;
import DataAn.fileSystem.service.IJ9Series_Star_Service;
import DataAn.galaxyManager.dao.ISeriesDao;
import DataAn.galaxyManager.domain.Series;
import DataAn.mongo.db.MongodbUtil;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import DataAn.Analysis.dto.AllJsonData;
import DataAn.Analysis.dto.GroupMenu;
import DataAn.Analysis.dto.ParamGroup;
import DataAn.Analysis.dto.SingleParamDto;
import DataAn.Analysis.dto.SeriesBtnMenu;
import DataAn.Util.EhCache;
import DataAn.Util.JsonStringToObj;
import DataAn.galaxyManager.domain.*;
import DataAn.galaxyManager.dto.SeriesDto;
import DataAn.galaxyManager.dto.StarDto;
import DataAn.galaxyManager.service.ISeriesService;
import DataAn.galaxyManager.service.IStarService;
import DataAn.galaxyManager.service.impl.SeriesServiceImpl;


@Controller
public class CommonController {

	@Resource
	private IJ9Series_Star_Service j9Series_Star_Service;
	
	@RequestMapping(value = "/Index", method = { RequestMethod.GET })
	public String goIndex(HttpServletRequest request, HttpServletResponse response) {
		return "index";
	}
	
	@RequestMapping(value = "/getConstraint", method = RequestMethod.GET)
	@ResponseBody
	public List<ConstraintDto> getConstraint(String beginDate,String endDate) throws Exception{
		
//		System.out.println("beginDate； " + beginDate);
//		System.out.println("endDate: " + endDate);
		//test
		return j9Series_Star_Service.getFlyWheelParameterList();
//		return j9Series_Star_Service.getAllParameterListFromBeginDateToEndDate(beginDate, endDate);
	}
	
	
	@RequestMapping(value = "/showPanel", method = { RequestMethod.POST})
	public void showPanel(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="JsonG",required = true) String JsonG) throws Exception {	
		Map<String, Class<SingleParamDto>> classMap = new HashMap<String, Class<SingleParamDto>>();
		classMap.put("secectRow", SingleParamDto.class);
		List<ParamGroup> pgs =JsonStringToObj.jsonArrayToListObject(JsonG,ParamGroup.class,classMap);
		EhCache ehCache = new EhCache(); 
		ehCache.addToCache("AllJsonData", pgs);		
	}
	

	@RequestMapping(value = "/showPanel", method = { RequestMethod.GET})
	public ModelAndView showGraphic(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		EhCache ehCache = new EhCache(); 
		@SuppressWarnings("unchecked")
		List<ParamGroup> lPs = (List<ParamGroup>) ehCache.getCacheElement("AllJsonData");
		ModelAndView mv = new ModelAndView("/secondStyle/showGraphicByGroup");
		mv.addObject("lPs", lPs);
		return mv;
		}
	

	
	@RequestMapping(value = "/getDate", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getDate(
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception{
			MongodbUtil mg = MongodbUtil.getInstance();
			List<String> result = mg.getDateList("tesx");
			return result;
		}
	
	
	@RequestMapping(value = "/getMenus", method = RequestMethod.GET)
	@ResponseBody
	public List<GroupMenu> getMenus(
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception{
		EhCache ehCache = new EhCache(); 
		@SuppressWarnings("unchecked")
		List<ParamGroup> lPs = (List<ParamGroup>) ehCache.getCacheElement("AllJsonData");
		List<GroupMenu> lgm = new ArrayList<GroupMenu>();
		for(ParamGroup  pg :lPs){
//			String text="";
//			List<SingleParamDto> spds =  pg.getSecectRow();
//			for(SingleParamDto sd :spds){
//				text+=sd.getName();
//			}
			GroupMenu gm = new GroupMenu();
			gm.setId(pg.getId()+"");
			gm.setText((pg.getId()+1)+"组");
			gm.setIcon("icon-glass");
			gm.setUrl("/DataRemote/showGraphic/"+pg.getId());
			lgm.add(gm);
		}
		return lgm;
	}
	
	
	@RequestMapping(value = "/showGraphic/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView showGraphic(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id") Integer id
			) throws Exception{
		EhCache ehCache = new EhCache(); 
		@SuppressWarnings("unchecked")
		List<ParamGroup> lPs = (List<ParamGroup>) ehCache.getCacheElement("AllJsonData");
//		List<String> params = new ArrayList<String>();
		List<SingleParamDto> params = new ArrayList<SingleParamDto>();
		Map<String,String> map = j9Series_Star_Service.getAllParameterList_simplyZh_and_en();
		ModelAndView mv = new ModelAndView("/secondStyle/graphicShow");
		for(ParamGroup  pg :lPs){		
			if(pg.getId()==id){
				List<SingleParamDto> spds = pg.getSecectRow();
				for(SingleParamDto spd : spds){
					spd.setValue(map.get(spd.getName()));
					params.add(spd);
				}	
				mv.addObject("params", params);
			}
		}
		return mv;
	}
		
	@RequestMapping(value = "/getData", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getData(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="filename",required = true) String filename,
			@RequestParam(value="start",required = false) String start,
			@RequestParam(value="end",required = false) String end
			) throws Exception{
		MongodbUtil mg = MongodbUtil.getInstance();
		if("".equals(start) || "".equals(start)){
			List<String> result = mg.findAllByTie(filename);
			return result ;	
			
		}else{	
			
			List<String> result = mg.getDateList(new String[]{start,end,filename});
			return result ;				
		}
		
	}
	
	
	@RequestMapping(value = "/showtab", method = RequestMethod.GET)
	public String showtab(
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception{
		return "DataAnalysis" ;				
	}
	
	//获取卫星系列信息，生成综合状态预览页面的卫星系列按钮
	@Resource
	private ISeriesService seriesService;
	@RequestMapping(value = "/getSeriesBtnMenus" ,method =RequestMethod.GET)
	@ResponseBody
	public List<SeriesBtnMenu> getSeriesBtnMenus(
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
			EhCache ehCache = new EhCache();
			@SuppressWarnings("unchecked")		
			Pager<SeriesDto> pager= seriesService.getRoleList(0, 100);
			List<SeriesDto> lsb = pager.getRows();
			List<SeriesBtnMenu> lseriesbtnMenu =new ArrayList<SeriesBtnMenu>();
			for(SeriesDto pg:lsb){
				SeriesBtnMenu sbtnm =new SeriesBtnMenu();
				sbtnm.setId(pg.getId());
				sbtnm.setName(pg.getName());
				sbtnm.setDescription(pg.getDescription());
				sbtnm.setCreateDate(pg.getCreateDate());
				lseriesbtnMenu.add(sbtnm);
			}
		return lseriesbtnMenu;
	}
	
	//获取一个系列的所有卫星信息，用于生成卫星图片
	@Resource private IStarService starService;
		@RequestMapping(value = "/conditionMonitoring/getSatellites" ,method =RequestMethod.POST)
		@ResponseBody
		public List<StarDto> getSatellites(
				@RequestParam(value = "seriesId", required = true) long seriesId,
				HttpServletRequest request, 
				HttpServletResponse response) throws Exception{			
				List<StarDto> lstarinfor= starService.getStarsBySeriesId(seriesId);
			return lstarinfor;
		}
}
