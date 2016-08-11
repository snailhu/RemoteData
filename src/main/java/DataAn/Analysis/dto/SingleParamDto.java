package DataAn.Analysis.dto;


public class SingleParamDto {
	private int id;
	
	private String name;//中文键
	
	private String value;//英文值
	
	private int max;
	
	private int min;
	
	private String yname;
	
	private String startDate;
	
	private String endDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public String getYname() {
		return yname;
	}

	public void setYname(String yname) {
		this.yname = yname;
	}

			
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "SingleParamDto [id=" + id + ", name=" + name + ", value="
				+ value + ", max=" + max + ", min=" + min + ", yname=" + yname
				+ "]";
	}

	

	
}		
