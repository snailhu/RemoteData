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
	public boolean saveStar(TemplateParameterDto tmplparamDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean deleteTmplparam(long starId) {
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
