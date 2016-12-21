package DataAn.Analysis.dto;

import java.io.Serializable;
import java.util.List;

/**
* Title: ConstraintDto
* @Description: 约束条件描述
* @author  Shewp
* @date 2016年7月15日
*/

public class ConstraintDto implements Serializable {

	private int id;
	private int parentId;
	private String name; //中文键
	private String value; //英文值
	private String max;
	private String min;
	private String unit; //参数单位
	private String yname; //hanz添加约束条件中当前参数Y轴名称
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
	public String getYname() {
		return yname;
	}
	public void setYname(String yname) {
		this.yname = yname;
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
				+ ", unit="+unit+",yname="+yname+", children=" + children + "]";
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
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
