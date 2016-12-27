package DataAn.reportManager.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_starParam")
public class StarParam {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "paramCode", nullable = false, length = 128)
	private String paramCode;

	@Column(name = "paramName", nullable = false, length = 256)
	private String paramName;

	@Column(name = "creater", nullable = false, length = 64)
	private String creater;

	// 系列 如：j9
	@Column(name = "series", nullable = false, length = 64)
	private String series;

	// 星 如: 02
	@Column(name = "star", nullable = false, length = 64)
	private String star;

	// 参数 如: 电流current、电压voltage、转速speed
	@Column(name = "parameterType", nullable = false, length = 64)
	private String parameterType;

	// 参数 如: flywheel、top
	@Column(name = "partsType", nullable = false, length = 64)
	private String partsType;

	@Column(name = "effeMin", nullable = false, length = 32)
	private double effeMin;

	@Column(name = "effeMax", nullable = false, length = 32)
	private double effeMax;

	@Column(name = "productName", nullable = false, length = 128)
	private String productName;

	@Column(name = "valueUnit", nullable = false, length = 64)
	private String valueUnit;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", nullable = true)
	private Date createDate = new Date();

	public String getValueUnit() {
		return valueUnit;
	}

	public void setValueUnit(String valueUnit) {
		this.valueUnit = valueUnit;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getEffeMin() {
		return effeMin;
	}

	public void setEffeMin(double effeMin) {
		this.effeMin = effeMin;
	}

	public double getEffeMax() {
		return effeMax;
	}

	public void setEffeMax(double effeMax) {
		this.effeMax = effeMax;
	}

	public String getPartsType() {
		return partsType;
	}

	public void setPartsType(String partsType) {
		this.partsType = partsType;
	}

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}
}
