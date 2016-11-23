package DataAn.galaxyManager.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	//参数 如: flywheel、top
	@Column(name = "parameterType", nullable = true, length = 16)
	private String parameterType;
		
	//参数名全称
	@Column(name = "fullName", unique = true, nullable = false, length = 128)
	private String fullName;
	
	//参数名简写
	@Column(name = "simplyName", nullable = true, length = 64)
	private String simplyName;
	
	//参数码
	@Column(name = "code", unique = true, nullable = false, length = 64)
	private String code;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", nullable = true)
	private Date createDate = new Date();
	
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

	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSimplyName() {
		return simplyName;
	}

	public void setSimplyName(String simplyName) {
		this.simplyName = simplyName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
