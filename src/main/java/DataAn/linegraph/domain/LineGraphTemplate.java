package DataAn.linegraph.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import DataAn.linegraph.domain.TemplateParameter;

/**
* Title: 曲线图参数模板类
* @Description: 一个曲线图参数模板实体
* @author  hanz
* @date 2016年8月3日
*/
@Entity
@Table(name = "t_linegraphtemplate")
public class LineGraphTemplate {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@Column(name = "name", nullable = false, length = 64)
	private String name;
	
	@Column(name = "ownerid", nullable = true)
	private Long ownerid;
	
	@Column(name = "description", nullable = true, length = 512)
	private String description;
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="linegraphtemplale") 
	private Set<TemplateParameter> Parameters;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<TemplateParameter> getParameters() {
		return Parameters;
	}
	public void setParameters(Set<TemplateParameter> parameters) {
		Parameters = parameters;
	}
	public Long getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(Long ownerid) {
		this.ownerid = ownerid;
	}
}
