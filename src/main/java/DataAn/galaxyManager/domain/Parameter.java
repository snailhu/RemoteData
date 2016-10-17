package DataAn.galaxyManager.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* Title: Parameter
* @Description: 参数实体类
* @author  Shewp
* @date 2016年10月17日
*/
@Entity
@Table(name = "t_parameter")
public class Parameter {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	//系列 如：j9
	@Column(name = "series", nullable = false, length = 16)
	private String series;
	
	//星 如: 02
	@Column(name = "star", nullable = false, length = 16)
	private String star;
	
	//参数名
	@Column(name = "name", nullable = false, length = 64)
	private String name;
	
	//参数码
	@Column(name = "code", nullable = false, length = 64)
	private String code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
