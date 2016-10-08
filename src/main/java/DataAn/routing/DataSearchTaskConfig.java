package DataAn.routing;

import java.io.Serializable;
import java.util.Date;

public class DataSearchTaskConfig implements Serializable{

	private Date startDate ;
	
	private Date endDate;
	
	private String property;
	
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

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Repo getRepo() {
		return repo;
	}

	public void setRepo(Repo repo) {
		this.repo = repo;
	}
}
