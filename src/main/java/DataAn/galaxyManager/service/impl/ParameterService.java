package DataAn.galaxyManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import DataAn.common.dao.Pager;
import DataAn.galaxyManager.dao.IParameterDao;
import DataAn.galaxyManager.domain.Parameter;
import DataAn.galaxyManager.dto.ParameterDto;
import DataAn.galaxyManager.service.IParameterService;
import DataAn.sys.domain.Role;
import DataAn.sys.dto.RoleDto;

@Service
public class ParameterService implements IParameterService{

	private IParameterDao parameterDao;
	
	@Override
	public Pager<ParameterDto> getParameterList(int pageIndex, int pageSize) {
		if(pageIndex == 0){
			pageIndex = 1;
		}
		List<ParameterDto> paramDtoList = new ArrayList<ParameterDto>();
		Pager<Parameter> paramPager = parameterDao.selectByPager(pageIndex, pageSize);
		List<Parameter> paramList = paramPager.getDatas();
		if(paramList != null && paramList.size() > 0){
			for (Parameter param : paramList) {
				paramDtoList.add(this.pojoToDto(param));
			}
		}
		Pager<ParameterDto> pager = new Pager<ParameterDto>(pageIndex, pageSize, paramPager.getTotalCount(), paramDtoList);
		return pager;
	}

	private ParameterDto pojoToDto(Parameter param) {
		// TODO Auto-generated method stub
		return null;
	}

}
