package DataAn.fileSystem.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import DataAn.Analysis.dto.ConstraintDto;
import DataAn.fileSystem.option.FlyWheel;
import DataAn.fileSystem.service.IFlyWheelService;

@Service
public class FlyWheelServiceImpl implements IFlyWheelService{

	@Override
	public List<ConstraintDto> getFlyWheelParameterList() throws Exception {
		List<ConstraintDto> list = new ArrayList<ConstraintDto>();
		//获取整个User类
		Class<?> pojoClass = Class.forName("DataAn.fileSystem.option.FlyWheel");
		Object obj = pojoClass.newInstance();
		Field[] fields = pojoClass.getDeclaredFields();
		ConstraintDto c = null;
		int count = 1;
		for (Field field : fields) {
			c = new ConstraintDto();
//			field.setAccessible(true);// 修改访问控制权限
			c.setId(count);
			c.setName(field.get(obj).toString());
			list.add(c);
			count ++;
		}
		return list;
	}

}
