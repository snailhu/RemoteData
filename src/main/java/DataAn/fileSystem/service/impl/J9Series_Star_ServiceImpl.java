package DataAn.fileSystem.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import DataAn.Analysis.dto.ConstraintDto;
import DataAn.Util.EhCache;
import DataAn.common.utils.DateUtil;
import DataAn.fileSystem.dao.IDateParametersDao;
import DataAn.fileSystem.domain.DateParameters;
import DataAn.fileSystem.option.J9Series_Star_ParameterGroupType;
import DataAn.fileSystem.service.IJ9Series_Star_Service;
import DataAn.mongo.db.MongodbUtil;

@Service
public class J9Series_Star_ServiceImpl implements IJ9Series_Star_Service{

	private EhCache ehCache = new EhCache("j9seriesConfig");
	@Resource
	private IDateParametersDao parametersDao;
	
	@Override
	public List<ConstraintDto> getAllParameterListFromBeginDateToEndDate(
			String beginDate, String endDate) throws Exception {
//		MongodbUtil mg = MongodbUtil.getInstance();
//		Set<String> exclueSet = new HashSet<String>();
//		exclueSet.add("_id");
//		exclueSet.add("year");
//		exclueSet.add("year_month");
//		exclueSet.add("year_month_day");
//		exclueSet.add("datetime");
		beginDate = DateUtil.formatString(beginDate, "dd/MM/yyyy", "yyyy-MM-dd");
		endDate = DateUtil.formatString(endDate, "dd/MM/yyyy", "yyyy-MM-dd");
		List<DateParameters> dpList = parametersDao.selectByYear_month_day(beginDate, endDate);
		if(dpList != null && dpList.size() > 0){
//			System.out.println("dblist size: " + dpList.size());
			Map<String,Integer> tempMap = new HashMap<String,Integer>();
			Integer count = null;
//			Set<String> inclueSet = new HashSet<String>();
			for (DateParameters dp : dpList) {
				String[] items = dp.getParameters().split(",");
//				System.out.println("params size: " + items.length);
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
				if(tempMap.get(parameter) == dpList.size()){
					simplyZh_and_enMap.put(parameter.split(":")[1], map.get(parameter));					
				}else{
					simplyZh_and_enMap.put(parameter.split(":")[1] + "-时间不连续", map.get(parameter));
				}
			}
			List<String> flyWheelDataTypes = J9Series_Star_ParameterGroupType.getFlywheelTypeOnParams();
			return this.getFlyWheelOrTopParameterList(simplyZh_and_enMap,flyWheelDataTypes);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConstraintDto> getFlyWheelParameterList() throws Exception {
		List<ConstraintDto> list = (List<ConstraintDto>) ehCache.getCacheElement("flyWheelParameterList");
		if(list == null || list.size() == 0){
//			System.out.println("getFlyWheelParameterList cache is null");
			String type = J9Series_Star_ParameterGroupType.FLYWHEEL.getName();
			List<String> flyWheelDataTypes = J9Series_Star_ParameterGroupType.getFlywheelTypeOnName();
			Map<String,String> map = this.getAllParameterList_simplyZh_and_en(this.getAllParameterList_allZh_and_enByOption(type,flyWheelDataTypes));
			list = this.getFlyWheelOrTopParameterList(map,flyWheelDataTypes);	
			ehCache.addToCache("flyWheelParameterList", list);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConstraintDto> getTopParameterList() throws Exception {
		List<ConstraintDto> list = (List<ConstraintDto>) ehCache.getCacheElement("topParameterList");
		if(list == null || list.size() == 0){
//			System.out.println("getTopParameterList cache is null");
			String type = J9Series_Star_ParameterGroupType.TOP.getName();
			List<String> params = J9Series_Star_ParameterGroupType.getTopTypeOnName();
			Map<String,String> map = this.getAllParameterList_simplyZh_and_en(this.getAllParameterList_allZh_and_enByOption(type,params));
			List<String> topDataTypes = J9Series_Star_ParameterGroupType.getTopTypeOnName();
			list = this.getFlyWheelOrTopParameterList(map,topDataTypes);			
			ehCache.addToCache("topParameterList", list);
		}
		return list;
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
				if(!key.equals("时间")){
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
			if(!key.equals("时间")){
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
			Class<?> pojoClass = Class.forName("DataAn.fileSystem.option.J9Series_Star_ParameterConfig");
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
			Class<?> pojoClass = Class.forName("DataAn.fileSystem.option.J9Series_Star_ParameterConfig");
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
	
	private List<ConstraintDto> getFlyWheelOrTopParameterList(Map<String,String> map, List<String> dataTypes) throws Exception {
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
			c.setId(count);
			c.setName(dataType);
			parentId = count;
			count ++;
			children = new ArrayList<ConstraintDto>();
			Set<String> flyWheelDatas = map.keySet();
			for (String flyWheelData : flyWheelDatas) {
				// 采集数据107:飞轮A转速(16107) == 采集数据107:飞轮a转速(16107) 大小写一样
				sameFlyWheelData = flyWheelData.toLowerCase();
				if(sameFlyWheelData.indexOf(dataType) != -1){
					child = new ConstraintDto();
					child.setId(count);
					child.setParentId(parentId);
					child.setName(flyWheelData); //设置中文
					child.setValue(map.get(flyWheelData)); //设置英文
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




}
