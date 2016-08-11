package DataAn.linegraph.service;

import java.util.List;

import DataAn.linegraph.dto.LineGraphTemplateDto;
import DataAn.linegraph.dto.TemplateParameterDto;

public interface ILineTmplService {
	public List<LineGraphTemplateDto> getTemplateList();
	public void SaveTemplate(LineGraphTemplateDto templateDto);
}
