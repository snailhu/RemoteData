package DataAn.reportManager.domain;

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

import DataAn.fileSystem.option.FileDataType;
import DataAn.fileSystem.option.FileType;
import DataAn.reportManager.option.ReportDataType;

@Entity
@Table(name = "t_reportFileSystem")
public class ReportFileSystem {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "parentId", nullable = true)
	private Long parentId;
	
	@Column(name = "fileName", nullable = false, length = 64)
	private String fileName;
	
	//系列 如：j9
	@Column(name = "series", nullable = false, length = 16)
	private String series;
	
	//星 如: 02
	@Column(name = "star", nullable = false, length = 16)
	private String star;
	
	//参数 如: flywheel、top
	@Column(name = "partsType", nullable = false, length = 32)
	private String partsType;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "dataType", nullable = true, length = 16)
	private ReportDataType dataType;
	
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
	
	@Column(name = "cachePath", nullable = true, length = 512)
	private String cachePath;

	@Column(name = "creater", nullable = true, length = 32)
	private String creater;//创建人
	
	@Column(name = "startTime", nullable = true, length = 32)
	private String startTime;
	
	@Column(name = "endTime", nullable = true, length = 32)
	private String endTime ;
	
	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public String getPartsType() {
		return partsType;
	}

	public void setPartsType(String partsType) {
		this.partsType = partsType;
	}

	public ReportDataType getDataType() {
		return dataType;
	}

	public void setDataType(ReportDataType dataType) {
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

	
	public String getCachePath() {
		return cachePath;
	}

	public void setCachePath(String cachePath) {
		this.cachePath = cachePath;
	}

	@Override
	public String toString() {
		return "VirtualFileSystem [id=" + id + ", series=" + series + ", star="
				+ star + ", dataType=" + dataType + ", parentId=" + parentId
				+ ", fileName=" + fileName + ", fileType=" + fileType
				+ ", fileSize=" + fileSize + ", updateDate=" + updateDate
				+ ", mongoFSUUId=" + mongoFSUUId + ", year_month_day="
				+ year_month_day + ", cachePath=" + cachePath + "]";
	}
	
}
