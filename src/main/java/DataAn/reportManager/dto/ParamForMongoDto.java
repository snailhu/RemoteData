package DataAn.reportManager.dto;

import java.util.Map;

public class ParamForMongoDto {


	private String keyName;
	
	private Map<String,String> parMap;

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public Map<String, String> getParMap() {
		return parMap;
	}

	public void setParMap(Map<String, String> parMap) {
		this.parMap = parMap;
	}
}
