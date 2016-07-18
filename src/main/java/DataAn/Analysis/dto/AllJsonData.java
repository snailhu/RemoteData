package DataAn.Analysis.dto;

import java.io.Serializable;
import java.util.List;

public class AllJsonData  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ParamGroup> alldata;

	public List<ParamGroup> getAlldata() {
		return alldata;
	}

	public void setAlldata(List<ParamGroup> alldata) {
		this.alldata = alldata;
	}

	
	
	
}
