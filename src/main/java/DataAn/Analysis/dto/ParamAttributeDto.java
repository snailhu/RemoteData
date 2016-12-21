
package DataAn.Analysis.dto;

import java.io.Serializable;

public class ParamAttributeDto implements Serializable {
	
	private String name;
	
	private String value;
	private String max;
	private String min;
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

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}
	
	
}
