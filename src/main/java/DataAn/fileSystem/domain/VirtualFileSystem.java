package DataAn.fileSystem.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import DataAn.fileSystem.option.DataType;
import DataAn.fileSystem.option.FileType;

/**
* Title: VirtualDirectory
* @Description: 虚拟文件系统实体类
* @author  Shewp
* @date 2016年7月5日
*/
@Entity
@Table(name = "t_virtualFileSystem")
public class VirtualFileSystem {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	//系列 如：j9
	@Column(name = "series", nullable = false)
	private String series;
	
	//星 如: 02
	@Column(name = "star", nullable = false)
	private String star;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "dataType", nullable = true, length = 16)
	private DataType dataType;
	
	@Column(name = "parentId", nullable = true)
	private Long parentId;
	
	@Column(name = "fileName", nullable = false)
	private String fileName;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "fileType", nullable = true, length = 16)
	private FileType fileType;
	
	@Column(name = "fileSize", nullable = true)
	private Float fileSize;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDate", nullable = true)
	private Date updateDate = new Date();
	
	@Column(name = "mongoFSUUId", nullable = true, length = 32)
	private String mongoFSUUId;
	
//	@Column(name = "year", nullable = true, length = 32)
//	private String year;
//	
//	@Column(name = "year_month", nullable = true, length = 32)
//	private String year_month;
	
	@Column(name = "year_month_day", nullable = true, length = 32)
	private String year_month_day;

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

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public Float getFileSize() {
		return fileSize;
	}

	public void setFileSize(Float fileSize) {
		this.fileSize = fileSize;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getMongoFSUUId() {
		return mongoFSUUId;
	}

	public void setMongoFSUUId(String mongoFSUUId) {
		this.mongoFSUUId = mongoFSUUId;
	}

//	public String getYear() {
//		return year;
//	}
//
//	public void setYear(String year) {
//		this.year = year;
//	}
//
//	public String getYear_month() {
//		return year_month;
//	}
//
//	public void setYear_month(String year_month) {
//		this.year_month = year_month;
//	}

	public String getYear_month_day() {
		return year_month_day;
	}

	public void setYear_month_day(String year_month_day) {
		this.year_month_day = year_month_day;
	}

	@Override
	public String toString() {
		return "VirtualFileSystem [id=" + id + ", series=" + series + ", star="
				+ star + ", dataType=" + dataType + ", parentId=" + parentId
				+ ", fileName=" + fileName + ", fileType=" + fileType
				+ ", fileSize=" + fileSize + ", updateDate=" + updateDate
				+ ", mongoFSUUId=" + mongoFSUUId + ", year_month_day="
				+ year_month_day + "]";
	}

	

	
	
}
