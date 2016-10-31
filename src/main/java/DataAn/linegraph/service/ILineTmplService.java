package DataAn.linegraph.service;

import java.util.List;

import DataAn.linegraph.dto.LineGraphTemplateDto;
import DataAn.linegraph.dto.TemplateParameterDto;

public interface ILineTmplService {
	public List<LineGraphTemplateDto> getTemplateList();
	public List<LineGraphTemplateDto> getTemplateListByUser(long userid);
	public void SaveTemplate(LineGraphTemplateDto templateDto);
	void deleteTemplate(String templateids);
}
