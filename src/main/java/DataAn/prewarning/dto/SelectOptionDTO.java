package DataAn.prewarning.dto;

import java.util.List;

import DataAn.Analysis.dto.ConstraintDto;
import DataAn.galaxyManager.dto.StarDto;

public class SelectOptionDTO {
	private List<StarDto> stars;
	private List<ConstraintDto> paramaters;

	public List<StarDto> getStars() {
		return stars;
	}

	public void setStars(List<StarDto> stars) {
		this.stars = stars;
	}

	public List<ConstraintDto> getParamaters() {
		return paramaters;
	}

	public void setParamaters(List<ConstraintDto> paramaters) {
		this.paramaters = paramaters;
	}

}
