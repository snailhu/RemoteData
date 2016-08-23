package DataAn.fileSystem.dto;

import java.util.List;

public class CSVFileDataResultDto<T> {

	private String title; //csv文件头部
	private String cacheFilePath; //边读边存的时候使用
	private List<T> datas; //csv文件内容
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCacheFilePath() {
		return cacheFilePath;
	}
	public void setCacheFilePath(String cacheFilePath) {
		this.cacheFilePath = cacheFilePath;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
	
}
