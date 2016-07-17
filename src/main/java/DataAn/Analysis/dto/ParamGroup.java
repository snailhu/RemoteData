package DataAn.Analysis.dto;

import java.util.List;

public class ParamGroup {
	private int j;
	
	private String Ycount;
	
	private String Y1name;
	
	private String Y2name;
	
	private List<SingleParamDto> sParamDtos;
	
	
	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public String getY2name() {
		return Y2name;
	}

	public void setY2name(String y2name) {
		Y2name = y2name;
	}

	public String getYcount() {
		return Ycount;
	}

	public void setYcount(String ycount) {
		Ycount = ycount;
	}

	public String getY1name() {
		return Y1name;
	}

	public void setY1name(String y1name) {
		Y1name = y1name;
	}

	public List<SingleParamDto> getsParamDtos() {
		return sParamDtos;
	}

	public void setsParamDtos(List<SingleParamDto> sParamDtos) {
		this.sParamDtos = sParamDtos;
	}
	
	
}
