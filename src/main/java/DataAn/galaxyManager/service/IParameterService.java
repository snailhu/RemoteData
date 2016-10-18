package DataAn.galaxyManager.service;

import java.util.List;

import DataAn.common.dao.Pager;
import DataAn.galaxyManager.domain.Parameter;
import DataAn.galaxyManager.dto.ParameterDto;

public interface IParameterService {

	public void save(String series, String star, String param_zh);
	
	public List<Parameter> getAllParameterList();
	
	public Pager<ParameterDto> getParameterList(int pageIndex, int pageSize);

}
