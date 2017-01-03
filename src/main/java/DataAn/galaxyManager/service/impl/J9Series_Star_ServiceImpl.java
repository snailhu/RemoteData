package DataAn.galaxyManager.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import DataAn.Analysis.dto.ConstraintDto;
import DataAn.Util.EhCache;
import DataAn.fileSystem.dao.IDateParametersDao;
import DataAn.fileSystem.domain.DateParameters;
import DataAn.galaxyManager.dto.ParameterDto;
import DataAn.galaxyManager.option.J9SeriesType;
import DataAn.galaxyManager.option.J9Series_Star_FlywheelParameterConfig;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.option.J9Series_Star_TopParameterConfig;
import DataAn.galaxyManager.option.SeriesType;
import DataAn.galaxyManager.service.IJ9Series_Star_Service;
import DataAn.galaxyManager.service.IParameterService;

@Service
public class J9Series_Star_ServiceImpl implements IJ9Series_Star_Service{

	private EhCache ehCache = new EhCache("j9seriesConfig");
	@Resource
	private IDateParametersDao parametersDao;
	@Resource
	private IParameterService paramService;
	
	@Override
	public List<ConstraintDto> getAllParameterList(
			String beginDate, String endDate,String type) throws Exception {

//		if (StringUtils.isNotBlank(type)) {
//			type = J9Series_Star_ParameterType.FLYWHEEL.getValue();
//		}
		List<DateParameters> dpList = parametersDao.selectByYear_month_dayAndParameterType(beginDate, endDate,type);
		if(dpList != null && dpList.size() > 0){
			Map<String,Integer> tempMap = new HashMap<String,Integer>();
			Integer count = null;
			for (DateParameters dp : dpList) {
				String[] items = dp.getParameters().split(",");
				for (String item : items) {
					if(!item.equals("时间")){
//						inclueSet.add(item);
						count = tempMap.get(item);
						if(count == null){
							tempMap.put(item, 1);
						}else{
							tempMap.put(item, count + 1);
						}
					}
				}
			}
			Map<String,String> map = this.getAllParameterList_allZh_and_en();
			Map<String,String> simplyZh_and_enMap = new HashMap<String,String>();
			Set<String> keys = tempMap.keySet();
			for (String parameter : keys) {
//				if(tempMap.get(parameter) == dpList.size()){
//					simplyZh_and_enMap.put(parameter.split(":")[1], map.get(parameter));					
//				}else{
//					simplyZh_and_enMap.put(parameter.split(":")[1] + "-时间不连续", map.get(parameter));
//				}
				simplyZh_and_enMap.put(parameter.split(":")[1], map.get(parameter));
			}
			List<String> dataTypes = null;
			if(type.equals(J9Series_Star_ParameterType.FLYWHEEL.getValue())){
				dataTypes = J9Series_Star_ParameterType.getFlywheelTypeOnParamTypeName();
			}else{
				dataTypes = J9Series_Star_ParameterType.getTopTypeOnName();
			}
			return this.getFlyWheelOrTopParameterList(simplyZh_and_enMap,dataTypes);
		}
		return null;
	}

	@Override
	public List<ConstraintDto> getAllParameterList(String beginDate,String endDate, 
			String series, String star, String paramType)throws Exception {
		List<DateParameters> dpList = parametersDao.selectByYear_month_dayAndParameterType(beginDate,endDate,series,star,paramType);
		if(dpList != null && dpList.size() > 0){
			Set<String> paramSet = new HashSet<String>();
			for (DateParameters dp : dpList) {
				String[] items = dp.getParameters().split(",");
				for (String item : items) {
					if(!item.equals("时间") && !item.equals("接收地方时") &&!item.equals("星上系统钟")){
						paramSet.add(item);
					}
				}
			}
			Map<String,String> simplyZh_and_enMap = new HashMap<String,String>();
			String param_en = "";
			for (String param_zh : paramSet) {
				param_en = paramService.getParameter_en_by_allZh(series, star,paramType, param_zh);
				simplyZh_and_enMap.put(param_zh.split(":")[1], param_en);
			} //TODO 参数分类显示
			List<String> dataTypes = null;
			if(paramType.equals(J9Series_Star_ParameterType.FLYWHEEL.getValue())){
				dataTypes = J9Series_Star_ParameterType.getFlywheelTypeOnParamTypeName();
			}else{
				dataTypes = J9Series_Star_ParameterType.getTopTypeOnName();
			}
			return this.getFlyWheelOrTopParameterList(simplyZh_and_enMap,dataTypes);
		}
				
		return null;
	}

	@Override
	public List<ConstraintDto> getParameterList(String series, String star,
			String paramType) {
		List<ParameterDto> list = paramService.getParameterList(series, star, paramType);
		Map<String,String> map = new HashMap<String,String>();
		for (ParameterDto parameterDto : list) {
			map.put(parameterDto.getSimplyName(), parameterDto.getCode());
		}
		List<String> dataTypes = null;
		if(paramType.equals(J9Series_Star_ParameterType.FLYWHEEL.getValue())){
			dataTypes = J9Series_Star_ParameterType.getFlywheelTypeOnParamTypeName();
		}else{
			dataTypes = J9Series_Star_ParameterType.getTopTypeOnName();
		}
		return this.getFlyWheelOrTopParameterList(map,dataTypes);
	}


	@Override
	public List<ConstraintDto> getFlyWheelParameterList(String series,String star) {
		if (!StringUtils.isNotBlank(series)) {
			series = SeriesType.J9_SERIES.getName();
		}
		if (!StringUtils.isNotBlank(star)) {
			star = J9SeriesType.STRA2.getValue();
		}
		String paramType = J9Series_Star_ParameterType.FLYWHEEL.getValue();
		List<ParameterDto> list = paramService.getParameterList(series, star, paramType);
		Map<String,String> map = new HashMap<String,String>();
		for (ParameterDto parameterDto : list) {
			if(StringUtils.isNotBlank(parameterDto.getSimplyName())){
				map.put(parameterDto.getSimplyName(), parameterDto.getCode());				
			}
		}
		List<String> flyWheelDataTypes = J9Series_Star_ParameterType.getFlywheelTypeOnParamTypeName();
		return this.getFlyWheelOrTopParameterList(map, flyWheelDataTypes);
	}

	
	@Override
	public String getFlyWheelParameterType(String series, String star,
			String paramType, String param_en){
//		series = SeriesType.J9_SERIES.getName();
//		star = J9SeriesType.STRA2.getValue();
//		String paramType = J9Series_Star_ParameterType.FLYWHEEL.getValue();
		if (StringUtils.isNotBlank(series) && StringUtils.isNotBlank(star) && StringUtils.isNotBlank(paramType)) {
			return paramService.getParameter_dataType_by_en(series, star, paramType, param_en);
		}
		return null;
	}
	

	@Override
	public String getFlyWheelName(String series, String star,String paramType, String param_en){
//		series = SeriesType.J9_SERIES.getName();
//		star = J9SeriesType.STRA2.getValue();
//		String paramType = J9Series_Star_ParameterType.FLYWHEEL.getValue();
		if (StringUtils.isNotBlank(series) && StringUtils.isNotBlank(star) && StringUtils.isNotBlank(paramType)) {
			return paramService.getParameter_deviceName_by_en(series, star, paramType, param_en);
		}
		return null;
	}
	
	@Override
	public List<ConstraintDto> getTopParameterList(String series, String star) {
		if (!StringUtils.isNotBlank(series)) {
			series = SeriesType.J9_SERIES.getName();
		}
		if (!StringUtils.isNotBlank(star)) {
			star = J9SeriesType.STRA2.getValue();
		}
		String paramType = J9Series_Star_ParameterType.TOP.getValue();
		List<ParameterDto> list = paramService.getParameterList(series, star, paramType);
		Map<String,String> map = new HashMap<String,String>();
		for (ParameterDto parameterDto : list) {
			if(StringUtils.isNotBlank(parameterDto.getSimplyName())){
				map.put(parameterDto.getSimplyName(), parameterDto.getCode());				
			}
		}
		List<String> topDataTypes = J9Series_Star_ParameterType.getTopTypeOnName();
		return this.getFlyWheelOrTopParameterList(map, topDataTypes);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getAllParameterList_en_and_simplyZh() throws Exception {
		Map<String,String> map = (Map<String, String>) ehCache.getCacheElement("allParameterList_en_and_simplyZh");
		if(map == null || map.size() == 0){
//			System.out.println("getAllParameterList_en_and_simplyZh cache is null");
			map = new HashMap<String,String>();
			Map<String,String> map2 =  this.getAllParameterList_allZh_and_en();
			Set<String> keys = map2.keySet();
			for (String key : keys) {
				if(!key.equals("时间") && !key.equals("接收地方时")){
					map.put(map2.get(key), key.split(":")[1]);					
				}
			}
			ehCache.addToCache("allParameterList_en_and_simplyZh", map);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getAllParameterList_en_and_allZh() throws Exception {
		Map<String,String> map = (Map<String, String>) ehCache.getCacheElement("allParameterList_en_and_allZh");
		if(map == null || map.size() == 0){
//			System.out.println("getAllParameterList_en_and_allZh cache is null");
			map = new HashMap<String,String>();
			Map<String,String> map2 =  this.getAllParameterList_allZh_and_en();
			Set<String> keys = map2.keySet();
			for (String key : keys) {
				map.put(map2.get(key), key);
			}
			ehCache.addToCache("allParameterList_en_and_allZh", map);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getAllParameterList_simplyZh_and_en()throws Exception {
		Map<String,String> map = (Map<String, String>) ehCache.getCacheElement("allParameterList_simplyZh_and_en");
		if(map == null || map.size() == 0){
//			System.out.println("getAllParameterList_simplyZh_and_en cache is null");
			map = this.getAllParameterList_simplyZh_and_en(this.getAllParameterList_allZh_and_en());
			ehCache.addToCache("allParameterList_simplyZh_and_en", map);
		}
		return map;
	}
	
	private Map<String, String> getAllParameterList_simplyZh_and_en(Map<String,String> map2)throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		Set<String> keys = map2.keySet();
		for (String key : keys) {
			if(!key.equals("时间") && !key.equals("接收地方时")){
				map.put(key.split(":")[1], map2.get(key));					
			}
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,String> getAllParameterList_allZh_and_en() throws Exception{
		Map<String,String> map = (Map<String, String>) ehCache.getCacheElement("allParameterList_allZh_and_en");
		if(map == null || map.size() == 0){
//			System.out.println("getAllParameterList_allZh_and_en cache is null");
			map = new HashMap<String,String>();
			Class<?> pojoClass = Class.forName("DataAn.galaxyManager.option.J9Series_Star_FlywheelParameterConfig");
			Object obj = pojoClass.newInstance();
			Field[] fields = pojoClass.getDeclaredFields();
			for (Field field : fields) {
//				field.setAccessible(true);// 修改访问控制权限
				map.put(field.get(obj).toString().trim(), field.getName().trim());
			}
			ehCache.addToCache("allParameterList_allZh_and_en", map);
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getAllParameterList_allZh_and_enByOption(String type, List<String> params) throws Exception {
		Map<String,String> map = (Map<String, String>) ehCache.getCacheElement("allParameterList_allZh_and_enByOption");
		if(map == null || map.size() == 0){
			map = new HashMap<String,String>();
			Class<?> pojoClass = Class.forName("DataAn.galaxyManager.option.J9Series_Star_ParameterConfig");
			Object obj = pojoClass.newInstance();
			Field[] fields = pojoClass.getDeclaredFields();
			String key = "";
			if (params != null && params.size() > 0) {
				boolean flag = false;
				for (Field field : fields) {
//				field.setAccessible(true);// 修改访问控制权限
					key = field.get(obj).toString().trim();
					for (String param : params) {
						if((key.indexOf(type) != -1) && (key.indexOf(param) != -1)){
							flag = true;
							break;
						}
					}
					if(flag){
						map.put(key, field.getName().trim());
					}
					flag = false;
				}
			}else{
				for (Field field : fields) {
//					field.setAccessible(true);// 修改访问控制权限
						key = field.get(obj).toString().trim();
						if(key.indexOf(type) != -1){
							map.put(key, field.getName().trim());						
						}
					}
			}
			ehCache.addToCache("allParameterList_allZh_and_en", map);
		}
		return map;
	}
	
	private List<ConstraintDto> getFlyWheelOrTopParameterList(Map<String,String> map, List<String> dataTypes) {
		List<ConstraintDto> list = new ArrayList<ConstraintDto>();
		ConstraintDto c = null;
		List<ConstraintDto> children = null;
		ConstraintDto child = null;
		int count = 1;
		int parentId = 0;
//		Map<String,String> map = this.getAllParameterList_simplyZh_and_en();
		String sameFlyWheelData = "";
		for (String dataType : dataTypes) {
			c = new ConstraintDto();
			c.setParentId(0);
			c.setId(count);
			c.setName(dataType);
			c.setMin("");
			c.setMin("");
			c.setUnit("");
			c.setYname("");
			parentId = count;
			count ++;
			children = new ArrayList<ConstraintDto>();
			Set<String> flyWheelDatas = map.keySet();
			for (String flyWheelData : flyWheelDatas) {
				// 采集数据107:飞轮A转速(16107) == 采集数据107:飞轮a转速(16107) 大小写一样
				//sameFlyWheelData = flyWheelData.toLowerCase();
				if(flyWheelData.indexOf(dataType) != -1){
					child = new ConstraintDto();
					child.setId(count);
					child.setParentId(parentId);
					child.setName(flyWheelData); //设置中文
					child.setValue(map.get(flyWheelData)); //设置英文
					//child.setMax(Float.MAX_VALUE);
					//child.setMin(-Float.MAX_VALUE);
					child.setMax("9999");
					child.setMin("-9999");
					child.setUnit("");
					child.setYname("Y1");
					children.add(child);
					count ++;
				}
			}
			if(children != null && children.size() > 0){
				//c.setChildren(children);
				list.add(c);	
				list.addAll(children);
			}
		}
		return list;
	}

	@Override
	public void initJ9SeriesParameterData() {
		try {
			//初始化飞轮数据
			Set<String> flywheelParamNames = new HashSet<String>();
			Class<?> flywheelClass = Class.forName(J9Series_Star_FlywheelParameterConfig.class.getName());
			Object flywheelObj = flywheelClass.newInstance();
			Field[] flywheelFields = flywheelClass.getDeclaredFields();
			for (Field field : flywheelFields) {
				String paramName = field.get(flywheelObj).toString().trim();
				if(paramName != null && !paramName.equals("接收地方时")){
					flywheelParamNames.add(paramName);					
				}
			}
			//初始化陀螺数据
			Set<String> topParamNames = new HashSet<String>();
			Class<?> topClass = Class.forName(J9Series_Star_TopParameterConfig.class.getName());
			Object topObj = topClass.newInstance();
			Field[] topFields = topClass.getDeclaredFields();
			for (Field field : topFields) {
				String paramName = field.get(topObj).toString().trim();
				if(paramName != null && !paramName.equals("接收地方时")){
					topParamNames.add(paramName);					
				}
			}
			
			String series = SeriesType.J9_SERIES.getName();
			J9SeriesType[] types = J9SeriesType.values();
			for (J9SeriesType j9SeriesType : types) {
				String star = j9SeriesType.getValue();
				paramService.saveMany(series, star, J9Series_Star_ParameterType.FLYWHEEL.getValue(), flywheelParamNames);
				paramService.saveMany(series, star, J9Series_Star_ParameterType.TOP.getValue(), topParamNames);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
