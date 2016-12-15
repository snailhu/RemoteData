package DataAn.linegraph.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
* Title: 曲线图参数模板参数类
* @Description: 一个曲线图的一个参数实体
* @author  hanz
* @date 2016年8月3日
*/
@Entity
@Table(name = "t_templateparameter")
public class TemplateParameter {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id",unique = true, nullable = false )
	private long id;
	
	@Column(name="name", nullable = false, length =64)
	private String name;
	
	@Column(name="max", nullable = true,length =64)
	private String max;
	
	@Column(name="min", nullable = true,length =64)
	private String min;
	
	@Column(name="unit", nullable = true ,length =64)
	private String unit;
	
	@Column(name="yname", nullable = false ,length =64)
	private String yname;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="templateid",nullable = false)
	private LineGraphTemplate linegraphtemplale;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getYname() {
		return yname;
	}
	public void setYname(String yname) {
		this.yname = yname;
	}
	public LineGraphTemplate getLinegraphtemplale() {
		return linegraphtemplale;
	}
	public void setLinegraphtemplale(LineGraphTemplate linegraphtemplale) {
		this.linegraphtemplale = linegraphtemplale;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
}
