package DataAn.galaxyManager.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
* Title: Star
* @Description: 一颗星星的实体对象
* @author  Shewp
* @date 2016年7月25日
*/
@Entity
@Table(name = "t_star")
public class Star {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	//系列 如：j9
	@Column(name = "name", nullable = false, length = 64)
	private String name;
	
	@Column(name = "code", nullable = false, length = 64)
	private String code;
	
	@Column(name = "description", nullable = true, length = 512)
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "startRunDate", nullable = false)
	private Date startRunDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", nullable = true)
	private Date createDate = new Date();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seriesId", nullable = false)
	private Series series;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartRunDate() {
		return startRunDate;
	}

	public void setStartRunDate(Date startRunDate) {
		this.startRunDate = startRunDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

	@Override
	public String toString() {
		return "Star [id=" + id + ", name=" + name + ", code=" + code
				+ ", description=" + description + ", startRunDate="
				+ startRunDate + ", createDate=" + createDate + ", series="
				+ series + "]";
	}
	
	
}
