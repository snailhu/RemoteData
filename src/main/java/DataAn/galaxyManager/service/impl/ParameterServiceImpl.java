package DataAn.galaxyManager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DataAn.common.dao.Pager;
import DataAn.galaxyManager.dao.IParameterDao;
import DataAn.galaxyManager.domain.Parameter;
import DataAn.galaxyManager.dto.ParameterDto;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.service.IParameterService;


@Service("parameterService")
public class ParameterServiceImpl implements IParameterService{

	@Resource
	private IParameterDao parameterDao;
	
//	private ConcurrentHashMap<String,String> parameterList_allZh_and_en = new ConcurrentHashMap<String,String>();
	
//	private ConcurrentHashMap<String,String> parameterList_en_and_allZh = new ConcurrentHashMap<String,String>();
	
	@Override
	@Transactional
	public Parameter saveOne(String series, String star, String paramType, String param_zh) {
		Parameter param = new Parameter();
		if(param_zh.equals("接收地方时")){ // || param_zh.equals("时间")
			param.setSeries(series);
			param.setStar(star);
//			if(StringUtils.isNotBlank(paramType)){
//				param.setParameterType(paramType);					
//			}
			param.setFullName(param_zh);
			param.setCode("datetime");
		}else{
			String item = param_zh.trim();
			String num = item.substring(item.indexOf("(") + 1, item.indexOf(")"));
			String code = "sequence_" + num;
			param.setSeries(series);
			param.setStar(star);
			if(StringUtils.isNotBlank(paramType)){
				param.setParameterType(paramType);					
			}else{//TODO 通过数据库判断参数类型
				param.setParameterType(J9Series_Star_ParameterType.getJ9SeriesStarParameterTypeByName(param_zh).getValue());	
			}
			param.setFullName(param_zh);
			param.setSimplyName(param_zh.split(":")[1]);
			param.setCode(code);
		}
		param = parameterDao.add(param);
		return param;
	}

	@Override
	@Transactional
	public void saveMany(String series, String star, String paramType, String param_zhs) {
		try {
			Set<String> paramSet = new HashSet<String>();
			String[] items = param_zhs.split(",");
			for (String item : items) {
				if(!item.equals("接收地方时")){
					paramSet.add(item);
				}
			}
			for (String param_zh : paramSet) {
				if(this.isExistParameter(0, series, star, param_zh.trim())){
					this.saveOne(series, star, paramType, param_zh);					
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void deleteParamter(String paramIds) {
		String[] ids = paramIds.split(",");
		for (String strId : ids) {
			parameterDao.delete(Long.parseLong(strId));
		}
	}

	@Override
	@Transactional
	public void updateParamter(long paramId, String param_zh) {
		Parameter param = parameterDao.get(paramId);
		if(StringUtils.isNotBlank(param_zh)){
			if(param_zh.equals("接收地方时")){ // || param_zh.equals("时间")
				param.setFullName(param_zh);
				param.setCode("datetime");
			}else{
				param.setFullName(param_zh);
				param.setSimplyName(param_zh.split(":")[1]);
			}			
		}
		parameterDao.update(param);
	}


	@Override
	public boolean isExistParameter(long paramId, String series, String star,String param_zh) {
		Parameter param = parameterDao.selectBySeriesAndStarAndFullName(series,star, param_zh);
		if(param != null){
			if(paramId == 0){
				return true;				
			}else{
				if(param.getId() != paramId){
					return true;
				}
			}
		}
		return false;
	}

	
	@Override
	public List<ParameterDto> getParameterList(String series, String star, String paramType) {
		List<ParameterDto> paramDtoList = new ArrayList<ParameterDto>();
		List<Parameter> paramList = parameterDao.selectBySeriesAndStarAndParameterType(series,star,paramType);
		if(paramList != null && paramList.size() > 0){
			for (Parameter parameter : paramList) {
				paramDtoList.add(this.pojoToDto(parameter));
			}
		}
		return paramDtoList;
	}
	
	@Override
	public Pager<ParameterDto> getParameterListByPager(String series, String star, int pageIndex, int pageSize) {
		if(pageIndex == 0){
			pageIndex = 1;
		}
		List<ParameterDto> paramDtoList = new ArrayList<ParameterDto>();
		Pager<Parameter> paramPager = parameterDao.selectByPager(series,star, pageIndex, pageSize);
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
		paramDto.setFullName(param.getFullName());
		paramDto.setSimplyName(param.getSimplyName());
		paramDto.setSeries(param.getSeries());
		paramDto.setStar(param.getStar());
		paramDto.setParameterType(param.getParameterType());
		return paramDto;
	}

	@Override
	public String getParameter_en_by_allZh(String series, String star,
			 String paramType, String param_zh) {
//		//先从Map集合里面查找
//		String param_en = parameterList_allZh_and_en.get(param_zh);
//		if(param_en == null){
//			//Map集合里面没有再从数据库中查找
//			Parameter param = parameterDao.selectBySeriesAndFullName(series, param_zh);
//			if(param == null){
//				//数据库中没有此集合
//				param = this.saveOne(series, star,paramType, param_zh);
//			}
//			param_en = param.getCode();
//			parameterList_allZh_and_en.put(param_zh, param_en);
//		}
//		return param_en;
		
		Parameter param = parameterDao.selectBySeriesAndFullName(series, param_zh);
		if(param == null){
			//数据库中没有此集合
			param = this.saveOne(series, star,paramType, param_zh);
		}
		return param.getCode();
	}

	public String getParameter_en_by_simpleZh(String series, String star,
			String paramType, String param_zh){
		Parameter param = parameterDao.selectBySeriesAndStarAndSimplyName(series,star, param_zh);
		if(param != null){
			return param.getCode();			
		}
		return null;
	}

	@Override
	public String getParameter_allZh_by_en(String series, String star,
			 String paramType, String param_en) {
		//先从Map集合里面查找
//		String param_zh = parameterList_en_and_allZh.get(param_en);
//		if(param_zh == null || param_zh.equals("")){
//			//Map集合里面没有再从数据库中查找
//			Parameter param = parameterDao.selectBySeriesAndCode(series, param_en);
//			if(param != null){
//				parameterList_en_and_allZh.put(param.getCode(), param.getFullName());
//				param_zh = param.getFullName();
//			}
//		}
//		return param_zh;
		
		Parameter param = parameterDao.selectBySeriesAndStarAndCode(series,star, param_en);
		if(param != null){
			return param.getFullName();
		}
		return null;
	}

	@Override
	public String getParameter_simpleZh_by_en(String series, String star, String paramType, String param_en) {
		Parameter param = parameterDao.selectBySeriesAndStarAndCode(series,star, param_en);
		if(param != null){
			return param.getSimplyName();
		}
		return null;
	}
	@Override
	public String getParameter_dataType_by_en(String series, String star,
			String paramType, String param_en) {
		String param_zh = this.getParameter_allZh_by_en(series, star, paramType, param_en);
		if(StringUtils.isNotBlank(param_zh)){
			if(!param_zh.equals("时间") && !param_zh.equals("接收地方时")){
				String param = param_zh.split(":")[1];//TODO 根据
				List<String> typeList = J9Series_Star_ParameterType.getFlywheelTypeOnDataType();
				for (String type : typeList) {
					if(param.indexOf(type) > -1){
						return type;
					}
				}
			}
		}
		return null;
	}

	@Override
	public String getParameter_paramTypeName_by_en(String series, String star,
			String paramType, String param_en) {
		String param_zh = this.getParameter_allZh_by_en(series, star, paramType, param_en);
		if(StringUtils.isNotBlank(param_zh)){
			if(!param_zh.equals("时间") && !param_zh.equals("接收地方时")){
				String param = param_zh.split(":")[1];//TODO 根据
				List<String> typeList = J9Series_Star_ParameterType.getFlywheelTypeOnParamTypeName();
				for (String type : typeList) {
					if(param.indexOf(type) > -1){
						return type;
					}
				}
			}
		}
		return null;
	}


}
