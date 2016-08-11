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
import DataAn.linegraph.service.ILineTmplService;

@Service
public class LineTmplServiceImpl implements ILineTmplService {

	@Resource
	private ILineTmplDao linetmpdao;
	
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
				templateList.add(dto);
			}
		}		
		return templateList;
	}

}
