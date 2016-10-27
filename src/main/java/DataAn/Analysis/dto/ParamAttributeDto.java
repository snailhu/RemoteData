package DataAn.Analysis.dto;

import java.io.Serializable;

public class ParamAttributeDto implements Serializable {
	
	private String name;
	
	private String value;
	
	private String y;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}
	
	
}
