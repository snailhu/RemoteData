package DataAn.galaxyManager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DataAn.common.dao.Pager;
import DataAn.galaxyManager.dao.IParameterDao;
import DataAn.galaxyManager.domain.Parameter;
import DataAn.galaxyManager.dto.ParameterDto;
import DataAn.galaxyManager.service.IParameterService;


@Service
public class ParameterServiceImpl implements IParameterService{

	@Resource
	private IParameterDao parameterDao;
	
	private ConcurrentHashMap<String,String> parameterList_allZh_and_en = new ConcurrentHashMap<String,String>();
	
	private ConcurrentHashMap<String,String> parameterList_en_and_allZh = new ConcurrentHashMap<String,String>();
	
	@Override
	@Transactional
	public Parameter save(String series, String star, String param_zh) {
		Parameter param = new Parameter();
		if(param_zh.equals("接收地方时")){ // || param_zh.equals("时间")
			param.setSeries(series);
			param.setStar(star);
			param.setName(param_zh);
			param.setCode("datetime");
		}else{
			String item = param_zh.trim();
			String num = item.substring(item.indexOf("(") + 1, item.indexOf(")"));
			String code = "sequence_" + num;
			param.setSeries(series);
			param.setStar(star);
			param.setName(param_zh);
			param.setCode(code);
		}
//		parameterList_allZh_and_en.put(param.getName(), param.getCode());
//		parameterList_en_and_allZh.put(param.getCode(), param.getName());
		return parameterDao.add(param);
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
		if(paramPager != null){
			List<Parameter> paramList = paramPager.getDatas();
			if(paramList != null && paramList.size() > 0){
				for (Parameter param : paramList) {
					paramDtoList.add(this.pojoToDto(param));
				}
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

	@Override
	public String getParameter_en_by_allZh(String series, String star,
			String param_zh) {
		//先从Map集合里面查找
		String param_en = parameterList_allZh_and_en.get(param_zh);
		if(param_en == null){
			//Map集合里面没有再从数据库中查找
			Parameter param = parameterDao.selectBySeriesAndName(series, param_zh);
			if(param == null){
				//数据库中没有此集合
				param = this.save(series, star, param_zh);
			}
			param_en = param.getCode();
			parameterList_allZh_and_en.put(param_zh, param_en);
		}
		return param_en;
	}

	@Override
	public String getParameter_allZh_by_en(String series, String star,
			String param_en) {
		//先从Map集合里面查找
		String param_zh = parameterList_en_and_allZh.get(param_en);
		if(param_zh == null || param_zh.equals("")){
			//Map集合里面没有再从数据库中查找
			Parameter param = parameterDao.selectBySeriesAndCode(series, param_en);
			if(param != null){
				parameterList_en_and_allZh.put(param.getCode(), param.getName());
				param_zh = param.getName();
			}
		}
		return param_zh;
	}


}
