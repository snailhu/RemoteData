package DataAn.Analysis.dto;

import java.io.Serializable;
import java.util.List;

public class ParamListDto implements Serializable{
	
	private int id;
	
	private List<String > paramName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getParamName() {
		return paramName;
	}

	public void setParamName(List<String> paramName) {
		this.paramName = paramName;
	}
	
	
}
