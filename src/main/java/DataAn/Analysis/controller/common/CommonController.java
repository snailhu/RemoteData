package DataAn.Analysis.controller.common;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import DataAn.Analysis.dto.ConstraintDto;
import DataAn.Analysis.dto.GroupMenu;
import DataAn.Analysis.dto.ParamAttributeDto;
import DataAn.Analysis.dto.ParamBatchDto;
import DataAn.Analysis.dto.ParamGroup;
import DataAn.Analysis.dto.SeriesBtnMenu;
import DataAn.Analysis.dto.SingleParamDto;
import DataAn.Analysis.dto.YearAndParamDataDto;
import DataAn.Util.EhCache;
import DataAn.Util.JsonStringToObj;
import DataAn.common.pageModel.Pager;
import DataAn.galaxyManager.dto.SeriesDto;
import DataAn.galaxyManager.dto.StarDto;
import DataAn.galaxyManager.option.J9SeriesType;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.option.SeriesType;
import DataAn.galaxyManager.service.IJ9Series_Star_Service;
import DataAn.galaxyManager.service.ISeriesService;
import DataAn.galaxyManager.service.IStarService;
import DataAn.mongo.db.MongoService;
import DataAn.routing.RequestConfig;
import DataAn.routing.RoutingService;
import DataAn.sys.dto.ActiveUserDto;


@Controller
public class CommonController {

	@Resource
	private IJ9Series_Star_Service j9Series_Star_Service;
	
	@Resource 
	private MongoService mongoService;
	
	@RequestMapping(value = "/Index", method = { RequestMethod.GET })
	public String goIndex(HttpServletRequest request, HttpServletResponse response) {
		return "index";
	}
	
	@RequestMapping(value = "/getConstraint", method = RequestMethod.GET)
	@ResponseBody
	public List<ConstraintDto> getConstraint(String beginDate,String endDate,String type) throws Exception{
		
		System.out.println("beginDate； " + beginDate);
		System.out.println("endDate: " + endDate);
		System.out.println("type: " + type);
		//test
	//	return j9Series_Star_Service.getFlyWheelParameterList();
		String series = SeriesType.J9_SERIES.getName();
		String star = J9SeriesType.STRA2.getValue();
		String paramType = J9Series_Star_ParameterType.FLYWHEEL.getValue();
		return j9Series_Star_Service.getAllParameterList(beginDate, endDate, series, star, paramType);
	}
	
	
	@RequestMapping(value = "/showPanel", method = { RequestMethod.POST})
	public void showPanel(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="JsonG",required = true) String JsonG) throws Exception {	
		System.out.println("JsonG: " + JsonG);
		Map<String, Class<SingleParamDto>> classMap = new HashMap<String, Class<SingleParamDto>>();
		classMap.put("secectRow", SingleParamDto.class);
		List<ParamGroup> pgs =JsonStringToObj.jsonArrayToListObject(JsonG,ParamGroup.class,classMap);
		System.out.println(pgs);
		EhCache ehCache = new EhCache(); 
		String sessionId = request.getSession().getId();
		ehCache.addToCache(sessionId+"AllJsonData", pgs);		
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
	

	
//	@RequestMapping(value = "/getDate", method = RequestMethod.GET)
//	@ResponseBody
//	public List<String> getDate(
//			HttpServletRequest request,
//			HttpServletResponse response,
//			@RequestParam(value="start",required = true) String start,
//			@RequestParam(value="end",required = true) String end,
//			@RequestParam(value="paramSize",required = true) Integer paramSize
//			) throws Exception{
//			MongodbUtil mg = MongodbUtil.getInstance();
//			List<String> result = mg.getDateList(paramSize,new String[]{start,end});
//			return result;
//		}
	
	
	@RequestMapping(value = "/getMenus", method = RequestMethod.GET)
	@ResponseBody
	public List<GroupMenu> getMenus(
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception{
		EhCache ehCache = new EhCache(); 
		String sessionId = request.getSession().getId();
		@SuppressWarnings("unchecked")
		List<ParamGroup> lPs = (List<ParamGroup>) ehCache.getCacheElement(sessionId+"AllJsonData");
		List<GroupMenu> lgm = new ArrayList<GroupMenu>();
		for(ParamGroup  pg :lPs){
			GroupMenu gm = new GroupMenu();
			gm.setId(pg.getId()+"");
			gm.setText((pg.getId()+1)+"组");
			gm.setIcon("icon-glass");
			gm.setUrl("/DataRemote/showGraphic/"+pg.getId());
			lgm.add(gm);
		}
		return lgm;
	}
	
	//获取 参数组	
	@RequestMapping(value = "/showGraphic/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView showGraphic(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id") Integer id
			) throws Exception{
		EhCache ehCache = new EhCache(); 
		String sessionId = request.getSession().getId();
		@SuppressWarnings("unchecked")
		List<ParamGroup> lPs = (List<ParamGroup>) ehCache.getCacheElement(sessionId+"AllJsonData");
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
				mv.addObject("beginDate", pg.getBeginDate());
				mv.addObject("endDate", pg.getEndDate());
				mv.addObject("nowSeries",pg.getNowSeries());
				mv.addObject("nowStar", pg.getNowStar());
				mv.addObject("component", pg.getComponent());
				mv.addObject("params", params);			
			}
		}
		return mv;
	}
	
	//绘制参数图形是获取数据
	@RequestMapping(value = "/getData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,YearAndParamDataDto> getData(
			HttpServletRequest request,
			HttpServletResponse response,
//			@RequestParam(value="paramNames",required = true) Object paramNames,
//			@RequestParam(value="start",required = true) String start,
//			@RequestParam(value="end",required = true) String end,
//			@RequestParam(value="paramSize",required = true) Integer paramSize,
//			@RequestParam(value="nowSeries",required = true) String nowSeries,
//			@RequestParam(value="nowStar",required = true) String nowStar,
			@RequestParam(value="paramObject",required = true) String paramObject
			) throws Exception{
		//String key = start+end;
//	  	Jedis jedis = RedisPoolUtil.buildJedisPool().getResource(); 
//				if(jedis.exists((key+"year"+filename).getBytes()) && jedis.exists((key+"param"+filename).getBytes())){
//					List<String> year_list = RedisPoolUtil.getList(key+"year"+filename);
//					List<String> parm_list = RedisPoolUtil.getList(key+"param"+filename);
//					YearAndParamDataDto result =  new YearAndParamDataDto();
//					result.setParamValue(parm_list);
//					result.setYearValue(year_list);	
//					return result;	
//				}   
	System.out.println("paramObject: " + paramObject);
	Map<String, Class<ParamAttributeDto>> classMap = new HashMap<String, Class<ParamAttributeDto>>();
	classMap.put("paramAttribute", ParamAttributeDto.class);
	ParamBatchDto pbd =JsonStringToObj.jsonToObject(paramObject,ParamBatchDto.class,classMap);
//	 YearAndParamDataDto result = mongoService.getList(paramSize,new String[]{start,end,paramNames,nowSeries,nowStar,component});				
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(5));
//		executor.execute(new Runnable() {
//	            public void run() {
//	            	RedisPoolUtil.saveList(key+"year"+filename, result.getYearValue());
//	        		RedisPoolUtil.saveList(key+"param"+filename, result.getParamValue());				
//	            }
//	        });

	RoutingService routingService=new RoutingService();
	RequestConfig requestConfig=new RequestConfig();
	requestConfig.setPropertyCount(pbd.getParamAttribute().size());
	String[] properties=new String[pbd.getParamAttribute().size()];
	int i=0;
	for(ParamAttributeDto paramAttributeDto: pbd.getParamAttribute()){
		properties[i++]=paramAttributeDto.getValue();
	}
	requestConfig.setProperties(properties);
	requestConfig.setSeries(pbd.getNowSeries());
	requestConfig.setStar(pbd.getNowStar());
	requestConfig.setDevice(pbd.getComponent());
	requestConfig.setTimeStart(pbd.getStartTime());
	requestConfig.setTimeEnd(pbd.getEndTime());
	
	Map<String, YearAndParamDataDto> result = routingService.getData(requestConfig);	
	
	return result;			
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
			//EhCache ehCache = new EhCache();
			//@SuppressWarnings("unchecked")		
			List<SeriesDto> lsb = seriesService.getAllSeries() ;
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
	//点击卫星图片跳转到图表管理页面中相应的卫星页面
	@RequestMapping(value = "/analysisData/{SeriesId}/{StarId}")
	@ResponseBody 
	public ModelAndView showFlyWheelOrGyroscope(
				@PathVariable String SeriesId, 
				@PathVariable String StarId,
				HttpServletRequest request,HttpServletResponse response
				){
		ModelAndView modelview = new ModelAndView("/secondStyle/dataAnalysis");	
		String nowSeriesId=null;
		String nowStar=null;
		try {
			nowSeriesId = new String(SeriesId.getBytes("ISO-8859-1"),"UTF-8");
			nowStar = new String(StarId.getBytes("ISO-8859-1"),"UTF-8");					 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		SeriesDto  nowSeries = seriesService.getSeriesDto(Long.parseLong(nowSeriesId));
		//当前所在系列
		modelview.addObject("nowSeries", nowSeries.getName());
		//当前所在星号
		modelview.addObject("nowStar", nowStar);
		
		HttpSession session = request.getSession();
		ActiveUserDto acticeUser = (ActiveUserDto) session.getAttribute("activeUser");
		String flywheel = J9Series_Star_ParameterType.FLYWHEEL.getValue();
		String type = acticeUser.getPermissionItems().get(flywheel);
		String value = "";
		String name = "";
		if (StringUtils.isNotBlank(type)) {
			value = J9Series_Star_ParameterType.getJ9SeriesStarParameterType(type).getValue();
			name = J9Series_Star_ParameterType.getJ9SeriesStarParameterType(type).getName();
		}else{
			value = J9Series_Star_ParameterType.TOP.getValue();
			name = J9Series_Star_ParameterType.TOP.getName();
		}
		//当前所在参数名称
		modelview.addObject("nowParameterTypeValue", value);
		modelview.addObject("nowParameterTypeName", name);
		
		return modelview;
	}	
}
