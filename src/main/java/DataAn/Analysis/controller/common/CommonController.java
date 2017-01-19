package DataAn.Analysis.controller.common;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import DataAn.common.utils.DateUtil;
import DataAn.galaxyManager.dto.SeriesDto;
import DataAn.galaxyManager.dto.StarDto;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.service.IJ9Series_Star_Service;
import DataAn.galaxyManager.service.IParameterService;
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
	private IParameterService parameter_Service;
	@Resource 
	private MongoService mongoService;
	
	@RequestMapping(value = "/Index", method = { RequestMethod.GET })
	public String goIndex(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("come in Index "+ DateUtil.format(new Date()));
//		return "index";
		//return "redirect:/conditionMonitoring";
		return "redirect:/Index3d";
	}
	
	@RequestMapping(value="/Index3d", method = { RequestMethod.GET })
	public String index3d() {
		return "/index3d";
	}
	
	@RequestMapping(value = "/home", method = { RequestMethod.GET })
	public String home(HttpServletRequest request, HttpServletResponse response) {
		return "index";
	}
	
	//根据用户选择的星系时间区间判断在 参数列表里显示的参数
	@RequestMapping(value = "/getConstraint", method = RequestMethod.GET)
	@ResponseBody
	public List<ConstraintDto> getConstraint(String beginDate,String endDate,String Series_current,String Star_current,String type_current) 
			throws Exception{
		System.out.println("获取echart 画图 参数列表。。");
		System.out.println("beginDate: " + beginDate);
		System.out.println("endDate: " + endDate);
		System.out.println("Series_current: " + Series_current);
		System.out.println("Star_current: " + Star_current);
		System.out.println("type_current: " + type_current);
		if(StringUtils.isNotBlank(Series_current) && StringUtils.isNotBlank(Star_current) && StringUtils.isNotBlank(type_current))
			if(StringUtils.isNotBlank(beginDate) || StringUtils.isNotBlank(endDate)){
				return j9Series_Star_Service.getAllParameterList(beginDate, endDate, Series_current, Star_current, type_current);			
			}
		return new ArrayList<ConstraintDto>();
	}
	
	//点击“提交分组”按钮，向查看曲线页面以post方式提交参数“AllRowselect”
	@RequestMapping(value = "/showPanel", method = { RequestMethod.POST})
	public void showPanel(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="JsonG",required = true) String JsonG) throws Exception {
		Map<String, Class<SingleParamDto>> classMap = new HashMap<String, Class<SingleParamDto>>();
		classMap.put("secectRow", SingleParamDto.class);
		List<ParamGroup> pgs =JsonStringToObj.jsonArrayToListObject(JsonG,ParamGroup.class,classMap);
		EhCache ehCache = new EhCache(); 
		String sessionId = request.getSession().getId();
		ehCache.addToCache(sessionId+"AllJsonData", pgs);		
	}
	
	//点击“提交分组”按钮，若果post请求成功，则跳转页面
	@RequestMapping(value = "/showPanel", method = { RequestMethod.GET})
	public ModelAndView showGraphic(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		EhCache ehCache = new EhCache(); 
		@SuppressWarnings("unchecked")
		List<ParamGroup> lPs = (List<ParamGroup>) ehCache.getCacheElement("AllJsonData");
		ModelAndView mv = new ModelAndView("/admin/ftltojsp/showGraphicByGroup");
		mv.addObject("lPs", lPs);
		return mv;
		}
	
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
			gm.setText("第"+(pg.getId()+1)+"组");
			gm.setIcon("icon-glass");
			gm.setUrl("/DataRemote/showGraphic/"+pg.getId());
			lgm.add(gm);
		}
		return lgm;
	}
	
	//获取 参数组	在当页面跳转到showpanel页面后，通过点击分组按钮，将本组别的所需要的
	//系列、星、设备、参数、开始时间、结束时间传递给相应的tab页面，用于绘制曲线。
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
        
		ModelAndView mv = new ModelAndView("/secondStyle/graphicShow");
		//ModelAndView mv = new ModelAndView("/admin/ftltojsp/graphicShow");
		for(ParamGroup  pg :lPs){		
			if(pg.getId()==id){
				List<SingleParamDto> spds = pg.getSecectRow();
				for(SingleParamDto spd : spds){
					String sequencevalue =parameter_Service.getParameter_en_by_simpleZh(pg.getNowSeries(),pg.getNowStar(),pg.getComponent(),spd.getName());
					spd.setValue(sequencevalue);
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
			@RequestParam(value="paramObject",required = true) String paramObject
			) throws Exception{
	long begin = System.currentTimeMillis();
	//paramObiect的结构eg:{"nowSeries":"j9name","nowStar":"02","component":"flywheel","startTime":"2016-06-22 13:02:08","endTime":"2016-06-23 13:02:23","paramAttribute":[{"name":"飞轮温度Xa(00815)","value":"","y":"0"},{"name":"飞轮温度Ya(00817)","value":"","y":"0"},{"name":"飞轮温度Za(00819)","value":"","y":"0"}]}
	//将参数信息放进缓存，供绘制曲线tab自己在读取
	EhCache ehCache = new EhCache(); 
	String sessionId = request.getSession().getId();
	ehCache.addToCache(sessionId+"paramObject", paramObject);
	
	Map<String, Class<ParamAttributeDto>> classMap = new HashMap<String, Class<ParamAttributeDto>>();
	classMap.put("paramAttribute", ParamAttributeDto.class);
	ParamBatchDto pbd =JsonStringToObj.jsonToObject(paramObject,ParamBatchDto.class,classMap);

	RoutingService routingService=new RoutingService();
	RequestConfig requestConfig=new RequestConfig();
	requestConfig.setPropertyCount(pbd.getParamAttribute().size());
	//将propeities从字符串数据转换成对象数组
	ParamAttributeDto properties[]=new ParamAttributeDto[pbd.getParamAttribute().size()];
	int i=0;
	List<ParamAttributeDto> listparam=pbd.getParamAttribute();
	for(ParamAttributeDto paramAttributeDto: listparam){
		properties[i++]=paramAttributeDto;	
	}
	requestConfig.setProperties(properties);
	requestConfig.setSeries(pbd.getNowSeries());
	requestConfig.setStar(pbd.getNowStar());
	requestConfig.setDevice(pbd.getComponent());
	requestConfig.setTimeStart(pbd.getStartTime());
	requestConfig.setTimeEnd(pbd.getEndTime());
	
	Map<String, YearAndParamDataDto> result = routingService.getData(requestConfig);
	Map<String,YearAndParamDataDto> linkedResult = new LinkedHashMap<String,YearAndParamDataDto>();
	for (ParamAttributeDto paramAttributeDto : listparam) {
		linkedResult.put(paramAttributeDto.getValue(), result.get(paramAttributeDto.getValue()));
	}
	
	return linkedResult;			
	}
	
	//鼠标滚轮滚动时
		@RequestMapping(value = "/showGraphic/getDatabytap", method = RequestMethod.POST)
		@ResponseBody
		public Map<String,YearAndParamDataDto> getDataDependDataZoom(
				HttpServletRequest request,
				@RequestParam(value="startDate",required = true) String startDate,
				@RequestParam(value="endDate",required = true) String endDate

				) throws Exception{
			Map<String, Class<ParamAttributeDto>> classMap = new HashMap<String, Class<ParamAttributeDto>>();
			classMap.put("paramAttribute", ParamAttributeDto.class);					
			EhCache ehCache = new EhCache();
			String sessionId = request.getSession().getId();
			System.out.println("在Tap页鼠标滚动时 sessionid:"+sessionId);
			String paramObject =  (String) ehCache.getCacheElement(sessionId+"paramObject");
			ParamBatchDto pbd =JsonStringToObj.jsonToObject(paramObject,ParamBatchDto.class,classMap);
			RoutingService routingService=new RoutingService();
			RequestConfig requestConfig=new RequestConfig();
			requestConfig.setPropertyCount(pbd.getParamAttribute().size());
			//String[] properties=new String[pbd.getParamAttribute().size()];
			ParamAttributeDto[] properties=new ParamAttributeDto[pbd.getParamAttribute().size()];
			int i=0;
			List<ParamAttributeDto> listparam=pbd.getParamAttribute();
			for(ParamAttributeDto paramAttributeDto: listparam){
				properties[i++]=paramAttributeDto;
			}
			requestConfig.setProperties(properties);
			requestConfig.setSeries(pbd.getNowSeries());
			requestConfig.setStar(pbd.getNowStar());
			requestConfig.setDevice(pbd.getComponent());
			requestConfig.setTimeStart(startDate);
			requestConfig.setTimeEnd(endDate);
			
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
		ModelAndView modelview = new ModelAndView("/admin/ftltojsp/dataAnalysis");
		String nowSeriesId=null;
		String nowStarId=null;
		try {
			nowSeriesId = new String(SeriesId.getBytes("ISO-8859-1"),"UTF-8");
			nowStarId = new String(StarId.getBytes("ISO-8859-1"),"UTF-8");					 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		SeriesDto nowSeries = seriesService.getSeriesDtoById(Long.parseLong(nowSeriesId));
		//当前所在系列
		//modelview.addObject("nowSeries", nowSeries.getName());
		//把卫星所在系列的系列编码传递到参数选择页面，供参数查询时使用
		modelview.addObject("nowSeries", nowSeries.getCode());
		modelview.addObject("nowSeriesname",nowSeries.getName());
		//当前所在星号
		modelview.addObject("nowStar", nowStarId);
		modelview.addObject("nowStarname",starService.getStarDtoById(Long.parseLong(nowStarId)).getName());
		
		String value = "";
		String name = "";
		HttpSession session = request.getSession();
		ActiveUserDto acticeUser = (ActiveUserDto) session.getAttribute("activeUser");
		if(acticeUser.getPermissionItems() != null ){
			String type = acticeUser.getPermissionItems().get(J9Series_Star_ParameterType.TOP.getValue());
			if (StringUtils.isNotBlank(type)) {
				value = J9Series_Star_ParameterType.TOP.getValue();
				name = J9Series_Star_ParameterType.TOP.getName();
			}
			type = acticeUser.getPermissionItems().get(J9Series_Star_ParameterType.FLYWHEEL.getValue());
			if (StringUtils.isNotBlank(type)) {
				value = J9Series_Star_ParameterType.getJ9SeriesStarParameterType(type).getValue();
				name = J9Series_Star_ParameterType.getJ9SeriesStarParameterType(type).getName();
			}
		}
		if(StringUtils.isNotBlank(value)){
			//当前所在参数名称
			modelview.addObject("nowParameterTypeValue", value);
			modelview.addObject("nowParameterTypeName", name);			
		}else{
			return new ModelAndView("redirect:/refuse");
		}
		
		return modelview;
	}	
}
