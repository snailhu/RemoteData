package DataAn.fileSystem.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import DataAn.Analysis.dto.ConstraintDto;
import DataAn.fileSystem.option.J9Series_Star_ParameterGroupType;
import DataAn.fileSystem.service.IJ9Series_Star_Service;

@Service
public class J9Series_Star_ServiceImpl implements IJ9Series_Star_Service{

	@Override
	public List<ConstraintDto> getFlyWheelParameterList() throws Exception {
		List<ConstraintDto> list = new ArrayList<ConstraintDto>();
		ConstraintDto c = null;
		List<ConstraintDto> children = null;
		ConstraintDto child = null;
		int count = 1;
		int parentId = 0;
		List<J9Series_Star_ParameterGroupType> flyWheelDataTypes = J9Series_Star_ParameterGroupType.get_FLYWHEE_Type();
		Map<String,String> map = getAllParameterList();
		for (J9Series_Star_ParameterGroupType flyWheelDataType : flyWheelDataTypes) {
			c = new ConstraintDto();
			c.setId(count);
			c.setName(flyWheelDataType.getName());
			parentId = count;
			count ++;
			children = new ArrayList<ConstraintDto>();
			Set<String> flyWheelDatas = map.keySet();
			for (String flyWheelData : flyWheelDatas) {
				if(flyWheelData.indexOf(flyWheelDataType.getName()) != -1){
					child = new ConstraintDto();
					child.setId(count);
					child.setParentId(parentId);
					child.setName(flyWheelData);
					child.setValue(map.get(flyWheelData));
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

	private Map<String,String> getAllParameterList() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		//获取整个User类
		Class<?> pojoClass = Class.forName("DataAn.fileSystem.option.J9Series_Star_ParameterConfig");
		Object obj = pojoClass.newInstance();
		Field[] fields = pojoClass.getDeclaredFields();
		for (Field field : fields) {
//			field.setAccessible(true);// 修改访问控制权限
			map.put(field.get(obj).toString().split(":")[1], field.getName());
		}
		return map;
	}
}
