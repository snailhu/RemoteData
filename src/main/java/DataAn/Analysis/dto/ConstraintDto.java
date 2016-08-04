package DataAn.Analysis.dto;

import java.util.List;

/**
* Title: ConstraintDto
* @Description: 约束条件描述
* @author  Shewp
* @date 2016年7月15日
*/
public class ConstraintDto {

	private int id;
	private int parentId;
	private String name; //中文键
	private String value; //英文值
	private float max;
	private float min;
	private List<ConstraintDto> children;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
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
	public List<ConstraintDto> getChildren() {
		return children;
	}
	public void setChildren(List<ConstraintDto> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "ConstraintDto [id=" + id + ", parentId=" + parentId + ", name="
				+ name + ", value=" + value + ", max=" + max + ", min=" + min
				+ ", children=" + children + "]";
	}
	
	
	
	
	
	
}
