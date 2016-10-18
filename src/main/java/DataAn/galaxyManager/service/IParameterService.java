package DataAn.galaxyManager.service;

import DataAn.common.dao.Pager;
import DataAn.galaxyManager.dto.ParameterDto;

public interface IParameterService {

	Pager<ParameterDto> getParameterList(int pageIndex, int pageSize);

}
