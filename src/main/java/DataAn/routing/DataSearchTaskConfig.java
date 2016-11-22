package DataAn.routing;

import java.io.Serializable;
import java.util.Date;

import DataAn.Analysis.dto.ParamAttributeDto;

public class DataSearchTaskConfig implements Serializable{

	private Date startDate ;
	
	private Date endDate;
	
	//private ParamAttributeDto properties[];
	private String property;
	private String maxvalue;
	private String minvalue;
	
	private Repo repo;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	public Repo getRepo() {
		return repo;
	}

	public void setRepo(Repo repo) {
		this.repo = repo;
	}

	/*public ParamAttributeDto[] getProperties() {
		return properties;
	}

	public void setProperties(ParamAttributeDto[] properties) {
		this.properties = properties;
	}*/

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getMaxvalue() {
		return maxvalue;
	}

	public void setMaxvalue(String maxvalue) {
		this.maxvalue = maxvalue;
	}

	public String getMinvalue() {
		return minvalue;
	}

	public void setMinvalue(String minvalue) {
		this.minvalue = minvalue;
	}

}
