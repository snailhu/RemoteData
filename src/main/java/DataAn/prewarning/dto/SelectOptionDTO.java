package DataAn.prewarning.dto;

import java.util.List;

import DataAn.galaxyManager.dto.ParameterDto;
import DataAn.galaxyManager.dto.StarDto;

public class SelectOptionDTO {
	private List<StarDto> stars;
	private List<ParameterDto> paramaters;

	public List<StarDto> getStars() {
		return stars;
	}

	public void setStars(List<StarDto> stars) {
		this.stars = stars;
	}

	public List<ParameterDto> getParamaters() {
		return paramaters;
	}

	public void setParamaters(List<ParameterDto> paramaters) {
		this.paramaters = paramaters;
	}

	@Override
	public String toString() {
		return "SelectOptionDTO [stars=" + stars + ", paramaters=" + paramaters + "]";
	}

}
