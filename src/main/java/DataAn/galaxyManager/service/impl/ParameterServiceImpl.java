package DataAn.galaxyManager.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import DataAn.common.dao.Pager;
import DataAn.galaxyManager.dao.IParameterDao;
import DataAn.galaxyManager.domain.Parameter;
import DataAn.galaxyManager.dto.ParameterDto;
import DataAn.galaxyManager.service.IParameterService;


@Service
public class ParameterServiceImpl implements IParameterService{

	private IParameterDao parameterDao;
	
	@Override
	@Transactional
	public void save(String series, String star, String param_zh) {
		if(param_zh.equals("接收地方时") || param_zh.equals("时间")){
			Parameter param = new Parameter();
			param.setSeries(series);
			param.setStar(star);
			param.setName(param_zh);
			param.setCode("datetime");
			parameterDao.add(param);
		}else{
			String item = param_zh.trim();
			String num = item.substring(item.indexOf("(") + 1, item.indexOf(")"));
			String code = "sequence_" + num;
			Parameter param = new Parameter();
			param.setSeries(series);
			param.setStar(star);
			param.setName(param_zh);
			param.setCode(code);
			parameterDao.add(param);
		}
	}
	
	@Override
	public List<Parameter> getAllParameterList() {
		// TODO Auto-generated method stub
		return null;
	}

	
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
		ParameterDto paramDto = new ParameterDto();
		paramDto.setId(param.getId());
		paramDto.setCode(param.getCode());
		paramDto.setName(param.getName());
		paramDto.setSeries(param.getSeries());
		paramDto.setStar(param.getStar());
		return paramDto;
	}


}
