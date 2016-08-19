package DataAn.linegraph.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DataAn.linegraph.dao.ILineTmplDao;
import DataAn.linegraph.dao.ITmplParamDao;
import DataAn.linegraph.domain.LineGraphTemplate;
import DataAn.linegraph.dto.LineGraphTemplateDto;
import DataAn.linegraph.dto.TemplateParameterDto;
import DataAn.linegraph.service.ILineTmplService;

@Service
public class LineTmplServiceImpl implements ILineTmplService {

	@Resource
	private ILineTmplDao linetmpdao;
	@Resource
	private ITmplParamDao tmplparamDao;

	
	@Override
	@Transactional
	public List<LineGraphTemplateDto> getTemplateList() {
		List<LineGraphTemplateDto> templateList = new ArrayList<LineGraphTemplateDto>();
		List<LineGraphTemplate> list= linetmpdao.findAll();
		if(list != null && list.size() > 0){
			LineGraphTemplateDto dto = null;
			for (LineGraphTemplate tmpl : list) {
				dto = new LineGraphTemplateDto();
				dto.setName(tmpl.getName());				
				dto.setId(tmpl.getId());
				dto.setDescription(tmpl.getDescription());
				templateList.add(dto);
			}
		}		
		return templateList;
	}

	@Override
	@Transactional
	public void SaveTemplate(LineGraphTemplateDto templateDto) {
		LineGraphTemplate template =new LineGraphTemplate();
		template.setName(templateDto.getName());
		template.setDescription(templateDto.getDescription());	
		linetmpdao.add(template);	
	}
	
	@Override
	@Transactional
	public void deleteTemplate(String templateids) {
		String[] ids = templateids.split(",");
		for (String strId : ids) {
			long templateId = Long.parseLong(strId);
			linetmpdao.delete(templateId);
		}
		//System.out.println("服务层删除模板");
		//long templateIds=Long.parseLong(templateids);
		//System.out.println(templateIds);
		//linetmpdao.delete(templateIds);
		//System.out.println("删除模板");
	}

}
