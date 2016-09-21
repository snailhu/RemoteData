package DataAn.fileSystem.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
* Title: DateParameters
* @Description: 某一天的参数列表集合
* @author  Shewp
* @date 2016年8月1日
*/
@Entity
@Table(name = "t_dateParameters")
public class DateParameters {

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
	@Column(name = "parameterType", nullable = false, length = 32)
	private String parameterType;
		
	@Column(name = "year_month_day", nullable = false, length = 32)
	private String year_month_day;
	
	@Lob   
	@Basic(fetch = FetchType.LAZY)   
	@Type(type="text")
	@Column(name = "parameters", nullable = false)
	private String parameters;

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

	public String getYear_month_day() {
		return year_month_day;
	}

	public void setYear_month_day(String year_month_day) {
		this.year_month_day = year_month_day;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	
	
}
