package DataAn.prewarning.dto;

import java.util.List;

import DataAn.Analysis.dto.ConstraintDto;

public class SelectOptionDTO {
	private List<ConstraintDto> paramaters;

	public List<ConstraintDto> getParamaters() {
		return paramaters;
	}

	public void setParamaters(List<ConstraintDto> paramaters) {
		this.paramaters = paramaters;
	}

}
