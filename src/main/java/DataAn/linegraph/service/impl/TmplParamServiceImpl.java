package DataAn.linegraph.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DataAn.linegraph.dao.ILineTmplDao;
import DataAn.linegraph.dao.ITmplParamDao;
import DataAn.linegraph.domain.LineGraphTemplate;
import DataAn.linegraph.domain.TemplateParameter;
import DataAn.linegraph.dto.LineGraphTemplateDto;
import DataAn.linegraph.dto.TemplateParameterDto;
import DataAn.linegraph.service.ITmplParamService;

@Service
public class TmplParamServiceImpl implements ITmplParamService {
	
	@Resource
	private ITmplParamDao tmplparamDao;
	@Resource
	private ILineTmplDao linetmpdao;
	
	@Override
	@Transactional
	public boolean saveTmplparam(TemplateParameterDto tmplparamDto) {
		LineGraphTemplate template = linetmpdao.get(tmplparamDto.getTemplateid());
		if(null==template){
			return false;
		}
		TemplateParameter param =new TemplateParameter();
		param.setMax(tmplparamDto.getMax());
		param.setMin(tmplparamDto.getMin());
		param.setUnit(tmplparamDto.getUnit());
		param.setName(tmplparamDto.getName());
		param.setYname(tmplparamDto.getYname());
		param.setLinegraphtemplale(template);
		tmplparamDao.add(param);
		return false;
	}
	@Override
	@Transactional
	public boolean saveTemplateParam(LineGraphTemplateDto templateDto,List<TemplateParameterDto> params)
	{
		LineGraphTemplate template =new LineGraphTemplate();
		template.setName(templateDto.getName());
		template.setDescription(templateDto.getDescription());
		template.setOwnerid(templateDto.getOwnerid());
		linetmpdao.add(template);
		for(TemplateParameterDto paramdto:params)
		{
			TemplateParameter param=new TemplateParameter();
			param.setName(paramdto.getName());
			param.setMax(paramdto.getMax());
			param.setMin(paramdto.getMin());
			param.setUnit(paramdto.getUnit());
			param.setYname(paramdto.getYname());
			param.setLinegraphtemplale(template);
			tmplparamDao.add(param);
		}
		return true;
	}

	@Override
	@Transactional
	public boolean deleteTmplparam(long parameterid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean updateTmplparam(TemplateParameterDto tmplparamDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public List<TemplateParameterDto> getTmplparamDtoByTmplId(
			long LineGraphTemplateid) {
		List<TemplateParameterDto> paramList = new ArrayList<TemplateParameterDto>();
		List<TemplateParameter> list= tmplparamDao.findByParam("linegraphtemplale.id",LineGraphTemplateid );
		if(list != null && list.size() > 0){
			TemplateParameterDto dto = null;
			for (TemplateParameter tmplparam : list) {
				dto = new TemplateParameterDto();
				dto.setId(tmplparam.getId());
				dto.setName(tmplparam.getName());
				dto.setMax(tmplparam.getMax());
				dto.setMin(tmplparam.getMin());
				dto.setUnit(tmplparam.getUnit());
				dto.setYname(tmplparam.getYname());
				paramList.add(dto);
			}
		}
		return paramList;
	}
	
	@Override
	@Transactional
	public List<TemplateParameterDto> getAllTemplate() {
		List<TemplateParameterDto> paramList = new ArrayList<TemplateParameterDto>();
		List<LineGraphTemplate> templatelist = linetmpdao.findAll();
		List<TemplateParameter> list = new ArrayList<TemplateParameter>();
		if(templatelist != null && templatelist.size() > 0){
			TemplateParameterDto dto = null;
			TemplateParameterDto param = null;
			int count = 1;
			for (LineGraphTemplate template : templatelist) {
				dto = new TemplateParameterDto();
				//dto.setId(template.getId());
				dto.setRowid(count);
				dto.setParentid(0);
				dto.setTemplateid(template.getId());
				dto.setYname("添加到分组");
				count++;
				dto.setTemplateName(template.getName());
				paramList.add(dto);				
				list= tmplparamDao.findByParam("linegraphtemplale.id",template.getId());				
				//list = tmplparamDao.findByParam(propertyName, value)
				//list = tmplparamDao.findAll();
				if(list !=null && list.size()>0 ){
					for (TemplateParameter tmplparam : list) {
						param = new TemplateParameterDto();
						param.setId(tmplparam.getId());
						param.setRowid(count);
						param.setName(tmplparam.getName());
						param.setParentid(dto.getRowid());
						//param.setTemplateid(tmplparam.getLinegraphtemplale().getId());
						//param.setTemplateName(template.getName());
						param.setMax(tmplparam.getMax());
						param.setMin(tmplparam.getMin());
						param.setUnit(tmplparam.getUnit());
						param.setYname(tmplparam.getYname());
						count++;
						paramList.add(param);
					}
				}
			}
		}
		return paramList;
	}
	
	@Override
	@Transactional
	public List<TemplateParameterDto> getTemplateByUser(long Userid) {
		List<TemplateParameterDto> paramList = new ArrayList<TemplateParameterDto>();
		//List<LineGraphTemplate> templatelist = linetmpdao.findAll();
		List<LineGraphTemplate> templatelist = linetmpdao.findByParam("ownerid", Userid);
		List<TemplateParameter> list = new ArrayList<TemplateParameter>();
		if(templatelist != null && templatelist.size() > 0){
			TemplateParameterDto dto = null;
			TemplateParameterDto param = null;
			int count = 1;
			for (LineGraphTemplate template : templatelist) {
				dto = new TemplateParameterDto();
				//dto.setId(template.getId());
				dto.setRowid(count);
				dto.setParentid(0);
				dto.setTemplateid(template.getId());
				dto.setYname("添加到分组");
				count++;
				dto.setTemplateName(template.getName());
				paramList.add(dto);				
				list= tmplparamDao.findByParam("linegraphtemplale.id",template.getId());				
				//list = tmplparamDao.findByParam(propertyName, value)
				//list = tmplparamDao.findAll();
				if(list !=null && list.size()>0 ){
					for (TemplateParameter tmplparam : list) {
						param = new TemplateParameterDto();
						param.setId(tmplparam.getId());
						param.setRowid(count);
						param.setName(tmplparam.getName());
						param.setParentid(dto.getRowid());
						//param.setTemplateid(tmplparam.getLinegraphtemplale().getId());
						//param.setTemplateName(template.getName());
						param.setMax(tmplparam.getMax());
						param.setMin(tmplparam.getMin());
						param.setUnit(tmplparam.getUnit());
						param.setYname(tmplparam.getYname());
						count++;
						paramList.add(param);
					}
				}
			}
		}
		return paramList;
	}
		
	@Override
	public List<TemplateParameterDto> getAllTemlparamDto() {
		List<TemplateParameterDto> paramList = new ArrayList<TemplateParameterDto>();
		List<TemplateParameter> list= tmplparamDao.findAll();
		if(list != null && list.size() > 0){
			TemplateParameterDto dto = null;
			for (TemplateParameter tmplparam : list) {
				dto = new TemplateParameterDto();
				dto.setId(tmplparam.getId());
				dto.setName(tmplparam.getName());
				dto.setMax(tmplparam.getMax());
				dto.setMin(tmplparam.getMin());
				dto.setUnit(tmplparam.getUnit());
				dto.setYname(tmplparam.getYname());
				dto.setTemplateName(tmplparam.getLinegraphtemplale(). getName());
				dto.setTemplateid(tmplparam.getLinegraphtemplale().getId());
				paramList.add(dto);
			}
		}		
		return paramList;
	}
	
	@Override
	@Transactional
	public TemplateParameterDto getTmplparamDto(long parameterid) {
		return null;
	}


}
