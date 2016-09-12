package DataAn.fileSystem.dto;

import java.io.InputStream;

public class FileDto {

	private String fileName; //上传的文件名称
	private float fileSize; //文件大小
	private String updateDate; //文件更新时间
	private InputStream in; // 上传-下载
	private String filePath; //文件批量下载时用到
	private String parameterType;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public float getFileSize() {
		return fileSize;
	}
	public void setFileSize(float fileSize) {
		this.fileSize = fileSize;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public InputStream getIn() {
		return in;
	}
	public void setIn(InputStream in) {
		this.in = in;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getParameterType() {
		return parameterType;
	}
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}
	@Override
	public String toString() {
		return "FileDto [fileName=" + fileName + ", fileSize=" + fileSize
				+ ", updateDate=" + updateDate + ", filePath=" + filePath
				+ ", parameterType=" + parameterType + "]";
	}
	
	
}
