package DataAn.Analysis.dto;

/**
* Title: ConstraintDto
* @Description: 约束条件描述
* @author  Shewp
* @date 2016年7月15日
*/
public class ConstraintDto {

	private int id;
	private String name;
	private float max;
	private float min;
	
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
	public float getMax() {
		return max;
	}
	public void setMax(float max) {
		this.max = max;
	}
	public float getMin() {
		return min;
	}
	public void setMin(float min) {
		this.min = min;
	}
	@Override
	public String toString() {
		return "ConstraintDto [id=" + id + ", name=" + name + ", max=" + max
				+ ", min=" + min + "]";
	}
	
	
}
